package com.forthe.deviceob;

import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.yxd.sum.MSimulater;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StringBuilder sb = new StringBuilder();

        sb.append("device_model").append(":\t").append(Build.MODEL).append("\n");
        sb.append("device_board").append(":\t").append(Build.BOARD).append("\n");
        sb.append("device_brand").append(":\t").append(Build.BRAND).append("\n");
        sb.append("device_manutime").append(":\t").append(Build.TIME).append("\n");
        sb.append("device_manufacturer").append(":\t").append(Build.MANUFACTURER).append("\n");
        sb.append("device_manuid").append(":\t").append(Build.ID).append("\n");
        sb.append("device_name").append(":\t").append(Build.DEVICE).append("\n");
        sb.append("cpu").append(":\t").append(SystemUtil.getCPUInfo()).append("\n");
        sb.append("device_id").append(":\t").append(SystemUtil.getTelephonyDeviceID(this)).append("\n");
        sb.append("mac").append(":\t").append(SystemUtil.getWifiMacAddress(this)).append("\n");
        sb.append("deviceId").append(":\t").append(Settings.Secure.getString(getContentResolver(), "android_id")).append("\n");
        sb.append("serial").append(":\t").append(Build.SERIAL).append("\n");
        sb.append("mc").append(":\t").append(SystemUtil.getMacFromFiles()).append("\n");

        String var13 = SystemUtil.getSystemName(this);
        if(!TextUtils.isEmpty(var13)) {
            sb.append("sub_os_name").append(":\t").append(var13).append("\n");
        }

        String var14 = SystemUtil.getOsVersion(this);
        if(!TextUtils.isEmpty(var14)) {
            sb.append("sub_os_version").append(":\t").append(var14).append("\n");
        }

        Log.e("Device", "\n"+sb.toString());


        MSimulater.send(this, "453281233280432");
        MSimulater.send(this, "453285111280432");
        MSimulater.send(this, "453285144210432");

        byte[] bas = Base64.decode("WFS+eAKduu8DAOa3huf5zO+O", 0);
        Log.e("Base64", bas.length+"");
    }

}
