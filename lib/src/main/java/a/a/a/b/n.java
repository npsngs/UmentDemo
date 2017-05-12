//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

import a.a.a.j;
import a.a.a.c.d;
import a.a.a.d.c;
import java.util.BitSet;

public final class n extends b {
    public n(c var1) {
        super(var1);
    }

    public Class<? extends a.a.a.c.a> D() {
        return d.class;
    }

    public void a(BitSet var1, int var2) throws j {
        byte[] var3 = b(var1, var2);
        byte[] var4 = var3;
        int var5 = var3.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            byte var7 = var4[var6];
            this.a(var7);
        }

    }

    public BitSet b(int var1) throws j {
        int var2 = (int)Math.ceil((double)var1 / 8.0D);
        byte[] var3 = new byte[var2];

        for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4] = this.u();
        }

        BitSet var5 = a(var3);
        return var5;
    }

    public static BitSet a(byte[] var0) {
        BitSet var1 = new BitSet();

        for(int var2 = 0; var2 < var0.length * 8; ++var2) {
            if((var0[var0.length - var2 / 8 - 1] & 1 << var2 % 8) > 0) {
                var1.set(var2);
            }
        }

        return var1;
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

    public static class a implements a.a.a.b.j {
        public a() {
        }

        public h a(c var1) {
            return new n(var1);
        }
    }
}
