//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;

import a.a.a.UMField;
import a.a.a.a.B_b;
import a.a.a.a.C_c;
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

public class ImprintValue implements UMBean<ImprintValue, ImprintValue.e_enum>, Serializable, Cloneable {
    private static final long e = 7501688097813630241L;
    private static final UMName f = new UMName("ImprintValue");
    private static final TField g = new TField("value", (byte)11, (short)1);
    private static final TField h = new TField("ts", (byte)10, (short)2);
    private static final TField i = new TField("guid", (byte)11, (short)3);
    private static final Map<Class<? extends UMBeanTransfer>, BeanTransferGetter> j = new HashMap();
    public String value;
    public long ts;
    public String guid;
    private byte l;
    private e_enum[] m;
    public static final Map<e_enum, B_b> d;

    public ImprintValue() {
        this.l = 0;
        this.m = new e_enum[]{e_enum.a};
    }

    public ImprintValue(long var1, String var3) {
        this();
        this.ts = var1;
        this.b(true);
        this.guid = var3;
    }

    public ImprintValue(ImprintValue var1) {
        this.l = 0;
        this.m = new e_enum[]{e_enum.a};
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

    public e_enum getUMField(int var1) {
        return e_enum.a(var1);
    }

    public void unpackFrom(UMBeanCoder var1) throws UMException {
        ((BeanTransferGetter)j.get(var1.getBeanTransferClass())).getBeanTransfer().unpack(var1, this);
    }

    public void packTo(UMBeanCoder var1) throws UMException {
        ((BeanTransferGetter)j.get(var1.getBeanTransferClass())).getBeanTransfer().pack(var1, this);
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

    private void a(ObjectOutputStream var1) throws IOException {
        try {
            this.packTo((UMBeanCoder)(new UMBeanCoder_b(new UMIOStream(var1))));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private void a(ObjectInputStream var1) throws IOException, ClassNotFoundException {
        try {
            this.l = 0;
            this.unpackFrom((UMBeanCoder)(new UMBeanCoder_b(new UMIOStream(var1))));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        j.put(UMBeanTransfer_c.class, new ImprintValue.b());
        j.put(UMBeanTransfer_d.class, new ImprintValue.d());
        EnumMap var0 = new EnumMap(e_enum.class);
        var0.put(e_enum.a, new B_b("value", (byte)2, new C_c((byte)11)));
        var0.put(e_enum.b, new B_b("ts", (byte)1, new C_c((byte)10)));
        var0.put(e_enum.c, new B_b("guid", (byte)1, new C_c((byte)11)));
        d = Collections.unmodifiableMap(var0);
        B_b b = new B_b(null, (byte)1, null);
        b.put(ImprintValue.class, d);
    }

    private static class c extends UMBeanTransfer_d<ImprintValue> {
        private c() {
        }

        public void unpack(UMBeanCoder var1, ImprintValue var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
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

        public void pack(UMBeanCoder var1, ImprintValue var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
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

    private static class d implements BeanTransferGetter {
        private d() {
        }

        public ImprintValue.c getBeanTransfer() {
            return new ImprintValue.c();
        }
    }

    private static class a extends UMBeanTransfer_c<ImprintValue> {
        private a() {
        }

        public void unpack(UMBeanCoder var1, ImprintValue var2) throws UMException {
            var1.startUnpack();

            while(true) {
                TField var3 = var1.readTField();
                if(var3.type == 0) {
                    var1.k();
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
                            UMBeanCoderEngine.read(var1, var3.type);
                        }
                        break;
                    case 2:
                        if(var3.type == 10) {
                            var2.ts = var1.readSignedLong();
                            var2.b(true);
                        } else {
                            UMBeanCoderEngine.read(var1, var3.type);
                        }
                        break;
                    case 3:
                        if(var3.type == 11) {
                            var2.guid = var1.readString();
                            var2.c(true);
                        } else {
                            UMBeanCoderEngine.read(var1, var3.type);
                        }
                        break;
                    default:
                        UMBeanCoderEngine.read(var1, var3.type);
                }

                var1.endReadObj();
            }
        }

        public void pack(UMBeanCoder var1, ImprintValue var2) throws UMException {
            var2.l();
            var1.startPack(ImprintValue.f);
            if(var2.value != null && var2.hasValue()) {
                var1.writeTField(ImprintValue.g);
                var1.writeString(var2.value);
                var1.endWriteField();
            }

            var1.writeTField(ImprintValue.h);
            var1.writeUnsignedLong(var2.ts);
            var1.endWriteField();
            if(var2.guid != null) {
                var1.writeTField(ImprintValue.i);
                var1.writeString(var2.guid);
                var1.endWriteField();
            }

            var1.writeDivider();
            var1.endWriteObj();
        }
    }

    private static class b implements BeanTransferGetter {
        private b() {
        }

        public ImprintValue.a getBeanTransfer() {
            return new ImprintValue.a();
        }
    }

    public static enum e_enum implements UMField {
        a((byte)1, "value"),
        b((byte)2, "ts"),
        c((byte)3, "guid");

        private static final Map<String, ImprintValue.e_enum> d = new HashMap();
        private final short e;
        private final String f;

        public static ImprintValue.e_enum a(int var0) {
            switch(var0) {
                case 1:
                    return a;
                case 2:
                    return b;
                case 3:
                    return c;
                default:
                    return null;
            }
        }

        public static ImprintValue.e_enum b(int var0) {
            ImprintValue.e_enum var1 = a(var0);
            if(var1 == null) {
                throw new IllegalArgumentException("Field " + var0 + " doesn\'getPackageName exist!");
            } else {
                return var1;
            }
        }

        public static ImprintValue.e_enum a(String var0) {
            return (ImprintValue.e_enum)d.get(var0);
        }

        private e_enum(short var3, String var4) {
            this.e = var3;
            this.f = var4;
        }

        public short getFieldId() {
            return this.e;
        }

        public String getFieldName() {
            return this.f;
        }

        static {
            Iterator var0 = EnumSet.allOf(ImprintValue.e_enum.class).iterator();

            while(var0.hasNext()) {
                ImprintValue.e_enum var1 = (ImprintValue.e_enum)var0.next();
                d.put(var1.getFieldName(), var1);
            }

        }
    }
}
