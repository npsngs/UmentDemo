//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.tool.StringTool;
import com.yxd.sum.tool.EncodeTool;

import java.io.File;

public class Oldumid extends UMProperty {
    private static final String a = "oldumid";
    private Context context;
    private String value = null;
    private String d = null;

    public Oldumid(Context context) {
        super("oldumid");
        this.context = context;
    }

    public String getValue() {
        return this.value;
    }

    public boolean g() {
        return this.h();
    }

    public boolean h() {
        this.d = ImprintTool.getInstance(this.context).getOption().getUmid(null);
        if(!TextUtils.isEmpty(this.d)) {
            this.d = StringTool.sha1(this.d);
            String var1 = EncodeTool.readStr(new File("/sdcard/Android/data/.um/sysid.dat"));
            String var2 = EncodeTool.readStr(new File("/sdcard/Android/obj/.um/sysid.dat"));
            String var3 = EncodeTool.readStr(new File("/data/local/tmp/.um/sysid.dat"));
            if(TextUtils.isEmpty(var1)) {
                this.l();
            } else if(!this.d.equals(var1)) {
                this.value = var1;
                return true;
            }

            if(TextUtils.isEmpty(var2)) {
                this.k();
            } else if(!this.d.equals(var2)) {
                this.value = var2;
                return true;
            }

            if(TextUtils.isEmpty(var3)) {
                this.j();
            } else if(!this.d.equals(var3)) {
                this.value = var3;
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
        } catch (Exception e) {
        }

    }

    private void j() {
        try {
            this.checkDirFile("/data/local/tmp/.um");
            EncodeTool.writeToFile(new File("/data/local/tmp/.um/sysid.dat"), this.d);
        } catch (Throwable var2) {
            ;
        }

    }

    private void k() {
        try {
            this.checkDirFile("/sdcard/Android/obj/.um");
            EncodeTool.writeToFile(new File("/sdcard/Android/obj/.um/sysid.dat"), this.d);
        } catch (Throwable var2) {
            ;
        }

    }

    private void l() {
        try {
            this.checkDirFile("/sdcard/Android/data/.um");
            EncodeTool.writeToFile(new File("/sdcard/Android/data/.um/sysid.dat"), this.d);
        } catch (Throwable var2) {
            ;
        }

    }

    private void checkDirFile(String dirPath) {
        File file = new File(dirPath);
        if(!file.exists()) {
            file.mkdirs();
        }

    }
}
