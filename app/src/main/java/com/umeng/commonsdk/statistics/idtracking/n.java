package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;

public class n extends a
{
    private static final String a = "umtt3";
    private Context b;
    
    public n(final Context b) {
        super("umtt3");
        this.b = b;
    }
    
    @Override
    public String f() {
        String s = null;
        try {
            final Class<?> forName = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (forName != null) {
                s = (String)forName.getMethod("getUmtt3", Context.class).invoke(forName, this.b);
            }
        }
        catch (ClassNotFoundException ex) {}
        catch (Throwable t) {}
        return s;
    }
}
