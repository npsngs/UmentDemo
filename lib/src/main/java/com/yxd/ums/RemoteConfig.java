package com.yxd.ums;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.umeng.tool.EncodeUtil;
import com.umeng.tool.StringTool;
import com.umeng.tool.SystemUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RemoteConfig {
    public RemoteConfig() {
        key = new byte[16];
        key[0] = 13;
        key[12] = 2;
    }

    public String getUID(Context context){
        SharedPreferences sp = context.getSharedPreferences("ums", Context.MODE_PRIVATE);
        String uid = sp.getString("uid", null);
        if(uid != null){
            return uid;
        }

        String serial = Build.VERSION.SDK_INT >= 9?Build.SERIAL:null;
        String mac = SystemUtil.getMacAddress(context);
        String android_id = Settings.Secure.getString(context.getContentResolver(), "android_id");
        String brand = Build.BRAND;
        String imei = getImei(context);

        StringBuilder sb = new StringBuilder();
        sb.append(serial!=null?serial:"null");
        sb.append(brand!=null?brand:"null");
        sb.append(imei!=null?imei:"null");
        sb.append(android_id!=null?android_id:"null");
        sb.append(mac!=null?mac:"null");


        uid = EncodeUtil.getMD5(sb.toString());
        sp.edit().putString("uid", uid).apply();
        return uid;
    }

    public List<DevBean> getRemoteConfig(Context context){
        HttpURLConnection connection = null;
        try {
            String json = null;

            String uid = getUID(context);
            String url = String.format("http://pmp.youxiduo.com/umworker2/sim_dev_req?real_dev_id=%s", uid);
            connection = (HttpURLConnection)(new URL(url)).openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            if(Build.VERSION.SDK_INT < 8) {
                System.setProperty("http.keepAlive", "false");
            }
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(responseCode != 200) {
                json = getLastConfig(context);
            }else{
                InputStream is = connection.getInputStream();
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int ret;
                    while (-1 != (ret = is.read(buffer))){
                        bos.write(buffer, 0 ,ret);
                    }
                    is.close();

                    byte[] data = bos.toByteArray();
                    if(data !=null && data.length > 0){
                        json = new String(data, "utf-8");
                    }
                    bos.close();

                    if(json != null){
                        setLastConfig(context, json);
                    }
                } finally {
                    EncodeUtil.close(is);
                }
            }

            if(null == json){
                return null;
            }

            List<DevBean> list = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DevBean devBean = new DevBean();
                devBean.setDeviceID(jsonObject.getString("devId"));
                devBean.setAppKey(jsonObject.getString("appKey"));
                devBean.setOsType(jsonObject.getString("osType"));
                list.add(devBean);
            }
            return list;
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }

    private byte[] key;
    public String getLastConfig(Context context) throws Exception{
        File cacheDir = context.getCacheDir();
        File cacheFile = new File(cacheDir, "abs");
        if(!cacheFile.exists()){
            return null;
        }

        FileInputStream fis = new FileInputStream(cacheFile);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int ret;
        while (-1 != (ret = fis.read(buffer))){
            bos.write(buffer, 0, ret);
        }
        fis.close();

        byte[] data = bos.toByteArray();
        data = StringTool.decrypt(data, key);

        bos.close();
        String s = new String(data, "utf-8");
        return s;
    }

    public void setLastConfig(Context context, String s) throws Exception{
        File cacheDir = context.getCacheDir();
        File cacheFile = new File(cacheDir, "abs");
        if(!cacheFile.exists()){
            cacheFile.createNewFile();
        }

        byte[] data = s.getBytes("utf-8");
        data = StringTool.encrypt(data, key);
        FileOutputStream fos = new FileOutputStream(cacheFile);
        fos.write(data);
        fos.flush();
        fos.close();
    }










    private String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(telephonyManager == null) {
            return null;
        }

        String deviceID = null;
        try {
            if(SystemUtil.hasPermission(context, "android.permission.READ_PHONE_STATE")) {
                deviceID = telephonyManager.getDeviceId();
            }
        } catch (Exception e) {
        }
        return deviceID;
    }
}
