//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.tool.CacheTool;

public class AU {
    private static String[] au = new String[2];

    public AU() {
    }

    public static void saveAU(Context context, String au_p, String au_u) {
        au[0] = au_p;
        au[1] = au_u;
        if(context != null) {
            CacheTool.getInstance(context).saveAU(au_p, au_u);
        }

    }

    public static String[] getAU(Context context) {
        if(!TextUtils.isEmpty(au[0]) && !TextUtils.isEmpty(au[1])) {
            return au;
        } else {
            if(context != null) {
                String[] var1 = CacheTool.getInstance(context).loadAU();
                if(var1 != null) {
                    au[0] = var1[0];
                    au[1] = var1[1];
                    return au;
                }
            }

            return null;
        }
    }

    public static void clearAU(Context context) {
        au[0] = null;
        au[1] = null;
        if(context != null) {
            CacheTool.getInstance(context).removeAU();
        }

    }
}
