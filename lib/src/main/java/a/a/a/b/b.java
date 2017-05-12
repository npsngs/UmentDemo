//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

import a.a.a.j;
import a.a.a.b_j;
import a.a.a.b.c;
import a.a.a.b.d;
import a.a.a.b.e;
import a.a.a.b.f;
import a.a.a.b.h;
import a.a.a.b.i;
import a.a.a.b.l;
import a.a.a.b.m;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class b extends h {
    private static final m d = new m("");
    private static final c e = new c("", (byte) 0, (short) 0);
    private static final byte[] f = new byte[16];
    private static final byte h = -126;
    private static final byte i = 1;
    private static final byte j = 31;
    private static final byte k = -32;
    private static final int l = 5;
    private b_j m;
    private short n;
    private c o;
    private Boolean p;
    private final long q;
    byte[] a;
    byte[] b;
    private byte[] r;
    byte[] c;

    public b(a.a.a.d.c var1, long var2) {
        super(var1);
        this.m = new b_j(15);
        this.n = 0;
        this.o = null;
        this.p = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.r = new byte[1];
        this.c = new byte[1];
        this.q = var2;
    }

    public b(a.a.a.d.c var1) {
        this(var1, -1L);
    }

    public void B() {
        this.m.c();
        this.n = 0;
    }

    public void a(f var1) throws j {
        this.b(-126);
        this.d(1 | var1.b << 5 & -32);
        this.b(var1.c);
        this.a(var1.a);
    }

    public void a(m var1) throws j {
        this.m.a(this.n);
        this.n = 0;
    }

    public void b() throws j {
        this.n = this.m.a();
    }

    public void a(c var1) throws j {
        if(var1.b == 2) {
            this.o = var1;
        } else {
            this.a(var1, (byte)-1);
        }

    }

    private void a(c var1, byte var2) throws j {
        byte var3 = var2 == -1?this.e(var1.b):var2;
        if(var1.c > this.n && var1.c - this.n <= 15) {
            this.d(var1.c - this.n << 4 | var3);
        } else {
            this.b(var3);
            this.a(var1.c);
        }

        this.n = var1.c;
    }

    public void d() throws j {
        this.b((byte)0);
    }

    public void a(e var1) throws j {
        if(var1.c == 0) {
            this.d((int)0);
        } else {
            this.b(var1.c);
            this.d(this.e(var1.a) << 4 | this.e(var1.b));
        }

    }

    public void a(d var1) throws j {
        this.a(var1.a, var1.b);
    }

    public void a(l var1) throws j {
        this.a(var1.a, var1.b);
    }

    public void a(boolean var1) throws j {
        if(this.o != null) {
            this.a(this.o, (byte)(var1?1:2));
            this.o = null;
        } else {
            this.b((byte)(var1?1:2));
        }

    }

    public void a(byte var1) throws j {
        this.b(var1);
    }

    public void a(short var1) throws j {
        this.b(this.c((int)var1));
    }

    public void a(int var1) throws j {
        this.b(this.c(var1));
    }

    public void a(long var1) throws j {
        this.b(this.c(var1));
    }

    public void a(double var1) throws j {
        byte[] var3 = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
        this.a(Double.doubleToLongBits(var1), var3, 0);
        this.g.b(var3);
    }

    public void a(String var1) throws j {
        try {
            byte[] var2 = var1.getBytes("UTF-8");
            this.a(var2, 0, var2.length);
        } catch (UnsupportedEncodingException var3) {
            throw new j("UTF-8 not supported!");
        }
    }

    public void a(ByteBuffer var1) throws j {
        int var2 = var1.limit() - var1.position();
        this.a(var1.array(), var1.position() + var1.arrayOffset(), var2);
    }

    private void a(byte[] var1, int var2, int var3) throws j {
        this.b(var3);
        this.g.b(var1, var2, var3);
    }

    public void a() throws j {
    }

    public void e() throws j {
    }

    public void f() throws j {
    }

    public void g() throws j {
    }

    public void c() throws j {
    }

    protected void a(byte var1, int var2) throws j {
        if(var2 <= 14) {
            this.d(var2 << 4 | this.e(var1));
        } else {
            this.d(240 | this.e(var1));
            this.b(var2);
        }

    }

    private void b(int var1) throws j {
        int var2;
        for(var2 = 0; (var1 & -128) != 0; var1 >>>= 7) {
            this.a[var2++] = (byte)(var1 & 127 | 128);
        }

        this.a[var2++] = (byte)var1;
        this.g.b(this.a, 0, var2);
    }

    private void b(long var1) throws j {
        int var3;
        for(var3 = 0; (var1 & -128L) != 0L; var1 >>>= 7) {
            this.b[var3++] = (byte)((int)(var1 & 127L | 128L));
        }

        this.b[var3++] = (byte)((int)var1);
        this.g.b(this.b, 0, var3);
    }

    private long c(long var1) {
        return var1 << 1 ^ var1 >> 63;
    }

    private int c(int var1) {
        return var1 << 1 ^ var1 >> 31;
    }

    private void a(long var1, byte[] var3, int var4) {
        var3[var4 + 0] = (byte)((int)(var1 & 255L));
        var3[var4 + 1] = (byte)((int)(var1 >> 8 & 255L));
        var3[var4 + 2] = (byte)((int)(var1 >> 16 & 255L));
        var3[var4 + 3] = (byte)((int)(var1 >> 24 & 255L));
        var3[var4 + 4] = (byte)((int)(var1 >> 32 & 255L));
        var3[var4 + 5] = (byte)((int)(var1 >> 40 & 255L));
        var3[var4 + 6] = (byte)((int)(var1 >> 48 & 255L));
        var3[var4 + 7] = (byte)((int)(var1 >> 56 & 255L));
    }

    private void b(byte var1) throws j {
        this.r[0] = var1;
        this.g.b(this.r);
    }

    private void d(int var1) throws j {
        this.b((byte)var1);
    }

    public f h() throws j {
        byte var1 = this.u();
        if(var1 != -126) {
            throw new i("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(var1));
        } else {
            byte var2 = this.u();
            byte var3 = (byte)(var2 & 31);
            if(var3 != 1) {
                throw new i("Expected version 1 but got " + var3);
            } else {
                byte var4 = (byte)(var2 >> 5 & 3);
                int var5 = this.E();
                String var6 = this.z();
                return new f(var6, var4, var5);
            }
        }
    }

    public m j() throws j {
        this.m.a(this.n);
        this.n = 0;
        return d;
    }

    public void k() throws j {
        this.n = this.m.a();
    }

    public c l() throws j {
        byte var1 = this.u();
        if(var1 == 0) {
            return e;
        } else {
            short var3 = (short)((var1 & 240) >> 4);
            short var2;
            if(var3 == 0) {
                var2 = this.v();
            } else {
                var2 = (short)(this.n + var3);
            }

            c var4 = new c("", this.d((byte)(var1 & 15)), var2);
            if(this.c(var1)) {
                this.p = (byte)(var1 & 15) == 1?Boolean.TRUE:Boolean.FALSE;
            }

            this.n = var4.c;
            return var4;
        }
    }

    public e n() throws j {
        int var1 = this.E();
        byte var2 = var1 == 0?0:this.u();
        return new e(this.d((byte)(var2 >> 4)), this.d((byte)(var2 & 15)), var1);
    }

    public d p() throws j {
        byte var1 = this.u();
        int var2 = var1 >> 4 & 15;
        if(var2 == 15) {
            var2 = this.E();
        }

        byte var3 = this.d(var1);
        return new d(var3, var2);
    }

    public l r() throws j {
        return new l(this.p());
    }

    public boolean t() throws j {
        if(this.p != null) {
            boolean var1 = this.p.booleanValue();
            this.p = null;
            return var1;
        } else {
            return this.u() == 1;
        }
    }

    public byte u() throws j {
        byte var1;
        if(this.g.h() > 0) {
            var1 = this.g.f()[this.g.g()];
            this.g.a(1);
        } else {
            this.g.d(this.c, 0, 1);
            var1 = this.c[0];
        }

        return var1;
    }

    public short v() throws j {
        return (short)this.g(this.E());
    }

    public int w() throws j {
        return this.g(this.E());
    }

    public long x() throws j {
        return this.d(this.F());
    }

    public double y() throws j {
        byte[] var1 = new byte[8];
        this.g.d(var1, 0, 8);
        return Double.longBitsToDouble(this.a(var1));
    }

    public String z() throws j {
        int var1 = this.E();
        this.f(var1);
        if(var1 == 0) {
            return "";
        } else {
            try {
                if(this.g.h() >= var1) {
                    String var2 = new String(this.g.f(), this.g.g(), var1, "UTF-8");
                    this.g.a(var1);
                    return var2;
                } else {
                    return new String(this.e(var1), "UTF-8");
                }
            } catch (UnsupportedEncodingException var3) {
                throw new j("UTF-8 not supported!");
            }
        }
    }

    public ByteBuffer A() throws j {
        int var1 = this.E();
        this.f(var1);
        if(var1 == 0) {
            return ByteBuffer.wrap(new byte[0]);
        } else {
            byte[] var2 = new byte[var1];
            this.g.d(var2, 0, var1);
            return ByteBuffer.wrap(var2);
        }
    }

    private byte[] e(int var1) throws j {
        if(var1 == 0) {
            return new byte[0];
        } else {
            byte[] var2 = new byte[var1];
            this.g.d(var2, 0, var1);
            return var2;
        }
    }

    private void f(int var1) throws i {
        if(var1 < 0) {
            throw new i("Negative length: " + var1);
        } else if(this.q != -1L && (long)var1 > this.q) {
            throw new i("Length exceeded max allowed: " + var1);
        }
    }

    public void i() throws j {
    }

    public void m() throws j {
    }

    public void o() throws j {
    }

    public void q() throws j {
    }

    public void s() throws j {
    }

    private int E() throws j {
        int var1 = 0;
        int var2 = 0;
        if(this.g.h() >= 5) {
            byte[] var7 = this.g.f();
            int var4 = this.g.g();
            int var5 = 0;

            while(true) {
                byte var6 = var7[var4 + var5];
                var1 |= (var6 & 127) << var2;
                if((var6 & 128) != 128) {
                    this.g.a(var5 + 1);
                    break;
                }

                var2 += 7;
                ++var5;
            }
        } else {
            while(true) {
                byte var3 = this.u();
                var1 |= (var3 & 127) << var2;
                if((var3 & 128) != 128) {
                    break;
                }

                var2 += 7;
            }
        }

        return var1;
    }

    private long F() throws j {
        int var1 = 0;
        long var2 = 0L;
        if(this.g.h() >= 10) {
            byte[] var8 = this.g.f();
            int var5 = this.g.g();
            int var6 = 0;

            while(true) {
                byte var7 = var8[var5 + var6];
                var2 |= (long)(var7 & 127) << var1;
                if((var7 & 128) != 128) {
                    this.g.a(var6 + 1);
                    break;
                }

                var1 += 7;
                ++var6;
            }
        } else {
            while(true) {
                byte var4 = this.u();
                var2 |= (long)(var4 & 127) << var1;
                if((var4 & 128) != 128) {
                    break;
                }

                var1 += 7;
            }
        }

        return var2;
    }

    private int g(int var1) {
        return var1 >>> 1 ^ -(var1 & 1);
    }

    private long d(long var1) {
        return var1 >>> 1 ^ -(var1 & 1L);
    }

    private long a(byte[] var1) {
        return ((long)var1[7] & 255L) << 56 | ((long)var1[6] & 255L) << 48 | ((long)var1[5] & 255L) << 40 | ((long)var1[4] & 255L) << 32 | ((long)var1[3] & 255L) << 24 | ((long)var1[2] & 255L) << 16 | ((long)var1[1] & 255L) << 8 | (long)var1[0] & 255L;
    }

    private boolean c(byte var1) {
        int var2 = var1 & 15;
        return var2 == 1 || var2 == 2;
    }

    private byte d(byte var1) throws i {
        switch((byte)(var1 & 15)) {
            case 0:
                return 0;
            case 1:
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 6;
            case 5:
                return 8;
            case 6:
                return 10;
            case 7:
                return 4;
            case 8:
                return 11;
            case 9:
                return 15;
            case 10:
                return 14;
            case 11:
                return 13;
            case 12:
                return 12;
            default:
                throw new i("don\'getPackageName know what type: " + (byte)(var1 & 15));
        }
    }

    private byte e(byte var1) {
        return f[var1];
    }

    static {
        f[0] = 0;
        f[2] = 1;
        f[3] = 3;
        f[6] = 4;
        f[8] = 5;
        f[10] = 6;
        f[4] = 7;
        f[11] = 8;
        f[15] = 9;
        f[14] = 10;
        f[13] = 11;
        f[12] = 12;
    }

    private static class b_inner {
        public static final byte a = 1;
        public static final byte b = 2;
        public static final byte c = 3;
        public static final byte d = 4;
        public static final byte e = 5;
        public static final byte f = 6;
        public static final byte g = 7;
        public static final byte h = 8;
        public static final byte i = 9;
        public static final byte j = 10;
        public static final byte k = 11;
        public static final byte l = 12;

        private b_inner() {
        }
    }

    public static class a implements a.a.a.b.j {
        private final long a;

        public a() {
            this.a = -1L;
        }

        public a(int var1) {
            this.a = (long)var1;
        }

        public h a(a.a.a.d.c var1) {
            return new b(var1, this.a);
        }
    }
}
