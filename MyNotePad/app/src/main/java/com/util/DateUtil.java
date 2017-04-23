package com.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 13410 on 2017/4/23.
 */

public class DateUtil {
    private static  final  String FORMAT="yyyy-MM-dd";
    private static SimpleDateFormat simpleDateFormat;
    static {
         simpleDateFormat=new SimpleDateFormat(FORMAT);
    }
    public static Date stringToDate(String str){
        Date res=null;
        try {
           res=simpleDateFormat.parse(str);
        } catch (ParseException e) {
            LogUtil.out("日期字符串的格式不正确");
        }
        return res;
    }

    public static  String DateToString(Date date){
        return simpleDateFormat.format(date);

    }
}
