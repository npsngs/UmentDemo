//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.tool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class GooglePlayTool {
    public GooglePlayTool() {
    }

    public static String a(Context context) {
        try {
            a_inner var1 = b(context);
            return var1 == null?null:var1.b();
        } catch (Exception e) {
            return null;
        }
    }

    private static a_inner b(Context context) throws Exception {
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getPackageInfo("com.android.vending", 0);
        } catch (Exception e) {
            throw e;
        }

        UServiceConnection var13 = new UServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if(context.bindService(intent, var13, Context.BIND_AUTO_CREATE)) {
            a_inner var5;
            try {
                GooglePlayTool.c var3 = new GooglePlayTool.c(var13.a());
                a_inner var4 = new a_inner(var3.a(), var3.a(true));
                var5 = var4;
            } catch (Exception var10) {
                throw var10;
            } finally {
                context.unbindService(var13);
            }

            return var5;
        } else {
            throw new IOException("Google Play connection failed");
        }
    }

    private static final class c implements IInterface {
        private IBinder a;

        public c(IBinder var1) {
            this.a = var1;
        }

        public IBinder asBinder() {
            return this.a;
        }

        public String a() throws RemoteException {
            Parcel var1 = Parcel.obtain();
            Parcel var2 = Parcel.obtain();

            String var3;
            try {
                var1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.a.transact(1, var1, var2, 0);
                var2.readException();
                var3 = var2.readString();
            } finally {
                var2.recycle();
                var1.recycle();
            }

            return var3;
        }

        public boolean a(boolean var1) throws RemoteException {
            Parcel var2 = Parcel.obtain();
            Parcel var3 = Parcel.obtain();

            boolean var4;
            try {
                var2.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                var2.writeInt(var1?1:0);
                this.a.transact(2, var2, var3, 0);
                var3.readException();
                var4 = 0 != var3.readInt();
            } finally {
                var3.recycle();
                var2.recycle();
            }

            return var4;
        }
    }

    private static final class UServiceConnection implements ServiceConnection {
        boolean a;
        private final LinkedBlockingQueue<IBinder> binders;

        private UServiceConnection() {
            this.a = false;
            this.binders = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.binders.put(iBinder);
            } catch (InterruptedException e) {
            }

        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder a() throws InterruptedException {
            if(this.a) {
                throw new IllegalStateException();
            } else {
                this.a = true;
                return this.binders.take();
            }
        }
    }

    private static final class a_inner {
        private final String a;
        private final boolean b;

        a_inner(String var1, boolean var2) {
            this.a = var1;
            this.b = var2;
        }

        private String b() {
            return this.a;
        }

        public boolean a() {
            return this.b;
        }
    }
}
