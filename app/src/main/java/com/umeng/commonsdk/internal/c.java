package com.umeng.commonsdk.internal;

import com.umeng.commonsdk.statistics.common.*;
import org.json.*;
import com.umeng.commonsdk.framework.*;
import android.content.*;

public class c implements UMLogDataProtocol
{
    private Context a;
    private static final String b = "info";
    private static final String c = "stat";
    
    public c(final Context context) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
    }
    
    @Override
    public void workEvent(final Object o, final int n) {
        ULog.i("walle", "[internal] workEvent");
        switch (n) {
            case 32769: {
                try {
                    ULog.i("walle", "[internal] workEvent send envelope");
                    try {
                        final Class<?> forName = Class.forName("com.umeng.commonsdk.internal.UMInternalManagerAgent");
                        if (forName != null) {
                            forName.getMethod("sendInternalEnvelopeByStateful2", Context.class).invoke(forName, this.a);
                        }
                    }
                    catch (ClassNotFoundException ex) {}
                    catch (Throwable t) {}
                }
                catch (Exception ex2) {}
                break;
            }
            case 32777: {
                try {
                    ULog.i("walle", "[internal] workEvent send envelope");
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("i_sdk_v", (Object)"1.2.0");
                    final JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("inner", (Object)new JSONObject());
                    final JSONObject buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.a, jsonObject, jsonObject2);
                    if (buildEnvelopeWithExtHeader != null && !buildEnvelopeWithExtHeader.has("exception")) {
                        ULog.i("walle", "[internal] workEvent send envelope back, result is ok");
                    }
                }
                catch (Exception ex3) {}
            }
            case 32771: {
                ULog.i("walle", "[internal] workEvent cache battery, event is " + o.toString());
                try {
                    final Class<?> forName2 = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                    if (forName2 != null) {
                        forName2.getMethod("saveBattery", Context.class, String.class).invoke(forName2, this.a, (String)o);
                    }
                }
                catch (ClassNotFoundException ex4) {}
                catch (Throwable t2) {}
                break;
            }
            case 32772: {
                ULog.i("walle", "[internal] workEvent cache station, event is " + o.toString());
                try {
                    final Class<?> forName3 = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                    if (forName3 != null) {
                        forName3.getMethod("saveBaseStationStrength", Context.class, String.class).invoke(forName3, this.a, (String)o);
                    }
                }
                catch (ClassNotFoundException ex5) {}
                catch (Throwable t3) {}
                break;
            }
            case 32773: {
                try {
                    final Class<?> forName4 = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                    if (forName4 != null) {
                        forName4.getMethod("saveBluetoothInfo", Context.class, Object.class).invoke(forName4, this.a, o);
                    }
                }
                catch (ClassNotFoundException ex6) {}
                catch (Throwable t4) {}
                break;
            }
            case 32774: {
                try {
                    final Class<?> forName5 = Class.forName("com.umeng.commonsdk.internal.utils.ApplicationLayerUtilAgent");
                    if (forName5 != null) {
                        forName5.getMethod("wifiChange", Context.class).invoke(forName5, this.a);
                    }
                }
                catch (ClassNotFoundException ex7) {}
                catch (Throwable t5) {}
                break;
            }
            case 32775: {
                try {
                    final Class<?> forName6 = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                    if (forName6 != null) {
                        forName6.getMethod("saveUA", Context.class, String.class).invoke(forName6, this.a, (String)o);
                    }
                }
                catch (ClassNotFoundException ex8) {}
                catch (Throwable t6) {}
                break;
            }
            case 32776: {
                final SharedPreferences sharedPreferences = this.a.getApplicationContext().getSharedPreferences("info", 0);
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putString("stat", (String)o).commit();
                }
                break;
            }
        }
    }
    
    @Override
    public void removeCacheData(final Object o) {
    }
    
    @Override
    public JSONObject setupReportData(final long n) {
        return null;
    }
}
