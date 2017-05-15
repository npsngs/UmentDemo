//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;

import com.umeng.analytics.AU;
import com.umeng.analytics.aggregate.tool.AggTool;
import com.umeng.analytics.c.ImprintTool;
import com.umeng.analytics.c.UMEnvelopeData;
import com.umeng.analytics.c.UMengItCache;
import com.umeng.analytics.database.DBDataTool;
import com.umeng.analytics.f.Imprint;
import com.umeng.tool.SystemUtil;
import com.umeng.tool.*;
import com.umeng.tool.ULog;
import com.umeng.tool.SafeRunnable;
import com.umeng.tool.j;
import com.umeng.tool.j.j_inner;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.f.IdTracking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import a.a.a.UMBeanPacker;

public final class LogPreReport implements Reporter, OptionSetter {
    private CacheTool cacheTool = null;
    private RequestTracker requestTracker = null;
    private com.umeng.analytics.e.b d = null;
    private com.umeng.analytics.e.a e = null;
    private com.umeng.analytics.e.c f = null;
    private LogPreReport.a g = null;
    private ImprintTool.Option option = null;
    private long ts = 0L;
    private static Context context;
    String track_list = null;
    private int memoryCacheMaxSize = 10;
    private JSONArray jsonArray = new JSONArray();
    private final int m = 5000;
    private int n = 0;
    private int o = 0;
    private long lastTime = 0L;
    private static final String q = "thtstart";
    private static final String r = "gkvc";
    private static final String s = "ekvc";
    private final long t = 28800000L;

    public LogPreReport(Context context) {
        LogPreReport.context = context;
        this.requestTracker = new RequestTracker(context);
        this.cacheTool = CacheTool.getInstance(context);
        this.option = ImprintTool.getInstance(context).getOption();
        this.g = new LogPreReport.a();
        this.e = com.umeng.analytics.e.a.a(LogPreReport.context);
        this.d = com.umeng.analytics.e.b.a(LogPreReport.context);
        this.f = com.umeng.analytics.e.c.a(LogPreReport.context, this.requestTracker);
        SharedPreferences var2 = SP_Util.getSp(LogPreReport.context);
        this.lastTime = var2.getLong("thtstart", 0L);
        this.n = var2.getInt("gkvc", 0);
        this.o = var2.getInt("ekvc", 0);
        this.track_list = ImprintTool.getInstance(LogPreReport.context).getOption().getTrack_list(null);
    }

    public void report() {
        if(SystemUtil.isConnectState(context)) {
            this.startReport();
        } else {
            ULog.b("network is unavailable");
        }
    }

    public void report(Object o) {
        boolean hasNotRequest = this.requestTracker.hasNotRequest();
        if(hasNotRequest) {
            this.ts = this.requestTracker.getFirstActiveTime();
        }

        boolean var3 = true;
        if(o instanceof JSONObject) {
            try {
                var3 = false;
                this.b((JSONObject)o);
            } catch (Throwable throwable) {
            }
        }

        if(this.a(var3)) {
            this.startReport();
        }
    }

    private void b(JSONObject jsonObject) {
        try {
            if(2050 == jsonObject.getInt("__t")) {
                if(!this.c(this.n)) {
                    return;
                }

                ++this.n;
            } else if(2049 == jsonObject.getInt("__t")) {
                if(!this.c(this.o)) {
                    return;
                }

                ++this.o;
            }

            if(this.jsonArray.length() > this.memoryCacheMaxSize) {
                DBDataTool.getInstance(context).insertToEt(this.jsonArray);
                this.jsonArray = new JSONArray();
            }

            if(this.lastTime == 0L) {
                this.lastTime = System.currentTimeMillis();
            }

            this.jsonArray.put(jsonObject);
        } catch (Throwable throwable) {
        }

    }

    public void packData() {
        this.envelope(this.packData(new int[0]));
    }

    private void a(int var1) {
        int interval = (int)(System.currentTimeMillis() - this.requestTracker.getLastReq());
        this.envelope(this.packData(new int[]{var1, interval}));
        TaskExecutor.scheduleDelayExecute(new SafeRunnable() {
            public void safeRun() {
                LogPreReport.this.report();
            }
        }, (long)var1);
    }

    private void envelope(JSONObject jsonObject) {
        try {
            if(jsonObject == null) {
                return;
            }

            UMengItCache uMengItCache = UMengItCache.getInstance(context);
            uMengItCache.a();

            try {
                IdTracking idTracking = uMengItCache.getIdTracking();
                byte[] var4 = (new UMBeanPacker()).pack2Bytes(idTracking);
                String trackingID = Base64.encodeToString(var4, 0);
                if(!TextUtils.isEmpty(trackingID)) {
                    JSONObject headerJson = jsonObject.getJSONObject("header");
                    headerJson.put("id_tracking", trackingID);
                    jsonObject.put("header", headerJson);
                }
            } catch (Exception e) {
            }

            byte[] bytes = String.valueOf(jsonObject).getBytes();
            if(bytes == null) {
                return;
            }

            if(StringTool.isTooLong(context, bytes)) {
                return;
            }

            UMEnvelopeData envelopeData;
            if(this.e()) {
                envelopeData = UMEnvelopeData.createCodex(context, AnalyticsConfig.getAppkey(context), bytes);
            } else {
                envelopeData = UMEnvelopeData.create(context, AnalyticsConfig.getAppkey(context), bytes);
            }

            byte[] data = envelopeData.envelope();
            CacheTool cacheTool = CacheTool.getInstance(context);
            cacheTool.clearData();
            cacheTool.saveToCache(data);
            uMengItCache.d();
        } catch (Exception e) {
        }

    }

    protected JSONObject packData(int... var1) {
        JSONObject jsonObject;

        try {
            if(TextUtils.isEmpty(AnalyticsConfig.getAppkey(context))) {
                ULog.e("Appkey is missing ,Please check AndroidManifest.xml");
                return null;
            } else {
                this.saveData(context);
                jsonObject = DBDataTool.getInstance(context).a();
                if(jsonObject == null) {
                    jsonObject = new JSONObject();
                }

                JSONObject bodyJson;
                try {
                    bodyJson = jsonObject.getJSONObject("body");
                } catch (Throwable t) {
                    bodyJson = new JSONObject();
                }

                JSONObject var4 = new JSONObject(bodyJson.toString());
                SharedPreferences sp = SP_Util.getSp(context);
                if(sp != null) {
                    String userlevel = sp.getString("userlevel", "");
                    if(!TextUtils.isEmpty(userlevel)) {
                        bodyJson.put("userlevel", userlevel);
                    }
                }

                JSONObject activate_msgJson;
                if(this.requestTracker.hasNotRequest() && this.ts != 0L) {
                    activate_msgJson = new JSONObject();
                    activate_msgJson.put("ts", this.ts);
                    bodyJson.put("activate_msg", activate_msgJson);
                    var4.put("activate_msg", activate_msgJson);
                }

                activate_msgJson = new JSONObject();
                JSONObject agJson = AggTool.getInstance(context).getAG();
                if(agJson != null && agJson.length() > 0) {
                    activate_msgJson.put("ag", agJson);
                }

                JSONObject ve_metaJson = AggTool.getInstance(context).getVe_mate();
                if(ve_metaJson != null && ve_metaJson.length() > 0) {
                    activate_msgJson.put("ve_meta", ve_metaJson);
                }

                if(activate_msgJson.length() > 0) {
                    bodyJson.put("cc", activate_msgJson);
                    var4.put("cc", activate_msgJson);
                }

                String[] au = AU.getAU(context);
                JSONObject active_userJson;
                if(au != null && !TextUtils.isEmpty(au[0]) && !TextUtils.isEmpty(au[1])) {
                    active_userJson = new JSONObject();
                    active_userJson.put("provider", au[0]);
                    active_userJson.put("puid", au[1]);
                    if(active_userJson.length() > 0) {
                        bodyJson.put("active_user", active_userJson);
                        var4.put("active_user", active_userJson);
                    }
                }

                if(com.umeng.analytics.e.a.a(context).a()) {
                    this.startReport(bodyJson);
                }

                this.d.a(bodyJson, context);
                if(var1 != null && var1.length == 2) {
                    active_userJson = new JSONObject();
                    JSONObject latent = new JSONObject();
                    latent.put("interval", var1[0] / 1000);
                    latent.put("latency", var1[1]);
                    active_userJson.put("latent", latent);
                    bodyJson.put("control_policy", active_userJson);
                }

                if(bodyJson.length() > 0) {
                    jsonObject.put("body", bodyJson);
                } else {
                    try {
                        jsonObject.remove("body");
                    } catch (Throwable var24) {
                        ;
                    }
                }

                active_userJson = new JSONObject();
                active_userJson.put("appkey", AnalyticsConfig.getAppkey(context));
                active_userJson.put("channel", AnalyticsConfig.getChannel(context));
                String var28 = EncodeUtil.getMD5_2(AnalyticsConfig.getSecretKey(context));
                if(!TextUtils.isEmpty(var28)) {
                    active_userJson.put("secret", var28);
                }

                active_userJson.put("display_name", SystemUtil.loadLabel(context));
                active_userJson.put("package_name", SystemUtil.getPackageName(context));
                active_userJson.put("app_signature", SystemUtil.getAppSignatures(context));

                try {
                    if(sp == null) {
                        sp = SP_Util.getSp(context);
                    }

                    if(sp != null) {
                        String var12 = sp.getString("vers_name", "");
                        if(!TextUtils.isEmpty(var12)) {
                            active_userJson.put("app_version", var12);
                            active_userJson.put("version_code", sp.getInt("vers_code", 0));
                        } else {
                            active_userJson.put("app_version", SystemUtil.getVersionName(context));
                            active_userJson.put("version_code", Integer.parseInt(SystemUtil.getVersionCode(context)));
                        }
                    }
                } catch (Throwable var23) {
                    active_userJson.put("app_version", SystemUtil.getVersionName(context));
                    active_userJson.put("version_code", Integer.parseInt(SystemUtil.getVersionCode(context)));
                }

                if(AnalyticsConfig.mWrapperType != null && AnalyticsConfig.mWrapperVersion != null) {
                    active_userJson.put("wrapper_type", AnalyticsConfig.mWrapperType);
                    active_userJson.put("wrapper_version", AnalyticsConfig.mWrapperVersion);
                }

                active_userJson.put("sdk_type", "Android");
                active_userJson.put("sdk_version", AnalyticsConfig.getSDKVersion(context));
                active_userJson.put("vertical_type", AnalyticsConfig.getVerticalType(context));
                active_userJson.put("idmd5", SystemUtil.get5DeviceIdInMD5(context));
                active_userJson.put("cpu", SystemUtil.getCPUInfo());
                active_userJson.put("os", "Android");
                active_userJson.put("os_version", VERSION.RELEASE);
                int[] var29 = SystemUtil.getNoncompatScreenSize(context);
                if(var29 != null) {
                    active_userJson.put("resolution", var29[1] + "*" + var29[0]);
                }

                active_userJson.put("mc", SystemUtil.getMacAddress(context));
                active_userJson.put("device_id", SystemUtil.getDeviceId(context));
                active_userJson.put("device_model", Build.MODEL);
                active_userJson.put("device_board", Build.BOARD);
                active_userJson.put("device_brand", Build.BRAND);
                active_userJson.put("device_manutime", Build.TIME);
                active_userJson.put("device_manufacturer", Build.MANUFACTURER);
                active_userJson.put("device_manuid", Build.ID);
                active_userJson.put("device_name", Build.DEVICE);
                String var13 = SystemUtil.getSystemName(context);
                if(!TextUtils.isEmpty(var13)) {
                    active_userJson.put("sub_os_name", var13);
                }

                String var14 = SystemUtil.getOsVersion(context);
                if(!TextUtils.isEmpty(var14)) {
                    active_userJson.put("sub_os_version", var14);
                }

                String[] var15 = SystemUtil.getCurrentNetwork(context);
                if("Wi-Fi".equals(var15[0])) {
                    active_userJson.put("access", "wifi");
                } else if("2G/3G".equals(var15[0])) {
                    active_userJson.put("access", "2G/3G");
                } else {
                    active_userJson.put("access", "unknow");
                }

                if(!"".equals(var15[1])) {
                    active_userJson.put("access_subtype", var15[1]);
                }

                String mobileCode = SystemUtil.getMobileCode(context);
                if(!TextUtils.isEmpty(mobileCode)) {
                    active_userJson.put("mccmnc", mobileCode);
                } else {
                    active_userJson.put("mccmnc", "");
                }

                String[] var17 = SystemUtil.getLocalInfo(context);
                active_userJson.put("country", var17[0]);
                active_userJson.put("language", var17[1]);
                active_userJson.put("timezone", SystemUtil.getTimeZone(context));
                active_userJson.put("carrier", SystemUtil.getNetworkOperatorName(context));

                try {
                    active_userJson.put("successful_requests", sp.getInt("successful_request", 0));
                    active_userJson.put("failed_requests", sp.getInt("failed_requests", 0));
                    active_userJson.put("req_time", sp.getInt("last_request_spent_ms", 0));
                } catch (Exception e) {
                }

                try {
                    Imprint imprint = ImprintTool.getInstance(context).getImprint();
                    if(imprint != null) {
                        byte[] imprintData = (new UMBeanPacker()).pack2Bytes(imprint);
                        active_userJson.put("imprint", Base64.encodeToString(imprintData, 0));
                    }
                } catch (Exception e) {
                }

                jsonObject.put("header", active_userJson);
                var4.put("sdk_version", active_userJson.getString("sdk_version")).put("device_id", active_userJson.getString("device_id")).put("device_model", active_userJson.getString("device_model")).put("version", active_userJson.getString("version_code")).put("appkey", active_userJson.getString("appkey")).put("channel", active_userJson.getString("channel"));
                if(!this.a(active_userJson)) {
                    jsonObject = null;
                }

                if(ULog.isLogOn && var4.length() > 0) {
                    ULog.b(String.valueOf(var4));
                }

                try {
                    if(sp != null) {
                        Editor editor = sp.edit();
                        editor.remove("vers_name");
                        editor.remove("vers_code");
                        editor.remove("vers_date");
                        editor.remove("vers_pre_version");
                        editor.commit();
                    }
                } catch (Throwable throwable) {
                }

                return jsonObject;
            }
        } catch (Throwable var26) {
            CacheTool.getInstance(context).clearData();
            return null;
        }
    }

    private void startReport(JSONObject var1) throws JSONException {
        JSONObject var2 = new JSONObject();
        var2.put(com.umeng.analytics.e.a.a(context).f(), com.umeng.analytics.e.a.a(context).e());
        var1.put("group_info", var2);
    }

    private String[] a(Editor var1, SharedPreferences var2, String var3, String var4) {
        var3 = var2.getString("pre_version", "");
        var4 = var2.getString("pre_date", "");
        String var5 = var2.getString("cur_version", "");
        String var6 = SystemUtil.getVersionName(context);
        if(!var5.equals(var6)) {
            var3 = var5;
            var4 = (new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())).format(new Date(System.currentTimeMillis()));
            var1.putString("pre_version", var5);
            var1.putString("pre_date", var4);
            var1.putString("cur_version", var6);
            var1.commit();
        }

        return new String[]{var3, var4};
    }

    private void a(JSONObject var1, String var2, String var3) throws JSONException {
        if(TextUtils.isEmpty(var2)) {
            var2 = "0";
        }

        var1.put("$pr_ve", var2);
        if(TextUtils.isEmpty(var3)) {
            var3 = (new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())).format(new Date(System.currentTimeMillis()));
        }

        var1.put("$ud_da", var3);
    }

    public boolean a(JSONObject jsonObject) {
        return !TextUtils.isEmpty("device_id") && !TextUtils.isEmpty("mc") && !TextUtils.isEmpty("resolution") && !TextUtils.isEmpty("appkey") && !TextUtils.isEmpty("channel") && !TextUtils.isEmpty("app_signature") && !TextUtils.isEmpty("package_name") && !TextUtils.isEmpty("app_version");
    }

    private boolean a(boolean var1) {
        if(!SystemUtil.isConnectState(context)) {
            ULog.e("network is unavailable");
            return false;
        } else {
            return this.requestTracker.hasNotRequest()?true:this.g.b(var1).a(var1);
        }
    }

    private void startReport() {
        try {
            if(this.cacheTool.hasCache()) {
                LogReportor logReportor = new LogReportor(context, this.requestTracker);
                logReportor.setOptionSetter(this);
                if(this.d.d()) {
                    logReportor.b(true);
                }

                logReportor.a();
            } else {
                JSONObject var4 = this.packData(new int[0]);
                if(var4.length() <= 0) {
                    return;
                }

                LogReportor var2 = new LogReportor(context, this.requestTracker);
                var2.setOptionSetter(this);
                if(this.d.d()) {
                    var2.b(true);
                }

                var2.setJsonLog(var4);
                var2.setCodex(this.e());
                var2.a();
            }
        } catch (Throwable throwable) {
            if(throwable != null) {
                throwable.printStackTrace();
            }
        }

    }

    private boolean e() {
        switch(this.option.c(-1)) {
            case -1:
                return AnalyticsConfig.sEncrypt;
            case 0:
                return false;
            case 1:
                return true;
            default:
                return false;
        }
    }

    private void b(int var1) {
        this.a(var1);
    }

    public void setOption(ImprintTool.Option option) {
        this.e.setOption(option);
        this.d.setOption(option);
        this.f.setOption(option);
        this.g.setOption(option);
        this.track_list = ImprintTool.getInstance(context).getOption().getTrack_list(null);
    }

    private boolean c(int var1) {
        if(this.lastTime != 0L) {
            long currentTimeMillis = System.currentTimeMillis();
            if(currentTimeMillis - this.lastTime > 28800000L) {//8 hours
                this.f();
                return true;
            } else {
                return var1 <= 5000;
            }
        } else {
            return true;
        }
    }

    public void saveData(Context context) {
        try {
            if(LogPreReport.context == null) {
                LogPreReport.context = context;
            }

            if(this.jsonArray.length() > 0) {
                DBDataTool.getInstance(LogPreReport.context).insertToEt(this.jsonArray);
                this.jsonArray = new JSONArray();
            }

            SharedPreferences sp = SP_Util.getSp(LogPreReport.context);
            sp
            .edit()
            .putLong("thtstart", this.lastTime)
            .putInt("gkvc", this.n)
            .putInt("ekvc", this.o)
            .apply();
        } catch (Throwable t) {
        }
    }

    private void f() {
        this.n = 0;
        this.o = 0;
        this.lastTime = System.currentTimeMillis();
    }

    public class a {
        private j.h b;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private int f = -1;

        public a() {
            int[] var2 = LogPreReport.this.option.a(-1, -1);
            this.c = var2[0];
            this.d = var2[1];
        }

        protected void a(boolean var1) {
            boolean var2;
            if(LogPreReport.this.d.d()) {
                var2 = this.b instanceof j.b && this.b.a();
                this.b = (j.h)(var2?this.b:new j.b(LogPreReport.this.requestTracker, LogPreReport.this.d));
            } else {
                var2 = this.b instanceof j.c && this.b.a();
                if(!var2) {
                    if(var1 && LogPreReport.this.f.a()) {
                        this.b = new j.c((int)LogPreReport.this.f.b());
                        LogPreReport.this.b((int)LogPreReport.this.f.b());
                    } else if(ULog.isLogOn && LogPreReport.this.option.b()) {
                        this.b = new j.a(LogPreReport.this.requestTracker);
                    } else {
                        int var3;
                        if(LogPreReport.this.e.a() && "RPT".equals(LogPreReport.this.e.f())) {
                            var3 = 0;
                            if(LogPreReport.this.e.b() == 6) {
                                if(LogPreReport.this.option.a()) {
                                    var3 = LogPreReport.this.option.d(90000);
                                } else if(this.d > 0) {
                                    var3 = this.d;
                                } else {
                                    var3 = this.f;
                                }
                            }

                            this.b = this.b(LogPreReport.this.e.b(), var3);
                        } else {
                            var3 = this.e;
                            int var4 = this.f;
                            if(this.c != -1) {
                                var3 = this.c;
                                var4 = this.d;
                            }

                            this.b = this.b(var3, var4);
                        }
                    }
                }
            }

        }

        public j.h b(boolean var1) {
            this.a(var1);
            return this.b;
        }

        private j.h b(int var1, int var2) {
            Object var3 = null;
            switch(var1) {
                case 0:
                    var3 = this.b instanceof j.g?this.b:new j.g();
                    break;
                case 1:
                    var3 = this.b instanceof j.d?this.b:new j.d();
                    break;
                case 2:
                case 3:
                case 7:
                default:
                    var3 = this.b instanceof j.d?this.b:new j.d();
                    break;
                case 4:
                    var3 = this.b instanceof j.f?this.b:new j.f(LogPreReport.this.requestTracker);
                    break;
                case 5:
                    var3 = this.b instanceof j.i?this.b:new j.i(LogPreReport.context);
                    break;
                case 6:
                    if(this.b instanceof j.e) {
                        var3 = this.b;
                        ((j.e)var3).a((long)var2);
                    } else {
                        var3 = new j.e(LogPreReport.this.requestTracker, (long)var2);
                    }
                    break;
                case 8:
                    var3 = this.b instanceof j_inner?this.b:new j_inner(LogPreReport.this.requestTracker);
            }

            return (j.h)var3;
        }

        public void a(int var1, int var2) {
            this.e = var1;
            this.f = var2;
        }

        public void setOption(ImprintTool.Option option) {
            int[] var2 = option.a(-1, -1);
            this.c = var2[0];
            this.d = var2[1];
        }
    }
}
