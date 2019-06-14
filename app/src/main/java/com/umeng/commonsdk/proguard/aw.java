package com.umeng.commonsdk.proguard;

public abstract class aw
{
    public abstract boolean a();
    
    public boolean i() {
        return this.a();
    }
    
    public abstract void b() throws ax;
    
    public abstract void c();
    
    public abstract int a(final byte[] p0, final int p1, final int p2) throws ax;
    
    public int d(final byte[] array, final int n, final int i) throws ax {
        int j;
        int a;
        for (j = 0; j < i; j += a) {
            a = this.a(array, n + j, i - j);
            if (a <= 0) {
                throw new ax("Cannot read. Remote side has closed. Tried to read " + i + " bytes, but only got " + j + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
            }
        }
        return j;
    }
    
    public void b(final byte[] array) throws ax {
        this.b(array, 0, array.length);
    }
    
    public abstract void b(final byte[] p0, final int p1, final int p2) throws ax;
    
    public void d() throws ax {
    }
    
    public byte[] f() {
        return null;
    }
    
    public int g() {
        return 0;
    }
    
    public int h() {
        return -1;
    }
    
    public void a(final int n) {
    }
}
