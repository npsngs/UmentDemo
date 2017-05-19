//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics;

import com.umeng.analytics.f.a;
import java.util.Locale;

public enum Gender {
    Male(1) {
        public String toString() {
            return String.format(Locale.US, "Male:%loadChannel", new Object[]{Integer.valueOf(this.value)});
        }
    },
    Female(2) {
        public String toString() {
            return String.format(Locale.US, "Female:%loadChannel", new Object[]{Integer.valueOf(this.value)});
        }
    },
    Unknown(0) {
        public String toString() {
            return String.format(Locale.US, "Unknown:%loadChannel", new Object[]{Integer.valueOf(this.value)});
        }
    };

    public int value;

    private Gender(int var3) {
        this.value = var3;
    }

    public int value() {
        return this.value;
    }

    public static Gender getGender(int var0) {
        Gender var1 = null;
        switch(var0) {
            case 1:
                var1 = Male;
                break;
            case 2:
                var1 = Female;
                break;
            case 3:
            default:
                var1 = Unknown;
        }

        return var1;
    }

    public static a transGender(Gender var0) {
        switch(var0.ordinal()) {//null.setRequestCallback[var0.ordinal()]
            case 1:
                return a.a;
            case 2:
                return a.b;
            case 3:
            default:
                return a.c;
        }
    }
}
