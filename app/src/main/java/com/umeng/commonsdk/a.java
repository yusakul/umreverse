package com.umeng.commonsdk;

import android.content.*;
import android.text.*;
import com.umeng.commonsdk.internal.*;
import com.umeng.commonsdk.framework.*;
import com.umeng.commonsdk.statistics.common.*;

public class a
{
    private static boolean a;
    
    public static synchronized void a(final Context context) {
        try {
            if (context != null && !com.umeng.commonsdk.a.a) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                            final String packageName = context.getPackageName();
                            if (!TextUtils.isEmpty((CharSequence)currentProcessName) && !TextUtils.isEmpty((CharSequence)packageName) && currentProcessName.equals(packageName)) {
                                try {
                                    if (UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                                        UMWorkDispatch.sendEvent(context, 32777, com.umeng.commonsdk.internal.b.a(context).a(), null);
                                    }
                                }
                                catch (Throwable t) {}
                            }
                        }
                        catch (Throwable t2) {}
                    }
                }).start();
                com.umeng.commonsdk.a.a = true;
            }
        }
        catch (Throwable t) {
            ULog.e("internal", "e_enum is " + t.getMessage());
        }
    }
    
    static {
        com.umeng.commonsdk.a.a = false;
    }
}
