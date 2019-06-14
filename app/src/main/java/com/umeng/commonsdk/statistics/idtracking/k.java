package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;

public class k extends a
{
    private static final String a = "umtt5";
    private Context b;
    
    public k(final Context b) {
        super("umtt5");
        this.b = b;
    }
    
    @Override
    public String f() {
        String s = null;
        try {
            final Class<?> forName = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (forName != null) {
                s = (String)forName.getMethod("getUmtt5", Context.class).invoke(forName, this.b);
            }
        }
        catch (ClassNotFoundException ex) {}
        catch (Throwable t) {}
        return s;
    }
}
