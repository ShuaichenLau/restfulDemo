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
 * 自动派发取货工单
 * SpringBoot启动之后会执行工单
 */
@Component
@Order(value = 1)
public class TalkgoodsAutoWorkOrder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(TalkgoodsAutoWorkOrder.class);

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
                        Thread.sleep(HallCode.fiveSecone);
                        getAutoHallOrderOfGetGoods();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * 取货工单的处理
     */
    public void getAutoHallOrderOfGetGoods() {
        logger.info("com.alice.function.crontab.TalkgoodsAutoWorkOrder.getAutoHallOrderOfGetGoods");
        List<HallTalkgoodsContent> hallTalkgoodsContentList = Lists.newCopyOnWriteArrayList();
        List<HallWorkplanInfo> hallWorkplanInfos = Lists.newCopyOnWriteArrayList();
        List<HallWorkplanInfo> workerListAM = Lists.newCopyOnWriteArrayList();
        List<HallWorkplanInfo> workerListPM = Lists.newCopyOnWriteArrayList();

        // 查询接收到的工单 取货工单
        hallTalkgoodsContentList = hallServiceImpl.getAutoHallOrderOfGetGoods();

        //查询今天的营业厅导购排班人员
        hallWorkplanInfos = hallServiceImpl.getHallWorkerOfToday(HallCode.Work_role04);
        String msg;

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
                msg = DistributionOfWorkOrders(hallTalkgoodsContentList, workerListAM);
            } else {
                //下午班的业务人员 + 全天班人员
                msg = DistributionOfWorkOrders(hallTalkgoodsContentList, workerListPM);
            }
            logger.info(msg);
        } else {
            logger.info("当天导购排班为空");
        }
    }

    /**
     * 取货工单派发
     *
     * @param talkgoodsContentList 取货工单
     * @param WorkerList           当前排班人员团队
     */
    public String DistributionOfWorkOrders(List<HallTalkgoodsContent> talkgoodsContentList, List<HallWorkplanInfo> WorkerList) {
        List<HallOrderTurn> hallOrderTurns = Lists.newCopyOnWriteArrayList();

        if (WorkerList.isEmpty()) {
            return "当天没有可以派发的排班人员...";
        } else if (talkgoodsContentList.isEmpty()) {
            return "当天没有可以派发的取货工单...";
        }

        //获取今天的营业厅人员 手里的工单情况
        hallOrderTurns = hallServiceImpl.getHallOrderTurnCount();

        try {
            logger.info("如果有营业厅人员 根据当前工单的客户ID 和营业厅人员手里工单的客户ID匹配 匹配成功 添加 hallOrderTurn 记录");
            Map<String, HallOrderTurn> hallOrderTurnMap = Maps.newHashMap();
            for (HallOrderTurn hallOrderTurn : hallOrderTurns) {
                hallOrderTurnMap.put(hallOrderTurn.getCust_id(), hallOrderTurn);
            }

            Iterator<HallTalkgoodsContent> talkgoodsContent = talkgoodsContentList.iterator();
            while (talkgoodsContent.hasNext()) {
                HallTalkgoodsContent goods = (HallTalkgoodsContent) talkgoodsContent.next();
                HallOrderTurn hallOrderTurn = new HallOrderTurn();
                hallOrderTurn.setDispose_date(DateUtils.getDate());
                hallOrderTurn.setCust_id(goods.getCust_id());
                hallOrderTurn.setStatus(HallCode.ORDER_STATUS03);
                hallOrderTurn.setOrder_Id(goods.getOrder_id());
                hallOrderTurn.setOrder_time(new Date(System.currentTimeMillis()));

                if (hallOrderTurnMap.get(goods.getCust_id()) == null) {
                    logger.info("没有匹配到客户ID 转发给手里工单最少的营业厅导购人员");
                    List<HallOrderTurnBean> minimumHoldingWorkOrderRecord = hallServiceImpl.getMinimumHoldingWorkOrderRecord();
                    HallOrderTurnBean hallOrderTurnBean = new HallOrderTurnBean();
                    if (!minimumHoldingWorkOrderRecord.isEmpty()) {
                        hallOrderTurnBean = minimumHoldingWorkOrderRecord.get(0);
                    }
                    hallOrderTurn.setLogin_no(hallOrderTurnBean.getLogin_no());
                } else {
                    logger.info("已经匹配到客户ID 转发给当前导购");
                    hallOrderTurn.setLogin_no(hallOrderTurnMap.get(goods.getCust_id()).getLogin_no());
                }

                goods.setOrder_status(HallCode.ORDER_STATUS03);
                boolean hallTalkFlag = hallServiceImpl.updateHallTalkGoodsContent(goods);

                boolean addhallOrderTurnFlag = hallServiceImpl.addHallOrderTurn(hallOrderTurn);

                if (addhallOrderTurnFlag) {
                    HallOrderInfo hallOrderInfo = new HallOrderInfo();
                    hallOrderInfo.setStatus(HallCode.ORDER_STATUS03);
                    hallOrderInfo.setOrder_id(goods.getOrder_id());
                    hallOrderInfo.setLogin_no(hallOrderTurn.getLogin_no());
                    hallServiceImpl.updateHallOrderInfoStatus(hallOrderInfo);

                    logger.info("该工单已经进入 '待处理' 状态 更新 hall_order表中的记录");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "当天取货工单派发完成";
    }
}
