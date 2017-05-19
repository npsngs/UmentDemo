//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.track;

import com.umeng.analytics.AnalyticsConfig;

import java.lang.Thread.UncaughtExceptionHandler;

public class MUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private UncaughtExceptionHandler uncaughtExceptionHandler;
    private ExceptionHandler exceptionHandler;

    public MUncaughtExceptionHandler() {
        if(Thread.getDefaultUncaughtExceptionHandler() != this) {
            this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable e) {
        this.HandleException(e);
        if(this.uncaughtExceptionHandler != null && this.uncaughtExceptionHandler != Thread.getDefaultUncaughtExceptionHandler()) {
            this.uncaughtExceptionHandler.uncaughtException(thread, e);
        }

    }

    private void HandleException(Throwable throwable) {
        if(AnalyticsConfig.CATCH_EXCEPTION) {
            this.exceptionHandler.onAppCrash(throwable);
        } else {
            this.exceptionHandler.onAppCrash(null);
        }
    }
}
