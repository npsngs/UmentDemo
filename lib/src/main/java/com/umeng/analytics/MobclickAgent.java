//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.tool.ULog;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.analytics.social.UMSocialService;
import com.umeng.analytics.social.d;
import com.yxd.ums.Simulator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;

public class MobclickAgent {
    private static final Mobclick MOBCLICK = new Mobclick();
    public MobclickAgent() {
    }

    public static void configSimlator(String prefix, int count){
        Simulator.count = count;
        Simulator.header = prefix;
    }

    public static void startWithConfigure(MobclickAgent.UMAnalyticsConfig config) {
        if(config != null) {
            MOBCLICK.startWithConfigure(config);
        }
    }

    public static void setLocation(double var0, double var2) {
        MOBCLICK.setLocation(var0, var2);
    }

    public static void setLatencyWindow(long var0) {
        MOBCLICK.setLatentWindow(var0);
    }

    public static void enableEncrypt(boolean enable) {
        MOBCLICK.enableEncrypt(enable);
    }

    public static void setCatchUncaughtExceptions(boolean catchUncaughtExceptions) {
        MOBCLICK.setCatchUncaughtExceptions(catchUncaughtExceptions);
    }

    public static void setSecret(Context var0, String var1) {
        MOBCLICK.setSecretKey(var0, var1);
    }

    public static void setScenarioType(Context var0, MobclickAgent.EScenarioType scenarioType) {
        MOBCLICK.setVerticalType(var0, scenarioType);
    }

    public static void setSessionContinueMillis(long var0) {
        MOBCLICK.setSessionContinueMillis(var0);
    }

    public static Mobclick getAgent() {
        return MOBCLICK;
    }

    public static void setCheckDevice(boolean isCheckDevice) {
        MOBCLICK.setCheckDevice(isCheckDevice);
    }

    public static void setOpenGLContext(GL10 gl10) {
        MOBCLICK.setOpenGLContext(gl10);
    }

    public static void openActivityDurationTrack(boolean activityDurationOpen) {
        MOBCLICK.openActivityDurationTrack(activityDurationOpen);
    }

    public static void onPageStart(String pageName) {
        if(!TextUtils.isEmpty(pageName)) {
            MOBCLICK.onPageStart(pageName);
        } else {
            ULog.e("pageName is null or empty");
        }
    }

    public static void onPageEnd(String pageName) {
        if(!TextUtils.isEmpty(pageName)) {
            MOBCLICK.onPageEnd(pageName);
        } else {
            ULog.e("pageName is null or empty");
        }

    }

    public static void setDebugMode(boolean isDebugMode) {
        MOBCLICK.setDebugMode(isDebugMode);
    }

    public static void onPause(Context context) {
        MOBCLICK.onPause(context);
    }

    public static void onResume(Context context) {
        if(context == null) {
            ULog.e("unexpected null context in onResume");
        } else {
            MOBCLICK.onResume(context);
        }
    }

    public static void reportError(Context context, String contextStr) {
        MOBCLICK.reportError(context, contextStr);
    }

    public static void reportError(Context context, Throwable throwable) {
        MOBCLICK.reportError(context, throwable);
    }

    public static void onEvent(Context context, List<String> var1, int var2, String var3) {
        MOBCLICK.onEvent(context, var1, var2, var3);
    }

    public static void onEvent(Context context, String var1) {
        MOBCLICK.onEvent(context, var1, null, -1L, 1);
    }

    public static void onEvent(Context context, String var1, String label) {
        if(TextUtils.isEmpty(label)) {
            ULog.c("label is null or empty");
        } else {
            MOBCLICK.onEvent(context, var1, label, -1L, 1);
        }
    }

    public static void onEvent(Context context, String var1, Map<String, String> inputMap) {
        if(inputMap == null) {
            ULog.e("input map is null");
        } else {
            MOBCLICK.onEvent(context, var1, new HashMap(inputMap), -1L);
        }
    }

    public static void onEventValue(Context context, String var1, Map<String, String> inputMap, int var3) {
        HashMap hashMap;
        if(inputMap == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(inputMap);
        }

        hashMap.put("__ct__", Integer.valueOf(var3));
        MOBCLICK.onEvent(context, var1, hashMap, -1L);
    }

    public static void onSocialEvent(Context context, String var1, UMPlatformData... var2) {
        if(context == null) {
            ULog.e("context is null in onShareEvent");
        } else {
            d.d = "3";
            UMSocialService.onShareEvent(context, var1, var2);
        }
    }

    public static void onSocialEvent(Context var0, UMPlatformData... var1) {
        if(var0 == null) {
            ULog.e("context is null in onShareEvent");
        } else {
            d.d = "3";
            UMSocialService.onShareEvent(var0, var1);
        }
    }

    public static void onKillProcess(Context context) {
        MOBCLICK.onKillProcess(context);
    }

    public static void onProfileSignIn(String uid) {
        onProfileSignIn("_adhoc", uid);
    }

    public static void onProfileSignIn(String provider, String uid) {
        if(TextUtils.isEmpty(uid)) {
            ULog.d("uid is null");
        } else if(uid.length() > 64) {
            ULog.d("uid is Illegal(length bigger then  legitimate length).");
        } else {
            if(TextUtils.isEmpty(provider)) {
                MOBCLICK.onProfileSignIn("_adhoc", uid);
            } else {
                if(provider.length() > 32) {
                    ULog.d("provider is Illegal(length bigger then  legitimate length).");
                    return;
                }

                MOBCLICK.onProfileSignIn(provider, uid);
            }

        }
    }

    public static void onProfileSignOff() {
        MOBCLICK.onProfileSignOff();
    }

    public static class UMAnalyticsConfig {
        public String mAppkey;
        public String mChannelId;
        public boolean mIsCrashEnable;
        public MobclickAgent.EScenarioType mType;
        public Context mContext;

        private UMAnalyticsConfig() {
            this.mAppkey = null;
            this.mChannelId = null;
            this.mIsCrashEnable = true;
            this.mType = MobclickAgent.EScenarioType.E_UM_NORMAL;
            this.mContext = null;
        }

        public UMAnalyticsConfig(Context context, String appKey, String channelId) {
            this(context, appKey, channelId, null, true);
        }

        public UMAnalyticsConfig(Context context, String appkey, String channelId, MobclickAgent.EScenarioType scenarioType) {
            this(context, appkey, channelId, scenarioType, true);
        }

        public UMAnalyticsConfig(Context context, String appKey, String channelId, MobclickAgent.EScenarioType scenarioType, boolean isCrashEnable) {
            this.mAppkey = null;
            this.mChannelId = null;
            this.mIsCrashEnable = true;
            this.mType = MobclickAgent.EScenarioType.E_UM_NORMAL;
            this.mContext = null;
            this.mContext = context;
            this.mAppkey = appKey;
            this.mChannelId = channelId;
            this.mIsCrashEnable = isCrashEnable;
            if(scenarioType != null) {
                this.mType = scenarioType;
            } else {
                switch(AnalyticsConfig.getVerticalType(context)) {
                    case 0:
                        this.mType = MobclickAgent.EScenarioType.E_UM_NORMAL;
                        break;
                    case 1:
                        this.mType = MobclickAgent.EScenarioType.E_UM_GAME;
                        break;
                    case 224:
                        this.mType = MobclickAgent.EScenarioType.E_UM_ANALYTICS_OEM;
                        break;
                    case 225:
                        this.mType = MobclickAgent.EScenarioType.E_UM_GAME_OEM;
                }
            }

        }
    }

    public enum EScenarioType {
        E_UM_NORMAL(0),
        E_UM_GAME(1),
        E_UM_ANALYTICS_OEM(224),
        E_UM_GAME_OEM(225);

        private int value;

        EScenarioType(int var3) {
            this.value = var3;
        }

        public int toValue() {
            return this.value;
        }
    }
}
