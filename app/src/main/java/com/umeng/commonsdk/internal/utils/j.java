package com.umeng.commonsdk.internal.utils;

import java.util.*;
import android.content.*;
import org.json.*;
import com.umeng.commonsdk.internal.*;
import com.umeng.commonsdk.framework.*;
import com.umeng.commonsdk.internal.crash.*;
import com.umeng.commonsdk.statistics.common.*;
import android.os.*;
import android.util.*;
import android.hardware.camera2.*;
import android.hardware.*;

public class j
{
    private static final String a = "info";
    private static final String b = "stat";
    private static boolean c;
    private static HandlerThread d;
    private static Context e;
    private static int f;
    private static int g;
    private static int h;
    private static int i;
    private static long j;
    private static long k;
    private static final int l = 40;
    private static final int m = 50000;
    private static SensorManager n;
    private static ArrayList<float[]> o;
    private static SensorEventListener p;
    
    public static List<Sensor> a(final Context context) {
        if (null == context) {
            return null;
        }
        final SensorManager sensorManager = (SensorManager)context.getSystemService("sensor");
        if (sensorManager == null) {
            return null;
        }
        return (List<Sensor>)sensorManager.getSensorList(-1);
    }
    
    public static void b(final Context context) {
        if (null == context) {
            return;
        }
        if (a()) {
            return;
        }
        com.umeng.commonsdk.internal.utils.j.c = true;
        com.umeng.commonsdk.internal.utils.j.e = context.getApplicationContext();
        final String currentProcessName = UMFrUtils.getCurrentProcessName(context);
        final String packageName = context.getPackageName();
        if (currentProcessName != null && currentProcessName.equals(packageName)) {
            com.umeng.commonsdk.internal.utils.j.n = (SensorManager)context.getSystemService("sensor");
            if (com.umeng.commonsdk.internal.utils.j.n != null) {
                final Sensor defaultSensor = com.umeng.commonsdk.internal.utils.j.n.getDefaultSensor(4);
                final Sensor defaultSensor2 = com.umeng.commonsdk.internal.utils.j.n.getDefaultSensor(1);
                if (null != defaultSensor) {
                    com.umeng.commonsdk.internal.utils.j.h = 4;
                    com.umeng.commonsdk.internal.utils.j.n.registerListener(com.umeng.commonsdk.internal.utils.j.p, defaultSensor, 50000);
                }
                else if (null != defaultSensor2) {
                    com.umeng.commonsdk.internal.utils.j.h = 1;
                    com.umeng.commonsdk.internal.utils.j.n.registerListener(com.umeng.commonsdk.internal.utils.j.p, defaultSensor2, 50000);
                }
                final int n = new Random().nextInt(3) * 1000 + 4000;
                (com.umeng.commonsdk.internal.utils.j.d = new HandlerThread("sensor_thread")).start();
                new Handler(com.umeng.commonsdk.internal.utils.j.d.getLooper()).postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        try {
                            com.umeng.commonsdk.internal.utils.j.f = 0;
                            if (null != defaultSensor) {
                                com.umeng.commonsdk.internal.utils.j.n.registerListener(com.umeng.commonsdk.internal.utils.j.p, defaultSensor, 50000);
                            }
                            else if (null != defaultSensor2) {
                                com.umeng.commonsdk.internal.utils.j.n.registerListener(com.umeng.commonsdk.internal.utils.j.p, defaultSensor2, 50000);
                            }
                        }
                        catch (Exception ex) {
                            ULog.i("sensor exception");
                        }
                    }
                }, (long)n);
            }
        }
    }
    
    private static void l() {
        if (null != com.umeng.commonsdk.internal.utils.j.n) {
            com.umeng.commonsdk.internal.utils.j.n.unregisterListener(com.umeng.commonsdk.internal.utils.j.p);
        }
        if (com.umeng.commonsdk.internal.utils.j.o.size() == 40) {
            f(com.umeng.commonsdk.internal.utils.j.e);
            if (null != com.umeng.commonsdk.internal.utils.j.o) {
                com.umeng.commonsdk.internal.utils.j.o.clear();
            }
            if (null != com.umeng.commonsdk.internal.utils.j.d) {
                com.umeng.commonsdk.internal.utils.j.d.quit();
                com.umeng.commonsdk.internal.utils.j.d = null;
            }
            com.umeng.commonsdk.internal.utils.j.e = null;
            com.umeng.commonsdk.internal.utils.j.c = false;
        }
    }
    
    public static JSONArray c(final Context context) {
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        JSONArray jsonArray = null;
        if (null != sharedPreferences) {
            final String string = sharedPreferences.getString("stat", (String)null);
            if (null != string) {
                try {
                    jsonArray = new JSONArray(string);
                }
                catch (JSONException ex) {}
            }
        }
        return jsonArray;
    }
    
    private static void f(final Context context) {
        if (null == context) {
            return;
        }
        try {
            final JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < 2; ++i) {
                final JSONObject jsonObject = new JSONObject();
                final JSONArray jsonArray2 = new JSONArray();
                int j = 0;
                int n = 20;
                if (i == 1) {
                    j = 20;
                    n = 40;
                }
                while (j < n) {
                    final JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("x", (double)com.umeng.commonsdk.internal.utils.j.o.get(j)[0]);
                    jsonObject2.put("y", (double)com.umeng.commonsdk.internal.utils.j.o.get(j)[1]);
                    jsonObject2.put("z", (double)com.umeng.commonsdk.internal.utils.j.o.get(j)[2]);
                    jsonArray2.put((Object)jsonObject2);
                    ++j;
                }
                if (com.umeng.commonsdk.internal.utils.j.h == 4) {
                    jsonObject.put("entity", (Object)jsonArray2);
                }
                else if (com.umeng.commonsdk.internal.utils.j.h == 1) {
                    jsonObject.put("InnerClass_a", (Object)jsonArray2);
                }
                if (i == 0) {
                    jsonObject.put("ts", com.umeng.commonsdk.internal.utils.j.j);
                }
                else {
                    jsonObject.put("ts", com.umeng.commonsdk.internal.utils.j.k);
                }
                jsonArray.put((Object)jsonObject);
                UMWorkDispatch.sendEvent(context, 32776, com.umeng.commonsdk.internal.b.a(context).a(), jsonArray.toString());
            }
        }
        catch (Exception ex) {
            UMCrashManager.reportCrash(context, ex);
        }
    }
    
    public static void d(final Context context) {
        if (null == context) {
            return;
        }
        context.getApplicationContext().getSharedPreferences("info", 0).edit().remove("stat").commit();
    }
    
    public static synchronized boolean a() {
        return com.umeng.commonsdk.internal.utils.j.c;
    }
    
    public static List<a> e(final Context context) {
        if (null == context) {
            return null;
        }
        if (!DeviceConfig.checkPermission(context, "android.permission.CAMERA")) {
            return null;
        }
        final ArrayList<a> list = new ArrayList<a>();
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                final CameraManager cameraManager = (CameraManager)context.getSystemService("camera");
                if (cameraManager != null) {
                    final String[] cameraIdList = cameraManager.getCameraIdList();
                    for (int i = 0; i < cameraIdList.length; ++i) {
                        final Size size = (Size)cameraManager.getCameraCharacteristics(cameraIdList[i]).get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
                        if (null != size) {
                            final a a = new a();
                            a.a = size.getWidth();
                            a.b = size.getHeight();
                            a.c = System.currentTimeMillis();
                            list.add(a);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ULog.i("camera access exception");
        }
        return list;
    }
    
    static {
        com.umeng.commonsdk.internal.utils.j.c = false;
        com.umeng.commonsdk.internal.utils.j.f = 0;
        com.umeng.commonsdk.internal.utils.j.g = 0;
        com.umeng.commonsdk.internal.utils.j.h = 0;
        com.umeng.commonsdk.internal.utils.j.i = 1;
        com.umeng.commonsdk.internal.utils.j.j = 0L;
        com.umeng.commonsdk.internal.utils.j.k = 0L;
        com.umeng.commonsdk.internal.utils.j.o = new ArrayList<float[]>();
        com.umeng.commonsdk.internal.utils.j.p = (SensorEventListener)new SensorEventListener() {
            public void onSensorChanged(final SensorEvent sensorEvent) {
                if (com.umeng.commonsdk.internal.utils.j.g < 15) {
                    com.umeng.commonsdk.internal.utils.j.g++;
                    return;
                }
                if (com.umeng.commonsdk.internal.utils.j.f < 20) {
                    com.umeng.commonsdk.internal.utils.j.f++;
                    com.umeng.commonsdk.internal.utils.j.o.add(sensorEvent.values.clone());
                }
                if (com.umeng.commonsdk.internal.utils.j.f == 20) {
                    com.umeng.commonsdk.internal.utils.j.f++;
                    if (com.umeng.commonsdk.internal.utils.j.i == 1) {
                        com.umeng.commonsdk.internal.utils.j.j = System.currentTimeMillis();
                    }
                    if (com.umeng.commonsdk.internal.utils.j.i == 2) {
                        com.umeng.commonsdk.internal.utils.j.k = System.currentTimeMillis();
                    }
                    com.umeng.commonsdk.internal.utils.j.i++;
                    l();
                }
            }
            
            public void onAccuracyChanged(final Sensor sensor, final int n) {
            }
        };
    }
    
    public static class a
    {
        public int a;//width
        public int b;//height
        public long c;//currentTimeMillis
    }
}
