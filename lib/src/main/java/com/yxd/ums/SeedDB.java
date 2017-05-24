package com.yxd.ums;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class SeedDB {
    private SeedDBTool seedDBTool;
    private static SeedDB instance;
    public static synchronized SeedDB getInstance(Context context) {
        if(instance == null) {
            instance = new SeedDB(context);
        }
        return instance;
    }

    private SeedDB(Context context) {
        seedDBTool = new SeedDBTool(context);
    }

    public ConfigBean readFromDB(String seed){
        ConfigBean configBean = null;
        try{
            SQLiteDatabase db = seedDBTool.getWritableDatabase();
            String sql = String.format(seedDBTool.createSelectSql(), seed);
            Cursor cursor = db.rawQuery(sql, null);
            if(cursor.moveToFirst()){
                configBean = new ConfigBean(cursor.getString(0));
                configBean.setSuccessfulRequests(cursor.getInt(1));
                configBean.setResolution(cursor.getString(2));
                configBean.setDeviceModel(cursor.getString(3));
                configBean.setDeviceName(cursor.getString(4));
                configBean.setDeviceBoard(cursor.getString(5));
                configBean.setDeviceBrand(cursor.getString(6));
                configBean.setDeviceManuid(cursor.getString(7));
                configBean.setDeviceManutime(cursor.getLong(8));
                configBean.setDeviceId(cursor.getString(9));
                configBean.setMac(cursor.getString(10));
                configBean.setCpu(cursor.getString(11));
                configBean.setOsVersion(cursor.getString(12));
                configBean.setIdmd5(cursor.getString(13));
                configBean.setSignature(cursor.getString(14));
                configBean.setIdTracking(cursor.getString(15));
                configBean.setImprint(cursor.getString(16));
            }
            cursor.close();
        }finally {
            seedDBTool.closeDB();
        }
        return configBean;
    }

    private SQLiteStatement insertStatement;
    public void writeToDB(ConfigBean configBean){
        try{
            SQLiteDatabase db = seedDBTool.getWritableDatabase();
            if(insertStatement == null){
                String sql = seedDBTool.createInsertSql();
                insertStatement = db.compileStatement(sql);
            }
            insertStatement.clearBindings();
            insertStatement.bindString(1, configBean.getSeed());
            insertStatement.bindLong(2, configBean.getSuccessfulRequests());
            insertStatement.bindString(3, configBean.getResolution());
            insertStatement.bindString(4, configBean.getDeviceModel());
            insertStatement.bindString(5, configBean.getDeviceName());
            insertStatement.bindString(6, configBean.getDeviceBoard());
            insertStatement.bindString(7, configBean.getDeviceBrand());
            insertStatement.bindString(8, configBean.getDeviceManuid());
            insertStatement.bindLong(9, configBean.getDeviceManutime());
            insertStatement.bindString(10, configBean.getDeviceId());
            insertStatement.bindString(11, configBean.getMac());
            insertStatement.bindString(12, configBean.getCpu());
            insertStatement.bindString(13, configBean.getOsVersion());
            insertStatement.bindString(14, configBean.getIdmd5());
            insertStatement.bindString(15, configBean.getSignature());
            insertStatement.bindString(16, configBean.getIdTracking());
            insertStatement.bindString(17, configBean.getImprint()==null?"":configBean.getImprint());
            insertStatement.executeInsert();
        }catch (Exception e){
        }finally {
            seedDBTool.closeDB();
        }
    }

    public void updateSuccessTimes(ConfigBean configBean){
        try {
            SQLiteDatabase db = seedDBTool.getWritableDatabase();
            String sql = String.format(seedDBTool.createUpdateSuccessSql(), configBean.getSuccessfulRequests(), configBean.getSeed());
            db.execSQL(sql);
        }catch (Exception e){
        }finally {
            seedDBTool.closeDB();
        }
    }

    public void updateImprint(ConfigBean configBean){
        try {
            SQLiteDatabase db = seedDBTool.getWritableDatabase();
            String sql = String.format(seedDBTool.createUpdateImprintSql(), configBean.getImprint(), configBean.getSeed());
            db.execSQL(sql);
        }catch (Exception e){
        }finally {
            seedDBTool.closeDB();
        }
    }
}
