//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.Database;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class DBConst {
    public static final String a = "/data/data/";
    public static final String b = "/databases/cc/";
    public static final String c = "cc.db";
    public static final int d = 1;
    public static final String e = "Id";
    public static final String f = "INTEGER";

    public DBConst() {
    }

    public static String getDatabaseDir(Context context) {
        return "/data/data/" + context.getPackageName() + "/databases/cc/";
    }

    public static String joinToStr(List<String> list) {
        return TextUtils.join("!", list);
    }

    public static JSONArray joinStrToJSONArray(String str) {
        String[] strings = str.split("!");
        JSONArray array = new JSONArray();

        for(int i = 0; i < strings.length; ++i) {
            array.put(strings[i]);
        }

        return array;
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

    public static class c {
        public static final String a = "system";

        public c() {
        }

        public static class b {
            public static final String a = "TEXT";
            public static final String b = "INTEGER";
            public static final String c = "INTEGER";
            public static final String d = "TEXT";

            public b() {
            }
        }

        public static class a {
            public static final String a = "key";
            public static final String b = "timeStamp";
            public static final String c = "count";
            public static final String d = "label";

            public a() {
            }
        }
    }

    public static class b {
        public static final String a = "limitedck";

        public b() {
        }

        public static class b_inner {
            public static final String a = "TEXT";

            public b_inner() {
            }
        }

        public static class a {
            public static final String a = "ck";

            public a() {
            }
        }
    }

    public static class a {
        public static final String a = "aggregated";
        public static final String b = "aggregated_cache";

        public a() {
        }

        public static class b {
            public static final String a = "TEXT";
            public static final String b = "TEXT";
            public static final String c = "INTEGER";
            public static final String d = "INTEGER";
            public static final String e = "TEXT";
            public static final String f = "TEXT";

            public b() {
            }
        }

        public static class a_inner {
            public static final String a = "key";
            public static final String b = "totalTimestamp";
            public static final String c = "value";
            public static final String d = "count";
            public static final String e = "label";
            public static final String f = "timeWindowNum";

            public a_inner() {
            }
        }
    }
}
