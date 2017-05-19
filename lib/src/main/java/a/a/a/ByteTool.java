//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

public class ByteTool {
    public ByteTool() {
    }

    public static final void int2Bytes(int src, byte[] buffer) {
        int2Bytes(src, buffer, 0);
    }

    public static final void int2Bytes(int src, byte[] buffer, int offset) {
        buffer[offset] = (byte)(255 & src >> 24);
        buffer[offset + 1] = (byte)(255 & src >> 16);
        buffer[offset + 2] = (byte)(255 & src >> 8);
        buffer[offset + 3] = (byte)(255 & src);
    }

    public static final int bytes2Int(byte[] offset) {
        return bytes2Int(offset, 0);
    }

    public static final int bytes2Int(byte[] bytes, int offset) {
        return (bytes[offset] & 255) << 24 | (bytes[offset + 1] & 255) << 16 | (bytes[offset + 2] & 255) << 8 | bytes[offset + 3] & 255;
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
