//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class StringUtil {
    StringUtil() {
    }

    public static boolean hasTable(String tableName, SQLiteDatabase database) {
        boolean hasTable = false;
        if(tableName == null) {
            return false;
        } else {
            Cursor cursor = null;

            try {
                String sql = "select count(*) as loadAppKey from sqlite_master where type =\'table\' and name =\'" + tableName.trim() + "\' ";
                cursor = database.rawQuery(sql, null);
                if(cursor.moveToNext()) {
                    int var5 = cursor.getInt(0);
                    if(var5 > 0) {
                        hasTable = true;
                    }
                }
            } catch (Exception e) {
            } finally {
                if(cursor != null) {
                    cursor.close();
                }

            }

            return hasTable;
        }
    }

    public static String a(Context context) {
        return "/data/data/" + context.getPackageName() + "/databases/.ua/";
    }

    public static String listToJoinStr(List<String> list) {
        return TextUtils.join("!", list);
    }

    public static List<String> joinStrToList(String joinStr) {
        String[] split = joinStr.split("!");
        List list = Arrays.asList(split);
        return new ArrayList(list);
    }

    public static List<String> removeDuplication(List<String> list) {
        ArrayList arrayList = new ArrayList();

        try {
            Iterator iterator = list.iterator();

            while(iterator.hasNext()) {
                String s = (String)iterator.next();
                if(Collections.frequency(arrayList, s) < 1) {
                    arrayList.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
