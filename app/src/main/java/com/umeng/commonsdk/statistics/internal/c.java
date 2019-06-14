package com.umeng.commonsdk.statistics.internal;

import android.content.*;
import com.umeng.commonsdk.framework.*;

import android.content.pm.PackageManager;
import android.text.*;
import com.umeng.commonsdk.statistics.*;
import com.umeng.commonsdk.internal.crash.*;
import android.net.*;
import android.os.*;
import com.umeng.commonsdk.statistics.common.*;
import org.apache.http.conn.ssl.*;
import org.apache.http.conn.ssl.SSLSocketFactory;

import java.net.Proxy;
import java.security.*;
import com.umeng.commonsdk.debug.*;
import javax.net.ssl.*;
import java.net.*;
import java.io.*;

public class c
{
    private String a;
    private int b;
    private Context c;
    private b d;
    private static boolean e;
    
    public c(final Context c) {
        this.a = "10.0.0.172";
        this.b = 80;
        this.c = c;
    }
    
    public void a(final b d) {
        this.d = d;
    }
    
    private void a() {
        final String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        final String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty((CharSequence)imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty((CharSequence)imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[] { UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL };
    }
    
    private void b() {
        final String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "domain_p", "");
        final String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "domain_s", "");
        if (!TextUtils.isEmpty((CharSequence)imprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(imprintProperty);
        }
        if (!TextUtils.isEmpty((CharSequence)imprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(imprintProperty2);
        }
        final String imprintProperty3 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_domain_p", "");
        final String imprintProperty4 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_domain_s", "");
        if (!TextUtils.isEmpty((CharSequence)imprintProperty3)) {
            UMServerURL.OVERSEA_DEFAULT_URL = DataHelper.assembleURL(imprintProperty3);
        }
        if (!TextUtils.isEmpty((CharSequence)imprintProperty4)) {
            UMServerURL.OVERSEA_SECONDARY_URL = DataHelper.assembleURL(imprintProperty4);
        }
        AnalyticsConstants.APPLOG_URL_LIST = new String[] { UMServerURL.OVERSEA_DEFAULT_URL, UMServerURL.OVERSEA_SECONDARY_URL };
        if (!TextUtils.isEmpty((CharSequence)com.umeng.commonsdk.statistics.b.b) && (com.umeng.commonsdk.statistics.b.b.startsWith("460") || com.umeng.commonsdk.statistics.b.b.startsWith("461"))) {
            AnalyticsConstants.APPLOG_URL_LIST = new String[] { UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL };
        }
    }
    
    public byte[] a(final byte[] array, final boolean b) {
        byte[] b2 = null;
        if (SdkVersion.SDK_TYPE == 0) {
            this.a();
        }
        else {
            UMServerURL.DEFAULT_URL = UMServerURL.OVERSEA_DEFAULT_URL;
            UMServerURL.SECONDARY_URL = UMServerURL.OVERSEA_SECONDARY_URL;
            this.b();
        }
        int i = 0;
        while (i < AnalyticsConstants.APPLOG_URL_LIST.length) {
            b2 = this.b(array, AnalyticsConstants.APPLOG_URL_LIST[i]);
            if (b2 != null) {
                if (this.d != null) {
                    this.d.onRequestSucceed(b);
                    break;
                }
                break;
            }
            else {
                if (this.d != null) {
                    this.d.onRequestFailed();
                }
                ++i;
            }
        }
        return b2;
    }
    
    private boolean c() {
        if (this.c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.c.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager)this.c.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (!DeviceConfig.checkPermission(this.c, "android.permission.ACCESS_NETWORK_STATE")) {
                return false;
            }
            if (connectivityManager == null) {
                return false;
            }
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() != 1) {
                final String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                    return true;
                }
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(this.c, t);
        }
        return false;
    }
    
    public byte[] a(final byte[] b, final String str) {
        HttpURLConnection httpURLConnection = null;
        try {
            if (this.d != null) {
                this.d.onRequestStart();
            }
            if (this.c()) {
                httpURLConnection = (HttpURLConnection)new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
            }
            else {
                httpURLConnection = (HttpURLConnection)new URL(str).openConnection();
            }
            httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
            httpURLConnection.setRequestProperty("X-Umeng-Sdk", com.umeng.commonsdk.statistics.internal.a.a(this.c).b());
            httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
            httpURLConnection.setRequestProperty("Content-Type", com.umeng.commonsdk.statistics.internal.a.a(this.c).a());
            httpURLConnection.setRequestProperty("X-Umeng-Pro-Ver", "1.0.0");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            if (Build.VERSION.SDK_INT < 8) {
                System.setProperty("http.keepAlive", "false");
            }
            final OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(b);
            outputStream.flush();
            outputStream.close();
            if (this.d != null) {
                this.d.onRequestEnd();
            }
            final int responseCode = httpURLConnection.getResponseCode();
            final String headerField = httpURLConnection.getHeaderField("Content-Type");
            boolean b2 = false;
            if (!TextUtils.isEmpty((CharSequence)headerField) && headerField.equalsIgnoreCase("application/thrift")) {
                b2 = true;
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.d("status code : " + responseCode + "; isThrifit:" + b2);
            }
            if (responseCode == 200 && b2) {
                MLog.i("Send message to " + str);
                final InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    return HelperUtils.readStreamToByteArray(inputStream);
                }
                finally {
                    HelperUtils.safeClose(inputStream);
                }
            }
        }
        catch (Throwable t) {
            MLog.e("IOException,Failed to send message.", t);
            return null;
        }
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return null;
    }
    
    public byte[] b(final byte[] b, final String str) {
        HttpsURLConnection httpsURLConnection = null;
        OutputStream outputStream = null;
        try {
            if (this.d != null) {
                this.d.onRequestStart();
            }
            if (this.c()) {
                httpsURLConnection = (HttpsURLConnection)new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
            }
            else {
                httpsURLConnection = (HttpsURLConnection)new URL(str).openConnection();
            }
            if (!com.umeng.commonsdk.statistics.internal.c.e) {
                httpsURLConnection.setHostnameVerifier((HostnameVerifier) SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                final SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, new SecureRandom());
                httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
                com.umeng.commonsdk.statistics.internal.c.e = true;
            }
            httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
            httpsURLConnection.setRequestProperty("X-Umeng-Sdk", com.umeng.commonsdk.statistics.internal.a.a(this.c).b());
            httpsURLConnection.setRequestProperty("Content-Type", com.umeng.commonsdk.statistics.internal.a.a(this.c).a());
            httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
            httpsURLConnection.setRequestProperty("X-Umeng-Pro-Ver", "1.0.0");
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setUseCaches(false);
            outputStream = httpsURLConnection.getOutputStream();
            outputStream.write(b);
            outputStream.flush();
            httpsURLConnection.connect();
            if (this.d != null) {
                this.d.onRequestEnd();
            }
            final int responseCode = httpsURLConnection.getResponseCode();
            final String headerField = httpsURLConnection.getHeaderField("Content-Type");
            boolean b2 = false;
            if (!TextUtils.isEmpty((CharSequence)headerField) && headerField.equalsIgnoreCase("application/thrift")) {
                b2 = true;
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i("status code : " + responseCode + "; isThrifit:" + b2);
            }
            if (responseCode == 200 && b2) {
                MLog.i("Send message to server. status code is: " + responseCode);
                UMRTLog.i("MobclickRT", "Send message to server. status code is: " + responseCode + "; url = " + str);
                final InputStream inputStream = httpsURLConnection.getInputStream();
                try {
                    return HelperUtils.readStreamToByteArray(inputStream);
                }
                finally {
                    HelperUtils.safeClose(inputStream);
                }
            }
        }
        catch (SSLHandshakeException ex2) {
            UMLog.aq("A_10201", 2, "\\|");
            return null;
        }
        catch (UnknownHostException ex3) {
            UMLog.aq("A_10200", 2, "\\|");
            return null;
        }
        catch (Throwable t) {
            return null;
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (Exception ex) {
                    UMCrashManager.reportCrash(this.c, ex);
                }
            }
            if (httpsURLConnection != null) {
                try {
                    httpsURLConnection.getInputStream().close();
                }
                catch (IOException ex4) {}
                httpsURLConnection.disconnect();
            }
        }
        return null;
    }
    
    static {
        com.umeng.commonsdk.statistics.internal.c.e = false;
    }
}
