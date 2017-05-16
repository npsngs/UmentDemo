//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

import a.a.a.UMException;
import a.a.a.c.UMBeanTransfer;
import a.a.a.c.UMBeanTransfer_d;
import a.a.a.d.IOStream;
import java.util.BitSet;

public final class UMBeanCoder_n extends UMBeanCoder_b {
    public UMBeanCoder_n(IOStream var1) {
        super(var1);
    }

    public Class<? extends UMBeanTransfer> getBeanTransferClass() {
        return UMBeanTransfer_d.class;
    }

    public void a(BitSet var1, int var2) throws UMException {
        byte[] var3 = b(var1, var2);
        byte[] var4 = var3;
        int var5 = var3.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            byte var7 = var4[var6];
            this.writeByte(var7);
        }

    }

    public BitSet b(int var1) throws UMException {
        int var2 = (int)Math.ceil((double)var1 / 8.0D);
        byte[] var3 = new byte[var2];

        for(int i = 0; i < var2; ++i) {
            var3[i] = this.readByte();
        }

        BitSet var5 = byte2BitSet(var3);
        return var5;
    }

    public static BitSet byte2BitSet(byte[] data) {
        BitSet bitSet = new BitSet();

        for(int i = 0; i < data.length * 8; ++i) {
            if((data[data.length - i / 8 - 1] & 1 << i % 8) > 0) {
                bitSet.set(i);
            }
        }

        return bitSet;
    }

    public static byte[] b(BitSet var0, int var1) {
        byte[] var2 = new byte[(int)Math.ceil((double)var1 / 8.0D)];

        for(int var3 = 0; var3 < var0.length(); ++var3) {
            if(var0.get(var3)) {
                var2[var2.length - var3 / 8 - 1] = (byte)(var2[var2.length - var3 / 8 - 1] | 1 << var3 % 8);
            }
        }

        return var2;
    }

    public static class UMBeanCoder_n_Inner implements UMBeanCoderBuilder {
        public UMBeanCoder_n_Inner() {
        }

        public UMBeanCoder build(IOStream var1) {
            return new UMBeanCoder_n(var1);
        }
    }
}
