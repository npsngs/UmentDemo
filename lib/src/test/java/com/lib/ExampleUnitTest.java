package com.lib;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <setRequestCallback href="http://d.android.com/tools/testing">Testing documentation</setRequestCallback>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        for(int i=0;i<1000;i++){
            int seedInt = (int) (Math.random()*10000);
            int index = findIndex(ACCESS_R, seedInt);
            System.out.println("seedInt:"+seedInt+"  index:"+index);
        }
    }

    private int findIndex(int[][] r, int n){
        if(n >10000 || n < 0){
            return 0;
        }
        int i = r.length/2;
        while (true){
            if(n > r[i][0]){
                i--;
            }else if(n < r[i][1]){
                i++;
            }else{
                return i;
            }
        }
    }

    private static final int[][] ACCESS_R = {
            {10000, 1831},
            {1830, 1567},
            {1566, 74},
            {73, 0}
    };
}