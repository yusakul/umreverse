package com.umeng.commonsdk.proguard;

import java.nio.*;
import java.util.*;

public abstract class t<T extends t<?, ?>, F extends q> implements j<T, F>
{
    protected Object a;
    protected F b;
    private static final Map<Class<? extends aq>, ar> c;
    
    protected t() {
        this.b = null;
        this.a = null;
    }
    
    protected t(final F n, final Object o) {
        this.a(n, o);
    }
    
    protected t(final t<T, F> t) {
        if (!t.getClass().equals(this.getClass())) {
            throw new ClassCastException();
        }
        this.b = t.b;
        this.a = a(t.a);
    }
    
    private static Object a(final Object o) {
        if (o instanceof j) {
            return ((j)o).deepCopy();
        }
        if (o instanceof ByteBuffer) {
            return k.d((ByteBuffer)o);
        }
        if (o instanceof List) {
            return a((List)o);
        }
        if (o instanceof Set) {
            return a((Set)o);
        }
        if (o instanceof Map) {
            return a((Map<Object, Object>)o);
        }
        return o;
    }
    
    private static Map a(final Map<Object, Object> map) {
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        for (final Map.Entry<Object, Object> entry : map.entrySet()) {
            hashMap.put(a(entry.getKey()), a(entry.getValue()));
        }
        return hashMap;
    }
    
    private static Set a(final Set set) {
        final HashSet<Object> set2 = new HashSet<Object>();
        final Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            set2.add(a(iterator.next()));
        }
        return set2;
    }
    
    private static List a(final List list) {
        final ArrayList<Object> list2 = new ArrayList<Object>(list.size());
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(a(iterator.next()));
        }
        return list2;
    }
    
    public F a() {
        return this.b;
    }
    
    public Object b() {
        return this.a;
    }
    
    public Object a(final F obj) {
        if (obj != this.b) {
            throw new IllegalArgumentException("Cannot get the value of field " + obj + " because union's set field is " + this.b);
        }
        return this.b();
    }
    
    public Object a(final int n) {
        return this.a(this.a((short)n));
    }
    
    public boolean c() {
        return this.b != null;
    }
    
    public boolean b(final F n) {
        return this.b == n;
    }
    
    public boolean b(final int n) {
        return this.b(this.a((short)n));
    }
    
    @Override
    public void read(final ai ai) throws p {
        t.c.get(ai.D()).b().b(ai, this);
    }
    
    public void a(final F b, final Object a) {
        this.b(b, a);
        this.b = b;
        this.a = a;
    }
    
    public void a(final int n, final Object o) {
        this.a(this.a((short)n), o);
    }
    
    @Override
    public void write(final ai ai) throws p {
        t.c.get(ai.D()).b().a(ai, this);
    }
    
    protected abstract void b(final F p0, final Object p1) throws ClassCastException;
    
    protected abstract Object a(final ai p0, final ad p1) throws p;
    
    protected abstract void a(final ai p0) throws p;
    
    protected abstract Object a(final ai p0, final short p1) throws p;
    
    protected abstract void b(final ai p0) throws p;
    
    protected abstract an d();
    
    protected abstract ad c(final F p0);
    
    protected abstract F a(final short p0);
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.getClass().getSimpleName());
        sb.append(" ");
        if (this.a() != null) {
            final Object b = this.b();
            sb.append(this.c(this.a()).a);
            sb.append(":");
            if (b instanceof ByteBuffer) {
                k.a((ByteBuffer)b, sb);
            }
            else {
                sb.append(b.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }
    
    @Override
    public final void clear() {
        this.b = null;
        this.a = null;
    }
    
    static {
        (c = new HashMap<Class<? extends aq>, ar>()).put(as.class, new InnerClass_b());
        t.c.put(at.class, new InnerClass_d());
    }
    
    private static class InnerClass_b implements ar
    {
        public InnerClass_a a() {
            return new InnerClass_a();
        }

        public aq b() {
            return this.a();
        }
    }
    
    private static class InnerClass_a extends as
    {
        public void a(ai arg1, j arg2) throws p {
            this.b(arg1, ((t)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.a(arg1, ((t)arg2));
        }
        public void a(final ai ai, final t t) throws p {
            t.b = null;
            t.a = null;
            ai.j();
            final ad l = ai.l();
            t.a = t.a(ai, l);
            if (t.a != null) {
                t.b = t.a(l.c);
            }
            ai.m();
            ai.l();
            ai.k();
        }

        public void b(final ai ai, final t t) throws p {
            if (t.a() == null || t.b() == null) {
                throw new aj("Cannot write InnerClass_a TUnion with no set value!");
            }
            ai.a(t.d());
            ai.a(t.c(t.b));
            t.a(ai);
            ai.c();
            ai.d();
            ai.b();
        }
    }
    
    private static class InnerClass_d implements ar
    {
        public InnerClass_c a() {
            return new InnerClass_c();
        }

        public aq b() {
            return this.a();
        }
    }
    
    private static class InnerClass_c extends at
    {
        public void a(ai arg1, j arg2) throws p {
            this.b(arg1, ((t)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.a(arg1, ((t)arg2));
        }

        public void a(final ai ai, final t t) throws p {
            t.b = null;
            t.a = null;
            final short v = ai.v();
            t.a = t.a(ai, v);
            if (t.a != null) {
                t.b = t.a(v);
            }
        }

        public void b(final ai ai, final t t) throws p {
            if (t.a() == null || t.b() == null) {
                throw new aj("Cannot write InnerClass_a TUnion with no set value!");
            }
            ai.a(t.b.a());
            t.b(ai);
        }
    }
}
