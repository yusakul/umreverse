package com.umeng.commonsdk;

import java.lang.reflect.*;
import android.text.*;
import com.umeng.commonsdk.stateless.*;
import android.content.*;
import android.util.*;
import com.umeng.commonsdk.utils.*;
import com.umeng.commonsdk.debug.*;
import com.umeng.commonsdk.statistics.*;
import com.umeng.commonsdk.service.*;
import com.umeng.commonsdk.framework.*;
import com.umeng.commonsdk.statistics.common.*;

public class UMConfigure
{
    private static final String TAG = "UMConfigure";
    private static boolean debugLog;
    public static UMLog umDebugLog;
    private static final String KEY_METHOD_NAME_SETAPPKEY = "setAppkey";
    private static final String KEY_METHOD_NAME_SETCHANNEL = "setChannel";
    private static final String KEY_METHOD_NAME_PUSH_SETCHANNEL = "setMessageChannel";
    private static final String KEY_METHOD_NAME_PUSH_SET_SECRET = "setSecret";
    private static final String KEY_METHOD_NAME_SETDEBUGMODE = "setDebugMode";
    private static final String KEY_FILE_NAME_APPKEY = "APPKEY";
    private static final String KEY_FILE_NAME_LOG = "LOG";
    public static final int DEVICE_TYPE_PHONE = 1;
    public static final int DEVICE_TYPE_BOX = 2;
    private static boolean isFinish;
    private static Object lockObject;
    private static final String WRAPER_TYPE_NATIVE = "native";
    private static final String WRAPER_TYPE_COCOS2DX_X = "Cocos2d-x";
    private static final String WRAPER_TYPE_COCOS2DX_XLUA = "Cocos2d-x_lua";
    private static final String WRAPER_TYPE_UNITY = "Unity";
    private static final String WRAPER_TYPE_REACTNATIVE = "react-native";
    private static final String WRAPER_TYPE_PHONEGAP = "phonegap";
    private static final String WRAPER_TYPE_WEEX = "weex";
    private static final String WRAPER_TYPE_HYBRID = "hybrid";
    private static final String WRAPER_TYPE_FLUTTER = "flutter";
    
    private static Class<?> getClass(final String className) {
        Class<?> forName = null;
        try {
            forName = Class.forName(className);
        }
        catch (ClassNotFoundException ex) {}
        return forName;
    }
    
    private Object getInstanceObject(final Class<?> clazz) {
        Object instance = null;
        if (clazz != null) {
            try {
                instance = clazz.newInstance();
            }
            catch (InstantiationException ex) {}
            catch (IllegalAccessException ex2) {}
        }
        return instance;
    }
    
    private static Object getDecInstanceObject(final Class<?> clazz) {
        Object instance = null;
        if (clazz != null) {
            Constructor<?> declaredConstructor = null;
            try {
                declaredConstructor = clazz.getDeclaredConstructor((Class<?>[])new Class[0]);
            }
            catch (NoSuchMethodException ex) {}
            if (declaredConstructor != null) {
                declaredConstructor.setAccessible(true);
                try {
                    instance = declaredConstructor.newInstance(new Object[0]);
                }
                catch (IllegalArgumentException ex2) {}
                catch (InstantiationException ex3) {}
                catch (IllegalAccessException ex4) {}
                catch (InvocationTargetException ex5) {}
            }
        }
        return instance;
    }
    
    private static Method getDecMethod(final Class<?> clazz, final String name, final Class<?>[] parameterTypes) {
        Method declaredMethod = null;
        if (clazz != null) {
            try {
                declaredMethod = clazz.getDeclaredMethod(name, (Class[])parameterTypes);
            }
            catch (NoSuchMethodException ex) {}
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
            }
        }
        return declaredMethod;
    }
    
    private static void invoke(final Method method, final Object obj, final Object[] args) {
        if (method != null && obj != null) {
            try {
                method.invoke(obj, args);
            }
            catch (IllegalArgumentException ex) {}
            catch (IllegalAccessException ex2) {}
            catch (InvocationTargetException ex3) {}
        }
    }
    
    private static void invoke(final Method method, final Object[] args) {
        if (method != null) {
            try {
                method.invoke(null, args);
            }
            catch (IllegalArgumentException ex) {}
            catch (IllegalAccessException ex2) {}
            catch (InvocationTargetException ex3) {}
        }
    }
    
    private static void setFile(final Class<?> clazz, final String s, final String value) {
        if (clazz != null) {
            try {
                clazz.getField(s).set(s, value);
            }
            catch (Exception ex) {}
        }
    }
    
    private static void setFile(final Class<?> clazz, final String s, final boolean b) {
        if (clazz != null) {
            try {
                clazz.getField(s).set(s, b);
            }
            catch (Exception ex) {}
        }
    }
    
    private static void saveSDKComponent() {
        final StringBuffer sb = new StringBuffer();
        if (getClass("com.umeng.analytics.vismode.V") != null) {
            sb.append("v");
        }
        else if (getClass("com.umeng.analytics.MobclickAgent") != null) {
            sb.append("a");
        }
        if (getClass("com.umeng.visual.UMVisualAgent") != null) {
            sb.append("x");
        }
        if (getClass("com.umeng.message.PushAgent") != null) {
            sb.append("p");
        }
        if (getClass("com.umeng.socialize.UMShareAPI") != null) {
            sb.append("s");
        }
        if (getClass("com.umeng.error.UMError") != null) {
            sb.append("e");
        }
        sb.append("i");
        if (SdkVersion.SDK_TYPE != 1 && getClass("com.umeng.commonsdk.internal.UMOplus") != null) {
            sb.append("o");
        }
        if (!TextUtils.isEmpty((CharSequence)sb)) {
            com.umeng.commonsdk.statistics.b.a = sb.toString();
            UMSLEnvelopeBuild.module = sb.toString();
        }
    }
    
    public static boolean getInitStatus() {
        boolean isFinish = false;
        synchronized (UMConfigure.lockObject) {
            isFinish = UMConfigure.isFinish;
        }
        return isFinish;
    }
    
    public static void init(final Context context, final int n, final String s) {
        init(context, null, null, n, s);
    }
    
    public static void init(final Context context, String appkeyByXML, String channelByXML, final int n, final String s) {
        try {
            if (UMConfigure.debugLog) {
                Log.i("UMConfigure", "common version is 2.0.2");
                Log.i("UMConfigure", "common type is " + SdkVersion.SDK_TYPE);
            }
            if (context == null) {
                if (UMConfigure.debugLog) {
                    Log.e("UMConfigure", "context is null !!!");
                }
                return;
            }
            final Context applicationContext = context.getApplicationContext();
            if (UMConfigure.debugLog) {
                final String appkeyByXML2 = UMUtils.getAppkeyByXML(applicationContext);
                if (!TextUtils.isEmpty((CharSequence)appkeyByXML) && !TextUtils.isEmpty((CharSequence)appkeyByXML2) && !appkeyByXML.equals(appkeyByXML2)) {
                    UMLog.mutlInfo("\u8bf7\u6ce8\u610f\uff1a\u60a8init\u63a5\u53e3\u4e2d\u8bbe\u7f6e\u7684AppKey\u662f@\uff0cmanifest\u4e2d\u8bbe\u7f6e\u7684AppKey\u662f#\uff0cinit\u63a5\u53e3\u8bbe\u7f6e\u7684AppKey\u4f1a\u8986\u76d6manifest\u4e2d\u8bbe\u7f6e\u7684AppKey", 3, "", new String[] { "@", "#" }, new String[] { appkeyByXML, appkeyByXML2 });
                }
            }
            if (TextUtils.isEmpty((CharSequence)appkeyByXML)) {
                appkeyByXML = UMUtils.getAppkeyByXML(applicationContext);
            }
            if (TextUtils.isEmpty((CharSequence)channelByXML)) {
                channelByXML = UMUtils.getChannelByXML(applicationContext);
            }
            if (TextUtils.isEmpty((CharSequence)channelByXML)) {
                channelByXML = "Unknown";
            }
            UMUtils.setChannel(applicationContext, channelByXML);
            if (UMConfigure.debugLog) {
                Log.i("UMConfigure", "channel is " + channelByXML);
            }
            if (UMUtils.isMainProgress(applicationContext)) {
                saveSDKComponent();
            }
            try {
                final Class<?> forName = Class.forName("com.umeng.analytics.MobclickAgent");
                if (forName != null) {
                    final Method declaredMethod = forName.getDeclaredMethod("init", Context.class);
                    if (declaredMethod != null) {
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(forName, applicationContext);
                        if (UMConfigure.debugLog) {
                            UMLog.mutlInfo("\u7edf\u8ba1SDK\u521d\u59cb\u5316\u6210\u529f", 2, "");
                        }
                    }
                    if (Class.forName("com.umeng.analytics.game.UMGameAgent") != null) {
                        final Method declaredMethod2 = forName.getDeclaredMethod("setGameScenarioType", Context.class);
                        if (declaredMethod2 != null) {
                            declaredMethod2.setAccessible(true);
                            declaredMethod2.invoke(forName, applicationContext);
                        }
                    }
                }
                if (com.umeng.commonsdk.statistics.b.a.indexOf("e") >= 0) {
                    final Class<?> forName2 = Class.forName("com.umeng.analytics.MobclickAgent");
                    if (forName2 != null) {
                        final Method declaredMethod3 = forName2.getDeclaredMethod("disableExceptionCatch", (Class[])new Class[0]);
                        if (declaredMethod3 != null) {
                            declaredMethod3.setAccessible(true);
                            declaredMethod3.invoke(forName2, new Object[0]);
                        }
                    }
                }
            }
            catch (Throwable t) {}
            try {
                final Class<?> forName3 = Class.forName("com.umeng.message.MessageSharedPrefs");
                if (forName3 != null) {
                    final Method declaredMethod4 = forName3.getDeclaredMethod("getInstance", Context.class);
                    if (declaredMethod4 != null) {
                        final Object invoke = declaredMethod4.invoke(forName3, applicationContext);
                        if (invoke != null) {
                            final Method declaredMethod5 = forName3.getDeclaredMethod("setMessageAppKey", String.class);
                            if (declaredMethod5 != null) {
                                declaredMethod5.setAccessible(true);
                                declaredMethod5.invoke(invoke, appkeyByXML);
                                if (UMConfigure.debugLog) {
                                    final UMLog umDebugLog = UMConfigure.umDebugLog;
                                    UMLog.mutlInfo("PUSH AppKey\u8bbe\u7f6e\u6210\u529f", 2, "");
                                }
                            }
                            final Method declaredMethod6 = forName3.getDeclaredMethod("setMessageChannel", String.class);
                            if (declaredMethod6 != null) {
                                declaredMethod6.setAccessible(true);
                                declaredMethod6.invoke(invoke, channelByXML);
                                if (UMConfigure.debugLog) {
                                    final UMLog umDebugLog2 = UMConfigure.umDebugLog;
                                    UMLog.mutlInfo("PUSH Channel\u8bbe\u7f6e\u6210\u529f", 2, "");
                                }
                            }
                            if (TextUtils.isEmpty((CharSequence)s)) {
                                if (UMConfigure.debugLog) {}
                            }
                            else {
                                if (UMConfigure.debugLog) {
                                    Log.i("UMConfigure", "push secret is " + s);
                                }
                                final Method declaredMethod7 = forName3.getDeclaredMethod("setMessageAppSecret", String.class);
                                if (declaredMethod7 != null) {
                                    declaredMethod7.setAccessible(true);
                                    declaredMethod7.invoke(invoke, s);
                                    if (UMConfigure.debugLog) {
                                        final UMLog umDebugLog3 = UMConfigure.umDebugLog;
                                        UMLog.mutlInfo("PUSH Secret\u8bbe\u7f6e\u6210\u529f", 2, "");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {}
            try {
                final Class<?> class1 = getClass("com.umeng.socialize.UMShareAPI");
                setFile(class1, "APPKEY", appkeyByXML);
                if (class1 != null) {
                    final Method declaredMethod8 = class1.getDeclaredMethod("init", Context.class, String.class);
                    if (declaredMethod8 != null) {
                        declaredMethod8.setAccessible(true);
                        declaredMethod8.invoke(class1, applicationContext, appkeyByXML);
                        if (UMConfigure.debugLog) {
                            UMLog.mutlInfo("Share AppKey\u8bbe\u7f6e\u6210\u529f", 2, "");
                        }
                    }
                }
            }
            catch (Throwable t2) {}
            if (TextUtils.isEmpty((CharSequence)appkeyByXML)) {
                if (UMConfigure.debugLog) {
                    UMLog.aq(UMLogCommon.SC_10007, 0, "\\|");
                }
                return;
            }
            UMUtils.setAppkey(applicationContext, appkeyByXML);
            final String lastAppkey = UMUtils.getLastAppkey(applicationContext);
            if (!TextUtils.isEmpty((CharSequence)appkeyByXML) && !appkeyByXML.equals(lastAppkey)) {
                if (!TextUtils.isEmpty((CharSequence)lastAppkey) && UMConfigure.debugLog) {
                    UMLog.mutlInfo("AppKey\u6539\u53d8!!!", 2, "");
                }
                UMUtils.setLastAppkey(applicationContext, appkeyByXML);
            }
            if (UMConfigure.debugLog) {
                Log.i("UMConfigure", "current appkey is " + appkeyByXML + ", last appkey is " + lastAppkey);
            }
            AnalyticsConstants.setDeviceType(n);
            try {
                final Class<?> forName4 = Class.forName("com.umeng.error.UMError");
                if (forName4 != null) {
                    final Method declaredMethod9 = forName4.getDeclaredMethod("init", Context.class);
                    if (declaredMethod9 != null) {
                        declaredMethod9.setAccessible(true);
                        declaredMethod9.invoke(forName4, applicationContext);
                        if (UMConfigure.debugLog) {
                            UMLog.mutlInfo("\u9519\u8bef\u5206\u6790SDK\u521d\u59cb\u5316\u6210\u529f", 2, "");
                        }
                    }
                }
            }
            catch (Throwable t3) {}
            if (UMUtils.isMainProgress(applicationContext)) {
                if (SdkVersion.SDK_TYPE != 1) {
                    try {
                        final Class<?> forName5 = Class.forName("com.umeng.commonsdk.UMConfigureImpl");
                        if (forName5 != null) {
                            final Method declaredMethod10 = forName5.getDeclaredMethod("init", Context.class);
                            if (declaredMethod10 != null) {
                                declaredMethod10.setAccessible(true);
                                declaredMethod10.invoke(forName5, applicationContext);
                            }
                        }
                    }
                    catch (Throwable t4) {}
                }
                else {
                    a.a(applicationContext);
                }
            }
            try {
                final Method method = Class.forName("com.umeng.visual.UMVisualAgent").getMethod("init", Context.class, String.class);
                if (!TextUtils.isEmpty((CharSequence)appkeyByXML)) {
                    method.invoke(null, context, appkeyByXML);
                }
                else if (AnalyticsConstants.UM_DEBUG) {
                    MLog.e("initDebugSDK appkey  is null");
                }
            }
            catch (ClassNotFoundException ex2) {
                try {
                    Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("init", Context.class).invoke(null, context);
                }
                catch (Exception ex3) {}
            }
            catch (Throwable t5) {}
            final UMGlobalContext.a a = new UMGlobalContext.a();
            a.a = applicationContext;
            a.b = n;
            a.c = s;
            a.d = appkeyByXML;
            a.e = channelByXML;
            a.f = com.umeng.commonsdk.statistics.b.a;
            a.g = false;
            a.h = UMFrUtils.getCurrentProcessName(applicationContext);
            a.i = UMUtils.getAppVersionName(applicationContext);
            a.j = UMUtils.isMainProgress(applicationContext);
            UMGlobalContext.newUMGlobalContext(a);
            synchronized (UMConfigure.lockObject) {
                UMConfigure.isFinish = true;
            }
        }
        catch (Exception obj) {
            if (UMConfigure.debugLog) {
                Log.e("UMConfigure", "init e_enum is " + obj);
            }
        }
        catch (Throwable obj2) {
            if (UMConfigure.debugLog) {
                Log.e("UMConfigure", "init e_enum is " + obj2);
            }
        }
    }
    
    public static boolean isDebugLog() {
        return UMConfigure.debugLog;
    }
    
    public static void setLogEnabled(final boolean b) {
        try {
            UMConfigure.debugLog = b;
            MLog.DEBUG = b;
            final Class<?> class1 = getClass("com.umeng.message.PushAgent");
            invoke(getDecMethod(class1, "setDebugMode", new Class[] { Boolean.TYPE }), getDecInstanceObject(class1), new Object[] { b });
            setFile(getClass("com.umeng.socialize.Config"), "DEBUG", b);
        }
        catch (Exception obj) {
            if (UMConfigure.debugLog) {
                Log.e("UMConfigure", "set log enabled e_enum is " + obj);
            }
        }
        catch (Throwable obj2) {
            if (UMConfigure.debugLog) {
                Log.e("UMConfigure", "set log enabled e_enum is " + obj2);
            }
        }
    }
    
    public static void setEncryptEnabled(final boolean encryptEnabled) {
        com.umeng.commonsdk.statistics.b.a(encryptEnabled);
        UMSLEnvelopeBuild.setEncryptEnabled(encryptEnabled);
    }
    
    public static String getUMIDString(Context applicationContext) {
        if (applicationContext != null) {
            applicationContext = applicationContext.getApplicationContext();
            return UMUtils.getUMId(applicationContext);
        }
        return null;
    }
    
    public static void setProcessEvent(final boolean sub_PROCESS_EVENT) {
        AnalyticsConstants.SUB_PROCESS_EVENT = sub_PROCESS_EVENT;
    }
    
    private static void setLatencyWindow(final long n) {
        com.umeng.commonsdk.statistics.a.c = (int)n * 1000;
    }
    
    private static void setCheckDevice(final boolean check_DEVICE) {
        AnalyticsConstants.CHECK_DEVICE = check_DEVICE;
    }
    
    private static void setWraperType(final String s, final String s2) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (s.equals("native")) {
                com.umeng.commonsdk.stateless.a.a = "native";
                com.umeng.commonsdk.statistics.a.a = "native";
            }
            else if (s.equals("Cocos2d-x")) {
                com.umeng.commonsdk.stateless.a.a = "Cocos2d-x";
                com.umeng.commonsdk.statistics.a.a = "Cocos2d-x";
            }
            else if (s.equals("Cocos2d-x_lua")) {
                com.umeng.commonsdk.stateless.a.a = "Cocos2d-x_lua";
                com.umeng.commonsdk.statistics.a.a = "Cocos2d-x_lua";
            }
            else if (s.equals("Unity")) {
                com.umeng.commonsdk.stateless.a.a = "Unity";
                com.umeng.commonsdk.statistics.a.a = "Unity";
            }
            else if (s.equals("react-native")) {
                com.umeng.commonsdk.stateless.a.a = "react-native";
                com.umeng.commonsdk.statistics.a.a = "react-native";
            }
            else if (s.equals("phonegap")) {
                com.umeng.commonsdk.stateless.a.a = "phonegap";
                com.umeng.commonsdk.statistics.a.a = "phonegap";
            }
            else if (s.equals("weex")) {
                com.umeng.commonsdk.stateless.a.a = "weex";
                com.umeng.commonsdk.statistics.a.a = "weex";
            }
            else if (s.equals("hybrid")) {
                com.umeng.commonsdk.stateless.a.a = "hybrid";
                com.umeng.commonsdk.statistics.a.a = "hybrid";
            }
            else if (s.equals("flutter")) {
                com.umeng.commonsdk.stateless.a.a = "flutter";
                com.umeng.commonsdk.statistics.a.a = "flutter";
            }
        }
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            com.umeng.commonsdk.stateless.a.b = s2;
            com.umeng.commonsdk.statistics.a.b = s2;
        }
    }
    
    public static String[] getTestDeviceInfo(final Context context) {
        final String[] array = new String[2];
        try {
            if (context != null) {
                array[0] = DeviceConfig.getDeviceIdForGeneral(context);
                array[1] = DeviceConfig.getMac(context);
            }
        }
        catch (Exception ex) {}
        return array;
    }
    
    static {
        UMConfigure.debugLog = false;
        UMConfigure.umDebugLog = new UMLog();
        UMConfigure.isFinish = false;
        UMConfigure.lockObject = new Object();
    }
}
