package com.yxd.ums.ios;

import android.util.Base64;

import com.umeng.analytics.f.IdJournal_b;
import com.umeng.analytics.f.IdSnapshot_c;
import com.umeng.analytics.f.IdTracking;

public class IOSUniqueID {
    private String deviceId;
    private String idfv;
    private String idfa;
    private String utdid;

    private String mac;
    private String signature;
    public IOSUniqueID(String seed) {
        deviceId = seed + seed.substring(10, 18);

        StringBuilder sb = new StringBuilder();
        sb.append(seed.substring(24, 26)).append(":");
        sb.append(seed.substring(4, 6)).append(":");
        sb.append(seed.substring(8, 10)).append(":");
        sb.append(seed.substring(11, 13)).append(":");
        sb.append(seed.substring(15, 17)).append(":");
        sb.append(seed.substring(27, 29));
        mac = sb.toString().toLowerCase();

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

        sb = new StringBuilder();
        sb.append(seed.substring(24, 32));
        sb.append("-");
        sb.append(seed.substring(20, 24));
        sb.append("-");
        sb.append(seed.substring(4, 8));
        sb.append("-");
        sb.append(seed.substring(0, 4));
        sb.append("-");
        sb.append(seed.substring(8, 20));
        idfv = sb.toString().toUpperCase();

        sb = new StringBuilder();
        sb.append(seed.substring(4, 12));
        sb.append("-");
        sb.append(seed.substring(8, 12));
        sb.append("-");
        sb.append(seed.substring(28, 32));
        sb.append("-");
        sb.append(seed.substring(24, 28));
        sb.append("-");
        sb.append(seed.substring(12, 24));
        idfa = sb.toString().toUpperCase();

        utdid = new String(Base64.encode(seed.substring(2, 20).getBytes(), Base64.DEFAULT));
    }

    public String getMacAddress(){
        return mac;
    }

    public String getSignature() {
        return signature;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public String getIdfv() {
        return idfv;
    }

    public String getIdfa() {
        return idfa;
    }

    public String getUtdid() {
        return utdid;
    }

    public String getIdTracking(){
        try {
            IdTracking idTracking = buildIdTracking();
            byte[] data = (new UMBeanPacker_old()).pack2Bytes(idTracking);
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
        IdSnapshot_c idSnapshot_c5 = new IdSnapshot_c(idfv, System.currentTimeMillis(), 1);
        IdJournal_b idJournal_b5 = new IdJournal_b("idfv", idfv,idSnapshot_c5.getTS());
        idTracking.putSnapshot("idfv", idSnapshot_c5);
        idTracking.addIdJournal(idJournal_b5);

        IdSnapshot_c idSnapshot_c4 = new IdSnapshot_c(utdid, System.currentTimeMillis(), 1);
        IdJournal_b idJournal_b4 = new IdJournal_b("utdid", utdid,idSnapshot_c4.getTS());
        idTracking.putSnapshot("utdid", idSnapshot_c4);
        idTracking.addIdJournal(idJournal_b4);

        IdSnapshot_c idSnapshot_c1 = new IdSnapshot_c(deviceId, System.currentTimeMillis(), 1);
        IdJournal_b idJournal_b1 = new IdJournal_b("oid", deviceId,idSnapshot_c1.getTS());
        idTracking.putSnapshot("oid", idSnapshot_c1);
        idTracking.addIdJournal(idJournal_b1);

        IdSnapshot_c idSnapshot_c2 = new IdSnapshot_c(mac, System.currentTimeMillis(), 1);
        IdJournal_b idJournal_b2 = new IdJournal_b("mc", mac,idSnapshot_c2.getTS());
        idTracking.putSnapshot("mc", idSnapshot_c2);
        idTracking.addIdJournal(idJournal_b2);

        IdSnapshot_c idSnapshot_c3 = new IdSnapshot_c(idfa, System.currentTimeMillis(), 1);
        IdJournal_b idJournal_b3 = new IdJournal_b("idfa", idfa,idSnapshot_c3.getTS());
        idTracking.putSnapshot("idfa", idSnapshot_c3);
        idTracking.addIdJournal(idJournal_b3);
        return idTracking;
    }
}
