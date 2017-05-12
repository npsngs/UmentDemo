//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.umeng.analytics.d.SP_Util;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;

public class p extends a {
    private static final String a = "uuid";
    private Context b = null;
    private String c = null;
    private String d = null;
    private static final String e = "yosuid";
    private static final String f = "23346339";

    public p(Context var1) {
        super("uuid");
        this.b = var1;
        this.c = null;
        this.d = null;
    }

    public String f() {
        try {
            if(!TextUtils.isEmpty(a("ro.yunos.version", "")) && this.b != null) {
                SharedPreferences var1 = SP_Util.getSp(this.b);
                if(var1 != null) {
                    String var2 = var1.getString("yosuid", "");
                    if(TextUtils.isEmpty(var2)) {
                        this.d = this.b("23346339");
                        if(!TextUtils.isEmpty(this.d) && this.b != null && var1 != null) {
                            Editor var3 = var1.edit();
                            if(var3 != null) {
                                var3.putString("yosuid", this.d).commit();
                            }
                        }

                        return this.d;
                    }

                    return var2;
                }
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return null;
    }

    private String b(String var1) {
        this.d = a("ro.yunos.openuuid", "");
        if(!TextUtils.isEmpty(this.d)) {
            return this.d;
        } else {
            this.c = a("ro.aliyun.clouduuid", "");
            if(TextUtils.isEmpty(this.c)) {
                this.c = a("ro.sys.aliyun.clouduuid", "");
            }

            if(!TextUtils.isEmpty(this.c)) {
                HttpsURLConnection var2 = null;
                InputStream var3 = null;
                DataOutputStream var4 = null;
                BufferedReader var5 = null;

                try {
                    URL var6 = new URL("https://cmnsguider.yunos.com:443/genDeviceToken");
                    var2 = (HttpsURLConnection)var6.openConnection();
                    var2.setConnectTimeout(30000);
                    var2.setReadTimeout(30000);
                    var2.setRequestMethod("POST");
                    var2.setDoInput(true);
                    var2.setDoOutput(true);
                    var2.setUseCaches(false);
                    var2.setRequestProperty("Content-Type", "application/getSystemName-www-form-urlencoded");
                    var2.setHostnameVerifier(new HostnameVerifier() {
                        public boolean verify(String var1, SSLSession var2) {
                            BrowserCompatHostnameVerifier var3 = new BrowserCompatHostnameVerifier();
                            return var3.verify("cmnsguider.yunos.com", var2);
                        }
                    });
                    String var7 = "appKey=" + URLEncoder.encode("23338940", "UTF-8") + "&uuid=" + URLEncoder.encode("FC1FE84794417B1BEF276234F6FB4E63", "UTF-8");
                    var4 = new DataOutputStream(var2.getOutputStream());
                    var4.writeBytes(var7);
                    var4.flush();
                    int var8 = var2.getResponseCode();
                    if(var8 == 200) {
                        try {
                            var3 = var2.getInputStream();
                            var5 = new BufferedReader(new InputStreamReader(var3));
                            StringBuffer var10 = new StringBuffer();

                            String var9;
                            while((var9 = var5.readLine()) != null) {
                                var10.append(var9);
                            }

                            if(var10 != null) {
                                this.d = var10.toString();
                            }
                        } catch (Exception var28) {
                            var28.printStackTrace();
                        }
                    }
                } catch (Exception var29) {
                    var29.printStackTrace();
                } finally {
                    if(var4 != null) {
                        try {
                            var4.close();
                        } catch (Exception var27) {
                            var27.printStackTrace();
                        }
                    }

                    if(var5 != null) {
                        try {
                            var5.close();
                        } catch (Exception var26) {
                            var26.printStackTrace();
                        }
                    }

                    if(var3 != null) {
                        try {
                            var3.close();
                        } catch (Exception var25) {
                            var25.printStackTrace();
                        }
                    }

                    if(var2 != null) {
                        var2.disconnect();
                    }

                }
            }

            return this.d;
        }
    }

    public static String a(String var0, String var1) {
        try {
            Class var2 = Class.forName("android.os.SystemProperties");
            Method var3 = var2.getMethod("get", new Class[]{String.class, String.class});
            String var4 = (String)var3.invoke((Object)null, new Object[]{var0, var1});
            return var4;
        } catch (Exception var5) {
            var5.printStackTrace();
            return var1;
        }
    }
}
