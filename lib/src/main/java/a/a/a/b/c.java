//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

public class c {
    public final String a;
    public final byte b;
    public final short c;

    public c() {
        this("", (byte)0, (short)0);
    }

    public c(String var1, byte var2, short var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public String toString() {
        return "<TField name:\'" + this.a + "\' type:" + this.b + " field-id:" + this.c + ">";
    }

    public boolean a(c var1) {
        return this.b == var1.b && this.c == var1.c;
    }
}
