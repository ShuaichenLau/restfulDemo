package com.alice.function.service;


import com.alice.function.entity.*;

import java.util.List;

/**
 * @author liusc
 * 2019-6-17 11:25:04
 */
public interface IHallService {

    boolean saveHallTalkgoodsOrder(HallTalkgoodsContent hallTalkgoodsContent);

    void saveHallTalkgoodsOrderHis(HallTalkgoodsContent hallTalkgoodsContent);

    boolean saveHallOrder(HallOrderInfo hallOrderInfo);

    boolean saveHallOrderHis(HallOrderInfo hallOrderInfo);

    HallEquipment getEquipmentAndGroupIdByEquipment(HallEquipment equipment);

    List<HallTalkgoodsContent> getAutoHallOrderOfGetGoods();

    List<HallWorkplanInfo> getHallWorkerOfToday(Integer work_role);

    List<HallOrderTurn> getHallOrderTurnCount();

    boolean addHallOrderTurn(HallOrderTurn hallOrderTurn);

    boolean updateHallOrderInfoStatus(HallOrderInfo hallOrderInfo);

    List<HallOrderTurnBean> getMinimumHoldingWorkOrderRecord();

    boolean delHallOrderInfo(HallOrderInfo orderInfo);

    boolean updateHallTalkGoodsContent(HallTalkgoodsContent hallTalkgoodsContent);

    List<HallOrderInfo> getHallOrderInfos(HallOrderInfo hallOrderInfoCondition);

    SysLoginMsg getSysUserByLoginNo(String login_no);

    List<HallWorkplanInfo> getWorkerInfoByLoginId(String loginMsgId);

    List<HallTalkgoodsContent> getTakegoodsForOrderInfo(HallOrderInfo hallOrderInfo);

    HallOrderInfo getHallOrderInfoByOrderIdAndGroupId(HallOrderInfo hallOrderInfoCondition);

    List<HallWorkplanInfo> getWorkerListByNowAndGroupId(HallWorkplanInfo workplanInfo);

    boolean updateTransferLoginNoByOrderIdForHallOrderTurn(HallOrderTurn hallOrderTurn);

    boolean updateTransferLoginNoByOrderIdForHallOrderInfo(HallOrderTurn hallOrderTurn);

    SysLoginMsg getSysUserByLoginId(String login_no);

    boolean delFinishOrderByOrderId(HallOrderInfo hallOrderInfo);

    boolean saveMarketInfo(HallMarketInfoEntity marketInfoEntity);

    SysGroupMsg getGroupInfo(String group_id);

    HallMarketInfoEntity getHallMarketForOrderInfo(HallOrderInfo hallOrderInfo);

    List<HallOrderInfo> getAutoHallOrderOfMarket();

    boolean updateHallMarketStatusForHallOrderId(HallMarketInfoEntity entity);

    List<HallOrderInfo> getHallOrderInfoForLoginNoAndConditinon(HallOrderInfo orderInfoCondition);
}
