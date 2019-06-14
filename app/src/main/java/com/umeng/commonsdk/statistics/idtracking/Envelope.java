package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.internal.*;
import android.content.*;
import com.umeng.commonsdk.internal.crash.*;
import com.umeng.commonsdk.proguard.*;
import com.umeng.commonsdk.framework.*;
import org.json.*;
import java.io.*;
import com.umeng.commonsdk.statistics.common.*;
import com.umeng.commonsdk.utils.*;

public class Envelope
{
    private final byte[] SEED;
    private final int CODEX_ENCRYPT = 1;
    private final int CODEX_NORMAL = 0;
    private String mVersion;
    private String mAddress;
    private byte[] mSignature;
    private byte[] mGuid;
    private byte[] mChecksum;
    private int mSerialNo;
    private int mTimestamp;
    private int mLength;
    private byte[] mEntity;
    private byte[] identity;
    private boolean encrypt;
    
    private Envelope(final byte[] array, final String mAddress, final byte[] identity) throws Exception {
        this.SEED = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        this.mVersion = "1.0";
        this.mAddress = null;
        this.mSignature = null;
        this.mGuid = null;
        this.mChecksum = null;
        this.mSerialNo = 0;
        this.mTimestamp = 0;
        this.mLength = 0;
        this.mEntity = null;
        this.identity = null;
        this.encrypt = false;
        if (array == null || array.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.mAddress = mAddress;
        this.mLength = array.length;
        this.mEntity = com.umeng.commonsdk.statistics.common.b.a(array);
        this.mTimestamp = (int)(System.currentTimeMillis() / 1000L);
        this.identity = identity;
    }
    
    public static String getSignature(final Context context) {
        final SharedPreferences default1 = PreferenceWrapper.getDefault(context);
        if (default1 == null) {
            return null;
        }
        return default1.getString("signature", (String)null);
    }
    
    public void setSignature(final String s) {
        this.mSignature = DataHelper.reverseHexString(s);
    }
    
    public String getSignature() {
        return DataHelper.toHexString(this.mSignature);
    }
    
    public void setSerialNumber(final int mSerialNo) {
        this.mSerialNo = mSerialNo;
    }
    
    public void setEncrypt(final boolean encrypt) {
        this.encrypt = encrypt;
    }
    
    public static Envelope genEnvelope(final Context context, final String s, final byte[] array) {
        try {
            final String mac = DeviceConfig.getMac(context);
            final String deviceId = DeviceConfig.getDeviceId(context);
            final SharedPreferences default1 = PreferenceWrapper.getDefault(context);
            final String string = default1.getString("signature", (String)null);
            final int int1 = default1.getInt("serial", 1);
            final Envelope envelope = new Envelope(array, s, (deviceId + mac).getBytes());
            envelope.setSignature(string);
            envelope.setSerialNumber(int1);
            envelope.seal();
            default1.edit().putInt("serial", int1 + 1).putString("signature", envelope.getSignature()).commit();
            envelope.export(context);
            return envelope;
        }
        catch (Exception ex) {
            UMCrashManager.reportCrash(context, ex);
            return null;
        }
    }
    
    public static Envelope genEncryptEnvelope(final Context context, final String s, final byte[] array) {
        try {
            final String mac = DeviceConfig.getMac(context);
            final String deviceId = DeviceConfig.getDeviceId(context);
            final SharedPreferences default1 = PreferenceWrapper.getDefault(context);
            final String string = default1.getString("signature", (String)null);
            final int int1 = default1.getInt("serial", 1);
            final Envelope envelope = new Envelope(array, s, (deviceId + mac).getBytes());
            envelope.setEncrypt(true);
            envelope.setSignature(string);
            envelope.setSerialNumber(int1);
            envelope.seal();
            default1.edit().putInt("serial", int1 + 1).putString("signature", envelope.getSignature()).commit();
            envelope.export(context);
            return envelope;
        }
        catch (Exception ex) {
            UMCrashManager.reportCrash(context, ex);
            return null;
        }
    }
    
    public void seal() {
        if (this.mSignature == null) {
            this.mSignature = this.genSignature();
        }
        if (this.encrypt) {
            final byte[] array = new byte[16];
            try {
                System.arraycopy(this.mSignature, 1, array, 0, 16);
                this.mEntity = DataHelper.encrypt(this.mEntity, array);
            }
            catch (Exception ex) {}
        }
        this.mGuid = this.genGuid(this.mSignature, this.mTimestamp);
        this.mChecksum = this.genCheckSum();
    }
    
    private byte[] genGuid(final byte[] array, final int n) {
        final byte[] hash = DataHelper.hash(this.identity);
        final byte[] hash2 = DataHelper.hash(this.mEntity);
        final int length = hash.length;
        final byte[] array2 = new byte[length * 2];
        for (int i = 0; i < length; ++i) {
            array2[i * 2] = hash2[i];
            array2[i * 2 + 1] = hash[i];
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
    
    private byte[] genSignature() {
        return this.genGuid(this.SEED, (int)(System.currentTimeMillis() / 1000L));
    }
    
    private byte[] genCheckSum() {
        final StringBuilder sb = new StringBuilder();
        sb.append(DataHelper.toHexString(this.mSignature));
        sb.append(this.mSerialNo);
        sb.append(this.mTimestamp);
        sb.append(this.mLength);
        sb.append(DataHelper.toHexString(this.mGuid));
        return DataHelper.hash(sb.toString().getBytes());
    }
    
    public byte[] toBinary() {
        final com.umeng.commonsdk.proguard.f f = new com.umeng.commonsdk.proguard.f();
        f.a(this.mVersion);
        f.b(this.mAddress);
        f.c(DataHelper.toHexString(this.mSignature));
        f.a(this.mSerialNo);
        f.b(this.mTimestamp);
        f.c(this.mLength);
        f.a(this.mEntity);
        f.d(this.encrypt ? 1 : 0);
        f.d(DataHelper.toHexString(this.mGuid));
        f.e(DataHelper.toHexString(this.mChecksum));
        try {
            return new com.umeng.commonsdk.proguard.s().a(f);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void export(final Context context) {
        final String mAddress = this.mAddress;
        final String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "umid", null);
        final String hexString = DataHelper.toHexString(this.mSignature);
        final byte[] array = new byte[16];
        System.arraycopy(this.mSignature, 2, array, 0, 16);
        final String hexString2 = DataHelper.toHexString(DataHelper.hash(array));
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("appkey", (Object)mAddress);
            if (imprintProperty != null) {
                jsonObject.put("umid", (Object)imprintProperty);
            }
            jsonObject.put("signature", (Object)hexString);
            jsonObject.put("checksum", (Object)hexString2);
            final File parent = new File(context.getFilesDir(), ".umeng");
            if (!parent.exists()) {
                parent.mkdir();
            }
            HelperUtils.writeFile(new File(parent, "exchangeIdentity.json"), jsonObject.toString());
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        try {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("appkey", (Object)mAddress);
            jsonObject2.put("channel", (Object)UMUtils.getChannel(context));
            if (imprintProperty != null) {
                jsonObject2.put("umid", (Object)HelperUtils.getUmengMD5(imprintProperty));
            }
            HelperUtils.writeFile(new File(context.getFilesDir(), "exid.dat"), jsonObject2.toString());
        }
        catch (Throwable t2) {
            t2.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("version : %s\n", this.mVersion));
        sb.append(String.format("address : %s\n", this.mAddress));
        sb.append(String.format("signature : %s\n", DataHelper.toHexString(this.mSignature)));
        sb.append(String.format("serial : %s\n", this.mSerialNo));
        sb.append(String.format("timestamp : %serial_num\n", this.mTimestamp));
        sb.append(String.format("length : %serial_num\n", this.mLength));
        sb.append(String.format("guid : %s\n", DataHelper.toHexString(this.mGuid)));
        sb.append(String.format("checksum : %s ", DataHelper.toHexString(this.mChecksum)));
        sb.append(String.format("codex : %serial_num", this.encrypt ? 1 : 0));
        return sb.toString();
    }
}
