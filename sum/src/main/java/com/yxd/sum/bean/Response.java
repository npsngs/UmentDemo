//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.bean;

import a.a.a.ByteTool;

import com.yxd.sum.TField;
import com.yxd.sum.BeanCoderSkiper;

import a.a.a.UMException;
import com.yxd.sum.coder.BeanCoder;
import com.yxd.sum.obj.UMMsgException;
import com.yxd.sum.obj.BeanName;
import com.yxd.sum.coder.BSBeanCoder;
import com.yxd.sum.coder.SerializerGetter;
import com.yxd.sum.coder.Serializer;
import com.yxd.sum.coder.NSerializer;
import com.yxd.sum.coder.OSerializer;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Response implements SerialBean, Serializable, Cloneable {
    private static final BeanName NAME = new BeanName("Response");
    private static final TField RESP_CODE = new TField("resp_code", (byte)8, (short) 1);
    private static final TField MSG = new TField("msg", (byte)11, (short)2);
    private static final TField IMPRINT = new TField("imprint", (byte)12, (short)3);
    private static final Map<Class<? extends Serializer>, SerializerGetter> j = new HashMap();
    public int respCode;
    public String msg;
    public Imprint imprint;
    private byte l;

    public Response() {
        this.l = 0;
    }

    public Response(int respCode) {
        this();
        this.respCode = respCode;
        this.a(true);
    }

    public Response(Response response) {
        this.l = 0;
        this.l = response.l;
        this.respCode = response.respCode;
        if(response.hasMsg()) {
            this.msg = response.msg;
        }

        if(response.hasImprint()) {
            this.imprint = new Imprint(response.imprint);
        }

    }

    public void reset() {
        this.a(false);
        this.respCode = 0;
        this.msg = null;
        this.imprint = null;
    }

    public int getRespCode() {
        return this.respCode;
    }

    public Response setRespCode(int respCode) {
        this.respCode = respCode;
        this.a(true);
        return this;
    }

    public void d() {
        this.l = ByteTool.b(this.l, 0);
    }

    public boolean e() {
        return ByteTool.a(this.l, 0);
    }

    public void a(boolean var1) {
        this.l = ByteTool.a(this.l, 0, var1);
    }

    public String getMsg() {
        return this.msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public void clearMsg() {
        this.msg = null;
    }

    public boolean hasMsg() {
        return this.msg != null;
    }

    public void b(boolean var1) {
        if(!var1) {
            this.msg = null;
        }

    }

    public Imprint getImprint() {
        return this.imprint;
    }

    public Response setImprint(Imprint imprint) {
        this.imprint = imprint;
        return this;
    }

    public void clearImprint() {
        this.imprint = null;
    }

    public boolean hasImprint() {
        return this.imprint != null;
    }

    public void c(boolean var1) {
        if(!var1) {
            this.imprint = null;
        }

    }

    public void unpackFrom(BeanCoder var1) throws UMException {
        (j.get(var1.getSerializerClass())).getSerializer().unpack(var1, this);
    }

    public void packTo(BeanCoder var1) throws UMException {
        (j.get(var1.getSerializerClass())).getSerializer().pack(var1, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Response(");
        boolean var2;
        sb.append("resp_code:");
        sb.append(this.respCode);
        var2 = false;
        if(this.hasMsg()) {
            if(!var2) {
                sb.append(", ");
            }

            sb.append("msg:");
            if(this.msg == null) {
                sb.append("null");
            } else {
                sb.append(this.msg);
            }

            var2 = false;
        }

        if(this.hasImprint()) {
            if(!var2) {
                sb.append(", ");
            }

            sb.append("imprint:");
            if(this.imprint == null) {
                sb.append("null");
            } else {
                sb.append(this.imprint);
            }
        }

        sb.append(")");
        return sb.toString();
    }

    public void l() throws UMException {
        if(this.imprint != null) {
            this.imprint.assertValid();
        }

    }


    static {
        j.put(NSerializer.class, new Response.b());
        j.put(OSerializer.class, new Response.d());
    }

    private static class c extends OSerializer<Response> {
        private c() {
        }

        public void unpack(BeanCoder var1, Response var2) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)var1;
            var3.writeUnsignedInt(var2.respCode);
            BitSet var4 = new BitSet();
            if(var2.hasMsg()) {
                var4.set(0);
            }

            if(var2.hasImprint()) {
                var4.set(1);
            }

            var3.a(var4, 2);
            if(var2.hasMsg()) {
                var3.writeString(var2.msg);
            }

            if(var2.hasImprint()) {
                var2.imprint.packTo(var3);
            }

        }

        public void pack(BeanCoder beanCoder, Response response) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)beanCoder;
            response.respCode = var3.readSignedInt();
            response.a(true);
            BitSet var4 = var3.b(2);
            if(var4.get(0)) {
                response.msg = var3.readString();
                response.b(true);
            }

            if(var4.get(1)) {
                response.imprint = new Imprint();
                response.imprint.unpackFrom(var3);
                response.c(true);
            }

        }
    }

    private static class d implements SerializerGetter {
        private d() {
        }

        public Response.c getSerializer() {
            return new Response.c();
        }
    }

    private static class a extends NSerializer<Response> {
        private a() {
        }

        public void unpack(BeanCoder beanCoder, Response response) throws UMException {
            beanCoder.startUnpack();

            while(true) {
                TField tField = beanCoder.readTField();
                if(tField.type == 0) {
                    beanCoder.popStack();
                    if(!response.e()) {
                        throw new UMMsgException("Required field \'resp_code\' was not found in serialized data! Struct: " + this.toString());
                    }

                    response.l();
                    return;
                }

                switch(tField.id) {
                    case 1:
                        if(tField.type == 8) {
                            response.respCode = beanCoder.readSignedInt();
                            response.a(true);
                        } else {
                            //BeanCoderSkiper.skip(beanCoder, tField.type);
                        }
                        break;
                    case 2:
                        if(tField.type == 11) {
                            response.msg = beanCoder.readString();
                            response.b(true);
                        } else {
                           // BeanCoderSkiper.skip(beanCoder, tField.type);
                        }
                        break;
                    case 3:
                        if(tField.type == 12) {
                            response.imprint = new Imprint();
                            response.imprint.unpackFrom(beanCoder);
                            response.c(true);
                        } else {
                           // BeanCoderSkiper.skip(beanCoder, tField.type);
                        }
                        break;
                    default:
                       // BeanCoderSkiper.skip(beanCoder, tField.type);
                }

                beanCoder.endReadObj();
            }
        }

        public void pack(BeanCoder beanCoder, Response response) throws UMException {
            response.l();
            beanCoder.startPack(Response.NAME);
            beanCoder.writeTField(Response.RESP_CODE);
            beanCoder.writeUnsignedInt(response.respCode);
            beanCoder.endWriteField();
            if(response.msg != null && response.hasMsg()) {
                beanCoder.writeTField(Response.MSG);
                beanCoder.writeString(response.msg);
                beanCoder.endWriteField();
            }

            if(response.imprint != null && response.hasImprint()) {
                beanCoder.writeTField(Response.IMPRINT);
                response.imprint.packTo(beanCoder);
                beanCoder.endWriteField();
            }

            beanCoder.writeDivider();
            beanCoder.endWriteObj();
        }
    }

    private static class b implements SerializerGetter {
        private b() {
        }

        public Response.a getSerializer() {
            return new Response.a();
        }
    }

}
