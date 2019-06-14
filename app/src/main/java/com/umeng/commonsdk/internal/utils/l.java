package com.umeng.commonsdk.internal.utils;

import org.json.*;
import com.umeng.commonsdk.internal.crash.*;
import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import android.text.*;
import java.io.*;

public class l
{
    public static final String a = "UM_PROBE_DATA";
    public static final String b = "_dsk_s";
    public static final String c = "_thm_z";
    public static final String d = "_gdf_r";
    private static Object e;
    
    public static String a(final Context context) {
        String string = null;
        try {
            final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("UM_PROBE_DATA", 0);
            if (sharedPreferences != null) {
                final JSONObject jsonObject = new JSONObject();
                synchronized (l.e) {
                    jsonObject.put("_dsk_s", (Object)sharedPreferences.getString("_dsk_s", ""));
                    jsonObject.put("_thm_z", (Object)sharedPreferences.getString("_thm_z", ""));
                    jsonObject.put("_gdf_r", (Object)sharedPreferences.getString("_gdf_r", ""));
                }
                string = jsonObject.toString();
            }
        }
        catch (Exception ex) {
            UMCrashManager.reportCrash(context, ex);
        }
        return string;
    }
    
    public static void b(final Context context) {
        if (!c(context)) {
            new Thread() {
                final /* synthetic */ String[] a = { "unknown", "unknown", "unknown" };
                
                @Override
                public void run() {
                    super.run();
                    try {
                        this.a[0] = l.c();
                        this.a[1] = l.a();
                        this.a[2] = l.b();
                        ULog.i("diskType = " + this.a[0] + "; ThremalZone = " + this.a[1] + "; GoldFishRc = " + this.a[2]);
                        b(context, this.a);
                    }
                    catch (Throwable t) {
                        UMCrashManager.reportCrash(context, t);
                    }
                }
            }.start();
        }
    }
    
    private static void b(final Context context, final String[] array) {
        if (context == null) {
            return;
        }
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("UM_PROBE_DATA", 0);
        if (sharedPreferences != null) {
            synchronized (l.e) {
                sharedPreferences.edit().putString("_dsk_s", array[0]).putString("_thm_z", array[1]).putString("_gdf_r", array[2]).commit();
            }
        }
    }
    
    public static boolean c(final Context context) {
        boolean b = false;
        if (context != null) {
            final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("UM_PROBE_DATA", 0);
            if (sharedPreferences != null && !TextUtils.isEmpty((CharSequence)sharedPreferences.getString("_dsk_s", ""))) {
                b = true;
            }
        }
        return b;
    }
    
    public static int a(final String command, final String s) throws IOException {
        int n = -1;
        final Process exec = Runtime.getRuntime().exec(command);
        String line;
        while ((line = new BufferedReader(new InputStreamReader(exec.getInputStream())).readLine()) != null) {
            if (line.contains(s)) {
                n = 1;
                break;
            }
        }
        try {
            if (exec.waitFor() != 0) {
                n = -1;
            }
        }
        catch (InterruptedException ex) {
            n = -1;
        }
        return n;
    }
    
    public static String a() {
        String s = "unknown";
        final String s2 = "ls /sys/class/thermal";
        int a = -1;
        try {
            a = a(s2, "thermal_zone");
        }
        catch (Throwable t) {}
        if (a > 0) {
            s = "thermal_zone";
        }
        else if (a < 0) {
            s = "noper";
        }
        return s;
    }
    
    public static String b() {
        String s = "unknown";
        final String s2 = "ls /";
        int a = -1;
        try {
            a = a(s2, "goldfish");
        }
        catch (Throwable t) {}
        if (a > 0) {
            s = "goldfish";
        }
        else if (a < 0) {
            s = "noper";
        }
        return s;
    }
    
    public static String c() {
        String s = "unknown";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/diskstats"));
            final String s2 = "mmcblk";
            final String s3 = "sda";
            final String s4 = "mtd";
            if (bufferedReader != null) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains(s2)) {
                        s = "mmcblk";
                        break;
                    }
                    if (line.contains(s3)) {
                        s = "sda";
                        break;
                    }
                    if (line.contains(s4)) {
                        s = "mtd";
                        break;
                    }
                }
            }
        }
        catch (Throwable t) {
            s = "noper";
        }
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        catch (Throwable t2) {}
        return s;
    }
    
    static {
        l.e = new Object();
    }
}
