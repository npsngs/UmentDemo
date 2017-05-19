//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.bean;

import a.a.a.ByteTool;
import com.yxd.sum.obj.TField;
import com.yxd.sum.obj.BeanCoderSkiper;

import a.a.a.UMException;
import com.yxd.sum.coder.BeanCoder;
import com.yxd.sum.obj.UMMsgException;
import com.yxd.sum.obj.BeanName;
import com.yxd.sum.coder.BSBeanCoder;
import com.yxd.sum.coder.SerializerGetter;
import com.yxd.sum.coder.Serializer;
import com.yxd.sum.coder.NSerializer;
import com.yxd.sum.coder.OSerializer;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class ImprintValue implements SerialBean{
    private static final BeanName NAME = new BeanName("ImprintValue");
    private static final TField VALUE = new TField("value", (byte)11, (short)1);
    private static final TField TS = new TField("ts", (byte)10, (short)2);
    private static final TField GUID = new TField("guid", (byte)11, (short)3);
    private static final Map<Class<? extends Serializer>, SerializerGetter> j = new HashMap();
    public String value;
    public long ts;
    public String guid;
    private byte l;

    public ImprintValue() {
        this.l = 0;
    }

    public ImprintValue(long var1, String var3) {
        this();
        this.ts = var1;
        this.b(true);
        this.guid = var3;
    }

    public ImprintValue(ImprintValue var1) {
        this.l = 0;
        this.l = var1.l;
        if(var1.hasValue()) {
            this.value = var1.value;
        }

        this.ts = var1.ts;
        if(var1.k()) {
            this.guid = var1.guid;
        }

    }

    public ImprintValue copyOne() {
        return new ImprintValue(this);
    }

    public void reset() {
        this.value = null;
        this.b(false);
        this.ts = 0L;
        this.guid = null;
    }

    public String getValue() {
        return this.value;
    }

    public ImprintValue a(String var1) {
        this.value = var1;
        return this;
    }

    public void d() {
        this.value = null;
    }

    public boolean hasValue() {
        return this.value != null;
    }

    public void a(boolean var1) {
        if(!var1) {
            this.value = null;
        }

    }

    public long getTS() {
        return this.ts;
    }

    public ImprintValue a(long var1) {
        this.ts = var1;
        this.b(true);
        return this;
    }

    public void g() {
        this.l = ByteTool.b(this.l, 0);
    }

    public boolean h() {
        return ByteTool.a(this.l, 0);
    }

    public void b(boolean var1) {
        this.l = ByteTool.a(this.l, 0, var1);
    }

    public String getGUID() {
        return this.guid;
    }

    public ImprintValue b(String var1) {
        this.guid = var1;
        return this;
    }

    public void j() {
        this.guid = null;
    }

    public boolean k() {
        return this.guid != null;
    }

    public void c(boolean var1) {
        if(!var1) {
            this.guid = null;
        }

    }

    public void unpackFrom(BeanCoder var1) throws UMException {
        ((SerializerGetter)j.get(var1.getSerializerClass())).getSerializer().unpack(var1, this);
    }

    public void packTo(BeanCoder var1) throws UMException {
        ((SerializerGetter)j.get(var1.getSerializerClass())).getSerializer().pack(var1, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ImprintValue(");
        boolean var2 = true;
        if(this.hasValue()) {
            sb.append("value:");
            if(this.value == null) {
                sb.append("null");
            } else {
                sb.append(this.value);
            }

            var2 = false;
        }

        if(!var2) {
            sb.append(", ");
        }

        sb.append("ts:");
        sb.append(this.ts);
        var2 = false;
        if(!var2) {
            sb.append(", ");
        }

        sb.append("guid:");
        if(this.guid == null) {
            sb.append("null");
        } else {
            sb.append(this.guid);
        }

        sb.append(")");
        return sb.toString();
    }

    public void l() throws UMException {
        if(this.guid == null) {
            throw new UMMsgException("Required field \'guid\' was not present! Struct: " + this.toString());
        }
    }

    static {
        j.put(NSerializer.class, new ImprintValue.b());
        j.put(OSerializer.class, new ImprintValue.d());
    }

    private static class c extends OSerializer<ImprintValue> {
        private c() {
        }

        public void unpack(BeanCoder var1, ImprintValue var2) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)var1;
            var3.writeUnsignedLong(var2.ts);
            var3.writeString(var2.guid);
            BitSet var4 = new BitSet();
            if(var2.hasValue()) {
                var4.set(0);
            }

            var3.a(var4, 1);
            if(var2.hasValue()) {
                var3.writeString(var2.value);
            }

        }

        public void pack(BeanCoder var1, ImprintValue var2) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)var1;
            var2.ts = var3.readSignedLong();
            var2.b(true);
            var2.guid = var3.readString();
            var2.c(true);
            BitSet var4 = var3.b(1);
            if(var4.get(0)) {
                var2.value = var3.readString();
                var2.a(true);
            }

        }
    }

    private static class d implements SerializerGetter {
        private d() {
        }

        public ImprintValue.c getSerializer() {
            return new ImprintValue.c();
        }
    }

    private static class a extends NSerializer<ImprintValue> {
        private a() {
        }

        public void unpack(BeanCoder var1, ImprintValue var2) throws UMException {
            var1.startUnpack();

            while(true) {
                TField var3 = var1.readTField();
                if(var3.type == 0) {
                    var1.popStack();
                    if(!var2.h()) {
                        throw new UMMsgException("Required field \'ts\' was not found in serialized data! Struct: " + this.toString());
                    }

                    var2.l();
                    return;
                }

                switch(var3.id) {
                    case 1:
                        if(var3.type == 11) {
                            var2.value = var1.readString();
                            var2.a(true);
                        } else {
                            BeanCoderSkiper.read(var1, var3.type);
                        }
                        break;
                    case 2:
                        if(var3.type == 10) {
                            var2.ts = var1.readSignedLong();
                            var2.b(true);
                        } else {
                            BeanCoderSkiper.read(var1, var3.type);
                        }
                        break;
                    case 3:
                        if(var3.type == 11) {
                            var2.guid = var1.readString();
                            var2.c(true);
                        } else {
                            BeanCoderSkiper.read(var1, var3.type);
                        }
                        break;
                    default:
                        BeanCoderSkiper.read(var1, var3.type);
                }

                var1.endReadObj();
            }
        }

        public void pack(BeanCoder var1, ImprintValue var2) throws UMException {
            var2.l();
            var1.startPack(ImprintValue.NAME);
            if(var2.value != null && var2.hasValue()) {
                var1.writeTField(ImprintValue.VALUE);
                var1.writeString(var2.value);
                var1.endWriteField();
            }

            var1.writeTField(ImprintValue.TS);
            var1.writeUnsignedLong(var2.ts);
            var1.endWriteField();
            if(var2.guid != null) {
                var1.writeTField(ImprintValue.GUID);
                var1.writeString(var2.guid);
                var1.endWriteField();
            }

            var1.writeDivider();
            var1.endWriteObj();
        }
    }

    private static class b implements SerializerGetter {
        private b() {
        }

        public ImprintValue.a getSerializer() {
            return new ImprintValue.a();
        }
    }
}
