package com.yxd.ums;

import android.content.Context;
import com.umeng.tool.SystemUtil;

public class PublicConfig {

    private static PublicConfig instance;
    public static PublicConfig getInstance(Context context){
        if(instance == null){
            instance = new PublicConfig(context);
        }
        return instance;
    }

    private int appVersionCode;
    private String appVersion;
    private String packageName;
    private String displayName;
    private String app_signature;

    private PublicConfig(Context context) {
        appVersionCode = Integer.parseInt(SystemUtil.getVersionCode(context));
        appVersion = SystemUtil.getVersionName(context);
        packageName = SystemUtil.getPackageName(context);
        displayName = SystemUtil.loadLabel(context);
        app_signature = SystemUtil.getAppSignatures(context);
    }

    public int getAppVersionCode(){
        return appVersionCode;
    }

    public String getAppVersion(){
        return appVersion;
    }

    public String getPackageName(){
        return packageName;
    }

    public String getDisplayName(){
        return displayName;
    }

    public String getApp_signature() {
        return app_signature;
    }
}
