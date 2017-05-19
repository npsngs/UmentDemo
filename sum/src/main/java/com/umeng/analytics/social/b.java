//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.social;

import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.tool.ULog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public abstract class b {
    public b() {
    }

    protected static String a(String var0) {
        int var1 = (new Random()).nextInt(1000);
        HttpURLConnection var2 = null;

        Object var4;
        try {
            String var3 = System.getProperty("line.separator");
            if(var0.length() <= 1) {
                ULog.e(var1 + ":  Invalid baseUrl.");
                var4 = null;
                return (String)var4;
            }

            var2 = (HttpURLConnection)(new URL(var0)).openConnection();
            var2.setConnectTimeout(10000);
            var2.setReadTimeout(20000);
            var2.setRequestMethod("GET");
            if(Integer.parseInt(VERSION.SDK) < 8) {
                System.setProperty("http.keepAlive", "false");
            }

            ULog.c(var1 + ": GET_URL: " + var0);
            if(var2.getResponseCode() != 200) {
                ULog.c(var1 + ":  Failed to get message." + var0);
                var4 = null;
                return (String)var4;
            }

            var4 = var2.getInputStream();
            String var5 = var2.getHeaderField("Content-Encoding");
            if(!TextUtils.isEmpty(var5) && var5.equalsIgnoreCase("gzip")) {
                ULog.c(var1 + "  Use GZIPInputStream get data....");
                var4 = new GZIPInputStream((InputStream)var4);
            } else if(!TextUtils.isEmpty(var5) && var5.equalsIgnoreCase("deflate")) {
                ULog.c(var1 + "  Use InflaterInputStream get data....");
                var4 = new InflaterInputStream((InputStream)var4);
            }

            String var6 = a((InputStream)var4);
            ULog.c(var1 + ":  response: " + var3 + var6);
            String var7;
            if(var6 == null) {
                var7 = null;
                return var7;
            }

            var7 = var6;
            return var7;
        } catch (Exception var11) {
            var4 = null;
        } finally {
            if(var2 != null) {
                var2.disconnect();
            }

        }

        return (String)var4;
    }

    private static String a(InputStream var0) {
        BufferedReader var1 = new BufferedReader(new InputStreamReader(var0), 8192);
        StringBuilder var2 = new StringBuilder();
        String var3 = null;

        Object var5;
        try {
            while((var3 = var1.readLine()) != null) {
                var2.append(var3 + "\n");
            }

            return var2.toString();
        } catch (IOException var15) {
            var5 = null;
        } finally {
            try {
                var0.close();
            } catch (IOException var14) {
                return null;
            }
        }

        return (String)var5;
    }

    protected static String a(String var0, String var1) {
        int var2 = (new Random()).nextInt(1000);
        HttpURLConnection var3 = null;

        ArrayList var5;
        try {
            String var4 = System.getProperty("line.separator");
            var3 = (HttpURLConnection)(new URL(var0)).openConnection();
            var3.setConnectTimeout(10000);
            var3.setReadTimeout(20000);
            var3.setDoOutput(true);
            var3.setDoInput(true);
            var3.setUseCaches(false);
            var3.setRequestMethod("POST");
            ULog.c(var2 + ": POST_URL: " + var0);
            if(Integer.parseInt(VERSION.SDK) < 8) {
                System.setProperty("http.keepAlive", "false");
            }

            if(!TextUtils.isEmpty(var1)) {
                ULog.c(var2 + ": POST_BODY: " + var1);
                var5 = new ArrayList();
                var5.add("data=" + var1);
                OutputStream var6 = var3.getOutputStream();
                var6.write(URLEncoder.encode(var5.toString()).getBytes());
                var6.flush();
                var6.close();
            }

            if(var3.getResponseCode() != 200) {
                ULog.e(var2 + ":  Failed to setRequestCallback message." + var0);
                var5 = null;
                return null;
            }

            Object var14 = var3.getInputStream();
            String var15 = var3.getHeaderField("Content-Encoding");
            if(!TextUtils.isEmpty(var15) && var15.equalsIgnoreCase("gzip")) {
                var14 = new InflaterInputStream((InputStream)var14);
            }

            String var7 = a((InputStream)var14);
            ULog.c(var2 + ":  response: " + var4 + var7);
            String var8;
            if(var7 == null) {
                var8 = null;
                return var8;
            }

            var8 = var7;
            return var8;
        } catch (Exception var12) {
            var5 = null;
        } finally {
            if(var3 != null) {
                var3.disconnect();
            }

        }

        return null;
    }
}
