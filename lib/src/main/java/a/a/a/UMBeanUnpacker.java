//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

import a.a.a.b.TField_c;
import a.a.a.b.UMBeanCoder;
import a.a.a.b.UMBeanCoderEngine;
import a.a.a.b.UMBeanCoderBuilder;
import a.a.a.b.UMBeanCoder_b.UMBeanCoder_b_Inner;
import a.a.a.d.BufferIOStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class UMBeanUnpacker {
    private final UMBeanCoder umBeanCoder;
    private final BufferIOStream b;

    public UMBeanUnpacker() {
        this(new UMBeanCoder_b_Inner());
    }

    public UMBeanUnpacker(UMBeanCoderBuilder umBeanCoderBuilder) {
        this.b = new BufferIOStream();
        this.umBeanCoder = umBeanCoderBuilder.build(this.b);
    }

    public void unpack(UMBean umBean, byte[] data) throws UMException {
        try {
            this.b.setBuffer(data);
            umBean.unpackFrom(this.umBeanCoder);
        } finally {
            this.b.clearBuffer();
            this.umBeanCoder.B();
        }

    }

    public void unpack(UMBean umBean, String str, String charsetName) throws UMException {
        try {
            this.unpack(umBean, str.getBytes(charsetName));
        } catch (UnsupportedEncodingException var8) {
            throw new UMException("JVM DOES NOT SUPPORT ENCODING: " + charsetName);
        } finally {
            this.umBeanCoder.B();
        }

    }

    public void unpack(UMBean umBean, byte[] bytes, UMField umField, UMField... umFields) throws UMException {
        try {
            if(this.j(bytes, umField, umFields) != null) {
                umBean.unpackFrom(this.umBeanCoder);
            }
        } catch (Exception e) {
            throw new UMException(e);
        } finally {
            this.b.clearBuffer();
            this.umBeanCoder.B();
        }

    }

    public Boolean unpack(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Boolean)this.unpack((byte)2, var1, var2, var3);
    }

    public Byte b(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Byte)this.unpack((byte)3, var1, var2, var3);
    }

    public Double c(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Double)this.unpack((byte)4, var1, var2, var3);
    }

    public Short d(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Short)this.unpack((byte)6, var1, var2, var3);
    }

    public Integer e(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Integer)this.unpack((byte)8, var1, var2, var3);
    }

    public Long f(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (Long)this.unpack((byte)10, var1, var2, var3);
    }

    public String g(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (String)this.unpack((byte)11, var1, var2, var3);
    }

    public ByteBuffer h(byte[] var1, UMField var2, UMField... var3) throws UMException {
        return (ByteBuffer)this.unpack((byte)100, var1, var2, var3);
    }

    public Short i(byte[] var1, UMField var2, UMField... var3) throws UMException {
        Short var5;
        try {
            TField_c var4 = this.j(var1, var2, var3);
            if(var4 != null) {
                this.umBeanCoder.startUnpack();
                var5 = Short.valueOf(this.umBeanCoder.readTField().id);
                return var5;
            }

            var5 = null;
        } catch (Exception var9) {
            throw new UMException(var9);
        } finally {
            this.b.clearBuffer();
            this.umBeanCoder.B();
        }

        return var5;
    }

    private Object unpack(byte var1, byte[] var2, UMField var3, UMField... var4) throws UMException {
        try {
            TField_c var5 = this.j(var2, var3, var4);
            ByteBuffer var6;
            if(var5 != null) {
                switch(var1) {
                    case 2:
                        if(var5.type == 2) {
                            Boolean var18 = Boolean.valueOf(this.umBeanCoder.readBoolean());
                            return var18;
                        }
                        break;
                    case 3:
                        if(var5.type == 3) {
                            Byte var17 = Byte.valueOf(this.umBeanCoder.readByte());
                            return var17;
                        }
                        break;
                    case 4:
                        if(var5.type == 4) {
                            Double var16 = Double.valueOf(this.umBeanCoder.readDouble());
                            return var16;
                        }
                        break;
                    case 6:
                        if(var5.type == 6) {
                            Short var15 = Short.valueOf(this.umBeanCoder.readSignedShort());
                            return var15;
                        }
                        break;
                    case 8:
                        if(var5.type == 8) {
                            Integer var14 = Integer.valueOf(this.umBeanCoder.readSignedInt());
                            return var14;
                        }
                        break;
                    case 10:
                        if(var5.type == 10) {
                            Long var13 = Long.valueOf(this.umBeanCoder.readSignedLong());
                            return var13;
                        }
                        break;
                    case 11:
                        if(var5.type == 11) {
                            String var12 = this.umBeanCoder.readString();
                            return var12;
                        }
                        break;
                    case 100:
                        if(var5.type == 11) {
                            var6 = this.umBeanCoder.readByteBuffer();
                            return var6;
                        }
                }
            }

            var6 = null;
            return var6;
        } catch (Exception var10) {
            throw new UMException(var10);
        } finally {
            this.b.clearBuffer();
            this.umBeanCoder.B();
        }
    }

    private TField_c j(byte[] var1, UMField var2, UMField... var3) throws UMException {
        this.b.setBuffer(var1);
        UMField[] var4 = new UMField[var3.length + 1];
        var4[0] = var2;

        int var5;
        for(var5 = 0; var5 < var3.length; ++var5) {
            var4[var5 + 1] = var3[var5];
        }

        var5 = 0;
        TField_c var6 = null;
        this.umBeanCoder.startUnpack();

        while(var5 < var4.length) {
            var6 = this.umBeanCoder.readTField();
            if(var6.type == 0 || var6.id > var4[var5].getFieldId()) {
                return null;
            }

            if(var6.id != var4[var5].getFieldId()) {
                UMBeanCoderEngine a = new UMBeanCoderEngine();
                a.read(this.umBeanCoder, var6.type);
                this.umBeanCoder.m();
            } else {
                ++var5;
                if(var5 < var4.length) {
                    this.umBeanCoder.startUnpack();
                }
            }
        }

        return var6;
    }

    public void unpack(UMBean var1, String var2) throws UMException {
        this.unpack(var1, var2.getBytes());
    }
}
