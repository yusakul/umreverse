package com.umeng.commonsdk.proguard;

import java.io.*;

public class w implements Serializable
{
    public final byte b;
    private final boolean a;
    private final String c;
    private final boolean d;
    
    public w(final byte b, final boolean d) {
        this.b = b;
        this.a = false;
        this.c = null;
        this.d = d;
    }
    
    public w(final byte b) {
        this(b, false);
    }
    
    public w(final byte b, final String c) {
        this.b = b;
        this.a = true;
        this.c = c;
        this.d = false;
    }
    
    public boolean a() {
        return this.a;
    }
    
    public String b() {
        return this.c;
    }
    
    public boolean c() {
        return this.b == 12;
    }
    
    public boolean d() {
        return this.b == 15 || this.b == 13 || this.b == 14;
    }
    
    public boolean e() {
        return this.d;
    }
}
