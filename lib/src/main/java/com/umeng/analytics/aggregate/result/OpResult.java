//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.result;

public class OpResult implements OnResult {
    private boolean isSuccess = false;

    public OpResult() {
    }

    public void setResult(Object var1, boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
