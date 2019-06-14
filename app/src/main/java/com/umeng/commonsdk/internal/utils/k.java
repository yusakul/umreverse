package com.umeng.commonsdk.internal.utils;

import android.os.*;
import android.provider.*;
import com.umeng.commonsdk.utils.*;
import android.bluetooth.*;
import android.telephony.gsm.*;
import android.telephony.cdma.*;
import android.telephony.*;
import android.text.*;
import android.content.*;
import org.json.*;
import android.hardware.*;
import java.util.*;

public class k
{
    private static final String a = "um_pri";
    private static final String b = "um_common_strength";
    private static final String c = "um_common_battery";
    
    public static String a(final Context context) {
        String s = null;
        if (context != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                s = Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
            }
            else if (UMUtils.checkPermission(context, "android.permission.BLUETOOTH")) {
                s = BluetoothAdapter.getDefaultAdapter().getAddress();
            }
        }
        return s;
    }
    
    public static String b(final Context context) {
        String simSerialNumber = null;
        if (context != null && UMUtils.checkPermission(context, "android.permission.READ_PHONE_STATE")) {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (telephonyManager == null) {
                return simSerialNumber;
            }
            simSerialNumber = telephonyManager.getSimSerialNumber();
        }
        return simSerialNumber;
    }
    
    public static String c(final Context context) {
        String s = null;
        if (context != null && Build.VERSION.SDK_INT >= 23 && UMUtils.checkPermission(context, "android.permission.READ_PHONE_STATE")) {
            try {
                final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                if (telephonyManager == null) {
                    return s;
                }
                final Class<? extends TelephonyManager> class1 = telephonyManager.getClass();
                if ((int)class1.getMethod("getPhoneCount", (Class<?>[])new Class[0]).invoke(telephonyManager, new Object[0]) == 2) {
                    s = (String)class1.getMethod("getDeviceId", Integer.TYPE).invoke(telephonyManager, 2);
                }
            }
            catch (Exception ex) {}
        }
        return s;
    }
    
    public static JSONObject d(final Context context) {
        JSONObject jsonObject = null;
        if (context != null && (UMUtils.checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION") || UMUtils.checkPermission(context, "android.permission.ACCESS_FINE_LOCATION"))) {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (telephonyManager == null) {
                return jsonObject;
            }
            final CellLocation cellLocation = telephonyManager.getCellLocation();
            final int phoneType = telephonyManager.getPhoneType();
            if (phoneType == 1 && cellLocation instanceof GsmCellLocation) {
                final GsmCellLocation gsmCellLocation = (GsmCellLocation)cellLocation;
                final int cid = gsmCellLocation.getCid();
                if (cid > 0 && cid != 65535) {
                    final int n = cid;
                    final int lac = gsmCellLocation.getLac();
                    jsonObject = new JSONObject();
                    try {
                        jsonObject.put("cid", n);
                        jsonObject.put("lacid", lac);
                        jsonObject.put("ts", System.currentTimeMillis());
                    }
                    catch (Exception ex) {}
                }
            }
            else if (phoneType == 2 && cellLocation instanceof CdmaCellLocation) {
                final CdmaCellLocation cdmaCellLocation = (CdmaCellLocation)cellLocation;
                final int baseStationId = cdmaCellLocation.getBaseStationId();
                final int networkId = cdmaCellLocation.getNetworkId();
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("cid", baseStationId);
                    jsonObject.put("lacid", networkId);
                    jsonObject.put("ts", System.currentTimeMillis());
                }
                catch (Exception ex2) {}
            }
        }
        return jsonObject;
    }
    
    public static void a(final Context context, final String s) {
        if (context == null || TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("um_pri", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("um_common_strength", s).commit();
        }
    }
    
    public static String e(final Context context) {
        String string = null;
        if (context != null) {
            final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("um_pri", 0);
            if (sharedPreferences != null) {
                string = sharedPreferences.getString("um_common_strength", (String)null);
            }
        }
        return string;
    }
    
    public static String f(final Context context) {
        String string = null;
        if (context != null) {
            final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("um_pri", 0);
            if (sharedPreferences != null) {
                string = sharedPreferences.getString("um_common_battery", (String)null);
            }
        }
        return string;
    }
    
    public static void b(final Context context, final String s) {
        if (context == null || TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("um_pri", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("um_common_battery", s).commit();
        }
    }
    
    public static JSONArray g(final Context context) {
        final JSONArray jsonArray = new JSONArray();
        if (context != null) {
            final SensorManager sensorManager = (SensorManager)context.getSystemService("sensor");
            if (sensorManager == null) {
                return jsonArray;
            }
            for (final Sensor sensor : sensorManager.getSensorList(-1)) {
                if (sensor != null) {
                    final JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("a_type", sensor.getType());
                        jsonObject.put("a_ven", (Object)sensor.getVendor());
                        if (Build.VERSION.SDK_INT >= 24) {
                            jsonObject.put("a_id", sensor.getId());
                        }
                        jsonObject.put("a_na", (Object)sensor.getName());
                        jsonObject.put("a_ver", sensor.getVersion());
                        jsonObject.put("a_mar", (double)sensor.getMaximumRange());
                        jsonObject.put("a_ver", sensor.getVersion());
                        jsonObject.put("a_res", (double)sensor.getResolution());
                        jsonObject.put("a_po", (double)sensor.getPower());
                        if (Build.VERSION.SDK_INT >= 9) {
                            jsonObject.put("a_mid", sensor.getMinDelay());
                        }
                        if (Build.VERSION.SDK_INT >= 21) {
                            jsonObject.put("a_mad", sensor.getMaxDelay());
                        }
                        jsonObject.put("ts", System.currentTimeMillis());
                    }
                    catch (Exception ex) {}
                    jsonArray.put((Object)jsonObject);
                }
            }
        }
        return jsonArray;
    }
}
