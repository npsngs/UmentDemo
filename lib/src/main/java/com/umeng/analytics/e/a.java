//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.e;

import android.content.Context;
import android.text.TextUtils;

import com.umeng.tool.ULog;
import com.umeng.analytics.d.l;

public class a implements l {
    private boolean a = false;
    private int b = -1;
    private int c = -1;
    private int d = -1;
    private float e = 0.0F;
    private float f = 0.0F;
    private String g = null;
    private Context h = null;
    private static a i = null;

    public static synchronized a a(Context var0) {
        if(i == null) {
            com.umeng.analytics.c.h.Option var1 = com.umeng.analytics.c.h.getInstance(var0).getOption();
            String var2 = var1.f((String)null);
            int var3 = var1.d(0);
            i = new a(var0, var2, var3);
        }

        return i;
    }

    private a(Context var1, String var2, int var3) {
        this.h = var1;
        this.a(var2, var3);
    }

    private float b(String var1, int var2) {
        var2 *= 2;
        if(var1 == null) {
            return 0.0F;
        } else {
            float var3 = (float)Integer.valueOf(var1.substring(var2, var2 + 5), 16).intValue();
            float var4 = 1048576.0F;
            return var3 / var4;
        }
    }

    public void a(String var1, int var2) {
        this.c = var2;
        String var3 = com.umeng.analytics.c.c.a(this.h);
        if(!TextUtils.isEmpty(var3) && !TextUtils.isEmpty(var1)) {
            try {
                this.e = this.b(var3, 12);
                this.f = this.b(var3, 6);
                if(var1.startsWith("SIG7")) {
                    this.b(var1);
                } else if(var1.startsWith("FIXED")) {
                    this.c(var1);
                }
            } catch (Exception var5) {
                this.a = false;
                ULog.e("getApplicationLabel:" + var1, var5);
            }

        } else {
            this.a = false;
        }
    }

    public static boolean a(String var0) {
        if(TextUtils.isEmpty(var0)) {
            return false;
        } else {
            String[] var1 = var0.split("\\|");
            if(var1.length != 6) {
                return false;
            } else {
                int var2;
                int var3;
                if(var1[0].startsWith("SIG7")) {
                    var2 = var1[1].split(",").length;
                    var3 = var1[5].split(",").length;
                    if(var2 == var3) {
                        return true;
                    }
                }

                if(var1[0].startsWith("FIXED")) {
                    var2 = var1[5].split(",").length;
                    var3 = Integer.parseInt(var1[1]);
                    if(var2 >= var3 && var3 >= 1) {
                        return true;
                    }
                }

                return false;
            }
        }
    }

    private void b(String var1) {
        if(var1 != null) {
            String[] var2 = var1.split("\\|");
            float var3 = 0.0F;
            if(var2[2].equals("SIG13")) {
                var3 = Float.valueOf(var2[3]).floatValue();
            }

            if(this.e > var3) {
                this.a = false;
            } else {
                float[] var4 = null;
                int var6;
                if(var2[0].equals("SIG7")) {
                    String[] var5 = var2[1].split(",");
                    var4 = new float[var5.length];

                    for(var6 = 0; var6 < var5.length; ++var6) {
                        var4[var6] = Float.valueOf(var5[var6]).floatValue();
                    }
                }

                int[] var10 = null;
                int var7;
                String[] var11;
                if(var2[4].equals("RPT")) {
                    this.g = "RPT";
                    var11 = var2[5].split(",");
                    var10 = new int[var11.length];

                    for(var7 = 0; var7 < var11.length; ++var7) {
                        var10[var7] = Integer.valueOf(var11[var7]).intValue();
                    }
                } else if(var2[4].equals("DOM")) {
                    this.a = true;
                    this.g = "DOM";

                    try {
                        var11 = var2[5].split(",");
                        var10 = new int[var11.length];

                        for(var7 = 0; var7 < var11.length; ++var7) {
                            var10[var7] = Integer.valueOf(var11[var7]).intValue();
                        }
                    } catch (Exception var9) {
                        ;
                    }
                }

                var6 = -1;
                float var12 = 0.0F;

                for(int var8 = 0; var8 < var4.length; ++var8) {
                    var12 += var4[var8];
                    if(this.f < var12) {
                        var6 = var8;
                        break;
                    }
                }

                if(var6 != -1) {
                    this.a = true;
                    this.d = var6 + 1;
                    if(var10 != null) {
                        this.b = var10[var6];
                    }
                } else {
                    this.a = false;
                }

            }
        }
    }

    private void c(String var1) {
        if(var1 != null) {
            String[] var2 = var1.split("\\|");
            float var3 = 0.0F;
            if(var2[2].equals("SIG13")) {
                var3 = Float.valueOf(var2[3]).floatValue();
            }

            if(this.e > var3) {
                this.a = false;
            } else {
                int var4 = -1;
                if(var2[0].equals("FIXED")) {
                    var4 = Integer.valueOf(var2[1]).intValue();
                }

                int[] var5 = null;
                String[] var6;
                int var7;
                if(var2[4].equals("RPT")) {
                    this.g = "RPT";
                    var6 = var2[5].split(",");
                    var5 = new int[var6.length];

                    for(var7 = 0; var7 < var6.length; ++var7) {
                        var5[var7] = Integer.valueOf(var6[var7]).intValue();
                    }
                } else if(var2[4].equals("DOM")) {
                    this.g = "DOM";
                    this.a = true;

                    try {
                        var6 = var2[5].split(",");
                        var5 = new int[var6.length];

                        for(var7 = 0; var7 < var6.length; ++var7) {
                            var5[var7] = Integer.valueOf(var6[var7]).intValue();
                        }
                    } catch (Exception var8) {
                        ;
                    }
                }

                if(var4 != -1) {
                    this.a = true;
                    this.d = var4;
                    if(var5 != null) {
                        this.b = var5[var4 - 1];
                    }
                } else {
                    this.a = false;
                }

            }
        }
    }

    public boolean a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public String e() {
        return !this.a?"error":String.valueOf(this.d);
    }

    public String f() {
        return this.g;
    }

    public void a(com.umeng.analytics.c.h.Option var1) {
        this.a(var1.f((String)null), var1.d(0));
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append(" p13:");
        var1.append(this.e);
        var1.append(" p07:");
        var1.append(this.f);
        var1.append(" policy:");
        var1.append(this.b);
        var1.append(" interval:");
        var1.append(this.c);
        return var1.toString();
    }
}
