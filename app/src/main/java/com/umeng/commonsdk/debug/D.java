package com.umeng.commonsdk.debug;

import android.util.*;

public class D implements UInterface
{
    @Override
    public void log(final String s, final String s2) {
        Log.d(s, s2);
    }
}
