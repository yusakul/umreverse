package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;
import android.text.*;
import com.umeng.commonsdk.statistics.*;
import java.util.*;
import com.umeng.commonsdk.statistics.common.*;
import java.io.*;
import com.umeng.commonsdk.statistics.internal.*;

public class e
{
    private final String c = "umeng_it.cache";
    public static final long a = 86400000L;
    private File d;
    private com.umeng.commonsdk.statistics.proto.c cVar;
    private long f;
    private long g;
    private Set<com.umeng.commonsdk.statistics.idtracking.a> h;
    private a i;
    public static e b;
    private static Object j;
    
    e(final Context context) {
        this.cVar = null;
        this.h = new HashSet<com.umeng.commonsdk.statistics.idtracking.a>();
        this.i = null;
        this.d = new File(context.getFilesDir(), "umeng_it.cache");
        this.g = 86400000L;
        (this.i = new a(context)).b();
    }
    
    public static synchronized e a(final Context context) {
        if (e.b == null) {
            (e.b = new e(context)).a(new f(context));
            e.b.a(new b(context));
            e.b.a(new r(context));
            e.b.a(new d(context));
            e.b.a(new com.umeng.commonsdk.statistics.idtracking.c(context));
            e.b.a(new g(context));
            e.b.a(new j());
            e.b.a(new s(context));
            final q q = new q(context);
            if (!TextUtils.isEmpty((CharSequence)q.f())) {
                e.b.a(q);
            }
            final i i = new i(context);
            if (i.g()) {
                e.b.a(i);
                e.b.a(new h(context));
                i.i();
            }
            if (SdkVersion.SDK_TYPE != 1) {
                e.b.a(new p(context));
                e.b.a(new m(context));
                e.b.a(new o(context));
                e.b.a(new n(context));
                e.b.a(new l(context));
                e.b.a(new k(context));
            }
            e.b.e();
        }
        return e.b;
    }
    
    private boolean a(final com.umeng.commonsdk.statistics.idtracking.a a) {
        if (this.i.a(a.b())) {
            return this.h.add(a);
        }
        if (AnalyticsConstants.UM_DEBUG) {
            MLog.w("invalid domain: " + a.b());
        }
        return false;
    }
    
    public void a(final long g) {
        this.g = g;
    }
    
    public synchronized void a() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f >= this.g) {
            boolean b = false;
            for (final com.umeng.commonsdk.statistics.idtracking.a a : this.h) {
                if (!a.c()) {
                    continue;
                }
                if (!a.a()) {
                    continue;
                }
                b = true;
                if (a.c()) {
                    continue;
                }
                this.i.b(a.b());
            }
            if (b) {
                this.g();
                this.i.a();
                this.f();
            }
            this.f = currentTimeMillis;
        }
    }
    
    public synchronized com.umeng.commonsdk.statistics.proto.c b() {
        return this.cVar;
    }
    
    private synchronized void g() {
        final  com.umeng.commonsdk.statistics.proto.c e = new  com.umeng.commonsdk.statistics.proto.c();
        final HashMap<String, com.umeng.commonsdk.statistics.proto.b> hashMap = new HashMap<String, com.umeng.commonsdk.statistics.proto.b>();
        final ArrayList<Object> list = new ArrayList<Object>();
        for (final com.umeng.commonsdk.statistics.idtracking.a index : this.h) {
            if (!index.c()) {
                continue;
            }
            if (index.d() != null) {
                hashMap.put(index.b(), index.d());
            }
            if (index.e() == null || index.e().isEmpty()) {
                continue;
            }
            list.addAll(index.e());
        }
        e.a((List)list);
        e.a(hashMap);
        synchronized (this) {
            this.cVar = e;
        }
    }
    
    public String c() {
        return null;
    }
    
    public synchronized void d() {
        boolean b = false;
        for (final com.umeng.commonsdk.statistics.idtracking.a a : this.h) {
            if (!a.c()) {
                continue;
            }
            if (a.e() == null || a.e().isEmpty()) {
                continue;
            }
            a.a((List<com.umeng.commonsdk.statistics.proto.a>)null);
            b = true;
        }
        if (b) {
            this.cVar.b(false);
            this.f();
        }
    }
    
    public synchronized void e() {
        final com.umeng.commonsdk.statistics.proto.c h = this.h();
        if (h == null) {
            return;
        }
        final ArrayList<com.umeng.commonsdk.statistics.idtracking.a> list = new ArrayList<com.umeng.commonsdk.statistics.idtracking.a>(this.h.size());
        synchronized (this) {
            this.cVar = h;
            for (final com.umeng.commonsdk.statistics.idtracking.a index : this.h) {
                index.a(this.cVar);
                if (!index.c()) {
                    list.add(index);
                }
            }
            final Iterator iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                this.h.remove(iterator2.next());
            }
        }
        this.g();
    }
    
    public synchronized void f() {
        if (this.cVar != null) {
            this.a(this.cVar);
        }
    }
    
    private com.umeng.commonsdk.statistics.proto.c h() {
        synchronized (com.umeng.commonsdk.statistics.idtracking.e.j) {
            if (!this.d.exists()) {
                return null;
            }
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(this.d);
                final byte[] streamToByteArray = HelperUtils.readStreamToByteArray(inputStream);
                final com.umeng.commonsdk.statistics.proto.c c = new com.umeng.commonsdk.statistics.proto.c();
                new com.umeng.commonsdk.proguard.m().a(c, streamToByteArray);
                return c;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                HelperUtils.safeClose(inputStream);
            }
        }
        return null;
    }
    
    private void a(final com.umeng.commonsdk.statistics.proto.c c) {
        synchronized (com.umeng.commonsdk.statistics.idtracking.e.j) {
            if (c != null) {
                try {
                    final byte[] a;
                    synchronized (this) {
                        a = new com.umeng.commonsdk.proguard.s().a(c);
                    }
                    if (a != null) {
                        HelperUtils.writeFile(this.d, a);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    static {
        e.j = new Object();
    }
    
    public static class a
    {
        private Context a;
        private Set<String> b;
        
        public a(final Context a) {
            this.b = new HashSet<String>();
            this.a = a;
        }
        
        public synchronized boolean a(final String s) {
            return !this.b.contains(s);
        }
        
        public synchronized void b(final String s) {
            this.b.add(s);
        }
        
        public void c(final String s) {
            this.b.remove(s);
        }
        
        public synchronized void a() {
            if (!this.b.isEmpty()) {
                final StringBuilder sb = new StringBuilder();
                final Iterator<String> iterator = this.b.iterator();
                while (iterator.hasNext()) {
                    sb.append(iterator.next());
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
                PreferenceWrapper.getDefault(this.a).edit().putString("invld_id", sb.toString()).commit();
            }
        }
        
        public synchronized void b() {
            final String string = PreferenceWrapper.getDefault(this.a).getString("invld_id", (String)null);
            if (!TextUtils.isEmpty((CharSequence)string)) {
                final String[] split = string.split(",");
                if (split != null) {
                    for (final String s : split) {
                        if (!TextUtils.isEmpty((CharSequence)s)) {
                            this.b.add(s);
                        }
                    }
                }
            }
        }
    }
}
