package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;

public class c extends a
{
    private static final String a = "idfa";
    private Context b;
    
    public c(final Context b) {
        super("idfa");
        this.b = b;
    }
    
    @Override
    public String f() {
        final String a = com.umeng.commonsdk.statistics.common.a.a(this.b);
        return (a == null) ? "" : a;
    }
}
