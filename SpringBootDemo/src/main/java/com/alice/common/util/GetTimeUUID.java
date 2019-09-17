package com.alice.common.util;

import java.text.SimpleDateFormat;

/**
 * 获取20位随机数
 * 4位年份+13位时间戳+3位随机数
 *
 * @author liusc_oup
 */
public class GetTimeUUID {

    /**
     * 20位末尾的数字id
     */
    public static volatile int Guid = 100;
    public static volatile int OrderCode = 100000;

    public static String getGuid() {

        GetTimeUUID.Guid += 1;

        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        //获取时间戳
        String time = dateFormat.format(now);
        String info = now + "";
        //获取三位随机数
        //int ran=(int) ((Math.random()*9+1)*100);
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        int ran = 0;
        if (GetTimeUUID.Guid > 999) {
            GetTimeUUID.Guid = 100;
        }
        ran = GetTimeUUID.Guid;

        return time + info.substring(2, info.length()) + ran;
    }

    /**
     * 工单编号：2019060110103401000000（日期（8位）+时分秒（6位）+渠道（2位）+序列（6位））
     * @return
     */
    public static String getOrderId(String Source) {

        GetTimeUUID.OrderCode += 1;

        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        //获取时间戳
        String time = dateFormat.format(now);
        String info = now + "" + Source;
        //获取三位随机数
        //int ran=(int) ((Math.random()*9+1)*100);
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        int ran = 0;
        if (GetTimeUUID.OrderCode > 999999) {
            GetTimeUUID.OrderCode = 100000;
        }
        ran = GetTimeUUID.OrderCode;

        return time + Source + ran;
    }
    
    public static void main(String[] args) {
        System.err.println(getOrderId("01"));
		System.out.println(getOrderId("01").length());
	}
}