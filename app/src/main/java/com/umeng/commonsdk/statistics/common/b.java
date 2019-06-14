package com.umeng.commonsdk.statistics.common;

import android.text.*;
import java.io.*;
import java.util.zip.*;

public class b
{
    public static int a;
    
    public static byte[] a(final String s, final String charsetName) throws IOException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        return a(s.getBytes(charsetName));
    }
    
    public static byte[] a(final byte[] input) throws IOException {
        if (input == null || input.length <= 0) {
            return null;
        }
        final Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();
        final byte[] array = new byte[8192];
        b.a = 0;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                final int deflate = deflater.deflate(array);
                b.a += deflate;
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
    
    public static String a(final byte[] array, final String charsetName) throws UnsupportedEncodingException, DataFormatException {
        final byte[] b = b(array);
        if (b != null) {
            return new String(b, charsetName);
        }
        return null;
    }
    
    public static byte[] b(final byte[] input) throws UnsupportedEncodingException, DataFormatException {
        if (input == null || input.length == 0) {
            return null;
        }
        final Inflater inflater = new Inflater();
        inflater.setInput(input, 0, input.length);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[1024];
        int off = 0;
        while (!inflater.needsInput()) {
            final int inflate = inflater.inflate(array);
            byteArrayOutputStream.write(array, off, inflate);
            off += inflate;
        }
        inflater.end();
        return byteArrayOutputStream.toByteArray();
    }
}
