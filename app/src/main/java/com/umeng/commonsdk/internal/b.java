package com.umeng.commonsdk.internal;

import android.content.*;

public class b
{
    private Context a;
    private static b b;
    private c c;
    
    private b(final Context a) {
        this.a = a;
        this.c = new c(a);
    }
    
    public static synchronized b a(final Context context) {
        if (com.umeng.commonsdk.internal.b.b == null) {
            com.umeng.commonsdk.internal.b.b = new b(context.getApplicationContext());
        }
        return com.umeng.commonsdk.internal.b.b;
    }
    
    public c a() {
        return this.c;
    }
}
