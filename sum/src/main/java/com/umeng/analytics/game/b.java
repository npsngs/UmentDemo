//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.yxd.sum.tool.SerializableTool;
import com.yxd.sum.tool.SPTool;
import java.io.Serializable;

public class b {
    public String a;
    public String b;
    private Context context;
    private final String d = "um_g_cache";
    private final String e = "single_level";
    private final String f = "stat_player_level";
    private final String g = "stat_game_level";
    private b.a h = null;

    public b(Context context) {
        this.context = context;
    }

    public b.a a(String var1) {
        this.h = new b.a(var1);
        this.h.a();
        return this.h;
    }

    public void a() {
        if(this.h != null) {
            this.h.b();
            Editor editor = this.context.getSharedPreferences("um_g_cache", 0).edit();
            editor.putString("single_level", SerializableTool.writeToStr(this.h));
            editor.putString("stat_player_level", this.b);
            editor.putString("stat_game_level", this.a);
            editor.apply();
        }

    }

    public void b() {
        SharedPreferences sp = SPTool.getSp(this.context, "um_g_cache");
        String singleLevel = sp.getString("single_level", null);
        if(!TextUtils.isEmpty(singleLevel)) {
            this.h = (b.a) SerializableTool.readFromStr(singleLevel);
            if(this.h != null) {
                this.h.c();
            }
        }

        if(TextUtils.isEmpty(this.b)) {
            this.b = sp.getString("stat_player_level", (String)null);
            if(this.b == null) {
                SharedPreferences var3 = SPTool.getSp(this.context);
                if(var3 == null) {
                    return;
                }

                this.b = var3.getString("userlevel", (String)null);
            }
        }

        if(this.a == null) {
            this.a = sp.getString("stat_game_level", (String)null);
        }

    }

    public b.a b(String var1) {
        b.a var2 = null;
        if(this.h != null) {
            this.h.d();
            if(this.h.a(var1)) {
                var2 = this.h;
                this.h = null;
            }
        }

        return var2;
    }

    static class a implements Serializable {
        private static final long a = 20140327L;
        private String b;
        private long c;
        private long d;

        public a(String var1) {
            this.b = var1;
        }

        public boolean a(String var1) {
            return this.b.equals(var1);
        }

        public void a() {
            this.d = System.currentTimeMillis();
        }

        public void b() {
            this.c += System.currentTimeMillis() - this.d;
            this.d = 0L;
        }

        public void c() {
            this.a();
        }

        public void d() {
            this.b();
        }

        public long e() {
            return this.c;
        }

        public String f() {
            return this.b;
        }
    }
}
