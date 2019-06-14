package com.umeng.commonsdk;

import android.content.*;
import com.umeng.commonsdk.framework.*;
import android.text.*;
import android.util.*;
import com.umeng.commonsdk.stateless.*;
import com.umeng.commonsdk.internal.crash.*;
import org.json.*;
import com.umeng.commonsdk.proguard.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.internal.*;
import com.umeng.commonsdk.internal.utils.*;

public class UMConfigureImpl
{
    private static boolean a;
    private static boolean b;
    
    public static void init(Context applicationContext) {
        if (applicationContext == null) {
            return;
        }
        applicationContext = applicationContext.getApplicationContext();
        b(applicationContext);
        a(applicationContext);
    }
    
    private static synchronized void a(final Context context) {
        try {
            if (context != null && !UMConfigureImpl.b) {
                final String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                final String packageName = context.getPackageName();
                if (!TextUtils.isEmpty((CharSequence)currentProcessName) && !TextUtils.isEmpty((CharSequence)packageName) && currentProcessName.equals(packageName)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final JSONArray b = com.umeng.commonsdk.proguard.c.b(context);
                                if (b != null && b.length() > 0) {
                                    com.umeng.commonsdk.stateless.f.a(context, context.getFilesDir() + "/" + "stateless" + "/" + Base64.encodeToString("umpx_oplus_lbs".getBytes(), 0), 10);
                                    final JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("lbs", (Object)b);
                                    final JSONObject jsonObject2 = new JSONObject();
                                    jsonObject2.put("tp", (Object)jsonObject);
                                    final UMSLEnvelopeBuild umslEnvelopeBuild = new UMSLEnvelopeBuild();
                                    umslEnvelopeBuild.buildSLEnvelope(context, umslEnvelopeBuild.buildSLBaseHeader(context), jsonObject2, "umpx_oplus_lbs");
                                }
                            }
                            catch (Exception ex) {
                                UMCrashManager.reportCrash(context, ex);
                            }
                        }
                    }).start();
                }
                UMConfigureImpl.b = true;
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(context, t);
        }
    }
    
    private static synchronized void b(final Context context) {
        try {
            if (context != null && !UMConfigureImpl.a) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                            final String packageName = context.getPackageName();
                            if (!TextUtils.isEmpty((CharSequence)currentProcessName) && !TextUtils.isEmpty((CharSequence)packageName) && currentProcessName.equals(packageName)) {
                                try {
                                    com.umeng.commonsdk.proguard.a.a(context);
                                }
                                catch (Throwable obj) {
                                    ULog.e("internal", "e is " + obj);
                                }
                                try {
                                    com.umeng.commonsdk.proguard.c.a(context);
                                }
                                catch (Throwable obj2) {
                                    ULog.e("internal", "e is " + obj2);
                                }
                                try {
                                    if (!com.umeng.commonsdk.internal.utils.c.a(context).a()) {
                                        com.umeng.commonsdk.internal.utils.c.a(context).b();
                                    }
                                }
                                catch (Throwable obj3) {
                                    ULog.e("internal", "e is " + obj3);
                                }
                                try {
                                    com.umeng.commonsdk.internal.utils.l.b(context);
                                }
                                catch (Throwable obj4) {
                                    ULog.e("internal", "e is " + obj4);
                                }
                                try {
                                    com.umeng.commonsdk.internal.utils.a.n(context);
                                }
                                catch (Throwable obj5) {
                                    ULog.e("internal", "e is " + obj5);
                                }
                                try {
                                    com.umeng.commonsdk.internal.utils.a.d(context);
                                }
                                catch (Throwable obj6) {
                                    ULog.e("internal", "e is " + obj6);
                                }
                                try {
                                    com.umeng.commonsdk.internal.utils.j.b(context);
                                }
                                catch (Throwable obj7) {
                                    ULog.e("internal", "e is " + obj7);
                                }
                                try {
                                    com.umeng.commonsdk.internal.d.b(context);
                                }
                                catch (Throwable obj8) {
                                    ULog.e("internal", "e is " + obj8);
                                }
                                try {
                                    com.umeng.commonsdk.internal.d.c(context);
                                }
                                catch (Throwable t2) {}
                            }
                        }
                        catch (Throwable t) {
                            UMCrashManager.reportCrash(context, t);
                        }
                    }
                }).start();
                try {
                    if (!com.umeng.commonsdk.internal.utils.b.a(context).a()) {
                        com.umeng.commonsdk.internal.utils.b.a(context).b();
                    }
                }
                catch (Throwable t2) {
                    ULog.e("internal", "get station is null ");
                }
                UMConfigureImpl.a = true;
            }
        }
        catch (Throwable t) {
            ULog.e("internal", "e is " + t.getMessage());
            UMCrashManager.reportCrash(context, t);
        }
    }
    
    static {
        UMConfigureImpl.a = false;
        UMConfigureImpl.b = false;
    }
}
