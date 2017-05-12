//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import com.umeng.tool.SystemUtil;

public class i extends a {
    private static final String a = "mac";
    private Context b;

    public i(Context var1) {
        super("mac");
        this.b = var1;
    }

    public String f() {
        String var1 = null;

        try {
            var1 = SystemUtil.getMacAddress(this.b);
        } catch (Exception var3) {
            ;
        }

        return var1;
    }
}
