package com.umeng.commonsdk.statistics.common;

import android.text.*;
import java.security.*;
import java.math.*;
import java.io.*;

public class HelperUtils
{
    public static final String LINE_SEPARATOR;
    private static final String TAG = "helper";
    
    public static String subStr(final String s, int n) {
        String s2 = "";
        try {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                final int n2 = n;
                s2 = s.substring(0, (s.length() < n) ? s.length() : n);
                for (int i = s2.getBytes("UTF-8").length; i > n2; i = s2.getBytes("UTF-8").length) {
                    final int n3 = --n;
                    s2 = s.substring(0, (n3 > s.length()) ? s.length() : n3);
                }
                return s2;
            }
        }
        catch (Exception ex) {
            MLog.e(ex);
        }
        return s2;
    }
    
    public static boolean checkStrLen(final String s, final int n) {
        try {
            if (!TextUtils.isEmpty((CharSequence)s) && s.length() <= n) {
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static String MD5(final String s) {
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
    
    public static String getUmengMD5(final String s) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(s.getBytes());
            final byte[] digest = instance.digest();
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; ++i) {
                sb.append(Integer.toHexString(0xFF & digest[i]));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException ex) {
            MLog.i("helper", "getMD5 error", ex);
            return "";
        }
    }
    
    public static String getMD5(final String s) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(s.getBytes());
            final byte[] digest = instance.digest();
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; ++i) {
                sb.append(Integer.toHexString((digest[i] & 0xFF) | 0xFFFFFF00).substring(6));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException ex) {
            MLog.i("helper", "getMD5 error", ex);
            return "";
        }
    }
    
    public static String getFileMD5(final File file) {
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
    
    public static String readStreamToString(final InputStream in) throws IOException {
        final InputStreamReader inputStreamReader = new InputStreamReader(in);
        final char[] array = new char[1024];
        final StringWriter stringWriter = new StringWriter();
        int read;
        while (-1 != (read = inputStreamReader.read(array))) {
            stringWriter.write(array, 0, read);
        }
        return stringWriter.toString();
    }
    
    public static byte[] readStreamToByteArray(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[1024];
        int read;
        while (-1 != (read = inputStream.read(array))) {
            byteArrayOutputStream.write(array, 0, read);
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void writeFile(final File file, final byte[] b) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(b);
            fileOutputStream.flush();
        }
        finally {
            safeClose(fileOutputStream);
        }
    }
    
    public static void writeFile(final File file, final String s) throws IOException {
        writeFile(file, s.getBytes());
    }
    
    public static String readFile(final File file) {
        FileInputStream fileInputStream = null;
        String s = null;
        try {
            if (!file.exists()) {
                return s;
            }
            fileInputStream = new FileInputStream(file);
            final byte[] array = new byte[fileInputStream.available()];
            fileInputStream.read(array);
            s = new String(array);
        }
        catch (Throwable t) {
            return s;
        }
        finally {
            safeClose(fileInputStream);
        }
        return s;
    }
    
    public static void safeClose(final InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public static void safeClose(final OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            }
            catch (Exception ex) {}
        }
    }
    
    static {
        LINE_SEPARATOR = System.getProperty("line.separator");
    }
}
