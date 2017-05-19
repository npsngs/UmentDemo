//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;

import com.umeng.tool.GooglePlayTool;

public class Idfa extends UMProperty {
    private static final String a = "idfa";
    private Context b;

    public Idfa(Context var1) {
        super("idfa");
        this.b = var1;
    }

    public String getValue() {
        String var1 = GooglePlayTool.a(this.b);
        return var1 == null?"":var1;
    }
}
