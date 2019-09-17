package com.alice.function.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 取货工单内容
 * @author liusc
 * 2019-6-17 11:28:20
 */
public class HallTalkgoodsContent implements Serializable {

    private static final long serialVersionUID = 5801232039349786146L;
    private String cust_id; //客户id
    private String cust_name;   //客户名称	手机号码
    private String phone_no;    //客户手机号
    private String take_code; //取货码
    private String goods_id;    //商品id
    private String goods_name;  //商品名称
    private String group_id;    //营业厅归属组织id
    private String group_name;  //营业厅名称
    private Date order_time;  //下单时间
    private String pay_num; //支付金额(单位元) 精确到小数点后两位
    private String cust_photo;  //照片base64位编码
    private String order_id;    //  订单id
    private Integer id;
    private String order_status;    //工单状态

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getCust_photo() {
        return cust_photo;
    }

    public void setCust_photo(String cust_photo) {
        this.cust_photo = cust_photo;
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

    public String getTake_code() {
        return take_code;
    }

    public void setTake_code(String take_code) {
        this.take_code = take_code;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
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

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public String getPay_num() {
        return pay_num;
    }

    public void setPay_num(String pay_num) {
        this.pay_num = pay_num;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HallTalkgoodsContent{" +
                "cust_id='" + cust_id + '\'' +
                ", cust_name='" + cust_name + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", take_code='" + take_code + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", group_id='" + group_id + '\'' +
                ", group_name='" + group_name + '\'' +
                ", order_time=" + order_time +
                ", pay_num='" + pay_num + '\'' +
                ", cust_photo='" + cust_photo + '\'' +
                ", order_id='" + order_id + '\'' +
                ", id=" + id +
                '}';
    }
}




