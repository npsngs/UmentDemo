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
import com.umeng.analytics.database.DBDataTool;
import com.umeng.tool.SystemUtil;
import com.umeng.tool.*;
import com.umeng.tool.ULog;
import com.umeng.tool.SafeRunnable;
import com.umeng.tool.j;
import com.umeng.tool.j.j_inner;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.f.d;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class LogPreReport implements g, com.umeng.analytics.d.l {
    private CacheTool cacheTool = null;
    private RequestTracker requestTracker = null;
    private com.umeng.analytics.e.b d = null;
    private com.umeng.analytics.e.a e = null;
    private com.umeng.analytics.e.c f = null;
    private LogPreReport.a g = null;
    private com.umeng.analytics.c.h.Option option = null;
    private long i = 0L;
    private static Context context;
    String a = null;
    private int k = 10;
    private JSONArray jsonArray = new JSONArray();
    private final int m = 5000;
    private int n = 0;
    private int o = 0;
    private long p = 0L;
    private static final String q = "thtstart";
    private static final String r = "gkvc";
    private static final String s = "ekvc";
    private final long t = 28800000L;

    public LogPreReport(Context context) {
        LogPreReport.context = context;
        this.requestTracker = new RequestTracker(context);
        this.cacheTool = CacheTool.getInstance(context);
        this.option = com.umeng.analytics.c.h.getInstance(context).getOption();
        this.g = new LogPreReport.a();
        this.e = com.umeng.analytics.e.a.a(LogPreReport.context);
        this.d = com.umeng.analytics.e.b.a(LogPreReport.context);
        this.f = com.umeng.analytics.e.c.a(LogPreReport.context, this.requestTracker);
        SharedPreferences var2 = SP_Util.getSp(LogPreReport.context);
        this.p = var2.getLong("thtstart", 0L);
        this.n = var2.getInt("gkvc", 0);
        this.o = var2.getInt("ekvc", 0);
        this.a = com.umeng.analytics.c.h.getInstance(LogPreReport.context).getOption().b((String)null);
    }

    public void a() {
        if(SystemUtil.isConnectState(context)) {
            this.startReport();
        } else {
            ULog.b("network is unavailable");
        }

    }

    public void a(Object o) {
        boolean var2 = this.requestTracker.isNoRequest();
        if(var2) {
            this.i = this.requestTracker.getFirstActiveTime();
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

    private void b(JSONObject var1) {
        try {
            if(2050 == var1.getInt("__t")) {
                if(!this.c(this.n)) {
                    return;
                }

                ++this.n;
            } else if(2049 == var1.getInt("__t")) {
                if(!this.c(this.o)) {
                    return;
                }

                ++this.o;
            }

            if(this.jsonArray.length() > this.k) {
                DBDataTool.getInstance(context).insertToEt(this.jsonArray);
                this.jsonArray = new JSONArray();
            }

            if(this.p == 0L) {
                this.p = System.currentTimeMillis();
            }

            this.jsonArray.put(var1);
        } catch (Throwable var3) {
            ;
        }

    }

    public void b() {
        this.c(this.a(new int[0]));
    }

    private void a(int var1) {
        int var2 = (int)(System.currentTimeMillis() - this.requestTracker.getLastReq());
        this.c(this.a(new int[]{var1, var2}));
        TaskExecutor.scheduleDelayExecute(new SafeRunnable() {
            public void safeRun() {
                LogPreReport.this.a();
            }
        }, (long)var1);
    }

    private void c(JSONObject jsonObject) {
        try {
            if(jsonObject == null) {
                return;
            }

            com.umeng.analytics.c.f var2 = com.umeng.analytics.c.f.a(context);
            var2.a();

            try {
                d var3 = var2.b();
                byte[] var4 = (new a.a.a.m()).a(var3);
                String trackingID = Base64.encodeToString(var4, 0);
                if(!TextUtils.isEmpty(trackingID)) {
                    JSONObject headerJson = jsonObject.getJSONObject("header");
                    headerJson.put("id_tracking", trackingID);
                    jsonObject.put("header", headerJson);
                }
            } catch (Exception e) {
            }

            byte[] var9 = String.valueOf(jsonObject).getBytes();
            if(var9 == null) {
                return;
            }

            if(StringTool.a(context, var9)) {
                return;
            }

            com.umeng.analytics.c.c var10 = null;
            if(this.e()) {
                var10 = com.umeng.analytics.c.c.b(context, AnalyticsConfig.getAppkey(context), var9);
            } else {
                var10 = com.umeng.analytics.c.c.a(context, AnalyticsConfig.getAppkey(context), var9);
            }

            byte[] var11 = var10.c();
            CacheTool var12 = CacheTool.getInstance(context);
            var12.g();
            var12.saveToCache(var11);
            var2.d();
        } catch (Exception var8) {
            ;
        }

    }

    protected JSONObject a(int... var1) {
        JSONObject var2 = null;

        try {
            if(TextUtils.isEmpty(AnalyticsConfig.getAppkey(context))) {
                ULog.e("Appkey is missing ,Please check AndroidManifest.xml");
                return null;
            } else {
                this.saveData(context);
                var2 = DBDataTool.getInstance(context).a();
                if(var2 == null) {
                    var2 = new JSONObject();
                }

                JSONObject var3 = null;

                try {
                    var3 = var2.getJSONObject("body");
                } catch (Throwable var25) {
                    var3 = new JSONObject();
                }

                JSONObject var4 = new JSONObject(var3.toString());
                SharedPreferences var5 = SP_Util.getSp(context);
                if(var5 != null) {
                    String var6 = var5.getString("userlevel", "");
                    if(!TextUtils.isEmpty(var6)) {
                        var3.put("userlevel", var6);
                    }
                }

                JSONObject var27;
                if(this.requestTracker.isNoRequest() && this.i != 0L) {
                    var27 = new JSONObject();
                    var27.put("ts", this.i);
                    var3.put("activate_msg", var27);
                    var4.put("activate_msg", var27);
                }

                var27 = new JSONObject();
                JSONObject var7 = AggTool.getInstance(context).b();
                if(var7 != null && var7.length() > 0) {
                    var27.put("ag", var7);
                }

                JSONObject var8 = AggTool.getInstance(context).c();
                if(var8 != null && var8.length() > 0) {
                    var27.put("ve_meta", var8);
                }

                if(var27.length() > 0) {
                    var3.put("cc", var27);
                    var4.put("cc", var27);
                }

                String[] var9 = AU.getAU(context);
                JSONObject var10;
                if(var9 != null && !TextUtils.isEmpty(var9[0]) && !TextUtils.isEmpty(var9[1])) {
                    var10 = new JSONObject();
                    var10.put("provider", var9[0]);
                    var10.put("puid", var9[1]);
                    if(var10.length() > 0) {
                        var3.put("active_user", var10);
                        var4.put("active_user", var10);
                    }
                }

                if(com.umeng.analytics.e.a.a(context).a()) {
                    this.startReport(var3);
                }

                this.d.a(var3, context);
                if(var1 != null && var1.length == 2) {
                    var10 = new JSONObject();
                    JSONObject var11 = new JSONObject();
                    var11.put("interval", var1[0] / 1000);
                    var11.put("latency", var1[1]);
                    var10.put("latent", var11);
                    var3.put("control_policy", var10);
                }

                if(var3.length() > 0) {
                    var2.put("body", var3);
                } else {
                    try {
                        var2.remove("body");
                    } catch (Throwable var24) {
                        ;
                    }
                }

                var10 = new JSONObject();
                var10.put("appkey", AnalyticsConfig.getAppkey(context));
                var10.put("channel", AnalyticsConfig.getChannel(context));
                String var28 = EncodeUtil.getMD5_2(AnalyticsConfig.getSecretKey(context));
                if(!TextUtils.isEmpty(var28)) {
                    var10.put("secret", var28);
                }

                var10.put("display_name", SystemUtil.loadLabel(context));
                var10.put("package_name", SystemUtil.getPackageName(context));
                var10.put("app_signature", SystemUtil.getAppSignatures(context));

                try {
                    if(var5 == null) {
                        var5 = SP_Util.getSp(context);
                    }

                    if(var5 != null) {
                        String var12 = var5.getString("vers_name", "");
                        if(!TextUtils.isEmpty(var12)) {
                            var10.put("app_version", var12);
                            var10.put("version_code", var5.getInt("vers_code", 0));
                        } else {
                            var10.put("app_version", SystemUtil.getVersionName(context));
                            var10.put("version_code", Integer.parseInt(SystemUtil.getVersionCode(context)));
                        }
                    }
                } catch (Throwable var23) {
                    var10.put("app_version", SystemUtil.getVersionName(context));
                    var10.put("version_code", Integer.parseInt(SystemUtil.getVersionCode(context)));
                }

                if(AnalyticsConfig.mWrapperType != null && AnalyticsConfig.mWrapperVersion != null) {
                    var10.put("wrapper_type", AnalyticsConfig.mWrapperType);
                    var10.put("wrapper_version", AnalyticsConfig.mWrapperVersion);
                }

                var10.put("sdk_type", "Android");
                var10.put("sdk_version", AnalyticsConfig.getSDKVersion(context));
                var10.put("vertical_type", AnalyticsConfig.getVerticalType(context));
                var10.put("idmd5", SystemUtil.get5DeviceIdInMD5(context));
                var10.put("cpu", SystemUtil.getCPUInfo());
                var10.put("os", "Android");
                var10.put("os_version", VERSION.RELEASE);
                int[] var29 = SystemUtil.getNoncompatScreenSize(context);
                if(var29 != null) {
                    var10.put("resolution", var29[1] + "*" + var29[0]);
                }

                var10.put("mc", SystemUtil.getMacAddress(context));
                var10.put("device_id", SystemUtil.getDeviceId(context));
                var10.put("device_model", Build.MODEL);
                var10.put("device_board", Build.BOARD);
                var10.put("device_brand", Build.BRAND);
                var10.put("device_manutime", Build.TIME);
                var10.put("device_manufacturer", Build.MANUFACTURER);
                var10.put("device_manuid", Build.ID);
                var10.put("device_name", Build.DEVICE);
                String var13 = SystemUtil.getSystemName(context);
                if(!TextUtils.isEmpty(var13)) {
                    var10.put("sub_os_name", var13);
                }

                String var14 = SystemUtil.getOsVersion(context);
                if(!TextUtils.isEmpty(var14)) {
                    var10.put("sub_os_version", var14);
                }

                String[] var15 = SystemUtil.getCurrentNetwork(context);
                if("Wi-Fi".equals(var15[0])) {
                    var10.put("access", "wifi");
                } else if("2G/3G".equals(var15[0])) {
                    var10.put("access", "2G/3G");
                } else {
                    var10.put("access", "unknow");
                }

                if(!"".equals(var15[1])) {
                    var10.put("access_subtype", var15[1]);
                }

                String var16 = SystemUtil.getProperties(context);
                if(!TextUtils.isEmpty(var16)) {
                    var10.put("mccmnc", var16);
                } else {
                    var10.put("mccmnc", "");
                }

                String[] var17 = SystemUtil.getLocalInfo(context);
                var10.put("country", var17[0]);
                var10.put("language", var17[1]);
                var10.put("timezone", SystemUtil.getTimeZone(context));
                var10.put("carrier", SystemUtil.getNetworkOperatorName(context));

                try {
                    var10.put("successful_requests", var5.getInt("successful_request", 0));
                    var10.put("failed_requests", var5.getInt("failed_requests", 0));
                    var10.put("req_time", var5.getInt("last_request_spent_ms", 0));
                } catch (Exception var22) {
                    ;
                }

                try {
                    com.umeng.analytics.f.e var18 = com.umeng.analytics.c.h.getInstance(context).a();
                    if(var18 != null) {
                        byte[] var19 = (new a.a.a.m()).a(var18);
                        var10.put("imprint", Base64.encodeToString(var19, 0));
                    }
                } catch (Exception var21) {
                    ;
                }

                var2.put("header", var10);
                var4.put("sdk_version", var10.getString("sdk_version")).put("device_id", var10.getString("device_id")).put("device_model", var10.getString("device_model")).put("version", var10.getString("version_code")).put("appkey", var10.getString("appkey")).put("channel", var10.getString("channel"));
                if(!this.a(var10)) {
                    var2 = null;
                }

                if(ULog.isLogOn && var4.length() > 0) {
                    ULog.b(String.valueOf(var4));
                }

                try {
                    if(var5 != null) {
                        Editor var30 = var5.edit();
                        var30.remove("vers_name");
                        var30.remove("vers_code");
                        var30.remove("vers_date");
                        var30.remove("vers_pre_version");
                        var30.commit();
                    }
                } catch (Throwable var20) {
                    ;
                }

                return var2;
            }
        } catch (Throwable var26) {
            CacheTool.getInstance(context).g();
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
            return this.requestTracker.isNoRequest()?true:this.g.b(var1).a(var1);
        }
    }

    private void startReport() {
        try {
            if(this.cacheTool.hasCache()) {
                LogReportor var1 = new LogReportor(context, this.requestTracker);
                var1.a(this);
                if(this.d.d()) {
                    var1.b(true);
                }

                var1.a();
            } else {
                JSONObject var4 = this.a(new int[0]);
                if(var4.length() <= 0) {
                    return;
                }

                LogReportor var2 = new LogReportor(context, this.requestTracker);
                var2.a(this);
                if(this.d.d()) {
                    var2.b(true);
                }

                var2.a(var4);
                var2.a(this.e());
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

    public void a(com.umeng.analytics.c.h.Option var1) {
        this.e.a(var1);
        this.d.a(var1);
        this.f.a(var1);
        this.g.a(var1);
        this.a = com.umeng.analytics.c.h.getInstance(context).getOption().b((String)null);
    }

    private boolean c(int var1) {
        if(this.p != 0L) {
            long var2 = System.currentTimeMillis();
            if(var2 - this.p > 28800000L) {
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
            .putLong("thtstart", this.p)
            .putInt("gkvc", this.n)
            .putInt("ekvc", this.o)
            .apply();
        } catch (Throwable t) {
        }
    }

    private void f() {
        this.n = 0;
        this.o = 0;
        this.p = System.currentTimeMillis();
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

        public void a(com.umeng.analytics.c.h.Option var1) {
            int[] var2 = var1.a(-1, -1);
            this.c = var2[0];
            this.d = var2[1];
        }
    }
}
