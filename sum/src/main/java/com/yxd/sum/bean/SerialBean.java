//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.bean;

import a.a.a.UMException;
import com.yxd.sum.coder.BeanCoder;

public interface SerialBean {
    void unpackFrom(BeanCoder beanCoder) throws UMException;
    void packTo(BeanCoder beanCoder) throws UMException;
    void reset();
}
