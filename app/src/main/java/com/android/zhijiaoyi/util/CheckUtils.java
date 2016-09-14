package com.android.zhijiaoyi.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by song on 2015/4/13.
 */
public class CheckUtils {
    /**
     * 验证输入的邮箱格式是否符合
     *
     * @param email
     * @return 是否合法
     */
    public static boolean emailCheck(String email) {
        boolean tag = true;
        final String pattern1 = ("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /**
     * 验证是否是手机号码
     *
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern pattern = Pattern.compile("[1][23456789]\\d{9}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }

    }

    public static String fotmatData(long l) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String currentDate = sdf.format(curDate);
        return currentDate;
    }

    public static boolean checkID(String number) {
        boolean tag = false;
        String patternId1 = ("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
        String patternId2 = ("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
        Pattern pattern = null;
        if ("15".equals(String.valueOf(number.length()))) {
            pattern = Pattern.compile(patternId1);
        } else if ("18".equals(String.valueOf(number.length()))) {
            LogUtils.logE(CheckUtils.class,"sdfadsfsdgasg");
            pattern = Pattern.compile(patternId2);
        }
        if(null!=pattern){
            Matcher matcher = pattern.matcher(number);
            if (matcher.matches()) {
                LogUtils.logE(CheckUtils.class,"1111111111111");
                tag = true;
            }
        }
        LogUtils.logE(CheckUtils.class,"tag"+tag);
        return tag;
    }


}
