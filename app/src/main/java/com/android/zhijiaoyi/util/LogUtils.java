package com.android.zhijiaoyi.util;

import android.util.Log;

/**
 * Created by Sony on 2015/9/14.
 */
public class LogUtils {
//    static boolean flag = false;
    static boolean flag = true;

    public static void logE(Class clazz, String msg) {
        try {
            if (flag)
                Log.e(clazz.getName(), msg);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
