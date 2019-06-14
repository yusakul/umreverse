package com.umeng.commonsdk.statistics.common;

import java.util.*;
import android.text.*;
import android.util.*;
import java.io.*;

public class ULog
{
    public static boolean DEBUG;
    private static String TAG;
    private static final int LEVEL_VERBOSE = 1;
    private static final int LEVEL_DEBUG = 2;
    private static final int LEVEL_INFO = 3;
    private static final int LEVEL_WARN = 4;
    private static final int LEVEL_ERROR = 5;
    private static int LOG_MAXLENGTH;
    
    private ULog() {
    }
    
    public static void i(final Locale l, final String format, final Object... args) {
        try {
            i(ULog.TAG, new Formatter(l).format(format, args).toString(), null);
        }
        catch (Throwable t) {
            e(t);
        }
    }
    
    public static void d(final Locale l, final String format, final Object... args) {
        try {
            d(ULog.TAG, new Formatter(l).format(format, args).toString(), null);
        }
        catch (Throwable t) {
            e(t);
        }
    }
    
    public static void e(final Locale l, final String format, final Object... args) {
        try {
            e(ULog.TAG, new Formatter(l).format(format, args).toString(), null);
        }
        catch (Throwable t) {
            e(t);
        }
    }
    
    public static void v(final Locale l, final String format, final Object... args) {
        try {
            v(ULog.TAG, new Formatter(l).format(format, args).toString(), null);
        }
        catch (Throwable t) {
            e(t);
        }
    }
    
    public static void w(final Locale l, final String format, final Object... args) {
        try {
            w(ULog.TAG, new Formatter(l).format(format, args).toString(), null);
        }
        catch (Throwable t) {
            e(t);
        }
    }
    
    public static void i(final String format, final Object... args) {
        try {
            String s = "";
            if (format.contains("%")) {
                i(ULog.TAG, new Formatter().format(format, args).toString(), null);
            }
            else {
                if (args != null) {
                    s = (String)args[0];
                }
                i(format, s, null);
            }
        }
        catch (Throwable t) {
            e(t);
        }
    }
    
    public static void d(final String format, final Object... args) {
        try {
            String s = "";
            if (format.contains("%")) {
                d(ULog.TAG, new Formatter().format(format, args).toString(), null);
            }
            else {
                if (args != null) {
                    s = (String)args[0];
                }
                d(format, s, null);
            }
        }
        catch (Throwable t) {
            e(t);
        }
    }
    
    public static void e(final String format, final Object... args) {
        try {
            String s = "";
            if (format.contains("%")) {
                e(ULog.TAG, new Formatter().format(format, args).toString(), null);
            }
            else {
                if (args != null) {
                    s = (String)args[0];
                }
                e(format, s, null);
            }
        }
        catch (Throwable t) {
            e(t);
        }
    }
    
    public static void v(final String format, final Object... args) {
        try {
            String s = "";
            if (format.contains("%")) {
                v(ULog.TAG, new Formatter().format(format, args).toString(), null);
            }
            else {
                if (args != null) {
                    s = (String)args[0];
                }
                v(format, s, null);
            }
        }
        catch (Throwable t) {
            e(t);
        }
    }
    
    public static void w(final String format, final Object... args) {
        try {
            String s = "";
            if (format.contains("%")) {
                w(ULog.TAG, new Formatter().format(format, args).toString(), null);
            }
            else {
                if (args != null) {
                    s = (String)args[0];
                }
                w(format, s, null);
            }
        }
        catch (Throwable t) {
            e(t);
        }
    }
    
    public static void i(final Throwable t) {
        i(ULog.TAG, null, t);
    }
    
    public static void v(final Throwable t) {
        v(ULog.TAG, null, t);
    }
    
    public static void w(final Throwable t) {
        w(ULog.TAG, null, t);
    }
    
    public static void d(final Throwable t) {
        d(ULog.TAG, null, t);
    }
    
    public static void e(final Throwable t) {
        e(ULog.TAG, null, t);
    }
    
    public static void i(final String s, final Throwable t) {
        i(ULog.TAG, s, t);
    }
    
    public static void v(final String s, final Throwable t) {
        v(ULog.TAG, s, t);
    }
    
    public static void w(final String s, final Throwable t) {
        w(ULog.TAG, s, t);
    }
    
    public static void d(final String s, final Throwable t) {
        d(ULog.TAG, s, t);
    }
    
    public static void e(final String s, final Throwable t) {
        e(ULog.TAG, s, t);
    }
    
    public static void v(final String s) {
        v(ULog.TAG, s, null);
    }
    
    public static void d(final String s) {
        d(ULog.TAG, s, null);
    }
    
    public static void i(final String s) {
        i(ULog.TAG, s, null);
    }
    
    public static void w(final String s) {
        w(ULog.TAG, s, null);
    }
    
    public static void e(final String s) {
        e(ULog.TAG, s, null);
    }
    
    public static void v(final String s, final String s2, final Throwable t) {
        if (ULog.DEBUG) {
            print(1, s, s2, t);
        }
    }
    
    public static void d(final String s, final String s2, final Throwable t) {
        if (ULog.DEBUG) {
            print(2, s, s2, t);
        }
    }
    
    public static void i(final String s, final String s2, final Throwable t) {
        if (ULog.DEBUG) {
            print(3, s, s2, t);
        }
    }
    
    public static void w(final String s, final String s2, final Throwable t) {
        if (ULog.DEBUG) {
            print(4, s, s2, t);
        }
    }
    
    public static void e(final String s, final String s2, final Throwable t) {
        if (ULog.DEBUG) {
            print(5, s, s2, t);
        }
    }
    
    private static void print(final int n, final String s, final String s2, final Throwable t) {
        Label_0293: {
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                final int length = s2.length();
                int n2 = 0;
                int log_MAXLENGTH = ULog.LOG_MAXLENGTH;
                int i = 0;
                while (i < 100) {
                    if (length > log_MAXLENGTH) {
                        switch (n) {
                            case 2: {
                                Log.d(s, s2.substring(n2, log_MAXLENGTH));
                                break;
                            }
                            case 3: {
                                Log.i(s, s2.substring(n2, log_MAXLENGTH));
                                break;
                            }
                            case 5: {
                                Log.e(s, s2.substring(n2, log_MAXLENGTH));
                                break;
                            }
                            case 1: {
                                Log.v(s, s2.substring(n2, log_MAXLENGTH));
                                break;
                            }
                            case 4: {
                                Log.w(s, s2.substring(n2, log_MAXLENGTH));
                                break;
                            }
                        }
                        n2 = log_MAXLENGTH;
                        log_MAXLENGTH += ULog.LOG_MAXLENGTH;
                        ++i;
                    }
                    else {
                        switch (n) {
                            case 2: {
                                Log.d(s, s2.substring(n2, length));
                                break Label_0293;
                            }
                            case 3: {
                                Log.i(s, s2.substring(n2, length));
                                break Label_0293;
                            }
                            case 5: {
                                Log.e(s, s2.substring(n2, length));
                                break Label_0293;
                            }
                            case 1: {
                                Log.v(s, s2.substring(n2, length));
                                break Label_0293;
                            }
                            case 4: {
                                Log.w(s, s2.substring(n2, length));
                                break Label_0293;
                            }
                            default: {
                                break Label_0293;
                            }
                        }
                    }
                }
            }
        }
        if (t != null) {
            final String stackTrace = getStackTrace(t);
            if (!TextUtils.isEmpty((CharSequence)stackTrace)) {
                switch (n) {
                    case 2: {
                        Log.d(s, stackTrace);
                        break;
                    }
                    case 3: {
                        Log.i(s, stackTrace);
                        break;
                    }
                    case 5: {
                        Log.e(s, stackTrace);
                        break;
                    }
                    case 1: {
                        Log.v(s, stackTrace);
                        break;
                    }
                    case 4: {
                        Log.w(s, stackTrace);
                        break;
                    }
                }
            }
        }
    }
    
    public static String getStackTrace(final Throwable t) {
        StringWriter out = null;
        PrintWriter s = null;
        String string = "";
        try {
            out = new StringWriter();
            s = new PrintWriter(out);
            t.printStackTrace(s);
            s.flush();
            out.flush();
            string = out.toString();
        }
        catch (Throwable t2) {}
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                catch (Throwable t3) {}
            }
            if (s != null) {
                s.close();
            }
        }
        return string;
    }
    
    static {
        ULog.DEBUG = false;
        ULog.TAG = "ULog";
        ULog.LOG_MAXLENGTH = 2000;
    }
}
