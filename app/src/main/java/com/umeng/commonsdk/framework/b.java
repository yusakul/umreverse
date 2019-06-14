package com.umeng.commonsdk.framework;

import com.umeng.commonsdk.statistics.internal.*;
import android.net.*;
import java.util.*;
import com.umeng.commonsdk.debug.*;
import com.umeng.commonsdk.internal.crash.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.statistics.idtracking.*;
import com.umeng.commonsdk.statistics.*;
import java.io.*;
import android.content.*;
import android.os.*;

class b implements UMImprintChangeCallback
{
    private static HandlerThread a;
    private static Handler b;
    private static Handler c;
    private static final int d = 200;
    private static final int e = 273;
    private static final int f = 274;
    private static final int g = 512;
    private static final int h = 769;
    private static a i;
    private static ConnectivityManager j;
    private static NetworkInfo k;
    private static IntentFilter l;
    private static boolean m;
    private static ArrayList<UMSenderStateNotify> n;
    private static Object o;
    private static final String p = "report_policy";
    private static final String q = "report_interval";
    private static boolean r;
    private static final int s = 15000;
    private static final int t = 15;
    private static final int u = 90;
    private static int v;
    private static Object w;
    private static BroadcastReceiver x;
    
    public static void a(final UMSenderStateNotify e) {
        synchronized (com.umeng.commonsdk.framework.b.o) {
            try {
                if (com.umeng.commonsdk.framework.b.n == null) {
                    com.umeng.commonsdk.framework.b.n = new ArrayList<UMSenderStateNotify>();
                }
                if (e != null) {
                    for (int i = 0; i < com.umeng.commonsdk.framework.b.n.size(); ++i) {
                        if (e == com.umeng.commonsdk.framework.b.n.get(i)) {
                            UMRTLog.i("MobclickRT", "--->>> addConnStateObserver: input item has exist.");
                            return;
                        }
                    }
                    com.umeng.commonsdk.framework.b.n.add(e);
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), t);
            }
        }
    }
    
    public static boolean a() {
        synchronized (com.umeng.commonsdk.framework.b.w) {
            return com.umeng.commonsdk.framework.b.r;
        }
    }
    
    public static int b() {
        synchronized (com.umeng.commonsdk.framework.b.w) {
            return com.umeng.commonsdk.framework.b.v;
        }
    }
    
    private void m() {
        synchronized (com.umeng.commonsdk.framework.b.w) {
            if ("11".equals(UMEnvelopeBuild.imprintProperty(UMModuleRegister.getAppContext(), "report_policy", ""))) {
                UMRTLog.i("MobclickRT", "--->>> switch to report_policy 11");
                com.umeng.commonsdk.framework.b.r = true;
                com.umeng.commonsdk.framework.b.v = 15000;
                final int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(UMModuleRegister.getAppContext(), "report_interval", "15"));
                UMRTLog.i("MobclickRT", "--->>> set report_interval value to: " + intValue);
                if (intValue < 15 || intValue > 90) {
                    com.umeng.commonsdk.framework.b.v = 15000;
                }
                else {
                    com.umeng.commonsdk.framework.b.v = intValue * 1000;
                }
            }
            else {
                com.umeng.commonsdk.framework.b.r = false;
            }
        }
    }
    
    @Override
    public void onImprintValueChanged(final String s, final String s2) {
        synchronized (com.umeng.commonsdk.framework.b.w) {
            if ("report_policy".equals(s)) {
                if ("11".equals(s2)) {
                    UMRTLog.i("MobclickRT", "--->>> switch to report_policy 11");
                    com.umeng.commonsdk.framework.b.r = true;
                }
                else {
                    com.umeng.commonsdk.framework.b.r = false;
                }
            }
            if ("report_interval".equals(s)) {
                final int intValue = Integer.valueOf(s2);
                UMRTLog.i("MobclickRT", "--->>> set report_interval value to: " + intValue);
                if (intValue < 15 || intValue > 90) {
                    com.umeng.commonsdk.framework.b.v = 15000;
                }
                else {
                    com.umeng.commonsdk.framework.b.v = intValue * 1000;
                }
            }
        }
    }
    
    public b(final Context context, final Handler c) {
        final Context appContext = UMModuleRegister.getAppContext();
        com.umeng.commonsdk.framework.b.j = (ConnectivityManager)appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        com.umeng.commonsdk.framework.b.c = c;
        try {
            if (com.umeng.commonsdk.framework.b.a == null) {
                (com.umeng.commonsdk.framework.b.a = new HandlerThread("NetWorkSender")).start();
                if (com.umeng.commonsdk.framework.b.i == null) {
                    (com.umeng.commonsdk.framework.b.i = new a(UMFrUtils.getEnvelopeDirPath(context))).startWatching();
                    ULog.d("--->>> FileMonitor has already started!");
                }
                if (DeviceConfig.checkPermission(appContext, "android.permission.ACCESS_NETWORK_STATE") && com.umeng.commonsdk.framework.b.j != null && com.umeng.commonsdk.framework.b.l == null) {
                    (com.umeng.commonsdk.framework.b.l = new IntentFilter()).addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    if (com.umeng.commonsdk.framework.b.x != null) {
                        appContext.registerReceiver(com.umeng.commonsdk.framework.b.x, com.umeng.commonsdk.framework.b.l);
                    }
                }
                this.m();
                if (com.umeng.commonsdk.framework.b.b == null) {
                    com.umeng.commonsdk.framework.b.b = new Handler(com.umeng.commonsdk.framework.b.a.getLooper()) {
                        public void handleMessage(final Message message) {
                            switch (message.what) {
                                case 273: {
                                    ULog.d("--->>> handleMessage: recv MSG_PROCESS_NEXT msg.");
                                    q();
                                    break;
                                }
                                case 512: {
                                    p();
                                    break;
                                }
                                case 274: {
                                    o();
                                    break;
                                }
                            }
                        }
                    };
                }
                ImprintHandler.getImprintService(context).registImprintCallback("report_policy", this);
                ImprintHandler.getImprintService(context).registImprintCallback("report_interval", this);
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(context, t);
        }
    }
    
    private static void n() {
        if (com.umeng.commonsdk.framework.b.a != null) {
            com.umeng.commonsdk.framework.b.a = null;
        }
        if (com.umeng.commonsdk.framework.b.b != null) {
            com.umeng.commonsdk.framework.b.b = null;
        }
        if (com.umeng.commonsdk.framework.b.c != null) {
            com.umeng.commonsdk.framework.b.c = null;
        }
    }
    
    private static void o() {
        synchronized (com.umeng.commonsdk.framework.b.o) {
            if (com.umeng.commonsdk.framework.b.n != null) {
                final int size = com.umeng.commonsdk.framework.b.n.size();
                if (size > 0) {
                    for (int i = 0; i < size; ++i) {
                        com.umeng.commonsdk.framework.b.n.get(i).onSenderIdle();
                    }
                }
            }
        }
    }
    
    private static void p() {
    }
    
    public static void c() {
    }
    
    private static void b(final int what) {
        if (com.umeng.commonsdk.framework.b.m && com.umeng.commonsdk.framework.b.b != null && !com.umeng.commonsdk.framework.b.b.hasMessages(what)) {
            final Message obtainMessage = com.umeng.commonsdk.framework.b.b.obtainMessage();
            obtainMessage.what = what;
            com.umeng.commonsdk.framework.b.b.sendMessage(obtainMessage);
        }
    }
    
    private static void c(final int what) {
        if (com.umeng.commonsdk.framework.b.m && com.umeng.commonsdk.framework.b.b != null) {
            final Message obtainMessage = com.umeng.commonsdk.framework.b.b.obtainMessage();
            obtainMessage.what = what;
            com.umeng.commonsdk.framework.b.b.sendMessage(obtainMessage);
        }
    }
    
    private static void a(final int what, final long lng) {
        if (com.umeng.commonsdk.framework.b.m && com.umeng.commonsdk.framework.b.b != null) {
            final Message obtainMessage = com.umeng.commonsdk.framework.b.b.obtainMessage();
            obtainMessage.what = what;
            UMRTLog.i("MobclickRT", "--->>> sendMsgDelayed: " + lng);
            com.umeng.commonsdk.framework.b.b.sendMessageDelayed(obtainMessage, lng);
        }
    }
    
    public static void d() {
        b(273);
    }
    
    private static void a(final int what, final int n) {
        if (com.umeng.commonsdk.framework.b.m && com.umeng.commonsdk.framework.b.b != null) {
            com.umeng.commonsdk.framework.b.b.removeMessages(what);
            final Message obtainMessage = com.umeng.commonsdk.framework.b.b.obtainMessage();
            obtainMessage.what = what;
            com.umeng.commonsdk.framework.b.b.sendMessageDelayed(obtainMessage, (long)n);
        }
    }
    
    public static void e() {
        a(274, 3000);
    }
    
    private static void q() {
        ULog.d("--->>> handleProcessNext: Enter...");
        if (!com.umeng.commonsdk.framework.b.m) {
            return;
        }
        final Context appContext = UMModuleRegister.getAppContext();
        try {
            if (UMFrUtils.envelopeFileNumber(appContext) > 0) {
                ULog.d("--->>> The envelope file exists.");
                if (UMFrUtils.envelopeFileNumber(appContext) > 200) {
                    ULog.d("--->>> Number of envelope files is greater than 200, remove old files first.");
                    UMFrUtils.removeRedundantEnvelopeFiles(appContext, 200);
                }
                final File envelopeFile = UMFrUtils.getEnvelopeFile(appContext);
                if (envelopeFile != null) {
                    final String path = envelopeFile.getPath();
                    ULog.d("--->>> Ready to send envelope file [" + path + "].");
                    UMRTLog.i("MobclickRT", "--->>> send envelope file [ " + path + "].");
                    if (new com.umeng.commonsdk.statistics.c(appContext).a(envelopeFile)) {
                        ULog.d("--->>> Send envelope file success, delete it.");
                        if (!UMFrUtils.removeEnvelopeFile(envelopeFile)) {
                            ULog.d("--->>> Failed to delete already processed file. We try again after delete failed.");
                            UMFrUtils.removeEnvelopeFile(envelopeFile);
                        }
                        c(273);
                        return;
                    }
                    ULog.d("--->>> Send envelope file failed, abandon and wait next trigger!");
                    return;
                }
            }
            e();
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(appContext, t);
        }
    }
    
    static {
        com.umeng.commonsdk.framework.b.a = null;
        com.umeng.commonsdk.framework.b.b = null;
        com.umeng.commonsdk.framework.b.c = null;
        com.umeng.commonsdk.framework.b.l = null;
        com.umeng.commonsdk.framework.b.m = false;
        com.umeng.commonsdk.framework.b.n = null;
        com.umeng.commonsdk.framework.b.o = new Object();
        com.umeng.commonsdk.framework.b.r = false;
        com.umeng.commonsdk.framework.b.v = 15000;
        com.umeng.commonsdk.framework.b.w = new Object();
        com.umeng.commonsdk.framework.b.x = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    final Context appContext = UMModuleRegister.getAppContext();
                    try {
                        if (com.umeng.commonsdk.framework.b.j != null) {
                            com.umeng.commonsdk.framework.b.k = com.umeng.commonsdk.framework.b.j.getActiveNetworkInfo();
                            if (com.umeng.commonsdk.framework.b.k != null && com.umeng.commonsdk.framework.b.k.isAvailable()) {
                                ULog.i("--->>> network isAvailable, check if there are any files to send.");
                                com.umeng.commonsdk.framework.b.m = true;
                                synchronized (com.umeng.commonsdk.framework.b.o) {
                                    if (com.umeng.commonsdk.framework.b.n != null) {
                                        final int size = com.umeng.commonsdk.framework.b.n.size();
                                        if (size > 0) {
                                            for (int i = 0; i < size; ++i) {
                                                ((UMSenderStateNotify)com.umeng.commonsdk.framework.b.n.get(i)).onConnectionAvailable();
                                            }
                                        }
                                    }
                                }
                                c(273);
                                if (com.umeng.commonsdk.framework.b.k.getType() == 1) {
                                    try {
                                        if (context != null && !UMWorkDispatch.eventHasExist(32774)) {
                                            UMWorkDispatch.sendEvent(context, 32774, com.umeng.commonsdk.internal.b.a(context).a(), null);
                                        }
                                    }
                                    catch (Throwable t2) {}
                                }
                            }
                            else {
                                ULog.i("--->>> network disconnected.");
                                com.umeng.commonsdk.framework.b.m = false;
                            }
                        }
                    }
                    catch (Throwable t) {
                        UMCrashManager.reportCrash(appContext, t);
                    }
                }
            }
        };
    }
    
    static class a extends FileObserver
    {
        public a(final String s) {
            super(s);
        }
        
        public void onEvent(final int n, final String s) {
            switch (n & 0x8) {
                case 8: {
                    ULog.d("--->>> envelope file created >>> " + s);
                    UMRTLog.i("MobclickRT", "--->>> envelope file created >>> " + s);
                    c(273);
                    break;
                }
            }
        }
    }
}
