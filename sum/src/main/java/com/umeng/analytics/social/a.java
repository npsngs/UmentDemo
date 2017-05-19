//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.social;

public class a extends RuntimeException {
    private static final long b = -4656673116019167471L;
    protected int a = 5000;
    private String c = "";

    public int a() {
        return this.a;
    }

    public a(int var1, String var2) {
        super(var2);
        this.a = var1;
        this.c = var2;
    }

    public a(String var1, Throwable var2) {
        super(var1, var2);
        this.c = var1;
    }

    public a(String var1) {
        super(var1);
        this.c = var1;
    }

    public String getMessage() {
        return this.c;
    }
}
