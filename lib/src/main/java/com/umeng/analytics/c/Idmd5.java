//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;

import com.umeng.tool.SystemUtil;

public class Idmd5 extends UMProperty {
    private static final String a = "idmd5";
    private Context b;

    public Idmd5(Context var1) {
        super("idmd5");
        this.b = var1;
    }

    public String getValue() {
        return SystemUtil.get5DeviceIdInMD5(this.b);
    }
}
