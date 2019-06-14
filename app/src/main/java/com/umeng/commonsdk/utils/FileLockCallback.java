package com.umeng.commonsdk.utils;

import java.io.*;

public interface FileLockCallback
{
    boolean onFileLock(final String p0);
    
    boolean onFileLock(final File p0, final int p1);
    
    boolean onFileLock(final String p0, final Object p1);
}
