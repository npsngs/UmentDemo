//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

import a.a.a.b.h;
import a.a.a.b.j;
import a.a.a.d_interface.a;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class m {
    private final ByteArrayOutputStream a;
    private final a_j b;
    private h c;

    public m() {
        this(new a_j.a.a.b.b.a());
    }

    public m(j var1) {
        this.a = new ByteArrayOutputStream();
        this.b = new a_j(this.a);
        this.c = var1.a(this.b);
    }

    public byte[] a(d_interface var1) throws a_j.a.a.j {
        this.a.reset();
        var1.b(this.c);
        return this.a.toByteArray();
    }

    public String a(d_interface var1, String var2) throws a_j.a.a.j {
        try {
            return new String(this.a(var1), var2);
        } catch (UnsupportedEncodingException var4) {
            throw new a_j.a.a.j("JVM DOES NOT SUPPORT ENCODING: " + var2);
        }
    }

    public String b(d_interface var1) throws a_j.a.a.j {
        return new String(this.a(var1));
    }
}
