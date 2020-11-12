package com.narc.alibaba.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author : Narcssus
 * @date : 2020/10/18 21:30
 */
@Slf4j
public class HttpUtils {

    public static String httpGet(String  url,JSONObject jsonObject) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        for(String key : jsonObject.keySet()){
            uriBuilder.addParameter(key,jsonObject.getString(key));
        }
        log.info("请求URL："+uriBuilder.toString());
        return  sendGet(uriBuilder.toString());
    }


    public static String sendGet(String url) {
        BufferedReader in = null;
        String result = "";
        try {
            log.info("url=" + url);
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("发送GET请求出现异常！" + e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}
