//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.data;


import com.umeng.analytics.aggregate.result.OpResult;
import com.umeng.analytics.aggregate.Database.DBConst;
import com.umeng.tool.ULog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AggregatedDara implements Serializable {
    private Map<List<String>, b> map = new HashMap();
    private long c = 0L;

    public AggregatedDara() {
    }

    public Map<List<String>, b> getMap() {
        return this.map;
    }

    public void a(Map<List<String>, b> var1) {
        if(this.map.size() <= 0) {
            this.map = var1;
        } else {
            this.b(var1);
        }

    }

    private void b(Map<List<String>, b> inputMap) {
        Entry entry1;
        Entry entry2;
        new ArrayList();
        new ArrayList();
        b var6;
        b var7;
        Iterator iterator = this.map.entrySet().iterator();

        while(iterator.hasNext()) {
            entry1 = (Entry)iterator.next();
            List list = (List)entry1.getKey();
            Iterator iterator1 = this.map.entrySet().iterator();

            while(iterator.hasNext()) {
                entry2 = (Entry)iterator1.next();
                List var5 = (List)entry1.getKey();
                if(!list.equals(var5)) {
                    this.map.put(var5, (b) entry2.getValue());
                } else {
                    var6 = (b)entry1.getValue();
                    var7 = (b)entry2.getValue();
                    this.a(var6, var7);
                    this.map.remove(list);
                    this.map.put(list, var7);
                }
            }
        }

    }

    private void a(b var1, b var2) {
        var2.setCount(var2.getCount() + var1.getCount());
        var2.setValue(var2.getValue() + var1.getValue());
        var2.setCurrentMillis(var2.getCurrentMills() + var1.getCurrentMills());

        for(int var3 = 0; var3 < var1.getLables().size(); ++var3) {
            var2.addLabel((String)var1.getLables().get(var3));
        }

    }

    public long b() {
        return this.c;
    }

    public void a(long var1) {
        this.c = var1;
    }

    public void aggregated(final OpResult var1, e var2) {
        try {
            if(this.a(var2.getKeys())) {
                b var3 = (b)this.map.get(var2.getKeys());
                if(var3 != null) {
                    var3.a(new OpResult() {
                        public void setResult(Object var1x, boolean var2) {
                            b var3 = (b)var1x;
                            AggregatedDara.this.map.remove(var3.keysJoinToStr());
                            AggregatedDara.this.map.put(var3.getKeys(), var3);
                            var1.setResult(this, false);
                        }
                    }, var2);
                } else {
                    this.a(var1, var2.getKeys(), var2);
                }
            } else {
                this.a(var1, var2.getKeys(), var2);
            }
        } catch (Exception var4) {
            ULog.e("aggregated faild!");
        }

    }

    public void a(OpResult var1, List<String> var2, e var3) {
        b var4 = new b();
        var4.a(var3);
        this.map.put(var2, var4);
        var1.setResult(this, false);
    }

    public boolean a(List<?> var1) {
        return this.map != null && this.map.containsKey(var1);
    }

    public void a(OpResult opResult) {
        Iterator iterator = this.map.keySet().iterator();

        while(iterator.hasNext()) {
            List var3 = (List)iterator.next();
            if(opResult.isSuccess()) {
                return;
            }

            opResult.setResult(this.map.get(var3), false);
        }

    }

    public int c() {
        return this.map != null?this.map.size():0;
    }

    public void d() {
        this.map.clear();
    }

    public boolean a(List<String> var1, List<String> var2) {
        if(var1 != null && var1.size() != 0) {
            ArrayList var3 = new ArrayList();

            for(int var4 = 0; var4 < var1.size() - 1; ++var4) {
                var3.add(DBConst.joinStrToList((String)var1.get(var4)));
            }

            return var1 != null && var1.size() != 0?var3.contains(var2):false;
        } else {
            return false;
        }
    }

    public void overFlowAggregated(OpResult var1, e var2, List<String> var3, List<String> var4) {
        while(true) {
            try {
                if(var3.size() >= 1) {
                    if(var3.size() == 1) {
                        if(!this.a(var4, var3)) {
                            var1.setResult(Boolean.valueOf(false), false);
                        } else {
                            this.a(var1, var2, var3);
                        }

                        return;
                    }

                    if(this.a(var4, var3)) {
                        this.a(var1, var2, var3);
                        return;
                    }

                    var3.remove(var3.size() - 1);
                    continue;
                }
            } catch (Exception var6) {
                ULog.e("overFlowAggregated faild");
            }

            return;
        }
    }

    private void a(OpResult var1, e var2, List<String> var3) {
        if(this.a(var3)) {
            this.aggregated(var1, var2);
        } else {
            this.a(var1, var3, var2);
        }

    }
}
