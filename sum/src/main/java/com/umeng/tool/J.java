//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;

import android.content.Context;
import com.yxd.sum.track.RequestTracker;
import com.umeng.analytics.e.OptionSetter_b;

public class J {
    public static final int a_int = 0;
    public static final int b_int = 1;
    static final int c_int = 2;
    static final int d_int = 3;
    public static final int e_int = 4;
    public static final int f_int = 5;
    public static final int g_int = 6;
    public static final int h_int = 8;

    public J() {
    }

    public static boolean a(int var0) {
        boolean var1;
        switch(var0) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                var1 = true;
                break;
            case 7:
            default:
                var1 = false;
        }

        return var1;
    }

    public static class UMPolicy_a extends UMPolicy {
        private final long a = 15000L;
        private RequestTracker requestTracker;

        public UMPolicy_a(RequestTracker requestTracker) {
            this.requestTracker = requestTracker;
        }

        public boolean canReport(boolean var1) {
            return System.currentTimeMillis() - this.requestTracker.lastRequestTime >= 15000L;
        }
    }

    public static class UMPolicy_j extends UMPolicy {
        private RequestTracker requestTracker;

        public UMPolicy_j(RequestTracker requestTracker) {
            this.requestTracker = requestTracker;
        }

        public boolean canReport(boolean var1) {
            return System.currentTimeMillis() - this.requestTracker.lastRequestTime >= 10800000L;
        }
    }

    public static class UMPolicy_c extends UMPolicy {
        private long maxInterval;
        private long lastTime = 0L;

        public UMPolicy_c(int interval) {
            this.maxInterval = (long)interval;
            this.lastTime = System.currentTimeMillis();
        }

        public boolean canReport(boolean var1) {
            return System.currentTimeMillis() - this.lastTime >= this.maxInterval;
        }

        public boolean canDiscard() {
            return System.currentTimeMillis() - this.lastTime < this.maxInterval;
        }
    }

    public static class UMPolicy_b extends UMPolicy {
        private OptionSetter_b optionSetter_b;
        private RequestTracker requestTracker;

        public UMPolicy_b(RequestTracker var1, OptionSetter_b var2) {
            this.requestTracker = var1;
            this.optionSetter_b = var2;
        }

        public boolean canReport(boolean var1) {
            long var2 = System.currentTimeMillis();
            long var4 = this.optionSetter_b.b();
            return var2 - this.requestTracker.lastRequestTime >= var4;
        }

        public boolean canDiscard() {
            return this.optionSetter_b.isDiscardOnFail();
        }
    }

    public static class UMPolicy_i extends UMPolicy {
        private Context context = null;

        public UMPolicy_i(Context context) {
            this.context = context;
        }

        public boolean canReport(boolean var1) {
            return SystemUtil.isCurrentWifiConnect(this.context);
        }
    }

    public static class UMPolicy_f extends UMPolicy {
        private long maxInterval = 86400000L;
        private RequestTracker requestTracker;

        public UMPolicy_f(RequestTracker requestTracker) {
            this.requestTracker = requestTracker;
        }

        public boolean canReport(boolean var1) {
            return System.currentTimeMillis() - this.requestTracker.lastRequestTime >= this.maxInterval;
        }
    }

    public static class UMPolicy_e extends UMPolicy {
        private static long a = 90000L;
        private static long b = 86400000L;
        private long maxInterval;
        private RequestTracker requestTracker;

        public UMPolicy_e(RequestTracker var1, long var2) {
            this.requestTracker = var1;
            this.setMaxInterval(var2);
        }

        public boolean canReport(boolean var1) {
            return System.currentTimeMillis() - this.requestTracker.lastRequestTime >= this.maxInterval;
        }

        public void setMaxInterval(long interval) {
            if(interval >= a && interval <= b) {
                this.maxInterval = interval;
            } else {
                this.maxInterval = a;
            }

        }

        public long getMaxInterval() {
            return this.maxInterval;
        }

        public static boolean a(int var0) {
            return (long)var0 >= a;
        }
    }

    public static class UMPolicy_d extends UMPolicy {
        public UMPolicy_d() {
        }

        public boolean canReport(boolean var1) {
            return var1;
        }
    }

    public static class UMPolicy_g extends UMPolicy {
        public UMPolicy_g() {
        }

        public boolean canReport(boolean var1) {
            return true;
        }
    }

    public static class UMPolicy {
        public UMPolicy() {
        }

        public boolean canReport(boolean var1) {
            return true;
        }

        public boolean canDiscard() {
            return true;
        }
    }
}
