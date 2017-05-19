//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.coder;

import com.yxd.sum.bean.SerialBean;

import a.a.a.UMException;
import com.yxd.sum.obj.BeanCoderBuilder;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class NBeanPacker {
    private final ByteArrayOutputStream baos;
    private final SumIOStream umioStream;
    private BeanCoder umBeanCoder;

    public NBeanPacker() {
        this(new NBeanCoder.NBeanCoderBuilder());
    }

    public NBeanPacker(BeanCoderBuilder builder) {
        this.baos = new ByteArrayOutputStream();
        this.umioStream = new SumIOStream(this.baos);
        this.umBeanCoder = builder.build(this.umioStream);
    }

    public byte[] pack2Bytes(SerialBean umBean) throws UMException {
        this.baos.reset();
        umBean.packTo(this.umBeanCoder);
        return this.baos.toByteArray();
    }

    public String pack2Str(SerialBean umBean, String charsetName) throws UMException {
        try {
            return new String(this.pack2Bytes(umBean), charsetName);
        } catch (UnsupportedEncodingException var4) {
            throw new UMException("JVM DOES NOT SUPPORT ENCODING: " + charsetName);
        }
    }

    public String packToStr(SerialBean umBean) throws UMException {
        return new String(this.pack2Bytes(umBean));
    }
}
