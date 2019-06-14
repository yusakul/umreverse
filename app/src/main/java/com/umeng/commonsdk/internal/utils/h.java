package com.umeng.commonsdk.internal.utils;

import android.os.*;
import java.io.*;

public class h
{
    public static boolean a() {
        return b() || c() || d() || e();
    }
    
    private static boolean b() {
        final String tags = Build.TAGS;
        return tags != null && tags.contains("test-keys");
    }
    
    private static boolean c() {
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
        }
        catch (Exception ex) {}
        try {
            if (new File("/system/app/Kinguser.apk").exists()) {
                return true;
            }
        }
        catch (Exception ex2) {}
        return false;
    }
    
    private static boolean d() {
        return new e().a(e.a.a) != null;
    }
    
    private static boolean e() {
        final String[] array = { "/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/" };
        for (int length = array.length, i = 0; i < length; ++i) {
            if (new File(array[i] + "su").exists()) {
                return true;
            }
        }
        return false;
    }
}
