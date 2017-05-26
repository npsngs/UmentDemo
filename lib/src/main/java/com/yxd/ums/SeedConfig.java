package com.yxd.ums;

import android.content.Context;

import com.yxd.ums.device.Device;
import com.yxd.ums.device.UniqueID;

public class SeedConfig {
    private ConfigBean configBean;
    private SeedDB seedDB;
    public SeedConfig(Context context, String seed) {
        seedDB = SeedDB.getInstance(context);
        configBean = seedDB.readFromDB(seed);
        if(configBean == null){
            configBean = buildConfigBean(context, seed);
            seedDB.writeToDB(configBean);
        }
    }

    private ConfigBean buildConfigBean(Context context, String seed){
        ConfigBean configBean = new ConfigBean(seed);

        Device device = new Device(context,seed);
        configBean.setDeviceModel(device.getDevice_model());
        configBean.setDeviceBoard(device.getDevice_board());
        configBean.setDeviceBrand(device.getDevice_brand());
        configBean.setDeviceName(device.getDevice_name());
        configBean.setResolution(device.getResolution());
        configBean.setCpu(device.getCpu());
        configBean.setDeviceManuid(device.getDevice_manuid());
        configBean.setOsVersion(device.getOs_version());
        configBean.setDeviceManutime(System.currentTimeMillis() - 8640000);


        UniqueID uniqueID = new UniqueID(seed);
        configBean.setDeviceId(uniqueID.getImei());
        configBean.setIdmd5(uniqueID.getIdmd5());
        configBean.setMac(uniqueID.getMacAddress());
        configBean.setSignature(uniqueID.getSignature());
        configBean.setIdTracking(uniqueID.getIdTracking());

        return configBean;
    }

    public void updateImprintToDB(String imprint){
        configBean.setImprint(imprint);
        seedDB.updateImprint(configBean);
    }

    public String getIdTracking(){
        return configBean.getIdTracking();
    }

    public String getImprint(){
        return configBean.getImprint();
    }


    public int addSuccessfulRequests(){
        int s = configBean.addSuccessfulRequests();
        seedDB.updateSuccessTimes(configBean);
        return s;
    }
    public int getSuccessfulRequests(){
        return configBean.getSuccessfulRequests();
    }

    public String getResolution(){
        return configBean.getResolution();
    }

    public String getDeviceModel(){
        return configBean.getDeviceModel();
    }

    public String getDeviceName(){
        return configBean.getDeviceName();
    }

    public String getDeviceBoard(){
        return configBean.getDeviceBoard();
    }

    public String getDeviceBrand(){
        return configBean.getDeviceBrand();
    }

    public String getDeviceManuid(){
        return configBean.getDeviceManuid();
    }

    public long getDeviceManutime(){
        return configBean.getDeviceManutime();
    }

    public String getDeviceManufacturer(){
        return configBean.getDeviceManufacturer();
    }

    public String getDeviceId(){
        return configBean.getDeviceId();
    }

    public String getMac(){
        return configBean.getMac();
    }

    public String getCpu(){
        return configBean.getCpu();
    }

    public String getOsVersion(){
        return configBean.getOsVersion();
    }

    public String getIdmd5(){
        return configBean.getIdmd5();
    }

    public String getSignature() {
        return configBean.getSignature();
    }
}
