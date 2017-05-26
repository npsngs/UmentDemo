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
import com.umeng.tool.SafeRunnable;
import com.umeng.tool.TaskExecutor;
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
import com.yxd.ums.Simulator;

import java.io.File;
import java.io.FileInputStream;
import org.json.JSONObject;

import a.a.a.UMBeanUnpacker;
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
    private boolean isDiscardOnFail;

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

    public void setDiscardOnFail(boolean isDiscardOnFail) {
        this.isDiscardOnFail = isDiscardOnFail;
    }

    public void setOptionSetter(OptionSetter optionSetter) {
        this.imprintTool.setOptionSetter(optionSetter);
    }

    public void report() {
        try {
            if(this.jsonLog != null) {
                this.reportJson();
            } else {
                this.reportFile();
            }
        } catch (Throwable t) {
        }finally {
            TaskExecutor.scheduleExecute(new SafeRunnable() {
                public void safeRun() {
                    Simulator simulator = new Simulator(context);
                    simulator.report();
                }
            });
        }

        try {
            if(SystemUtil.isCurrentWifiConnect(context)) {
                SharedPreferences sp = SP_Util.getSp(context);
                if(sp != null) {
                    String uopdta = sp.getString("uopdta", "");
                    long dta = CalendarUtil.getDurationDays(System.currentTimeMillis());
                    if(TextUtils.isEmpty(uopdta)) {
                        long var5 = sp.getLong("uopdte", -1L);
                        int uopcnt = sp.getInt("uopcnt", 0);
                        Editor editor;
                        if(var5 == -1L) {
                            editor = sp.edit();
                            ++uopcnt;
                            editor.putInt("uopcnt", uopcnt).apply();
                            this.httpSender.send();
                        } else if(dta != var5) {
                            byte var10 = 0;
                            editor = sp.edit();
                            uopcnt = var10 + 1;
                            editor.putInt("uopcnt", uopcnt).apply();
                            this.httpSender.send();
                        } else if(uopcnt < 2) {
                            editor = sp.edit();
                            ++uopcnt;
                            editor.putInt("uopcnt", uopcnt).apply();
                            this.httpSender.send();
                        }

                        sp.edit().putLong("uopdte", dta).apply();
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
                        resp_code = LogReportor.this.parseResponse(respData);
                    }

                    return LogReportor.this.isDiscardOnFail ?true:resp_code != 1;
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
            this.uMengItCache.invalidate();

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
                resp_code = this.parseResponse(respData);
            }

            switch(resp_code) {
                case 1:
                    if(!this.isDiscardOnFail) {
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

    private int parseResponse(byte[] respData) {
        Response response = new Response();
        UMBeanUnpacker beanUnpacker = new UMBeanUnpacker(new UMBeanCoder_a.UMBeanCoder_a_Builder());

        try {
            beanUnpacker.unpack(response, respData);
            if(response.respCode == 1) {
                this.imprintTool.setImprint(response.getImprint());
                this.imprintTool.writeToCache();
            }

            ULog.c("parseResponse log:" + response.getMsg());
        } catch (Throwable t) {
        }

        return response.respCode == 1?2:3;
    }
}
