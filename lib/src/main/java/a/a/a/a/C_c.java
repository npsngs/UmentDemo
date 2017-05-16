//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.a;

import java.io.Serializable;

public class C_c implements Serializable {
    public final byte b;
    private final boolean a;
    private final String c;
    private final boolean d;

    public C_c(byte var1, boolean var2) {
        this.b = var1;
        this.a = false;
        this.c = null;
        this.d = var2;
    }

    public C_c(byte var1) {
        this(var1, false);
    }

    public C_c(byte var1, String var2) {
        this.b = var1;
        this.a = true;
        this.c = var2;
        this.d = false;
    }

    public boolean a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.b == 12;
    }

    public boolean d() {
        return this.b == 15 || this.b == 13 || this.b == 14;
    }

    public boolean e() {
        return this.d;
    }
}
