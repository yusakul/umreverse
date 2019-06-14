package com.umeng.commonsdk.proguard;

import java.util.*;

public final class ao extends ac
{
    public ao(final aw aw) {
        super(aw);
    }
    
    @Override
    public Class<? extends aq> D() {
        return at.class;
    }
    
    public void a(final BitSet set, final int n) throws p {
        final byte[] b = b(set, n);
        for (int length = b.length, i = 0; i < length; ++i) {
            this.a(b[i]);
        }
    }
    
    public BitSet b(final int n) throws p {
        final int n2 = (int)Math.ceil(n / 8.0);
        final byte[] array = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array[i] = this.u();
        }
        return a(array);
    }
    
    public static BitSet a(final byte[] array) {
        final BitSet set = new BitSet();
        for (int i = 0; i < array.length * 8; ++i) {
            if ((array[array.length - i / 8 - 1] & 1 << i % 8) > 0) {
                set.set(i);
            }
        }
        return set;
    }
    
    public static byte[] b(final BitSet set, final int n) {
        final byte[] array = new byte[(int)Math.ceil(n / 8.0)];
        for (int i = 0; i < set.length(); ++i) {
            if (set.get(i)) {
                final byte[] array2 = array;
                final int n2 = array.length - i / 8 - 1;
                array2[n2] |= (byte)(1 << i % 8);
            }
        }
        return array;
    }
    
    public static class a implements ak
    {
        @Override
        public ai a(final aw aw) {
            return new ao(aw);
        }
    }
}
