package com.umeng.commonsdk.framework;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.service.*;
import com.umeng.commonsdk.internal.crash.*;
import com.umeng.commonsdk.debug.*;
import org.json.*;
import android.os.*;

public class c
{
    private static HandlerThread d;
    private static Handler e;
    private static b f;
    private static Object g;
    private static final int h = 768;
    private static final int i = 769;
    private static final int j = 770;
    private static final int k = 784;
    public static final String a = "content";
    public static final String b = "header";
    public static final String c = "exception";
    
    public static void a(final UMSenderStateNotify umSenderStateNotify) {
        if (com.umeng.commonsdk.framework.c.f != null) {
            com.umeng.commonsdk.framework.b.a(umSenderStateNotify);
        }
    }
    
    public static void a(final Context context, final int arg1, final UMLogDataProtocol umLogDataProtocol, final Object obj) {
        if (context == null || umLogDataProtocol == null) {
            ULog.d("--->>> Context or UMLogDataProtocol parameter cannot be null!");
            return;
        }
        UMModuleRegister.registerAppContext(context.getApplicationContext());
        if (!UMModuleRegister.registerCallback(arg1, umLogDataProtocol)) {
            return;
        }
        if (com.umeng.commonsdk.framework.c.d == null || com.umeng.commonsdk.framework.c.e == null) {
            e();
        }
        try {
            if (com.umeng.commonsdk.framework.c.e != null) {
                if (UMGlobalContext.getInstance().isMainProcess(context)) {
                    synchronized (com.umeng.commonsdk.framework.c.g) {
                        if (com.umeng.commonsdk.framework.c.f == null) {
                            UMFrUtils.syncLegacyEnvelopeIfNeeded(context);
                            com.umeng.commonsdk.framework.c.f = new b(context, com.umeng.commonsdk.framework.c.e);
                        }
                    }
                }
                final Message obtainMessage = com.umeng.commonsdk.framework.c.e.obtainMessage();
                obtainMessage.what = 768;
                obtainMessage.arg1 = arg1;
                obtainMessage.obj = obj;
                com.umeng.commonsdk.framework.c.e.sendMessage(obtainMessage);
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), t);
        }
    }
    
    private c() {
    }
    
    private static void d() {
        ULog.d("--->>> delayProcess Enter...");
        UMRTLog.i("MobclickRT", "--->>> delayProcess Enter...");
        final Context appContext = UMModuleRegister.getAppContext();
        if (appContext != null) {
            if (!UMFrUtils.isOnline(appContext)) {
                return;
            }
            final long maxDataSpace = UMEnvelopeBuild.maxDataSpace(appContext);
            final UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName("analytics");
            JSONObject setupReportData = null;
            try {
                if (callbackFromModuleName != null) {
                    setupReportData = callbackFromModuleName.setupReportData(maxDataSpace);
                    if (setupReportData == null) {
                        UMRTLog.i("MobclickRT", "--->>> analyticsCB.setupReportData() return null");
                        return;
                    }
                }
                if (setupReportData != null && setupReportData.length() > 0) {
                    final JSONObject jsonObject = (JSONObject)setupReportData.opt("header");
                    final JSONObject jsonObject2 = (JSONObject)setupReportData.opt("content");
                    if (appContext != null && jsonObject != null && jsonObject2 != null) {
                        final JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(appContext, jsonObject, jsonObject2);
                        if (buildEnvelopeWithExtHeader != null) {
                            try {
                                if (buildEnvelopeWithExtHeader.has("exception")) {
                                    UMRTLog.i("MobclickRT", "--->>> autoProcess: Build envelope error code: " + buildEnvelopeWithExtHeader.getInt("exception"));
                                }
                            }
                            catch (Throwable t2) {}
                            UMRTLog.i("MobclickRT", "--->>> autoProcess: removeCacheData ... ");
                            callbackFromModuleName.removeCacheData(buildEnvelopeWithExtHeader);
                        }
                    }
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(appContext, t);
            }
        }
    }
    
    public static void a(final long n) {
        if (com.umeng.commonsdk.framework.c.e != null) {
            if (com.umeng.commonsdk.framework.c.e.hasMessages(770)) {
                UMRTLog.i("MobclickRT", "--->>> MSG_DELAY_PROCESS has exist. do nothing.");
            }
            else {
                UMRTLog.i("MobclickRT", "--->>> MSG_DELAY_PROCESS not exist. send it.");
                final Message obtainMessage = com.umeng.commonsdk.framework.c.e.obtainMessage();
                obtainMessage.what = 770;
                com.umeng.commonsdk.framework.c.e.sendMessageDelayed(obtainMessage, n);
            }
        }
    }
    
    private static synchronized void e() {
        ULog.d("--->>> Dispatch: init Enter...");
        try {
            if (com.umeng.commonsdk.framework.c.d == null) {
                (com.umeng.commonsdk.framework.c.d = new HandlerThread("work_thread")).start();
                if (com.umeng.commonsdk.framework.c.e == null) {
                    com.umeng.commonsdk.framework.c.e = new Handler(com.umeng.commonsdk.framework.c.d.getLooper()) {
                        public void handleMessage(final Message message) {
                            switch (message.what) {
                                case 768: {
                                    b(message);
                                    break;
                                }
                                case 770: {
                                    d();
                                    break;
                                }
                                case 784: {
                                    g();
                                    break;
                                }
                            }
                        }
                    };
                }
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), t);
        }
        ULog.d("--->>> Dispatch: init Exit...");
    }
    
    public static synchronized boolean a(final int n) {
        return com.umeng.commonsdk.framework.c.e != null && com.umeng.commonsdk.framework.c.e.hasMessages(n);
    }
    
    private static void b(final Message message) {
        final int arg1 = message.arg1;
        final Object obj = message.obj;
        final UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName(UMModuleRegister.eventType2ModuleName(arg1));
        if (callbackFromModuleName != null) {
            ULog.d("--->>> dispatch:handleEvent: call back workEvent with msg type [ 0x" + Integer.toHexString(arg1) + "]");
            callbackFromModuleName.workEvent(obj, arg1);
        }
    }
    
    private static void f() {
        if (com.umeng.commonsdk.framework.c.d != null) {
            com.umeng.commonsdk.framework.c.d = null;
        }
        if (com.umeng.commonsdk.framework.c.e != null) {
            com.umeng.commonsdk.framework.c.e = null;
        }
        if (com.umeng.commonsdk.framework.c.f != null) {
            com.umeng.commonsdk.framework.c.f = null;
        }
    }
    
    private static void g() {
        if (com.umeng.commonsdk.framework.c.f != null && com.umeng.commonsdk.framework.c.d != null) {
            com.umeng.commonsdk.framework.b.c();
            ULog.d("--->>> handleQuit: Quit dispatch thread.");
            com.umeng.commonsdk.framework.c.d.quit();
            f();
        }
    }
    
    public static void a() {
        if (com.umeng.commonsdk.framework.c.e != null) {
            final Message obtainMessage = com.umeng.commonsdk.framework.c.e.obtainMessage();
            obtainMessage.what = 784;
            com.umeng.commonsdk.framework.c.e.sendMessage(obtainMessage);
        }
    }
    
    static {
        com.umeng.commonsdk.framework.c.d = null;
        com.umeng.commonsdk.framework.c.e = null;
        com.umeng.commonsdk.framework.c.f = null;
        com.umeng.commonsdk.framework.c.g = new Object();
    }
}
