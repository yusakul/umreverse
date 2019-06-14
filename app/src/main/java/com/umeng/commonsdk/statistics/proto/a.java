package com.umeng.commonsdk.statistics.proto;

import java.io.*;
import com.umeng.commonsdk.proguard.*;
import java.util.*;

public class a implements j , Serializable, Cloneable
{
    private static final long f = 9132678615281394583L;
    private static final an g;
    private static final ad h;
    private static final ad i;
    private static final ad j;
    private static final ad k;
    private static final Map<Class<? extends aq>, ar> l;
    public String a;
    public String b;
    public String c;
    public long d;
    private static final int m = 0;
    private byte n;
    private e_enum[] o;
    public static final Map<e_enum, v> e;

    public q fieldForId(int arg1) {
        return this.a(arg1);
    }
    public j deepCopy() {
        return this.a();
    }

    public a() {
        this.n = 0;
        this.o = new e_enum[] { com.umeng.commonsdk.statistics.proto.a.e_enum.b };
    }
    
    public a(final String a, final String c, final long d) {
        this();
        this.a = a;
        this.c = c;
        this.d = d;
        this.d(true);
    }
    
    public a(final a a) {
        this.n = 0;
        this.o = new e_enum[] { com.umeng.commonsdk.statistics.proto.a.e_enum.b };
        this.n = a.n;
        if (a.d()) {
            this.a = a.a;
        }
        if (a.g()) {
            this.b = a.b;
        }
        if (a.j()) {
            this.c = a.c;
        }
        this.d = a.d;
    }
    
    public a a() {
        return new a();
    }
    
    @Override
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d(false);
        this.d = 0L;
    }
    
    public String b() {
        return this.a;
    }
    
    public a a(final String a) {
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
    
    public String e() {
        return this.b;
    }
    
    public a b(final String b) {
        this.b = b;
        return this;
    }
    
    public void f() {
        this.b = null;
    }
    
    public boolean g() {
        return this.b != null;
    }
    
    public void b(final boolean b) {
        if (!b) {
            this.b = null;
        }
    }
    
    public String h() {
        return this.c;
    }
    
    public a c(final String c) {
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
    
    public long k() {
        return this.d;
    }
    
    public a a(final long d) {
        this.d = d;
        this.d(true);
        return this;
    }
    
    public void l() {
        this.n = com.umeng.commonsdk.proguard.g.b(this.n, 0);
    }
    
    public boolean m() {
        return com.umeng.commonsdk.proguard.g.a(this.n, 0);
    }
    
    public void d(final boolean b) {
        this.n = com.umeng.commonsdk.proguard.g.a(this.n, 0, b);
    }
    
    public e_enum a(final int n) {
        return com.umeng.commonsdk.statistics.proto.a.e_enum.a(n);
    }
    
    @Override
    public void read(final ai ai) throws p {
        com.umeng.commonsdk.statistics.proto.a.l.get(ai.D()).b().b(ai, this);
    }
    
    @Override
    public void write(final ai ai) throws p {
        com.umeng.commonsdk.statistics.proto.a.l.get(ai.D()).b().a(ai, this);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        if (this.a == null) {
            sb.append("null");
        }
        else {
            sb.append(this.a);
        }
        int n = 0;
        if (this.g()) {
            if (n == 0) {
                sb.append(", ");
            }
            sb.append("old_id:");
            if (this.b == null) {
                sb.append("null");
            }
            else {
                sb.append(this.b);
            }
            n = 0;
        }
        if (n == 0) {
            sb.append(", ");
        }
        sb.append("new_id:");
        if (this.c == null) {
            sb.append("null");
        }
        else {
            sb.append(this.c);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.d);
        sb.append(")");
        return sb.toString();
    }
    
    public void n() throws p {
        if (this.a == null) {
            throw new aj("Required field 'domain' was not present! Struct: " + this.toString());
        }
        if (this.c == null) {
            throw new aj("Required field 'new_id' was not present! Struct: " + this.toString());
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
            this.n = 0;
            this.read(new ac(new au(objectInputStream)));
        }
        catch (p p) {
            throw new IOException(p.getMessage());
        }
    }
    
    static {
        g = new an("IdJournal");
        h = new ad("domain", (byte)11, (short)1);
        i = new ad("old_id", (byte)11, (short)2);
        j = new ad("new_id", (byte)11, (short)3);
        k = new ad("ts", (byte)10, (short)4);
        l = new HashMap<Class<? extends aq>, ar>();
        l.put(as.class, new InnerClass_b());
        l.put(at.class, new InnerClass_d());
        final EnumMap<e_enum, v> m = new EnumMap<e_enum, v>(e_enum.class);
        m.put(com.umeng.commonsdk.statistics.proto.a.e_enum.a, new v("domain", (byte)1, new w((byte)11)));
        m.put(com.umeng.commonsdk.statistics.proto.a.e_enum.b, new v("old_id", (byte)2, new w((byte)11)));
        m.put(com.umeng.commonsdk.statistics.proto.a.e_enum.c, new v("new_id", (byte)1, new w((byte)11)));
        m.put(com.umeng.commonsdk.statistics.proto.a.e_enum.d, new v("ts", (byte)1, new w((byte)10)));
        v.a(a.class, e = Collections.unmodifiableMap((Map)m));
    }
    
    public enum e_enum implements q
    {
        a((short)1, "domain"), 
        b((short)2, "old_id"), 
        c((short)3, "new_id"), 
        d((short)4, "ts");
        
        private static final Map<String, e_enum> ENUM_MAP;
        private final short f;
        private final String g;
        
        public static e_enum a(final int n) {
            switch (n) {
                case 1: {
                    return com.umeng.commonsdk.statistics.proto.a.e_enum.a;
                }
                case 2: {
                    return com.umeng.commonsdk.statistics.proto.a.e_enum.b;
                }
                case 3: {
                    return com.umeng.commonsdk.statistics.proto.a.e_enum.c;
                }
                case 4: {
                    return com.umeng.commonsdk.statistics.proto.a.e_enum.d;
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
            return com.umeng.commonsdk.statistics.proto.a.e_enum.ENUM_MAP.get(s);
        }
        
        private e_enum(final short f, final String g) {
            this.f = f;
            this.g = g;
        }
        
        @Override
        public short a() {
            return this.f;
        }
        
        @Override
        public String b() {
            return this.g;
        }
        
        static {
            ENUM_MAP = new HashMap<String, e_enum>();
            for (final e_enum eEnum2 : EnumSet.allOf(e_enum.class)) {
                com.umeng.commonsdk.statistics.proto.a.e_enum.ENUM_MAP.put(eEnum2.b(), eEnum2);
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
            this.b(arg1, ((a)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.a(arg1, ((a)arg2));
        }

        public void a(final ai ai, final com.umeng.commonsdk.statistics.proto.a a) throws p {
            ai.j();
            while (true) {
                final ad l = ai.l();
                if (l.b == 0) {
                    break;
                }
                switch (l.c) {
                    case 1: {
                        if (l.b == 11) {
                            a.a = ai.z();
                            a.a(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 2: {
                        if (l.b == 11) {
                            a.b = ai.z();
                            a.b(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 3: {
                        if (l.b == 11) {
                            a.c = ai.z();
                            a.c(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 4: {
                        if (l.b == 10) {
                            a.d = ai.x();
                            a.d(true);
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
            if (!a.m()) {
                throw new aj("Required field 'ts' was not found in serialized data! Struct: " + this.toString());
            }
            a.n();
        }
        

        public void b(final ai ai, final com.umeng.commonsdk.statistics.proto.a a) throws p {
            a.n();
            ai.a(a.g);
            if (a.a != null) {
                ai.a(a.h);
                ai.a(a.a);
                ai.c();
            }
            if (a.b != null && a.g()) {
                ai.a(a.i);
                ai.a(a.b);
                ai.c();
            }
            if (a.c != null) {
                ai.a(a.j);
                ai.a(a.c);
                ai.c();
            }
            ai.a(a.k);
            ai.a(a.d);
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
            this.a(arg1, ((a)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.b(arg1, ((a)arg2));
        }


        public void a(final ai ai, final a a) throws p {
            final ao ao = (ao)ai;
            ao.a(a.a);
            ao.a(a.c);
            ao.a(a.d);
            final BitSet set = new BitSet();
            if (a.g()) {
                set.set(0);
            }
            ao.a(set, 1);
            if (a.g()) {
                ao.a(a.b);
            }
        }
        

        public void b(final ai ai, final a a) throws p {
            final ao ao = (ao)ai;
            a.a = ao.z();
            a.a(true);
            a.c = ao.z();
            a.c(true);
            a.d = ao.x();
            a.d(true);
            if (ao.b(1).get(0)) {
                a.b = ao.z();
                a.b(true);
            }
        }
    }
}
