package com.yxd.ums.ios;

public class IOSBean {
    private String seed;
    private int successfulRequests = 0;
    private String resolution;
    private String deviceModel;
    private String deviceId;
    private String mac;
    private String osVersion;
    private String carrier;
    private String mccmnc;
    private String access_subtype;
    private String signature;
    private String idfa;
    private String idfv;
    private String utdid;
    private String jailbroken;
    private String access;
    private String idTracking;
    private String imprint;
    public IOSBean(String seed) {
        this.seed = seed;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
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

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getMccmnc() {
        return mccmnc;
    }

    public void setMccmnc(String mccmnc) {
        this.mccmnc = mccmnc;
    }

    public String getAccess_subtype() {
        return access_subtype;
    }

    public void setAccess_subtype(String access_subtype) {
        this.access_subtype = access_subtype;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public String getUtdid() {
        return utdid;
    }

    public void setUtdid(String utdid) {
        this.utdid = utdid;
    }

    public String getJailbroken() {
        return jailbroken;
    }

    public void setJailbroken(String jailbroken) {
        this.jailbroken = jailbroken;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
