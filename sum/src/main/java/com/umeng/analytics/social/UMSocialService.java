//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.social;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.analytics.social.c;
import com.umeng.analytics.social.e;
import org.json.JSONObject;

public abstract class UMSocialService {
    public UMSocialService() {
    }

    private static void a(Context var0, UMSocialService.b var1, String var2, UMPlatformData... var3) {
        try {
            if(var3 != null) {
                UMPlatformData[] var4 = var3;
                int var5 = var3.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    UMPlatformData var7 = var4[var6];
                    if(!var7.isValid()) {
                        throw new com.umeng.analytics.social.a("parameter is not valid.");
                    }
                }
            }

            String[] var9 = e.a(var0, var2, var3);
            (new UMSocialService.a(var9, var1, var3)).execute(new Object[0]);
        } catch (Exception var8) {
        }

    }

    public static void onShareEvent(Context context, String var1, UMPlatformData... var2) {
        a(context, null, var1, var2);
    }

    public static void onShareEvent(Context var0, UMPlatformData... var1) {
        a(var0, (UMSocialService.b)null, (String)null, var1);
    }

    private static class a extends AsyncTask<Object, Object, c> {
        String a;
        String b;
        UMSocialService.b c;
        UMPlatformData[] d;

        public a(String[] var1, UMSocialService.b var2, UMPlatformData[] var3) {
            this.a = var1[0];
            this.b = var1[1];
            this.c = var2;
            this.d = var3;
        }


        protected void onPreExecute() {
            if(this.c != null) {
                this.c.a();
            }

        }

        protected c doInBackground(Object... var1) {
            String var2 = null;
            if(TextUtils.isEmpty(this.b)) {
                var2 = com.umeng.analytics.social.b.a(this.a);
            } else {
                var2 = com.umeng.analytics.social.b.a(this.a, this.b);
            }

            try {
                JSONObject var3 = new JSONObject(var2);
                int var4 = var3.optInt("st");
                if(var4 == 0) {
                    var4 = -404;
                }

                c var5 = new c(var4);
                String var6 = var3.optString("msg");
                if(!TextUtils.isEmpty(var6)) {
                    var5.a(var6);
                }

                String var7 = var3.optString("data");
                if(!TextUtils.isEmpty(var7)) {
                    var5.b(var7);
                }

                return var5;
            } catch (Exception var8) {
                return new c(-99, var8);
            }
        }

        protected void a(c var1) {
            if(this.c != null) {
                this.c.a(var1, this.d);
            }

        }
    }

    public interface b {
        void a();

        void a(c var1, UMPlatformData... var2);
    }
}
