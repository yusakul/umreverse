package com.umeng.commonsdk.proguard;

import java.io.*;
import java.nio.*;

public class ab extends ai
{
    private static final an h;
    protected static final int a = -65536;
    protected static final int b = -2147418112;
    protected boolean c;
    protected boolean d;
    protected int e;
    protected boolean f;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;
    
    public ab(final aw aw) {
        this(aw, false, true);
    }
    
    public ab(final aw aw, final boolean c, final boolean d) {
        super(aw);
        this.c = false;
        this.d = true;
        this.f = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.c = c;
        this.d = d;
    }
    
    @Override
    public void a(final ag ag) throws p {
        if (this.d) {
            this.a(0x80010000 | ag.b);
            this.a(ag.a);
            this.a(ag.c);
        }
        else {
            this.a(ag.a);
            this.a(ag.b);
            this.a(ag.c);
        }
    }
    
    @Override
    public void a() {
    }
    
    @Override
    public void a(final an an) {
    }
    
    @Override
    public void b() {
    }
    
    @Override
    public void a(final ad ad) throws p {
        this.a(ad.b);
        this.a(ad.c);
    }
    
    @Override
    public void c() {
    }
    
    @Override
    public void d() throws p {
        this.a((byte)0);
    }
    
    @Override
    public void a(final af af) throws p {
        this.a(af.a);
        this.a(af.b);
        this.a(af.c);
    }
    
    @Override
    public void e() {
    }
    
    @Override
    public void a(final ae ae) throws p {
        this.a(ae.a);
        this.a(ae.b);
    }
    
    @Override
    public void f() {
    }
    
    @Override
    public void a(final am am) throws p {
        this.a(am.a);
        this.a(am.b);
    }
    
    @Override
    public void g() {
    }
    
    @Override
    public void a(final boolean b) throws p {
        this.a((byte)(b ? 1 : 0));
    }
    
    @Override
    public void a(final byte b) throws p {
        this.i[0] = b;
        this.g.b(this.i, 0, 1);
    }
    
    @Override
    public void a(final short n) throws p {
        this.j[0] = (byte)(0xFF & n >> 8);
        this.j[1] = (byte)(0xFF & n);
        this.g.b(this.j, 0, 2);
    }
    
    @Override
    public void a(final int n) throws p {
        this.k[0] = (byte)(0xFF & n >> 24);
        this.k[1] = (byte)(0xFF & n >> 16);
        this.k[2] = (byte)(0xFF & n >> 8);
        this.k[3] = (byte)(0xFF & n);
        this.g.b(this.k, 0, 4);
    }
    
    @Override
    public void a(final long n) throws p {
        this.l[0] = (byte)(0xFFL & n >> 56);
        this.l[1] = (byte)(0xFFL & n >> 48);
        this.l[2] = (byte)(0xFFL & n >> 40);
        this.l[3] = (byte)(0xFFL & n >> 32);
        this.l[4] = (byte)(0xFFL & n >> 24);
        this.l[5] = (byte)(0xFFL & n >> 16);
        this.l[6] = (byte)(0xFFL & n >> 8);
        this.l[7] = (byte)(0xFFL & n);
        this.g.b(this.l, 0, 8);
    }
    
    @Override
    public void a(final double value) throws p {
        this.a(Double.doubleToLongBits(value));
    }
    
    @Override
    public void a(final String s) throws p {
        try {
            final byte[] bytes = s.getBytes("UTF-8");
            this.a(bytes.length);
            this.g.b(bytes, 0, bytes.length);
        }
        catch (UnsupportedEncodingException ex) {
            throw new p("JVM DOES NOT SUPPORT UTF-8");
        }
    }
    
    @Override
    public void a(final ByteBuffer byteBuffer) throws p {
        final int n = byteBuffer.limit() - byteBuffer.position();
        this.a(n);
        this.g.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), n);
    }
    
    @Override
    public ag h() throws p {
        final int w = this.w();
        if (w < 0) {
            if ((w & 0xFFFF0000) != 0x80010000) {
                throw new aj(4, "Bad version in readMessageBegin");
            }
            return new ag(this.z(), (byte)(w & 0xFF), this.w());
        }
        else {
            if (this.c) {
                throw new aj(4, "Missing version in readMessageBegin, old client?");
            }
            return new ag(this.b(w), this.u(), this.w());
        }
    }
    
    @Override
    public void i() {
    }
    
    @Override
    public an j() {
        return ab.h;
    }
    
    @Override
    public void k() {
    }
    
    @Override
    public ad l() throws p {
        final byte u = this.u();
        return new ad("", u, (short)((u == 0) ? 0 : this.v()));
    }
    
    @Override
    public void m() {
    }
    
    @Override
    public af n() throws p {
        return new af(this.u(), this.u(), this.w());
    }
    
    @Override
    public void o() {
    }
    
    @Override
    public ae p() throws p {
        return new ae(this.u(), this.w());
    }
    
    @Override
    public void q() {
    }
    
    @Override
    public am r() throws p {
        return new am(this.u(), this.w());
    }
    
    @Override
    public void s() {
    }
    
    @Override
    public boolean t() throws p {
        return this.u() == 1;
    }
    
    @Override
    public byte u() throws p {
        if (this.g.h() >= 1) {
            final byte b = this.g.f()[this.g.g()];
            this.g.a(1);
            return b;
        }
        this.a(this.m, 0, 1);
        return this.m[0];
    }
    
    @Override
    public short v() throws p {
        byte[] array = this.n;
        int g = 0;
        if (this.g.h() >= 2) {
            array = this.g.f();
            g = this.g.g();
            this.g.a(2);
        }
        else {
            this.a(this.n, 0, 2);
        }
        return (short)((array[g] & 0xFF) << 8 | (array[g + 1] & 0xFF));
    }
    
    @Override
    public int w() throws p {
        byte[] array = this.o;
        int g = 0;
        if (this.g.h() >= 4) {
            array = this.g.f();
            g = this.g.g();
            this.g.a(4);
        }
        else {
            this.a(this.o, 0, 4);
        }
        return (array[g] & 0xFF) << 24 | (array[g + 1] & 0xFF) << 16 | (array[g + 2] & 0xFF) << 8 | (array[g + 3] & 0xFF);
    }
    
    @Override
    public long x() throws p {
        byte[] array = this.p;
        int g = 0;
        if (this.g.h() >= 8) {
            array = this.g.f();
            g = this.g.g();
            this.g.a(8);
        }
        else {
            this.a(this.p, 0, 8);
        }
        return (long)(array[g] & 0xFF) << 56 | (long)(array[g + 1] & 0xFF) << 48 | (long)(array[g + 2] & 0xFF) << 40 | (long)(array[g + 3] & 0xFF) << 32 | (long)(array[g + 4] & 0xFF) << 24 | (long)(array[g + 5] & 0xFF) << 16 | (long)(array[g + 6] & 0xFF) << 8 | (long)(array[g + 7] & 0xFF);
    }
    
    @Override
    public double y() throws p {
        return Double.longBitsToDouble(this.x());
    }
    
    @Override
    public String z() throws p {
        final int w = this.w();
        if (this.g.h() >= w) {
            try {
                final String s = new String(this.g.f(), this.g.g(), w, "UTF-8");
                this.g.a(w);
                return s;
            }
            catch (UnsupportedEncodingException ex) {
                throw new p("JVM DOES NOT SUPPORT UTF-8");
            }
        }
        return this.b(w);
    }
    
    public String b(final int n) throws p {
        try {
            this.d(n);
            final byte[] bytes = new byte[n];
            this.g.d(bytes, 0, n);
            return new String(bytes, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            throw new p("JVM DOES NOT SUPPORT UTF-8");
        }
    }
    
    @Override
    public ByteBuffer A() throws p {
        final int w = this.w();
        this.d(w);
        if (this.g.h() >= w) {
            final ByteBuffer wrap = ByteBuffer.wrap(this.g.f(), this.g.g(), w);
            this.g.a(w);
            return wrap;
        }
        final byte[] array = new byte[w];
        this.g.d(array, 0, w);
        return ByteBuffer.wrap(array);
    }
    
    private int a(final byte[] array, final int n, final int n2) throws p {
        this.d(n2);
        return this.g.d(array, n, n2);
    }
    
    public void c(final int e) {
        this.e = e;
        this.f = true;
    }
    
    protected void d(final int n) throws p {
        if (n < 0) {
            throw new aj("Negative length: " + n);
        }
        if (this.f) {
            this.e -= n;
            if (this.e < 0) {
                throw new aj("Message length exceeded: " + n);
            }
        }
    }
    
    static {
        h = new an();
    }
    
    public static class a implements ak
    {
        protected boolean a;
        protected boolean b;
        protected int c;
        
        public a() {
            this(false, true);
        }
        
        public a(final boolean b, final boolean b2) {
            this(b, b2, 0);
        }
        
        public a(final boolean a, final boolean b, final int c) {
            this.a = false;
            this.b = true;
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        @Override
        public ai a(final aw aw) {
            final ab ab = new ab(aw, this.a, this.b);
            if (this.c != 0) {
                ab.c(this.c);
            }
            return ab;
        }
    }
}
