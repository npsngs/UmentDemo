package com.yxd.ums;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.umeng.tool.EncodeUtil;
import com.umeng.tool.StringTool;
import com.umeng.tool.SystemUtil;

import org.json.JSONArray;
import org.json.JSONException;
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
    private static final long INTERVAL = 28800000L;
    private SharedPreferences sp;
    private Context context;
    public RemoteConfig(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("ums", Context.MODE_PRIVATE);
        key = new byte[16];
        key[0] = 13;
        key[12] = 2;
    }

    public String getUID(){
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

    private List<DevBean> getFromCache() throws Exception{
        long last = sp.getLong("last", -1);
        long current = System.currentTimeMillis();
        if(current - last > INTERVAL){
            return null;
        }

        String json = getLastConfig(context);
        return parse(json);
    }

    public List<DevBean> getRemoteConfig(){


        HttpURLConnection connection = null;
        try {
            List<DevBean> list = getFromCache();
            if(null != list){
                return list;
            }

            String json = null;

            String uid = getUID();
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

            list = parse(json);


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

    @NonNull
    private List<DevBean> parse(String json) throws JSONException {
        if(TextUtils.isEmpty(json)){
            return null;
        }

        List<DevBean> list;
        list = new ArrayList<>();
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
        sp.edit().putLong("last", System.currentTimeMillis()).apply();

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
