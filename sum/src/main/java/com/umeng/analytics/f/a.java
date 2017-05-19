//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;

import a.a.a.h;

public enum a implements h {
    a(0),
    b(1),
    c(2);

    private final int d;

    private a(int var3) {
        this.d = var3;
    }

    public int a() {
        return this.d;
    }

    public static a a(int var0) {
        switch(var0) {
            case 0:
                return a;
            case 1:
                return b;
            case 2:
                return c;
            default:
                return null;
        }
    }
}
