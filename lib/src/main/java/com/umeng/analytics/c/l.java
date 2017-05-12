//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.analytics.c.a;

public class l extends a {
    private static final String a = "serial";

    public l() {
        super("serial");
    }

    public String f() {
        return VERSION.SDK_INT >= 9?Build.SERIAL:null;
    }
}
