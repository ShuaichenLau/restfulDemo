package com.alice.function.entity;

import java.io.Serializable;
import java.util.Date;

public class SysGroupMsg implements Serializable {

    private static final long serialVersionUID = 4058704347485592172L;
    private String group_id;  // varchar(32) NOT NULL COMMENT '行政机构编码',
    private String group_name;  // varchar(64) DEFAULT NULL COMMENT '行政机构名称',
    private String class_code;  // varchar(32) DEFAULT NULL COMMENT '渠道分类编码',
    private String region_group_id;  // varchar(32) DEFAULT NULL COMMENT '地市标识',
    private String status;  // varchar(2) DEFAULT NULL COMMENT 'Y:有效 N:无效',
    private int root_distance;  // int(2) DEFAULT NULL COMMENT '与根节点距离',
    private String has_child;  // varchar(1) DEFAULT NULL COMMENT 'Y:有 N:没有',
    private String boss_org_code;  // varchar(30) DEFAULT NULL COMMENT '营帐系统中对应代码',
    private int query_index;  // int(6) DEFAULT NULL COMMENT '同级排列顺序索引',
    private String bureau_code;  // varchar(2) DEFAULT NULL COMMENT '渠道大区代码',
    private String city_grade_code;  // varchar(1) DEFAULT NULL COMMENT '渠道城市级别编码',
    private String grade_code;  // varchar(2) DEFAULT NULL COMMENT '渠道等级编码',
    private String layer_code;  // varchar(1) DEFAULT NULL COMMENT '组织层次编码',
    private String credit;  // varchar(20) DEFAULT NULL COMMENT '信誉度',
    private double bail;  // double(10,2) DEFAULT NULL COMMENT '保证金',
    private String business_hours;  // varchar(40) DEFAULT NULL COMMENT '营业时间',
    private Date open_date;  // datetime DEFAULT NULL COMMENT '开业日期',
    private String phone;  // varchar(20) DEFAULT NULL COMMENT '部门联系电话',
    private String fax;  // varchar(30) DEFAULT NULL COMMENT '传真',
    private Date create_date;  // datetime DEFAULT NULL COMMENT '创建时间',

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

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getRegion_group_id() {
        return region_group_id;
    }

    public void setRegion_group_id(String region_group_id) {
        this.region_group_id = region_group_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoot_distance() {
        return root_distance;
    }

    public void setRoot_distance(int root_distance) {
        this.root_distance = root_distance;
    }

    public String getHas_child() {
        return has_child;
    }

    public void setHas_child(String has_child) {
        this.has_child = has_child;
    }

    public String getBoss_org_code() {
        return boss_org_code;
    }

    public void setBoss_org_code(String boss_org_code) {
        this.boss_org_code = boss_org_code;
    }

    public int getQuery_index() {
        return query_index;
    }

    public void setQuery_index(int query_index) {
        this.query_index = query_index;
    }

    public String getBureau_code() {
        return bureau_code;
    }

    public void setBureau_code(String bureau_code) {
        this.bureau_code = bureau_code;
    }

    public String getCity_grade_code() {
        return city_grade_code;
    }

    public void setCity_grade_code(String city_grade_code) {
        this.city_grade_code = city_grade_code;
    }

    public String getGrade_code() {
        return grade_code;
    }

    public void setGrade_code(String grade_code) {
        this.grade_code = grade_code;
    }

    public String getLayer_code() {
        return layer_code;
    }

    public void setLayer_code(String layer_code) {
        this.layer_code = layer_code;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public double getBail() {
        return bail;
    }

    public void setBail(double bail) {
        this.bail = bail;
    }

    public String getBusiness_hours() {
        return business_hours;
    }

    public void setBusiness_hours(String business_hours) {
        this.business_hours = business_hours;
    }

    public Date getOpen_date() {
        return open_date;
    }

    public void setOpen_date(Date open_date) {
        this.open_date = open_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
