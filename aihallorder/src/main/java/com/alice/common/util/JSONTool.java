package com.alice.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * json处理的工具类，如json串转json对象、json对象与java对象的转化等功能
 *
 * @author hejw 2014年5月5日9:33:40
 */
public class JSONTool {

    /**
     * 取json中普通字符串，防止没有此key造成抛出异常报错
     * hejwa 2014年6月5日11:05:08
     */

    public static String getStr_F_JsonObj(JSONObject jsonObj, String key) {
        String value = "";
        try {
            value = jsonObj.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 把json字符串转化为json对象
     *
     * @param jsonStr：入参json字符串
     * @return
     */
    public static JSONObject getJson_F_Str(String jsonStr) {
        JSONObject jSONObject = null;
        try {
            jSONObject = JSONObject.fromObject(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /**
     * json字符串转化为 HashMap<String ,String>
     *
     * @param hashmap
     */
    public static HashMap<String, String> getMap_F_JsonStr(String jsonStr) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (jsonStr == null) return hashMap;
        JSONObject jSONObject = getJson_F_Str(jsonStr);
        if (jSONObject != null) {
            @SuppressWarnings("unchecked")
            Iterator<String> it = jSONObject.keys();
            while (it.hasNext()) {
                String key = String.valueOf(it.next());
                String value = jSONObject.getString(key);
                // System.out.println("key = "+key+"   value = "+value);
                hashMap.put(key, value);
            }
        }
        return hashMap;
    }

    /**
     * json字符串转化为 HashMap<String ,String>
     *
     * @param hashmap
     */
    public static HashMap<String, Object> getMap_F_JsonStr_o(String jsonStr) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        if (jsonStr == null) return hashMap;
        JSONObject jSONObject = getJson_F_Str(jsonStr);
        if (jSONObject != null) {
            @SuppressWarnings("unchecked")
            Iterator<String> it = jSONObject.keys();
            while (it.hasNext()) {
                String key = String.valueOf(it.next());
                String value = jSONObject.getString(key);
                // System.out.println("key = "+key+"   value = "+value);
                hashMap.put(key, value);
            }
        }
        return hashMap;
    }

    /**
     * json对象转化为 HashMap<String ,String>
     *
     * @param hashmap
     */
    public static HashMap<String, String> getMap_F_JsonObj(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (jSONObject == null) {
            return hashMap;
        }
        if (jSONObject != null) {
            @SuppressWarnings("unchecked")
            Iterator<String> it = jSONObject.keys();
            while (it.hasNext()) {
                String key = String.valueOf(it.next());
                String value = jSONObject.getString(key);
                // System.out.println("key = "+key+"   value = "+value);
                hashMap.put(key, value);
            }
        }
        return hashMap;
    }

    /**
     * json数组对象串转为 ListMap
     *
     * @param jsonStr
     * @return
     */
    public static ArrayList<HashMap<String, String>> getListMap_F_JsonArrObj(JSONArray jsonArray) {
        ArrayList<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();
        if (jsonArray == null) return listmap;
        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                listmap.add(getMap_F_JsonStr(jsonObject.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listmap;
    }

    /**
     * json数组对象串转为 ListMap
     *
     * @param jsonStr
     * @return
     */
    public static List<Map<String, Object>> getListMap_F_JsonArrObj_o(JSONArray jsonArray) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        if (jsonArray == null) return listmap;
        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                listmap.add(getMap_F_JsonStr_o(jsonObject.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listmap;
    }

    /**
     * json对象串转为 ListMap
     *
     * @param jsonStr
     * @return
     */
    public static ArrayList<HashMap<String, String>> getListMap_F_JsonObj(JSONObject jSONObject) {
        ArrayList<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();
        if (jSONObject == null) return listmap;
        try {
            JSONArray jsonArray = JSONArray.fromObject(jSONObject);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                listmap.add(getMap_F_JsonStr(jsonObject.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listmap;
    }

    /**
     * json字符串转为 ListMap
     *
     * @param jsonStr
     * @return
     */
    public static ArrayList<HashMap<String, String>> getListMap_F_JsonStr(String jsonStr) {
        ArrayList<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();
        if (jsonStr == null) return listmap;
        try {
            JSONArray jsonArray = JSONArray.fromObject(jsonStr);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                listmap.add(getMap_F_JsonStr(jsonObject.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listmap;
    }

    /**
     * 将HashMap<String ,String> 转化为json字符串
     *
     * @param map
     */
    public static String getJsonStr_F_Map(HashMap<String, String> map) {
        String jsonStr = "{}"; // 空的json串
        if (map == null) return jsonStr;
        try {
            JSONObject jsonObject = new JSONObject();
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String key = String.valueOf(it.next());
                String value = String.valueOf(map.get(key));
                jsonObject.put(key, value);
            }
            jsonStr = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * listmap转化为json数组串
     *
     * @param listmap
     * @return
     */


    public static String getJsonStr_F_ListMap(ArrayList<HashMap<String, String>> listmap) {
        String jsonStr = "{}"; // 空的json串
        if (listmap == null) return jsonStr;
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for (int i = 0; i < listmap.size(); i++) {
                sb.append(getJsonStr_F_Map(listmap.get(i)));
                if (i < listmap.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            jsonStr = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;

    }

    /**
     * 将java对象转化为json字符串
     *
     * @param benObj
     */
    public static String getJsonStr_F_Bean(Object benObj) {
        String jsonStr = "{}"; // 空的json串
        if (benObj == null) return jsonStr;
        try {
            JSONObject jSONObject = JSONObject.fromObject(benObj);
            jsonStr = jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * List<Object> 对象转化为json字符串
     *
     * @param listBean
     * @return
     */
    public static String getJsonStr_F_ListBean(ArrayList<Object> listBean) {
        String jsonStr = "{}"; // 空的json串
        if (listBean == null) return jsonStr;
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for (int i = 0; i < listBean.size(); i++) {
                sb.append(getJsonStr_F_Bean(listBean.get(i)));
                if (i < listBean.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            jsonStr = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * json字符串转化为bean对象
     *
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("static-access")
    public static Object getBean_F_JsonStr(String jsonStr, @SuppressWarnings("rawtypes") Class cls) {
        Object object = null;
        try {
            JSONObject jSONObject = getJson_F_Str(jsonStr);
            object = jSONObject.toBean(jSONObject, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * json字符串转化为ArrayList<Bean>对象
     *
     * @param jsonStr
     * @param cls
     * @return
     */
    public static ArrayList<Object> getListBean_F_JsonStr(String jsonStr, @SuppressWarnings("rawtypes") Class cls) {
        ArrayList<Object> listBean = new ArrayList<Object>();
        try {
            JSONArray jsonArray = JSONArray.fromObject(jsonStr);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                listBean.add(getBean_F_JsonStr(jsonObject.toString(), cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBean;
    }

    public static void main(String[] args) {

//		
//		HashMap<String,Object> map = new HashMap<String,Object>();
//		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
//		
//		int id = 11111;
//		String name = "张三";
//		Date now = new Date();
//		
//		map.put("id", id);
//		map.put("name", name);
//		map.put("now", now);
//		
//		list.add(map);
//		
//		WsMessage wmsg = new WsMessage();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("map", map);
//		jsonObject.put("list", list);
//		
//		wmsg.setRetJsonObj(jsonObject);
//		
//		String retJsonStr = getJsonStr_F_Bean(wmsg);
//		
//		System.out.println("retJsonStr的值是："+retJsonStr);


//		HashMap<String,String> hm = new HashMap<String,String>();
//		hm = JSONTool.getMap_F_JsonStr(retJsonStr);
//		System.out.println("hm的值是："+hm);
//		System.out.println("hm的值是："+hm.get("retCode"));
//		System.out.println("hm的值是："+hm.get("retMsg"));
//		System.out.println("hm的值是："+hm.get("retJsonObj"));
//		
//		String retJsonObjStr = hm.get("retJsonObj");
//		HashMap<String,String> hm1 = new HashMap<String,String>();
//		hm1 = JSONTool.getMap_F_JsonStr(retJsonObjStr);
//		
//		System.out.println("=========="+hm1.get("map"));
//		System.out.println(hm1.get("list"));


//		JSONObject outJsonObj = JSONTool.getJson_F_Str(retJsonStr);
//		
//		System.out.println("outJsonObj的值是："+outJsonObj);
//		
//		JSONObject object = outJsonObj.getJSONObject("retJsonObj");
//		
//		System.out.println("object的值是："+object);


//		String allJsonStr = "{\"retCode\":\"000000\",\"retMsg\":\"操作成功\",\"retData\":{\"idNo\":\"230112347192300012\",\"phoneNo\":\"13904510001\",\"name\":\"姓名1\",\"addr\":\"黑龙江省哈尔滨市松山路汉水路中容国际大厦4楼\"},\"listBeanArr\":[{\"param_desc\":\"DESC_1\",\"param_name\":\"NAME_1\",\"param_type\":\"TYPE_1\",\"param_value\":\"VALUE_1\"},{\"param_desc\":\"DESC_2\",\"param_name\":\"NAME_2\",\"param_type\":\"TYPE_2\",\"param_value\":\"VALUE_2\"},{\"param_desc\":\"DESC_3\",\"param_name\":\"NAME_3\",\"param_type\":\"TYPE_3\",\"param_value\":\"VALUE_3\"}],\"listMapArr\":[{\"phoneNo\":\"13904510001\",\"name\":\"姓名1\"},{\"phoneNo\":\"13904510002\",\"name\":\"姓名2\"},{\"phoneNo\":\"13904510003\",\"name\":\"姓名3\"}]}";
//		JSONObject jsonObject = getJson_F_Str(allJsonStr);
//		System.out.println(jsonObject.getString("retCode1"));
//		JSONObject j = new JSONObject();
//		getMap_F_JsonObj(j.getJSONObject("a"));
        // -------------将各种对象拼接到一个json串中--------------拼串的过程-------------
        // JSONObject jsonObject = new JSONObject();
        // ArrayList<HashMap<String, String>> listmapArr = new ArrayList<HashMap<String, String>>();
        // HashMap<String, String> hm1 = new HashMap<String, String>();
        // hm1.put("name", "姓名1");
        // hm1.put("phoneNo", "13904510001");
        // listmapArr.add(hm1);
        //
        // HashMap<String, String> hm2 = new HashMap<String, String>();
        // hm2.put("name", "姓名2");
        // hm2.put("phoneNo", "13904510002");
        // listmapArr.add(hm2);
        //
        // HashMap<String, String> hm3 = new HashMap<String, String>();
        // hm3.put("name", "姓名3");
        // hm3.put("phoneNo", "13904510003");
        // listmapArr.add(hm3);
        //
        // ArrayList<Object> listSC = new ArrayList<Object>();
        // SYS_CONFIG sc1 = new SYS_CONFIG();
        // sc1.setParam_desc("DESC_1");
        // sc1.setParam_name("NAME_1");
        // sc1.setParam_type("TYPE_1");
        // sc1.setParam_value("VALUE_1");
        // listSC.add(sc1);
        //
        // SYS_CONFIG sc2 = new SYS_CONFIG();
        // sc2.setParam_desc("DESC_2");
        // sc2.setParam_name("NAME_2");
        // sc2.setParam_type("TYPE_2");
        // sc2.setParam_value("VALUE_2");
        // listSC.add(sc2);
        //
        // SYS_CONFIG sc3 = new SYS_CONFIG();
        // sc3.setParam_desc("DESC_3");
        // sc3.setParam_name("NAME_3");
        // sc3.setParam_type("TYPE_3");
        // sc3.setParam_value("VALUE_3");
        // listSC.add(sc3);
        //
        // HashMap<String, String> hmm = new HashMap<String, String>();
        // hmm.put("name", "姓名1");
        // hmm.put("phoneNo", "13904510001");
        // hmm.put("idNo", "230112347192300012");
        // hmm.put("addr", "黑龙江省哈尔滨市松山路汉水路中容国际大厦4楼");
        // jsonObject.put("retCode", "000000");
        // jsonObject.put("retMsg", "操作成功");
        // jsonObject.put("retData", hmm);
        // jsonObject.put("listBeanArr", listSC);
        // jsonObject.put("listMapArr", listmapArr);
        //
        // System.out.println(jsonObject.toString());
        // -------------json串中各种数据分别对应到对象中----------拆串的过程-----------------
        // String allJsonStr =
        // "{\"retCode\":\"000000\",\"retMsg\":\"操作成功\",\"retData\":{\"idNo\":\"230112347192300012\",\"phoneNo\":\"13904510001\",\"name\":\"姓名1\",\"addr\":\"黑龙江省哈尔滨市松山路汉水路中容国际大厦4楼\"},\"listBeanArr\":[{\"param_desc\":\"DESC_1\",\"param_name\":\"NAME_1\",\"param_type\":\"TYPE_1\",\"param_value\":\"VALUE_1\"},{\"param_desc\":\"DESC_2\",\"param_name\":\"NAME_2\",\"param_type\":\"TYPE_2\",\"param_value\":\"VALUE_2\"},{\"param_desc\":\"DESC_3\",\"param_name\":\"NAME_3\",\"param_type\":\"TYPE_3\",\"param_value\":\"VALUE_3\"}],\"listMapArr\":[{\"phoneNo\":\"13904510001\",\"name\":\"姓名1\"},{\"phoneNo\":\"13904510002\",\"name\":\"姓名2\"},{\"phoneNo\":\"13904510003\",\"name\":\"姓名3\"}]}";
        // JSONObject jsonObjectAll = getJson_F_Str(allJsonStr);
        // System.out.println("retCode = " + jsonObjectAll.get("retCode"));
        // System.out.println("retMsg = " + jsonObjectAll.get("retMsg"));
        // System.out.println("retData = " + jsonObjectAll.get("retData")); // getListMap_F_JsonStr(retData);
        // System.out.println("listBeanArr = " + jsonObjectAll.get("listBeanArr")); // getListBean_F_JsonStr(jsonStr, cls)
        // System.out.println("listMapArr = " + jsonObjectAll.get("listMapArr"));// getListMap_F_JsonStr(jsonStr)

        // ---------json数组转化为list bean-----------------
        // String jsonListBeanStr = "[{\"param_desc\":\"DESC_1\",\"param_name\":\"NAME_1\",\"param_type\":\"TYPE_1\",\"param_value\":\"VALUE_1\"},{\"param_desc\":\"DESC_2\",\"param_name\":\"NAME_2\",\"param_type\":\"TYPE_2\",\"param_value\":\"VALUE_2\"},{\"param_desc\":\"DESC_3\",\"param_name\":\"NAME_3\",\"param_type\":\"TYPE_3\",\"param_value\":\"VALUE_3\"}]";
        // ArrayList<Object> listBeanSc = getListBean_F_JsonStr(jsonListBeanStr,SYS_CONFIG.class);
        // System.out.println(((SYS_CONFIG)listBeanSc.get(0)).getParam_value());
        // System.out.println(((SYS_CONFIG)listBeanSc.get(1)).getParam_value());
        // System.out.println(((SYS_CONFIG)listBeanSc.get(2)).getParam_value());
        // ---------list bean转化为json数组-----------------
        // ArrayList<Object> listSC = new ArrayList<Object>();
        // SYS_CONFIG sc1 = new SYS_CONFIG();
        // sc1.setParam_desc("DESC_1");
        // sc1.setParam_name("NAME_1");
        // sc1.setParam_type("TYPE_1");
        // sc1.setParam_value("VALUE_1");
        // listSC.add(sc1);
        //
        // SYS_CONFIG sc2 = new SYS_CONFIG();
        // sc2.setParam_desc("DESC_2");
        // sc2.setParam_name("NAME_2");
        // sc2.setParam_type("TYPE_2");
        // sc2.setParam_value("VALUE_2");
        // listSC.add(sc2);
        //
        // SYS_CONFIG sc3 = new SYS_CONFIG();
        // sc3.setParam_desc("DESC_3");
        // sc3.setParam_name("NAME_3");
        // sc3.setParam_type("TYPE_3");
        // sc3.setParam_value("VALUE_3");
        // listSC.add(sc3);
        //
        // System.out.println(getJsonStr_F_ListBean(listSC));
        // ---------listmap转为json数组字符串----------------------
        // ArrayList<HashMap<String, String>> listmapArr = new ArrayList<HashMap<String, String>>();
        // HashMap<String, String> hm1= new HashMap<String, String>();
        // hm1.put("name", "姓名1");
        // hm1.put("phoneNo", "13904510001");
        // listmapArr.add(hm1);
        //
        // HashMap<String, String> hm2= new HashMap<String, String>();
        // hm2.put("name", "姓名2");
        // hm2.put("phoneNo", "13904510002");
        // listmapArr.add(hm2);
        //
        // HashMap<String, String> hm3= new HashMap<String, String>();
        // hm3.put("name", "姓名3");
        // hm3.put("phoneNo", "13904510003");
        // listmapArr.add(hm3);
        // System.out.println(getJsonStr_F_ListMap(listmapArr));
        //
        // ---------json转为listmap----------------------
        // String listJson = "[{\"phoneNo\":\"13904510001\",\"name\":\"姓名1\"},{\"phoneNo\":\"13904510002\",\"name\":\"姓名2\"},{\"phoneNo\":\"13904510003\",\"name\":\"姓名3\"}]";
        // ArrayList<HashMap<String, String>> listmap = getListMap_F_JsonStr(listJson);
        // System.out.println(listmap.get(0).get("name"));
        // System.out.println(listmap.get(1).get("name"));
        // System.out.println(listmap.get(2).get("name"));

        // ---------json串转化为bean-----------
        // String jsonStrFBean = "{\"param_desc\":\"1\",\"param_name\":\"2\",\"param_type\":\"3\",\"param_value\":\"4\"}";
        // SYS_CONFIG scc = (SYS_CONFIG) getBean_F_JsonStr(jsonStrFBean, SYS_CONFIG.class);
        // System.out.println(scc.getParam_desc());

        // -----------bean转化为json------------
        // SYS_CONFIG sc = new SYS_CONFIG();
        // sc.setParam_desc("1");
        // sc.setParam_name("2");
        // sc.setParam_type("3");
        // sc.setParam_value("4");
        // System.out.println(getJsonStr_F_Bean(sc));

        // --------字符串转为json对象-----------------
        // String jsonStr = "{\"username\":\"huangwuyi\",\"arraykey\":[{\"ak\":\"av\",\"ak1\":\"av1\"},{\"ak\":\"avo\",\"ak1\":\"avo1\"}],\"nickname\":\"梦中心境\"}";
        // JSONObject a = getJson_F_Str(jsonStr);
        // System.out.println(a);

        // ----------json字符串转为map----------------
        // String jsonStr = "{\"username\":\"huangwuyi\",\"arraykey\":[{\"ak\":\"av\",\"ak1\":\"av1\"},{\"ak\":\"avo\",\"ak1\":\"avo1\"}],\"nickname\":\"梦中心境\"}";
        // HashMap<String, String> b = getMap_F_JsonStr(jsonStr);
        // System.out.println(b.get("arraykey"));

    }


    /**
     * listmap转化为json数组串 重构方法，入参 List
     *
     * @param listmap
     * @return
     */
    public static String getJsonStr_F_ListMap(List<HashMap<String, String>> listmap) {
        String jsonStr = "{}"; // 空的json串
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for (int i = 0; i < listmap.size(); i++) {
                sb.append(getJsonStr_F_Map(listmap.get(i)));
                if (i < listmap.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            jsonStr = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}
