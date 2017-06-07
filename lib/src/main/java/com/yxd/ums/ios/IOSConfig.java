package com.yxd.ums.ios;

import android.content.Context;

import com.yxd.ums.SeedDB;

public class IOSConfig {
    private IOSBean iosBean;
    private SeedDB seedDB;

    public IOSConfig(Context context, String seed) {
        seedDB = SeedDB.getInstance(context);
        iosBean = seedDB.readIosFromDB(seed);
        if(iosBean == null){
            iosBean = buildConfigBean(seed);
            seedDB.writeIosToDB(iosBean);
        }
    }

    private IOSBean buildConfigBean(String seed){
        IOSBean iosBean = new IOSBean(seed);

        IOSDevice device = new IOSDevice(seed);
        iosBean.setDeviceModel(device.getDeviceModel());
        iosBean.setAccess_subtype(device.getAccessSubtype());
        iosBean.setMccmnc(device.getMccmnc());
        iosBean.setCarrier(device.getCarrier());
        iosBean.setResolution(device.getResolution());
        iosBean.setOsVersion(device.getOsVersion());
        iosBean.setJailbroken(device.getJailbroken());
        iosBean.setAccess(device.getAccess());

        IOSUniqueID uniqueID = new IOSUniqueID(seed);
        iosBean.setDeviceId(uniqueID.getDeviceId());
        iosBean.setMac(uniqueID.getMacAddress());
        iosBean.setSignature(uniqueID.getSignature());
        iosBean.setIdfa(uniqueID.getIdfa());
        iosBean.setIdfv(uniqueID.getIdfv());
        iosBean.setUtdid(uniqueID.getUtdid());
        iosBean.setIdTracking(uniqueID.getIdTracking());

        return iosBean;
    }

    public void updateImprintToDB(String imprint){
        iosBean.setImprint(imprint);
        seedDB.updateOsImprint(iosBean);
    }

    public String getIdTracking(){
        return iosBean.getIdTracking();
    }

    public String getImprint(){
        return iosBean.getImprint();
    }


    public int addSuccessfulRequests(){
        int s = iosBean.addSuccessfulRequests();
        seedDB.updateOsSuccessTimes(iosBean);
        return s;
    }
    public int getSuccessfulRequests(){
        return iosBean.getSuccessfulRequests();
    }

    public String getSeed() {
        return iosBean.getSeed();
    }

    public String getResolution() {
        return iosBean.getResolution();
    }

    public String getDeviceModel() {
        return iosBean.getDeviceModel();
    }

    public String getDeviceId() {
        return iosBean.getDeviceId();
    }

    public String getMac() {
        return iosBean.getMac();
    }

    public String getOsVersion() {
        return iosBean.getOsVersion();
    }

    public String getCarrier() {
        return iosBean.getCarrier();
    }

    public String getMccmnc() {
        return iosBean.getMccmnc();
    }

    public String getAccess_subtype() {
        return iosBean.getAccess_subtype();
    }

    public String getSignature() {
        return iosBean.getSignature();
    }

    public String getIdfa() {
        return iosBean.getIdfa();
    }

    public String getIdfv() {
        return iosBean.getIdfv();
    }

    public String getUtdid() {
        return iosBean.getUtdid();
    }

    public String getJailbroken() {
        return iosBean.getJailbroken();
    }

    public String getAccess() {
        return iosBean.getAccess();
    }
}
