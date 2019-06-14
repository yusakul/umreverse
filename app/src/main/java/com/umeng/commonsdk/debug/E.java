package com.umeng.commonsdk.debug;

import android.util.*;

public class E implements UInterface
{
    @Override
    public void log(final String s, final String s2) {
        Log.e(s, s2);
    }
}
