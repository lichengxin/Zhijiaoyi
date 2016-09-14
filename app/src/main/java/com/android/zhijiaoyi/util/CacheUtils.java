package com.android.zhijiaoyi.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jingmeiling on 2015/12/16.
 */
public class CacheUtils {
    /**
     * shared_preference文件名
     */
    private static final String sharedPreferenceName = "rqd_product";
    private static int mode = Context.MODE_PRIVATE;


    public static Object getValue(Context c, String key, Object value) {

        SharedPreferences sp = c.getSharedPreferences(sharedPreferenceName, mode);
        if (value instanceof Boolean)
            return sp.getBoolean(key, (Boolean) value);
        else if (value instanceof Integer)
            return sp.getInt(key, (Integer) value);
        else if (value instanceof Float)
            return sp.getFloat(key, (Float) value);
        else if (value instanceof Long)
            return sp.getLong(key, (Long) value);
        else if (value instanceof String)
            return sp.getString(key, (String) value);
        else
            return null;
    }

    public static Object saveValue(Context c, String key, Object value) {
        SharedPreferences sp = c.getSharedPreferences(sharedPreferenceName, mode);
        SharedPreferences.Editor ed = sp.edit();
        if (value instanceof Boolean)
            return ed.putBoolean(key, (Boolean) value).commit();
        else if (value instanceof Integer)
            return ed.putInt(key, (Integer) value).commit();
        else if (value instanceof Float)
            return ed.putFloat(key, (Float) value).commit();
        else if (value instanceof Long)
            return ed.putLong(key, (Long) value).commit();
        else if (value instanceof String)
            return ed.putString(key, (String) value).commit();
        else
            return false;
    }

    public static void saveString(Context c, String key, String value) {
        SharedPreferences sp = c.getSharedPreferences(sharedPreferenceName, mode);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(key, value).commit();
    }
    public static String getString(Context c, String key, String value) {
        SharedPreferences sp = c.getSharedPreferences(sharedPreferenceName, mode);
        return sp.getString(key,value);
    }

    public static void clearData(Context c){
        SharedPreferences sp = c.getSharedPreferences(sharedPreferenceName, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }

    //通过key来删除对应的缓存值
    public static void removeData(Context c, String key){
        SharedPreferences sp = c.getSharedPreferences(sharedPreferenceName, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key).commit();
    }
}
