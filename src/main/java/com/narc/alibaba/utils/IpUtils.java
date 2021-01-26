package com.narc.alibaba.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Narcssus
 * @date : 2021/1/25 18:02
 */
@Slf4j
public class IpUtils {


    private static Pattern pattern = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");


    public static String getOutIPV4() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),
                    "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read).append("\r\n");
            }
        } catch (Exception e) {
            log.error("获取IP失败",e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    log.error("获取IP失败",e);
                }
            }
        }
        Matcher m = pattern.matcher(inputLine.toString());
        if (m.find()) {
            ip = m.group(1);
        }
        return ip;
    }

}
