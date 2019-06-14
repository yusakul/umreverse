package com.umeng.commonsdk.framework;

import android.content.*;
import android.app.*;
import com.umeng.commonsdk.internal.crash.*;

import android.content.pm.PackageManager;
import android.os.*;
import android.net.*;
import android.os.Process;
import android.text.*;
import java.util.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.statistics.internal.*;
import java.io.*;
import java.nio.channels.*;
import java.nio.*;

public class UMFrUtils
{
    private static final String KEY_LAST_SUCC_BUILD_TIME = "last_successful_build_time";
    private static final String KEY_LAST_INSTANT_SUCC_BUILD_TIME = "last_instant_build_time";
    private static Object mEnvelopeBuildTimeLock;
    private static String mDefaultEnvelopeDir;
    private static String mDefaultEnvelopeDirPath;
    private static Object mEnvelopeFileLock;
    
    public static String getCurrentProcessName(final Context context) {
        String processName = "";
        try {
            final int myPid = Process.myPid();
            final ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            if (activityManager != null) {
                final List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
                    for (final ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                        if (runningAppProcessInfo.pid == myPid) {
                            processName = runningAppProcessInfo.processName;
                            break;
                        }
                    }
                }
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(context.getApplicationContext(), t);
        }
        return processName;
    }
    
    public static String getSubProcessName(final Context context) {
        String s = "";
        try {
            final String currentProcessName = getCurrentProcessName(context);
            final int index = currentProcessName.indexOf(":");
            if (index >= 0) {
                s = currentProcessName.substring(index + 1);
            }
            if (index < 0) {
                s = currentProcessName.substring(context.getPackageName().length() + 1, currentProcessName.length());
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(context.getApplicationContext(), t);
        }
        return s;
    }
    
    private static boolean checkPermission(final Context obj, final String s) {
        boolean b = false;
        if (obj != null) {
            final Context applicationContext = obj.getApplicationContext();
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    b = ((int)Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(obj, s) == 0);
                }
                catch (Throwable t) {
                    b = false;
                    UMCrashManager.reportCrash(applicationContext, t);
                }
            }
            else {
                try {
                    if (applicationContext.getPackageManager().checkPermission(s, applicationContext.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                        b = true;
                    }
                }
                catch (Throwable t2) {
                    b = false;
                    UMCrashManager.reportCrash(applicationContext, t2);
                }
            }
        }
        return b;
    }
    
    public static boolean isOnline(final Context context) {
        try {
            if (checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager != null) {
                    final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        return activeNetworkInfo.isConnectedOrConnecting();
                    }
                }
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(context.getApplicationContext(), t);
        }
        return false;
    }
    
    public static int envelopeFileNumber(final Context context) {
        if (context != null) {
            try {
                final File file = new File(getEnvelopeDirPath(context));
                synchronized (UMFrUtils.mEnvelopeFileLock) {
                    if (file.isDirectory()) {
                        final String[] list = file.list();
                        if (list != null) {
                            return list.length;
                        }
                    }
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(context, t);
            }
            return 0;
        }
        return 0;
    }
    
    private static long getDistanceDays(final long n, final long n2) {
        long n3;
        if (n < n2) {
            n3 = n2 - n;
        }
        else {
            n3 = n - n2;
        }
        return n3 / 86400000L;
    }
    
    public static void removeRedundantEnvelopeFiles(final Context context, final int n) {
        final File file = new File(getEnvelopeDirPath(context));
        synchronized (UMFrUtils.mEnvelopeFileLock) {
            final File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length <= n) {
                return;
            }
            Arrays.sort(listFiles, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return this.a(((File)o1), ((File)o2));
                }

                public int a(final File file, final File file2) {
                    final String name = file.getName();
                    final String name2 = file2.getName();
                    final String access$000 = getCreateTimeFromFileName(name);
                    final String access$2 = getCreateTimeFromFileName(name2);
                    if (TextUtils.isEmpty((CharSequence)access$000) || TextUtils.isEmpty((CharSequence)access$2)) {
                        return 1;
                    }
                    long longValue;
                    long longValue2;
                    try {
                        longValue = Long.valueOf(access$000);
                        longValue2 = Long.valueOf(access$2);
                    }
                    catch (NumberFormatException ex) {
                        UMCrashManager.reportCrash(context, ex);
                        return 1;
                    }
                    final long n = longValue - longValue2;
                    if (n > 0L) {
                        return 1;
                    }
                    if (n == 0L) {
                        return 0;
                    }
                    return -1;
                }
            });
            if (listFiles.length > n) {
                try {
                    for (int i = 0; i < listFiles.length - n; ++i) {
                        if (!listFiles[i].delete()) {
                            ULog.d("--->>> remove [" + i + "] file fail.");
                        }
                    }
                }
                catch (Throwable t) {
                    UMCrashManager.reportCrash(context, t);
                }
            }
        }
    }
    
    private static String getCreateTimeFromFileName(final String s) {
        final Context appContext = UMModuleRegister.getAppContext();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final int index = s.indexOf(95);
            final int index2 = s.indexOf(95, index + 1);
            String substring;
            try {
                substring = s.substring(index + 1, index2);
            }
            catch (IndexOutOfBoundsException ex) {
                UMCrashManager.reportCrash(appContext, ex);
                return "";
            }
            return substring;
        }
        return "";
    }
    
    public static File getEnvelopeFile(final Context context) {
        if (context == null) {
            return null;
        }
        final File file = new File(getEnvelopeDirPath(context));
        synchronized (UMFrUtils.mEnvelopeFileLock) {
            final File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                return null;
            }
            Arrays.sort(listFiles, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return this.a(((File)o1), ((File)o2));
                }

                public int a(final File file, final File file2) {
                    final String name = file.getName();
                    final String name2 = file2.getName();
                    final String access$000 = getCreateTimeFromFileName(name);
                    final String access$2 = getCreateTimeFromFileName(name2);
                    if (TextUtils.isEmpty((CharSequence)access$000) || TextUtils.isEmpty((CharSequence)access$2)) {
                        return 1;
                    }
                    long longValue;
                    long longValue2;
                    try {
                        longValue = Long.valueOf(access$000);
                        longValue2 = Long.valueOf(access$2);
                    }
                    catch (NumberFormatException ex) {
                        UMCrashManager.reportCrash(context, ex);
                        return 1;
                    }
                    final long n = longValue - longValue2;
                    if (n > 0L) {
                        return 1;
                    }
                    if (n == 0L) {
                        return 0;
                    }
                    return -1;
                }
            });
            return listFiles[0];
        }
    }
    
    public static void syncLegacyEnvelopeIfNeeded(final Context context) {
        if (context == null) {
            return;
        }
        try {
            final String legacyEnvelopeDir = getLegacyEnvelopeDir(context);
            if (TextUtils.isEmpty((CharSequence)legacyEnvelopeDir)) {
                return;
            }
            if (legacyEnvelopeDir.equals(UMFrUtils.mDefaultEnvelopeDir)) {
                return;
            }
            final File file = new File(context.getFilesDir().getAbsolutePath() + "/." + legacyEnvelopeDir);
            if (!file.exists()) {
                return;
            }
            final File[] listFiles = file.listFiles();
            if (listFiles != null) {
                if (listFiles.length != 0) {
                    try {
                        final String envelopeDirPath = getEnvelopeDirPath(context);
                        for (int i = 0; i < listFiles.length; ++i) {
                            listFiles[i].renameTo(new File(envelopeDirPath + "/" + listFiles[i].getName()));
                        }
                        if (file.isDirectory()) {
                            file.delete();
                        }
                    }
                    catch (Throwable t) {
                        UMCrashManager.reportCrash(context, t);
                    }
                    return;
                }
            }
            try {
                if (file.isDirectory()) {
                    file.delete();
                }
            }
            catch (Throwable t2) {
                UMCrashManager.reportCrash(context, t2);
            }
        }
        catch (Throwable t3) {
            UMCrashManager.reportCrash(context, t3);
        }
    }
    
    public static String getLegacyEnvelopeDir(final Context context) {
        final ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            final List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                ULog.d("--->>> getEnvelopeDir: can't get process name, use default envelope directory.");
                return UMFrUtils.mDefaultEnvelopeDir;
            }
            if (runningAppProcesses.size() == 0) {
                return UMFrUtils.mDefaultEnvelopeDir;
            }
            try {
                for (final ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == Process.myPid()) {
                        final String replace = runningAppProcessInfo.processName.replace(':', '_');
                        ULog.d("--->>> getEnvelopeDir: use current process name as envelope directory.");
                        return replace;
                    }
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(context, t);
            }
        }
        return UMFrUtils.mDefaultEnvelopeDir;
    }
    
    public static String getEnvelopeDirPath(final Context context) {
        synchronized (UMFrUtils.mEnvelopeFileLock) {
            try {
                if (UMFrUtils.mDefaultEnvelopeDirPath == null) {
                    UMFrUtils.mDefaultEnvelopeDirPath = context.getFilesDir().getAbsolutePath() + "/." + UMFrUtils.mDefaultEnvelopeDir;
                }
                final File file = new File(UMFrUtils.mDefaultEnvelopeDirPath);
                if (!file.exists() && !file.mkdir()) {
                    ULog.d("--->>> Create Envelope Directory failed!!!");
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(context, t);
            }
            return UMFrUtils.mDefaultEnvelopeDirPath;
        }
    }
    
    public static long getLastSuccessfulBuildTime(final Context context) {
        synchronized (UMFrUtils.mEnvelopeBuildTimeLock) {
            return PreferenceWrapper.getDefault(context).getLong("last_successful_build_time", 0L);
        }
    }
    
    public static long getLastInstantBuildTime(final Context context) {
        synchronized (UMFrUtils.mEnvelopeBuildTimeLock) {
            return PreferenceWrapper.getDefault(context).getLong("last_instant_build_time", 0L);
        }
    }
    
    private static void updateLastSuccessfulBuildTime(final Context context) {
        synchronized (UMFrUtils.mEnvelopeBuildTimeLock) {
            PreferenceWrapper.getDefault(context).edit().putLong("last_successful_build_time", System.currentTimeMillis()).commit();
        }
    }
    
    private static void updateLastInstantBuildTime(final Context context) {
        synchronized (UMFrUtils.mEnvelopeBuildTimeLock) {
            PreferenceWrapper.getDefault(context).edit().putLong("last_instant_build_time", System.currentTimeMillis()).commit();
        }
    }
    
    public static int saveEnvelopeFile(final Context context, final String str, final byte[] b) {
        if (b == null) {
            return 101;
        }
        final File file = new File(getEnvelopeDirPath(context) + "/" + str);
        synchronized (UMFrUtils.mEnvelopeFileLock) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(b);
                fileOutputStream.close();
                fileOutputStream = null;
            }
            catch (IOException ex) {
                UMCrashManager.reportCrash(context, ex);
                return 101;
            }
            finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    }
                    catch (Throwable t) {
                        UMCrashManager.reportCrash(context, t);
                    }
                }
            }
            final boolean a = com.umeng.commonsdk.statistics.internal.a.a(context).a(str);
            final boolean b2 = com.umeng.commonsdk.statistics.internal.a.a(context).b(str);
            if (a) {
                updateLastSuccessfulBuildTime(context);
            }
            if (b2) {
                updateLastInstantBuildTime(context);
            }
            return 0;
        }
    }
    
    public static boolean removeEnvelopeFile(final File file) {
        final Context appContext = UMModuleRegister.getAppContext();
        synchronized (UMFrUtils.mEnvelopeFileLock) {
            try {
                if (file != null && file.exists()) {
                    return file.delete();
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(appContext, t);
            }
            return true;
        }
    }
    
    public static byte[] toByteArray(final String name) throws IOException {
        final Context appContext = UMModuleRegister.getAppContext();
        FileChannel channel = null;
        synchronized (UMFrUtils.mEnvelopeFileLock) {
            try {
                channel = new RandomAccessFile(name, "r").getChannel();
                final MappedByteBuffer load = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size()).load();
                System.out.println(load.isLoaded());
                final byte[] dst = new byte[(int)channel.size()];
                if (load.remaining() > 0) {
                    load.get(dst, 0, load.remaining());
                }
                return dst;
            }
            catch (IOException ex) {
                UMCrashManager.reportCrash(appContext, ex);
                throw ex;
            }
            finally {
                try {
                    channel.close();
                }
                catch (Throwable t) {
                    UMCrashManager.reportCrash(appContext, t);
                }
            }
        }
    }
    
    public static boolean hasEnvelopeFile(final Context context, final UMLogDataProtocol.UMBusinessType umBusinessType) {
        String prefix = "InnerClass_a";
        if (umBusinessType == UMLogDataProtocol.UMBusinessType.U_INTERNAL) {
            prefix = "checksum";
        }
        final File file = new File(getEnvelopeDirPath(context));
        synchronized (UMFrUtils.mEnvelopeFileLock) {
            try {
                final File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    return false;
                }
                final File[] array = listFiles;
                for (int length = array.length, i = 0; i < length; ++i) {
                    if (array[i].getName().startsWith(prefix)) {
                        return true;
                    }
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(context, t);
            }
            return false;
        }
    }
    
    static {
        UMFrUtils.mEnvelopeBuildTimeLock = new Object();
        UMFrUtils.mDefaultEnvelopeDir = "envelope";
        UMFrUtils.mDefaultEnvelopeDirPath = null;
        UMFrUtils.mEnvelopeFileLock = new Object();
    }
}
