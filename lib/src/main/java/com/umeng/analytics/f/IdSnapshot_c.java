//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;

import a.a.a.UMBean;
import a.a.a.UMField;
import a.a.a.a.B_b;
import a.a.a.a.C_c;
import a.a.a.ByteTool;
import a.a.a.UMException;
import a.a.a.b.TField;
import a.a.a.b.UMBeanCoder;
import a.a.a.b.UMBeanCoder_b;
import a.a.a.b.UMMsgException;
import a.a.a.b.UMBeanCoderEngine;
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
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IdSnapshot_c implements UMBean<IdSnapshot_c, IdSnapshot_c.e_enum>, Serializable, Cloneable {
    private static final long e = -6496538196005191531L;
    private static final UMName f = new UMName("IdSnapshot");
    private static final TField IDENTITY_FIELD = new TField("identity", (byte) 11, (short) 1);
    private static final TField TS_FIELD = new TField("ts", (byte)10, (short)2);
    private static final TField VERSION_FIELD = new TField("version", (byte)8, (short)3);
    private static final Map<Class<? extends UMBeanTransfer>, BeanTransferGetter> j = new HashMap();
    public String identity;
    public long ts;
    public int version;
    private byte m;
    public static final Map<e_enum, B_b> d;

    public IdSnapshot_c() {
        this.m = 0;
    }

    public IdSnapshot_c(String id, long ts, int version) {
        this();
        this.identity = id;
        this.ts = ts;
        this.b(true);
        this.version = version;
        this.c(true);
    }

    public IdSnapshot_c(IdSnapshot_c snapshot) {
        this.m = 0;
        this.m = snapshot.m;
        if(snapshot.hasIdentity()) {
            this.identity = snapshot.identity;
        }

        this.ts = snapshot.ts;
        this.version = snapshot.version;
    }

    public IdSnapshot_c copyOne() {
        return new IdSnapshot_c(this);
    }

    public void reset() {
        this.identity = null;
        this.b(false);
        this.ts = 0L;
        this.c(false);
        this.version = 0;
    }

    public String getIdentity() {
        return this.identity;
    }

    public IdSnapshot_c setIdentity(String identity) {
        this.identity = identity;
        return this;
    }

    public void clearIdentity() {
        this.identity = null;
    }

    public boolean hasIdentity() {
        return this.identity != null;
    }

    public void a(boolean var1) {
        if(!var1) {
            this.identity = null;
        }

    }

    public long getTS() {
        return this.ts;
    }

    public IdSnapshot_c setTS(long ts) {
        this.ts = ts;
        this.b(true);
        return this;
    }

    public void g() {
        this.m = ByteTool.b(this.m, 0);
    }

    public boolean h() {
        return ByteTool.a(this.m, 0);
    }

    public void b(boolean var1) {
        this.m = ByteTool.a(this.m, 0, var1);
    }

    public int getVersion() {
        return this.version;
    }

    public IdSnapshot_c setVersion(int version) {
        this.version = version;
        this.c(true);
        return this;
    }

    public void j() {
        this.m = ByteTool.b(this.m, 1);
    }

    public boolean k() {
        return ByteTool.a(this.m, 1);
    }

    public void c(boolean var1) {
        this.m = ByteTool.a(this.m, 1, var1);
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
        StringBuilder var1 = new StringBuilder("IdSnapshot(");
        boolean var2 = true;
        var1.append("identity:");
        if(this.identity == null) {
            var1.append("null");
        } else {
            var1.append(this.identity);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("ts:");
        var1.append(this.ts);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("version:");
        var1.append(this.version);
        var2 = false;
        var1.append(")");
        return var1.toString();
    }

    public void l() throws UMException {
        if(this.identity == null) {
            throw new UMMsgException("Required field \'identity\' was not present! Struct: " + this.toString());
        }
    }

    private void a(ObjectOutputStream var1) throws IOException {
        try {
            this.packTo(new UMBeanCoder_b(new UMIOStream(var1)));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private void a(ObjectInputStream var1) throws IOException, ClassNotFoundException {
        try {
            this.m = 0;
            this.unpackFrom((UMBeanCoder)(new UMBeanCoder_b(new UMIOStream(var1))));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        j.put(UMBeanTransfer_c.class, new IdSnapshot_c.b());
        j.put(UMBeanTransfer_d.class, new IdSnapshot_c.d());
        EnumMap var0 = new EnumMap(e_enum.class);
        var0.put(e_enum.a, new B_b("identity", (byte)1, new C_c((byte)11)));
        var0.put(e_enum.b, new B_b("ts", (byte)1, new C_c((byte)10)));
        var0.put(e_enum.c, new B_b("version", (byte)1, new C_c((byte)8)));
        d = Collections.unmodifiableMap(var0);
        B_b a = new B_b(null, (byte)1, null);
        a.put(IdSnapshot_c.class, d);
    }

    private static class c_inner extends UMBeanTransfer_d<IdSnapshot_c> {
        private c_inner() {
        }

        public void pack(UMBeanCoder var1, IdSnapshot_c var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
            var3.writeString(var2.identity);
            var3.writeUnsignedLong(var2.ts);
            var3.writeUnsignedInt(var2.version);
        }

        public void unpack(UMBeanCoder var1, IdSnapshot_c var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
            var2.identity = var3.readString();
            var2.a(true);
            var2.ts = var3.readSignedLong();
            var2.b(true);
            var2.version = var3.readSignedInt();
            var2.c(true);
        }
    }

    private static class d implements BeanTransferGetter {
        private d() {
        }

        public IdSnapshot_c.c_inner getBeanTransfer() {
            return new IdSnapshot_c.c_inner();
        }
    }

    private static class a_inner extends UMBeanTransfer_c<IdSnapshot_c> {
        private a_inner() {
        }

        public void unpack(UMBeanCoder var1, IdSnapshot_c var2) throws UMException {
            var1.startUnpack();

            while(true) {
                TField var3 = var1.readTField();
                if(var3.type == 0) {
                    var1.k();
                    if(!var2.h()) {
                        throw new UMMsgException("Required field \'ts\' was not found in serialized data! Struct: " + this.toString());
                    }

                    if(!var2.k()) {
                        throw new UMMsgException("Required field \'version\' was not found in serialized data! Struct: " + this.toString());
                    }

                    var2.l();
                    return;
                }

                switch(var3.id) {
                    case 1:
                        if(var3.type == 11) {
                            var2.identity = var1.readString();
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
                        if(var3.type == 8) {
                            var2.version = var1.readSignedInt();
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

        public void pack(UMBeanCoder var1, IdSnapshot_c var2) throws UMException {
            var2.l();
            var1.startPack(IdSnapshot_c.f);
            if(var2.identity != null) {
                var1.writeTField(IdSnapshot_c.IDENTITY_FIELD);
                var1.writeString(var2.identity);
                var1.endWriteField();
            }

            var1.writeTField(IdSnapshot_c.TS_FIELD);
            var1.writeUnsignedLong(var2.ts);
            var1.endWriteField();
            var1.writeTField(IdSnapshot_c.VERSION_FIELD);
            var1.writeUnsignedInt(var2.version);
            var1.endWriteField();
            var1.writeEOF();
            var1.endWriteObj();
        }
    }

    private static class b implements BeanTransferGetter {
        private b() {
        }

        public a_inner getBeanTransfer() {
            return new a_inner();
        }
    }

    public static enum e_enum implements UMField {
        a((short)1, "identity"),
        b((short)2, "ts"),
        c((short)3, "version");

        private static final Map<String, e_enum> d = new HashMap();
        private final short e;
        private final String f;

        public static e_enum a(int var0) {
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

        public static e_enum b(int var0) {
            e_enum var1 = a(var0);
            if(var1 == null) {
                throw new IllegalArgumentException("Field " + var0 + " doesn\'getPackageName exist!");
            } else {
                return var1;
            }
        }

        public static e_enum a(String var0) {
            return d.get(var0);
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
            Iterator var0 = EnumSet.allOf(e_enum.class).iterator();

            while(var0.hasNext()) {
                e_enum var1 = (e_enum)var0.next();
                d.put(var1.getFieldName(), var1);
            }

        }
    }
}
