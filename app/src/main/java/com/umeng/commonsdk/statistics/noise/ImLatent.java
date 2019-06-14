package com.umeng.commonsdk.statistics.noise;

import com.umeng.commonsdk.statistics.internal.*;
import android.content.*;
import com.umeng.commonsdk.statistics.idtracking.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.statistics.*;

public class ImLatent implements com.umeng.commonsdk.statistics.internal.d
{
    private final int _DEFAULT_HOURS = 360;
    private final int _DEFAULT_MIN_HOURS = 36;
    private final int _DEFAULT_MIN_LATENT = 1;
    private final int _DEFAULT_MAX_LATENT = 1800;
    private final long _ONE_HOURS_IN_MS = 3600000L;
    private final long _360HOURS_IN_MS = 1296000000L;
    private final long _36HOURS_IN_MS = 129600000L;
    private final int LATENT_MAX = 1800000;
    private final int LATENT_WINDOW = 10;
    private com.umeng.commonsdk.statistics.common.d storeHelper;
    private StatTracer statTracer;
    private long latentHour;
    private int latentWindow;
    private long mDelay;
    private long mElapsed;
    private boolean mLatentActivite;
    private Object mLatentLock;
    private Context context;
    private static ImLatent instanse;
    
    public static synchronized ImLatent getService(final Context context, final StatTracer statTracer) {
        if (ImLatent.instanse == null) {
            (ImLatent.instanse = new ImLatent(context, statTracer)).onImprintChanged(ImprintHandler.getImprintService(context).c());
        }
        return ImLatent.instanse;
    }
    
    private ImLatent(final Context context, final StatTracer statTracer) {
        this.latentHour = 1296000000L;
        this.latentWindow = 10;
        this.mDelay = 0L;
        this.mElapsed = 0L;
        this.mLatentActivite = false;
        this.mLatentLock = new Object();
        this.context = context;
        this.storeHelper = com.umeng.commonsdk.statistics.common.d.a(context);
        this.statTracer = statTracer;
    }
    
    public boolean shouldStartLatency() {
        if (this.storeHelper.c()) {
            return false;
        }
        if (this.statTracer.isFirstRequest()) {
            return false;
        }
        synchronized (this.mLatentLock) {
            if (this.mLatentActivite) {
                return false;
            }
        }
        final long n = System.currentTimeMillis() - this.statTracer.getLastReqTime();
        if (n > this.latentHour) {
            final String signature = Envelope.getSignature(this.context);
            synchronized (this.mLatentLock) {
                this.mDelay = DataHelper.random(this.latentWindow, signature);
                this.mElapsed = n;
                this.mLatentActivite = true;
            }
            return true;
        }
        if (n > 129600000L) {
            synchronized (this.mLatentLock) {
                this.mDelay = 0L;
                this.mElapsed = n;
                this.mLatentActivite = true;
            }
            return true;
        }
        return false;
    }
    
    public boolean isLatentActivite() {
        synchronized (this.mLatentLock) {
            return this.mLatentActivite;
        }
    }
    
    public void latentDeactivite() {
        synchronized (this.mLatentLock) {
            this.mLatentActivite = false;
        }
    }
    
    public long getDelayTime() {
        synchronized (this.mLatentLock) {
            return this.mDelay;
        }
    }
    
    public long getElapsedTime() {
        return this.mElapsed;
    }
    
    @Override
    public void onImprintChanged(final ImprintHandler.a a) {
        int intValue = Integer.valueOf(a.a("latent_hours", String.valueOf(360)));
        if (intValue <= 36) {
            intValue = 360;
        }
        this.latentHour = intValue * 3600000L;
        int intValue2 = Integer.valueOf(a.a("latent", "0"));
        if (intValue2 < 1 || intValue2 > 1800) {
            intValue2 = 0;
        }
        if (intValue2 == 0) {
            if (com.umeng.commonsdk.statistics.a.c <= 0 || com.umeng.commonsdk.statistics.a.c > 1800000) {
                this.latentWindow = 10;
            }
            else {
                this.latentWindow = com.umeng.commonsdk.statistics.a.c;
            }
        }
        else {
            this.latentWindow = intValue2;
        }
    }
    
    static {
        ImLatent.instanse = null;
    }
}
