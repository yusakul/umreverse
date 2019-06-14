package com.umeng.commonsdk.stateless;

import android.content.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.internal.crash.*;
import com.umeng.commonsdk.proguard.*;

public class c
{
    private final byte[] a;
    private final int b = 1;
    private final int c = 0;
    private String d;
    private String e;
    private byte[] f;
    private byte[] g;
    private byte[] h;
    private int i;
    private int j;
    private int k;
    private byte[] l;
    private byte[] m;
    private boolean n;
    
    private c(final byte[] array, final String e, final byte[] m) throws Exception {
        this.a = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        this.d = "1.0";
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = null;
        this.m = null;
        this.n = false;
        if (array == null || array.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.e = e;
        this.k = array.length;
        this.l = com.umeng.commonsdk.stateless.f.a(array);
        this.j = (int)(System.currentTimeMillis() / 1000L);
        this.m = m;
    }
    
    public static c a(final Context context, final String str, final byte[] array) {
        try {
            final String mac = DeviceConfig.getMac(context);
            final String deviceId = DeviceConfig.getDeviceId(context);
            ULog.i("walle", "[stateless] build envelope, raw is  " + (array == null) + "m app key is " + str + "device id is " + deviceId + ", mac is " + mac);
            final c c = new c(array, str, (deviceId + mac).getBytes());
            c.a();
            return c;
        }
        catch (Exception ex) {
            ULog.i("walle", "[stateless] build envelope, e_enum is " + ex.getMessage());
            UMCrashManager.reportCrash(context, ex);
            return null;
        }
    }
    
    public static c b(final Context context, final String s, final byte[] array) {
        try {
            final c c = new c(array, s, (DeviceConfig.getDeviceId(context) + DeviceConfig.getMac(context)).getBytes());
            c.a(true);
            c.a();
            return c;
        }
        catch (Exception ex) {
            UMCrashManager.reportCrash(context, ex);
            return null;
        }
    }
    
    public void a(final boolean n) {
        this.n = n;
    }
    
    public void a() {
        if (this.f == null) {
            this.f = this.c();
        }
        if (this.n) {
            final byte[] array = new byte[16];
            try {
                System.arraycopy(this.f, 1, array, 0, 16);
                this.l = com.umeng.commonsdk.stateless.f.a(this.l, array);
            }
            catch (Exception ex) {}
        }
        this.g = this.a(this.f, this.j);
        this.h = this.d();
    }
    
    private byte[] a(final byte[] array, final int n) {
        final byte[] b = com.umeng.commonsdk.stateless.f.b(this.m);
        final byte[] b2 = com.umeng.commonsdk.stateless.f.b(this.l);
        final int length = b.length;
        final byte[] array2 = new byte[length * 2];
        for (int i = 0; i < length; ++i) {
            array2[i * 2] = b2[i];
            array2[i * 2 + 1] = b[i];
        }
        for (int j = 0; j < 2; ++j) {
            array2[j] = array[j];
            array2[array2.length - j - 1] = array[array.length - j - 1];
        }
        final byte[] array3 = { (byte)(n & 0xFF), (byte)(n >> 8 & 0xFF), (byte)(n >> 16 & 0xFF), (byte)(n >>> 24) };
        for (int k = 0; k < array2.length; ++k) {
            array2[k] ^= array3[k % 4];
        }
        return array2;
    }
    
    private byte[] c() {
        return this.a(this.a, (int)(System.currentTimeMillis() / 1000L));
    }
    
    private byte[] d() {
        final StringBuilder sb = new StringBuilder();
        sb.append(com.umeng.commonsdk.stateless.f.c(this.f));
        sb.append(this.i);
        sb.append(this.j);
        sb.append(this.k);
        sb.append(com.umeng.commonsdk.stateless.f.c(this.g));
        return com.umeng.commonsdk.stateless.f.b(sb.toString().getBytes());
    }
    
    public byte[] b() {
        final b b = new b();
        b.a(this.d);
        b.b(this.e);
        b.c(com.umeng.commonsdk.stateless.f.c(this.f));
        b.a(this.i);
        b.b(this.j);
        b.c(this.k);
        b.a(this.l);
        b.d(this.n ? 1 : 0);
        b.d(com.umeng.commonsdk.stateless.f.c(this.g));
        b.e(com.umeng.commonsdk.stateless.f.c(this.h));
        try {
            return new s().a(b);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
