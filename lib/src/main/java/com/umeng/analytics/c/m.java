//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import com.umeng.tool.SystemUtil;

public class m extends a {
    private static final String a = "idmd5";
    private Context b;

    public m(Context var1) {
        super("idmd5");
        this.b = var1;
    }

    public String f() {
        return SystemUtil.get5DeviceIdInMD5(this.b);
    }
}
