package com.umeng.analytics.d;

public interface RequestCallback {
    void request();

    void requestFinish();

    void requstSuccess();

    void RequestFailed();
}
