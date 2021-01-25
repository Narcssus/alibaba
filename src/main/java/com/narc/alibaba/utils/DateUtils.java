package com.narc.alibaba.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Narcssus
 * @date : 2021/1/25 15:49
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    public static final String FORMAT_19 ="yyyy-MM-dd HH:mm:ss";


    public static Date convertStrToDate(String str,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try{
            return sdf.parse(str);
        }catch (Exception e){
            return null;
        }
    }


    public static String convertDateToStr(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try{
            return sdf.format(date);
        }catch (Exception e){
            return null;
        }
    }






}
