//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TaskExecutor {
    private static List<WeakReference<ScheduledFuture<?>>> futures = new ArrayList();
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static long c = 5L;
    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public TaskExecutor() {
    }

    public static void execute(Runnable runnable) {
        if(executorService.isShutdown()) {
            executorService = Executors.newSingleThreadExecutor();
        }

        executorService.execute(runnable);
    }

    public static void shutDown() {
        try {
            Iterator iterator = futures.iterator();

            while(iterator.hasNext()) {
                WeakReference reference = (WeakReference)iterator.next();
                ScheduledFuture scheduledFuture = (ScheduledFuture)reference.get();
                if(scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
            }

            futures.clear();
            if(!executorService.isShutdown()) {
                executorService.shutdown();
            }

            if(!scheduledExecutorService.isShutdown()) {
                scheduledExecutorService.shutdown();
            }

            executorService.awaitTermination(c, TimeUnit.SECONDS);
            scheduledExecutorService.awaitTermination(c, TimeUnit.SECONDS);
        } catch (Exception e) {
        }

    }

    public static synchronized void scheduleExecute(Runnable runnable) {
        if(scheduledExecutorService.isShutdown()) {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        }

        scheduledExecutorService.execute(runnable);
    }

    public static synchronized void scheduleDelayExecute(Runnable runnable, long delay) {
        if(scheduledExecutorService.isShutdown()) {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        }

        futures.add(new WeakReference(scheduledExecutorService.schedule(runnable, delay, TimeUnit.MILLISECONDS)));
    }

    public static synchronized void submit(Runnable runnable) {
        if(scheduledExecutorService.isShutdown()) {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        }

        Future future = scheduledExecutorService.submit(runnable);

        try {
            future.get(5L, TimeUnit.SECONDS);
        } catch (Exception e) {
        }
    }
}
