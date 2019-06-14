package com.umeng.commonsdk.statistics.common;

import com.umeng.commonsdk.statistics.internal.*;
import android.content.*;
import com.umeng.commonsdk.framework.*;
import android.text.*;
import java.io.*;
import java.util.*;

public final class d
{
    private static d a;
    private static Context b;
    private static String c;
    private a d;
    private static final String e = "mobclick_agent_user_";
    private static final String f = "mobclick_agent_header_";
    private static final String g = "mobclick_agent_cached_";
    
    public d(final Context context) {
        this.d = new a(context);
    }
    
    public static synchronized d a(final Context context) {
        com.umeng.commonsdk.statistics.common.d.b = context.getApplicationContext();
        com.umeng.commonsdk.statistics.common.d.c = context.getPackageName();
        if (com.umeng.commonsdk.statistics.common.d.a == null) {
            com.umeng.commonsdk.statistics.common.d.a = new d(context);
        }
        return com.umeng.commonsdk.statistics.common.d.a;
    }
    
    public void a(final int n) {
        final SharedPreferences default1 = PreferenceWrapper.getDefault(com.umeng.commonsdk.statistics.common.d.b);
        if (default1 != null) {
            default1.edit().putInt("vt", n).commit();
        }
    }
    
    public int a() {
        final SharedPreferences default1 = PreferenceWrapper.getDefault(com.umeng.commonsdk.statistics.common.d.b);
        if (default1 != null) {
            return default1.getInt("vt", 0);
        }
        return 0;
    }
    
    public String b() {
        final SharedPreferences default1 = PreferenceWrapper.getDefault(com.umeng.commonsdk.statistics.common.d.b);
        if (default1 != null) {
            return default1.getString("st", (String)null);
        }
        return null;
    }
    
    public void a(final String s) {
        final SharedPreferences default1 = PreferenceWrapper.getDefault(com.umeng.commonsdk.statistics.common.d.b);
        if (default1 != null) {
            default1.edit().putString("st", s).commit();
        }
    }
    
    public boolean c() {
        return UMFrUtils.envelopeFileNumber(com.umeng.commonsdk.statistics.common.d.b) > 0;
    }
    
    private SharedPreferences f() {
        return com.umeng.commonsdk.statistics.common.d.b.getSharedPreferences("mobclick_agent_user_" + com.umeng.commonsdk.statistics.common.d.c, 0);
    }
    
    public void a(final String s, final String s2) {
        if (!TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2)) {
            final SharedPreferences.Editor edit = this.f().edit();
            edit.putString("au_p", s);
            edit.putString("au_u", s2);
            edit.commit();
        }
    }
    
    public String[] d() {
        try {
            final SharedPreferences f = this.f();
            final String string = f.getString("au_p", (String)null);
            final String string2 = f.getString("au_u", (String)null);
            if (string != null && string2 != null) {
                return new String[] { string, string2 };
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public void e() {
        this.f().edit().remove("au_p").remove("au_u").commit();
    }
    
    static {
        com.umeng.commonsdk.statistics.common.d.a = null;
    }
    
    public static class a
    {
        private final int a = 10;
        private File b;
        private FilenameFilter c;
        
        public a(final Context context) {
            this(context, ".um");
        }
        
        public a(final Context context, final String child) {
            this.c = new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String s) {
                    return s.startsWith("um");
                }
            };
            this.b = new File(context.getFilesDir(), child);
            if (!this.b.exists() || !this.b.isDirectory()) {
                this.b.mkdir();
            }
        }
        
        public boolean a() {
            final File[] listFiles = this.b.listFiles();
            return listFiles != null && listFiles.length > 0;
        }
        
        public void a(final b b) {
            final File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length >= 10) {
                Arrays.sort(listFiles);
                for (int n = listFiles.length - 10, i = 0; i < n; ++i) {
                    listFiles[i].delete();
                }
            }
            if (listFiles != null && listFiles.length > 0) {
                b.a(this.b);
                for (int length = listFiles.length, j = 0; j < length; ++j) {
                    boolean b2 = false;
                    try {
                        b2 = b.b(listFiles[j]);
                    }
                    catch (Throwable t) {
                        b2 = true;
                    }
                    finally {
                        if (b2) {
                            listFiles[j].delete();
                        }
                    }
                }
                b.c(this.b);
            }
        }
        
        public void a(final byte[] array) {
            if (array == null || array.length == 0) {
                return;
            }
            final File file = new File(this.b, String.format(Locale.US, "um_cache_%serial_num.env", System.currentTimeMillis()));
            try {
                HelperUtils.writeFile(file, array);
            }
            catch (Exception ex) {}
        }
        
        public void b() {
            final File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length > 0) {
                final File[] array = listFiles;
                for (int length = array.length, i = 0; i < length; ++i) {
                    array[i].delete();
                }
            }
        }
        
        public int c() {
            final File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length > 0) {
                return listFiles.length;
            }
            return 0;
        }
    }
    
    public interface b
    {
        void a(final File p0);
        
        boolean b(final File p0);
        
        void c(final File p0);
    }
}
