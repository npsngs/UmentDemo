//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;

import com.umeng.analytics.aggregate.tool.CalendarUtil;
import com.umeng.tool.CacheTool;
import com.umeng.tool.EncodeUtil;
import com.umeng.tool.ULog;
import com.umeng.tool.StringTool;
import com.umeng.tool.SystemUtil;
import com.umeng.tool.CacheTool.FileCache;
import com.umeng.tool.CacheTool.b;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.c.f;
import com.umeng.analytics.c.h;
import com.umeng.analytics.f.d;
import com.umeng.analytics.f.g;
import java.io.File;
import java.io.FileInputStream;
import org.json.JSONObject;

public class LogReportor {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c_int = 3;
    private f d;
    private h e_h;
    private final int f_int = 1;
    private static Context context;
    private RequestTracker requestTracker;
    private HttpSender httpSender;
    private JSONObject jsonObject;
    private boolean k = false;
    private boolean l;

    public LogReportor(Context context, RequestTracker requestTracker) {
        this.d = f.a(context);
        this.e_h = h.getInstance(context);
        LogReportor.context = context;
        this.requestTracker = requestTracker;
        this.httpSender = new HttpSender(context);
        this.httpSender.setRequestCallback(this.requestTracker);
    }

    public void a(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void a(boolean var1) {
        this.k = var1;
    }

    public void b(boolean var1) {
        this.l = var1;
    }

    public void a(l var1) {
        this.e_h.a(var1);
    }

    public void a() {
        try {
            if(this.jsonObject != null) {
                this.c();
            } else {
                this.b();
            }
        } catch (Throwable var9) {
            ;
        }

        try {
            if(SystemUtil.isCurrentWifiConnect(context)) {
                SharedPreferences var1 = SP_Util.getSp(context);
                if(var1 != null) {
                    String var2 = var1.getString("uopdta", "");
                    long var3 = CalendarUtil.getDurationDays(System.currentTimeMillis());
                    if(TextUtils.isEmpty(var2)) {
                        long var5 = var1.getLong("uopdte", -1L);
                        int var7 = var1.getInt("uopcnt", 0);
                        Editor var10000;
                        if(var5 == -1L) {
                            var10000 = var1.edit();
                            ++var7;
                            var10000.putInt("uopcnt", var7).commit();
                            this.httpSender.send();
                        } else if(var3 != var5) {
                            byte var10 = 0;
                            var10000 = var1.edit();
                            var7 = var10 + 1;
                            var10000.putInt("uopcnt", var7).commit();
                            this.httpSender.send();
                        } else if(var7 < 2) {
                            var10000 = var1.edit();
                            ++var7;
                            var10000.putInt("uopcnt", var7).commit();
                            this.httpSender.send();
                        }

                        var1.edit().putLong("uopdte", var3).commit();
                    }
                } else {
                    this.httpSender.send();
                }
            }
        } catch (Throwable var8) {
            ;
        }

    }

    private void b() {
        FileCache var1 = CacheTool.getInstance(context).getFileCache();
        var1.a(new b() {
            public void a(File var1) {
            }

            public boolean b(File var1) {
                try {
                    Object var2 = null;
                    FileInputStream var3 = null;

                    byte[] var10;
                    try {
                        var3 = new FileInputStream(var1);
                        var10 = EncodeUtil.readData(var3);
                    } finally {
                        EncodeUtil.close(var3);
                    }

                    boolean var4 = true;
                    byte[] var5 = LogReportor.this.httpSender.send(var10);
                    int var11;
                    if(var5 == null) {
                        var11 = 1;
                    } else {
                        var11 = LogReportor.this.a(var5);
                    }

                    return LogReportor.this.l?true:var11 != 1;
                } catch (Exception var9) {
                    return false;
                }
            }

            public void c(File var1) {
                LogReportor.this.requestTracker.commitToSp();
            }
        });
    }

    private void c() {
        try {
            this.d.a();

            try {
                d var1 = this.d.b();
                byte[] var2 = (new a.a.a.m()).a(var1);
                String var3 = Base64.encodeToString(var2, 0);
                if(!TextUtils.isEmpty(var3)) {
                    JSONObject var4 = this.jsonObject.getJSONObject("header");
                    var4.put("id_tracking", var3);
                    this.jsonObject.put("header", var4);
                }
            } catch (Exception var6) {
                ;
            }

            byte[] var8 = String.valueOf(this.jsonObject).getBytes();
            if(var8 == null) {
                return;
            }

            if(StringTool.a(context, var8)) {
                return;
            }

            com.umeng.analytics.c.c var9 = null;
            if(!this.k) {
                var9 = com.umeng.analytics.c.c.a(context, AnalyticsConfig.getAppkey(context), var8);
            } else {
                var9 = com.umeng.analytics.c.c.b(context, AnalyticsConfig.getAppkey(context), var8);
            }

            byte[] var10 = var9.c();
            CacheTool.getInstance(context).g();
            boolean var11 = true;
            byte[] var5 = this.httpSender.send(var10);
            int var12;
            if(var5 == null) {
                var12 = 1;
            } else {
                var12 = this.a(var5);
            }

            switch(var12) {
                case 1:
                    if(!this.l) {
                        CacheTool.getInstance(context).saveToCache(var10);
                    }
                    break;
                case 2:
                    this.d.d();
                    this.requestTracker.commitToSp();
                    break;
                case 3:
                    this.requestTracker.commitToSp();
            }
        } catch (Throwable var7) {
            ;
        }

    }

    private int a(byte[] var1) {
        g var2 = new g();
        a.a.a.g var3 = new a.a.a.g(new a.a.a.b.a.a_inner());

        try {
            var3.a(var2, var1);
            if(var2.a == 1) {
                this.e_h.b(var2.i());
                this.e_h.d();
            }

            ULog.c("setRequestCallback log:" + var2.f());
        } catch (Throwable t) {
        }

        return var2.a == 1?2:3;
    }
}
