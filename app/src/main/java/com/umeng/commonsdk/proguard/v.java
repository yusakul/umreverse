package com.umeng.commonsdk.proguard;

import java.io.*;
import java.util.*;

public class v implements Serializable
{
    public final String a;
    public final byte b;
    public final w c;
    private static Map<Class<? extends j>, Map<? extends q, v>> d;
    
    public v(final String a, final byte b, final w c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public static void a(final Class<? extends j> clazz, final Map<? extends q, v> map) {
        v.d.put(clazz, map);
    }
    
    public static Map<? extends q, v> a(final Class<? extends j> clazz) {
        if (!v.d.containsKey(clazz)) {
            try {
                clazz.newInstance();
            }
            catch (InstantiationException ex) {
                throw new RuntimeException("InstantiationException for TBase class: " + clazz.getName() + ", message: " + ex.getMessage());
            }
            catch (IllegalAccessException ex2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + clazz.getName() + ", message: " + ex2.getMessage());
            }
        }
        return v.d.get(clazz);
    }
    
    static {
        v.d = new HashMap<Class<? extends j>, Map<? extends q, v>>();
    }
}
