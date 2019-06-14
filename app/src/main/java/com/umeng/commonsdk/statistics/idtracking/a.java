package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.proto.*;
import java.util.*;

public abstract class a
{
    private final int a = 10;
    private final int b = 20;
    private final String c;
    private List<com.umeng.commonsdk.statistics.proto.a> d;
    private com.umeng.commonsdk.statistics.proto.b e;
    
    public a(final String c) {
        this.c = c;
    }
    
    public boolean a() {
        return this.g();
    }
    
    public String b() {
        return this.c;
    }
    
    public boolean c() {
        return this.e == null || this.e.h() <= 20;
    }
    
    private boolean g() {
        com.umeng.commonsdk.statistics.proto.b e = this.e;
        final String anObject = (e == null) ? null : e.b();
        final int n = (e == null) ? 0 : e.h();
        final String a = this.a(this.f());
        if (a != null && !a.equals(anObject)) {
            if (e == null) {
                e = new com.umeng.commonsdk.statistics.proto.b();
            }
            e.a(a);
            e.a(System.currentTimeMillis());
            e.a(n + 1);
            final com.umeng.commonsdk.statistics.proto.a a2 = new com.umeng.commonsdk.statistics.proto.a();
            a2.a(this.c);
            a2.c(a);
            a2.b(anObject);
            a2.a(e.e());
            if (this.d == null) {
                this.d = new ArrayList<com.umeng.commonsdk.statistics.proto.a>(2);
            }
            this.d.add(a2);
            if (this.d.size() > 10) {
                this.d.remove(0);
            }
            this.e = e;
            return true;
        }
        return false;
    }
    
    public  com.umeng.commonsdk.statistics.proto.b d() {
        return this.e;
    }
    
    public void a(final  com.umeng.commonsdk.statistics.proto.b e) {
        this.e = e;
    }
    
    public List<com.umeng.commonsdk.statistics.proto.a> e() {
        return this.d;
    }
    
    public void a(final List<com.umeng.commonsdk.statistics.proto.a> d) {
        this.d = d;
    }
    
    public String a(String trim) {
        if (trim == null) {
            return null;
        }
        trim = trim.trim();
        if (trim.length() == 0) {
            return null;
        }
        if ("0".equals(trim)) {
            return null;
        }
        if ("unknown".equals(trim.toLowerCase(Locale.US))) {
            return null;
        }
        return trim;
    }
    
    public abstract String f();
    
    public void a(final com.umeng.commonsdk.statistics.proto.c c) {
        this.e = c.c().get(this.c);
        final List<com.umeng.commonsdk.statistics.proto.a> h = c.h();
        if (h != null && h.size() > 0) {
            if (this.d == null) {
                this.d = new ArrayList<com.umeng.commonsdk.statistics.proto.a>();
            }
            for (final com.umeng.commonsdk.statistics.proto.a a : h) {
                if (this.c.equals(a.a)) {
                    this.d.add(a);
                }
            }
        }
    }
}
