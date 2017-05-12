//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

class DBUtil {
    private AtomicInteger a = new AtomicInteger();
    private AtomicInteger b = new AtomicInteger();
    private static DBUtil dbUtil;
    private static SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;

    DBUtil() {
    }

    private static synchronized void createInstance(Context context) {
        if(dbUtil == null) {
            dbUtil = new DBUtil();
            sqLiteOpenHelper = MYSQLiteOpenHelper.a(context);
        }

    }

    public static synchronized DBUtil getInstance(Context context) {
        if(dbUtil == null) {
            createInstance(context);
        }

        return dbUtil;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        if(this.a.incrementAndGet() == 1) {
            this.database = sqLiteOpenHelper.getWritableDatabase();
        }

        return this.database;
    }

    public synchronized void closeDB() {
        if(this.a.decrementAndGet() == 0) {
            this.database.close();
        }

        if(this.b.decrementAndGet() == 0) {
            this.database.close();
        }

    }
}
