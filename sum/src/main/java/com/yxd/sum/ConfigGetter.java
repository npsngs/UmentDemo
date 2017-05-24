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
        return "1960*1540";
    }

    public int getVerionCode(){
        return 1;
    }

    public String getDeviceModel(){
        return "xiaomi";
    }

    public int getTimezone(){
        return 8;
    }

    public String getDeviceName(){
        return "xiaomi";
    }

    public String getMacAddress(){
        return "88:88:88:88:88:88";
    }

    public int getRequestTime(){
        return 1;
    }

    public String getCarrier(){
        return "";
    }

    public int getSuccessfulRequests(){
        return 1;
    }

    public String getDeviceBoard(){
        return "XIAOMI";
    }

    public int getVerticalType(){
        return 0;
    }

    public String getDeviceManufacturer(){
        return "XIAOMI";
    }

    public String getOS(){
        return  "Android";
    }

    public String getAppSignature(){
        return "11:11:11:11:11:11:11:11:11:11:11:11:11:11:11:11";
    }

    public String getCpu(){
        return "ARMv7";
    }

    public String getPackageName(){
        return  "com.yxd.umenstdemo";
    }

    public String getDeviceId(){
        return  deviceId;
    }

    public String getDeviceBrand(){
        return  "Hsuawei";
    }

    public String getAccess(){
        return  "wifi";
    }

    public String getCountry(){
        return  "CN";
    }

    public String getOsVersion(){
        return "9.3";
    }

    public String getIdMd5(){
        return EncodeTool.getMD5(getDeviceId()+"sdsd");
    }

    public String getDisplayName(){
        return "UmentDemo";
    }

    public String getSdkType(){
        return "Android";
    }

    public String getDeviceManuId(){
        return "HuaweiG6s-U00";
    }

    public String getMccmnc(){
        return "";
    }

    public String getLanguage(){
        return "zh";
    }

    public String getChannel(){
        return "yxd_androisd_demo";
    }

    public long getDeviceManutime(){
        return 1404237854000L;
    }

    public String getSdkVersion(){
        return "6.1.0";
    }
}
