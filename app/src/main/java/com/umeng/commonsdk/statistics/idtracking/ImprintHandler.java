package com.umeng.commonsdk.statistics.idtracking;

import android.content.*;

import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.statistics.proto.d;
import com.umeng.commonsdk.utils.*;
import com.umeng.commonsdk.statistics.internal.*;
import com.umeng.commonsdk.internal.crash.*;
import android.text.*;
import com.umeng.commonsdk.debug.*;
import com.umeng.commonsdk.statistics.proto.*;
import java.nio.*;
import com.umeng.commonsdk.statistics.*;
import com.umeng.commonsdk.statistics.common.*;
import java.util.*;
import com.umeng.commonsdk.proguard.*;
import java.io.*;
import android.util.*;

public class ImprintHandler implements FileLockCallback
{
    private static final String a = "ImprintHandler";
    private static Object b;
    private static final String c = ".imprint";
    private static final byte[] d;
    private com.umeng.commonsdk.statistics.internal.d e;
    private static Map<String, ArrayList<UMImprintChangeCallback>> f;
    private static Object g;
    private a h;
    private com.umeng.commonsdk.statistics.proto.d i;
    private static ImprintHandler j;
    private static Context k;
    private static FileLockUtil l;
    private static final int m = 0;
    private static final int n = 1;
    private static Map<String, UMImprintPreProcessCallback> o;
    private static Object p;
    
    @Override
    public boolean onFileLock(final String s) {
        return false;
    }
    
    @Override
    public boolean onFileLock(final String s, final Object o) {
        return false;
    }
    
    @Override
    public boolean onFileLock(final File file, final int n) {
        if (n == 0) {
            ImprintHandler.j.e();
        }
        else if (n == 1) {
            ImprintHandler.j.a(file);
        }
        return true;
    }
    
    private ImprintHandler(final Context context) {
        this.h = new a();
        this.i = null;
        ImprintHandler.k = context.getApplicationContext();
    }
    
    public static synchronized ImprintHandler getImprintService(final Context context) {
        if (ImprintHandler.j == null) {
            ImprintHandler.j = new ImprintHandler(context);
            ImprintHandler.l = new FileLockUtil();
            if (ImprintHandler.l != null) {
                ImprintHandler.l.doFileOperateion(new File(ImprintHandler.k.getFilesDir(), ".imprint"), ImprintHandler.j, 0);
            }
        }
        return ImprintHandler.j;
    }
    
    private static void a(final String s, final UMImprintChangeCallback umImprintChangeCallback) {
        synchronized (ImprintHandler.g) {
            try {
                if (ImprintHandler.f.containsKey(s)) {
                    final ArrayList<UMImprintChangeCallback> list = ImprintHandler.f.get(s);
                    final int size = list.size();
                    ULog.i("--->>> addCallback: before add: callbacks size is: " + size);
                    for (int i = 0; i < size; ++i) {
                        if (umImprintChangeCallback == list.get(i)) {
                            ULog.i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                    }
                    list.add(umImprintChangeCallback);
                    ULog.i("--->>> addCallback: after add: callbacks size is: " + list.size());
                }
                else {
                    final ArrayList<UMImprintChangeCallback> list2 = new ArrayList<UMImprintChangeCallback>();
                    final int size2 = list2.size();
                    ULog.i("--->>> addCallback: before add: callbacks size is: " + size2);
                    for (int j = 0; j < size2; ++j) {
                        if (umImprintChangeCallback == list2.get(j)) {
                            ULog.i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                    }
                    list2.add(umImprintChangeCallback);
                    ULog.i("--->>> addCallback: after add: callbacks size is: " + list2.size());
                    ImprintHandler.f.put(s, list2);
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(ImprintHandler.k, t);
            }
        }
    }
    
    private static void b(final String str, final UMImprintChangeCallback umImprintChangeCallback) {
        if (TextUtils.isEmpty((CharSequence)str) || umImprintChangeCallback == null) {
            return;
        }
        synchronized (ImprintHandler.g) {
            try {
                if (ImprintHandler.f.containsKey(str)) {
                    final ArrayList<UMImprintChangeCallback> list = ImprintHandler.f.get(str);
                    if (umImprintChangeCallback != null && list.size() > 0) {
                        final int size = list.size();
                        ULog.i("--->>> removeCallback: before remove: callbacks size is: " + size);
                        for (int i = 0; i < size; ++i) {
                            if (umImprintChangeCallback == list.get(i)) {
                                ULog.i("--->>> removeCallback: remove index " + i);
                                list.remove(i);
                                break;
                            }
                        }
                        ULog.i("--->>> removeCallback: after remove: callbacks size is: " + list.size());
                        if (list.size() == 0) {
                            ULog.i("--->>> removeCallback: remove key from map: key = " + str);
                            ImprintHandler.f.remove(str);
                        }
                    }
                }
            }
            catch (Throwable t) {
                UMCrashManager.reportCrash(ImprintHandler.k, t);
            }
        }
    }
    
    public void registImprintCallback(final String s, final UMImprintChangeCallback umImprintChangeCallback) {
        if (!TextUtils.isEmpty((CharSequence)s) && umImprintChangeCallback != null) {
            a(s, umImprintChangeCallback);
        }
    }
    
    public void unregistImprintCallback(final String s, final UMImprintChangeCallback umImprintChangeCallback) {
        if (!TextUtils.isEmpty((CharSequence)s) && umImprintChangeCallback != null) {
            b(s, umImprintChangeCallback);
        }
    }
    
    public void registPreProcessCallback(final String s, final UMImprintPreProcessCallback umImprintPreProcessCallback) {
        if (!TextUtils.isEmpty((CharSequence)s) && umImprintPreProcessCallback != null) {
            synchronized (ImprintHandler.p) {
                try {
                    if (!ImprintHandler.o.containsKey(s)) {
                        ImprintHandler.o.put(s, umImprintPreProcessCallback);
                        UMRTLog.i("MobclickRT", "--->>> registPreProcessCallback: key : " + s + " regist success.");
                    }
                    else {
                        UMRTLog.i("MobclickRT", "--->>> key : " + s + " PreProcesser has registed!");
                    }
                }
                catch (Throwable t) {
                    UMCrashManager.reportCrash(ImprintHandler.k, t);
                }
            }
        }
    }
    
    public void a(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            synchronized (ImprintHandler.p) {
                try {
                    if (ImprintHandler.o.containsKey(s)) {
                        UMRTLog.i("MobclickRT", "--->>> unregistPreProcessCallback: unregist [" + s + "] success.");
                        ImprintHandler.f.remove(s);
                    }
                    else {
                        UMRTLog.i("MobclickRT", "--->>> unregistPreProcessCallback: can't find [" + s + "], pls regist first.");
                    }
                }
                catch (Throwable t) {
                    UMCrashManager.reportCrash(ImprintHandler.k, t);
                }
            }
        }
    }
    
    public void a(final com.umeng.commonsdk.statistics.internal.d e) {
        this.e = e;
    }
    
    public String a(final com.umeng.commonsdk.statistics.proto.d d) {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<String, Object> entry : new TreeMap<String, Object>(d.c()).entrySet()) {
            sb.append(entry.getKey());
            if (((com.umeng.commonsdk.statistics.proto.e)entry.getValue()).d()) {
                sb.append(((com.umeng.commonsdk.statistics.proto.e)entry.getValue()).b());
            }
            sb.append(((com.umeng.commonsdk.statistics.proto.e)entry.getValue()).e());
            sb.append(((com.umeng.commonsdk.statistics.proto.e)entry.getValue()).h());
        }
        sb.append(d.b);
        return HelperUtils.MD5(sb.toString()).toLowerCase(Locale.US);
    }
    
    private boolean c(final com.umeng.commonsdk.statistics.proto.d d) {
        if (!d.i().equals(this.a(d))) {
            return false;
        }
        for (final Object index : d.c().values()) {
            final byte[] reverseHexString = DataHelper.reverseHexString(((com.umeng.commonsdk.statistics.proto.e)index).h());
            final byte[] a = this.a(((com.umeng.commonsdk.statistics.proto.e)index));
            for (int i = 0; i < 4; ++i) {
                if (reverseHexString[i] != a[i]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public byte[] a(final com.umeng.commonsdk.statistics.proto.e  e) {
        final ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(e.e());
        final byte[] array = allocate.array();
        final byte[] d = ImprintHandler.d;
        final byte[] array2 = new byte[4];
        for (int i = 0; i < 4; ++i) {
            array2[i] = (byte)(array[i] ^ d[i]);
        }
        return array2;
    }
    
    public byte[] a() {
        try {
            synchronized (this) {
                if (this.i == null) {
                    return null;
                }
                return new com.umeng.commonsdk.proguard.s().a(this.i);
            }
        }
        catch (Throwable t) {
            UMCrashManager.reportCrash(ImprintHandler.k, t);
            return null;
        }
    }
    
    public void b(final com.umeng.commonsdk.statistics.proto.d d) {
        if (d == null) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.d("Imprint is null");
            }
            return;
        }
        if (!this.c(d)) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.e("Imprint is not valid");
            }
            return;
        }
        if (AnalyticsConstants.UM_DEBUG) {
            MLog.d("Imprint is ok");
        }
        boolean b = false;
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        synchronized (this) {
            final com.umeng.commonsdk.statistics.proto.d i = this.i;
            final String s = (i == null) ? null : i.i();
            com.umeng.commonsdk.statistics.proto.d j;
            if (i == null) {
                j = this.d(d);
            }
            else {
                j = this.a(i, d, hashMap);
            }
            this.i = j;
            if (!this.a(s, (j == null) ? null : j.i())) {
                b = true;
            }
        }
        if (this.i != null) {
            if (AnalyticsConstants.UM_DEBUG) {}
            if (b) {
                this.h.a(this.i);
                if (this.e != null) {
                    this.e.onImprintChanged(this.h);
                }
            }
        }
        if (hashMap.size() > 0) {
            synchronized (ImprintHandler.g) {
                for (final Map.Entry entry : hashMap.entrySet()) {
                    final Object str = entry.getKey();
                    final Object str2 = (String)entry.getValue();
                    if (!TextUtils.isEmpty((CharSequence)str) && ImprintHandler.f.containsKey(str)) {
                        ULog.i("--->>> target imprint key is: " + str + "; value is: " + str2);
                        final ArrayList<UMImprintChangeCallback> list = ImprintHandler.f.get(str);
                        if (list == null) {
                            continue;
                        }
                        for (int k = 0; k < list.size(); ++k) {
                            list.get(k).onImprintValueChanged((String)str, (String)str2);
                        }
                    }
                }
            }
        }
    }
    
    private boolean a(final String s, final String anObject) {
        if (s == null) {
            return anObject == null;
        }
        return s.equals(anObject);
    }

    private com.umeng.commonsdk.statistics.proto.d a(com.umeng.commonsdk.statistics.proto.d dClassVar, com.umeng.commonsdk.statistics.proto.d dClassVar2, Map<String, String> map) {
        if (dClassVar2 == null) {
            return dClassVar;
        }
        Map c = dClassVar.c();
        for (Object entry : dClassVar2.c().entrySet()) {
            int i = 0;
            if (((com.umeng.commonsdk.statistics.proto.e) ((Map.Entry)entry).getValue()).d()) {
                String str = (String) ((Map.Entry)entry).getKey();
                String str2 = ((com.umeng.commonsdk.statistics.proto.e) ((Map.Entry)entry) .getValue()).a;
                synchronized (p) {
                    if (!TextUtils.isEmpty(str) && o.containsKey(str)) {
                        UMImprintPreProcessCallback uMImprintPreProcessCallback = (UMImprintPreProcessCallback) o.get(str);
                        if (uMImprintPreProcessCallback != null && uMImprintPreProcessCallback.onPreProcessImprintKey(str, str2)) {
                            i = 1;
                        }
                    }
                }
                if (i == 0) {
                    c.put(((Map.Entry)entry).getKey(), ((Map.Entry)entry).getValue());
                    synchronized (g) {
                        if (!(TextUtils.isEmpty(str) || !f.containsKey(str) || ((ArrayList) f.get(str)) == null)) {
                            map.put(str, str2);
                        }
                    }
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("--->>> merge: [");
                    stringBuilder.append(str);
                    stringBuilder.append("] should be ignored.");
                    UMRTLog.i(UMRTLog.RTLOG_TAG, stringBuilder.toString());
                }
            } else {
                String str3 = (String) ((Map.Entry)entry).getKey();
                synchronized (g) {
                    if (!TextUtils.isEmpty(str3) && f.containsKey(str3)) {
                        ArrayList arrayList = (ArrayList) f.get(str3);
                        if (arrayList != null) {
                            while (i < arrayList.size()) {
                                ((UMImprintChangeCallback) arrayList.get(i)).onImprintValueChanged(str3, null);
                                i++;
                            }
                        }
                    }
                }
                c.remove(str3);
                this.h.a(str3);
            }
        }
        dClassVar.a(dClassVar2.f());
        dClassVar.a(a(dClassVar));
        return dClassVar;
    }

    private com.umeng.commonsdk.statistics.proto.d d(com.umeng.commonsdk.statistics.proto.d dClassVar) {
        Map c = dClassVar.c();
        ArrayList<String> arrayList = new ArrayList(c.size() / 2);
        Iterator it = c.entrySet().iterator();
        while (true) {
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (((com.umeng.commonsdk.statistics.proto.e) entry.getValue()).d()) {
                    Object obj;
                    String str = (String) entry.getKey();
                    String str2 = ((com.umeng.commonsdk.statistics.proto.e) entry.getValue()).a;
                    synchronized (p) {
                        if (!TextUtils.isEmpty(str) && o.containsKey(str)) {
                            UMImprintPreProcessCallback uMImprintPreProcessCallback = (UMImprintPreProcessCallback) o.get(str);
                            if (uMImprintPreProcessCallback != null && uMImprintPreProcessCallback.onPreProcessImprintKey(str, str2)) {
                                obj = 1;
                            }
                        }
                        obj = null;
                    }
                    if (obj != null) {
                        arrayList.add(str);
                    }
                    synchronized (ImprintHandler.g) {
                        if (!TextUtils.isEmpty(str) && f.containsKey(str)) {
                            ArrayList arrayList2 = (ArrayList) f.get(str);
                            if (arrayList2 != null) {
                                for (int i = 0; i < arrayList2.size(); i++) {
                                    ((UMImprintChangeCallback) arrayList2.get(i)).onImprintValueChanged(str, str2);
                                }
                            }
                        }
                    }
                } else {
                    arrayList.add((String)(((Map.Entry)entry).getKey()));
                }
            } else {
                for (String str3 : arrayList) {
                    synchronized (g) {
                        if (!TextUtils.isEmpty(str3) && f.containsKey(str3)) {
                            ArrayList arrayList3 = (ArrayList) f.get(str3);
                            if (arrayList3 != null) {
                                for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                                    ((UMImprintChangeCallback) arrayList3.get(i2)).onImprintValueChanged(str3, null);
                                }
                            }
                        }
                    }
                    c.remove(str3);
                }
                return dClassVar;
            }
        }
    }

    public synchronized com.umeng.commonsdk.statistics.proto.d b() {
        return this.i;
    }
    
    public a c() {
        return this.h;
    }

    private void e() {
        final File file = new File(ImprintHandler.k.getFilesDir(), ".imprint");
        synchronized (ImprintHandler.b) {
            if (!file.exists()) {
                return;
            }
            InputStream openFileInput = null;
            byte[] streamToByteArray = null;
            try {
                openFileInput = ImprintHandler.k.openFileInput(".imprint");
                streamToByteArray = HelperUtils.readStreamToByteArray(openFileInput);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                HelperUtils.safeClose(openFileInput);
            }
            if (streamToByteArray != null) {
                try {
                    final com.umeng.commonsdk.statistics.proto.d i = new d();
                    new com.umeng.commonsdk.proguard.m().a(i, streamToByteArray);
                    this.i = i;
                    this.h.a(i);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }

    private void a(File file) {
        if (this.i != null) {
            try {
                synchronized (b) {
                    byte[] a = new s().a(this.i);
                    OutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(a);
                        fileOutputStream.flush();
                    } finally {
                        HelperUtils.safeClose(fileOutputStream);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void d() {
        if (this.i == null) {
            return;
        }
        if (ImprintHandler.l != null) {
            final File file = new File(ImprintHandler.k.getFilesDir(), ".imprint");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                }
                catch (IOException ex2) {
                    try {
                        file.createNewFile();
                    }
                    catch (IOException ex) {
                        UMCrashManager.reportCrash(ImprintHandler.k, ex);
                    }
                }
            }
            ImprintHandler.l.doFileOperateion(file, ImprintHandler.j, 1);
        }
    }
    
    static {
        ImprintHandler.b = new Object();
        d = "pbl0".getBytes();
        ImprintHandler.f = new HashMap<String, ArrayList<UMImprintChangeCallback>>();
        ImprintHandler.g = new Object();
        ImprintHandler.j = null;
        ImprintHandler.o = new HashMap<String, UMImprintPreProcessCallback>();
        ImprintHandler.p = new Object();
    }
    
    public static class a
    {
        private Map<String, String> a;
        
        a() {
            this.a = new HashMap<String, String>();
        }
        
        public synchronized void a(final String s) {
            if (this.a != null && this.a.size() > 0 && !TextUtils.isEmpty((CharSequence)s) && this.a.containsKey(s)) {
                this.a.remove(s);
            }
        }
        
        a(final com.umeng.commonsdk.statistics.proto.d d) {
            this.a = new HashMap<String, String>();
            this.a(d);
        }
        
        public void a(final com.umeng.commonsdk.statistics.proto.d d) {
            if (d == null) {
                return;
            }
            this.b(d);
        }

        private synchronized void b(com.umeng.commonsdk.statistics.proto.d d) {
            try {
                if (d == null || !d.e()) {
                    return;
                }
                final Map map = d.c();
                for ( Object obj : map.keySet()) {
                    if (!TextUtils.isEmpty((CharSequence)obj)) {
                        final Object e = map.get(obj);
                        if (e == null) {
                            continue;
                        }
                        final String b = ((com.umeng.commonsdk.statistics.proto.e)e).b();
                        if (TextUtils.isEmpty((CharSequence)b)) {
                            continue;
                        }
                        this.a.put((String)obj, b);
                        if (!AnalyticsConstants.UM_DEBUG) {
                            continue;
                        }
                        Log.i("ImprintHandler", "imKey is " + obj + ", imValue is " + b);
                    }
                }
            }
            catch (Throwable t) {}
        }
        
        public synchronized String a(final String s, final String s2) {
            if (TextUtils.isEmpty((CharSequence)s) || this.a.size() <= 0) {
                return s2;
            }
            final String s3 = this.a.get(s);
            if (!TextUtils.isEmpty((CharSequence)s3)) {
                return s3;
            }
            return s2;
        }
    }
}
