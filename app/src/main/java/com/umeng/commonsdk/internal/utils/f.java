package com.umeng.commonsdk.internal.utils;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import org.json.*;

public class f
{
    private static final String a = "info";
    private static final String b = "a_na";
    private static final String c = "a_st";
    private static final String d = "a_ad";
    private static final String e = "blueinfo";
    private static final String f = "a_dc";
    private static final String g = "bssid";
    private static final String h = "ssid";
    private static final String i = "a_fcy";
    private static final String j = "a_hssid";
    private static final String k = "a_ip";
    private static final String l = "a_ls";
    private static final String m = "a_mac";
    private static final String n = "a_nid";
    private static final String o = "rssi";
    private static final String p = "sta";
    private static final String q = "ts";
    private static final String r = "wifiinfo";
    private static final String s = "ua";
    
    public static JSONObject a(final Context context) {
        JSONObject jsonObject = null;
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        try {
            if (sharedPreferences != null) {
                final String string = sharedPreferences.getString("blueinfo", (String)null);
                if (string != null) {
                    jsonObject = new JSONObject(string);
                }
            }
        }
        catch (Exception ex) {
            if (ex != null) {}
        }
        return jsonObject;
    }
    
    public static void a(final Context context, final Object o) {
        try {
            if (o != null) {
                final a.b b = (a.b)o;
                final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
                String string = null;
                if (sharedPreferences != null) {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("a_na", (Object)b.c);
                    jsonObject.put("a_st", b.b);
                    jsonObject.put("a_ad", (Object)b.a);
                    jsonObject.put("ts", System.currentTimeMillis());
                    if (jsonObject != null) {
                        string = jsonObject.toString();
                    }
                }
                if (string != null) {
                    sharedPreferences.edit().putString("blueinfo", string).commit();
                }
            }
        }
        catch (Exception ex) {
            if (ex != null) {
                ULog.e("saveBluetoothInfo:" + ex.getMessage());
            }
        }
    }
    
    public static JSONArray b(final Context context) {
        JSONArray jsonArray = null;
        try {
            final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
            if (sharedPreferences != null) {
                final String string = sharedPreferences.getString("wifiinfo", (String)null);
                if (string != null) {
                    jsonArray = new JSONArray(string);
                }
            }
        }
        catch (Exception ex) {
            if (ex != null) {
                ULog.e(ex.getMessage());
            }
        }
        return jsonArray;
    }
    
    public static void c(final Context context) {
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove("wifiinfo").commit();
        }
    }
    
    public static void a(final Context context, final a.c c) {
        try {
            final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
            String string = null;
            if (sharedPreferences != null) {
                final String string2 = sharedPreferences.getString("wifiinfo", (String)null);
                JSONArray jsonArray;
                if (string2 == null) {
                    jsonArray = new JSONArray();
                }
                else {
                    jsonArray = new JSONArray(string2);
                }
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("a_dc", c.a);
                jsonObject.put("bssid", (Object)c.b);
                jsonObject.put("ssid", (Object)c.c);
                jsonObject.put("a_fcy", c.d);
                jsonObject.put("a_hssid", c.e);
                jsonObject.put("a_ip", c.f);
                jsonObject.put("a_ls", c.g);
                jsonObject.put("a_mac", (Object)c.h);
                jsonObject.put("a_nid", c.i);
                jsonObject.put("rssi", c.j);
                jsonObject.put("sta", c.k);
                jsonObject.put("ts", c.l);
                jsonArray.put((Object)jsonObject);
                if (jsonObject != null) {
                    string = jsonArray.toString();
                }
            }
            if (string != null) {
                sharedPreferences.edit().putString("wifiinfo", string).commit();
            }
        }
        catch (Exception ex) {
            if (ex != null) {
                ULog.e(ex.getMessage());
            }
        }
    }
    
    public static void a(final Context context, final String s) {
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("ua", s).commit();
        }
    }
    
    public static String d(final Context context) {
        String string = null;
        final SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            string = sharedPreferences.getString("ua", (String)null);
        }
        return string;
    }
}
