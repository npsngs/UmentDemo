//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;

public class j extends a {
    private static final String a = "newumid";
    private Context b;

    public j(Context var1) {
        super("newumid");
        this.b = var1;
    }

    public String f() {
        return h.getInstance(this.b).getOption().g((String)null);
    }
}
