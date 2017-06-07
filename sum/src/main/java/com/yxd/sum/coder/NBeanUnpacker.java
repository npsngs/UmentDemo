//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.coder;

import com.yxd.sum.bean.SerialBean;

import a.a.a.UMException;
import a.a.a.UMField;
import com.yxd.sum.TField;

import com.yxd.sum.BeanCoderSkiper;
import com.yxd.sum.obj.BeanCoderBuilder;
import com.yxd.sum.coder.NBeanCoder.NBeanCoderBuilder;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class NBeanUnpacker {
    private final BeanCoder umBeanCoder;
    private final BufferIOStream bis;

    public NBeanUnpacker() {
        this(new NBeanCoderBuilder());
    }

    public NBeanUnpacker(BeanCoderBuilder umBeanCoderBuilder) {
        this.bis = new BufferIOStream();
        this.umBeanCoder = umBeanCoderBuilder.build(this.bis);
    }

    public void unpack(SerialBean umBean, byte[] data) throws UMException {
        try {
            this.bis.setBuffer(data);
            umBean.unpackFrom(this.umBeanCoder);
        } finally {
            this.bis.clearBuffer();
            this.umBeanCoder.reset();
        }

    }

    public void unpack(SerialBean umBean, String str, String charsetName) throws UMException {
        try {
            this.unpack(umBean, str.getBytes(charsetName));
        } catch (UnsupportedEncodingException var8) {
            throw new UMException("JVM DOES NOT SUPPORT ENCODING: " + charsetName);
        } finally {
            this.umBeanCoder.reset();
        }

    }

    public void unpack(SerialBean umBean, byte[] bytes, UMField umField, UMField... umFields) throws UMException {
        try {
            if(this.j(bytes, umField, umFields) != null) {
                umBean.unpackFrom(this.umBeanCoder);
            }
        } catch (Exception e) {
            throw new UMException(e);
        } finally {
            this.bis.clearBuffer();
            this.umBeanCoder.reset();
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
            TField var4 = this.j(var1, var2, var3);
            if(var4 != null) {
                this.umBeanCoder.startUnpack();
                var5 = Short.valueOf(this.umBeanCoder.readTField().id);
                return var5;
            }

            var5 = null;
        } catch (Exception var9) {
            throw new UMException(var9);
        } finally {
            this.bis.clearBuffer();
            this.umBeanCoder.reset();
        }

        return var5;
    }

    private Object unpack(byte var1, byte[] var2, UMField var3, UMField... var4) throws UMException {
        try {
            TField var5 = this.j(var2, var3, var4);
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
            this.bis.clearBuffer();
            this.umBeanCoder.reset();
        }
    }

    private TField j(byte[] buffer, UMField umField, UMField... umFields) throws UMException {
        this.bis.setBuffer(buffer);
        UMField[] var4 = new UMField[umFields.length + 1];
        var4[0] = umField;

        int i;
        for(i = 0; i < umFields.length; ++i) {
            var4[i + 1] = umFields[i];
        }

        i = 0;
        TField var6 = null;
        this.umBeanCoder.startUnpack();

        while(i < var4.length) {
            var6 = this.umBeanCoder.readTField();
            if(var6.type == 0 || var6.id > var4[i].getFieldId()) {
                return null;
            }

            if(var6.id != var4[i].getFieldId()) {
                BeanCoderSkiper a = new BeanCoderSkiper();
                a.skip(this.umBeanCoder, var6.type);
                this.umBeanCoder.endReadObj();
            } else {
                ++i;
                if(i < var4.length) {
                    this.umBeanCoder.startUnpack();
                }
            }
        }

        return var6;
    }

    public void unpack(SerialBean var1, String var2) throws UMException {
        this.unpack(var1, var2.getBytes());
    }
}
