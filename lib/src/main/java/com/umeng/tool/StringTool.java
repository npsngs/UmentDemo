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

    public static byte[] a(String var0) {
        if(var0 == null) {
            return null;
        } else {
            int var1 = var0.length();
            if(var1 % 2 != 0) {
                return null;
            } else {
                byte[] var2 = new byte[var1 / 2];

                for(int var3 = 0; var3 < var1; var3 += 2) {
                    var2[var3 / 2] = (byte)Integer.valueOf(var0.substring(var3, var3 + 2), 16).intValue();
                }

                return var2;
            }
        }
    }

    public static boolean a(Context var0, byte[] var1) {
        long var2 = (long)var1.length;
        if(var2 > UMConst.y) {
            CacheTool.getInstance(var0).g();
            AggTool.getInstance(var0).a(var2, System.currentTimeMillis(), "__data_size_of");
            return true;
        } else {
            return false;
        }
    }

    public static String a(byte[] var0) {
        if(var0 == null) {
            return null;
        } else {
            StringBuffer var1 = new StringBuffer();

            for(int var2 = 0; var2 < var0.length; ++var2) {
                var1.append(String.format("%02X", new Object[]{Byte.valueOf(var0[var2])}));
            }

            return var1.toString().toLowerCase(Locale.US);
        }
    }

    public static byte[] b(byte[] var0) {
        try {
            MessageDigest var1 = MessageDigest.getInstance("MD5");
            var1.reset();
            var1.update(var0);
            return var1.digest();
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] var0, byte[] var1) throws Exception {
        Cipher var2 = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec var3 = new SecretKeySpec(var1, "AES");
        IvParameterSpec var4 = new IvParameterSpec(a);
        var2.init(1, var3, var4);
        return var2.doFinal(var0);
    }

    public static byte[] b(byte[] var0, byte[] var1) throws Exception {
        Cipher var2 = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec var3 = new SecretKeySpec(var1, "AES");
        IvParameterSpec var4 = new IvParameterSpec(a);
        var2.init(2, var3, var4);
        return var2.doFinal(var0);
    }

    public static int a(int var0, String var1) {
        boolean var2 = false;
        float var3 = (new Random()).nextFloat();
        int var7;
        if((double)var3 < 0.001D) {
            if(var1 == null) {
                ULog.c("--->", new Object[]{"null signature.."});
            }

            int var4 = 0;

            try {
                var4 = Integer.parseInt(var1.substring(9, 11), 16);
            } catch (Exception var6) {
                ;
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

    public static String c(String var0) {
        MessageDigest var1 = null;
        String var2 = null;
        byte[] var3 = var0.getBytes();

        try {
            var1 = MessageDigest.getInstance("SHA1");
            var1.update(var3);
            var2 = c(var1.digest());
            return var2;
        } catch (Exception var5) {
            return null;
        }
    }

    static String c(byte[] var0) {
        String var1 = "";
        String var2 = null;

        for(int var3 = 0; var3 < var0.length; ++var3) {
            var2 = Integer.toHexString(var0[var3] & 255);
            if(var2.length() == 1) {
                var1 = var1 + "0";
            }

            var1 = var1 + var2;
        }

        return var1;
    }
}
