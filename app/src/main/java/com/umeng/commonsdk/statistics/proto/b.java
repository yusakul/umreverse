package com.umeng.commonsdk.statistics.proto;

import java.io.*;
import java.util.*;
import com.umeng.commonsdk.proguard.*;

public class b implements j, Serializable, Cloneable
{
    private static final long e = -6496538196005191531L;
    private static final an f;
    private static final ad g;
    private static final ad h;
    private static final ad i;
    private static final Map<Class<? extends aq>, ar> j;
    public String a;
    public long b;
    public int c;
    private static final int k = 0;
    private static final int l = 1;
    private byte m;
    public static final Map<e_enum, v> d;

    public q fieldForId(int arg1) {
        return this.b(arg1);
    }
    public j deepCopy() {
        return this.a();
    }
    
    public b() {
        this.m = 0;
    }
    
    public b(final String a, final long b, final int c) {
        this();
        this.a = a;
        this.b = b;
        this.b(true);
        this.c = c;
        this.c(true);
    }
    
    public b(final b b) {
        this.m = 0;
        this.m = b.m;
        if (b.d()) {
            this.a = b.a;
        }
        this.b = b.b;
        this.c = b.c;
    }
    
    public b a() {
        return new b();
    }
    
    @Override
    public void clear() {
        this.a = null;
        this.b(false);
        this.b = 0L;
        this.c(false);
        this.c = 0;
    }
    
    public String b() {
        return this.a;
    }
    
    public b a(final String a) {
        this.a = a;
        return this;
    }
    
    public void c() {
        this.a = null;
    }
    
    public boolean d() {
        return this.a != null;
    }
    
    public void a(final boolean b) {
        if (!b) {
            this.a = null;
        }
    }
    
    public long e() {
        return this.b;
    }
    
    public b a(final long b) {
        this.b = b;
        this.b(true);
        return this;
    }
    
    public void f() {
        this.m = com.umeng.commonsdk.proguard.g.b(this.m, 0);
    }
    
    public boolean g() {
        return com.umeng.commonsdk.proguard.g.a(this.m, 0);
    }
    
    public void b(final boolean b) {
        this.m = com.umeng.commonsdk.proguard.g.a(this.m, 0, b);
    }
    
    public int h() {
        return this.c;
    }
    
    public b a(final int c) {
        this.c = c;
        this.c(true);
        return this;
    }
    
    public void i() {
        this.m = com.umeng.commonsdk.proguard.g.b(this.m, 1);
    }
    
    public boolean j() {
        return com.umeng.commonsdk.proguard.g.a(this.m, 1);
    }
    
    public void c(final boolean b) {
        this.m = com.umeng.commonsdk.proguard.g.a(this.m, 1, b);
    }
    
    public e_enum b(final int n) {
        return com.umeng.commonsdk.statistics.proto.b.e_enum.a(n);
    }
    
    @Override
    public void read(final ai ai) throws p {
        com.umeng.commonsdk.statistics.proto.b.j.get(ai.D()).b().b(ai, this);
    }
    
    @Override
    public void write(final ai ai) throws p {
        com.umeng.commonsdk.statistics.proto.b.j.get(ai.D()).b().a(ai, this);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        if (this.a == null) {
            sb.append("null");
        }
        else {
            sb.append(this.a);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.b);
        if (!false) {
            sb.append(", ");
        }
        sb.append("version:");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }
    
    public void k() throws p {
        if (this.a == null) {
            throw new aj("Required field 'identity' was not present! Struct: " + this.toString());
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
            this.m = 0;
            this.read(new ac(new au(objectInputStream)));
        }
        catch (p p) {
            throw new IOException(p.getMessage());
        }
    }
    
    static {
        f = new an("IdSnapshot");
        g = new ad("identity", (byte)11, (short)1);
        h = new ad("ts", (byte)10, (short)2);
        i = new ad("version", (byte)8, (short)3);
        (j = new HashMap<Class<? extends aq>, ar>()).put(as.class, new InnerClass_b());
        j.put(at.class, new InnerClass_d());
        final EnumMap<e_enum, v> m = new EnumMap<e_enum, v>(e_enum.class);
        m.put(com.umeng.commonsdk.statistics.proto.b.e_enum.a, new v("identity", (byte)1, new w((byte)11)));
        m.put(com.umeng.commonsdk.statistics.proto.b.e_enum.b, new v("ts", (byte)1, new w((byte)10)));
        m.put(com.umeng.commonsdk.statistics.proto.b.e_enum.c, new v("version", (byte)1, new w((byte)8)));
        v.a(b.class, d = Collections.unmodifiableMap((Map)m));
    }
    
    public enum e_enum implements q
    {
        a((short)1, "identity"), 
        b((short)2, "ts"), 
        c((short)3, "version");
        
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
            this.b(arg1, ((b)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.a(arg1, ((b)arg2));
        }


        public void a(final ai ai, final b b) throws p {
            ai.j();
            while (true) {
                final ad l = ai.l();
                if (l.b == 0) {
                    break;
                }
                switch (l.c) {
                    case 1: {
                        if (l.b == 11) {
                            b.a = ai.z();
                            b.a(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 2: {
                        if (l.b == 10) {
                            b.b = ai.x();
                            b.b(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 3: {
                        if (l.b == 8) {
                            b.c = ai.w();
                            b.c(true);
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
            if (!b.g()) {
                throw new aj("Required field 'ts' was not found in serialized data! Struct: " + this.toString());
            }
            if (!b.j()) {
                throw new aj("Required field 'version' was not found in serialized data! Struct: " + this.toString());
            }
            b.k();
        }
        

        public void b(final ai ai, final b b) throws p {
            b.k();
            ai.a(b.f);
            if (b.a != null) {
                ai.a(b.g);
                ai.a(b.a);
                ai.c();
            }
            ai.a(b.h);
            ai.a(b.b);
            ai.c();
            ai.a(b.i);
            ai.a(b.c);
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
            this.a(arg1, ((b)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.b(arg1, ((b)arg2));
        }

        public void a(final ai ai, final b b) throws p {
            final ao ao = (ao)ai;
            ao.a(b.a);
            ao.a(b.b);
            ao.a(b.c);
        }
        

        public void b(final ai ai, final b b) throws p {
            final ao ao = (ao)ai;
            b.a = ao.z();
            b.a(true);
            b.b = ao.x();
            b.b(true);
            b.c = ao.w();
            b.c(true);
        }
    }
}
