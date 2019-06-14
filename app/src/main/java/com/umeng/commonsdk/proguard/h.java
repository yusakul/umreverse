package com.umeng.commonsdk.proguard;

public class h
{
    private short[] a;
    private int b;
    
    public h(final int n) {
        this.b = -1;
        this.a = new short[n];
    }
    
    public short a() {
        return this.a[this.b--];
    }
    
    public void a(final short n) {
        if (this.a.length == this.b + 1) {
            this.d();
        }
        this.a[++this.b] = n;
    }
    
    private void d() {
        final short[] a = new short[this.a.length * 2];
        System.arraycopy(this.a, 0, a, 0, this.a.length);
        this.a = a;
    }
    
    public short b() {
        return this.a[this.b];
    }
    
    public void c() {
        this.b = -1;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<ShortStack vector:[");
        for (int i = 0; i < this.a.length; ++i) {
            if (i != 0) {
                sb.append(" ");
            }
            if (i == this.b) {
                sb.append(">>");
            }
            sb.append(this.a[i]);
            if (i == this.b) {
                sb.append("<<");
            }
        }
        sb.append("]>");
        return sb.toString();
    }
}
