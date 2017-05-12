//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.tool;

import android.text.TextUtils;

import com.umeng.analytics.aggregate.data.AggData;
import com.umeng.analytics.aggregate.result.OpResult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class AggDataManager implements Serializable {
    private Map<String, AggData> hashMap = new HashMap();

    public AggDataManager() {
    }

    public void addCount(OpResult opResult, String key) {
        if(this.hashMap.containsKey(key)) {
            this.addItemCount(key);
        } else {
            this.put(key);
        }

        opResult.setResult(this, false);
    }

    public Map<String, AggData> getDataMap() {
        return this.hashMap;
    }

    public void clearData() {
        this.hashMap.clear();
    }

    public void setDataMap(Map<String, AggData> dataMap) {
        this.hashMap = dataMap;
    }

    public boolean isContainKey(String key) {
        if(TextUtils.isEmpty(key)) {
            return false;
        } else {
            Entry entry;
            Iterator iterator = this.hashMap.entrySet().iterator();

            do {
                if(!iterator.hasNext()) {
                    return false;
                }
                entry = (Entry)iterator.next();
            } while(!(entry.getKey()).equals(key));

            return true;
        }
    }

    private void put(String key) {
        long currentTimeMillis = System.currentTimeMillis();
        AggData aggData = new AggData(key, currentTimeMillis, 1L);
        this.hashMap.put(key, aggData);
    }

    private void addItemCount(String key) {
        AggData aggData = this.hashMap.get(key);
        aggData = aggData.addCount();
        this.hashMap.put(key, aggData);
    }

    public void put(AggData aggData) {
        if(this.isContainKey(aggData.getKey())) {
            this.copyItemFrom(aggData);
        } else {
            this.hashMap.put(aggData.getKey(), aggData);
        }

    }

    private void copyItemFrom(AggData aggData) {
        AggData data = this.hashMap.get(aggData.getKey());
        data = data.copyFrom(aggData);
        this.hashMap.put(aggData.getKey(), data);
    }
}
