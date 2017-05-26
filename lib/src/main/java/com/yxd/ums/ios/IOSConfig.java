package com.yxd.ums.ios;

import android.content.Context;

import com.yxd.ums.SeedDB;
import com.yxd.ums.device.UniqueID;

public class IOSConfig {
    private IOSBean iosBean;
    private SeedDB seedDB;
    private String appkey = "592532e05312dd78be0020b8";//test_ios
//    private String appkey = "57d7cd6e67e58e70f700093f";//lion_ios

    public IOSConfig(Context context, String seed) {
        seedDB = SeedDB.getInstance(context);
        iosBean = seedDB.readIosFromDB(seed);
        if(iosBean == null){
            iosBean = buildConfigBean(seed);
            seedDB.writeIosToDB(iosBean);
        }
    }

    private IOSBean buildConfigBean(String seed){
        IOSBean configBean = new IOSBean(seed);

        IOSDevice device = new IOSDevice(seed);
        configBean.setDeviceModel(device.getDeviceModel());
        configBean.setAccess_subtype(device.getAccessSubtype());
        configBean.setMccmnc(device.getMccmnc());
        configBean.setCarrier(device.getCarrier());
        configBean.setResolution(device.getResolution());
        configBean.setOsVersion(device.getOsVersion());
        configBean.setJailbroken(device.getJailbroken());
        configBean.setAccess(device.getAccessSubtype());


        IOSUniqueID uniqueID = new IOSUniqueID(seed);
        configBean.setDeviceId(uniqueID.getDeviceId());
        configBean.setMac(uniqueID.getMacAddress());
        configBean.setSignature(uniqueID.getSignature());
        configBean.setIdfa(uniqueID.getIdfa());
        configBean.setIdfv(uniqueID.getIdfv());
        configBean.setUtdid(uniqueID.getUtdid());
        configBean.setIdTracking(uniqueID.getIdTracking());

        return configBean;
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

    public String getAppkey() {
        return appkey;
    }

    public String getJailbroken() {
        return iosBean.getJailbroken();
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAccess() {
        return iosBean.getAccess();
    }
}
