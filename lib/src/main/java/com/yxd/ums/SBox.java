package com.yxd.ums;

import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.g.UMEnvelope;
import com.umeng.tool.StringTool;
import com.umeng.tool.ZipTool;

import org.json.JSONException;
import org.json.JSONObject;

public class SBox {
    private String seed;
    private Context context;
    private SeedConfig seedConfig;
    private PublicConfig publicConfig;
    private String appkey;
    public SBox(Context context, DevBean devBean) {
        this.context = context;
        this.seed = devBean.getDeviceID().toLowerCase();
        this.appkey = devBean.getAppKey().toLowerCase();
        seedConfig = new SeedConfig(context, seed);
        publicConfig = PublicConfig.getInstance(context);
    }

    public String getAppkey() {
        return appkey;
    }

    public SeedConfig getSeedConfig() {
        return seedConfig;
    }

    public PublicConfig getPublicConfig() {
        return publicConfig;
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
                .put("sdk_version","6.0.9")
                .put("carrier","null")
                .put("mccmnc","")
                .put("access", "wifi")
                .put("req_time", 90 + (int)(Math.random()*20))
                .put("version_code", publicConfig.getAppVersionCode())
                .put("app_version", publicConfig.getAppVersion())
                .put("package_name", publicConfig.getPackageName())
                .put("display_name", publicConfig.getDisplayName())
                .put("channel", publicConfig.getChannel())
                .put("app_signature", publicConfig.getApp_signature())
                .put("appkey", appkey)

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




    public JSONObject buildActiveEntity() throws JSONException {
        JSONObject entity = new JSONObject();
        JSONObject boby = new JSONObject();
        JSONObject msg = new JSONObject();
        msg.put("ts", System.currentTimeMillis());
        boby.put("activate_msg", msg);

        entity.put("body", boby);
        entity.put("header", buildHeader());
        return entity;
    }

    public JSONObject buildNoBodyEntity() throws JSONException {
        JSONObject entity = new JSONObject();
        entity.put("header", buildHeader());
        return entity;
    }

    public UMEnvelope buildEnvelope() throws Exception {
        JSONObject jsonObject = buildActiveEntity();
        byte[] entity = String.valueOf(jsonObject).getBytes();
        return  buildEnvelope(entity);
    }


    public UMEnvelope buildEnvelopeWithBody(JSONObject body) throws Exception {
        JSONObject jsonObject = buildNoBodyEntity();
        jsonObject.put("body", body);

        byte[] entity = String.valueOf(jsonObject).getBytes();
        return buildEnvelope(entity);
    }


    public UMEnvelope buildEnvelope(byte[] entity) throws Exception {
        if(entity == null || StringTool.isTooLong(context, entity)) {
            return null;
        }

        UMEnvelope umEnvelope = new UMEnvelope();
        umEnvelope.setVersion("1.0");
        umEnvelope.setAddress(appkey);
        seedConfig.addSuccessfulRequests();
        umEnvelope.setSerial(seedConfig.getSuccessfulRequests());
        umEnvelope.setTimestamp((int)(System.currentTimeMillis() / 1000L));
        umEnvelope.setLength(entity.length);
        entity = ZipTool.deflate(entity);
        umEnvelope.setEntity(entity);
        umEnvelope.setCodex(0);

        umEnvelope.setSignature(seedConfig.getSignature());
        byte[] signature = StringTool.hex2Byte(umEnvelope.getSignature());
        umEnvelope.setGuid(buildGuid(signature, umEnvelope.ts_secs, entity));

        StringBuilder sb = new StringBuilder();
        sb.append(umEnvelope.getSignature());
        sb.append(umEnvelope.getSerial_num());
        sb.append(umEnvelope.ts_secs);
        sb.append(umEnvelope.length);
        sb.append(umEnvelope.getGuid());
        byte[] checkSum = StringTool.md5(sb.toString().getBytes());

        umEnvelope.setCheckSum(StringTool.byte2Hex(checkSum));
        return  umEnvelope;
    }


    private String buildGuid(byte[] var1, int var2, byte[] entity){
        byte[] uidMd5 = StringTool.md5((seedConfig.getDeviceId() + seedConfig.getMac()).getBytes());
        byte[] entityMd5 = StringTool.md5(entity);
        int size = uidMd5.length;
        byte[] ret = new byte[size * 2];

        for(int i = 0; i < size; ++i) {
            ret[i * 2] = entityMd5[i];
            ret[i * 2 + 1] = uidMd5[i];
        }

        byte[] var10 = var1;

        for(int i = 0; i < 2; ++i) {
            ret[i] = var10[i];
            ret[ret.length - i - 1] = var10[var10.length - i - 1];
        }

        byte[] var11 = new byte[]{(byte)(var2 & 255), (byte)(var2 >> 8 & 255), (byte)(var2 >> 16 & 255), (byte)(var2 >>> 24)};

        for(int i = 0; i < ret.length; ++i) {
            ret[i] ^= var11[i % 4];
        }

        return StringTool.byte2Hex(ret);
    }

}
