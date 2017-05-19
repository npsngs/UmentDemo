package com.yxd.sum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EntityBuilder {

    private JSONObject buildHeader(String deviceID) throws JSONException{
        ConfigGetter configGetter = new ConfigGetter(deviceID);
        JSONObject header = new JSONObject();
        header
                .put("failed_requests",0)
                .put("timezone", configGetter.getTimezone())
                .put("package_name", configGetter.getPackageName())
                .put("appkey",configGetter.getAppkey())
                .put("country", configGetter.getCountry())
                .put("display_name", configGetter.getDisplayName())
                .put("sdk_type", configGetter.getSdkType())
                .put("language", configGetter.getLanguage())
                .put("sdk_version", configGetter.getSdkVersion())
                .put("os", configGetter.getOS())

                .put("version_code", configGetter.getVerionCode())
                .put("app_version", configGetter.getAppVersion())
                .put("req_time", configGetter.getRequestTime())
                .put("successful_requests", configGetter.getSuccessfulRequests())
                .put("access", configGetter.getAccess())
                .put("channel", configGetter.getChannel())

                .put("os_version", configGetter.getOsVersion())
                .put("resolution", configGetter.getResolution())
                .put("device_model", configGetter.getDeviceModel())
                .put("device_name", configGetter.getDeviceName())
                .put("device_board", configGetter.getDeviceBoard())
                .put("device_id", configGetter.getDeviceId())
                .put("device_brand", configGetter.getDeviceBrand())
                .put("device_manuid", configGetter.getDeviceManuId())
                .put("device_manutime", configGetter.getDeviceManutime())
                .put("device_manufacturer", configGetter.getDeviceManufacturer())
                .put("mc", configGetter.getMacAddress())
                .put("cpu", configGetter.getCpu())
                .put("carrier", configGetter.getCarrier())
                .put("idmd5", configGetter.getIdMd5())
                .put("mccmnc", configGetter.getMccmnc())
                .put("app_signature", configGetter.getAppSignature())


                .put("imprint","")
                .put("id_tracking","");
        return header;
    }

    private JSONObject buildBody() throws JSONException{
        JSONObject body = new JSONObject();
        JSONArray sessions = new JSONArray();

        JSONObject session = new JSONObject();
        JSONArray autopages = new JSONArray();
        JSONArray pages = new JSONArray();
        JSONObject autopage;
        for(int i=0;i<5;i++){
            autopage = new JSONObject();
            autopage.put("page_name", "com.yxd.umentdemo.MainActivity").put("duration", 1024+Math.random()*3000);
            autopages.put(autopage);
            pages.put(autopage);
        }

        session
                .put("autopages", autopages)
                .put("pages", pages)
                .put("id","452C0522690C493C8165F18FBBE7F60A")
                .put("start_time", "1488967987287")
                .put("duration", 1116456)
                .put("end_time", "1488969103743");

        JSONObject traffic = new JSONObject();
        traffic.put("download_traffic", 10016);
        traffic.put("upload_traffic", 13675);
        session.put("traffic", traffic);

        sessions.put(session);
        body.put("sessions", sessions);
        return body;
    }

    public JSONObject build(String deviceID) throws JSONException {
        JSONObject entity = new JSONObject();
        JSONObject body = buildBody();
        JSONObject header = buildHeader(deviceID);
        entity.put("header", header)
                .put("body", body);

        return entity;
    }
}
