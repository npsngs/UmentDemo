//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

public class a_j {
    public a_j() {
    }

    public static final void a(int var0, byte[] var1) {
        a(var0, var1, 0);
    }

    public static final void a(int var0, byte[] var1, int var2) {
        var1[var2] = (byte)(255 & var0 >> 24);
        var1[var2 + 1] = (byte)(255 & var0 >> 16);
        var1[var2 + 2] = (byte)(255 & var0 >> 8);
        var1[var2 + 3] = (byte)(255 & var0);
    }

    public static final int a(byte[] var0) {
        return a(var0, 0);
    }

    public static final int a(byte[] var0, int var1) {
        return (var0[var1] & 255) << 24 | (var0[var1 + 1] & 255) << 16 | (var0[var1 + 2] & 255) << 8 | var0[var1 + 3] & 255;
    }

    public static final boolean a(byte var0, int var1) {
        return a((int)var0, var1);
    }

    public static final boolean a(short var0, int var1) {
        return a((int)var0, var1);
    }

    public static final boolean a(int var0, int var1) {
        return (var0 & 1 << var1) != 0;
    }

    public static final boolean a(long var0, int var2) {
        return (var0 & 1L << var2) != 0L;
    }

    public static final byte b(byte var0, int var1) {
        return (byte)b((int)var0, var1);
    }

    public static final short b(short var0, int var1) {
        return (short)b((int)var0, var1);
    }

    public static final int b(int var0, int var1) {
        return var0 & ~(1 << var1);
    }

    public static final long b(long var0, int var2) {
        return var0 & ~(1L << var2);
    }

    public static final byte a(byte var0, int var1, boolean var2) {
        return (byte)a((int)var0, var1, var2);
    }

    public static final short a(short var0, int var1, boolean var2) {
        return (short)a((int)var0, var1, var2);
    }

    public static final int a(int var0, int var1, boolean var2) {
        return var2?var0 | 1 << var1:b(var0, var1);
    }

    public static final long a(long var0, int var2, boolean var3) {
        return var3?var0 | 1L << var2:b(var0, var2);
    }
}
