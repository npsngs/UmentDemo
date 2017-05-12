//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import com.umeng.analytics.f.b;
import com.umeng.analytics.f.c;
import com.umeng.analytics.f.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public abstract class a {
    private final int a = 10;
    private final int b = 20;
    private final String c;
    private List<b> d;
    private c e;

    public a(String var1) {
        this.c = var1;
    }

    public boolean a() {
        return this.g();
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.e == null || this.e.i() <= 20;
    }

    private boolean g() {
        c var1 = this.e;
        String var2 = var1 == null?null:var1.c();
        int var3 = var1 == null?0:var1.i();
        String var4 = this.a(this.f());
        if(var4 != null && !var4.equals(var2)) {
            if(var1 == null) {
                var1 = new c();
            }

            var1.a(var4);
            var1.a(System.currentTimeMillis());
            var1.a(var3 + 1);
            b var5 = new b();
            var5.b(this.c);
            var5.c(var4);
            var5.b(var2);
            var5.a(var1.f());
            if(this.d == null) {
                this.d = new ArrayList(2);
            }

            this.d.add(var5);
            if(this.d.size() > 10) {
                this.d.remove(0);
            }

            this.e = var1;
            return true;
        } else {
            return false;
        }
    }

    public c d() {
        return this.e;
    }

    public void a(c var1) {
        this.e = var1;
    }

    public List<b> e() {
        return this.d;
    }

    public void a(List<b> var1) {
        this.d = var1;
    }

    public String a(String var1) {
        if(var1 == null) {
            return null;
        } else {
            var1 = var1.trim();
            return var1.length() == 0?null:("0".equals(var1)?null:("unknown".equals(var1.toLowerCase(Locale.US))?null:var1));
        }
    }

    public abstract String f();

    public void a(d var1) {
        this.e = (c)var1.d().get(this.c);
        List var2 = var1.i();
        if(var2 != null && var2.size() > 0) {
            if(this.d == null) {
                this.d = new ArrayList();
            }

            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
                b var4 = (b)var3.next();
                if(this.c.equals(var4.a)) {
                    this.d.add(var4);
                }
            }
        }

    }
}
