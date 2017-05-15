package com.yxd.umentdemo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        long a = 102345L;
        byte[] tmp = new byte[8];
        long2Bytes(a, tmp ,0);

        System.out.println(a(tmp));
    }


    private int c(int var1) {
        return var1 << 1 ^ var1 >> 31;
    }

    private int g(int var1) {
        return var1 >>> 1 ^ -(var1 & 1);
    }

    private void long2Bytes(long inputLong, byte[] outBytes, int offset) {
        outBytes[offset + 0] = (byte)((int)(inputLong & 255L));
        outBytes[offset + 1] = (byte)((int)(inputLong >> 8 & 255L));
        outBytes[offset + 2] = (byte)((int)(inputLong >> 16 & 255L));
        outBytes[offset + 3] = (byte)((int)(inputLong >> 24 & 255L));
        outBytes[offset + 4] = (byte)((int)(inputLong >> 32 & 255L));
        outBytes[offset + 5] = (byte)((int)(inputLong >> 40 & 255L));
        outBytes[offset + 6] = (byte)((int)(inputLong >> 48 & 255L));
        outBytes[offset + 7] = (byte)((int)(inputLong >> 56 & 255L));
    }

    private long a(byte[] var1) {
        return ((long)var1[7] & 255L) << 56 | ((long)var1[6] & 255L) << 48 | ((long)var1[5] & 255L) << 40 | ((long)var1[4] & 255L) << 32 | ((long)var1[3] & 255L) << 24 | ((long)var1[2] & 255L) << 16 | ((long)var1[1] & 255L) << 8 | (long)var1[0] & 255L;
    }

}