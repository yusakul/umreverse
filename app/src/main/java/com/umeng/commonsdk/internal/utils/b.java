package com.umeng.commonsdk.internal.utils;

import android.content.*;
import android.telephony.*;
import com.umeng.commonsdk.statistics.common.*;
import android.text.*;
import com.umeng.commonsdk.framework.*;

public class b
{
    private static final String b = "BaseStationUtils";
    private static boolean c;
    private static Context d;
    private TelephonyManager e;
    PhoneStateListener a;
    
    private b(final Context context) {
        this.a = new PhoneStateListener() {
            public void onSignalStrengthsChanged(final SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                ULog.e("BaseStationUtils", "base station onSignalStrengthsChanged");
                try {
                    com.umeng.commonsdk.internal.utils.b.this.e = (TelephonyManager)com.umeng.commonsdk.internal.utils.b.d.getSystemService("phone");
                    final String[] split = signalStrength.toString().split(" ");
                    String str = null;
                    if (com.umeng.commonsdk.internal.utils.b.this.e != null && com.umeng.commonsdk.internal.utils.b.this.e.getNetworkType() == 13) {
                        str = "" + Integer.parseInt(split[9]);
                    }
                    else if (com.umeng.commonsdk.internal.utils.b.this.e != null && (com.umeng.commonsdk.internal.utils.b.this.e.getNetworkType() == 8 || com.umeng.commonsdk.internal.utils.b.this.e.getNetworkType() == 10 || com.umeng.commonsdk.internal.utils.b.this.e.getNetworkType() == 9 || com.umeng.commonsdk.internal.utils.b.this.e.getNetworkType() == 3)) {
                        final String b = com.umeng.commonsdk.internal.utils.b.this.e();
                        if (!TextUtils.isEmpty((CharSequence)b) && b.equals("\u4e2d\u56fd\u79fb\u52a8")) {
                            str = "0";
                        }
                        else if (!TextUtils.isEmpty((CharSequence)b) && b.equals("\u4e2d\u56fd\u8054\u901a")) {
                            str = signalStrength.getCdmaDbm() + "";
                        }
                        else if (!TextUtils.isEmpty((CharSequence)b) && b.equals("\u4e2d\u56fd\u7535\u4fe1")) {
                            str = signalStrength.getEvdoDbm() + "";
                        }
                    }
                    else {
                        str = -113 + 2 * signalStrength.getGsmSignalStrength() + "";
                    }
                    ULog.e("BaseStationUtils", "stationStrength is " + str);
                    if (!TextUtils.isEmpty((CharSequence)str)) {
                        try {
                            UMWorkDispatch.sendEvent(com.umeng.commonsdk.internal.utils.b.d, 32772, com.umeng.commonsdk.internal.b.a(com.umeng.commonsdk.internal.utils.b.d).a(), str);
                        }
                        catch (Throwable t) {}
                    }
                    com.umeng.commonsdk.internal.utils.b.this.c();
                }
                catch (Exception ex) {}
            }
        };
        try {
            if (context != null) {
                this.e = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            }
        }
        catch (Throwable t) {}
    }
    
    public static b a(final Context context) {
        if (com.umeng.commonsdk.internal.utils.b.d == null && context != null) {
            com.umeng.commonsdk.internal.utils.b.d = context.getApplicationContext();
        }
        return InnerClass_a.a;
    }
    
    public synchronized boolean a() {
        return com.umeng.commonsdk.internal.utils.b.c;
    }
    
    private String e() {
        String s = null;
        try {
            final TelephonyManager telephonyManager = (TelephonyManager)com.umeng.commonsdk.internal.utils.b.d.getSystemService("phone");
            if (telephonyManager == null) {
                return s;
            }
            final String simOperator = telephonyManager.getSimOperator();
            if (!TextUtils.isEmpty((CharSequence)simOperator)) {
                if (simOperator.equals("46000") || simOperator.equals("46002")) {
                    s = "\u4e2d\u56fd\u79fb\u52a8";
                }
                else if (simOperator.equals("46001")) {
                    s = "\u4e2d\u56fd\u8054\u901a";
                }
                else if (simOperator.equals("46003")) {
                    s = "\u4e2d\u56fd\u7535\u4fe1";
                }
            }
        }
        catch (Throwable t) {}
        return s;
    }
    
    public synchronized void b() {
        ULog.e("BaseStationUtils", "base station registerListener");
        try {
            if (this.e != null) {
                this.e.listen(this.a, 256);
            }
            com.umeng.commonsdk.internal.utils.b.c = true;
        }
        catch (Throwable t) {}
    }
    
    public synchronized void c() {
        ULog.e("BaseStationUtils", "base station unRegisterListener");
        try {
            if (this.e != null) {
                this.e.listen(this.a, 0);
            }
            com.umeng.commonsdk.internal.utils.b.c = false;
        }
        catch (Throwable t) {}
    }
    
    static {
        com.umeng.commonsdk.internal.utils.b.c = false;
        com.umeng.commonsdk.internal.utils.b.d = null;
    }
    
    private static class InnerClass_a
    {
        private static final b a;
        
        static {
            a = new b(com.umeng.commonsdk.internal.utils.b.d);
        }
        static b a() {
            return InnerClass_a.a;
        }
    }
}
