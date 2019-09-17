package com.alice.function.service.impl;

import com.alice.function.entity.*;
import com.alice.function.mapper.HallMapper;
import com.alice.function.service.IHallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liusc
 * 2019-6-17 11:25:28
 */
@Service("hallServiceImpl")
public class HallServiceImpl implements IHallService {

    private static final Logger logger = LoggerFactory.getLogger(HallServiceImpl.class);

    @Autowired
    private HallMapper hallMapper;

    @Override
    public boolean saveHallTalkgoodsOrder(HallTalkgoodsContent hallTalkgoodsContent) {
        return hallMapper.saveHallTalkgoodsOrder(hallTalkgoodsContent);
    }

    /**
     * 历史表
     * @param hallTalkgoodsContent
     */
    @Override
    public void saveHallTalkgoodsOrderHis(HallTalkgoodsContent hallTalkgoodsContent) {
        hallMapper.saveHallTalkgoodsOrderHis(hallTalkgoodsContent);
    }

    @Override
    public boolean saveHallOrder(HallOrderInfo hallOrderInfo) {
        return hallMapper.saveHallOrder(hallOrderInfo);
    }

    @Override
    public boolean saveHallOrderHis(HallOrderInfo hallOrderInfo) {
        return hallMapper.saveHallOrderHis(hallOrderInfo);
    }

    /**
     * 根据设备MAC地址查询 设备ID和所在门店group_id
     * @param equipment
     * @return
     */
    @Override
    public HallEquipment getEquipmentAndGroupIdByEquipment(HallEquipment equipment) {
        return hallMapper.getEquipmentAndGroupIdByEquipment(equipment);
    }

    @Override
    public List<HallTalkgoodsContent> getAutoHallOrderOfGetGoods() {
        return hallMapper.getAutoHallOrderOfGetGoods();
    }

    /**
     * 获取今天的排班的人员
     * @return
     */
    @Override
    public List<HallWorkplanInfo> getHallWorkerOfToday(Integer work_role) {
        return hallMapper.getHallWorkerOfToday(work_role);
    }

    /**
     * 获取hallOrderTurn
     * @return
     */
    @Override
    public List<HallOrderTurn> getHallOrderTurnCount() {
        return hallMapper.getHallOrderTurnCount();
    }

    @Override
    public boolean addHallOrderTurn(HallOrderTurn hallOrderTurn) {
        return hallMapper.addHallOrderTurn(hallOrderTurn);
    }

    @Override
    public boolean updateHallOrderInfoStatus(HallOrderInfo hallOrderInfo) {
        return hallMapper.updateHallOrderInfoStatus(hallOrderInfo);
    }

    @Override
    public List<HallOrderTurnBean> getMinimumHoldingWorkOrderRecord() {
        return hallMapper.getMinimumHoldingWorkOrderRecord();
    }

    @Override
    public boolean delHallOrderInfo(HallOrderInfo orderInfo) {
        return hallMapper.delHallOrderInfo(orderInfo);
    }

    @Override
    public boolean updateHallTalkGoodsContent(HallTalkgoodsContent hallTalkgoodsContent) {
        return hallMapper.updateHallTalkGoodsContent(hallTalkgoodsContent);
    }

    @Override
    public List<HallOrderInfo> getHallOrderInfos(HallOrderInfo hallOrderInfoCondition) {
        return hallMapper.getHallOrderInfos(hallOrderInfoCondition);
    }

    @Override
    public SysLoginMsg getSysUserByLoginNo(String getSysUserLoginNo) {
        return hallMapper.getSysUserByLoginNo(getSysUserLoginNo);
    }

    @Override
    public List<HallWorkplanInfo> getWorkerInfoByLoginId(String loginMsgId) {
        return hallMapper.getWorkerInfoByLoginId(loginMsgId);
    }

    @Override
    public List<HallTalkgoodsContent> getTakegoodsForOrderInfo(HallOrderInfo hallOrderInfo) {
        return hallMapper.getTakegoodsForOrderInfo(hallOrderInfo);
    }

    @Override
    public HallOrderInfo getHallOrderInfoByOrderIdAndGroupId(HallOrderInfo hallOrderInfoCondition) {
        return hallMapper.getHallOrderInfoByOrderIdAndGroupId(hallOrderInfoCondition);
    }

    @Override
    public List<HallWorkplanInfo> getWorkerListByNowAndGroupId(HallWorkplanInfo workplanInfo) {
        return hallMapper.getWorkerListByNowAndGroupId(workplanInfo);
    }

    @Override
    public boolean updateTransferLoginNoByOrderIdForHallOrderTurn(HallOrderTurn hallOrderTurn) {
        return hallMapper.updateTransferLoginNoByOrderIdForHallOrderTurn(hallOrderTurn);
    }

    @Override
    public boolean updateTransferLoginNoByOrderIdForHallOrderInfo(HallOrderTurn hallOrderTurn) {
        return hallMapper.updateTransferLoginNoByOrderIdForHallOrderInfo(hallOrderTurn);
    }

    @Override
    public SysLoginMsg getSysUserByLoginId(String login_no) {
        return hallMapper.getSysUserByLoginId(login_no);
    }

    @Override
    public boolean delFinishOrderByOrderId(HallOrderInfo hallOrderInfo) {
        return hallMapper.delFinishOrderByOrderId(hallOrderInfo);
    }

    @Override
    public boolean saveMarketInfo(HallMarketInfoEntity marketInfoEntity) {
        return hallMapper.saveMarketInfo(marketInfoEntity);
    }

    @Override
    public SysGroupMsg getGroupInfo(String group_id) {
        return hallMapper.getGroupInfo(group_id);
    }

    @Override
    public HallMarketInfoEntity getHallMarketForOrderInfo(HallOrderInfo hallOrderInfo) {
        return hallMapper.getHallMarketForOrderInfo(hallOrderInfo);
    }

    @Override
    public List<HallOrderInfo> getAutoHallOrderOfMarket() {
        return hallMapper.getAutoHallOrderOfMarket();
    }

    @Override
    public boolean updateHallMarketStatusForHallOrderId(HallMarketInfoEntity hallMarketInfoEntity) {
        return hallMapper.updateHallMarketStatusForHallOrderId(hallMarketInfoEntity);
    }

    @Override
    public List<HallOrderInfo> getHallOrderInfoForLoginNoAndConditinon(HallOrderInfo orderInfoCondition) {
        return hallMapper.getHallOrderInfoForLoginNoAndConditinon(orderInfoCondition);
    }


}