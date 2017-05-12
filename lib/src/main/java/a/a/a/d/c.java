//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.d;

import a.a.a.d.d;

public abstract class c {
    public c() {
    }

    public abstract boolean a();

    public boolean i() {
        return this.a();
    }

    public abstract void b() throws d;

    public abstract void c();

    public abstract int a(byte[] var1, int var2, int var3) throws d;

    public int d(byte[] var1, int var2, int var3) throws d {
        int var4 = 0;

        int var6;
        for(boolean var5 = false; var4 < var3; var4 += var6) {
            var6 = this.a(var1, var2 + var4, var3 - var4);
            if(var6 <= 0) {
                throw new d("Cannot read. Remote side has closed. Tried to read " + var3 + " bytes, but only got " + var4 + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
            }
        }

        return var4;
    }

    public void b(byte[] var1) throws d {
        this.b(var1, 0, var1.length);
    }

    public abstract void b(byte[] var1, int var2, int var3) throws d;

    public void d() throws d {
    }

    public byte[] f() {
        return null;
    }

    public int g() {
        return 0;
    }

    public int h() {
        return -1;
    }

    public void a(int var1) {
    }
}
