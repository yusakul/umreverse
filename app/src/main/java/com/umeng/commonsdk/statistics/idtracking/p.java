package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;

public class p extends a
{
    private static final String a = "umtt0";
    private Context b;
    
    public p(final Context b) {
        super("umtt0");
        this.b = b;
    }
    
    @Override
    public String f() {
        String s = null;
        try {
            final Class<?> forName = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (forName != null) {
                s = (String)forName.getMethod("getUmtt0", Context.class).invoke(forName, this.b);
            }
        }
        catch (ClassNotFoundException ex) {}
        catch (Throwable t) {}
        return s;
    }
}
