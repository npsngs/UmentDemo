//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;

import a.a.a.a_j;
import a.a.a.b.k;
import a.a.a.d_interface;
import a.a.a.j;
import a.a.a.a.g;
import a.a.a.b.h;
import a.a.a.b.i;
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
import java.util.Map.Entry;

public class e implements d_interface<e, e.e_enum>, Serializable, Cloneable {
    private static final long serialNumber = 2846460275012375038L;
    private static final m f = new m("Imprint");
    private static final a.a.a.b.c g = new a.a.a.b.c("property", (byte) 13, (short) 1);
    private static final a.a.a.b.c h = new a.a.a.b.c("version", (byte)8, (short)2);
    private static final a.a.a.b.c i = new a.a.a.b.c("checksum", (byte)11, (short)3);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> j = new HashMap();
    public Map<String, f> a;
    public int b;
    public String c;
    private static final int k_int = 0;
    private byte l;
    public static final Map<e_enum, a.a.a.a.b> d;

    public e() {
        this.l = 0;
    }

    public e(Map<String, f> var1, int var2, String var3) {
        this();
        this.a = var1;
        this.b = var2;
        this.b(true);
        this.c = var3;
    }

    public e(e var1) {
        this.l = 0;
        this.l = var1.l;
        if(var1.f()) {
            HashMap var2 = new HashMap();
            Iterator var3 = var1.a.entrySet().iterator();

            while(var3.hasNext()) {
                Entry var4 = (Entry)var3.next();
                String var5 = (String)var4.getKey();
                f var6 = (f)var4.getValue();
                f var8 = new f(var6);
                var2.put(var5, var8);
            }

            this.a = var2;
        }

        this.b = var1.b;
        if(var1.l()) {
            this.c = var1.c;
        }

    }

    public e p() {
        return new e(this);
    }

    public void b() {
        this.a = null;
        this.b(false);
        this.b = 0;
        this.c = null;
    }

    public int c() {
        return this.a == null?0:this.a.size();
    }

    public void a(String var1, f var2) {
        if(this.a == null) {
            this.a = new HashMap();
        }

        this.a.put(var1, var2);
    }

    public Map<String, f> d() {
        return this.a;
    }

    public e a(Map<String, f> var1) {
        this.a = var1;
        return this;
    }

    public void e() {
        this.a = null;
    }

    public boolean f() {
        return this.a != null;
    }

    public void a(boolean var1) {
        if(!var1) {
            this.a = null;
        }

    }

    public int g() {
        return this.b;
    }

    public e a(int var1) {
        this.b = var1;
        this.b(true);
        return this;
    }

    public void h() {
        this.l = a_j.b(this.l, 0);
    }

    public boolean i() {
        return a_j.a(this.l, 0);
    }

    public void b(boolean var1) {
        this.l = a_j.a(this.l, 0, var1);
    }

    public String j() {
        return this.c;
    }

    public e a(String var1) {
        this.c = var1;
        return this;
    }

    public void k() {
        this.c = null;
    }

    public boolean l() {
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
        StringBuilder var1 = new StringBuilder("Imprint(");
        boolean var2 = true;
        var1.append("property:");
        if(this.a == null) {
            var1.append("null");
        } else {
            var1.append(this.a);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("version:");
        var1.append(this.b);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("checksum:");
        if(this.c == null) {
            var1.append("null");
        } else {
            var1.append(this.c);
        }

        var2 = false;
        var1.append(")");
        return var1.toString();
    }

    public void m() throws j {
        if(this.a == null) {
            throw new i("Required field \'property\' was not present! Struct: " + this.toString());
        } else if(this.c == null) {
            throw new i("Required field \'checksum\' was not present! Struct: " + this.toString());
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
        j.put(a.a.a.c.c.class, new e.b());
        j.put(a.a.a.c.d.class, new e.d());
        EnumMap var0 = new EnumMap(e_enum.class);
        var0.put(e_enum.PROPERTY, new a.a.a.a.b("property", (byte)1, new a.a.a.a.e((byte)13, new a.a.a.a.c((byte)11), new g((byte)12, f.class))));
        var0.put(e_enum.VERSION, new a.a.a.a.b("version", (byte)1, new a.a.a.a.c((byte)8)));
        var0.put(e_enum.CHECKSUM, new a.a.a.a.b("checksum", (byte)1, new a.a.a.a.c((byte)11)));
        d = Collections.unmodifiableMap(var0);
        a.a.a.a.b b = new a.a.a.a.b(null, (byte) 1, null);
        b.a(e.class, d);
    }

    private static class c extends a.a.a.c.d<e> {
        private c() {
        }

        public void a(h var1, e var2) throws j {
            n var3 = (n)var1;
            var3.a(var2.a.size());
            Iterator var4 = var2.a.entrySet().iterator();

            while(var4.hasNext()) {
                Entry var5 = (Entry)var4.next();
                var3.a((String)var5.getKey());
                ((f)var5.getValue()).b(var3);
            }

            var3.a(var2.b);
            var3.a(var2.c);
        }

        public void b(h var1, e var2) throws j {
            n var3 = (n)var1;
            a.a.a.b.e var4 = new a.a.a.b.e((byte)11, (byte)12, var3.w());
            var2.a = new HashMap(2 * var4.c);

            for(int var5 = 0; var5 < var4.c; ++var5) {
                String var6 = var3.z();
                f var7 = new f();
                var7.a(var3);
                var2.a.put(var6, var7);
            }

            var2.a(true);
            var2.b = var3.w();
            var2.b(true);
            var2.c = var3.z();
            var2.c(true);
        }
    }

    private static class d implements a.a.a.c.b {
        private d() {
        }

        public e.c b() {
            return new e.c();
        }
    }

    private static class a extends a.a.a.c.c<com.umeng.analytics.f.e> {
        private a() {
        }

        public void a(h var1, e var2) throws j {
            var1.j();

            while(true) {
                a.a.a.b.c var3 = var1.l();
                if(var3.b == 0) {
                    var1.k();
                    if(!var2.i()) {
                        throw new i("Required field \'version\' was not found in serialized data! Struct: " + this.toString());
                    }

                    var2.m();
                    return;
                }

                switch(var3.c) {
                    case 1:
                        if(var3.b != 13) {
                            k.a(var1, var3.b);
                            break;
                        }

                        a.a.a.b.e var4 = var1.n();
                        var2.a = new HashMap(2 * var4.c);

                        for(int var5 = 0; var5 < var4.c; ++var5) {
                            String var6 = var1.z();
                            f var7 = new f();
                            var7.a(var1);
                            var2.a.put(var6, var7);
                        }

                        var1.o();
                        var2.a(true);
                        break;
                    case 2:
                        if(var3.b == 8) {
                            var2.b = var1.w();
                            var2.b(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 3:
                        if(var3.b == 11) {
                            var2.c = var1.z();
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

        public void b(h var1, e var2) throws j {
            var2.m();
            var1.a(com.umeng.analytics.f.e.f);
            if(var2.a != null) {
                var1.a(com.umeng.analytics.f.e.g);
                var1.a(new a.a.a.b.e((byte)11, (byte)12, var2.a.size()));
                Iterator var3 = var2.a.entrySet().iterator();

                while(var3.hasNext()) {
                    Entry var4 = (Entry)var3.next();
                    var1.a((String)var4.getKey());
                    ((f)var4.getValue()).b(var1);
                }

                var1.e();
                var1.c();
            }

            var1.a(com.umeng.analytics.f.e.h);
            var1.a(var2.b);
            var1.c();
            if(var2.c != null) {
                var1.a(com.umeng.analytics.f.e.i);
                var1.a(var2.c);
                var1.c();
            }

            var1.d();
            var1.b();
        }
    }

    private static class b implements a.a.a.c.b {
        private b() {
        }

        public e.a b() {
            return new e.a();
        }
    }

    public static enum e_enum implements a.a.a.k {
        PROPERTY((byte)1, "property"),
        VERSION((byte)2, "version"),
        CHECKSUM((byte)3, "checksum");

        private static final Map<String, e.e_enum> d = new HashMap();
        private final short e;
        private final String f;

        public static e.e_enum a(int var0) {
            switch(var0) {
                case 1:
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        public static e.e_enum b(int var0) {
            e.e_enum var1 = a(var0);
            if(var1 == null) {
                throw new IllegalArgumentException("Field " + var0 + " doesn\'getPackageName exist!");
            } else {
                return var1;
            }
        }

        public static e.e_enum a(String var0) {
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
            Iterator var0 = EnumSet.allOf(e.e_enum.class).iterator();

            while(var0.hasNext()) {
                e.e_enum var1 = (e.e_enum)var0.next();
                d.put(var1.b(), var1);
            }

        }
    }
}
