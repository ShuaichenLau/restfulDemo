package com.alice.function.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HallOrderInfo implements Serializable {

    private static final long serialVersionUID = 6319482823688048754L;
    private Integer id;  // int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
    private String order_id;  // varchar(24) DEFAULT NULL COMMENT '工单编号',
    private String order_name;  // varchar(100) DEFAULT NULL COMMENT '工单名称',
    private String template_id;  // varchar(24) DEFAULT NULL COMMENT '工单模板id',
    private String group_id;  // int(10) DEFAULT NULL COMMENT '组织节点',
    private String group_name;  // varchar(255) DEFAULT NULL COMMENT '组织名称',
    private String order_type;  // int(2) DEFAULT NULL COMMENT '工单类型 1、关怀工单，2、营销工单，3、业务办理工单，4、取货工单，',
    private String status;  // varchar(2) DEFAULT NULL COMMENT '工单状态：1、生成，2、派发、3、待处理 4、完成',
    private String channel_id;  // int(2) DEFAULT NULL COMMENT '工单来源渠道 1、维系挽留，2、业务中心，3、云货架，4、大数据中心，5、营销中心，6、手工单',
    private String login_no;  // varchar(24) DEFAULT NULL COMMENT '工单处理工号',
    private String order_dis_type;  // int(2) DEFAULT NULL COMMENT '工单派发类型：1、人工，2、自动, 3、抢单',
    private String cust_id;  // int(12) DEFAULT NULL COMMENT '客户id',
    private String cust_name;  // varchar(12) DEFAULT NULL COMMENT '客户姓名',
    private String phone_no;  // int(11) DEFAULT NULL COMMENT '客户手机号',
    private Date create_time;  // datetime DEFAULT NULL COMMENT '工单生成时间',
    private Date finish_time;  // datetime DEFAULT NULL COMMENT '工单完成时间',
    private String order_desc;  // varchar(255) DEFAULT NULL COMMENT '描述',
    private String order_note;  // varchar(255) DEFAULT NULL COMMENT '备注',
    private String order_eval;  // varchar(10) DEFAULT NULL COMMENT '用户满意度',
    private String face_image_id;   // int(11) DEFAULT NULL COMMENT '人脸的URL',
    private String id_no;   // int(11) DEFAULT NULL COMMENT '用户的IDNO',

    //条件查询时间段 比较 create_time 是否在这个范围
    private String BEGIN_DATE;    //开始时间
    private String END_DATE;  //结束时间

    private String login_Name;  //工单处理工号名称
    private List<HallTalkgoodsContent> hallTalkgoodsContentList;  //取货工单

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public String getOrder_eval() {
        return order_eval;
    }

    public void setOrder_eval(String order_eval) {
        this.order_eval = order_eval;
    }

    public String getBEGIN_DATE() {
        return BEGIN_DATE;
    }

    public void setBEGIN_DATE(String BEGIN_DATE) {
        this.BEGIN_DATE = BEGIN_DATE;
    }

    public String getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }

    public String getFace_image_id() {
        return face_image_id;
    }

    public void setFace_image_id(String face_image_id) {
        this.face_image_id = face_image_id;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getLogin_Name() {
        return login_Name;
    }

    public void setLogin_Name(String login_Name) {
        this.login_Name = login_Name;
    }

    public List<HallTalkgoodsContent> getHallTalkgoodsContentList() {
        return hallTalkgoodsContentList;
    }

    public void setHallTalkgoodsContentList(List<HallTalkgoodsContent> hallTalkgoodsContentList) {
        this.hallTalkgoodsContentList = hallTalkgoodsContentList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getLogin_no() {
        return login_no;
    }

    public void setLogin_no(String login_no) {
        this.login_no = login_no;
    }

    public String getOrder_dis_type() {
        return order_dis_type;
    }

    public void setOrder_dis_type(String order_dis_type) {
        this.order_dis_type = order_dis_type;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Date finish_time) {
        this.finish_time = finish_time;
    }

    public String getOrder_desc() {
        return order_desc;
    }

    public void setOrder_desc(String order_desc) {
        this.order_desc = order_desc;
    }
}
