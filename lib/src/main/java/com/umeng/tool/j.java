//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;

import android.content.Context;
import com.umeng.analytics.d.RequestTracker;

public class j {
    public static final int a_int = 0;
    public static final int b_int = 1;
    static final int c_int = 2;
    static final int d_int = 3;
    public static final int e_int = 4;
    public static final int f_int = 5;
    public static final int g_int = 6;
    public static final int h_int = 8;

    public j() {
    }

    public static boolean a(int var0) {
        boolean var1 = false;
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

    public static class a extends h {
        private final long a = 15000L;
        private RequestTracker b;

        public a(RequestTracker var1) {
            this.b = var1;
        }

        public boolean a(boolean var1) {
            return System.currentTimeMillis() - this.b.lastRequestTime >= 15000L;
        }
    }

    public static class j_inner extends h {
        private RequestTracker b;

        public j_inner(RequestTracker var1) {
            this.b = var1;
        }

        public boolean a(boolean var1) {
            return System.currentTimeMillis() - this.b.lastRequestTime >= 10800000L;
        }
    }

    public static class c extends h {
        private long a;
        private long b = 0L;

        public c(int var1) {
            this.a = (long)var1;
            this.b = System.currentTimeMillis();
        }

        public boolean a(boolean var1) {
            return System.currentTimeMillis() - this.b >= this.a;
        }

        public boolean a() {
            return System.currentTimeMillis() - this.b < this.a;
        }
    }

    public static class b extends h {
        private com.umeng.analytics.e.b a;
        private RequestTracker b;

        public b(RequestTracker var1, com.umeng.analytics.e.b var2) {
            this.b = var1;
            this.a = var2;
        }

        public boolean a(boolean var1) {
            long var2 = System.currentTimeMillis();
            long var4 = this.a.b();
            return var2 - this.b.lastRequestTime >= var4;
        }

        public boolean a() {
            return this.a.d();
        }
    }

    public static class i extends h {
        private Context context = null;

        public i(Context var1) {
            this.context = var1;
        }

        public boolean a(boolean var1) {
            return SystemUtil.isCurrentWifiConnect(this.context);
        }
    }

    public static class f extends h {
        private long a = 86400000L;
        private RequestTracker b;

        public f(RequestTracker var1) {
            this.b = var1;
        }

        public boolean a(boolean var1) {
            return System.currentTimeMillis() - this.b.lastRequestTime >= this.a;
        }
    }

    public static class e extends h {
        private static long a = 90000L;
        private static long b = 86400000L;
        private long c;
        private RequestTracker d;

        public e(RequestTracker var1, long var2) {
            this.d = var1;
            this.a(var2);
        }

        public boolean a(boolean var1) {
            return System.currentTimeMillis() - this.d.lastRequestTime >= this.c;
        }

        public void a(long var1) {
            if(var1 >= a && var1 <= b) {
                this.c = var1;
            } else {
                this.c = a;
            }

        }

        public long b() {
            return this.c;
        }

        public static boolean a(int var0) {
            return (long)var0 >= a;
        }
    }

    public static class d extends h {
        public d() {
        }

        public boolean a(boolean var1) {
            return var1;
        }
    }

    public static class g extends h {
        public g() {
        }

        public boolean a(boolean var1) {
            return true;
        }
    }

    public static class h {
        public h() {
        }

        public boolean a(boolean var1) {
            return true;
        }

        public boolean a() {
            return true;
        }
    }
}
