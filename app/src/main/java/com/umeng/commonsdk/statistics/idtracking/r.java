package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;
import java.io.*;
import java.util.regex.*;
import com.umeng.commonsdk.statistics.common.*;
import android.os.*;

public class r extends a
{
    private static final String a = "utdid";
    private static final String b = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final Pattern c;
    private Context d;
    
    public r(final Context d) {
        super("utdid");
        this.d = d;
    }
    
    @Override
    public String f() {
        try {
            return (String)Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, this.d);
        }
        catch (Exception ex) {
            return this.g();
        }
    }
    
    private String g() {
        final File h = this.h();
        if (h == null || !h.exists()) {
            return null;
        }
        try {
            final FileInputStream fileInputStream = new FileInputStream(h);
            try {
                return this.b(HelperUtils.readStreamToString(fileInputStream));
            }
            finally {
                HelperUtils.safeClose(fileInputStream);
            }
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private String b(final String input) {
        if (input == null) {
            return null;
        }
        final Matcher matcher = r.c.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
    
    private File h() {
        if (!DeviceConfig.checkPermission(this.d, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return null;
        }
        if (Environment.getExternalStorageState().equals("mounted")) {
            final File externalStorageDirectory = Environment.getExternalStorageDirectory();
            try {
                return new File(externalStorageDirectory.getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    static {
        c = Pattern.compile("UTDID\">([^<]+)");
    }
}
