package com.umeng.commonsdk.proguard;

import java.io.*;

public interface j<T extends j<?, ?>, F extends q> extends Serializable
{
    void read(final ai p0) throws p;
    
    void write(final ai p0) throws p;
    
    F fieldForId(final int p0);
    
    j<T, F> deepCopy();
    
    void clear();
}
