//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

public final class f {
    public final String a;
    public final byte b;
    public final int c;

    public f() {
        this("", (byte)0, 0);
    }

    public f(String var1, byte var2, int var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public String toString() {
        return "<TMessage name:\'" + this.a + "\' type: " + this.b + " seqid:" + this.c + ">";
    }

    public boolean equals(Object var1) {
        return var1 instanceof f?this.a((f)var1):false;
    }

    public boolean a(f var1) {
        return this.a.equals(var1.a) && this.b == var1.b && this.c == var1.c;
    }
}
