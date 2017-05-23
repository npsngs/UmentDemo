package com.yxd.ums;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.concurrent.atomic.AtomicInteger;

public class SeedDBTool {
    private AtomicInteger a = new AtomicInteger();
    private AtomicInteger b = new AtomicInteger();
    private SeedDBHelper sqLiteOpenHelper;
    private SQLiteDatabase database;

    public SeedDBTool(Context context) {
        sqLiteOpenHelper = new SeedDBHelper(context, "sbs", null, 1);
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


    public String createInsertSql(){
        return "INSERT INTO seed_configs(seed, success, resolution, deviceModel, " +
                "deviceName, deviceBoard, deviceBrand, deviceManuid, deviceManutime, " +
                "deviceId, mac, cpu, osVersion, idmd5, idTracking, imprint) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public String createSelectSql(){
        return "SELECT * FROM seed_configs WHERE seed='%s'";
    }

    public String createUpdateSuccessSql(){
        return "UPDATE seed_configs SET success=%d WHERE seed='%s'";
    }

    public String createUpdateImprintSql(){
        return "UPDATE seed_configs SET imprint='%s' WHERE seed='%s'";
    }

    static class SeedDBHelper extends SQLiteOpenHelper{

        public SeedDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE IF NOT EXISTS seed_configs(" +
                        "seed VARCHAR(32) PRIMARY KEY NOT NULL," +
                        "success INTEGER NOT NULL," +
                        "resolution VARCHAR(12) NOT NULL," +
                        "deviceModel VARCHAR(20) NOT NULL," +
                        "deviceName VARCHAR(20) NOT NULL," +
                        "deviceBoard VARCHAR(30) NOT NULL," +
                        "deviceBrand VARCHAR(30) NOT NULL," +
                        "deviceManuid VARCHAR(6) NOT NULL," +
                        "deviceManutime LONG NOT NULL," +
                        "deviceId VARCHAR(15) NOT NULL," +
                        "mac VARCHAR(17) NOT NULL," +
                        "cpu VARCHAR(40) NOT NULL," +
                        "osVersion VARCHAR(6) NOT NULL," +
                        "idmd5 VARCHAR(32) NOT NULL," +
                        "idTracking TEXT NOT NULL," +
                        "imprint TEXT)");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
