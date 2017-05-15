//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.d;

import android.content.Context;
import com.umeng.tool.TaskExecutor;
import com.umeng.tool.SafeRunnable;

public final class ExecuteReport implements Reporter {
    private Reporter reporter;
    private Context context;
    private static ExecuteReport instance;

    private ExecuteReport(Context context) {
        this.context = context;
        this.reporter = new LogPreReport(this.context);
    }

    public synchronized LogPreReport getPreReport(Context context) {
        return (LogPreReport)this.reporter;
    }

    public static synchronized ExecuteReport getInstance(Context context) {
        if(instance == null && context != null) {
            instance = new ExecuteReport(context);
        }

        return instance;
    }

    public void getPreReport(Reporter var1) {
        this.reporter = var1;
    }

    public void report(final Object var1) {
        TaskExecutor.scheduleExecute(new SafeRunnable() {
            public void safeRun() {
                ExecuteReport.this.reporter.report(var1);
            }
        });
    }

    public void report() {
        TaskExecutor.scheduleExecute(new SafeRunnable() {
            public void safeRun() {
                ExecuteReport.this.reporter.report();
            }
        });
    }

    public void packData() {
        TaskExecutor.submit(new SafeRunnable() {
            public void safeRun() {
                ExecuteReport.this.reporter.packData();
            }
        });
    }
}
