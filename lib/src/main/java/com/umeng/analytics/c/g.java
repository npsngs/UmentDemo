//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.umeng.tool.SystemUtil;

public class g extends a {
    private static final String a = "imei";
    private Context b;

    public g(Context var1) {
        super("imei");
        this.b = var1;
    }

    public String f() {
        TelephonyManager var1 = (TelephonyManager)this.b.getSystemService(Context.TELEPHONY_SERVICE);
        if(var1 == null) {
            ;
        }

        String var2 = null;

        try {
            if(SystemUtil.hasPermission(this.b, "android.permission.READ_PHONE_STATE")) {
                var2 = var1.getDeviceId();
            }
        } catch (Exception var4) {
            ;
        }

        return var2;
    }
}
