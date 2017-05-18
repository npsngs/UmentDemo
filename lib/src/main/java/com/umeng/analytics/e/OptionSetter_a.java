//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.e;

import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.c.ImprintTool;
import com.umeng.analytics.c.UMEnvelopeData;
import com.umeng.tool.ULog;
import com.umeng.analytics.d.OptionSetter;

public class OptionSetter_a implements OptionSetter {
    private boolean a = false;
    private int policy = -1;
    private int interval = -1;
    private int d = -1;
    private float p13 = 0.0F;
    private float p07 = 0.0F;
    private String g = null;
    private Context context = null;
    private static OptionSetter_a i = null;

    public static synchronized OptionSetter_a a(Context context) {
        if(i == null) {
            ImprintTool.Option option = ImprintTool.getInstance(context).getOption();
            String var2 = option.getClient_test(null);
            int var3 = option.getTest_report_interval(0);
            i = new OptionSetter_a(context, var2, var3);
        }

        return i;
    }

    private OptionSetter_a(Context var1, String var2, int var3) {
        this.context = var1;
        this.a(var2, var3);
    }

    private float b(String signature, int var2) {
        var2 *= 2;
        if(signature == null) {
            return 0.0F;
        } else {
            float var3 = (float)Integer.valueOf(signature.substring(var2, var2 + 5), 16).intValue();
            float var4 = 1048576.0F;
            return var3 / var4;
        }
    }

    public void a(String test_client, int interval) {
        this.interval = interval;
        String signature = UMEnvelopeData.getCachedSignature(context);
        if(!TextUtils.isEmpty(signature) && !TextUtils.isEmpty(test_client)) {
            try {
                this.p13 = this.b(signature, 12);
                this.p07 = this.b(signature, 6);
                if(test_client.startsWith("SIG7")) {
                    this.b(test_client);
                } else if(test_client.startsWith("FIXED")) {
                    this.c(test_client);
                }
            } catch (Exception e) {
                this.a = false;
                ULog.e("getApplicationLabel:" + test_client, e);
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

    private void b(String test_client) {
        if(test_client != null) {
            String[] var2 = test_client.split("\\|");
            float var3 = 0.0F;
            if(var2[2].equals("SIG13")) {
                var3 = Float.valueOf(var2[3]).floatValue();
            }

            if(this.p13 > var3) {
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
                int i;
                String[] var11;
                if(var2[4].equals("RPT")) {
                    this.g = "RPT";
                    var11 = var2[5].split(",");
                    var10 = new int[var11.length];

                    for(i = 0; i < var11.length; ++i) {
                        var10[i] = Integer.valueOf(var11[i]).intValue();
                    }
                } else if(var2[4].equals("DOM")) {
                    this.a = true;
                    this.g = "DOM";

                    try {
                        var11 = var2[5].split(",");
                        var10 = new int[var11.length];

                        for(i = 0; i < var11.length; ++i) {
                            var10[i] = Integer.valueOf(var11[i]).intValue();
                        }
                    } catch (Exception e) {
                    }
                }

                var6 = -1;
                float var12 = 0.0F;

                for(int var8 = 0; var8 < var4.length; ++var8) {
                    var12 += var4[var8];
                    if(this.p07 < var12) {
                        var6 = var8;
                        break;
                    }
                }

                if(var6 != -1) {
                    this.a = true;
                    this.d = var6 + 1;
                    if(var10 != null) {
                        this.policy = var10[var6];
                    }
                } else {
                    this.a = false;
                }

            }
        }
    }

    private void c(String test_client) {
        if(test_client != null) {
            String[] var2 = test_client.split("\\|");
            float var3 = 0.0F;
            if(var2[2].equals("SIG13")) {
                var3 = Float.valueOf(var2[3]).floatValue();
            }

            if(this.p13 > var3) {
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
                        this.policy = var5[var4 - 1];
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

    public int getPolicy() {
        return this.policy;
    }

    public int getInterval() {
        return this.interval;
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

    public void setOption(ImprintTool.Option option) {
        this.a(option.getClient_test(null), option.getTest_report_interval(0));
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append(" p13:");
        var1.append(this.p13);
        var1.append(" p07:");
        var1.append(this.p07);
        var1.append(" policy:");
        var1.append(this.policy);
        var1.append(" interval:");
        var1.append(this.interval);
        return var1.toString();
    }
}
