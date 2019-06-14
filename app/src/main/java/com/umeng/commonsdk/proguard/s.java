package com.umeng.commonsdk.proguard;

import java.io.*;

public class s
{
    private final ByteArrayOutputStream a;
    private final au b;
    private ai c;
    
    public s() {
        this(new ac.a());
    }
    
    public s(final ak ak) {
        this.a = new ByteArrayOutputStream();
        this.b = new au(this.a);
        this.c = ak.a(this.b);
    }
    
    public byte[] a(final j j) throws p {
        this.a.reset();
        j.write(this.c);
        return this.a.toByteArray();
    }
    
    public String a(final j j, final String s) throws p {
        try {
            return new String(this.a(j), s);
        }
        catch (UnsupportedEncodingException ex) {
            throw new p("JVM DOES NOT SUPPORT ENCODING: " + s);
        }
    }
    
    public String b(final j j) throws p {
        return new String(this.a(j));
    }
}
