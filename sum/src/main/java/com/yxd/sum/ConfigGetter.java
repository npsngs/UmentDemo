package com.yxd.sum;

import com.yxd.sum.tool.EncodeTool;

public class ConfigGetter {
    private String deviceId;
    public ConfigGetter(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppkey(){
        return "5423af13fd98c5f4b002c8ef";
    }

    public String getAppVersion(){
        return "1.0";
    }

    public String getResolution(){
        return "960*540";
    }

    public int getVerionCode(){
        return 1;
    }

    public String getDeviceModel(){
        return "HUAWEI G6-U00";
    }

    public int getTimezone(){
        return 8;
    }

    public String getDeviceName(){
        return "hwG6-U00";
    }

    public String getMacAddress(){
        return "f8:01:13:3f:4c:f5";
    }

    public int getRequestTime(){
        return 108;
    }

    public String getCarrier(){
        return "null";
    }

    public int getSuccessfulRequests(){
        return 21;
    }

    public String getDeviceBoard(){
        return "G6-U00";
    }

    public int getVerticalType(){
        return 0;
    }

    public String getDeviceManufacturer(){
        return "HUAWEI";
    }

    public String getOS(){
        return  "Android";
    }

    public String getAppSignature(){
        return "9F:E0:18:70:E3:5A:EC:35:38:EA:61:D0:7B:91:3A:7E";
    }

    public String getCpu(){
        return "ARMv7 Processor rev 3 (v7l)";
    }

    public String getPackageName(){
        return  "com.yxd.umentdemo";
    }

    public String getDeviceId(){
        return  deviceId;
    }

    public String getDeviceBrand(){
        return  "Huawei";
    }

    public String getAccess(){
        return  "wifi";
    }

    public String getCountry(){
        return  "CN";
    }

    public String getOsVersion(){
        return "4.3";
    }

    public String getIdMd5(){
        return EncodeTool.getMD5(getDeviceId());
    }

    public String getDisplayName(){
        return "UmentDemo";
    }

    public String getSdkType(){
        return "Android";
    }

    public String getDeviceManuId(){
        return "HuaweiG6-U00";
    }

    public String getMccmnc(){
        return "";
    }

    public String getLanguage(){
        return "zh";
    }

    public String getChannel(){
        return "yxd_android_demo";
    }

    public long getDeviceManutime(){
        return 1404237854000L;
    }

    public String getSdkVersion(){
        return "6.0.9";
    }
}
