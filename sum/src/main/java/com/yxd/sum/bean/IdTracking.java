//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.bean;


import a.a.a.UMException;
import a.a.a.UMField;
import a.a.a.a.B_b;
import a.a.a.a.C_c;
import a.a.a.a.D_d_a;
import a.a.a.a.E_e_a;
import a.a.a.a.G_g_a;
import com.yxd.sum.obj.ListHeader;
import com.yxd.sum.obj.MapHeader;
import com.yxd.sum.TField;
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
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IdTracking implements SerialBean{
    private static final BeanName NAME = new BeanName("IdTracking");
    private static final TField SNAPSHOTS = new TField("snapshots", (byte)13, (short) 1);
    private static final TField JOURNALS = new TField("journals", (byte)15, (short)2);
    private static final TField CHECKSUM = new TField("checksum", (byte)11, (short)3);
    private static final Map<Class<? extends Serializer>, SerializerGetter> j = new HashMap();
    public Map<String, IdSnapshot> snapshots;
    public List<IdJournal> journals;
    public String checksum;
    public static final Map<e_enum, B_b> d;

    public IdTracking() {
    }

    public IdTracking(Map<String, IdSnapshot> idSnapshot) {
        this();
        this.snapshots = idSnapshot;
    }

    public IdTracking(IdTracking idTracking) {
        Iterator var3;
        if(idTracking.isSnapshotNotNull()) {
            HashMap var2 = new HashMap();
            var3 = idTracking.snapshots.entrySet().iterator();

            while(var3.hasNext()) {
                Entry var4 = (Entry)var3.next();
                String var5 = (String)var4.getKey();
                IdSnapshot var6 = (IdSnapshot)var4.getValue();
                IdSnapshot var8 = new IdSnapshot(var6);
                var2.put(var5, var8);
            }

            this.snapshots = var2;
        }

        if(idTracking.k()) {
            ArrayList var9 = new ArrayList();
            var3 = idTracking.journals.iterator();

            while(var3.hasNext()) {
                IdJournal var10 = (IdJournal)var3.next();
                var9.add(new IdJournal(var10));
            }

            this.journals = var9;
        }

        if(idTracking.isNotNullChecksum()) {
            this.checksum = idTracking.checksum;
        }

    }

    public IdTracking copyOne() {
        return new IdTracking(this);
    }

    public void reset() {
        this.snapshots = null;
        this.journals = null;
        this.checksum = null;
    }

    public int getSnapshotsSize() {
        return this.snapshots == null?0:this.snapshots.size();
    }

    public void putSnapshot(String key, IdSnapshot idSnapshot) {
        if(this.snapshots == null) {
            this.snapshots = new HashMap();
        }

        this.snapshots.put(key, idSnapshot);
    }

    public Map<String, IdSnapshot> getSnapShots() {
        return this.snapshots;
    }

    public IdTracking setSnapshots(Map<String, IdSnapshot> snapshots) {
        this.snapshots = snapshots;
        return this;
    }

    public void clearSnapshot() {
        this.snapshots = null;
    }

    public boolean isSnapshotNotNull() {
        return this.snapshots != null;
    }

    public void a(boolean var1) {
        if(!var1) {
            this.snapshots = null;
        }

    }

    public int getJournalsSize() {
        return this.journals == null?0:this.journals.size();
    }

    public Iterator<IdJournal> iteratorJournals() {
        return this.journals == null?null:this.journals.iterator();
    }

    public void addIdJournal(IdJournal idJournal) {
        if(this.journals == null) {
            this.journals = new ArrayList();
        }

        this.journals.add(idJournal);
    }

    public List<IdJournal> getJournals() {
        return this.journals;
    }

    public IdTracking setJournals(List<IdJournal> journals) {
        this.journals = journals;
        return this;
    }

    public void j() {
        this.journals = null;
    }

    public boolean k() {
        return this.journals != null;
    }

    public void b(boolean var1) {
        if(!var1) {
            this.journals = null;
        }

    }

    public String l() {
        return this.checksum;
    }

    public IdTracking a(String var1) {
        this.checksum = var1;
        return this;
    }

    public void clearChecksum() {
        this.checksum = null;
    }

    public boolean isNotNullChecksum() {
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
        StringBuilder sb = new StringBuilder("IdTracking(");
        boolean var2;
        sb.append("snapshots:");
        if(this.snapshots == null) {
            sb.append("null");
        } else {
            sb.append(this.snapshots);
        }

        var2 = false;
        if(this.k()) {
            if(!var2) {
                sb.append(", ");
            }

            sb.append("journals:");
            if(this.journals == null) {
                sb.append("null");
            } else {
                sb.append(this.journals);
            }

            var2 = false;
        }

        if(this.isNotNullChecksum()) {
            if(!var2) {
                sb.append(", ");
            }

            sb.append("checksum:");
            if(this.checksum == null) {
                sb.append("null");
            } else {
                sb.append(this.checksum);
            }
        }

        sb.append(")");
        return sb.toString();
    }

    public void assertValid() throws UMException {
        if(this.snapshots == null) {
            throw new UMMsgException("Required field \'snapshots\' was not present! Struct: " + this.toString());
        }
    }

    private void pack(ObjectOutputStream oos) throws IOException {
        try {
            this.packTo(new NBeanCoder(new SumIOStream(oos)));
        } catch (UMException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void unpack(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        try {
            this.unpackFrom((BeanCoder)(new NBeanCoder(new SumIOStream(ois))));
        } catch (UMException e) {
            throw new IOException(e.getMessage());
        }
    }

    static {
        j.put(NSerializer.class, new IdTracking.b());
        j.put(OSerializer.class, new IdTracking.d_inner());
        EnumMap var0 = new EnumMap(IdTracking.e_enum.class);
        var0.put(e_enum.a, new B_b("snapshots", (byte)1, new E_e_a((byte)13, new C_c((byte)11), new G_g_a((byte)12, IdSnapshot.class))));
        var0.put(e_enum.b, new B_b("journals", (byte)2, new D_d_a((byte)15, new G_g_a((byte)12, IdJournal.class))));
        var0.put(e_enum.c, new B_b("checksum", (byte)2, new C_c((byte)11)));
        d = Collections.unmodifiableMap(var0);
        B_b.put(IdTracking.class, d);
    }

    private static class c extends OSerializer<IdTracking> {
        private c() {
        }

        public void unpack(BeanCoder var1, IdTracking var2) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)var1;
            var3.writeUnsignedInt(var2.snapshots.size());
            Iterator var4 = var2.snapshots.entrySet().iterator();

            while(var4.hasNext()) {
                Entry var5 = (Entry)var4.next();
                var3.writeString((String)var5.getKey());
                ((IdSnapshot)var5.getValue()).packTo(var3);
            }

            BitSet var7 = new BitSet();
            if(var2.k()) {
                var7.set(0);
            }

            if(var2.isNotNullChecksum()) {
                var7.set(1);
            }

            var3.a(var7, 2);
            if(var2.k()) {
                var3.writeUnsignedInt(var2.journals.size());
                Iterator var8 = var2.journals.iterator();

                while(var8.hasNext()) {
                    IdJournal var6 = (IdJournal)var8.next();
                    var6.packTo(var3);
                }
            }

            if(var2.isNotNullChecksum()) {
                var3.writeString(var2.checksum);
            }

        }

        public void pack(BeanCoder umBeanCoder, IdTracking idTracking) throws UMException {
            BSBeanCoder var3 = (BSBeanCoder)umBeanCoder;
            MapHeader var4 = new MapHeader((byte)11, (byte)12, var3.readSignedInt());
            idTracking.snapshots = new HashMap(2 * var4.size);

            for(int var5 = 0; var5 < var4.size; ++var5) {
                String var6 = var3.readString();
                IdSnapshot var7 = new IdSnapshot();
                var7.unpackFrom(var3);
                idTracking.snapshots.put(var6, var7);
            }

            idTracking.a(true);
            BitSet var8 = var3.b(2);
            if(var8.get(0)) {
                ListHeader var9 = new ListHeader((byte)12, var3.readSignedInt());
                idTracking.journals = new ArrayList(var9.size);

                for(int var10 = 0; var10 < var9.size; ++var10) {
                    IdJournal var11 = new IdJournal();
                    var11.unpackFrom(var3);
                    idTracking.journals.add(var11);
                }

                idTracking.b(true);
            }

            if(var8.get(1)) {
                idTracking.checksum = var3.readString();
                idTracking.c(true);
            }

        }
    }

    private static class d_inner implements SerializerGetter {
        private d_inner() {
        }

        public IdTracking.c getSerializer() {
            return new IdTracking.c();
        }
    }

    private static class a extends NSerializer<IdTracking> {
        private a() {
        }

        public void unpack(BeanCoder umBeanCoder, IdTracking idTracking) throws UMException {
            umBeanCoder.startUnpack();

            while(true) {
                TField tField = umBeanCoder.readTField();
                if(tField.type == 0) {
                    umBeanCoder.popStack();
                    idTracking.assertValid();
                    return;
                }

                int var5;
                switch(tField.id) {
                    case 1:
                        if(tField.type != 13) {
                            BeanCoderSkiper.skip(umBeanCoder, tField.type);
                            break;
                        }

                        MapHeader var8 = umBeanCoder.readMapHeader();
                        idTracking.snapshots = new HashMap(2 * var8.size);

                        for(var5 = 0; var5 < var8.size; ++var5) {
                            String var9 = umBeanCoder.readString();
                            IdSnapshot var7 = new IdSnapshot();
                            var7.unpackFrom(umBeanCoder);
                            idTracking.snapshots.put(var9, var7);
                        }

                        umBeanCoder.o();
                        idTracking.a(true);
                        break;
                    case 2:
                        if(tField.type != 15) {
                            BeanCoderSkiper.skip(umBeanCoder, tField.type);
                            break;
                        }

                        ListHeader var4 = umBeanCoder.readListHeader();
                        idTracking.journals = new ArrayList(var4.size);

                        for(var5 = 0; var5 < var4.size; ++var5) {
                            IdJournal var6 = new IdJournal();
                            var6.unpackFrom(umBeanCoder);
                            idTracking.journals.add(var6);
                        }

                        umBeanCoder.q();
                        idTracking.b(true);
                        break;
                    case 3:
                        if(tField.type == 11) {
                            idTracking.checksum = umBeanCoder.readString();
                            idTracking.c(true);
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

        public void pack(BeanCoder umBeanCoder, IdTracking idTracking) throws UMException {
            idTracking.assertValid();
            umBeanCoder.startPack(IdTracking.NAME);
            Iterator var3;
            if(idTracking.snapshots != null) {
                umBeanCoder.writeTField(IdTracking.SNAPSHOTS);
                umBeanCoder.writeMapHeader(new MapHeader((byte)11, (byte)12, idTracking.snapshots.size()));
                var3 = idTracking.snapshots.entrySet().iterator();

                while(var3.hasNext()) {
                    Entry var4 = (Entry)var3.next();
                    umBeanCoder.writeString((String)var4.getKey());
                    ((IdSnapshot)var4.getValue()).packTo(umBeanCoder);
                }

                umBeanCoder.e();
                umBeanCoder.endWriteField();
            }

            if(idTracking.journals != null && idTracking.k()) {
                umBeanCoder.writeTField(IdTracking.JOURNALS);
                umBeanCoder.writeListHeader(new ListHeader((byte)12, idTracking.journals.size()));
                var3 = idTracking.journals.iterator();

                while(var3.hasNext()) {
                    IdJournal var5 = (IdJournal)var3.next();
                    var5.packTo(umBeanCoder);
                }

                umBeanCoder.f();
                umBeanCoder.endWriteField();
            }

            if(idTracking.checksum != null && idTracking.isNotNullChecksum()) {
                umBeanCoder.writeTField(IdTracking.CHECKSUM);
                umBeanCoder.writeString(idTracking.checksum);
                umBeanCoder.endWriteField();
            }

            umBeanCoder.writeDivider();
            umBeanCoder.endWriteObj();
        }
    }

    private static class b implements SerializerGetter {
        private b() {
        }

        public IdTracking.a getSerializer() {
            return new IdTracking.a();
        }
    }

    public static enum e_enum implements UMField {
        a((byte)1, "snapshots"),
        b((byte)2, "journals"),
        c((byte)3, "checksum");

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
