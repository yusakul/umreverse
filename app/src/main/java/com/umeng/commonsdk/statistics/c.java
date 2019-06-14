package com.umeng.commonsdk.statistics;

import com.umeng.commonsdk.statistics.idtracking.*;
import com.umeng.commonsdk.statistics.internal.d;
import com.umeng.commonsdk.statistics.noise.*;
import android.text.*;
import com.umeng.commonsdk.statistics.internal.*;
import android.content.*;
import java.io.*;
import com.umeng.commonsdk.framework.*;
import com.umeng.commonsdk.internal.crash.*;
import com.umeng.commonsdk.statistics.proto.*;
import com.umeng.commonsdk.proguard.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.debug.*;

public class c
{
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private final int e = 1;
    private com.umeng.commonsdk.statistics.internal.c f;
    private ImprintHandler g;
    private com.umeng.commonsdk.statistics.idtracking.e h;
    private ImprintHandler.a i;
    private ABTest j;
    private Defcon k;
    private long l;
    private int m;
    private int n;
    String a;
    private static final String o = "thtstart";
    private static final String p = "gkvc";
    private static final String q = "ekvc";
    private Context r;
    private ReportPolicy.ReportStrategy s;

    public c(Context context) {
        this.r = context;
        this.i = ImprintHandler.getImprintService(this.r).c();
        this.k = Defcon.getService(this.r);
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.r);
        this.l = sharedPreferences.getLong(o, 0);
        this.m = sharedPreferences.getInt(p, 0);
        this.n = sharedPreferences.getInt(q, 0);
        this.a = UMEnvelopeBuild.imprintProperty(this.r, "track_list", null);
        this.g = com.umeng.commonsdk.statistics.idtracking.ImprintHandler.getImprintService(this.r);
        this.g.a(new com.umeng.commonsdk.statistics.internal.d() {
            public void onImprintChanged(ImprintHandler.a aVar) {
                c.this.k.onImprintChanged(aVar);
                c cVar = c.this;
                cVar.a = UMEnvelopeBuild.imprintProperty(cVar.r, "track_list", null);
                try {
                    if (!TextUtils.isEmpty(com.umeng.commonsdk.framework.a.a(c.this.r, com.umeng.commonsdk.proguard.e.e, null))) {
                        try {
                            Class cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                            if (cls != null) {
                                cls.getMethod("updateUMTT", new Class[]{Context.class, String.class}).invoke(cls, new Object[]{c.this.r, aVar});
                            }
                        } catch (Throwable th) {
                        }
                    }
                } catch (Throwable th2) {
                }
            }
        });
        this.h = com.umeng.commonsdk.statistics.idtracking.e.a(this.r);
        this.f = new com.umeng.commonsdk.statistics.internal.c(this.r);
        this.f.a(StatTracer.getInstance(this.r));
    }
    
    public boolean a(final File file) {
        try {
            if (file == null) {
                return false;
            }
            final byte[] byteArray = UMFrUtils.toByteArray(file.getPath());
            if (byteArray == null) {
                return false;
            }
            com.umeng.commonsdk.statistics.internal.a.a(this.r).c(file.getName());
            final boolean a = com.umeng.commonsdk.statistics.internal.a.a(this.r).a(file.getName());
            final boolean b = com.umeng.commonsdk.statistics.internal.a.a(this.r).b(file.getName());
            final byte[] a2 = this.f.a(byteArray, a);
            int a3;
            if (a2 == null) {
                a3 = 1;
            }
            else {
                a3 = this.a(a2, a || b);
            }
            switch (a3) {
                case 2: {
                    this.h.d();
                    StatTracer.getInstance(this.r).saveSate();
                    break;
                }
                case 3: {
                    StatTracer.getInstance(this.r).saveSate();
                }
            }
            return a3 == 2;
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(this.r, t);
            return false;
        }
    }
    
    private int a(final byte[] array, final boolean b) {
        final Response response = new Response();
        final com.umeng.commonsdk.proguard.m m = new com.umeng.commonsdk.proguard.m(new ab.a());
        try {
            m.a(response, array);
            if (response.resp_code == 1) {
                this.g.b(response.getImprint());
                this.g.d();
            }
            if (b) {
                MLog.i("send log:" + response.getMsg());
            }
            else {
                MLog.i("inner req:" + response.getMsg());
            }
            if (b) {
                UMRTLog.i("MobclickRT", "send log: " + response.getMsg());
            }
            else {
                UMRTLog.i("MobclickRT", "inner req: " + response.getMsg());
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(this.r, t);
        }
        if (response.resp_code == 1) {
            return 2;
        }
        return 3;
    }
}
