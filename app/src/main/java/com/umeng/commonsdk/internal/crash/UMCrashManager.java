package com.umeng.commonsdk.internal.crash;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import android.text.*;
import android.util.*;
import com.umeng.commonsdk.stateless.*;
import org.json.*;

public class UMCrashManager
{
    private static boolean isReportCrash;
    private static Object mObject;
    
    public static void reportCrash(final Context context, final Throwable t) {
        if (!UMCrashManager.isReportCrash) {
            ULog.i("walle-crash", "report is " + UMCrashManager.isReportCrash);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (UMCrashManager.mObject) {
                            if (context != null && t != null && !UMCrashManager.isReportCrash) {
                                UMCrashManager.isReportCrash = true;
                                ULog.i("walle-crash", "report thread is " + UMCrashManager.isReportCrash);
                                final String a = com.umeng.commonsdk.internal.crash.a.a(t);
                                if (!TextUtils.isEmpty((CharSequence)a)) {
                                    f.a(context, context.getFilesDir() + "/" + "stateless" + "/" + Base64.encodeToString("umpx_internal".getBytes(), 0), 10);
                                    final UMSLEnvelopeBuild umslEnvelopeBuild = new UMSLEnvelopeBuild();
                                    final JSONObject buildSLBaseHeader = umslEnvelopeBuild.buildSLBaseHeader(context);
                                    try {
                                        final JSONObject jsonObject = new JSONObject();
                                        jsonObject.put("content", (Object)a);
                                        jsonObject.put("ts", System.currentTimeMillis());
                                        final JSONObject jsonObject2 = new JSONObject();
                                        jsonObject2.put("crash", (Object)jsonObject);
                                        final JSONObject jsonObject3 = new JSONObject();
                                        jsonObject3.put("tp", (Object)jsonObject2);
                                        final JSONObject buildSLEnvelope = umslEnvelopeBuild.buildSLEnvelope(context, buildSLBaseHeader, jsonObject3, "umpx_internal");
                                        if (buildSLEnvelope == null || !buildSLEnvelope.has("exception")) {}
                                    }
                                    catch (JSONException ex) {}
                                }
                            }
                        }
                    }
                    catch (Throwable t) {}
                }
            }).start();
        }
    }
    
    static {
        UMCrashManager.isReportCrash = false;
        UMCrashManager.mObject = new Object();
    }
}
