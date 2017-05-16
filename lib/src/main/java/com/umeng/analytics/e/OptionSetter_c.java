//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.e;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.c.ImprintTool;
import com.umeng.analytics.c.ImprintTool.Option;
import com.umeng.analytics.c.UMEnvelopeData;
import com.umeng.analytics.d.OptionSetter;
import com.umeng.analytics.d.RequestTracker;
import com.umeng.tool.CacheTool;
import com.umeng.tool.StringTool;

public class OptionSetter_c implements OptionSetter {
    private final long a = 1296000000L;
    private final long b = 129600000L;
    private final int c = 1800000;
    private final int d = 10000;
    private CacheTool e;
    private RequestTracker f;
    private long g = 1296000000L;
    private int h = 10000;
    private long i = 0L;
    private long j = 0L;
    private Context k;
    private static OptionSetter_c l = null;

    public static synchronized OptionSetter_c a(Context var0, RequestTracker var1) {
        if(l == null) {
            l = new OptionSetter_c(var0, var1);
            l.setOption(ImprintTool.getInstance(var0).getOption());
        }

        return l;
    }

    private OptionSetter_c(Context var1, RequestTracker var2) {
        this.k = var1;
        this.e = CacheTool.getInstance(var1);
        this.f = var2;
    }

    public boolean a() {
        if(this.e.hasCache()) {
            return false;
        } else if(this.f.hasNotRequest()) {
            return false;
        } else {
            long var1 = System.currentTimeMillis() - this.f.getLastReq();
            if(var1 > this.g) {
                String var3 = UMEnvelopeData.getCachedSignature(this.k);
                this.i = (long) StringTool.a(this.h, var3);
                this.j = var1;
                return true;
            } else if(var1 > 129600000L) {
                this.i = 0L;
                this.j = var1;
                return true;
            } else {
                return false;
            }
        }
    }

    public long b() {
        return this.i;
    }

    public long c() {
        return this.j;
    }

    public void setOption(Option var1) {
        this.g = var1.a(1296000000L);
        int var2 = var1.b(0);
        if(var2 == 0) {
            if(AnalyticsConfig.sLatentWindow > 0 && AnalyticsConfig.sLatentWindow <= 1800000) {
                this.h = AnalyticsConfig.sLatentWindow;
            } else {
                this.h = 10000;
            }
        } else {
            this.h = var2;
        }

    }
}
