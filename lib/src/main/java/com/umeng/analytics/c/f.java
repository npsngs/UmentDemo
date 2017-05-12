//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import a.a.a.m;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.umeng.analytics.d.SP_Util;
import com.umeng.analytics.f.d;
import com.umeng.tool.EncodeUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class f {
    private final String b = "umeng_it.cache";
    private File file;
    private d d = null;
    private long e;
    private long f;
    private Set<com.umeng.analytics.c.a> g = new HashSet();
    private f.a h = null;
    public static f a;

    f(Context context) {
        this.file = new File(context.getFilesDir(), "umeng_it.cache");
        this.f = 86400000L;
        this.h = new f.a(context);
        this.h.b();
    }

    public static synchronized f a(Context var0) {
        if(a == null) {
            a = new f(var0);
            a.a((com.umeng.analytics.c.a)(new g(var0)));
            a.a((com.umeng.analytics.c.a)(new b(var0)));
            a.a((com.umeng.analytics.c.a)(new o(var0)));
            a.a((com.umeng.analytics.c.a)(new e(var0)));
            a.a((com.umeng.analytics.c.a)(new com.umeng.analytics.c.d(var0)));
            a.a((com.umeng.analytics.c.a)(new i(var0)));
            a.a((com.umeng.analytics.c.a)(new l()));
            a.a((com.umeng.analytics.c.a)(new p(var0)));
            n var1 = new n(var0);
            if(!TextUtils.isEmpty(var1.f())) {
                a.a((com.umeng.analytics.c.a)var1);
            }

            k var2 = new k(var0);
            if(var2.g()) {
                a.a((com.umeng.analytics.c.a)var2);
                a.a((com.umeng.analytics.c.a)(new j(var0)));
                var2.i();
            }

            a.e();
        }

        return a;
    }

    public boolean a(com.umeng.analytics.c.a var1) {
        return this.h.a(var1.b())?this.g.add(var1):false;
    }

    public void a(long var1) {
        this.f = var1;
    }

    public void a() {
        long var1 = System.currentTimeMillis();
        if(var1 - this.e >= this.f) {
            boolean var3 = false;
            Iterator var4 = this.g.iterator();

            while(var4.hasNext()) {
                com.umeng.analytics.c.a var5 = (com.umeng.analytics.c.a)var4.next();
                if(var5.c() && var5.a()) {
                    var3 = true;
                    if(!var5.c()) {
                        this.h.b(var5.b());
                    }
                }
            }

            if(var3) {
                this.g();
                this.h.a();
                this.f();
            }

            this.e = var1;
        }

    }

    public d b() {
        return this.d;
    }

    private void g() {
        d var1 = new d();
        HashMap var2 = new HashMap();
        ArrayList var3 = new ArrayList();
        Iterator var4 = this.g.iterator();

        while(var4.hasNext()) {
            com.umeng.analytics.c.a var5 = (com.umeng.analytics.c.a)var4.next();
            if(var5.c()) {
                if(var5.d() != null) {
                    var2.put(var5.b(), var5.d());
                }

                if(var5.e() != null && !var5.e().isEmpty()) {
                    var3.addAll(var5.e());
                }
            }
        }

        var1.a(var3);
        var1.a(var2);
        synchronized(this) {
            this.d = var1;
        }
    }

    public String c() {
        return null;
    }

    public void d() {
        boolean var1 = false;
        Iterator var2 = this.g.iterator();

        while(var2.hasNext()) {
            com.umeng.analytics.c.a var3 = (com.umeng.analytics.c.a)var2.next();
            if(var3.c() && var3.e() != null && !var3.e().isEmpty()) {
                var3.a((List)null);
                var1 = true;
            }
        }

        if(var1) {
            this.d.b(false);
            this.f();
        }

    }

    public void e() {
        d var1 = this.h();
        if(var1 != null) {
            ArrayList var2 = new ArrayList(this.g.size());
            synchronized(this) {
                this.d = var1;
                Iterator var4 = this.g.iterator();

                while(true) {
                    com.umeng.analytics.c.a var5;
                    if(!var4.hasNext()) {
                        var4 = var2.iterator();

                        while(var4.hasNext()) {
                            var5 = (com.umeng.analytics.c.a)var4.next();
                            this.g.remove(var5);
                        }
                        break;
                    }

                    var5 = (com.umeng.analytics.c.a)var4.next();
                    var5.a(this.d);
                    if(!var5.c()) {
                        var2.add(var5);
                    }
                }
            }

            this.g();
        }
    }

    public void f() {
        if(this.d != null) {
            this.a(this.d);
        }

    }

    private d h() {
        if(!this.file.exists()) {
            return null;
        } else {
            FileInputStream var1 = null;

            try {
                var1 = new FileInputStream(this.file);
                byte[] var2 = EncodeUtil.readData(var1);
                d var3 = new d();
                (new a.a.a.g()).a(var3, var2);
                d var4 = var3;
                return var4;
            } catch (Exception var8) {
                var8.printStackTrace();
            } finally {
                EncodeUtil.close(var1);
            }

            return null;
        }
    }

    private void a(d var1) {
        if(var1 != null) {
            try {
                byte[] var2;
                synchronized(this) {
                    var2 = (new m()).a(var1);
                }

                if(var2 != null) {
                    EncodeUtil.writeToFile(this.file, var2);
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

    }

    public static class a {
        private Context a;
        private Set<String> b = new HashSet();

        public a(Context var1) {
            this.a = var1;
        }

        public boolean a(String var1) {
            return !this.b.contains(var1);
        }

        public void b(String var1) {
            this.b.add(var1);
        }

        public void c(String var1) {
            this.b.remove(var1);
        }

        public void a() {
            if(!this.b.isEmpty()) {
                StringBuilder var1 = new StringBuilder();
                Iterator var2 = this.b.iterator();

                while(var2.hasNext()) {
                    String var3 = (String)var2.next();
                    var1.append(var3);
                    var1.append(',');
                }

                var1.deleteCharAt(var1.length() - 1);
                SharedPreferences var4 = SP_Util.getSp(this.a);
                var4.edit().putString("invld_id", var1.toString()).commit();
            }

        }

        public void b() {
            SharedPreferences var1 = SP_Util.getSp(this.a);
            String var2 = var1.getString("invld_id", (String)null);
            if(!TextUtils.isEmpty(var2)) {
                String[] var3 = var2.split(",");
                if(var3 != null) {
                    String[] var4 = var3;
                    int var5 = var3.length;

                    for(int var6 = 0; var6 < var5; ++var6) {
                        String var7 = var4[var6];
                        if(!TextUtils.isEmpty(var7)) {
                            this.b.add(var7);
                        }
                    }
                }
            }

        }
    }
}
