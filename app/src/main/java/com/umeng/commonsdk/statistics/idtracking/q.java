package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.internal.*;
import android.content.*;

public class q extends a
{
    private static final String b = "uop";
    private Context c;
    public static final String a = "uopdta";
    
    public q(final Context c) {
        super("uop");
        this.c = c;
    }
    
    @Override
    public String f() {
        String string = "";
        final SharedPreferences default1 = PreferenceWrapper.getDefault(this.c);
        if (default1 != null) {
            string = default1.getString("uopdta", "");
        }
        return string;
    }
}
