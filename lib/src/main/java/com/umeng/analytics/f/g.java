//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;

import a.a.a.a_j;
import a.a.a.b.k;
import a.a.a.d_interface;
import a.a.a.j;
import a.a.a.b.h;
import a.a.a.b.i;
import a.a.a.b.m;
import a.a.a.b.n;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class g implements d_interface<g, g.e_enum>, Serializable, Cloneable {
    private static final long e = -4549277923241195391L;
    private static final m f = new m("Response");
    private static final a.a.a.b.c g = new a.a.a.b.c("resp_code", (byte)8, (short) 1);
    private static final a.a.a.b.c h = new a.a.a.b.c("msg", (byte)11, (short)2);
    private static final a.a.a.b.c i = new a.a.a.b.c("imprint", (byte)12, (short)3);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> j = new HashMap();
    public int a;
    public String b;
    public com.umeng.analytics.f.e c;
    private static final int k_int = 0;
    private byte l;
    private e_enum[] m;
    public static final Map<e_enum, a.a.a.a.b> d;

    public g() {
        this.l = 0;
        this.m = new e_enum[]{e_enum.b, e_enum.c};
    }

    public g(int var1) {
        this();
        this.a = var1;
        this.a(true);
    }

    public g(g var1) {
        this.l = 0;
        this.m = new e_enum[]{e_enum.b, e_enum.c};
        this.l = var1.l;
        this.a = var1.a;
        if(var1.h()) {
            this.b = var1.b;
        }

        if(var1.k()) {
            this.c = new com.umeng.analytics.f.e(var1.c);
        }

    }

    public g p() {
        return new g(this);
    }

    public void b() {
        this.a(false);
        this.a = 0;
        this.b = null;
        this.c = null;
    }

    public int c() {
        return this.a;
    }

    public g a(int var1) {
        this.a = var1;
        this.a(true);
        return this;
    }

    public void d() {
        this.l = a_j.b(this.l, 0);
    }

    public boolean e() {
        return a_j.a(this.l, 0);
    }

    public void a(boolean var1) {
        this.l = a_j.a(this.l, 0, var1);
    }

    public String f() {
        return this.b;
    }

    public g a(String var1) {
        this.b = var1;
        return this;
    }

    public void g() {
        this.b = null;
    }

    public boolean h() {
        return this.b != null;
    }

    public void b(boolean var1) {
        if(!var1) {
            this.b = null;
        }

    }

    public com.umeng.analytics.f.e i() {
        return this.c;
    }

    public g a(com.umeng.analytics.f.e var1) {
        this.c = var1;
        return this;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
        return this.c != null;
    }

    public void c(boolean var1) {
        if(!var1) {
            this.c = null;
        }

    }

    public e_enum b(int var1) {
        return e_enum.a(var1);
    }

    public void a(h var1) throws j {
        ((a.a.a.c.b)j.get(var1.D())).b().b(var1, this);
    }

    public void b(h var1) throws j {
        ((a.a.a.c.b)j.get(var1.D())).b().a(var1, this);
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder("Response(");
        boolean var2 = true;
        var1.append("resp_code:");
        var1.append(this.a);
        var2 = false;
        if(this.h()) {
            if(!var2) {
                var1.append(", ");
            }

            var1.append("msg:");
            if(this.b == null) {
                var1.append("null");
            } else {
                var1.append(this.b);
            }

            var2 = false;
        }

        if(this.k()) {
            if(!var2) {
                var1.append(", ");
            }

            var1.append("imprint:");
            if(this.c == null) {
                var1.append("null");
            } else {
                var1.append(this.c);
            }

            var2 = false;
        }

        var1.append(")");
        return var1.toString();
    }

    public void l() throws j {
        if(this.c != null) {
            this.c.m();
        }

    }

    private void a(ObjectOutputStream var1) throws IOException {
        try {
            this.b(new a.a.a.b.b(new a.a.a.d.a(var1)));
        } catch (j var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private void a(ObjectInputStream var1) throws IOException, ClassNotFoundException {
        try {
            this.l = 0;
            this.a((h)(new a.a.a.b.b(new a.a.a.d.a(var1))));
        } catch (j var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        j.put(a.a.a.c.c.class, new g.b());
        j.put(a.a.a.c.d.class, new g.d());
        EnumMap var0 = new EnumMap(e_enum.class);
        var0.put(e_enum.a, new a.a.a.a.b("resp_code", (byte)1, new a.a.a.a.c((byte)8)));
        var0.put(e_enum.b, new a.a.a.a.b("msg", (byte)2, new a.a.a.a.c((byte)11)));
        var0.put(e_enum.c, new a.a.a.a.b("imprint", (byte)2, new a.a.a.a.g((byte)12, com.umeng.analytics.f.e.class)));
        d = Collections.unmodifiableMap(var0);
        a.a.a.a.b b = new a.a.a.a.b(null, (byte)1, null);
        b.a(g.class, d);
    }

    private static class c extends a.a.a.c.d<g> {
        private c() {
        }

        public void a(h var1, g var2) throws j {
            n var3 = (n)var1;
            var3.a(var2.a);
            BitSet var4 = new BitSet();
            if(var2.h()) {
                var4.set(0);
            }

            if(var2.k()) {
                var4.set(1);
            }

            var3.a(var4, 2);
            if(var2.h()) {
                var3.a(var2.b);
            }

            if(var2.k()) {
                var2.c.b(var3);
            }

        }

        public void b(h var1, g var2) throws j {
            n var3 = (n)var1;
            var2.a = var3.w();
            var2.a(true);
            BitSet var4 = var3.b(2);
            if(var4.get(0)) {
                var2.b = var3.z();
                var2.b(true);
            }

            if(var4.get(1)) {
                var2.c = new com.umeng.analytics.f.e();
                var2.c.a(var3);
                var2.c(true);
            }

        }
    }

    private static class d implements a.a.a.c.b {
        private d() {
        }

        public g.c b() {
            return new g.c();
        }
    }

    private static class a extends a.a.a.c.c<g> {
        private a() {
        }

        public void a(h var1, g var2) throws j {
            var1.j();

            while(true) {
                a.a.a.b.c var3 = var1.l();
                if(var3.b == 0) {
                    var1.k();
                    if(!var2.e()) {
                        throw new i("Required field \'resp_code\' was not found in serialized data! Struct: " + this.toString());
                    }

                    var2.l();
                    return;
                }

                switch(var3.c) {
                    case 1:
                        if(var3.b == 8) {
                            var2.a = var1.w();
                            var2.a(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 2:
                        if(var3.b == 11) {
                            var2.b = var1.z();
                            var2.b(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 3:
                        if(var3.b == 12) {
                            var2.c = new com.umeng.analytics.f.e();
                            var2.c.a(var1);
                            var2.c(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    default:
                        k.a(var1, var3.b);
                }

                var1.m();
            }
        }

        public void b(h var1, g var2) throws j {
            var2.l();
            var1.a(com.umeng.analytics.f.g.f);
            var1.a(com.umeng.analytics.f.g.g);
            var1.a(var2.a);
            var1.c();
            if(var2.b != null && var2.h()) {
                var1.a(com.umeng.analytics.f.g.h);
                var1.a(var2.b);
                var1.c();
            }

            if(var2.c != null && var2.k()) {
                var1.a(com.umeng.analytics.f.g.i);
                var2.c.b(var1);
                var1.c();
            }

            var1.d();
            var1.b();
        }
    }

    private static class b implements a.a.a.c.b {
        private b() {
        }

        public g.a b() {
            return new g.a();
        }
    }

    public static enum e_enum implements a.a.a.k {
        a((byte)1, "resp_code"),
        b((byte)2, "msg"),
        c((byte)3, "imprint");

        private static final Map<String, g.e_enum> d = new HashMap();
        private final short e;
        private final String f;

        public static g.e_enum a(int var0) {
            switch(var0) {
                case 1:
                    return a;
                case 2:
                    return b;
                case 3:
                    return c;
                default:
                    return null;
            }
        }

        public static g.e_enum b(int var0) {
            g.e_enum var1 = a(var0);
            if(var1 == null) {
                throw new IllegalArgumentException("Field " + var0 + " doesn\'getPackageName exist!");
            } else {
                return var1;
            }
        }

        public static g.e_enum a(String var0) {
            return (g.e_enum)d.get(var0);
        }

        private e_enum(short var3, String var4) {
            this.e = var3;
            this.f = var4;
        }

        public short a() {
            return this.e;
        }

        public String b() {
            return this.f;
        }

        static {
            Iterator var0 = EnumSet.allOf(g.e_enum.class).iterator();

            while(var0.hasNext()) {
                g.e_enum var1 = (g.e_enum)var0.next();
                d.put(var1.b(), var1);
            }

        }
    }
}
