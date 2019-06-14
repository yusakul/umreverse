package com.umeng.commonsdk.proguard;

import java.io.*;
import java.nio.*;

public class ac extends ai
{
    private static final an d;
    private static final ad e;
    private static final byte[] f;
    private static final byte h = -126;
    private static final byte i = 1;
    private static final byte j = 31;
    private static final byte k = -32;
    private static final int l = 5;
    private h m;
    private short n;
    private ad o;
    private Boolean p;
    private final long q;
    byte[] a;
    byte[] b;
    private byte[] r;
    byte[] c;
    
    public ac(final aw aw, final long q) {
        super(aw);
        this.m = new h(15);
        this.n = 0;
        this.o = null;
        this.p = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.r = new byte[1];
        this.c = new byte[1];
        this.q = q;
    }
    
    public ac(final aw aw) {
        this(aw, -1L);
    }
    
    @Override
    public void B() {
        this.m.c();
        this.n = 0;
    }
    
    @Override
    public void a(final ag ag) throws p {
        this.b((byte)(-126));
        this.d(0x1 | (ag.b << 5 & 0xFFFFFFE0));
        this.b(ag.c);
        this.a(ag.a);
    }
    
    @Override
    public void a(final an an) throws p {
        this.m.a(this.n);
        this.n = 0;
    }
    
    @Override
    public void b() throws p {
        this.n = this.m.a();
    }
    
    @Override
    public void a(final ad o) throws p {
        if (o.b == 2) {
            this.o = o;
        }
        else {
            this.a(o, (byte)(-1));
        }
    }
    
    private void a(final ad ad, final byte b) throws p {
        final byte b2 = (b == -1) ? this.e(ad.b) : b;
        if (ad.c > this.n && ad.c - this.n <= 15) {
            this.d(ad.c - this.n << 4 | b2);
        }
        else {
            this.b(b2);
            this.a(ad.c);
        }
        this.n = ad.c;
    }
    
    @Override
    public void d() throws p {
        this.b((byte)0);
    }
    
    @Override
    public void a(final af af) throws p {
        if (af.c == 0) {
            this.d(0);
        }
        else {
            this.b(af.c);
            this.d(this.e(af.a) << 4 | this.e(af.b));
        }
    }
    
    @Override
    public void a(final ae ae) throws p {
        this.a(ae.a, ae.b);
    }
    
    @Override
    public void a(final am am) throws p {
        this.a(am.a, am.b);
    }
    
    @Override
    public void a(final boolean b) throws p {
        if (this.o != null) {
            this.a(this.o, (byte)(b ? 1 : 2));
            this.o = null;
        }
        else {
            this.b((byte)(b ? 1 : 2));
        }
    }
    
    @Override
    public void a(final byte b) throws p {
        this.b(b);
    }
    
    @Override
    public void a(final short n) throws p {
        this.b(this.c(n));
    }
    
    @Override
    public void a(final int n) throws p {
        this.b(this.c(n));
    }
    
    @Override
    public void a(final long n) throws p {
        this.b(this.c(n));
    }
    
    @Override
    public void a(final double value) throws p {
        final byte[] array = { 0, 0, 0, 0, 0, 0, 0, 0 };
        this.a(Double.doubleToLongBits(value), array, 0);
        this.g.b(array);
    }

    @Override//安装时间
    public void a(final String s) throws p {
        try {
            final byte[] bytes = s.getBytes("UTF-8");
            this.a(bytes, 0, bytes.length);
        }
        catch (UnsupportedEncodingException ex) {
            throw new p("UTF-8 not supported!");
        }
    }
    
    @Override
    public void a(final ByteBuffer byteBuffer) throws p {
        this.a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }
    
    private void a(final byte[] array, final int n, final int n2) throws p {
        this.b(n2);
        this.g.b(array, n, n2);
    }
    
    @Override
    public void a() throws p {
    }
    
    @Override
    public void e() throws p {
    }
    
    @Override
    public void f() throws p {
    }
    
    @Override
    public void g() throws p {
    }
    
    @Override
    public void c() throws p {
    }
    
    protected void a(final byte b, final int n) throws p {
        if (n <= 14) {
            this.d(n << 4 | this.e(b));
        }
        else {
            this.d(0xF0 | this.e(b));
            this.b(n);
        }
    }
    
    private void b(int n) throws p {
        int n2 = 0;
        while ((n & 0xFFFFFF80) != 0x0) {
            this.a[n2++] = (byte)((n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.a[n2++] = (byte)n;
        this.g.b(this.a, 0, n2);
    }
    
    private void b(long n) throws p {
        int n2 = 0;
        while ((n & 0xFFFFFFFFFFFFFF80L) != 0x0L) {
            this.b[n2++] = (byte)((n & 0x7FL) | 0x80L);
            n >>>= 7;
        }
        this.b[n2++] = (byte)n;
        this.g.b(this.b, 0, n2);
    }
    
    private long c(final long n) {
        return n << 1 ^ n >> 63;
    }
    
    private int c(final int n) {
        return n << 1 ^ n >> 31;
    }
    
    private void a(final long n, final byte[] array, final int n2) {
        array[n2 + 0] = (byte)(n & 0xFFL);
        array[n2 + 1] = (byte)(n >> 8 & 0xFFL);
        array[n2 + 2] = (byte)(n >> 16 & 0xFFL);
        array[n2 + 3] = (byte)(n >> 24 & 0xFFL);
        array[n2 + 4] = (byte)(n >> 32 & 0xFFL);
        array[n2 + 5] = (byte)(n >> 40 & 0xFFL);
        array[n2 + 6] = (byte)(n >> 48 & 0xFFL);
        array[n2 + 7] = (byte)(n >> 56 & 0xFFL);
    }
    
    private void b(final byte b) throws p {
        this.r[0] = b;
        this.g.b(this.r);
    }
    
    private void d(final int n) throws p {
        this.b((byte)n);
    }
    
    @Override
    public ag h() throws p {
        final byte u = this.u();
        if (u != -126) {
            throw new aj("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(u));
        }
        final byte u2 = this.u();
        final byte i = (byte)(u2 & 0x1F);
        if (i != 1) {
            throw new aj("Expected version 1 but got " + i);
        }
        return new ag(this.z(), (byte)(u2 >> 5 & 0x3), this.E());
    }
    
    @Override
    public an j() throws p {
        this.m.a(this.n);
        this.n = 0;
        return ac.d;
    }
    
    @Override
    public void k() throws p {
        this.n = this.m.a();
    }
    
    @Override
    public ad l() throws p {
        final byte u = this.u();
        if (u == 0) {
            return ac.e;
        }
        final short n = (short)((u & 0xF0) >> 4);
        short v;
        if (n == 0) {
            v = this.v();
        }
        else {
            v = (short)(this.n + n);
        }
        final ad ad = new ad("", this.d((byte)(u & 0xF)), v);
        if (this.c(u)) {
            this.p = (((byte)(u & 0xF) == 1) ? Boolean.TRUE : Boolean.FALSE);
        }
        this.n = ad.c;
        return ad;
    }
    
    @Override
    public af n() throws p {
        final int e = this.E();
        final byte b = (byte)((e == 0) ? 0 : this.u());
        return new af(this.d((byte)(b >> 4)), this.d((byte)(b & 0xF)), e);
    }
    
    @Override
    public ae p() throws p {
        final byte u = this.u();
        int e = u >> 4 & 0xF;
        if (e == 15) {
            e = this.E();
        }
        return new ae(this.d(u), e);
    }
    
    @Override
    public am r() throws p {
        return new am(this.p());
    }
    
    @Override
    public boolean t() throws p {
        if (this.p != null) {
            final boolean booleanValue = this.p;
            this.p = null;
            return booleanValue;
        }
        return this.u() == 1;
    }
    
    @Override
    public byte u() throws p {
        byte b;
        if (this.g.h() > 0) {
            b = this.g.f()[this.g.g()];
            this.g.a(1);
        }
        else {
            this.g.d(this.c, 0, 1);
            b = this.c[0];
        }
        return b;
    }
    
    @Override
    public short v() throws p {
        return (short)this.g(this.E());
    }
    
    @Override
    public int w() throws p {
        return this.g(this.E());
    }
    
    @Override
    public long x() throws p {
        return this.d(this.F());
    }
    
    @Override
    public double y() throws p {
        final byte[] array = new byte[8];
        this.g.d(array, 0, 8);
        return Double.longBitsToDouble(this.a(array));
    }
    
    @Override
    public String z() throws p {
        final int e = this.E();
        this.f(e);
        if (e == 0) {
            return "";
        }
        try {
            if (this.g.h() >= e) {
                final String s = new String(this.g.f(), this.g.g(), e, "UTF-8");
                this.g.a(e);
                return s;
            }
            return new String(this.e(e), "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            throw new p("UTF-8 not supported!");
        }
    }
    
    @Override
    public ByteBuffer A() throws p {
        final int e = this.E();
        this.f(e);
        if (e == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        final byte[] array = new byte[e];
        this.g.d(array, 0, e);
        return ByteBuffer.wrap(array);
    }
    
    private byte[] e(final int n) throws p {
        if (n == 0) {
            return new byte[0];
        }
        final byte[] array = new byte[n];
        this.g.d(array, 0, n);
        return array;
    }
    
    private void f(final int n) throws aj {
        if (n < 0) {
            throw new aj("Negative length: " + n);
        }
        if (this.q != -1L && n > this.q) {
            throw new aj("Length exceeded max allowed: " + n);
        }
    }
    
    @Override
    public void i() throws p {
    }
    
    @Override
    public void m() throws p {
    }
    
    @Override
    public void o() throws p {
    }
    
    @Override
    public void q() throws p {
    }
    
    @Override
    public void s() throws p {
    }
    
    private int E() throws p {
        int n = 0;
        int n2 = 0;
        if (this.g.h() >= 5) {
            final byte[] f = this.g.f();
            final int g = this.g.g();
            int n3 = 0;
            while (true) {
                final byte b = f[g + n3];
                n |= (b & 0x7F) << n2;
                if ((b & 0x80) != 0x80) {
                    break;
                }
                n2 += 7;
                ++n3;
            }
            this.g.a(n3 + 1);
        }
        else {
            while (true) {
                final byte u = this.u();
                n |= (u & 0x7F) << n2;
                if ((u & 0x80) != 0x80) {
                    break;
                }
                n2 += 7;
            }
        }
        return n;
    }
    
    private long F() throws p {
        int n = 0;
        long n2 = 0L;
        if (this.g.h() >= 10) {
            final byte[] f = this.g.f();
            final int g = this.g.g();
            int n3 = 0;
            while (true) {
                final byte b = f[g + n3];
                n2 |= (long)(b & 0x7F) << n;
                if ((b & 0x80) != 0x80) {
                    break;
                }
                n += 7;
                ++n3;
            }
            this.g.a(n3 + 1);
        }
        else {
            while (true) {
                final byte u = this.u();
                n2 |= (long)(u & 0x7F) << n;
                if ((u & 0x80) != 0x80) {
                    break;
                }
                n += 7;
            }
        }
        return n2;
    }
    
    private int g(final int n) {
        return n >>> 1 ^ -(n & 0x1);
    }
    
    private long d(final long n) {
        return n >>> 1 ^ -(n & 0x1L);
    }
    
    private long a(final byte[] array) {
        return ((long)array[7] & 0xFFL) << 56 | ((long)array[6] & 0xFFL) << 48 | ((long)array[5] & 0xFFL) << 40 | ((long)array[4] & 0xFFL) << 32 | ((long)array[3] & 0xFFL) << 24 | ((long)array[2] & 0xFFL) << 16 | ((long)array[1] & 0xFFL) << 8 | ((long)array[0] & 0xFFL);
    }
    
    private boolean c(final byte b) {
        final int n = b & 0xF;
        return n == 1 || n == 2;
    }
    
    private byte d(final byte b) throws aj {
        switch ((byte)(b & 0xF)) {
            case 0: {
                return 0;
            }
            case 1:
            case 2: {
                return 2;
            }
            case 3: {
                return 3;
            }
            case 4: {
                return 6;
            }
            case 5: {
                return 8;
            }
            case 6: {
                return 10;
            }
            case 7: {
                return 4;
            }
            case 8: {
                return 11;
            }
            case 9: {
                return 15;
            }
            case 10: {
                return 14;
            }
            case 11: {
                return 13;
            }
            case 12: {
                return 12;
            }
            default: {
                throw new aj("don't know what type: " + (byte)(b & 0xF));
            }
        }
    }
    
    private byte e(final byte b) {
        return ac.f[b];
    }
    
    static {
        d = new an("");
        e = new ad("", (byte)0, (short)0);
        (f = new byte[16])[0] = 0;
        ac.f[2] = 1;
        ac.f[3] = 3;
        ac.f[6] = 4;
        ac.f[8] = 5;
        ac.f[10] = 6;
        ac.f[4] = 7;
        ac.f[11] = 8;
        ac.f[15] = 9;
        ac.f[14] = 10;
        ac.f[13] = 11;
        ac.f[12] = 12;
    }
    
    public static class a implements ak
    {
        private final long a;
        
        public a() {
            this.a = -1L;
        }
        
        public a(final int n) {
            this.a = n;
        }
        
        @Override
        public ai a(final aw aw) {
            return new ac(aw, this.a);
        }
    }
    
    private static class b
    {
        public static final byte a = 1;
        public static final byte b = 2;
        public static final byte c = 3;
        public static final byte d = 4;
        public static final byte e = 5;
        public static final byte f = 6;
        public static final byte g = 7;
        public static final byte h = 8;
        public static final byte i = 9;
        public static final byte j = 10;
        public static final byte k = 11;
        public static final byte l = 12;
    }
}
