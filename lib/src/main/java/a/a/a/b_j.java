//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

public class b_j {
    private short[] a;
    private int b = -1;

    public b_j(int var1) {
        this.a = new short[var1];
    }

    public short a() {
        return this.a[this.b--];
    }

    public void a(short var1) {
        if(this.a.length == this.b + 1) {
            this.d();
        }

        this.a[++this.b] = var1;
    }

    private void d() {
        short[] var1 = new short[this.a.length * 2];
        System.arraycopy(this.a, 0, var1, 0, this.a.length);
        this.a = var1;
    }

    public short b() {
        return this.a[this.b];
    }

    public void c() {
        this.b = -1;
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append("<ShortStack vector:[");

        for(int var2 = 0; var2 < this.a.length; ++var2) {
            if(var2 != 0) {
                var1.append(" ");
            }

            if(var2 == this.b) {
                var1.append(">>");
            }

            var1.append(this.a[var2]);
            if(var2 == this.b) {
                var1.append("<<");
            }
        }

        var1.append("]>");
        return var1.toString();
    }
}
