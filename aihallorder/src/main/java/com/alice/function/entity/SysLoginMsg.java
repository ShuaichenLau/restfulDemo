package com.alice.function.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 对应表记录 sys_login_msg
 * 2019-6-5 13:44:31
 */
public class SysLoginMsg implements Serializable {

    private static final long serialVersionUID = 4845901720537074841L;
    private String id;  // int(8) NOT NULL AUTO_INCREMENT,
    private String login_no;  // varchar(20) NOT NULL,
    private String login_password;  // varchar(32) NOT NULL,
    private String login_name;  // varchar(64) DEFAULT NULL,
    private String role_id;  // varchar(16) DEFAULT NULL,
    private String group_id;  // varchar(32) DEFAULT NULL,
    private Date allow_begin_time;  // datetime DEFAULT NULL,
    private Date allow_end_time;  // datetime DEFAULT NULL,
    private String login_status;  // varchar(2) DEFAULT NULL,
    private String contact_phone;  // varchar(20) DEFAULT NULL,
    private String account_type;  // varchar(10) DEFAULT NULL,
    private Date create_time;  // datetime DEFAULT NULL,
    private String create_login_no;  // varchar(20) DEFAULT NULL,
    private String email;  // varchar(50) DEFAULT NULL,
    private Date last_login_time;  // datetime DEFAULT NULL,
    private String login_ip;  // int(11) DEFAULT NULL,
    private String session_id;  // int(11) DEFAULT NULL,
    private String iccid_type;  // varchar(2) DEFAULT NULL,
    private String iccid_no;  // varchar(50) DEFAULT NULL,

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin_no() {
        return login_no;
    }

    public void setLogin_no(String login_no) {
        this.login_no = login_no;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public Date getAllow_begin_time() {
        return allow_begin_time;
    }

    public void setAllow_begin_time(Date allow_begin_time) {
        this.allow_begin_time = allow_begin_time;
    }

    public Date getAllow_end_time() {
        return allow_end_time;
    }

    public void setAllow_end_time(Date allow_end_time) {
        this.allow_end_time = allow_end_time;
    }

    public String getLogin_status() {
        return login_status;
    }

    public void setLogin_status(String login_status) {
        this.login_status = login_status;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getCreate_login_no() {
        return create_login_no;
    }

    public void setCreate_login_no(String create_login_no) {
        this.create_login_no = create_login_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getIccid_type() {
        return iccid_type;
    }

    public void setIccid_type(String iccid_type) {
        this.iccid_type = iccid_type;
    }

    public String getIccid_no() {
        return iccid_no;
    }

    public void setIccid_no(String iccid_no) {
        this.iccid_no = iccid_no;
    }
}
