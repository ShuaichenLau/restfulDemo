package com.alice.function.entity;

import java.io.Serializable;
import java.util.Date;

public class HallEquipment implements Serializable {

    private static final long serialVersionUID = 4514227885592190193L;
    private String equipment_id; // int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
    private String hall_id; // bigint(24) NOT NULL COMMENT '门店ID',
    private String hall_name; // varchar(100) NOT NULL COMMENT '门店名称',
    private String floor_id; // bigint(24) NOT NULL COMMENT '楼层id',
    private String floor_name; // varchar(50) NOT NULL COMMENT '楼层名称',
    private String equipment_sn; // varchar(24) NOT NULL COMMENT '设备序列号',
    private String equipment_name; // varchar(20) NOT NULL COMMENT '设备名称',
    private String equipment_code; // varchar(20) NOT NULL COMMENT '设备型号',
    private String equipment_ablity; // varchar(500) DEFAULT NULL COMMENT '设备功能',
    private String equipment_producer; // varchar(50) DEFAULT NULL COMMENT '设备厂商',
    private String equipment_size; // varchar(10) DEFAULT NULL COMMENT '设备屏幕尺寸',
    private String equipment_ppi; // varchar(10) DEFAULT NULL COMMENT '设备屏幕分辨率',
    private String equipment_color; // varchar(10) DEFAULT NULL COMMENT '设备颜色',
    private String equipment_status; // varchar(6) DEFAULT NULL COMMENT '设备状态on、开启；off、关闭',
    private String equipment_type; // int(2) NOT NULL COMMENT '设备类型 1、表示摄像头；2、大屏机；3:PC机；4:触摸屏；5：电子价签',
    private String equipment_mac; // varchar(24) DEFAULT NULL COMMENT '设备MAC地址',
    private String equipment_ip; // varchar(24) DEFAULT NULL COMMENT '设备IP地址',
    private String res_id; // int(6) DEFAULT NULL COMMENT '资源id',
    private String camera_length; // decimal(10,0) DEFAULT NULL COMMENT '摄像头摆放所在位置长',
    private String camera_width; // decimal(10,0) DEFAULT NULL COMMENT '摄像头摆放所在位置宽',
    private String group_id; // varchar(24) DEFAULT NULL COMMENT '组织节点id',
    private String group_name; // varchar(24) DEFAULT NULL COMMENT '组织节点名称',
    private Date op_time; // date NOT NULL COMMENT '操作时间',
    private String op_code; // varchar(5) NOT NULL COMMENT '操作代码',
    private String login_no; // varchar(20) NOT NULL COMMENT '操作人',
    private String login_accept; // decimal(18,0) NOT NULL COMMENT '操作流水',
    private String op_note; // varchar(500) DEFAULT NULL COMMENT '操作备注',


    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getHall_id() {
        return hall_id;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }

    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }

    public String getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(String floor_id) {
        this.floor_id = floor_id;
    }

    public String getFloor_name() {
        return floor_name;
    }

    public void setFloor_name(String floor_name) {
        this.floor_name = floor_name;
    }

    public String getEquipment_sn() {
        return equipment_sn;
    }

    public void setEquipment_sn(String equipment_sn) {
        this.equipment_sn = equipment_sn;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getEquipment_code() {
        return equipment_code;
    }

    public void setEquipment_code(String equipment_code) {
        this.equipment_code = equipment_code;
    }

    public String getEquipment_ablity() {
        return equipment_ablity;
    }

    public void setEquipment_ablity(String equipment_ablity) {
        this.equipment_ablity = equipment_ablity;
    }

    public String getEquipment_producer() {
        return equipment_producer;
    }

    public void setEquipment_producer(String equipment_producer) {
        this.equipment_producer = equipment_producer;
    }

    public String getEquipment_size() {
        return equipment_size;
    }

    public void setEquipment_size(String equipment_size) {
        this.equipment_size = equipment_size;
    }

    public String getEquipment_ppi() {
        return equipment_ppi;
    }

    public void setEquipment_ppi(String equipment_ppi) {
        this.equipment_ppi = equipment_ppi;
    }

    public String getEquipment_color() {
        return equipment_color;
    }

    public void setEquipment_color(String equipment_color) {
        this.equipment_color = equipment_color;
    }

    public String getEquipment_status() {
        return equipment_status;
    }

    public void setEquipment_status(String equipment_status) {
        this.equipment_status = equipment_status;
    }

    public String getEquipment_type() {
        return equipment_type;
    }

    public void setEquipment_type(String equipment_type) {
        this.equipment_type = equipment_type;
    }

    public String getEquipment_mac() {
        return equipment_mac;
    }

    public void setEquipment_mac(String equipment_mac) {
        this.equipment_mac = equipment_mac;
    }

    public String getEquipment_ip() {
        return equipment_ip;
    }

    public void setEquipment_ip(String equipment_ip) {
        this.equipment_ip = equipment_ip;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getCamera_length() {
        return camera_length;
    }

    public void setCamera_length(String camera_length) {
        this.camera_length = camera_length;
    }

    public String getCamera_width() {
        return camera_width;
    }

    public void setCamera_width(String camera_width) {
        this.camera_width = camera_width;
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

    public Date getOp_time() {
        return op_time;
    }

    public void setOp_time(Date op_time) {
        this.op_time = op_time;
    }

    public String getOp_code() {
        return op_code;
    }

    public void setOp_code(String op_code) {
        this.op_code = op_code;
    }

    public String getLogin_no() {
        return login_no;
    }

    public void setLogin_no(String login_no) {
        this.login_no = login_no;
    }

    public String getLogin_accept() {
        return login_accept;
    }

    public void setLogin_accept(String login_accept) {
        this.login_accept = login_accept;
    }

    public String getOp_note() {
        return op_note;
    }

    public void setOp_note(String op_note) {
        this.op_note = op_note;
    }
}
