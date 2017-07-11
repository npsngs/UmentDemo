package com.yxd.ums.ios;

import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.g.UMEnvelope;
import com.umeng.tool.StringTool;
import com.umeng.tool.ZipTool;
import com.yxd.ums.DevBean;

import org.json.JSONException;
import org.json.JSONObject;

public class IOSBox {
    private String seed;
    private Context context;
    private IOSConfig iosConfig;
    private String appKey;
    private String channel;
    private String version;
    public IOSBox(Context context, DevBean devBean) {
        this.context = context;
        this.seed = StringTool.byte2Hex(StringTool.md5(devBean.getDeviceID().getBytes())).toLowerCase();
        this.appKey = devBean.getAppKey().toLowerCase();
        this.channel = devBean.getChannel();
        if(TextUtils.isEmpty(channel)){
            channel = "App Store";
        }
        this.version = devBean.getVersion();
        iosConfig = new IOSConfig(context, seed);
    }

    public String getAppKey() {
        return appKey;
    }

    public IOSConfig getIosConfig() {
        return iosConfig;
    }

    public JSONObject buildIosHeader() throws JSONException {
        JSONObject header = new JSONObject();
        header
                .put("os","IOS")
                .put("country","CN")
                .put("language","zh-Hans-CN")
                .put("timezone",8)
                .put("sdk_type","IOS")
                .put("sdk_version","4.1.0")
                .put("app_version",version)
                .put("channel", channel)
                .put("display_name","狮吼")
                .put("req_time", 90 + (int)(Math.random()*20))
                .put("is_pirated", "NO")
                .put("package_name", "com.youxiduo.LionRoaring")

                .put("access", iosConfig.getAccess())
                .put("is_jailbroken", iosConfig.getJailbroken())
                .put("appkey", appKey)
                .put("carrier", iosConfig.getCarrier())
                .put("mccmnc",iosConfig.getMccmnc())
                .put("successful_requests",iosConfig.getSuccessfulRequests())
                .put("device_model", iosConfig.getDeviceModel())
                .put("mc", iosConfig.getMac())
                .put("os_version", iosConfig.getOsVersion())
                .put("access_subtype", iosConfig.getAccess_subtype())
                .put("resolution", iosConfig.getResolution())
                .put("device_id", iosConfig.getDeviceId())
                .put("id_tracking", iosConfig.getIdTracking());

        String imprint = iosConfig.getImprint();
        if(!TextUtils.isEmpty(imprint)){
            header.put("imprint", iosConfig.getImprint());
        }

        return header;
    }


    private String buildGuid(byte[] var1, int var2, byte[] entity){
        byte[] uidMd5 = StringTool.md5((iosConfig.getDeviceId() + iosConfig.getMac()).getBytes());
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

    public UMEnvelope buildIosEnvelope() throws Exception {
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

    public JSONObject buildNoBodyEntity() throws JSONException {
        JSONObject entity = new JSONObject();
        entity.put("header", buildIosHeader());
        return entity;
    }

    public UMEnvelope buildEnvelope(byte[] entity) throws Exception {
        if(entity == null || StringTool.isTooLong(context, entity)) {
            return null;
        }

        UMEnvelope umEnvelope = new UMEnvelope();
        umEnvelope.setVersion("V1.0");
        umEnvelope.setAddress(appKey);
        iosConfig.addSuccessfulRequests();
        umEnvelope.setSerial(iosConfig.getSuccessfulRequests());
        umEnvelope.setTimestamp((int)(System.currentTimeMillis() / 1000L));
        umEnvelope.setLength(entity.length);
        entity = ZipTool.deflate(entity);
        umEnvelope.setEntity(entity);
        umEnvelope.setCodex(0);

        umEnvelope.setSignature(iosConfig.getSignature());
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


    public JSONObject buildActiveEntity() throws JSONException {
        JSONObject entity = new JSONObject();
        JSONObject boby = new JSONObject();
        JSONObject msg = new JSONObject();
        msg.put("ts", System.currentTimeMillis());
        boby.put("activate_msg", msg);

        entity.put("body", boby);
        entity.put("header", buildIosHeader());
        return entity;
    }
}
