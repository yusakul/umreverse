package com.umeng.commonsdk.proguard;

public class al
{
    private static int a;
    
    public static void a(final int a) {
        al.a = a;
    }
    
    public static void a(final ai ai, final byte b) throws p {
        a(ai, b, al.a);
    }
    
    public static void a(final ai ai, final byte b, final int n) throws p {
        if (n <= 0) {
            throw new p("Maximum skip depth exceeded");
        }
        switch (b) {
            case 2: {
                ai.t();
                break;
            }
            case 3: {
                ai.u();
                break;
            }
            case 6: {
                ai.v();
                break;
            }
            case 8: {
                ai.w();
                break;
            }
            case 10: {
                ai.x();
                break;
            }
            case 4: {
                ai.y();
                break;
            }
            case 11: {
                ai.A();
                break;
            }
            case 12: {
                ai.j();
                while (true) {
                    final ad l = ai.l();
                    if (l.b == 0) {
                        break;
                    }
                    a(ai, l.b, n - 1);
                    ai.m();
                }
                ai.k();
                break;
            }
            case 13: {
                final af n2 = ai.n();
                for (int i = 0; i < n2.c; ++i) {
                    a(ai, n2.a, n - 1);
                    a(ai, n2.b, n - 1);
                }
                ai.o();
                break;
            }
            case 14: {
                final am r = ai.r();
                for (int j = 0; j < r.b; ++j) {
                    a(ai, r.a, n - 1);
                }
                ai.s();
                break;
            }
            case 15: {
                final ae p3 = ai.p();
                for (int k = 0; k < p3.b; ++k) {
                    a(ai, p3.a, n - 1);
                }
                ai.q();
                break;
            }
        }
    }
    
    public static ak a(final byte[] array, final ak ak) {
        if (array[0] > 16) {
            return new ac.a();
        }
        if (array.length > 1 && (array[1] & 0x80) != 0x0) {
            return new ac.a();
        }
        return ak;
    }
    
    static {
        al.a = Integer.MAX_VALUE;
    }
}
