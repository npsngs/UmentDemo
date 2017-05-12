//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public final class e_j {
    private static final Comparator a = new e_j.a();

    private e_j() {
    }

    public static int a(Object var0, Object var1) {
        if(var0 instanceof Comparable) {
            return a((Comparable)var0, (Comparable)var1);
        } else if(var0 instanceof List) {
            return a((List)var0, (List)var1);
        } else if(var0 instanceof Set) {
            return a((Set)var0, (Set)var1);
        } else if(var0 instanceof Map) {
            return a((Map)var0, (Map)var1);
        } else if(var0 instanceof byte[]) {
            return a((byte[])((byte[])var0), (byte[])((byte[])var1));
        } else {
            throw new IllegalArgumentException("Cannot compare objects of type " + var0.getClass());
        }
    }

    public static int a(boolean var0, boolean var1) {
        return Boolean.valueOf(var0).compareTo(Boolean.valueOf(var1));
    }

    public static int a(byte var0, byte var1) {
        return var0 < var1?-1:(var1 < var0?1:0);
    }

    public static int a(short var0, short var1) {
        return var0 < var1?-1:(var1 < var0?1:0);
    }

    public static int a(int var0, int var1) {
        return var0 < var1?-1:(var1 < var0?1:0);
    }

    public static int a(long var0, long var2) {
        return var0 < var2?-1:(var2 < var0?1:0);
    }

    public static int a(double var0, double var2) {
        return var0 < var2?-1:(var2 < var0?1:0);
    }

    public static int a(String var0, String var1) {
        return var0.compareTo(var1);
    }

    public static int a(byte[] var0, byte[] var1) {
        int var2 = a(var0.length, var1.length);
        if(var2 != 0) {
            return var2;
        } else {
            for(int var3 = 0; var3 < var0.length; ++var3) {
                int var4 = a(var0[var3], var1[var3]);
                if(var4 != 0) {
                    return var4;
                }
            }

            return 0;
        }
    }

    public static int a(Comparable var0, Comparable var1) {
        return var0.compareTo(var1);
    }

    public static int a(List var0, List var1) {
        int var2 = a(var0.size(), var1.size());
        if(var2 != 0) {
            return var2;
        } else {
            for(int var3 = 0; var3 < var0.size(); ++var3) {
                var2 = a.compare(var0.get(var3), var1.get(var3));
                if(var2 != 0) {
                    return var2;
                }
            }

            return 0;
        }
    }

    public static int a(Set var0, Set var1) {
        int var2 = a(var0.size(), var1.size());
        if(var2 != 0) {
            return var2;
        } else {
            TreeSet var3 = new TreeSet(a);
            var3.addAll(var0);
            TreeSet var4 = new TreeSet(a);
            var4.addAll(var1);
            Iterator var5 = var3.iterator();
            Iterator var6 = var4.iterator();

            while(var5.hasNext() && var6.hasNext()) {
                var2 = a.compare(var5.next(), var6.next());
                if(var2 != 0) {
                    return var2;
                }
            }

            return 0;
        }
    }

    public static int a(Map var0, Map var1) {
        int var2 = a(var0.size(), var1.size());
        if(var2 != 0) {
            return var2;
        } else {
            TreeMap var3 = new TreeMap(a);
            var3.putAll(var0);
            Iterator var4 = var3.entrySet().iterator();
            TreeMap var5 = new TreeMap(a);
            var5.putAll(var1);
            Iterator var6 = var5.entrySet().iterator();

            while(var4.hasNext() && var6.hasNext()) {
                Entry var7 = (Entry)var4.next();
                Entry var8 = (Entry)var6.next();
                var2 = a.compare(var7.getKey(), var8.getKey());
                if(var2 != 0) {
                    return var2;
                }

                var2 = a.compare(var7.getValue(), var8.getValue());
                if(var2 != 0) {
                    return var2;
                }
            }

            return 0;
        }
    }

    public static void a(ByteBuffer var0, StringBuilder var1) {
        byte[] var2 = var0.array();
        int var3 = var0.arrayOffset();
        int var4 = var3 + var0.position();
        int var5 = var3 + var0.limit();
        int var6 = var5 - var4 > 128?var4 + 128:var5;

        for(int var7 = var4; var7 < var6; ++var7) {
            if(var7 > var4) {
                var1.append(" ");
            }

            var1.append(a(var2[var7]));
        }

        if(var5 != var6) {
            var1.append("...");
        }

    }

    public static String a(byte var0) {
        int var1 = (var0 | 256) & 511;
        return Integer.toHexString(var1).toUpperCase().substring(1);
    }

    public static byte[] a(ByteBuffer var0) {
        if(b(var0)) {
            return var0.array();
        } else {
            byte[] var1 = new byte[var0.remaining()];
            a(var0, var1, 0);
            return var1;
        }
    }

    public static boolean b(ByteBuffer var0) {
        return var0.hasArray() && var0.position() == 0 && var0.arrayOffset() == 0 && var0.remaining() == var0.capacity();
    }

    public static int a(ByteBuffer var0, byte[] var1, int var2) {
        int var3 = var0.remaining();
        System.arraycopy(var0.array(), var0.arrayOffset() + var0.position(), var1, var2, var3);
        return var3;
    }

    public static ByteBuffer c(ByteBuffer var0) {
        return var0 == null?null:(b(var0)?var0:ByteBuffer.wrap(a(var0)));
    }

    public static ByteBuffer d(ByteBuffer var0) {
        if(var0 == null) {
            return null;
        } else {
            ByteBuffer var1 = ByteBuffer.wrap(new byte[var0.remaining()]);
            if(var0.hasArray()) {
                System.arraycopy(var0.array(), var0.arrayOffset() + var0.position(), var1.array(), 0, var0.remaining());
            } else {
                var0.slice().get(var1.array());
            }

            return var1;
        }
    }

    public static byte[] a(byte[] var0) {
        if(var0 == null) {
            return null;
        } else {
            byte[] var1 = new byte[var0.length];
            System.arraycopy(var0, 0, var1, 0, var0.length);
            return var1;
        }
    }

    private static class a implements Comparator {
        private a() {
        }

        public int compare(Object var1, Object var2) {
            return var1 == null && var2 == null?0:(var1 == null?-1:(var2 == null?1:(var1 instanceof List?e_j.a((List)var1, (List)var2):(var1 instanceof Set?e_j.a((Set)var1, (Set)var2):(var1 instanceof Map?e_j.a((Map)var1, (Map)var2):(var1 instanceof byte[]?e_j.a((byte[])((byte[])var1), (byte[])((byte[])var2)):((Comparable)var1).compareTo((Comparable)var2)))))));
        }
    }
}
