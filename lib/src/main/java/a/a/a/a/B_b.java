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

public class B_b implements Serializable {
    public final String a;
    public final byte b;
    public final C_c c;
    private static Map<Class<? extends UMBean>, Map<? extends UMField, B_b>> d = new HashMap();

    public B_b(String var1, byte var2, C_c var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public static void put(Class<? extends UMBean> var0, Map<? extends UMField, B_b> var1) {
        d.put(var0, var1);
    }

    public static Map<? extends UMField, B_b> a(Class<? extends UMBean> aClass) {
        if(!d.containsKey(aClass)) {
            try {
                aClass.newInstance();
            } catch (InstantiationException var2) {
                throw new RuntimeException("InstantiationException for TBase class: " + aClass.getName() + ", message: " + var2.getMessage());
            } catch (IllegalAccessException var3) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + aClass.getName() + ", message: " + var3.getMessage());
            }
        }

        return (Map)d.get(aClass);
    }
}
