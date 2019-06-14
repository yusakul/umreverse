package com.umeng.commonsdk.statistics.proto;

import java.io.*;
import com.umeng.commonsdk.proguard.*;
import java.util.*;

public class e implements j , Serializable, Cloneable
{
    private static final long e = 7501688097813630241L;
    private static final an f;
    private static final ad g;
    private static final ad h;
    private static final ad i;
    private static final Map<Class<? extends aq>, ar> j;
    public String a;
    public long b;
    public String c;
    private static final int k = 0;
    private byte l;
    private e_enum[] m;
    public static final Map<e_enum, v> d;

    public j deepCopy() {
        return this.a();
    }


    public q fieldForId(int arg1) {
        return this.a(arg1);
    }


    public e() {
        this.l = 0;
        this.m = new e_enum[] { com.umeng.commonsdk.statistics.proto.e.e_enum.a };
    }
    
    public e(final long b, final String c) {
        this();
        this.b = b;
        this.b(true);
        this.c = c;
    }
    
    public e(final e e) {
        this.l = 0;
        this.m = new e_enum[] { com.umeng.commonsdk.statistics.proto.e.e_enum.a };
        this.l = e.l;
        if (e.d()) {
            this.a = e.a;
        }
        this.b = e.b;
        if (e.j()) {
            this.c = e.c;
        }
    }
    
    public e a() {
        return new e(this);
    }
    
    @Override
    public void clear() {
        this.a = null;
        this.b(false);
        this.b = 0L;
        this.c = null;
    }
    
    public String b() {
        return this.a;
    }
    
    public e a(final String a) {
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
    
    public e a(final long b) {
        this.b = b;
        this.b(true);
        return this;
    }
    
    public void f() {
        this.l = com.umeng.commonsdk.proguard.g.b(this.l, 0);
    }
    
    public boolean g() {
        return com.umeng.commonsdk.proguard.g.a(this.l, 0);
    }
    
    public void b(final boolean b) {
        this.l = com.umeng.commonsdk.proguard.g.a(this.l, 0, b);
    }
    
    public String h() {
        return this.c;
    }
    
    public e b(final String c) {
        this.c = c;
        return this;
    }
    
    public void i() {
        this.c = null;
    }
    
    public boolean j() {
        return this.c != null;
    }
    
    public void c(final boolean b) {
        if (!b) {
            this.c = null;
        }
    }
    
    public e_enum a(final int n) {
        return com.umeng.commonsdk.statistics.proto.e.e_enum.a(n);
    }
    
    @Override
    public void read(final ai ai) throws p {
        com.umeng.commonsdk.statistics.proto.e.j.get(ai.D()).b().b(ai, this);
    }
    
    @Override
    public void write(final ai ai) throws p {
        com.umeng.commonsdk.statistics.proto.e.j.get(ai.D()).b().a(ai, this);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImprintValue(");
        boolean b = true;
        if (this.d()) {
            sb.append("value:");
            if (this.a == null) {
                sb.append("null");
            }
            else {
                sb.append(this.a);
            }
            b = false;
        }
        if (!b) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.b);
        if (!false) {
            sb.append(", ");
        }
        sb.append("guid:");
        if (this.c == null) {
            sb.append("null");
        }
        else {
            sb.append(this.c);
        }
        sb.append(")");
        return sb.toString();
    }
    
    public void k() throws p {
        if (this.c == null) {
            throw new aj("Required field 'guid' was not present! Struct: " + this.toString());
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
        f = new an("ImprintValue");
        g = new ad("value", (byte)11, (short)1);
        h = new ad("ts", (byte)10, (short)2);
        i = new ad("guid", (byte)11, (short)3);
        (j = new HashMap<Class<? extends aq>, ar>()).put(as.class, new InnerClass_b());
        com.umeng.commonsdk.statistics.proto.e.j.put(at.class, new InnerClass_d());
        final EnumMap<e_enum, v> m = new EnumMap<e_enum, v>(e_enum.class);
        m.put(com.umeng.commonsdk.statistics.proto.e.e_enum.a, new v("value", (byte)2, new w((byte)11)));
        m.put(com.umeng.commonsdk.statistics.proto.e.e_enum.b, new v("ts", (byte)1, new w((byte)10)));
        m.put(com.umeng.commonsdk.statistics.proto.e.e_enum.c, new v("guid", (byte)1, new w((byte)11)));
        v.a(e.class, d = Collections.unmodifiableMap((Map)m));
    }
    
    public enum e_enum implements q
    {
        a((short)1, "value"), 
        b((short)2, "ts"), 
        c((short)3, "guid");
        
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
            for (final e_enum e : EnumSet.allOf(e_enum.class)) {
                e.d.put(e.b(), e);
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
            this.b(arg1, ((e)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.a(arg1, ((e)arg2));
        }

        public void a(final ai ai, final e e) throws p {
            ai.j();
            while (true) {
                final ad l = ai.l();
                if (l.b == 0) {
                    break;
                }
                switch (l.c) {
                    case 1: {
                        if (l.b == 11) {
                            e.a = ai.z();
                            e.a(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 2: {
                        if (l.b == 10) {
                            e.b = ai.x();
                            e.b(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 3: {
                        if (l.b == 11) {
                            e.c = ai.z();
                            e.c(true);
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
            if (!e.g()) {
                throw new aj("Required field 'ts' was not found in serialized data! Struct: " + this.toString());
            }
            e.k();
        }

        public void b(final ai ai, final e e) throws p {
            e.k();
            ai.a(e.f);
            if (e.a != null && e.d()) {
                ai.a(e.g);
                ai.a(e.a);
                ai.c();
            }
            ai.a(e.h);
            ai.a(e.b);
            ai.c();
            if (e.c != null) {
                ai.a(e.i);
                ai.a(e.c);
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
            this.a(arg1, ((e)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.b(arg1, ((e)arg2));
        }

        public void a(final ai ai, final e e) throws p {
            final ao ao = (ao)ai;
            ao.a(e.b);
            ao.a(e.c);
            final BitSet set = new BitSet();
            if (e.d()) {
                set.set(0);
            }
            ao.a(set, 1);
            if (e.d()) {
                ao.a(e.a);
            }
        }

        public void b(final ai ai, final e e) throws p {
            final ao ao = (ao)ai;
            e.b = ao.x();
            e.b(true);
            e.c = ao.z();
            e.c(true);
            if (ao.b(1).get(0)) {
                e.a = ao.z();
                e.a(true);
            }
        }
    }
}
