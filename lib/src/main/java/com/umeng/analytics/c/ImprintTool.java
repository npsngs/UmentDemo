//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.c;

import a.a.a.UMBeanUnpacker;
import a.a.a.UMBeanPacker;
import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.d.OptionSetter;
import com.umeng.analytics.e.OptionSetter_a;
import com.umeng.analytics.f.Imprint;
import com.umeng.analytics.f.ImprintValue;
import com.umeng.tool.EncodeUtil;
import com.umeng.tool.J;
import com.umeng.tool.StringTool;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class ImprintTool {
    private static final String a = ".imprint";
    private static final byte[] b = "pbl0".getBytes();
    private OptionSetter optionSetter;
    private Option option = new Option();
    private Imprint imprint = null;
    private static ImprintTool instance;
    private Context context;

    ImprintTool(Context context) {
        this.context = context;
    }

    public static synchronized ImprintTool getInstance(Context context) {
        if(instance == null) {
            instance = new ImprintTool(context);
            instance.readCache();
        }

        return instance;
    }

    public void setOptionSetter(OptionSetter optionSetter) {
        this.optionSetter = optionSetter;
    }

    public String checkSum(Imprint imprint) {
        StringBuilder sb = new StringBuilder();
        TreeMap treeMap = new TreeMap(imprint.getProperty());
        Iterator iterator = treeMap.entrySet().iterator();

        while(iterator.hasNext()) {
            Entry entry = (Entry)iterator.next();
            sb.append((String)entry.getKey());
            if(((ImprintValue)entry.getValue()).hasValue()) {
                sb.append(((ImprintValue)entry.getValue()).getValue());
            }

            sb.append(((ImprintValue)entry.getValue()).getTS());
            sb.append(((ImprintValue)entry.getValue()).getGUID());
        }

        sb.append(imprint.version);
        return EncodeUtil.getMD5_2(sb.toString()).toLowerCase(Locale.US);
    }

    private boolean c(Imprint imprint) {
        if(!imprint.getCheckSum().equals(this.checkSum(imprint))) {
            return false;
        } else {
            Iterator iterator = imprint.getProperty().values().iterator();

            while(iterator.hasNext()) {
                ImprintValue value = (ImprintValue)iterator.next();
                byte[] var4 = StringTool.hex2Byte(value.getGUID());
                byte[] var5 = this.a(value);

                for(int i = 0; i < 4; ++i) {
                    if(var4[i] != var5[i]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    public byte[] a(ImprintValue imprintValue) {
        ByteBuffer bf = ByteBuffer.allocate(8);
        bf.order(null);
        bf.putLong(imprintValue.getTS());
        byte[] var3 = bf.array();
        byte[] var4 = b;
        byte[] var5 = new byte[4];

        for(int i = 0; i < 4; ++i) {
            var5[i] = (byte)(var3[i] ^ var4[i]);
        }

        return var5;
    }

    public void setImprint(Imprint imprint) {
        if(imprint != null) {
            if(this.c(imprint)) {
                boolean isNeedUpdateOption = false;
                synchronized(this) {
                    Imprint imprint1 = this.imprint;
                    String old_checksum = imprint1 == null?null:imprint1.getCheckSum();
                    if(imprint1 == null) {
                        imprint1 = this.removeEmptyProperty(imprint);
                    } else {
                        imprint1 = this.append(imprint1, imprint);
                    }

                    this.imprint = imprint1;
                    String new_checksum = imprint1 == null?null:imprint1.getCheckSum();
                    if(!this.isEqualStr(old_checksum, new_checksum)) {
                        isNeedUpdateOption = true;
                    }
                }

                if(this.imprint != null && isNeedUpdateOption) {
                    this.option.init(this.imprint);
                    if(this.optionSetter != null) {
                        this.optionSetter.setOption(this.option);
                    }
                }

            }
        }
    }

    private boolean isEqualStr(String var1, String var2) {
        return var1 == null?var2 == null:var1.equals(var2);
    }

    private Imprint append(Imprint var1, Imprint var2) {
        if(var2 == null) {
            return var1;
        } else {
            Map var3 = var1.getProperty();
            Map var4 = var2.getProperty();
            Iterator var5 = var4.entrySet().iterator();

            while(var5.hasNext()) {
                Entry var6 = (Entry)var5.next();
                if(((ImprintValue)var6.getValue()).hasValue()) {
                    var3.put(var6.getKey(), var6.getValue());
                } else {
                    var3.remove(var6.getKey());
                }
            }

            var1.setVersion(var2.getVersion());
            var1.setCheckSum(this.checkSum(var1));
            return var1;
        }
    }

    private Imprint removeEmptyProperty(Imprint imprint) {
        Map var2 = imprint.getProperty();
        ArrayList var3 = new ArrayList(var2.size() / 2);
        Iterator var4 = var2.entrySet().iterator();

        while(var4.hasNext()) {
            Entry var5 = (Entry)var4.next();
            if(!((ImprintValue)var5.getValue()).hasValue()) {
                var3.add(var5.getKey());
            }
        }

        var4 = var3.iterator();

        while(var4.hasNext()) {
            String var6 = (String)var4.next();
            var2.remove(var6);
        }

        return imprint;
    }

    public synchronized Imprint getImprint() {
        return this.imprint;
    }

    public Option getOption() {
        return this.option;
    }

    public void readCache() {
        File file = new File(this.context.getFilesDir(), ".imprint");
        if(file.exists()) {
            FileInputStream fis = null;
            byte[] data = null;

            try {
                fis = this.context.openFileInput(".imprint");
                data = EncodeUtil.readData(fis);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                EncodeUtil.close(fis);
            }

            if(data != null) {
                try {
                    Imprint imprint = new Imprint();
                    (new UMBeanUnpacker()).unpack(imprint, data);
                    this.imprint = imprint;
                    this.option.init(imprint);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void writeToCache() {
        if(this.imprint != null) {
            try {
                byte[] data = (new UMBeanPacker()).pack2Bytes(this.imprint);
                EncodeUtil.writeToFile(new File(this.context.getFilesDir(), ".imprint"), data);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public boolean e() {
        File var1 = new File(this.context.getFilesDir(), ".imprint");
        return var1.delete();
    }

    public static class Option {
        private int defcon = -1;
        private int latent = -1;
        private int codex = -1;
        private int report_policy = -1;
        private int report_interval = -1;
        private String client_test = null;
        private int test_report_interval = -1;
        private String umid = null;
        private int integrated_test = -1;
        private int latent_hours = -1;
        private String country = null;
        private String domain_p = null;
        private String domain_s = null;
        private String initial_view_time = null;
        private String track_list = null;

        Option() {
        }

        Option(Imprint var1) {
            this.init(var1);
        }

        public void init(Imprint var1) {
            if(var1 != null) {
                this.defcon = this.a(var1, "defcon");
                this.latent = this.a(var1, "latent");
                this.codex = this.a(var1, "codex");
                this.report_policy = this.a(var1, "report_policy");
                this.report_interval = this.a(var1, "report_interval");
                this.client_test = this.getValue(var1, "client_test");
                this.test_report_interval = this.a(var1, "test_report_interval");
                this.umid = this.getValue(var1, "umid");
                this.integrated_test = this.a(var1, "integrated_test");
                this.latent_hours = this.a(var1, "latent_hours");
                this.country = this.getValue(var1, "country");
                this.domain_p = this.getValue(var1, "getDomainP");
                this.domain_s = this.getValue(var1, "getDomain_s");
                this.initial_view_time = this.getValue(var1, "initial_view_time");
                this.track_list = this.getValue(var1, "track_list");
            }
        }

        public String a(String var1) {
            return this.initial_view_time != null?this.initial_view_time :var1;
        }

        public String getTrack_list(String def) {
            return this.track_list != null?this.track_list :def;
        }

        public String getDomain_s(String def) {
            return this.domain_s != null?this.domain_s :def;
        }

        public String getDomainP(String def) {
            return this.domain_p != null?this.domain_p :def;
        }

        public String getCountry(String defaultCountry) {
            return this.country != null?this.country :defaultCountry;
        }

        public int getDefCon(int def) {
            return this.defcon == -1?def:(this.defcon <= 3 && this.defcon >= 0?this.defcon :def);
        }

        public int getLatent(int def) {
            return this.latent == -1?def:(this.latent >= 0 && this.latent <= 1800?this.latent * 1000:def);
        }

        public int getCodex(int def) {
            return this.codex != 0 && this.codex != 1 && this.codex != -1?def:this.codex;
        }

        public int[] getReportPolicy(int def_policy, int def_interval) {
            if(this.report_policy != -1 && J.a(this.report_policy)) {
                if(this.report_interval == -1 || this.report_interval < 90 || this.report_interval > 86400) {
                    this.report_interval = 90;
                }

                return new int[]{this.report_policy, this.report_interval * 1000};
            } else {
                return new int[]{def_policy, def_interval};
            }
        }

        public String getClient_test(String def) {
            return this.client_test != null && OptionSetter_a.a(this.client_test)?this.client_test :def;
        }

        public int getTest_report_interval(int def) {
            return this.test_report_interval != -1 && this.test_report_interval >= 90 && this.test_report_interval <= 86400?this.test_report_interval * 1000:def;
        }

        public boolean hasTest_report_interval() {
            return this.test_report_interval != -1;
        }

        public String getUmid(String defaultValue) {
            return this.umid;
        }

        public boolean isIntegrated_test() {
            return this.integrated_test == 1;
        }

        public long a(long var1) {
            return this.latent_hours == -1?var1:(this.latent_hours < 48?var1:3600000L * (long)this.latent_hours);
        }

        private int a(Imprint var1, String var2) {
            try {
                if(var1 == null || !var1.hasProperty()) {
                    return -1;
                }

                ImprintValue var3 = (ImprintValue)var1.getProperty().get(var2);
                if(var3 == null || TextUtils.isEmpty(var3.getValue())) {
                    return -1;
                }

                try {
                    return Integer.parseInt(var3.getValue().trim());
                } catch (Exception var5) {
                    ;
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            return -1;
        }

        private String getValue(Imprint imprint, String key) {
            String value = null;

            try {
                if(imprint == null || !imprint.hasProperty()) {
                    return null;
                }

                ImprintValue imprintValue = imprint.getProperty().get(key);
                if(imprintValue == null || TextUtils.isEmpty(imprintValue.getValue())) {
                    return null;
                }

                value = imprintValue.getValue();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return value;
        }
    }
}
