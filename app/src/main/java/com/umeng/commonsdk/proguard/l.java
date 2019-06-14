package com.umeng.commonsdk.proguard;

import java.io.*;

public class l extends ByteArrayOutputStream
{
    public l(final int size) {
        super(size);
    }
    
    public l() {
    }
    
    public byte[] a() {
        return this.buf;
    }
    
    public int b() {
        return this.count;
    }
}
