//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZipTool {
    public static int len;
    public ZipTool() {
    }

    public static byte[] deflate(String source, String charsetName) throws IOException {
        return TextUtils.isEmpty(source)?null: deflate(source.getBytes(charsetName));
    }

    public static byte[] deflate(byte[] source) throws IOException {
        if(source != null && source.length > 0) {
            Deflater deflater = new Deflater();
            deflater.setInput(source);
            deflater.finish();
            byte[] buffer = new byte[8192];
            len = 0;
            ByteArrayOutputStream bos = null;

            try {
                bos = new ByteArrayOutputStream();
                while(true) {
                    if(deflater.finished()) {
                        deflater.end();
                        break;
                    }

                    int var4 = deflater.deflate(buffer);
                    len += var4;
                    bos.write(buffer, 0, var4);
                }
            } finally {
                if(bos != null) {
                    bos.close();
                }

            }

            byte[] data = bos.toByteArray();
            return data;
        } else {
            return null;
        }
    }

    public static String inflater(byte[] source, String charsetName) throws UnsupportedEncodingException, DataFormatException {
        byte[] data = inflater(source);
        return data != null?new String(data, charsetName):null;
    }

    public static byte[] inflater(byte[] source) throws UnsupportedEncodingException, DataFormatException {
        if(source != null && source.length != 0) {
            Inflater inflater = new Inflater();
            inflater.setInput(source, 0, source.length);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            for(int i = 0; !inflater.needsInput(); i += len) {
                len = inflater.inflate(buffer);
                bos.write(buffer, i, len);
            }

            inflater.end();
            return bos.toByteArray();
        } else {
            return null;
        }
    }
}
