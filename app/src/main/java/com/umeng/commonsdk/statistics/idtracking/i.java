package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;
import com.umeng.commonsdk.framework.*;
import android.text.*;
import java.io.*;
import com.umeng.commonsdk.statistics.common.*;

public class i extends a
{
    private static final String a = "oldumid";
    private Context b;
    private String c;
    private String d;
    
    public i(final Context b) {
        super("oldumid");
        this.c = null;
        this.d = null;
        this.b = b;
    }
    
    @Override
    public String f() {
        return this.c;
    }
    
    public boolean g() {
        return this.h();
    }
    
    public boolean h() {
        this.d = UMEnvelopeBuild.imprintProperty(this.b, "umid", null);
        if (!TextUtils.isEmpty((CharSequence)this.d)) {
            this.d = DataHelper.encryptBySHA1(this.d);
            final String file = HelperUtils.readFile(new File("/sdcard/Android/data/.um/sysid.dat"));
            final String file2 = HelperUtils.readFile(new File("/sdcard/Android/obj/.um/sysid.dat"));
            final String file3 = HelperUtils.readFile(new File("/data/local/tmp/.um/sysid.dat"));
            if (TextUtils.isEmpty((CharSequence)file)) {
                this.l();
            }
            else if (!this.d.equals(file)) {
                this.c = file;
                return true;
            }
            if (TextUtils.isEmpty((CharSequence)file2)) {
                this.k();
            }
            else if (!this.d.equals(file2)) {
                this.c = file2;
                return true;
            }
            if (TextUtils.isEmpty((CharSequence)file3)) {
                this.j();
            }
            else if (!this.d.equals(file3)) {
                this.c = file3;
                return true;
            }
        }
        return false;
    }
    
    public void i() {
        try {
            this.l();
            this.k();
            this.j();
        }
        catch (Exception ex) {}
    }
    
    private void j() {
        try {
            this.b("/data/local/tmp/.um");
            HelperUtils.writeFile(new File("/data/local/tmp/.um/sysid.dat"), this.d);
        }
        catch (Throwable t) {}
    }
    
    private void k() {
        try {
            this.b("/sdcard/Android/obj/.um");
            HelperUtils.writeFile(new File("/sdcard/Android/obj/.um/sysid.dat"), this.d);
        }
        catch (Throwable t) {}
    }
    
    private void l() {
        try {
            this.b("/sdcard/Android/data/.um");
            HelperUtils.writeFile(new File("/sdcard/Android/data/.um/sysid.dat"), this.d);
        }
        catch (Throwable t) {}
    }
    
    private void b(final String pathname) {
        final File file = new File(pathname);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
