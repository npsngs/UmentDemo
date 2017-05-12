//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

import a.a.a.j;
import a.a.a.d.c;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class a extends h {
    private static final m h = new m();
    protected static final int a = -65536;
    protected static final int b = -2147418112;
    protected boolean c;
    protected boolean d;
    protected int e;
    protected boolean f;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;

    public a(c var1) {
        this(var1, false, true);
    }

    public a(c var1, boolean var2, boolean var3) {
        super(var1);
        this.c = false;
        this.d = true;
        this.f = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.c = var2;
        this.d = var3;
    }

    public void a(f var1) throws j {
        if(this.d) {
            int var2 = -2147418112 | var1.b;
            this.a(var2);
            this.a(var1.a);
            this.a(var1.c);
        } else {
            this.a(var1.a);
            this.a(var1.b);
            this.a(var1.c);
        }

    }

    public void a() {
    }

    public void a(m var1) {
    }

    public void b() {
    }

    public void a(a.a.a.b.c var1) throws j {
        this.a(var1.b);
        this.a(var1.c);
    }

    public void c() {
    }

    public void d() throws j {
        this.a((byte)0);
    }

    public void a(e var1) throws j {
        this.a(var1.a);
        this.a(var1.b);
        this.a(var1.c);
    }

    public void e() {
    }

    public void a(d var1) throws j {
        this.a(var1.a);
        this.a(var1.b);
    }

    public void f() {
    }

    public void a(l var1) throws j {
        this.a(var1.a);
        this.a(var1.b);
    }

    public void g() {
    }

    public void a(boolean var1) throws j {
        this.a((byte)(var1?1:0));
    }

    public void a(byte var1) throws j {
        this.i[0] = var1;
        this.g.b(this.i, 0, 1);
    }

    public void a(short var1) throws j {
        this.j[0] = (byte)(255 & var1 >> 8);
        this.j[1] = (byte)(255 & var1);
        this.g.b(this.j, 0, 2);
    }

    public void a(int var1) throws j {
        this.k[0] = (byte)(255 & var1 >> 24);
        this.k[1] = (byte)(255 & var1 >> 16);
        this.k[2] = (byte)(255 & var1 >> 8);
        this.k[3] = (byte)(255 & var1);
        this.g.b(this.k, 0, 4);
    }

    public void a(long var1) throws j {
        this.l[0] = (byte)((int)(255L & var1 >> 56));
        this.l[1] = (byte)((int)(255L & var1 >> 48));
        this.l[2] = (byte)((int)(255L & var1 >> 40));
        this.l[3] = (byte)((int)(255L & var1 >> 32));
        this.l[4] = (byte)((int)(255L & var1 >> 24));
        this.l[5] = (byte)((int)(255L & var1 >> 16));
        this.l[6] = (byte)((int)(255L & var1 >> 8));
        this.l[7] = (byte)((int)(255L & var1));
        this.g.b(this.l, 0, 8);
    }

    public void a(double var1) throws j {
        this.a(Double.doubleToLongBits(var1));
    }

    public void a(String var1) throws j {
        try {
            byte[] var2 = var1.getBytes("UTF-8");
            this.a(var2.length);
            this.g.b(var2, 0, var2.length);
        } catch (UnsupportedEncodingException var3) {
            throw new j("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void a(ByteBuffer var1) throws j {
        int var2 = var1.limit() - var1.position();
        this.a(var2);
        this.g.b(var1.array(), var1.position() + var1.arrayOffset(), var2);
    }

    public f h() throws j {
        int var1 = this.w();
        if(var1 < 0) {
            int var2 = var1 & -65536;
            if(var2 != -2147418112) {
                throw new i(4, "Bad version in readMessageBegin");
            } else {
                return new f(this.z(), (byte)(var1 & 255), this.w());
            }
        } else if(this.c) {
            throw new i(4, "Missing version in readMessageBegin, old client?");
        } else {
            return new f(this.b(var1), this.u(), this.w());
        }
    }

    public void i() {
    }

    public m j() {
        return h;
    }

    public void k() {
    }

    public a.a.a.b.c l() throws j {
        byte var1 = this.u();
        short var2 = var1 == 0?0:this.v();
        return new a.a.a.b.c("", var1, var2);
    }

    public void m() {
    }

    public e n() throws j {
        return new e(this.u(), this.u(), this.w());
    }

    public void o() {
    }

    public d p() throws j {
        return new d(this.u(), this.w());
    }

    public void q() {
    }

    public l r() throws j {
        return new l(this.u(), this.w());
    }

    public void s() {
    }

    public boolean t() throws j {
        return this.u() == 1;
    }

    public byte u() throws j {
        if(this.g.h() >= 1) {
            byte var1 = this.g.f()[this.g.g()];
            this.g.a(1);
            return var1;
        } else {
            this.a(this.m, 0, 1);
            return this.m[0];
        }
    }

    public short v() throws j {
        byte[] var1 = this.n;
        int var2 = 0;
        if(this.g.h() >= 2) {
            var1 = this.g.f();
            var2 = this.g.g();
            this.g.a(2);
        } else {
            this.a(this.n, 0, 2);
        }

        return (short)((var1[var2] & 255) << 8 | var1[var2 + 1] & 255);
    }

    public int w() throws j {
        byte[] var1 = this.o;
        int var2 = 0;
        if(this.g.h() >= 4) {
            var1 = this.g.f();
            var2 = this.g.g();
            this.g.a(4);
        } else {
            this.a(this.o, 0, 4);
        }

        return (var1[var2] & 255) << 24 | (var1[var2 + 1] & 255) << 16 | (var1[var2 + 2] & 255) << 8 | var1[var2 + 3] & 255;
    }

    public long x() throws j {
        byte[] var1 = this.p;
        int var2 = 0;
        if(this.g.h() >= 8) {
            var1 = this.g.f();
            var2 = this.g.g();
            this.g.a(8);
        } else {
            this.a(this.p, 0, 8);
        }

        return (long)(var1[var2] & 255) << 56 | (long)(var1[var2 + 1] & 255) << 48 | (long)(var1[var2 + 2] & 255) << 40 | (long)(var1[var2 + 3] & 255) << 32 | (long)(var1[var2 + 4] & 255) << 24 | (long)(var1[var2 + 5] & 255) << 16 | (long)(var1[var2 + 6] & 255) << 8 | (long)(var1[var2 + 7] & 255);
    }

    public double y() throws j {
        return Double.longBitsToDouble(this.x());
    }

    public String z() throws j {
        int var1 = this.w();
        if(this.g.h() >= var1) {
            try {
                String var2 = new String(this.g.f(), this.g.g(), var1, "UTF-8");
                this.g.a(var1);
                return var2;
            } catch (UnsupportedEncodingException var3) {
                throw new j("JVM DOES NOT SUPPORT UTF-8");
            }
        } else {
            return this.b(var1);
        }
    }

    public String b(int var1) throws j {
        try {
            this.d(var1);
            byte[] var2 = new byte[var1];
            this.g.d(var2, 0, var1);
            return new String(var2, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            throw new j("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer A() throws j {
        int var1 = this.w();
        this.d(var1);
        if(this.g.h() >= var1) {
            ByteBuffer var3 = ByteBuffer.wrap(this.g.f(), this.g.g(), var1);
            this.g.a(var1);
            return var3;
        } else {
            byte[] var2 = new byte[var1];
            this.g.d(var2, 0, var1);
            return ByteBuffer.wrap(var2);
        }
    }

    private int a(byte[] var1, int var2, int var3) throws j {
        this.d(var3);
        return this.g.d(var1, var2, var3);
    }

    public void c(int var1) {
        this.e = var1;
        this.f = true;
    }

    protected void d(int var1) throws j {
        if(var1 < 0) {
            throw new i("Negative length: " + var1);
        } else {
            if(this.f) {
                this.e -= var1;
                if(this.e < 0) {
                    throw new i("Message length exceeded: " + var1);
                }
            }

        }
    }

    public static class a_inner implements a.a.a.b.j {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a_inner() {
            this(false, true);
        }

        public a_inner(boolean var1, boolean var2) {
            this(var1, var2, 0);
        }

        public a_inner(boolean var1, boolean var2, int var3) {
            this.a = false;
            this.b = true;
            this.a = var1;
            this.b = var2;
            this.c = var3;
        }

        public h a(c var1) {
            a var2 = new a(var1, this.a, this.b);
            if(this.c != 0) {
                var2.c(this.c);
            }

            return var2;
        }
    }
}
