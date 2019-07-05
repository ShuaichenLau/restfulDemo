package com.alice.function.code;

/**
 * @author liusc_oup
 */
public class HallCode {

    //`work_role` varchar(255) NOT NULL COMMENT '角色类型 1、值班经理，2、前台坐席，3、行销人员，4、导购，5、巡视员',
    public static final int Work_role01 = 1;
    public static final int Work_role02 = 2;
    public static final int Work_role03 = 3;
    public static final int Work_role04 = 4;
    public static final int Work_role05 = 5;

    //work_shift` bigint(10) NOT NULL COMMENT '工作班次 1.上午班 2.下午班 3全天班',
    public static final String Work_Shift_01 = "1";
    public static final String Work_Shift_02 = "2";
    public static final String Work_Shift_03 = "3";


    //'工单类型 1、关怀工单，2、营销工单，3、业务办理工单，4、取货工单，',
    public static final String ORDER_TYPE01 = "1";
    public static final String ORDER_TYPE02 = "2";
    public static final String ORDER_TYPE03 = "3";
    public static final String ORDER_TYPE04 = "4";

    //'工单状态：1、生成，2、派发、3、待处理 4、完成',
    public static final String ORDER_STATUS01 = "1";
    public static final String ORDER_STATUS02 = "2";
    public static final String ORDER_STATUS03 = "3";
    public static final String ORDER_STATUS04 = "4";

    //'工单来源渠道 1、维系挽留，2、业务中心，3、云货架，4、大数据中心，5、营销中心，6、手工单',
    public static final String ORDER_SORCE01 = "1";
    public static final String ORDER_SORCE02 = "2";
    public static final String ORDER_SORCE03 = "3";
    public static final String ORDER_SORCE04 = "4";
    public static final String ORDER_SORCE05 = "5";
    public static final String ORDER_SORCE06 = "6";

    //'工单派发类型：1、人工，2、自动, 3、抢单',
    public static final String ORDER_DISTRIBUTE01 = "1";
    public static final String ORDER_DISTRIBUTE02 = "2";
    public static final String ORDER_DISTRIBUTE03 = "3";

    public static final Integer oneSecone = 1000;
    public static final Integer threeSecone = 3000;
    public static final Integer fiveSecone = 5000;
    public static final Integer tenSecone = 10000;

    // 禁止new对象
    private HallCode() {
    }
}
