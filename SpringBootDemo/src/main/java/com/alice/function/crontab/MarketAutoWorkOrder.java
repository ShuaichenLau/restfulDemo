package com.alice.function.crontab;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.alice.common.util.DateUtils;
import com.alice.function.code.HallCode;
import com.alice.function.entity.*;
import com.alice.function.service.IHallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 自动派发营销工单
 * SpringBoot启动之后会执行工单
 */
@Component
@Order(value = 2)
public class MarketAutoWorkOrder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MarketAutoWorkOrder.class);

    @Autowired
    private IHallService hallServiceImpl;

    @Override
    public void run(String... strings) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(HallCode.tenSecone);
                        getAutoHallOrderOfMarket();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 营销工单自动派发 派发给营销员
     * 派发逻辑与
     */
    public void getAutoHallOrderOfMarket() {
        logger.info("com.alice.function.crontab.MarketAutoWorkOrder.getAutoHallOrderOfMarket");
        List<HallWorkplanInfo> workerListAM = Lists.newCopyOnWriteArrayList();
        List<HallWorkplanInfo> workerListPM = Lists.newCopyOnWriteArrayList();

        List<HallWorkplanInfo> hallWorkplanInfos = Lists.newCopyOnWriteArrayList();
        List<HallOrderInfo> hallOrderInfoListForMarket = Lists.newCopyOnWriteArrayList();

        //获取今天所有的营销工单 hallOrderList
        hallOrderInfoListForMarket = hallServiceImpl.getAutoHallOrderOfMarket();

        //查询今天的营业厅行销人员 排班人员
        hallWorkplanInfos = hallServiceImpl.getHallWorkerOfToday(HallCode.Work_role03);

        String msg = "";
        if (hallWorkplanInfos != null && !hallWorkplanInfos.isEmpty()) {
            for (HallWorkplanInfo info : hallWorkplanInfos) {

                if (HallCode.Work_Shift_03.equals(info.getWork_shift())) {
                    workerListAM.add(info);
                    workerListPM.add(info);
                } else if (HallCode.Work_Shift_01.equals(info.getWork_shift())) {
                    workerListAM.add(info);
                } else if (HallCode.Work_Shift_02.equals(info.getWork_shift())) {
                    workerListPM.add(info);
                }
            }
            if (DateUtils.getAMorPM() == 0) {
                //上午班的业务人员 + 全天班人员
                msg = DistributionOfHallMarketOrder(hallOrderInfoListForMarket, workerListAM);
            } else {
                //下午班的业务人员 + 全天班人员
                msg = DistributionOfHallMarketOrder(hallOrderInfoListForMarket, workerListPM);
            }
            logger.info(msg);
        } else {
            logger.info("当天营销排班为空");
        }
    }


    /**
     * 营销工单派发
     *
     * @param hallOrderInfoListForMarket 营销工单
     * @param WorkerList                 当前排班人员团队
     */
    public String DistributionOfHallMarketOrder(List<HallOrderInfo> hallOrderInfoListForMarket, List<HallWorkplanInfo> WorkerList) {
        List<HallOrderTurn> hallOrderTurns = Lists.newCopyOnWriteArrayList();

        if (WorkerList.isEmpty()) {
            return "当天没有可以派发的行销人员...";
        } else if (hallOrderInfoListForMarket.isEmpty()) {
            return "当天没有可以派发的营销工单...";
        }

        //获取今天的营业厅人员 手里的工单情况
        hallOrderTurns = hallServiceImpl.getHallOrderTurnCount();

        try {
            logger.info("如果有营业厅人员 根据当前工单的客户ID 和营业厅人员手里工单的客户ID匹配 匹配成功 添加 hallOrderOfMarket 记录");
            Map<String, HallOrderTurn> hallOrderTurnMap = Maps.newHashMap();
            for (HallOrderTurn hallOrderTurn : hallOrderTurns) {
                hallOrderTurnMap.put(hallOrderTurn.getCust_id(), hallOrderTurn);
            }

            Iterator<HallOrderInfo> hallMakret = hallOrderInfoListForMarket.iterator();
            while (hallMakret.hasNext()) {
                HallOrderInfo orderInfo = hallMakret.next();

                HallOrderTurn hallOrderTurn = new HallOrderTurn();
                hallOrderTurn.setDispose_date(DateUtils.getDate());
                hallOrderTurn.setCust_id(orderInfo.getCust_id());
                hallOrderTurn.setStatus(HallCode.ORDER_STATUS03);
                hallOrderTurn.setOrder_Id(orderInfo.getOrder_id());
                hallOrderTurn.setOrder_time(new Date(System.currentTimeMillis()));

                if (hallOrderTurnMap.get(orderInfo.getCust_id()) == null) {
                    logger.info("没有匹配到客户ID 转发给手里工单最少的营业厅行销专员人员");
                    List<HallOrderTurnBean> minimumHoldingWorkOrderRecord = hallServiceImpl.getMinimumHoldingWorkOrderRecord();
                    HallOrderTurnBean hallOrderTurnBean = new HallOrderTurnBean();
                    if (!minimumHoldingWorkOrderRecord.isEmpty()) {
                        hallOrderTurnBean = minimumHoldingWorkOrderRecord.get(0);
                    }
                    hallOrderTurn.setLogin_no(hallOrderTurnBean.getLogin_no());
                } else {
                    logger.info("已经匹配到客户ID 转发给当前行销人员");
                    hallOrderTurn.setLogin_no(hallOrderTurnMap.get(orderInfo.getCust_id()).getLogin_no());
                }

                //更新hallMarket表
                HallMarketInfoEntity hallMarketInfoEntity = new HallMarketInfoEntity();
                hallMarketInfoEntity.setSTATUS_CODE(HallCode.ORDER_STATUS03);
                hallMarketInfoEntity.setORDER_ID(orderInfo.getOrder_id());
                boolean flag = hallServiceImpl.updateHallMarketStatusForHallOrderId(hallMarketInfoEntity);

                //添加hallOrderTurn
                boolean addhallOrderTurnFlag = hallServiceImpl.addHallOrderTurn(hallOrderTurn);

                //营销工单表更新记录 hallOrderInfo
                if (addhallOrderTurnFlag) {
                    orderInfo.setStatus(HallCode.ORDER_STATUS03);
                    orderInfo.setLogin_no(hallOrderTurn.getLogin_no());
                    hallServiceImpl.updateHallOrderInfoStatus(orderInfo);
                    logger.info("该工单已经进入 '待处理' 状态 更新 hall_order表中的记录");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "当天营销工单派发完成";
    }
}
