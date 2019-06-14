package com.umeng.commonsdk.statistics.internal;

public interface b
{
    void onRequestStart();
    
    void onRequestEnd();
    
    void onRequestSucceed(final boolean p0);
    
    void onRequestFailed();
}
