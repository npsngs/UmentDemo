//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.tool;

import android.content.Context;
import android.content.SharedPreferences;

public class SPTool {
    private static final String sp_name = "umeng_general_config";

    private SPTool() {
    }

    public static SharedPreferences getSp(Context context, String var1) {
        return context.getSharedPreferences(var1, 0);
    }

    public static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences(sp_name, 0);
    }
}
