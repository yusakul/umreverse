package com.umeng.commonsdk.proguard;

import java.util.*;
import java.nio.*;

public final class k
{
    private static final Comparator a;
    
    private k() {
    }
    
    public static int a(final Object o, final Object o2) {
        if (o instanceof Comparable) {
            return a((Comparable)o, (Comparable)o2);
        }
        if (o instanceof List) {
            return a((List)o, (List)o2);
        }
        if (o instanceof Set) {
            return a((Set)o, (Set)o2);
        }
        if (o instanceof Map) {
            return a((Map)o, (Map)o2);
        }
        if (o instanceof byte[]) {
            return a((byte[])o, (byte[])o2);
        }
        throw new IllegalArgumentException("Cannot compare objects of type " + o.getClass());
    }
    
    public static int a(final boolean b, final boolean b2) {
        return Boolean.valueOf(b).compareTo(Boolean.valueOf(b2));
    }
    
    public static int a(final byte b, final byte b2) {
        if (b < b2) {
            return -1;
        }
        if (b2 < b) {
            return 1;
        }
        return 0;
    }
    
    public static int a(final short n, final short n2) {
        if (n < n2) {
            return -1;
        }
        if (n2 < n) {
            return 1;
        }
        return 0;
    }
    
    public static int a(final int n, final int n2) {
        if (n < n2) {
            return -1;
        }
        if (n2 < n) {
            return 1;
        }
        return 0;
    }
    
    public static int a(final long n, final long n2) {
        if (n < n2) {
            return -1;
        }
        if (n2 < n) {
            return 1;
        }
        return 0;
    }
    
    public static int a(final double n, final double n2) {
        if (n < n2) {
            return -1;
        }
        if (n2 < n) {
            return 1;
        }
        return 0;
    }
    
    public static int a(final String s, final String anotherString) {
        return s.compareTo(anotherString);
    }
    
    public static int a(final byte[] array, final byte[] array2) {
        final int a = a(array.length, array2.length);
        if (a != 0) {
            return a;
        }
        for (int i = 0; i < array.length; ++i) {
            final int a2 = a(array[i], array2[i]);
            if (a2 != 0) {
                return a2;
            }
        }
        return 0;
    }
    
    public static int a(final Comparable comparable, final Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }
    
    public static int a(final List list, final List list2) {
        final int a = a(list.size(), list2.size());
        if (a != 0) {
            return a;
        }
        for (int i = 0; i < list.size(); ++i) {
            final int compare = k.a.compare(list.get(i), list2.get(i));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
    
    public static int a(final Set set, final Set set2) {
        final int a = a(set.size(), set2.size());
        if (a != 0) {
            return a;
        }
        final TreeSet<Object> set3 = new TreeSet<Object>(k.a);
        set3.addAll(set);
        final TreeSet<Object> set4 = new TreeSet<Object>(k.a);
        set4.addAll(set2);
        final Iterator<Object> iterator = set3.iterator();
        final Iterator<Object> iterator2 = set4.iterator();
        while (iterator.hasNext() && iterator2.hasNext()) {
            final int compare = k.a.compare(iterator.next(), iterator2.next());
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
    
    public static int a(final Map map, final Map map2) {
        final int a = a(map.size(), map2.size());
        if (a != 0) {
            return a;
        }
        final TreeMap treeMap = new TreeMap<Object, Object>(k.a);
        treeMap.putAll(map);
        final Iterator<Map.Entry> iterator = treeMap.entrySet().iterator();
        final TreeMap<Object, Object> treeMap2 = new TreeMap<Object, Object>(k.a);
        treeMap2.putAll(map2);
        final Iterator iterator2 = treeMap2.entrySet().iterator();
        while (iterator.hasNext() && iterator2.hasNext()) {
            final Map.Entry entry = iterator.next();
            final Map.Entry entry2 = (Map.Entry)iterator2.next();
            final int compare = k.a.compare(entry.getKey(), entry2.getKey());
            if (compare != 0) {
                return compare;
            }
            final int compare2 = k.a.compare(entry.getValue(), entry2.getValue());
            if (compare2 != 0) {
                return compare2;
            }
        }
        return 0;
    }
    
    public static void a(final ByteBuffer byteBuffer, final StringBuilder sb) {
        final byte[] array = byteBuffer.array();
        final int arrayOffset = byteBuffer.arrayOffset();
        final int n = arrayOffset + byteBuffer.position();
        final int n2 = arrayOffset + byteBuffer.limit();
        final int n3 = (n2 - n > 128) ? (n + 128) : n2;
        for (int i = n; i < n3; ++i) {
            if (i > n) {
                sb.append(" ");
            }
            sb.append(a(array[i]));
        }
        if (n2 != n3) {
            sb.append("...");
        }
    }
    
    public static String a(final byte b) {
        return Integer.toHexString((b | 0x100) & 0x1FF).toUpperCase().substring(1);
    }
    
    public static byte[] a(final ByteBuffer byteBuffer) {
        if (b(byteBuffer)) {
            return byteBuffer.array();
        }
        final byte[] array = new byte[byteBuffer.remaining()];
        a(byteBuffer, array, 0);
        return array;
    }
    
    public static boolean b(final ByteBuffer byteBuffer) {
        return byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity();
    }
    
    public static int a(final ByteBuffer byteBuffer, final byte[] array, final int n) {
        final int remaining = byteBuffer.remaining();
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), array, n, remaining);
        return remaining;
    }
    
    public static ByteBuffer c(final ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        if (b(byteBuffer)) {
            return byteBuffer;
        }
        return ByteBuffer.wrap(a(byteBuffer));
    }
    
    public static ByteBuffer d(final ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        final ByteBuffer wrap = ByteBuffer.wrap(new byte[byteBuffer.remaining()]);
        if (byteBuffer.hasArray()) {
            System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), wrap.array(), 0, byteBuffer.remaining());
        }
        else {
            byteBuffer.slice().get(wrap.array());
        }
        return wrap;
    }
    
    public static byte[] a(final byte[] array) {
        if (array == null) {
            return null;
        }
        final byte[] array2 = new byte[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    static {
        a = new a();
    }
    
    private static class a implements Comparator
    {
        @Override
        public int compare(final Object o, final Object o2) {
            if (o == null && o2 == null) {
                return 0;
            }
            if (o == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            if (o instanceof List) {
                return k.a((List)o, (List)o2);
            }
            if (o instanceof Set) {
                return k.a((Set)o, (Set)o2);
            }
            if (o instanceof Map) {
                return k.a((Map)o, (Map)o2);
            }
            if (o instanceof byte[]) {
                return k.a((byte[])o, (byte[])o2);
            }
            return k.a((Comparable)o, (Comparable)o2);
        }
    }
}
