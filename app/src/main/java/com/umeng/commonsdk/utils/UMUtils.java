package com.umeng.commonsdk.utils;

import android.text.*;
import com.umeng.commonsdk.statistics.*;
import com.umeng.commonsdk.internal.crash.*;
import java.util.regex.*;
import javax.microedition.khronos.opengles.*;
import java.io.*;
import android.telephony.*;
import android.util.*;
import android.view.*;
import android.content.res.*;
import android.provider.*;
import android.net.wifi.*;
import android.os.*;
import java.security.*;
import java.math.*;
import com.umeng.commonsdk.framework.*;
import java.lang.reflect.*;
import android.content.*;
import android.net.*;
import android.content.pm.*;
import java.util.*;
import com.umeng.commonsdk.statistics.common.*;

public class UMUtils
{
    private static final String TAG = "UMUtils";
    private static final String KEY_SHARED_PREFERENCES_NAME = "umeng_common_config";
    private static final String KEY_APP_KEY = "appkey";
    private static final String KEY_LAST_APP_KEY = "last_appkey";
    private static final String KEY_CHANNEL = "channel";
    public static final String UNKNOW = "";
    public static final String MOBILE_NETWORK = "2G/3G";
    public static final String WIFI = "Wi-Fi";
    public static final int DEFAULT_TIMEZONE = 8;
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String SD_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final Pattern pattern;
    private static Object spLock;
    
    public static void setMultiProcessSP(final Context context, final String s, final String s2) {
        try {
            synchronized (UMUtils.spLock) {
                if (context == null || TextUtils.isEmpty((CharSequence)s) || s2 == null) {
                    return;
                }
                SharedPreferences sharedPreferences;
                if (isMainProgress(context)) {
                    sharedPreferences = context.getApplicationContext().getSharedPreferences("umeng_common_config", 0);
                }
                else {
                    sharedPreferences = context.getApplicationContext().getSharedPreferences(UMFrUtils.getSubProcessName(context) + "_" + "umeng_common_config", 0);
                }
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putString(s, s2).commit();
                }
            }
        }
        catch (Exception ex) {}
        catch (Throwable t) {}
    }
    
    public static String getMultiProcessSP(final Context context, final String s) {
        try {
            synchronized (UMUtils.spLock) {
                if (context == null || TextUtils.isEmpty((CharSequence)s)) {
                    return null;
                }
                SharedPreferences sharedPreferences;
                if (isMainProgress(context)) {
                    sharedPreferences = context.getApplicationContext().getSharedPreferences("umeng_common_config", 0);
                }
                else {
                    sharedPreferences = context.getApplicationContext().getSharedPreferences(UMFrUtils.getSubProcessName(context) + "_" + "umeng_common_config", 0);
                }
                if (sharedPreferences != null) {
                    return sharedPreferences.getString(s, (String)null);
                }
                return null;
            }
        }
        catch (Exception ex) {
            return null;
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static void setLastAppkey(final Context context, final String s) {
        try {
            if (context == null || s == null) {
                return;
            }
            setMultiProcessSP(context, "last_appkey", s);
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "set last app key e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "set last app key e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
        }
    }
    
    public static String getLastAppkey(final Context context) {
        try {
            if (context == null) {
                return null;
            }
            return getMultiProcessSP(context, "last_appkey");
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get last app key e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get last app key e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return null;
        }
    }
    
    public static void setAppkey(final Context context, final String s) {
        try {
            if (context == null || s == null) {
                return;
            }
            setMultiProcessSP(context, "appkey", s);
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "set app key e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "set app key e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
        }
    }
    
    public static String getAppkey(final Context context) {
        try {
            if (context == null) {
                return null;
            }
            return getMultiProcessSP(context, "appkey");
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app key e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app key e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return null;
        }
    }
    
    public static void setChannel(final Context context, final String s) {
        try {
            if (context == null || s == null) {
                return;
            }
            setMultiProcessSP(context, "channel", s);
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "set channel e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "set channel e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
        }
    }
    
    public static String getChannel(final Context context) {
        try {
            if (context == null) {
                return null;
            }
            return getMultiProcessSP(context, "channel");
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get channel e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get channel e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return null;
        }
    }
    
    public static String getUTDID(final Context context) {
        try {
            if (context == null) {
                return null;
            }
            try {
                return (String)Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, context.getApplicationContext());
            }
            catch (Exception ex) {
                return readUTDId(context);
            }
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get utiid e_enum is " + obj);
            }
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get utiid e_enum is " + obj2);
            }
            return null;
        }
    }
    
    private static String readUTDId(final Context context) {
        if (context == null) {
            return null;
        }
        final File file = getFile(context);
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return parseId(readStreamToString(fileInputStream));
            }
            finally {
                safeClose(fileInputStream);
            }
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static void safeClose(final InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            }
            catch (Exception ex) {}
        }
    }
    
    private static String parseId(final String input) {
        if (input == null) {
            return null;
        }
        final Matcher matcher = UMUtils.pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
    
    private static String readStreamToString(final InputStream in) throws IOException {
        final InputStreamReader inputStreamReader = new InputStreamReader(in);
        final char[] array = new char[1024];
        final StringWriter stringWriter = new StringWriter();
        int read;
        while (-1 != (read = inputStreamReader.read(array))) {
            stringWriter.write(array, 0, read);
        }
        return stringWriter.toString();
    }
    
    private static File getFile(final Context context) {
        if (context == null) {
            return null;
        }
        if (!checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return null;
        }
        if (Environment.getExternalStorageState().equals("mounted")) {
            final File externalStorageDirectory = Environment.getExternalStorageDirectory();
            try {
                return new File(externalStorageDirectory.getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
            }
            catch (Exception ex) {}
        }
        return null;
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
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "Could not read gpu infor, e_enum is " + obj);
            }
            return new String[0];
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "Could not read gpu infor, e_enum is " + obj2);
            }
            return new String[0];
        }
    }
    
    public static String getCPU() {
        try {
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
                    catch (IOException obj) {
                        if (AnalyticsConstants.UM_DEBUG) {
                            Log.e("UMUtils", "Could not read from file /proc/cpuinfo, e_enum is " + obj);
                        }
                    }
                }
            }
            catch (FileNotFoundException obj2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "Could not read from file /proc/cpuinfo, e_enum is " + obj2);
                }
            }
            if (line != null) {
                return line.substring(line.indexOf(58) + 1).trim();
            }
            return "";
        }
        catch (Exception obj3) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get cpu e_enum is " + obj3);
            }
            return "";
        }
        catch (Throwable obj4) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get cpu e_enum is " + obj4);
            }
            return "";
        }
    }
    
    public static String getImsi(final Context context) {
        try {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            String subscriberId = null;
            if (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                subscriberId = telephonyManager.getSubscriberId();
            }
            return subscriberId;
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get imei e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get imei e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return null;
        }
    }
    
    public static String getRegisteredOperator(final Context context) {
        if (context == null) {
            return null;
        }
        try {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            String networkOperator = null;
            if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                networkOperator = telephonyManager.getNetworkOperator();
            }
            return networkOperator;
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get registered operator e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get registered operator e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return null;
        }
    }
    
    public static String getNetworkOperatorName(final Context context) {
        if (context == null) {
            return null;
        }
        try {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (!checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            if (telephonyManager == null) {
                return "";
            }
            return telephonyManager.getNetworkOperatorName();
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get network operator e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return "";
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get network operator e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return "";
        }
    }
    
    public static String getDisplayResolution(final Context context) {
        if (context == null) {
            return "";
        }
        try {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            final WindowManager windowManager = (WindowManager)context.getSystemService("window");
            if (windowManager == null) {
                return "";
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(displayMetrics.widthPixels);
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get display resolution e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return "";
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get display resolution e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
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
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get network access mode e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get network access mode e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
        }
        return array;
    }
    
    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }
    
    public static Locale getLocale(final Context context) {
        if (context == null) {
            return null;
        }
        try {
            Locale locale = null;
            try {
                final Configuration configuration = new Configuration();
                configuration.setToDefaults();
                Settings.System.getConfiguration(context.getContentResolver(), configuration);
                if (configuration != null) {
                    locale = configuration.locale;
                }
            }
            catch (Exception obj) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "fail to read user config locale, e_enum is " + obj);
                }
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
            return locale;
        }
        catch (Exception obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get locale e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return null;
        }
        catch (Throwable obj3) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get locale e_enum is " + obj3);
            }
            UMCrashManager.reportCrash(context, obj3);
            return null;
        }
    }
    
    public static String getMac(final Context context) {
        if (context == null) {
            return null;
        }
        try {
            final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
            if (wifiManager == null) {
                return null;
            }
            if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            }
            return "";
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get mac e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get mac e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return null;
        }
    }
    
    public static String getOperator(final Context context) {
        if (context == null) {
            return "Unknown";
        }
        try {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (telephonyManager == null) {
                return "Unknown";
            }
            return telephonyManager.getNetworkOperator();
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get get operator e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get get operator e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
        }
        return "Unknown";
    }
    
    public static String getSubOSName(final Context context) {
        if (context == null) {
            return null;
        }
        try {
            final Properties buildProp = getBuildProp();
            String property;
            try {
                property = buildProp.getProperty("ro.miui.ui.version.name");
                if (TextUtils.isEmpty((CharSequence)property)) {
                    if (isFlyMe()) {
                        property = "Flyme";
                    }
                    else if (!TextUtils.isEmpty((CharSequence)getYunOSVersion(buildProp))) {
                        property = "YunOS";
                    }
                }
                else {
                    property = "MIUI";
                }
            }
            catch (Exception ex) {
                property = null;
                UMCrashManager.reportCrash(context, ex);
            }
            return property;
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get sub os name e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get sub os name e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return null;
        }
    }
    
    public static String getSubOSVersion(final Context context) {
        if (context == null) {
            return null;
        }
        try {
            final Properties buildProp = getBuildProp();
            String s;
            try {
                s = buildProp.getProperty("ro.miui.ui.version.name");
                if (TextUtils.isEmpty((CharSequence)s)) {
                    if (isFlyMe()) {
                        try {
                            s = getFlymeVersion(buildProp);
                        }
                        catch (Exception ex2) {}
                    }
                    else {
                        try {
                            s = getYunOSVersion(buildProp);
                        }
                        catch (Exception ex3) {}
                    }
                }
            }
            catch (Exception ex) {
                s = null;
                UMCrashManager.reportCrash(context, ex);
            }
            return s;
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get sub os version e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get sub os version e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return null;
        }
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
        catch (Exception ex) {}
        return null;
    }
    
    public static Properties getBuildProp() {
        final Properties properties = new Properties();
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            properties.load(inStream);
        }
        catch (IOException ex) {}
        finally {
            if (inStream != null) {
                try {
                    ((FileInputStream)inStream).close();
                }
                catch (IOException ex2) {}
            }
        }
        return properties;
    }
    
    private static boolean isFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar", (Class<?>[])new Class[0]);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public static String getDeviceType(final Context context) {
        try {
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
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get device type e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get device type e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return null;
        }
    }
    
    public static String getAppVersionCode(final Context context) {
        if (context == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version code e_enum is " + obj);
            }
            return "";
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version code e_enum is " + obj2);
            }
            return "";
        }
    }
    
    public static String getAppVersinoCode(final Context context, final String s) {
        if (context == null || s == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(s, 0).versionCode);
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version code e_enum is " + obj);
            }
            return "";
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version code e_enum is " + obj2);
            }
            return "";
        }
    }
    
    public static String getAppVersionName(final Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch (PackageManager.NameNotFoundException obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version name e_enum is " + obj);
            }
            return "";
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version name e_enum is " + obj2);
            }
            return "";
        }
    }
    
    public static String getAppVersionName(final Context context, final String s) {
        if (context == null || s == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(s, 0).versionName;
        }
        catch (PackageManager.NameNotFoundException obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version name e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, (Throwable)obj);
            return "";
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version name e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
            return "";
        }
    }
    
    public static boolean checkPermission(final Context obj, final String s) {
        boolean b = false;
        if (obj == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                b = ((int)Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(obj, s) == 0);
            }
            catch (Exception ex) {
                UMCrashManager.reportCrash(obj, ex);
                b = false;
            }
        }
        else if (obj.getPackageManager().checkPermission(s, obj.getPackageName()) == 0) {
            b = true;
        }
        return b;
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
            sb.append(str.toUpperCase());
            if (i < array.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }
    
    public static boolean isDebug(final Context context) {
        if (context == null) {
            return false;
        }
        try {
            return (context.getApplicationInfo().flags & 0x2) != 0x0;
        }
        catch (Exception ex) {
            UMCrashManager.reportCrash(context, ex);
            return false;
        }
    }
    
    public static String getAppName(final Context context) {
        String string = null;
        if (context == null) {
            return null;
        }
        try {
            string = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app name e_enum is " + obj);
            }
            UMCrashManager.reportCrash(context, obj);
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app name e_enum is " + obj2);
            }
            UMCrashManager.reportCrash(context, obj2);
        }
        return string;
    }
    
    public static String MD5(final String s) {
        try {
            if (s == null) {
                return null;
            }
            try {
                final byte[] bytes = s.getBytes();
                final MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.reset();
                instance.update(bytes);
                final byte[] digest = instance.digest();
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < digest.length; ++i) {
                    sb.append(String.format("%02X", digest[i]));
                }
                return sb.toString();
            }
            catch (Exception ex) {
                return s.replaceAll("[^[InnerClass_a-z][A-Z][0-9][.][_]]", "");
            }
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "MD5 e_enum is " + obj);
            }
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "MD5 e_enum is " + obj2);
            }
            return null;
        }
    }
    
    public static String getFileMD5(final File file) {
        try {
            final byte[] array = new byte[1024];
            MessageDigest instance;
            try {
                if (!file.isFile()) {
                    return "";
                }
                instance = MessageDigest.getInstance("MD5");
                final FileInputStream fileInputStream = new FileInputStream(file);
                int read;
                while ((read = fileInputStream.read(array, 0, 1024)) != -1) {
                    instance.update(array, 0, read);
                }
                fileInputStream.close();
            }
            catch (Exception ex) {
                return null;
            }
            return String.format("%1$032x", new BigInteger(1, instance.digest()));
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get file MD5 e_enum is " + obj);
            }
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get file MD5 e_enum is " + obj2);
            }
            return null;
        }
    }
    
    public static String encryptBySHA1(final String s) {
        try {
            final byte[] bytes = s.getBytes();
            String bytes2Hex;
            try {
                final MessageDigest instance = MessageDigest.getInstance("SHA1");
                instance.update(bytes);
                bytes2Hex = bytes2Hex(instance.digest());
            }
            catch (Exception ex) {
                return null;
            }
            return bytes2Hex;
        }
        catch (Exception obj) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "encrypt by SHA1 e_enum is " + obj);
            }
            return null;
        }
        catch (Throwable obj2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "encrypt by SHA1 e_enum is " + obj2);
            }
            return null;
        }
    }
    
    private static String bytes2Hex(final byte[] array) {
        String s = "";
        for (int i = 0; i < array.length; ++i) {
            final String hexString = Integer.toHexString(array[i] & 0xFF);
            if (hexString.length() == 1) {
                s += "0";
            }
            s += hexString;
        }
        return s;
    }
    
    public static String getUMId(final Context context) {
        String imprintProperty = null;
        try {
            if (context != null) {
                imprintProperty = UMEnvelopeBuild.imprintProperty(context.getApplicationContext(), "umid", null);
            }
        }
        catch (Exception ex) {
            UMCrashManager.reportCrash(context, ex);
        }
        return imprintProperty;
    }
    
    public static String getDeviceToken(Context applicationContext) {
        String s = null;
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            try {
                final Class<?> forName = Class.forName("com.umeng.message.MessageSharedPrefs");
                if (forName != null) {
                    final Method method = forName.getMethod("getInstance", Context.class);
                    if (method != null) {
                        final Object invoke = method.invoke(forName, applicationContext);
                        if (invoke != null) {
                            final Method method2 = forName.getMethod("getDeviceToken", (Class<?>[])new Class[0]);
                            if (method2 != null) {
                                final Object invoke2 = method2.invoke(invoke, new Object[0]);
                                if (invoke2 != null && invoke2 instanceof String) {
                                    s = (String)invoke2;
                                }
                            }
                        }
                    }
                }
            }
            catch (Throwable t) {}
        }
        return s;
    }
    
    public static String getAppkeyByXML(final Context context) {
        try {
            final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                final String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i("MobclickAgent", "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
                }
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    public static String getChannelByXML(final Context context) {
        try {
            final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                final Object value = applicationInfo.metaData.get("UMENG_CHANNEL");
                if (value != null) {
                    final String string = value.toString();
                    if (string != null) {
                        return string.trim();
                    }
                    if (AnalyticsConstants.UM_DEBUG) {
                        MLog.i("MobclickAgent", "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                    }
                }
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    public static boolean checkPath(final String className) {
        try {
            return Class.forName(className) != null;
        }
        catch (ClassNotFoundException ex) {
            return false;
        }
    }
    
    public static boolean checkAndroidManifest(final Context context, final String s) {
        try {
            context.getApplicationContext().getPackageManager().getActivityInfo(new ComponentName(context.getApplicationContext().getPackageName(), s), 0);
            return true;
        }
        catch (PackageManager.NameNotFoundException ex) {
            return false;
        }
    }
    
    public static boolean checkIntentFilterData(final Context context, final String str) {
        final Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("tencent" + str + ":"));
        final List<ResolveInfo> queryIntentActivities = context.getApplicationContext().getPackageManager().queryIntentActivities(intent, 64);
        if (queryIntentActivities.size() > 0) {
            for (final ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName.equals(context.getApplicationContext().getPackageName())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    
    public static boolean checkResource(final Context context, final String s, final String s2) {
        return context.getApplicationContext().getResources().getIdentifier(s, s2, context.getApplicationContext().getPackageName()) > 0;
    }
    
    public static boolean checkMetaData(final Context context, final String s) {
        final PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getApplicationContext().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData.get(s) != null;
            }
        }
        catch (PackageManager.NameNotFoundException ex) {}
        return false;
    }
    
    public static String getAppMD5Signature(final Context context) {
        String s = DeviceConfig.getAppMD5Signature(context);
        if (!TextUtils.isEmpty((CharSequence)s)) {
            s = s.replace(":", "").toLowerCase();
        }
        return s;
    }
    
    public static String getAppSHA1Key(final Context context) {
        return DeviceConfig.getAppSHA1Key(context);
    }
    
    public static String getAppHashKey(final Context context) {
        return DeviceConfig.getAppHashKey(context);
    }
    
    public static int getTargetSdkVersion(final Context context) {
        return context.getApplicationInfo().targetSdkVersion;
    }
    
    public static boolean isMainProgress(final Context context) {
        boolean b = false;
        try {
            final String currentProcessName = UMFrUtils.getCurrentProcessName(context);
            final String packageName = context.getApplicationContext().getPackageName();
            if (!TextUtils.isEmpty((CharSequence)currentProcessName) && !TextUtils.isEmpty((CharSequence)packageName) && currentProcessName.equals(packageName)) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static boolean isApplication(final Context context) {
        boolean b = false;
        try {
            final String name = context.getApplicationContext().getClass().getSuperclass().getName();
            if (!TextUtils.isEmpty((CharSequence)name) && name.equals("android.app.Application")) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    static {
        pattern = Pattern.compile("UTDID\">([^<]+)");
        UMUtils.spLock = new Object();
    }
}
