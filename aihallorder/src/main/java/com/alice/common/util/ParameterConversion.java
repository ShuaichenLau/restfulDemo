package com.alice.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import net.sf.json.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuezn on 2018/1/12.
 */
/*对服务平台传过来的参数进行解析转换
 * */
public class ParameterConversion {

    public static Map intoParameterConversion(HttpServletRequest request) {
        String buffer = new String();

        Map paramMap = new HashMap();

        buffer = requestToString(request);
        String paramString = "";
        System.out.println("输入参数=" + buffer);
        if (buffer.length() > 0) {
            if (buffer.trim().substring(0, 1).equals("?")) {
                paramString = buffer.trim().substring(1);
            } else {
                paramString = buffer;
            }
            String[] paramStrings = paramString.split("[&]");

            for (String param : paramStrings) {
                String[] params = param.split("[=]");
                paramMap.put(params[0].trim(), params[1].trim());
            }
        }
        //System.out.println("name="+greeting.getContent());
        return paramMap;
    }

    public static <T> T jsonToObject(HttpServletRequest request, Class<T> clazz) {
        String jsonStr = new String();
        jsonStr = requestToString(request);

        return JSON.parseObject(JSONPath.read(jsonStr, "ROOT.BODY.BUSI_INFO").toString(), clazz);
        //return JSON.parseObject(jsonStr.substring(jsonStr.indexOf("\"BODY\":{")+7,jsonStr.indexOf(",\"RESULT\":{}}}")),clazz);
    }


    public static JSONObject jsonToFormat(HttpServletRequest request) {
        String jsonStr = new String();
        jsonStr = requestToString(request);

        return JSONObject.fromObject(jsonStr);
        //return JSON.parseObject(jsonStr.substring(jsonStr.indexOf("\"BODY\":{")+7,jsonStr.indexOf(",\"RESULT\":{}}}")),clazz);
    }

    public static <T> T getBusiInfo(HttpServletRequest request, Class<T> clazz, String path) {
        String jsonStr = new String();
        jsonStr = requestToString(request);
        return JSON.parseObject(JSONPath.read(jsonStr, path + "").toString(), clazz);
    }

    public static String getInParamter(HttpServletRequest request) {
        String jsonStr = new String();
        return jsonStr = requestToString(request);
    }

    //设置接口反参头部节点
/*	    public static Map<String, Object> interfaceReturn(HeaderDto dto, Map map){
	        Map<String,Object> mapThird = new HashMap<String,Object>();
	        mapThird.put("DB_ID",dto.getDb_id());
	        mapThird.put("TRACE_ID",dto.getTrace_id());
	        mapThird.put("CHANNEL_ID",dto.getChannel_id());

	        Map<String,Object> mapFourth = new HashMap<String,Object>();
	        mapFourth.put("ROUTE_KEY",dto.getRouting().getRoute_key());
	        mapFourth.put("ROUTE_VALUE",dto.getRouting().getRoute_value());
	        mapThird.put("ROUTING",mapFourth);

	        Map<String,Object> mapSnd = new HashMap<String,Object>();
	        mapSnd.put("HEADER",mapThird);
	        mapSnd.put("BODY",map);
	        Map<String,Object> mapReturn = new HashMap<String,Object>();
	        mapReturn.put("ROOT",mapSnd);
	        System.out.println("反参为："+mapReturn.get("ROOT").toString());
	        return mapReturn;
	    }*/
    private static String requestToString(HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer();
        try {
            ServletInputStream s = request.getInputStream();

            BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = bis.read(b)) != -1) {
                buffer.append(new String(b, 0, len));
            }
            bis.close();
            System.out.println("输入参数=" + buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
