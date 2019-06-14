package com.umeng.commonsdk.proguard;

public final class av extends aw
{
    private byte[] a;
    private int b;
    private int c;
    
    public av() {
    }
    
    public av(final byte[] array) {
        this.a(array);
    }
    
    public av(final byte[] array, final int n, final int n2) {
        this.c(array, n, n2);
    }
    
    public void a(final byte[] array) {
        this.c(array, 0, array.length);
    }
    
    public void c(final byte[] a, final int b, final int n) {
        this.a = a;
        this.b = b;
        this.c = b + n;
    }
    
    public void e() {
        this.a = null;
    }
    
    @Override
    public void c() {
    }
    
    @Override
    public boolean a() {
        return true;
    }
    
    @Override
    public void b() throws ax {
    }
    
    @Override
    public int a(final byte[] array, final int n, final int n2) throws ax {
        final int h = this.h();
        final int n3 = (n2 > h) ? h : n2;
        if (n3 > 0) {
            System.arraycopy(this.a, this.b, array, n, n3);
            this.a(n3);
        }
        return n3;
    }
    
    @Override
    public void b(final byte[] array, final int n, final int n2) throws ax {
        throw new UnsupportedOperationException("No writing allowed!");
    }
    
    @Override
    public byte[] f() {
        return this.a;
    }
    
    @Override
    public int g() {
        return this.b;
    }
    
    @Override
    public int h() {
        return this.c - this.b;
    }
    
    @Override
    public void a(final int n) {
        this.b += n;
    }
}
