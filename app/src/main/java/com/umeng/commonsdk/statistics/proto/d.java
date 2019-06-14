package com.umeng.commonsdk.statistics.proto;

import java.io.*;
import java.util.*;
import com.umeng.commonsdk.proguard.*;

    public class d implements j , Serializable, Cloneable
{
    private static final long e = 2846460275012375038L;
    private static final an f;
    private static final ad g;
    private static final ad h;
    private static final ad i;
    private static final Map<Class<? extends aq>, ar> j;
    public Map<String, com.umeng.commonsdk.statistics.proto.e> a;
    public int b;
    public String c;
    private static final int k = 0;
    private byte l;
    public static final Map<e_enum, v> d;

    public j deepCopy() {
        return this.a();
    }

    public q fieldForId(int arg1) {
        return this.b(arg1);
    }

    public d() {
        this.l = 0;
    }
    
    public d(final Map<String, com.umeng.commonsdk.statistics.proto.e> a, final int b, final String c) {
        this();
        this.a = a;
        this.b = b;
        this.b(true);
        this.c = c;
    }
    
    public d(final com.umeng.commonsdk.statistics.proto.d d) {
        this.l = 0;
        this.l = d.l;
        if (d.e()) {
            final HashMap<String, com.umeng.commonsdk.statistics.proto.e> a = new HashMap<String, com.umeng.commonsdk.statistics.proto.e>();
            for (final Object entry : d.a.entrySet()) {
                a.put((String)((Map.Entry)entry).getKey(), new com.umeng.commonsdk.statistics.proto.e((com.umeng.commonsdk.statistics.proto.e)((Map.Entry)entry).getValue()));
            }
            this.a = a;
        }
        this.b = d.b;
        if (d.k()) {
            this.c = d.c;
        }
    }
    
    public d a() {
        return new d();
    }
    
    @Override
    public void clear() {
        this.a = null;
        this.b(false);
        this.b = 0;
        this.c = null;
    }
    
    public int b() {
        return (this.a == null) ? 0 : this.a.size();
    }
    
    public void a(final String s, final com.umeng.commonsdk.statistics.proto.e e) {
        if (this.a == null) {
            this.a = new HashMap<String, com.umeng.commonsdk.statistics.proto.e>();
        }
        this.a.put(s, e);
    }
    
    public Map<String, com.umeng.commonsdk.statistics.proto.e> c() {
        return this.a;
    }
    
    public d a(final Map<String, com.umeng.commonsdk.statistics.proto.e> a) {
        this.a = a;
        return this;
    }
    
    public void d() {
        this.a = null;
    }
    
    public boolean e() {
        return this.a != null;
    }
    
    public void a(final boolean b) {
        if (!b) {
            this.a = null;
        }
    }
    
    public int f() {
        return this.b;
    }
    
    public d a(final int b) {
        this.b = b;
        this.b(true);
        return this;
    }
    
    public void g() {
        this.l = com.umeng.commonsdk.proguard.g.b(this.l, 0);
    }
    
    public boolean h() {
        return com.umeng.commonsdk.proguard.g.a(this.l, 0);
    }
    
    public void b(final boolean b) {
        this.l = com.umeng.commonsdk.proguard.g.a(this.l, 0, b);
    }
    
    public String i() {
        return this.c;
    }
    
    public d a(final String c) {
        this.c = c;
        return this;
    }
    
    public void j() {
        this.c = null;
    }
    
    public boolean k() {
        return this.c != null;
    }
    
    public void c(final boolean b) {
        if (!b) {
            this.c = null;
        }
    }
    
    public e_enum b(final int n) {
        return com.umeng.commonsdk.statistics.proto.d.e_enum.a(n);
    }
    
    @Override
    public void read(final ai ai) throws p {
        com.umeng.commonsdk.statistics.proto.d.j.get(ai.D()).b().b(ai, this);
    }
    
    @Override
    public void write(final ai ai) throws p {
        com.umeng.commonsdk.statistics.proto.d.j.get(ai.D()).b().a(ai, this);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Imprint(");
        sb.append("property:");
        if (this.a == null) {
            sb.append("null");
        }
        else {
            sb.append(this.a);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("version:");
        sb.append(this.b);
        if (!false) {
            sb.append(", ");
        }
        sb.append("checksum:");
        if (this.c == null) {
            sb.append("null");
        }
        else {
            sb.append(this.c);
        }
        sb.append(")");
        return sb.toString();
    }
    
    public void l() throws p {
        if (this.a == null) {
            throw new aj("Required field 'property' was not present! Struct: " + this.toString());
        }
        if (this.c == null) {
            throw new aj("Required field 'checksum' was not present! Struct: " + this.toString());
        }
    }
    
    private void a(final ObjectOutputStream objectOutputStream) throws IOException {
        try {
            this.write(new ac(new au(objectOutputStream)));
        }
        catch (p p) {
            throw new IOException(p.getMessage());
        }
    }
    
    private void a(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.l = 0;
            this.read(new ac(new au(objectInputStream)));
        }
        catch (p p) {
            throw new IOException(p.getMessage());
        }
    }
    
    static {
        f = new an("Imprint");
        g = new ad("property", (byte)13, (short)1);
        h = new ad("version", (byte)8, (short)2);
        i = new ad("checksum", (byte)11, (short)3);
        (j = new HashMap<Class<? extends aq>, ar>()).put(as.class, new InnerClass_b());
        com.umeng.commonsdk.statistics.proto.d.j.put(at.class, new InnerClass_d());
        final EnumMap<e_enum, v> m = new EnumMap<e_enum, v>(e_enum.class);
        m.put(com.umeng.commonsdk.statistics.proto.d.e_enum.a, new v("property", (byte)1, new y((byte)13, new w((byte)11), new aa((byte)12, com.umeng.commonsdk.statistics.proto.e.class))));
        m.put(com.umeng.commonsdk.statistics.proto.d.e_enum.b, new v("version", (byte)1, new w((byte)8)));
        m.put(com.umeng.commonsdk.statistics.proto.d.e_enum.c, new v("checksum", (byte)1, new w((byte)11)));
        v.a(d.class, d = Collections.unmodifiableMap((Map)m));
    }
    
    public enum e_enum implements q
    {
        a((short)1, "property"), 
        b((short)2, "version"), 
        c((short)3, "checksum");
        
        private static final Map<String, e_enum> d;
        private final short e;
        private final String f;
        
        public static e_enum a(final int n) {
            switch (n) {
                case 1: {
                    return e_enum.a;
                }
                case 2: {
                    return e_enum.b;
                }
                case 3: {
                    return e_enum.c;
                }
                default: {
                    return null;
                }
            }
        }
        
        public static e_enum b(final int i) {
            final e_enum a = a(i);
            if (a == null) {
                throw new IllegalArgumentException("Field " + i + " doesn't exist!");
            }
            return a;
        }
        
        public static e_enum a(final String s) {
            return e_enum.d.get(s);
        }
        
        private e_enum(final short e, final String f) {
            this.e = e;
            this.f = f;
        }
        
        @Override
        public short a() {
            return this.e;
        }
        
        @Override
        public String b() {
            return this.f;
        }
        
        static {
            d = new HashMap<String, e_enum>();
            for (final e_enum e_enum : EnumSet.allOf(e_enum.class)) {
                e_enum.d.put(e_enum.b(), e_enum);
            }
        }
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
            this.b(arg1, ((d)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.a(arg1, ((d)arg2));
        }

        public void a(final ai ai, final d d) throws p {
            ai.j();
            while (true) {
                final ad l = ai.l();
                if (l.b == 0) {
                    break;
                }
                switch (l.c) {
                    case 1: {
                        if (l.b == 13) {
                            final af n = ai.n();
                            d.a = new HashMap<String, com.umeng.commonsdk.statistics.proto.e>(2 * n.c);
                            for (int i = 0; i < n.c; ++i) {
                                final String z = ai.z();
                                final com.umeng.commonsdk.statistics.proto.e e = new com.umeng.commonsdk.statistics.proto.e();
                                e.read(ai);
                                d.a.put(z, e);
                            }
                            ai.o();
                            d.a(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 2: {
                        if (l.b == 8) {
                            d.b = ai.w();
                            d.b(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 3: {
                        if (l.b == 11) {
                            d.c = ai.z();
                            d.c(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    default: {
                        al.a(ai, l.b);
                        break;
                    }
                }
                ai.m();
            }
            ai.k();
            if (!d.h()) {
                throw new aj("Required field 'version' was not found in serialized data! Struct: " + this.toString());
            }
            d.l();
        }
        

        public void b(final ai ai, final d d) throws p {
            d.l();
            ai.a(d.f);
            if (d.a != null) {
                ai.a(d.g);
                ai.a(new af((byte)11, (byte)12, d.a.size()));
                for (final Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : d.a.entrySet()) {
                    ai.a(entry.getKey());
                    entry.getValue().write(ai);
                }
                ai.e();
                ai.c();
            }
            ai.a(d.h);
            ai.a(d.b);
            ai.c();
            if (d.c != null) {
                ai.a(d.i);
                ai.a(d.c);
                ai.c();
            }
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
            this.a(arg1, ((d)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.b(arg1, ((d)arg2));
        }

        public void a(final ai ai, final d d) throws p {
            final ao ao = (ao)ai;
            ao.a(d.a.size());
            for (final Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : d.a.entrySet()) {
                ao.a(entry.getKey());
                entry.getValue().write(ao);
            }
            ao.a(d.b);
            ao.a(d.c);
        }
        

        public void b(final ai ai, final d d) throws p {
            final ao ao = (ao)ai;
            final af af = new af((byte)11, (byte)12, ao.w());
            d.a = new HashMap<String, com.umeng.commonsdk.statistics.proto.e>(2 * af.c);
            for (int i = 0; i < af.c; ++i) {
                final String z = ao.z();
                final com.umeng.commonsdk.statistics.proto.e e = new com.umeng.commonsdk.statistics.proto.e();
                e.read(ao);
                d.a.put(z, e);
            }
            d.a(true);
            d.b = ao.w();
            d.b(true);
            d.c = ao.z();
            d.c(true);
        }
    }
}
