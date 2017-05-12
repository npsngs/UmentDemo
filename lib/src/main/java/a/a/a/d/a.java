//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.d;

import a.a.a.d.c;
import a.a.a.d.d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class a extends c {
    protected InputStream a = null;
    protected OutputStream b = null;

    protected a() {
    }

    public a(InputStream var1) {
        this.a = var1;
    }

    public a(OutputStream var1) {
        this.b = var1;
    }

    public a(InputStream var1, OutputStream var2) {
        this.a = var1;
        this.b = var2;
    }

    public boolean a() {
        return true;
    }

    public void b() throws d {
    }

    public void c() {
        if(this.a != null) {
            try {
                this.a.close();
            } catch (IOException var3) {
                var3.printStackTrace();
            }

            this.a = null;
        }

        if(this.b != null) {
            try {
                this.b.close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }

            this.b = null;
        }

    }

    public int a(byte[] var1, int var2, int var3) throws d {
        if(this.a == null) {
            throw new d(1, "Cannot read from null inputStream");
        } else {
            int var4;
            try {
                var4 = this.a.read(var1, var2, var3);
            } catch (IOException var6) {
                throw new d(0, var6);
            }

            if(var4 < 0) {
                throw new d(4);
            } else {
                return var4;
            }
        }
    }

    public void b(byte[] var1, int var2, int var3) throws d {
        if(this.b == null) {
            throw new d(1, "Cannot write to null outputStream");
        } else {
            try {
                this.b.write(var1, var2, var3);
            } catch (IOException var5) {
                throw new d(0, var5);
            }
        }
    }

    public void d() throws d {
        if(this.b == null) {
            throw new d(1, "Cannot flush null outputStream");
        } else {
            try {
                this.b.flush();
            } catch (IOException var2) {
                throw new d(0, var2);
            }
        }
    }
}
