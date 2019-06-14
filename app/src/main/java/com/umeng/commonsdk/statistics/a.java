package com.umeng.commonsdk.statistics;

import android.content.*;
import android.text.*;
import com.umeng.commonsdk.statistics.common.*;

public class a
{
    public static String a;
    public static String b;
    private static String d;
    public static int c;
    
    public static String a(final Context context) {
        if (TextUtils.isEmpty((CharSequence)com.umeng.commonsdk.statistics.a.d)) {
            com.umeng.commonsdk.statistics.a.d = com.umeng.commonsdk.statistics.common.d.a(context).b();
        }
        return com.umeng.commonsdk.statistics.a.d;
    }
    
    static {
        com.umeng.commonsdk.statistics.a.a = "native";
        com.umeng.commonsdk.statistics.a.b = "";
        com.umeng.commonsdk.statistics.a.d = null;
    }
}
