//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.content.SharedPreferences;

import com.umeng.tool.EncodeUtil;
import com.umeng.tool.StringTool;
import com.umeng.tool.SystemUtil;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.d.SP_Util;
import com.umeng.analytics.g.a;
import com.umeng.tool.ZipTool;

import java.io.File;
import org.json.JSONObject;

public class c {
    private final byte[] a = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
    private final int b = 1;
    private final int c = 0;
    private String d = "1.0";
    private String e = null;
    private byte[] f = null;
    private byte[] g = null;
    private byte[] h = null;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private byte[] l = null;
    private byte[] m = null;
    private boolean n = false;

    private c(byte[] var1, String var2, byte[] var3) throws Exception {
        if(var1 != null && var1.length != 0) {
            this.e = var2;
            this.k = var1.length;
            this.l = ZipTool.deflate(var1);
            this.j = (int)(System.currentTimeMillis() / 1000L);
            this.m = var3;
        } else {
            throw new Exception("entity is null or empty");
        }
    }

    public static String a(Context var0) {
        SharedPreferences var1 = SP_Util.getSp(var0);
        return var1 == null?null:var1.getString("signature", (String)null);
    }

    public void a(String var1) {
        this.f = StringTool.a(var1);
    }

    public String a() {
        return StringTool.a(this.f);
    }

    public void a(int var1) {
        this.i = var1;
    }

    public void a(boolean var1) {
        this.n = var1;
    }

    public static c a(Context var0, String var1, byte[] var2) {
        try {
            String var3 = SystemUtil.getMacAddress(var0);
            String var4 = SystemUtil.getDeviceId(var0);
            SharedPreferences var5 = SP_Util.getSp(var0);
            String var6 = var5.getString("signature", (String)null);
            int var7 = var5.getInt("serial", 1);
            c var8 = new c(var2, var1, (var4 + var3).getBytes());
            var8.a(var6);
            var8.a(var7);
            var8.b();
            var5.edit().putInt("serial", var7 + 1).putString("signature", var8.a()).commit();
            var8.b(var0);
            return var8;
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public static c b(Context var0, String var1, byte[] var2) {
        try {
            String var3 = SystemUtil.getMacAddress(var0);
            String var4 = SystemUtil.getDeviceId(var0);
            SharedPreferences var5 = SP_Util.getSp(var0);
            String var6 = var5.getString("signature", (String)null);
            int var7 = var5.getInt("serial", 1);
            c var8 = new c(var2, var1, (var4 + var3).getBytes());
            var8.a(true);
            var8.a(var6);
            var8.a(var7);
            var8.b();
            var5.edit().putInt("serial", var7 + 1).putString("signature", var8.a()).commit();
            var8.b(var0);
            return var8;
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public void b() {
        if(this.f == null) {
            this.f = this.d();
        }

        if(this.n) {
            byte[] var1 = new byte[16];

            try {
                System.arraycopy(this.f, 1, var1, 0, 16);
                this.l = StringTool.a(this.l, var1);
            } catch (Exception var3) {
                ;
            }
        }

        this.g = this.a(this.f, this.j);
        this.h = this.e();
    }

    private byte[] a(byte[] var1, int var2) {
        byte[] var3 = StringTool.b(this.m);
        byte[] var4 = StringTool.b(this.l);
        int var5 = var3.length;
        byte[] var6 = new byte[var5 * 2];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7 * 2] = var4[var7];
            var6[var7 * 2 + 1] = var3[var7];
        }

        byte[] var10 = var1;

        for(int var8 = 0; var8 < 2; ++var8) {
            var6[var8] = var10[var8];
            var6[var6.length - var8 - 1] = var10[var10.length - var8 - 1];
        }

        byte[] var11 = new byte[]{(byte)(var2 & 255), (byte)(var2 >> 8 & 255), (byte)(var2 >> 16 & 255), (byte)(var2 >>> 24)};

        for(int var9 = 0; var9 < var6.length; ++var9) {
            var6[var9] ^= var11[var9 % 4];
        }

        return var6;
    }

    private byte[] d() {
        return this.a(this.a, (int)(System.currentTimeMillis() / 1000L));
    }

    private byte[] e() {
        StringBuilder var1 = new StringBuilder();
        var1.append(StringTool.a(this.f));
        var1.append(this.i);
        var1.append(this.j);
        var1.append(this.k);
        var1.append(StringTool.a(this.g));
        return StringTool.b(var1.toString().getBytes());
    }

    public byte[] c() {
        a var1 = new a();
        var1.a(this.d);
        var1.b(this.e);
        var1.c(StringTool.a(this.f));
        var1.a(this.i);
        var1.c(this.j);
        var1.d(this.k);
        var1.a(this.l);
        var1.e(this.n?1:0);
        var1.d(StringTool.a(this.g));
        var1.e(StringTool.a(this.h));

        try {
            return (new a.a.a.m()).a(var1);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public void b(Context var1) {
        String var2 = this.e;
        String var3 = com.umeng.analytics.c.h.getInstance(var1).getOption().g((String)null);
        String var4 = StringTool.a(this.f);
        byte[] var5 = new byte[16];
        System.arraycopy(this.f, 2, var5, 0, 16);
        String var6 = StringTool.a(StringTool.b(var5));

        JSONObject var7;
        try {
            var7 = new JSONObject();
            var7.put("appkey", var2);
            if(var3 != null) {
                var7.put("umid", var3);
            }

            var7.put("signature", var4);
            var7.put("checksum", var6);
            File var8 = new File(var1.getFilesDir(), ".umeng");
            if(!var8.exists()) {
                var8.mkdir();
            }

            EncodeUtil.writeToFile(new File(var8, "exchangeIdentity.json"), var7.toString());
        } catch (Throwable var10) {
            var10.printStackTrace();
        }

        try {
            var7 = new JSONObject();
            var7.put("appkey", var2);
            var7.put("channel", AnalyticsConfig.getChannel(var1));
            if(var3 != null) {
                var7.put("umid", EncodeUtil.getMD5(var3));
            }

            EncodeUtil.writeToFile(new File(var1.getFilesDir(), "exid.dat"), var7.toString());
        } catch (Throwable var9) {
            var9.printStackTrace();
        }

    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append(String.format("version : %getUMengChannel\n", new Object[]{this.d}));
        var1.append(String.format("address : %getUMengChannel\n", new Object[]{this.e}));
        var1.append(String.format("signature : %getUMengChannel\n", new Object[]{StringTool.a(this.f)}));
        var1.append(String.format("serial : %getUMengChannel\n", new Object[]{Integer.valueOf(this.i)}));
        var1.append(String.format("timestamp : %loadChannel\n", new Object[]{Integer.valueOf(this.j)}));
        var1.append(String.format("length : %loadChannel\n", new Object[]{Integer.valueOf(this.k)}));
        var1.append(String.format("guid : %getUMengChannel\n", new Object[]{StringTool.a(this.g)}));
        var1.append(String.format("checksum : %getUMengChannel ", new Object[]{StringTool.a(this.h)}));
        var1.append(String.format("codex : %loadChannel", new Object[]{Integer.valueOf(this.n?1:0)}));
        return var1.toString();
    }
}
