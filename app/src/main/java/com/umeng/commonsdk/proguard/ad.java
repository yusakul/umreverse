package com.umeng.commonsdk.proguard;

public class ad
{
    public final String a;
    public final byte b;
    public final short c;
    
    public ad() {
        this("", (byte)0, (short)0);
    }
    
    public ad(final String a, final byte b, final short c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public String toString() {
        return "<TField name:'" + this.a + "' type:" + this.b + " field-id:" + this.c + ">";
    }
    
    public boolean a(final ad ad) {
        return this.b == ad.b && this.c == ad.c;
    }
}
