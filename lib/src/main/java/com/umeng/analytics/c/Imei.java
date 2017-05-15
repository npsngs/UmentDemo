//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.umeng.tool.SystemUtil;

public class Imei extends UMProperty {
    private static final String a = "imei";
    private Context context;

    public Imei(Context context) {
        super("imei");
        this.context = context;
    }

    public String getValue() {
        TelephonyManager telephonyManager = (TelephonyManager)this.context.getSystemService(Context.TELEPHONY_SERVICE);
        if(telephonyManager == null) {
            return null;
        }

        String deviceID = null;
        try {
            if(SystemUtil.hasPermission(this.context, "android.permission.READ_PHONE_STATE")) {
                deviceID = telephonyManager.getDeviceId();
            }
        } catch (Exception e) {
        }
        return deviceID;
    }
}
