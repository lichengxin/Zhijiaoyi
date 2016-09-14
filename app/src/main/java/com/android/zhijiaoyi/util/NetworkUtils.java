package com.android.zhijiaoyi.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by SONY-SONG on 2015/3/27.
 */
public class NetworkUtils {
    /**
     * 判断当前网络是否可用
     *
     * @param context 当前Acitivity对象
     * @return 可用返回true 否则返回false
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    /**
     * 判断当前是否是wifi
     * @param context
     * @return
     */

    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static String int2ip(long paramLong) {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramLong & 0xFF).append(".");
        localStringBuilder.append(0xFF & paramLong >> 8).append(".");
        localStringBuilder.append(0xFF & paramLong >> 16).append(".");
        localStringBuilder.append(0xFF & paramLong >> 24);
        return localStringBuilder.toString();
    }

    /**
     * 得到wifi的ip地址
     * @param context
     * @return
     */
    public static String getIpAddress(Context context) {
        if (isWifiConnected(context)) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int ip = wifiInfo.getIpAddress();
                return int2ip(ip);
            }
        }
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface inf = en.nextElement();
                for (Enumeration<InetAddress> enumeration = inf.getInetAddresses(); enumeration.hasMoreElements(); ) {
                    InetAddress address = enumeration.nextElement();
                    if (!address.isLoopbackAddress()) {
                        return address.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到网络类型
     * @param context
     * @return one of {@link ConnectivityManager#TYPE_MOBILE}, {@link
     * ConnectivityManager#TYPE_WIFI}, {@link ConnectivityManager#TYPE_WIMAX}, {@link
     * ConnectivityManager#TYPE_ETHERNET},  {@link ConnectivityManager#TYPE_BLUETOOTH}, or other
     * types defined by {@link ConnectivityManager}
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

}
