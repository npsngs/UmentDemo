//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.umeng.analytics.aggregate.Database.DBConst;
import com.umeng.analytics.aggregate.data.AggregatedDara;
import com.umeng.analytics.aggregate.data.e;
import com.umeng.analytics.aggregate.result.OpResult;
import com.umeng.tool.TaskExecutor;
import com.umeng.tool.ULog;
import com.umeng.tool.SafeRunnable;
import com.yxd.sum.tool.SPTool;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class AggTool {
    private AggregatedDara AggregatedDara;
    private DBDataTool dataTool;
    private AggDataManager aggDataManager;
    private boolean isDataLoaded;
    private boolean mainFestMode;
    private long mainFestTimestamp;
    private static Context context;
    private List<String> CKList;
    private AggToolHandler aggToolHandler;
    private final Thread thread;

    public boolean a() {
        return this.isDataLoaded;
    }

    private AggTool() {
        this.AggregatedDara = null;
        this.dataTool = null;
        this.aggDataManager = null;
        this.isDataLoaded = false;
        this.mainFestMode = false;
        this.mainFestTimestamp = 0L;
        this.CKList = new ArrayList();
        this.aggToolHandler = null;
        this.thread = new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                if(AggTool.this.aggToolHandler == null) {
                    AggTool.this.aggToolHandler = new AggToolHandler(AggTool.this);
                }

                AggTool.this.h();
            }
        });
        if(context != null) {
            if(this.AggregatedDara == null) {
                this.AggregatedDara = new AggregatedDara();
            }

            if(this.dataTool == null) {
                this.dataTool = DBDataTool.getInstance(context);
            }

            if(this.aggDataManager == null) {
                this.aggDataManager = new AggDataManager();
            }
        }

        this.thread.start();
    }

    private void h() {
        long currentTimeMillis = System.currentTimeMillis();
        this.aggToolHandler.sendEmptyMessageDelayed(48, CalendarUtil.c(currentTimeMillis));
        this.aggToolHandler.sendEmptyMessageDelayed(49, CalendarUtil.d(currentTimeMillis));
    }

    public static final AggTool getInstance(Context context) {
        AggTool.context = context;
        return AggToolInner.AGG_TOOL;
    }

    public void loadAggData(final OpResult opResult) {
        if(!this.isDataLoaded) {
            TaskExecutor.scheduleExecute(new SafeRunnable() {
                public void safeRun() {
                    try {
                        dataTool.loadAggData(new OpResult() {
                            public void setResult(Object o, boolean var2) {
                                if(o instanceof Map) {
                                    Map map = (Map)o;
                                    AggTool.this.AggregatedDara.a(map);
                                } else if(!(o instanceof String) && o instanceof Boolean) {
                                }

                                AggTool.this.isDataLoaded = true;
                            }
                        });
                        AggTool.this.loadMainFestInfo();
                        AggTool.this.loadCKToMemory();
                        opResult.setResult("success", false);
                    } catch (Exception var2) {
                        var2.printStackTrace();
                    }

                }
            });
        }
    }

    public void a(final OpResult var1, Map<List<String>, e> var2) {
        e var3 = (e)var2.values().toArray()[0];
        List var4 = var3.getKeys();
        if(this.CKList.size() > 0 && this.CKList.contains(DBConst.joinToStr(var4))) {
            this.AggregatedDara.aggregated(new OpResult() {
                public void setResult(Object var1x, boolean var2) {
                    if(var1x instanceof AggregatedDara) {
                        AggTool.this.AggregatedDara = (AggregatedDara)var1x;
                    }

                    var1.setResult("success", false);
                }
            }, var3);
        } else if(this.mainFestMode) {
            this.a(var3, var4);
        } else {
            if(this.i()) {
                String var5 = DBConst.joinToStr(var4);
                if(!this.CKList.contains(var5)) {
                    this.CKList.add(var5);
                }

                this.AggregatedDara.a(new OpResult() {
                    public void setResult(Object var1, boolean var2) {
                        AggTool.this.AggregatedDara = (AggregatedDara)var1;
                    }
                }, var4, var3);
            } else {
                this.a(var3, var4);
                this.updateMainFestInfo();
            }

        }
    }

    private void a(e var1, List<String> var2) {
        this.AggregatedDara.overFlowAggregated(new OpResult() {
            public void setResult(Object var1, boolean var2) {
                if(var1 instanceof AggregatedDara) {
                    AggTool.this.AggregatedDara = (AggregatedDara)var1;
                } else if(var1 instanceof Boolean) {
                    AggTool.this.addCount();
                }

            }
        }, var1, var2, this.CKList);
    }

    private boolean i() {
        return this.CKList.size() < StringCheck.getInstance().d();
    }

    private void updateMainFestInfo() {
        SharedPreferences sp = SPTool.getSp(context);
        if(!sp.getBoolean("main_fest_mode", false)) {
            this.mainFestMode = true;
            Editor var2 = sp.edit();
            var2.putBoolean("main_fest_mode", true);
            var2.putLong("main_fest_timestamp", System.currentTimeMillis());
            var2.apply();
        }

    }

    private void clearMainFestInfo() {
        SharedPreferences sp = SPTool.getSp(context);
        Editor editor = sp.edit();
        editor.putBoolean("main_fest_mode", false);
        editor.putLong("main_fest_timestamp", 0L);
        editor.apply();
        this.mainFestMode = false;
    }

    private void loadMainFestInfo() {
        SharedPreferences sp = SPTool.getSp(context);
        this.mainFestMode = sp.getBoolean("main_fest_mode", false);
        this.mainFestTimestamp = sp.getLong("main_fest_timestamp", 0L);
    }

    public JSONObject getAG() {
        JSONObject var1 = this.dataTool.uploadAggData();
        JSONObject var2 = new JSONObject();
        if(var1 != null && var1.length() > 0) {
            Iterator var3 = this.CKList.iterator();

            while(var3.hasNext()) {
                String var4 = (String)var3.next();
                if(var1.has(var4)) {
                    try {
                        var2.put(var4, var1.opt(var4));
                    } catch (Exception var6) {
                        ;
                    }
                }
            }

            return var2;
        } else {
            return null;
        }
    }

    public JSONObject getVe_mate() {
        if(this.aggDataManager.getDataMap().size() > 0) {
            this.dataTool.saveToSystemTable(new OpResult() {
                public void setResult(Object var1, boolean var2) {
                    if(var1 instanceof String) {
                        AggTool.this.aggDataManager.clearData();
                    }

                }
            }, this.aggDataManager.getDataMap());
        }

        JSONObject var1 = this.dataTool.readAllSystemData(new OpResult());
        return var1;
    }

    public void cleatData(OpResult opResult) {
        boolean var2 = false;
        if(this.mainFestMode) {
            if(this.mainFestTimestamp == 0L) {
                this.loadMainFestInfo();
            }

            var2 = CalendarUtil.isSameDayOfMonth(System.currentTimeMillis(), this.mainFestTimestamp);
        }

        if(!var2) {
            this.clearMainFestInfo();
            this.CKList.clear();
        }

        this.aggDataManager.clearData();
        this.dataTool.notifyUploadSuccess(new OpResult() {
            public void setResult(Object var1, boolean var2) {
                if(var1.equals("success")) {
                    AggTool.this.m();
                }

            }
        }, var2);
    }

    private void m() {
        Entry entry;
        Iterator iterator = this.AggregatedDara.getMap().entrySet().iterator();

        while(iterator.hasNext()) {
            entry = (Entry)iterator.next();
            List list = (List)entry.getKey();
            if(!this.CKList.contains(list)) {
                this.CKList.add(DBConst.joinToStr(list));
            }
        }

        if(this.CKList.size() > 0) {
            this.dataTool.saveToLimitCKTable(new OpResult(), this.CKList);
        }

    }

    private void addCount() {
        this.aggDataManager.addCount(new OpResult() {
            public void setResult(Object var1, boolean var2) {
                AggTool.this.aggDataManager = (AggDataManager)var1;
            }
        }, "__ag_of");
    }

    public void insertToSystemTable(long count, long timeStamp, String key) {
        this.dataTool.insertToSystemTable(new OpResult() {
            public void setResult(Object o, boolean var2) {
                if(o.equals("success")) {
                }
            }
        }, key, count, timeStamp);
    }

    private void converyMemoryToDataTable() {
        try {
            if(this.AggregatedDara.getMap().size() > 0) {
                this.dataTool.cacheToData(new OpResult() {
                    public void setResult(Object var1, boolean var2) {
                        if(var1 instanceof String) {
                            AggTool.this.AggregatedDara.d();
                        }

                    }
                }, this.AggregatedDara.getMap());
            }

            if(this.aggDataManager.getDataMap().size() > 0) {
                this.dataTool.saveToSystemTable(new OpResult() {
                    public void setResult(Object var1, boolean var2) {
                        if(var1 instanceof String) {
                            AggTool.this.aggDataManager.clearData();
                        }

                    }
                }, this.aggDataManager.getDataMap());
            }

            if(this.CKList.size() > 0) {
                this.dataTool.saveToLimitCKTable(new OpResult(), this.CKList);
            }
        } catch (Throwable throwable) {
            ULog.b("converyMemoryToDataTable happen error: " + throwable.toString());
        }

    }

    private void convertMemoryToCacheTable() {
        try {
            if(this.AggregatedDara.getMap().size() > 0) {
                this.dataTool.saveAggData(new OpResult() {
                    public void setResult(Object var1, boolean var2) {
                    }
                }, this.AggregatedDara.getMap());
            }

            if(this.aggDataManager.getDataMap().size() > 0) {
                this.dataTool.saveToSystemTable(new OpResult() {
                    public void setResult(Object var1, boolean var2) {
                        if(var1 instanceof String) {
                            AggTool.this.aggDataManager.clearData();
                        }

                    }
                }, this.aggDataManager.getDataMap());
            }

            if(this.CKList.size() > 0) {
                this.dataTool.saveToLimitCKTable(new OpResult(), this.CKList);
            }
        } catch (Throwable t) {
            ULog.b("convertMemoryToCacheTable happen error: " + t.toString());
        }

    }

    private void loadCKToMemory() {
        List list = this.dataTool.loadCKToMemory();
        if(list != null) {
            this.CKList = list;
        }

    }

    public void d() {
        this.convertMemoryToCacheTable();
    }

    public void e() {
        this.convertMemoryToCacheTable();
    }

    public void f() {
        this.convertMemoryToCacheTable();
    }

    private static class AggToolHandler extends Handler {
        private final WeakReference<AggTool> aggToolWeakReference;

        public AggToolHandler(AggTool aggTool) {
            this.aggToolWeakReference = new WeakReference(aggTool);
        }

        public void handleMessage(Message message) {
            if(this.aggToolWeakReference != null) {
                switch(message.what) {
                    case 48:
                        this.sendEmptyMessageDelayed(48, CalendarUtil.c(System.currentTimeMillis()));
                        AggTool.getInstance(AggTool.context).convertMemoryToCacheTable();
                        break;
                    case 49:
                        this.sendEmptyMessageDelayed(49, CalendarUtil.d(System.currentTimeMillis()));
                        AggTool.getInstance(AggTool.context).converyMemoryToDataTable();
                }
            }

        }
    }

    private static class AggToolInner {
        private static final AggTool AGG_TOOL = new AggTool();
        private AggToolInner() {
        }
    }
}
