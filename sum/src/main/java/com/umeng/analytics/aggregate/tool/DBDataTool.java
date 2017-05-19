//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.tool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.umeng.analytics.aggregate.Database.DBHelper;
import com.umeng.analytics.aggregate.data.b;
import com.umeng.analytics.aggregate.result.OpResult;
import com.umeng.analytics.aggregate.Database.DBUtil;
import com.umeng.analytics.aggregate.data.AggData;
import com.umeng.tool.ULog;

import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class DBDataTool {
    private static Context context;

    private DBDataTool() {
        if(context != null) {
        }
    }

    public static DBDataTool getInstance(Context context) {
        DBDataTool.context = context;
        return DBDataToolInner.a;
    }

    public void loadAggData(OpResult opResult) {
        SQLiteDatabase database;

        try {
            database = DBUtil.getInstance(context).getReadableDatabase();
            String lastTimeWindowNum = DBHelper.queryLastTimeWindowNumFromCache(database);
            String var4 = CalendarUtil.a(System.currentTimeMillis());
            if(lastTimeWindowNum.equals("0")) {
                opResult.setResult("faild", false);
                return;
            }

            if(!lastTimeWindowNum.equals(var4)) {
                DBHelper.cacheToAggregatedTable(database, opResult);
            } else {
                DBHelper.cacheToMemory(database, opResult);
            }
        } catch (Exception e) {
            opResult.setResult(Boolean.valueOf(false), false);
            ULog.e("load agg data error");
        } finally {
            DBUtil.getInstance(context).closeDatabase();
        }

    }

    public void saveAggData(OpResult opResult, Map<List<String>, b> map) {
        SQLiteDatabase database;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            DBHelper.insertToAggregatedTable(database, map.values());
            opResult.setResult("success", false);
        } catch (Exception e) {
            ULog.e("save agg data error");
        } finally {
            DBUtil.getInstance(context).closeDatabase();
        }

    }

    public JSONObject uploadAggData() {
        SQLiteDatabase database;

        try {
            database = DBUtil.getInstance(context).getReadableDatabase();
            JSONObject jsonObject = DBHelper.readAllAggregatedDataForUpload(database);
            return jsonObject;
        } catch (Exception e) {
            ULog.e("upload agg date error");
        } finally {
            DBUtil.getInstance(context).closeDatabase();
        }

        return null;
    }

    public JSONObject readAllSystemData(OpResult opResult) {
        SQLiteDatabase database;

        try {
            database = DBUtil.getInstance(context).getReadableDatabase();
            JSONObject jsonObject = DBHelper.readAllSystemDataForUpload(opResult, database);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.getInstance(context).closeDatabase();
        }

        return null;
    }

    public void notifyUploadSuccess(OpResult opResult, boolean var2) {
        SQLiteDatabase database;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            DBHelper.cleanCache(database, var2, opResult);
        } catch (Exception var8) {
            ULog.e("notifyUploadSuccess error");
        } finally {
            DBUtil.getInstance(context).closeDatabase();
        }

    }

    public void insertToSystemTable(OpResult opResult, String key, long count, long timeStamp) {
        SQLiteDatabase database;
        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            DBHelper.insertToSystemTable(database, key, count, timeStamp);
            opResult.setResult("success", false);
        } catch (Exception e) {
            ULog.e("package size to big or envelopeOverflowPackageCount exception");
        } finally {
            DBUtil.getInstance(context).closeDatabase();
        }

    }

    public void saveToLimitCKTable(OpResult opResult, List<String> list) {
        SQLiteDatabase database;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            DBHelper.insertToLimitCKTable(opResult, database, list);
        } catch (Exception var8) {
            ULog.e("saveToLimitCKTable exception");
        } finally {
            DBUtil.getInstance(context).closeDatabase();
        }

    }

    public void saveToSystemTable(OpResult opResult, Map<String, AggData> map) {
        SQLiteDatabase database;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            DBHelper.saveToSystemTable(database, map, opResult);
        } catch (Exception e) {
            ULog.e("arrgetated system buffer exception");
        } finally {
            DBUtil.getInstance(context).closeDatabase();
        }

    }

    public List<String> loadCKToMemory() {
        SQLiteDatabase database;

        try {
            database = DBUtil.getInstance(context).getReadableDatabase();
            List var2 = DBHelper.loadLimitCKFromDB(database);
            return var2;
        } catch (Exception var6) {
            ULog.e("loadCKToMemory exception");
        } finally {
            DBUtil.getInstance(context).closeDatabase();
        }

        return null;
    }

    public void cacheToData(OpResult opResult, Map<List<String>, b> map) {
        SQLiteDatabase database;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            DBHelper.insertToAggregatedTable(opResult, database, map.values());
        } catch (Exception var8) {
            ULog.e("cacheToData error");
        } finally {
            DBUtil.getInstance(context).closeDatabase();
        }

    }

    private static final class DBDataToolInner {
        private static final DBDataTool a = new DBDataTool();

        private DBDataToolInner() {
        }
    }
}
