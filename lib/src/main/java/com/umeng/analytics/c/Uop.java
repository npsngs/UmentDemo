//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.content.SharedPreferences;

import com.umeng.analytics.d.SP_Util;

public class Uop extends UMProperty {
    private static final String a = "uop";
    private Context b;

    public Uop(Context var1) {
        super("uop");
        this.b = var1;
    }

    public String getValue() {
        String var1 = "";
        SharedPreferences sp = SP_Util.getSp(this.b);
        if(sp != null) {
            var1 = sp.getString("uopdta", "");
        }

        return var1;
    }
}
