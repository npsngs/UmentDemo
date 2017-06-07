package com.yxd.ums;

public class DevBean {
    private String deviceID;
    private String appKey;
    private String osType;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(null != deviceID){
            sb.append("deviceID=").append(deviceID).append(", ");
        }
        if(null != appKey){
            sb.append("appKey=").append(appKey).append(", ");
        }
        if(null != osType){
            sb.append("osType=").append(osType).append("");
        }
        return sb.toString();
    }
}
