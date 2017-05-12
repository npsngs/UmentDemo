//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.UMConst;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import javax.microedition.khronos.opengles.GL10;

public class SystemUtil {
    protected static final String tag = SystemUtil.class.getName();
    public static final String b = "";
    public static final String c = "2G/3G";
    public static final String d = "Wi-Fi";
    public static final int e = 8;
    private static final String f_str = "ro.miui.ui.version.name";

    public SystemUtil() {
    }

    public static String getVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            return String.valueOf(versionCode);
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static String getVersionName(Context context) {
        try {
            PackageInfo var1 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return var1.versionName;
        } catch (NameNotFoundException var2) {
            return "";
        }
    }

    public static boolean hasPermission(Context context, String permissionName) {
        boolean hasPermission = false;
        if(VERSION.SDK_INT >= 23) {
            try {
                Class contextClass = Class.forName("android.content.Context");
                Method checkSelfPermission = contextClass.getMethod("checkSelfPermission", new Class[]{String.class});
                int var5 = ((Integer)checkSelfPermission.invoke(context, new Object[]{permissionName})).intValue();
                if(var5 == 0) {
                    hasPermission = true;
                } else {
                    hasPermission = false;
                }
            } catch (Throwable t) {
                hasPermission = false;
            }
        } else {
            PackageManager packageManager = context.getPackageManager();
            if(packageManager.checkPermission(permissionName, context.getPackageName()) == 0) {
                hasPermission = true;
            }
        }

        return hasPermission;
    }

    public static String[] getGL10Value(GL10 gl10) {
        try {
            String[] var1 = new String[2];
            String var2 = gl10.glGetString(7936);
            String var3 = gl10.glGetString(7937);
            var1[0] = var2;
            var1[1] = var3;
            return var1;
        } catch (Throwable throwable) {
            return new String[0];
        }
    }

    private static String getHardwareAddress() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();

            NetworkInterface networkInterface;
            do {
                if(!networkInterfaces.hasMoreElements()) {
                    return null;
                }

                networkInterface = (NetworkInterface)networkInterfaces.nextElement();
            } while(!"wlan0".equals(networkInterface.getName()) && !"eth0".equals(networkInterface.getName()));

            byte[] hardwareAddress = networkInterface.getHardwareAddress();
            if(hardwareAddress != null && hardwareAddress.length != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                byte[] bytes = hardwareAddress;
                int length = hardwareAddress.length;

                for(int i = 0; i < length; ++i) {
                    byte b = bytes[i];
                    stringBuilder.append(String.format("%02X:", new Object[]{Byte.valueOf(b)}));
                }

                if(stringBuilder.length() > 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }

                return stringBuilder.toString().toLowerCase(Locale.getDefault());
            } else {
                return null;
            }
        } catch (Throwable t) {
            return null;
        }
    }

    private static String getMacFromFiles() {
        try {
            String[] addressFileNames = new String[]{"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};

            for(int i = 0; i < addressFileNames.length; ++i) {
                try {
                    String mac = readFile(addressFileNames[i]);
                    if(mac != null) {
                        return mac;
                    }
                } catch (Throwable t1) {
                }
            }
        } catch (Throwable t2) {
        }
        return null;
    }

    private static String readFile(String fileName) {
        String fileContent = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = null;
            if(fileReader != null) {
                try {
                    bufferedReader = new BufferedReader(fileReader, 1024);
                    fileContent = bufferedReader.readLine();
                } finally {
                    if(fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable throwable) {
                        }
                    }

                    if(bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable t1) {
                        }
                    }

                }
            }
        } catch (Throwable t3) {
        }

        return fileContent;
    }

    public static String getCPUInfo() {
        String lineStr = null;
        FileReader fileReader;
        BufferedReader bufferedReader;


        try {
            fileReader = new FileReader("/proc/cpuinfo");
            if(fileReader != null) {
                try {
                    bufferedReader = new BufferedReader(fileReader, 1024);
                    lineStr = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (Throwable throwable) {
                    ULog.e(tag, "Could not read from file /proc/cpuinfo", throwable);
                }
            }
        } catch (FileNotFoundException e) {
            ULog.e(tag, "Could not open file /proc/cpuinfo", e);
        }

        if(lineStr != null) {
            int var3 = lineStr.indexOf(58) + 1;
            lineStr = lineStr.substring(var3);
            return lineStr.trim();
        } else {
            return "";
        }
    }

    public static String getDeviceId(Context context) {
        return EScenarioType.E_UM_ANALYTICS_OEM.toValue() != AnalyticsConfig.getVerticalType(context) && EScenarioType.E_UM_GAME_OEM.toValue() != AnalyticsConfig.getVerticalType(context)? getDeviceID1(context): getDeviceID2(context);
    }

    public static String get5DeviceIdInMD5(Context context) {
        return EncodeUtil.getMD5(getDeviceId(context));
    }

    public static String getProperties(Context context) {
        if(getSubscribeID(context) == null) {
            return null;
        } else {
            int mcc = context.getResources().getConfiguration().mcc;
            int mnc = context.getResources().getConfiguration().mnc;
            if(mcc != 0) {
                String var3 = String.valueOf(mnc);
                if(mnc < 10) {
                    var3 = String.format("%02d", new Object[]{Integer.valueOf(mnc)});
                }

                return mcc + var3;
            } else {
                return null;
            }
        }
    }

    public static String getSubscribeID(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String subscriberID = null;
        if(hasPermission(context, "android.permission.READ_PHONE_STATE")) {
            subscriberID = telephonyManager.getSubscriberId();
        }

        return subscriberID;
    }

    public static String getNetworkOperator(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = null;
        if(hasPermission(context, "android.permission.READ_PHONE_STATE")) {
            networkOperator = telephonyManager.getNetworkOperator();
        }

        return networkOperator;
    }

    public static String getNetworkOperatorName(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            if(hasPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
        } catch (Throwable throwable) {
        }

        return "";
    }

    public static String getScreenSize(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int widthPixels = displayMetrics.widthPixels;
            int heightPixels = displayMetrics.heightPixels;
            String sizeStr = heightPixels + "*" + widthPixels;
            return sizeStr;
        } catch (Throwable t) {
            return "";
        }
    }

    public static String[] getCurrentNetwork(Context context) {
        String[] ret = new String[]{"", ""};

        try {
            if(!hasPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ret[0] = "";
                return ret;
            }

            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager == null) {
                ret[0] = "";
                return ret;
            }

            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if(networkInfo != null && networkInfo.getState() == State.CONNECTED) {
                ret[0] = "Wi-Fi";
                return ret;
            }

            NetworkInfo var4 = connectivityManager.getNetworkInfo(0);
            if(var4 != null && var4.getState() == State.CONNECTED) {
                ret[0] = "2G/3G";
                ret[1] = var4.getSubtypeName();
                return ret;
            }
        } catch (Throwable t) {
        }

        return ret;
    }

    public static boolean isCurrentWifiConnect(Context context) {
        return "Wi-Fi".equals(getCurrentNetwork(context)[0]);
    }

    public static boolean isConnectState(Context var0) {
        try {
            if(hasPermission(var0, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager != null) {
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    if(networkInfo != null) {
                        return networkInfo.isConnectedOrConnecting();
                    }
                }
            }
        } catch (Throwable t) {
        }
        return false;
    }

    public static int getTimeZone(Context context) {
        try {
            Locale locale = getLocale(context);
            Calendar calendar = Calendar.getInstance(locale);
            if(calendar != null) {
                return calendar.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Throwable t) {
            ULog.c(tag, "error in getTimeZone", t);
        }

        return 8;
    }

    public static boolean isInChina(Context context) {
        String country = com.umeng.analytics.c.h.getInstance(context).getOption().getCountry("");
        if(!TextUtils.isEmpty(country)) {
            return country.equals("cn");
        } else {
            if(getSubscribeID(context) == null) {
                country = getLocalInfo(context)[0];
                if(!TextUtils.isEmpty(country) && country.equalsIgnoreCase("cn")) {
                    return true;
                }
            } else {
                int mcc = context.getResources().getConfiguration().mcc;
                if(mcc == 460 || mcc == 461) {
                    return true;
                }

                if(mcc == 0) {
                    country = getLocalInfo(context)[0];
                    if(!TextUtils.isEmpty(country) && country.equalsIgnoreCase("cn")) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static String[] getLocalInfo(Context context) {
        String[] ret = new String[2];

        try {
            Locale locale = getLocale(context);
            if(locale != null) {
                ret[0] = locale.getCountry();
                ret[1] = locale.getLanguage();
            }

            if(TextUtils.isEmpty(ret[0])) {
                ret[0] = "Unknown";
            }

            if(TextUtils.isEmpty(ret[1])) {
                ret[1] = "Unknown";
            }

            return ret;
        } catch (Throwable var3) {
            ULog.e(tag, "error in getLocaleInfo", var3);
            return ret;
        }
    }

    private static Locale getLocale(Context context) {
        Locale locale = null;

        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            System.getConfiguration(context.getContentResolver(), configuration);
            if(configuration != null) {
                locale = configuration.locale;
            }
        } catch (Throwable throwable) {
            ULog.c(tag, new Object[]{"fail to read user config locale"});
        }

        if(locale == null) {
            locale = Locale.getDefault();
        }

        return locale;
    }

    public static String getUMengAppKey(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if(applicationInfo != null) {
                String appkey = applicationInfo.metaData.getString("UMENG_APPKEY");
                if(appkey != null) {
                    return appkey.trim();
                }

                ULog.c(tag, new Object[]{"getAppkey failed. the applicationinfo is null!"});
            }
        } catch (Throwable throwable) {
            ULog.e(tag, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", throwable);
        }

        return null;
    }

    public static String getMacAddress(Context context) {
        String macAddress;
        if(VERSION.SDK_INT < 23) {
            macAddress = getWifiMacAddress(context);
        } else if(VERSION.SDK_INT == 23) {
            macAddress = getHardwareAddress();
            if(TextUtils.isEmpty(macAddress)) {
                if(UMConst.isCheckDevice) {
                    macAddress = getMacFromFiles();
                } else {
                    macAddress = getWifiMacAddress(context);
                }
            }
        } else {
            macAddress = getHardwareAddress();
            if(TextUtils.isEmpty(macAddress)) {
                macAddress = getWifiMacAddress(context);
            }
        }

        return macAddress;
    }

    private static String getWifiMacAddress(Context context) {
        try {
            WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            if(hasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                return wifiInfo.getMacAddress();
            } else {
                return "";
            }
        } catch (Throwable t) {
            return "";
        }
    }

    public static int[] getNoncompatScreenSize(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int w = -1;
            int h = -1;
            if((context.getApplicationInfo().flags & 8192) == 0) {
                w = getFieldValue(displayMetrics, "noncompatWidthPixels");
                h = getFieldValue(displayMetrics, "noncompatHeightPixels");
            }

            if(w == -1 || h == -1) {
                w = displayMetrics.widthPixels;
                h = displayMetrics.heightPixels;
            }

            int[] size = new int[2];
            if(w > h) {
                size[0] = h;
                size[1] = w;
            } else {
                size[0] = w;
                size[1] = h;
            }

            return size;
        } catch (Throwable t) {
            return null;
        }
    }

    private static int getFieldValue(Object o, String fieldName) {
        try {
            Field field = DisplayMetrics.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.getInt(o);
        } catch (Throwable throwable) {
            return -1;
        }
    }

    public static String getUMengChannel(Context context) {
        String umengChannel = "Unknown";

        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if(applicationInfo != null && applicationInfo.metaData != null) {
                Object o = applicationInfo.metaData.get("UMENG_CHANNEL");
                if(o != null) {
                    String channel = o.toString();
                    if(channel != null) {
                        umengChannel = channel;
                    }
                }
            }
        } catch (Throwable t) {
        }

        return umengChannel;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getAppSignatures(Context context) {
        String signatureStr = null;

        try {
            PackageManager packageManager = context.getPackageManager();
            byte flag = 64;
            PackageInfo info = packageManager.getPackageInfo(getPackageName(context), flag);
            Signature[] signatures = info.signatures;
            byte[] bytes = signatures[0].toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
            X509Certificate x509Certificate = (X509Certificate)certificateFactory.generateCertificate(bais);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] data = messageDigest.digest(x509Certificate.getEncoded());
            signatureStr = getHexStr(data);
        } catch (Throwable t) {
        }

        return signatureStr;
    }

    private static String getHexStr(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder(data.length * 2);

        for(int i = 0; i < data.length; ++i) {
            String hexStr = Integer.toHexString(data[i]);
            int length = hexStr.length();
            if(length == 1) {
                hexStr = "0" + hexStr;
            }

            if(length > 2) {
                hexStr = hexStr.substring(length - 2, length);
            }

            stringBuilder.append(hexStr.toUpperCase(Locale.getDefault()));
            if(i < data.length - 1) {
                stringBuilder.append(':');
            }
        }

        return stringBuilder.toString();
    }

    public static String getApplicationLabel(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static String loadLabel(Context context) {
        String label = null;

        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            label = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable t) {
        }

        return label;
    }

    private static String getDeviceID1(Context context) {
        String deviceId;
        if(VERSION.SDK_INT < 23) {
            deviceId = getTelephonyDeviceID(context);
            if(TextUtils.isEmpty(deviceId)) {
                deviceId = getWifiMacAddress(context);
                if(TextUtils.isEmpty(deviceId)) {
                    deviceId = Secure.getString(context.getContentResolver(), "android_id");
                    if(TextUtils.isEmpty(deviceId)) {
                        deviceId = getSerialNumber();
                    }
                }
            }
        } else if(VERSION.SDK_INT == 23) {
            deviceId = getTelephonyDeviceID(context);
            if(TextUtils.isEmpty(deviceId)) {
                deviceId = getHardwareAddress();
                if(TextUtils.isEmpty(deviceId)) {
                    if(UMConst.isCheckDevice) {
                        deviceId = getMacFromFiles();
                    } else {
                        deviceId = getWifiMacAddress(context);
                    }
                }

                if(TextUtils.isEmpty(deviceId)) {
                    deviceId = Secure.getString(context.getContentResolver(), "android_id");
                    if(TextUtils.isEmpty(deviceId)) {
                        deviceId = getSerialNumber();
                    }
                }
            }
        } else {
            deviceId = getTelephonyDeviceID(context);
            if(TextUtils.isEmpty(deviceId)) {
                deviceId = getSerialNumber();
                if(TextUtils.isEmpty(deviceId)) {
                    deviceId = Secure.getString(context.getContentResolver(), "android_id");
                    if(TextUtils.isEmpty(deviceId)) {
                        deviceId = getHardwareAddress();
                        if(TextUtils.isEmpty(deviceId)) {
                            deviceId = getWifiMacAddress(context);
                        }
                    }
                }
            }
        }

        return deviceId;
    }

    private static String getDeviceID2(Context context) {
        String deviceId;
        if(VERSION.SDK_INT < 23) {
            deviceId = Secure.getString(context.getContentResolver(), "android_id");
            if(TextUtils.isEmpty(deviceId)) {
                deviceId = getWifiMacAddress(context);
                if(TextUtils.isEmpty(deviceId)) {
                    deviceId = getSerialNumber();
                    if(TextUtils.isEmpty(deviceId)) {
                        deviceId = getTelephonyDeviceID(context);
                    }
                }
            }
        } else if(VERSION.SDK_INT == 23) {
            deviceId = Secure.getString(context.getContentResolver(), "android_id");
            if(TextUtils.isEmpty(deviceId)) {
                deviceId = getHardwareAddress();
                if(TextUtils.isEmpty(deviceId)) {
                    if(UMConst.isCheckDevice) {
                        deviceId = getMacFromFiles();
                    } else {
                        deviceId = getWifiMacAddress(context);
                    }
                }

                if(TextUtils.isEmpty(deviceId)) {
                    deviceId = getSerialNumber();
                    if(TextUtils.isEmpty(deviceId)) {
                        deviceId = getTelephonyDeviceID(context);
                    }
                }
            }
        } else {
            deviceId = Secure.getString(context.getContentResolver(), "android_id");
            if(TextUtils.isEmpty(deviceId)) {
                deviceId = getSerialNumber();
                if(TextUtils.isEmpty(deviceId)) {
                    deviceId = getTelephonyDeviceID(context);
                    if(TextUtils.isEmpty(deviceId)) {
                        deviceId = getHardwareAddress();
                        if(TextUtils.isEmpty(deviceId)) {
                            deviceId = getWifiMacAddress(context);
                        }
                    }
                }
            }
        }

        return deviceId;
    }

    private static String getTelephonyDeviceID(Context context) {
        String telephonyID = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(telephonyManager != null) {
            try {
                if(hasPermission(context, "android.permission.READ_PHONE_STATE")) {
                    telephonyID = telephonyManager.getDeviceId();
                }
            } catch (Throwable t) {
            }
        }

        return telephonyID;
    }

    private static String getSerialNumber() {
        String serialNumber = "";
        if(VERSION.SDK_INT >= 9) {
            serialNumber = Build.SERIAL;
        }

        return serialNumber;
    }

    public static String getSystemName(Context context) {
        String systemName;
        Properties properties = getProperties();

        try {
            systemName = properties.getProperty("ro.miui.ui.version.name");
            if(TextUtils.isEmpty(systemName)) {
                if(hasSmartBar()) {
                    systemName = "Flyme";
                } else if(!TextUtils.isEmpty(getYunOSVersionStr(properties))) {
                    systemName = "YunOS";
                }
            } else {
                systemName = "MIUI";
            }
        } catch (Throwable throwable) {
            systemName = null;
        }

        return systemName;
    }

    public static String getOsVersion(Context context) {
        String osVersion;
        Properties properties = getProperties();

        try {
            osVersion = properties.getProperty("ro.miui.ui.version.name");
            if(TextUtils.isEmpty(osVersion)) {
                if(hasSmartBar()) {
                    try {
                        osVersion = getFlymeVersionName(properties);
                    } catch (Throwable t) {
                    }
                } else {
                    try {
                        osVersion = getYunOSVersionStr(properties);
                    } catch (Throwable throwable) {
                    }
                }
            }
        } catch (Throwable t) {
            osVersion = null;
        }

        return osVersion;
    }

    private static String getYunOSVersionStr(Properties properties) {
        String var1 = properties.getProperty("ro.yunos.version");
        return !TextUtils.isEmpty(var1)?var1:null;
    }

    private static String getFlymeVersionName(Properties properties) {
        try {
            String var1 = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if(var1.contains("flyme os")) {
                return var1.split(" ")[2];
            }
        } catch (Throwable t) {
        }

        return null;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            properties.load(fis);
        } catch (Throwable t) {
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (Throwable t2) {
                }
            }

        }

        return properties;
    }

    private static boolean hasSmartBar() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static String getDeviceType(Context context) {
        String type = "Phone";
        if(context == null) {
            return type;
        } else {
            boolean var2 = (context.getResources().getConfiguration().screenLayout & 15) >= 3;
            if(var2) {
                type = "Tablet";
            } else {
                type = "Phone";
            }

            return type;
        }
    }

    public static String getAndroidID(Context context) {
        String id = null;

        try {
            if(context != null) {
                TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                if(telephonyManager != null && hasPermission(context, "android.permission.READ_PHONE_STATE")) {
                    id = telephonyManager.getDeviceId();
                }

                if(TextUtils.isEmpty(id)) {
                    id = Secure.getString(context.getContentResolver(), "android_id");
                    if(TextUtils.isEmpty(id) && VERSION.SDK_INT >= 9) {
                        id = Build.SERIAL;
                    }
                }
            }
        } catch (Throwable t) {
        }

        return id;
    }
}
