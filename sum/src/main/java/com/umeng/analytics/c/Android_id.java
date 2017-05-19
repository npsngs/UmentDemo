//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.provider.Settings.Secure;

public class Android_id extends UMProperty {
    private static final String a = "android_id";
    private Context b;

    public Android_id(Context var1) {
        super("android_id");
        this.b = var1;
    }

    public String getValue() {
        String var1 = null;
        try {
            var1 = Secure.getString(this.b.getContentResolver(), "android_id");
        } catch (Exception var3) {
        }

        return var1;
    }
}
