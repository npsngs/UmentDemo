package com.yxd.ums;

public class DevBean {
    private String deviceID;
    private String appKey;
    private String osType;
    private String channel;
    private String version;

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

        if(null != channel){
            sb.append("channel=").append(channel).append("");
        }

        if(null != version){
            sb.append("version=").append(version).append("");
        }
        return sb.toString();
    }
}
