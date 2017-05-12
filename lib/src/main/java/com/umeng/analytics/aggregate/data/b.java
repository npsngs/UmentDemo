//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.data;

import com.umeng.analytics.aggregate.Database.DBConst;
import com.umeng.analytics.aggregate.tool.StringCheck;
import com.umeng.analytics.aggregate.result.OpResult;
import com.umeng.analytics.aggregate.tool.CalendarUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class b implements Serializable {
    private List<String> keys = new ArrayList();
    private List<String> labels = new ArrayList();
    private long currentMillis = 0L;
    private long value = 0L;
    private long count = 0L;
    private String totalTimeStamp = null;

    public b() {
    }

    public b(List<String> keys, long currentMillis, long value, long count, List<String> labels, String totalTimeStamp) {
        this.keys = keys;
        this.labels = labels;
        this.currentMillis = currentMillis;
        this.value = value;
        this.count = count;
        this.totalTimeStamp = totalTimeStamp;
    }

    public void addLabel(String label) {
        try {
            if(this.labels.size() < StringCheck.getInstance().b()) {
                this.labels.add(label);
            } else {
                this.labels.remove(this.labels.get(0));
                this.labels.add(label);
            }

            if(this.labels.size() > StringCheck.getInstance().b()) {
                for(int var2 = 0; var2 < this.labels.size() - StringCheck.getInstance().b(); ++var2) {
                    this.labels.remove(this.labels.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void a(OpResult opResult, e var2) {
        this.addLabel(var2.getLabel());
        ++this.count;
        this.value += var2.getValue();
        this.currentMillis += var2.d();
        opResult.setResult(this, false);
    }

    public void a(e var1) {
        this.count = 1L;
        this.keys = var1.getKeys();
        this.addLabel(var1.getLabel());
        this.value = var1.getValue();
        this.currentMillis = System.currentTimeMillis();
        this.totalTimeStamp = CalendarUtil.a(System.currentTimeMillis());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[keys: ").append(this.keys).append("] [labels: ").append(this.labels).append("][ totalTimeStamp").append(this.totalTimeStamp).append("][ value").append(this.value).append("][ count").append(this.count).append("][ timeWindowNum").append(this.totalTimeStamp).append("]");
        return stringBuffer.toString();
    }

    public String keysJoinToStr() {
        return DBConst.joinToStr(this.keys);
    }

    public List<String> getKeys() {
        return this.keys;
    }

    public String labelsJoinToStr() {
        return DBConst.joinToStr(this.labels);
    }

    public List<String> getLables() {
        return this.labels;
    }

    public long getCurrentMills() {
        return this.currentMillis;
    }

    public long getValue() {
        return this.value;
    }

    public long getCount() {
        return this.count;
    }

    public String getTotalTimeStamp() {
        return this.totalTimeStamp;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public void setLables(List<String> labels) {
        this.labels = labels;
    }

    public void setCurrentMillis(long currentMillis) {
        this.currentMillis = currentMillis;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setTotalTimeStamp(String totalTimeStamp) {
        this.totalTimeStamp = totalTimeStamp;
    }
}
