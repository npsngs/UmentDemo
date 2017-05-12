//
// Source code recreated from setRequestCallback .class cacheDir by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.umeng.analytics.aggregate.tool.AggTool;
import com.umeng.analytics.aggregate.result.OpResult;
import com.umeng.analytics.database.DBDataTool;
import com.umeng.analytics.d.SP_Util;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Locale;


public final class CacheTool {
    private static CacheTool instance = null;
    private static Context context;
    private static String packageName;
    private FileCache fileCache;
    private static final String e_str = "mobclick_agent_user_";
    private static final String f_str = "mobclick_agent_header_";
    private static final String g = "mobclick_agent_cached_";

    public CacheTool(Context context) {
        this.fileCache = new FileCache(context);
    }

    public static synchronized CacheTool getInstance(Context context) {
        CacheTool.context = context.getApplicationContext();
        packageName = context.getPackageName();
        if(instance == null) {
            instance = new CacheTool(context);
        }

        return instance;
    }

    public void saveAU(String var1, String var2) {
        if(!TextUtils.isEmpty(var1) && !TextUtils.isEmpty(var2)) {
            SharedPreferences var3 = this.getMobClick_AgentSP();
            Editor var4 = var3.edit();
            var4.putString("au_p", var1);
            var4.putString("au_u", var2);
            var4.apply();
        }

    }

    public String[] loadAU() {
        SharedPreferences var1 = this.getMobClick_AgentSP();
        String var2 = var1.getString("au_p", null);
        String var3 = var1.getString("au_u", null);
        return var2 != null && var3 != null?new String[]{var2, var3}:null;
    }

    public void removeAU() {
        SharedPreferences sp = this.getMobClick_AgentSP();
        sp.edit().remove("au_p").remove("au_u").apply();
    }

    public String loadAppKey() {
        SharedPreferences sp = SP_Util.getSp(context);
        return sp != null?sp.getString("appkey", null):null;
    }

    public void saveAppKey(String appKey) {
        SharedPreferences sp = SP_Util.getSp(context);
        if(sp != null) {
            sp.edit().putString("appkey", appKey).apply();
        }

    }

    public String loadChannel() {
        SharedPreferences var1 = SP_Util.getSp(context);
        return var1 != null?var1.getString("channel", null):null;
    }

    public void saveChannel(String channel) {
        SharedPreferences sp = SP_Util.getSp(context);
        if(sp != null) {
            sp.edit().putString("channel", channel).apply();
        }

    }

    public String loadST() {
        SharedPreferences var1 = SP_Util.getSp(context);
        return var1 != null?var1.getString("st", null):null;
    }

    public void saveST(String st) {
        SharedPreferences sp = SP_Util.getSp(context);
        if(sp != null) {
            sp.edit().putString("st", st).apply();
        }

    }

    public void saveVT(int vt) {
        SharedPreferences sp = SP_Util.getSp(context);
        if(sp != null) {
            sp.edit().putInt("vt", vt).apply();
        }

    }

    public int loadVT() {
        SharedPreferences sp = SP_Util.getSp(context);
        return sp != null?sp.getInt("vt", 0):0;
    }

    public void g() {
        context.deleteFile(this.getAgentName());
        context.deleteFile(this.m());
        DBDataTool.getInstance(context).a(true, false);
        AggTool.getInstance(context).b(new OpResult() {
            public void setResult(Object var1, boolean var2) {
                if(var1.equals("success")) {
                }

            }
        });
    }

    public void saveToCache(byte[] data) {
        this.fileCache.saveToCache(data);
    }

    public boolean hasCache() {
        return this.fileCache.hasCache();
    }

    public FileCache getFileCache() {
        return this.fileCache;
    }

    private SharedPreferences getMobClick_AgentSP() {
        return context.getSharedPreferences("mobclick_agent_user_" + packageName, 0);
    }

    private String getAgentName() {
        return "mobclick_agent_header_" + packageName;
    }

    private String m() {
        SharedPreferences sp = SP_Util.getSp(context);
        String var2;
        if(sp != null) {
            int versioncode = sp.getInt("versioncode", 0);
            int newVersionCode = Integer.parseInt(SystemUtil.getVersionCode(context));
            if(versioncode != 0 && newVersionCode != versioncode) {
                var2 = "mobclick_agent_cached_" + packageName + versioncode;
            } else {
                var2 = "mobclick_agent_cached_" + packageName + SystemUtil.getVersionCode(context);
            }
        } else {
            var2 = "mobclick_agent_cached_" + packageName + SystemUtil.getVersionCode(context);
        }

        return var2;
    }

    public interface b {
        void a(File file);

        boolean b(File file);

        void c(File file);
    }

    public static class FileCache {
        private File cacheDir;
        private FilenameFilter filenameFilter;

        public FileCache(Context context) {
            this(context, ".um");
        }

        public FileCache(Context context, String dirName) {
            this.filenameFilter = new FilenameFilter() {
                public boolean accept(File file, String fileName) {
                    return fileName.startsWith("um");
                }
            };
            this.cacheDir = new File(context.getFilesDir(), dirName);
            if(!this.cacheDir.exists() || !this.cacheDir.isDirectory()) {
                this.cacheDir.mkdir();
            }

        }

        public boolean hasCache() {
            File[] subFiles = this.cacheDir.listFiles();
            return subFiles != null && subFiles.length > 0;
        }

        public void a(CacheTool.b var1) {
            File[] listFiles = this.cacheDir.listFiles(this.filenameFilter);
            int i;
            if(listFiles != null && listFiles.length >= 10) {
                Arrays.sort(listFiles);
                final int len = listFiles.length - 10;
                TaskExecutor.scheduleExecute(new Runnable() {
                    public void run() {
                        if(len > 0) {
                            AggTool.getInstance(CacheTool.context).a((long)len, System.currentTimeMillis(), "__evp_file_of");
                        }

                    }
                });

                for(i = 0; i < len; ++i) {
                    listFiles[i].delete();
                }
            }

            if(listFiles != null && listFiles.length > 0) {
                var1.a(this.cacheDir);
                i = listFiles.length;

                for(int j = 0; j < i; ++j) {
                    boolean var12 = false;

                    try {
                        var12 = var1.b(listFiles[j]);
                    } catch (Throwable throwable) {
                        var12 = true;
                    } finally {
                        if(var12) {
                            listFiles[j].delete();
                        }

                    }
                }

                var1.c(this.cacheDir);
            }

        }

        public void saveToCache(byte[] data) {
            if(data != null && data.length != 0) {
                String fileName = String.format(Locale.US, "um_cache_%d_int.env", new Object[]{Long.valueOf(System.currentTimeMillis())});
                File file = new File(this.cacheDir, fileName);

                try {
                    EncodeUtil.writeToFile(file, data);
                } catch (Exception e) {
                }

            }
        }

        public void deleteCaches() {
            File[] listFiles = this.cacheDir.listFiles(this.filenameFilter);
            if(listFiles != null && listFiles.length > 0) {
                File[] files = listFiles;
                int length = listFiles.length;

                for(int i = 0; i < length; ++i) {
                    File file = files[i];
                    file.delete();
                }
            }

        }

        public int getCacheCount() {
            File[] listFiles = this.cacheDir.listFiles(this.filenameFilter);
            return listFiles != null && listFiles.length > 0?listFiles.length:0;
        }
    }
}
