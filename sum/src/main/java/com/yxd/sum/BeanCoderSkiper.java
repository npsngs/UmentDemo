package com.yxd.sum;

import a.a.a.UMException;
import com.yxd.sum.obj.ArrayHeader;
import com.yxd.sum.obj.ListHeader;
import com.yxd.sum.obj.MapHeader;

public class BeanCoderSkiper {
    private static int skip = Integer.MAX_VALUE;
    public BeanCoderSkiper() {
    }

    public static void skip(BeanCoder beanCoder, byte fieldType) throws UMException {
        skip(beanCoder, fieldType, skip);
    }

    public static void skip(BeanCoder beanCoder, byte fieldType, int skip) throws UMException {
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
                    while(true) {
                        TField tField = beanCoder.readTField();
                        if(tField.type == 0) {
                            return;
                        }
                        skip(beanCoder, tField.type, skip - 1);
                    }
                case 13:
                    MapHeader var3 = beanCoder.readMapHeader();

                    for(int var8 = 0; var8 < var3.size; ++var8) {
                        skip(beanCoder, var3.keyType, skip - 1);
                        skip(beanCoder, var3.valueType, skip - 1);
                    }
                    break;
                case 14:
                    ArrayHeader var4 = beanCoder.readArrayHeader();

                    for(int i = 0; i < var4.size; ++i) {
                        skip(beanCoder, var4.type, skip - 1);
                    }
                    break;
                case 15:
                    ListHeader var5 = beanCoder.readListHeader();

                    for(int i = 0; i < var5.size; ++i) {
                        skip(beanCoder, var5.type, skip - 1);
                    }
            }

        }
    }
}
