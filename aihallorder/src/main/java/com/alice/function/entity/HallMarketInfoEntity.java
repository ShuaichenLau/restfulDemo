package com.alice.function.entity;

import java.io.Serializable;
import java.util.Date;

public class HallMarketInfoEntity implements Serializable {

    private static final long serialVersionUID = 8681581111230111115L;

    private String OPERATION_TREE;  // "",
    private String id; // int(11) NOT NULL AUTO_INCREMENT COMMENT '营销工单id',
    private String ID_NO; // varchar(255) DEFAULT NULL COMMENT '用户的IDNO',
    private String PHONE_APP; // varchar(255) DEFAULT NULL COMMENT '广告',
    private Date END_DATE; // datetime DEFAULT NULL COMMENT '失效时间',
    private String ACT_ID; // varchar(255) DEFAULT NULL COMMENT '活动ID',
    private String PHONE_NO; // varchar(255) DEFAULT NULL COMMENT '手机号码',
    private String ONEKEY_ORDER_FLAG; // varchar(10) DEFAULT NULL COMMENT '一键订购标识 Y:可以一键订购   N：不能一键订购\r\n',
    private String LANUCH_CHANNEL; // varchar(255) DEFAULT NULL COMMENT '发射渠道',
    private String BUSI_TYPE; // varchar(255) DEFAULT NULL COMMENT '业务类型 <00 重点推荐 01 流量经营，02 套餐推荐， 03 终端销售， 04 数据业务， 05 客户服务>\r\n',
    private String POSITION_ID; // varchar(255) DEFAULT NULL COMMENT '运营位ID',
    private String CONTENT_TYPE; // varchar(255) DEFAULT NULL COMMENT '内容类型\r\n01 活动， 02 资费， 03 终端， 04 广告  ',
    private String MARKET_CASE_ID; // varchar(255) DEFAULT NULL COMMENT '营销案ID',
    private String CUST_GROUP_ID; // varchar(255) DEFAULT NULL COMMENT '活动的客户群ID',
    private String PROD_TYPE; // varchar(255) DEFAULT NULL COMMENT '资费类型\r\nCONTENT_TYPE = 02    0 主套餐， 1叠加套餐\r\n',
    private String PROD_ID; // varchar(255) DEFAULT NULL COMMENT '产品ID',
    private String CHN_TYPE; // varchar(255) DEFAULT NULL COMMENT '渠道类型',
    private String ACT_NAME; // varchar(255) DEFAULT NULL COMMENT '活动名称',
    private String LANUCH_NO; // varchar(255) DEFAULT NULL COMMENT '发射工号',
    private String ORDER_TRAC_SERIAL; // varchar(255) DEFAULT NULL COMMENT '工单轨迹流水',
    private String MEANS_NAME; // varchar(255) DEFAULT NULL,
    private String SMS_INSTRUCTION; // varchar(255) DEFAULT NULL COMMENT '短信指令',
    private String RES_CODE; // varchar(255) DEFAULT NULL COMMENT '终端资源编码',
    private String MARKET_OPERATION; // varchar(255) DEFAULT NULL COMMENT '营销话术',
    private String MEANS_ID; // varchar(255) DEFAULT NULL COMMENT '档次ID',
    private Date START_DATE; // datetime DEFAULT NULL COMMENT '生效时间',
    private String STATUS_CODE; // varchar(255) DEFAULT NULL COMMENT '工单状态',
    private Date OPER_DATE; // datetime DEFAULT NULL COMMENT '操作时间',
    private String PROD_PRCDESC; // varchar(255) DEFAULT NULL COMMENT '资费名称',
    private String TERMI_NAME; // varchar(255) DEFAULT NULL COMMENT '终端名称',
    private String PROD_PRCD; // varchar(255) DEFAULT NULL COMMENT '资费ID',
    private String COMMEND_CONTENT; // varchar(255) DEFAULT NULL COMMENT '推荐介绍',
    private String ORDER_ID; // varchar(255) DEFAULT NULL COMMENT '工单ID',
    private String SHORT_MSG; // varchar(255) DEFAULT NULL COMMENT '短信内容',
    private String COMMEND_LINK; // varchar(255) DEFAULT NULL COMMENT '推荐链接',
    private String COMMEND_TITLE; // varchar(255) DEFAULT NULL COMMENT '标题',
    private String MARKET_CASE_NAME; // varchar(255) DEFAULT NULL COMMENT '营销案名称',

    @Override
    public String toString() {
        return "MarketInfoEntity{" +
                "ID_NO='" + ID_NO + '\'' +
                ", PHONE_APP='" + PHONE_APP + '\'' +
                ", OPERATION_TREE='" + OPERATION_TREE + '\'' +
                ", END_DATE=" + END_DATE +
                ", ACT_ID='" + ACT_ID + '\'' +
                ", PHONE_NO='" + PHONE_NO + '\'' +
                ", ONEKEY_ORDER_FLAG='" + ONEKEY_ORDER_FLAG + '\'' +
                ", LANUCH_CHANNEL='" + LANUCH_CHANNEL + '\'' +
                ", BUSI_TYPE='" + BUSI_TYPE + '\'' +
                ", POSITION_ID='" + POSITION_ID + '\'' +
                ", CONTENT_TYPE='" + CONTENT_TYPE + '\'' +
                ", MARKET_CASE_ID='" + MARKET_CASE_ID + '\'' +
                ", CUST_GROUP_ID='" + CUST_GROUP_ID + '\'' +
                ", PROD_TYPE='" + PROD_TYPE + '\'' +
                ", PROD_ID='" + PROD_ID + '\'' +
                ", CHN_TYPE='" + CHN_TYPE + '\'' +
                ", ACT_NAME='" + ACT_NAME + '\'' +
                ", LANUCH_NO='" + LANUCH_NO + '\'' +
                ", ORDER_TRAC_SERIAL='" + ORDER_TRAC_SERIAL + '\'' +
                ", MEANS_NAME='" + MEANS_NAME + '\'' +
                ", SMS_INSTRUCTION='" + SMS_INSTRUCTION + '\'' +
                ", RES_CODE='" + RES_CODE + '\'' +
                ", MARKET_OPERATION='" + MARKET_OPERATION + '\'' +
                ", MEANS_ID='" + MEANS_ID + '\'' +
                ", START_DATE=" + START_DATE +
                ", STATUS_CODE='" + STATUS_CODE + '\'' +
                ", OPER_DATE=" + OPER_DATE +
                ", PROD_PRCDESC='" + PROD_PRCDESC + '\'' +
                ", TERMI_NAME='" + TERMI_NAME + '\'' +
                ", PROD_PRCD='" + PROD_PRCD + '\'' +
                ", COMMEND_CONTENT='" + COMMEND_CONTENT + '\'' +
                ", ORDER_ID='" + ORDER_ID + '\'' +
                ", SHORT_MSG='" + SHORT_MSG + '\'' +
                ", COMMEND_LINK='" + COMMEND_LINK + '\'' +
                ", COMMEND_TITLE='" + COMMEND_TITLE + '\'' +
                ", MARKET_CASE_NAME='" + MARKET_CASE_NAME + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getID_NO() {
        return ID_NO;
    }

    public void setID_NO(String ID_NO) {
        this.ID_NO = ID_NO;
    }

    public String getPHONE_APP() {
        return PHONE_APP;
    }

    public void setPHONE_APP(String PHONE_APP) {
        this.PHONE_APP = PHONE_APP;
    }

    public String getOPERATION_TREE() {
        return OPERATION_TREE;
    }

    public void setOPERATION_TREE(String OPERATION_TREE) {
        this.OPERATION_TREE = OPERATION_TREE;
    }

    public Date getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(Date END_DATE) {
        this.END_DATE = END_DATE;
    }

    public String getACT_ID() {
        return ACT_ID;
    }

    public void setACT_ID(String ACT_ID) {
        this.ACT_ID = ACT_ID;
    }

    public String getPHONE_NO() {
        return PHONE_NO;
    }

    public void setPHONE_NO(String PHONE_NO) {
        this.PHONE_NO = PHONE_NO;
    }

    public String getONEKEY_ORDER_FLAG() {
        return ONEKEY_ORDER_FLAG;
    }

    public void setONEKEY_ORDER_FLAG(String ONEKEY_ORDER_FLAG) {
        this.ONEKEY_ORDER_FLAG = ONEKEY_ORDER_FLAG;
    }

    public String getLANUCH_CHANNEL() {
        return LANUCH_CHANNEL;
    }

    public void setLANUCH_CHANNEL(String LANUCH_CHANNEL) {
        this.LANUCH_CHANNEL = LANUCH_CHANNEL;
    }

    public String getBUSI_TYPE() {
        return BUSI_TYPE;
    }

    public void setBUSI_TYPE(String BUSI_TYPE) {
        this.BUSI_TYPE = BUSI_TYPE;
    }

    public String getPOSITION_ID() {
        return POSITION_ID;
    }

    public void setPOSITION_ID(String POSITION_ID) {
        this.POSITION_ID = POSITION_ID;
    }

    public String getCONTENT_TYPE() {
        return CONTENT_TYPE;
    }

    public void setCONTENT_TYPE(String CONTENT_TYPE) {
        this.CONTENT_TYPE = CONTENT_TYPE;
    }

    public String getMARKET_CASE_ID() {
        return MARKET_CASE_ID;
    }

    public void setMARKET_CASE_ID(String MARKET_CASE_ID) {
        this.MARKET_CASE_ID = MARKET_CASE_ID;
    }

    public String getCUST_GROUP_ID() {
        return CUST_GROUP_ID;
    }

    public void setCUST_GROUP_ID(String CUST_GROUP_ID) {
        this.CUST_GROUP_ID = CUST_GROUP_ID;
    }

    public String getPROD_TYPE() {
        return PROD_TYPE;
    }

    public void setPROD_TYPE(String PROD_TYPE) {
        this.PROD_TYPE = PROD_TYPE;
    }

    public String getPROD_ID() {
        return PROD_ID;
    }

    public void setPROD_ID(String PROD_ID) {
        this.PROD_ID = PROD_ID;
    }

    public String getCHN_TYPE() {
        return CHN_TYPE;
    }

    public void setCHN_TYPE(String CHN_TYPE) {
        this.CHN_TYPE = CHN_TYPE;
    }

    public String getACT_NAME() {
        return ACT_NAME;
    }

    public void setACT_NAME(String ACT_NAME) {
        this.ACT_NAME = ACT_NAME;
    }

    public String getLANUCH_NO() {
        return LANUCH_NO;
    }

    public void setLANUCH_NO(String LANUCH_NO) {
        this.LANUCH_NO = LANUCH_NO;
    }

    public String getORDER_TRAC_SERIAL() {
        return ORDER_TRAC_SERIAL;
    }

    public void setORDER_TRAC_SERIAL(String ORDER_TRAC_SERIAL) {
        this.ORDER_TRAC_SERIAL = ORDER_TRAC_SERIAL;
    }

    public String getMEANS_NAME() {
        return MEANS_NAME;
    }

    public void setMEANS_NAME(String MEANS_NAME) {
        this.MEANS_NAME = MEANS_NAME;
    }

    public String getSMS_INSTRUCTION() {
        return SMS_INSTRUCTION;
    }

    public void setSMS_INSTRUCTION(String SMS_INSTRUCTION) {
        this.SMS_INSTRUCTION = SMS_INSTRUCTION;
    }

    public String getRES_CODE() {
        return RES_CODE;
    }

    public void setRES_CODE(String RES_CODE) {
        this.RES_CODE = RES_CODE;
    }

    public String getMARKET_OPERATION() {
        return MARKET_OPERATION;
    }

    public void setMARKET_OPERATION(String MARKET_OPERATION) {
        this.MARKET_OPERATION = MARKET_OPERATION;
    }

    public String getMEANS_ID() {
        return MEANS_ID;
    }

    public void setMEANS_ID(String MEANS_ID) {
        this.MEANS_ID = MEANS_ID;
    }

    public Date getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(Date START_DATE) {
        this.START_DATE = START_DATE;
    }

    public String getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(String STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public Date getOPER_DATE() {
        return OPER_DATE;
    }

    public void setOPER_DATE(Date OPER_DATE) {
        this.OPER_DATE = OPER_DATE;
    }

    public String getPROD_PRCDESC() {
        return PROD_PRCDESC;
    }

    public void setPROD_PRCDESC(String PROD_PRCDESC) {
        this.PROD_PRCDESC = PROD_PRCDESC;
    }

    public String getTERMI_NAME() {
        return TERMI_NAME;
    }

    public void setTERMI_NAME(String TERMI_NAME) {
        this.TERMI_NAME = TERMI_NAME;
    }

    public String getPROD_PRCD() {
        return PROD_PRCD;
    }

    public void setPROD_PRCD(String PROD_PRCD) {
        this.PROD_PRCD = PROD_PRCD;
    }

    public String getCOMMEND_CONTENT() {
        return COMMEND_CONTENT;
    }

    public void setCOMMEND_CONTENT(String COMMEND_CONTENT) {
        this.COMMEND_CONTENT = COMMEND_CONTENT;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getSHORT_MSG() {
        return SHORT_MSG;
    }

    public void setSHORT_MSG(String SHORT_MSG) {
        this.SHORT_MSG = SHORT_MSG;
    }

    public String getCOMMEND_LINK() {
        return COMMEND_LINK;
    }

    public void setCOMMEND_LINK(String COMMEND_LINK) {
        this.COMMEND_LINK = COMMEND_LINK;
    }

    public String getCOMMEND_TITLE() {
        return COMMEND_TITLE;
    }

    public void setCOMMEND_TITLE(String COMMEND_TITLE) {
        this.COMMEND_TITLE = COMMEND_TITLE;
    }

    public String getMARKET_CASE_NAME() {
        return MARKET_CASE_NAME;
    }

    public void setMARKET_CASE_NAME(String MARKET_CASE_NAME) {
        this.MARKET_CASE_NAME = MARKET_CASE_NAME;
    }

}
