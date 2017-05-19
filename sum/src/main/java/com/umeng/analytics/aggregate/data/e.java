//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.data;

import com.umeng.analytics.aggregate.tool.CalendarUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class e implements Serializable {
    private List<String> keys = new ArrayList();
    private String label;
    private long value;
    private long e;
    private String f;

    public e(List<String> keys, long value, String label, long var5) {
        this.keys = keys;
        this.value = value;
        this.label = label;
        this.e = var5;
        this.f();
    }

    private void f() {
        this.f = CalendarUtil.a(this.e);
    }

    public List<String> getKeys() {
        return this.keys;
    }

    public String getLabel() {
        return this.label;
    }

    public long getValue() {
        return this.value;
    }

    public long d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }
}
