package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;

public class l extends a
{
    private static final String a = "umtt4";
    private Context b;
    
    public l(final Context b) {
        super("umtt4");
        this.b = b;
    }
    
    @Override
    public String f() {
        String s = null;
        try {
            final Class<?> forName = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (forName != null) {
                s = (String)forName.getMethod("getUmtt4", Context.class).invoke(forName, this.b);
            }
        }
        catch (ClassNotFoundException ex) {}
        catch (Throwable t) {}
        return s;
    }
}
