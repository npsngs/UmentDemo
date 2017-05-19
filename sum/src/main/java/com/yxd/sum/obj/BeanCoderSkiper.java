//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.obj;

import a.a.a.UMException;

import com.yxd.sum.coder.BeanCoder;
import com.yxd.sum.coder.NBeanCoder.NBeanCoderBuilder;

public class BeanCoderSkiper {
    private static int skip = Integer.MAX_VALUE;

    public BeanCoderSkiper() {
    }

    public static void setSkip(int skip) {
        BeanCoderSkiper.skip = skip;
    }

    public static void read(BeanCoder beanCoder, byte fieldType) throws UMException {
        read(beanCoder, fieldType, skip);
    }

    public static void read(BeanCoder beanCoder, byte fieldType, int skip) throws UMException {
        if(skip <= 0) {
            throw new UMException("Maximum skip depth exceeded");
        } else {
            switch(fieldType) {
                case 2:
                    beanCoder.readBoolean();
                    break;
                case 3:
                    beanCoder.readByte();
                    break;
                case 4:
                    beanCoder.readDouble();
                case 5:
                case 7:
                case 9:
                default:
                    break;
                case 6:
                    beanCoder.readSignedShort();
                    break;
                case 8:
                    beanCoder.readSignedInt();
                    break;
                case 10:
                    beanCoder.readSignedLong();
                    break;
                case 11:
                    beanCoder.readByteBuffer();
                    break;
                case 12:
                    beanCoder.startUnpack();

                    while(true) {
                        TField tField = beanCoder.readTField();
                        if(tField.type == 0) {
                            beanCoder.popStack();
                            return;
                        }

                        read(beanCoder, tField.type, skip - 1);
                        beanCoder.endReadObj();
                    }
                case 13:
                    MapHeader var3 = beanCoder.readMapHeader();

                    for(int var8 = 0; var8 < var3.size; ++var8) {
                        read(beanCoder, var3.keyType, skip - 1);
                        read(beanCoder, var3.valueType, skip - 1);
                    }

                    beanCoder.o();
                    break;
                case 14:
                    ArrayHeader var4 = beanCoder.readArrayHeader();

                    for(int i = 0; i < var4.size; ++i) {
                        read(beanCoder, var4.type, skip - 1);
                    }

                    beanCoder.s();
                    break;
                case 15:
                    ListHeader var5 = beanCoder.readListHeader();

                    for(int i = 0; i < var5.size; ++i) {
                        read(beanCoder, var5.type, skip - 1);
                    }

                    beanCoder.q();
            }

        }
    }

    public static BeanCoderBuilder read(byte[] var0, BeanCoderBuilder coderBuilder) {
        return var0[0] > 16?new NBeanCoderBuilder():(var0.length > 1 && (var0[1] & 128) != 0?new NBeanCoderBuilder():coderBuilder);
    }
}
