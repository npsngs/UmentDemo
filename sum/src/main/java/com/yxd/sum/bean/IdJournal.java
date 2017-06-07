//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.bean;

import a.a.a.UMField;
import a.a.a.a.B_b;
import a.a.a.a.C_c;
import a.a.a.ByteTool;
import com.yxd.sum.TField;

import a.a.a.UMException;
import com.yxd.sum.coder.BeanCoder;
import com.yxd.sum.coder.NBeanCoder;
import com.yxd.sum.obj.UMMsgException;
import com.yxd.sum.BeanCoderSkiper;
import com.yxd.sum.obj.BeanName;
import com.yxd.sum.coder.BSBeanCoder;
import com.yxd.sum.coder.SerializerGetter;
import com.yxd.sum.coder.Serializer;
import com.yxd.sum.coder.NSerializer;
import com.yxd.sum.coder.OSerializer;
import com.yxd.sum.coder.SumIOStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IdJournal implements SerialBean {
    private static final BeanName NAME = new BeanName("IdJournal");
    private static final TField DOMAIN = new TField("domain", (byte)11, (short)1);
    private static final TField OLD_ID = new TField("old_id", (byte)11, (short)2);
    private static final TField NEW_ID = new TField("new_id", (byte)11, (short)3);
    private static final TField TS = new TField("ts", (byte)10, (short)4);
    private static final Map<Class<? extends Serializer>, SerializerGetter> l = new HashMap();
    public String domain;
    public String old_id;
    public String new_id;
    public long ts;
    private static final int m = 0;
    private byte n;
    private e_enum[] o;
    public static final Map<e_enum, B_b> e;

    public IdJournal() {
        this.n = 0;
        this.o = new e_enum[]{e_enum.b};
    }

    public IdJournal(String var1, String var2, long var3) {
        this();
        this.domain = var1;
        this.new_id = var2;
        this.ts = var3;
        this.d(true);
    }

    public IdJournal(IdJournal var1) {
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

        this.ts = var1.ts;
    }

    public IdJournal copyOne() {
        return new IdJournal(this);
    }

    public void reset() {
        this.domain = null;
        this.old_id = null;
        this.new_id = null;
        this.d(false);
        this.ts = 0L;
    }

    public String c() {
        return this.domain;
    }

    public IdJournal p(String var1) {
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

    public IdJournal b(String var1) {
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

    public IdJournal c(String var1) {
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
        return this.ts;
    }

    public IdJournal a(long var1) {
        this.ts = var1;
        this.d(true);
        return this;
    }

    public void m() {
        this.n = ByteTool.b(this.n, 0);
    }

    public boolean n() {
        return ByteTool.a(this.n, 0);
    }

    public void d(boolean var1) {
        this.n = ByteTool.a(this.n, 0, var1);
    }

    public e_enum getUMField(int var1) {
        return e_enum.a(var1);
    }

    public void unpackFrom(BeanCoder var1) throws UMException {
        ((SerializerGetter)l.get(var1.getSerializerClass())).getSerializer().unpack(var1, this);
    }

    public void packTo(BeanCoder var1) throws UMException {
        ((SerializerGetter)l.get(var1.getSerializerClass())).getSerializer().pack(var1, this);
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
        var1.append(this.ts);
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
            this.packTo((BeanCoder)(new NBeanCoder(new SumIOStream(var1))));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private void a(ObjectInputStream var1) throws IOException, ClassNotFoundException {
        try {
            this.n = 0;
            this.unpackFrom((BeanCoder)(new NBeanCoder(new SumIOStream(var1))));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        l.put(NSerializer.class, new IdJournal.b_inner());
        l.put(OSerializer.class, new IdJournal.d());
        EnumMap var0 = new EnumMap(IdJournal.e_enum.class);
        var0.put(e_enum.a, new B_b("domain", (byte)1, new C_c((byte)11)));
        var0.put(e_enum.b, new B_b("old_id", (byte)2, new C_c((byte)11)));
        var0.put(e_enum.c, new B_b("new_id", (byte)1, new C_c((byte)11)));
        var0.put(e_enum.d, new B_b("ts", (byte)1, new C_c((byte)10)));
        e = Collections.unmodifiableMap(var0);
        B_b a = new B_b("",(byte)1,null);
        a.put(IdJournal.class, e);
    }

    private static class c extends OSerializer<IdJournal> {
        private c() {
        }

        public void unpack(BeanCoder var1, IdJournal var2) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)var1;
            var3.writeString(var2.domain);
            var3.writeString(var2.new_id);
            var3.writeUnsignedLong(var2.ts);
            BitSet var4 = new BitSet();
            if(var2.h()) {
                var4.set(0);
            }

            var3.a(var4, 1);
            if(var2.h()) {
                var3.writeString(var2.old_id);
            }

        }

        public void pack(BeanCoder var1, IdJournal var2) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)var1;
            var2.domain = var3.readString();
            var2.a(true);
            var2.new_id = var3.readString();
            var2.c(true);
            var2.ts = var3.readSignedLong();
            var2.d(true);
            BitSet var4 = var3.b(1);
            if(var4.get(0)) {
                var2.old_id = var3.readString();
                var2.b(true);
            }

        }
    }

    private static class d implements SerializerGetter {
        private d() {
        }

        public IdJournal.c getSerializer() {
            return new IdJournal.c();
        }
    }

    private static class a_inner extends NSerializer<IdJournal> {
        private a_inner() {
        }

        public void unpack(BeanCoder umBeanCoder, IdJournal idJournal_b) throws UMException {
            umBeanCoder.startUnpack();

            while(true) {
                TField tField = umBeanCoder.readTField();
                if(tField.type == 0) {
                    umBeanCoder.popStack();
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
                            BeanCoderSkiper.skip(umBeanCoder, tField.type);
                        }
                        break;
                    case 2:
                        if(tField.type == 11) {
                            idJournal_b.old_id = umBeanCoder.readString();
                            idJournal_b.b(true);
                        } else {
                            BeanCoderSkiper.skip(umBeanCoder, tField.type);
                        }
                        break;
                    case 3:
                        if(tField.type == 11) {
                            idJournal_b.new_id = umBeanCoder.readString();
                            idJournal_b.c(true);
                        } else {
                            BeanCoderSkiper.skip(umBeanCoder, tField.type);
                        }
                        break;
                    case 4:
                        if(tField.type == 10) {
                            idJournal_b.ts = umBeanCoder.readSignedLong();
                            idJournal_b.d(true);
                        } else {
                            BeanCoderSkiper.skip(umBeanCoder, tField.type);
                        }
                        break;
                    default:
                        BeanCoderSkiper.skip(umBeanCoder, tField.type);
                }

                umBeanCoder.endReadObj();
            }
        }

        public void pack(BeanCoder umBeanCoder, IdJournal idJournal_b) throws UMException {
            idJournal_b.assertValid();
            umBeanCoder.startPack(IdJournal.NAME);
            if(idJournal_b.domain != null) {
                umBeanCoder.writeTField(IdJournal.DOMAIN);
                umBeanCoder.writeString(idJournal_b.domain);
                umBeanCoder.endWriteField();
            }

            if(idJournal_b.old_id != null && idJournal_b.h()) {
                umBeanCoder.writeTField(IdJournal.OLD_ID);
                umBeanCoder.writeString(idJournal_b.old_id);
                umBeanCoder.endWriteField();
            }

            if(idJournal_b.new_id != null) {
                umBeanCoder.writeTField(IdJournal.NEW_ID);
                umBeanCoder.writeString(idJournal_b.new_id);
                umBeanCoder.endWriteField();
            }

            umBeanCoder.writeTField(IdJournal.TS);
            umBeanCoder.writeUnsignedLong(idJournal_b.ts);
            umBeanCoder.endWriteField();
            umBeanCoder.writeDivider();
            umBeanCoder.endWriteObj();
        }
    }

    private static class b_inner implements SerializerGetter {
        private b_inner() {
        }

        public IdJournal.a_inner getSerializer() {
            return new IdJournal.a_inner();
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
