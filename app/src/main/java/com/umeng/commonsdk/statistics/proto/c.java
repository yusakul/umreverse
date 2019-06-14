package com.umeng.commonsdk.statistics.proto;

import java.io.*;
import com.umeng.commonsdk.proguard.*;
import java.util.*;

public class c implements j , Serializable, Cloneable
{
    private static final long e = -5764118265293965743L;
    private static final an f;
    private static final ad g;
    private static final ad h;
    private static final ad i;
    private static final Map<Class<? extends aq>, ar> j;
    public Map<String, com.umeng.commonsdk.statistics.proto.b> a;
    public List<com.umeng.commonsdk.statistics.proto.a> b;
    public String c;
    private e_enum[] k;
    public static final Map<e_enum, v> d;


    public q fieldForId(int arg1) {
        return this.a(arg1);
    }

    public j deepCopy() {
        return this.a();
    }

    public c() {
        this.k = new e_enum[] { com.umeng.commonsdk.statistics.proto.c.e_enum.b, com.umeng.commonsdk.statistics.proto.c.e_enum.c };
    }
    
    public c(final Map<String, com.umeng.commonsdk.statistics.proto.b> a) {
        this();
        this.a = a;
    }
    
    public c(final c c) {
        this.k = new e_enum[] { com.umeng.commonsdk.statistics.proto.c.e_enum.b, com.umeng.commonsdk.statistics.proto.c.e_enum.c };
        if (c.e()) {
            final HashMap<String, com.umeng.commonsdk.statistics.proto.b> a = new HashMap<String, com.umeng.commonsdk.statistics.proto.b>();
            for (final Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : c.a.entrySet()) {
                a.put(entry.getKey(), new com.umeng.commonsdk.statistics.proto.b(entry.getValue()));
            }
            this.a = a;
        }
        if (c.j()) {
            final ArrayList<com.umeng.commonsdk.statistics.proto.a> b = new ArrayList<com.umeng.commonsdk.statistics.proto.a>();
            final Iterator<com.umeng.commonsdk.statistics.proto.a> iterator2 = c.b.iterator();
            while (iterator2.hasNext()) {
                b.add(new com.umeng.commonsdk.statistics.proto.a(iterator2.next()));
            }
            this.b = b;
        }
        if (c.m()) {
            this.c = c.c;
        }
    }
    
    public c a() {
        return new c();
    }
    
    @Override
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
    }
    
    public int b() {
        return (this.a == null) ? 0 : this.a.size();
    }
    
    public void a(final String s, final com.umeng.commonsdk.statistics.proto.b b) {
        if (this.a == null) {
            this.a = new HashMap<String, com.umeng.commonsdk.statistics.proto.b>();
        }
        this.a.put(s, b);
    }
    
    public Map<String, com.umeng.commonsdk.statistics.proto.b> c() {
        return this.a;
    }
    
    public c a(final Map<String, com.umeng.commonsdk.statistics.proto.b> a) {
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
        return (this.b == null) ? 0 : this.b.size();
    }
    
    public Iterator<com.umeng.commonsdk.statistics.proto.a> g() {
        return (this.b == null) ? null : this.b.iterator();
    }
    
    public void a(final com.umeng.commonsdk.statistics.proto.a a) {
        if (this.b == null) {
            this.b = new ArrayList<com.umeng.commonsdk.statistics.proto.a>();
        }
        this.b.add(a);
    }
    
    public List<com.umeng.commonsdk.statistics.proto.a> h() {
        return this.b;
    }
    
    public c a(final List<com.umeng.commonsdk.statistics.proto.a> b) {
        this.b = b;
        return this;
    }
    
    public void i() {
        this.b = null;
    }
    
    public boolean j() {
        return this.b != null;
    }
    
    public void b(final boolean b) {
        if (!b) {
            this.b = null;
        }
    }
    
    public String k() {
        return this.c;
    }
    
    public c a(final String c) {
        this.c = c;
        return this;
    }
    
    public void l() {
        this.c = null;
    }
    
    public boolean m() {
        return this.c != null;
    }
    
    public void c(final boolean b) {
        if (!b) {
            this.c = null;
        }
    }
    
    public e_enum a(final int n) {
        return com.umeng.commonsdk.statistics.proto.c.e_enum.a(n);
    }
    
    @Override
    public void read(final ai ai) throws p {
        com.umeng.commonsdk.statistics.proto.c.j.get(ai.D()).b().b(ai, this);
    }
    
    @Override
    public void write(final ai ai) throws p {
        com.umeng.commonsdk.statistics.proto.c.j.get(ai.D()).b().a(ai, this);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        if (this.a == null) {
            sb.append("null");
        }
        else {
            sb.append(this.a);
        }
        int n = 0;
        if (this.j()) {
            if (n == 0) {
                sb.append(", ");
            }
            sb.append("journals:");
            if (this.b == null) {
                sb.append("null");
            }
            else {
                sb.append(this.b);
            }
            n = 0;
        }
        if (this.m()) {
            if (n == 0) {
                sb.append(", ");
            }
            sb.append("checksum:");
            if (this.c == null) {
                sb.append("null");
            }
            else {
                sb.append(this.c);
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    public void n() throws p {
        if (this.a == null) {
            throw new aj("Required field 'snapshots' was not present! Struct: " + this.toString());
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
            this.read(new ac(new au(objectInputStream)));
        }
        catch (p p) {
            throw new IOException(p.getMessage());
        }
    }
    
    static {
        f = new an("IdTracking");
        g = new ad("snapshots", (byte)13, (short)1);
        h = new ad("journals", (byte)15, (short)2);
        i = new ad("checksum", (byte)11, (short)3);
        (j = new HashMap<Class<? extends aq>, ar>()).put(as.class, new InnerClass_b());
        j.put(at.class, new InnerClass_d());
        final EnumMap<e_enum, v> m = new EnumMap<e_enum, v>(e_enum.class);
        m.put(com.umeng.commonsdk.statistics.proto.c.e_enum.a, new v("snapshots", (byte)1, new y((byte)13, new w((byte)11), new aa((byte)12, com.umeng.commonsdk.statistics.proto.b.class))));
        m.put(com.umeng.commonsdk.statistics.proto.c.e_enum.b, new v("journals", (byte)2, new x((byte)15, new aa((byte)12, com.umeng.commonsdk.statistics.proto.a.class))));
        m.put(com.umeng.commonsdk.statistics.proto.c.e_enum.c, new v("checksum", (byte)2, new w((byte)11)));
        v.a(c.class, d = Collections.unmodifiableMap((Map)m));
    }
    
    public enum e_enum implements q
    {
        a((short)1, "snapshots"), 
        b((short)2, "journals"), 
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
            this.b(arg1, ((c)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.a(arg1, ((c)arg2));
        }

        public void a(final ai ai, final c c) throws p {
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
                            c.a = new HashMap<String, com.umeng.commonsdk.statistics.proto.b>(2 * n.c);
                            for (int i = 0; i < n.c; ++i) {
                                final String z = ai.z();
                                final com.umeng.commonsdk.statistics.proto.b b = new com.umeng.commonsdk.statistics.proto.b();
                                b.read(ai);
                                c.a.put(z, b);
                            }
                            ai.o();
                            c.a(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 2: {
                        if (l.b == 15) {
                            final ae p2 = ai.p();
                            c.b = new ArrayList<com.umeng.commonsdk.statistics.proto.a>(p2.b);
                            for (int j = 0; j < p2.b; ++j) {
                                final com.umeng.commonsdk.statistics.proto.a a = new com.umeng.commonsdk.statistics.proto.a();
                                a.read(ai);
                                c.b.add(a);
                            }
                            ai.q();
                            c.b(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 3: {
                        if (l.b == 11) {
                            c.c = ai.z();
                            c.c(true);
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
            c.n();
        }


        public void b(final ai ai, final c c) throws p {
            c.n();
            ai.a(c.f);
            if (c.a != null) {
                ai.a(c.g);
                ai.a(new af((byte)11, (byte)12, c.a.size()));
                for (final Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : c.a.entrySet()) {
                    ai.a(entry.getKey());
                    entry.getValue().write(ai);
                }
                ai.e();
                ai.c();
            }
            if (c.b != null && c.j()) {
                ai.a(c.h);
                ai.a(new ae((byte)12, c.b.size()));
                final Iterator<com.umeng.commonsdk.statistics.proto.a> iterator2 = c.b.iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().write(ai);
                }
                ai.f();
                ai.c();
            }
            if (c.c != null && c.m()) {
                ai.a(c.i);
                ai.a(c.c);
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
            this.a(arg1, ((com.umeng.commonsdk.statistics.proto.c)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.b(arg1, ((com.umeng.commonsdk.statistics.proto.c)arg2));
        }

        public void a(final ai ai, final com.umeng.commonsdk.statistics.proto.c c) throws p {
            final ao ao = (ao)ai;
            ao.a(c.a.size());
            for (final Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : c.a.entrySet()) {
                ao.a(entry.getKey());
                entry.getValue().write(ao);
            }
            final BitSet set = new BitSet();
            if (c.j()) {
                set.set(0);
            }
            if (c.m()) {
                set.set(1);
            }
            ao.a(set, 2);
            if (c.j()) {
                ao.a(c.b.size());
                final Iterator<com.umeng.commonsdk.statistics.proto.a> iterator2 = c.b.iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().write(ao);
                }
            }
            if (c.m()) {
                ao.a(c.c);
            }
        }

        public void b(final ai ai, final com.umeng.commonsdk.statistics.proto.c c) throws p {
            final ao ao = (ao)ai;
            final af af = new af((byte)11, (byte)12, ao.w());
            c.a = new HashMap<String, com.umeng.commonsdk.statistics.proto.b>(2 * af.c);
            for (int i = 0; i < af.c; ++i) {
                final String z = ao.z();
                final com.umeng.commonsdk.statistics.proto.b b = new com.umeng.commonsdk.statistics.proto.b();
                b.read(ao);
                c.a.put(z, b);
            }
            c.a(true);
            final BitSet b2 = ao.b(2);
            if (b2.get(0)) {
                final ae ae = new ae((byte)12, ao.w());
                c.b = new ArrayList<com.umeng.commonsdk.statistics.proto.a>(ae.b);
                for (int j = 0; j < ae.b; ++j) {
                    final com.umeng.commonsdk.statistics.proto.a a = new com.umeng.commonsdk.statistics.proto.a();
                    a.read(ao);
                    c.b.add(a);
                }
                c.b(true);
            }
            if (b2.get(1)) {
                c.c = ao.z();
                c.c(true);
            }
        }
    }
}
