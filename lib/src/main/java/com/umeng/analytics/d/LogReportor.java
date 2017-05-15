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
import com.umeng.analytics.c.UMEnvelopeData;
import com.umeng.tool.CacheTool;
import com.umeng.tool.EncodeUtil;
import com.umeng.tool.ULog;
import com.umeng.tool.StringTool;
import com.umeng.tool.SystemUtil;
import com.umeng.tool.CacheTool.FileCache;
import com.umeng.tool.CacheTool.SendFile;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.c.UMengItCache;
import com.umeng.analytics.c.ImprintTool;
import com.umeng.analytics.f.IdTracking;
import com.umeng.analytics.f.Response;
import java.io.File;
import java.io.FileInputStream;
import org.json.JSONObject;

import a.a.a.UMBeanPacker;
import a.a.a.b.UMBeanCoder_a;

public class LogReportor {
    private static final int a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private UMengItCache uMengItCache;
    private ImprintTool imprintTool;
    private static Context context;
    private RequestTracker requestTracker;
    private HttpSender httpSender;
    private JSONObject jsonLog;
    private boolean isCodex = false;
    private boolean l;

    public LogReportor(Context context, RequestTracker requestTracker) {
        this.uMengItCache = UMengItCache.getInstance(context);
        this.imprintTool = ImprintTool.getInstance(context);
        LogReportor.context = context;
        this.requestTracker = requestTracker;
        this.httpSender = new HttpSender(context);
        this.httpSender.setRequestCallback(this.requestTracker);
    }

    public void setJsonLog(JSONObject jsonObject) {
        this.jsonLog = jsonObject;
    }

    public void setCodex(boolean isCodex) {
        this.isCodex = isCodex;
    }

    public void b(boolean var1) {
        this.l = var1;
    }

    public void setOptionSetter(OptionSetter optionSetter) {
        this.imprintTool.setOptionSetter(optionSetter);
    }

    public void a() {
        try {
            if(this.jsonLog != null) {
                this.reportJson();
            } else {
                this.reportFile();
            }
        } catch (Throwable t) {
        }

        try {
            if(SystemUtil.isCurrentWifiConnect(context)) {
                SharedPreferences sp = SP_Util.getSp(context);
                if(sp != null) {
                    String var2 = sp.getString("uopdta", "");
                    long var3 = CalendarUtil.getDurationDays(System.currentTimeMillis());
                    if(TextUtils.isEmpty(var2)) {
                        long var5 = sp.getLong("uopdte", -1L);
                        int var7 = sp.getInt("uopcnt", 0);
                        Editor editor;
                        if(var5 == -1L) {
                            editor = sp.edit();
                            ++var7;
                            editor.putInt("uopcnt", var7).commit();
                            this.httpSender.send();
                        } else if(var3 != var5) {
                            byte var10 = 0;
                            editor = sp.edit();
                            var7 = var10 + 1;
                            editor.putInt("uopcnt", var7).commit();
                            this.httpSender.send();
                        } else if(var7 < 2) {
                            editor = sp.edit();
                            ++var7;
                            editor.putInt("uopcnt", var7).commit();
                            this.httpSender.send();
                        }

                        sp.edit().putLong("uopdte", var3).commit();
                    }
                } else {
                    this.httpSender.send();
                }
            }
        } catch (Throwable t) {
        }

    }

    private void reportFile() {
        FileCache fileCache = CacheTool.getInstance(context).getFileCache();
        fileCache.send(new SendFile() {
            public void preSend(File file) {
            }

            public boolean send(File file) {
                try {
                    FileInputStream fis = null;
                    byte[] data;
                    try {
                        fis = new FileInputStream(file);
                        data = EncodeUtil.readData(fis);
                    } finally {
                        EncodeUtil.close(fis);
                    }

                    byte[] respData = LogReportor.this.httpSender.send(data);
                    int resp_code;
                    if(respData == null) {
                        resp_code = 1;
                    } else {
                        resp_code = LogReportor.this.setRequestCallback(respData);
                    }

                    return LogReportor.this.l?true:resp_code != 1;
                } catch (Exception e) {
                    return false;
                }
            }

            public void afterSend(File f) {
                LogReportor.this.requestTracker.commitToSp();
            }
        });
    }

    private void reportJson() {
        try {
            this.uMengItCache.a();

            try {
                IdTracking idTracking = this.uMengItCache.getIdTracking();
                byte[] var2 = (new UMBeanPacker()).pack2Bytes(idTracking);
                String id_tracking = Base64.encodeToString(var2, 0);
                if(!TextUtils.isEmpty(id_tracking)) {
                    JSONObject jsonObject = this.jsonLog.getJSONObject("header");
                    jsonObject.put("id_tracking", id_tracking);
                    this.jsonLog.put("header", jsonObject);
                }
            } catch (Exception e) {
            }

            byte[] data = String.valueOf(this.jsonLog).getBytes();
            if(data == null) {
                return;
            }

            if(StringTool.isTooLong(context, data)) {
                return;
            }

            UMEnvelopeData envelopeData;
            if(!this.isCodex) {
                envelopeData = UMEnvelopeData.create(context, AnalyticsConfig.getAppkey(context), data);
            } else {
                envelopeData = UMEnvelopeData.createCodex(context, AnalyticsConfig.getAppkey(context), data);
            }

            byte[] enveloped_data = envelopeData.envelope();
            CacheTool.getInstance(context).clearData();
            byte[] respData = this.httpSender.send(enveloped_data);
            int resp_code;
            if(respData == null) {
                resp_code = 1;
            } else {
                resp_code = this.setRequestCallback(respData);
            }

            switch(resp_code) {
                case 1:
                    if(!this.l) {
                        CacheTool.getInstance(context).saveToCache(enveloped_data);
                    }
                    break;
                case 2:
                    this.uMengItCache.d();
                    this.requestTracker.commitToSp();
                    break;
                case 3:
                    this.requestTracker.commitToSp();
            }
        } catch (Throwable t) {
        }

    }

    private int setRequestCallback(byte[] respData) {
        Response response = new Response();
        a.a.a.g var3 = new a.a.a.g(new UMBeanCoder_a.a_inner());

        try {
            var3.a(response, respData);
            if(response.respCode == 1) {
                this.imprintTool.b(response.getImprint());
                this.imprintTool.writeToCache();
            }

            ULog.c("setRequestCallback log:" + response.f());
        } catch (Throwable t) {
        }

        return response.respCode == 1?2:3;
    }
}
