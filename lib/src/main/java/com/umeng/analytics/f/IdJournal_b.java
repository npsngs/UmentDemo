//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;

import a.a.a.UMField;
import a.a.a.a.B_b;
import a.a.a.a.C_c;
import a.a.a.a_j;
import a.a.a.b.TField_c;
import a.a.a.UMBean;
import a.a.a.UMException;
import a.a.a.b.UMBeanCoder;
import a.a.a.b.UMBeanCoder_b;
import a.a.a.b.UMMsgException;
import a.a.a.b.UMBeanCoderEngine;
import a.a.a.b.Name;
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

public class IdJournal_b implements UMBean<IdJournal_b, IdJournal_b.e_enum>, Serializable, Cloneable {
    private static final long f = 9132678615281394583L;
    private static final Name g = new Name("IdJournal");
    private static final TField_c h = new TField_c("domain", (byte)11, (short)1);
    private static final TField_c i = new TField_c("old_id", (byte)11, (short)2);
    private static final TField_c j = new TField_c("new_id", (byte)11, (short)3);
    private static final TField_c K_bc = new TField_c("ts", (byte)10, (short)4);
    private static final Map<Class<? extends UMBeanTransfer>, BeanTransferGetter> l = new HashMap();
    public String domain;
    public String old_id;
    public String new_id;
    public long d;
    private static final int m = 0;
    private byte n;
    private e_enum[] o;
    public static final Map<e_enum, B_b> e;

    public IdJournal_b() {
        this.n = 0;
        this.o = new e_enum[]{e_enum.b};
    }

    public IdJournal_b(String var1, String var2, long var3) {
        this();
        this.domain = var1;
        this.new_id = var2;
        this.d = var3;
        this.d(true);
    }

    public IdJournal_b(IdJournal_b var1) {
        this.n = 0;
        this.o = new e_enum[]{e_enum.b};
        this.n = var1.n;
        if(var1.e()) {
            this.domain = var1.domain;
        }

        if(var1.h()) {
            this.old_id = var1.old_id;
        }

        if(var1.k()) {
            this.new_id = var1.new_id;
        }

        this.d = var1.d;
    }

    public IdJournal_b copyOne() {
        return new IdJournal_b(this);
    }

    public void reset() {
        this.domain = null;
        this.old_id = null;
        this.new_id = null;
        this.d(false);
        this.d = 0L;
    }

    public String c() {
        return this.domain;
    }

    public IdJournal_b p(String var1) {
        this.domain = var1;
        return this;
    }

    public void d() {
        this.domain = null;
    }

    public boolean e() {
        return this.domain != null;
    }

    public void a(boolean var1) {
        if(!var1) {
            this.domain = null;
        }

    }

    public String f() {
        return this.old_id;
    }

    public IdJournal_b b(String var1) {
        this.old_id = var1;
        return this;
    }

    public void g() {
        this.old_id = null;
    }

    public boolean h() {
        return this.old_id != null;
    }

    public void b(boolean var1) {
        if(!var1) {
            this.old_id = null;
        }

    }

    public String i() {
        return this.new_id;
    }

    public IdJournal_b c(String var1) {
        this.new_id = var1;
        return this;
    }

    public void j() {
        this.new_id = null;
    }

    public boolean k() {
        return this.new_id != null;
    }

    public void c(boolean var1) {
        if(!var1) {
            this.new_id = null;
        }

    }

    public long l() {
        return this.d;
    }

    public IdJournal_b a(long var1) {
        this.d = var1;
        this.d(true);
        return this;
    }

    public void m() {
        this.n = a_j.b(this.n, 0);
    }

    public boolean n() {
        return a_j.a(this.n, 0);
    }

    public void d(boolean var1) {
        this.n = a_j.a(this.n, 0, var1);
    }

    public e_enum getUMField(int var1) {
        return e_enum.a(var1);
    }

    public void unpackFrom(UMBeanCoder var1) throws UMException {
        ((BeanTransferGetter)l.get(var1.getBeanTransferClass())).getBeanTransfer().unpack(var1, this);
    }

    public void packTo(UMBeanCoder var1) throws UMException {
        ((BeanTransferGetter)l.get(var1.getBeanTransferClass())).getBeanTransfer().pack(var1, this);
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder("IdJournal(");
        boolean var2 = true;
        var1.append("domain:");
        if(this.domain == null) {
            var1.append("null");
        } else {
            var1.append(this.domain);
        }

        var2 = false;
        if(this.h()) {
            if(!var2) {
                var1.append(", ");
            }

            var1.append("old_id:");
            if(this.old_id == null) {
                var1.append("null");
            } else {
                var1.append(this.old_id);
            }

            var2 = false;
        }

        if(!var2) {
            var1.append(", ");
        }

        var1.append("new_id:");
        if(this.new_id == null) {
            var1.append("null");
        } else {
            var1.append(this.new_id);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("ts:");
        var1.append(this.d);
        var2 = false;
        var1.append(")");
        return var1.toString();
    }

    public void assertValid() throws UMException {
        if(this.domain == null) {
            throw new UMMsgException("Required field \'domain\' was not present! Struct: " + this.toString());
        } else if(this.new_id == null) {
            throw new UMMsgException("Required field \'new_id\' was not present! Struct: " + this.toString());
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
            this.n = 0;
            this.unpackFrom((UMBeanCoder)(new UMBeanCoder_b(new UMIOStream(var1))));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        l.put(UMBeanTransfer_c.class, new IdJournal_b.b_inner());
        l.put(UMBeanTransfer_d.class, new IdJournal_b.d());
        EnumMap var0 = new EnumMap(IdJournal_b.e_enum.class);
        var0.put(e_enum.a, new B_b("domain", (byte)1, new C_c((byte)11)));
        var0.put(e_enum.b, new B_b("old_id", (byte)2, new C_c((byte)11)));
        var0.put(e_enum.c, new B_b("new_id", (byte)1, new C_c((byte)11)));
        var0.put(e_enum.d, new B_b("ts", (byte)1, new C_c((byte)10)));
        e = Collections.unmodifiableMap(var0);
        B_b a = new B_b("",(byte)1,null);
        a.a(IdJournal_b.class, e);
    }

    private static class c extends UMBeanTransfer_d<IdJournal_b> {
        private c() {
        }

        public void unpack(UMBeanCoder var1, IdJournal_b var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
            var3.writeString(var2.domain);
            var3.writeString(var2.new_id);
            var3.writeUnsignedLong(var2.d);
            BitSet var4 = new BitSet();
            if(var2.h()) {
                var4.set(0);
            }

            var3.a(var4, 1);
            if(var2.h()) {
                var3.writeString(var2.old_id);
            }

        }

        public void pack(UMBeanCoder var1, IdJournal_b var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
            var2.domain = var3.readString();
            var2.a(true);
            var2.new_id = var3.readString();
            var2.c(true);
            var2.d = var3.readSignedLong();
            var2.d(true);
            BitSet var4 = var3.b(1);
            if(var4.get(0)) {
                var2.old_id = var3.readString();
                var2.b(true);
            }

        }
    }

    private static class d implements BeanTransferGetter {
        private d() {
        }

        public IdJournal_b.c getBeanTransfer() {
            return new IdJournal_b.c();
        }
    }

    private static class a_inner extends UMBeanTransfer_c<IdJournal_b> {
        private a_inner() {
        }

        public void unpack(UMBeanCoder umBeanCoder, IdJournal_b idJournal_b) throws UMException {
            umBeanCoder.startUnpack();

            while(true) {
                TField_c tField = umBeanCoder.readTField();
                if(tField.type == 0) {
                    umBeanCoder.k();
                    if(!idJournal_b.n()) {
                        throw new UMMsgException("Required field \'ts\' was not found in serialized data! Struct: " + this.toString());
                    }

                    idJournal_b.assertValid();
                    return;
                }

                switch(tField.id) {
                    case 1:
                        if(tField.type == 11) {
                            idJournal_b.domain = umBeanCoder.readString();
                            idJournal_b.a(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 2:
                        if(tField.type == 11) {
                            idJournal_b.old_id = umBeanCoder.readString();
                            idJournal_b.b(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 3:
                        if(tField.type == 11) {
                            idJournal_b.new_id = umBeanCoder.readString();
                            idJournal_b.c(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 4:
                        if(tField.type == 10) {
                            idJournal_b.d = umBeanCoder.readSignedLong();
                            idJournal_b.d(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    default:
                        UMBeanCoderEngine.read(umBeanCoder, tField.type);
                }

                umBeanCoder.m();
            }
        }

        public void pack(UMBeanCoder umBeanCoder, IdJournal_b idJournal_b) throws UMException {
            idJournal_b.assertValid();
            umBeanCoder.startPack(IdJournal_b.g);
            if(idJournal_b.domain != null) {
                umBeanCoder.writeTField(IdJournal_b.h);
                umBeanCoder.writeString(idJournal_b.domain);
                umBeanCoder.c();
            }

            if(idJournal_b.old_id != null && idJournal_b.h()) {
                umBeanCoder.writeTField(IdJournal_b.i);
                umBeanCoder.writeString(idJournal_b.old_id);
                umBeanCoder.c();
            }

            if(idJournal_b.new_id != null) {
                umBeanCoder.writeTField(IdJournal_b.j);
                umBeanCoder.writeString(idJournal_b.new_id);
                umBeanCoder.c();
            }

            umBeanCoder.writeTField(IdJournal_b.K_bc);
            umBeanCoder.writeUnsignedLong(idJournal_b.d);
            umBeanCoder.c();
            umBeanCoder.writeDivider();
            umBeanCoder.endPack();
        }
    }

    private static class b_inner implements BeanTransferGetter {
        private b_inner() {
        }

        public IdJournal_b.a_inner getBeanTransfer() {
            return new IdJournal_b.a_inner();
        }
    }

    public static enum e_enum implements UMField {
        a((short)1, "domain"),
        b((short)2, "old_id"),
        c((short)3, "new_id"),
        d((short)4, "ts");

        private static final Map<String, e_enum> e = new HashMap();
        private final short f;
        private final String g;

        public static e_enum a(int var0) {
            switch(var0) {
                case 1:
                    return a;
                case 2:
                    return b;
                case 3:
                    return c;
                case 4:
                    return d;
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
            return e.get(var0);
        }

        private e_enum(short var3, String var4) {
            this.f = var3;
            this.g = var4;
        }

        public short getFieldId() {
            return this.f;
        }

        public String getFieldName() {
            return this.g;
        }

        static {
            Iterator var0 = EnumSet.allOf(e_enum.class).iterator();

            while(var0.hasNext()) {
                e_enum var1 = (e_enum)var0.next();
                e.put(var1.getFieldName(), var1);
            }

        }
    }
}
