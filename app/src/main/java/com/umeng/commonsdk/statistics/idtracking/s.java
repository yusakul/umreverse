package com.umeng.commonsdk.statistics.idtracking;

import android.text.*;
import com.umeng.commonsdk.statistics.internal.*;
import android.content.*;
import javax.net.ssl.*;
import org.apache.http.conn.ssl.*;
import java.net.*;
import java.io.*;

public class s extends a
{
    private static final String a = "uuid";
    private Context b;
    private String c;
    private String d;
    private static final String e = "yosuid";
    private static final String f = "23346339";
    
    public s(final Context b) {
        super("uuid");
        this.b = null;
        this.c = null;
        this.d = null;
        this.b = b;
        this.c = null;
        this.d = null;
    }
    
    @Override
    public String f() {
        try {
            if (!TextUtils.isEmpty((CharSequence)a("ro.yunos.version", "")) && this.b != null) {
                final SharedPreferences default1 = PreferenceWrapper.getDefault(this.b);
                if (default1 != null) {
                    final String string = default1.getString("yosuid", "");
                    if (TextUtils.isEmpty((CharSequence)string)) {
                        this.d = this.b("23346339");
                        if (!TextUtils.isEmpty((CharSequence)this.d) && this.b != null && default1 != null) {
                            final SharedPreferences.Editor edit = default1.edit();
                            if (edit != null) {
                                edit.putString("yosuid", this.d).commit();
                            }
                        }
                        return this.d;
                    }
                    return string;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private String b(final String s) {
        this.d = a("ro.yunos.openuuid", "");
        if (!TextUtils.isEmpty((CharSequence)this.d)) {
            return this.d;
        }
        this.c = a("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty((CharSequence)this.c)) {
            this.c = a("ro.sys.aliyun.clouduuid", "");
        }
        if (!TextUtils.isEmpty((CharSequence)this.c)) {
            HttpsURLConnection httpsURLConnection = null;
            InputStream inputStream = null;
            DataOutputStream dataOutputStream = null;
            BufferedReader bufferedReader = null;
            try {
                httpsURLConnection = (HttpsURLConnection)new URL("https://cmnsguider.yunos.com:443/genDeviceToken").openConnection();
                httpsURLConnection.setConnectTimeout(30000);
                httpsURLConnection.setReadTimeout(30000);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(final String s, final SSLSession sslSession) {
                        return new BrowserCompatHostnameVerifier().verify("cmnsguider.yunos.com", sslSession);
                    }
                });
                final String string = "appKey=" + URLEncoder.encode("23338940", "UTF-8") + "&uuid=" + URLEncoder.encode("FC1FE84794417B1BEF276234F6FB4E63", "UTF-8");
                dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                dataOutputStream.writeBytes(string);
                dataOutputStream.flush();
                if (httpsURLConnection.getResponseCode() == 200) {
                    try {
                        inputStream = httpsURLConnection.getInputStream();
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        final StringBuffer sb = new StringBuffer();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            sb.append(line);
                        }
                        if (sb != null) {
                            this.d = sb.toString();
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            catch (Exception ex2) {}
            finally {
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    }
                    catch (Exception ex3) {}
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    }
                    catch (Exception ex4) {}
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (Exception ex5) {}
                }
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
            }
        }
        return this.d;
    }
    
    public static String a(final String s, final String s2) {
        try {
            return (String)Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, s, s2);
        }
        catch (Exception ex) {
            return s2;
        }
    }
}
