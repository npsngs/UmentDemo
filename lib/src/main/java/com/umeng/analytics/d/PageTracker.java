//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.d;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.database.DBDataTool;
import com.umeng.analytics.database.DBDataTool.a_enum;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class PageTracker {
    private final Map<String, Long> record = new HashMap();
    private static JSONObject pageRecordData = new JSONObject();

    public PageTracker() {
    }

    public void onPageStart(String pageName) {
        if(!TextUtils.isEmpty(pageName)) {
            synchronized(this.record) {
                this.record.put(pageName, Long.valueOf(System.currentTimeMillis()));
            }
        }

    }

    public void onPageEnd(String pageName) {
        if(!TextUtils.isEmpty(pageName)) {
            Long time;
            synchronized(this.record) {
                time = this.record.remove(pageName);
            }

            if(time == null) {
                return;
            }

            long duration = System.currentTimeMillis() - time.longValue();
            synchronized(pageRecordData) {
                try {
                    pageRecordData = new JSONObject();
                    pageRecordData.put("page_name", pageName);
                    pageRecordData.put("duration", duration);
                } catch (Throwable throwable) {
                }
            }
        }
    }

    public void leaveLastPage() {
        String pageName = null;
        long lastTime = 0L;
        synchronized(this.record) {
            Iterator iterator = this.record.entrySet().iterator();

            while(true) {
                if(!iterator.hasNext()) {
                    break;
                }

                Entry entry = (Entry)iterator.next();
                if(((Long)entry.getValue()).longValue() > lastTime) {
                    lastTime = ((Long)entry.getValue()).longValue();
                    pageName = (String)entry.getKey();
                }
            }
        }

        if(pageName != null) {
            this.onPageEnd(pageName);
        }

    }

    public static void insertIntoSd(Context context) {
        try {
            if(context != null) {
                synchronized(pageRecordData) {
                    if(pageRecordData.length() > 0) {
                        DBDataTool.getInstance(context).insertIntoSd(SessionHelper.getSessionID(), pageRecordData, a_enum.b);
                        pageRecordData = new JSONObject();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
