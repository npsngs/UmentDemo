//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.a;

import a.a.a.UMBean;
import a.a.a.UMField;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class b implements Serializable {
    public final String a;
    public final byte b;
    public final c c;
    private static Map<Class<? extends UMBean>, Map<? extends UMField, b>> d = new HashMap();

    public b(String var1, byte var2, c var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public static void a(Class<? extends UMBean> var0, Map<? extends UMField, b> var1) {
        d.put(var0, var1);
    }

    public static Map<? extends UMField, b> a(Class<? extends UMBean> var0) {
        if(!d.containsKey(var0)) {
            try {
                var0.newInstance();
            } catch (InstantiationException var2) {
                throw new RuntimeException("InstantiationException for TBase class: " + var0.getName() + ", message: " + var2.getMessage());
            } catch (IllegalAccessException var3) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + var0.getName() + ", message: " + var3.getMessage());
            }
        }

        return (Map)d.get(var0);
    }
}
