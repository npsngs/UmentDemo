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

public class Oldumid extends UMProperty {
    private static final String a = "oldumid";
    private Context context;
    private String value = null;
    private String umid = null;

    public Oldumid(Context context) {
        super("oldumid");
        this.context = context;
    }

    public String getValue() {
        return this.value;
    }

    public boolean hasOld() {
        return this.isExistOld();
    }

    public boolean isExistOld() {
        this.umid = ImprintTool.getInstance(this.context).getOption().getUmid(null);
        if(!TextUtils.isEmpty(this.umid)) {
            this.umid = StringTool.sha1(this.umid);
            String cacheUmid1 = EncodeUtil.readStr(new File("/sdcard/Android/data/.um/sysid.dat"));
            String cacheUmid2 = EncodeUtil.readStr(new File("/sdcard/Android/obj/.um/sysid.dat"));
            String cacheUmid3 = EncodeUtil.readStr(new File("/data/local/tmp/.um/sysid.dat"));
            if(TextUtils.isEmpty(cacheUmid1)) {
                this.writeToDataCache();
            } else if(!this.umid.equals(cacheUmid1)) {
                this.value = cacheUmid1;
                return true;
            }

            if(TextUtils.isEmpty(cacheUmid2)) {
                this.writeToObjCache();
            } else if(!this.umid.equals(cacheUmid2)) {
                this.value = cacheUmid2;
                return true;
            }

            if(TextUtils.isEmpty(cacheUmid3)) {
                this.writeToTmpCache();
            } else if(!this.umid.equals(cacheUmid3)) {
                this.value = cacheUmid3;
                return true;
            }
        }

        return false;
    }

    public void writeToCaches() {
        try {
            this.writeToDataCache();
            this.writeToObjCache();
            this.writeToTmpCache();
        } catch (Exception e) {
        }

    }

    private void writeToTmpCache() {
        try {
            this.checkDirFile("/data/local/tmp/.um");
            EncodeUtil.writeToFile(new File("/data/local/tmp/.um/sysid.dat"), this.umid);
        } catch (Throwable t) {
        }

    }

    private void writeToObjCache() {
        try {
            this.checkDirFile("/sdcard/Android/obj/.um");
            EncodeUtil.writeToFile(new File("/sdcard/Android/obj/.um/sysid.dat"), this.umid);
        } catch (Throwable t) {
        }

    }

    private void writeToDataCache() {
        try {
            this.checkDirFile("/sdcard/Android/data/.um");
            EncodeUtil.writeToFile(new File("/sdcard/Android/data/.um/sysid.dat"), this.umid);
        } catch (Throwable t) {
        }

    }

    private void checkDirFile(String dirPath) {
        File file = new File(dirPath);
        if(!file.exists()) {
            file.mkdirs();
        }

    }
}
