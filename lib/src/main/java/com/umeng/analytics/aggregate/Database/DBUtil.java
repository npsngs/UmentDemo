//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class DBUtil {
    private AtomicInteger a = new AtomicInteger();
    private AtomicInteger b = new AtomicInteger();
    private static DBUtil instance;
    private static SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;

    public DBUtil() {
    }

    private static synchronized void createInstance(Context context) {
        if(instance == null) {
            instance = new DBUtil();
            sqLiteOpenHelper = UMSQLiteOpenHelper.getDBOpenHelper(context);
        }

    }

    public static synchronized DBUtil getInstance(Context context) {
        if(instance == null) {
            createInstance(context);
        }

        return instance;
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        if(this.a.incrementAndGet() == 1) {
            this.database = sqLiteOpenHelper.getReadableDatabase();
        }

        return this.database;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        if(this.a.incrementAndGet() == 1) {
            this.database = sqLiteOpenHelper.getWritableDatabase();
        }

        return this.database;
    }

    public synchronized void closeDatabase() {
        if(this.a.decrementAndGet() == 0) {
            this.database.close();
        }

        if(this.b.decrementAndGet() == 0) {
            this.database.close();
        }

    }
}
