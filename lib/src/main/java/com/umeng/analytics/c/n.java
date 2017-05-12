//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.content.SharedPreferences;

import com.umeng.analytics.d.SP_Util;

public class n extends a {
    private static final String a = "uop";
    private Context b;

    public n(Context var1) {
        super("uop");
        this.b = var1;
    }

    public String f() {
        String var1 = "";
        SharedPreferences var2 = SP_Util.getSp(this.b);
        if(var2 != null) {
            var1 = var2.getString("uopdta", "");
        }

        return var1;
    }
}
