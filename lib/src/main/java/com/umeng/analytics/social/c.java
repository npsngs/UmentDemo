//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.social;

public class c {
    private int a = -1;
    private String b = "";
    private String c = "";
    private Exception d = null;

    public c(int var1) {
        this.a = var1;
    }

    public c(int var1, Exception var2) {
        this.a = var1;
        this.d = var2;
    }

    public Exception a() {
        return this.d;
    }

    public int b() {
        return this.a;
    }

    public void a(int var1) {
        this.a = var1;
    }

    public String c() {
        return this.b;
    }

    public void a(String var1) {
        this.b = var1;
    }

    public String d() {
        return this.c;
    }

    public void b(String var1) {
        this.c = var1;
    }

    public String toString() {
        return "status=" + this.a + "\r\n" + "msg:  " + this.b + "\r\n" + "data:  " + this.c;
    }
}
