package com.umeng.commonsdk.stateless;

import java.nio.*;
import java.io.*;
import com.umeng.commonsdk.proguard.*;
import java.util.*;

public class b implements j , Serializable, Cloneable
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

    public q fieldForId(int arg1) {
        return this.e(arg1);
    }

    public j deepCopy() {
        return this.a();
    }

    public b() {
        this.C = 0;
        this.D = new e_enum[] { e_enum.codex };
    }
    
    public b(final String a, final String b, final String c, final int d, final int e, final int f, final ByteBuffer g, final String h, final String i) {
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
    
    public b(final b b) {
        this.C = 0;
        this.D = new e_enum[] { e_enum.codex };
        this.C = b.C;
        if (b.d()) {
            this.a = b.a;
        }
        if (b.g()) {
            this.b = b.b;
        }
        if (b.j()) {
            this.c = b.c;
        }
        this.d = b.d;
        this.e = b.e;
        this.f = b.f;
        if (b.w()) {
            this.g = com.umeng.commonsdk.proguard.k.d(b.g);
        }
        if (b.z()) {
            this.h = b.h;
        }
        if (b.C()) {
            this.i = b.i;
        }
        this.j = b.j;
    }
    
    public b a() {
        return new b(this);
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
    
    public String e() {
        return this.b;
    }
    
    public b b(final String b) {
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
    
    public b c(final String c) {
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
    
    public b a(final int d) {
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
    
    public b b(final int e) {
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
    
    public b c(final int f) {
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
    
    public b a(final byte[] array) {
        this.a((array == null) ? null : ByteBuffer.wrap(array));
        return this;
    }
    
    public b a(final ByteBuffer g) {
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
    
    public b d(final String h) {
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
    
    public b e(final String i) {
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
    
    public b d(final int j) {
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
        return com.umeng.commonsdk.stateless.b.e_enum.a(n);
    }
    
    @Override
    public void read(final ai ai) throws p {
        com.umeng.commonsdk.stateless.b.x.get(ai.D()).b().b(ai, this);
    }
    
    @Override
    public void write(final ai ai) throws p {
        com.umeng.commonsdk.stateless.b.x.get(ai.D()).b().a(ai, this);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UMSLEnvelope(");
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
        m = new an("UMSLEnvelope");
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
        x = new HashMap<Class<? extends aq>, ar>();
        x.put(as.class, new com.umeng.commonsdk.stateless.b.InnerClass_b());
        x.put(at.class, new InnerClass_d());
        final EnumMap<e_enum, v> i = new EnumMap<e_enum, v>(e_enum.class);
        i.put(e_enum.version, new v("version", (byte)1, new w((byte)11)));
        i.put(e_enum.address, new v("address", (byte)1, new w((byte)11)));
        i.put(e_enum.signature, new v("signature", (byte)1, new w((byte)11)));
        i.put(e_enum.serial_num, new v("serial_num", (byte)1, new w((byte)8)));
        i.put(e_enum.ts_secs, new v("ts_secs", (byte)1, new w((byte)8)));
        i.put(e_enum.length, new v("length", (byte)1, new w((byte)8)));
        i.put(e_enum.entity, new v("entity", (byte)1, new w((byte)11, true)));
        i.put(e_enum.guid, new v("guid", (byte)1, new w((byte)11)));
        i.put(e_enum.checksum, new v("checksum", (byte)1, new w((byte)11)));
        i.put(e_enum.codex, new v("codex", (byte)2, new w((byte)8)));
        com.umeng.commonsdk.proguard.v.a(b.class, k = Collections.unmodifiableMap((Map)i));
    }
    
    public enum e_enum implements q
    {
        version((short)1, "version"),
        address((short)2, "address"),
        signature((short)3, "signature"),
        serial_num((short)4, "serial_num"),
        ts_secs((short)5, "ts_secs"),
        length((short)6, "length"),
        entity((short)7, "entity"),
        guid((short)8, "guid"),
        checksum((short)9, "checksum"),
        codex((short)10, "codex");
        
        private static final Map<String, e_enum> k;
        private final short l;
        private final String m;
        
        public static e_enum a(final int n) {
            switch (n) {
                case 1: {
                    return e_enum.version;
                }
                case 2: {
                    return e_enum.address;
                }
                case 3: {
                    return e_enum.signature;
                }
                case 4: {
                    return e_enum.serial_num;
                }
                case 5: {
                    return e_enum.ts_secs;
                }
                case 6: {
                    return e_enum.length;
                }
                case 7: {
                    return e_enum.entity;
                }
                case 8: {
                    return e_enum.guid;
                }
                case 9: {
                    return e_enum.checksum;
                }
                case 10: {
                    return e_enum.codex;
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
            return ts_secs.k.get(s);
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
                ts_secs.k.put(eEnum2.b(), eEnum2);
            }
        }
    }
    
    private static class InnerClass_b implements ar
    {
        public a a() {
            return new a();
        }


        public aq b() {
            return this.a();
        }
    }
    
    private static class a extends as<b>
    {
        @Override
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
                        if (l.b == 11) {
                            b.b = ai.z();
                            b.b(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 3: {
                        if (l.b == 11) {
                            b.c = ai.z();
                            b.c(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 4: {
                        if (l.b == 8) {
                            b.d = ai.w();
                            b.d(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 5: {
                        if (l.b == 8) {
                            b.e = ai.w();
                            b.e(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 6: {
                        if (l.b == 8) {
                            b.f = ai.w();
                            b.f(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 7: {
                        if (l.b == 11) {
                            b.g = ai.A();
                            b.g(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 8: {
                        if (l.b == 11) {
                            b.h = ai.z();
                            b.h(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 9: {
                        if (l.b == 11) {
                            b.i = ai.z();
                            b.i(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 10: {
                        if (l.b == 8) {
                            b.j = ai.w();
                            b.j(true);
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
            if (!b.m()) {
                throw new aj("Required field 'serial_num' was not found in serialized data! Struct: " + this.toString());
            }
            if (!b.p()) {
                throw new aj("Required field 'ts_secs' was not found in serialized data! Struct: " + this.toString());
            }
            if (!b.s()) {
                throw new aj("Required field 'length' was not found in serialized data! Struct: " + this.toString());
            }
            b.G();
        }
        
        @Override
        public void b(final ai ai, final b b) throws p {
            b.G();
            ai.a(b.m);
            if (b.a != null) {
                ai.a(b.n);
                ai.a(b.a);
                ai.c();
            }
            if (b.b != null) {
                ai.a(b.o);
                ai.a(b.b);
                ai.c();
            }
            if (b.c != null) {
                ai.a(b.p);
                ai.a(b.c);
                ai.c();
            }
            ai.a(b.q);
            ai.a(b.d);
            ai.c();
            ai.a(b.r);
            ai.a(b.e);
            ai.c();
            ai.a(b.s);
            ai.a(b.f);
            ai.c();
            if (b.g != null) {
                ai.a(b.t);
                ai.a(b.g);
                ai.c();
            }
            if (b.h != null) {
                ai.a(b.u);
                ai.a(b.h);
                ai.c();
            }
            if (b.i != null) {
                ai.a(b.v);
                ai.a(b.i);
                ai.c();
            }
            if (b.F()) {
                ai.a(b.w);
                ai.a(b.j);
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
    
    private static class c extends at<b>
    {
        @Override
        public void a(final ai ai, final b b) throws p {
            final ao ao = (ao)ai;
            ao.a(b.a);
            ao.a(b.b);
            ao.a(b.c);
            ao.a(b.d);
            ao.a(b.e);
            ao.a(b.f);
            ao.a(b.g);
            ao.a(b.h);
            ao.a(b.i);
            final BitSet set = new BitSet();
            if (b.F()) {
                set.set(0);
            }
            ao.a(set, 1);
            if (b.F()) {
                ao.a(b.j);
            }
        }
        
        @Override
        public void b(final ai ai, final b b) throws p {
            final ao ao = (ao)ai;
            b.a = ao.z();
            b.a(true);
            b.b = ao.z();
            b.b(true);
            b.c = ao.z();
            b.c(true);
            b.d = ao.w();
            b.d(true);
            b.e = ao.w();
            b.e(true);
            b.f = ao.w();
            b.f(true);
            b.g = ao.A();
            b.g(true);
            b.h = ao.z();
            b.h(true);
            b.i = ao.z();
            b.i(true);
            if (ao.b(1).get(0)) {
                b.j = ao.w();
                b.j(true);
            }
        }
    }
}
