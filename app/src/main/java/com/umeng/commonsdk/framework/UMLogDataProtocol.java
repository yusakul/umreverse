package com.umeng.commonsdk.framework;

import org.json.*;

public interface UMLogDataProtocol
{
    void workEvent(final Object p0, final int p1);
    
    void removeCacheData(final Object p0);
    
    JSONObject setupReportData(final long p0);
    
    public enum UMBusinessType
    {
        U_APP, 
        U_INTERNAL;
    }
}
