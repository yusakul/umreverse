package com.umeng.commonsdk.debug;

import android.util.*;

public class UMRTLog
{
    private static final String RTLOG_PROP = "debug.umeng.rtlog";
    private static final String RTLOG_ENABLE = "1";
    public static final String RTLOG_TAG = "MobclickRT";
    
    private UMRTLog() {
    }
    
    private static String getSystemProperty(final String s, final String s2) {
        try {
            return (String)Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, s, s2);
        }
        catch (Throwable t) {
            return s2;
        }
    }
    
    private static boolean shouldOutput() {
        return "1".equals(getSystemProperty("debug.umeng.rtlog", "0"));
    }
    
    public static void e(final String s, final String s2) {
        if (shouldOutput()) {
            Log.e(s, warpperMsg(s2, false));
        }
    }
    
    public static void w(final String s, final String s2) {
        if (shouldOutput()) {
            Log.w(s, warpperMsg(s2, false));
        }
    }
    
    public static void i(final String s, final String s2) {
        if (shouldOutput()) {
            Log.i(s, warpperMsg(s2, false));
        }
    }
    
    public static void d(final String s, final String s2) {
        if (shouldOutput()) {
            Log.d(s, warpperMsg(s2, false));
        }
    }
    
    public static void v(final String s, final String s2) {
        if (shouldOutput()) {
            Log.v(s, warpperMsg(s2, false));
        }
    }
    
    public static void se(final String s, final String s2) {
        if (shouldOutput()) {
            Log.e(s, warpperMsg(s2, true));
        }
    }
    
    public static void sw(final String s, final String s2) {
        if (shouldOutput()) {
            Log.w(s, warpperMsg(s2, true));
        }
    }
    
    public static void si(final String s, final String s2) {
        if (shouldOutput()) {
            Log.i(s, warpperMsg(s2, true));
        }
    }
    
    public static void sd(final String s, final String s2) {
        if (shouldOutput()) {
            Log.d(s, warpperMsg(s2, true));
        }
    }
    
    public static void sv(final String s, final String s2) {
        if (shouldOutput()) {
            Log.v(s, warpperMsg(s2, true));
        }
    }
    
    private static String warpperMsg(final String str, final boolean b) {
        if (!b) {
            return str;
        }
        StringBuffer sb = null;
        try {
            final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length >= 3) {
                final String fileName = stackTrace[2].getFileName();
                final String methodName = stackTrace[2].getMethodName();
                final int lineNumber = stackTrace[2].getLineNumber();
                sb = new StringBuffer();
                sb.append("<");
                sb.append(fileName);
                sb.append(":");
                sb.append(methodName);
                sb.append(":");
                sb.append(lineNumber);
                sb.append("> ");
                sb.append(str);
            }
        }
        catch (Throwable t) {
            return str;
        }
        return sb.toString();
    }
}
