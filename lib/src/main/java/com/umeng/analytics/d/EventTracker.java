//
// Source code recreated from a_str .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.d;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.umeng.analytics.aggregate.tool.StringCheck;
import com.umeng.analytics.aggregate.result.OpResult;
import com.umeng.tool.EncodeUtil;
import com.umeng.tool.TaskExecutor;
import com.umeng.tool.ULog;
import com.umeng.tool.SafeRunnable;
import com.umeng.analytics.aggregate.data.e;
import com.umeng.analytics.aggregate.tool.AggTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class EventTracker {
    private Context context;
    private ExecuteReport executeReport1 = null;
    private LogPreReport preReport = null;
    private JSONObject jsonObject = null;
    private ExecuteReport executeReport;

    public EventTracker(Context context) {
        try {
            if(context == null) {
                ULog.e("Context is null, can\'getPackageName track event");
            }
            this.context = context;
            this.executeReport = ExecuteReport.getInstance(context);
            this.executeReport1 = ExecuteReport.getInstance(context);
            this.preReport = this.executeReport1.getPreReport(context);
            if(this.jsonObject == null) {
                this.isValidEventID(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void reportEvent(String id, Map<String, Object> map, long du) {
        try {
            if(!this.isValidEventID(id)) {
                return;
            }

            if(!this.isValidEventLabel(map)) {
                return;
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("ts", System.currentTimeMillis());
            if(du > 0L) {
                jsonObject.put("du", du);
            }

            jsonObject.put("__t", 2049);
            Iterator iterator = map.entrySet().iterator();
            Entry entry;
            int i = 0;

            for(Object var9; i < 10 && iterator.hasNext(); ++i) {
                entry = (Entry)iterator.next();
                if(!"$st_fl".equals(entry.getKey()) && !"dplus_st".equals(entry.getKey()) && !"du".equals(entry.getKey()) && !"id".equals(entry.getKey()) && !"ts".equals(entry.getKey())) {
                    var9 = entry.getValue();
                    if(var9 instanceof String || var9 instanceof Integer || var9 instanceof Long) {
                        jsonObject.put((String)entry.getKey(), var9);
                    }
                }
            }

            jsonObject.put("__i", SessionHelper.getSessionID(this.context));
            jsonObject.put("_umpname", ActivityObserver.activityName);
            this.executeReport.a(jsonObject);
        } catch (Throwable t) {
        }

    }

    public void reportEvent(String id, String id_value, long du, int unuse) {
        try {
            if(!this.isValidEventID(id) || !this.isValidEventLabel(id_value)) {
                return;
            }

            JSONObject var6 = new JSONObject();
            var6.put("id", id);
            var6.put("ts", System.currentTimeMillis());
            if(du > 0L) {
                var6.put("du", du);
            }

            var6.put("__t", 2049);
            var6.put(id, id_value == null?"":id_value);
            var6.put("__i", SessionHelper.getSessionID(this.context));
            var6.put("_umpname", ActivityObserver.activityName);
            this.executeReport.a(var6);
        } catch (Throwable t) {
        }

    }


    public void reportEvent(String id, Map<String, Object> map) {
        try {
            if(!this.isValidEventID(id)) {
                return;
            }

            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("ts", System.currentTimeMillis());
            json.put("du", 0);
            json.put("__t", 2050);
            Iterator iterator = map.entrySet().iterator();
            Entry entry;
            int var6 = 0;

            for(Object var7 = null; var6 < 10 && iterator.hasNext(); ++var6) {
                entry = (Entry)iterator.next();
                if(!"$st_fl".equals(entry.getKey()) && !"dplus_st".equals(entry.getKey()) && !"du".equals(entry.getKey()) && !"id".equals(entry.getKey()) && !"ts".equals(entry.getKey())) {
                    var7 = entry.getValue();
                    if(var7 instanceof String || var7 instanceof Integer || var7 instanceof Long) {
                        json.put((String)entry.getKey(), var7);
                    }
                }
            }

            json.put("__i", SessionHelper.getSessionID(this.context));
            this.executeReport.a(json);
        } catch (Throwable t) {
        }

    }

    public boolean isValidEventID(List<String> cklist, int var2, String var3) {
        try {
            StringCheck var4 = StringCheck.getInstance();
            if(cklist == null) {
                ULog.e("cklist is null!");
            } else {
                if(cklist.size() <= 0) {
                    ULog.e("the KeyList is null!");
                    return false;
                }

                ArrayList tmplist = new ArrayList(cklist);
                if(!var4.b((String)tmplist.get(0))) {
                    ULog.e("Primary key Invalid!");
                    return false;
                }

                String var5;
                if(tmplist.size() > 8) {
                    var5 = (String)tmplist.get(0);
                    tmplist.clear();
                    tmplist.add(var5);
                    tmplist.add("__cc");
                    tmplist.add("illegal");
                } else if(!var4.a(tmplist)) {
                    var5 = (String)tmplist.get(0);
                    tmplist.clear();
                    tmplist.add(var5);
                    tmplist.add("__cc");
                    tmplist.add("illegal");
                } else if(!var4.b(tmplist)) {
                    var5 = (String)tmplist.get(0);
                    tmplist.clear();
                    tmplist.add(var5);
                    tmplist.add("__cc");
                    tmplist.add("illegal");
                } else {
                    for(int var6 = 0; var6 < tmplist.size(); ++var6) {
                        var5 = (String)tmplist.get(var6);
                        if(var5.length() > 16) {
                            tmplist.remove(var6);
                            tmplist.add(var6, var5.substring(0, 16));
                        }
                    }
                }

                if(!var4.a(var3)) {
                    ULog.e("label  Invalid!");
                    var3 = "__illegal";
                }

                final HashMap var9 = new HashMap();
                var9.put(tmplist, new e(tmplist, (long)var2, var3, System.currentTimeMillis()));
                TaskExecutor.scheduleExecute(new SafeRunnable() {
                    public void safeRun() {
                        AggTool.getInstance(EventTracker.this.context).a(new OpResult() {
                            public void setResult(Object var1, boolean var2) {
                                if(var1.equals("success")) {
                                }
                            }
                        }, var9);
                    }
                });
            }
        } catch (Exception e) {
        }

        return true;
    }

    private void isValidEventID(Context context) {
        try {
            SharedPreferences sp = SP_Util.getSp(context);
            String jsonStr = sp.getString("fs_lc_tl", null);
            if(!TextUtils.isEmpty(jsonStr)) {
                this.jsonObject = new JSONObject(jsonStr);
            }

            this.isValidEventID();
        } catch (Exception e) {
        }

    }

    private void isValidEventID() {
        try {
            if(!TextUtils.isEmpty(this.preReport.a)) {
                String[] var1 = this.preReport.a.split("!");
                JSONObject tmpJson = new JSONObject();
                if(this.jsonObject != null) {
                    for(int i = 0; i < var1.length; ++i) {
                        String var3 = EncodeUtil.getSubstring(var1[i], 128);
                        if(this.jsonObject.has(var3)) {
                            tmpJson.put(var3, this.jsonObject.get(var3));
                        }
                    }
                }

                this.jsonObject = new JSONObject();
                int i;
                if(var1.length >= 10) {
                    for(i = 0; i < 10; ++i) {
                        this.isValidEventID(var1[i], tmpJson);
                    }
                } else {
                    for(i = 0; i < var1.length; ++i) {
                        this.isValidEventID(var1[i], tmpJson);
                    }
                }

                this.isValidEventLabel(this.context);
                this.preReport.a = null;
            }
        } catch (Exception e) {
        }

    }

    private void isValidEventID(String var1, JSONObject jsonObject) throws JSONException {
        String var3 = EncodeUtil.getSubstring(var1, 128);
        if(jsonObject.has(var3)) {
            this.isValidEventID(var3, ((Boolean)jsonObject.get(var3)).booleanValue());
        } else {
            this.isValidEventID(var3, false);
        }

    }

    private void isValidEventID(String key, boolean var2) {
        try {
            if(!"$st_fl".equals(key) && !"dplus_st".equals(key) && !"du".equals(key) && !"id".equals(key) && !"ts".equals(key) && !this.jsonObject.has(key)) {
                this.jsonObject.put(key, var2);
            }
        } catch (Exception e) {
        }

    }

    private void isValidEventLabel(Context context) {
        try {
            if(this.jsonObject != null) {
                SharedPreferences sp = SP_Util.getSp(this.context);
                sp.edit().putString("fs_lc_tl", this.jsonObject.toString()).apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void isValidEventID(Context context, List<String> unused) {
        try {
            if(this.context == null) {
                if(context == null) {
                    return;
                }
                this.context = context;
            }

        } catch (Exception e) {
        }
    }

    private JSONObject isValidEventID(Map<String, Object> map) {
        String var2;
        String var3;
        String var4;
        Object obj;
        JSONObject jsonObject = new JSONObject();

        try {
            Iterator iterator = map.entrySet().iterator();
            Entry entry;

            while(iterator.hasNext()) {
                try {
                    entry = (Entry)iterator.next();
                    var2 = (String)entry.getKey();
                    if(var2 != null) {
                        var3 = EncodeUtil.getSubstring(var2, 128);
                        obj = entry.getValue();
                        if(obj != null) {
                            if(obj.getClass().isArray()) {
                                ArrayList var10;
                                int i;
                                if(obj instanceof int[]) {
                                    int[] var9 = (int[])((int[])obj);
                                    var10 = new ArrayList();

                                    for(i = 0; i < var9.length; ++i) {
                                        var10.add(Integer.valueOf(var9[i]));
                                    }

                                    jsonObject.put(var3, var10);
                                } else if(obj instanceof double[]) {
                                    double[] var14 = (double[])((double[])obj);
                                    var10 = new ArrayList();

                                    for(i = 0; i < var14.length; ++i) {
                                        var10.add(Double.valueOf(var14[i]));
                                    }

                                    jsonObject.put(var3, var10);
                                } else if(obj instanceof long[]) {
                                    long[] var15 = (long[])((long[])obj);
                                    var10 = new ArrayList();

                                    for(i = 0; i < var15.length; ++i) {
                                        var10.add(Long.valueOf(var15[i]));
                                    }

                                    jsonObject.put(var3, var10);
                                } else if(obj instanceof float[]) {
                                    float[] var16 = (float[])((float[])obj);
                                    var10 = new ArrayList();

                                    for(i = 0; i < var16.length; ++i) {
                                        var10.add(Float.valueOf(var16[i]));
                                    }

                                    jsonObject.put(var3, var10);
                                } else if(obj instanceof boolean[]) {
                                    boolean[] var17 = (boolean[])((boolean[])obj);
                                    var10 = new ArrayList();

                                    for(i = 0; i < var17.length; ++i) {
                                        var10.add(Boolean.valueOf(var17[i]));
                                    }

                                    jsonObject.put(var3, var10);
                                } else if(obj instanceof byte[]) {
                                    byte[] var18 = (byte[])((byte[])obj);
                                    var10 = new ArrayList();

                                    for(i = 0; i < var18.length; ++i) {
                                        var10.add(Byte.valueOf(var18[i]));
                                    }

                                    jsonObject.put(var3, var10);
                                } else if(obj instanceof short[]) {
                                    short[] var19 = (short[])((short[])obj);
                                    var10 = new ArrayList();

                                    for(i = 0; i < var19.length; ++i) {
                                        var10.add(Short.valueOf(var19[i]));
                                    }

                                    jsonObject.put(var3, var10);
                                } else if(obj instanceof char[]) {
                                    char[] var20 = (char[])((char[])obj);
                                    var10 = new ArrayList();

                                    for(i = 0; i < var20.length; ++i) {
                                        var10.add(Character.valueOf(var20[i]));
                                    }

                                    jsonObject.put(var3, var10);
                                } else {
                                    jsonObject.put(var3, new ArrayList(Arrays.asList((Object[])((Object[])obj))));
                                }
                            } else if(obj instanceof String) {
                                var4 = EncodeUtil.getSubstring(obj.toString(), 256);
                                jsonObject.put(var3, var4);
                            } else {
                                jsonObject.put(var3, obj);
                            }
                        }
                    }
                } catch (Exception e1) {
                    ULog.e(e1);
                }
            }
        } catch (Exception e) {
        }

        return jsonObject;
    }

    private boolean isValidEventID(String eventId) {
        try {
            if(eventId != null) {
                int length = eventId.trim().getBytes().length;
                if(length > 0 && length <= 128) {
                    return true;
                }
            }

            ULog.e("Event id is empty or too long in tracking Event");
        } catch (Exception e) {
        }

        return false;
    }

    private boolean isValidEventLabel(String eventLabel) {
        try {
            if(eventLabel == null) {
                return true;
            }

            if(eventLabel.trim().getBytes().length <= 256) {
                return true;
            }

            ULog.e("Event label or value is empty or too long in tracking Event");
        } catch (Exception e) {
        }

        return false;
    }

    private boolean isValidEventLabel(Map<String, Object> map) {
        try {
            if(map == null || map.isEmpty()) {
                ULog.e("map is null or empty in onPageStart");
                return false;
            }

            Iterator iterator = map.entrySet().iterator();

            while(iterator.hasNext()) {
                Entry entry = (Entry)iterator.next();
                if(!this.isValidEventID((String)entry.getKey())) {
                    return false;
                }

                if(entry.getValue() == null) {
                    return false;
                }

                if(entry.getValue() instanceof String && !this.isValidEventLabel(entry.getValue().toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
        }

        return true;
    }
}
