//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.d;

public final class b extends IOStream {
    private byte[] a;
    private int b;
    private int c;

    public b() {
    }

    public b(byte[] var1) {
        this.a(var1);
    }

    public b(byte[] var1, int var2, int var3) {
        this.c(var1, var2, var3);
    }

    public void a(byte[] var1) {
        this.c(var1, 0, var1.length);
    }

    public void c(byte[] var1, int var2, int var3) {
        this.a = var1;
        this.b = var2;
        this.c = var2 + var3;
    }

    public void e() {
        this.a = null;
    }

    public void close() {
    }

    public boolean a() {
        return true;
    }

    public void b() throws UMErrCodeException {
    }

    public int read(byte[] var1, int var2, int var3) throws UMErrCodeException {
        int var4 = this.getBufferSize();
        int var5 = var3 > var4?var4:var3;
        if(var5 > 0) {
            System.arraycopy(this.a, this.b, var1, var2, var5);
            this.offset(var5);
        }

        return var5;
    }

    public void write(byte[] var1, int var2, int var3) throws UMErrCodeException {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public byte[] getBuffer() {
        return this.a;
    }

    public int getBufferOffset() {
        return this.b;
    }

    public int getBufferSize() {
        return this.c - this.b;
    }

    public void offset(int var1) {
        this.b += var1;
    }
}
