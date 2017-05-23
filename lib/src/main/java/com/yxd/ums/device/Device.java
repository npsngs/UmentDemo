package com.yxd.ums.device;

import com.yxd.ums.SeedTool;

public class Device {
    private static  final String[][] DEVICE = {
            {"MHA-AL00", "MHA", "HUAWEI", "HWMHA", "AArch64 Processor rev 1 (aarch64)", "1920*1080"},
            {"SM-N9200", "universal7420", "samsung", "nobleltechn", "AArch64 Processor rev 0 (aarch64)", "2560x1440"},
            {"GT-I9507V", "MSM8960", "samsung", "jftdd", "ARMv7 Processor rev 0 (v7l)", "1920x1080"},
            {"MI 4W", "MSM8974", "Xiaomi", "cancro", "ARMv7 Processor rev 1 (v7l)", "1920*1080"},
            {"MI 5", "QC_Reference_Phone", "Xiaomi",  "gemini", "AArch64 Processor rev 1 (aarch64)", "1920*1080"},
            {"vivo X6D", "unknown", "vivo", "PD1415D", "AArch64 Processor rev 2 (aarch64)", "1920*1080"},
            {"OPPO A59m", "full_oppo6750_15131", "OPPO", "A59", "AArch64 Processor rev 2 (aarch64)", "1280x720"},
            {"R7Plus", "full_oppo6795_15019", "OPPO", "R7Plus", "AArch64 Processor rev 2 (aarch64)", "1280x720"},
            {"MX4", "mx4", "Meizu", "mx4", "ARMv7 Processor rev 5 (v7l)", "1280x720"},
    };

    private static  final String[][] OS = {
            {"6.0.1", "MMB29K"},
            {"6.0",   "KTU84P"},
            {"5.1.1", "LMY47I"},
            {"5.0.2", "LRX22L"},
            {"5.0.1", "LRX22C"},
            {"5.0",   "LRX21R"},
            {"4.4.4", "KTU84P"}
    };


    private int deviceIndex;
    private int osIndex;
    public Device(String seed) {
        int seedInt = SeedTool.seed2Int(seed);
        deviceIndex = seedInt%DEVICE.length;
        seedInt = seed.charAt(0);
        osIndex = seedInt%OS.length;
    }

    public String getDevice_model() {
        return DEVICE[deviceIndex][0];
    }

    public String getDevice_board() {
        return DEVICE[deviceIndex][1];
    }

    public String getDevice_brand() {
        return DEVICE[deviceIndex][2];
    }

    public String getDevice_name() {
        return DEVICE[deviceIndex][3];
    }

    public String getCpu() {
        return DEVICE[deviceIndex][4];
    }

    public String getResolution() {
        return DEVICE[deviceIndex][5];
    }

    public String getDevice_manuid() {
        return OS[osIndex][1];
    }

    public String getOs_version() {
        return OS[osIndex][0];
    }
}
