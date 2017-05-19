//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.track;

import android.content.Context;
import android.content.SharedPreferences;

import com.umeng.tool.CacheTool;
import com.yxd.sum.tool.SPTool;

public class RequestTracker implements RequestCallback {
    private final int d = 3600000;
    public int successfulRequest;
    public int failedRequests;
    private int lastRequestSpent_ms;
    public long lastRequestTime;
    private long lastReq = 0L;
    private long firstActivateTime = 0L;
    private static final String h = "successful_request";
    private static final String i = "failed_requests ";
    private static final String j = "last_request_spent_ms";
    private static final String k = "last_request_time";
    private static final String l_str = "first_activate_time";
    private static final String m_str = "last_req";
    private Context context;

    public RequestTracker(Context var1) {
        this.a(var1);
    }

    private void a(Context context) {
        this.context = context.getApplicationContext();
        SharedPreferences sp = SPTool.getSp(context);
        this.successfulRequest = sp.getInt("successful_request", 0);
        this.failedRequests = sp.getInt("failed_requests ", 0);
        this.lastRequestSpent_ms = sp.getInt("last_request_spent_ms", 0);
        this.lastRequestTime = sp.getLong("last_request_time", 0L);
        this.lastReq = sp.getLong("last_req", 0L);
    }

    public int getLastRequestSpentMs() {
        return this.lastRequestSpent_ms > 3600000?3600000:this.lastRequestSpent_ms;
    }

    public boolean hasNotRequest() {
        boolean noRequest = this.lastRequestTime == 0L;
        boolean noCache = !CacheTool.getInstance(this.context).hasCache();
        return noRequest && noCache;
    }

    public void onRequstSuccess() {
        ++this.successfulRequest;
        this.lastRequestTime = this.lastReq;
    }

    public void onRequestFailed() {
        ++this.failedRequests;
    }

    public void onRequest() {
        this.lastReq = System.currentTimeMillis();
    }

    public void onRequestFinish() {
        this.lastRequestSpent_ms = (int)(System.currentTimeMillis() - this.lastReq);
    }

    public void commitToSp() {
        SharedPreferences sp = SPTool.getSp(this.context);
        sp.edit()
                .putInt("successful_request", this.successfulRequest)
                .putInt("failed_requests ", this.failedRequests)
                .putInt("last_request_spent_ms", this.lastRequestSpent_ms)
                .putLong("last_request_time", this.lastRequestTime)
                .putLong("last_req", this.lastReq)
                .apply();
    }

    public long getFirstActiveTime() {
        SharedPreferences sp = SPTool.getSp(this.context);
        this.firstActivateTime = SPTool.getSp(this.context).getLong("first_activate_time", 0L);
        if(this.firstActivateTime == 0L) {
            this.firstActivateTime = System.currentTimeMillis();
            sp.edit().putLong("first_activate_time", this.firstActivateTime).apply();
        }

        return this.firstActivateTime;
    }

    public long getLastReq() {
        return this.lastReq;
    }

    public void request() {
        this.onRequest();
    }

    public void requestFinish() {
        this.onRequestFinish();
    }

    public void requstSuccess() {
        this.onRequstSuccess();
    }

    public void RequestFailed() {
        this.onRequestFailed();
    }
}
