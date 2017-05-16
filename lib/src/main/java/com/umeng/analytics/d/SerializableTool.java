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

public class SerializableTool {
    public SerializableTool() {
    }

    public static String writeToStr(Serializable serializable) {
        if(serializable == null) {
            return "";
        } else {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(serializable);
                oos.close();
                return encode(baos.toByteArray());
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public static Object readFromStr(String s) {
        if(s != null && s.length() != 0) {
            try {
                ByteArrayInputStream var1 = new ByteArrayInputStream(decode(s));
                ObjectInputStream var2 = new ObjectInputStream(var1);
                return var2.readObject();
            } catch (Exception var3) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String encode(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < bytes.length; ++i) {
            stringBuffer.append((char)((bytes[i] >> 4 & 15) + 97));
            stringBuffer.append((char)((bytes[i] & 15) + 97));
        }
        return stringBuffer.toString();
    }

    public static byte[] decode(String s) {
        byte[] var1 = new byte[s.length() / 2];
        for(int i = 0; i < s.length(); i += 2) {
            char var3 = s.charAt(i);
            var1[i / 2] = (byte)(var3 - 97 << 4);
            var3 = s.charAt(i + 1);
            var1[i / 2] = (byte)(var1[i / 2] + (var3 - 97));
        }
        return var1;
    }
}
