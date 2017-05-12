//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.d;

import a.a.a.d.c;
import a.a.a.d.d;

public final class b extends c {
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

    public void c() {
    }

    public boolean a() {
        return true;
    }

    public void b() throws d {
    }

    public int a(byte[] var1, int var2, int var3) throws d {
        int var4 = this.h();
        int var5 = var3 > var4?var4:var3;
        if(var5 > 0) {
            System.arraycopy(this.a, this.b, var1, var2, var5);
            this.a(var5);
        }

        return var5;
    }

    public void b(byte[] var1, int var2, int var3) throws d {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public byte[] f() {
        return this.a;
    }

    public int g() {
        return this.b;
    }

    public int h() {
        return this.c - this.b;
    }

    public void a(int var1) {
        this.b += var1;
    }
}
