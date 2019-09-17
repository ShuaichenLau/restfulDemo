package com.alice.function.mapper;

import com.alice.function.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * dao
 * @author liusc
 * 2019-6-17 11:29:03
 */
@Component
public interface HallMapper {

    boolean saveHallTalkgoodsOrder(HallTalkgoodsContent talkgoodsContent);

    void saveHallTalkgoodsOrderHis(HallTalkgoodsContent hallTalkgoodsContent);

    boolean saveHallOrder(HallOrderInfo hallOrderInfo);

    boolean saveHallOrderHis(HallOrderInfo hallOrderInfo);

    HallEquipment getEquipmentAndGroupIdByEquipment(HallEquipment equipment);

    List<HallTalkgoodsContent> getAutoHallOrderOfGetGoods();

    List<HallWorkplanInfo> getHallWorkerOfToday(@Param("work_role") Integer work_role);

    List<HallOrderTurn> getHallOrderTurnCount();

    boolean addHallOrderTurn(HallOrderTurn hallOrderTurn);

    boolean updateHallOrderInfoStatus(HallOrderInfo hallOrderInfo);

    List<HallOrderTurnBean> getMinimumHoldingWorkOrderRecord();

    boolean delHallOrderInfo(HallOrderInfo orderInfo);

    boolean updateHallTalkGoodsContent(HallTalkgoodsContent hallTalkgoodsContent);

    List<HallOrderInfo> getHallOrderInfos(HallOrderInfo hallOrderInfoCondition);

    SysLoginMsg getSysUserByLoginNo(@Param("loginNo") String loginNo);

    List<HallWorkplanInfo> getWorkerInfoByLoginId(@Param("loginMsgId") String loginMsgId);

    List<HallTalkgoodsContent> getTakegoodsForOrderInfo(HallOrderInfo hallOrderInfo);

    HallOrderInfo getHallOrderInfoByOrderIdAndGroupId(HallOrderInfo hallOrderInfoCondition);

    List<HallWorkplanInfo> getWorkerListByNowAndGroupId(HallWorkplanInfo workplanInfo);

    boolean updateTransferLoginNoByOrderIdForHallOrderTurn(HallOrderTurn hallOrderTurn);

    boolean updateTransferLoginNoByOrderIdForHallOrderInfo(HallOrderTurn hallOrderTurn);

    SysLoginMsg getSysUserByLoginId(String login_no);

    boolean delFinishOrderByOrderId(HallOrderInfo hallOrderInfo);

    boolean saveMarketInfo(HallMarketInfoEntity marketInfoEntity);

    SysGroupMsg getGroupInfo(@Param("group_id")String group_id);

    HallMarketInfoEntity getHallMarketForOrderInfo(HallOrderInfo hallOrderInfo);

    List<HallOrderInfo> getAutoHallOrderOfMarket();

    boolean updateHallMarketStatusForHallOrderId(HallMarketInfoEntity hallMarketInfoEntity);

    List<HallOrderInfo> getHallOrderInfoForLoginNoAndConditinon(HallOrderInfo orderInfoCondition);
}
