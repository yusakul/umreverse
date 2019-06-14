package com.umeng.commonsdk.service;

import android.content.*;
import com.umeng.commonsdk.framework.*;
import com.umeng.commonsdk.utils.*;

public class UMGlobalContext
{
    private static final String TAG = "UMGlobalContext";
    private Context mApplicationContext;
    private int mDeviceType;
    private String mPushSecret;
    private String mAppkey;
    private String mChannel;
    private String mModules;
    private boolean mIsDebugMode;
    private String mProcessName;
    private String mAppVersion;
    private boolean mIsMainProcess;
    
    private UMGlobalContext() {
        this.mProcessName = "unknown";
    }
    
    public static UMGlobalContext newUMGlobalContext(final a a) {
        getInstance();
        b.a.mDeviceType = a.b;
        b.a.mPushSecret = a.c;
        b.a.mAppkey = a.d;
        b.a.mChannel = a.e;
        b.a.mModules = a.f;
        b.a.mIsDebugMode = a.g;
        b.a.mProcessName = a.h;
        b.a.mAppVersion = a.i;
        b.a.mIsMainProcess = a.j;
        if (a.a != null) {
            b.a.mApplicationContext = a.a.getApplicationContext();
        }
        return b.a;
    }
    
    public static UMGlobalContext getInstance() {
        return b.a;
    }
    
    public static Context getAppContext(final Context context) {
        if (context != null) {
            final Context mApplicationContext = b.a.mApplicationContext;
            return (mApplicationContext != null) ? mApplicationContext : context.getApplicationContext();
        }
        return b.a.mApplicationContext;
    }
    
    public Context getAppContextDirectly() {
        return this.mApplicationContext;
    }
    
    public int getDeviceType() {
        return this.mDeviceType;
    }
    
    public String getPushSecret() {
        return this.mPushSecret;
    }
    
    public String getAppkey() {
        return this.mAppkey;
    }
    
    public String getChannel() {
        return this.mChannel;
    }
    
    public boolean hasVisualSdk() {
        return this.mModules.contains("v");
    }
    
    public boolean hasVisualDebugSdk() {
        return this.mModules.contains("x");
    }
    
    public boolean hasAnalyticsSdk() {
        return this.mModules.contains("InnerClass_a");
    }
    
    public boolean hasPushSdk() {
        return this.mModules.contains("p");
    }
    
    public boolean hasShareSdk() {
        return this.mModules.contains("s");
    }
    
    public boolean hasErrorSdk() {
        return this.mModules.contains("e_enum");
    }
    
    public boolean hasOplusModule() {
        return this.mModules.contains("o");
    }
    
    public boolean hasInternalModule() {
        return true;
    }
    
    public boolean isDebugMode() {
        return this.mIsDebugMode;
    }
    
    public String getProcessName(final Context context) {
        if (context == null) {
            return b.a.mProcessName;
        }
        if (b.a.mApplicationContext != null) {
            return this.mProcessName;
        }
        return UMFrUtils.getCurrentProcessName(context);
    }
    
    public String getAppVersion() {
        return this.mAppVersion;
    }
    
    public boolean isMainProcess(final Context context) {
        if (context == null) {
            return b.a.mIsMainProcess;
        }
        if (b.a.mApplicationContext != null) {
            return b.a.mIsMainProcess;
        }
        return UMUtils.isMainProgress(context.getApplicationContext());
    }
    
    @Override
    public String toString() {
        if (b.a.mApplicationContext != null) {
            final StringBuilder sb = new StringBuilder("[");
            sb.append("devType:" + this.mDeviceType + ",");
            sb.append("appkey:" + this.mAppkey + ",");
            sb.append("channel:" + this.mChannel + ",");
            sb.append("procName:" + this.mProcessName + "]");
            return sb.toString();
        }
        return "uninitialized.";
    }
    
    public static class a
    {
        public Context a;
        public int b;
        public String c;
        public String d;
        public String e;
        public String f;
        public boolean g;
        public String h;
        public String i;
        public boolean j;
    }
    
    private static class b
    {
        private static final UMGlobalContext a;
        
        static {
            a = new UMGlobalContext();
        }
    }
}
