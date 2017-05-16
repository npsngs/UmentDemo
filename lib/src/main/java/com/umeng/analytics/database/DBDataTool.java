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
    private static String d = null;
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
                    values.put("__s", this.a(jsonObject.toString()));
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
            String var6 = this.a(var2);
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

    public boolean insertIntoSd(String var1, JSONObject jsonObject, a_enum var3) {
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
                    var28.put("__ii", var1);
                    var28.put("__e", String.valueOf(var24));
                    database.insert("__sd", null, var28);
                } else {
                    String var8;
                    if(var3 == a_enum.d) {
                        var24 = (Long) jsonObject.get("__f");
                        var8 = "update __sd set __f=\"" + var24 + "\" where " + "__ii" + "=\"" + var1 + "\"";
                        database.execSQL(var8);
                    } else if(var3 == a_enum.b) {
                        this.a(var1, jsonObject, database, "__a");
                    } else if(var3 == a_enum.a) {
                        this.a(var1, jsonObject, database, "__b");
                    } else if(var3 == a_enum.e) {
                        JSONObject var6 = null;

                        try {
                            var6 = jsonObject.getJSONObject("__d");
                        } catch (Exception e) {
                        }

                        String var7 = null;
                        if(var6 != null) {
                            var8 = "select __d from __sd where __ii=\"" + var1 + "\"";
                            cursor = database.rawQuery(var8, null);
                            if(cursor != null) {
                                while(cursor.moveToNext()) {
                                    var7 = cursor.getString(cursor.getColumnIndex("__d"));
                                    var7 = this.b(var7);
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
                                var9 = this.a(var25.toString());
                                if(!TextUtils.isEmpty(var9)) {
                                    var10 = "update  __sd set __d=\"" + var9 + "\" where " + "__ii" + "=\"" + var1 + "\"";
                                    database.execSQL(var10);
                                }
                            }
                        } catch (Exception var20) {
                            ;
                        }

                        try {
                            JSONObject var26 = jsonObject.getJSONObject("__c");
                            if(var26 != null) {
                                var9 = this.a(var26.toString());
                                if(!TextUtils.isEmpty(var9)) {
                                    var10 = "update  __sd set __c=\"" + var9 + "\" where " + "__ii" + "=\"" + var1 + "\"";
                                    database.execSQL(var10);
                                }
                            }
                        } catch (Exception var19) {
                            ;
                        }

                        try {
                            long var27 = jsonObject.getLong("__f");
                            var10 = "update  __sd set __f=\"" + String.valueOf(var27) + "\" where " + "__ii" + "=\"" + var1 + "\"";
                            database.execSQL(var10);
                        } catch (Exception var18) {
                            ;
                        }
                    }
                }

                database.setTransactionSuccessful();
            } catch (Throwable var22) {
                ;
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

    private void a(String var1, JSONObject var2, SQLiteDatabase database, String var4) throws JSONException {
        Cursor cursor = null;

        try {
            String sql = "select " + var4 + " from " + "__sd" + " where " + "__ii" + "=\"" + var1 + "\"";
            cursor = database.rawQuery(sql, null);
            String var7 = null;
            if(cursor != null) {
                while(cursor.moveToNext()) {
                    var7 = cursor.getString(cursor.getColumnIndex(var4));
                    var7 = this.b(var7);
                }
            }

            JSONArray jsonArray = new JSONArray();
            if(!TextUtils.isEmpty(var7)) {
                jsonArray = new JSONArray(var7);
            }

            jsonArray.put(var2);
            String var9 = this.a(jsonArray.toString());
            if(!TextUtils.isEmpty(var9)) {
                sql = "update __sd set " + var4 + "=\"" + var9 + "\" where " + "__ii" + "=\"" + var1 + "\"";
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
            cursor = database.rawQuery(var4, (String[])null);
            if(cursor != null) {
                JSONObject var5 = null;
                JSONArray var6 = null;
                JSONObject var7 = new JSONObject();
                JSONObject var8 = new JSONObject();

                String var11;
                while(cursor.moveToNext()) {
                    int var9 = cursor.getInt(cursor.getColumnIndex("__t"));
                    String var10 = cursor.getString(cursor.getColumnIndex("__i"));
                    var11 = cursor.getString(cursor.getColumnIndex("__s"));
                    if("".equals(var10)) {
                        var10 = SessionHelper.getSessionID();
                    }

                    switch(var9) {
                        case 2049:
                            if(!TextUtils.isEmpty(var11)) {
                                var5 = new JSONObject(this.b(var11));
                                if(var7.has(var10)) {
                                    var6 = var7.optJSONArray(var10);
                                } else {
                                    var6 = new JSONArray();
                                }

                                var6.put(var5);
                                var7.put(var10, var6);
                            }
                            break;
                        case 2050:
                            if(!TextUtils.isEmpty(var11)) {
                                var5 = new JSONObject(this.b(var11));
                                if(var8.has(var10)) {
                                    var6 = var8.optJSONArray(var10);
                                } else {
                                    var6 = new JSONArray();
                                }

                                var6.put(var5);
                                var8.put(var10, var6);
                            }
                    }
                }

                String var12;
                String var13;
                JSONArray var19;
                Iterator var20;
                JSONObject var21;
                if(var7.length() > 0) {
                    var19 = new JSONArray();
                    var20 = var7.keys();
                    var11 = null;

                    while(var20.hasNext()) {
                        var21 = new JSONObject();
                        var12 = (String)var20.next();
                        var13 = var7.optString(var12);
                        var21.put(var12, new JSONArray(var13));
                        if(var21.length() > 0) {
                            var19.put(var21);
                        }
                    }

                    if(var19.length() > 0) {
                        outJson.put("ekv", var19);
                    }
                }

                if(var8.length() > 0) {
                    var19 = new JSONArray();
                    var20 = var8.keys();
                    var11 = null;

                    while(var20.hasNext()) {
                        var21 = new JSONObject();
                        var12 = (String)var20.next();
                        var13 = var8.optString(var12);
                        var21.put(var12, new JSONArray(var13));
                        if(var21.length() > 0) {
                            var19.put(var21);
                        }
                    }

                    if(var19.length() > 0) {
                        outJson.put("gkv", var19);
                    }
                }

                var5 = null;
                var6 = null;
                var7 = null;
                var8 = null;
            }

            database.setTransactionSuccessful();
        } catch (Throwable var17) {
            ;
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
            cursor = database.rawQuery(var4, (String[])null);
            if(cursor != null) {
                JSONArray var5 = new JSONArray();

                while(cursor.moveToNext()) {
                    String var6 = cursor.getString(cursor.getColumnIndex("__a"));
                    if(!TextUtils.isEmpty(var6)) {
                        var5.put(new JSONObject(this.b(var6)));
                    }
                }

                if(var5.length() > 0) {
                    outJson.put("error", var5);
                }

                var5 = null;
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
                        long var9 = Long.parseLong(endTime) - Long.parseLong(startTime);
                        if(var9 > 0L) {
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
                                json.put("pages", new JSONArray(this.b(var11)));
                            }

                            if(!TextUtils.isEmpty(var12)) {
                                json.put("autopages", new JSONArray(this.b(var12)));
                            }

                            if(!TextUtils.isEmpty(var13)) {
                                json.put("traffic", new JSONObject(this.b(var13)));
                            }

                            if(!TextUtils.isEmpty(var14)) {
                                json.put("locations", new JSONArray(this.b(var14)));
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
            if(TextUtils.isEmpty(d)) {
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

                    d = stringBuilder.toString();
                }

                if(!TextUtils.isEmpty(d)) {
                    d = d + (new StringBuilder(d)).reverse().toString();
                    ekId = sp.getString("ek_key", null);
                    if(TextUtils.isEmpty(ekId)) {
                        sp.edit().putString("ek_key", this.a("umeng+")).commit();
                    } else if(!"umeng+".equals(this.b(ekId))) {
                        this.deleteData(true, false);
                    }
                }
            }
        } catch (Throwable throwable) {
        }

    }

    public String a(String var1) {
        String var2 = null;

        try {
            if(TextUtils.isEmpty(d)) {
                var2 = var1;
            } else {
                byte[] var3 = StringTool.a(var1.getBytes(), d.getBytes());
                var2 = Base64.encodeToString(var3, 0);
            }
        } catch (Exception e) {
        }

        return var2;
    }

    public String b(String source) {
        String var2 = null;

        try {
            if(TextUtils.isEmpty(d)) {
                var2 = source;
            } else {
                byte[] var3 = Base64.decode(source.getBytes(), 0);
                var2 = new String(StringTool.b(var3, d.getBytes()));
            }
        } catch (Exception e) {
        }

        return var2;
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
