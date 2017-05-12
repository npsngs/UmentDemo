//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;

import a.a.a.a_j;
import a.a.a.j;
import a.a.a.b.h;
import a.a.a.b.i;
import a.a.a.b.k;
import a.a.a.b.m;
import a.a.a.b.n;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class c implements a.a.a.d_interface<c, c.e_enum>, Serializable, Cloneable {
    private static final long e = -6496538196005191531L;
    private static final m f = new m("IdSnapshot");
    private static final a.a.a.b.c g = new a.a.a.b.c("identity", (byte) 11, (short) 1);
    private static final a.a.a.b.c h = new a.a.a.b.c("ts", (byte)10, (short)2);
    private static final a.a.a.b.c i = new a.a.a.b.c("version", (byte)8, (short)3);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> j = new HashMap();
    public String a;
    public long b;
    public int c;
    private static final int k_int = 0;
    private static final int l = 1;
    private byte m;
    public static final Map<e_enum, a.a.a.a.b> d;

    public c() {
        this.m = 0;
    }

    public c(String var1, long var2, int var4) {
        this();
        this.a = var1;
        this.b = var2;
        this.b(true);
        this.c = var4;
        this.c(true);
    }

    public c(c var1) {
        this.m = 0;
        this.m = var1.m;
        if(var1.e()) {
            this.a = var1.a;
        }

        this.b = var1.b;
        this.c = var1.c;
    }

    public c p() {
        return new c(this);
    }

    public void b() {
        this.a = null;
        this.b(false);
        this.b = 0L;
        this.c(false);
        this.c = 0;
    }

    public String c() {
        return this.a;
    }

    public c a(String var1) {
        this.a = var1;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean var1) {
        if(!var1) {
            this.a = null;
        }

    }

    public long f() {
        return this.b;
    }

    public c a(long var1) {
        this.b = var1;
        this.b(true);
        return this;
    }

    public void g() {
        this.m = a_j.b(this.m, 0);
    }

    public boolean h() {
        return a_j.a(this.m, 0);
    }

    public void b(boolean var1) {
        this.m = a_j.a(this.m, 0, var1);
    }

    public int i() {
        return this.c;
    }

    public c a(int var1) {
        this.c = var1;
        this.c(true);
        return this;
    }

    public void j() {
        this.m = a_j.b(this.m, 1);
    }

    public boolean k() {
        return a_j.a(this.m, 1);
    }

    public void c(boolean var1) {
        this.m = a_j.a(this.m, 1, var1);
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
        StringBuilder var1 = new StringBuilder("IdSnapshot(");
        boolean var2 = true;
        var1.append("identity:");
        if(this.a == null) {
            var1.append("null");
        } else {
            var1.append(this.a);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("ts:");
        var1.append(this.b);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("version:");
        var1.append(this.c);
        var2 = false;
        var1.append(")");
        return var1.toString();
    }

    public void l() throws j {
        if(this.a == null) {
            throw new i("Required field \'identity\' was not present! Struct: " + this.toString());
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
            this.m = 0;
            this.a((h)(new a.a.a.b.b(new a.a.a.d.a(var1))));
        } catch (j var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        j.put(a.a.a.c.c.class, new c.b());
        j.put(a.a.a.c.d.class, new c.d());
        EnumMap var0 = new EnumMap(e_enum.class);
        var0.put(e_enum.a, new a.a.a.a.b("identity", (byte)1, new a.a.a.a.c((byte)11)));
        var0.put(e_enum.b, new a.a.a.a.b("ts", (byte)1, new a.a.a.a.c((byte)10)));
        var0.put(e_enum.c, new a.a.a.a.b("version", (byte)1, new a.a.a.a.c((byte)8)));
        d = Collections.unmodifiableMap(var0);
        a.a.a.a.b a = new a.a.a.a.b(null, (byte)1, null);
        a.a(c.class, d);
    }

    private static class c_inner extends a.a.a.c.d<c> {
        private c_inner() {
        }

        public void a(h var1, c var2) throws j {
            n var3 = (n)var1;
            var3.a(var2.a);
            var3.a(var2.b);
            var3.a(var2.c);
        }

        public void b(h var1, c var2) throws j {
            n var3 = (n)var1;
            var2.a = var3.z();
            var2.a(true);
            var2.b = var3.x();
            var2.b(true);
            var2.c = var3.w();
            var2.c(true);
        }
    }

    private static class d implements a.a.a.c.b {
        private d() {
        }

        public c.c_inner b() {
            return new c.c_inner();
        }
    }

    private static class a_inner extends a.a.a.c.c<com.umeng.analytics.f.c> {
        private a_inner() {
        }

        public void a(h var1, com.umeng.analytics.f.c var2) throws j {
            var1.j();

            while(true) {
                a.a.a.b.c var3 = var1.l();
                if(var3.b == 0) {
                    var1.k();
                    if(!var2.h()) {
                        throw new i("Required field \'ts\' was not found in serialized data! Struct: " + this.toString());
                    }

                    if(!var2.k()) {
                        throw new i("Required field \'version\' was not found in serialized data! Struct: " + this.toString());
                    }

                    var2.l();
                    return;
                }

                switch(var3.c) {
                    case 1:
                        if(var3.b == 11) {
                            var2.a = var1.z();
                            var2.a(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 2:
                        if(var3.b == 10) {
                            var2.b = var1.x();
                            var2.b(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 3:
                        if(var3.b == 8) {
                            var2.c = var1.w();
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

        public void b(h var1, com.umeng.analytics.f.c var2) throws j {
            var2.l();
            var1.a(com.umeng.analytics.f.c.f);
            if(var2.a != null) {
                var1.a(com.umeng.analytics.f.c.g);
                var1.a(var2.a);
                var1.c();
            }

            var1.a(com.umeng.analytics.f.c.h);
            var1.a(var2.b);
            var1.c();
            var1.a(com.umeng.analytics.f.c.i);
            var1.a(var2.c);
            var1.c();
            var1.d();
            var1.b();
        }
    }

    private static class b implements a.a.a.c.b {
        private b() {
        }

        public a_inner b() {
            return new a_inner();
        }
    }

    public static enum e_enum implements a.a.a.k {
        a((short)1, "identity"),
        b((short)2, "ts"),
        c((short)3, "version");

        private static final Map<String, e_enum> d = new HashMap();
        private final short e;
        private final String f;

        public static e_enum a(int var0) {
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

        public static e_enum b(int var0) {
            e_enum var1 = a(var0);
            if(var1 == null) {
                throw new IllegalArgumentException("Field " + var0 + " doesn\'getPackageName exist!");
            } else {
                return var1;
            }
        }

        public static e_enum a(String var0) {
            return d.get(var0);
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
            Iterator var0 = EnumSet.allOf(e_enum.class).iterator();

            while(var0.hasNext()) {
                e_enum var1 = (e_enum)var0.next();
                d.put(var1.b(), var1);
            }

        }
    }
}
