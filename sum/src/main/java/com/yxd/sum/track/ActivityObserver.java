//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.track;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;

import com.umeng.analytics.database.DBDataTool;
import com.umeng.analytics.database.DBDataTool.a_enum;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ActivityObserver {
    public static String activityName = null;
    private final Map<String, Long> map = new HashMap();
    private static JSONObject jsonObject = new JSONObject();
    private Application app = null;
    ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        public void onActivityStopped(Activity activity) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityResumed(Activity activity) {
            ActivityObserver.this.onActivityResumed(activity);
        }

        public void onActivityPaused(Activity activity) {
            ActivityObserver.this.onActivityPaused(activity);
        }

        public void onActivityDestroyed(Activity var1) {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }
    };

    public ActivityObserver(Activity activity) {
        if(activity != null) {
            this.app = activity.getApplication();
            this.a(activity);
        }

    }

    private void a(Activity activity) {
        this.app.registerActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
        if(activityName == null) {
            this.onActivityResumed(activity);
        }

    }

    public void a() {
        if(this.app != null) {
            this.app.unregisterActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
        }

    }

    public void a(Context context) {
        this.onActivityPaused(null);
        this.a();
    }

    public static void insertToDBSd(Context context) {
        try {
            synchronized(jsonObject) {
                if(jsonObject.length() > 0) {
                    DBDataTool.getInstance(context).insertIntoSd(SessionHelper.getSessionID(), jsonObject, a_enum.a);
                    jsonObject = new JSONObject();
                }
            }
        } catch (Throwable throwable) {
        }

    }

    private void onActivityResumed(Activity activity) {
        activityName = activity.getPackageName() + "." + activity.getLocalClassName();
        synchronized(this.map) {
            this.map.put(activityName, Long.valueOf(System.currentTimeMillis()));
        }
    }

    private void onActivityPaused(Activity activity) {
        try {
            long var2 = 0L;
            synchronized(this.map) {
                if(this.map.containsKey(activityName)) {
                    var2 = System.currentTimeMillis() - (this.map.get(activityName)).longValue();
                    this.map.remove(activityName);
                }
            }

            synchronized(jsonObject) {
                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("page_name", activityName);
                    jsonObject.put("duration", var2);
                } catch (Throwable t) {
                }
            }
        } catch (Throwable t) {
        }

    }
}
