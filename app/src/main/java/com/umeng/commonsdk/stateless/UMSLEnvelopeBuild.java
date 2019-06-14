package com.umeng.commonsdk.stateless;

import android.content.*;
import org.json.*;
import android.text.*;
import com.umeng.commonsdk.statistics.common.*;
import android.os.*;
import com.umeng.commonsdk.statistics.*;
import com.umeng.commonsdk.utils.*;
import com.umeng.commonsdk.framework.*;
import com.umeng.commonsdk.internal.crash.*;
import com.umeng.commonsdk.statistics.idtracking.*;
import com.umeng.commonsdk.proguard.*;
import android.util.*;
import android.util.Base64;

import java.util.*;

public class UMSLEnvelopeBuild
{
    public static Context mContext;
    private static final String TAG = "UMSLEnvelopeBuild";
    private static String cacheSystemheader;
    public static String module;
    private static boolean isEncryptEnabled;
    
    public synchronized JSONObject buildSLBaseHeader(Context applicationContext) {
        ULog.i("walle", "[stateless] begin build hader, thread is " + Thread.currentThread());
        if (applicationContext == null) {
            return null;
        }
        applicationContext = applicationContext.getApplicationContext();
        try {
            JSONObject jsonObject = null;
            if (!TextUtils.isEmpty((CharSequence)UMSLEnvelopeBuild.cacheSystemheader)) {
                try {
                    jsonObject = new JSONObject(UMSLEnvelopeBuild.cacheSystemheader);
                }
                catch (Exception ex) {}
            }
            else {
                jsonObject = new JSONObject();
                jsonObject.put("app_signature", (Object)DeviceConfig.getAppMD5Signature(applicationContext));
                jsonObject.put("app_sig_sha1", (Object)DeviceConfig.getAppSHA1Key(applicationContext));
                jsonObject.put("app_sig_sha", (Object)DeviceConfig.getAppHashKey(applicationContext));
                jsonObject.put("app_version", (Object)DeviceConfig.getAppVersionName(applicationContext));
                jsonObject.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(applicationContext)));
                jsonObject.put("idmd5", (Object)DeviceConfig.getDeviceIdUmengMD5(applicationContext));
                jsonObject.put("cpu", (Object)DeviceConfig.getCPU());
                final String mccmnc = DeviceConfig.getMCCMNC(applicationContext);
                if (!TextUtils.isEmpty((CharSequence)mccmnc)) {
                    jsonObject.put("mccmnc", (Object)mccmnc);
                }
                else {
                    jsonObject.put("mccmnc", (Object)"");
                }
                final String subOSName = DeviceConfig.getSubOSName(applicationContext);
                if (!TextUtils.isEmpty((CharSequence)subOSName)) {
                    jsonObject.put("sub_os_name", (Object)subOSName);
                }
                final String subOSVersion = DeviceConfig.getSubOSVersion(applicationContext);
                if (!TextUtils.isEmpty((CharSequence)subOSVersion)) {
                    jsonObject.put("sub_os_version", (Object)subOSVersion);
                }
                final String deviceType = DeviceConfig.getDeviceType(applicationContext);
                if (!TextUtils.isEmpty((CharSequence)deviceType)) {
                    jsonObject.put("device_type", (Object)deviceType);
                }
                jsonObject.put("package_name", (Object)DeviceConfig.getPackageName(applicationContext));
                jsonObject.put("sdk_type", (Object)"Android");
                jsonObject.put("device_id", (Object)DeviceConfig.getDeviceId(applicationContext));
                jsonObject.put("device_model", (Object)Build.MODEL);
                jsonObject.put("device_board", (Object)Build.BOARD);
                jsonObject.put("device_brand", (Object)Build.BRAND);
                jsonObject.put("device_manutime", Build.TIME);
                jsonObject.put("device_manufacturer", (Object)Build.MANUFACTURER);
                jsonObject.put("device_manuid", (Object)Build.ID);
                jsonObject.put("device_name", (Object)Build.DEVICE);
                jsonObject.put("os", (Object)"Android");
                jsonObject.put("os_version", (Object)Build.VERSION.RELEASE);
                final int[] resolutionArray = DeviceConfig.getResolutionArray(applicationContext);
                if (resolutionArray != null) {
                    jsonObject.put("resolution", (Object)(resolutionArray[1] + "*" + resolutionArray[0]));
                }
                jsonObject.put("mc", (Object)DeviceConfig.getMac(applicationContext));
                jsonObject.put("timezone", DeviceConfig.getTimeZone(applicationContext));
                final String[] localeInfo = DeviceConfig.getLocaleInfo(applicationContext);
                jsonObject.put("country", (Object)localeInfo[0]);
                jsonObject.put("language", (Object)localeInfo[1]);
                jsonObject.put("carrier", (Object)DeviceConfig.getNetworkOperatorName(applicationContext));
                jsonObject.put("display_name", (Object)DeviceConfig.getAppName(applicationContext));
                final String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(applicationContext);
                if ("Wi-Fi".equals(networkAccessMode[0])) {
                    jsonObject.put("access", (Object)"wifi");
                }
                else if ("2G/3G".equals(networkAccessMode[0])) {
                    jsonObject.put("access", (Object)"2G/3G");
                }
                else {
                    jsonObject.put("access", (Object)"unknow");
                }
                if (!"".equals(networkAccessMode[1])) {
                    jsonObject.put("access_subtype", (Object)networkAccessMode[1]);
                }
                jsonObject.put("com_ver", (Object)"2.0.2");
                jsonObject.put("com_type", SdkVersion.SDK_TYPE);
                if (!TextUtils.isEmpty((CharSequence)UMSLEnvelopeBuild.module)) {
                    jsonObject.put("module", (Object)UMSLEnvelopeBuild.module);
                }
                UMSLEnvelopeBuild.cacheSystemheader = jsonObject.toString();
            }
            if (jsonObject == null) {
                return null;
            }
            jsonObject.put("channel", (Object)UMUtils.getChannel(applicationContext));
            jsonObject.put("appkey", (Object)UMUtils.getAppkey(applicationContext));
            try {
                if (SdkVersion.SDK_TYPE != 1) {
                    CharSequence charSequence = null;
                    try {
                        final Class<?> forName = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                        if (forName != null) {
                            charSequence = (String)forName.getMethod("getUmtt", Context.class).invoke(forName, applicationContext);
                        }
                    }
                    catch (ClassNotFoundException ex2) {}
                    catch (Throwable t2) {}
                    if (!TextUtils.isEmpty(charSequence)) {
                        jsonObject.put("umtt", (Object)charSequence);
                    }
                }
            }
            catch (Exception ex3) {}
            try {
                final String imprintProperty = UMEnvelopeBuild.imprintProperty(applicationContext, "umid", null);
                if (!TextUtils.isEmpty((CharSequence)imprintProperty)) {
                    jsonObject.put("umid", (Object)imprintProperty);
                }
            }
            catch (Exception ex4) {}
            try {
                if (SdkVersion.SDK_TYPE != 1 && com.umeng.commonsdk.proguard.a.b(applicationContext) != null) {
                    jsonObject.put("utoken", com.umeng.commonsdk.proguard.a.b(applicationContext));
                }
            }
            catch (Exception ex5) {}
            try {
                jsonObject.put("wrapper_type", (Object)com.umeng.commonsdk.stateless.a.a);
                jsonObject.put("wrapper_version", (Object)com.umeng.commonsdk.stateless.a.b);
            }
            catch (Exception ex6) {}
            if (jsonObject != null && jsonObject.length() > 0) {
                final JSONObject jsonObject2 = new JSONObject();
                ULog.i("walle", "[stateless] build header end , header is " + jsonObject.toString() + ", thread is " + Thread.currentThread());
                return jsonObject2.put("header", (Object)jsonObject);
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(applicationContext, t);
        }
        ULog.i("walle", "[stateless] build header end , header is null !!! thread is " + Thread.currentThread());
        return null;
    }
    
    private synchronized JSONObject makeErrorResult(final int n, final JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                jsonObject.put("exception", n);
            }
            catch (Exception ex) {}
            return jsonObject;
        }
        final JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject2.put("exception", n);
        }
        catch (Exception ex2) {}
        return jsonObject2;
    }
    
    public synchronized JSONObject buildSLEnvelope(Context applicationContext, final JSONObject jsonObject, final JSONObject jsonObject2, final String str) {
        ULog.i("walle", "[stateless] build envelope, heade is " + jsonObject.toString());
        ULog.i("walle", "[stateless] build envelope, body is " + jsonObject2.toString());
        ULog.i("walle", "[stateless] build envelope, thread is " + Thread.currentThread());
        if (applicationContext == null || jsonObject == null || jsonObject2 == null || str == null) {
            ULog.i("walle", "[stateless] build envelope, context is null or header is null or body is null");
            return this.makeErrorResult(110, null);
        }
        try {
            applicationContext = applicationContext.getApplicationContext();
            if (jsonObject != null && jsonObject2 != null) {
                final Iterator keys = jsonObject2.keys();
                while (keys.hasNext()) {
                    final Object next = keys.next();
                    if (next != null && next instanceof String) {
                        final Object s = next;
                        if (s == null || jsonObject2.opt((String)s) == null) {
                            continue;
                        }
                        try {
                            jsonObject.put((String)s, jsonObject2.opt((String)s));
                        }
                        catch (Exception ex) {}
                    }
                }
            }
            try {
                if (jsonObject != null) {
                    final com.umeng.commonsdk.statistics.idtracking.e a = com.umeng.commonsdk.statistics.idtracking.e.a(applicationContext);
                    if (a != null) {
                        a.a();
                        final String encodeToString = Base64.encodeToString(new com.umeng.commonsdk.proguard.s().a(a.b()), 0);
                        if (!TextUtils.isEmpty((CharSequence)encodeToString)) {
                            final JSONObject jsonObject3 = jsonObject.getJSONObject("header");
                            jsonObject3.put("id_tracking", (Object)encodeToString);
                            jsonObject.put("header", (Object)jsonObject3);
                        }
                    }
                }
            }
            catch (Exception ex2) {}
            if (jsonObject != null && f.a(jsonObject.toString().getBytes().length, com.umeng.commonsdk.stateless.a.c)) {
                ULog.i("walle", "[stateless] build envelope, json overstep!!!! size is " + jsonObject.toString().getBytes().length);
                return this.makeErrorResult(113, jsonObject);
            }
            ULog.i("walle", "[stateless] build envelope, json size is " + jsonObject.toString().getBytes().length);
            c constructEnvelope = null;
            if (jsonObject != null) {
                constructEnvelope = this.constructEnvelope(applicationContext, jsonObject.toString().getBytes());
                if (constructEnvelope == null) {
                    ULog.i("walle", "[stateless] build envelope, envelope is null !!!!");
                    return this.makeErrorResult(111, jsonObject);
                }
            }
            if (constructEnvelope != null && f.a(constructEnvelope.b().length, com.umeng.commonsdk.stateless.a.d)) {
                ULog.i("walle", "[stateless] build envelope, envelope overstep!!!! size is " + constructEnvelope.b().length);
                return this.makeErrorResult(114, jsonObject);
            }
            if (!f.a(applicationContext, Base64.encodeToString(str.getBytes(), 0), Base64.encodeToString((str + "_" + System.currentTimeMillis()).getBytes(), 0), constructEnvelope.b())) {
                ULog.i("walle", "[stateless] build envelope, save fail ----->>>>>");
                return this.makeErrorResult(101, jsonObject);
            }
            ULog.i("walle", "[stateless] build envelope, save ok ----->>>>>");
            ULog.i("walle", "[stateless] envelope file size is " + jsonObject.toString().getBytes().length);
            final d d = new d(applicationContext);
            com.umeng.commonsdk.stateless.d.b(273);
            ULog.i("walle", "[stateless] build envelope end, thread is " + Thread.currentThread());
            return jsonObject;
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(applicationContext, t);
            ULog.i("walle", "build envelope end, thread is " + Thread.currentThread());
            return this.makeErrorResult(110, null);
        }
    }
    
    private synchronized c constructEnvelope(final Context context, final byte[] array) {
        int intValue = -1;
        final String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "slcodex", null);
        ULog.i("walle", "[stateless] build envelope, codexStr is " + imprintProperty);
        try {
            if (!TextUtils.isEmpty((CharSequence)imprintProperty)) {
                intValue = Integer.valueOf(imprintProperty);
            }
        }
        catch (NumberFormatException ex) {
            UMCrashManager.reportCrash(context, ex);
        }
        c c;
        if (intValue == 0) {
            ULog.i("walle", "[stateless] build envelope, codexValue is 0");
            c = com.umeng.commonsdk.stateless.c.a(context, UMUtils.getAppkey(context), array);
        }
        else if (intValue == 1) {
            ULog.i("walle", "[stateless] build envelope, codexValue is 1");
            c = com.umeng.commonsdk.stateless.c.b(context, UMUtils.getAppkey(context), array);
        }
        else if (UMSLEnvelopeBuild.isEncryptEnabled) {
            ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is true");
            c = com.umeng.commonsdk.stateless.c.b(context, UMUtils.getAppkey(context), array);
        }
        else {
            ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is false");
            c = com.umeng.commonsdk.stateless.c.a(context, UMUtils.getAppkey(context), array);
        }
        return c;
    }
    
    public static void setEncryptEnabled(final boolean isEncryptEnabled) {
        UMSLEnvelopeBuild.isEncryptEnabled = isEncryptEnabled;
    }
    
    static {
        UMSLEnvelopeBuild.cacheSystemheader = null;
    }
}
