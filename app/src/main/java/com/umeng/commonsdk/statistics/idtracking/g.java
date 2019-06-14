package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.statistics.*;
import com.umeng.commonsdk.internal.crash.*;

public class g extends a
{
    private static final String a = "mac";
    private Context b;
    
    public g(final Context b) {
        super("mac");
        this.b = b;
    }
    
    @Override
    public String f() {
        String mac = null;
        try {
            mac = DeviceConfig.getMac(this.b);
        }
        catch (Exception ex) {
            if (AnalyticsConstants.UM_DEBUG) {
                ex.printStackTrace();
            }
            UMCrashManager.reportCrash(this.b, ex);
        }
        return mac;
    }
}
