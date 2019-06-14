package com.umeng.commonsdk.debug;

import android.util.*;

public class I implements UInterface
{
    @Override
    public void log(final String s, final String s2) {
        Log.i(s, s2);
    }
}
