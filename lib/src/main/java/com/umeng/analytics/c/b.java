//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.provider.Settings.Secure;
import com.umeng.analytics.c.a;

public class b extends a {
    private static final String a = "android_id";
    private Context b;

    public b(Context var1) {
        super("android_id");
        this.b = var1;
    }

    public String f() {
        String var1 = null;

        try {
            var1 = Secure.getString(this.b.getContentResolver(), "android_id");
        } catch (Exception var3) {
            ;
        }

        return var1;
    }
}
