//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.report;

import android.content.Context;

import com.umeng.tool.TaskExecutor;
import com.umeng.tool.SafeRunnable;

public final class AsyncReporter implements Reporter {
    private Reporter reporter;
    private Context context;
    private static AsyncReporter instance;

    private AsyncReporter(Context context) {
        this.context = context;
        this.reporter = new LogReporter(this.context);
    }

    public synchronized LogReporter getPreReport(Context context) {
        return (LogReporter)this.reporter;
    }

    public static synchronized AsyncReporter getInstance(Context context) {
        if(instance == null && context != null) {
            instance = new AsyncReporter(context);
        }

        return instance;
    }

    public void getReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    public void report(final Object o) {
        TaskExecutor.scheduleExecute(new SafeRunnable() {
            public void safeRun() {
                AsyncReporter.this.reporter.report(o);
            }
        });
    }

    public void report() {
        TaskExecutor.scheduleExecute(new SafeRunnable() {
            public void safeRun() {
                AsyncReporter.this.reporter.report();
            }
        });
    }

    public void packData() {
        TaskExecutor.submit(new SafeRunnable() {
            public void safeRun() {
                AsyncReporter.this.reporter.packData();
            }
        });
    }
}
