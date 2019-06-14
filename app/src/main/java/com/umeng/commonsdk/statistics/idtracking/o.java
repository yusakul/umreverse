package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;

public class o extends a
{
    private static final String a = "umtt2";
    private Context b;
    
    public o(final Context b) {
        super("umtt2");
        this.b = b;
    }
    
    @Override
    public String f() {
        String s = null;
        try {
            final Class<?> forName = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (forName != null) {
                s = (String)forName.getMethod("getUmtt2", Context.class).invoke(forName, this.b);
            }
        }
        catch (ClassNotFoundException ex) {}
        catch (Throwable t) {}
        return s;
    }
}
