package com.yxd.sum.report;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;

import com.umeng.analytics.aggregate.tool.CalendarUtil;
import com.umeng.analytics.c.UMEnvelopeData;
import com.umeng.analytics.d.OptionSetter;
import com.yxd.sum.track.RequestTracker;
import com.yxd.sum.tool.SPTool;
import com.umeng.tool.CacheTool;
import com.yxd.sum.tool.EncodeTool;
import com.umeng.tool.ULog;
import com.umeng.tool.StringTool;
import com.umeng.tool.SystemUtil;
import com.umeng.tool.CacheTool.FileCache;
import com.umeng.tool.CacheTool.SendFile;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.c.UMengItCache;
import com.umeng.analytics.c.ImprintTool;
import com.yxd.sum.bean.IdTracking;
import com.yxd.sum.bean.Response;
import java.io.File;
import java.io.FileInputStream;
import org.json.JSONObject;

import com.yxd.sum.coder.NBeanUnpacker;
import com.yxd.sum.coder.NBeanPacker;
import com.yxd.sum.coder.OBeanCoder;

public class DataReportor {
    private UMengItCache uMengItCache;
    private ImprintTool imprintTool;
    private static Context context;
    private RequestTracker requestTracker;
    private HttpSender httpSender;
    private JSONObject jsonLog;
    private boolean isCodex = false;
    private boolean isDiscardOnFail;

    public DataReportor(Context context, RequestTracker requestTracker) {
        this.uMengItCache = UMengItCache.getInstance(context);
        this.imprintTool = ImprintTool.getInstance(context);
        DataReportor.context = context;
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
        }

        try {
            if(SystemUtil.isCurrentWifiConnect(context)) {
                SharedPreferences sp = SPTool.getSp(context);
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
                            this.httpSender.sendHttps();
                        } else if(dta != var5) {
                            byte var10 = 0;
                            editor = sp.edit();
                            uopcnt = var10 + 1;
                            editor.putInt("uopcnt", uopcnt).apply();
                            this.httpSender.sendHttps();
                        } else if(uopcnt < 2) {
                            editor = sp.edit();
                            ++uopcnt;
                            editor.putInt("uopcnt", uopcnt).apply();
                            this.httpSender.sendHttps();
                        }

                        sp.edit().putLong("uopdte", dta).apply();
                    }
                } else {
                    this.httpSender.sendHttps();
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
                        data = EncodeTool.readData(fis);
                    } finally {
                        EncodeTool.close(fis);
                    }

                    byte[] respData = DataReportor.this.httpSender.send(data);
                    int resp_code;
                    if(respData == null) {
                        resp_code = 1;
                    } else {
                        resp_code = DataReportor.this.parseResponse(respData);
                    }

                    return DataReportor.this.isDiscardOnFail ?true:resp_code != 1;
                } catch (Exception e) {
                    return false;
                }
            }

            public void afterSend(File f) {
                DataReportor.this.requestTracker.commitToSp();
            }
        });
    }

    private void reportJson() {
        try {
            this.uMengItCache.invalidate();

            try {
                IdTracking idTracking = this.uMengItCache.getIdTracking();
                byte[] var2 = (new NBeanPacker()).pack2Bytes(idTracking);
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
        NBeanUnpacker beanUnpacker = new NBeanUnpacker(new OBeanCoder.UMBeanCoder_a_Builder());

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
