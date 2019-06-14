package com.umeng.commonsdk.statistics.internal;

import com.umeng.commonsdk.statistics.common.*;
import android.content.*;

public class StatTracer implements b
{
    private final int MAX_REQUEST_LIMIT = 3600000;
    public int mSuccessfulRequest;
    public int mFailedRequest;
    private int mLastRequestLatency;
    public long mLastSuccessfulRequestTime;
    private long lastRequestTime;
    private long firstActivateTime;
    private static final String KEY_CLIENT_REQUEST_SUCCESS = "successful_request";
    private static final String KEY_CLIENT_REQUEST_FAILED = "failed_requests ";
    private static final String KEY_CLIENT_REQUEST_LATENCY = "last_request_spent_ms";
    private static final String KEY_CLIENT_REQUEST_TIME = "last_request_time";
    private static final String KEY_FIRST_ACTIVATE_TIME = "first_activate_time";
    private static final String KEY_LAST_REQ = "last_req";
    private static Context mContext;
    
    private StatTracer() {
        this.lastRequestTime = 0L;
        this.firstActivateTime = 0L;
        this.init();
    }
    
    public static StatTracer getInstance(final Context context) {
        if (StatTracer.mContext == null) {
            if (context != null) {
                StatTracer.mContext = context.getApplicationContext();
            }
            else {
                MLog.e("inside StatTracer. please check context. context must not be null!");
            }
        }
        return a.a;
    }
    
    private void init() {
        final SharedPreferences default1 = PreferenceWrapper.getDefault(StatTracer.mContext);
        this.mSuccessfulRequest = default1.getInt("successful_request", 0);
        this.mFailedRequest = default1.getInt("failed_requests ", 0);
        this.mLastRequestLatency = default1.getInt("last_request_spent_ms", 0);
        this.mLastSuccessfulRequestTime = default1.getLong("last_request_time", 0L);
        this.lastRequestTime = default1.getLong("last_req", 0L);
    }
    
    public int getLastRequestLatency() {
        return (this.mLastRequestLatency > 3600000) ? 3600000 : this.mLastRequestLatency;
    }
    
    public boolean isFirstRequest() {
        return this.mLastSuccessfulRequestTime == 0L;
    }
    
    public void logSuccessfulRequest(final boolean b) {
        ++this.mSuccessfulRequest;
        if (b) {
            this.mLastSuccessfulRequestTime = this.lastRequestTime;
        }
    }
    
    public void logFailedRequest() {
        ++this.mFailedRequest;
    }
    
    public void logRequestStart() {
        this.lastRequestTime = System.currentTimeMillis();
    }
    
    public void logRequestEnd() {
        this.mLastRequestLatency = (int)(System.currentTimeMillis() - this.lastRequestTime);
    }
    
    public void saveSate() {
        PreferenceWrapper.getDefault(StatTracer.mContext).edit().putInt("successful_request", this.mSuccessfulRequest).putInt("failed_requests ", this.mFailedRequest).putInt("last_request_spent_ms", this.mLastRequestLatency).putLong("last_req", this.lastRequestTime).putLong("last_request_time", this.mLastSuccessfulRequestTime).commit();
    }
    
    public long getFirstActivateTime() {
        final SharedPreferences default1 = PreferenceWrapper.getDefault(StatTracer.mContext);
        this.firstActivateTime = PreferenceWrapper.getDefault(StatTracer.mContext).getLong("first_activate_time", 0L);
        if (this.firstActivateTime == 0L) {
            this.firstActivateTime = System.currentTimeMillis();
            default1.edit().putLong("first_activate_time", this.firstActivateTime).commit();
        }
        return this.firstActivateTime;
    }
    
    public long getLastReqTime() {
        return this.lastRequestTime;
    }
    
    @Override
    public void onRequestStart() {
        this.logRequestStart();
    }
    
    @Override
    public void onRequestEnd() {
        this.logRequestEnd();
    }
    
    @Override
    public void onRequestSucceed(final boolean b) {
        this.logSuccessfulRequest(b);
    }
    
    @Override
    public void onRequestFailed() {
        this.logFailedRequest();
    }
    
    static {
        StatTracer.mContext = null;
    }
    
    private static class a
    {
        public static final StatTracer a;
        
        static {
            a = new StatTracer();
        }
    }
}
