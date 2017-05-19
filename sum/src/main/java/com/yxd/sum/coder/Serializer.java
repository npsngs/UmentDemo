//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.coder;

import com.yxd.sum.bean.SerialBean;
import a.a.a.UMException;
import com.yxd.sum.coder.BeanCoder;

public interface Serializer<T extends SerialBean> {
    void pack(BeanCoder beanCoder, T bean) throws UMException;

    void unpack(BeanCoder beanCoder, T bean) throws UMException;
}
