package com.umeng.commonsdk.proguard;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.utils.*;
import android.location.*;
import android.os.*;
import com.umeng.commonsdk.internal.crash.*;

public class b
{
    private static final String a = "UMSysLocation";
    private LocationManager b;
    private static final int c = 10000;
    private Context d;
    private d e;
    
    private b() {
    }
    
    public b(final Context context) {
        if (context == null) {
            MLog.e("Context\u53c2\u6570\u4e0d\u80fd\u4e3anull");
        }
        else {
            this.d = context.getApplicationContext();
            this.b = (LocationManager)context.getApplicationContext().getSystemService("location");
        }
    }
    
    public synchronized void a(final d e) {
        ULog.i("UMSysLocation", "getSystemLocation");
        if (e == null || this.d == null) {
            return;
        }
        this.e = e;
        final boolean checkPermission = UMUtils.checkPermission(this.d, "android.permission.ACCESS_COARSE_LOCATION");
        final boolean checkPermission2 = UMUtils.checkPermission(this.d, "android.permission.ACCESS_FINE_LOCATION");
        if (!checkPermission) {
            if (!checkPermission2) {
                if (this.e != null) {
                    this.e.a(null);
                }
                return;
            }
        }
        try {
            if (this.b != null) {
                boolean b = false;
                boolean b2 = false;
                if (Build.VERSION.SDK_INT >= 21) {
                    b = this.b.isProviderEnabled("gps");
                    b2 = this.b.isProviderEnabled("network");
                }
                else {
                    if (checkPermission2) {
                        b = this.b.isProviderEnabled("gps");
                    }
                    if (checkPermission) {
                        b2 = this.b.isProviderEnabled("network");
                    }
                }
                Location location = null;
                if (b || b2) {
                    ULog.i("UMSysLocation", "getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)");
                    if (checkPermission2) {
                        location = this.b.getLastKnownLocation("passive");
                    }
                    else if (checkPermission) {
                        location = this.b.getLastKnownLocation("network");
                    }
                }
                this.e.a(location);
            }
        }
        catch (Throwable obj) {
            ULog.i("UMSysLocation", "e_enum is " + obj);
            try {
                if (e != null) {
                    e.a(null);
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(this.d, t);
            }
            UMCrashManager.reportCrash(this.d, obj);
        }
    }
    
    public synchronized void a() {
        ULog.i("UMSysLocation", "destroy");
        try {
            if (this.b != null) {
                this.b = null;
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(this.d, t);
        }
    }
}
