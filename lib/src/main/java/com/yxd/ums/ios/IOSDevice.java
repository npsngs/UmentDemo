package com.yxd.ums.ios;

import com.yxd.ums.SeedTool;

public class IOSDevice {
    private static final String[][] MCCMNC = {
        {"46000", "中国移动"},
        {"46001", "中国联通"},
        {"46002", "中国移动"},
        {"46003", "中国电信"},
        {"46005", "中国电信"},
        {"46006", "中国联通"},
        {"46007", "中国移动"},
        {"46001", "中国移动"},
        {"46001", "中国联通"}
    };

    private static final String[][] ACCESS = {
            {"wifi", ""},
            {"4G", "LTE"},
            {"3G", "HSDPA"},
            {"2G", "GPRS "},
    };

    private static final int[][] ACCESS_R = {
            {10000, 1902},
            {1901, 82},
            {81, 2},
            {1, 0}
    };


    private static final String[] OS_VERSION= {
            "10.3.1",
            "10.2.1",
            "10.2",
            "10.3.2",
            "10.1.1",
            "10.0.2",
            "9.3.5",
            "9.3.2",
            "8.1",
            "9.2.1",
            "10.0.1",
            "10.3.2",
            "10.3.1"
    };

    private static final int[][] VERSION_R= {
            {10000, 6368},
            {6367, 5114},
            {5113, 4413},
            {4412, 3447},
            {3447, 2906},
            {2905, 2571},
            {2570, 2267},
            {2266, 2083},
            {2082, 1927},
            {1926, 1800},
            {1799, 1705},
            {1704, 800},
            {799, 0},
    };

    private static final String[] JAILBROKEN= {
            "NO", "YES"
    };

    /*private static final String[][] DEVICE = {
            {"iPhone 5",     "640x1136", "iPhone 5,1"},
            {"iPhone 5",     "640x1136", "iPhone 5,2"},
            {"iPhone 5c",    "640x1136", "iPhone 5,3"},
            {"iPhone 5c",    "640x1136", "iPhone 5,4"},
            {"iPhone 5s",    "640x1136", "iPhone 6,1"},
            {"iPhone 5s",    "640x1136", "iPhone 6,2"},
            {"iPhone 6",     "750x1334", "iPhone 7,2"},
            {"iPhone 6Plus", "1242x2208","iPhone 7,1"},
            {"iPhone 6s",    "750x1334", "iPhone 8,1"},
            {"iPhone 6sPlus","1242x2208","iPhone 8,2"},
            {"iPhone 7",     "750x1334", "iPhone 9,1"},
            {"iPhone 7Plus", "1242x2208","iPhone 9,2"},
    };*/


    private static final String[][] DEVICE = {
            {"iPhone 6s",       "1334*750"},
            {"iPhone 6",        "1334*750"},
            {"iPhone 6sPlus",   "2208*1242"},
            {"iPhone 7Plus",    "2208*1242"},
            {"iPhone 6Plus",    "2208*1242"},
            {"iPhone 7",        "1334*750"},
            {"iPhone 5s Global","1136*640"},
            {"iPad Air 2 (WiFi)","2048*1536"},
            {"iPhone 5 CDMA",   "1136*640"},
            {"iPhone SE",       "1136*640"},
            {"iPad mini 2G Wi-Fi","1024*768"},
            {"iPhone 5s GSM",  "1136*640"},
            {"iPad mini 4 (WiFi)","2048*1536"},
            {"iPhone 4S",       "960*640"},
            {"iPad 4 Wi-Fi",    "2048*1536"},
            {"iPhone 6s",       "1334*750"}
    };


    private static final int[][] DEVICE_R = {
            {10000, 8013},
            {8012, 6507},
            {6506, 5101},
            {5100, 3750},
            {3750, 2776},
            {2775, 1951},
            {1950, 1455},
            {1454, 1255},
            {1254, 1135},
            {1134, 1016},
            {1015, 918},
            {917, 826},
            {825, 756},
            {755, 676},
            {675, 633},
            {632, 0}
    };

    private int mccmncIndex, accessIndex, osIndex, deviceIndex, jailbroken;
    public IOSDevice(String seed) {
        int seedInt = Math.abs(SeedTool.seed2Int(seed));
        this.deviceIndex = findIndex(DEVICE_R, seedInt % 10000);

        seedInt = Integer.parseInt(seed.substring(12,16), 16);
        this.osIndex = findIndex(VERSION_R, seedInt % 10000);

        seedInt = (int) (Math.random()*10000);
        this.accessIndex = findIndex(ACCESS_R, seedInt);

        seedInt = Math.abs(seed.charAt(2));
        this.mccmncIndex = seedInt % MCCMNC.length;
        seedInt = Integer.parseInt(seed.substring(0,2), 16);
        jailbroken = seedInt < 248?0:1;
    }
    private int findIndex(int[][] r, int n){
        if(n >10000 || n < 0){
            return 0;
        }
        int i = r.length/2;
        while (true){
            if(n > r[i][0]){
                i--;
            }else if(n < r[i][1]){
                i++;
            }else{
                return i;
            }
        }
    }



    public String getDeviceModel(){
        return DEVICE[deviceIndex][0];
    }

    public String getResolution(){
        return DEVICE[deviceIndex][1];
    }

    public String getOsVersion(){
        return OS_VERSION[osIndex];
    }

    public String getAccessSubtype(){
        return ACCESS[accessIndex][1];
    }

    public String getAccess(){
        return ACCESS[accessIndex][0];
    }

    public String getMccmnc(){
        return MCCMNC[mccmncIndex][0];
    }

    public String getCarrier(){
        return MCCMNC[mccmncIndex][1];
    }

    public String getJailbroken(){
        return JAILBROKEN[jailbroken];
    }
}
