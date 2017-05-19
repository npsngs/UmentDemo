//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.d;

import a.a.a.UMException;

public class UMErrCodeException extends UMException {
    private static final long g = 1L;
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    protected int errorCode = 0;

    public UMErrCodeException() {
    }

    public UMErrCodeException(int errCode) {
        this.errorCode = errCode;
    }

    public UMErrCodeException(int errCode, String msg) {
        super(msg);
        this.errorCode = errCode;
    }

    public UMErrCodeException(String msg) {
        super(msg);
    }

    public UMErrCodeException(int errcode, Throwable t) {
        super(t);
        this.errorCode = errcode;
    }

    public UMErrCodeException(Throwable t) {
        super(t);
    }

    public UMErrCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public UMErrCodeException(int errcode, String msg, Throwable t) {
        super(msg, t);
        this.errorCode = errcode;
    }

    public int a() {
        return this.errorCode;
    }
}
