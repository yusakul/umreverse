package com.umeng.commonsdk.proguard;

import android.content.*;
import android.text.*;
import com.umeng.commonsdk.statistics.common.*;
import java.lang.reflect.*;

public class a
{
    private static Object a;
    private static String b;
    
    public static void a(final Context context) {
        try {
            final Class[] parameterTypes = { Context.class };
            try {
                final Class<?> forName = Class.forName("com.wireless.security.securityenv.sdk.SecurityEnvSDK");
                if (forName != null) {
                    final Constructor<?> constructor = forName.getConstructor((Class<?>[])parameterTypes);
                    if (constructor != null) {
                        final Object instance = constructor.newInstance(context);
                        if (instance != null) {
                            final Method declaredMethod = forName.getDeclaredMethod("initSync", (Class<?>[])new Class[0]);
                            if (declaredMethod != null) {
                                declaredMethod.setAccessible(true);
                                declaredMethod.invoke(instance, new Object[0]);
                            }
                            final Method declaredMethod2 = forName.getDeclaredMethod("getToken", (Class<?>[])new Class[0]);
                            if (declaredMethod2 != null) {
                                declaredMethod2.setAccessible(true);
                                final String b = (String)declaredMethod2.invoke(instance, new Object[0]);
                                if (!TextUtils.isEmpty((CharSequence)b)) {
                                    synchronized (com.umeng.commonsdk.proguard.a.a) {
                                        com.umeng.commonsdk.proguard.a.b = b;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
        catch (Exception obj) {
            ULog.e("internal", "e_enum is " + obj);
        }
    }
    
    public static String b(final Context context) {
        synchronized (com.umeng.commonsdk.proguard.a.a) {
            return com.umeng.commonsdk.proguard.a.b;
        }
    }
    
    static {
        com.umeng.commonsdk.proguard.a.a = new Object();
    }
}
