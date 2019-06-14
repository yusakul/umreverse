package com.umeng.commonsdk.internal.utils;

import java.io.*;

public class g
{
    private static final String a;
    private static final byte[] b;
    private static byte[] c;
    
    public static String a(final String... command) {
        Process start = null;
        StringBuilder sb = null;
        BufferedReader bufferedReader = null;
        InputStreamReader in = null;
        InputStream inputStream = null;
        InputStream errorStream = null;
        OutputStream outputStream = null;
        try {
            start = new ProcessBuilder(new String[0]).command(command).start();
            outputStream = start.getOutputStream();
            inputStream = start.getInputStream();
            errorStream = start.getErrorStream();
            outputStream.write(g.b);
            outputStream.flush();
            start.waitFor();
            in = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(in);
            final String line;
            if ((line = bufferedReader.readLine()) != null) {
                sb = new StringBuilder();
                sb.append(line);
                sb.append(g.a);
                String line2;
                while ((line2 = bufferedReader.readLine()) != null) {
                    sb.append(line2);
                    sb.append(g.a);
                }
            }
            while (errorStream.read(g.c) > 0) {}
        }
        catch (IOException ex) {}
        catch (Exception ex2) {}
        finally {
            a(outputStream, errorStream, inputStream, in, bufferedReader);
            if (start != null) {
                c(start);
            }
        }
        if (sb == null) {
            return null;
        }
        return sb.toString();
    }
    
    private static void a(final OutputStream outputStream, final InputStream inputStream, final InputStream inputStream2, final InputStreamReader inputStreamReader, final BufferedReader bufferedReader) {
        if (outputStream != null) {
            try {
                outputStream.close();
            }
            catch (IOException ex) {}
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            }
            catch (IOException ex2) {}
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            }
            catch (IOException ex3) {}
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            }
            catch (IOException ex4) {}
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            }
            catch (IOException ex5) {}
        }
    }
    
    private static void a(final Process process) {
        final int b = b(process);
        if (b != 0) {
            try {
                android.os.Process.killProcess(b);
            }
            catch (Exception ex) {
                try {
                    process.destroy();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    private static int b(final Process process) {
        final String string = process.toString();
        try {
            return Integer.parseInt(string.substring(string.indexOf("=") + 1, string.indexOf("]")));
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    private static void c(final Process process) {
        if (process != null) {
            try {
                if (process.exitValue() != 0) {
                    a(process);
                }
            }
            catch (IllegalThreadStateException ex) {
                a(process);
            }
        }
    }
    
    static {
        a = "\n";
        b = "\nexit\n".getBytes();
        g.c = new byte[32];
    }
}
