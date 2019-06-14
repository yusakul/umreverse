package com.umeng.commonsdk.internal.utils;

import android.text.*;
import java.io.*;

public class d
{
    public static a a() {
        a a = null;
        int c = 0;
        InputStreamReader in = null;
        BufferedReader bufferedReader = null;
        try {
            a = new a();
            in = new FileReader("/proc/cpuinfo");
            if (in == null) {
                return null;
            }
            bufferedReader = new BufferedReader(in);
            if (bufferedReader == null) {
                return null;
            }
            String s = bufferedReader.readLine();
            int n = 1;
            for (int n2 = 0; !TextUtils.isEmpty((CharSequence)s) && ++n2 < 30; s = bufferedReader.readLine()) {
                final String[] split = s.split(":\\s+", 2);
                if (n != 0 && split != null && split.length > 1) {
                    a.a = split[1];
                    n = 0;
                }
                if (split != null && split.length > 1 && split[0].contains("processor")) {
                    ++c;
                }
                if (split != null && split.length > 1 && split[0].contains("Features")) {
                    a.d = split[1];
                }
                if (split != null && split.length > 1 && split[0].contains("implementer")) {
                    a.e = split[1];
                }
                if (split != null && split.length > 1 && split[0].contains("architecture")) {
                    a.f = split[1];
                }
                if (split != null && split.length > 1 && split[0].contains("variant")) {
                    a.g = split[1];
                }
                if (split != null && split.length > 1 && split[0].contains("part")) {
                    a.h = split[1];
                }
                if (split != null && split.length > 1 && split[0].contains("revision")) {
                    a.i = split[1];
                }
                if (split != null && split.length > 1 && split[0].contains("Hardware")) {
                    a.j = split[1];
                }
                if (split != null && split.length > 1 && split[0].contains("Revision")) {
                    a.k = split[1];
                }
                if (split != null && split.length > 1 && split[0].contains("Serial")) {
                    a.l = split[1];
                }
                if (split != null && split.length > 1 && split[0].contains("implementer")) {
                    a.e = split[1];
                }
            }
        }
        catch (Exception ex) {}
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException ex2) {}
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex3) {}
            }
        }
        a.c = c;
        return a;
    }
    
    public static String b() {
        String string = "";
        try {
            final InputStream inputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" }).start().getInputStream();
            final byte[] array = new byte[24];
            while (inputStream.read(array) != -1) {
                string += new String(array);
            }
            inputStream.close();
        }
        catch (Exception ex) {}
        return string.trim();
    }
    
    public static String c() {
        String string = "";
        try {
            final InputStream inputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq" }).start().getInputStream();
            final byte[] array = new byte[24];
            while (inputStream.read(array) != -1) {
                string += new String(array);
            }
            inputStream.close();
        }
        catch (Exception ex) {}
        return string.trim();
    }
    
    public static String d() {
        String trim = "";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"));
            trim = bufferedReader.readLine().trim();
        }
        catch (Exception ex) {}
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (Throwable t) {}
        }
        return trim;
    }
    
    public static class a
    {
        public String a;
        public String b;
        public int c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
    }
}
