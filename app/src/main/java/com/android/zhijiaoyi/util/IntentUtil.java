package com.android.zhijiaoyi.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.android.zhijiaoyi.R;


/**
 * 作者：Keven on 2016/9/6 14:15
 * <p/>  Intent 工具类
 * 邮箱：xinyao0626@gmail.com
 */
public class IntentUtil {
    public static void showIntent(Activity context, Class<?> clzz, String[] keys, String[] values) {
        Intent intent = new Intent(context, clzz);
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                if (!TextUtils.isEmpty(keys[i]) && !TextUtils.isEmpty(values[i])) {
                    intent.putExtra(keys[i], values[i]);
                }
            }
        }
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.r2l_in, R.anim.r2l_out);
    }

    public static void showIntent(Activity context, Class<?> clzz) {
        showIntent(context, clzz, null, null);
    }

    /**
     * 打电话
     */
    public static void openCall(Context context, String tel) {
        tel = tel.replaceAll("-", "");
        Intent intent = new Intent();
        // 激活源代码,添加intent对象
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
