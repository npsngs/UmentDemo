//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.os.Environment;
import com.umeng.tool.SystemUtil;
import com.yxd.sum.tool.EncodeTool;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UTDeviceId extends UMProperty {
    private static final String a = "utdid";
    private static final String b = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final Pattern c = Pattern.compile("UTDID\">([^<]+)");
    private Context d;

    public UTDeviceId(Context var1) {
        super("utdid");
        this.d = var1;
    }

    public String getValue() {
        try {
            Class var1 = Class.forName("com.ut.device.UTDevice");
            Method var2 = var1.getMethod("getUtdid", new Class[]{Context.class});
            return (String)var2.invoke((Object)null, new Object[]{this.d});
        } catch (Exception var3) {
            return this.g();
        }
    }

    private String g() {
        File var1 = this.h();
        if(var1 != null && var1.exists()) {
            try {
                FileInputStream var2 = new FileInputStream(var1);

                String var3;
                try {
                    var3 = this.b(EncodeTool.readStr(var2));
                } finally {
                    EncodeTool.close(var2);
                }

                return var3;
            } catch (Exception var8) {
                var8.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    private String b(String var1) {
        if(var1 == null) {
            return null;
        } else {
            Matcher var2 = c.matcher(var1);
            return var2.find()?var2.group(1):null;
        }
    }

    private File h() {
        if(!SystemUtil.hasPermission(this.d, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return null;
        } else {
            if(Environment.getExternalStorageState().equals("mounted")) {
                File var1 = Environment.getExternalStorageDirectory();

                try {
                    return new File(var1.getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
                } catch (Exception var3) {
                    ;
                }
            }

            return null;
        }
    }
}
