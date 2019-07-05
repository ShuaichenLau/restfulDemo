package com.alice.common.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class HttpRequest {
	public static String sendPost(String url,  String params) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// 处理请求参数
        //String params = "";// 编码之后的参数
        try {
            // 编码请求参数
        /*    if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name).toString(), "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name).toString(), "UTF-8"))
                            .append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }*/
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
            // 设置通用属性
            //httpConn.setRequestProperty("Accept", "*/*");
            //httpConn.setRequestProperty("Connection", "Keep-Alive");
            //httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestMethod("POST"); 
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setUseCaches(false);
            httpConn.setInstanceFollowRedirects(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
	
	
	 public static JSONObject doPost(String url, JSONObject json){
	          DefaultHttpClient client = new DefaultHttpClient();
	          HttpPost post = new HttpPost(url);
	          JSONObject response = null;
	          try {
	              StringEntity s = new StringEntity(json.toString());
	              s.setContentEncoding("UTF-8");
	              s.setContentType("application/json");//发送json数据需要设置contentType
	              post.setEntity(s);
	              HttpResponse res = client.execute(post);
	              if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	                  HttpEntity entity = res.getEntity();
	                  String result = EntityUtils.toString(entity);// 返回json格式：
	                  
	                  System.out.println("result============"+result);
	                  response = JSONObject.fromObject(result);
	              }
	          } catch (Exception e) {
	              throw new RuntimeException(e);
	          }
	          return response;
	      }

}
