//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.text.TextUtils;

import com.umeng.analytics.aggregate.result.OpResult;
import com.umeng.analytics.database.DBDataTool;
import com.yxd.sum.track.ActivityObserver;
import com.umeng.tool.SafeRunnable;
import com.umeng.tool.StringTool;
import com.umeng.tool.SystemUtil;
import com.umeng.tool.TaskExecutor;
import com.umeng.tool.g;
import com.umeng.tool.ULog;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.analytics.MobclickAgent.UMAnalyticsConfig;
import com.umeng.analytics.aggregate.tool.AggTool;
import com.yxd.sum.tool.SPTool;
import com.yxd.sum.report.AsyncReporter;
import com.yxd.sum.track.MUncaughtExceptionHandler;
import com.yxd.sum.track.EventTracker;
import com.yxd.sum.track.ExceptionHandler;
import com.yxd.sum.track.SessionHelper;
import com.yxd.sum.track.PageTracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

public class Mobclick implements ExceptionHandler {
    private Context context = null;
    private g b;
    private MUncaughtExceptionHandler uncaughtExceptionHandler = new MUncaughtExceptionHandler();
    private PageTracker pageTracker = new PageTracker();
    private SessionHelper sessionHelper = new SessionHelper();
    private EventTracker eventTracker = null;
    private AsyncReporter executeReport = null;
    private ActivityObserver activityObserver = null;
    private AggTool aggTool = null;
    private boolean isToolsInit = false;
    private boolean isDataLoaded = false;
    private boolean isInitActivityObserver = false;

    Mobclick() {
        this.uncaughtExceptionHandler.setExceptionHandler(this);
    }

    private void init(Context context) {
        try {
            if(context == null) {
                return;
            }

            if(VERSION.SDK_INT > 13 && !this.isInitActivityObserver && context instanceof Activity) {
                this.activityObserver = new ActivityObserver((Activity)context);
                this.isInitActivityObserver = true;
            }

            if(!this.isToolsInit) {
                this.context = context.getApplicationContext();
                this.eventTracker = new EventTracker(this.context);
                this.executeReport = AsyncReporter.getInstance(this.context);
                this.isToolsInit = true;
                if(this.aggTool == null) {
                    this.aggTool = AggTool.getInstance(this.context);
                }

                if(!this.isDataLoaded) {
                    TaskExecutor.scheduleExecute(new SafeRunnable() {
                        public void safeRun() {
                            Mobclick.this.aggTool.loadAggData(new OpResult() {
                                public void setResult(Object o, boolean var2) {
                                    Mobclick.this.isDataLoaded = true;
                                }
                            });
                        }
                    });
                }
            }
        } catch (Throwable t) {
        }

    }

    void onPageStart(String pageName) {
        if(!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.pageTracker.onPageStart(pageName);
            } catch (Exception e) {
            }
        }

    }

    void onPageEnd(String pageName) {
        if(!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.pageTracker.onPageEnd(pageName);
            } catch (Exception e) {
            }
        }
    }

    public void a(g var1) {
        this.b = var1;
    }

    public void setVerticalType(Context context, int verticalType) {
        AnalyticsConfig.setVerticalType(context, verticalType);
    }

    void onResume(final Context context) {
        if(context == null) {
            ULog.e("unexpected null context in onResume");
        } else {
            if(AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                this.pageTracker.onPageStart(context.getClass().getName());
            }

            try {
                if(!this.isToolsInit || !this.isInitActivityObserver) {
                    this.init(context);
                }

                TaskExecutor.execute(new SafeRunnable() {
                    public void safeRun() {
                        Mobclick.this.resumeSession(context.getApplicationContext());
                    }
                });
            } catch (Exception e) {
                ULog.e("Exception occurred in Mobclick.onResume(). ", e);
            }
        }
    }

    void onPause(final Context context) {
        if(context == null) {
            ULog.e("unexpected null context in onPause");
        } else {
            if(AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                this.pageTracker.onPageEnd(context.getClass().getName());
            }

            try {
                if(!this.isToolsInit || !this.isInitActivityObserver) {
                    this.init(context);
                }

                TaskExecutor.execute(new SafeRunnable() {
                    public void safeRun() {
                        Mobclick.this.saveData(context.getApplicationContext());
                        Mobclick.this.aggTool.f();
                    }
                });
            } catch (Exception e) {
                if(ULog.isDebugMode) {
                    ULog.e("Exception occurred in Mobclick.onRause(). ", e);
                }
            }

        }
    }

    public SessionHelper getSessionHelper() {
        return this.sessionHelper;
    }

    public void a(Context var1, String var2, HashMap<String, Object> var3) {
        try {
            if(!this.isToolsInit || !this.isInitActivityObserver) {
                this.init(var1);
            }

            this.eventTracker.reportEvent(var2, var3);
        } catch (Exception var5) {
            if(ULog.isDebugMode) {
                ULog.e(var5);
            }
        }

    }

    void reportError(Context context, String contextStr) {
        if(!TextUtils.isEmpty(contextStr)) {
            if(context == null) {
                ULog.e("unexpected null context in reportError");
            } else {
                try {
                    if(!this.isToolsInit || !this.isInitActivityObserver) {
                        this.init(context);
                    }

                    JSONObject var3 = new JSONObject();
                    var3.put("ts", System.currentTimeMillis());
                    var3.put("error_source", 2);
                    var3.put("context", contextStr);
                    DBDataTool.getInstance(this.context).insertToEr(SessionHelper.getSessionID(), var3.toString(), 2);
                } catch (Exception e) {
                    if(ULog.isDebugMode) {
                        ULog.e(e);
                    }
                }

            }
        }
    }

    void reportError(Context context, Throwable throwable) {
        if(context != null && throwable != null) {
            try {
                this.reportError(context, StringTool.readStrFromThrowable(throwable));
            } catch (Exception e) {
                if(ULog.isDebugMode) {
                    ULog.e(e);
                }
            }
        }
    }

    private void resumeSession(Context context) {

        this.sessionHelper.resumeSession(context);



        if(this.b != null) {
            this.b.a();
        }
    }

    private void saveData(Context context) {
        this.sessionHelper.onPause(context);
        PageTracker.insertIntoSd(context);
        ActivityObserver.insertToDBSd(context);
        this.executeReport.getPreReport(this.context).saveData(context);
        if(this.b != null) {
            this.b.b();
        }

    }

    void c(Context context) {
        try {
            if(!this.isToolsInit || !this.isInitActivityObserver) {
                this.init(context);
            }

            this.executeReport.report();
        } catch (Throwable t) {
        }

    }

    public void onEvent(Context context, List<String> cklist, int var3, String var4) {
        try {
            if(!this.isToolsInit || !this.isInitActivityObserver) {
                this.init(context);
            }

            this.eventTracker.isValidEventID(cklist, var3, var4);
        } catch (Exception e) {
            if(ULog.isDebugMode) {
                ULog.e(e);
            }
        }

    }

    public void onEvent(Context context, String var2, String label, long var4, int var6) {
        try {
            if(!this.isToolsInit || !this.isInitActivityObserver) {
                this.init(context);
            }

            this.eventTracker.reportEvent(var2, label, var4, var6);
        } catch (Exception e) {
            if(ULog.isDebugMode) {
                ULog.e(e);
            }
        }

    }

    void onEvent(Context context, String var2, Map<String, Object> inputMap, long var4) {
        try {
            if(!this.isToolsInit || !this.isInitActivityObserver) {
                this.init(context);
            }

            this.eventTracker.reportEvent(var2, inputMap, var4);
        } catch (Exception var7) {
            if(ULog.isDebugMode) {
                ULog.e(var7);
            }
        }

    }

    public void a(Context var1, String var2, Map<String, Object> var3) {
    }

    void onKillProcess(Context context) {
        try {
            this.activityObserver.a(context);
            this.pageTracker.leaveLastPage();
            this.saveData(context);
            SPTool.getSp(context).edit().apply();
            this.aggTool.d();
            aggTool.a();
        } catch (Exception e) {
            if(ULog.isDebugMode) {
                e.printStackTrace();
            }
        }

    }

    public void onAppCrash(Throwable throwable) {
        try {
            this.pageTracker.leaveLastPage();
            if(this.context != null) {
                if(throwable != null && this.executeReport != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ts", System.currentTimeMillis());
                    jsonObject.put("error_source", 1);
                    jsonObject.put("context", StringTool.readStrFromThrowable(throwable));
                    DBDataTool.getInstance(this.context).insertToEr(SessionHelper.getSessionID(), jsonObject.toString(), 1);
                }

                this.aggTool.e();
                this.activityObserver.a(this.context);
                this.saveData(this.context);
                SPTool.getSp(this.context).edit().apply();
            }

            aggTool.a();
        } catch (Exception e) {
            if(ULog.isDebugMode) {
                ULog.e("Exception in onAppCrash", e);
            }
        }

    }

    void onProfileSignIn(final String au_p, final String au_u) {
        try {
            TaskExecutor.execute(new SafeRunnable() {
                public void safeRun() {
                    String[] au = AU.getAU(Mobclick.this.context);
                    if(au == null || !au_p.equals(au[0]) || !au_u.equals(au[1])) {
                        Mobclick.this.executeReport.getPreReport(Mobclick.this.context).saveData(Mobclick.this.context);
                        boolean var2x = Mobclick.this.getSessionHelper().pause(Mobclick.this.context);
                        AsyncReporter.getInstance(Mobclick.this.context).packData();
                        if(var2x) {
                            Mobclick.this.getSessionHelper().saveToCache(Mobclick.this.context);
                        }
                        AU.saveAU(Mobclick.this.context, au_p, au_u);
                    }
                }
            });
        } catch (Exception e) {
            if(ULog.isDebugMode) {
                ULog.e(" Excepthon  in  onProfileSignIn", e);
            }
        }

    }

    void onProfileSignOff() {
        try {
            TaskExecutor.execute(new SafeRunnable() {
                public void safeRun() {
                    String[] var1 = AU.getAU(Mobclick.this.context);
                    if(var1 != null && !TextUtils.isEmpty(var1[0]) && !TextUtils.isEmpty(var1[1])) {
                        Mobclick.this.executeReport.getPreReport(Mobclick.this.context).saveData(Mobclick.this.context);
                        boolean var2 = Mobclick.this.getSessionHelper().pause(Mobclick.this.context);
                        AsyncReporter.getInstance(Mobclick.this.context).packData();
                        if(var2) {
                            Mobclick.this.getSessionHelper().saveToCache(Mobclick.this.context);
                        }

                        AU.clearAU(Mobclick.this.context);
                    }

                }
            });
        } catch (Exception e) {
            if(ULog.isDebugMode) {
                ULog.e(" Excepthon  in  onProfileSignOff", e);
            }
        }

    }

    void setCatchUncaughtExceptions(boolean catchException) {
        AnalyticsConfig.CATCH_EXCEPTION = catchException;
    }

    void setOpenGLContext(GL10 gl10) {
        String[] gpuInfo = SystemUtil.getGL10Value(gl10);
        if(gpuInfo.length == 2) {
            AnalyticsConfig.GPU_VENDER = gpuInfo[0];
            AnalyticsConfig.GPU_RENDERER = gpuInfo[1];
        }

    }

    void openActivityDurationTrack(boolean activityDurationOpen) {
        AnalyticsConfig.ACTIVITY_DURATION_OPEN = activityDurationOpen;
    }

    void setCheckDevice(boolean isCheckDevice) {
        UMConst.isCheckDevice = isCheckDevice;
    }

    void setDebugMode(boolean isDebugMode) {
        ULog.isDebugMode = isDebugMode;
    }

    void enableEncrypt(boolean encrypt) {
        AnalyticsConfig.setEncrypt(encrypt);
    }

    void setLocation(double location1, double location2) {
        if(AnalyticsConfig.location == null) {
            AnalyticsConfig.location = new double[2];
        }

        AnalyticsConfig.location[0] = location1;
        AnalyticsConfig.location[1] = location2;
    }

    void setLatentWindow(long latentWindow) {
        AnalyticsConfig.sLatentWindow = (int)latentWindow * 1000;
    }

    void setVerticalType(Context context, EScenarioType scenarioType) {
        if(context != null) {
            this.context = context.getApplicationContext();
        }

        if(scenarioType != null) {
            this.setVerticalType(context, scenarioType.toValue());
        }

    }

    void setSecretKey(Context context, String secretKey) {
        if(context != null) {
            this.context = context.getApplicationContext();
        }

        AnalyticsConfig.setSecretKey(context, secretKey);
    }

    void startWithConfigure(UMAnalyticsConfig analyticsConfig) {
        if(analyticsConfig.mContext != null) {
            this.context = analyticsConfig.mContext.getApplicationContext();
        }

        if(!TextUtils.isEmpty(analyticsConfig.mAppkey)) {
            AnalyticsConfig.setAppKey(analyticsConfig.mContext, analyticsConfig.mAppkey);
            if(!TextUtils.isEmpty(analyticsConfig.mChannelId)) {
                AnalyticsConfig.setChannel(analyticsConfig.mChannelId);
            }

            AnalyticsConfig.CATCH_EXCEPTION = analyticsConfig.mIsCrashEnable;
            this.setVerticalType(this.context, analyticsConfig.mType);
        } else {
            ULog.e("the appkey is null!");
        }
    }

    void setSessionContinueMillis(long continueSessionMillis) {
        AnalyticsConfig.kContinueSessionMillis = continueSessionMillis;
    }

    public void a(Context var1, String var2, Object var3) {
    }

    public void c(Context var1, String var2) {
    }

    public Object d(Context var1, String var2) {
        return null;
    }

    public String e(Context var1) {
        return null;
    }

    private JSONObject j(Context context) {
        try {
            SharedPreferences sp = SPTool.getSp(context);
            String var3 = sp.getString("sp", null);
            if(!TextUtils.isEmpty(var3)) {
                JSONObject var4 = new JSONObject(var3);
                return var4;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void f(Context var1) {
    }

    public void a(Context context, List<String> var2) {
        try {
            if(!this.isToolsInit || !this.isInitActivityObserver) {
                this.init(context);
            }

            this.eventTracker.isValidEventID(context, var2);
        } catch (Exception var4) {
            ULog.e(var4);
        }

    }
}
