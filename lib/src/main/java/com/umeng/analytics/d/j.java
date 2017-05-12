//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.d;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class j {
    public j() {
    }

    public static String a(Serializable var0) {
        if(var0 == null) {
            return "";
        } else {
            try {
                ByteArrayOutputStream var1 = new ByteArrayOutputStream();
                ObjectOutputStream var2 = new ObjectOutputStream(var1);
                var2.writeObject(var0);
                var2.close();
                return a(var1.toByteArray());
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public static Object a(String var0) {
        if(var0 != null && var0.length() != 0) {
            try {
                ByteArrayInputStream var1 = new ByteArrayInputStream(b(var0));
                ObjectInputStream var2 = new ObjectInputStream(var1);
                return var2.readObject();
            } catch (Exception var3) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String a(byte[] var0) {
        StringBuffer var1 = new StringBuffer();

        for(int var2 = 0; var2 < var0.length; ++var2) {
            var1.append((char)((var0[var2] >> 4 & 15) + 97));
            var1.append((char)((var0[var2] & 15) + 97));
        }

        return var1.toString();
    }

    public static byte[] b(String var0) {
        byte[] var1 = new byte[var0.length() / 2];

        for(int var2 = 0; var2 < var0.length(); var2 += 2) {
            char var3 = var0.charAt(var2);
            var1[var2 / 2] = (byte)(var3 - 97 << 4);
            var3 = var0.charAt(var2 + 1);
            var1[var2 / 2] = (byte)(var1[var2 / 2] + (var3 - 97));
        }

        return var1;
    }
}
