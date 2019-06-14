package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;

public class d extends a
{
    private static final String a = "idmd5";
    private Context b;
    
    public d(final Context b) {
        super("idmd5");
        this.b = b;
    }
    
    @Override
    public String f() {
        return DeviceConfig.getDeviceIdUmengMD5(this.b);
    }
}
