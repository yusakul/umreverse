package com.umeng.commonsdk.proguard;

public final class ag
{
    public final String a;
    public final byte b;
    public final int c;
    
    public ag() {
        this("", (byte)0, 0);
    }
    
    public ag(final String a, final byte b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public String toString() {
        return "<TMessage name:'" + this.a + "' type: " + this.b + " seqid:" + this.c + ">";
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof ag && this.a((ag)o);
    }
    
    public boolean a(final ag ag) {
        return this.a.equals(ag.a) && this.b == ag.b && this.c == ag.c;
    }
}
