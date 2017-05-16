//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import a.a.a.UMBeanUnpacker;
import a.a.a.UMBeanPacker;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.umeng.analytics.d.SP_Util;
import com.umeng.analytics.f.IdTracking;
import com.umeng.tool.EncodeUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UMengItCache {
    private final String b = "umeng_it.cache";
    private File file;
    private IdTracking idTracking = null;
    private long lastTimeInMills;
    private long interval;
    private Set<UMProperty> properties = new HashSet();
    private UMengItCache.a h = null;
    public static UMengItCache instance;

    UMengItCache(Context context) {
        this.file = new File(context.getFilesDir(), "umeng_it.cache");
        this.interval = 86400000L;
        this.h = new UMengItCache.a(context);
        this.h.b();
    }


    public static synchronized UMengItCache getInstance(Context context) {
        if(instance == null) {
            instance = new UMengItCache(context);
            instance.addProperty(new Imei(context));
            instance.addProperty(new Android_id(context));
            instance.addProperty(new UTDeviceId(context));
            instance.addProperty(new Idmd5(context));
            instance.addProperty(new Idfa(context));
            instance.addProperty(new Mac(context));
            instance.addProperty(new Serial());
            instance.addProperty(new Uuid(context));
            Uop uop = new Uop(context);
            if(!TextUtils.isEmpty(uop.getValue())) {
                instance.addProperty(uop);
            }

            Oldumid oldumid = new Oldumid(context);
            if(oldumid.g()) {
                instance.addProperty(oldumid);
                instance.addProperty(new Newumid(context));
                oldumid.i();
            }

            instance.init();
        }

        return instance;
    }


    public boolean addProperty(UMProperty property) {
        return this.h.a(property.getName())?this.properties.add(property):false;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void invalidate() {
        long currentTimeMillis = System.currentTimeMillis();
        if(currentTimeMillis - this.lastTimeInMills >= this.interval) {
            boolean var3 = false;
            Iterator iterator = this.properties.iterator();

            while(iterator.hasNext()) {
                UMProperty umProperty = (UMProperty)iterator.next();
                if(umProperty.isValidIDSnapshot() && umProperty.a()) {
                    var3 = true;
                    if(!umProperty.isValidIDSnapshot()) {
                        this.h.b(umProperty.getName());
                    }
                }
            }

            if(var3) {
                this.initIdTracking();
                this.h.a();
                this.cache();
            }

            this.lastTimeInMills = currentTimeMillis;
        }

    }

    public IdTracking getIdTracking() {
        return this.idTracking;
    }

    private void initIdTracking() {
        IdTracking idTracking = new IdTracking();
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        Iterator iterator = this.properties.iterator();

        while(iterator.hasNext()) {
            UMProperty umProperty = (UMProperty)iterator.next();
            if(umProperty.isValidIDSnapshot()) {
                if(umProperty.getIdSnapshot() != null) {
                    hashMap.put(umProperty.getName(), umProperty.getIdSnapshot());
                }

                if(umProperty.getJournals() != null && !umProperty.getJournals().isEmpty()) {
                    arrayList.addAll(umProperty.getJournals());
                }
            }
        }

        idTracking.setJournals(arrayList);
        idTracking.setSnapshots(hashMap);
        synchronized(this) {
            this.idTracking = idTracking;
        }
    }

    public String c() {
        return null;
    }

    public void d() {
        boolean var1 = false;
        Iterator iterator = this.properties.iterator();

        while(iterator.hasNext()) {
            UMProperty umProperty = (UMProperty)iterator.next();
            if(umProperty.isValidIDSnapshot() && umProperty.getJournals() != null && !umProperty.getJournals().isEmpty()) {
                umProperty.setJournals(null);
                var1 = true;
            }
        }

        if(var1) {
            this.idTracking.b(false);
            this.cache();
        }
    }

    public void init() {
        IdTracking idTracking = this.readFromFile();
        if(idTracking != null) {
            ArrayList arrayList = new ArrayList(this.properties.size());
            synchronized(this) {
                this.idTracking = idTracking;
                Iterator iterator = this.properties.iterator();

                while(true) {
                    UMProperty umProperty;
                    if(!iterator.hasNext()) {
                        iterator = arrayList.iterator();

                        while(iterator.hasNext()) {
                            umProperty = (UMProperty)iterator.next();
                            this.properties.remove(umProperty);
                        }
                        break;
                    }

                    umProperty = (UMProperty)iterator.next();
                    umProperty.addJournal(this.idTracking);
                    if(!umProperty.isValidIDSnapshot()) {
                        arrayList.add(umProperty);
                    }
                }
            }

            this.initIdTracking();
        }
    }

    public void cache() {
        if(this.idTracking != null) {
            this.writeToFile(this.idTracking);
        }

    }

    private IdTracking readFromFile() {
        if(!this.file.exists()) {
            return null;
        } else {
            FileInputStream fis = null;

            try {
                fis = new FileInputStream(this.file);
                byte[] data = EncodeUtil.readData(fis);
                IdTracking idTracking = new IdTracking();
                (new UMBeanUnpacker()).unpack(idTracking, data);
                return idTracking;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                EncodeUtil.close(fis);
            }
            return null;
        }
    }

    private void writeToFile(IdTracking idTracking) {
        if(idTracking != null) {
            try {
                byte[] data;
                synchronized(this) {
                    data = (new UMBeanPacker()).pack2Bytes(idTracking);
                }

                if(data != null) {
                    EncodeUtil.writeToFile(this.file, data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static class a {
        private Context context;
        private Set<String> hashSet = new HashSet();

        public a(Context context) {
            this.context = context;
        }

        public boolean a(String var1) {
            return !this.hashSet.contains(var1);
        }

        public void b(String var1) {
            this.hashSet.add(var1);
        }

        public void c(String var1) {
            this.hashSet.remove(var1);
        }

        public void a() {
            if(!this.hashSet.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                Iterator iterator = this.hashSet.iterator();

                while(iterator.hasNext()) {
                    String var3 = (String)iterator.next();
                    sb.append(var3);
                    sb.append(',');
                }

                sb.deleteCharAt(sb.length() - 1);
                SharedPreferences sp = SP_Util.getSp(this.context);
                sp.edit().putString("invld_id", sb.toString()).apply();
            }

        }

        public void b() {
            SharedPreferences sp = SP_Util.getSp(this.context);
            String var2 = sp.getString("invld_id", null);
            if(!TextUtils.isEmpty(var2)) {
                String[] var3 = var2.split(",");
                if(var3 != null) {
                    String[] var4 = var3;
                    int var5 = var3.length;

                    for(int var6 = 0; var6 < var5; ++var6) {
                        String var7 = var4[var6];
                        if(!TextUtils.isEmpty(var7)) {
                            this.hashSet.add(var7);
                        }
                    }
                }
            }

        }
    }
}
