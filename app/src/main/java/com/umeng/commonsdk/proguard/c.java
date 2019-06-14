package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.statistics.common.*;
import android.location.*;
import android.text.*;
import org.json.*;
import android.content.*;

public class c
{
    private static final String f = "UMSysLocationCache";
    public static final String a = "lng";
    public static final String b = "lat";
    public static final String c = "ts";
    public static final long d = 30000L;
    public static final int e = 200;
    private static boolean g;
    
    public static void a(final Context context) {
        ULog.i("UMSysLocationCache", "begin location");
        if (context == null) {
            return;
        }
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (com.umeng.commonsdk.proguard.c.g) {
                            try {
                                final JSONArray b = com.umeng.commonsdk.proguard.c.b(context);
                                if (b != null && b.length() >= 200) {
                                    com.umeng.commonsdk.proguard.c.g = false;
                                    return;
                                }
                            }
                            catch (Throwable t) {
                                com.umeng.commonsdk.proguard.c.g = false;
                                return;
                            }
                            ULog.i("UMSysLocationCache", "location status is ok, time is " + System.currentTimeMillis());
                            final b b2 = new b(context);
                            b2.a(new d() {
                                @Override
                                public void a(final Location location) {
                                    try {
                                        if (location != null) {
                                            final double longitude = location.getLongitude();
                                            final double latitude = location.getLatitude();
                                            final float accuracy = location.getAccuracy();
                                            final double altitude = location.getAltitude();
                                            ULog.i("UMSysLocationCache", "lon is " + longitude + ", lat is " + latitude + ", acc is " + accuracy + ", alt is " + altitude);
                                            if (longitude != 0.0 && latitude != 0.0) {
                                                final long time = location.getTime();
                                                final JSONObject jsonObject = new JSONObject();
                                                try {
                                                    jsonObject.put("lng", longitude);
                                                    jsonObject.put("lat", latitude);
                                                    jsonObject.put("ts", time);
                                                    jsonObject.put("acc", (double)accuracy);
                                                    jsonObject.put("alt", altitude);
                                                }
                                                catch (JSONException obj) {
                                                    ULog.i("UMSysLocationCache", "e_enum is " + obj);
                                                }
                                                ULog.i("UMSysLocationCache", "locationJSONObject is " + jsonObject.toString());
                                                final SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_common_location", 0);
                                                if (sharedPreferences != null) {
                                                    final String string = sharedPreferences.getString("location_json_lon", "");
                                                    final String string2 = sharedPreferences.getString("location_json_lat", "");
                                                    ULog.i("UMSysLocationCache", "--->>> get lon is " + string + ", lat is " + string2);
                                                    if (!TextUtils.isEmpty((CharSequence)string) && Double.parseDouble(string) == longitude && !TextUtils.isEmpty((CharSequence)string2) && Double.parseDouble(string2) == latitude) {
                                                        ULog.i("UMSysLocationCache", "location same");
                                                    }
                                                    else {
                                                        JSONArray b = com.umeng.commonsdk.proguard.c.b(context);
                                                        if (b == null) {
                                                            b = new JSONArray();
                                                        }
                                                        b.put((Object)jsonObject);
                                                        final SharedPreferences.Editor edit = sharedPreferences.edit();
                                                        edit.putString("location_json_lon", String.valueOf(longitude));
                                                        edit.putString("location_json_lat", String.valueOf(latitude));
                                                        edit.putString("location_json_array", b.toString());
                                                        edit.commit();
                                                        ULog.i("UMSysLocationCache", "location put is ok~~");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    catch (Throwable t) {
                                        ULog.i("UMSysLocationCache", "" + t.getMessage());
                                    }
                                    b2.a();
                                }
                            });
                            try {
                                Thread.sleep(30000L);
                            }
                            catch (Exception ex) {}
                        }
                    }
                    catch (Throwable t2) {}
                }
            }).start();
        }
        catch (Exception ex) {}
    }
    
    public static JSONArray b(final Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_common_location", 0);
        if (sharedPreferences == null) {
            return null;
        }
        JSONArray jsonArray = null;
        try {
            final String string = sharedPreferences.getString("location_json_array", "");
            if (!TextUtils.isEmpty((CharSequence)string)) {
                jsonArray = new JSONArray(string);
            }
        }
        catch (JSONException obj) {
            ULog.i("UMSysLocationCache", "e_enum is " + obj);
        }
        catch (Throwable obj2) {
            ULog.i("UMSysLocationCache", "e_enum is " + obj2);
        }
        if (jsonArray != null) {
            ULog.i("UMSysLocationCache", "get json str is " + jsonArray.toString());
        }
        return jsonArray;
    }
    
    public static void c(final Context context) {
        try {
            final SharedPreferences sharedPreferences = context.getSharedPreferences("umeng_common_location", 0);
            if (sharedPreferences != null) {
                final SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("location_json_array", "");
                edit.commit();
                ULog.i("UMSysLocationCache", "delete is ok~~");
            }
        }
        catch (Throwable t) {}
    }
    
    static {
        com.umeng.commonsdk.proguard.c.g = true;
    }
}
