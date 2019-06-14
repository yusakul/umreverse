package com.umeng.commonsdk.proguard;

import java.lang.reflect.*;

public class o
{
    public static n a(final Class<? extends n> clazz, final int i) {
        try {
            return (n)clazz.getMethod("findByValue", Integer.TYPE).invoke(null, i);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
        catch (IllegalAccessException ex2) {
            return null;
        }
        catch (InvocationTargetException ex3) {
            return null;
        }
    }
}
