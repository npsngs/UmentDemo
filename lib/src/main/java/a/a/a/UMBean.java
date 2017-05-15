//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

import a.a.a.b.UMBeanCoder;
import java.io.Serializable;

public interface UMBean<T extends UMBean<?, ?>, F extends UMField> extends Serializable {
    void unpackFrom(UMBeanCoder beanCoder) throws UMException;

    void packTo(UMBeanCoder beanCoder) throws UMException;

    F getUMField(int fieldId);

    UMBean<T, F> copyOne();

    void reset();
}
