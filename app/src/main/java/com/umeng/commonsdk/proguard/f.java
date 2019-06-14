package com.umeng.commonsdk.proguard;

import java.nio.*;
import java.io.*;
import java.util.*;

public class f implements j , Serializable, Cloneable
{
    private static final long l = 420342210744516016L;
    private static final an m;
    private static final ad n;
    private static final ad o;
    private static final ad p;
    private static final ad q;
    private static final ad r;
    private static final ad s;
    private static final ad t;
    private static final ad u;
    private static final ad v;
    private static final ad w;
    private static final Map<Class<? extends aq>, ar> x;
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;
    private static final int y = 0;
    private static final int z = 1;
    private static final int A = 2;
    private static final int B = 3;
    private byte C;
    private e_enum[] D;
    public static final Map<e_enum, v> k;

    public j deepCopy() {
        return this.a();
    }

    public q fieldForId(int arg1) {
        return this.e(arg1);
    }

    public f() {
        this.C = 0;
        this.D = new e_enum[] { com.umeng.commonsdk.proguard.f.e_enum.j };
    }
    
    public f(final String a, final String b, final String c, final int d, final int e, final int f, final ByteBuffer g, final String h, final String i) {
        this();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.d(true);
        this.e = e;
        this.e(true);
        this.f = f;
        this.f(true);
        this.g = g;
        this.h = h;
        this.i = i;
    }
    
    public f(final f f) {
        this.C = 0;
        this.D = new e_enum[] { com.umeng.commonsdk.proguard.f.e_enum.j };
        this.C = f.C;
        if (f.d()) {
            this.a = f.a;
        }
        if (f.g()) {
            this.b = f.b;
        }
        if (f.j()) {
            this.c = f.c;
        }
        this.d = f.d;
        this.e = f.e;
        this.f = f.f;
        if (f.w()) {
            this.g = com.umeng.commonsdk.proguard.k.d(f.g);
        }
        if (f.z()) {
            this.h = f.h;
        }
        if (f.C()) {
            this.i = f.i;
        }
        this.j = f.j;
    }
    
    public f a() {
        return new f(this);
    }
    
    @Override
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d(false);
        this.d = 0;
        this.e(false);
        this.e = 0;
        this.f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j(false);
        this.j = 0;
    }
    
    public String b() {
        return this.a;
    }
    
    public f a(final String a) {
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
    
    public f b(final String b) {
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
    
    public f c(final String c) {
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
    
    public int k() {
        return this.d;
    }
    
    public f a(final int d) {
        this.d = d;
        this.d(true);
        return this;
    }
    
    public void l() {
        this.C = com.umeng.commonsdk.proguard.g.b(this.C, 0);
    }
    
    public boolean m() {
        return com.umeng.commonsdk.proguard.g.a(this.C, 0);
    }
    
    public void d(final boolean b) {
        this.C = com.umeng.commonsdk.proguard.g.a(this.C, 0, b);
    }
    
    public int n() {
        return this.e;
    }
    
    public f b(final int e) {
        this.e = e;
        this.e(true);
        return this;
    }
    
    public void o() {
        this.C = com.umeng.commonsdk.proguard.g.b(this.C, 1);
    }
    
    public boolean p() {
        return com.umeng.commonsdk.proguard.g.a(this.C, 1);
    }
    
    public void e(final boolean b) {
        this.C = com.umeng.commonsdk.proguard.g.a(this.C, 1, b);
    }
    
    public int q() {
        return this.f;
    }
    
    public f c(final int f) {
        this.f = f;
        this.f(true);
        return this;
    }
    
    public void r() {
        this.C = com.umeng.commonsdk.proguard.g.b(this.C, 2);
    }
    
    public boolean s() {
        return com.umeng.commonsdk.proguard.g.a(this.C, 2);
    }
    
    public void f(final boolean b) {
        this.C = com.umeng.commonsdk.proguard.g.a(this.C, 2, b);
    }
    
    public byte[] t() {
        this.a(com.umeng.commonsdk.proguard.k.c(this.g));
        return (byte[])((this.g == null) ? null : this.g.array());
    }
    
    public ByteBuffer u() {
        return this.g;
    }
    
    public f a(final byte[] array) {
        this.a((array == null) ? null : ByteBuffer.wrap(array));
        return this;
    }
    
    public f a(final ByteBuffer g) {
        this.g = g;
        return this;
    }
    
    public void v() {
        this.g = null;
    }
    
    public boolean w() {
        return this.g != null;
    }
    
    public void g(final boolean b) {
        if (!b) {
            this.g = null;
        }
    }
    
    public String x() {
        return this.h;
    }
    
    public f d(final String h) {
        this.h = h;
        return this;
    }
    
    public void y() {
        this.h = null;
    }
    
    public boolean z() {
        return this.h != null;
    }
    
    public void h(final boolean b) {
        if (!b) {
            this.h = null;
        }
    }
    
    public String A() {
        return this.i;
    }
    
    public f e(final String i) {
        this.i = i;
        return this;
    }
    
    public void B() {
        this.i = null;
    }
    
    public boolean C() {
        return this.i != null;
    }
    
    public void i(final boolean b) {
        if (!b) {
            this.i = null;
        }
    }
    
    public int D() {
        return this.j;
    }
    
    public f d(final int j) {
        this.j = j;
        this.j(true);
        return this;
    }
    
    public void E() {
        this.C = com.umeng.commonsdk.proguard.g.b(this.C, 3);
    }
    
    public boolean F() {
        return com.umeng.commonsdk.proguard.g.a(this.C, 3);
    }
    
    public void j(final boolean b) {
        this.C = com.umeng.commonsdk.proguard.g.a(this.C, 3, b);
    }
    
    public e_enum e(final int n) {
        return com.umeng.commonsdk.proguard.f.e_enum.a(n);
    }
    
    @Override
    public void read(final ai ai) throws p {
        com.umeng.commonsdk.proguard.f.x.get(ai.D()).b().b(ai, this);
    }
    
    @Override
    public void write(final ai ai) throws p {
        com.umeng.commonsdk.proguard.f.x.get(ai.D()).b().a(ai, this);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        if (this.a == null) {
            sb.append("null");
        }
        else {
            sb.append(this.a);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("address:");
        if (this.b == null) {
            sb.append("null");
        }
        else {
            sb.append(this.b);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("signature:");
        if (this.c == null) {
            sb.append("null");
        }
        else {
            sb.append(this.c);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("serial_num:");
        sb.append(this.d);
        if (!false) {
            sb.append(", ");
        }
        sb.append("ts_secs:");
        sb.append(this.e);
        if (!false) {
            sb.append(", ");
        }
        sb.append("length:");
        sb.append(this.f);
        if (!false) {
            sb.append(", ");
        }
        sb.append("entity:");
        if (this.g == null) {
            sb.append("null");
        }
        else {
            com.umeng.commonsdk.proguard.k.a(this.g, sb);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("guid:");
        if (this.h == null) {
            sb.append("null");
        }
        else {
            sb.append(this.h);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("checksum:");
        if (this.i == null) {
            sb.append("null");
        }
        else {
            sb.append(this.i);
        }
        final boolean b = false;
        if (this.F()) {
            if (!b) {
                sb.append(", ");
            }
            sb.append("codex:");
            sb.append(this.j);
        }
        sb.append(")");
        return sb.toString();
    }
    
    public void G() throws p {
        if (this.a == null) {
            throw new aj("Required field 'version' was not present! Struct: " + this.toString());
        }
        if (this.b == null) {
            throw new aj("Required field 'address' was not present! Struct: " + this.toString());
        }
        if (this.c == null) {
            throw new aj("Required field 'signature' was not present! Struct: " + this.toString());
        }
        if (this.g == null) {
            throw new aj("Required field 'entity' was not present! Struct: " + this.toString());
        }
        if (this.h == null) {
            throw new aj("Required field 'guid' was not present! Struct: " + this.toString());
        }
        if (this.i == null) {
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
            this.C = 0;
            this.read(new ac(new au(objectInputStream)));
        }
        catch (p p) {
            throw new IOException(p.getMessage());
        }
    }
    
    static {
        m = new an("UMEnvelope");
        n = new ad("version", (byte)11, (short)1);
        o = new ad("address", (byte)11, (short)2);
        p = new ad("signature", (byte)11, (short)3);
        q = new ad("serial_num", (byte)8, (short)4);
        r = new ad("ts_secs", (byte)8, (short)5);
        s = new ad("length", (byte)8, (short)6);
        t = new ad("entity", (byte)11, (short)7);
        u = new ad("guid", (byte)11, (short)8);
        v = new ad("checksum", (byte)11, (short)9);
        w = new ad("codex", (byte)8, (short)10);
        (x = new HashMap<Class<? extends aq>, ar>()).put(as.class, new InnerClass_b());
        x.put(at.class, new InnerClass_d());
        final EnumMap<e_enum, v> i = new EnumMap<e_enum, v>(e_enum.class);
        i.put(e_enum.a, new v("version", (byte)1, new w((byte)11)));
        i.put(e_enum.b, new v("address", (byte)1, new w((byte)11)));
        i.put(e_enum.c, new v("signature", (byte)1, new w((byte)11)));
        i.put(e_enum.d, new v("serial_num", (byte)1, new w((byte)8)));
        i.put(e_enum.e, new v("ts_secs", (byte)1, new w((byte)8)));
        i.put(e_enum.f, new v("length", (byte)1, new w((byte)8)));
        i.put(e_enum.g, new v("entity", (byte)1, new w((byte)11, true)));
        i.put(e_enum.h, new v("guid", (byte)1, new w((byte)11)));
        i.put(e_enum.i, new v("checksum", (byte)1, new w((byte)11)));
        i.put(e_enum.j, new v("codex", (byte)2, new w((byte)8)));
        com.umeng.commonsdk.proguard.v.a(f.class, k = Collections.unmodifiableMap((Map)i));
    }
    
    public enum e_enum implements q
    {
        a((short)1, "version"), 
        b((short)2, "address"), 
        c((short)3, "signature"), 
        d((short)4, "serial_num"), 
        e((short)5, "ts_secs"), 
        f((short)6, "length"), 
        g((short)7, "entity"), 
        h((short)8, "guid"), 
        i((short)9, "checksum"), 
        j((short)10, "codex");
        
        private static final Map<String, e_enum> k;
        private final short l;
        private final String m;
        
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
                case 4: {
                    return e_enum.d;
                }
                case 5: {
                    return e_enum.e;
                }
                case 6: {
                    return e_enum.f;
                }
                case 7: {
                    return e_enum.g;
                }
                case 8: {
                    return e_enum.h;
                }
                case 9: {
                    return e_enum.i;
                }
                case 10: {
                    return e_enum.j;
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
            return e.k.get(s);
        }
        
        private e_enum(final short l, final String m) {
            this.l = l;
            this.m = m;
        }
        
        @Override
        public short a() {
            return this.l;
        }
        
        @Override
        public String b() {
            return this.m;
        }
        
        static {
            k = new HashMap<String, e_enum>();
            for (final e_enum eEnum2 : EnumSet.allOf(e_enum.class)) {
                e.k.put(eEnum2.b(), eEnum2);
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
            this.b(arg1, ((f)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.a(arg1, ((f)arg2));
        }

        public void a(final ai ai, final f f) throws p {
            ai.j();
            while (true) {
                final ad l = ai.l();
                if (l.b == 0) {
                    break;
                }
                switch (l.c) {
                    case 1: {
                        if (l.b == 11) {
                            f.a = ai.z();
                            f.a(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 2: {
                        if (l.b == 11) {
                            f.b = ai.z();
                            f.b(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 3: {
                        if (l.b == 11) {
                            f.c = ai.z();
                            f.c(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 4: {
                        if (l.b == 8) {
                            f.d = ai.w();
                            f.d(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 5: {
                        if (l.b == 8) {
                            f.e = ai.w();
                            f.e(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 6: {
                        if (l.b == 8) {
                            f.f = ai.w();
                            f.f(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 7: {
                        if (l.b == 11) {
                            f.g = ai.A();
                            f.g(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 8: {
                        if (l.b == 11) {
                            f.h = ai.z();
                            f.h(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 9: {
                        if (l.b == 11) {
                            f.i = ai.z();
                            f.i(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 10: {
                        if (l.b == 8) {
                            f.j = ai.w();
                            f.j(true);
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
            if (!f.m()) {
                throw new aj("Required field 'serial_num' was not found in serialized data! Struct: " + this.toString());
            }
            if (!f.p()) {
                throw new aj("Required field 'ts_secs' was not found in serialized data! Struct: " + this.toString());
            }
            if (!f.s()) {
                throw new aj("Required field 'length' was not found in serialized data! Struct: " + this.toString());
            }
            f.G();
        }

        public void b(final ai ai, final f f) throws p {
            f.G();
            ai.a(f.m);
            if (f.a != null) {
                ai.a(f.n);
                ai.a(f.a);
                ai.c();
            }
            if (f.b != null) {
                ai.a(f.o);
                ai.a(f.b);
                ai.c();
            }
            if (f.c != null) {
                ai.a(f.p);
                ai.a(f.c);
                ai.c();
            }
            ai.a(f.q);
            ai.a(f.d);
            ai.c();
            ai.a(f.r);
            ai.a(f.e);
            ai.c();
            ai.a(f.s);
            ai.a(f.f);
            ai.c();
            if (f.g != null) {
                ai.a(f.t);
                ai.a(f.g);
                ai.c();
            }
            if (f.h != null) {
                ai.a(f.u);
                ai.a(f.h);
                ai.c();
            }
            if (f.i != null) {
                ai.a(f.v);
                ai.a(f.i);
                ai.c();
            }
            if (f.F()) {
                ai.a(f.w);
                ai.a(f.j);
                ai.c();
            }
            ai.d();
            ai.b();
        }
    }
    
    private static class InnerClass_d implements ar
    {
        public c a() {
            return new c();
        }

        public aq b() {
            return this.a();
        }
    }
    
    private static class c extends at<f>
    {
        @Override
        public void a(final ai ai, final f f) throws p {
            final ao ao = (ao)ai;
            ao.a(f.a);
            ao.a(f.b);
            ao.a(f.c);
            ao.a(f.d);
            ao.a(f.e);
            ao.a(f.f);
            ao.a(f.g);
            ao.a(f.h);
            ao.a(f.i);
            final BitSet set = new BitSet();
            if (f.F()) {
                set.set(0);
            }
            ao.a(set, 1);
            if (f.F()) {
                ao.a(f.j);
            }
        }
        
        @Override
        public void b(final ai ai, final f f) throws p {
            final ao ao = (ao)ai;
            f.a = ao.z();
            f.a(true);
            f.b = ao.z();
            f.b(true);
            f.c = ao.z();
            f.c(true);
            f.d = ao.w();
            f.d(true);
            f.e = ao.w();
            f.e(true);
            f.f = ao.w();
            f.f(true);
            f.g = ao.A();
            f.g(true);
            f.h = ao.z();
            f.h(true);
            f.i = ao.z();
            f.i(true);
            if (ao.b(1).get(0)) {
                f.j = ao.w();
                f.j(true);
            }
        }
    }
}
