package com.umeng.commonsdk.internal.utils;

import android.text.*;
import com.umeng.commonsdk.framework.*;
import android.os.*;
import java.io.*;
import java.nio.*;
import com.umeng.commonsdk.statistics.common.*;
import java.nio.channels.*;
import android.content.*;

public class i
{
    private static final String a = "/.um/.umm.dat";
    private static final String b = "/.uxx/.cca.dat";
    private static final String c = "/.cc/.adfwe.dat";
    private static final String d = "/.InnerClass_a.dat";
    private static final String e = "umdat";
    
    public static void a(final Context context, final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final String g = g(context);
            if (TextUtils.isEmpty((CharSequence)g) || !s.equals(g)) {
                b(context, s);
            }
        }
    }
    
    public static String a(final Context context) {
        String s = b(context);
        if (s == null || s.equals("")) {
            s = g(context);
        }
        if (s == null || s.equals("")) {
            s = c(context);
        }
        if (s == null || s.equals("")) {
            s = d(context);
        }
        if (s == null || s.equals("")) {
            s = e(context);
        }
        if (s == null || s.equals("")) {
            s = f(context);
        }
        return s;
    }
    
    public static String b(final Context context) {
        return h(context);
    }
    
    public static String c(final Context context) {
        return c(context, "/.um/.umm.dat");
    }
    
    public static String d(final Context context) {
        return c(context, "/.uxx/.cca.dat");
    }
    
    public static String e(final Context context) {
        return c(context, "/.cc/.adfwe.dat");
    }
    
    public static String f(final Context context) {
        return c(context, "/.InnerClass_a.dat");
    }
    
    public static String g(final Context context) {
        return i(context);
    }
    
    private static String h(final Context context) {
        return com.umeng.commonsdk.framework.a.a(context, "umtt", null);
    }
    
    public static void b(final Context context, final String s) {
        a(context, s, "/.um/.umm.dat");
        a(context, s, "/.uxx/.cca.dat");
        a(context, s, "/.cc/.adfwe.dat");
        a(context, s, "/.InnerClass_a.dat");
        d(context, s);
    }
    
    private static void a(final Context context, final String anObject, final String s) {
        if (DeviceConfig.checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            try {
                final String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState != null && externalStorageState.equalsIgnoreCase("mounted")) {
                    final String c = c(context, s);
                    if (c == null || !c.equals(anObject)) {
                        final File file = new File(Environment.getExternalStorageDirectory() + s);
                        if (file.getParentFile() != null && !file.getParentFile().exists()) {
                            file.getParentFile().mkdir();
                        }
                        final RandomAccessFile randomAccessFile = new RandomAccessFile(Environment.getExternalStorageDirectory() + s, "rw");
                        randomAccessFile.setLength(anObject.getBytes().length);
                        final FileChannel channel = randomAccessFile.getChannel();
                        final FileLock tryLock = channel.tryLock();
                        final ByteBuffer allocate = ByteBuffer.allocate(1024);
                        allocate.clear();
                        allocate.put(anObject.getBytes());
                        allocate.flip();
                        while (allocate.hasRemaining()) {
                            channel.write(allocate);
                        }
                        channel.force(true);
                        if (tryLock != null) {
                            tryLock.release();
                        }
                        channel.close();
                    }
                }
            }
            catch (Exception ex) {
                if (ex != null) {
                    ULog.e("saveFileUmtt:" + ex.getMessage());
                }
            }
        }
    }
    
    private static String c(final Context context, final String s) {
        String string = null;
        try {
            if (DeviceConfig.checkPermission(context, "android.permission.READ_EXTERNAL_STORAGE")) {
                final String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState != null && externalStorageState.equalsIgnoreCase("mounted")) {
                    if (!new File(Environment.getExternalStorageDirectory() + s).exists()) {
                        return string;
                    }
                    final FileChannel channel = new RandomAccessFile(Environment.getExternalStorageDirectory() + s, "rw").getChannel();
                    final FileLock tryLock = channel.tryLock();
                    final StringBuilder sb = new StringBuilder();
                    final ByteBuffer allocate = ByteBuffer.allocate(1024);
                    allocate.clear();
                    while (channel.read(allocate) != -1) {
                        final byte[] bytes = new byte[allocate.position()];
                        for (int i = 0; i < allocate.position(); ++i) {
                            bytes[i] = allocate.get(i);
                        }
                        sb.append(new String(bytes));
                        allocate.clear();
                    }
                    if (channel != null) {
                        tryLock.release();
                    }
                    channel.close();
                    string = sb.toString();
                }
            }
        }
        catch (Exception ex) {
            if (ex != null) {
                ULog.e("getFileUmtt:" + ex.getMessage());
            }
        }
        return string;
    }
    
    private static void d(final Context context, final String anObject) {
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("umdat", 0);
        if (sharedPreferences != null) {
            final String string = sharedPreferences.getString("u", (String)null);
            if (string == null || !string.equals(anObject)) {
                sharedPreferences.edit().putString("u", anObject).commit();
            }
        }
    }
    
    private static String i(final Context context) {
        String string = null;
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("umdat", 0);
        if (sharedPreferences != null) {
            string = sharedPreferences.getString("u", (String)null);
        }
        return string;
    }
}
