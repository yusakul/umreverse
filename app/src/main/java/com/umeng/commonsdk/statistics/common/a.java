package com.umeng.commonsdk.statistics.common;

import java.io.*;
import java.util.concurrent.*;
import android.content.*;
import android.os.*;

public class a
{
    public static String a(final Context context) {
        try {
            final InnerClass_a b = b(context);
            if (b == null) {
                return null;
            }
            return b.b();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static InnerClass_a b(final Context context) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return null;
        }
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
        }
        catch (Exception ex) {
            throw ex;
        }
        final b b = new b();
        final Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, (ServiceConnection)b, 1)) {
            try {
                final c c = new c(b.a());
                return new InnerClass_a(c.a(), c.a(true));
            }
            catch (Exception ex2) {
                throw ex2;
            }
            finally {
                context.unbindService((ServiceConnection)b);
            }
        }
        throw new IOException("Google Play connection failed");
    }
    
    private static final class InnerClass_a
    {
        private final String a;
        private final boolean b;
        
        InnerClass_a(final String a, final boolean b) {
            this.a = a;
            this.b = b;
        }
        
        private String b() {
            return this.a;
        }
        
        public boolean a() {
            return this.b;
        }
    }
    
    private static final class b implements ServiceConnection
    {
        boolean a;
        private final LinkedBlockingQueue<IBinder> b;
        
        private b() {
            this.a = false;
            this.b = new LinkedBlockingQueue<IBinder>(1);
        }
        
        public void onServiceConnected(final ComponentName componentName, final IBinder e) {
            try {
                this.b.put(e);
            }
            catch (InterruptedException ex) {}
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
        }
        
        public IBinder a() throws InterruptedException {
            if (this.a) {
                throw new IllegalStateException();
            }
            this.a = true;
            return this.b.take();
        }
    }
    
    private static final class c implements IInterface
    {
        private IBinder a;
        
        public c(final IBinder a) {
            this.a = a;
        }
        
        public IBinder asBinder() {
            return this.a;
        }
        
        public String a() throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            String string;
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                string = obtain2.readString();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return string;
        }
        
        public boolean a(final boolean b) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            boolean b2;
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt((int)(b ? 1 : 0));
                this.a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                b2 = (0 != obtain2.readInt());
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return b2;
        }
    }
}
