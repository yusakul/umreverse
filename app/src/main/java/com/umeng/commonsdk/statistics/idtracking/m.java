package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;

public class m extends a
{
    private static final String a = "umtt1";
    private Context b;
    
    public m(final Context b) {
        super("umtt1");
        this.b = b;
    }
    
    @Override
    public String f() {
        String s = null;
        try {
            final Class<?> forName = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (forName != null) {
                s = (String)forName.getMethod("getUmtt1", Context.class).invoke(forName, this.b);
            }
        }
        catch (ClassNotFoundException ex) {}
        catch (Throwable t) {}
        return s;
    }
}
