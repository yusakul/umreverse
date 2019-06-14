package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.common.*;

public class j extends a
{
    private static final String a = "serial";
    
    public j() {
        super("serial");
    }
    
    @Override
    public String f() {
        return DeviceConfig.getSerial();
    }
}
