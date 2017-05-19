package com.yxd.sum.track;

public interface RequestCallback {
    void request();

    void requestFinish();

    void requstSuccess();

    void RequestFailed();
}
