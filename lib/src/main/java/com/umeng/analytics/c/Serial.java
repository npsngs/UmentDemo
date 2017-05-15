//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.os.Build;
import android.os.Build.VERSION;

public class Serial extends UMProperty {
    private static final String a = "serial";

    public Serial() {
        super("serial");
    }

    public String getValue() {
        return VERSION.SDK_INT >= 9?Build.SERIAL:null;
    }
}
