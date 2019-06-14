package com.umeng.commonsdk.proguard;

import java.io.*;

public class au extends aw
{
    protected InputStream a = null;
    protected OutputStream b = null;
    
    protected au() {
    }
    
    public au(final InputStream a) {
        this.a = a;
    }
    
    public au(final OutputStream b) {
        this.b = b;
    }
    
    public au(final InputStream a, final OutputStream b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public boolean a() {
        return true;
    }
    
    @Override
    public void b() throws ax {
    }
    
    @Override
    public void c() {
        if (this.a != null) {
            try {
                this.a.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            this.a = null;
        }
        if (this.b != null) {
            try {
                this.b.close();
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
            this.b = null;
        }
    }
    
    @Override
    public int a(final byte[] b, final int off, final int len) throws ax {
        if (this.a == null) {
            throw new ax(1, "Cannot read from null inputStream");
        }
        int read;
        try {
            read = this.a.read(b, off, len);
        }
        catch (IOException ex) {
            throw new ax(0, ex);
        }
        if (read < 0) {
            throw new ax(4);
        }
        return read;
    }
    
    @Override
    public void b(final byte[] b, final int off, final int len) throws ax {
        if (this.b == null) {
            throw new ax(1, "Cannot write to null outputStream");
        }
        try {
            this.b.write(b, off, len);
        }
        catch (IOException ex) {
            throw new ax(0, ex);
        }
    }
    
    @Override
    public void d() throws ax {
        if (this.b == null) {
            throw new ax(1, "Cannot flush null outputStream");
        }
        try {
            this.b.flush();
        }
        catch (IOException ex) {
            throw new ax(0, ex);
        }
    }
}
