//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

import a.a.a.UMException;
import a.a.a.b.UMBeanCoder_b.UMBeanCoder_b_Inner;

public class UMBeanCoderEngine {
    private static int skip = Integer.MAX_VALUE;

    public UMBeanCoderEngine() {
    }

    public static void setSkip(int skip) {
        UMBeanCoderEngine.skip = skip;
    }

    public static void read(UMBeanCoder beanCoder, byte fieldType) throws UMException {
        read(beanCoder, fieldType, skip);
    }

    public static void read(UMBeanCoder beanCoder, byte fieldType, int skip) throws UMException {
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
                        TField_c tField = beanCoder.readTField();
                        if(tField.type == 0) {
                            beanCoder.k();
                            return;
                        }

                        read(beanCoder, tField.type, skip - 1);
                        beanCoder.m();
                    }
                case 13:
                    E_e var3 = beanCoder.n();

                    for(int var8 = 0; var8 < var3.size; ++var8) {
                        read(beanCoder, var3.a, skip - 1);
                        read(beanCoder, var3.b, skip - 1);
                    }

                    beanCoder.o();
                    break;
                case 14:
                    l var4 = beanCoder.r();

                    for(int i = 0; i < var4.b; ++i) {
                        read(beanCoder, var4.a, skip - 1);
                    }

                    beanCoder.s();
                    break;
                case 15:
                    D_d var5 = beanCoder.p();

                    for(int i = 0; i < var5.b; ++i) {
                        read(beanCoder, var5.a, skip - 1);
                    }

                    beanCoder.q();
            }

        }
    }

    public static UMBeanCoderBuilder read(byte[] var0, UMBeanCoderBuilder coderBuilder) {
        return var0[0] > 16?new UMBeanCoder_b_Inner():(var0.length > 1 && (var0[1] & 128) != 0?new UMBeanCoder_b_Inner():coderBuilder);
    }
}
