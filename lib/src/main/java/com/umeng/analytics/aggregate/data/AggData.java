//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.data;

import java.io.Serializable;

public class AggData implements Serializable {
    private String key;
    private long timeStamp;
    private long count;
    private String e;

    private AggData() {
        this.key = null;
        this.timeStamp = 0L;
        this.count = 0L;
        this.e = null;
    }

    public AggData(String key, long timeStamp, long count) {
        this(key, timeStamp, count, null);
    }

    public AggData(String key, long timeStamp, long count, String e) {
        this.key = null;
        this.timeStamp = 0L;
        this.count = 0L;
        this.key = key;
        this.timeStamp = timeStamp;
        this.count = count;
        this.e = e;
    }

    public AggData addCount() {
        ++this.count;
        return this;
    }

    public String getE() {
        return this.e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getKey() {
        return this.key;
    }

    public void b(String key) {
        this.key = key;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public long getCount() {
        return this.count;
    }

    public AggData copyFrom(AggData aggData) {
        long count = aggData.getCount();
        this.count += count;
        this.timeStamp = aggData.getTimeStamp();
        return this;
    }
}
