package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;
import com.umeng.commonsdk.framework.*;

public class h extends a
{
    private static final String a = "newumid";
    private Context b;
    
    public h(final Context b) {
        super("newumid");
        this.b = b;
    }
    
    @Override
    public String f() {
        return UMEnvelopeBuild.imprintProperty(this.b, "umid", null);
    }
}
