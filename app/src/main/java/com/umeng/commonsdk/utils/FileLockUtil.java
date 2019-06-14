package com.umeng.commonsdk.utils;

import java.io.*;
import java.nio.channels.*;

public class FileLockUtil
{
    private final Object lockObject;
    
    public FileLockUtil() {
        this.lockObject = new Object();
    }
    
    public void doFileOperateion(final File file, final FileLockCallback fileLockCallback, final Object o) {
        if (!file.exists()) {
            return;
        }
        synchronized (this.lockObject) {
            final FileLock fileLock = getFileLock(file.getAbsolutePath());
            if (null != fileLock) {
                try {
                    fileLockCallback.onFileLock(file.getName(), o);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        fileLock.release();
                        fileLock.channel().close();
                    }
                    catch (IOException ex2) {
                        ex2.printStackTrace();
                    }
                }
                finally {
                    try {
                        fileLock.release();
                        fileLock.channel().close();
                    }
                    catch (IOException ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
        }
    }
    
    public void doFileOperateion(final File file, final FileLockCallback fileLockCallback, final int n) {
        if (!file.exists()) {
            return;
        }
        synchronized (this.lockObject) {
            final FileLock fileLock = getFileLock(file.getAbsolutePath());
            if (null != fileLock) {
                try {
                    fileLockCallback.onFileLock(file, n);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        fileLock.release();
                        fileLock.channel().close();
                    }
                    catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
                finally {
                    try {
                        fileLock.release();
                        fileLock.channel().close();
                    }
                    catch (Throwable t2) {
                        t2.printStackTrace();
                    }
                }
            }
        }
    }
    
    public void doFileOperateion(final File file, final FileLockCallback fileLockCallback) {
        if (!file.exists()) {
            return;
        }
        synchronized (this.lockObject) {
            final FileLock fileLock = getFileLock(file.getAbsolutePath());
            if (null != fileLock) {
                try {
                    fileLockCallback.onFileLock(file.getName());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        fileLock.release();
                        fileLock.channel().close();
                    }
                    catch (IOException ex2) {
                        ex2.printStackTrace();
                    }
                }
                finally {
                    try {
                        fileLock.release();
                        fileLock.channel().close();
                    }
                    catch (IOException ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
        }
    }
    
    public void doFileOperateion(final String pathname, final FileLockCallback fileLockCallback) {
        final File file = new File(pathname);
        if (!file.exists()) {
            return;
        }
        synchronized (this.lockObject) {
            final FileLock fileLock = getFileLock(pathname);
            if (null != fileLock) {
                try {
                    fileLockCallback.onFileLock(file.getName());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        fileLock.release();
                        fileLock.channel().close();
                    }
                    catch (IOException ex2) {
                        ex2.printStackTrace();
                    }
                }
                finally {
                    try {
                        fileLock.release();
                        fileLock.channel().close();
                    }
                    catch (IOException ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
        }
    }
    
    private static FileLock getFileLock(final String name) {
        FileChannel channel = null;
        try {
            channel = new RandomAccessFile(name, "rw").getChannel();
            return channel.lock();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
        if (channel != null) {
            try {
                channel.close();
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }
        return null;
    }
}
