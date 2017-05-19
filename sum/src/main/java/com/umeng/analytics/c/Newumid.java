//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;

public class Newumid extends UMProperty {
    private static final String a = "newumid";
    private Context b;

    public Newumid(Context var1) {
        super("newumid");
        this.b = var1;
    }

    public String getValue() {
        return ImprintTool.getInstance(this.b).getOption().getUmid((String)null);
    }
}
