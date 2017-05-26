//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Base64;

import com.umeng.tool.*;
import com.umeng.analytics.d.SP_Util;
import com.umeng.analytics.d.SessionHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBDataTool {
    private static Context context = null;
    private static String key = null;
    private List<String> list;
    public static final int a = 2049;
    public static final int b = 2050;

    private DBDataTool() {
        this.list = new ArrayList();
        if(context != null) {
            this.b();
            this.list.clear();
        }

    }

    public static final DBDataTool getInstance(Context c) {
        context = c;
        return DBDataToolInner.dbTool;
    }

    public void insertToEt(JSONArray jsonArray) {
        SQLiteDatabase database = null;
        JSONObject jsonObject;
        String sessionId;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            database.beginTransaction();

            for(int i = 0; i < jsonArray.length(); ++i) {
                try {
                    jsonObject = jsonArray.getJSONObject(i);
                    ContentValues values = new ContentValues();
                    sessionId = jsonObject.optString("__i");
                    if(TextUtils.isEmpty(sessionId)) {
                        sessionId = SessionHelper.getSessionID(context);
                        if(TextUtils.isEmpty(sessionId)) {
                            sessionId = "";
                        }
                    }

                    values.put("__i", sessionId);
                    values.put("__e", jsonObject.optString("id"));
                    values.put("__t", Integer.valueOf(jsonObject.optInt("__t")));
                    jsonObject.remove("__i");
                    jsonObject.remove("__t");
                    values.put("__s", this.encode(jsonObject.toString()));
                    database.insert("__et", null, values);
                } catch (Exception e) {
                }
            }

            database.setTransactionSuccessful();
        } catch (Throwable t) {
        } finally {
            database.endTransaction();
            DBUtil.getInstance(context).closeDB();
        }

    }

    public boolean insertToEr(String var1, String var2, int var3) {
        SQLiteDatabase database = null;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            database.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("__i", var1);
            String var6 = this.encode(var2);
            if(!TextUtils.isEmpty(var6)) {
                values.put("__a", var6);
                values.put("__t", Integer.valueOf(var3));
                database.insert("__er", null, values);
            }

            database.setTransactionSuccessful();
        } catch (Throwable t) {
        } finally {
            database.endTransaction();
            DBUtil.getInstance(context).closeDB();
        }

        return false;
    }

    public boolean insertIntoSd(String id, JSONObject jsonObject, a_enum var3) {
        if(jsonObject == null) {
            return false;
        } else {
            SQLiteDatabase database = null;
            Cursor cursor = null;

            try {
                database = DBUtil.getInstance(context).getWritableDatabase();
                database.beginTransaction();
                long var24;
                if(var3 == a_enum.c) {
                    var24 = (Long) jsonObject.get("__e");
                    ContentValues var28 = new ContentValues();
                    var28.put("__ii", id);
                    var28.put("__e", String.valueOf(var24));
                    database.insert("__sd", null, var28);
                } else {
                    String var8;
                    if(var3 == a_enum.d) {
                        var24 = (Long) jsonObject.get("__f");
                        var8 = "update __sd set __f=\"" + var24 + "\" where " + "__ii" + "=\"" + id + "\"";
                        database.execSQL(var8);
                    } else if(var3 == a_enum.b) {
                        this.a(id, jsonObject, database, "__a");
                    } else if(var3 == a_enum.a) {
                        this.a(id, jsonObject, database, "__b");
                    } else if(var3 == a_enum.e) {
                        JSONObject var6 = null;

                        try {
                            var6 = jsonObject.getJSONObject("__d");
                        } catch (Exception e) {
                        }

                        String var7 = null;
                        if(var6 != null) {
                            var8 = "select __d from __sd where __ii=\"" + id + "\"";
                            cursor = database.rawQuery(var8, null);
                            if(cursor != null) {
                                while(cursor.moveToNext()) {
                                    var7 = cursor.getString(cursor.getColumnIndex("__d"));
                                    var7 = this.decode(var7);
                                }
                            }
                        }

                        String var9;
                        String var10;
                        try {
                            if(var6 != null) {
                                JSONArray var25 = new JSONArray();
                                if(!TextUtils.isEmpty(var7)) {
                                    var25 = new JSONArray(var7);
                                }

                                var25.put(var6);
                                var9 = this.encode(var25.toString());
                                if(!TextUtils.isEmpty(var9)) {
                                    var10 = "update  __sd set __d=\"" + var9 + "\" where " + "__ii" + "=\"" + id + "\"";
                                    database.execSQL(var10);
                                }
                            }
                        } catch (Exception e) {
                        }

                        try {
                            JSONObject var26 = jsonObject.getJSONObject("__c");
                            if(var26 != null) {
                                var9 = this.encode(var26.toString());
                                if(!TextUtils.isEmpty(var9)) {
                                    var10 = "update  __sd set __c=\"" + var9 + "\" where " + "__ii" + "=\"" + id + "\"";
                                    database.execSQL(var10);
                                }
                            }
                        } catch (Exception e) {
                        }

                        try {
                            long var27 = jsonObject.getLong("__f");
                            var10 = "update  __sd set __f=\"" + String.valueOf(var27) + "\" where " + "__ii" + "=\"" + id + "\"";
                            database.execSQL(var10);
                        } catch (Exception e) {
                        }
                    }
                }

                database.setTransactionSuccessful();
            } catch (Throwable t) {
            } finally {
                if(cursor != null) {
                    cursor.close();
                }
                database.endTransaction();
                DBUtil.getInstance(context).closeDB();
            }

            return false;
        }
    }

    private void a(String id, JSONObject var2, SQLiteDatabase database, String var4) throws JSONException {
        Cursor cursor = null;

        try {
            String sql = "select " + var4 + " from " + "__sd" + " where " + "__ii" + "=\"" + id + "\"";
            cursor = database.rawQuery(sql, null);
            String var7 = null;
            if(cursor != null) {
                while(cursor.moveToNext()) {
                    var7 = cursor.getString(cursor.getColumnIndex(var4));
                    var7 = this.decode(var7);
                }
            }

            JSONArray jsonArray = new JSONArray();
            if(!TextUtils.isEmpty(var7)) {
                jsonArray = new JSONArray(var7);
            }

            jsonArray.put(var2);
            String var9 = this.encode(jsonArray.toString());
            if(!TextUtils.isEmpty(var9)) {
                sql = "update __sd set " + var4 + "=\"" + var9 + "\" where " + "__ii" + "=\"" + id + "\"";
                database.execSQL(sql);
            }
        } catch (Throwable t) {
        } finally {
            if(cursor != null) {
                cursor.close();
            }

        }

    }

    public JSONObject selectAll() {
        JSONObject ret = new JSONObject();
        JSONObject body = new JSONObject();
        this.selectFromSd(body);
        this.selectFromER(body);
        this.selectFromET(body);

        try {
            if(body.length() > 0) {
                ret.put("body", body);
            }
        } catch (Throwable t) {
        }

        return ret;
    }

    private void selectFromET(JSONObject outJson) {
        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            database.beginTransaction();
            String var4 = "select *  from __et";
            cursor = database.rawQuery(var4, null);
            if(cursor != null) {
                JSONObject var5;
                JSONArray var6;
                JSONObject var7 = new JSONObject();
                JSONObject var8 = new JSONObject();

                String var11;
                while(cursor.moveToNext()) {
                    int var9 = cursor.getInt(cursor.getColumnIndex("__t"));
                    String sessionId = cursor.getString(cursor.getColumnIndex("__i"));
                    var11 = cursor.getString(cursor.getColumnIndex("__s"));
                    if("".equals(sessionId)) {
                        sessionId = SessionHelper.getSessionID();
                    }

                    switch(var9) {
                        case 2049:
                            if(!TextUtils.isEmpty(var11)) {
                                var5 = new JSONObject(this.decode(var11));
                                if(var7.has(sessionId)) {
                                    var6 = var7.optJSONArray(sessionId);
                                } else {
                                    var6 = new JSONArray();
                                }

                                var6.put(var5);
                                var7.put(sessionId, var6);
                            }
                            break;
                        case 2050:
                            if(!TextUtils.isEmpty(var11)) {
                                var5 = new JSONObject(this.decode(var11));
                                if(var8.has(sessionId)) {
                                    var6 = var8.optJSONArray(sessionId);
                                } else {
                                    var6 = new JSONArray();
                                }

                                var6.put(var5);
                                var8.put(sessionId, var6);
                            }
                    }
                }

                String var12;
                String var13;
                JSONArray etArray;
                Iterator iterator;
                JSONObject et;
                if(var7.length() > 0) {
                    etArray = new JSONArray();
                    iterator = var7.keys();

                    while(iterator.hasNext()) {
                        et = new JSONObject();
                        var12 = (String)iterator.next();
                        var13 = var7.optString(var12);
                        et.put(var12, new JSONArray(var13));
                        if(et.length() > 0) {
                            etArray.put(et);
                        }
                    }

                    if(etArray.length() > 0) {
                        outJson.put("ekv", etArray);
                    }
                }

                if(var8.length() > 0) {
                    etArray = new JSONArray();
                    iterator = var8.keys();

                    while(iterator.hasNext()) {
                        et = new JSONObject();
                        var12 = (String)iterator.next();
                        var13 = var8.optString(var12);
                        et.put(var12, new JSONArray(var13));
                        if(et.length() > 0) {
                            etArray.put(et);
                        }
                    }

                    if(etArray.length() > 0) {
                        outJson.put("gkv", etArray);
                    }
                }
            }

            database.setTransactionSuccessful();
        } catch (Throwable t) {
        } finally {
            if(cursor != null) {
                cursor.close();
            }

            database.endTransaction();
            DBUtil.getInstance(context).closeDB();
        }

    }

    private void selectFromER(JSONObject outJson) {
        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            database.beginTransaction();
            String var4 = "select *  from __er";
            cursor = database.rawQuery(var4, null);
            if(cursor != null) {
                JSONArray var5 = new JSONArray();

                while(cursor.moveToNext()) {
                    String var6 = cursor.getString(cursor.getColumnIndex("__a"));
                    if(!TextUtils.isEmpty(var6)) {
                        var5.put(new JSONObject(this.decode(var6)));
                    }
                }

                if(var5.length() > 0) {
                    outJson.put("error", var5);
                }
            }

            database.setTransactionSuccessful();
        } catch (Throwable var10) {
            ;
        } finally {
            if(cursor != null) {
                cursor.close();
            }

            database.endTransaction();
            DBUtil.getInstance(context).closeDB();
        }

    }

    private void selectFromSd(JSONObject jsonObject) {
        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            database.beginTransaction();
            String sql = "select *  from __sd";
            cursor = database.rawQuery(sql, null);
            if(cursor != null) {
                JSONArray jsonArray = new JSONArray();
                JSONObject json;
                this.list.clear();

                while(cursor.moveToNext()) {
                    json = new JSONObject();
                    String endTime = cursor.getString(cursor.getColumnIndex("__f"));
                    String startTime = cursor.getString(cursor.getColumnIndex("__e"));
                    if(!TextUtils.isEmpty(endTime) && !TextUtils.isEmpty(startTime)) {
                        long duration = Long.parseLong(endTime) - Long.parseLong(startTime);
                        if(duration > 0L) {
                            String var11 = cursor.getString(cursor.getColumnIndex("__a"));
                            String var12 = cursor.getString(cursor.getColumnIndex("__b"));
                            String var13 = cursor.getString(cursor.getColumnIndex("__c"));
                            String var14 = cursor.getString(cursor.getColumnIndex("__d"));
                            String id = cursor.getString(cursor.getColumnIndex("__ii"));
                            this.list.add(id);
                            json.put("id", id);
                            json.put("start_time", startTime);
                            json.put("end_time", endTime);
                            json.put("duration", Long.parseLong(endTime) - Long.parseLong(startTime));
                            if(!TextUtils.isEmpty(var11)) {
                                json.put("pages", new JSONArray(this.decode(var11)));
                            }

                            if(!TextUtils.isEmpty(var12)) {
                                json.put("autopages", new JSONArray(this.decode(var12)));
                            }

                            if(!TextUtils.isEmpty(var13)) {
                                json.put("traffic", new JSONObject(this.decode(var13)));
                            }

                            if(!TextUtils.isEmpty(var14)) {
                                json.put("locations", new JSONArray(this.decode(var14)));
                            }

                            if(json.length() > 0) {
                                jsonArray.put(json);
                            }
                        }
                    }
                }

                if(jsonArray.length() > 0) {
                    jsonObject.put("sessions", jsonArray);
                }
            }

            database.setTransactionSuccessful();
        } catch (Throwable t) {
        } finally {
            if(cursor != null) {
                cursor.close();
            }

            database.endTransaction();
            DBUtil.getInstance(context).closeDB();
        }

    }

    public void deleteData(boolean var1, boolean var2) {
        SQLiteDatabase database = null;

        try {
            database = DBUtil.getInstance(context).getWritableDatabase();
            database.beginTransaction();
            String var4 = "delete from __er";
            database.execSQL(var4);
            var4 = "delete from __et";
            database.execSQL(var4);
            if(var2) {
                if(var1) {
                    var4 = "delete from __sd";
                    database.execSQL(var4);
                }
            } else {
                if(this.list.size() > 0) {
                    for(int var5 = 0; var5 < this.list.size(); ++var5) {
                        var4 = "delete from __sd where __ii=\"" + (String)this.list.get(var5) + "\"";
                        database.execSQL(var4);
                    }
                }

                this.list.clear();
            }

            database.setTransactionSuccessful();
        } catch (Throwable t) {
        } finally {
            database.endTransaction();
            DBUtil.getInstance(context).closeDB();
        }

    }

    private void b() {
        try {
            if(TextUtils.isEmpty(key)) {
                SharedPreferences sp = SP_Util.getSp(context);
                String ekId = sp.getString("ek__id",null);
                if(TextUtils.isEmpty(ekId)) {
                    ekId = SystemUtil.getAndroidID(context);
                    if(!TextUtils.isEmpty(ekId)) {
                        sp.edit().putString("ek__id", ekId).apply();
                    }
                }

                if(!TextUtils.isEmpty(ekId)) {
                    ekId = ekId.substring(1, 9);
                    StringBuilder stringBuilder = new StringBuilder();

                    for(int i = 0; i < ekId.length(); ++i) {
                        char var4 = ekId.charAt(i);
                        if(Character.isDigit(var4)) {
                            if(Integer.parseInt(Character.toString(var4)) == 0) {
                                stringBuilder.append(0);
                            } else {
                                stringBuilder.append(10 - Integer.parseInt(Character.toString(var4)));
                            }
                        } else {
                            stringBuilder.append(var4);
                        }
                    }

                    key = stringBuilder.toString();
                }

                if(!TextUtils.isEmpty(key)) {
                    key = key + (new StringBuilder(key)).reverse().toString();
                    ekId = sp.getString("ek_key", null);
                    if(TextUtils.isEmpty(ekId)) {
                        sp.edit().putString("ek_key", this.encode("umeng+")).commit();
                    } else if(!"umeng+".equals(this.decode(ekId))) {
                        this.deleteData(true, false);
                    }
                }
            }
        } catch (Throwable throwable) {
        }

    }

    public String encode(String str) {
        String ret = null;

        try {
            if(TextUtils.isEmpty(key)) {
                ret = str;
            } else {
                byte[] data = StringTool.encrypt(str.getBytes(), key.getBytes());
                ret = Base64.encodeToString(data, 0);
            }
        } catch (Exception e) {
        }

        return ret;
    }

    public String decode(String source) {
        String ret = null;

        try {
            if(TextUtils.isEmpty(key)) {
                ret = source;
            } else {
                byte[] data = Base64.decode(source.getBytes(), 0);
                ret = new String(StringTool.decrypt(data, key.getBytes()));
            }
        } catch (Exception e) {
        }

        return ret;
    }

    public enum a_enum {
        a,
        b,
        c,
        d,
        e;

        a_enum() {
        }
    }

    private static class DBDataToolInner {
        private static final DBDataTool dbTool = new DBDataTool();

        private DBDataToolInner() {
        }
    }
}
