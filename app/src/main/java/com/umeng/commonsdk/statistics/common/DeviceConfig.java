package com.umeng.commonsdk.statistics.common;

import android.content.*;
import android.content.pm.Signature;
import android.telephony.*;
import com.umeng.commonsdk.statistics.*;
import android.text.*;
import android.provider.*;
import com.umeng.commonsdk.utils.*;
import javax.microedition.khronos.opengles.*;
import java.net.*;

import android.util.Base64;
import android.view.*;
import android.net.*;
import com.umeng.commonsdk.framework.*;
import android.content.res.*;
import android.net.wifi.*;
import java.lang.reflect.*;
import java.security.*;
import java.security.cert.*;
import android.util.*;
import android.content.pm.*;
import java.util.*;
import android.os.*;
import java.io.*;

public class DeviceConfig
{
    protected static final String LOG_TAG;
    public static final String UNKNOW = "";
    public static final String MOBILE_NETWORK = "2G/3G";
    public static final String WIFI = "Wi-Fi";
    public static final int DEFAULT_TIMEZONE = 8;
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.hw_emui_api_level";
    
    public static String getImei(final Context context) {
        String deviceId = null;
        try {
            if (context != null) {
                final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                if (telephonyManager != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                    deviceId = telephonyManager.getDeviceId();
                }
            }
        }
        catch (Exception ex) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.w("No IMEI.", ex);
            }
        }
        return deviceId;
    }
    
    public static String getImeiNew(final Context context) {
        String s = null;
        try {
            if (context != null) {
                final TelephonyManager obj = (TelephonyManager)context.getSystemService("phone");
                if (obj != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        try {
                            final Method method = obj.getClass().getMethod("getImei", (Class<?>[])new Class[0]);
                            method.setAccessible(true);
                            s = (String)method.invoke(obj, new Object[0]);
                        }
                        catch (Exception ex2) {}
                        if (TextUtils.isEmpty((CharSequence)s)) {
                            s = obj.getDeviceId();
                        }
                    }
                    else {
                        s = obj.getDeviceId();
                    }
                }
            }
        }
        catch (Exception ex) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.w("No IMEI.", ex);
            }
        }
        return s;
    }
    
    public static String getAndroidId(final Context context) {
        String string = null;
        if (context != null) {
            try {
                string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            }
            catch (Exception ex) {
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.w("can't read android id");
                }
            }
        }
        return string;
    }
    
    public static String getSerial() {
        if (Build.VERSION.SDK_INT >= 9) {
            String serial = null;
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    final Class<?> forName = Class.forName("android.os.Build");
                    serial = (String)forName.getMethod("getSerial", (Class<?>[])new Class[0]).invoke(forName, new Object[0]);
                }
                catch (Throwable t) {}
            }
            else {
                serial = Build.SERIAL;
            }
            return serial;
        }
        return null;
    }
    
    public static String getAppVersionCode(final Context context) {
        return UMUtils.getAppVersionCode(context);
    }
    
    public static String getAppVersionName(final Context context) {
        return UMUtils.getAppVersionName(context);
    }
    
    public static boolean checkPermission(final Context obj, final String s) {
        boolean b = false;
        if (obj == null) {
            return b;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                b = ((int)Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(obj, s) == 0);
            }
            catch (Throwable t) {
                b = false;
            }
        }
        else if (obj.getPackageManager().checkPermission(s, obj.getPackageName()) == 0) {
            b = true;
        }
        return b;
    }
    
    public static String[] getGPU(final GL10 gl10) {
        try {
            final String[] array = new String[2];
            final String glGetString = gl10.glGetString(7936);
            final String glGetString2 = gl10.glGetString(7937);
            array[0] = glGetString;
            array[1] = glGetString2;
            return array;
        }
        catch (Throwable t) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.e(DeviceConfig.LOG_TAG, "Could not read gpu infor:", t);
            }
            return new String[0];
        }
    }
    
    private static String getMacByJavaAPI() {
        try {
            final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                final NetworkInterface networkInterface = networkInterfaces.nextElement();
                if ("wlan0".equals(networkInterface.getName()) || "eth0".equals(networkInterface.getName())) {
                    final byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null || hardwareAddress.length == 0) {
                        return null;
                    }
                    final StringBuilder sb = new StringBuilder();
                    final byte[] array = hardwareAddress;
                    for (int length = array.length, i = 0; i < length; ++i) {
                        sb.append(String.format("%02X:", array[i]));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString().toLowerCase(Locale.getDefault());
                }
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    private static String getMacShell() {
        try {
            final String[] array = { "/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address" };
            for (int i = 0; i < array.length; ++i) {
                try {
                    final String reaMac = reaMac(array[i]);
                    if (reaMac != null) {
                        return reaMac;
                    }
                }
                catch (Throwable t) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        MLog.e(DeviceConfig.LOG_TAG, "open file  Failed", t);
                    }
                }
            }
        }
        catch (Throwable t2) {}
        return null;
    }
    
    private static String reaMac(final String fileName) {
        String line = null;
        try {
            final FileReader in = new FileReader(fileName);
            BufferedReader bufferedReader = null;
            if (in != null) {
                try {
                    bufferedReader = new BufferedReader(in, 1024);
                    line = bufferedReader.readLine();
                }
                finally {
                    if (in != null) {
                        try {
                            in.close();
                        }
                        catch (Throwable t) {}
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        }
                        catch (Throwable t2) {}
                    }
                }
            }
        }
        catch (Throwable t3) {}
        return line;
    }
    
    public static String getCPU() {
        String line = null;
        try {
            final FileReader in = new FileReader("/proc/cpuinfo");
            if (in != null) {
                try {
                    final BufferedReader bufferedReader = new BufferedReader(in, 1024);
                    line = bufferedReader.readLine();
                    bufferedReader.close();
                    in.close();
                }
                catch (Throwable t) {
                    MLog.e(DeviceConfig.LOG_TAG, "Could not read from file /proc/cpuinfo", t);
                }
            }
        }
        catch (FileNotFoundException ex) {
            MLog.e(DeviceConfig.LOG_TAG, "Could not open file /proc/cpuinfo", ex);
        }
        if (line != null) {
            return line.substring(line.indexOf(58) + 1).trim();
        }
        return "";
    }
    
    public static String getDeviceId(final Context context) {
        if (AnalyticsConstants.getDeviceType() == 2) {
            return getDeviceIdForBox(context);
        }
        return getDeviceIdForGeneral(context);
    }
    
    public static String getDeviceIdUmengMD5(final Context context) {
        return HelperUtils.getUmengMD5(getDeviceId(context));
    }
    
    public static String getMCCMNC(final Context context) {
        if (context == null) {
            return null;
        }
        if (getImsi(context) == null) {
            return null;
        }
        final int mcc = context.getResources().getConfiguration().mcc;
        final int mnc = context.getResources().getConfiguration().mnc;
        if (mcc != 0) {
            String str = String.valueOf(mnc);
            if (mnc < 10) {
                str = String.format("%02d", mnc);
            }
            return new StringBuffer().append(String.valueOf(mcc)).append(str).toString();
        }
        return null;
    }
    
    public static String getImsi(final Context context) {
        if (context == null) {
            return null;
        }
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        String subscriberId = null;
        if (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
            subscriberId = telephonyManager.getSubscriberId();
        }
        return subscriberId;
    }
    
    public static String getRegisteredOperator(final Context context) {
        if (context == null) {
            return null;
        }
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        String networkOperator = null;
        if (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
            networkOperator = telephonyManager.getNetworkOperator();
        }
        return networkOperator;
    }
    
    public static String getNetworkOperatorName(final Context context) {
        if (context == null) {
            return "";
        }
        try {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
        }
        catch (Throwable t) {}
        return "";
    }
    
    public static String getDisplayResolution(final Context context) {
        if (context == null) {
            return "";
        }
        try {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            final WindowManager windowManager = (WindowManager)context.getSystemService("window");
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(displayMetrics.widthPixels);
            }
            return "";
        }
        catch (Throwable t) {
            return "";
        }
    }
    
    public static String[] getNetworkAccessMode(final Context context) {
        final String[] array = { "", "" };
        if (context == null) {
            return array;
        }
        try {
            if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                array[0] = "";
                return array;
            }
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
            if (connectivityManager == null) {
                array[0] = "";
                return array;
            }
            final NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                array[0] = "Wi-Fi";
                return array;
            }
            final NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo2 != null && networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                array[0] = "2G/3G";
                array[1] = networkInfo2.getSubtypeName();
                return array;
            }
        }
        catch (Throwable t) {}
        return array;
    }
    
    public static boolean isWiFiAvailable(final Context context) {
        return context != null && "Wi-Fi".equals(getNetworkAccessMode(context)[0]);
    }
    
    public static boolean isOnline(final Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        return activeNetworkInfo.isConnectedOrConnecting();
                    }
                }
            }
        }
        catch (Throwable t) {}
        return false;
    }
    
    public static int getTimeZone(final Context context) {
        if (context == null) {
            return 8;
        }
        try {
            final Calendar instance = Calendar.getInstance(getLocale(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
        }
        catch (Throwable t) {
            MLog.i(DeviceConfig.LOG_TAG, "error in getTimeZone", t);
        }
        return 8;
    }
    
    public static boolean isChineseAera(final Context context) {
        if (context == null) {
            return false;
        }
        final String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "country", "");
        if (!TextUtils.isEmpty((CharSequence)imprintProperty)) {
            return imprintProperty.equals("cn");
        }
        if (getImsi(context) == null) {
            final String s = getLocaleInfo(context)[0];
            if (!TextUtils.isEmpty((CharSequence)s) && s.equalsIgnoreCase("cn")) {
                return true;
            }
        }
        else {
            final int mcc = context.getResources().getConfiguration().mcc;
            if (mcc == 460 || mcc == 461) {
                return true;
            }
            if (mcc == 0) {
                final String s2 = getLocaleInfo(context)[0];
                if (!TextUtils.isEmpty((CharSequence)s2) && s2.equalsIgnoreCase("cn")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static String[] getLocaleInfo(final Context context) {
        final String[] array = { "Unknown", "Unknown" };
        if (context == null) {
            return array;
        }
        try {
            final Locale locale = getLocale(context);
            if (locale != null) {
                array[0] = locale.getCountry();
                array[1] = locale.getLanguage();
            }
            if (TextUtils.isEmpty((CharSequence)array[0])) {
                array[0] = "Unknown";
            }
            if (TextUtils.isEmpty((CharSequence)array[1])) {
                array[1] = "Unknown";
            }
            return array;
        }
        catch (Throwable t) {
            MLog.e(DeviceConfig.LOG_TAG, "error in getLocaleInfo", t);
            return array;
        }
    }
    
    private static Locale getLocale(final Context context) {
        Locale locale = null;
        if (context == null) {
            return Locale.getDefault();
        }
        try {
            final Configuration configuration = new Configuration();
            configuration.setToDefaults();
            Settings.System.getConfiguration(context.getContentResolver(), configuration);
            if (configuration != null) {
                locale = configuration.locale;
            }
        }
        catch (Throwable t) {
            MLog.e(DeviceConfig.LOG_TAG, "fail to read user config locale");
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return locale;
    }
    
    public static String getMac(final Context context) {
        final String s = "";
        if (context == null) {
            return s;
        }
        String s2;
        if (Build.VERSION.SDK_INT < 23) {
            s2 = getMacBySystemInterface(context);
        }
        else if (Build.VERSION.SDK_INT == 23) {
            s2 = getMacByJavaAPI();
            if (TextUtils.isEmpty((CharSequence)s2)) {
                if (AnalyticsConstants.CHECK_DEVICE) {
                    s2 = getMacShell();
                }
                else {
                    s2 = getMacBySystemInterface(context);
                }
            }
        }
        else {
            s2 = getMacByJavaAPI();
            if (TextUtils.isEmpty((CharSequence)s2)) {
                s2 = getMacBySystemInterface(context);
            }
        }
        return s2;
    }
    
    private static String getMacBySystemInterface(final Context context) {
        if (context == null) {
            return "";
        }
        try {
            final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
            if (!checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.w(DeviceConfig.LOG_TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
                }
                return "";
            }
            if (wifiManager != null) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            return "";
        }
        catch (Throwable t) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.w(DeviceConfig.LOG_TAG, "Could not get mac address." + t.toString());
            }
            return "";
        }
    }
    
    public static int[] getResolutionArray(final Context context) {
        if (context == null) {
            return null;
        }
        try {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            final WindowManager windowManager = (WindowManager)context.getSystemService("window");
            if (windowManager == null) {
                return null;
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int n = -1;
            int n2 = -1;
            if ((context.getApplicationInfo().flags & 0x2000) == 0x0) {
                n = reflectMetrics(displayMetrics, "noncompatWidthPixels");
                n2 = reflectMetrics(displayMetrics, "noncompatHeightPixels");
            }
            if (n == -1 || n2 == -1) {
                n = displayMetrics.widthPixels;
                n2 = displayMetrics.heightPixels;
            }
            final int[] array = new int[2];
            if (n > n2) {
                array[0] = n2;
                array[1] = n;
            }
            else {
                array[0] = n;
                array[1] = n2;
            }
            return array;
        }
        catch (Throwable t) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.e(DeviceConfig.LOG_TAG, "read resolution fail", t);
            }
            return null;
        }
    }
    
    private static int reflectMetrics(final Object obj, final String name) {
        try {
            final Field declaredField = DisplayMetrics.class.getDeclaredField(name);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        }
        catch (Throwable t) {
            return -1;
        }
    }
    
    public static String getPackageName(final Context context) {
        if (context == null) {
            return null;
        }
        return context.getPackageName();
    }
    
    public static String getAppSHA1Key(final Context context) {
        String byte2HexFormatted = null;
        try {
            byte2HexFormatted = byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(((X509Certificate)CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures[0].toByteArray()))).getEncoded()));
        }
        catch (Exception ex) {}
        return byte2HexFormatted;
    }
    
    public static String getAppHashKey(final Context context) {
        try {
            final Signature[] signatures = context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures;
            final int length = signatures.length;
            final int n = 0;
            if (n < length) {
                final Signature signature = signatures[n];
                final MessageDigest instance = MessageDigest.getInstance("SHA");
                instance.update(signature.toByteArray());
                return Base64.encodeToString(instance.digest(), 0).trim();
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    public static String getAppMD5Signature(final Context context) {
        String byte2HexFormatted = null;
        if (context == null) {
            return byte2HexFormatted;
        }
        try {
            byte2HexFormatted = byte2HexFormatted(MessageDigest.getInstance("MD5").digest(((X509Certificate)CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures[0].toByteArray()))).getEncoded()));
        }
        catch (Throwable t) {}
        return byte2HexFormatted;
    }
    
    private static String byte2HexFormatted(final byte[] array) {
        final StringBuilder sb = new StringBuilder(array.length * 2);
        for (int i = 0; i < array.length; ++i) {
            String str = Integer.toHexString(array[i]);
            final int length = str.length();
            if (length == 1) {
                str = "0" + str;
            }
            if (length > 2) {
                str = str.substring(length - 2, length);
            }
            sb.append(str.toUpperCase(Locale.getDefault()));
            if (i < array.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }
    
    public static String getApplicationLable(final Context context) {
        if (context == null) {
            return "";
        }
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }
    
    public static String getAppName(final Context context) {
        String string = null;
        if (context == null) {
            return string;
        }
        try {
            string = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        }
        catch (Throwable t) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(DeviceConfig.LOG_TAG, t);
            }
        }
        return string;
    }
    
    public static String getDeviceIdForGeneral(final Context context) {
        final String s = "";
        if (context == null) {
            return s;
        }
        String str;
        if (Build.VERSION.SDK_INT < 23) {
            str = getIMEI(context);
            if (TextUtils.isEmpty((CharSequence)str)) {
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.w(DeviceConfig.LOG_TAG, "No IMEI.");
                }
                str = getMacBySystemInterface(context);
                if (TextUtils.isEmpty((CharSequence)str)) {
                    str = Settings.Secure.getString(context.getContentResolver(), "android_id");
                    if (AnalyticsConstants.UM_DEBUG) {
                        MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, ANDROID_ID: " + str);
                    }
                    if (TextUtils.isEmpty((CharSequence)str)) {
                        str = getSerialNo();
                    }
                }
            }
        }
        else if (Build.VERSION.SDK_INT == 23) {
            str = getIMEI(context);
            if (TextUtils.isEmpty((CharSequence)str)) {
                str = getMacByJavaAPI();
                if (TextUtils.isEmpty((CharSequence)str)) {
                    if (AnalyticsConstants.CHECK_DEVICE) {
                        str = getMacShell();
                    }
                    else {
                        str = getMacBySystemInterface(context);
                    }
                }
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, MAC: " + str);
                }
                if (TextUtils.isEmpty((CharSequence)str)) {
                    str = Settings.Secure.getString(context.getContentResolver(), "android_id");
                    if (AnalyticsConstants.UM_DEBUG) {
                        MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, ANDROID_ID: " + str);
                    }
                    if (TextUtils.isEmpty((CharSequence)str)) {
                        str = getSerialNo();
                    }
                }
            }
        }
        else {
            str = getIMEI(context);
            if (TextUtils.isEmpty((CharSequence)str)) {
                str = getSerialNo();
                if (TextUtils.isEmpty((CharSequence)str)) {
                    str = Settings.Secure.getString(context.getContentResolver(), "android_id");
                    if (AnalyticsConstants.UM_DEBUG) {
                        MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, ANDROID_ID: " + str);
                    }
                    if (TextUtils.isEmpty((CharSequence)str)) {
                        str = getMacByJavaAPI();
                        if (TextUtils.isEmpty((CharSequence)str)) {
                            str = getMacBySystemInterface(context);
                            if (AnalyticsConstants.UM_DEBUG) {
                                MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, MAC: " + str);
                            }
                        }
                    }
                }
            }
        }
        return str;
    }
    
    public static String getDeviceIdForBox(final Context context) {
        final String s = "";
        if (context == null) {
            return s;
        }
        String s2;
        if (Build.VERSION.SDK_INT < 23) {
            s2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, ANDROID_ID: " + s2);
            }
            if (TextUtils.isEmpty((CharSequence)s2)) {
                s2 = getMacBySystemInterface(context);
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, MAC: " + s2);
                }
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    s2 = getSerialNo();
                    if (TextUtils.isEmpty((CharSequence)s2)) {
                        s2 = getIMEI(context);
                    }
                }
            }
        }
        else if (Build.VERSION.SDK_INT == 23) {
            s2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, ANDROID_ID: " + s2);
            }
            if (TextUtils.isEmpty((CharSequence)s2)) {
                s2 = getMacByJavaAPI();
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    if (AnalyticsConstants.CHECK_DEVICE) {
                        s2 = getMacShell();
                    }
                    else {
                        s2 = getMacBySystemInterface(context);
                    }
                }
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, MAC: " + s2);
                }
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    s2 = getSerialNo();
                    if (TextUtils.isEmpty((CharSequence)s2)) {
                        s2 = getIMEI(context);
                    }
                }
            }
        }
        else {
            s2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(DeviceConfig.LOG_TAG, "getDeviceId: ANDROID_ID: " + s2);
            }
            if (TextUtils.isEmpty((CharSequence)s2)) {
                s2 = getSerialNo();
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    s2 = getIMEI(context);
                    if (TextUtils.isEmpty((CharSequence)s2)) {
                        s2 = getMacByJavaAPI();
                        if (TextUtils.isEmpty((CharSequence)s2)) {
                            s2 = getMacBySystemInterface(context);
                            if (AnalyticsConstants.UM_DEBUG) {
                                MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, MAC: " + s2);
                            }
                        }
                    }
                }
            }
        }
        return s2;
    }
    
    private static String getIMEI(final Context context) {
        String deviceId = "";
        if (context == null) {
            return deviceId;
        }
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        if (telephonyManager != null) {
            try {
                if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                    deviceId = telephonyManager.getDeviceId();
                    if (AnalyticsConstants.UM_DEBUG) {
                        MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, IMEI: " + deviceId);
                    }
                }
            }
            catch (Throwable t) {
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.w(DeviceConfig.LOG_TAG, "No IMEI.", t);
                }
            }
        }
        return deviceId;
    }
    
    private static String getSerialNo() {
        String serial = "";
        if (Build.VERSION.SDK_INT >= 9) {
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    final Class<?> forName = Class.forName("android.os.Build");
                    serial = (String)forName.getMethod("getSerial", (Class<?>[])new Class[0]).invoke(forName, new Object[0]);
                }
                catch (Throwable t) {}
            }
            else {
                serial = Build.SERIAL;
            }
        }
        if (AnalyticsConstants.UM_DEBUG) {
            MLog.i(DeviceConfig.LOG_TAG, "getDeviceId, serial no: " + serial);
        }
        return serial;
    }
    
    public static String getSubOSName(final Context context) {
        final Properties buildProp = getBuildProp();
        String property;
        try {
            property = buildProp.getProperty("ro.miui.ui.version.name");
            if (TextUtils.isEmpty((CharSequence)property)) {
                if (isFlyMe()) {
                    property = "Flyme";
                }
                else if (isEmui(buildProp)) {
                    property = "Emui";
                }
                else if (!TextUtils.isEmpty((CharSequence)getYunOSVersion(buildProp))) {
                    property = "YunOS";
                }
            }
            else {
                property = "MIUI";
            }
        }
        catch (Throwable t) {
            property = null;
        }
        return property;
    }
    
    public static String getSubOSVersion(final Context context) {
        final Properties buildProp = getBuildProp();
        String s;
        try {
            s = buildProp.getProperty("ro.miui.ui.version.name");
            if (TextUtils.isEmpty((CharSequence)s)) {
                if (isFlyMe()) {
                    try {
                        s = getFlymeVersion(buildProp);
                    }
                    catch (Throwable t) {}
                }
                else if (isEmui(buildProp)) {
                    try {
                        s = getEmuiVersion(buildProp);
                    }
                    catch (Throwable t2) {}
                }
                else {
                    try {
                        s = getYunOSVersion(buildProp);
                    }
                    catch (Throwable t3) {}
                }
            }
        }
        catch (Throwable t4) {
            s = null;
        }
        return s;
    }
    
    private static String getYunOSVersion(final Properties properties) {
        final String property = properties.getProperty("ro.yunos.version");
        if (!TextUtils.isEmpty((CharSequence)property)) {
            return property;
        }
        return null;
    }
    
    private static String getFlymeVersion(final Properties properties) {
        try {
            final String lowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (lowerCase.contains("flyme os")) {
                return lowerCase.split(" ")[2];
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    private static String getEmuiVersion(final Properties properties) {
        String property = null;
        try {
            property = properties.getProperty("ro.build.hw_emui_api_level", null);
        }
        catch (Exception ex) {}
        return property;
    }
    
    private static Properties getBuildProp() {
        final Properties properties = new Properties();
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            properties.load(inStream);
        }
        catch (Throwable t) {}
        finally {
            if (inStream != null) {
                try {
                    ((FileInputStream)inStream).close();
                }
                catch (Throwable t2) {}
            }
        }
        return properties;
    }
    
    private static boolean isFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar", (Class<?>[])new Class[0]);
        }
        catch (Throwable t) {
            return false;
        }
        return true;
    }
    
    private static boolean isEmui(final Properties properties) {
        try {
            if (properties.getProperty("ro.build.hw_emui_api_level", null) != null) {
                return true;
            }
        }
        catch (Exception ex) {
            return false;
        }
        return false;
    }
    
    public static String getDeviceType(final Context context) {
        final String s = "Phone";
        if (context == null) {
            return s;
        }
        String s2;
        if ((context.getResources().getConfiguration().screenLayout & 0xF) >= 3) {
            s2 = "Tablet";
        }
        else {
            s2 = "Phone";
        }
        return s2;
    }
    
    public static String getDBencryptID(final Context context) {
        String s = null;
        try {
            if (context != null) {
                final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                if (telephonyManager != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                    s = telephonyManager.getDeviceId();
                }
                if (TextUtils.isEmpty((CharSequence)s)) {
                    s = Settings.Secure.getString(context.getContentResolver(), "android_id");
                    if (TextUtils.isEmpty((CharSequence)s) && Build.VERSION.SDK_INT >= 9) {
                        if (Build.VERSION.SDK_INT >= 26) {
                            try {
                                final Class<?> forName = Class.forName("android.os.Build");
                                s = (String)forName.getMethod("getSerial", (Class<?>[])new Class[0]).invoke(forName, new Object[0]);
                            }
                            catch (Throwable t) {}
                        }
                        else {
                            s = Build.SERIAL;
                        }
                    }
                }
            }
        }
        catch (Throwable t2) {}
        return s;
    }
    
    static {
        LOG_TAG = DeviceConfig.class.getName();
    }
}
