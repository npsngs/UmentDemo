//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

import a.a.a.b.c;
import a.a.a.b.h;
import a.a.a.b.j;
import a.a.a.b.b.a;
import a.a.a.d_interface.b;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class g {
    private final h a;
    private final b b;

    public g() {
        this(new a());
    }

    public g(j var1) {
        this.b = new b();
        this.a = var1.a(this.b);
    }

    public void a(d_interface var1, byte[] var2) throws a.a.a.j {
        try {
            this.b.a(var2);
            var1.a(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }

    }

    public void a(d_interface var1, String var2, String var3) throws a.a.a.j {
        try {
            this.a(var1, var2.getBytes(var3));
        } catch (UnsupportedEncodingException var8) {
            throw new a.a.a.j("JVM DOES NOT SUPPORT ENCODING: " + var3);
        } finally {
            this.a.B();
        }

    }

    public void a(d_interface var1, byte[] var2, k var3, k... var4) throws a.a.a.j {
        try {
            if(this.j(var2, var3, var4) != null) {
                var1.a(this.a);
            }
        } catch (Exception var9) {
            throw new a.a.a.j(var9);
        } finally {
            this.b.e();
            this.a.B();
        }

    }

    public Boolean a(byte[] var1, k var2, k... var3) throws a.a.a.j {
        return (Boolean)this.a(2, var1, var2, var3);
    }

    public Byte b(byte[] var1, k var2, k... var3) throws a.a.a.j {
        return (Byte)this.a(3, var1, var2, var3);
    }

    public Double c(byte[] var1, k var2, k... var3) throws a.a.a.j {
        return (Double)this.a(4, var1, var2, var3);
    }

    public Short d(byte[] var1, k var2, k... var3) throws a.a.a.j {
        return (Short)this.a(6, var1, var2, var3);
    }

    public Integer e(byte[] var1, k var2, k... var3) throws a.a.a.j {
        return (Integer)this.a(8, var1, var2, var3);
    }

    public Long f(byte[] var1, k var2, k... var3) throws a.a.a.j {
        return (Long)this.a(10, var1, var2, var3);
    }

    public String g(byte[] var1, k var2, k... var3) throws a.a.a.j {
        return (String)this.a(11, var1, var2, var3);
    }

    public ByteBuffer h(byte[] var1, k var2, k... var3) throws a.a.a.j {
        return (ByteBuffer)this.a(100, var1, var2, var3);
    }

    public Short i(byte[] var1, k var2, k... var3) throws a.a.a.j {
        Short var5;
        try {
            c var4 = this.j(var1, var2, var3);
            if(var4 != null) {
                this.a.j();
                var5 = Short.valueOf(this.a.l().c);
                return var5;
            }

            var5 = null;
        } catch (Exception var9) {
            throw new a.a.a.j(var9);
        } finally {
            this.b.e();
            this.a.B();
        }

        return var5;
    }

    private Object a(byte var1, byte[] var2, k var3, k... var4) throws a.a.a.j {
        try {
            c var5 = this.j(var2, var3, var4);
            ByteBuffer var6;
            if(var5 != null) {
                switch(var1) {
                    case 2:
                        if(var5.b == 2) {
                            Boolean var18 = Boolean.valueOf(this.a.t());
                            return var18;
                        }
                        break;
                    case 3:
                        if(var5.b == 3) {
                            Byte var17 = Byte.valueOf(this.a.u());
                            return var17;
                        }
                        break;
                    case 4:
                        if(var5.b == 4) {
                            Double var16 = Double.valueOf(this.a.y());
                            return var16;
                        }
                        break;
                    case 6:
                        if(var5.b == 6) {
                            Short var15 = Short.valueOf(this.a.v());
                            return var15;
                        }
                        break;
                    case 8:
                        if(var5.b == 8) {
                            Integer var14 = Integer.valueOf(this.a.w());
                            return var14;
                        }
                        break;
                    case 10:
                        if(var5.b == 10) {
                            Long var13 = Long.valueOf(this.a.x());
                            return var13;
                        }
                        break;
                    case 11:
                        if(var5.b == 11) {
                            String var12 = this.a.z();
                            return var12;
                        }
                        break;
                    case 100:
                        if(var5.b == 11) {
                            var6 = this.a.A();
                            return var6;
                        }
                }
            }

            var6 = null;
            return var6;
        } catch (Exception var10) {
            throw new a.a.a.j(var10);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    private c j(byte[] var1, k var2, k... var3) throws a.a.a.j {
        this.b.a(var1);
        k[] var4 = new k[var3.length + 1];
        var4[0] = var2;

        int var5;
        for(var5 = 0; var5 < var3.length; ++var5) {
            var4[var5 + 1] = var3[var5];
        }

        var5 = 0;
        c var6 = null;
        this.a.j();

        while(var5 < var4.length) {
            var6 = this.a.l();
            if(var6.b == 0 || var6.c > var4[var5].a()) {
                return null;
            }

            if(var6.c != var4[var5].a()) {
                a.a.a.b.k.a(this.a, var6.b);
                this.a.m();
            } else {
                ++var5;
                if(var5 < var4.length) {
                    this.a.j();
                }
            }
        }

        return var6;
    }

    public void a(d_interface var1, String var2) throws a.a.a.j {
        this.a(var1, var2.getBytes());
    }
}
