package com.umeng.commonsdk.statistics;

import com.umeng.commonsdk.statistics.common.*;

public class AnalyticsConstants
{
    public static final String OS = "Android";
    public static final String SDK_TYPE = "Android";
    public static final String LOG_TAG = "MobclickAgent";
    public static boolean CHECK_DEVICE;
    public static boolean SUB_PROCESS_EVENT;
    public static String[] APPLOG_URL_LIST;
    public static final boolean UM_DEBUG;
    private static int commonDeviceType;
    
    public static void setDeviceType(final int commonDeviceType) {
        AnalyticsConstants.commonDeviceType = commonDeviceType;
    }
    
    public static synchronized int getDeviceType() {
        return AnalyticsConstants.commonDeviceType;
    }
    
    static {
        AnalyticsConstants.CHECK_DEVICE = true;
        AnalyticsConstants.SUB_PROCESS_EVENT = false;
        AnalyticsConstants.APPLOG_URL_LIST = new String[] { UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL };
        UM_DEBUG = ULog.DEBUG;
        AnalyticsConstants.commonDeviceType = 1;
    }
}
