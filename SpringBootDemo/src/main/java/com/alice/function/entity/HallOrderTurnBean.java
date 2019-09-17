package com.alice.function.entity;

import java.io.Serializable;


/**
 * 查找营业厅人员手里 "待处理 "工单最少的人来分配
 * 2019-6-4 14:16:40
 */
public class HallOrderTurnBean implements Serializable {

    private static final long serialVersionUID = -3260720303714715631L;
    private String login_no;
    private String login_name;
    private String cust_id;
    private String order_id;
    private int orderCount;

    public String getLogin_no() {
        return login_no;
    }

    public void setLogin_no(String login_no) {
        this.login_no = login_no;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}
