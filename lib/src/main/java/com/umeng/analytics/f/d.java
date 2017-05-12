//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;


import a.a.a.j;
import a.a.a.a.g;
import a.a.a.b.h;
import a.a.a.b.i;
import a.a.a.b.k;
import a.a.a.b.m;
import a.a.a.b.n;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class d implements a.a.a.d_interface<d, d.e_enum>, Serializable, Cloneable {
    private static final long e = -5764118265293965743L;
    private static final m f = new m("IdTracking");
    private static final a.a.a.b.c g = new a.a.a.b.c("snapshots", (byte)13, (short) 1);
    private static final a.a.a.b.c h = new a.a.a.b.c("journals", (byte)15, (short)2);
    private static final a.a.a.b.c i = new a.a.a.b.c("checksum", (byte)11, (short)3);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> j = new HashMap();
    public Map<String, com.umeng.analytics.f.c> a;
    public List<com.umeng.analytics.f.b> b;
    public String c;
    private e_enum[] k_enums;
    public static final Map<e_enum, a.a.a.a.b> d;

    public d() {
        this.k_enums = new e_enum[]{e_enum.b, e_enum.c};
    }

    public d(Map<String, com.umeng.analytics.f.c> var1) {
        this();
        this.a = var1;
    }

    public d(d var1) {
        this.k_enums = new e_enum[]{e_enum.b, e_enum.c};
        Iterator var3;
        if(var1.f()) {
            HashMap var2 = new HashMap();
            var3 = var1.a.entrySet().iterator();

            while(var3.hasNext()) {
                Entry var4 = (Entry)var3.next();
                String var5 = (String)var4.getKey();
                com.umeng.analytics.f.c var6 = (com.umeng.analytics.f.c)var4.getValue();
                com.umeng.analytics.f.c var8 = new com.umeng.analytics.f.c(var6);
                var2.put(var5, var8);
            }

            this.a = var2;
        }

        if(var1.k()) {
            ArrayList var9 = new ArrayList();
            var3 = var1.b.iterator();

            while(var3.hasNext()) {
                com.umeng.analytics.f.b var10 = (com.umeng.analytics.f.b)var3.next();
                var9.add(new com.umeng.analytics.f.b(var10));
            }

            this.b = var9;
        }

        if(var1.n()) {
            this.c = var1.c;
        }

    }

    public d p() {
        return new d(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int c() {
        return this.a == null?0:this.a.size();
    }

    public void a(String var1, com.umeng.analytics.f.c var2) {
        if(this.a == null) {
            this.a = new HashMap();
        }

        this.a.put(var1, var2);
    }

    public Map<String, com.umeng.analytics.f.c> d() {
        return this.a;
    }

    public d a(Map<String, com.umeng.analytics.f.c> var1) {
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
        return this.b == null?0:this.b.size();
    }

    public Iterator<com.umeng.analytics.f.b> h() {
        return this.b == null?null:this.b.iterator();
    }

    public void a(com.umeng.analytics.f.b var1) {
        if(this.b == null) {
            this.b = new ArrayList();
        }

        this.b.add(var1);
    }

    public List<com.umeng.analytics.f.b> i() {
        return this.b;
    }

    public d a(List<com.umeng.analytics.f.b> var1) {
        this.b = var1;
        return this;
    }

    public void j() {
        this.b = null;
    }

    public boolean k() {
        return this.b != null;
    }

    public void b(boolean var1) {
        if(!var1) {
            this.b = null;
        }

    }

    public String l() {
        return this.c;
    }

    public d a(String var1) {
        this.c = var1;
        return this;
    }

    public void m() {
        this.c = null;
    }

    public boolean n() {
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
        StringBuilder var1 = new StringBuilder("IdTracking(");
        boolean var2 = true;
        var1.append("snapshots:");
        if(this.a == null) {
            var1.append("null");
        } else {
            var1.append(this.a);
        }

        var2 = false;
        if(this.k()) {
            if(!var2) {
                var1.append(", ");
            }

            var1.append("journals:");
            if(this.b == null) {
                var1.append("null");
            } else {
                var1.append(this.b);
            }

            var2 = false;
        }

        if(this.n()) {
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
        }

        var1.append(")");
        return var1.toString();
    }

    public void o() throws j {
        if(this.a == null) {
            throw new i("Required field \'snapshots\' was not present! Struct: " + this.toString());
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
            this.a((h)(new a.a.a.b.b(new a.a.a.d.a(var1))));
        } catch (j var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        j.put(a.a.a.c.c.class, new d.b());
        j.put(a.a.a.c.d.class, new d.d_inner());
        EnumMap var0 = new EnumMap(d.e_enum.class);
        var0.put(e_enum.a, new a.a.a.a.b("snapshots", (byte)1, new a.a.a.a.e((byte)13, new a.a.a.a.c((byte)11), new g((byte)12, com.umeng.analytics.f.c.class))));
        var0.put(e_enum.b, new a.a.a.a.b("journals", (byte)2, new a.a.a.a.d((byte)15, new g((byte)12, com.umeng.analytics.f.b.class))));
        var0.put(e_enum.c, new a.a.a.a.b("checksum", (byte)2, new a.a.a.a.c((byte)11)));
        d = Collections.unmodifiableMap(var0);
        a.a.a.a.b b = new a.a.a.a.b(null, (byte)1, null);
        b.a(d.class, d);
    }

    private static class c extends a.a.a.c.d<d> {
        private c() {
        }

        public void a(h var1, d var2) throws j {
            n var3 = (n)var1;
            var3.a(var2.a.size());
            Iterator var4 = var2.a.entrySet().iterator();

            while(var4.hasNext()) {
                Entry var5 = (Entry)var4.next();
                var3.a((String)var5.getKey());
                ((com.umeng.analytics.f.c)var5.getValue()).b(var3);
            }

            BitSet var7 = new BitSet();
            if(var2.k()) {
                var7.set(0);
            }

            if(var2.n()) {
                var7.set(1);
            }

            var3.a(var7, 2);
            if(var2.k()) {
                var3.a(var2.b.size());
                Iterator var8 = var2.b.iterator();

                while(var8.hasNext()) {
                    com.umeng.analytics.f.b var6 = (com.umeng.analytics.f.b)var8.next();
                    var6.b(var3);
                }
            }

            if(var2.n()) {
                var3.a(var2.c);
            }

        }

        public void b(h var1, d var2) throws j {
            n var3 = (n)var1;
            a.a.a.b.e var4 = new a.a.a.b.e((byte)11, (byte)12, var3.w());
            var2.a = new HashMap(2 * var4.c);

            for(int var5 = 0; var5 < var4.c; ++var5) {
                String var6 = var3.z();
                com.umeng.analytics.f.c var7 = new com.umeng.analytics.f.c();
                var7.a(var3);
                var2.a.put(var6, var7);
            }

            var2.a(true);
            BitSet var8 = var3.b(2);
            if(var8.get(0)) {
                a.a.a.b.d var9 = new a.a.a.b.d((byte)12, var3.w());
                var2.b = new ArrayList(var9.b);

                for(int var10 = 0; var10 < var9.b; ++var10) {
                    com.umeng.analytics.f.b var11 = new com.umeng.analytics.f.b();
                    var11.a(var3);
                    var2.b.add(var11);
                }

                var2.b(true);
            }

            if(var8.get(1)) {
                var2.c = var3.z();
                var2.c(true);
            }

        }
    }

    private static class d_inner implements a.a.a.c.b {
        private d_inner() {
        }

        public d.c b() {
            return new d.c();
        }
    }

    private static class a extends a.a.a.c.c<com.umeng.analytics.f.d> {
        private a() {
        }

        public void a(h var1, com.umeng.analytics.f.d var2) throws j {
            var1.j();

            while(true) {
                a.a.a.b.c var3 = var1.l();
                if(var3.b == 0) {
                    var1.k();
                    var2.o();
                    return;
                }

                int var5;
                switch(var3.c) {
                    case 1:
                        if(var3.b != 13) {
                            k.a(var1, var3.b);
                            break;
                        }

                        a.a.a.b.e var8 = var1.n();
                        var2.a = new HashMap(2 * var8.c);

                        for(var5 = 0; var5 < var8.c; ++var5) {
                            String var9 = var1.z();
                            com.umeng.analytics.f.c var7 = new com.umeng.analytics.f.c();
                            var7.a(var1);
                            var2.a.put(var9, var7);
                        }

                        var1.o();
                        var2.a(true);
                        break;
                    case 2:
                        if(var3.b != 15) {
                            k.a(var1, var3.b);
                            break;
                        }

                        a.a.a.b.d var4 = var1.p();
                        var2.b = new ArrayList(var4.b);

                        for(var5 = 0; var5 < var4.b; ++var5) {
                            com.umeng.analytics.f.b var6 = new com.umeng.analytics.f.b();
                            var6.a(var1);
                            var2.b.add(var6);
                        }

                        var1.q();
                        var2.b(true);
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

        public void b(h var1, com.umeng.analytics.f.d var2) throws j {
            var2.o();
            var1.a(com.umeng.analytics.f.d.f);
            Iterator var3;
            if(var2.a != null) {
                var1.a(com.umeng.analytics.f.d.g);
                var1.a(new a.a.a.b.e((byte)11, (byte)12, var2.a.size()));
                var3 = var2.a.entrySet().iterator();

                while(var3.hasNext()) {
                    Entry var4 = (Entry)var3.next();
                    var1.a((String)var4.getKey());
                    ((com.umeng.analytics.f.c)var4.getValue()).b(var1);
                }

                var1.e();
                var1.c();
            }

            if(var2.b != null && var2.k()) {
                var1.a(com.umeng.analytics.f.d.h);
                var1.a(new a.a.a.b.d((byte)12, var2.b.size()));
                var3 = var2.b.iterator();

                while(var3.hasNext()) {
                    com.umeng.analytics.f.b var5 = (com.umeng.analytics.f.b)var3.next();
                    var5.b(var1);
                }

                var1.f();
                var1.c();
            }

            if(var2.c != null && var2.n()) {
                var1.a(com.umeng.analytics.f.d.i);
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

        public d.a b() {
            return new d.a();
        }
    }

    public static enum e_enum implements a.a.a.k {
        a((byte)1, "snapshots"),
        b((byte)2, "journals"),
        c((byte)3, "checksum");

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
