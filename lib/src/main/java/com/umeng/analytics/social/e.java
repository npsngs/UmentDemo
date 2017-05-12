//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.social;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.umeng.tool.SystemUtil;
import com.umeng.tool.ULog;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.social.UMPlatformData.GENDER;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public abstract class e {
    private static Map<String, String> a;

    public e() {
    }

    protected static String[] a(Context var0, String var1, UMPlatformData... var2) throws a {
        if(var2 != null && var2.length != 0) {
            String var3 = AnalyticsConfig.getAppkey(var0);
            if(TextUtils.isEmpty(var3)) {
                throw new a("can`getPackageName get appkey.");
            } else {
                ArrayList var4 = new ArrayList();
                String var5 = "http://log.umsns.com/onShareEvent/api/" + var3 + "/";
                if(a == null || a.isEmpty()) {
                    a = b(var0);
                }

                if(a != null && !a.isEmpty()) {
                    Iterator var6 = a.entrySet().iterator();

                    while(var6.hasNext()) {
                        Entry var7 = (Entry)var6.next();
                        var4.add((String)var7.getKey() + "=" + (String)var7.getValue());
                    }
                }

                var4.add("date=" + String.valueOf(System.currentTimeMillis()));
                var4.add("channel=" + d.d);
                if(!TextUtils.isEmpty(var1)) {
                    var4.add("topic=" + var1);
                }

                var4.addAll(a(var2));
                String var8 = b(var2);
                if(var8 == null) {
                    var8 = "null";
                }

                for(var5 = var5 + "?" + a((List)var4); var5.contains("%2C+"); var5 = var5.replace("%2C+", "&")) {
                    ;
                }

                while(var5.contains("%3D")) {
                    var5 = var5.replace("%3D", "=");
                }

                while(var5.contains("%5B")) {
                    var5 = var5.replace("%5B", "");
                }

                while(var5.contains("%5D")) {
                    var5 = var5.replace("%5D", "");
                }

                ULog.b("url:" + var5 + "\nBody:" + var8);
                String[] var9 = new String[]{var5, var8};
                return var9;
            }
        } else {
            throw new a("platform data is null");
        }
    }

    private static String a(List<String> var0) {
        try {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            var1.write(URLEncoder.encode(var0.toString()).getBytes());
            return var1.toString();
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    private static List<String> a(UMPlatformData... var0) {
        StringBuilder var1 = new StringBuilder();
        StringBuilder var2 = new StringBuilder();
        StringBuilder var3 = new StringBuilder();
        UMPlatformData[] var4 = var0;
        int var5 = var0.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            UMPlatformData var7 = var4[var6];
            var1.append(var7.getMeida().toString());
            var1.append(',');
            var2.append(var7.getUsid());
            var2.append(',');
            var3.append(var7.getWeiboId());
            var3.append(',');
        }

        if(var0.length > 0) {
            var1.deleteCharAt(var1.length() - 1);
            var2.deleteCharAt(var2.length() - 1);
            var3.deleteCharAt(var3.length() - 1);
        }

        ArrayList var8 = new ArrayList();
        var8.add("platform=" + var1.toString());
        var8.add("usid=" + var2.toString());
        if(var3.length() > 0) {
            var8.add("weiboid=" + var3.toString());
        }

        return var8;
    }

    private static String b(UMPlatformData... var0) {
        JSONObject var1 = new JSONObject();
        UMPlatformData[] var2 = var0;
        int var3 = var0.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            UMPlatformData var5 = var2[var4];
            GENDER var6 = var5.getGender();
            String var7 = var5.getName();

            try {
                if(var6 != null || !TextUtils.isEmpty(var7)) {
                    JSONObject var8 = new JSONObject();
                    var8.put("gender", var6 == null?"":String.valueOf(var6.value));
                    var8.put("name", var7 == null?"":String.valueOf(var7));
                    var1.put(var5.getMeida().toString(), var8);
                }
            } catch (Exception var9) {
                throw new a("build body exception", var9);
            }
        }

        return var1.length() == 0?null:var1.toString();
    }

    private static Map<String, String> b(Context var0) throws a {
        HashMap var1 = new HashMap();
        Map var2 = a(var0);
        if(var2 != null && !var2.isEmpty()) {
            StringBuilder var3 = new StringBuilder();
            StringBuilder var4 = new StringBuilder();
            Iterator var5 = var2.entrySet().iterator();

            while(var5.hasNext()) {
                Entry var6 = (Entry)var5.next();
                if(!TextUtils.isEmpty((CharSequence)var6.getValue())) {
                    var4.append((String)var6.getKey()).append(",");
                    var3.append((String)var6.getValue()).append(",");
                }
            }

            if(var3.length() > 0) {
                var3.deleteCharAt(var3.length() - 1);
                var1.put("deviceid", var3.toString());
            }

            if(var4.length() > 0) {
                var4.deleteCharAt(var4.length() - 1);
                var1.put("idtype", var4.toString());
            }

            return var1;
        } else {
            throw new a("can`getPackageName get device id.");
        }
    }

    public static Map<String, String> a(Context var0) {
        HashMap var1 = new HashMap();
        TelephonyManager var2 = (TelephonyManager)var0.getSystemService(Context.TELEPHONY_SERVICE);
        if(var2 == null) {
            ULog.b("No IMEI.");
        }

        String var3 = null;

        try {
            if(SystemUtil.hasPermission(var0, "android.permission.READ_PHONE_STATE")) {
                var3 = var2.getDeviceId();
            }
        } catch (Exception var6) {
            ULog.e(var6);
        }

        String var4 = SystemUtil.getMacAddress(var0);
        String var5 = Secure.getString(var0.getContentResolver(), "android_id");
        if(!TextUtils.isEmpty(var4)) {
            var1.put("mac", var4);
        }

        if(!TextUtils.isEmpty(var3)) {
            var1.put("imei", var3);
        }

        if(!TextUtils.isEmpty(var5)) {
            var1.put("android_id", var5);
        }

        return var1;
    }
}
