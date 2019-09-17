package com.alice.function.entity;

import java.io.Serializable;

public class CustEntity implements Serializable {

    private static final long serialVersionUID = -2190305294433996681L;
    private String ID_NO;  // "100101000000013064",
    private String FACE_IMAGE_URL;  // "http://172.21.4.101:10100/20190531/9193AD02B8822A4F1EE904D2103848C5.png",
    private String PHONE_NO;  // "15834198784",
    private String CUST_NAME;  // "张**",
    private String GROUP_ID;  // "00001"

    public String getID_NO() {
        return ID_NO;
    }

    public void setID_NO(String ID_NO) {
        this.ID_NO = ID_NO;
    }

    public String getFACE_IMAGE_URL() {
        return FACE_IMAGE_URL;
    }

    public void setFACE_IMAGE_URL(String FACE_IMAGE_URL) {
        this.FACE_IMAGE_URL = FACE_IMAGE_URL;
    }

    public String getPHONE_NO() {
        return PHONE_NO;
    }

    public void setPHONE_NO(String PHONE_NO) {
        this.PHONE_NO = PHONE_NO;
    }

    public String getCUST_NAME() {
        return CUST_NAME;
    }

    public void setCUST_NAME(String CUST_NAME) {
        this.CUST_NAME = CUST_NAME;
    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(String GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    @Override
    public String toString() {
        return "CustEntity{" +
                "ID_NO='" + ID_NO + '\'' +
                ", FACE_IMAGE_URL='" + FACE_IMAGE_URL + '\'' +
                ", PHONE_NO='" + PHONE_NO + '\'' +
                ", CUST_NAME='" + CUST_NAME + '\'' +
                ", GROUP_ID='" + GROUP_ID + '\'' +
                '}';
    }

}
