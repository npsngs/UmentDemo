//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.track;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yxd.sum.tool.EncodeTool;
import com.umeng.tool.SystemUtil;
import com.umeng.tool.ULog;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.database.DBDataTool;
import com.umeng.analytics.database.DBDataTool.a_enum;
import com.yxd.sum.report.AsyncReporter;
import com.yxd.sum.tool.SPTool;

import java.lang.reflect.Method;
import org.json.JSONObject;


public class SessionHelper {
    private static String sessionId = null;
    private static Context context = null;

    public SessionHelper() {
    }

    public boolean commitSession(Context context) {
        SharedPreferences sp = SPTool.getSp(context);
        String sessionId = sp.getString("session_id", null);
        if(sessionId == null) {
            return false;
        } else {
            long sessionStartTime = sp.getLong("session_start_time", 0L);
            long sessionEndTime = sp.getLong("session_end_time", 0L);
            long sessionLastTime;
            if(sessionEndTime != 0L) {
                sessionLastTime = sessionEndTime - sessionStartTime;
                if(Math.abs(sessionLastTime) > 86400000L) {
                }
            }

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("__ii", sessionId);
                jsonObject.put("__e", sessionStartTime);
                jsonObject.put("__f", sessionEndTime);
                double[] location = AnalyticsConfig.getLocation();
                if(location != null) {
                    JSONObject locationJson = new JSONObject();
                    locationJson.put("lat", location[0]);
                    locationJson.put("lng", location[1]);
                    locationJson.put("ts", System.currentTimeMillis());
                    jsonObject.put("__d", locationJson);
                }

                Class TrafficStatsClass = Class.forName("android.net.TrafficStats");
                Method getUidRxBytesMethod = TrafficStatsClass.getMethod("getUidRxBytes", new Class[]{Integer.TYPE});
                Method getUidTxBytesMethod = TrafficStatsClass.getMethod("getUidTxBytes", new Class[]{Integer.TYPE});
                int uid = context.getApplicationInfo().uid;
                if(uid == -1) {
                    return false;
                } else {
                    long uidRx = ((Long)getUidRxBytesMethod.invoke(null, new Object[]{Integer.valueOf(uid)})).longValue();
                    long uidTx = ((Long)getUidTxBytesMethod.invoke(null, new Object[]{Integer.valueOf(uid)})).longValue();
                    if(uidRx > 0L && uidTx > 0L) {
                        JSONObject trafficJson = new JSONObject();
                        trafficJson.put("download_traffic", uidRx);
                        trafficJson.put("upload_traffic", uidTx);
                        jsonObject.put("__c", trafficJson);
                    }

                    DBDataTool.getInstance(context).insertIntoSd(sessionId, jsonObject, a_enum.e);
                    PageTracker.insertIntoSd(SessionHelper.context);
                    ActivityObserver.insertToDBSd(SessionHelper.context);
                    this.removeSessionInfo(sp);
                    return true;
                }
            } catch (Throwable t) {
                return false;
            }
        }
    }

    private void removeSessionInfo(SharedPreferences sp) {
        Editor editor = sp.edit();
        editor.remove("session_start_time");
        editor.remove("session_end_time");
        editor.remove("a_start_time");
        editor.remove("a_end_time");
        editor.apply();
    }

    public String buildSessionId(Context context) {
        String deviceId = SystemUtil.getDeviceId(context);
        String appkey = AnalyticsConfig.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if(appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(currentTimeMillis).append(appkey).append(deviceId);
            sessionId = EncodeTool.getMD5_2(stringBuilder.toString());
            return sessionId;
        }
    }

    public void resumeSession(Context context) {
        SessionHelper.context = context;
        SharedPreferences sp = SPTool.getSp(context);
        if(sp != null) {
            String session;
            Editor edit = sp.edit();
            int lastVersionCode = sp.getInt("versioncode", 0);
            int versionCode = Integer.parseInt(SystemUtil.getVersionCode(SessionHelper.context));
            if(lastVersionCode != 0 && versionCode != lastVersionCode) {
                try {
                    edit.putInt("vers_code", lastVersionCode);
                    edit.putString("vers_name", sp.getString("versionname", ""));
                    edit.apply();
                } catch (Throwable t) {
                }

                if(getSessionID(context) == null) {
                    this.beginSession(context, sp);
                }

                this.pause(SessionHelper.context);
                AsyncReporter.getInstance(SessionHelper.context).packData();
                this.saveToCache(SessionHelper.context);
                AsyncReporter.getInstance(SessionHelper.context).report();
            } else {
                if(this.hasPauseLast(sp)) {
                    session = this.beginSession(context, sp);
                    ULog.c("Start new session: " + session);
                } else {
                    session = sp.getString("session_id", null);
                    edit.putLong("a_start_time", System.currentTimeMillis());
                    edit.putLong("a_end_time", 0L);
                    edit.commit();
                    ULog.c("Extend current session: " + session);
                }

            }
        }
    }

    public void onPause(Context context) {
        SharedPreferences sp = SPTool.getSp(context);
        if(sp != null) {
            long startTime = sp.getLong("a_start_time", 0L);
            if(startTime == 0L && AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                ULog.e("onPause called before onResume");
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                Editor editor = sp.edit();
                editor.putLong("a_start_time", 0L);
                editor.putLong("a_end_time", currentTimeMillis);
                editor.putLong("session_end_time", currentTimeMillis);
                editor.apply();

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("__f", currentTimeMillis);
                    DBDataTool.getInstance(context).insertIntoSd(getSessionID(), jsonObject, a_enum.d);
                } catch (Throwable t) {
                }
            }
        }
    }

    private boolean hasPauseLast(SharedPreferences sp) {
        long start_time = sp.getLong("a_start_time", 0L);
        long end_time = sp.getLong("a_end_time", 0L);
        long currentTimeMillis = System.currentTimeMillis();
        if(start_time != 0L && currentTimeMillis - start_time < AnalyticsConfig.kContinueSessionMillis) {
            ULog.e("onResume called before onPause");
            return false;
        } else {
            return currentTimeMillis - end_time > AnalyticsConfig.kContinueSessionMillis;
        }
    }

    private String beginSession(Context context, SharedPreferences sp) {
        AsyncReporter executeReport = AsyncReporter.getInstance(context);
        String sessionId = this.buildSessionId(context);
        long currentTimeMillis = System.currentTimeMillis();

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("__e", currentTimeMillis);
            DBDataTool.getInstance(SessionHelper.context).insertIntoSd(sessionId, jsonObject, a_enum.c);
        } catch (Throwable t) {
        }

        this.commitSession(context);
        Editor editor = sp.edit();
        editor.putString("session_id", sessionId);
        editor.putLong("session_start_time", System.currentTimeMillis());
        editor.putLong("session_end_time", 0L);
        editor.putLong("a_start_time", currentTimeMillis);
        editor.putLong("a_end_time", 0L);
        editor.putInt("versioncode", Integer.parseInt(SystemUtil.getVersionCode(context)));
        editor.putString("versionname", SystemUtil.getVersionName(context));
        editor.apply();
        executeReport.report(Boolean.valueOf(true));
        return sessionId;
    }

    public boolean pause(Context context) {
        boolean var2 = false;
        SharedPreferences sp = SPTool.getSp(context);
        if(sp == null) {
            return var2;
        } else {
            String sessionId = sp.getString("session_id", null);
            if(sessionId == null) {
                return var2;
            } else {
                long start_time = sp.getLong("a_start_time", 0L);
                long end_time = sp.getLong("a_end_time", 0L);
                if(start_time > 0L && end_time == 0L) {
                    var2 = true;
                    this.onPause(context);
                }

                this.commitSession(context);
                return var2;
            }
        }
    }

    public void saveToCache(Context context) {
        SharedPreferences sp = SPTool.getSp(context);
        if(sp != null) {
            String sessionId = this.buildSessionId(context);
            Editor editor = sp.edit();
            long currentTimeMillis = System.currentTimeMillis();
            editor.putString("session_id", sessionId);
            editor.putLong("session_start_time", System.currentTimeMillis());
            editor.putLong("session_end_time", 0L);
            editor.putLong("a_start_time", currentTimeMillis);
            editor.putLong("a_end_time", 0L);
            editor.putInt("versioncode", Integer.parseInt(SystemUtil.getVersionCode(context)));
            editor.putString("versionname", SystemUtil.getVersionName(context));

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("__e", currentTimeMillis);
                DBDataTool.getInstance(SessionHelper.context).insertIntoSd(sessionId, jsonObject, a_enum.c);
            } catch (Throwable throwable) {
            }

            editor.apply();
        }
    }

    public static String getSessionID(Context context) {
        if(sessionId == null) {
            sessionId = SPTool.getSp(context).getString("session_id", null);
        }

        return sessionId;
    }

    public static String getSessionID() {
        return getSessionID(context);
    }
}
