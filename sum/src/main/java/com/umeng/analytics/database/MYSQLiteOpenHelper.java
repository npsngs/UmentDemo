//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

class MYSQLiteOpenHelper extends SQLiteOpenHelper {
    private String sql;
    private static Context context = null;

    public static synchronized MYSQLiteOpenHelper a(Context var0) {
        context = var0;
        return MYSQLiteOpenHelperInner.sqliteOpenHelper;
    }

    private MYSQLiteOpenHelper(Context context, String dbName, String name, CursorFactory cursorFactory, int version) {
        this(new DBContext(context, dbName), name, cursorFactory, version);
    }

    private MYSQLiteOpenHelper(Context context, String name, CursorFactory cursorFactory, int version) {
        super(context, name != null && !name.equals("")?name:"ua.db", cursorFactory, version);
        this.sql = null;
        this.createTables();
    }

    private void createTables() {
        SQLiteDatabase database;

        try {
            database = this.getWritableDatabase();
            if(!StringUtil.hasTable("__sd", database)) {
                this.createTableSd(database);
            }

            if(!StringUtil.hasTable("__et", database)) {
                this.createTableEt(database);
            }

            if(!StringUtil.hasTable("__er", database)) {
                this.createTableEr(database);
            }
        } catch (Exception e) {
        }
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            this.createTableSd(db);
            this.createTableEt(db);
            this.createTableEr(db);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

    }

    private void createTableEr(SQLiteDatabase database) {
        try {
            this.sql = "create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER)";
            database.execSQL(this.sql);
        } catch (SQLException e) {
        }
    }

    private void createTableEt(SQLiteDatabase database) {
        try {
            this.sql = "create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER)";
            database.execSQL(this.sql);
        } catch (SQLException e) {
        }

    }

    private void createTableSd(SQLiteDatabase database) {
        try {
            this.sql = "create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT)";
            database.execSQL(this.sql);
        } catch (SQLException e) {
        }

    }

    public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
    }

    private static class MYSQLiteOpenHelperInner {
        private static final MYSQLiteOpenHelper sqliteOpenHelper;

        private MYSQLiteOpenHelperInner() {
        }

        static {
            sqliteOpenHelper = new MYSQLiteOpenHelper(MYSQLiteOpenHelper.context, StringUtil.a(MYSQLiteOpenHelper.context), "ua.db", null, 1);
        }
    }
}
