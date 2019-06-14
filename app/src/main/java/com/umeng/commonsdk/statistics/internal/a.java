package com.umeng.commonsdk.statistics.internal;

import android.content.*;
import android.text.*;
import android.os.*;
import com.umeng.commonsdk.utils.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.internal.crash.*;

public class a
{
    private static Context a;
    private String b;
    private String c;
    
    private a() {
        this.b = null;
        this.c = null;
    }
    
    public static a a(final Context context) {
        if (com.umeng.commonsdk.statistics.internal.a.a == null && context != null) {
            com.umeng.commonsdk.statistics.internal.a.a = context.getApplicationContext();
        }
        return com.umeng.commonsdk.statistics.internal.a.InnerClass_a.a;
    }
    
    public boolean a(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.startsWith("InnerClass_a");
    }
    
    public boolean b(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.startsWith("t");
    }
    
    public void c(String substring) {
        substring = substring.substring(0, substring.indexOf(95));
        this.e(substring);
        this.d(substring);
    }
    
    private void d(final String s) {
        try {
            final String replaceAll = s.replaceAll("&=", " ").replaceAll("&&", " ").replaceAll("==", "/");
            final StringBuilder sb = new StringBuilder();
            sb.append(replaceAll).append("/").append("Android").append("/").append(Build.DISPLAY).append("/").append(Build.MODEL).append("/").append(Build.VERSION.RELEASE).append(" ").append(HelperUtils.getUmengMD5(UMUtils.getAppkey(com.umeng.commonsdk.statistics.internal.a.a)));
            this.b = sb.toString();
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(com.umeng.commonsdk.statistics.internal.a.a, t);
        }
    }
    
    private void e(final String s) {
        try {
            final String s2 = s.split("&&")[0];
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                final String[] split = s2.split("&=");
                final StringBuilder sb = new StringBuilder();
                sb.append("ut/");
                for (int i = 0; i < split.length; ++i) {
                    final String s3 = split[i];
                    if (!TextUtils.isEmpty((CharSequence)s3)) {
                        String str = s3.substring(0, 2);
                        if (str.endsWith("=")) {
                            str = str.replace("=", "");
                        }
                        sb.append(str);
                    }
                }
                this.c = sb.toString();
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(com.umeng.commonsdk.statistics.internal.a.a, t);
        }
    }
    
    public String a() {
        return this.c;
    }
    
    public String b() {
        return this.b;
    }
    
    static {
        com.umeng.commonsdk.statistics.internal.a.a = null;
    }
    
    private static class InnerClass_a
    {
        private static final com.umeng.commonsdk.statistics.internal.a a;
        
        static {
            a = new com.umeng.commonsdk.statistics.internal.a();
        }
    }
}
