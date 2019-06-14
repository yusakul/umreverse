package com.umeng.commonsdk.framework;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.service.*;
import org.json.*;
import com.umeng.commonsdk.statistics.idtracking.*;

public class a
{
    public static long a(final Context context) {
        if (context == null) {
            return 0L;
        }
        return UMFrUtils.getLastSuccessfulBuildTime(context.getApplicationContext());
    }
    
    public static long b(final Context context) {
        if (context == null) {
            return 0L;
        }
        return UMFrUtils.getLastInstantBuildTime(context.getApplicationContext());
    }
    
    public static boolean a(final Context context, final UMLogDataProtocol.UMBusinessType umBusinessType) {
        boolean b = false;
        if (context != null) {
            final Context applicationContext = context.getApplicationContext();
            final boolean online = UMFrUtils.isOnline(applicationContext);
            final int envelopeFileNumber = UMFrUtils.envelopeFileNumber(applicationContext);
            if (online) {
                if (umBusinessType == UMLogDataProtocol.UMBusinessType.U_INTERNAL) {
                    b = true;
                    if (UMFrUtils.hasEnvelopeFile(applicationContext, umBusinessType)) {
                        b = false;
                    }
                }
                else if (com.umeng.commonsdk.framework.b.a()) {
                    c.a((long)com.umeng.commonsdk.framework.b.b());
                    b = false;
                }
                else {
                    b = true;
                    if (UMFrUtils.hasEnvelopeFile(applicationContext, umBusinessType)) {
                        b = false;
                    }
                }
            }
            if (online && envelopeFileNumber > 0) {
                com.umeng.commonsdk.framework.b.d();
            }
        }
        return b;
    }
    
    public static JSONObject a(final Context context, final JSONObject jsonObject, final JSONObject jsonObject2) {
        ULog.d("--->>> buildEnvelopeFile Enter.");
        if (!UMGlobalContext.getInstance().isMainProcess(context)) {
            JSONObject jsonObject3 = null;
            try {
                jsonObject3 = new JSONObject();
                jsonObject3.put("exception", 101);
            }
            catch (JSONException ex) {
                ex.printStackTrace();
            }
            return jsonObject3;
        }
        return new com.umeng.commonsdk.statistics.b().a(context.getApplicationContext(), jsonObject, jsonObject2);
    }
    
    public static String a(final Context context, final String s, final String s2) {
        if (context == null) {
            return s2;
        }
        return ImprintHandler.getImprintService(context.getApplicationContext()).c().a(s, s2);
    }
    
    public static long c(final Context context) {
        if (context == null) {
            return 0L;
        }
        return com.umeng.commonsdk.statistics.b.a(context.getApplicationContext());
    }
}
