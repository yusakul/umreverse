package com.umeng.commonsdk.statistics.noise;

import com.umeng.commonsdk.statistics.internal.*;
import android.content.*;
import com.umeng.commonsdk.framework.*;
import android.text.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.statistics.idtracking.*;

public class ABTest implements com.umeng.commonsdk.statistics.internal.d
{
    private boolean isInTest;
    private int mPolicy;
    private int mInterval;
    private int mGroup;
    private float mProb13;
    private float mProb07;
    private String mPoli;
    private Context context;
    private static ABTest instance;
    
    public static synchronized ABTest getService(final Context context) {
        if (ABTest.instance == null) {
            ABTest.instance = new ABTest(context, UMEnvelopeBuild.imprintProperty(context, "client_test", null), Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "test_report_interval", "0")));
        }
        return ABTest.instance;
    }
    
    private ABTest(final Context context, final String s, final int n) {
        this.isInTest = false;
        this.mPolicy = -1;
        this.mInterval = -1;
        this.mGroup = -1;
        this.mProb13 = 0.0f;
        this.mProb07 = 0.0f;
        this.mPoli = null;
        this.context = null;
        this.context = context;
        this.onExperimentChanged(s, n);
    }
    
    private float prob(final String s, int beginIndex) {
        beginIndex *= 2;
        if (s == null) {
            return 0.0f;
        }
        return Integer.valueOf(s.substring(beginIndex, beginIndex + 5), 16) / 1048576.0f;
    }
    
    public void onExperimentChanged(final String str, final int mInterval) {
        this.mInterval = mInterval;
        final String signature = Envelope.getSignature(this.context);
        if (TextUtils.isEmpty((CharSequence)signature) || TextUtils.isEmpty((CharSequence)str)) {
            this.isInTest = false;
            return;
        }
        try {
            this.mProb13 = this.prob(signature, 12);
            this.mProb07 = this.prob(signature, 6);
            if (str.startsWith("SIG7")) {
                this.parseSig7(str);
            }
            else if (str.startsWith("FIXED")) {
                this.parseFIXED(str);
            }
        }
        catch (Exception ex) {
            this.isInTest = false;
            MLog.e("v:" + str, ex);
        }
    }
    
    public static boolean validate(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return false;
        }
        final String[] split = s.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (split[0].startsWith("SIG7") && split[1].split(",").length == split[5].split(",").length) {
            return true;
        }
        if (split[0].startsWith("FIXED")) {
            final int length = split[5].split(",").length;
            final int int1 = Integer.parseInt(split[1]);
            if (length >= int1 && int1 >= 1) {
                return true;
            }
        }
        return false;
    }
    
    private void parseSig7(final String s) {
        if (s == null) {
            return;
        }
        final String[] split = s.split("\\|");
        float floatValue = 0.0f;
        if (split[2].equals("SIG13")) {
            floatValue = Float.valueOf(split[3]);
        }
        if (this.mProb13 > floatValue) {
            this.isInTest = false;
            return;
        }
        float[] array = null;
        if (split[0].equals("SIG7")) {
            final String[] split2 = split[1].split(",");
            array = new float[split2.length];
            for (int i = 0; i < split2.length; ++i) {
                array[i] = Float.valueOf(split2[i]);
            }
        }
        int[] array2 = null;
        if (split[4].equals("RPT")) {
            this.mPoli = "RPT";
            final String[] split3 = split[5].split(",");
            array2 = new int[split3.length];
            for (int j = 0; j < split3.length; ++j) {
                array2[j] = Integer.valueOf(split3[j]);
            }
        }
        else if (split[4].equals("DOM")) {
            this.isInTest = true;
            this.mPoli = "DOM";
            try {
                final String[] split4 = split[5].split(",");
                array2 = new int[split4.length];
                for (int k = 0; k < split4.length; ++k) {
                    array2[k] = Integer.valueOf(split4[k]);
                }
            }
            catch (Exception ex) {}
        }
        int n = -1;
        float n2 = 0.0f;
        for (int l = 0; l < array.length; ++l) {
            n2 += array[l];
            if (this.mProb07 < n2) {
                n = l;
                break;
            }
        }
        if (n != -1) {
            this.isInTest = true;
            this.mGroup = n + 1;
            if (array2 != null) {
                this.mPolicy = array2[n];
            }
        }
        else {
            this.isInTest = false;
        }
    }
    
    private void parseFIXED(final String s) {
        if (s == null) {
            return;
        }
        final String[] split = s.split("\\|");
        float floatValue = 0.0f;
        if (split[2].equals("SIG13")) {
            floatValue = Float.valueOf(split[3]);
        }
        if (this.mProb13 > floatValue) {
            this.isInTest = false;
            return;
        }
        int intValue = -1;
        if (split[0].equals("FIXED")) {
            intValue = Integer.valueOf(split[1]);
        }
        int[] array = null;
        if (split[4].equals("RPT")) {
            this.mPoli = "RPT";
            final String[] split2 = split[5].split(",");
            array = new int[split2.length];
            for (int i = 0; i < split2.length; ++i) {
                array[i] = Integer.valueOf(split2[i]);
            }
        }
        else if (split[4].equals("DOM")) {
            this.mPoli = "DOM";
            this.isInTest = true;
            try {
                final String[] split3 = split[5].split(",");
                array = new int[split3.length];
                for (int j = 0; j < split3.length; ++j) {
                    array[j] = Integer.valueOf(split3[j]);
                }
            }
            catch (Exception ex) {}
        }
        if (intValue != -1) {
            this.isInTest = true;
            this.mGroup = intValue;
            if (array != null) {
                this.mPolicy = array[intValue - 1];
            }
        }
        else {
            this.isInTest = false;
        }
    }
    
    public boolean isInTest() {
        return this.isInTest;
    }
    
    public int getTestPolicy() {
        return this.mPolicy;
    }
    
    public int getTestInterval() {
        return this.mInterval;
    }
    
    public int getGroup() {
        return this.mGroup;
    }
    
    public String getGroupInfo() {
        if (!this.isInTest) {
            return "error";
        }
        return String.valueOf(this.mGroup);
    }
    
    public String getTestName() {
        return this.mPoli;
    }
    
    @Override
    public void onImprintChanged(final ImprintHandler.a a) {
        this.onExperimentChanged(a.a("client_test", null), Integer.valueOf(a.a("test_report_interval", "0")));
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" p13:");
        sb.append(this.mProb13);
        sb.append(" p07:");
        sb.append(this.mProb07);
        sb.append(" policy:");
        sb.append(this.mPolicy);
        sb.append(" interval:");
        sb.append(this.mInterval);
        return sb.toString();
    }
    
    static {
        ABTest.instance = null;
    }
}
