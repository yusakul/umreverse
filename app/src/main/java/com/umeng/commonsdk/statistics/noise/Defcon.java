package com.umeng.commonsdk.statistics.noise;

import com.umeng.commonsdk.statistics.internal.*;
import android.content.*;
import com.umeng.commonsdk.framework.*;
import com.umeng.commonsdk.statistics.idtracking.*;

public class Defcon implements com.umeng.commonsdk.statistics.internal.d
{
    private static final int LEVEL_0 = 0;
    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;
    private static final long MILLIS_4_HOURS = 14400000L;
    private static final long MILLIS_8_HOURS = 28800000L;
    private static final long MILLIS_24_HOURS = 86400000L;
    private int mLevel;
    private static Defcon instanse;
    
    public static synchronized Defcon getService(final Context context) {
        if (Defcon.instanse == null) {
            (Defcon.instanse = new Defcon()).setLevel(Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "defcon", "0")));
        }
        return Defcon.instanse;
    }
    
    private Defcon() {
        this.mLevel = 0;
    }
    
    public int getLevel() {
        return this.mLevel;
    }
    
    public long getReqInterval() {
        switch (this.mLevel) {
            case 1: {
                return 14400000L;
            }
            case 2: {
                return 28800000L;
            }
            case 3: {
                return 86400000L;
            }
            default: {
                return 0L;
            }
        }
    }
    
    public long getRetryInterval() {
        return (this.mLevel == 0) ? 0L : 300000L;
    }
    
    public void setLevel(final int mLevel) {
        if (mLevel >= 0 && mLevel <= 3) {
            this.mLevel = mLevel;
        }
    }
    
    public boolean isOpen() {
        return this.mLevel != 0;
    }
    
    @Override
    public void onImprintChanged(final ImprintHandler.a a) {
        this.setLevel(Integer.valueOf(a.a("defcon", String.valueOf(0))));
    }
    
    static {
        Defcon.instanse = null;
    }
}
