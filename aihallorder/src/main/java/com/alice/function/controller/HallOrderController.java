package com.alice.function.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.alice.common.util.DateUtils;
import com.alice.common.util.GetTimeUUID;
import com.alice.common.util.JSONTool;
import com.alice.common.util.ParameterConversion;
import com.alice.function.code.HallCode;
import com.alice.function.entity.*;
import com.alice.function.service.IHallService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 工单服务
 * 2019-6-13 16:27:02
 */
@Controller
@RequestMapping("/hallOrder")
public class HallOrderController {
    private static final Logger logger = LoggerFactory.getLogger(HallOrderController.class);

    @Autowired
    private IHallService hallServiceImpl;

    /**
     * 处理取货工单
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addHallOrderGetGoods", method = RequestMethod.POST)
    public Map<String, Object> addHallOrderGetGoods(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("com.alice.function.controller.HallOrderController.addHallOrderGetGoods");
        Map<String, Object> root = Maps.newHashMap();
        Map<String, Object> body = Maps.newHashMap();
        Map<String, Object> outDate = Maps.newHashMap();

        try {
            HallTalkgoodsContent hallTalkgoodsContent = ParameterConversion.jsonToObject(request, HallTalkgoodsContent.class);

            if (hallTalkgoodsContent != null) {
                SysGroupMsg sysGroupMsg = hallServiceImpl.getGroupInfo(hallTalkgoodsContent.getGroup_id());

                hallTalkgoodsContent.setOrder_status(HallCode.ORDER_STATUS01);
                hallTalkgoodsContent.setOrder_id(GetTimeUUID.getOrderId(HallCode.ORDER_SORCE03));
                hallTalkgoodsContent.setGroup_name(sysGroupMsg.getGroup_name());
                hallTalkgoodsContent.setGoods_name(URLDecoder.decode(hallTalkgoodsContent.getGoods_name(),"UTF-8"));

                boolean flag01 = hallServiceImpl.saveHallTalkgoodsOrder(hallTalkgoodsContent);
//                hallServiceImpl.saveHallTalkgoodsOrderHis(hallTalkgoodsContent);

                HallOrderInfo hallOrderInfo = new HallOrderInfo();
                hallOrderInfo.setCust_id(hallTalkgoodsContent.getCust_id());
                hallOrderInfo.setCust_name(URLDecoder.decode(hallTalkgoodsContent.getCust_name(),"UTF-8"));
                hallOrderInfo.setPhone_no(hallTalkgoodsContent.getPhone_no());
                hallOrderInfo.setCreate_time(new Date(System.currentTimeMillis()));
                hallOrderInfo.setGroup_id(hallTalkgoodsContent.getGroup_id());
                hallOrderInfo.setGroup_name(sysGroupMsg.getGroup_name());
                hallOrderInfo.setOrder_id(hallTalkgoodsContent.getOrder_id());
                hallOrderInfo.setOrder_name("云货架取货工单");
                hallOrderInfo.setOrder_type(HallCode.ORDER_TYPE04);
                hallOrderInfo.setStatus(HallCode.ORDER_STATUS01);
                hallOrderInfo.setChannel_id(HallCode.ORDER_SORCE03);
                //取货工单为自动单
                hallOrderInfo.setOrder_dis_type(HallCode.ORDER_DISTRIBUTE02);
                //        hallOrderInfo.setTemplate_id("工单模板ID");
                //        hallOrderInfo.setTemplate_id("22");
                //        hallOrderInfo.setLogin_no("工单处理工号");
                //        hallOrderInfo.setLogin_no("444");

                boolean flag02 = hallServiceImpl.saveHallOrder(hallOrderInfo);
//                hallServiceImpl.saveHallOrderHis(hallOrderInfo);

                if (flag01 == flag02) {
                    outDate.put("RETURN_CODE", "0");
                    outDate.put("RETURN_MSG", "SUCCESS");
                } else {
                    outDate.put("RETURN_CODE", "1");
                    outDate.put("RETURN_MSG", "ERROR");
                }
            }

        } catch (Exception e) {
            outDate.put("RETURN_CODE", "1");
            outDate.put("RETURN_MSG", "ERROR");
            e.printStackTrace();
        }

        body.put("BODY", outDate);
        root.put("ROOT", body);

        return root;
    }

    /**
     * 条件查询工单列表
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getHallOrderInfos", method = RequestMethod.POST)
    public Map<String, Object> getHallOrderInfos(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("com.alice.function.controller.HallOrderController.getHallOrderInfos");
        Map<String, Object> root = Maps.newHashMap();
        Map<String, Object> body = Maps.newHashMap();
        Map<String, Object> outDate = Maps.newHashMap();

        List<HallOrderInfo> hallOrderInfoList = Lists.newArrayList();

        try {
            HallOrderInfo hallOrderInfoCondition = ParameterConversion.jsonToObject(request, HallOrderInfo.class);
            if (hallOrderInfoCondition == null) {
                outDate.put("RETURN_CODE", "1");
                outDate.put("RETURN_MSG", "ERROR");
                body.put("BODY", outDate);
                root.put("ROOT", body);
                return root;
            }

//            查询之前先判断工号角色
//            如果为值班经理 能查看所有的工单
//            其他角色只能看见转发给自己的工单
            SysLoginMsg sysLoginMsg = hallServiceImpl.getSysUserByLoginNo(hallOrderInfoCondition.getLogin_no());

            // 根据 login_no 获取当天的排班信息的排班角色岗位
            List<HallWorkplanInfo> workerInfoList = hallServiceImpl.getWorkerInfoByLoginId(sysLoginMsg.getId());
            HallWorkplanInfo workerInfo = workerInfoList.get(0);

            if (workerInfo != null && "1".equals(workerInfo.getWork_role())) {
                //当前为值班经理角色 查询当天所有工单信息  不限制查询工单查询权限
                hallOrderInfoCondition.setLogin_no("");
                hallOrderInfoList = hallServiceImpl.getHallOrderInfos(hallOrderInfoCondition);
            } else {
                hallOrderInfoList = hallServiceImpl.getHallOrderInfos(hallOrderInfoCondition);
            }

//            如果需要输出map形式的 就把注释放开
//            Map<String, Object> customerGroupMap = Maps.newHashMap();
//            List<HallOrderInfo> hallOrderInfos = null;
//            for (HallOrderInfo orderInfo : hallOrderInfoList) {
//                if(customerGroupMap.get(orderInfo.getCust_id()) == null){
//                    hallOrderInfos = Lists.newCopyOnWriteArrayList();
//                    hallOrderInfos.add(orderInfo);
//                    customerGroupMap.put(orderInfo.getCust_id(), hallOrderInfos);
//                }else {
//                    hallOrderInfos.add(orderInfo);
//                    customerGroupMap.put(orderInfo.getCust_id(), hallOrderInfos);
//                }
//            }

            outDate.put("RETURN_CODE", "0");
            outDate.put("RETURN_MSG", "SUCCESS");
            outDate.put("ORDERINFOS", hallOrderInfoList);

        } catch (Exception e) {
            outDate.put("RETURN_CODE", "1");
            outDate.put("RETURN_MSG", "ERROR");
            e.printStackTrace();
        }

        body.put("BODY", outDate);
        root.put("ROOT", body);
        return root;
    }

    /**
     * getHallOrderByOrderTypeAndOrderId
     * 根据不同的工单类型 输出不同的工单模板
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getHallOrderByOrderTypeAndOrderId", method = RequestMethod.POST)
    public Map<String, Object> getHallOrderByOrderTypeAndOrderId(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("com.alice.function.controller.HallOrderController.getHallOrderByOrderTypeAndOrderId");
        Map<String, Object> root = Maps.newHashMap();
        Map<String, Object> body = Maps.newHashMap();
        Map<String, Object> outDate = Maps.newHashMap();

        List<Map> outString = Lists.newArrayList();
        List<Map> OutStringDynamic = Lists.newArrayList();

        try {
            HallOrderInfo hallOrderInfoCondition = ParameterConversion.jsonToObject(request, HallOrderInfo.class);
            //  获取工单类型 工单ID groupId
            HallOrderInfo hallOrderInfo = hallServiceImpl.getHallOrderInfoByOrderIdAndGroupId(hallOrderInfoCondition);

            Map<String, String> orderStatusMap = Maps.newHashMap();
            if (HallCode.ORDER_STATUS03.equals(hallOrderInfo.getStatus())) {
                orderStatusMap.put("name", "工单状态");
                orderStatusMap.put("value", "待处理");
                outString.add(orderStatusMap);
            } else if (HallCode.ORDER_STATUS02.equals(hallOrderInfo.getStatus())) {
                orderStatusMap.put("name", "工单状态");
                orderStatusMap.put("value", "派发");
                outString.add(orderStatusMap);
            } else if (HallCode.ORDER_STATUS01.equals(hallOrderInfo.getStatus())) {
                orderStatusMap.put("name", "工单状态");
                orderStatusMap.put("value", "生成");
                outString.add(orderStatusMap);
            } else if (HallCode.ORDER_STATUS04.equals(hallOrderInfo.getStatus())) {
                orderStatusMap.put("name", "工单状态");
                orderStatusMap.put("value", "完成");
                outString.add(orderStatusMap);
            }

            //'工单来源渠道 1、维系挽留，2、业务中心，3、云货架，4、大数据中心，5、营销中心，6、手工单',
            Map<String, String> orderSorceMap = Maps.newHashMap();
            if (HallCode.ORDER_SORCE01.equals(hallOrderInfo.getChannel_id())) {
                orderSorceMap.put("name", "工单来源");
                orderSorceMap.put("value", "维系挽留");
                outString.add(orderSorceMap);
            } else if (HallCode.ORDER_SORCE02.equals(hallOrderInfo.getChannel_id())) {
                orderSorceMap.put("name", "工单来源");
                orderSorceMap.put("value", "业务中心");
                outString.add(orderSorceMap);
            } else if (HallCode.ORDER_SORCE03.equals(hallOrderInfo.getChannel_id())) {
                orderSorceMap.put("name", "工单来源");
                orderSorceMap.put("value", "云货架");
                outString.add(orderSorceMap);
            } else if (HallCode.ORDER_SORCE04.equals(hallOrderInfo.getChannel_id())) {
                orderSorceMap.put("name", "工单来源");
                orderSorceMap.put("value", "大数据中心");
                outString.add(orderSorceMap);
            } else if (HallCode.ORDER_SORCE05.equals(hallOrderInfo.getChannel_id())) {
                orderSorceMap.put("name", "工单来源");
                orderSorceMap.put("value", "营销中心");
                outString.add(orderSorceMap);
            } else if (HallCode.ORDER_SORCE06.equals(hallOrderInfo.getChannel_id())) {
                orderSorceMap.put("name", "工单来源");
                orderSorceMap.put("value", "手工单");
                outString.add(orderSorceMap);
            }

            //'工单派发类型：1、人工，2、自动, 3、抢单',
            Map<String, String> orderDistributeMap = Maps.newHashMap();
            if (HallCode.ORDER_DISTRIBUTE01.equals(hallOrderInfo.getOrder_dis_type())) {
                orderDistributeMap.put("name", "工单派发类型");
                orderDistributeMap.put("value", "人工");
                outString.add(orderDistributeMap);
            } else if (HallCode.ORDER_DISTRIBUTE02.equals(hallOrderInfo.getOrder_dis_type())) {
                orderDistributeMap.put("name", "工单派发类型");
                orderDistributeMap.put("value", "自动");
                outString.add(orderDistributeMap);
            } else if (HallCode.ORDER_DISTRIBUTE03.equals(hallOrderInfo.getOrder_dis_type())) {
                orderDistributeMap.put("name", "工单派发类型");
                orderDistributeMap.put("value", "抢单");
                outString.add(orderDistributeMap);
            }

            Map<String, String> orderGroupMap = Maps.newHashMap();
            orderGroupMap.put("name", "归属组织名称");
            orderGroupMap.put("value", hallOrderInfo.getGroup_name());
            outString.add(orderGroupMap);

            Map<String, String> orderIdMap = Maps.newHashMap();
            orderIdMap.put("name", "订单编号");
            orderIdMap.put("value", hallOrderInfo.getOrder_id());
            outString.add(orderIdMap);

            Map<String, String> orderNameMap = Maps.newHashMap();
            orderNameMap.put("name", "订单名称");
            orderNameMap.put("value", hallOrderInfo.getOrder_name());
            outString.add(orderNameMap);

            Map<String, String> orderCustomPhoneNoMap = Maps.newHashMap();
            orderCustomPhoneNoMap.put("name", "手机号码");
            orderCustomPhoneNoMap.put("value", hallOrderInfo.getPhone_no());
            outString.add(orderCustomPhoneNoMap);

            if (HallCode.ORDER_TYPE04.equals(hallOrderInfo.getOrder_type())) {  //取货工单
                List<HallTalkgoodsContent> hallTalkgoodsContents = hallServiceImpl.getTakegoodsForOrderInfo(hallOrderInfo);
                hallOrderInfo.setHallTalkgoodsContentList(hallTalkgoodsContents);
                HallTalkgoodsContent talkgoodsContent = hallOrderInfo.getHallTalkgoodsContentList().get(0);

                Map<String, String> orderTypeMap = Maps.newHashMap();
                orderTypeMap.put("name", "工单类型");
                orderTypeMap.put("value", "取货工单");
                outString.add(orderTypeMap);

                Map<String, String> orderCustomNameMap = Maps.newHashMap();
                orderCustomNameMap.put("name", "客户名称");
                orderCustomNameMap.put("value", hallOrderInfo.getCust_name());
                outString.add(orderCustomNameMap);

                Map<String, String> orderTakeCodeMap = Maps.newHashMap();
                orderTakeCodeMap.put("name", "取货码");
                orderTakeCodeMap.put("value", talkgoodsContent.getTake_code());
                OutStringDynamic.add(orderTakeCodeMap);

                Map<String, String> orderGoodsNameMap = Maps.newHashMap();
                orderGoodsNameMap.put("name", "商品名称");
                orderGoodsNameMap.put("value", talkgoodsContent.getGoods_name());
                OutStringDynamic.add(orderGoodsNameMap);

                Map<String, String> orderPayNumMap = Maps.newHashMap();
                orderPayNumMap.put("name", "支付金额");
                orderPayNumMap.put("value", talkgoodsContent.getPay_num() + " 元");
                OutStringDynamic.add(orderPayNumMap);

            } else if (HallCode.ORDER_TYPE03.equals(hallOrderInfo.getOrder_type())) {  //TODO 业务办理工单

            } else if (HallCode.ORDER_TYPE02.equals(hallOrderInfo.getOrder_type())) {  // 营销工单
                HallMarketInfoEntity marketInfoEntity = hallServiceImpl.getHallMarketForOrderInfo(hallOrderInfo);

                Map<String, String> orderTypeMap = Maps.newHashMap();
                orderTypeMap.put("name", "工单类型");
                orderTypeMap.put("value", "营销工单");
                outString.add(orderTypeMap);

                Map<String, String> ACT_NAME = Maps.newHashMap();
                ACT_NAME.put("name", "活动名称");
                ACT_NAME.put("value", marketInfoEntity.getACT_NAME());
                OutStringDynamic.add(ACT_NAME);

                Map<String, String> MARKET_CASE_NAME = Maps.newHashMap();
                MARKET_CASE_NAME.put("name", "营销案名称");
                MARKET_CASE_NAME.put("value", marketInfoEntity.getMARKET_CASE_NAME());
                OutStringDynamic.add(MARKET_CASE_NAME);


                Map<String, String> COMMEND_CONTENT = Maps.newHashMap();
                COMMEND_CONTENT.put("name", "推荐介绍");
                COMMEND_CONTENT.put("value", marketInfoEntity.getCOMMEND_CONTENT());
                OutStringDynamic.add(COMMEND_CONTENT);

                Map<String, String> MARKET_OPERATION = Maps.newHashMap();
                MARKET_OPERATION.put("name", "营销话术");
                MARKET_OPERATION.put("value", marketInfoEntity.getMARKET_OPERATION());
                OutStringDynamic.add(MARKET_OPERATION);

                Map<String, String> START_DATE = Maps.newHashMap();
                START_DATE.put("name", "生效时间");
                START_DATE.put("value", DateUtils.format(marketInfoEntity.getSTART_DATE()));
                OutStringDynamic.add(START_DATE);

                Map<String, String> END_DATE = Maps.newHashMap();
                END_DATE.put("name", "失效时间");
                END_DATE.put("value", DateUtils.format(marketInfoEntity.getEND_DATE()));
                OutStringDynamic.add(END_DATE);

//                Map<String, String> ID_NO = Maps.newHashMap();
//                ID_NO.put("name", "用户IDNO");
//                ID_NO.put("value", marketInfoEntity.getID_NO());
//                OutStringDynamic.add(ID_NO);
//
//                Map<String, String> PHONE_APP = Maps.newHashMap();
//                PHONE_APP.put("name", "广告");
//                PHONE_APP.put("value", marketInfoEntity.getPHONE_APP());
//                OutStringDynamic.add(PHONE_APP);
//
//                Map<String, String> OPERATION_TREE = Maps.newHashMap();
//                OPERATION_TREE.put("name", "OPERATION_TREE");
//                OPERATION_TREE.put("value", marketInfoEntity.getOPERATION_TREE());
//                OutStringDynamic.add(OPERATION_TREE);
//
//
//
//                Map<String, String> ACT_ID = Maps.newHashMap();
//                ACT_ID.put("name", "活动ID");
//                ACT_ID.put("value", marketInfoEntity.getACT_ID());
//                OutStringDynamic.add(ACT_ID);
//
//                Map<String, String> ONEKEY_ORDER_FLAG = Maps.newHashMap();
//                ONEKEY_ORDER_FLAG.put("name", "一键订购标识");
//                ONEKEY_ORDER_FLAG.put("value", marketInfoEntity.getONEKEY_ORDER_FLAG());
//                OutStringDynamic.add(ONEKEY_ORDER_FLAG);
//
//                Map<String, String> LANUCH_CHANNEL = Maps.newHashMap();
//                LANUCH_CHANNEL.put("name", "发射渠道");
//                LANUCH_CHANNEL.put("value", marketInfoEntity.getLANUCH_CHANNEL());
//                OutStringDynamic.add(LANUCH_CHANNEL);
//
//                Map<String, String> BUSI_TYPE = Maps.newHashMap();
//                BUSI_TYPE.put("name", "业务类型");
//                BUSI_TYPE.put("value", marketInfoEntity.getBUSI_TYPE());
//                OutStringDynamic.add(BUSI_TYPE);
//
//                Map<String, String> POSITION_ID = Maps.newHashMap();
//                POSITION_ID.put("name", "运营位ID");
//                POSITION_ID.put("value", marketInfoEntity.getPOSITION_ID());
//                OutStringDynamic.add(POSITION_ID);
//
//                Map<String, String> CONTENT_TYPE = Maps.newHashMap();
//                CONTENT_TYPE.put("name", "内容类型");
//                CONTENT_TYPE.put("value", marketInfoEntity.getCONTENT_TYPE());
//                OutStringDynamic.add(CONTENT_TYPE);
//
//                Map<String, String> MARKET_CASE_ID = Maps.newHashMap();
//                MARKET_CASE_ID.put("name", "营销案ID");
//                MARKET_CASE_ID.put("value", marketInfoEntity.getMARKET_CASE_ID());
//                OutStringDynamic.add(MARKET_CASE_ID);
//
//                Map<String, String> CUST_GROUP_ID = Maps.newHashMap();
//                CUST_GROUP_ID.put("name", "活动的客户群ID");
//                CUST_GROUP_ID.put("value", marketInfoEntity.getCUST_GROUP_ID());
//                OutStringDynamic.add(CUST_GROUP_ID);
//
//                Map<String, String> PROD_TYPE = Maps.newHashMap();
//                PROD_TYPE.put("name", "资费类型");
//                PROD_TYPE.put("value", marketInfoEntity.getPROD_TYPE());
//                OutStringDynamic.add(PROD_TYPE);
//
//                Map<String, String> PROD_ID = Maps.newHashMap();
//                PROD_ID.put("name", "产品ID");
//                PROD_ID.put("value", marketInfoEntity.getPROD_ID());
//                OutStringDynamic.add(PROD_ID);
//
//                Map<String, String> CHN_TYPE = Maps.newHashMap();
//                CHN_TYPE.put("name", "渠道类型");
//                CHN_TYPE.put("value", marketInfoEntity.getCHN_TYPE());
//                OutStringDynamic.add(CHN_TYPE);
//

//
//                Map<String, String> LANUCH_NO = Maps.newHashMap();
//                LANUCH_NO.put("name", "发射工号");
//                LANUCH_NO.put("value", marketInfoEntity.getLANUCH_NO());
//                OutStringDynamic.add(LANUCH_NO);
//
//                Map<String, String> ORDER_TRAC_SERIAL = Maps.newHashMap();
//                ORDER_TRAC_SERIAL.put("name", "工单轨迹流水");
//                ORDER_TRAC_SERIAL.put("value", marketInfoEntity.getORDER_TRAC_SERIAL());
//                OutStringDynamic.add(ORDER_TRAC_SERIAL);
//
//                Map<String, String> MEANS_NAME = Maps.newHashMap();
//                MEANS_NAME.put("name", "MEANS_NAME");
//                MEANS_NAME.put("value", marketInfoEntity.getMEANS_NAME());
//                OutStringDynamic.add(MEANS_NAME);
//
//                Map<String, String> SMS_INSTRUCTION = Maps.newHashMap();
//                SMS_INSTRUCTION.put("name", "短信指令");
//                SMS_INSTRUCTION.put("value", marketInfoEntity.getSMS_INSTRUCTION());
//                OutStringDynamic.add(SMS_INSTRUCTION);
//
//                Map<String, String> RES_CODE = Maps.newHashMap();
//                RES_CODE.put("name", "终端资源编码");
//                RES_CODE.put("value", marketInfoEntity.getRES_CODE());
//                OutStringDynamic.add(RES_CODE);
//
//
//                Map<String, String> MEANS_ID = Maps.newHashMap();
//                MEANS_ID.put("name", "档次ID");
//                MEANS_ID.put("value", marketInfoEntity.getMEANS_ID());
//                OutStringDynamic.add(MEANS_ID);
//
//
//                Map<String, String> STATUS_CODE = Maps.newHashMap();
//                STATUS_CODE.put("name", "工单状态");
//                STATUS_CODE.put("value", marketInfoEntity.getSTATUS_CODE());
//                OutStringDynamic.add(STATUS_CODE);
//
//                Map<String, String> OPER_DATE = Maps.newHashMap();
//                OPER_DATE.put("name", "操作时间");
//                OPER_DATE.put("value", DateUtils.format(marketInfoEntity.getEND_DATE()));
//                OutStringDynamic.add(OPER_DATE);
//
//                Map<String, String> PROD_PRCDESC = Maps.newHashMap();
//                PROD_PRCDESC.put("name", "资费名称");
//                PROD_PRCDESC.put("value", marketInfoEntity.getPROD_PRCDESC());
//                OutStringDynamic.add(PROD_PRCDESC);
//
//                Map<String, String> TERMI_NAME = Maps.newHashMap();
//                TERMI_NAME.put("name", "终端名称");
//                TERMI_NAME.put("value", marketInfoEntity.getTERMI_NAME());
//                OutStringDynamic.add(TERMI_NAME);
//
//                Map<String, String> PROD_PRCD = Maps.newHashMap();
//                PROD_PRCD.put("name", "资费ID");
//                PROD_PRCD.put("value", marketInfoEntity.getPROD_PRCD());
//                OutStringDynamic.add(PROD_PRCD);
//
//
//                Map<String, String> SHORT_MSG = Maps.newHashMap();
//                SHORT_MSG.put("name", "短信内容");
//                SHORT_MSG.put("value", marketInfoEntity.getSHORT_MSG());
//                OutStringDynamic.add(SHORT_MSG);
//
//                Map<String, String> COMMEND_LINK = Maps.newHashMap();
//                COMMEND_LINK.put("name", "推荐链接");
//                COMMEND_LINK.put("value", marketInfoEntity.getCOMMEND_LINK());
//                OutStringDynamic.add(COMMEND_LINK);
//
//                Map<String, String> COMMEND_TITLE = Maps.newHashMap();
//                COMMEND_TITLE.put("name", "标题");
//                COMMEND_TITLE.put("value", marketInfoEntity.getCOMMEND_TITLE());
//                OutStringDynamic.add(COMMEND_TITLE);


//                for (HallMarketInfoEntity marketInfoEntity : hallMarketListForOrderInfo) {
//                    List<Map> outMarketListField = Lists.newArrayList();
//                    Map<String, Object> outDateDymic = Maps.newHashMap();
//
//                    outDateDymic.put("name", marketInfoEntity.getCOMMEND_TITLE());
//                    outDateDymic.put("value", outMarketListField);
//
//                    OutStringDynamic.add(outDateDymic);
//
//                }

                Map<String, String> orderCustomNameMap = Maps.newHashMap();
                orderCustomNameMap.put("name", "客户名称");
                orderCustomNameMap.put("value", hallOrderInfo.getCust_name());
                outString.add(orderCustomNameMap);

            } else if (HallCode.ORDER_TYPE01.equals(hallOrderInfo.getOrder_type())) {  //TODO 关怀工单

            }

            outDate.put("RETURN_CODE", "0");
            outDate.put("RETURN_MSG", "SUCCESS");
            outDate.put("ORDERINFOS", hallOrderInfo);
            outDate.put("DYNAMICORDERINFOS", OutStringDynamic);

        } catch (Exception e) {
            outDate.put("RETURN_CODE", "1");
            outDate.put("RETURN_MSG", "ERROR");
            body.put("BODY", outDate);
            root.put("ROOT", body);
            e.printStackTrace();
        }

        body.put("BODY", outDate);
        root.put("ROOT", body);
        return root;
    }

    /**
     * 获取今天门店排班人员信息
     * 取货工单
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWorkerListByNowAndGroupId", method = RequestMethod.POST)
    public Map<String, Object> getWorkerListByNowAndGroupId(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("com.alice.function.controller.HallOrderController.getWorkerListByNowAndGroupId");

        Map<String, Object> root = Maps.newHashMap();
        Map<String, Object> body = Maps.newHashMap();
        Map<String, Object> outDate = Maps.newHashMap();

        try {
            HallWorkplanInfo workplanInfo = ParameterConversion.jsonToObject(request, HallWorkplanInfo.class);

            List<HallWorkplanInfo> workplanInfoList = hallServiceImpl.getWorkerListByNowAndGroupId(workplanInfo);
            outDate.put("RETURN_CODE", "0");
            outDate.put("RETURN_MSG", "SUCCESS");
            outDate.put("ORDERINFOS", workplanInfoList);
        } catch (Exception e) {
            outDate.put("RETURN_CODE", "1");
            outDate.put("RETURN_MSG", "ERROR");
            body.put("BODY", outDate);
            root.put("ROOT", body);
            e.printStackTrace();
        }
        body.put("BODY", outDate);
        root.put("ROOT", body);
        return root;
    }


    /**
     * 取货工单转派
     * 入参 orderId and loginNo
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateTransferLoginNoByOrderId", method = RequestMethod.POST)
    public Map<String, Object> updateTransferLoginNoByOrderId(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("com.alice.function.controller.HallOrderController.updateTransferLoginNoByOrderId");

        Map<String, Object> root = Maps.newHashMap();
        Map<String, Object> body = Maps.newHashMap();
        Map<String, Object> outDate = Maps.newHashMap();

        try {
            HallOrderTurn hallOrderTurn = ParameterConversion.jsonToObject(request, HallOrderTurn.class);

            SysLoginMsg sysUserByLoginNo = hallServiceImpl.getSysUserByLoginId(hallOrderTurn.getLogin_no());
            hallOrderTurn.setLogin_no(sysUserByLoginNo.getLogin_no());

            boolean flagForHallOrderTurn = hallServiceImpl.updateTransferLoginNoByOrderIdForHallOrderTurn(hallOrderTurn);
            boolean flagForHallOrderInfo = hallServiceImpl.updateTransferLoginNoByOrderIdForHallOrderInfo(hallOrderTurn);

            outDate.put("RETURN_CODE", "0");
            outDate.put("RETURN_MSG", "SUCCESS");
//            outDate.put("ORDERINFOS", workplanInfoList);
        } catch (Exception e) {
            outDate.put("RETURN_CODE", "1");
            outDate.put("RETURN_MSG", "ERROR");
            body.put("BODY", outDate);
            root.put("ROOT", body);
            e.printStackTrace();
        }
        body.put("BODY", outDate);
        root.put("ROOT", body);
        return root;
    }

    /**
     * 工单处理情况
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePickupOrderStatusByOrderId", method = RequestMethod.POST)
    public Map<String, Object> updatePickupOrderStatusByOrderId(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("com.alice.function.controller.HallOrderController.updatePickupOrderStatusByOrderId");

        Map<String, Object> root = Maps.newHashMap();
        Map<String, Object> body = Maps.newHashMap();
        Map<String, Object> outDate = Maps.newHashMap();

        try {
            HallOrderInfo orderInfoCondition = ParameterConversion.jsonToObject(request, HallOrderInfo.class);
            // 更改状态 添加工单完成反馈描述  实际操作是 删除工单记录 插入历史表
            HallOrderInfo hallOrderInfo = hallServiceImpl.getHallOrderInfoByOrderIdAndGroupId(orderInfoCondition);

            // 工单状态 完成
            hallOrderInfo.setStatus(HallCode.ORDER_STATUS04);
            hallOrderInfo.setOrder_desc(orderInfoCondition.getOrder_desc());
            hallOrderInfo.setOrder_note(orderInfoCondition.getOrder_note());
            hallOrderInfo.setOrder_eval(orderInfoCondition.getOrder_eval());
            hallOrderInfo.setFinish_time(new Date(System.currentTimeMillis()));

            boolean flag = hallServiceImpl.saveHallOrderHis(hallOrderInfo);
            if (flag) {
                hallServiceImpl.delFinishOrderByOrderId(hallOrderInfo);
            }

            outDate.put("RETURN_CODE", "0");
            outDate.put("RETURN_MSG", "SUCCESS");

        } catch (Exception e) {
            e.printStackTrace();
        }

        body.put("BODY", outDate);
        root.put("ROOT", body);
        return root;
    }

    /**
     * 处理营销工单
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addHallMarketOrder", method = RequestMethod.POST)
    public Map<String, Object> addHallMarketOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("com.alice.function.controller.HallOrderController.addHallMarketOrder");

        Map<String, Object> root = Maps.newHashMap();
        Map<String, Object> body = Maps.newHashMap();
        Map<String, Object> outDate = Maps.newHashMap();

        try {

            JSONObject jsonObject = ParameterConversion.jsonToFormat(request);
            String custinfoStr = jsonObject.getJSONObject("ROOT").getJSONObject("BODY").getJSONObject("BUSI_INFO").getString("CUST_INFO");
            CustEntity beanFJsonStr = (CustEntity) JSONTool.getBean_F_JsonStr(custinfoStr, CustEntity.class);

//            beanFJsonStr.setGROUP_ID(GetTimeUUID.getOrderId(HallCode.ORDER_SORCE05));
            logger.info("CUST_INFO==> " + beanFJsonStr.toString());

            String marketList = jsonObject.getJSONObject("ROOT").getJSONObject("BODY").getJSONObject("BUSI_INFO").getJSONObject("MARKETLIST").getString("MARKET_INFO");
            List<HallMarketInfoEntity> marketInfoEntities = JSON.parseArray(marketList, HallMarketInfoEntity.class);


            for (HallMarketInfoEntity marketInfoEntity : marketInfoEntities) {

                HallOrderInfo hallOrderInfo = new HallOrderInfo();
                hallOrderInfo.setCust_id(beanFJsonStr.getID_NO());
                hallOrderInfo.setCust_name(beanFJsonStr.getCUST_NAME());
                hallOrderInfo.setPhone_no(beanFJsonStr.getPHONE_NO());
                hallOrderInfo.setCreate_time(new Date(System.currentTimeMillis()));
                hallOrderInfo.setGroup_id(beanFJsonStr.getGROUP_ID());

                hallOrderInfo.setOrder_id(GetTimeUUID.getOrderId(HallCode.ORDER_SORCE05));
                hallOrderInfo.setOrder_name("营销工单");
                hallOrderInfo.setOrder_type(HallCode.ORDER_TYPE02);
                hallOrderInfo.setStatus(HallCode.ORDER_STATUS01);
                hallOrderInfo.setChannel_id(HallCode.ORDER_SORCE05);

                hallOrderInfo.setId_no(beanFJsonStr.getID_NO());
                hallOrderInfo.setFace_image_id(beanFJsonStr.getFACE_IMAGE_URL());

                hallOrderInfo.setOrder_dis_type(HallCode.ORDER_DISTRIBUTE02);
                SysGroupMsg sysGroupMsg = hallServiceImpl.getGroupInfo(beanFJsonStr.getGROUP_ID());
                hallOrderInfo.setGroup_name(sysGroupMsg.getGroup_name());

                hallServiceImpl.saveHallOrder(hallOrderInfo);

                marketInfoEntity.setORDER_ID(hallOrderInfo.getOrder_id());
                hallServiceImpl.saveMarketInfo(marketInfoEntity);
            }

            outDate.put("RETURN_CODE", "0");
            outDate.put("RETURN_MSG", "SUCCESS");
        } catch (Exception e) {
            outDate.put("RETURN_CODE", "1");
            outDate.put("RETURN_MSG", "ERROR");
            e.printStackTrace();
        }

        body.put("BODY", outDate);
        root.put("ROOT", body);

        return root;
    }

    /**
     * 工单完成记录 条件查询
     * by login_no 查询
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getHallOrderInfoHisForLoginNo", method = RequestMethod.POST)
    public Map<String, Object> getHallOrderInfoHisForLoginNo(HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.info("com.alice.function.controller.HallOrderController.getHallOrderInfoHisForLoginNo");
        Map<String, Object> root = Maps.newHashMap();
        Map<String, Object> body = Maps.newHashMap();
        Map<String, Object> outDate = Maps.newHashMap();

        List<HallOrderInfo> returnHallOrderInfoList = Lists.newArrayList();
        try {
            HallOrderInfo orderInfoCondition = ParameterConversion.jsonToObject(request, HallOrderInfo.class);
            if (orderInfoCondition == null) {
                outDate.put("RETURN_CODE", "1");
                outDate.put("RETURN_MSG", "ERROR");
                body.put("BODY", outDate);
                root.put("ROOT", body);
                return root;
            }

            returnHallOrderInfoList = hallServiceImpl.getHallOrderInfoForLoginNoAndConditinon(orderInfoCondition);

            outDate.put("RETURN_CODE", "0");
            outDate.put("RETURN_MSG", "SUCCESS");
            outDate.put("ORDERINFOS", returnHallOrderInfoList);
        } catch (Exception e) {
            outDate.put("RETURN_CODE", "1");
            outDate.put("RETURN_MSG", "ERROR");
            e.printStackTrace();
        }
        body.put("BODY", outDate);
        root.put("ROOT", body);
        return root;
    }
}
