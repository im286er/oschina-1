package com.liang.oschina.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class TimeFormatUtils {
    private TimeFormatUtils(){}

   private final static SimpleDateFormat format =
           new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static String getTimesAway(String strTime){
        if(strTime == null)  return "" ;
        long pubDate = 0;
        try {
            Date  date  = format.parse(strTime);
            pubDate = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long mCurrentDate = System.currentTimeMillis() ;
        long date  = (mCurrentDate - pubDate)/(1000*60*60*24);
        long hours =(mCurrentDate - pubDate)/(1000*60*60) ;
        long min =(mCurrentDate - pubDate)/(1000*60) ;

        if(date != 0){
            return  date+"天前";

        }else if(hours != 0){
           return hours+"小时前";

        }else if(min != 0){
           return min+"分钟前";

        }
           return (mCurrentDate - pubDate)/1000+"秒前";
    }
}
