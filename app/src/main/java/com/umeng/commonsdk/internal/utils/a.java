package com.umeng.commonsdk.internal.utils;

import android.content.*;
import com.umeng.commonsdk.internal.crash.*;
import android.content.pm.*;
import java.text.*;
import android.net.wifi.*;
import android.content.res.*;
import android.util.*;
import android.telephony.*;
import android.text.*;
import android.view.inputmethod.*;
import android.bluetooth.*;
import com.umeng.commonsdk.internal.*;
import com.umeng.commonsdk.framework.*;
import org.json.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;
import android.app.*;
import com.umeng.commonsdk.statistics.common.*;
import android.os.*;

public class a
{
    public static long a(final Context context, final String s) {
        if (context == null) {
            return 0L;
        }
        long firstInstallTime = 0L;
        try {
            firstInstallTime = context.getPackageManager().getPackageInfo(s, 0).firstInstallTime;
        }
        catch (PackageManager.NameNotFoundException ex) {
            UMCrashManager.reportCrash(context, (Throwable)ex);
            if (ex != null) {
                ULog.e("getAppFirstInstallTime" + ex.getMessage());
            }
        }
        return firstInstallTime;
    }
    
    public static long b(final Context context, final String s) {
        if (context == null) {
            return 0L;
        }
        long lastUpdateTime = 0L;
        try {
            lastUpdateTime = context.getPackageManager().getPackageInfo(s, 0).lastUpdateTime;
        }
        catch (PackageManager.NameNotFoundException ex) {
            UMCrashManager.reportCrash(context, (Throwable)ex);
            if (ex != null) {
                ULog.e("getAppLastUpdateTime:" + ex.getMessage());
            }
        }
        return lastUpdateTime;
    }
    
    public static String c(final Context context, final String s) {
        String installerPackageName = null;
        try {
            installerPackageName = context.getPackageManager().getInstallerPackageName(s);
        }
        catch (Exception ex) {
            UMCrashManager.reportCrash(context, ex);
            if (ex != null) {
                ULog.e("getAppInstaller:" + ex.getMessage());
            }
        }
        return installerPackageName;
    }
    
    public static int d(final Context context, final String s) {
        if (context == null) {
            return 0;
        }
        int uid = 0;
        try {
            final ApplicationInfo applicationInfo = context.getPackageManager().getPackageInfo(s, 0).applicationInfo;
            if (applicationInfo != null) {
                uid = applicationInfo.uid;
            }
        }
        catch (PackageManager.NameNotFoundException ex) {
            UMCrashManager.reportCrash(context, (Throwable)ex);
            if (ex != null) {
                ULog.e("getAppUid:" + ex.getMessage());
            }
            return uid;
        }
        return uid;
    }
    
    public static boolean a() {
        return h.a();
    }
    
    public static String b() {
        return new SimpleDateFormat().format(new Date());
    }
    
    public static float a(final Context context) {
        if (context == null) {
            return 0.0f;
        }
        float fontScale = 0.0f;
        final Configuration configuration = new Configuration();
        try {
            configuration.updateFrom(context.getResources().getConfiguration());
            if (configuration != null) {
                fontScale = configuration.fontScale;
            }
        }
        catch (Exception ex) {
            if (ex != null) {
                ULog.e("getFontSize:" + ex.getMessage());
            }
        }
        return fontScale;
    }
    
    public static List<ScanResult> b(final Context context) {
        if (context == null) {
            return null;
        }
        List scanResults = null;
        final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
        if (wifiManager == null) {
            return (List<ScanResult>)scanResults;
        }
        if (DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") && (DeviceConfig.checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION") || DeviceConfig.checkPermission(context, "android.permission.ACCESS_FINE_LOCATION"))) {
            scanResults = wifiManager.getScanResults();
            if (scanResults == null || scanResults.size() == 0) {
                return (List<ScanResult>)scanResults;
            }
        }
        return (List<ScanResult>)scanResults;
    }
    
    public static WifiInfo c(final Context context) {
        if (context == null) {
            return null;
        }
        WifiInfo connectionInfo = null;
        if (DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
            if (wifiManager != null) {
                connectionInfo = wifiManager.getConnectionInfo();
            }
        }
        return connectionInfo;
    }
    
    public static void d(final Context context) {
        if (context == null) {
            return;
        }
        final WifiInfo c = c(context);
        if (c == null) {
            return;
        }
        final c c2 = new c();
        c2.a = c.describeContents();
        c2.b = c.getBSSID();
        c2.c = c.getSSID();
        if (Build.VERSION.SDK_INT >= 21) {
            c2.d = c.getFrequency();
        }
        else {
            c2.d = -1;
        }
        if (c.getHiddenSSID()) {
            c2.e = 1;
        }
        else {
            c2.e = 0;
        }
        c2.f = c.getIpAddress();
        c2.g = c.getLinkSpeed();
        c2.h = DeviceConfig.getMac(context);
        c2.i = c.getNetworkId();
        c2.j = c.getRssi();
        c2.k = g(context);
        c2.l = System.currentTimeMillis();
        if (c != null) {
            try {
                boolean b = false;
                final JSONArray b2 = f.b(context);
                if (b2 != null && b2.length() > 0) {
                    for (int i = 0; i < b2.length(); ++i) {
                        final String optString = b2.optJSONObject(i).optString("ssid", (String)null);
                        if (optString != null && optString.equals(c2.c)) {
                            b = true;
                            break;
                        }
                    }
                }
                if (!b) {
                    f.a(context, c2);
                }
            }
            catch (Exception ex) {
                if (ex != null) {
                    ULog.e("wifiChange:" + ex.getMessage());
                }
            }
        }
    }
    
    public static JSONArray e(final Context context) {
        if (context == null) {
            return null;
        }
        return f.b(context);
    }
    
    public static void f(final Context context) {
        if (context == null) {
            return;
        }
        f.c(context);
    }
    
    public static int g(final Context context) {
        if (context == null) {
            return -1;
        }
        int wifiState = -1;
        if (DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
            if (wifiManager != null) {
                wifiState = wifiManager.getWifiState();
            }
        }
        return wifiState;
    }
    
    public static int h(final Context context) {
        if (context == null) {
            return 0;
        }
        final Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }
    
    public static int i(final Context context) {
        if (context == null) {
            return 0;
        }
        final Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }
    
    public static DisplayMetrics j(final Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDisplayMetrics();
    }
    
    private static boolean j() {
        final String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }
    
    public static long c() {
        long freeSpace = 0L;
        if (j() && Build.VERSION.SDK_INT >= 9) {
            freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
        }
        return freeSpace;
    }
    
    public static long d() {
        long totalSpace = 0L;
        if (j() && Build.VERSION.SDK_INT >= 9) {
            totalSpace = Environment.getExternalStorageDirectory().getTotalSpace();
        }
        return totalSpace;
    }
    
    public static String e() {
        return g.a("df");
    }
    
    public static String k(final Context context) {
        if (context == null) {
            return null;
        }
        String subscriberId = null;
        if (DeviceConfig.checkPermission(context, "android.permission.READ_PHONE_STATE")) {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (telephonyManager != null) {
                subscriberId = telephonyManager.getSubscriberId();
            }
        }
        return subscriberId;
    }
    
    public static String l(final Context context) {
        if (context == null) {
            return null;
        }
        CharSequence charSequence = null;
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        if (DeviceConfig.checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
            if (Build.VERSION.SDK_INT < 26) {
                charSequence = telephonyManager.getDeviceId();
            }
            else {
                try {
                    charSequence = t(context);
                    if (TextUtils.isEmpty(charSequence)) {
                        charSequence = telephonyManager.getDeviceId();
                    }
                }
                catch (Throwable t) {}
            }
        }
        return (String)charSequence;
    }
    
    private static String t(final Context context) {
        if (context == null) {
            return null;
        }
        String s = null;
        try {
            final Object invoke = Class.forName("android.telephony.TelephonyManager").getMethod("getMeid", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
            if (null != invoke && invoke instanceof String) {
                s = (String)invoke;
            }
        }
        catch (Exception ex) {
            if (ex != null) {
                ULog.e("meid:" + ex.getMessage());
            }
        }
        return s;
    }
    
    public static List<InputMethodInfo> m(final Context context) {
        if (context == null) {
            return null;
        }
        final InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService("input_method");
        if (inputMethodManager == null) {
            return null;
        }
        return (List<InputMethodInfo>)inputMethodManager.getInputMethodList();
    }
    
    public static void n(final Context context) {
        if (context == null) {
            return;
        }
        try {
            if (DeviceConfig.checkPermission(context, "android.permission.BLUETOOTH")) {
                final BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                final b b = new b();
                if (defaultAdapter.isEnabled()) {
                    b.b = defaultAdapter.getState();
                    if (Build.VERSION.SDK_INT < 23) {
                        b.a = defaultAdapter.getAddress();
                    }
                    else {
                        b.a = a(defaultAdapter);
                    }
                    b.c = defaultAdapter.getName();
                    UMWorkDispatch.sendEvent(context, 32773, com.umeng.commonsdk.internal.b.a(context).a(), b);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static JSONObject o(final Context context) {
        if (context == null) {
            return null;
        }
        return f.a(context);
    }
    
    private static String a(final BluetoothAdapter obj) {
        if (obj == null) {
            return null;
        }
        final Class<? extends BluetoothAdapter> class1 = obj.getClass();
        try {
            final Class<?> forName = Class.forName("android.bluetooth.IBluetooth");
            final Field declaredField = class1.getDeclaredField("mService");
            declaredField.setAccessible(true);
            final Method method = forName.getMethod("getAddress", (Class[])new Class[0]);
            method.setAccessible(true);
            return (String)method.invoke(declaredField.get(obj), new Object[0]);
        }
        catch (Exception ex) {
            return obj.getAddress();
        }
    }
    
    public static List<InnerClass_a> p(final Context context) {
        if (context == null) {
            return null;
        }
        final ArrayList list = new ArrayList();
        try {
            final File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/");
            if (file != null && file.isDirectory()) {
                final String[] list2 = file.list();
                if (list2 != null && list2.length > 0) {
                    for (final String a : list2) {
                        if (a != null && !a.startsWith(".")) {
                            final com.umeng.commonsdk.internal.utils.a.InnerClass_a innerClassA2 = new com.umeng.commonsdk.internal.utils.a.InnerClass_a();
                            innerClassA2.a = a;
                            innerClassA2.b = e(context, a);
                            list.add(innerClassA2);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            if (ex != null) {
                ULog.e("getAppList:" + ex.getMessage());
            }
        }
        return list;
    }
    
    private static String e(final Context context, final String s) {
        if (context == null) {
            return null;
        }
        String s2 = null;
        try {
            final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(s, 128);
            if (applicationInfo != null) {
                s2 = (String)applicationInfo.loadLabel(context.getPackageManager());
            }
        }
        catch (Exception ex) {
            if (ex != null) {
                ULog.e("getLabel:" + ex.getMessage());
            }
        }
        return s2;
    }
    
    public static ActivityManager.MemoryInfo q(final Context context) {
        if (context == null) {
            return null;
        }
        final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
        if (activityManager == null) {
            return null;
        }
        final ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }
    
    public static long f() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }
    
    public static String r(final Context context) {
        if (context == null) {
            return null;
        }
        return null;
    }
    
    public static String s(final Context context) {
        return null;
    }
    
    public static String g() {
        String s = null;
        try {
            final Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            s = declaredMethod.invoke(null, "net.hostname").toString();
            if (s != null && !s.equalsIgnoreCase("")) {
                s = HelperUtils.getUmengMD5(s);
            }
        }
        catch (Exception ex) {
            if (ex != null) {
                ULog.e("getHostName:" + ex.getMessage());
            }
        }
        return s;
    }
    
    public static long h() {
        long n = 0L;
        try {
            final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            n = statFs.getBlockSize() * (long)statFs.getBlockCount();
        }
        catch (Exception ex) {}
        return n;
    }
    
    public static long i() {
        long n = 0L;
        try {
            final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            n = statFs.getBlockSize() * (long)statFs.getAvailableBlocks();
        }
        catch (Exception ex) {}
        return n;
    }
    
    public static class c
    {
        public int a;
        public String b;
        public String c;
        public int d;
        public int e;
        public int f;
        public int g;
        public String h;
        public int i;
        public int j;
        public int k;
        public long l;
    }
    
    public static class b
    {
        public String a;
        public int b;
        public String c;
        
        public b() {
            this.a = null;
            this.b = -1;
            this.c = null;
        }
    }
    
    public static class InnerClass_a
    {
        public String a;
        public String b;
    }
}
