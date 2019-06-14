package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;

public class f extends a
{
    private static final String a = "imei";
    private Context b;
    
    public f(final Context b) {
        super("imei");
        this.b = b;
    }
    
    @Override
    public String f() {
        return DeviceConfig.getImeiNew(this.b);
    }
}
