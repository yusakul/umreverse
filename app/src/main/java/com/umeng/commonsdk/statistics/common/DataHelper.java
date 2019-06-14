package com.umeng.commonsdk.statistics.common;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import java.io.*;

public class DataHelper
{
    private static final byte[] iv;
    public static long ENVELOPE_EXTRA_LENGTH;
    public static long ENVELOPE_ENTITY_RAW_LENGTH_MAX;
    public static long ENVELOPE_LENGTH_MAX;
    
    public static byte[] reverseHexString(final String s) {
        if (s == null) {
            return null;
        }
        final int length = s.length();
        if (length % 2 != 0) {
            return null;
        }
        final byte[] array = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            array[i / 2] = (byte)(int)Integer.valueOf(s.substring(i, i + 2), 16);
        }
        return array;
    }
    
    public static boolean largeThanMaxSize(final long n, final long n2) {
        return n > n2;
    }
    
    public static String toHexString(final byte[] array) {
        if (array == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(String.format("%02X", array[i]));
        }
        return sb.toString().toLowerCase(Locale.US);
    }
    
    public static byte[] hash(final byte[] input) {
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
    
    public static byte[] encrypt(final byte[] input, final byte[] key) throws Exception {
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, new SecretKeySpec(key, "AES"), new IvParameterSpec(DataHelper.iv));
        return instance.doFinal(input);
    }
    
    public static byte[] decrypt(final byte[] input, final byte[] key) throws Exception {
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(2, new SecretKeySpec(key, "AES"), new IvParameterSpec(DataHelper.iv));
        return instance.doFinal(input);
    }
    
    public static int random(final int bound, final String s) {
        int nextInt;
        if (new Random().nextFloat() < 0.001) {
            if (s == null) {
                MLog.e("--->", "null signature..");
            }
            int int1 = 0;
            try {
                int1 = Integer.parseInt(s.substring(9, 11), 16);
            }
            catch (Exception ex) {}
            nextInt = (0x80 | int1) * 1000;
        }
        else {
            nextInt = new Random().nextInt(bound);
            if (nextInt <= 255000 && nextInt >= 128000) {
                nextInt = 127000;
            }
        }
        return nextInt;
    }
    
    public static String convertExceptionToString(final Throwable t) {
        if (t == null) {
            return null;
        }
        String string = null;
        try {
            final StringWriter out = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(out);
            t.printStackTrace(printWriter);
            for (Throwable t2 = t.getCause(); t2 != null; t2 = t2.getCause()) {
                t2.printStackTrace(printWriter);
            }
            string = out.toString();
            printWriter.close();
            out.close();
        }
        catch (Exception ex) {}
        return string;
    }
    
    public static String assembleURL(final String str) {
        return "https://" + str + "/unify_logs";
    }
    
    public static String assembleStatelessURL(final String str) {
        return "https://" + str;
    }
    
    public static String encryptBySHA1(final String s) {
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
    
    static String bytes2Hex(final byte[] array) {
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
    
    static {
        iv = new byte[] { 10, 1, 11, 5, 4, 15, 7, 9, 23, 3, 1, 6, 8, 12, 13, 91 };
        DataHelper.ENVELOPE_EXTRA_LENGTH = 1024L;
        DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX = 2097152L;
        DataHelper.ENVELOPE_LENGTH_MAX = 204800L;
    }
}
