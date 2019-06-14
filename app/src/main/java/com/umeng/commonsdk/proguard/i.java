package com.umeng.commonsdk.proguard;

public class i extends p
{
    private static final an j;
    private static final ad k;
    private static final ad l;
    private static final long m = 1L;
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    protected int i;
    
    public i() {
        this.i = 0;
    }
    
    public i(final int i) {
        this.i = 0;
        this.i = i;
    }
    
    public i(final int i, final String s) {
        super(s);
        this.i = 0;
        this.i = i;
    }
    
    public i(final String s) {
        super(s);
        this.i = 0;
    }
    
    public int a() {
        return this.i;
    }
    
    public static i a(final ai ai) throws p {
        ai.j();
        String z = null;
        int w = 0;
        while (true) {
            final ad l = ai.l();
            if (l.b == 0) {
                break;
            }
            switch (l.c) {
                case 1: {
                    if (l.b == 11) {
                        z = ai.z();
                        break;
                    }
                    al.a(ai, l.b);
                    break;
                }
                case 2: {
                    if (l.b == 8) {
                        w = ai.w();
                        break;
                    }
                    al.a(ai, l.b);
                    break;
                }
                default: {
                    al.a(ai, l.b);
                    break;
                }
            }
            ai.m();
        }
        ai.k();
        return new i(w, z);
    }
    
    public void b(final ai ai) throws p {
        ai.a(com.umeng.commonsdk.proguard.i.j);
        if (this.getMessage() != null) {
            ai.a(com.umeng.commonsdk.proguard.i.k);
            ai.a(this.getMessage());
            ai.c();
        }
        ai.a(com.umeng.commonsdk.proguard.i.l);
        ai.a(this.i);
        ai.c();
        ai.d();
        ai.b();
    }
    
    static {
        j = new an("TApplicationException");
        k = new ad("message", (byte)11, (short)1);
        l = new ad("type", (byte)8, (short)2);
    }
}
