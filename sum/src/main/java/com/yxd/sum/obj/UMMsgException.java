//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.obj;

import a.a.a.UMException;

public class UMMsgException extends UMException {
    private static final long h = 1L;
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    protected int errorCode = 0;

    public UMMsgException() {
    }

    public UMMsgException(int var1) {
        this.errorCode = var1;
    }

    public UMMsgException(int var1, String var2) {
        super(var2);
        this.errorCode = var1;
    }

    public UMMsgException(String var1) {
        super(var1);
    }

    public UMMsgException(int var1, Throwable var2) {
        super(var2);
        this.errorCode = var1;
    }

    public UMMsgException(Throwable var1) {
        super(var1);
    }

    public UMMsgException(String var1, Throwable var2) {
        super(var1, var2);
    }

    public UMMsgException(int var1, String var2, Throwable var3) {
        super(var2, var3);
        this.errorCode = var1;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
