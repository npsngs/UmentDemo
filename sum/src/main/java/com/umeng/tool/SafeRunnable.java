//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;

public abstract class SafeRunnable implements Runnable {
    public SafeRunnable() {
    }

    public void run() {
        try {
            this.safeRun();
        } catch (Throwable throwable) {
            if(throwable != null) {
                throwable.printStackTrace();
            }
        }

    }

    public abstract void safeRun();
}
