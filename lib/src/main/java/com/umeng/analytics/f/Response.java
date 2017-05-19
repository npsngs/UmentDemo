//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;

import a.a.a.UMField;
import a.a.a.a.B_b;
import a.a.a.a.C_c;
import a.a.a.a.G_g_a;
import a.a.a.ByteTool;
import a.a.a.b.TField;
import a.a.a.b.UMBeanCoderEngine;
import a.a.a.UMBean;
import a.a.a.UMException;
import a.a.a.b.UMBeanCoder;
import a.a.a.b.UMBeanCoder_b;
import a.a.a.b.UMMsgException;
import a.a.a.b.UMName;
import a.a.a.b.UMBeanCoder_n;
import a.a.a.c.BeanTransferGetter;
import a.a.a.c.UMBeanTransfer;
import a.a.a.c.UMBeanTransfer_c;
import a.a.a.c.UMBeanTransfer_d;
import a.a.a.d.UMIOStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Response implements UMBean<Response, Response.RespField>, Serializable, Cloneable {
    private static final UMName f = new UMName("Response");
    private static final TField RESP_CODE_FIELD = new TField("resp_code", (byte)8, (short) 1);
    private static final TField MSH_FIELD = new TField("msg", (byte)11, (short)2);
    private static final TField IMPRINT_FIELD = new TField("imprint", (byte)12, (short)3);
    private static final Map<Class<? extends UMBeanTransfer>, BeanTransferGetter> j = new HashMap();
    public int respCode;
    public String msg;
    public Imprint imprint;
    private byte l;
    public static final Map<RespField, B_b> d;

    public Response() {
        this.l = 0;
    }

    public Response(int var1) {
        this();
        this.respCode = var1;
        this.a(true);
    }

    public Response(Response var1) {
        this.l = 0;
        this.l = var1.l;
        this.respCode = var1.respCode;
        if(var1.hasMsg()) {
            this.msg = var1.msg;
        }

        if(var1.hasImprint()) {
            this.imprint = new Imprint(var1.imprint);
        }

    }

    public Response copyOne() {
        return new Response(this);
    }

    public void reset() {
        this.a(false);
        this.respCode = 0;
        this.msg = null;
        this.imprint = null;
    }

    public int c() {
        return this.respCode;
    }

    public Response a(int var1) {
        this.respCode = var1;
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

    public Response a(String var1) {
        this.msg = var1;
        return this;
    }

    public void g() {
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

    public Response a(Imprint var1) {
        this.imprint = var1;
        return this;
    }

    public void j() {
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

    public RespField getUMField(int fieldId) {
        return RespField.getField(fieldId);
    }

    public void unpackFrom(UMBeanCoder var1) throws UMException {
        (j.get(var1.getBeanTransferClass())).getBeanTransfer().unpack(var1, this);
    }

    public void packTo(UMBeanCoder var1) throws UMException {
        (j.get(var1.getBeanTransferClass())).getBeanTransfer().pack(var1, this);
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

    private void a(ObjectOutputStream oos) throws IOException {
        try {
            this.packTo(new UMBeanCoder_b(new UMIOStream(oos)));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private void a(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        try {
            this.l = 0;
            this.unpackFrom(new UMBeanCoder_b(new UMIOStream(ois)));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        j.put(UMBeanTransfer_c.class, new Response.b());
        j.put(UMBeanTransfer_d.class, new Response.d());
        EnumMap var0 = new EnumMap(RespField.class);
        var0.put(RespField.RESP_CODE, new B_b("resp_code", (byte)1, new C_c((byte)8)));
        var0.put(RespField.MSG, new B_b("msg", (byte)2, new C_c((byte)11)));
        var0.put(RespField.IMPRINT, new B_b("imprint", (byte)2, new G_g_a((byte)12, Imprint.class)));
        d = Collections.unmodifiableMap(var0);
        B_b b = new B_b(null, (byte)1, null);
        b.put(Response.class, d);
    }

    private static class c extends UMBeanTransfer_d<Response> {
        private c() {
        }

        public void unpack(UMBeanCoder var1, Response var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
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

        public void pack(UMBeanCoder beanCoder, Response response) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)beanCoder;
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

    private static class d implements BeanTransferGetter {
        private d() {
        }

        public Response.c getBeanTransfer() {
            return new Response.c();
        }
    }

    private static class a extends UMBeanTransfer_c<Response> {
        private a() {
        }

        public void unpack(UMBeanCoder beanCoder, Response response) throws UMException {
            beanCoder.startUnpack();

            while(true) {
                TField tField = beanCoder.readTField();
                if(tField.type == 0) {
                    beanCoder.k();
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
                            UMBeanCoderEngine.read(beanCoder, tField.type);
                        }
                        break;
                    case 2:
                        if(tField.type == 11) {
                            response.msg = beanCoder.readString();
                            response.b(true);
                        } else {
                            UMBeanCoderEngine.read(beanCoder, tField.type);
                        }
                        break;
                    case 3:
                        if(tField.type == 12) {
                            response.imprint = new Imprint();
                            response.imprint.unpackFrom(beanCoder);
                            response.c(true);
                        } else {
                            UMBeanCoderEngine.read(beanCoder, tField.type);
                        }
                        break;
                    default:
                        UMBeanCoderEngine.read(beanCoder, tField.type);
                }

                beanCoder.endReadObj();
            }
        }

        public void pack(UMBeanCoder beanCoder, Response response) throws UMException {
            response.l();
            beanCoder.startPack(Response.f);
            beanCoder.writeTField(Response.RESP_CODE_FIELD);
            beanCoder.writeUnsignedInt(response.respCode);
            beanCoder.endWriteField();
            if(response.msg != null && response.hasMsg()) {
                beanCoder.writeTField(Response.MSH_FIELD);
                beanCoder.writeString(response.msg);
                beanCoder.endWriteField();
            }

            if(response.imprint != null && response.hasImprint()) {
                beanCoder.writeTField(Response.IMPRINT_FIELD);
                response.imprint.packTo(beanCoder);
                beanCoder.endWriteField();
            }

            beanCoder.writeDivider();
            beanCoder.endWriteObj();
        }
    }

    private static class b implements BeanTransferGetter {
        private b() {
        }

        public Response.a getBeanTransfer() {
            return new Response.a();
        }
    }

    public enum RespField implements UMField {
        RESP_CODE((byte)1, "resp_code"),
        MSG((byte)2, "msg"),
        IMPRINT((byte)3, "imprint");

        private static final Map<String, RespField> fieldMap = new HashMap();
        private final short fieldId;
        private final String fieldName;

        public static RespField getField(int fieldId) {
            switch(fieldId) {
                case 1:
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
                default:
                    return null;
            }
        }

        public static RespField obtainField(int fieldCode) {
            RespField field = getField(fieldCode);
            if(field == null) {
                throw new IllegalArgumentException("Field " + fieldCode + " doesnt\'getPackageName exist!");
            } else {
                return field;
            }
        }

        public static RespField getField(String fieldName) {
            return fieldMap.get(fieldName);
        }

        RespField(short fieldId, String fieldName) {
            this.fieldId = fieldId;
            this.fieldName = fieldName;
        }

        public short getFieldId() {
            return this.fieldId;
        }

        public String getFieldName() {
            return this.fieldName;
        }

        static {
            Iterator iterator = EnumSet.allOf(RespField.class).iterator();

            while(iterator.hasNext()) {
                RespField field = (RespField)iterator.next();
                fieldMap.put(field.getFieldName(), field);
            }

        }
    }
}
