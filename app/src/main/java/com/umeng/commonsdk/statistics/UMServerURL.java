package com.umeng.commonsdk.statistics;

public class UMServerURL
{
    public static String DEFAULT_URL;
    public static String SECONDARY_URL;
    public static String OVERSEA_DEFAULT_URL;
    public static String OVERSEA_SECONDARY_URL;
    
    static {
        UMServerURL.DEFAULT_URL = "https://ulogs.umeng.com/unify_logs";
        UMServerURL.SECONDARY_URL = "https://ulogs.umengcloud.com/unify_logs";
        UMServerURL.OVERSEA_DEFAULT_URL = "https://alogus.umeng.com/unify_logs";
        UMServerURL.OVERSEA_SECONDARY_URL = "https://alogsus.umeng.com/unify_logs";
    }
}
