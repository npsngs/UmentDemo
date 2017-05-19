//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.game;

import android.content.Context;
import com.umeng.tool.ULog;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.analytics.social.UMSocialService;

public class UMGameAgent extends MobclickAgent {
    private static final String a = "Input string is null or empty";
    private static final String b = "Input string must be less than 64 chars";
    private static final String c = "Input value type is negative";
    private static final String d = "The int value for \'Pay Channels\' ranges between 1 ~ 99 ";
    private static final c e = new c();
    private static Context f;

    public UMGameAgent() {
    }

    public static void init(Context var0) {
        e.a(var0);
        f = var0.getApplicationContext();
    }

    public static void setTraceSleepTime(boolean var0) {
        e.a(var0);
    }

    public static void setPlayerLevel(int var0) {
        e.a(String.valueOf(var0));
    }

    public static void startLevel(String var0) {
        if(a(var0)) {
            ULog.e("Input string is null or empty");
        } else if(var0.length() > 64) {
            ULog.e("Input string must be less than 64 chars");
        } else {
            e.b(var0);
        }
    }

    public static void finishLevel(String var0) {
        if(a(var0)) {
            ULog.e("Input string is null or empty");
        } else if(var0.length() > 64) {
            ULog.e("Input string must be less than 64 chars");
        } else {
            e.c(var0);
        }
    }

    public static void failLevel(String var0) {
        if(a(var0)) {
            ULog.e("Input string is null or empty");
        } else if(var0.length() > 64) {
            ULog.e("Input string must be less than 64 chars");
        } else {
            e.d(var0);
        }
    }

    public static void pay(double var0, double var2, int var4) {
        if(var4 > 0 && var4 < 100) {
            if(var0 >= 0.0D && var2 >= 0.0D) {
                e.a(var0, var2, var4);
            } else {
                ULog.e("Input value type is negative");
            }
        } else {
            ULog.e("The int value for \'Pay Channels\' ranges between 1 ~ 99 ");
        }
    }

    public static void pay(double var0, String var2, int var3, double var4, int var6) {
        if(var6 > 0 && var6 < 100) {
            if(var0 >= 0.0D && var3 >= 0 && var4 >= 0.0D) {
                if(a(var2)) {
                    ULog.e("Input string is null or empty");
                } else {
                    e.a(var0, var2, var3, var4, var6);
                }
            } else {
                ULog.e("Input value type is negative");
            }
        } else {
            ULog.e("The int value for \'Pay Channels\' ranges between 1 ~ 99 ");
        }
    }

    public static void exchange(double var0, String var2, double var3, int var5, String var6) {
        if(var0 >= 0.0D && var3 >= 0.0D) {
            if(var5 > 0 && var5 < 100) {
                e.a(var0, var2, var3, var5, var6);
            } else {
                ULog.e("The int value for \'Pay Channels\' ranges between 1 ~ 99 ");
            }
        } else {
            ULog.e("Input value type is negative");
        }
    }

    public static void buy(String var0, int var1, double var2) {
        if(a(var0)) {
            ULog.e("Input string is null or empty");
        } else if(var1 >= 0 && var2 >= 0.0D) {
            e.a(var0, var1, var2);
        } else {
            ULog.e("Input value type is negative");
        }
    }

    public static void use(String var0, int var1, double var2) {
        if(a(var0)) {
            ULog.e("Input string is null or empty");
        } else if(var1 >= 0 && var2 >= 0.0D) {
            e.b(var0, var1, var2);
        } else {
            ULog.e("Input value type is negative");
        }
    }

    public static void bonus(double var0, int var2) {
        if(var0 < 0.0D) {
            ULog.e("Input value type is negative");
        } else if(var2 > 0 && var2 < 100) {
            e.a(var0, var2);
        } else {
            ULog.e("The int value for \'Pay Channels\' ranges between 1 ~ 99 ");
        }
    }

    public static void bonus(String var0, int var1, double var2, int var4) {
        if(a(var0)) {
            ULog.e("Input string is null or empty");
        } else if(var1 >= 0 && var2 >= 0.0D) {
            if(var4 > 0 && var4 < 100) {
                e.a(var0, var1, var2, var4);
            } else {
                ULog.e("The int value for \'Pay Channels\' ranges between 1 ~ 99 ");
            }
        } else {
            ULog.e("Input value type is negative");
        }
    }

    private static boolean a(String var0) {
        if(var0 == null) {
            return true;
        } else {
            var0 = var0.trim();
            return var0.length() <= 0;
        }
    }

    public static void onEvent(String var0, String var1) {
        onEvent(f, var0, var1);
    }

    public static void onSocialEvent(Context var0, String var1, UMPlatformData... var2) {
        if(var0 == null) {
            ULog.e("context is null in onShareEvent");
        } else {
//            loadChannel = "4";
            UMSocialService.onShareEvent(var0, var1, var2);
        }
    }

    public static void onSocialEvent(Context var0, UMPlatformData... var1) {
        if(var0 == null) {
            ULog.e("context is null in onShareEvent");
        } else {
//            loadChannel = "4";
            UMSocialService.onShareEvent(var0, var1);
        }
    }
}
