package com.umeng.commonsdk.statistics;

import android.text.*;
import android.util.*;
import com.umeng.commonsdk.statistics.internal.*;
import com.umeng.commonsdk.internal.crash.*;
import org.json.*;
import java.util.*;
import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import android.os.*;
import android.util.Base64;

import com.umeng.commonsdk.utils.*;
import com.umeng.commonsdk.proguard.*;
import com.umeng.commonsdk.statistics.idtracking.*;
import com.umeng.commonsdk.framework.*;

public class b
{
    private static final String c = "EnvelopeManager";
    private static String d;
    public static String a;
    public static String b;
    private int e;
    private static boolean f;
    
    public b() {
        this.e = 0;
    }
    
    public static long a(final Context context) {
        long lng = DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX - DataHelper.ENVELOPE_EXTRA_LENGTH;
        final JSONObject b = b(context);
        if (b != null && b.toString() != null && b.toString().getBytes() != null) {
            final long lng2 = b.toString().getBytes().length;
            if (ULog.DEBUG) {
                Log.i("EnvelopeManager", "headerLen size is " + lng2);
            }
            lng -= lng2;
        }
        if (ULog.DEBUG) {
            Log.i("EnvelopeManager", "free size is " + lng);
        }
        return lng;
    }
    
    private JSONObject a(final int n, final JSONObject jsonObject) {
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
    
    public JSONObject a(final Context context, final JSONObject jsonObject, final JSONObject jsonObject2) {
        if (ULog.DEBUG && jsonObject != null && jsonObject2 != null) {
            Log.i("EnvelopeManager", "headerJSONObject size is " + jsonObject.toString().getBytes().length);
            Log.i("EnvelopeManager", "bodyJSONObject size is " + jsonObject2.toString().getBytes().length);
        }
        if (context == null || jsonObject2 == null) {
            return this.a(110, null);
        }
        try {
            JSONObject jsonObject3 = b(context);
            if (jsonObject3 != null && jsonObject != null) {
                jsonObject3 = this.a(jsonObject3, jsonObject);
            }
            if (jsonObject3 != null && jsonObject2 != null) {
                final Iterator keys = jsonObject2.keys();
                while (keys.hasNext()) {
                    final Object next = keys.next();
                    if (next != null && next instanceof String) {
                        final String s = (String)next;
                        if (s == null || jsonObject2.opt(s) == null) {
                            continue;
                        }
                        try {
                            jsonObject3.put(s, jsonObject2.opt(s));
                        }
                        catch (Exception ex2) {}
                    }
                }
            }
            String s2 = null;
            if (jsonObject3 != null) {
                final StringBuilder sb = new StringBuilder();
                if (jsonObject3.length() > 0) {
                    if (jsonObject3.has("push")) {
                        final String str = "p";
                        final String optString = jsonObject3.optJSONObject("header").optString("p_sdk_v");
                        if (!TextUtils.isEmpty((CharSequence)str) && !TextUtils.isEmpty((CharSequence)optString)) {
                            sb.append(str).append("==").append(optString).append("&=");
                        }
                    }
                    if (jsonObject3.has("share")) {
                        final String str2 = "s";
                        final String optString2 = jsonObject3.optJSONObject("header").optString("s_sdk_v");
                        if (!TextUtils.isEmpty((CharSequence)str2) && !TextUtils.isEmpty((CharSequence)optString2)) {
                            sb.append(str2).append("==").append(optString2).append("&=");
                        }
                    }
                    if (jsonObject3.has("analytics")) {
                        String str3;
                        if (jsonObject3.has("dplus")) {
                            str3 = "ad";
                        }
                        else {
                            str3 = "InnerClass_a";
                            if (jsonObject3.optJSONObject("header").has("st")) {
                                str3 = "t";
                            }
                        }
                        final String optString3 = jsonObject3.optJSONObject("header").optString("sdk_version");
                        if (!TextUtils.isEmpty((CharSequence)str3) && !TextUtils.isEmpty((CharSequence)optString3)) {
                            sb.append(str3).append("==").append(optString3).append("&=");
                        }
                    }
                    if (jsonObject3.has("dplus")) {
                        final String optString4 = jsonObject3.optJSONObject("header").optString("sdk_version");
                        if (jsonObject3.has("analytics")) {
                            final String str4 = "ad";
                            if (!sb.toString().contains("ad") && !TextUtils.isEmpty((CharSequence)str4) && !TextUtils.isEmpty((CharSequence)optString4)) {
                                sb.append(str4).append("==").append(optString4).append("&=");
                            }
                        }
                        else {
                            final String str5 = "serial_num";
                            if (!TextUtils.isEmpty((CharSequence)str5) && !TextUtils.isEmpty((CharSequence)optString4)) {
                                sb.append(str5).append("==").append(optString4).append("&=");
                            }
                        }
                    }
                    if (jsonObject3.has("inner")) {
                        final String str6 = "checksum";
                        final String optString5 = jsonObject3.optJSONObject("header").optString("i_sdk_v");
                        if (!TextUtils.isEmpty((CharSequence)str6) && !TextUtils.isEmpty((CharSequence)optString5)) {
                            sb.append(str6).append("==").append(optString5).append("&=");
                        }
                    }
                }
                s2 = sb.toString();
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    return this.a(101, jsonObject3);
                }
                if (s2.endsWith("&=")) {
                    s2 = s2.substring(0, s2.length() - 2);
                }
            }
            try {
                if (jsonObject3 != null) {
                    final com.umeng.commonsdk.statistics.idtracking.e a = com.umeng.commonsdk.statistics.idtracking.e.a(context);
                    if (a != null) {
                        a.a();
                        final String encodeToString = Base64.encodeToString(new com.umeng.commonsdk.proguard.s().a(a.b()), 0);
                        if (!TextUtils.isEmpty((CharSequence)encodeToString)) {
                            final JSONObject jsonObject4 = jsonObject3.getJSONObject("header");
                            jsonObject4.put("id_tracking", (Object)encodeToString);
                            jsonObject3.put("header", (Object)jsonObject4);
                        }
                    }
                }
            }
            catch (Exception ex3) {}
            if (jsonObject3 != null && DataHelper.largeThanMaxSize(jsonObject3.toString().getBytes().length, DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX)) {
                final SharedPreferences default1 = PreferenceWrapper.getDefault(context);
                if (default1 != null) {
                    int int1 = default1.getInt("serial", 1);
                    default1.edit().putInt("serial", ++int1).commit();
                }
                return this.a(113, jsonObject3);
            }
            Envelope a2 = null;
            if (jsonObject3 != null) {
                a2 = this.a(context, jsonObject3.toString().getBytes());
                if (a2 == null) {
                    return this.a(111, jsonObject3);
                }
            }
            if (a2 != null && DataHelper.largeThanMaxSize(a2.toBinary().length, DataHelper.ENVELOPE_LENGTH_MAX)) {
                return this.a(114, jsonObject3);
            }
            String optString6 = null;
            if (jsonObject3 != null) {
                optString6 = jsonObject3.optJSONObject("header").optString("app_version");
            }
            final int a3 = this.a(context, a2, s2, optString6);
            if (a3 != 0) {
                return this.a(a3, jsonObject3);
            }
            if (ULog.DEBUG) {
                Log.i("EnvelopeManager", "constructHeader size is " + jsonObject3.toString().getBytes().length);
            }
            return jsonObject3;
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(context, t);
            JSONObject jsonObject5 = null;
            try {
                if (jsonObject != null) {
                    if (jsonObject5 == null) {
                        jsonObject5 = new JSONObject();
                    }
                    try {
                        jsonObject5.put("header", (Object)jsonObject);
                    }
                    catch (JSONException ex4) {}
                }
                if (jsonObject2 != null) {
                    if (jsonObject5 == null) {
                        jsonObject5 = new JSONObject();
                    }
                    if (jsonObject2 != null) {
                        final Iterator keys2 = jsonObject2.keys();
                        while (keys2.hasNext()) {
                            final Object next2 = keys2.next();
                            if (next2 != null && next2 instanceof String) {
                                final String s3 = (String)next2;
                                if (s3 == null || jsonObject2.opt(s3) == null) {
                                    continue;
                                }
                                try {
                                    jsonObject5.put(s3, jsonObject2.opt(s3));
                                }
                                catch (Exception ex5) {}
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {
                UMCrashManager.reportCrash(context, ex);
            }
            return this.a(110, jsonObject5);
        }
    }
    
    private static JSONObject b(final Context context) {
        try {
            final SharedPreferences default1 = PreferenceWrapper.getDefault(context);
            JSONObject jsonObject = null;
            if (!TextUtils.isEmpty((CharSequence)com.umeng.commonsdk.statistics.b.d)) {
                try {
                    jsonObject = new JSONObject(com.umeng.commonsdk.statistics.b.d);
                }
                catch (Exception ex5) {}
            }
            else {
                jsonObject = new JSONObject();
                jsonObject.put("app_signature", (Object)DeviceConfig.getAppMD5Signature(context));
                jsonObject.put("app_sig_sha1", (Object)DeviceConfig.getAppSHA1Key(context));
                jsonObject.put("app_sig_sha", (Object)DeviceConfig.getAppHashKey(context));
                jsonObject.put("app_version", (Object)DeviceConfig.getAppVersionName(context));
                jsonObject.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(context)));
                jsonObject.put("idmd5", (Object)DeviceConfig.getDeviceIdUmengMD5(context));
                jsonObject.put("cpu", (Object)DeviceConfig.getCPU());
                final String mccmnc = DeviceConfig.getMCCMNC(context);
                if (!TextUtils.isEmpty((CharSequence)mccmnc)) {
                    jsonObject.put("mccmnc", (Object)mccmnc);
                    com.umeng.commonsdk.statistics.b.b = mccmnc;
                }
                else {
                    jsonObject.put("mccmnc", (Object)"");
                }
                final String subOSName = DeviceConfig.getSubOSName(context);
                if (!TextUtils.isEmpty((CharSequence)subOSName)) {
                    jsonObject.put("sub_os_name", (Object)subOSName);
                }
                final String subOSVersion = DeviceConfig.getSubOSVersion(context);
                if (!TextUtils.isEmpty((CharSequence)subOSVersion)) {
                    jsonObject.put("sub_os_version", (Object)subOSVersion);
                }
                final String deviceType = DeviceConfig.getDeviceType(context);
                if (!TextUtils.isEmpty((CharSequence)deviceType)) {
                    jsonObject.put("device_type", (Object)deviceType);
                }
                jsonObject.put("package_name", (Object)DeviceConfig.getPackageName(context));
                jsonObject.put("sdk_type", (Object)"Android");
                jsonObject.put("device_id", (Object)DeviceConfig.getDeviceId(context));
                jsonObject.put("device_model", (Object)Build.MODEL);
                jsonObject.put("device_board", (Object)Build.BOARD);
                jsonObject.put("device_brand", (Object)Build.BRAND);
                jsonObject.put("device_manutime", Build.TIME);
                jsonObject.put("device_manufacturer", (Object)Build.MANUFACTURER);
                jsonObject.put("device_manuid", (Object)Build.ID);
                jsonObject.put("device_name", (Object)Build.DEVICE);
                jsonObject.put("os", (Object)"Android");
                jsonObject.put("os_version", (Object)Build.VERSION.RELEASE);
                final int[] resolutionArray = DeviceConfig.getResolutionArray(context);
                if (resolutionArray != null) {
                    jsonObject.put("resolution", (Object)(resolutionArray[1] + "*" + resolutionArray[0]));
                }
                jsonObject.put("mc", (Object)DeviceConfig.getMac(context));
                jsonObject.put("timezone", DeviceConfig.getTimeZone(context));
                final String[] localeInfo = DeviceConfig.getLocaleInfo(context);
                jsonObject.put("country", (Object)localeInfo[0]);
                jsonObject.put("language", (Object)localeInfo[1]);
                jsonObject.put("carrier", (Object)DeviceConfig.getNetworkOperatorName(context));
                jsonObject.put("display_name", (Object)DeviceConfig.getAppName(context));
                final String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(context);
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
                if (!TextUtils.isEmpty((CharSequence)com.umeng.commonsdk.statistics.b.a)) {
                    jsonObject.put("module", (Object)com.umeng.commonsdk.statistics.b.a);
                }
                com.umeng.commonsdk.statistics.b.d = jsonObject.toString();
            }
            if (jsonObject == null) {
                return null;
            }
            try {
                jsonObject.put("successful_requests", default1.getInt("successful_request", 0));
                jsonObject.put("failed_requests", default1.getInt("failed_requests", 0));
                jsonObject.put("req_time", default1.getInt("last_request_spent_ms", 0));
            }
            catch (Exception ex6) {}
            jsonObject.put("channel", (Object)UMUtils.getChannel(context));
            jsonObject.put("appkey", (Object)UMUtils.getAppkey(context));
            try {
                final String deviceToken = UMUtils.getDeviceToken(context);
                if (!TextUtils.isEmpty((CharSequence)deviceToken)) {
                    jsonObject.put("devicetoken", (Object)deviceToken);
                }
            }
            catch (Exception ex) {
                UMCrashManager.reportCrash(context, ex);
            }
            try {
                if (SdkVersion.SDK_TYPE != 1) {
                    CharSequence charSequence = null;
                    try {
                        final Class<?> forName = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                        if (forName != null) {
                            charSequence = (String)forName.getMethod("getUmtt", Context.class).invoke(forName, context);
                        }
                    }
                    catch (ClassNotFoundException ex7) {}
                    catch (Throwable t2) {}
                    if (!TextUtils.isEmpty(charSequence)) {
                        jsonObject.put("umtt", (Object)charSequence);
                    }
                }
            }
            catch (Exception ex2) {
                UMCrashManager.reportCrash(context, ex2);
            }
            try {
                final String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "umid", null);
                if (!TextUtils.isEmpty((CharSequence)imprintProperty)) {
                    jsonObject.put("umid", (Object)imprintProperty);
                }
            }
            catch (Exception ex3) {
                UMCrashManager.reportCrash(context, ex3);
            }
            try {
                if (SdkVersion.SDK_TYPE != 1 && com.umeng.commonsdk.proguard.a.b(context) != null) {
                    jsonObject.put("utoken", (Object)com.umeng.commonsdk.proguard.a.b(context));
                }
            }
            catch (Exception ex8) {}
            try {
                jsonObject.put("wrapper_type", (Object)com.umeng.commonsdk.statistics.a.a);
                jsonObject.put("wrapper_version", (Object)com.umeng.commonsdk.statistics.a.b);
            }
            catch (Exception ex9) {}
            final byte[] a = ImprintHandler.getImprintService(context).a();
            if (a != null && a.length > 0) {
                try {
                    jsonObject.put("imprint", (Object)Base64.encodeToString(a, 0));
                }
                catch (JSONException ex4) {
                    UMCrashManager.reportCrash(context, (Throwable)ex4);
                }
            }
            if (jsonObject != null && jsonObject.length() > 0) {
                return new JSONObject().put("header", (Object)jsonObject);
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(context, t);
        }
        return null;
    }
    
    private JSONObject a(final JSONObject jsonObject, final JSONObject jsonObject2) {
        if (jsonObject != null && jsonObject2 != null && jsonObject.opt("header") != null && jsonObject.opt("header") instanceof JSONObject) {
            final JSONObject jsonObject3 = (JSONObject)jsonObject.opt("header");
            final Iterator keys = jsonObject2.keys();
            while (keys.hasNext()) {
                final Object next = keys.next();
                if (next != null && next instanceof String) {
                    final String s = (String)next;
                    if (s == null || jsonObject2.opt(s) == null) {
                        continue;
                    }
                    try {
                        jsonObject3.put(s, jsonObject2.opt(s));
                        if (!s.equals("vertical_type") || !(jsonObject2.opt(s) instanceof Integer)) {
                            continue;
                        }
                        this.e = (int)jsonObject2.opt(s);
                    }
                    catch (Exception ex) {}
                }
            }
        }
        return jsonObject;
    }
    
    private Envelope a(final Context context, final byte[] array) {
        int intValue = -1;
        final String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "codex", null);
        try {
            if (!TextUtils.isEmpty((CharSequence)imprintProperty)) {
                intValue = Integer.valueOf(imprintProperty);
            }
        }
        catch (NumberFormatException ex) {
            UMCrashManager.reportCrash(context, ex);
        }
        Envelope envelope;
        if (intValue == 0) {
            envelope = Envelope.genEnvelope(context, UMUtils.getAppkey(context), array);
        }
        else if (intValue == 1) {
            envelope = Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), array);
        }
        else if (com.umeng.commonsdk.statistics.b.f) {
            envelope = Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), array);
        }
        else {
            envelope = Envelope.genEnvelope(context, UMUtils.getAppkey(context), array);
        }
        return envelope;
    }
    
    private int a(final Context context, final Envelope envelope, final String str, String appVersionName) {
        if (context == null || envelope == null || TextUtils.isEmpty((CharSequence)str)) {
            return 101;
        }
        if (TextUtils.isEmpty((CharSequence)appVersionName)) {
            appVersionName = DeviceConfig.getAppVersionName(context);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(str).append("&&").append(appVersionName).append("_").append(System.currentTimeMillis()).append("_envelope.log");
        return UMFrUtils.saveEnvelopeFile(context, sb.toString(), envelope.toBinary());
    }
    
    public static void a(final boolean f) {
        com.umeng.commonsdk.statistics.b.f = f;
    }
    
    static {
        com.umeng.commonsdk.statistics.b.d = null;
        com.umeng.commonsdk.statistics.b.b = "";
    }
}
