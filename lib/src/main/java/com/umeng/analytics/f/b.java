//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;

import a.a.a.a_j;
import a.a.a.d_interface;
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
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class b implements d_interface<b, b.e_enum>, Serializable, Cloneable {
    private static final long f = 9132678615281394583L;
    private static final m g = new m("IdJournal");
    private static final a.a.a.b.c h = new a.a.a.b.c("domain", (byte)11, (short)1);
    private static final a.a.a.b.c i = new a.a.a.b.c("old_id", (byte)11, (short)2);
    private static final a.a.a.b.c j = new a.a.a.b.c("new_id", (byte)11, (short)3);
    private static final a.a.a.b.c K_bc = new a.a.a.b.c("ts", (byte)10, (short)4);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> l = new HashMap();
    public String a;
    public String b;
    public String c;
    public long d;
    private static final int m = 0;
    private byte n;
    private e_enum[] o;
    public static final Map<e_enum, a.a.a.a.b> e;

    public b() {
        this.n = 0;
        this.o = new e_enum[]{e_enum.b};
    }

    public b(String var1, String var2, long var3) {
        this();
        this.a = var1;
        this.c = var2;
        this.d = var3;
        this.d(true);
    }

    public b(b var1) {
        this.n = 0;
        this.o = new e_enum[]{e_enum.b};
        this.n = var1.n;
        if(var1.e()) {
            this.a = var1.a;
        }

        if(var1.h()) {
            this.b = var1.b;
        }

        if(var1.k()) {
            this.c = var1.c;
        }

        this.d = var1.d;
    }

    public b p() {
        return new b(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d(false);
        this.d = 0L;
    }

    public String c() {
        return this.a;
    }

    public b p(String var1) {
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

    public String f() {
        return this.b;
    }

    public b b(String var1) {
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

    public String i() {
        return this.c;
    }

    public b c(String var1) {
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

    public long l() {
        return this.d;
    }

    public b a(long var1) {
        this.d = var1;
        this.d(true);
        return this;
    }

    public void m() {
        this.n = a_j.b(this.n, 0);
    }

    public boolean n() {
        return a_j.a(this.n, 0);
    }

    public void d(boolean var1) {
        this.n = a_j.a(this.n, 0, var1);
    }

    public e_enum b(int var1) {
        return e_enum.a(var1);
    }

    public void a(h var1) throws j {
        ((a.a.a.c.b)l.get(var1.D())).b().b(var1, this);
    }

    public void b(h var1) throws j {
        ((a.a.a.c.b)l.get(var1.D())).b().a(var1, this);
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder("IdJournal(");
        boolean var2 = true;
        var1.append("domain:");
        if(this.a == null) {
            var1.append("null");
        } else {
            var1.append(this.a);
        }

        var2 = false;
        if(this.h()) {
            if(!var2) {
                var1.append(", ");
            }

            var1.append("old_id:");
            if(this.b == null) {
                var1.append("null");
            } else {
                var1.append(this.b);
            }

            var2 = false;
        }

        if(!var2) {
            var1.append(", ");
        }

        var1.append("new_id:");
        if(this.c == null) {
            var1.append("null");
        } else {
            var1.append(this.c);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("ts:");
        var1.append(this.d);
        var2 = false;
        var1.append(")");
        return var1.toString();
    }

    public void o() throws j {
        if(this.a == null) {
            throw new i("Required field \'domain\' was not present! Struct: " + this.toString());
        } else if(this.c == null) {
            throw new i("Required field \'new_id\' was not present! Struct: " + this.toString());
        }
    }

    private void a(ObjectOutputStream var1) throws IOException {
        try {
            this.b((h)(new a.a.a.b.b(new a.a.a.d.a(var1))));
        } catch (j var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private void a(ObjectInputStream var1) throws IOException, ClassNotFoundException {
        try {
            this.n = 0;
            this.a((h)(new a.a.a.b.b(new a.a.a.d.a(var1))));
        } catch (j var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        l.put(a.a.a.c.c.class, new b.b_inner());
        l.put(a.a.a.c.d.class, new b.d());
        EnumMap var0 = new EnumMap(b.e_enum.class);
        var0.put(e_enum.a, new a.a.a.a.b("domain", (byte)1, new a.a.a.a.c((byte)11)));
        var0.put(e_enum.b, new a.a.a.a.b("old_id", (byte)2, new a.a.a.a.c((byte)11)));
        var0.put(e_enum.c, new a.a.a.a.b("new_id", (byte)1, new a.a.a.a.c((byte)11)));
        var0.put(e_enum.d, new a.a.a.a.b("ts", (byte)1, new a.a.a.a.c((byte)10)));
        e = Collections.unmodifiableMap(var0);
        a.a.a.a.b a = new a.a.a.a.b("",(byte)1,null);
        a.a(b.class, e);
    }

    private static class c extends a.a.a.c.d<b> {
        private c() {
        }

        public void a(h var1, b var2) throws j {
            n var3 = (n)var1;
            var3.a(var2.a);
            var3.a(var2.c);
            var3.a(var2.d);
            BitSet var4 = new BitSet();
            if(var2.h()) {
                var4.set(0);
            }

            var3.a(var4, 1);
            if(var2.h()) {
                var3.a(var2.b);
            }

        }

        public void b(h var1, b var2) throws j {
            n var3 = (n)var1;
            var2.a = var3.z();
            var2.a(true);
            var2.c = var3.z();
            var2.c(true);
            var2.d = var3.x();
            var2.d(true);
            BitSet var4 = var3.b(1);
            if(var4.get(0)) {
                var2.b = var3.z();
                var2.b(true);
            }

        }
    }

    private static class d implements a.a.a.c.b {
        private d() {
        }

        public b.c b() {
            return new b.c();
        }
    }

    private static class a_inner extends a.a.a.c.c<com.umeng.analytics.f.b> {
        private a_inner() {
        }

        public void a(h var1, com.umeng.analytics.f.b var2) throws j {
            var1.j();

            while(true) {
                a.a.a.b.c var3 = var1.l();
                if(var3.b == 0) {
                    var1.k();
                    if(!var2.n()) {
                        throw new i("Required field \'ts\' was not found in serialized data! Struct: " + this.toString());
                    }

                    var2.o();
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
                        if(var3.b == 11) {
                            var2.b = var1.z();
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
                    case 4:
                        if(var3.b == 10) {
                            var2.d = var1.x();
                            var2.d(true);
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

        public void b(h var1, com.umeng.analytics.f.b var2) throws j {
            var2.o();
            var1.a(com.umeng.analytics.f.b.g);
            if(var2.a != null) {
                var1.a(com.umeng.analytics.f.b.h);
                var1.a(var2.a);
                var1.c();
            }

            if(var2.b != null && var2.h()) {
                var1.a(com.umeng.analytics.f.b.i);
                var1.a(var2.b);
                var1.c();
            }

            if(var2.c != null) {
                var1.a(com.umeng.analytics.f.b.j);
                var1.a(var2.c);
                var1.c();
            }

            var1.a(com.umeng.analytics.f.b.K_bc);
            var1.a(var2.d);
            var1.c();
            var1.d();
            var1.b();
        }
    }

    private static class b_inner implements a.a.a.c.b {
        private b_inner() {
        }

        public b.a_inner b() {
            return new b.a_inner();
        }
    }

    public static enum e_enum implements a.a.a.k {
        a((short)1, "domain"),
        b((short)2, "old_id"),
        c((short)3, "new_id"),
        d((short)4, "ts");

        private static final Map<String, e_enum> e = new HashMap();
        private final short f;
        private final String g;

        public static e_enum a(int var0) {
            switch(var0) {
                case 1:
                    return a;
                case 2:
                    return b;
                case 3:
                    return c;
                case 4:
                    return d;
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
            return e.get(var0);
        }

        private e_enum(short var3, String var4) {
            this.f = var3;
            this.g = var4;
        }

        public short a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }

        static {
            Iterator var0 = EnumSet.allOf(e_enum.class).iterator();

            while(var0.hasNext()) {
                e_enum var1 = (e_enum)var0.next();
                e.put(var1.b(), var1);
            }

        }
    }
}
