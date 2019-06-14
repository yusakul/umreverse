package com.umeng.commonsdk.framework;

import android.content.*;

public class UMWorkDispatch
{
    public static void sendEvent(final Context context, final int n, final UMLogDataProtocol umLogDataProtocol, final Object o) {
        c.a(context, n, umLogDataProtocol, o);
    }
    
    public static boolean eventHasExist(final int n) {
        return c.a(n);
    }
    
    public static void Quit() {
        c.a();
    }
    
    public static void registerConnStateObserver(final UMSenderStateNotify umSenderStateNotify) {
        c.a(umSenderStateNotify);
    }
}
