package com.umeng.commonsdk.proguard;

import java.io.*;
import java.nio.*;

public class m
{
    private final ai a;
    private final av b;
    
    public m() {
        this(new ac.a());
    }
    
    public m(final ak ak) {
        this.b = new av();
        this.a = ak.a(this.b);
    }
    
    public void a(final j j, final byte[] array) throws p {
        try {
            this.b.a(array);
            j.read(this.a);
        }
        finally {
            this.b.e();
            this.a.B();
        }
    }
    
    public void a(final j j, final String s, final String s2) throws p {
        try {
            this.a(j, s.getBytes(s2));
        }
        catch (UnsupportedEncodingException ex) {
            throw new p("JVM DOES NOT SUPPORT ENCODING: " + s2);
        }
        finally {
            this.a.B();
        }
    }
    
    public void a(final j j, final byte[] array, final q q, final q... array2) throws p {
        try {
            if (this.j(array, q, array2) != null) {
                j.read(this.a);
            }
        }
        catch (Exception ex) {
            throw new p(ex);
        }
        finally {
            this.b.e();
            this.a.B();
        }
    }
    
    public Boolean a(final byte[] array, final q q, final q... array2) throws p {
        return (Boolean)this.a((byte)2, array, q, array2);
    }
    
    public Byte b(final byte[] array, final q q, final q... array2) throws p {
        return (Byte)this.a((byte)3, array, q, array2);
    }
    
    public Double c(final byte[] array, final q q, final q... array2) throws p {
        return (Double)this.a((byte)4, array, q, array2);
    }
    
    public Short d(final byte[] array, final q q, final q... array2) throws p {
        return (Short)this.a((byte)6, array, q, array2);
    }
    
    public Integer e(final byte[] array, final q q, final q... array2) throws p {
        return (Integer)this.a((byte)8, array, q, array2);
    }
    
    public Long f(final byte[] array, final q q, final q... array2) throws p {
        return (Long)this.a((byte)10, array, q, array2);
    }
    
    public String g(final byte[] array, final q q, final q... array2) throws p {
        return (String)this.a((byte)11, array, q, array2);
    }
    
    public ByteBuffer h(final byte[] array, final q q, final q... array2) throws p {
        return (ByteBuffer)this.a((byte)100, array, q, array2);
    }
    
    public Short i(final byte[] array, final q q, final q... array2) throws p {
        try {
            if (this.j(array, q, array2) != null) {
                this.a.j();
                return this.a.l().c;
            }
            return null;
        }
        catch (Exception ex) {
            throw new p(ex);
        }
        finally {
            this.b.e();
            this.a.B();
        }
    }
    
    private Object a(final byte b, final byte[] array, final q q, final q... array2) throws p {
        try {
            final ad j = this.j(array, q, array2);
            if (j != null) {
                switch (b) {
                    case 2: {
                        if (j.b == 2) {
                            return this.a.t();
                        }
                        break;
                    }
                    case 3: {
                        if (j.b == 3) {
                            return this.a.u();
                        }
                        break;
                    }
                    case 4: {
                        if (j.b == 4) {
                            return this.a.y();
                        }
                        break;
                    }
                    case 6: {
                        if (j.b == 6) {
                            return this.a.v();
                        }
                        break;
                    }
                    case 8: {
                        if (j.b == 8) {
                            return this.a.w();
                        }
                        break;
                    }
                    case 10: {
                        if (j.b == 10) {
                            return this.a.x();
                        }
                        break;
                    }
                    case 11: {
                        if (j.b == 11) {
                            return this.a.z();
                        }
                        break;
                    }
                    case 100: {
                        if (j.b == 11) {
                            return this.a.A();
                        }
                        break;
                    }
                }
            }
            return null;
        }
        catch (Exception ex) {
            throw new p(ex);
        }
        finally {
            this.b.e();
            this.a.B();
        }
    }
    
    private ad j(final byte[] array, final q q, final q... array2) throws p {
        this.b.a(array);
        final q[] array3 = new q[array2.length + 1];
        array3[0] = q;
        for (int i = 0; i < array2.length; ++i) {
            array3[i + 1] = array2[i];
        }
        int j = 0;
        ad l = null;
        this.a.j();
        while (j < array3.length) {
            l = this.a.l();
            if (l.b == 0 || l.c > array3[j].a()) {
                return null;
            }
            if (l.c != array3[j].a()) {
                al.a(this.a, l.b);
                this.a.m();
            }
            else {
                if (++j >= array3.length) {
                    continue;
                }
                this.a.j();
            }
        }
        return l;
    }
    
    public void a(final j j, final String s) throws p {
        this.a(j, s.getBytes());
    }
}
