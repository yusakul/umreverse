package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;

public class b extends a
{
    private static final String a = "android_id";
    private Context b;
    
    public b(final Context b) {
        super("android_id");
        this.b = b;
    }
    
    @Override
    public String f() {
        return DeviceConfig.getAndroidId(this.b);
    }
}
