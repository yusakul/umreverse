package com.umeng.commonsdk.statistics.internal;

import android.content.*;

public class PreferenceWrapper
{
    private static final String DEFAULT_PREFERENCE = "umeng_general_config";
    
    private PreferenceWrapper() {
    }
    
    public static SharedPreferences getInstance(final Context context, final String s) {
        if (context != null) {
            return context.getSharedPreferences(s, 0);
        }
        return null;
    }
    
    public static SharedPreferences getDefault(final Context context) {
        if (context != null) {
            return context.getSharedPreferences("umeng_general_config", 0);
        }
        return null;
    }
}
