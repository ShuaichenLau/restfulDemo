package com.alice.function.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应表记录 hall_order_turn
 * 2019-6-4 14:16:23
 * @author liusc
 */
public class HallOrderTurn implements Serializable {

    private static final long serialVersionUID = 8134125555520222871L;
    private String id;  // int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
    private String order_Id;  // varchar(255) NOT NULL COMMENT '工单ID',
    private String login_no;  // varchar(255) DEFAULT NULL COMMENT '处理工号ID',
    private String cust_id; //客户ID  该工单关联客户ID
    private String status;  // varchar(2) DEFAULT NULL COMMENT '处理状态',
    private Date order_time;  // datetime DEFAULT NULL COMMENT '工单时间',
    private Date dispose_date;  // datetime DEFAULT NULL COMMENT '处理日期',

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(String order_Id) {
        this.order_Id = order_Id;
    }

    public String getLogin_no() {
        return login_no;
    }

    public void setLogin_no(String login_no) {
        this.login_no = login_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Date getDispose_date() {
        return dispose_date;
    }

    public void setDispose_date(Date dispose_date) {
        this.dispose_date = dispose_date;
    }
}
