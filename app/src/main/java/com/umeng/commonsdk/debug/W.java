package com.umeng.commonsdk.debug;

import android.util.*;

public class W implements UInterface
{
    @Override
    public void log(final String s, final String s2) {
        Log.w(s, s2);
    }
}
