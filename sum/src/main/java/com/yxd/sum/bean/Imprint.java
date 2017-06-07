//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.bean;

import a.a.a.UMField;
import a.a.a.a.B_b;
import a.a.a.a.C_c;
import a.a.a.a.E_e_a;
import a.a.a.ByteTool;

import com.yxd.sum.obj.MapHeader;
import com.yxd.sum.TField;
import com.yxd.sum.BeanCoderSkiper;

import a.a.a.UMException;
import a.a.a.a.G_g_a;
import com.yxd.sum.coder.BeanCoder;
import com.yxd.sum.coder.NBeanCoder;
import com.yxd.sum.obj.UMMsgException;
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
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Imprint implements SerialBean{
    private static final BeanName NAME = new BeanName("Imprint");
    private static final TField PROPERTY = new TField("property", (byte) 13, (short) 1);
    private static final TField VERSION = new TField("version", (byte)8, (short)2);
    private static final TField CHECKSUM = new TField("checksum", (byte)11, (short)3);
    private static final Map<Class<? extends Serializer>, SerializerGetter> j = new HashMap();
    public Map<String, ImprintValue> property;
    public int version;
    public String checksum;
    private byte l;
    public static final Map<e_enum, B_b> d;

    public Imprint() {
        this.l = 0;
    }

    public Imprint(Map<String, ImprintValue> var1, int var2, String var3) {
        this();
        this.property = var1;
        this.version = var2;
        this.b(true);
        this.checksum = var3;
    }

    public Imprint(Imprint imprint) {
        this.l = imprint.l;
        if(imprint.hasProperty()) {
            HashMap hashMap = new HashMap();
            Iterator iterator = imprint.property.entrySet().iterator();

            while(iterator.hasNext()) {
                Entry entry = (Entry)iterator.next();
                String key = (String)entry.getKey();
                ImprintValue imprintValue = (ImprintValue)entry.getValue();
                ImprintValue imprintValue1 = new ImprintValue(imprintValue);
                hashMap.put(key, imprintValue1);
            }

            this.property = hashMap;
        }

        this.version = imprint.version;
        if(imprint.hasCheckSum()) {
            this.checksum = imprint.checksum;
        }

    }

    public Imprint copyOne() {
        return new Imprint(this);
    }

    public void reset() {
        this.property = null;
        this.b(false);
        this.version = 0;
        this.checksum = null;
    }

    public int c() {
        return this.property == null?0:this.property.size();
    }

    public void putValue(String key, ImprintValue value) {
        if(this.property == null) {
            this.property = new HashMap();
        }

        this.property.put(key, value);
    }

    public Map<String, ImprintValue> getProperty() {
        return this.property;
    }

    public Imprint setProperty(Map<String, ImprintValue> property) {
        this.property = property;
        return this;
    }

    public void clearProperty() {
        this.property = null;
    }

    public boolean hasProperty() {
        return this.property != null;
    }

    public void a(boolean var1) {
        if(!var1) {
            this.property = null;
        }

    }

    public int getVersion() {
        return this.version;
    }

    public Imprint setVersion(int version) {
        this.version = version;
        this.b(true);
        return this;
    }

    public void h() {
        this.l = ByteTool.b(this.l, 0);
    }

    public boolean i() {
        return ByteTool.a(this.l, 0);
    }

    public void b(boolean var1) {
        this.l = ByteTool.a(this.l, 0, var1);
    }

    public String getCheckSum() {
        return this.checksum;
    }

    public Imprint setCheckSum(String checkSum) {
        this.checksum = checkSum;
        return this;
    }

    public void clearCheckSum() {
        this.checksum = null;
    }

    public boolean hasCheckSum() {
        return this.checksum != null;
    }

    public void c(boolean var1) {
        if(!var1) {
            this.checksum = null;
        }

    }

    public e_enum getUMField(int var1) {
        return e_enum.a(var1);
    }

    public void unpackFrom(BeanCoder umBeanCoder) throws UMException {
        j.get(umBeanCoder.getSerializerClass()).getSerializer().unpack(umBeanCoder, this);
    }

    public void packTo(BeanCoder umBeanCoder) throws UMException {
        j.get(umBeanCoder.getSerializerClass()).getSerializer().pack(umBeanCoder, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Imprint(");
        boolean var2 = true;
        sb.append("property:");
        if(this.property == null) {
            sb.append("null");
        } else {
            sb.append(this.property);
        }

        var2 = false;
        if(!var2) {
            sb.append(", ");
        }

        sb.append("version:");
        sb.append(this.version);
        var2 = false;
        if(!var2) {
            sb.append(", ");
        }

        sb.append("checksum:");
        if(this.checksum == null) {
            sb.append("null");
        } else {
            sb.append(this.checksum);
        }

        sb.append(")");
        return sb.toString();
    }

    public void assertValid() throws UMException {
        if(this.property == null) {
            throw new UMMsgException("Required field \'property\' was not present! Struct: " + this.toString());
        } else if(this.checksum == null) {
            throw new UMMsgException("Required field \'checksum\' was not present! Struct: " + this.toString());
        }
    }

    private void unpackFrom(ObjectOutputStream oos) throws IOException {
        try {
            this.packTo(new NBeanCoder(new SumIOStream(oos)));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private void packTo(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        try {
            this.l = 0;
            this.unpackFrom(new NBeanCoder(new SumIOStream(ois)));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        j.put(NSerializer.class, new Imprint.b());
        j.put(OSerializer.class, new Imprint.d());
        EnumMap enumMap = new EnumMap(e_enum.class);
        enumMap.put(e_enum.PROPERTY, new B_b("property", (byte)1, new E_e_a((byte)13, new C_c((byte)11), new G_g_a((byte)12, ImprintValue.class))));
        enumMap.put(e_enum.VERSION, new B_b("version", (byte)1, new C_c((byte)8)));
        enumMap.put(e_enum.CHECKSUM, new B_b("checksum", (byte)1, new C_c((byte)11)));
        d = Collections.unmodifiableMap(enumMap);
        B_b b = new B_b(null, (byte) 1, null);
        b.put(Imprint.class, d);
    }

    private static class c extends OSerializer<Imprint> {
        private c() {
        }

        public void unpack(BeanCoder var1, Imprint var2) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)var1;
            var3.writeUnsignedInt(var2.property.size());
            Iterator var4 = var2.property.entrySet().iterator();

            while(var4.hasNext()) {
                Entry var5 = (Entry)var4.next();
                var3.writeString((String)var5.getKey());
                ((ImprintValue)var5.getValue()).packTo(var3);
            }

            var3.writeUnsignedInt(var2.version);
            var3.writeString(var2.checksum);
        }

        public void pack(BeanCoder var1, Imprint var2) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)var1;
            MapHeader var4 = new MapHeader((byte)11, (byte)12, var3.readSignedInt());
            var2.property = new HashMap(2 * var4.size);

            for(int var5 = 0; var5 < var4.size; ++var5) {
                String var6 = var3.readString();
                ImprintValue var7 = new ImprintValue();
                var7.unpackFrom(var3);
                var2.property.put(var6, var7);
            }

            var2.a(true);
            var2.version = var3.readSignedInt();
            var2.b(true);
            var2.checksum = var3.readString();
            var2.c(true);
        }
    }

    private static class d implements SerializerGetter {
        private d() {
        }

        public Imprint.c getSerializer() {
            return new Imprint.c();
        }
    }

    private static class a extends NSerializer<Imprint> {
        private a() {
        }

        public void unpack(BeanCoder umBeanCoder, Imprint imprint) throws UMException {
            umBeanCoder.startUnpack();

            while(true) {
                TField tField = umBeanCoder.readTField();
                if(tField.type == 0) {
                    umBeanCoder.popStack();
                    if(!imprint.i()) {
                        throw new UMMsgException("Required field \'version\' was not found in serialized data! Struct: " + this.toString());
                    }

                    imprint.assertValid();
                    return;
                }

                switch(tField.id) {
                    case 1:
                        if(tField.type != 13) {
                            BeanCoderSkiper.skip(umBeanCoder, tField.type);
                            break;
                        }

                        MapHeader mapHeader = umBeanCoder.readMapHeader();
                        imprint.property = new HashMap(2 * mapHeader.size);

                        for(int i = 0; i < mapHeader.size; ++i) {
                            String key = umBeanCoder.readString();
                            ImprintValue imprintValue = new ImprintValue();
                            imprintValue.unpackFrom(umBeanCoder);
                            imprint.property.put(key, imprintValue);
                        }

                        umBeanCoder.o();
                        imprint.a(true);
                        break;
                    case 2:
                        if(tField.type == 8) {
                            imprint.version = umBeanCoder.readSignedInt();
                            imprint.b(true);
                        } else {
                            BeanCoderSkiper.skip(umBeanCoder, tField.type);
                        }
                        break;
                    case 3:
                        if(tField.type == 11) {
                            imprint.checksum = umBeanCoder.readString();
                            imprint.c(true);
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

        public void pack(BeanCoder umBeanCoder, Imprint imprint) throws UMException {
            imprint.assertValid();
            umBeanCoder.startPack(Imprint.NAME);
            if(imprint.property != null) {
                umBeanCoder.writeTField(Imprint.PROPERTY);
                umBeanCoder.writeMapHeader(new MapHeader((byte)11, (byte)12, imprint.property.size()));
                Iterator iterator = imprint.property.entrySet().iterator();

                while(iterator.hasNext()) {
                    Entry entry = (Entry)iterator.next();
                    umBeanCoder.writeString((String)entry.getKey());
                    ((ImprintValue)entry.getValue()).packTo(umBeanCoder);
                }

                umBeanCoder.e();
                umBeanCoder.endWriteField();
            }

            umBeanCoder.writeTField(Imprint.VERSION);
            umBeanCoder.writeUnsignedInt(imprint.version);
            umBeanCoder.endWriteField();
            if(imprint.checksum != null) {
                umBeanCoder.writeTField(Imprint.CHECKSUM);
                umBeanCoder.writeString(imprint.checksum);
                umBeanCoder.endWriteField();
            }

            umBeanCoder.writeDivider();
            umBeanCoder.endWriteObj();
        }
    }

    private static class b implements SerializerGetter {
        private b() {
        }

        public Imprint.a getSerializer() {
            return new Imprint.a();
        }
    }

    public static enum e_enum implements UMField {
        PROPERTY((byte)1, "property"),
        VERSION((byte)2, "version"),
        CHECKSUM((byte)3, "checksum");

        private static final Map<String, Imprint.e_enum> d = new HashMap();
        private final short e;
        private final String f;

        public static Imprint.e_enum a(int var0) {
            switch(var0) {
                case 1:
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        public static Imprint.e_enum b(int var0) {
            Imprint.e_enum var1 = a(var0);
            if(var1 == null) {
                throw new IllegalArgumentException("Field " + var0 + " doesn\'getPackageName exist!");
            } else {
                return var1;
            }
        }

        public static Imprint.e_enum a(String var0) {
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
            Iterator var0 = EnumSet.allOf(Imprint.e_enum.class).iterator();

            while(var0.hasNext()) {
                Imprint.e_enum var1 = (Imprint.e_enum)var0.next();
                d.put(var1.getFieldName(), var1);
            }

        }
    }
}
