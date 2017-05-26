package com.yxd.ums.device;

import android.content.Context;
import android.os.Build;

import com.umeng.tool.SystemUtil;
import com.yxd.ums.SeedTool;

public class Device {
    private static  final String[][] DEVICE = {
            {"MHA-AL00", "MHA", "HUAWEI", "HWMHA", "1", "1"},
            {"EVA-AL10", "EVA-AL10", "HUAWEI", "HWEVA", "0", "1"},
            {"FRD-AL00", "FRD-AL00", "honor", "HWFRD", "0", "1"},
            {"KNT-AL10", "KNT-AL10", "HONOR", "HWKNT", "0", "1"},
            {"PLK-UL00", "PLK-UL00", "HONOR", "HWPLK", "3", "1"},
            {"KIW-AL10", "KIW-AL10", "HONOR", "HWKIW", "4", "1"},
            {"VTR-AL00", "VTR", "HUAWEI", "HWVTR", "1", "1"},

            {"OPPO A59m", "full_oppo6750_15131", "OPPO", "A59", "2", "2"},
            {"R7Plus", "full_oppo6795_15019", "OPPO", "R7Plus", "2", "2"},
            {"OPPO A37m", "full_oppo6750_15127", "OPPO", "A37", "2", "2"},
            {"OPPO R9tm", "full_oppo6755_15111", "OPPO", "R9", "2", "1"},
            {"N5209", "MSM8974", "OPPO", "N3", "5", "1920x1080"},

            {"vivo X6D", "unknown", "vivo", "PD1415D", "2", "1"},
            {"vivo V3Max A", "msm8916", "vivo", "PD1523", "0", "1"},
            {"vivo X5Pro D", "unknown", "vivo", "bbk6752_lwt_l", "2", "1"},
            {"vivo X7Plus", "msm8952", "vivo", "PD1603", "0", "1"},
            {"vivo X9", "msm8953", "vivo", "PD1616", "4", "1"},

            {"MI 4W", "MSM8974", "Xiaomi", "cancro", "5", "1"},
            {"MI 5", "QC_Reference_Phone", "Xiaomi",  "gemini", "1", "1"},
            {"Redmi 3X", "msm8937", "Xiaomi", "land", "4", "2"},
            {"Redmi Note 4", "unknown", "Xiaomi", "nikel", "1", "1"},

            {"SM-N9200", "universal7420", "samsung", "nobleltechn", "0", "0"},
            {"GT-I9507V", "MSM8960", "samsung", "jftdd", "5", "1"},

            {"MX4", "mx4", "Meizu", "mx4", "5", "2"},
    };

    private static  final String[][] OS = {
            {"7.0.0", "MMB29K"},
            {"6.0.1", "MMB29K"},
            {"6.0",   "KTU84P"},
            {"6.0",   "MDB08M"},
            {"6.0",   "MDB08L"},
            {"5.1.1", "LMY47I"},
            {"5.0.2", "LRX22L"},
            {"5.0.1", "LRX22C"},
            {"5.0",   "LRX21R"},
            {"4.4.4", "KTU84P"}
    };

    private static  final String[] CPU = {
            "AArch64 Processor rev 0 (aarch64)",
            "AArch64 Processor rev 1 (aarch64)",
            "AArch64 Processor rev 2 (aarch64)",
            "AArch64 Processor rev 3 (aarch64)",
            "AArch64 Processor rev 4 (aarch64)",
            "ARMv7 Processor rev 1 (v7l)"
    };

    private static  final String[] RESOLUTION = {
            "2560x1440",
            "1920*1080",
            "1280x720"
    };


    private int deviceIndex = -1;
    private int osIndex = -1;
    private static String localResolution;
    public Device(Context context, String seed) {
        char c = seed.charAt(1);
        if(c < '0'){//decide local device probability  c<'8' == 50%
            int seedInt = Math.abs(SeedTool.seed2Int(seed));
            deviceIndex = seedInt%DEVICE.length;
            seedInt = Math.abs(seed.charAt(0));
            osIndex = seedInt%OS.length;
        }else{
            int[] rs = SystemUtil.getNoncompatScreenSize(context);
            localResolution = rs[1]+"*"+rs[0];
        }
    }

    public String getDevice_model() {
        if(deviceIndex < 0){
            return Build.MODEL;
        }else{
            return DEVICE[deviceIndex][0];
        }
    }

    public String getDevice_board() {
        if(deviceIndex < 0){
            return Build.BOARD;
        }else{
            return DEVICE[deviceIndex][1];
        }
    }

    public String getDevice_brand() {
        if(deviceIndex < 0){
            return Build.BRAND;
        }else{
            return DEVICE[deviceIndex][2];
        }
    }

    public String getDevice_name() {
        if(deviceIndex < 0){
            return Build.DEVICE;
        }else{
            return DEVICE[deviceIndex][3];
        }
    }

    public String getCpu() {
        if(deviceIndex < 0){
            return mapCpu("-1");
        }else{
            return mapCpu(DEVICE[deviceIndex][4]);
        }
    }

    public String getResolution() {
        if(deviceIndex < 0){
            return mapResolution("-1");
        }else{
            return mapResolution(DEVICE[deviceIndex][5]);
        }
    }

    public String getDevice_manuid() {
        if(osIndex < 0){
            return Build.ID;
        }else{
            return OS[osIndex][1];
        }
    }

    public String getOs_version() {
        if(osIndex < 0){
            return Build.VERSION.RELEASE;
        }else{
            return OS[osIndex][0];
        }
    }


    public static String mapCpu(String index){
        switch (index){
            case "0":
                return CPU[0];
            case "1":
                return CPU[1];
            case "2":
                return CPU[2];
            case "3":
                return CPU[3];
            case "4":
                return CPU[4];
            case "5":
                return CPU[5];
            default:
                return CPU[0];
        }
    }

    public static String unmapCpu(String cpu){
        switch (cpu){
            case "AArch64 Processor rev 0 (aarch64)":
                return "0";
            case "AArch64 Processor rev 1 (aarch64)":
                return "1";
            case "AArch64 Processor rev 2 (aarch64)":
                return "2";
            case "AArch64 Processor rev 3 (aarch64)":
                return "3";
            case "AArch64 Processor rev 4 (aarch64)":
                return "4";
            case "ARMv7 Processor rev 1 (v7l)":
                return "5";
            default:
                return "0";
        }
    }


    public static String mapResolution(String index){
        switch (index){
            case "0":
                return RESOLUTION[0];
            case "1":
                return RESOLUTION[1];
            case "2":
                return RESOLUTION[2];
            default:
                if(null != localResolution){
                    return localResolution;
                }
                return RESOLUTION[1];
        }
    }




    public static String unmapResolution(String resolution){
        switch (resolution){
            case "2560x1440":
                return "0";
            case "1920*1080":
                return "1";
            case "1280x720":
                return "0";
            default:
                return "1";
        }
    }
}
