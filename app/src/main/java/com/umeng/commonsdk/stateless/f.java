package com.umeng.commonsdk.stateless;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.internal.crash.*;
import java.nio.channels.*;
import java.nio.*;
import android.text.*;
import java.util.zip.*;
import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.spec.*;
import java.security.*;
import java.util.*;

public class f
{
    public static int a;
    private static final byte[] b;
    private static Object c;
    
    public static boolean a(final Context context, final String str, final String str2, final byte[] b) {
        boolean b2 = false;
        if (context != null) {
            FileOutputStream fileOutputStream = null;
            try {
                synchronized (f.c) {
                    ULog.i("walle", "[stateless] begin write envelope, thread is " + Thread.currentThread());
                    final File file = new File(context.getFilesDir() + "/" + "stateless");
                    if (!file.isDirectory()) {
                        file.mkdir();
                    }
                    final File file2 = new File(file.getPath() + "/" + str);
                    if (!file2.isDirectory()) {
                        file2.mkdir();
                    }
                    final File file3 = new File(file2.getPath() + "/" + str2);
                    if (!file3.exists()) {
                        file3.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(file3);
                    fileOutputStream.write(b);
                    fileOutputStream.close();
                    fileOutputStream = null;
                    b2 = true;
                }
            }
            catch (IOException ex) {
                ULog.i("walle", "[stateless] write envelope, e_enum is " + ex.getMessage());
                UMCrashManager.reportCrash(context, ex);
            }
            catch (Throwable t) {
                ULog.i("walle", "[stateless] write envelope, e_enum is " + t.getMessage());
                UMCrashManager.reportCrash(context, t);
            }
            finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    }
                    catch (IOException ex2) {}
                }
                ULog.i("walle", "[stateless] end write envelope, thread id " + Thread.currentThread());
            }
        }
        return b2;
    }
    
    public static byte[] a(final String name) throws IOException {
        FileChannel channel = null;
        synchronized (f.c) {
            ULog.i("walle", "[stateless] begin read envelope, thread is " + Thread.currentThread());
            try {
                channel = new RandomAccessFile(name, "r").getChannel();
                final MappedByteBuffer load = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size()).load();
                System.out.println(load.isLoaded());
                final byte[] dst = new byte[(int)channel.size()];
                if (load.remaining() > 0) {
                    load.get(dst, 0, load.remaining());
                }
                ULog.i("walle", "[stateless] end read envelope, thread id " + Thread.currentThread());
                return dst;
            }
            catch (IOException ex) {
                ULog.i("walle", "[stateless] write envelope, e_enum is " + ex.getMessage());
                throw ex;
            }
            finally {
                if (channel != null) {
                    try {
                        channel.close();
                    }
                    catch (IOException ex2) {}
                }
            }
        }
    }
    
    public static File a(final Context context) {
        File file = null;
        try {
            synchronized (f.c) {
                ULog.i("walle", "get last envelope begin, thread is " + Thread.currentThread());
                if (context != null && context.getApplicationContext() != null) {
                    final String string = context.getApplicationContext().getFilesDir() + "/" + "stateless";
                    if (!TextUtils.isEmpty((CharSequence)string)) {
                        final File file2 = new File(string);
                        if (file2 != null && file2.isDirectory()) {
                            final File[] listFiles = file2.listFiles();
                            if (listFiles != null && listFiles.length > 0) {
                                for (int i = 0; i < listFiles.length; ++i) {
                                    final File file3 = listFiles[i];
                                    if (file3 != null && file3.isDirectory()) {
                                        final File[] listFiles2 = file3.listFiles();
                                        if (listFiles2 != null && listFiles2.length > 0) {
                                            Arrays.sort(listFiles2, new Comparator<File>() {
                                                @Override
                                                public int compare(File o1, File o2) {
                                                    return a(o1, o2);
                                                }

                                                public int a(final File file, final File file2) {
                                                    final long n = file.lastModified() - file2.lastModified();
                                                    if (n > 0L) {
                                                        return 1;
                                                    }
                                                    if (n == 0L) {
                                                        return 0;
                                                    }
                                                    return -1;
                                                }
                                            });
                                            final File file4 = listFiles2[0];
                                            if (file4 != null && (file == null || file.lastModified() > file4.lastModified())) {
                                                file = file4;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                ULog.i("walle", "get last envelope end, thread is " + Thread.currentThread());
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(context, t);
        }
        return file;
    }
    
    public static void a(final Context context, final String pathname, final int n) {
        try {
            if (pathname == null) {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            final File file = new File(pathname);
            if (!file.isDirectory()) {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            synchronized (f.c) {
                final File[] listFiles = file.listFiles();
                ULog.i("AmapLBS", "[lbs-build] delete file begin " + listFiles.length + ", thread is " + Thread.currentThread());
                if (listFiles != null && listFiles.length >= n) {
                    ULog.i("AmapLBS", "[lbs-build] file size >= max");
                    final ArrayList<Object> list = new ArrayList<Object>();
                    for (final File e : listFiles) {
                        if (e != null) {
                            list.add(e);
                        }
                    }
                    if (list != null && list.size() >= n) {
                        Collections.sort(list, new Comparator() {
                            public int a(File arg6, File arg7) {
                                if(arg6 != null && arg7 != null && arg6.lastModified() < arg7.lastModified()) {
                                    return -1;
                                }

                                if(arg6 != null && arg7 != null && arg6.lastModified() == arg7.lastModified()) {
                                    return 0;
                                }

                                return 1;
                            }

                            public int compare(Object arg1, Object arg2) {
                                return this.a(((File)arg1), ((File)arg2));
                            }
                        });
                        if (ULog.DEBUG) {
                            for (int j = 0; j < list.size(); ++j) {
                                ULog.i("AmapLBS", "[lbs-build] overrun native file is " + ((File)list.get(j)).getPath());
                            }
                        }
                        for (int k = 0; k <= list.size() - n; ++k) {
                            if (list != null && list.get(k) != null) {
                                ULog.i("AmapLBS", "[lbs-build] overrun remove file is " + ((File)list.get(k)).getPath());
                                try {
                                    ((File)list.get(k)).delete();
                                    list.remove(k);
                                }
                                catch (Exception ex) {}
                            }
                        }
                    }
                }
                else {
                    ULog.i("AmapLBS", "[lbs-build] file size < max");
                }
                ULog.i("AmapLBS", "[lbs-build] delete file end " + listFiles.length + ", thread is " + Thread.currentThread());
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(context, t);
        }
    }
    
    public static boolean a(final long n, final long n2) {
        return n > n2;
    }
    
    public static byte[] a(final byte[] input) throws IOException {
        if (input == null || input.length <= 0) {
            return null;
        }
        final Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();
        final byte[] array = new byte[8192];
        f.a = 0;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                final int deflate = deflater.deflate(array);
                f.a += deflate;
                byteArrayOutputStream.write(array, 0, deflate);
            }
            deflater.end();
        }
        finally {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public static byte[] a(final byte[] input, final byte[] key) throws Exception {
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, new SecretKeySpec(key, "AES"), new IvParameterSpec(f.b));
        return instance.doFinal(input);
    }
    
    public static byte[] b(final byte[] input) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(input);
            return instance.digest();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String c(final byte[] array) {
        if (array == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(String.format("%02X", array[i]));
        }
        return sb.toString().toLowerCase(Locale.US);
    }
    
    static {
        b = new byte[] { 10, 1, 11, 5, 4, 15, 7, 9, 23, 3, 1, 6, 8, 12, 13, 91 };
        f.c = new Object();
    }
}
