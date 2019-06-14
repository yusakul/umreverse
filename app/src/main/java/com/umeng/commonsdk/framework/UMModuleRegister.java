package com.umeng.commonsdk.framework;

import java.util.*;
import android.content.*;

public class UMModuleRegister
{
    private static HashMap<String, UMLogDataProtocol> mModuleMap;
    private static Context mModuleAppContext;
    public static final String ANALYTICS = "analytics";
    public static final String PUSH = "push";
    public static final String SHARE = "share";
    public static final String INNER = "internal";
    public static final String PROCESS = "process";
    private static final int PUSH_EVENT_VALUE_LOW = 16385;
    private static final int PUSH_EVENT_VALUE_HIGH = 20480;
    private static final int SHARE_EVENT_VALUE_LOW = 24577;
    private static final int SHARE_EVENT_VALUE_HIGH = 28672;
    private static final int INNER_EVENT_VALUE_LOW = 32769;
    private static final int INNER_EVENT_VALUE_HIGH = 36864;
    private static final int PROCESS_EVENT_VALUE_LOW = 36945;
    private static final int PROCESS_EVENT_VALUE_HIGH = 37120;
    
    public static String eventType2ModuleName(final int n) {
        String s = "analytics";
        if (n >= 16385 && n <= 20480) {
            s = "push";
        }
        if (n >= 24577 && n <= 28672) {
            s = "share";
        }
        if (n >= 32769 && n <= 36864) {
            s = "internal";
        }
        if (n >= 36945 && n <= 37120) {
            s = "process";
        }
        return s;
    }
    
    public static boolean registerCallback(final int n, final UMLogDataProtocol value) {
        if (UMModuleRegister.mModuleMap == null) {
            UMModuleRegister.mModuleMap = new HashMap<String, UMLogDataProtocol>();
        }
        final String eventType2ModuleName = eventType2ModuleName(n);
        if (!UMModuleRegister.mModuleMap.containsKey(eventType2ModuleName)) {
            UMModuleRegister.mModuleMap.put(eventType2ModuleName, value);
        }
        return true;
    }
    
    public static void registerAppContext(final Context context) {
        if (UMModuleRegister.mModuleAppContext == null) {
            UMModuleRegister.mModuleAppContext = context.getApplicationContext();
        }
    }
    
    public static UMLogDataProtocol getCallbackFromModuleName(final String s) {
        if (UMModuleRegister.mModuleMap.containsKey(s)) {
            return UMModuleRegister.mModuleMap.get(s);
        }
        return null;
    }
    
    public static Context getAppContext() {
        return UMModuleRegister.mModuleAppContext;
    }
    
    static {
        UMModuleRegister.mModuleMap = null;
        UMModuleRegister.mModuleAppContext = null;
    }
}
