//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.c;

import a.a.a.UMBean;
import a.a.a.UMException;
import a.a.a.b.UMBeanCoder;

public interface UMBeanTransfer<T extends UMBean> {
    void pack(UMBeanCoder var1, T var2) throws UMException;

    void unpack(UMBeanCoder var1, T var2) throws UMException;
}
