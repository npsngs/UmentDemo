//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;

import com.umeng.tool.SystemUtil;
import com.umeng.tool.ULog;
import com.umeng.tool.CacheTool;

public class AnalyticsConfig {
    private static String appKey = null;
    private static String channel = null;
    private static String secretKey = null;
    public static String mWrapperType = null;
    public static String mWrapperVersion = null;
    private static int verticalType = 0;
    public static String GPU_VENDER = "";
    public static String GPU_RENDERER = "";
    public static boolean ACTIVITY_DURATION_OPEN = true;
    public static boolean CATCH_EXCEPTION = true;
    public static long kContinueSessionMillis = 30000L;
    public static boolean sEncrypt = false;
    public static int sLatentWindow;
    static double[] location = null;

    public AnalyticsConfig() {
    }

    static void setEncrypt(boolean isEncrypt) {
        sEncrypt = isEncrypt;
    }

    static void setAppKey(Context context, String appKey) {
        if(context == null) {
            AnalyticsConfig.appKey = appKey;
        } else {
            String configAppKey = SystemUtil.getUMengAppKey(context);
            if(!TextUtils.isEmpty(configAppKey)) {
                AnalyticsConfig.appKey = configAppKey;
                if(!configAppKey.equals(appKey)) {
                    ULog.d("Appkey和AndroidManifest.xml中配置的不一致 ");
                }
            } else {
                String lastAppKey = CacheTool.getInstance(context).loadAppKey();
                if(!TextUtils.isEmpty(lastAppKey)) {
                    if(!lastAppKey.equals(appKey)) {
                        ULog.d("Appkey和上次配置的不一致 ");
                        CacheTool.getInstance(context).saveAppKey(appKey);
                    }
                } else {
                    CacheTool.getInstance(context).saveAppKey(appKey);
                }

                AnalyticsConfig.appKey = appKey;
            }

        }
    }

    static void setChannel(String channel) {
        AnalyticsConfig.channel = channel;
    }

    public static String getAppkey(Context context) {
        if(TextUtils.isEmpty(appKey)) {
            appKey = SystemUtil.getUMengAppKey(context);
            if(TextUtils.isEmpty(appKey)) {
                appKey = CacheTool.getInstance(context).loadAppKey();
            }
        }

        return appKey;
    }

    public static String getChannel(Context context) {
        if(TextUtils.isEmpty(channel)) {
            channel = SystemUtil.getUMengChannel(context);
        }

        return channel;
    }

    public static double[] getLocation() {
        return location;
    }

    static void setSecretKey(Context context, String secretKey) {
        if(!TextUtils.isEmpty(secretKey)) {
            AnalyticsConfig.secretKey = secretKey;
            CacheTool.getInstance(context).saveST(AnalyticsConfig.secretKey);
        }

    }

    public static String getSecretKey(Context context) {
        if(TextUtils.isEmpty(secretKey)) {
            secretKey = CacheTool.getInstance(context).loadST();
        }

        return secretKey;
    }

    static void setVerticalType(Context context, int verticalType) {
        AnalyticsConfig.verticalType = verticalType;
        CacheTool.getInstance(context).saveVT(AnalyticsConfig.verticalType);
    }

    public static int getVerticalType(Context context) {
        if(verticalType == 0) {
            verticalType = CacheTool.getInstance(context).loadVT();
        }

        return verticalType;
    }

    public static String getSDKVersion(Context context) {
        return "6.1.0";
    }
}
