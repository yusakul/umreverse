package com.umeng.commonsdk.stateless;

import android.content.*;
import com.umeng.commonsdk.framework.*;

import android.content.pm.PackageManager;
import android.text.*;
import com.umeng.commonsdk.internal.crash.*;
import android.net.*;
import com.umeng.commonsdk.statistics.*;
import org.apache.http.conn.ssl.*;
import org.apache.http.conn.ssl.SSLSocketFactory;

import java.net.Proxy;
import java.security.*;
import com.umeng.commonsdk.debug.*;
import com.umeng.commonsdk.statistics.common.*;
import javax.net.ssl.*;
import java.io.*;
import java.net.*;

public class e
{
    private String a;
    private int b;
    private Context c;
    
    public e(final Context c) {
        this.a = "10.0.0.172";
        this.b = 80;
        this.c = c;
    }
    
    private void a() {
        final String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "sl_domain_p", "");
        if (!TextUtils.isEmpty((CharSequence)imprintProperty)) {
            com.umeng.commonsdk.stateless.a.g = DataHelper.assembleStatelessURL(imprintProperty);
        }
    }
    
    private void b() {
        final String imprintProperty = UMEnvelopeBuild.imprintProperty(this.c, "sl_domain_p", "");
        final String imprintProperty2 = UMEnvelopeBuild.imprintProperty(this.c, "oversea_sl_domain_p", "");
        if (!TextUtils.isEmpty((CharSequence)imprintProperty)) {
            com.umeng.commonsdk.stateless.a.f = DataHelper.assembleStatelessURL(imprintProperty);
        }
        if (!TextUtils.isEmpty((CharSequence)imprintProperty2)) {
            com.umeng.commonsdk.stateless.a.h = DataHelper.assembleStatelessURL(imprintProperty2);
        }
        com.umeng.commonsdk.stateless.a.g = com.umeng.commonsdk.stateless.a.h;
        if (!TextUtils.isEmpty((CharSequence)com.umeng.commonsdk.statistics.b.b) && (com.umeng.commonsdk.statistics.b.b.startsWith("460") || com.umeng.commonsdk.statistics.b.b.startsWith("461"))) {
            com.umeng.commonsdk.stateless.a.g = com.umeng.commonsdk.stateless.a.f;
        }
    }
    
    private boolean c() {
        if (this.c != null) {
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
        }
        return false;
    }
    
    public boolean a(final byte[] b, final String str) {
        if (b == null || str == null) {
            ULog.i("walle", "[stateless] sendMessage, envelopeByte == null || path == null ");
            return false;
        }
        if (SdkVersion.SDK_TYPE == 0) {
            this.a();
        }
        else {
            com.umeng.commonsdk.stateless.a.f = com.umeng.commonsdk.stateless.a.h;
            this.b();
        }
        boolean b2 = false;
        HttpsURLConnection httpsURLConnection = null;
        OutputStream outputStream = null;
        try {
            if (this.c()) {
                httpsURLConnection = (HttpsURLConnection)new URL(com.umeng.commonsdk.stateless.a.g + "/" + str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
            }
            else {
                httpsURLConnection = (HttpsURLConnection)new URL(com.umeng.commonsdk.stateless.a.g + "/" + str).openConnection();
            }
            httpsURLConnection.setHostnameVerifier((HostnameVerifier) SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            final SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, null, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
            httpsURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
            httpsURLConnection.setRequestProperty("Msg-Type", "envelope/json");
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
            if (httpsURLConnection.getResponseCode() == 200) {
                UMRTLog.i("MobclickRT", "--->>> send stateless message success : " + com.umeng.commonsdk.stateless.a.g + "/" + str);
                b2 = true;
            }
        }
        catch (SSLHandshakeException ex) {
            MLog.e("SSLHandshakeException, Failed to send message.", ex);
        }
        catch (Throwable t) {
            MLog.e("Exception,Failed to send message.", t);
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (Exception ex2) {}
            }
            if (httpsURLConnection != null) {
                try {
                    httpsURLConnection.getInputStream().close();
                }
                catch (IOException ex3) {}
                httpsURLConnection.disconnect();
            }
        }
        return b2;
    }
    
    public boolean b(final byte[] b, final String s) {
        if (b == null || s == null) {
            return false;
        }
        boolean b2 = false;
        HttpURLConnection httpURLConnection = null;
        OutputStream outputStream = null;
        try {
            if (this.c()) {
                httpURLConnection = (HttpURLConnection)new URL(com.umeng.commonsdk.stateless.a.g + "/" + s).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.a, this.b)));
            }
            else {
                httpURLConnection = (HttpURLConnection)new URL(com.umeng.commonsdk.stateless.a.g + "/" + s).openConnection();
            }
            httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
            httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            outputStream = httpURLConnection.getOutputStream();
            outputStream.write(b);
            outputStream.flush();
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                b2 = true;
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(this.c, t);
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (Exception ex) {}
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return b2;
    }
}
