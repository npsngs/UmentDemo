package com.yxd.ums;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class SBox {
    private String seed;
    private Context context;
    private SeedConfig seedConfig;
    private PublicConfig publicConfig;
    public SBox(Context context, String seed) {
        this.context = context;
        this.seed = seed;
        seedConfig = new SeedConfig(context, seed);
        publicConfig = PublicConfig.getInstance(context);
    }

    public JSONObject buildHeader() throws JSONException {
        JSONObject header = new JSONObject();
        header
                .put("os","Android")
                .put("country","CN")
                .put("language","zh")
                .put("sdk_type","Android")
                .put("timezone",8)
                .put("vertical_type",0)
                .put("failed_requests",0)
                .put("sdk_version","6.1.0")
                .put("carrier","null")
                .put("mccmnc","")
                .put("access", "wifi")
                .put("req_time", 90 + (int)(Math.random()*20))
                .put("version_code", publicConfig.getAppVersionCode())
                .put("app_version", publicConfig.getAppVersion())
                .put("package_name", publicConfig.getPackageName())
                .put("display_name", publicConfig.getDisplayName())
                .put("channel", publicConfig.getChannel())
                .put("appkey", publicConfig.getAppkey())
                .put("app_signature", publicConfig.getApp_signature())

                .put("successful_requests", seedConfig.getSuccessfulRequests())
                .put("resolution", seedConfig.getResolution())
                .put("device_model", seedConfig.getDeviceModel())
                .put("device_name", seedConfig.getDeviceName())
                .put("device_board", seedConfig.getDeviceBoard())
                .put("device_brand", seedConfig.getDeviceBrand())
                .put("device_manuid", seedConfig.getDeviceManuid())
                .put("device_manutime", seedConfig.getDeviceManutime())
                .put("device_manufacturer", seedConfig.getDeviceManufacturer())
                .put("cpu", seedConfig.getCpu())
                .put("os_version", seedConfig.getOsVersion())
                .put("device_id", seedConfig.getDeviceId())
                .put("mc", seedConfig.getMac())
                .put("idmd5", seedConfig.getIdmd5())

                .put("id_tracking", seedConfig.getIdTracking());

                String imprint = seedConfig.getImprint();
                if(!TextUtils.isEmpty(imprint)){
                    header.put("imprint", seedConfig.getImprint());
                }

        return header;
    }














}
