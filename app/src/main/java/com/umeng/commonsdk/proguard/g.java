package com.umeng.commonsdk.proguard;

public class g
{
    public static final void a(final int n, final byte[] array) {
        a(n, array, 0);
    }
    
    public static final void a(final int n, final byte[] array, final int n2) {
        array[n2] = (byte)(0xFF & n >> 24);
        array[n2 + 1] = (byte)(0xFF & n >> 16);
        array[n2 + 2] = (byte)(0xFF & n >> 8);
        array[n2 + 3] = (byte)(0xFF & n);
    }
    
    public static final int a(final byte[] array) {
        return a(array, 0);
    }
    
    public static final int a(final byte[] array, final int n) {
        return (array[n] & 0xFF) << 24 | (array[n + 1] & 0xFF) << 16 | (array[n + 2] & 0xFF) << 8 | (array[n + 3] & 0xFF);
    }
    
    public static final boolean a(final byte b, final int n) {
        return a((int)b, n);
    }
    
    public static final boolean a(final short n, final int n2) {
        return a((int)n, n2);
    }
    
    public static final boolean a(final int n, final int n2) {
        return (n & 1 << n2) != 0x0;
    }
    
    public static final boolean a(final long n, final int n2) {
        return (n & 1L << n2) != 0x0L;
    }
    
    public static final byte b(final byte b, final int n) {
        return (byte)b((int)b, n);
    }
    
    public static final short b(final short n, final int n2) {
        return (short)b((int)n, n2);
    }
    
    public static final int b(final int n, final int n2) {
        return n & ~(1 << n2);
    }
    
    public static final long b(final long n, final int n2) {
        return n & ~(1L << n2);
    }
    
    public static final byte a(final byte b, final int n, final boolean b2) {
        return (byte)a((int)b, n, b2);
    }
    
    public static final short a(final short n, final int n2, final boolean b) {
        return (short)a((int)n, n2, b);
    }
    
    public static final int a(final int n, final int n2, final boolean b) {
        if (b) {
            return n | 1 << n2;
        }
        return b(n, n2);
    }
    
    public static final long a(final long n, final int n2, final boolean b) {
        if (b) {
            return n | 1L << n2;
        }
        return b(n, n2);
    }
}
