package com.umeng.commonsdk.internal.utils;

import org.json.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.internal.*;
import com.umeng.commonsdk.framework.*;
import com.umeng.commonsdk.internal.crash.*;
import android.content.*;

public class c
{
    private static final String a = "BatteryUtils";
    private static boolean b;
    private static Context c;
    private BroadcastReceiver d;
    
    private c() {
        this.d = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                try {
                    if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                        final JSONObject jsonObject = new JSONObject();
                        final int intExtra = intent.getIntExtra("level", 0);
                        try {
                            jsonObject.put("le", intExtra);
                        }
                        catch (Exception ex) {}
                        final int intExtra2 = intent.getIntExtra("voltage", 0);
                        try {
                            jsonObject.put("vol", intExtra2);
                        }
                        catch (Exception ex2) {}
                        final int intExtra3 = intent.getIntExtra("temperature", 0);
                        try {
                            jsonObject.put("temp", intExtra3);
                            jsonObject.put("ts", System.currentTimeMillis());
                        }
                        catch (Exception ex3) {}
                        final int intExtra4 = intent.getIntExtra("status", 0);
                        int n = -1;
                        switch (intExtra4) {
                            case 1: {
                                n = -1;
                                break;
                            }
                            case 2: {
                                n = 1;
                            }
                            case 4: {
                                n = 0;
                                break;
                            }
                            case 5: {
                                n = 2;
                                break;
                            }
                        }
                        try {
                            jsonObject.put("st", n);
                        }
                        catch (Exception ex4) {}
                        final int intExtra5 = intent.getIntExtra("plugged", 0);
                        int n2 = 0;
                        switch (intExtra5) {
                            case 1: {
                                n2 = 1;
                                break;
                            }
                            case 2: {
                                n2 = 2;
                                break;
                            }
                        }
                        try {
                            jsonObject.put("ct", n2);
                            jsonObject.put("ts", System.currentTimeMillis());
                        }
                        catch (Exception ex5) {}
                        ULog.i("BatteryUtils", jsonObject.toString());
                        UMWorkDispatch.sendEvent(context, 32771, com.umeng.commonsdk.internal.b.a(com.umeng.commonsdk.internal.utils.c.c).a(), jsonObject.toString());
                        com.umeng.commonsdk.internal.utils.c.this.c();
                    }
                }
                catch (Throwable t) {
                    UMCrashManager.reportCrash(com.umeng.commonsdk.internal.utils.c.c, t);
                }
            }
        };
    }
    
    public static c a(final Context context) {
        if (com.umeng.commonsdk.internal.utils.c.c == null && context != null) {
            com.umeng.commonsdk.internal.utils.c.c = context.getApplicationContext();
        }
        return com.umeng.commonsdk.internal.utils.c.InnerClass_a.a;
    }
    
    public synchronized boolean a() {
        return com.umeng.commonsdk.internal.utils.c.b;
    }
    
    public synchronized void b() {
        try {
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            com.umeng.commonsdk.internal.utils.c.c.registerReceiver(this.d, intentFilter);
            com.umeng.commonsdk.internal.utils.c.b = true;
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(com.umeng.commonsdk.internal.utils.c.c, t);
        }
    }
    
    public synchronized void c() {
        try {
            com.umeng.commonsdk.internal.utils.c.c.unregisterReceiver(this.d);
            com.umeng.commonsdk.internal.utils.c.b = false;
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(com.umeng.commonsdk.internal.utils.c.c, t);
        }
    }
    
    static {
        com.umeng.commonsdk.internal.utils.c.b = false;
        com.umeng.commonsdk.internal.utils.c.c = null;
    }
    
    private static class InnerClass_a
    {
        private static final c a;
        
        static {
            a = new c();
        }

        static c a() {
            return InnerClass_a.a;
        }
    }
}
