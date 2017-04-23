package com.util;

import android.util.Log;

/**
 * Created by 13410 on 2017/4/23.
 */

public class LogUtil {
    private static  final String LOGCAT="notepad";
    public static  void out(String message){
        Log.i(LOGCAT,message);
    }
}
