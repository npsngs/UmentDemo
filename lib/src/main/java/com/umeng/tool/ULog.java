//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;

import android.util.Log;
import java.util.Formatter;
import java.util.Locale;

/**
 * Log类
 */
public class ULog {
    public static boolean isLogOn = false;
    private static String tag = "MobclickAgent";
    private static int h = 2000;

    private ULog() {
    }

    public static void a(Locale var0, String var1, Object... var2) {
        try {
            String var3 = (new Formatter(var0)).format(var1, var2).toString();
            c(tag, var3, null);
        } catch (Throwable var4) {
            e(var4);
        }

    }

    public static void b(Locale var0, String var1, Object... var2) {
        try {
            String var3 = (new Formatter(var0)).format(var1, var2).toString();
            b((String) tag, var3, (Throwable)null);
        } catch (Throwable var4) {
            e(var4);
        }

    }

    public static void c(Locale var0, String var1, Object... var2) {
        try {
            String var3 = (new Formatter(var0)).format(var1, var2).toString();
            e((String) tag, var3, (Throwable)null);
        } catch (Throwable var4) {
            e(var4);
        }

    }

    public static void d(Locale var0, String var1, Object... var2) {
        try {
            String var3 = (new Formatter(var0)).format(var1, var2).toString();
            a((String) tag, var3, (Throwable)null);
        } catch (Throwable var4) {
            e(var4);
        }

    }

    public static void e(Locale var0, String var1, Object... var2) {
        try {
            String var3 = (new Formatter(var0)).format(var1, var2).toString();
            d((String) tag, var3, (Throwable)null);
        } catch (Throwable var4) {
            e(var4);
        }

    }

    public static void a(String var0, Object... var1) {
        try {
            String var2 = "";
            if(var0.contains("%")) {
                var2 = (new Formatter()).format(var0, var1).toString();
                c((String) tag, var2, (Throwable)null);
            } else {
                if(var1 != null) {
                    var2 = (String)var1[0];
                }

                c((String)var0, var2, (Throwable)null);
            }
        } catch (Throwable var3) {
            e(var3);
        }

    }

    public static void b(String var0, Object... var1) {
        try {
            String var2 = "";
            if(var0.contains("%")) {
                var2 = (new Formatter()).format(var0, var1).toString();
                b((String) tag, var2, (Throwable)null);
            } else {
                if(var1 != null) {
                    var2 = (String)var1[0];
                }

                b((String)var0, var2, (Throwable)null);
            }
        } catch (Throwable var3) {
            e(var3);
        }

    }

    public static void c(String var0, Object... var1) {
        try {
            String var2 = "";
            if(var0.contains("%")) {
                var2 = (new Formatter()).format(var0, var1).toString();
                e((String) tag, var2, (Throwable)null);
            } else {
                if(var1 != null) {
                    var2 = (String)var1[0];
                }

                e((String)var0, var2, (Throwable)null);
            }
        } catch (Throwable var3) {
            e(var3);
        }

    }

    public static void d(String var0, Object... var1) {
        try {
            String var2 = "";
            if(var0.contains("%")) {
                var2 = (new Formatter()).format(var0, var1).toString();
                a((String) tag, var2, (Throwable)null);
            } else {
                if(var1 != null) {
                    var2 = (String)var1[0];
                }

                a((String)var0, var2, (Throwable)null);
            }
        } catch (Throwable var3) {
            e(var3);
        }

    }

    public static void e(String var0, Object... var1) {
        try {
            String var2 = "";
            if(var0.contains("%")) {
                var2 = (new Formatter()).format(var0, var1).toString();
                d((String) tag, var2, (Throwable)null);
            } else {
                if(var1 != null) {
                    var2 = (String)var1[0];
                }

                d((String)var0, var2, (Throwable)null);
            }
        } catch (Throwable var3) {
            e(var3);
        }

    }

    public static void a(Throwable var0) {
        c((String) tag, (String)null, (Throwable)var0);
    }

    public static void b(Throwable var0) {
        a((String) tag, (String)null, (Throwable)var0);
    }

    public static void c(Throwable var0) {
        d((String) tag, (String)null, (Throwable)var0);
    }

    public static void d(Throwable var0) {
        b((String) tag, (String)null, (Throwable)var0);
    }

    public static void e(Throwable var0) {
        e((String) tag, (String)null, (Throwable)var0);
    }

    public static void a(String var0, Throwable var1) {
        c(tag, var0, var1);
    }

    public static void b(String var0, Throwable var1) {
        a(tag, var0, var1);
    }

    public static void c(String var0, Throwable var1) {
        d(tag, var0, var1);
    }

    public static void d(String var0, Throwable var1) {
        b(tag, var0, var1);
    }

    public static void e(String var0, Throwable var1) {
        e(tag, var0, var1);
    }

    public static void a(String var0) {
        a((String) tag, var0, (Throwable)null);
    }

    public static void b(String var0) {
        b((String) tag, var0, (Throwable)null);
    }

    public static void c(String var0) {
        c((String) tag, var0, (Throwable)null);
    }

    public static void d(String var0) {
        d((String) tag, var0, (Throwable)null);
    }

    public static void e(String var0) {
        e((String) tag, var0, (Throwable)null);
    }

    public static void a(String var0, String var1, Throwable var2) {
        if(isLogOn) {
            a(1, var0, var1, var2);
        }

    }

    public static void b(String var0, String var1, Throwable var2) {
        if(isLogOn) {
            a(2, var0, var1, var2);
        }

    }

    public static void c(String var0, String var1, Throwable var2) {
        if(isLogOn) {
            a(3, var0, var1, var2);
        }

    }

    public static void d(String var0, String var1, Throwable var2) {
        if(isLogOn) {
            a(4, var0, var1, var2);
        }

    }

    public static void e(String var0, String var1, Throwable var2) {
        if(isLogOn) {
            a(5, var0, var1, var2);
        }

    }

    private static void a(int var0, String var1, String var2, Throwable var3) {
        int var4 = var2.length();
        int var5 = 0;
        int var6 = h;

        for(int var7 = 0; var7 < 100; ++var7) {
            if(var4 <= var6) {
                switch(var0) {
                    case 1:
                        Log.v(var1, var2.substring(var5, var4));
                        return;
                    case 2:
                        Log.d(var1, var2.substring(var5, var4));
                        return;
                    case 3:
                        Log.i(var1, var2.substring(var5, var4));
                        return;
                    case 4:
                        Log.w(var1, var2.substring(var5, var4));
                        return;
                    case 5:
                        Log.e(var1, var2.substring(var5, var4));
                        return;
                    default:
                        return;
                }
            }

            switch(var0) {
                case 1:
                    Log.v(var1, var2.substring(var5, var6));
                    break;
                case 2:
                    Log.d(var1, var2.substring(var5, var6));
                    break;
                case 3:
                    Log.i(var1, var2.substring(var5, var6));
                    break;
                case 4:
                    Log.w(var1, var2.substring(var5, var6));
                    break;
                case 5:
                    Log.e(var1, var2.substring(var5, var6));
            }

            var5 = var6;
            var6 += h;
            if(var3 != null) {
                StackTraceElement[] var8 = var3.getStackTrace();
                StackTraceElement[] var9 = var8;
                int var10 = var8.length;

                for(int var11 = 0; var11 < var10; ++var11) {
                    StackTraceElement var12 = var9[var11];
                    switch(var0) {
                        case 1:
                            Log.v(var1, "\t\tat\t" + var12.toString());
                            break;
                        case 2:
                            Log.d(var1, "\t\tat\t" + var12.toString());
                            break;
                        case 3:
                            Log.i(var1, "\t\tat\t" + var12.toString());
                            break;
                        case 4:
                            Log.w(var1, "\t\tat\t" + var12.toString());
                            break;
                        case 5:
                            Log.e(var1, "\t\tat\t" + var12.toString());
                    }
                }
            }
        }

    }
}
