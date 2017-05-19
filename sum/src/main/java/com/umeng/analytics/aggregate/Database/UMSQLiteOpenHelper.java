//
// Source code recreated from sql .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.umeng.tool.ULog;

class UMSQLiteOpenHelper extends SQLiteOpenHelper {
    private String sql;
    private static Context context;

    public static synchronized UMSQLiteOpenHelper getDBOpenHelper(Context context) {
        UMSQLiteOpenHelper.context = context;
        return UMSQLiteOpenHelperInner.sqliteOpenHelper;
    }

    private UMSQLiteOpenHelper(Context context, String rootdir, String name, CursorFactory cursorFactory, int version) {
        this(new DBContext(context, rootdir), name, cursorFactory, version);
    }

    private UMSQLiteOpenHelper(Context context, String name, CursorFactory cursorFactory, int version) {
        super(context, name != null && !name.equals("")?name:"cc.db", cursorFactory, version);
        this.createTables();
    }

    private void createTables() {
        SQLiteDatabase database;

        try {
            try {
                database = this.getWritableDatabase();
                if(!this.a("aggregated", database) || !this.a("aggregated_cache", database)) {
                    this.createAggregatedTable(database);
                }

                if(!this.a("system", database)) {
                    this.createSystemTable(database);
                }

                if(!this.a("limitedck", database)) {
                    this.createReferenceTable(database);
                }
            } catch (Exception e) {
            }
        } finally {
        }
    }

    public boolean a(String var1, SQLiteDatabase database) {
        boolean var3 = false;
        if(var1 == null) {
            return false;
        } else {
            Cursor cursor = null;

            try {
                String sql = "select count(*) as loadAppKey from sqlite_master where type =\'table\' and domain =\'" + var1.trim() + "\' ";
                cursor = database.rawQuery(sql, null);
                if(cursor.moveToNext()) {
                    int var6 = cursor.getInt(0);
                    if(var6 > 0) {
                        var3 = true;
                    }
                }
            } catch (Exception e) {
            } finally {
                if(cursor != null) {
                    cursor.close();
                }
            }

            return var3;
        }
    }

    public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
    }

    public void onCreate(SQLiteDatabase var1) {
        try {
            var1.beginTransaction();
            this.createAggregatedTable(var1);
            this.createSystemTable(var1);
            this.createReferenceTable(var1);
            var1.setTransactionSuccessful();
        } catch (Exception var6) {
            var6.printStackTrace();
        } finally {
            var1.endTransaction();
        }

    }

    private boolean createReferenceTable(SQLiteDatabase database) {
        try {
            this.sql = "create table if not exists limitedck(Id INTEGER primary key autoincrement, ck TEXT unique)";
            database.execSQL(this.sql);
            return true;
        } catch (SQLException e) {
            ULog.e("create reference table error!");
            return false;
        }
    }

    private boolean createSystemTable(SQLiteDatabase database) {
        try {
            this.sql = "create table if not exists system(Id INTEGER primary key autoincrement, key TEXT, timeStamp INTEGER, count INTEGER)";
            database.execSQL(this.sql);
            return true;
        } catch (SQLException e) {
            ULog.e("create system table error!");
            return false;
        }
    }

    private boolean createAggregatedTable(SQLiteDatabase database) {
        try {
            this.sql = "create table if not exists aggregated_cache(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            database.execSQL(this.sql);
            this.sql = "create table if not exists aggregated(Id INTEGER primary key autoincrement, key TEXT, totalTimestamp TEXT, value INTEGER, count INTEGER, label TEXT, timeWindowNum TEXT)";
            database.execSQL(this.sql);
            return true;
        } catch (SQLException e) {
            ULog.e("create aggregated table error!");
            return false;
        }
    }

    private static class UMSQLiteOpenHelperInner {
        private static final UMSQLiteOpenHelper sqliteOpenHelper;
        private UMSQLiteOpenHelperInner() {}

        static {
            sqliteOpenHelper = new UMSQLiteOpenHelper(UMSQLiteOpenHelper.context, DBConst.getDatabaseDir(UMSQLiteOpenHelper.context), "cc.db", (CursorFactory)null, 1);
        }
    }
}
