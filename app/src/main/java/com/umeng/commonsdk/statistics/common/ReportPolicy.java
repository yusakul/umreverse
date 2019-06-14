package com.umeng.commonsdk.statistics.common;

import com.umeng.commonsdk.statistics.internal.*;
import com.umeng.commonsdk.framework.*;
import android.content.*;
import com.umeng.commonsdk.statistics.noise.*;

public class ReportPolicy
{
    public static final int REALTIME = 0;
    public static final int BATCH_AT_LAUNCH = 1;
    static final int BATCH_AT_TERMINATE = 2;
    static final int IMMEDIATE = 3;
    public static final int DAILY = 4;
    public static final int WIFIONLY = 5;
    public static final int BATCH_BY_INTERVAL = 6;
    public static final int SMART_POLICY = 8;
    public static final int QUASI_REALTIME_POLICY = 11;
    
    public static boolean isValid(final int n) {
        boolean b = false;
        switch (n) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
            case 11: {
                b = true;
                break;
            }
            default: {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public static class ReportStrategy
    {
        public boolean shouldSendMessage(final boolean b) {
            return true;
        }
        
        public boolean isValid() {
            return true;
        }
    }
    
    public static class ReportQuasiRealtime extends ReportStrategy
    {
        private static long MIN_REPORT_INTERVAL;
        private static long MAX_REPORT_INTERVAL;
        private long mReportInterval;
        
        @Override
        public boolean shouldSendMessage(final boolean b) {
            return true;
        }
        
        public long getReportInterval() {
            return this.mReportInterval;
        }
        
        public void setReportInterval(final long mReportInterval) {
            if (mReportInterval >= ReportQuasiRealtime.MIN_REPORT_INTERVAL && mReportInterval <= ReportQuasiRealtime.MAX_REPORT_INTERVAL) {
                this.mReportInterval = mReportInterval;
            }
            else {
                this.mReportInterval = ReportQuasiRealtime.MIN_REPORT_INTERVAL;
            }
        }
        
        static {
            ReportQuasiRealtime.MIN_REPORT_INTERVAL = 15000L;
            ReportQuasiRealtime.MAX_REPORT_INTERVAL = 90000L;
        }
    }
    
    public static class ReportRealtime extends ReportStrategy
    {
        @Override
        public boolean shouldSendMessage(final boolean b) {
            return true;
        }
    }
    
    public static class ReportAtLaunch extends ReportStrategy
    {
        @Override
        public boolean shouldSendMessage(final boolean b) {
            return b;
        }
    }
    
    public static class ReportByInterval extends ReportStrategy
    {
        private static long MIN_REPORT_INTERVAL;
        private static long MAX_REPORT_INTERVAL;
        private long mReportInterval;
        private StatTracer mTracer;
        
        public ReportByInterval(final StatTracer mTracer, final long reportInterval) {
            this.mTracer = mTracer;
            this.setReportInterval(reportInterval);
        }
        
        @Override
        public boolean shouldSendMessage(final boolean b) {
            return b || System.currentTimeMillis() - UMEnvelopeBuild.lastSuccessfulBuildTime(UMModuleRegister.getAppContext()) >= this.mReportInterval;
        }
        
        public void setReportInterval(final long mReportInterval) {
            if (mReportInterval >= ReportByInterval.MIN_REPORT_INTERVAL && mReportInterval <= ReportByInterval.MAX_REPORT_INTERVAL) {
                this.mReportInterval = mReportInterval;
            }
            else {
                this.mReportInterval = ReportByInterval.MIN_REPORT_INTERVAL;
            }
        }
        
        public long getReportInterval() {
            return this.mReportInterval;
        }
        
        public static boolean isValidValue(final int n) {
            return n >= ReportByInterval.MIN_REPORT_INTERVAL;
        }
        
        static {
            ReportByInterval.MIN_REPORT_INTERVAL = 90000L;
            ReportByInterval.MAX_REPORT_INTERVAL = 86400000L;
        }
    }
    
    public static class ReportDaily extends ReportStrategy
    {
        private long HOURS_DAY;
        private StatTracer mTracer;
        
        public ReportDaily(final StatTracer mTracer) {
            this.HOURS_DAY = 86400000L;
            this.mTracer = mTracer;
        }
        
        @Override
        public boolean shouldSendMessage(final boolean b) {
            return System.currentTimeMillis() - UMEnvelopeBuild.lastSuccessfulBuildTime(UMModuleRegister.getAppContext()) >= this.HOURS_DAY;
        }
    }
    
    public static class ReportWifiOnly extends ReportStrategy
    {
        private Context mContext;
        
        public ReportWifiOnly(final Context mContext) {
            this.mContext = null;
            this.mContext = mContext;
        }
        
        @Override
        public boolean shouldSendMessage(final boolean b) {
            return DeviceConfig.isWiFiAvailable(this.mContext);
        }
    }
    
    public static class DefconPolicy extends ReportStrategy
    {
        private Defcon defcon;
        private StatTracer tracer;
        
        public DefconPolicy(final StatTracer tracer, final Defcon defcon) {
            this.tracer = tracer;
            this.defcon = defcon;
        }
        
        public boolean shouldSendMessageByInstant() {
            return System.currentTimeMillis() - UMEnvelopeBuild.lastInstantBuildTime(UMModuleRegister.getAppContext()) >= this.defcon.getReqInterval();
        }
        
        @Override
        public boolean shouldSendMessage(final boolean b) {
            return System.currentTimeMillis() - UMEnvelopeBuild.lastSuccessfulBuildTime(UMModuleRegister.getAppContext()) >= this.defcon.getReqInterval();
        }
        
        @Override
        public boolean isValid() {
            return this.defcon.isOpen();
        }
    }
    
    public static class LatentPolicy extends ReportStrategy
    {
        private long latency;
        private long start;
        
        public LatentPolicy(final int n) {
            this.start = 0L;
            this.latency = n;
            this.start = System.currentTimeMillis();
        }
        
        @Override
        public boolean shouldSendMessage(final boolean b) {
            return System.currentTimeMillis() - this.start >= this.latency;
        }
        
        @Override
        public boolean isValid() {
            return System.currentTimeMillis() - this.start < this.latency;
        }
    }
    
    public static class SmartPolicy extends ReportStrategy
    {
        private final long ReportInterval = 10800000L;
        private StatTracer mTracer;
        
        public SmartPolicy(final StatTracer mTracer) {
            this.mTracer = mTracer;
        }
        
        @Override
        public boolean shouldSendMessage(final boolean b) {
            return System.currentTimeMillis() - UMEnvelopeBuild.lastSuccessfulBuildTime(UMModuleRegister.getAppContext()) >= 10800000L;
        }
    }
    
    public static class DebugPolicy extends ReportStrategy
    {
        private final long ReportInterval = 15000L;
        private StatTracer mTracer;
        
        public DebugPolicy(final StatTracer mTracer) {
            this.mTracer = mTracer;
        }
        
        @Override
        public boolean shouldSendMessage(final boolean b) {
            return System.currentTimeMillis() - UMEnvelopeBuild.lastSuccessfulBuildTime(UMModuleRegister.getAppContext()) >= 15000L;
        }
    }
}
