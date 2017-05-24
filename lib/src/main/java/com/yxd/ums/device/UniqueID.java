package com.yxd.ums.device;

import android.util.Base64;

import com.umeng.analytics.f.IdJournal_b;
import com.umeng.analytics.f.IdSnapshot_c;
import com.umeng.analytics.f.IdTracking;
import com.umeng.tool.EncodeUtil;

import a.a.a.UMBeanPacker;

public class UniqueID {
    private String imei;
    private String android_id;
    private String idmd5;
    private String mac;
    private String serial;
    private String signature;
    public UniqueID(String seed) {
        android_id = seed.substring(8, 24).toLowerCase();
        imei = hex2Dec(seed, 15);
        idmd5 = EncodeUtil.getMD5(imei);

        StringBuilder sb = new StringBuilder();
        sb.append(seed.substring(24, 26)).append(":");
        sb.append(seed.substring(4, 6)).append(":");
        sb.append(seed.substring(8, 10)).append(":");
        sb.append(seed.substring(11, 13)).append(":");
        sb.append(seed.substring(15, 17)).append(":");
        sb.append(seed.substring(27, 29));
        mac = sb.toString().toLowerCase();

        sb = new StringBuilder();
        sb.append(seed.substring(1, 3));
        sb.append(seed.substring(3, 5));
        sb.append(seed.substring(20, 22));
        sb.append(seed.substring(10, 12));
        sb.append(seed.substring(8, 10));
        sb.append(seed.substring(25, 27));
        serial = sb.toString().toLowerCase();


        sb = new StringBuilder();
        sb.append(seed.substring(21, 29));
        sb.append(seed.substring(23, 31));
        sb.append(seed.substring(2, 10));
        sb.append(seed.substring(3, 11));
        sb.append(seed.substring(4, 12));
        sb.append(seed.substring(10, 18));
        sb.append(seed.substring(11, 19));
        sb.append(seed.substring(20, 28));
        signature = sb.toString().toLowerCase();
    }

    public String getImei(){
        return imei;
    }

    public String getIdmd5(){
        return idmd5;
    }

    public String getMacAddress(){
        return mac;
    }

    public String getSignature() {
        return signature;
    }

    public String getIdTracking(){
        try {
            IdTracking idTracking = buildIdTracking();
            byte[] data = (new UMBeanPacker()).pack2Bytes(idTracking);
            String id_tracking = Base64.encodeToString(data, 0);
            return id_tracking;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String hex2Dec(String hex, int len){
        StringBuilder sb = new StringBuilder();
        int start = 1;
        while(sb.length() <= len){
            sb.append(String.format("%5d",Integer.parseInt(hex.substring(start, start+4) , 16)));
            start = start+2;
        }
        return sb.toString().substring(0,15);
    }

    private IdTracking buildIdTracking(){
        IdTracking idTracking = new IdTracking();
        IdSnapshot_c idSnapshot_c5 = new IdSnapshot_c(serial, System.currentTimeMillis(), 1);
        IdJournal_b idJournal_b5 = new IdJournal_b("serial", serial,idSnapshot_c5.getTS());
        idTracking.putSnapshot("serial", idSnapshot_c5);
        idTracking.addIdJournal(idJournal_b5);

        IdSnapshot_c idSnapshot_c4 = new IdSnapshot_c(idmd5, System.currentTimeMillis(), 1);
        IdJournal_b idJournal_b4 = new IdJournal_b("idmd5", idmd5,idSnapshot_c4.getTS());
        idTracking.putSnapshot("idmd5", idSnapshot_c4);
        idTracking.addIdJournal(idJournal_b4);

        IdSnapshot_c idSnapshot_c1 = new IdSnapshot_c(android_id, System.currentTimeMillis(), 1);
        IdJournal_b idJournal_b1 = new IdJournal_b("android_id", android_id,idSnapshot_c1.getTS());
        idTracking.putSnapshot("android_id", idSnapshot_c1);
        idTracking.addIdJournal(idJournal_b1);

        IdSnapshot_c idSnapshot_c2 = new IdSnapshot_c(mac, System.currentTimeMillis(), 1);
        IdJournal_b idJournal_b2 = new IdJournal_b("mac", mac,idSnapshot_c2.getTS());
        idTracking.putSnapshot("mac", idSnapshot_c2);
        idTracking.addIdJournal(idJournal_b2);

        IdSnapshot_c idSnapshot_c3 = new IdSnapshot_c(imei, System.currentTimeMillis(), 1);
        IdJournal_b idJournal_b3 = new IdJournal_b("imei", imei,idSnapshot_c3.getTS());
        idTracking.putSnapshot("imei", idSnapshot_c3);
        idTracking.addIdJournal(idJournal_b3);
        return idTracking;
    }
}
