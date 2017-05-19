//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.Database;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import java.io.File;

public class DBContext extends ContextWrapper {
    private String rootDir;

    public DBContext(Context context, String rootdir) {
        super(context);
        this.rootDir = rootdir;
    }

    public SQLiteDatabase openOrCreateDatabase(String dbName, int var2, CursorFactory cursorFactory) {
        return SQLiteDatabase.openOrCreateDatabase(this.getDatabasePath(dbName).getAbsolutePath(), cursorFactory);
    }

    public File getDatabasePath(String name) {
        File file = new File(this.rootDir + name);
        if(!file.getParentFile().exists() && !file.getParentFile().isDirectory()) {
            file.getParentFile().mkdirs();
        }

        return file;
    }
}
