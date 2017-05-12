//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import a.a.a.g;
import a.a.a.m;
import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.d.l;
import com.umeng.analytics.f.e;
import com.umeng.analytics.f.f;
import com.umeng.tool.EncodeUtil;
import com.umeng.tool.StringTool;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class h {
    private static final String a = ".imprint";
    private static final byte[] b = "pbl0".getBytes();
    private l c;
    private Option option = new Option();
    private e e = null;
    private static h instance;
    private Context context;

    h(Context context) {
        this.context = context;
    }

    public static synchronized h getInstance(Context context) {
        if(instance == null) {
            instance = new h(context);
            instance.c();
        }

        return instance;
    }

    public void a(l var1) {
        this.c = var1;
    }

    public String a(e var1) {
        StringBuilder var2 = new StringBuilder();
        TreeMap var3 = new TreeMap(var1.d());
        Iterator var4 = var3.entrySet().iterator();

        while(var4.hasNext()) {
            Entry var5 = (Entry)var4.next();
            var2.append((String)var5.getKey());
            if(((f)var5.getValue()).e()) {
                var2.append(((f)var5.getValue()).c());
            }

            var2.append(((f)var5.getValue()).f());
            var2.append(((f)var5.getValue()).i());
        }

        var2.append(var1.b);
        return EncodeUtil.getMD5_2(var2.toString()).toLowerCase(Locale.US);
    }

    private boolean c(e var1) {
        if(!var1.j().equals(this.a(var1))) {
            return false;
        } else {
            Iterator var2 = var1.d().values().iterator();

            while(var2.hasNext()) {
                f var3 = (f)var2.next();
                byte[] var4 = StringTool.a(var3.i());
                byte[] var5 = this.a(var3);

                for(int var6 = 0; var6 < 4; ++var6) {
                    if(var4[var6] != var5[var6]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    public byte[] a(f var1) {
        ByteBuffer var2 = ByteBuffer.allocate(8);
        var2.order((ByteOrder)null);
        var2.putLong(var1.f());
        byte[] var3 = var2.array();
        byte[] var4 = b;
        byte[] var5 = new byte[4];

        for(int var6 = 0; var6 < 4; ++var6) {
            var5[var6] = (byte)(var3[var6] ^ var4[var6]);
        }

        return var5;
    }

    public void b(e var1) {
        if(var1 != null) {
            if(this.c(var1)) {
                boolean var2 = false;
                synchronized(this) {
                    e var4 = this.e;
                    String var6 = var4 == null?null:var4.j();
                    if(var4 == null) {
                        var4 = this.d(var1);
                    } else {
                        var4 = this.a(var4, var1);
                    }

                    this.e = var4;
                    String var7 = var4 == null?null:var4.j();
                    if(!this.a(var6, var7)) {
                        var2 = true;
                    }
                }

                if(this.e != null && var2) {
                    this.option.init(this.e);
                    if(this.c != null) {
                        this.c.a(this.option);
                    }
                }

            }
        }
    }

    private boolean a(String var1, String var2) {
        return var1 == null?var2 == null:var1.equals(var2);
    }

    private e a(e var1, e var2) {
        if(var2 == null) {
            return var1;
        } else {
            Map var3 = var1.d();
            Map var4 = var2.d();
            Iterator var5 = var4.entrySet().iterator();

            while(var5.hasNext()) {
                Entry var6 = (Entry)var5.next();
                if(((f)var6.getValue()).e()) {
                    var3.put(var6.getKey(), var6.getValue());
                } else {
                    var3.remove(var6.getKey());
                }
            }

            var1.a(var2.g());
            var1.a(this.a(var1));
            return var1;
        }
    }

    private e d(e var1) {
        Map var2 = var1.d();
        ArrayList var3 = new ArrayList(var2.size() / 2);
        Iterator var4 = var2.entrySet().iterator();

        while(var4.hasNext()) {
            Entry var5 = (Entry)var4.next();
            if(!((f)var5.getValue()).e()) {
                var3.add(var5.getKey());
            }
        }

        var4 = var3.iterator();

        while(var4.hasNext()) {
            String var6 = (String)var4.next();
            var2.remove(var6);
        }

        return var1;
    }

    public synchronized e a() {
        return this.e;
    }

    public Option getOption() {
        return this.option;
    }

    public void c() {
        File var1 = new File(this.context.getFilesDir(), ".imprint");
        if(var1.exists()) {
            FileInputStream var2 = null;
            byte[] var3 = null;

            try {
                var2 = this.context.openFileInput(".imprint");
                var3 = EncodeUtil.readData(var2);
            } catch (Exception var10) {
                var10.printStackTrace();
            } finally {
                EncodeUtil.close(var2);
            }

            if(var3 != null) {
                try {
                    e var4 = new e();
                    (new g()).a(var4, var3);
                    this.e = var4;
                    this.option.init(var4);
                } catch (Exception var9) {
                    var9.printStackTrace();
                }
            }

        }
    }

    public void d() {
        if(this.e != null) {
            try {
                byte[] var1 = (new m()).a(this.e);
                EncodeUtil.writeToFile(new File(this.context.getFilesDir(), ".imprint"), var1);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

        }
    }

    public boolean e() {
        File var1 = new File(this.context.getFilesDir(), ".imprint");
        return var1.delete();
    }

    public static class Option {
        private int defcon = -1;
        private int latent = -1;
        private int codex = -1;
        private int report_policy = -1;
        private int report_interval = -1;
        private String client_test = null;
        private int test_report_interval = -1;
        private String umid = null;
        private int integrated_test = -1;
        private int latent_hours = -1;
        private String country = null;
        private String domain_p = null;
        private String domain_s = null;
        private String initial_view_time = null;
        private String track_list = null;

        Option() {
        }

        Option(e var1) {
            this.init(var1);
        }

        public void init(e var1) {
            if(var1 != null) {
                this.defcon = this.a(var1, "defcon");
                this.latent = this.a(var1, "latent");
                this.codex = this.a(var1, "codex");
                this.report_policy = this.a(var1, "report_policy");
                this.report_interval = this.a(var1, "report_interval");
                this.client_test = this.b(var1, "client_test");
                this.test_report_interval = this.a(var1, "test_report_interval");
                this.umid = this.b(var1, "umid");
                this.integrated_test = this.a(var1, "integrated_test");
                this.latent_hours = this.a(var1, "latent_hours");
                this.country = this.b(var1, "country");
                this.domain_p = this.b(var1, "getDomainP");
                this.domain_s = this.b(var1, "getDomain_s");
                this.initial_view_time = this.b(var1, "initial_view_time");
                this.track_list = this.b(var1, "track_list");
            }
        }

        public String a(String var1) {
            return this.initial_view_time != null?this.initial_view_time :var1;
        }

        public String b(String var1) {
            return this.track_list != null?this.track_list :var1;
        }

        public String getDomain_s(String def) {
            return this.domain_s != null?this.domain_s :def;
        }

        public String getDomainP(String def) {
            return this.domain_p != null?this.domain_p :def;
        }

        public String getCountry(String defaultCountry) {
            return this.country != null?this.country :defaultCountry;
        }

        public int getDefCon(int def) {
            return this.defcon == -1?def:(this.defcon <= 3 && this.defcon >= 0?this.defcon :def);
        }

        public int b(int var1) {
            return this.latent == -1?var1:(this.latent >= 0 && this.latent <= 1800?this.latent * 1000:var1);
        }

        public int c(int var1) {
            return this.codex != 0 && this.codex != 1 && this.codex != -1?var1:this.codex;
        }

        public int[] a(int var1, int var2) {
            if(this.report_policy != -1 && com.umeng.tool.j.a(this.report_policy)) {
                if(this.report_interval == -1 || this.report_interval < 90 || this.report_interval > 86400) {
                    this.report_interval = 90;
                }

                return new int[]{this.report_policy, this.report_interval * 1000};
            } else {
                return new int[]{var1, var2};
            }
        }

        public String f(String var1) {
            return this.client_test != null && com.umeng.analytics.e.a.a(this.client_test)?this.client_test :var1;
        }

        public int d(int var1) {
            return this.test_report_interval != -1 && this.test_report_interval >= 90 && this.test_report_interval <= 86400?this.test_report_interval * 1000:var1;
        }

        public boolean a() {
            return this.test_report_interval != -1;
        }

        public String g(String var1) {
            return this.umid;
        }

        public boolean b() {
            return this.integrated_test == 1;
        }

        public long a(long var1) {
            return this.latent_hours == -1?var1:(this.latent_hours < 48?var1:3600000L * (long)this.latent_hours);
        }

        private int a(e var1, String var2) {
            try {
                if(var1 == null || !var1.f()) {
                    return -1;
                }

                f var3 = (f)var1.d().get(var2);
                if(var3 == null || TextUtils.isEmpty(var3.c())) {
                    return -1;
                }

                try {
                    return Integer.parseInt(var3.c().trim());
                } catch (Exception var5) {
                    ;
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            return -1;
        }

        private String b(e var1, String var2) {
            String var3 = null;

            try {
                if(var1 == null || !var1.f()) {
                    return null;
                }

                f var4 = (f)var1.d().get(var2);
                if(var4 == null || TextUtils.isEmpty(var4.c())) {
                    return null;
                }

                var3 = var4.c();
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            return var3;
        }
    }
}
