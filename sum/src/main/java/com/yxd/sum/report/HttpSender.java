//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.report;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;

import com.umeng.analytics.c.ImprintTool;
import com.yxd.sum.track.RequestCallback;
import com.yxd.sum.tool.SPTool;
import com.umeng.analytics.e.OptionSetter_a;
import com.umeng.tool.StringTool;
import com.umeng.tool.SystemUtil;
import com.umeng.tool.ULog;
import com.yxd.sum.tool.EncodeTool;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.UMConst;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.net.Proxy.Type;
import java.security.KeyStore;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

public class HttpSender {
    private String systemInfo;
    private String ip = "10.0.0.172";
    private int port = 80;
    private Context context;
    private RequestCallback requestCallback;

    public HttpSender(Context context) {
        this.context = context;
        this.systemInfo = this.getSystemInfo(context);
    }

    public void setRequestCallback(RequestCallback requestCallback) {
        this.requestCallback = requestCallback;
    }

    private void initUrl() {
        String domain_p = ImprintTool.getInstance(this.context).getOption().getDomainP("");
        String domain_s = ImprintTool.getInstance(this.context).getOption().getDomain_s("");
        if(!TextUtils.isEmpty(domain_p)) {
            UMConst.url_p = StringTool.getUrl(domain_p);
        }

        if(!TextUtils.isEmpty(domain_s)) {
            UMConst.url_s = StringTool.getUrl(domain_s);
        }

        UMConst.urls = new String[]{UMConst.url_p, UMConst.url_s};
        int var3 = OptionSetter_a.a(this.context).getPolicy();
        if(var3 != -1) {
            if(var3 == 0) {
                UMConst.urls = new String[]{UMConst.url_p, UMConst.url_s};
            } else if(var3 == 1) {
                UMConst.urls = new String[]{UMConst.url_s, UMConst.url_p};
            }
        }

    }

    public byte[] send(byte[] data) {
        byte[] resp = null;
        this.initUrl();

        for(int i = 0; i < UMConst.urls.length; ++i) {
            resp = this.send(data, UMConst.urls[i]);
            if(resp != null) {
                if(this.requestCallback != null) {
                    this.requestCallback.requstSuccess();
                }
                break;
            }

            if(this.requestCallback != null) {
                this.requestCallback.RequestFailed();
            }
        }

        return resp;
    }

    private boolean isNeedProxy() {
        PackageManager packageManager = this.context.getPackageManager();
        if(packageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", this.context.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager)this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(!SystemUtil.hasPermission(this.context, "android.permission.ACCESS_NETWORK_STATE")) {
                    return false;
                }

                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.getType() != ConnectivityManager.TYPE_WIFI) {
                    String extraInfo = networkInfo.getExtraInfo();
                    if(extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                        return true;
                    }
                }
            } catch (Throwable throwable) {
            }

            return false;
        }
    }

    private byte[] send(byte[] sendData, String url) {
        HttpURLConnection connection = null;

        byte[] responseData;
        try {
            if(this.requestCallback != null) {
                this.requestCallback.request();
            }

            if(this.isNeedProxy()) {
                Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(this.ip, this.port));
                connection = (HttpURLConnection)(new URL(url)).openConnection(proxy);
            } else {
                connection = (HttpURLConnection)(new URL(url)).openConnection();
            }

            connection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
            connection.setRequestProperty("X-Umeng-Sdk", this.systemInfo);
            connection.setRequestProperty("Msg-Type", "envelope/json");
            connection.setRequestProperty("Content-Type", "envelope/json");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(30000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            if(VERSION.SDK_INT < 8) {
                System.setProperty("http.keepAlive", "false");
            }

            OutputStream os = connection.getOutputStream();
            os.write(sendData);
            os.flush();
            os.close();
            if(this.requestCallback != null) {
                this.requestCallback.requestFinish();
            }

            int responseCode = connection.getResponseCode();
            String contentType = connection.getHeaderField("Content-Type");
            boolean isThriftType = false;
            if(!TextUtils.isEmpty(contentType) && contentType.equalsIgnoreCase("application/thrift")) {
                isThriftType = true;
            }

            if(responseCode != 200 || !isThriftType) {
                return null;
            }

            ULog.c("Send message to " + url);
            InputStream is = connection.getInputStream();

            try {
                responseData = EncodeTool.readData(is);
            } finally {
                EncodeTool.close(is);
            }
        } catch (Throwable t) {
            ULog.e("IOException,Failed to setRequestCallback message.", t);
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }

        }

        return responseData;
    }

    private String getSystemInfo(Context context) {
        StringBuffer sb = new StringBuffer();
        sb.append("Android");
        sb.append("/");
        sb.append("6.1.0");
        sb.append(" ");

        try {
            StringBuffer var3 = new StringBuffer();
            var3.append(SystemUtil.getApplicationLabel(context));
            var3.append("/");
            var3.append(SystemUtil.getVersionName(context));
            var3.append(" ");
            var3.append(Build.MODEL);
            var3.append("/");
            var3.append(VERSION.RELEASE);
            var3.append(" ");
            var3.append(EncodeTool.getMD5_2(AnalyticsConfig.getAppkey(context)));
            sb.append(URLEncoder.encode(var3.toString(), "UTF-8"));
        } catch (Throwable throwable) {
        }

        return sb.toString();
    }

    public void sendHttps() {
        InputStream is = null;

        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            MSSLSocketFactory umsslSocket = new MSSLSocketFactory(keyStore);
            umsslSocket.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            HttpGet httpGet = new HttpGet("https://uop.umeng.com");
            BasicHttpParams httpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(httpParams, "ISO-8859-1");
            HttpProtocolParams.setUseExpectContinue(httpParams, true);
            ConnManagerParams.setTimeout(httpParams, 10000L);
            HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
            HttpConnectionParams.setSoTimeout(httpParams, 10000);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", umsslSocket, 443));
            ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(httpParams, schemeRegistry);
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient(threadSafeClientConnManager, httpParams);
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
            is = httpResponse.getEntity().getContent();
            if(is != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int ret;
                while((ret = is.read(buffer)) != -1) {
                    bos.write(buffer, 0, ret);
                }

                bos.close();
                String resp = new String(bos.toByteArray(), "UTF-8");
                if(!TextUtils.isEmpty(resp) && resp.length() > 0 && resp.length() < 50) {
                    SharedPreferences sp = SPTool.getSp(this.context);
                    if(sp != null) {
                        sp.edit().putString("uopdta", resp).apply();
                    }
                }
            }
        } catch (Throwable t) {
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (Throwable t2) {
                }
            }
        }
    }
}
