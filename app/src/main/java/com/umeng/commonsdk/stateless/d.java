package com.umeng.commonsdk.stateless;

import android.os.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.internal.crash.*;
import android.text.*;
import android.util.*;
import java.io.*;
import android.content.*;
import android.net.*;

public class d
{
    private static Context b;
    private static HandlerThread c;
    private static Handler d;
    private static Object e;
    public static final int a = 273;
    private static final int f = 512;
    private static IntentFilter g;
    private static boolean h;
    private static BroadcastReceiver i;
    
    public d(final Context context) {
        synchronized (com.umeng.commonsdk.stateless.d.e) {
            try {
                if (context != null) {
                    com.umeng.commonsdk.stateless.d.b = context.getApplicationContext();
                    if (com.umeng.commonsdk.stateless.d.b != null && com.umeng.commonsdk.stateless.d.c == null) {
                        (com.umeng.commonsdk.stateless.d.c = new HandlerThread("SL-NetWorkSender")).start();
                        if (com.umeng.commonsdk.stateless.d.d == null) {
                            com.umeng.commonsdk.stateless.d.d = new Handler(com.umeng.commonsdk.stateless.d.c.getLooper()) {
                                public void handleMessage(final Message message) {
                                    switch (message.what) {
                                        case 273: {
                                            e();
                                            break;
                                        }
                                        case 512: {
                                            f();
                                            break;
                                        }
                                    }
                                }
                            };
                        }
                        if (DeviceConfig.checkPermission(com.umeng.commonsdk.stateless.d.b, "android.permission.ACCESS_NETWORK_STATE")) {
                            ULog.i("walle", "[stateless] begin register receiver");
                            if (com.umeng.commonsdk.stateless.d.g == null) {
                                (com.umeng.commonsdk.stateless.d.g = new IntentFilter()).addAction("android.net.conn.CONNECTIVITY_CHANGE");
                                if (com.umeng.commonsdk.stateless.d.i != null) {
                                    ULog.i("walle", "[stateless] register receiver ok");
                                    com.umeng.commonsdk.stateless.d.b.registerReceiver(com.umeng.commonsdk.stateless.d.i, com.umeng.commonsdk.stateless.d.g);
                                }
                            }
                        }
                    }
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(context, t);
            }
        }
    }
    
    public static void a(final int what) {
        if (com.umeng.commonsdk.stateless.d.h && com.umeng.commonsdk.stateless.d.d != null) {
            final Message obtainMessage = com.umeng.commonsdk.stateless.d.d.obtainMessage();
            obtainMessage.what = what;
            com.umeng.commonsdk.stateless.d.d.sendMessage(obtainMessage);
        }
    }
    
    public static void b(final int what) {
        try {
            if (com.umeng.commonsdk.stateless.d.h && com.umeng.commonsdk.stateless.d.d != null && !com.umeng.commonsdk.stateless.d.d.hasMessages(what)) {
                ULog.i("walle", "[stateless] sendMsgOnce !!!!");
                final Message obtainMessage = com.umeng.commonsdk.stateless.d.d.obtainMessage();
                obtainMessage.what = what;
                com.umeng.commonsdk.stateless.d.d.sendMessage(obtainMessage);
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(com.umeng.commonsdk.stateless.d.b, t);
        }
    }
    
    private static void e() {
        if (!com.umeng.commonsdk.stateless.d.h || com.umeng.commonsdk.stateless.d.b == null) {
            return;
        }
        try {
            final File a = com.umeng.commonsdk.stateless.f.a(com.umeng.commonsdk.stateless.d.b);
            if (a != null && a.getParentFile() != null && !TextUtils.isEmpty((CharSequence)a.getParentFile().getName())) {
                final e e = new e(com.umeng.commonsdk.stateless.d.b);
                if (e != null) {
                    final String str = new String(Base64.decode(a.getParentFile().getName(), 0));
                    ULog.i("walle", "[stateless] handleProcessNext, pathUrl is " + str);
                    byte[] a2 = null;
                    try {
                        a2 = com.umeng.commonsdk.stateless.f.a(a.getAbsolutePath());
                    }
                    catch (Exception ex) {}
                    if (!e.a(a2, str)) {
                        ULog.i("walle", "[stateless] Send envelope file failed, abandon and wait next trigger!");
                        return;
                    }
                    ULog.i("walle", "[stateless] Send envelope file success, delete it.");
                    final File file = new File(a.getAbsolutePath());
                    if (!file.delete()) {
                        ULog.i("walle", "[stateless] Failed to delete already processed file. We try again after delete failed.");
                        file.delete();
                    }
                }
                b(273);
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(com.umeng.commonsdk.stateless.d.b, t);
        }
    }
    
    public static void a() {
        b(512);
    }
    
    private static void f() {
        if (com.umeng.commonsdk.stateless.d.g != null) {
            if (com.umeng.commonsdk.stateless.d.i != null) {
                if (com.umeng.commonsdk.stateless.d.b != null) {
                    com.umeng.commonsdk.stateless.d.b.unregisterReceiver(com.umeng.commonsdk.stateless.d.i);
                }
                com.umeng.commonsdk.stateless.d.i = null;
            }
            com.umeng.commonsdk.stateless.d.g = null;
        }
        if (com.umeng.commonsdk.stateless.d.c != null) {
            com.umeng.commonsdk.stateless.d.c.quit();
            if (com.umeng.commonsdk.stateless.d.c != null) {
                com.umeng.commonsdk.stateless.d.c = null;
            }
            if (com.umeng.commonsdk.stateless.d.d != null) {
                com.umeng.commonsdk.stateless.d.d = null;
            }
        }
    }
    
    static {
        com.umeng.commonsdk.stateless.d.c = null;
        com.umeng.commonsdk.stateless.d.d = null;
        com.umeng.commonsdk.stateless.d.e = new Object();
        com.umeng.commonsdk.stateless.d.h = false;
        com.umeng.commonsdk.stateless.d.i = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                try {
                    if (context != null && intent != null && intent.getAction() != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                        com.umeng.commonsdk.stateless.d.b = context.getApplicationContext();
                        if (com.umeng.commonsdk.stateless.d.b != null) {
                            final ConnectivityManager connectivityManager = (ConnectivityManager)com.umeng.commonsdk.stateless.d.b.getSystemService("connectivity");
                            if (connectivityManager != null) {
                                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                                    com.umeng.commonsdk.stateless.d.h = true;
                                    ULog.i("walle", "[stateless] net reveiver ok --->>>");
                                    com.umeng.commonsdk.stateless.d.b(273);
                                }
                                else {
                                    ULog.i("walle", "[stateless] net reveiver disconnected --->>>");
                                    com.umeng.commonsdk.stateless.d.h = false;
                                }
                            }
                        }
                    }
                }
                catch (Throwable t) {
                    UMCrashManager.reportCrash(context, t);
                }
            }
        };
    }
}
