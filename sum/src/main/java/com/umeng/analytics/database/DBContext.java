//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.database;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import java.io.File;

class DBContext extends ContextWrapper {
    private String dbName;

    public DBContext(Context context, String dbName) {
        super(context);
        this.dbName = dbName;
    }

    public SQLiteDatabase openOrCreateDatabase(String dbName, int var2, CursorFactory cursorFactory) {
        return SQLiteDatabase.openDatabase(this.getDatabasePath(dbName).getAbsolutePath(), cursorFactory, 268435472);
    }

    public File getDatabasePath(String dbName) {
        File dbDir = new File(this.dbName + dbName);
        if(!dbDir.getParentFile().exists() && !dbDir.getParentFile().isDirectory()) {
            dbDir.getParentFile().mkdirs();
        }

        return dbDir;
    }
}
