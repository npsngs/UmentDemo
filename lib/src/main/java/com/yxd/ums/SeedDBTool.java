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
        sqLiteOpenHelper = new SeedDBHelper(context, "sbs.db", null, 1);
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
                "deviceId, mac, cpu, osVersion, idmd5, signature, idTracking, imprint) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public String createInsertOsSql(){
        return "INSERT INTO iod_configs(seed, success, resolution, deviceModel, " +
                "deviceId, mac, osVersion, signature, carrier, " +
                "mccmnc, access_subtype, idfa, idfv, utdid, jailbroken, idTracking, imprint) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public String createSelectSql(){
        return "SELECT * FROM seed_configs WHERE seed='%s'";
    }

    public String createSelectOsSql(){
        return "SELECT * FROM iod_configs WHERE seed='%s'";
    }



    public String createUpdateSuccessSql(){
        return "UPDATE seed_configs SET success=%d WHERE seed='%s'";
    }

    public String createUpdateOsSuccessSql(){
        return "UPDATE iod_configs SET success=%d WHERE seed='%s'";
    }




    public String createUpdateImprintSql(){
        return "UPDATE seed_configs SET imprint='%s' WHERE seed='%s'";
    }

    public String createUpdateOsImprintSql(){
        return "UPDATE iod_configs SET imprint='%s' WHERE seed='%s'";
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
                        "resolution VARCHAR(1) NOT NULL," +
                        "deviceModel VARCHAR(20) NOT NULL," +
                        "deviceName VARCHAR(20) NOT NULL," +
                        "deviceBoard VARCHAR(30) NOT NULL," +
                        "deviceBrand VARCHAR(30) NOT NULL," +
                        "deviceManuid VARCHAR(6) NOT NULL," +
                        "deviceManutime LONG NOT NULL," +
                        "deviceId VARCHAR(15) NOT NULL," +
                        "mac VARCHAR(17) NOT NULL," +
                        "cpu VARCHAR(1) NOT NULL," +
                        "osVersion VARCHAR(6) NOT NULL," +
                        "idmd5 VARCHAR(32) NOT NULL," +
                        "signature VARCHAR(64) NOT NULL," +
                        "idTracking TEXT NOT NULL," +
                        "imprint TEXT)");

                db.execSQL("CREATE TABLE IF NOT EXISTS iod_configs(" +
                        "seed VARCHAR(32) PRIMARY KEY NOT NULL," +
                        "success INTEGER NOT NULL," +
                        "resolution VARCHAR(1) NOT NULL," +
                        "deviceModel VARCHAR(20) NOT NULL," +
                        "deviceId VARCHAR(40) NOT NULL," +
                        "mac VARCHAR(17) NOT NULL," +
                        "osVersion VARCHAR(6) NOT NULL," +
                        "signature VARCHAR(64) NOT NULL," +
                        "carrier VARCHAR(20) NOT NULL," +
                        "mccmnc VARCHAR(5) NOT NULL," +
                        "access_subtype VARCHAR(8) NOT NULL," +
                        "idfa VARCHAR(36) NOT NULL," +
                        "idfv VARCHAR(36) NOT NULL," +
                        "utdid VARCHAR(32) NOT NULL," +
                        "jailbroken VARCHAR(3) NOT NULL," +
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
