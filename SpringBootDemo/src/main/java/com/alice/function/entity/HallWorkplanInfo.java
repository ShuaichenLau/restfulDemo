package com.alice.function.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应表记录 hall_workplan_info
 * 2019-6-4 14:17:08
 * @author liusc
 */
public class HallWorkplanInfo implements Serializable {

    private static final long serialVersionUID = -9178203239512431760L;
    private String work_id;  // bigint(20) NOT NULL COMMENT '排班人员信息ID',
    private String hall_id;  // bigint(20) NOT NULL COMMENT '门店ID',
    private String group_id;  // varchar(20) NOT NULL COMMENT '营业厅归属组织Id',
    private Date work_date;  // date NOT NULL COMMENT '工作日期',
    private String work_shift;  // bigint(10) NOT NULL COMMENT '工作班次 1.上午班 2.下午班 3全天班',
    private String work_role;  // varchar(255) NOT NULL COMMENT '角色类型 1、值班经理，2、前台坐席，3、行销人员，4、导购，5、巡视员',
    private String work_name;  // varchar(255) NOT NULL COMMENT '根据工号的group_id  选到自己的厅的人员\r\n人员名称(指的是安排人员排班的名称)',
    private String create_person;  // varchar(255) NOT NULL COMMENT '创建人',
    private Date OP_TIME;  // datetime NOT NULL COMMENT '操作时间',
    private String OP_CODE;  // varchar(255) NOT NULL COMMENT '操作代码',
    private String LOGIN_NO;  // varchar(20) NOT NULL COMMENT '操作人',
    private String LOGIN_ACCEPT;  // bigint(255) NOT NULL COMMENT '操作流水',
    private String OP_NOTE;  // varchar(255) DEFAULT NULL COMMENT '操作备注',

    private String workNameStr; //排班名称

    public String getWorkNameStr() {
        return workNameStr;
    }

    public void setWorkNameStr(String workNameStr) {
        this.workNameStr = workNameStr;
    }

    public String getWork_id() {
        return work_id;
    }

    public void setWork_id(String work_id) {
        this.work_id = work_id;
    }

    public String getHall_id() {
        return hall_id;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public Date getWork_date() {
        return work_date;
    }

    public void setWork_date(Date work_date) {
        this.work_date = work_date;
    }

    public String getWork_shift() {
        return work_shift;
    }

    public void setWork_shift(String work_shift) {
        this.work_shift = work_shift;
    }

    public String getWork_role() {
        return work_role;
    }

    public void setWork_role(String work_role) {
        this.work_role = work_role;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public String getCreate_person() {
        return create_person;
    }

    public void setCreate_person(String create_person) {
        this.create_person = create_person;
    }

    public Date getOP_TIME() {
        return OP_TIME;
    }

    public void setOP_TIME(Date OP_TIME) {
        this.OP_TIME = OP_TIME;
    }

    public String getOP_CODE() {
        return OP_CODE;
    }

    public void setOP_CODE(String OP_CODE) {
        this.OP_CODE = OP_CODE;
    }

    public String getLOGIN_NO() {
        return LOGIN_NO;
    }

    public void setLOGIN_NO(String LOGIN_NO) {
        this.LOGIN_NO = LOGIN_NO;
    }

    public String getLOGIN_ACCEPT() {
        return LOGIN_ACCEPT;
    }

    public void setLOGIN_ACCEPT(String LOGIN_ACCEPT) {
        this.LOGIN_ACCEPT = LOGIN_ACCEPT;
    }

    public String getOP_NOTE() {
        return OP_NOTE;
    }

    public void setOP_NOTE(String OP_NOTE) {
        this.OP_NOTE = OP_NOTE;
    }
}
