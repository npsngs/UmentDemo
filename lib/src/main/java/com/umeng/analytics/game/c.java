//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.umeng.tool.TaskExecutor;
import com.umeng.tool.g;
import com.umeng.tool.ULog;
import com.umeng.tool.SafeRunnable;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.Mobclick;
import com.umeng.analytics.d.SP_Util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

class c implements g {
    private Mobclick mobclick = MobclickAgent.getAgent();
    private com.umeng.analytics.game.b b = null;
    private final int c = 100;
    private final int d = 1;
    private final int e = 0;
    private final int f = -1;
    private final int g = 1;
    private final String h = "level";
    private final String i = "pay";
    private final String j = "buy";
    private final String k = "use";
    private final String l = "bonus";
    private final String m = "item";
    private final String n = "cash";
    private final String o = "coin";
    private final String p = "source";
    private final String q = "amount";
    private final String r = "user_level";
    private final String s = "bonus_source";
    private final String t = "level";
    private final String u = "status";
    private final String v = "duration";
    private final String w = "curtype";
    private final String x = "orderid";
    private final String y = "UMGameAgent.init(Context) should be called before any game api";
    private Context context;

    public c() {
        com.umeng.analytics.game.a.a = true;
    }

    void a(Context context) {
        if(context == null) {
            ULog.e("Context is null, can\'getPackageName init GameAgent");
        } else {
            this.context = context.getApplicationContext();
            this.mobclick.a(this);
            this.b = new com.umeng.analytics.game.b(this.context);
            this.mobclick.setVerticalType(context, 1);
        }
    }

    void a(boolean var1) {
        ULog.b(String.format("Trace sleep time : %scheduleExecute", new Object[]{Boolean.valueOf(var1)}));
        com.umeng.analytics.game.a.a = var1;
    }

    void a(String var1) {
        this.b.b = var1;
        SharedPreferences var2 = SP_Util.getSp(this.context);
        if(var2 != null) {
            Editor var3 = var2.edit();
            var3.putString("userlevel", var1);
            var3.commit();
        }
    }

    void b(final String var1) {
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            this.b.a = var1;
            TaskExecutor.execute(new SafeRunnable() {
                public void safeRun() {
                    c.this.b.a(var1);
                    HashMap var1x = new HashMap();
                    var1x.put("level", var1);
                    var1x.put("status", Integer.valueOf(0));
                    if(c.this.b.b != null) {
                        var1x.put("user_level", c.this.b.b);
                    }

                    c.this.mobclick.a(c.this.context, "level", var1x);
                }
            });
        }
    }

    private void a(final String var1, final int var2) {
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            TaskExecutor.execute(new SafeRunnable() {
                public void safeRun() {
                    com.umeng.analytics.game.b.a var1x = c.this.b.b(var1);
                    if(var1x != null) {
                        long var2x = var1x.e();
                        if(var2x <= 0L) {
                            ULog.b("level duration is 0");
                            return;
                        }

                        HashMap var4 = new HashMap();
                        var4.put("level", var1);
                        var4.put("status", Integer.valueOf(var2));
                        var4.put("duration", Long.valueOf(var2x));
                        if(c.this.b.b != null) {
                            var4.put("user_level", c.this.b.b);
                        }

                        c.this.mobclick.a(c.this.context, "level", var4);
                    } else {
                        ULog.d(String.format("finishLevel(or failLevel) called before startLevel", new Object[0]));
                    }

                }
            });
        }
    }

    void c(String var1) {
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            this.a(var1, 1);
        }
    }

    void d(String var1) {
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            this.a(var1, -1);
        }
    }

    void a(double var1, double var3, int var5) {
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("cash", Long.valueOf((long)(var1 * 100.0D)));
            hashMap.put("coin", Long.valueOf((long)(var3 * 100.0D)));
            hashMap.put("source", Integer.valueOf(var5));
            if(this.b.b != null) {
                hashMap.put("user_level", this.b.b);
            }

            if(this.b.a != null) {
                hashMap.put("level", this.b.a);
            }

            this.mobclick.a(this.context, "pay", hashMap);
        }
    }

    void a(double var1, String var3, int var4, double var5, int var7) {
        this.a(var1, var5 * (double)var4, var7);
        this.a(var3, var4, var5);
    }

    void a(String var1, int var2, double var3) {
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            HashMap var5 = new HashMap();
            var5.put("item", var1);
            var5.put("amount", Integer.valueOf(var2));
            var5.put("coin", Long.valueOf((long)((double)var2 * var3 * 100.0D)));
            if(this.b.b != null) {
                var5.put("user_level", this.b.b);
            }

            if(this.b.a != null) {
                var5.put("level", this.b.a);
            }

            this.mobclick.a(this.context, "buy", var5);
        }
    }

    void b(String var1, int var2, double var3) {
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            HashMap var5 = new HashMap();
            var5.put("item", var1);
            var5.put("amount", Integer.valueOf(var2));
            var5.put("coin", Long.valueOf((long)((double)var2 * var3 * 100.0D)));
            if(this.b.b != null) {
                var5.put("user_level", this.b.b);
            }

            if(this.b.a != null) {
                var5.put("level", this.b.a);
            }

            this.mobclick.a(this.context, "use", var5);
        }
    }

    void a(double var1, int var3) {
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            HashMap var4 = new HashMap();
            var4.put("coin", Long.valueOf((long)(var1 * 100.0D)));
            var4.put("bonus_source", Integer.valueOf(var3));
            if(this.b.b != null) {
                var4.put("user_level", this.b.b);
            }

            if(this.b.a != null) {
                var4.put("level", this.b.a);
            }

            this.mobclick.a(this.context, "bonus", var4);
        }
    }

    void a(String var1, int var2, double var3, int var5) {
        this.a(var3 * (double)var2, var5);
        this.a(var1, var2, var3);
    }

    public void a() {
        ULog.b("App resume from background");
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            if(com.umeng.analytics.game.a.a) {
                this.b.b();
            }

        }
    }

    public void b() {
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            if(com.umeng.analytics.game.a.a) {
                this.b.a();
            }

        }
    }

    void a(double var1, String var3, double var4, int var6, String var7) {
        if(this.context == null) {
            ULog.e("UMGameAgent.init(Context) should be called before any game api");
        } else {
            if(var1 >= 0.0D && var4 >= 0.0D) {
                HashMap var8 = new HashMap();
                if(!TextUtils.isEmpty(var3) && var3.length() > 0 && var3.length() <= 3) {
                    var8.put("curtype", var3);
                }

                if(!TextUtils.isEmpty(var7)) {
                    try {
                        int var9 = var7.getBytes("UTF-8").length;
                        if(var9 > 0 && var9 <= 1024) {
                            var8.put("orderid", var7);
                        }
                    } catch (UnsupportedEncodingException var10) {
                        var10.printStackTrace();
                    }
                }

                var8.put("cash", Long.valueOf((long)(var1 * 100.0D)));
                var8.put("coin", Long.valueOf((long)(var4 * 100.0D)));
                var8.put("source", Integer.valueOf(var6));
                if(this.b.b != null) {
                    var8.put("user_level", this.b.b);
                }

                if(this.b.a != null) {
                    var8.put("level", this.b.a);
                }

                this.mobclick.a(this.context, "pay", var8);
            }

        }
    }
}
