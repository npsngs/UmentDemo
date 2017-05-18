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
    private CacheTool cacheTool;
    private RequestTracker requestTracker;
    private long half_month = 1296000000L;
    private int h = 10000;
    private long i = 0L;
    private long j = 0L;
    private Context context;
    private static OptionSetter_c optionSetter_c = null;

    public static synchronized OptionSetter_c a(Context var0, RequestTracker var1) {
        if(optionSetter_c == null) {
            optionSetter_c = new OptionSetter_c(var0, var1);
            optionSetter_c.setOption(ImprintTool.getInstance(var0).getOption());
        }

        return optionSetter_c;
    }

    private OptionSetter_c(Context context, RequestTracker requestTracker) {
        this.context = context;
        this.cacheTool = CacheTool.getInstance(context);
        this.requestTracker = requestTracker;
    }

    public boolean isNeedReport() {
        if(this.cacheTool.hasCache()) {
            return false;
        } else if(this.requestTracker.hasNotRequest()) {
            return false;
        } else {
            long interval = System.currentTimeMillis() - this.requestTracker.getLastReq();
            if(interval > this.half_month) {
                String signature = UMEnvelopeData.getCachedSignature(this.context);
                this.i = (long) StringTool.a(this.h, signature);
                this.j = interval;
                return true;
            } else if(interval > 129600000L) {
                this.i = 0L;
                this.j = interval;
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
        this.half_month = var1.a(1296000000L);
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
