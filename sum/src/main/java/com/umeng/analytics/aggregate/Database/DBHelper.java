//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.umeng.analytics.aggregate.tool.StringCheck;
import com.umeng.analytics.aggregate.data.AggData;
import com.umeng.analytics.aggregate.result.OpResult;
import com.umeng.tool.ULog;
import com.umeng.analytics.aggregate.data.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class DBHelper {
    public DBHelper() {
    }

    public static boolean dropTable(SQLiteDatabase db, String tableName) {
        try {
            String sql = "drop table if exists " + tableName;
            db.execSQL(sql);
            return true;
        } catch (SQLException e) {
            ULog.e("delete table faild!");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean cleanTableData(SQLiteDatabase db, String tableName) {
        try {
            if(getRowCount(db, tableName) >= 0) {
                String var2 = "delete from " + tableName;
                db.execSQL(var2);
            }

            return true;
        } catch (SQLException e) {
            ULog.e("cleanTableData faild!" + e.toString());
            return false;
        }
    }

    public static int getRowCount(SQLiteDatabase database, String table) {
        int count = 0;
        Cursor cursor = null;

        try {
            cursor = database.rawQuery("select * from " + table, null);
            count = cursor.getCount();
        } catch (Exception var8) {
            ULog.e("count error " + var8.toString());
        } finally {
            if(cursor != null) {
                cursor.close();
            }

        }

        return count;
    }

    public static boolean insertToAggregatedTable(SQLiteDatabase database, Collection<b> var1) {
        boolean isSuccess;
        try {
            database.beginTransaction();
            if(getRowCount(database, "aggregated_cache") > 0) {
                cleanTableData(database, "aggregated_cache");
            }

            Iterator iterator = var1.iterator();

            while(iterator.hasNext()) {
                b var10 = (b)iterator.next();
                ContentValues var4 = toContentValues(var10);
                database.insert("aggregated_cache", null, var4);
            }

            database.setTransactionSuccessful();
            return true;
        } catch (SQLException e) {
            ULog.e("insert to Aggregated cache table faild!");
            isSuccess = false;
        } finally {
            database.endTransaction();
        }

        return isSuccess;
    }

    public static boolean insertToAggregatedTable(OpResult opResult, SQLiteDatabase database, Collection<b> var2) {
        boolean isSuccess;
        try {
            database.beginTransaction();
            Iterator iterator = var2.iterator();

            while(iterator.hasNext()) {
                b var11 = (b)iterator.next();
                ContentValues var5 = toContentValues(var11);
                database.insert("aggregated", null, var5);
            }

            database.setTransactionSuccessful();
            cleanTableData(database, "aggregated_cache");
            opResult.setResult("success", false);
            return true;
        } catch (SQLException e) {
            ULog.e("insert to Aggregated cache table faild!");
            isSuccess = false;
        } finally {
            database.endTransaction();
        }

        return isSuccess;
    }

    public static boolean cacheToAggregatedTable(SQLiteDatabase database, OpResult opResult) {
        try {
            database.beginTransaction();
            if(getRowCount(database, "aggregated_cache") <= 0) {
                opResult.setResult("faild", false);
                return false;
            }

            String sql = "insert into aggregated(key, count, value, totalTimestamp, timeWindowNum, label) select key, count, value, totalTimestamp, timeWindowNum, label from aggregated_cache";
            database.execSQL(sql);
            database.setTransactionSuccessful();
            cleanTableData(database, "aggregated_cache");
            opResult.setResult("success", false);
        } catch (SQLException e) {
            opResult.setResult(Boolean.valueOf(false), false);
            ULog.e("cacheToAggregatedTable happen " + e.toString());
            return false;
        } finally {
            database.endTransaction();
        }

        return true;
    }

    private static ContentValues toContentValues(b var0) {
        ContentValues var1 = new ContentValues();
        var1.put("key", var0.keysJoinToStr());
        var1.put("label", var0.labelsJoinToStr());
        var1.put("count", Long.valueOf(var0.getCount()));
        var1.put("value", Long.valueOf(var0.getValue()));
        var1.put("totalTimestamp", Long.valueOf(var0.getCurrentMills()));
        var1.put("timeWindowNum", var0.getTotalTimeStamp());
        return var1;
    }

    public static boolean cacheToMemory(SQLiteDatabase database, OpResult opResult) {
        boolean isSuccess = false;
        Cursor cursor = null;

        try {
            HashMap hashMap = new HashMap();
            cursor = database.rawQuery("select * from aggregated_cache", null);

            while(cursor.moveToNext()) {
                b var4 = new b();
                var4.setKeys(DBConst.joinStrToList(cursor.getString(cursor.getColumnIndex("key"))));
                var4.setLables(DBConst.joinStrToList(cursor.getString(cursor.getColumnIndex("label"))));
                var4.setCount((long)cursor.getInt(cursor.getColumnIndex("count")));
                var4.setValue((long)cursor.getInt(cursor.getColumnIndex("value")));
                var4.setTotalTimeStamp(cursor.getString(cursor.getColumnIndex("timeWindowNum")));
                var4.setCurrentMillis(Long.parseLong(cursor.getString(cursor.getColumnIndex("totalTimestamp"))));
                hashMap.put(DBConst.joinStrToList(cursor.getString(cursor.getColumnIndex("key"))), var4);
            }

            if(hashMap.size() > 0) {
                opResult.setResult(hashMap, false);
            } else {
                opResult.setResult("faild", false);
            }
        } catch (SQLException e) {
            opResult.setResult(Boolean.valueOf(false), false);
            ULog.e("cacheToMemory happen " + e.toString());
        } finally {
            if(cursor != null) {
                cursor.close();
            }

        }

        return isSuccess;
    }

    public static void cleanCache(SQLiteDatabase database, boolean var1, OpResult opResult) {
        cleanTableData(database, "system");
        cleanTableData(database, "aggregated");
        if(!var1) {
            cleanTableData(database, "limitedck");
            opResult.setResult("success", false);
        }

    }

    public static void insertToSystemTable(SQLiteDatabase database, String key, long count, long timeStamp) {
        try {
            int rowCount = getRowCount(database, "system");
            int var7 = StringCheck.getInstance().c();
            ContentValues values;
            if(rowCount < var7) {
                values = new ContentValues();
                values.put("key", key);
                values.put("timeStamp", Long.valueOf(timeStamp));
                values.put("count", Long.valueOf(count));
                database.insert("system", null, values);
            } else if(rowCount == var7) {
                values = new ContentValues();
                values.put("key", "__meta_ve_of");
                values.put("timeStamp", Long.valueOf(System.currentTimeMillis()));
                values.put("count", Integer.valueOf(1));
                database.insert("system", null, values);
            } else {
                updateCountValue(database, "__meta_ve_of");
            }
        } catch (SQLException e) {
        }

    }

    private static void updateCountValue(SQLiteDatabase database, String key) {
        try {
            database.beginTransaction();
            String var2 = "update system set count=count+1 where key like \'" + key + "\'";
            database.execSQL(var2);
            database.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            if(database != null) {
                database.endTransaction();
            }
        }
    }

    public static void insertToLimitCKTable(OpResult opResult, SQLiteDatabase database, List<String> list) {
        try {
            database.beginTransaction();
            if(getRowCount(database, "limitedck") > 0) {
                cleanTableData(database, "limitedck");
            }

            ContentValues values;
            Iterator iterator = list.iterator();

            while(iterator.hasNext()) {
                String ck = (String)iterator.next();
                values = new ContentValues();
                values.put("ck", ck);
                database.insert("limitedck", null, values);
            }

            database.setTransactionSuccessful();
            opResult.setResult("success", false);
        } catch (SQLException var9) {
            ULog.e("insertToLimitCKTable error " + var9.toString());
        } finally {
            database.endTransaction();
        }

    }

    public static void saveToSystemTable(SQLiteDatabase database, Map<String, AggData> map, OpResult opResult) {
        Cursor cursor = null;

        try {
            AggData ageData = map.get("__ag_of");
            if(ageData == null) {
                return;
            }

            String sql = "system where key=\"__ag_of\"";
            cursor = database.rawQuery("select * from " + sql, null);
            int count = 0;
            long timeStamp = 0L;
            cursor.moveToFirst();

            for(; !cursor.isAfterLast(); cursor.moveToNext()) {
                if(cursor.getCount() > 0) {
                    count = cursor.getInt(cursor.getColumnIndex("count"));
                    timeStamp = cursor.getLong(cursor.getColumnIndex("timeStamp"));
                    database.execSQL("delete from " + sql);
                }
            }

            ContentValues values = new ContentValues();
            values.put("key", ageData.getKey());
            values.put("count", Long.valueOf(count == 0?ageData.getCount():(long)count + ageData.getCount()));
            values.put("timeStamp", Long.valueOf(timeStamp == 0L?ageData.getTimeStamp():timeStamp));
            database.insert("system", null, values);
            opResult.setResult("success", false);
        } catch (SQLException e) {
            ULog.e("save to system table error " + e.toString());
        } finally {
            if(cursor != null) {
                cursor.close();
            }
        }
    }

    public static String queryLastTimeWindowNumFromCache(SQLiteDatabase database) {
        String timeWindowNum = null;
        Cursor cursor = null;

        try {
            database.beginTransaction();
            if(getRowCount(database, "aggregated_cache") <= 0) {
                String var3 = String.valueOf(0);
                return var3;
            }

            cursor = database.rawQuery("select * from aggregated_cache", null);
            if(cursor.moveToLast()) {
                timeWindowNum = cursor.getString(cursor.getColumnIndex("timeWindowNum"));
            }

            database.setTransactionSuccessful();
        } catch (SQLException e) {
            ULog.e("queryLastTimeWindowNumFromCache error " + e.toString());
            return timeWindowNum;
        } finally {
            if(cursor != null) {
                cursor.close();
            }

            database.endTransaction();
        }

        return timeWindowNum;
    }

    public static JSONObject readAllAggregatedDataForUpload(SQLiteDatabase database) {
        Cursor cursor = null;
        try {
            if(getRowCount(database, "aggregated") <= 0) {
                return null;
            } else {
                cursor = database.rawQuery("select * from aggregated", null);
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray;

                while(cursor.moveToNext()) {
                    try {
                        String key = cursor.getString(cursor.getColumnIndex("key"));
                        if(jsonObject.has(key)) {
                            jsonArray = jsonObject.getJSONArray(key);
                            jsonObject.remove(key);
                        } else {
                            jsonArray = new JSONArray();
                        }

                        JSONObject json = new JSONObject();
                        json.put("v_sum", cursor.getLong(cursor.getColumnIndex("value")));
                        json.put("ts_sum", cursor.getLong(cursor.getColumnIndex("totalTimestamp")));
                        json.put("tw_num", Integer.parseInt(cursor.getString(cursor.getColumnIndex("timeWindowNum"))));
                        json.put("count", cursor.getInt(cursor.getColumnIndex("count")));
                        json.put("labels", DBConst.joinStrToJSONArray(cursor.getString(cursor.getColumnIndex("label"))));
                        jsonArray.put(json);
                        jsonObject.put(key, jsonArray);
                    } catch (Exception e) {
                    }
                }
                return jsonObject;
            }
        } catch (SQLException e1) {
            ULog.e("readAllAggregatedDataForUpload error " + e1.toString());
            return null;
        } finally {
            if(cursor != null) {
                cursor.close();
            }

        }
    }

    public static JSONObject readAllSystemDataForUpload(OpResult opResult, SQLiteDatabase database) {
        Cursor cursor = null;

        try {
            JSONObject jsonObject = new JSONObject();
            if(getRowCount(database, "system") > 0) {
                cursor = database.rawQuery("select * from system", null);
                JSONArray jsonArray;
                JSONObject jsonObject1;

                while(cursor.moveToNext()) {
                    try {
                        String key = cursor.getString(cursor.getColumnIndex("key"));
                        if(jsonObject.has(key)) {
                            jsonArray = jsonObject.getJSONArray(key);
                            jsonObject.remove(key);
                        } else {
                            jsonArray = new JSONArray();
                        }

                        jsonObject1 = new JSONObject();
                        jsonObject1.put("value", cursor.getInt(cursor.getColumnIndex("count")));
                        jsonObject1.put("ts", cursor.getLong(cursor.getColumnIndex("timeStamp")));
                        jsonArray.put(jsonObject1);
                        jsonObject.put(key, jsonArray);
                    } catch (Exception e) {
                    }
                }
            }

            return jsonObject;
        } catch (SQLException e1) {
            opResult.setResult("faild", false);
            ULog.e("readAllSystemDataForUpload error " + e1.toString());
        } finally {
            if(cursor != null) {
                cursor.close();
            }

        }

        return null;
    }

    public static List<String> loadLimitCKFromDB(SQLiteDatabase database) {
        Cursor cursor = null;

        try {
            if(getRowCount(database, "limitedck") <= 0) {
                return null;
            } else {
                cursor = database.rawQuery("select * from limitedck", (String[])null);
                ArrayList arrayList = new ArrayList();

                while(cursor.moveToNext()) {
                    String ck = cursor.getString(cursor.getColumnIndex("ck"));
                    arrayList.add(ck);
                }

                return arrayList;
            }
        } catch (SQLException e) {
            ULog.e("loadLimitCKFromDB error " + e.toString());
            return null;
        } finally {
            if(cursor != null) {
                cursor.close();
            }

        }
    }
}
