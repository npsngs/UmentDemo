package com.yxd.ums;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.yxd.ums.device.Device;
import com.yxd.ums.ios.IOSBean;

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
                configBean.setResolution(Device.mapResolution(cursor.getString(2)));
                configBean.setDeviceModel(cursor.getString(3));
                configBean.setDeviceName(cursor.getString(4));
                configBean.setDeviceBoard(cursor.getString(5));
                configBean.setDeviceBrand(cursor.getString(6));
                configBean.setDeviceManuid(cursor.getString(7));
                configBean.setDeviceManutime(cursor.getLong(8));
                configBean.setDeviceId(cursor.getString(9));
                configBean.setMac(cursor.getString(10));
                configBean.setCpu(Device.mapCpu(cursor.getString(11)));
                configBean.setOsVersion(cursor.getString(12));
                configBean.setIdmd5(cursor.getString(13));
                configBean.setSignature(cursor.getString(14));
                configBean.setIdTracking(cursor.getString(15));
                configBean.setImprint(cursor.getString(16));
            }
            cursor.close();
        }finally {
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
            insertStatement.bindString(3, Device.unmapResolution(configBean.getResolution()));
            insertStatement.bindString(4, configBean.getDeviceModel());
            insertStatement.bindString(5, configBean.getDeviceName());
            insertStatement.bindString(6, configBean.getDeviceBoard());
            insertStatement.bindString(7, configBean.getDeviceBrand());
            insertStatement.bindString(8, configBean.getDeviceManuid());
            insertStatement.bindLong(9, configBean.getDeviceManutime());
            insertStatement.bindString(10, configBean.getDeviceId());
            insertStatement.bindString(11, configBean.getMac());
            insertStatement.bindString(12, Device.unmapCpu(configBean.getCpu()));
            insertStatement.bindString(13, configBean.getOsVersion());
            insertStatement.bindString(14, configBean.getIdmd5());
            insertStatement.bindString(15, configBean.getSignature());
            insertStatement.bindString(16, configBean.getIdTracking());
            insertStatement.bindString(17, configBean.getImprint()==null?"":configBean.getImprint());
            insertStatement.executeInsert();
        }catch (Exception e){
        }finally {
        }
    }

    public void updateSuccessTimes(ConfigBean configBean){
        try {
            SQLiteDatabase db = seedDBTool.getWritableDatabase();
            String sql = String.format(seedDBTool.createUpdateSuccessSql(), configBean.getSuccessfulRequests(), configBean.getSeed());
            db.execSQL(sql);
        }catch (Exception e){
        }finally {
        }
    }

    public void updateImprint(ConfigBean configBean){
        try {
            SQLiteDatabase db = seedDBTool.getWritableDatabase();
            String sql = String.format(seedDBTool.createUpdateImprintSql(), configBean.getImprint(), configBean.getSeed());
            db.execSQL(sql);
        }catch (Exception e){
        }finally {
        }
    }


    /************************************
     * ios module
     ************************************/
    public IOSBean readIosFromDB(String seed){
        IOSBean iosBean = null;
        try{
            SQLiteDatabase db = seedDBTool.getWritableDatabase();
            String sql = String.format(seedDBTool.createSelectOsSql(), seed);
            Cursor cursor = db.rawQuery(sql, null);
            if(cursor.moveToFirst()){
                iosBean = new IOSBean(cursor.getString(0));
                iosBean.setSuccessfulRequests(cursor.getInt(1));
                iosBean.setResolution(cursor.getString(2));
                iosBean.setDeviceModel(cursor.getString(3));
                iosBean.setDeviceId(cursor.getString(4));
                iosBean.setMac(cursor.getString(5));
                iosBean.setOsVersion(cursor.getString(6));
                iosBean.setSignature(cursor.getString(7));
                iosBean.setCarrier(cursor.getString(8));
                iosBean.setMccmnc(cursor.getString(9));
                iosBean.setAccess_subtype(cursor.getString(10));
                iosBean.setIdfa(cursor.getString(11));
                iosBean.setIdfv(cursor.getString(12));
                iosBean.setUtdid(cursor.getString(13));
                iosBean.setJailbroken(cursor.getString(14));
                iosBean.setIdTracking(cursor.getString(15));
                iosBean.setImprint(cursor.getString(16));
            }
            cursor.close();
        }finally {
        }
        return iosBean;
    }

    private SQLiteStatement insertOsStatement;
    public void writeIosToDB(IOSBean iosBean){
        try{
            SQLiteDatabase db = seedDBTool.getWritableDatabase();
            if(insertOsStatement == null){
                String sql = seedDBTool.createInsertOsSql();
                insertOsStatement = db.compileStatement(sql);
            }
            insertOsStatement.clearBindings();
            insertOsStatement.bindString(1, iosBean.getSeed());
            insertOsStatement.bindLong(2, iosBean.getSuccessfulRequests());
            insertOsStatement.bindString(3, iosBean.getResolution());
            insertOsStatement.bindString(4, iosBean.getDeviceModel());
            insertOsStatement.bindString(5, iosBean.getDeviceId());
            insertOsStatement.bindString(6, iosBean.getMac());
            insertOsStatement.bindString(7, iosBean.getOsVersion());
            insertOsStatement.bindString(8, iosBean.getSignature());
            insertOsStatement.bindString(9, iosBean.getCarrier());
            insertOsStatement.bindString(10, iosBean.getMccmnc());
            insertOsStatement.bindString(11, iosBean.getAccess_subtype());
            insertOsStatement.bindString(12, iosBean.getIdfa());
            insertOsStatement.bindString(13, iosBean.getIdfv());
            insertOsStatement.bindString(14, iosBean.getUtdid());
            insertOsStatement.bindString(15, iosBean.getJailbroken());
            insertOsStatement.bindString(16, iosBean.getIdTracking());
            insertOsStatement.bindString(17, iosBean.getImprint()==null?"":iosBean.getImprint());
            insertOsStatement.executeInsert();
        }catch (Exception e){
        }finally {
        }
    }

    public void updateOsImprint(IOSBean iosBean){
        try {
            SQLiteDatabase db = seedDBTool.getWritableDatabase();
            String sql = String.format(seedDBTool.createUpdateOsImprintSql(), iosBean.getImprint(), iosBean.getSeed());
            db.execSQL(sql);
        }catch (Exception e){
        }finally {
        }
    }

    public void updateOsSuccessTimes(IOSBean iosBean){
        try {
            SQLiteDatabase db = seedDBTool.getWritableDatabase();
            String sql = String.format(seedDBTool.createUpdateOsSuccessSql(), iosBean.getSuccessfulRequests(), iosBean.getSeed());
            db.execSQL(sql);
        }catch (Exception e){
        }finally {
        }
    }
}
