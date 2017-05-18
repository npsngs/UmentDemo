//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.g;
import a.a.a.UMField;
import a.a.a.a.B_b;
import a.a.a.a.C_c;
import a.a.a.b.TField_c;
import a.a.a.b.UMBeanCoder_b;
import a.a.a.c.BeanTransferGetter;
import a.a.a.c.UMBeanTransfer;
import a.a.a.c.UMBeanTransfer_c;
import a.a.a.c.UMBeanTransfer_d;
import a.a.a.d.UMIOStream;
import a.a.a.e_j;
import a.a.a.a_j;
import a.a.a.b.UMBeanCoderEngine;
import a.a.a.UMBean;
import a.a.a.UMException;
import a.a.a.b.UMBeanCoder;
import a.a.a.b.UMMsgException;
import a.a.a.b.Name;
import a.a.a.b.UMBeanCoder_n;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UMEnvelope implements UMBean<UMEnvelope, UMEnvelope.e_enum>, Serializable, Cloneable {
    private static final long l = 420342210744516016L;
    private static final Name m = new Name("UMEnvelope");
    private static final TField_c n = new TField_c("version", (byte)11, (short)1);
    private static final TField_c o = new TField_c("address", (byte)11, (short)2);
    private static final TField_c p = new TField_c("signature", (byte)11, (short)3);
    private static final TField_c q = new TField_c("serial_num", (byte)8, (short)4);
    private static final TField_c r = new TField_c("ts_secs", (byte)8, (short)5);
    private static final TField_c s = new TField_c("length", (byte)8, (short)6);
    private static final TField_c t = new TField_c("entity", (byte)11, (short)7);
    private static final TField_c u = new TField_c("guid", (byte)11, (short)8);
    private static final TField_c v = new TField_c("checksum", (byte)11, (short)9);
    private static final TField_c w = new TField_c("codex", (byte)8, (short)10);
    private static final Map<Class<? extends UMBeanTransfer>, BeanTransferGetter> x = new HashMap();
    public String version;
    public String address;
    public String signature;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;
    private static final int y = 0;
    private static final int z = 1;
    private static final int A = 2;
    private static final int B = 3;
    private byte C;
    private e_enum[] D;
    public static final Map<e_enum, B_b> k_map;

    public UMEnvelope() {
        this.C = 0;
        this.D = new e_enum[]{e_enum.j};
    }

    public UMEnvelope(String var1, String var2, String var3, int var4, int var5, int var6, ByteBuffer var7, String var8, String var9) {
        this();
        this.version = var1;
        this.address = var2;
        this.signature = var3;
        this.d = var4;
        this.d(true);
        this.e = var5;
        this.e(true);
        this.f = var6;
        this.f(true);
        this.g = var7;
        this.h = var8;
        this.i = var9;
    }

    public UMEnvelope(UMEnvelope var1) {
        this.C = 0;
        this.D = new e_enum[]{e_enum.j};
        this.C = var1.C;
        if(var1.e()) {
            this.version = var1.version;
        }

        if(var1.h()) {
            this.address = var1.address;
        }

        if(var1.k()) {
            this.signature = var1.signature;
        }

        this.d = var1.d;
        this.e = var1.e;
        this.f = var1.f;
        if(var1.y()) {
            this.g = e_j.d(var1.g);
        }

        if(var1.B()) {
            this.h = var1.h;
        }

        if(var1.E()) {
            this.i = var1.i;
        }

        this.j = var1.j;
    }

    public UMEnvelope copyOne() {
        return new UMEnvelope(this);
    }

    public void reset() {
        this.version = null;
        this.address = null;
        this.signature = null;
        this.d(false);
        this.d = 0;
        this.e(false);
        this.e = 0;
        this.f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j(false);
        this.j = 0;
    }

    public String getVersion() {
        return this.version;
    }

    public UMEnvelope setVersion(String version) {
        this.version = version;
        return this;
    }

    public void d() {
        this.version = null;
    }

    public boolean e() {
        return this.version != null;
    }

    public void a(boolean var1) {
        if(!var1) {
            this.version = null;
        }

    }

    public String getAddress() {
        return this.address;
    }

    public UMEnvelope setAddress(String address) {
        this.address = address;
        return this;
    }

    public void g() {
        this.address = null;
    }

    public boolean h() {
        return this.address != null;
    }

    public void b(boolean var1) {
        if(!var1) {
            this.address = null;
        }

    }

    public String i() {
        return this.signature;
    }

    public UMEnvelope setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public void j() {
        this.signature = null;
    }

    public boolean k() {
        return this.signature != null;
    }

    public void c(boolean var1) {
        if(!var1) {
            this.signature = null;
        }

    }

    public int l() {
        return this.d;
    }

    public UMEnvelope setSerial(int var1) {
        this.d = var1;
        this.d(true);
        return this;
    }

    public void m() {
        this.C = a_j.b(this.C, 0);
    }

    public boolean n() {
        return a_j.a(this.C, 0);
    }

    public void d(boolean var1) {
        this.C = a_j.a(this.C, 0, var1);
    }

    public int o() {
        return this.e;
    }

    public UMEnvelope setTimestamp(int var1) {
        this.e = var1;
        this.e(true);
        return this;
    }

    public void q() {
        this.C = a_j.b(this.C, 1);
    }

    public boolean r() {
        return a_j.a(this.C, 1);
    }

    public void e(boolean var1) {
        this.C = a_j.a(this.C, 1, var1);
    }

    public int s() {
        return this.f;
    }

    public UMEnvelope setLength(int var1) {
        this.f = var1;
        this.f(true);
        return this;
    }

    public void t() {
        this.C = a_j.b(this.C, 2);
    }

    public boolean u() {
        return a_j.a(this.C, 2);
    }

    public void f(boolean var1) {
        this.C = a_j.a(this.C, 2, var1);
    }

    public byte[] v() {
        this.a(e_j.c(this.g));
        return this.g == null?null:this.g.array();
    }

    public ByteBuffer w() {
        return this.g;
    }

    public UMEnvelope setEntity(byte[] var1) {
        this.a(var1 == null?(ByteBuffer)null:ByteBuffer.wrap(var1));
        return this;
    }

    public UMEnvelope a(ByteBuffer var1) {
        this.g = var1;
        return this;
    }

    public void x() {
        this.g = null;
    }

    public boolean y() {
        return this.g != null;
    }

    public void g(boolean var1) {
        if(!var1) {
            this.g = null;
        }

    }

    public String z() {
        return this.h;
    }

    public UMEnvelope setGuid(String var1) {
        this.h = var1;
        return this;
    }

    public void A() {
        this.h = null;
    }

    public boolean B() {
        return this.h != null;
    }

    public void h(boolean var1) {
        if(!var1) {
            this.h = null;
        }

    }

    public String C() {
        return this.i;
    }

    public UMEnvelope setCheckSum(String var1) {
        this.i = var1;
        return this;
    }

    public void D() {
        this.i = null;
    }

    public boolean E() {
        return this.i != null;
    }

    public void i(boolean var1) {
        if(!var1) {
            this.i = null;
        }

    }

    public int F() {
        return this.j;
    }

    public UMEnvelope setCodex(int var1) {
        this.j = var1;
        this.j(true);
        return this;
    }

    public void G() {
        this.C = a_j.b(this.C, 3);
    }

    public boolean H() {
        return a_j.a(this.C, 3);
    }

    public void j(boolean var1) {
        this.C = a_j.a(this.C, 3, var1);
    }

    public e_enum getUMField(int var1) {
        return e_enum.a(var1);
    }

    public void unpackFrom(UMBeanCoder umBeanCoder) throws UMException {
        x.get(umBeanCoder.getBeanTransferClass()).getBeanTransfer().unpack(umBeanCoder, this);
    }

    public void packTo(UMBeanCoder umBeanCoder) throws UMException {
        x.get(umBeanCoder.getBeanTransferClass()).getBeanTransfer().pack(umBeanCoder, this);
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder("UMEnvelope(");
        boolean var2;
        var1.append("version:");
        if(this.version == null) {
            var1.append("null");
        } else {
            var1.append(this.version);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("address:");
        if(this.address == null) {
            var1.append("null");
        } else {
            var1.append(this.address);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("signature:");
        if(this.signature == null) {
            var1.append("null");
        } else {
            var1.append(this.signature);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("serial_num:");
        var1.append(this.d);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("ts_secs:");
        var1.append(this.e);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("length:");
        var1.append(this.f);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("entity:");
        if(this.g == null) {
            var1.append("null");
        } else {
            e_j.a(this.g, var1);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("guid:");
        if(this.h == null) {
            var1.append("null");
        } else {
            var1.append(this.h);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("checksum:");
        if(this.i == null) {
            var1.append("null");
        } else {
            var1.append(this.i);
        }

        var2 = false;
        if(this.H()) {
            if(!var2) {
                var1.append(", ");
            }

            var1.append("codex:");
            var1.append(this.j);
            var2 = false;
        }

        var1.append(")");
        return var1.toString();
    }

    public void assertValid() throws UMException {
        if(this.version == null) {
            throw new UMMsgException("Required field \'version\' was not present! Struct: " + this.toString());
        } else if(this.address == null) {
            throw new UMMsgException("Required field \'address\' was not present! Struct: " + this.toString());
        } else if(this.signature == null) {
            throw new UMMsgException("Required field \'signature\' was not present! Struct: " + this.toString());
        } else if(this.g == null) {
            throw new UMMsgException("Required field \'entity\' was not present! Struct: " + this.toString());
        } else if(this.h == null) {
            throw new UMMsgException("Required field \'guid\' was not present! Struct: " + this.toString());
        } else if(this.i == null) {
            throw new UMMsgException("Required field \'checksum\' was not present! Struct: " + this.toString());
        }
    }

    private void pack(ObjectOutputStream oos) throws IOException {
        try {
            this.packTo(new UMBeanCoder_b(new UMIOStream(oos)));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private void unpack(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        try {
            this.C = 0;
            this.unpackFrom(new UMBeanCoder_b(new UMIOStream(ois)));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        x.put(UMBeanTransfer_c.class, new UMEnvelope.b());
        x.put(UMBeanTransfer_d.class, new UMEnvelope.d());
        EnumMap var0 = new EnumMap(e_enum.class);
        var0.put(e_enum.a, new B_b("version", (byte)1, new C_c((byte)11)));
        var0.put(e_enum.b, new B_b("address", (byte)1, new C_c((byte)11)));
        var0.put(e_enum.c, new B_b("signature", (byte)1, new C_c((byte)11)));
        var0.put(e_enum.d, new B_b("serial_num", (byte)1, new C_c((byte)8)));
        var0.put(e_enum.e, new B_b("ts_secs", (byte)1, new C_c((byte)8)));
        var0.put(e_enum.f, new B_b("length", (byte)1, new C_c((byte)8)));
        var0.put(e_enum.g, new B_b("entity", (byte)1, new C_c((byte)11, true)));
        var0.put(e_enum.h, new B_b("guid", (byte)1, new C_c((byte)11)));
        var0.put(e_enum.i, new B_b("checksum", (byte)1, new C_c((byte)11)));
        var0.put(e_enum.j, new B_b("codex", (byte)2, new C_c((byte)8)));
        k_map = Collections.unmodifiableMap(var0);
        B_b b = new B_b(null,(byte)1,null);
        b.a(UMEnvelope.class, k_map);
    }

    private static class c extends UMBeanTransfer_d<UMEnvelope> {
        private c() {
        }

        public void unpack(UMBeanCoder var1, UMEnvelope var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
            var3.writeString(var2.version);
            var3.writeString(var2.address);
            var3.writeString(var2.signature);
            var3.writeUnsignedInt(var2.d);
            var3.writeUnsignedInt(var2.e);
            var3.writeUnsignedInt(var2.f);
            var3.writeByteBuffer(var2.g);
            var3.writeString(var2.h);
            var3.writeString(var2.i);
            BitSet var4 = new BitSet();
            if(var2.H()) {
                var4.set(0);
            }

            var3.a(var4, 1);
            if(var2.H()) {
                var3.writeUnsignedInt(var2.j);
            }

        }

        public void pack(UMBeanCoder var1, UMEnvelope var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
            var2.version = var3.readString();
            var2.a(true);
            var2.address = var3.readString();
            var2.b(true);
            var2.signature = var3.readString();
            var2.c(true);
            var2.d = var3.readSignedInt();
            var2.d(true);
            var2.e = var3.readSignedInt();
            var2.e(true);
            var2.f = var3.readSignedInt();
            var2.f(true);
            var2.g = var3.readByteBuffer();
            var2.g(true);
            var2.h = var3.readString();
            var2.h(true);
            var2.i = var3.readString();
            var2.i(true);
            BitSet var4 = var3.b(1);
            if(var4.get(0)) {
                var2.j = var3.readSignedInt();
                var2.j(true);
            }

        }
    }

    private static class d implements BeanTransferGetter {
        private d() {
        }

        public UMEnvelope.c getBeanTransfer() {
            return new UMEnvelope.c();
        }
    }

    private static class a_inner extends UMBeanTransfer_c<UMEnvelope> {
        private a_inner() {
        }

        public void unpack(UMBeanCoder umBeanCoder, UMEnvelope umEnvelope) throws UMException {
            umBeanCoder.startUnpack();

            while(true) {
                TField_c tField = umBeanCoder.readTField();
                if(tField.type == 0) {
                    umBeanCoder.k();
                    if(!umEnvelope.n()) {
                        throw new UMMsgException("Required field \'serial_num\' was not found in serialized data! Struct: " + this.toString());
                    }

                    if(!umEnvelope.r()) {
                        throw new UMMsgException("Required field \'ts_secs\' was not found in serialized data! Struct: " + this.toString());
                    }

                    if(!umEnvelope.u()) {
                        throw new UMMsgException("Required field \'length\' was not found in serialized data! Struct: " + this.toString());
                    }

                    umEnvelope.assertValid();
                    return;
                }

                switch(tField.id) {
                    case 1:
                        if(tField.type == 11) {
                            umEnvelope.version = umBeanCoder.readString();
                            umEnvelope.a(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 2:
                        if(tField.type == 11) {
                            umEnvelope.address = umBeanCoder.readString();
                            umEnvelope.b(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 3:
                        if(tField.type == 11) {
                            umEnvelope.signature = umBeanCoder.readString();
                            umEnvelope.c(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 4:
                        if(tField.type == 8) {
                            umEnvelope.d = umBeanCoder.readSignedInt();
                            umEnvelope.d(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 5:
                        if(tField.type == 8) {
                            umEnvelope.e = umBeanCoder.readSignedInt();
                            umEnvelope.e(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 6:
                        if(tField.type == 8) {
                            umEnvelope.f = umBeanCoder.readSignedInt();
                            umEnvelope.f(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 7:
                        if(tField.type == 11) {
                            umEnvelope.g = umBeanCoder.readByteBuffer();
                            umEnvelope.g(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 8:
                        if(tField.type == 11) {
                            umEnvelope.h = umBeanCoder.readString();
                            umEnvelope.h(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 9:
                        if(tField.type == 11) {
                            umEnvelope.i = umBeanCoder.readString();
                            umEnvelope.i(true);
                        } else {
                            UMBeanCoderEngine.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 10:
                        if(tField.type == 8) {
                            umEnvelope.j = umBeanCoder.readSignedInt();
                            umEnvelope.j(true);
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

        public void pack(UMBeanCoder umBeanCoder, UMEnvelope umEnvelope) throws UMException {
            umEnvelope.assertValid();
            umBeanCoder.startPack(UMEnvelope.m);
            if(umEnvelope.version != null) {
                umBeanCoder.writeTField(UMEnvelope.n);
                umBeanCoder.writeString(umEnvelope.version);
                umBeanCoder.c();
            }

            if(umEnvelope.address != null) {
                umBeanCoder.writeTField(UMEnvelope.o);
                umBeanCoder.writeString(umEnvelope.address);
                umBeanCoder.c();
            }

            if(umEnvelope.signature != null) {
                umBeanCoder.writeTField(UMEnvelope.p);
                umBeanCoder.writeString(umEnvelope.signature);
                umBeanCoder.c();
            }

            umBeanCoder.writeTField(UMEnvelope.q);
            umBeanCoder.writeUnsignedInt(umEnvelope.d);
            umBeanCoder.c();
            umBeanCoder.writeTField(UMEnvelope.r);
            umBeanCoder.writeUnsignedInt(umEnvelope.e);
            umBeanCoder.c();
            umBeanCoder.writeTField(UMEnvelope.s);
            umBeanCoder.writeUnsignedInt(umEnvelope.f);
            umBeanCoder.c();
            if(umEnvelope.g != null) {
                umBeanCoder.writeTField(UMEnvelope.t);
                umBeanCoder.writeByteBuffer(umEnvelope.g);
                umBeanCoder.c();
            }

            if(umEnvelope.h != null) {
                umBeanCoder.writeTField(UMEnvelope.u);
                umBeanCoder.writeString(umEnvelope.h);
                umBeanCoder.c();
            }

            if(umEnvelope.i != null) {
                umBeanCoder.writeTField(UMEnvelope.v);
                umBeanCoder.writeString(umEnvelope.i);
                umBeanCoder.c();
            }

            if(umEnvelope.H()) {
                umBeanCoder.writeTField(UMEnvelope.w);
                umBeanCoder.writeUnsignedInt(umEnvelope.j);
                umBeanCoder.c();
            }

            umBeanCoder.writeDivider();
            umBeanCoder.endPack();
        }
    }

    private static class b implements BeanTransferGetter {
        private b() {
        }

        public UMEnvelope.a_inner getBeanTransfer() {
            return new UMEnvelope.a_inner();
        }
    }

    public static enum e_enum implements UMField {
        a((byte)1, "version"),
        b((byte)2, "address"),
        c((byte)3, "signature"),
        d((byte)4, "serial_num"),
        e((byte)5, "ts_secs"),
        f((byte)6, "length"),
        g((byte)7, "entity"),
        h((byte)8, "guid"),
        i((byte)9, "checksum"),
        j((byte)10, "codex");

        private static final Map<String, UMEnvelope.e_enum> k = new HashMap();
        private final short l;
        private final String m;

        public static UMEnvelope.e_enum a(int var0) {
            switch(var0) {
                case 1:
                    return a;
                case 2:
                    return b;
                case 3:
                    return c;
                case 4:
                    return d;
                case 5:
                    return e;
                case 6:
                    return f;
                case 7:
                    return g;
                case 8:
                    return h;
                case 9:
                    return i;
                case 10:
                    return j;
                default:
                    return null;
            }
        }

        public static UMEnvelope.e_enum b(int var0) {
            UMEnvelope.e_enum var1 = a(var0);
            if(var1 == null) {
                throw new IllegalArgumentException("Field " + var0 + " doesn\'getPackageName exist!");
            } else {
                return var1;
            }
        }

        public static UMEnvelope.e_enum a(String var0) {
            return (UMEnvelope.e_enum)k.get(var0);
        }

        private e_enum(short var3, String var4) {
            this.l = var3;
            this.m = var4;
        }

        public short getFieldId() {
            return this.l;
        }

        public String getFieldName() {
            return this.m;
        }

        static {
            Iterator var0 = EnumSet.allOf(UMEnvelope.e_enum.class).iterator();

            while(var0.hasNext()) {
                UMEnvelope.e_enum var1 = (UMEnvelope.e_enum)var0.next();
                k.put(var1.getFieldName(), var1);
            }

        }
    }
}
