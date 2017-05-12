//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.g;
import a.a.a.e_j;
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
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class a implements d_interface<a, a.e_enum>, Serializable, Cloneable {
    private static final long l = 420342210744516016L;
    private static final m m = new m("UMEnvelope");
    private static final a.a.a.b.c n = new a.a.a.b.c("version", (byte)11, (short)1);
    private static final a.a.a.b.c o = new a.a.a.b.c("address", (byte)11, (short)2);
    private static final a.a.a.b.c p = new a.a.a.b.c("signature", (byte)11, (short)3);
    private static final a.a.a.b.c q = new a.a.a.b.c("serial_num", (byte)8, (short)4);
    private static final a.a.a.b.c r = new a.a.a.b.c("ts_secs", (byte)8, (short)5);
    private static final a.a.a.b.c s = new a.a.a.b.c("length", (byte)8, (short)6);
    private static final a.a.a.b.c t = new a.a.a.b.c("entity", (byte)11, (short)7);
    private static final a.a.a.b.c u = new a.a.a.b.c("guid", (byte)11, (short)8);
    private static final a.a.a.b.c v = new a.a.a.b.c("checksum", (byte)11, (short)9);
    private static final a.a.a.b.c w = new a.a.a.b.c("codex", (byte)8, (short)10);
    private static final Map<Class<? extends a.a.a.c.a>, a.a.a.c.b> x = new HashMap();
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;
    private static final int y = 0;
    private static final int z = 1;
    private static final int A = 2;
    private static final int B = 3;
    private byte C;
    private e_enum[] D;
    public static final Map<e_enum, a.a.a.a.b> k_map;

    public a() {
        this.C = 0;
        this.D = new e_enum[]{e_enum.j};
    }

    public a(String var1, String var2, String var3, int var4, int var5, int var6, ByteBuffer var7, String var8, String var9) {
        this();
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.d(true);
        this.e = var5;
        this.e(true);
        this.f = var6;
        this.f(true);
        this.g = var7;
        this.h = var8;
        this.i = var9;
    }

    public a(a var1) {
        this.C = 0;
        this.D = new e_enum[]{e_enum.j};
        this.C = var1.C;
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
        this.e = var1.e;
        this.f = var1.f;
        if(var1.y()) {
            this.g = e_j.d(var1.g);
        }

        if(var1.B()) {
            this.h = var1.h;
        }

        if(var1.E()) {
            this.i = var1.i;
        }

        this.j = var1.j;
    }

    public a p() {
        return new a(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d(false);
        this.d = 0;
        this.e(false);
        this.e = 0;
        this.f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j(false);
        this.j = 0;
    }

    public String c() {
        return this.a;
    }

    public a a(String var1) {
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

    public a b(String var1) {
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

    public a c(String var1) {
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

    public int l() {
        return this.d;
    }

    public a a(int var1) {
        this.d = var1;
        this.d(true);
        return this;
    }

    public void m() {
        this.C = a_j.b(this.C, 0);
    }

    public boolean n() {
        return a_j.a(this.C, 0);
    }

    public void d(boolean var1) {
        this.C = a_j.a(this.C, 0, var1);
    }

    public int o() {
        return this.e;
    }

    public a c(int var1) {
        this.e = var1;
        this.e(true);
        return this;
    }

    public void q() {
        this.C = a_j.b(this.C, 1);
    }

    public boolean r() {
        return a_j.a(this.C, 1);
    }

    public void e(boolean var1) {
        this.C = a_j.a(this.C, 1, var1);
    }

    public int s() {
        return this.f;
    }

    public a d(int var1) {
        this.f = var1;
        this.f(true);
        return this;
    }

    public void t() {
        this.C = a_j.b(this.C, 2);
    }

    public boolean u() {
        return a_j.a(this.C, 2);
    }

    public void f(boolean var1) {
        this.C = a_j.a(this.C, 2, var1);
    }

    public byte[] v() {
        this.a(e_j.c(this.g));
        return this.g == null?null:this.g.array();
    }

    public ByteBuffer w() {
        return this.g;
    }

    public a a(byte[] var1) {
        this.a(var1 == null?(ByteBuffer)null:ByteBuffer.wrap(var1));
        return this;
    }

    public a a(ByteBuffer var1) {
        this.g = var1;
        return this;
    }

    public void x() {
        this.g = null;
    }

    public boolean y() {
        return this.g != null;
    }

    public void g(boolean var1) {
        if(!var1) {
            this.g = null;
        }

    }

    public String z() {
        return this.h;
    }

    public a d(String var1) {
        this.h = var1;
        return this;
    }

    public void A() {
        this.h = null;
    }

    public boolean B() {
        return this.h != null;
    }

    public void h(boolean var1) {
        if(!var1) {
            this.h = null;
        }

    }

    public String C() {
        return this.i;
    }

    public a e(String var1) {
        this.i = var1;
        return this;
    }

    public void D() {
        this.i = null;
    }

    public boolean E() {
        return this.i != null;
    }

    public void i(boolean var1) {
        if(!var1) {
            this.i = null;
        }

    }

    public int F() {
        return this.j;
    }

    public a e(int var1) {
        this.j = var1;
        this.j(true);
        return this;
    }

    public void G() {
        this.C = a_j.b(this.C, 3);
    }

    public boolean H() {
        return a_j.a(this.C, 3);
    }

    public void j(boolean var1) {
        this.C = a_j.a(this.C, 3, var1);
    }

    public e_enum b(int var1) {
        return e_enum.a(var1);
    }

    public void a(h var1) throws j {
        ((a.a.a.c.b)x.get(var1.D())).b().b(var1, this);
    }

    public void b(h var1) throws j {
        ((a.a.a.c.b)x.get(var1.D())).b().a(var1, this);
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder("UMEnvelope(");
        boolean var2 = true;
        var1.append("version:");
        if(this.a == null) {
            var1.append("null");
        } else {
            var1.append(this.a);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("address:");
        if(this.b == null) {
            var1.append("null");
        } else {
            var1.append(this.b);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("signature:");
        if(this.c == null) {
            var1.append("null");
        } else {
            var1.append(this.c);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("serial_num:");
        var1.append(this.d);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("ts_secs:");
        var1.append(this.e);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("length:");
        var1.append(this.f);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("entity:");
        if(this.g == null) {
            var1.append("null");
        } else {
            e_j.a(this.g, var1);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("guid:");
        if(this.h == null) {
            var1.append("null");
        } else {
            var1.append(this.h);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("checksum:");
        if(this.i == null) {
            var1.append("null");
        } else {
            var1.append(this.i);
        }

        var2 = false;
        if(this.H()) {
            if(!var2) {
                var1.append(", ");
            }

            var1.append("codex:");
            var1.append(this.j);
            var2 = false;
        }

        var1.append(")");
        return var1.toString();
    }

    public void I() throws j {
        if(this.a == null) {
            throw new i("Required field \'version\' was not present! Struct: " + this.toString());
        } else if(this.b == null) {
            throw new i("Required field \'address\' was not present! Struct: " + this.toString());
        } else if(this.c == null) {
            throw new i("Required field \'signature\' was not present! Struct: " + this.toString());
        } else if(this.g == null) {
            throw new i("Required field \'entity\' was not present! Struct: " + this.toString());
        } else if(this.h == null) {
            throw new i("Required field \'guid\' was not present! Struct: " + this.toString());
        } else if(this.i == null) {
            throw new i("Required field \'checksum\' was not present! Struct: " + this.toString());
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
            this.C = 0;
            this.a((h)(new a.a.a.b.b(new a.a.a.d.a(var1))));
        } catch (j var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        x.put(a.a.a.c.c.class, new a.b());
        x.put(a.a.a.c.d.class, new a.d());
        EnumMap var0 = new EnumMap(e_enum.class);
        var0.put(e_enum.a, new a.a.a.a.b("version", (byte)1, new a.a.a.a.c((byte)11)));
        var0.put(e_enum.b, new a.a.a.a.b("address", (byte)1, new a.a.a.a.c((byte)11)));
        var0.put(e_enum.c, new a.a.a.a.b("signature", (byte)1, new a.a.a.a.c((byte)11)));
        var0.put(e_enum.d, new a.a.a.a.b("serial_num", (byte)1, new a.a.a.a.c((byte)8)));
        var0.put(e_enum.e, new a.a.a.a.b("ts_secs", (byte)1, new a.a.a.a.c((byte)8)));
        var0.put(e_enum.f, new a.a.a.a.b("length", (byte)1, new a.a.a.a.c((byte)8)));
        var0.put(e_enum.g, new a.a.a.a.b("entity", (byte)1, new a.a.a.a.c((byte)11, true)));
        var0.put(e_enum.h, new a.a.a.a.b("guid", (byte)1, new a.a.a.a.c((byte)11)));
        var0.put(e_enum.i, new a.a.a.a.b("checksum", (byte)1, new a.a.a.a.c((byte)11)));
        var0.put(e_enum.j, new a.a.a.a.b("codex", (byte)2, new a.a.a.a.c((byte)8)));
        k_map = Collections.unmodifiableMap(var0);
        a.a.a.a.b b = new a.a.a.a.b(null,(byte)1,null);
        b.a(a.class, k_map);
    }

    private static class c extends a.a.a.c.d<a> {
        private c() {
        }

        public void a(h var1, a var2) throws j {
            n var3 = (n)var1;
            var3.a(var2.a);
            var3.a(var2.b);
            var3.a(var2.c);
            var3.a(var2.d);
            var3.a(var2.e);
            var3.a(var2.f);
            var3.a(var2.g);
            var3.a(var2.h);
            var3.a(var2.i);
            BitSet var4 = new BitSet();
            if(var2.H()) {
                var4.set(0);
            }

            var3.a(var4, 1);
            if(var2.H()) {
                var3.a(var2.j);
            }

        }

        public void b(h var1, a var2) throws j {
            n var3 = (n)var1;
            var2.a = var3.z();
            var2.a(true);
            var2.b = var3.z();
            var2.b(true);
            var2.c = var3.z();
            var2.c(true);
            var2.d = var3.w();
            var2.d(true);
            var2.e = var3.w();
            var2.e(true);
            var2.f = var3.w();
            var2.f(true);
            var2.g = var3.A();
            var2.g(true);
            var2.h = var3.z();
            var2.h(true);
            var2.i = var3.z();
            var2.i(true);
            BitSet var4 = var3.b(1);
            if(var4.get(0)) {
                var2.j = var3.w();
                var2.j(true);
            }

        }
    }

    private static class d implements a.a.a.c.b {
        private d() {
        }

        public a.c b() {
            return new a.c();
        }
    }

    private static class a_inner extends a.a.a.c.c<com.umeng.analytics.g.a> {
        private a_inner() {
        }

        public void a(h var1, a var2) throws j {
            var1.j();

            while(true) {
                a.a.a.b.c var3 = var1.l();
                if(var3.b == 0) {
                    var1.k();
                    if(!var2.n()) {
                        throw new i("Required field \'serial_num\' was not found in serialized data! Struct: " + this.toString());
                    }

                    if(!var2.r()) {
                        throw new i("Required field \'ts_secs\' was not found in serialized data! Struct: " + this.toString());
                    }

                    if(!var2.u()) {
                        throw new i("Required field \'length\' was not found in serialized data! Struct: " + this.toString());
                    }

                    var2.I();
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
                        if(var3.b == 8) {
                            var2.d = var1.w();
                            var2.d(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 5:
                        if(var3.b == 8) {
                            var2.e = var1.w();
                            var2.e(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 6:
                        if(var3.b == 8) {
                            var2.f = var1.w();
                            var2.f(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 7:
                        if(var3.b == 11) {
                            var2.g = var1.A();
                            var2.g(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 8:
                        if(var3.b == 11) {
                            var2.h = var1.z();
                            var2.h(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 9:
                        if(var3.b == 11) {
                            var2.i = var1.z();
                            var2.i(true);
                        } else {
                            k.a(var1, var3.b);
                        }
                        break;
                    case 10:
                        if(var3.b == 8) {
                            var2.j = var1.w();
                            var2.j(true);
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

        public void b(h var1, a var2) throws j {
            var2.I();
            var1.a(com.umeng.analytics.g.a.m);
            if(var2.a != null) {
                var1.a(com.umeng.analytics.g.a.n);
                var1.a(var2.a);
                var1.c();
            }

            if(var2.b != null) {
                var1.a(com.umeng.analytics.g.a.o);
                var1.a(var2.b);
                var1.c();
            }

            if(var2.c != null) {
                var1.a(com.umeng.analytics.g.a.p);
                var1.a(var2.c);
                var1.c();
            }

            var1.a(com.umeng.analytics.g.a.q);
            var1.a(var2.d);
            var1.c();
            var1.a(com.umeng.analytics.g.a.r);
            var1.a(var2.e);
            var1.c();
            var1.a(com.umeng.analytics.g.a.s);
            var1.a(var2.f);
            var1.c();
            if(var2.g != null) {
                var1.a(com.umeng.analytics.g.a.t);
                var1.a(var2.g);
                var1.c();
            }

            if(var2.h != null) {
                var1.a(com.umeng.analytics.g.a.u);
                var1.a(var2.h);
                var1.c();
            }

            if(var2.i != null) {
                var1.a(com.umeng.analytics.g.a.v);
                var1.a(var2.i);
                var1.c();
            }

            if(var2.H()) {
                var1.a(com.umeng.analytics.g.a.w);
                var1.a(var2.j);
                var1.c();
            }

            var1.d();
            var1.b();
        }
    }

    private static class b implements a.a.a.c.b {
        private b() {
        }

        public a.a_inner b() {
            return new a.a_inner();
        }
    }

    public static enum e_enum implements a.a.a.k {
        a((byte)1, "version"),
        b((byte)2, "address"),
        c((byte)3, "signature"),
        d((byte)4, "serial_num"),
        e((byte)5, "ts_secs"),
        f((byte)6, "length"),
        g((byte)7, "entity"),
        h((byte)8, "guid"),
        i((byte)9, "checksum"),
        j((byte)10, "codex");

        private static final Map<String, a.e_enum> k = new HashMap();
        private final short l;
        private final String m;

        public static a.e_enum a(int var0) {
            switch(var0) {
                case 1:
                    return a;
                case 2:
                    return b;
                case 3:
                    return c;
                case 4:
                    return d;
                case 5:
                    return e;
                case 6:
                    return f;
                case 7:
                    return g;
                case 8:
                    return h;
                case 9:
                    return i;
                case 10:
                    return j;
                default:
                    return null;
            }
        }

        public static a.e_enum b(int var0) {
            a.e_enum var1 = a(var0);
            if(var1 == null) {
                throw new IllegalArgumentException("Field " + var0 + " doesn\'getPackageName exist!");
            } else {
                return var1;
            }
        }

        public static a.e_enum a(String var0) {
            return (a.e_enum)k.get(var0);
        }

        private e_enum(short var3, String var4) {
            this.l = var3;
            this.m = var4;
        }

        public short a() {
            return this.l;
        }

        public String b() {
            return this.m;
        }

        static {
            Iterator var0 = EnumSet.allOf(a.e_enum.class).iterator();

            while(var0.hasNext()) {
                a.e_enum var1 = (a.e_enum)var0.next();
                k.put(var1.b(), var1);
            }

        }
    }
}
