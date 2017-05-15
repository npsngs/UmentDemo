//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

import a.a.a.b.TField_c;
import a.a.a.b.UMBeanCoder;
import a.a.a.b.UMBeanCoderEngine;
import a.a.a.b.j;
import a.a.a.b.UMBeanCoder_b.a;
import a.a.a.d.b;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class g {
    private final UMBeanCoder a;
    private final b b;

    public g() {
        this(new a());
    }

    public g(j var1) {
        this.b = new b();
        this.a = var1.a(this.b);
    }

    public void a(UMBean var1, byte[] data) throws UMException {
        try {
            this.b.a(data);
            var1.unpackFrom(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }

    }

    public void a(UMBean var1, String var2, String var3) throws UMException {
        try {
            this.a(var1, var2.getBytes(var3));
        } catch (UnsupportedEncodingException var8) {
            throw new UMException("JVM DOES NOT SUPPORT ENCODING: " + var3);
        } finally {
            this.a.B();
        }

    }

    public void a(UMBean var1, byte[] var2, UMField var3, UMField... var4) throws UMException {
        try {
            if(this.j(var2, var3, var4) != null) {
                var1.unpackFrom(this.a);
            }
        } catch (Exception var9) {
            throw new UMException(var9);
        } finally {
            this.b.e();
            this.a.B();
        }

    }

    public Boolean a(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Boolean)this.a((byte)2, var1, var2, var3);
    }

    public Byte b(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Byte)this.a((byte)3, var1, var2, var3);
    }

    public Double c(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Double)this.a((byte)4, var1, var2, var3);
    }

    public Short d(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Short)this.a((byte)6, var1, var2, var3);
    }

    public Integer e(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Integer)this.a((byte)8, var1, var2, var3);
    }

    public Long f(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Long)this.a((byte)10, var1, var2, var3);
    }

    public String g(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (String)this.a((byte)11, var1, var2, var3);
    }

    public ByteBuffer h(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (ByteBuffer)this.a((byte)100, var1, var2, var3);
    }

    public Short i(byte[] var1, UMField var2, UMField... var3) throws UMException {
        Short var5;
        try {
            TField_c var4 = this.j(var1, var2, var3);
            if(var4 != null) {
                this.a.startUnpack();
                var5 = Short.valueOf(this.a.readTField().id);
                return var5;
            }

            var5 = null;
        } catch (Exception var9) {
            throw new UMException(var9);
        } finally {
            this.b.e();
            this.a.B();
        }

        return var5;
    }

    private Object a(byte var1, byte[] var2, UMField var3, UMField... var4) throws UMException {
        try {
            TField_c var5 = this.j(var2, var3, var4);
            ByteBuffer var6;
            if(var5 != null) {
                switch(var1) {
                    case 2:
                        if(var5.type == 2) {
                            Boolean var18 = Boolean.valueOf(this.a.readBoolean());
                            return var18;
                        }
                        break;
                    case 3:
                        if(var5.type == 3) {
                            Byte var17 = Byte.valueOf(this.a.readByte());
                            return var17;
                        }
                        break;
                    case 4:
                        if(var5.type == 4) {
                            Double var16 = Double.valueOf(this.a.readDouble());
                            return var16;
                        }
                        break;
                    case 6:
                        if(var5.type == 6) {
                            Short var15 = Short.valueOf(this.a.readSignedShort());
                            return var15;
                        }
                        break;
                    case 8:
                        if(var5.type == 8) {
                            Integer var14 = Integer.valueOf(this.a.readSignedInt());
                            return var14;
                        }
                        break;
                    case 10:
                        if(var5.type == 10) {
                            Long var13 = Long.valueOf(this.a.readSignedLong());
                            return var13;
                        }
                        break;
                    case 11:
                        if(var5.type == 11) {
                            String var12 = this.a.readString();
                            return var12;
                        }
                        break;
                    case 100:
                        if(var5.type == 11) {
                            var6 = this.a.readByteBuffer();
                            return var6;
                        }
                }
            }

            var6 = null;
            return var6;
        } catch (Exception var10) {
            throw new UMException(var10);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    private TField_c j(byte[] var1, UMField var2, UMField... var3) throws UMException {
        this.b.a(var1);
        UMField[] var4 = new UMField[var3.length + 1];
        var4[0] = var2;

        int var5;
        for(var5 = 0; var5 < var3.length; ++var5) {
            var4[var5 + 1] = var3[var5];
        }

        var5 = 0;
        TField_c var6 = null;
        this.a.startUnpack();

        while(var5 < var4.length) {
            var6 = this.a.readTField();
            if(var6.type == 0 || var6.id > var4[var5].getFieldId()) {
                return null;
            }

            if(var6.id != var4[var5].getFieldId()) {
                UMBeanCoderEngine a = new UMBeanCoderEngine();
                a.read(this.a, var6.type);
                this.a.m();
            } else {
                ++var5;
                if(var5 < var4.length) {
                    this.a.startUnpack();
                }
            }
        }

        return var6;
    }

    public void a(UMBean var1, String var2) throws UMException {
        this.a(var1, var2.getBytes());
    }
}
