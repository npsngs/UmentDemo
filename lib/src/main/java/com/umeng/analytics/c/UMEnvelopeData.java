//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import android.content.Context;
import android.content.SharedPreferences;

import com.umeng.analytics.g.UMEnvelope;
import com.umeng.tool.EncodeUtil;
import com.umeng.tool.StringTool;
import com.umeng.tool.SystemUtil;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.d.SP_Util;
import com.umeng.tool.ZipTool;

import java.io.File;
import org.json.JSONObject;

import a.a.a.UMBeanPacker;

public class UMEnvelopeData {
    private final byte[] a = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
    private final int b = 1;
    private final int c = 0;
    private String version = "1.0";
    private String address = null;
    private byte[] signature = null;
    private byte[] guid = null;
    private byte[] checkSum = null;
    private int serial = 0;
    private int timeStamp = 0;
    private int length = 0;
    private byte[] entity = null;
    private byte[] m = null;
    private boolean isCodex = false;

    private UMEnvelopeData(byte[] entity, String address, byte[] var3) throws Exception {
        if(entity != null && entity.length != 0) {
            this.address = address;
            this.length = entity.length;
            this.entity = ZipTool.deflate(entity);
            this.timeStamp = (int)(System.currentTimeMillis() / 1000L);
            this.m = var3;
        } else {
            throw new Exception("entity is null or empty");
        }
    }

    public static String getCachedSignature(Context context) {
        SharedPreferences sp = SP_Util.getSp(context);
        return sp == null?null:sp.getString("signature", null);
    }

    public void setSignature(String signature) {
        this.signature = StringTool.hex2Byte(signature);
    }

    public String getSignature() {
        return StringTool.byte2Hex(this.signature);
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public void setCodex(boolean isCodex) {
        this.isCodex = isCodex;
    }

    public static UMEnvelopeData create(Context context, String address, byte[] entity) {
        try {
            String macAddress = SystemUtil.getMacAddress(context);
            String deviceId = SystemUtil.getDeviceId(context);
            SharedPreferences sp = SP_Util.getSp(context);
            String signature = sp.getString("signature", null);
            int serial = sp.getInt("serial", 1);
            UMEnvelopeData umEnvelopeData = new UMEnvelopeData(entity, address, (deviceId + macAddress).getBytes());
            umEnvelopeData.setSignature(signature);
            umEnvelopeData.setSerial(serial);
            umEnvelopeData.buildDefault();
            sp.edit()
                    .putInt("serial", serial + 1)
                    .putString("signature", umEnvelopeData.getSignature()).apply();
            umEnvelopeData.writeToCache(context);
            return umEnvelopeData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UMEnvelopeData createCodex(Context context, String address, byte[] entity) {
        try {
            String macAddress = SystemUtil.getMacAddress(context);
            String deviceId = SystemUtil.getDeviceId(context);
            SharedPreferences sp = SP_Util.getSp(context);
            String signature = sp.getString("signature", null);
            int serial = sp.getInt("serial", 1);
            UMEnvelopeData umEnvelopeData = new UMEnvelopeData(entity, address, (deviceId + macAddress).getBytes());
            umEnvelopeData.setCodex(true);
            umEnvelopeData.setSignature(signature);
            umEnvelopeData.setSerial(serial);

            umEnvelopeData.buildDefault();
            sp.edit()
                    .putInt("serial", serial + 1)
                    .putString("signature", umEnvelopeData.getSignature())
                    .apply();
            umEnvelopeData.writeToCache(context);
            return umEnvelopeData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void buildDefault() {
        if(this.signature == null) {
            this.signature = this.createDefaultSignature();
        }

        if(this.isCodex) {
            byte[] key = new byte[16];

            try {
                System.arraycopy(this.signature, 1, key, 0, 16);
                this.entity = StringTool.encrypt(this.entity, key);
            } catch (Exception e) {
            }
        }

        this.guid = this.encode(this.signature, this.timeStamp);
        this.checkSum = this.createChecksum();
    }

    private byte[] encode(byte[] var1, int var2) {
        byte[] var3 = StringTool.md5(this.m);
        byte[] var4 = StringTool.md5(this.entity);
        int var5 = var3.length;
        byte[] var6 = new byte[var5 * 2];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7 * 2] = var4[var7];
            var6[var7 * 2 + 1] = var3[var7];
        }

        byte[] var10 = var1;

        for(int i = 0; i < 2; ++i) {
            var6[i] = var10[i];
            var6[var6.length - i - 1] = var10[var10.length - i - 1];
        }

        byte[] var11 = new byte[]{(byte)(var2 & 255), (byte)(var2 >> 8 & 255), (byte)(var2 >> 16 & 255), (byte)(var2 >>> 24)};

        for(int i = 0; i < var6.length; ++i) {
            var6[i] ^= var11[i % 4];
        }

        return var6;
    }

    private byte[] createDefaultSignature() {
        return this.encode(this.a, (int)(System.currentTimeMillis() / 1000L));
    }

    private byte[] createChecksum() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringTool.byte2Hex(this.signature));
        sb.append(this.serial);
        sb.append(this.timeStamp);
        sb.append(this.length);
        sb.append(StringTool.byte2Hex(this.guid));
        return StringTool.md5(sb.toString().getBytes());
    }

    public byte[] envelope() {
        UMEnvelope umEnvelope = new UMEnvelope();
        umEnvelope.setVersion(this.version);
        umEnvelope.setAddress(this.address);
        umEnvelope.setSignature(StringTool.byte2Hex(this.signature));
        umEnvelope.setSerial(this.serial);
        umEnvelope.setTimestamp(this.timeStamp);
        umEnvelope.setLength(this.length);
        umEnvelope.setEntity(this.entity);
        umEnvelope.setCodex(this.isCodex ?1:0);
        umEnvelope.setGuid(StringTool.byte2Hex(this.guid));
        umEnvelope.setCheckSum(StringTool.byte2Hex(this.checkSum));

        try {
            return new UMBeanPacker().pack2Bytes(umEnvelope);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToCache(Context context) {
        String address = this.address;
        String umid = ImprintTool.getInstance(context).getOption().getUmid(null);
        String signature = StringTool.byte2Hex(this.signature);
        byte[] tmp = new byte[16];
        System.arraycopy(this.signature, 2, tmp, 0, 16);
        String signatureHex = StringTool.byte2Hex(StringTool.md5(tmp));

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("appkey", address);
            if(umid != null) {
                jsonObject.put("umid", umid);
            }

            jsonObject.put("signature", signature);
            jsonObject.put("checksum", signatureHex);
            File cacheDir = new File(context.getFilesDir(), ".umeng");
            if(!cacheDir.exists()) {
                cacheDir.mkdir();
            }

            EncodeUtil.writeToFile(new File(cacheDir, "exchangeIdentity.json"), jsonObject.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        try {
            jsonObject = new JSONObject();
            jsonObject.put("appkey", address);
            jsonObject.put("channel", AnalyticsConfig.getChannel(context));
            if(umid != null) {
                jsonObject.put("umid", EncodeUtil.getMD5(umid));
            }

            EncodeUtil.writeToFile(new File(context.getFilesDir(), "exid.dat"), jsonObject.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("version : %getUMengChannel\n", new Object[]{this.version}));
        sb.append(String.format("address : %getUMengChannel\n", new Object[]{this.address}));
        sb.append(String.format("signature : %getUMengChannel\n", new Object[]{StringTool.byte2Hex(this.signature)}));
        sb.append(String.format("serial : %getUMengChannel\n", new Object[]{Integer.valueOf(this.serial)}));
        sb.append(String.format("timestamp : %loadChannel\n", new Object[]{Integer.valueOf(this.timeStamp)}));
        sb.append(String.format("length : %loadChannel\n", new Object[]{Integer.valueOf(this.length)}));
        sb.append(String.format("guid : %getUMengChannel\n", new Object[]{StringTool.byte2Hex(this.guid)}));
        sb.append(String.format("checksum : %getUMengChannel ", new Object[]{StringTool.byte2Hex(this.checkSum)}));
        sb.append(String.format("codex : %loadChannel", new Object[]{Integer.valueOf(this.isCodex ?1:0)}));
        return sb.toString();
    }
}
