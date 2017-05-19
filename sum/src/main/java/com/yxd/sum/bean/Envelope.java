//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.bean;
import com.yxd.sum.obj.TField;
import com.yxd.sum.coder.SerializerGetter;
import com.yxd.sum.coder.Serializer;
import com.yxd.sum.coder.NSerializer;
import com.yxd.sum.coder.OSerializer;
import a.a.a.e_j;
import a.a.a.ByteTool;
import com.yxd.sum.obj.BeanCoderSkiper;
import a.a.a.UMException;
import com.yxd.sum.coder.BeanCoder;
import com.yxd.sum.obj.UMMsgException;
import com.yxd.sum.obj.BeanName;
import com.yxd.sum.coder.BSBeanCoder;

import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Envelope implements SerialBean{
    private static final BeanName name = new BeanName("UMEnvelope");
    private static final TField VERSION = new TField("version", (byte)11, (short)1);
    private static final TField ADDRESS = new TField("address", (byte)11, (short)2);
    private static final TField SIGNATURE = new TField("signature", (byte)11, (short)3);
    private static final TField SERIAL_NUM = new TField("serial_num", (byte)8, (short)4);
    private static final TField TS_SECS = new TField("ts_secs", (byte)8, (short)5);
    private static final TField LENGTH = new TField("length", (byte)8, (short)6);
    private static final TField ENTITY = new TField("entity", (byte)11, (short)7);
    private static final TField GUID = new TField("guid", (byte)11, (short)8);
    private static final TField CHECKSUM = new TField("checksum", (byte)11, (short)9);
    private static final TField CODEX = new TField("codex", (byte)8, (short)10);
    private static final Map<Class<? extends Serializer>, SerializerGetter> x = new HashMap();
    public String version;
    public String address;
    public String signature;
    public int serial_num;
    public int ts_secs;
    public int length;
    public ByteBuffer entity;
    public String guid;
    public String checksum;
    public int codex;
    private byte C;

    public Envelope() {
        this.C = 0;
    }

    public Envelope(String version, String address, String signature, int serial_num, int ts_secs, int length, ByteBuffer entity, String guid, String checksum) {
        this();
        this.version = version;
        this.address = address;
        this.signature = signature;
        this.serial_num = serial_num;
        this.d(true);
        this.ts_secs = ts_secs;
        this.e(true);
        this.length = length;
        this.f(true);
        this.entity = entity;
        this.guid = guid;
        this.checksum = checksum;
    }

    public Envelope(Envelope umEnvelope) {
        this.C = 0;
        this.C = umEnvelope.C;
        if(umEnvelope.hasVersion()) {
            this.version = umEnvelope.version;
        }

        if(umEnvelope.hasAddress()) {
            this.address = umEnvelope.address;
        }

        if(umEnvelope.hasSignature()) {
            this.signature = umEnvelope.signature;
        }

        this.serial_num = umEnvelope.serial_num;
        this.ts_secs = umEnvelope.ts_secs;
        this.length = umEnvelope.length;
        if(umEnvelope.hasEntity()) {
            this.entity = e_j.d(umEnvelope.entity);
        }

        if(umEnvelope.hasGuid()) {
            this.guid = umEnvelope.guid;
        }

        if(umEnvelope.hasChecksum()) {
            this.checksum = umEnvelope.checksum;
        }

        this.codex = umEnvelope.codex;
    }

    public void reset() {
        this.version = null;
        this.address = null;
        this.signature = null;
        this.d(false);
        this.serial_num = 0;
        this.e(false);
        this.ts_secs = 0;
        this.f(false);
        this.length = 0;
        this.entity = null;
        this.guid = null;
        this.checksum = null;
        this.j(false);
        this.codex = 0;
    }

    public String getVersion() {
        return this.version;
    }

    public Envelope setVersion(String version) {
        this.version = version;
        return this;
    }

    public void clearVersion() {
        this.version = null;
    }

    public boolean hasVersion() {
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

    public Envelope setAddress(String address) {
        this.address = address;
        return this;
    }

    public void clearAddress() {
        this.address = null;
    }

    public boolean hasAddress() {
        return this.address != null;
    }

    public void b(boolean var1) {
        if(!var1) {
            this.address = null;
        }

    }

    public String getSignature() {
        return this.signature;
    }

    public Envelope setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public void clearSignature() {
        this.signature = null;
    }

    public boolean hasSignature() {
        return this.signature != null;
    }

    public void c(boolean var1) {
        if(!var1) {
            this.signature = null;
        }

    }

    public int getSerial_num() {
        return this.serial_num;
    }

    public Envelope setSerial(int serial) {
        this.serial_num = serial;
        this.d(true);
        return this;
    }

    public void m() {
        this.C = ByteTool.b(this.C, 0);
    }

    public boolean n() {
        return ByteTool.a(this.C, 0);
    }

    public void d(boolean var1) {
        this.C = ByteTool.a(this.C, 0, var1);
    }

    public int o() {
        return this.ts_secs;
    }

    public Envelope setTimestamp(int ts) {
        this.ts_secs = ts;
        this.e(true);
        return this;
    }

    public void q() {
        this.C = ByteTool.b(this.C, 1);
    }

    public boolean r() {
        return ByteTool.a(this.C, 1);
    }

    public void e(boolean var1) {
        this.C = ByteTool.a(this.C, 1, var1);
    }

    public int getLength() {
        return this.length;
    }

    public Envelope setLength(int length) {
        this.length = length;
        this.f(true);
        return this;
    }

    public void t() {
        this.C = ByteTool.b(this.C, 2);
    }

    public boolean u() {
        return ByteTool.a(this.C, 2);
    }

    public void f(boolean var1) {
        this.C = ByteTool.a(this.C, 2, var1);
    }

    public byte[] v() {
        this.setEntity(e_j.c(this.entity));
        return this.entity == null?null:this.entity.array();
    }

    public ByteBuffer getEntity() {
        return this.entity;
    }

    public Envelope setEntity(byte[] entity) {
        this.setEntity(entity == null?null:ByteBuffer.wrap(entity));
        return this;
    }

    public Envelope setEntity(ByteBuffer entity) {
        this.entity = entity;
        return this;
    }

    public void clearEntity() {
        this.entity = null;
    }

    public boolean hasEntity() {
        return this.entity != null;
    }

    public void g(boolean var1) {
        if(!var1) {
            this.entity = null;
        }

    }

    public String getGuid() {
        return this.guid;
    }

    public Envelope setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public void clearGuid() {
        this.guid = null;
    }

    public boolean hasGuid() {
        return this.guid != null;
    }

    public void h(boolean var1) {
        if(!var1) {
            this.guid = null;
        }

    }

    public String getChecksum() {
        return this.checksum;
    }

    public Envelope setCheckSum(String checkSum) {
        this.checksum = checkSum;
        return this;
    }

    public void clearChecksum() {
        this.checksum = null;
    }

    public boolean hasChecksum() {
        return this.checksum != null;
    }

    public void i(boolean var1) {
        if(!var1) {
            this.checksum = null;
        }

    }

    public int getCodex() {
        return this.codex;
    }

    public Envelope setCodex(int codex) {
        this.codex = codex;
        this.j(true);
        return this;
    }

    public void G() {
        this.C = ByteTool.b(this.C, 3);
    }

    public boolean H() {
        return ByteTool.a(this.C, 3);
    }

    public void j(boolean var1) {
        this.C = ByteTool.a(this.C, 3, var1);
    }


    public void unpackFrom(BeanCoder umBeanCoder) throws UMException {
        x.get(umBeanCoder.getSerializerClass()).getSerializer().unpack(umBeanCoder, this);
    }

    public void packTo(BeanCoder umBeanCoder) throws UMException {
        x.get(umBeanCoder.getSerializerClass()).getSerializer().pack(umBeanCoder, this);
    }

    static {
        x.put(NSerializer.class, new Envelope.b());
        x.put(OSerializer.class, new Envelope.d());
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
        var1.append(this.serial_num);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("ts_secs:");
        var1.append(this.ts_secs);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("length:");
        var1.append(this.length);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("entity:");
        if(this.entity == null) {
            var1.append("null");
        } else {
            e_j.a(this.entity, var1);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("guid:");
        if(this.guid == null) {
            var1.append("null");
        } else {
            var1.append(this.guid);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("checksum:");
        if(this.checksum == null) {
            var1.append("null");
        } else {
            var1.append(this.checksum);
        }

        var2 = false;
        if(this.H()) {
            if(!var2) {
                var1.append(", ");
            }

            var1.append("codex:");
            var1.append(this.codex);
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
        } else if(this.entity == null) {
            throw new UMMsgException("Required field \'entity\' was not present! Struct: " + this.toString());
        } else if(this.guid == null) {
            throw new UMMsgException("Required field \'guid\' was not present! Struct: " + this.toString());
        } else if(this.checksum == null) {
            throw new UMMsgException("Required field \'checksum\' was not present! Struct: " + this.toString());
        }
    }




    private static class c extends OSerializer<Envelope> {
        private c() {
        }

        public void unpack(BeanCoder var1, Envelope var2) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)var1;
            var3.writeString(var2.version);
            var3.writeString(var2.address);
            var3.writeString(var2.signature);
            var3.writeUnsignedInt(var2.serial_num);
            var3.writeUnsignedInt(var2.ts_secs);
            var3.writeUnsignedInt(var2.length);
            var3.writeByteBuffer(var2.entity);
            var3.writeString(var2.guid);
            var3.writeString(var2.checksum);
            BitSet var4 = new BitSet();
            if(var2.H()) {
                var4.set(0);
            }

            var3.a(var4, 1);
            if(var2.H()) {
                var3.writeUnsignedInt(var2.codex);
            }

        }

        public void pack(BeanCoder var1, Envelope var2) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)var1;
            var2.version = var3.readString();
            var2.a(true);
            var2.address = var3.readString();
            var2.b(true);
            var2.signature = var3.readString();
            var2.c(true);
            var2.serial_num = var3.readSignedInt();
            var2.d(true);
            var2.ts_secs = var3.readSignedInt();
            var2.e(true);
            var2.length = var3.readSignedInt();
            var2.f(true);
            var2.entity = var3.readByteBuffer();
            var2.g(true);
            var2.guid = var3.readString();
            var2.h(true);
            var2.checksum = var3.readString();
            var2.i(true);
            BitSet var4 = var3.b(1);
            if(var4.get(0)) {
                var2.codex = var3.readSignedInt();
                var2.j(true);
            }

        }
    }

    private static class d implements SerializerGetter {
        private d() {
        }

        public Envelope.c getSerializer() {
            return new Envelope.c();
        }
    }

    private static class a_inner extends NSerializer<Envelope> {
        private a_inner() {
        }

        public void unpack(BeanCoder umBeanCoder, Envelope umEnvelope) throws UMException {
            umBeanCoder.startUnpack();

            while(true) {
                TField tField = umBeanCoder.readTField();
                if(tField.type == 0) {
                    umBeanCoder.popStack();
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
                            BeanCoderSkiper.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 2:
                        if(tField.type == 11) {
                            umEnvelope.address = umBeanCoder.readString();
                            umEnvelope.b(true);
                        } else {
                            BeanCoderSkiper.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 3:
                        if(tField.type == 11) {
                            umEnvelope.signature = umBeanCoder.readString();
                            umEnvelope.c(true);
                        } else {
                            BeanCoderSkiper.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 4:
                        if(tField.type == 8) {
                            umEnvelope.serial_num = umBeanCoder.readSignedInt();
                            umEnvelope.d(true);
                        } else {
                            BeanCoderSkiper.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 5:
                        if(tField.type == 8) {
                            umEnvelope.ts_secs = umBeanCoder.readSignedInt();
                            umEnvelope.e(true);
                        } else {
                            BeanCoderSkiper.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 6:
                        if(tField.type == 8) {
                            umEnvelope.length = umBeanCoder.readSignedInt();
                            umEnvelope.f(true);
                        } else {
                            BeanCoderSkiper.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 7:
                        if(tField.type == 11) {
                            umEnvelope.entity = umBeanCoder.readByteBuffer();
                            umEnvelope.g(true);
                        } else {
                            BeanCoderSkiper.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 8:
                        if(tField.type == 11) {
                            umEnvelope.guid = umBeanCoder.readString();
                            umEnvelope.h(true);
                        } else {
                            BeanCoderSkiper.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 9:
                        if(tField.type == 11) {
                            umEnvelope.checksum = umBeanCoder.readString();
                            umEnvelope.i(true);
                        } else {
                            BeanCoderSkiper.read(umBeanCoder, tField.type);
                        }
                        break;
                    case 10:
                        if(tField.type == 8) {
                            umEnvelope.codex = umBeanCoder.readSignedInt();
                            umEnvelope.j(true);
                        } else {
                            BeanCoderSkiper.read(umBeanCoder, tField.type);
                        }
                        break;
                    default:
                        BeanCoderSkiper.read(umBeanCoder, tField.type);
                }

                umBeanCoder.endReadObj();
            }
        }

        public void pack(BeanCoder umBeanCoder, Envelope umEnvelope) throws UMException {
            umEnvelope.assertValid();
            umBeanCoder.startPack(Envelope.name);
            if(umEnvelope.version != null) {
                umBeanCoder.writeTField(Envelope.VERSION);
                umBeanCoder.writeString(umEnvelope.version);
                umBeanCoder.endWriteField();
            }

            if(umEnvelope.address != null) {
                umBeanCoder.writeTField(Envelope.ADDRESS);
                umBeanCoder.writeString(umEnvelope.address);
                umBeanCoder.endWriteField();
            }

            if(umEnvelope.signature != null) {
                umBeanCoder.writeTField(Envelope.SIGNATURE);
                umBeanCoder.writeString(umEnvelope.signature);
                umBeanCoder.endWriteField();
            }

            umBeanCoder.writeTField(Envelope.SERIAL_NUM);
            umBeanCoder.writeUnsignedInt(umEnvelope.serial_num);
            umBeanCoder.endWriteField();
            umBeanCoder.writeTField(Envelope.TS_SECS);
            umBeanCoder.writeUnsignedInt(umEnvelope.ts_secs);
            umBeanCoder.endWriteField();
            umBeanCoder.writeTField(Envelope.LENGTH);
            umBeanCoder.writeUnsignedInt(umEnvelope.length);
            umBeanCoder.endWriteField();
            if(umEnvelope.entity != null) {
                umBeanCoder.writeTField(Envelope.ENTITY);
                umBeanCoder.writeByteBuffer(umEnvelope.entity);
                umBeanCoder.endWriteField();
            }

            if(umEnvelope.guid != null) {
                umBeanCoder.writeTField(Envelope.GUID);
                umBeanCoder.writeString(umEnvelope.guid);
                umBeanCoder.endWriteField();
            }

            if(umEnvelope.checksum != null) {
                umBeanCoder.writeTField(Envelope.CHECKSUM);
                umBeanCoder.writeString(umEnvelope.checksum);
                umBeanCoder.endWriteField();
            }

            if(umEnvelope.H()) {
                umBeanCoder.writeTField(Envelope.CODEX);
                umBeanCoder.writeUnsignedInt(umEnvelope.codex);
                umBeanCoder.endWriteField();
            }

            umBeanCoder.writeDivider();
            umBeanCoder.endWriteObj();
        }
    }

    private static class b implements SerializerGetter {
        private b() {
        }

        public Envelope.a_inner getSerializer() {
            return new Envelope.a_inner();
        }
    }
}
