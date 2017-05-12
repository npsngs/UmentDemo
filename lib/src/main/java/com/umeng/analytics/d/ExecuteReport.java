//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.d;

import android.content.Context;
import com.umeng.tool.TaskExecutor;
import com.umeng.tool.SafeRunnable;

public final class ExecuteReport implements g {
    private g a;
    private Context context;
    private static ExecuteReport instance;

    private ExecuteReport(Context context) {
        this.context = context;
        this.a = new LogPreReport(this.context);
    }

    public synchronized LogPreReport getPreReport(Context context) {
        return (LogPreReport)this.a;
    }

    public static synchronized ExecuteReport getInstance(Context context) {
        if(instance == null && context != null) {
            instance = new ExecuteReport(context);
        }

        return instance;
    }

    public void getPreReport(g var1) {
        this.a = var1;
    }

    public void a(final Object var1) {
        TaskExecutor.scheduleExecute(new SafeRunnable() {
            public void safeRun() {
                ExecuteReport.this.a.a(var1);
            }
        });
    }

    public void a() {
        TaskExecutor.scheduleExecute(new SafeRunnable() {
            public void safeRun() {
                ExecuteReport.this.a.a();
            }
        });
    }

    public void b() {
        TaskExecutor.submit(new SafeRunnable() {
            public void safeRun() {
                ExecuteReport.this.a.b();
            }
        });
    }
}
