//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.tool.StringTool;
import com.umeng.tool.EncodeUtil;

import java.io.File;

public class k extends a {
    private static final String a = "oldumid";
    private Context b;
    private String c_str = null;
    private String d = null;

    public k(Context var1) {
        super("oldumid");
        this.b = var1;
    }

    public String f() {
        return this.c_str;
    }

    public boolean g() {
        return this.h();
    }

    public boolean h() {
        this.d = h.getInstance(this.b).getOption().g((String)null);
        if(!TextUtils.isEmpty(this.d)) {
            this.d = StringTool.c(this.d);
            String var1 = EncodeUtil.b(new File("/sdcard/Android/data/.um/sysid.dat"));
            String var2 = EncodeUtil.b(new File("/sdcard/Android/obj/.um/sysid.dat"));
            String var3 = EncodeUtil.b(new File("/data/local/tmp/.um/sysid.dat"));
            if(TextUtils.isEmpty(var1)) {
                this.l();
            } else if(!this.d.equals(var1)) {
                this.c_str = var1;
                return true;
            }

            if(TextUtils.isEmpty(var2)) {
                this.k();
            } else if(!this.d.equals(var2)) {
                this.c_str = var2;
                return true;
            }

            if(TextUtils.isEmpty(var3)) {
                this.j();
            } else if(!this.d.equals(var3)) {
                this.c_str = var3;
                return true;
            }
        }

        return false;
    }

    public void i() {
        try {
            this.l();
            this.k();
            this.j();
        } catch (Exception var2) {
            ;
        }

    }

    private void j() {
        try {
            this.b("/data/local/tmp/.um");
            EncodeUtil.writeToFile(new File("/data/local/tmp/.um/sysid.dat"), this.d);
        } catch (Throwable var2) {
            ;
        }

    }

    private void k() {
        try {
            this.b("/sdcard/Android/obj/.um");
            EncodeUtil.writeToFile(new File("/sdcard/Android/obj/.um/sysid.dat"), this.d);
        } catch (Throwable var2) {
            ;
        }

    }

    private void l() {
        try {
            this.b("/sdcard/Android/data/.um");
            EncodeUtil.writeToFile(new File("/sdcard/Android/data/.um/sysid.dat"), this.d);
        } catch (Throwable var2) {
            ;
        }

    }

    private void b(String var1) {
        File var2 = new File(var1);
        if(!var2.exists()) {
            var2.mkdirs();
        }

    }
}
