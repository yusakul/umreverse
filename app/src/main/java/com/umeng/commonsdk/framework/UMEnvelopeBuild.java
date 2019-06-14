package com.umeng.commonsdk.framework;

import android.content.*;
import org.json.*;

public class UMEnvelopeBuild
{
    public static boolean isOnline(final Context context) {
        return UMFrUtils.isOnline(context);
    }
    
    public static boolean isReadyBuild(final Context context, final UMLogDataProtocol.UMBusinessType umBusinessType) {
        return a.a(context, umBusinessType);
    }
    
    public static JSONObject buildEnvelopeWithExtHeader(final Context context, final JSONObject jsonObject, final JSONObject jsonObject2) {
        return a.a(context, jsonObject, jsonObject2);
    }
    
    public static String imprintProperty(final Context context, final String s, final String s2) {
        return a.a(context, s, s2);
    }
    
    public static long maxDataSpace(final Context context) {
        return a.c(context);
    }
    
    public static long lastSuccessfulBuildTime(final Context context) {
        return a.a(context);
    }
    
    public static long lastInstantBuildTime(final Context context) {
        return a.b(context);
    }
}
