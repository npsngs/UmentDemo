//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.e;

import android.content.Context;

import com.umeng.analytics.aggregate.result.OpResult;
import com.umeng.analytics.aggregate.tool.AggTool;
import com.umeng.analytics.database.DBDataTool;
import com.umeng.analytics.c.ImprintTool;
import com.umeng.analytics.d.OptionSetter;
import com.yxd.sum.track.SessionHelper;
import org.json.JSONObject;

public class OptionSetter_b implements OptionSetter {
    private static final int a_int = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final long e = 14400000L;
    private static final long f_long = 28800000L;
    private static final long g = 86400000L;
    private int defCon = 0;
    private final long i = 60000L;
    private static OptionSetter_b instance = null;

    public static synchronized OptionSetter_b a(Context context) {
        if(instance == null) {
            instance = new OptionSetter_b();
            int defCon = ImprintTool.getInstance(context).getOption().getDefCon(0);
            instance.setDefCon(defCon);
        }

        return instance;
    }

    private OptionSetter_b() {
    }

    public void a(JSONObject jsonObject, Context context) {
        if(this.defCon == 1) {
            jsonObject.remove("error");
            jsonObject.remove("ekv");
            jsonObject.remove("gkv");
            jsonObject.remove("cc");
            DBDataTool.getInstance(context).deleteData(false, true);
            AggTool.getInstance(context).cleatData(new OpResult());
        } else if(this.defCon == 2) {
            jsonObject.remove("sessions");

            try {
                jsonObject.put("sessions", this.a());
            } catch (Exception var4) {
                ;
            }

            jsonObject.remove("error");
            jsonObject.remove("ekv");
            jsonObject.remove("gkv");
            jsonObject.remove("cc");
            DBDataTool.getInstance(context).deleteData(false, true);
            AggTool.getInstance(context).cleatData(new OpResult());
        } else if(this.defCon == 3) {
            jsonObject.remove("sessions");
            jsonObject.remove("error");
            jsonObject.remove("ekv");
            jsonObject.remove("gkv");
            jsonObject.remove("cc");
            DBDataTool.getInstance(context).deleteData(false, true);
            AggTool.getInstance(context).cleatData(new OpResult());
        }

    }

    public JSONObject a() {
        JSONObject var1 = new JSONObject();

        try {
            long var2 = System.currentTimeMillis();
            var1.put("id", SessionHelper.getSessionID());
            var1.put("start_time", var2);
            var1.put("end_time", var2 + 60000L);
            var1.put("duration", 60000L);
        } catch (Throwable var4) {
            ;
        }

        return var1;
    }

    public long b() {
        switch(this.defCon) {
            case 0:
            default:
                return 0L;
            case 1:
                return 14400000L;
            case 2:
                return 28800000L;
            case 3:
                return 86400000L;
        }
    }

    public long c() {
        return this.defCon == 0?0L:300000L;
    }

    public void setDefCon(int defCon) {
        if(defCon >= 0 && defCon <= 3) {
            this.defCon = defCon;
        }

    }

    public boolean isDiscardOnFail() {
        return this.defCon != 0;
    }

    public void setOption(ImprintTool.Option option) {
        this.setDefCon(option.getDefCon(0));
    }
}
