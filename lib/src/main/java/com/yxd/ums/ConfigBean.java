package com.yxd.ums;

public class ConfigBean {
    private String seed;
    private int successfulRequests = 0;
    private String resolution;
    private String deviceModel;
    private String deviceName;
    private String deviceBoard;
    private String deviceBrand;
    private String deviceManuid;
    private long deviceManutime;
    private String deviceId;
    private String mac;
    private String cpu;
    private String osVersion;
    private String idmd5;
    private String signature;
    private String idTracking;
    private String imprint;

    public ConfigBean(String seed) {
        this.seed = seed;
    }

    public int getSuccessfulRequests() {
        return successfulRequests;
    }

    public int addSuccessfulRequests() {
        this.successfulRequests++;
        return successfulRequests;
    }

    public void setSuccessfulRequests(int successfulRequests) {
        this.successfulRequests = successfulRequests;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceBoard() {
        return deviceBoard;
    }

    public void setDeviceBoard(String deviceBoard) {
        this.deviceBoard = deviceBoard;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceManuid() {
        return deviceManuid;
    }

    public void setDeviceManuid(String deviceManuid) {
        this.deviceManuid = deviceManuid;
    }

    public long getDeviceManutime() {
        return deviceManutime;
    }

    public void setDeviceManutime(long deviceManutime) {
        this.deviceManutime = deviceManutime;
    }

    public String getDeviceManufacturer() {
        return deviceBrand;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getIdmd5() {
        return idmd5;
    }

    public void setIdmd5(String idmd5) {
        this.idmd5 = idmd5;
    }

    public String getIdTracking() {
        return idTracking;
    }

    public void setIdTracking(String idTracking) {
        this.idTracking = idTracking;
    }

    public String getImprint() {
        return imprint;
    }

    public void setImprint(String imprint) {
        this.imprint = imprint;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
