package com.umeng.commonsdk.internal;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.internal.crash.*;
import com.umeng.commonsdk.framework.*;
import com.umeng.commonsdk.stateless.*;
import android.text.*;
import org.json.*;
import android.app.*;
import android.content.pm.*;
import com.umeng.commonsdk.internal.utils.*;
import com.umeng.commonsdk.statistics.idtracking.*;
import com.umeng.commonsdk.proguard.*;
import android.os.*;
import android.util.*;
import android.net.wifi.*;
import java.util.*;

import android.util.Base64;
import android.view.inputmethod.*;

public class d
{
    public static void a(final Context context) {
        try {
            ULog.i("walle", "[internal] workEvent send envelope");
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("i_sdk_v", (Object)"1.2.0");
            final JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(context, jsonObject, e(context));
            if (buildEnvelopeWithExtHeader != null && !buildEnvelopeWithExtHeader.has("exception")) {
                ULog.i("walle", "[internal] workEvent send envelope back, result is ok");
                com.umeng.commonsdk.internal.utils.a.f(context);
                com.umeng.commonsdk.internal.utils.j.d(context);
                com.umeng.commonsdk.proguard.c.c(context);
            }
        }
        catch (Exception ex) {
            UMCrashManager.reportCrash(context, ex);
        }
    }
    
    public static void b(final Context context) {
        ULog.i("walle", "[internal] begin by stateful--->>>");
        if (context != null) {
            try {
                if (UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                    UMWorkDispatch.sendEvent(context, 32769, b.a(context).a(), null);
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(context, t);
            }
        }
    }
    
    public static void c(Context applicationContext) {
        if (applicationContext != null) {
            try {
                ULog.i("walle", "[internal] begin by not stateful--->>>");
                applicationContext = applicationContext.getApplicationContext();
                com.umeng.commonsdk.stateless.f.a(applicationContext, applicationContext.getFilesDir() + "/" + "stateless" + "/" + Base64.encodeToString("umpx_internal".getBytes(), 0), 10);
                final UMSLEnvelopeBuild umslEnvelopeBuild = new UMSLEnvelopeBuild();
                final JSONObject buildSLBaseHeader = umslEnvelopeBuild.buildSLBaseHeader(applicationContext);
                if (buildSLBaseHeader != null && buildSLBaseHeader.has("header")) {
                    try {
                        final JSONObject jsonObject = (JSONObject)buildSLBaseHeader.opt("header");
                        if (jsonObject != null) {
                            jsonObject.put("i_sdk_v", (Object)"1.2.0");
                        }
                    }
                    catch (Exception ex) {}
                }
                ULog.i("walle", "[internal] header is " + buildSLBaseHeader.toString());
                final JSONObject d = d(applicationContext);
                ULog.i("walle", "[internal] body is " + d.toString());
                ULog.i("walle", umslEnvelopeBuild.buildSLEnvelope(applicationContext, buildSLBaseHeader, d, "umpx_internal").toString());
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(applicationContext, t);
            }
        }
    }
    
    public static JSONObject d(Context applicationContext) {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            try {
                try {
                    final JSONArray p = p(applicationContext);
                    if (p != null && p.length() > 0) {
                        jsonObject2.put("run_server", (Object)p);
                    }
                }
                catch (Exception ex) {
                    UMCrashManager.reportCrash(applicationContext, ex);
                }
                try {
                    final String k = com.umeng.commonsdk.internal.utils.a.k(applicationContext);
                    if (!TextUtils.isEmpty((CharSequence)k)) {
                        jsonObject2.put("imsi", (Object)k);
                    }
                }
                catch (Exception ex2) {
                    UMCrashManager.reportCrash(applicationContext, ex2);
                }
                try {
                    final String l = com.umeng.commonsdk.internal.utils.a.l(applicationContext);
                    if (!TextUtils.isEmpty((CharSequence)l)) {
                        jsonObject2.put("meid", (Object)l);
                    }
                }
                catch (Exception ex3) {
                    UMCrashManager.reportCrash(applicationContext, ex3);
                }
                try {
                    jsonObject.put("internal", (Object)jsonObject2);
                }
                catch (JSONException ex4) {
                    UMCrashManager.reportCrash(applicationContext, (Throwable)ex4);
                }
            }
            catch (Exception ex5) {}
        }
        return jsonObject;
    }
    
    public static JSONObject e(Context applicationContext) {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            try {
                final JSONArray p = p(applicationContext);
                if (p != null && p.length() > 0) {
                    jsonObject2.put("rs", (Object)p);
                }
            }
            catch (Exception ex) {
                UMCrashManager.reportCrash(applicationContext, ex);
            }
            try {
                final JSONArray q = q(applicationContext);
                if (q != null && q.length() > 0) {
                    jsonObject2.put("bstn", (Object)q);
                }
            }
            catch (Exception ex2) {
                UMCrashManager.reportCrash(applicationContext, ex2);
            }
            try {
                final JSONArray r = r(applicationContext);
                if (r != null && r.length() > 0) {
                    jsonObject2.put("by", (Object)r);
                }
            }
            catch (Exception ex3) {
                UMCrashManager.reportCrash(applicationContext, ex3);
            }
            try {
                a(applicationContext, jsonObject2);
            }
            catch (Exception ex4) {
                UMCrashManager.reportCrash(applicationContext, ex4);
            }
            try {
                b(applicationContext, jsonObject2);
            }
            catch (Exception ex5) {
                UMCrashManager.reportCrash(applicationContext, ex5);
            }
            try {
                final JSONObject a = a();
                if (a != null && a.length() > 0) {
                    jsonObject2.put("sd", (Object)a);
                }
            }
            catch (Exception ex6) {
                UMCrashManager.reportCrash(applicationContext, ex6);
            }
            try {
                final JSONObject b = b();
                if (b != null && b.length() > 0) {
                    jsonObject2.put("build", (Object)b);
                }
            }
            catch (Exception ex7) {
                UMCrashManager.reportCrash(applicationContext, ex7);
            }
            try {
                final JSONObject jsonObject3 = new JSONObject();
                final JSONArray g = g(applicationContext);
                if (g != null && g.length() > 0) {
                    try {
                        jsonObject3.put("a_sr", (Object)g);
                    }
                    catch (JSONException ex19) {}
                }
                final JSONArray c = com.umeng.commonsdk.internal.utils.j.c(applicationContext);
                if (c != null && c.length() > 0) {
                    try {
                        jsonObject3.put("stat", (Object)c);
                    }
                    catch (JSONException ex20) {}
                }
                jsonObject2.put("sr", (Object)jsonObject3);
            }
            catch (Exception ex8) {
                UMCrashManager.reportCrash(applicationContext, ex8);
            }
            try {
                final JSONObject h = h(applicationContext);
                if (h != null && h.length() > 0) {
                    jsonObject2.put("scr", (Object)h);
                }
            }
            catch (Exception ex9) {
                UMCrashManager.reportCrash(applicationContext, ex9);
            }
            try {
                final JSONObject i = i(applicationContext);
                if (i != null && i.length() > 0) {
                    jsonObject2.put("sinfo", (Object)i);
                }
            }
            catch (Exception ex10) {
                UMCrashManager.reportCrash(applicationContext, ex10);
            }
            try {
                final JSONObject jsonObject4 = new JSONObject();
                final JSONArray e = com.umeng.commonsdk.internal.utils.a.e(applicationContext);
                if (e != null && e.length() > 0) {
                    try {
                        jsonObject4.put("wl", (Object)e);
                    }
                    catch (JSONException ex21) {}
                }
                final JSONArray j = j(applicationContext);
                if (j != null && j.length() > 0) {
                    try {
                        jsonObject4.put("a_wls", (Object)j);
                    }
                    catch (JSONException ex22) {}
                }
                jsonObject2.put("winfo", (Object)jsonObject4);
            }
            catch (Exception ex11) {
                UMCrashManager.reportCrash(applicationContext, ex11);
            }
            try {
                final JSONArray k = k(applicationContext);
                if (k != null && k.length() > 0) {
                    jsonObject2.put("input", (Object)k);
                }
            }
            catch (Exception ex12) {
                UMCrashManager.reportCrash(applicationContext, ex12);
            }
            try {
                final JSONObject o = com.umeng.commonsdk.internal.utils.a.o(applicationContext);
                if (o != null && o.length() > 0) {
                    jsonObject2.put("bt", (Object)o);
                }
            }
            catch (Exception ex13) {
                UMCrashManager.reportCrash(applicationContext, ex13);
            }
            try {
                final JSONArray l = l(applicationContext);
                if (l != null && l.length() > 0) {
                    jsonObject2.put("cam", (Object)l);
                }
            }
            catch (Exception ex14) {
                UMCrashManager.reportCrash(applicationContext, ex14);
            }
            try {
                final JSONArray m = m(applicationContext);
                if (m != null && m.length() > 0) {
                    jsonObject2.put("appls", (Object)m);
                }
            }
            catch (Exception ex15) {
                UMCrashManager.reportCrash(applicationContext, ex15);
            }
            try {
                final JSONObject n = n(applicationContext);
                if (n != null && n.length() > 0) {
                    jsonObject2.put("mem", (Object)n);
                }
            }
            catch (Exception ex16) {
                UMCrashManager.reportCrash(applicationContext, ex16);
            }
            try {
                final JSONArray o2 = o(applicationContext);
                if (o2 != null && o2.length() > 0) {
                    jsonObject2.put("lbs", (Object)o2);
                }
            }
            catch (Exception ex17) {
                UMCrashManager.reportCrash(applicationContext, ex17);
            }
            try {
                final JSONObject d = d();
                if (d != null && d.length() > 0) {
                    jsonObject2.put("cpu", (Object)d);
                }
            }
            catch (Exception ex23) {}
            try {
                final JSONObject c2 = c();
                if (c2 != null && c2.length() > 0) {
                    jsonObject2.put("rom", (Object)c2);
                }
            }
            catch (Exception ex24) {}
            try {
                jsonObject.put("inner", (Object)jsonObject2);
            }
            catch (JSONException ex18) {
                UMCrashManager.reportCrash(applicationContext, (Throwable)ex18);
            }
        }
        return jsonObject;
    }
    
    private static JSONObject c() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("tot_s", com.umeng.commonsdk.internal.utils.a.h());
            jsonObject.put("ava_s", com.umeng.commonsdk.internal.utils.a.i());
            jsonObject.put("ts", System.currentTimeMillis());
        }
        catch (Exception ex) {}
        return jsonObject;
    }
    
    private static JSONObject d() {
        JSONObject jsonObject = null;
        try {
            final com.umeng.commonsdk.internal.utils.d.a a = com.umeng.commonsdk.internal.utils.d.a();
            if (a != null) {
                jsonObject = new JSONObject();
                jsonObject.put("pro", (Object)a.a);
                jsonObject.put("pla", (Object)a.b);
                jsonObject.put("cpus", a.c);
                jsonObject.put("fea", (Object)a.d);
                jsonObject.put("imp", (Object)a.e);
                jsonObject.put("arc", (Object)a.f);
                jsonObject.put("var", (Object)a.g);
                jsonObject.put("par", (Object)a.h);
                jsonObject.put("rev", (Object)a.i);
                jsonObject.put("har", (Object)a.j);
                jsonObject.put("rev", (Object)a.k);
                jsonObject.put("ser", (Object)a.l);
                jsonObject.put("cur_cpu", (Object)com.umeng.commonsdk.internal.utils.d.d());
                jsonObject.put("max_cpu", (Object)com.umeng.commonsdk.internal.utils.d.b());
                jsonObject.put("min_cpu", (Object)com.umeng.commonsdk.internal.utils.d.c());
                jsonObject.put("ts", System.currentTimeMillis());
            }
        }
        catch (Exception ex) {}
        return jsonObject;
    }
    
    private static JSONArray o(Context applicationContext) {
        JSONArray b = null;
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            b = com.umeng.commonsdk.proguard.c.b(applicationContext);
        }
        return b;
    }
    
    private static JSONArray p(final Context context) {
        JSONArray jsonArray = null;
        try {
            if (context != null) {
                final ActivityManager activityManager = (ActivityManager)context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE );
                if (activityManager != null) {
                    final List runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                    if (runningServices != null && !runningServices.isEmpty()) {
                        for (int i = 0; i < runningServices.size(); ++i) {
                            if (runningServices.get(i) != null && ((ActivityManager.RunningServiceInfo)runningServices.get(i)).service != null && ((ActivityManager.RunningServiceInfo)runningServices.get(i)).service.getClassName() != null && (((ActivityManager.RunningServiceInfo)runningServices.get(i)).service.getPackageName() != null)) {
                                try {
                                    final JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("sn", ((ActivityManager.RunningServiceInfo)runningServices.get(i)).service.getClassName().toString());
                                    jsonObject.put("pn", ((ActivityManager.RunningServiceInfo)runningServices.get(i)).service.getPackageName().toString());
                                    if (jsonArray == null) {
                                        jsonArray = new JSONArray();
                                    }
                                    jsonArray.put((Object)jsonObject);
                                }
                                catch (JSONException ex) {}
                            }
                        }
                        if (jsonArray != null) {
                            final JSONObject jsonObject2 = new JSONObject();
                            try {
                                jsonObject2.put("ts", System.currentTimeMillis());
                                jsonObject2.put("ls", (Object)jsonArray);
                            }
                            catch (JSONException ex2) {}
                            final JSONObject jsonObject3 = new JSONObject();
                            try {
                                jsonObject3.put("sers", (Object)jsonObject2);
                            }
                            catch (JSONException ex3) {}
                            jsonArray = new JSONArray();
                            jsonArray.put((Object)jsonObject3);
                        }
                    }
                }
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(context, t);
        }
        return jsonArray;
    }
    
    private static JSONArray q(final Context context) {
        final JSONArray jsonArray = new JSONArray();
        final JSONObject d = com.umeng.commonsdk.internal.utils.k.d(context);
        if (d != null) {
            try {
                final String e = com.umeng.commonsdk.internal.utils.k.e(context);
                if (!TextUtils.isEmpty((CharSequence)e)) {
                    d.put("sig", (Object)e);
                }
                jsonArray.put((Object)d);
            }
            catch (Exception ex) {}
        }
        return jsonArray;
    }
    
    private static JSONArray r(final Context context) {
        final JSONArray jsonArray = new JSONArray();
        final String f = com.umeng.commonsdk.internal.utils.k.f(context);
        if (!TextUtils.isEmpty((CharSequence)f)) {
            try {
                final JSONObject jsonObject = new JSONObject(f);
                if (jsonObject != null) {
                    jsonArray.put((Object)jsonObject);
                }
            }
            catch (Exception ex) {}
        }
        return jsonArray;
    }
    
    private static JSONArray s(Context applicationContext) {
        final JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            final String a = com.umeng.commonsdk.internal.utils.k.a(applicationContext);
            if (!TextUtils.isEmpty((CharSequence)a)) {
                try {
                    if (jsonObject == null) {
                        jsonObject = new JSONObject();
                    }
                    jsonObject.put("bluetoothmac", (Object)a);
                }
                catch (Exception ex) {}
            }
            final String b = com.umeng.commonsdk.internal.utils.k.b(applicationContext);
            if (!TextUtils.isEmpty((CharSequence)b)) {
                try {
                    if (jsonObject == null) {
                        jsonObject = new JSONObject();
                    }
                    jsonObject.put("iccid", (Object)b);
                }
                catch (Exception ex2) {}
            }
            final String c = com.umeng.commonsdk.internal.utils.k.c(applicationContext);
            if (!TextUtils.isEmpty((CharSequence)c)) {
                try {
                    if (jsonObject == null) {
                        jsonObject = new JSONObject();
                    }
                    jsonObject.put("secondaryimei", (Object)c);
                }
                catch (Exception ex3) {}
            }
            final JSONObject d = com.umeng.commonsdk.internal.utils.k.d(applicationContext);
            if (d != null) {
                try {
                    final String e = com.umeng.commonsdk.internal.utils.k.e(applicationContext);
                    if (!TextUtils.isEmpty((CharSequence)e)) {
                        d.put("signalscale", (Object)e);
                    }
                    if (jsonObject == null) {
                        jsonObject = new JSONObject();
                    }
                    jsonObject.put("basestation", (Object)d);
                }
                catch (Exception ex4) {}
            }
            final String f = com.umeng.commonsdk.internal.utils.k.f(applicationContext);
            if (!TextUtils.isEmpty((CharSequence)f)) {
                try {
                    if (jsonObject == null) {
                        jsonObject = new JSONObject();
                    }
                    jsonObject.put("battery", (Object)new JSONObject(f));
                }
                catch (Exception ex5) {}
            }
            if (jsonObject != null) {
                jsonArray.put((Object)jsonObject);
            }
        }
        return jsonArray;
    }
    
    private static void a(Context applicationContext, JSONObject jsonObject) {
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            final PackageManager packageManager = applicationContext.getPackageManager();
            if (packageManager != null) {
                if (jsonObject == null) {
                    jsonObject = new JSONObject();
                }
                a(jsonObject, "gp", packageManager.hasSystemFeature("android.hardware.location.gps"));
                a(jsonObject, "to", packageManager.hasSystemFeature("android.hardware.touchscreen"));
                a(jsonObject, "mo", packageManager.hasSystemFeature("android.hardware.telephony"));
                a(jsonObject, "ca", packageManager.hasSystemFeature("android.hardware.camera"));
                a(jsonObject, "fl", packageManager.hasSystemFeature("android.hardware.camera.flash"));
            }
        }
    }
    
    private static void a(final JSONObject jsonObject, final String s, final boolean b) {
        if (jsonObject != null && !TextUtils.isEmpty((CharSequence)s)) {
            try {
                if (b) {
                    jsonObject.put(s, 1);
                }
                else {
                    jsonObject.put(s, 0);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private static void b(final Context context, JSONObject jsonObject) {
        if (context != null) {
            final String a = com.umeng.commonsdk.internal.utils.l.a(context);
            if (!TextUtils.isEmpty((CharSequence)a)) {
                try {
                    final JSONObject jsonObject2 = new JSONObject(a);
                    if (jsonObject2 != null) {
                        if (jsonObject == null) {
                            jsonObject = new JSONObject();
                        }
                        if (jsonObject2.has("_gdf_r")) {
                            jsonObject.put("_gdf_r", jsonObject2.opt("_gdf_r"));
                        }
                        if (jsonObject2.has("_thm_z")) {
                            jsonObject.put("_thm_z", jsonObject2.opt("_thm_z"));
                        }
                        if (jsonObject2.has("_dsk_s")) {
                            jsonObject.put("_dsk_s", jsonObject2.opt("_dsk_s"));
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public static String f(final Context context) {
        String s = null;
        try {
            final com.umeng.commonsdk.statistics.idtracking.e index = com.umeng.commonsdk.statistics.idtracking.e.a(context);
            if (index != null) {
                index.a();
                final String encodeToString = Base64.encodeToString(new com.umeng.commonsdk.proguard.s().a(index.b()), 0);//?
                if (!TextUtils.isEmpty((CharSequence)encodeToString)) {
                    s = encodeToString;
                }
            }
        }
        catch (Exception ex) {
            UMCrashManager.reportCrash(context, ex);
        }
        return s;
    }
    
    public static JSONObject a() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("f", com.umeng.commonsdk.internal.utils.a.c());
            jsonObject.put("t", com.umeng.commonsdk.internal.utils.a.d());
            jsonObject.put("ts", System.currentTimeMillis());
        }
        catch (Exception ex) {}
        return jsonObject;
    }
    
    public static JSONObject b() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("a_pr", (Object)Build.PRODUCT);
            jsonObject.put("a_bl", (Object)Build.BOOTLOADER);
            if (Build.VERSION.SDK_INT >= 14) {
                jsonObject.put("a_rv", (Object)Build.getRadioVersion());
            }
            jsonObject.put("a_fp", (Object)Build.FINGERPRINT);
            jsonObject.put("a_hw", (Object)Build.HARDWARE);
            jsonObject.put("a_host", (Object)Build.HOST);
            if (Build.VERSION.SDK_INT >= 21) {
                final JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < Build.SUPPORTED_32_BIT_ABIS.length; ++i) {
                    jsonArray.put((Object)Build.SUPPORTED_32_BIT_ABIS[i]);
                }
                if (jsonArray != null && jsonArray.length() > 0) {
                    jsonObject.put("a_s32", (Object)jsonArray);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                final JSONArray jsonArray2 = new JSONArray();
                for (int j = 0; j < Build.SUPPORTED_64_BIT_ABIS.length; ++j) {
                    jsonArray2.put((Object)Build.SUPPORTED_64_BIT_ABIS[j]);
                }
                if (jsonArray2 != null && jsonArray2.length() > 0) {
                    jsonObject.put("a_s64", (Object)jsonArray2);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                final JSONArray jsonArray3 = new JSONArray();
                for (int k = 0; k < Build.SUPPORTED_ABIS.length; ++k) {
                    jsonArray3.put((Object)Build.SUPPORTED_ABIS[k]);
                }
                if (jsonArray3 != null && jsonArray3.length() > 0) {
                    jsonObject.put("a_sa", (Object)jsonArray3);
                }
            }
            jsonObject.put("a_ta", (Object)Build.TAGS);
            jsonObject.put("a_uk", (Object)"unknown");
            jsonObject.put("a_user", (Object)Build.USER);
            jsonObject.put("a_cpu1", (Object)Build.CPU_ABI);
            jsonObject.put("a_cpu2", (Object)Build.CPU_ABI2);
            jsonObject.put("a_ra", (Object)Build.RADIO);
            if (Build.VERSION.SDK_INT >= 23) {
                jsonObject.put("a_bos", (Object)Build.VERSION.BASE_OS);
                jsonObject.put("a_pre", Build.VERSION.PREVIEW_SDK_INT);
                jsonObject.put("a_sp", (Object)Build.VERSION.SECURITY_PATCH);
            }
            jsonObject.put("a_cn", (Object)Build.VERSION.CODENAME);
            jsonObject.put("a_intl", (Object)Build.VERSION.INCREMENTAL);
        }
        catch (Exception ex) {}
        return jsonObject;
    }
    
    public static JSONArray g(Context applicationContext) {
        JSONArray g = null;
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            g = com.umeng.commonsdk.internal.utils.k.g(applicationContext);
        }
        return g;
    }
    
    public static JSONObject h(final Context context) {
        final JSONObject jsonObject = new JSONObject();
        if (context != null) {
            try {
                jsonObject.put("a_st_h", com.umeng.commonsdk.internal.utils.a.h(context));
                jsonObject.put("a_nav_h", com.umeng.commonsdk.internal.utils.a.i(context));
                if (context.getResources() != null) {
                    final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                    if (displayMetrics != null) {
                        jsonObject.put("a_den", (double)displayMetrics.density);
                        jsonObject.put("a_dpi", displayMetrics.densityDpi);
                    }
                }
            }
            catch (Exception ex) {
                UMCrashManager.reportCrash(context, ex);
            }
        }
        return jsonObject;
    }
    
    public static JSONObject i(Context applicationContext) {
        final JSONObject jsonObject = new JSONObject();
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            final String packageName = applicationContext.getPackageName();
            try {
                jsonObject.put("a_fit", com.umeng.commonsdk.internal.utils.a.a(applicationContext, packageName));
                jsonObject.put("a_alut", com.umeng.commonsdk.internal.utils.a.b(applicationContext, packageName));
                jsonObject.put("a_c", com.umeng.commonsdk.internal.utils.a.c(applicationContext, packageName));
                jsonObject.put("a_uid", com.umeng.commonsdk.internal.utils.a.d(applicationContext, packageName));
                if (com.umeng.commonsdk.internal.utils.a.a()) {
                    jsonObject.put("a_root", 1);
                }
                else {
                    jsonObject.put("a_root", 0);
                }
                jsonObject.put("tf", com.umeng.commonsdk.internal.utils.a.b());
                jsonObject.put("s_fs", (double)com.umeng.commonsdk.internal.utils.a.a(applicationContext));
                jsonObject.put("a_meid", (Object)com.umeng.commonsdk.internal.utils.a.l(applicationContext));
                jsonObject.put("a_imsi", (Object)com.umeng.commonsdk.internal.utils.a.k(applicationContext));
                jsonObject.put("st", com.umeng.commonsdk.internal.utils.a.f());
                final String b = com.umeng.commonsdk.internal.utils.k.b(applicationContext);
                if (!TextUtils.isEmpty((CharSequence)b)) {
                    try {
                        jsonObject.put("a_iccid", (Object)b);
                    }
                    catch (Exception ex2) {}
                }
                final String c = com.umeng.commonsdk.internal.utils.k.c(applicationContext);
                if (!TextUtils.isEmpty((CharSequence)c)) {
                    try {
                        jsonObject.put("a_simei", (Object)c);
                    }
                    catch (Exception ex3) {}
                }
                jsonObject.put("hn", (Object)com.umeng.commonsdk.internal.utils.a.g());
                jsonObject.put("ts", System.currentTimeMillis());
            }
            catch (Exception ex) {
                UMCrashManager.reportCrash(applicationContext, ex);
            }
        }
        return jsonObject;
    }
    
    public static JSONArray j(Context applicationContext) {
        final JSONArray jsonArray = new JSONArray();
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            final List<ScanResult> b = com.umeng.commonsdk.internal.utils.a.b(applicationContext);
            if (b != null && b.size() > 0) {
                for (final ScanResult scanResult : b) {
                    try {
                        final JSONObject jsonObject = new JSONObject();
                        jsonObject.put("a_bssid", (Object)scanResult.BSSID);
                        jsonObject.put("a_ssid", (Object)scanResult.SSID);
                        jsonObject.put("a_cap", (Object)scanResult.capabilities);
                        jsonObject.put("a_fcy", scanResult.frequency);
                        jsonObject.put("ts", System.currentTimeMillis());
                        if (Build.VERSION.SDK_INT >= 23) {
                            jsonObject.put("a_c0", scanResult.centerFreq0);
                            jsonObject.put("a_c1", scanResult.centerFreq1);
                            jsonObject.put("a_cw", scanResult.channelWidth);
                            if (scanResult.is80211mcResponder()) {
                                jsonObject.put("a_is80211", 1);
                            }
                            else {
                                jsonObject.put("a_is80211", 0);
                            }
                            if (scanResult.isPasspointNetwork()) {
                                jsonObject.put("a_isppn", 1);
                            }
                            else {
                                jsonObject.put("a_isppn", 0);
                            }
                            jsonObject.put("a_ofn", (Object)scanResult.operatorFriendlyName);
                            jsonObject.put("a_vn", (Object)scanResult.venueName);
                        }
                        jsonObject.put("a_dc", scanResult.describeContents());
                        if (jsonObject == null) {
                            continue;
                        }
                        jsonArray.put((Object)jsonObject);
                    }
                    catch (Exception ex) {
                        UMCrashManager.reportCrash(applicationContext, ex);
                    }
                }
            }
        }
        return jsonArray;
    }
    
    public static JSONArray k(Context applicationContext) {
        final JSONArray jsonArray = new JSONArray();
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            final List<InputMethodInfo> m = com.umeng.commonsdk.internal.utils.a.m(applicationContext);
            if (m != null) {
                for (final InputMethodInfo inputMethodInfo : m) {
                    try {
                        final JSONObject jsonObject = new JSONObject();
                        jsonObject.put("a_id", (Object)inputMethodInfo.getId());
                        jsonObject.put("a_pn", (Object)inputMethodInfo.getPackageName());
                        jsonObject.put("ts", System.currentTimeMillis());
                        if (jsonObject == null) {
                            continue;
                        }
                        jsonArray.put((Object)jsonObject);
                    }
                    catch (Throwable t) {
                        UMCrashManager.reportCrash(applicationContext, t);
                    }
                }
            }
        }
        return jsonArray;
    }
    
    public static JSONArray l(Context applicationContext) {
        final JSONArray jsonArray = new JSONArray();
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            final List<com.umeng.commonsdk.internal.utils.j.a> e = com.umeng.commonsdk.internal.utils.j.e(applicationContext);
            if (e != null && !e.isEmpty()) {
                for (final com.umeng.commonsdk.internal.utils.j.a a : e) {
                    if (a != null) {
                        try {
                            final JSONObject jsonObject = new JSONObject();
                            jsonObject.put("a_w", a.a);
                            jsonObject.put("a_h", a.b);
                            jsonObject.put("ts", System.currentTimeMillis());
                            if (jsonObject == null) {
                                continue;
                            }
                            jsonArray.put((Object)jsonObject);
                        }
                        catch (Exception ex) {
                            UMCrashManager.reportCrash(applicationContext, ex);
                        }
                    }
                }
            }
        }
        return jsonArray;
    }
    
    public static JSONArray m(Context applicationContext) {
        final JSONArray jsonArray = new JSONArray();
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            final List<com.umeng.commonsdk.internal.utils.a.InnerClass_a> p = com.umeng.commonsdk.internal.utils.a.p(applicationContext);
            if (p != null && !p.isEmpty()) {
                for (final com.umeng.commonsdk.internal.utils.a.InnerClass_a index : p) {
                    if (index != null) {
                        try {
                            final JSONObject jsonObject = new JSONObject();
                            jsonObject.put("a_pn", (Object)index.a);
                            jsonObject.put("a_la", (Object)index.b);
                            jsonObject.put("ts", System.currentTimeMillis());
                            if (jsonObject == null) {
                                continue;
                            }
                            jsonArray.put((Object)jsonObject);
                        }
                        catch (Exception ex) {
                            UMCrashManager.reportCrash(applicationContext, ex);
                        }
                    }
                }
            }
        }
        return jsonArray;
    }
    
    public static JSONObject n(Context applicationContext) {
        final JSONObject jsonObject = new JSONObject();
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            final ActivityManager.MemoryInfo q = com.umeng.commonsdk.internal.utils.a.q(applicationContext);
            if (q != null) {
                try {
                    if (Build.VERSION.SDK_INT >= 16) {
                        jsonObject.put("t", q.totalMem);
                    }
                    jsonObject.put("length", q.availMem);
                    jsonObject.put("ts", System.currentTimeMillis());
                }
                catch (Exception ex) {
                    UMCrashManager.reportCrash(applicationContext, ex);
                }
            }
        }
        return jsonObject;
    }
}
