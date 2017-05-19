//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a;

import a.a.a.b.UMBeanCoder;
import a.a.a.b.UMBeanCoder_b;
import a.a.a.b.UMBeanCoderBuilder;
import a.a.a.d.UMIOStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class UMBeanPacker {
    private final ByteArrayOutputStream baos;
    private final UMIOStream umioStream;
    private UMBeanCoder umBeanCoder;

    public UMBeanPacker() {
        this(new UMBeanCoder_b.UMBeanCoder_b_Builder());
    }

    public UMBeanPacker(UMBeanCoderBuilder builder) {
        this.baos = new ByteArrayOutputStream();
        this.umioStream = new UMIOStream(this.baos);
        this.umBeanCoder = builder.build(this.umioStream);
    }

    public byte[] pack2Bytes(UMBean umBean) throws UMException {
        this.baos.reset();
        umBean.packTo(this.umBeanCoder);
        return this.baos.toByteArray();
    }

    public String pack2Str(UMBean umBean, String charsetName) throws UMException {
        try {
            return new String(this.pack2Bytes(umBean), charsetName);
        } catch (UnsupportedEncodingException var4) {
            throw new UMException("JVM DOES NOT SUPPORT ENCODING: " + charsetName);
        }
    }

    public String packToStr(UMBean umBean) throws UMException {
        return new String(this.pack2Bytes(umBean));
    }
}
