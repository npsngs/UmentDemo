//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;

import android.content.Context;

import com.umeng.analytics.UMConst;
import com.umeng.analytics.aggregate.tool.AggTool;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class StringTool {
    private static final byte[] a = new byte[]{10, 1, 11, 5, 4, 15, 7, 9, 23, 3, 1, 6, 8, 12, 13, 91};

    public StringTool() {
    }

    public static byte[] hex2Byte(String hex) {
        if(hex == null) {
            return null;
        } else {
            int strLen = hex.length();
            if(strLen % 2 != 0) {
                return null;
            } else {
                byte[] ret = new byte[strLen / 2];

                for(int i = 0; i < strLen; i += 2) {
                    ret[i / 2] = (byte)Integer.valueOf(hex.substring(i, i + 2), 16).intValue();
                }

                return ret;
            }
        }
    }

    public static boolean isTooLong(Context context, byte[] data) {
        long length = (long)data.length;
        if(length > UMConst.MAX_SIZE) {
            CacheTool.getInstance(context).clearData();
            AggTool.getInstance(context).insertToSystemTable(length, System.currentTimeMillis(), "__data_size_of");
            return true;
        } else {
            return false;
        }
    }

    public static String byte2Hex(byte[] data) {
        if(data == null) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < data.length; ++i) {
                sb.append(String.format("%02X", new Object[]{Byte.valueOf(data[i])}));
            }

            return sb.toString().toLowerCase(Locale.US);
        }
    }

    public static byte[] md5(byte[] input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(input);
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(a);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(a);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(data);
    }

    public static int a(int var0, String var1) {
        float var3 = (new Random()).nextFloat();
        int var7;
        if((double)var3 < 0.001D) {
            if(var1 == null) {
                ULog.c("--->", new Object[]{"null signature.."});
            }

            int var4 = 0;

            try {
                var4 = Integer.parseInt(var1.substring(9, 11), 16);
            } catch (Exception e) {
            }

            var7 = (128 | var4) * 1000;
        } else {
            var7 = (new Random()).nextInt(var0);
            if(var7 <= 255000 && var7 >= 128000) {
                var7 = 127000;
            }
        }

        return var7;
    }

    public static String readStrFromThrowable(Throwable throwable) {
        if(throwable == null) {
            return null;
        } else {
            String ret = null;

            try {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                throwable.printStackTrace(printWriter);

                for(Throwable throwableCause = throwable.getCause(); throwableCause != null; throwableCause = throwableCause.getCause()) {
                    throwableCause.printStackTrace(printWriter);
                }

                ret = stringWriter.toString();
                printWriter.close();
                stringWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return ret;
        }
    }

    public static String getUrl(String demain) {
        String var1 = "http://";
        String var2 = ".umeng.com/app_logs";
        return var1 + demain + var2;
    }

    public static String sha1(String source) {
        MessageDigest messageDigest;
        String ret;
        byte[] data = source.getBytes();

        try {
            messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(data);
            ret = toHexStr(messageDigest.digest());
            return ret;
        } catch (Exception e) {
            return null;
        }
    }

    public static String toHexStr(byte[] data) {
        String var1 = "";
        String var2;

        for(int i = 0; i < data.length; ++i) {
            var2 = Integer.toHexString(data[i] & 255);
            if(var2.length() == 1) {
                var1 = var1 + "0";
            }

            var1 = var1 + var2;
        }

        return var1;
    }
}
