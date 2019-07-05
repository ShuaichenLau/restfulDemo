package com.alice.common.errorcode;

//import com.alice.ijcf.boot.core.error.exception.BusiException;

/**
 * Created by liusc on 2018/7/24.
 */
public class AppError {
    public static String getErrorCode(String channelType,String errorType,String subSystemCode,String opCode,String seq){

            if(errorType.length() != 2){
//                throw new BusiException("errorType length isn't 2!");

        }
        return channelType+errorType+subSystemCode+opCode+seq;
    }

}
