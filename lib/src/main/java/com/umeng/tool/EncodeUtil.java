//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;

import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtil {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String b = "helper";

    public EncodeUtil() {
    }

    public static String getSubstring(String source, int offset) {
        String ret = "";

        try {
            if(!TextUtils.isEmpty(source)) {
                int len = offset;
                ret = source.substring(0, source.length() < offset?source.length():offset);

                for(int i = ret.getBytes("UTF-8").length; i > len; i = ret.getBytes("UTF-8").length) {
                    --offset;
                    ret = source.substring(0, offset > source.length()?source.length():offset);
                }

                return ret;
            }
        } catch (Exception e) {
            ULog.e(e);
        }

        return ret;
    }

    public static String getMD5_2(String source) {
        if(source == null) {
            return null;
        } else {
            try {
                byte[] data = source.getBytes();
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                messageDigest.update(data);
                byte[] md5Data = messageDigest.digest();
                StringBuffer stringBuffer = new StringBuffer();

                for(int i = 0; i < md5Data.length; ++i) {
                    stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(md5Data[i])}));
                }

                return stringBuffer.toString();
            } catch (Exception e) {
                return source.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
            }
        }
    }

    public static String getMD5(String source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(source.getBytes());
            byte[] data = messageDigest.digest();
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < data.length; ++i) {
                int var5 = 255 & data[i];
                sb.append(Integer.toHexString(var5));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            ULog.c("helper", "getMD5 error", e);
            return "";
        }
    }

    public static String readAsBigInteger(File file) {
        MessageDigest messageDigest;
        FileInputStream fis;
        byte[] buffer = new byte[1024];

        try {
            if(!file.isFile()) {
                return "";
            }

            messageDigest = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);

            while(true) {
                int ret;
                if((ret = fis.read(buffer, 0, 1024)) == -1) {
                    fis.close();
                    break;
                }

                messageDigest.update(buffer, 0, ret);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        BigInteger bigInteger = new BigInteger(1, messageDigest.digest());
        return String.format("%1$032x", new Object[]{bigInteger});
    }

    public static String readStr(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        char[] buffer = new char[1024];
        StringWriter sw = new StringWriter();

        int ret;
        while(-1 != (ret = isr.read(buffer))) {
            sw.write(buffer, 0, ret);
        }

        return sw.toString();
    }

    public static byte[] readData(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int ret;
        while(-1 != (ret = is.read(buffer))) {
            bos.write(buffer, 0, ret);
        }

        return bos.toByteArray();
    }

    public static void writeToFile(File file, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);

        try {
            fos.write(data);
            fos.flush();
        } finally {
            close(fos);
        }

    }

    public static void writeToFile(File file, String str) throws IOException {
        writeToFile(file, str.getBytes());
    }

    public static String readStr(File file) {
        FileInputStream fis = null;
        String ret = null;
        try {
            if(file.exists()) {
                fis = new FileInputStream(file);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                ret = new String(buffer);
                return ret;
            }
        } catch (Throwable var8) {
            return ret;
        } finally {
            close(fis);
        }

        return ret;
    }

    public static void close(InputStream is) {
        if(is != null) {
            try {
                is.close();
            } catch (Exception e) {
            }
        }
    }

    public static void close(OutputStream os) {
        if(os != null) {
            try {
                os.close();
            } catch (Exception e) {
            }
        }

    }
}
