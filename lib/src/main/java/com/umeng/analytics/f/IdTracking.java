//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.f;


import a.a.a.UMBean;
import a.a.a.UMException;
import a.a.a.UMField;
import a.a.a.a.g;
import a.a.a.b.TField_c;
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

public class IdTracking implements UMBean<IdTracking, IdTracking.e_enum>, Serializable, Cloneable {
    private static final long e = -5764118265293965743L;
    private static final Name f = new Name("IdTracking");
    private static final TField_c g = new TField_c("snapshots", (byte)13, (short) 1);
    private static final TField_c h = new TField_c("journals", (byte)15, (short)2);
    private static final TField_c i = new TField_c("checksum", (byte)11, (short)3);
    private static final Map<Class<? extends UMBeanTransfer>, BeanTransferGetter> j = new HashMap();
    public Map<String, IdSnapshot_c> snapshots;
    public List<IdJournal_b> journals;
    public String checksum;
    private e_enum[] k_enums;
    public static final Map<e_enum, a.a.a.a.b> d;

    public IdTracking() {
        this.k_enums = new e_enum[]{e_enum.b, e_enum.c};
    }

    public IdTracking(Map<String, IdSnapshot_c> var1) {
        this();
        this.snapshots = var1;
    }

    public IdTracking(IdTracking var1) {
        this.k_enums = new e_enum[]{e_enum.b, e_enum.c};
        Iterator var3;
        if(var1.f()) {
            HashMap var2 = new HashMap();
            var3 = var1.snapshots.entrySet().iterator();

            while(var3.hasNext()) {
                Entry var4 = (Entry)var3.next();
                String var5 = (String)var4.getKey();
                IdSnapshot_c var6 = (IdSnapshot_c)var4.getValue();
                IdSnapshot_c var8 = new IdSnapshot_c(var6);
                var2.put(var5, var8);
            }

            this.snapshots = var2;
        }

        if(var1.k()) {
            ArrayList var9 = new ArrayList();
            var3 = var1.journals.iterator();

            while(var3.hasNext()) {
                IdJournal_b var10 = (IdJournal_b)var3.next();
                var9.add(new IdJournal_b(var10));
            }

            this.journals = var9;
        }

        if(var1.n()) {
            this.checksum = var1.checksum;
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

    public int c() {
        return this.snapshots == null?0:this.snapshots.size();
    }

    public void a(String var1, IdSnapshot_c var2) {
        if(this.snapshots == null) {
            this.snapshots = new HashMap();
        }

        this.snapshots.put(var1, var2);
    }

    public Map<String, IdSnapshot_c> getSnapShots() {
        return this.snapshots;
    }

    public IdTracking setSnapshots(Map<String, IdSnapshot_c> snapshots) {
        this.snapshots = snapshots;
        return this;
    }

    public void e() {
        this.snapshots = null;
    }

    public boolean f() {
        return this.snapshots != null;
    }

    public void a(boolean var1) {
        if(!var1) {
            this.snapshots = null;
        }

    }

    public int g() {
        return this.journals == null?0:this.journals.size();
    }

    public Iterator<IdJournal_b> h() {
        return this.journals == null?null:this.journals.iterator();
    }

    public void a(IdJournal_b var1) {
        if(this.journals == null) {
            this.journals = new ArrayList();
        }

        this.journals.add(var1);
    }

    public List<IdJournal_b> getJournals() {
        return this.journals;
    }

    public IdTracking setJournals(List<IdJournal_b> journals) {
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

    public void m() {
        this.checksum = null;
    }

    public boolean n() {
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

    public void unpackFrom(UMBeanCoder var1) throws UMException {
        ((BeanTransferGetter)j.get(var1.getBeanTransferClass())).getBeanTransfer().pack(var1, this);
    }

    public void packTo(UMBeanCoder var1) throws UMException {
        ((BeanTransferGetter)j.get(var1.getBeanTransferClass())).getBeanTransfer().unpack(var1, this);
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

        if(this.n()) {
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

    public void o() throws UMException {
        if(this.snapshots == null) {
            throw new UMMsgException("Required field \'snapshots\' was not present! Struct: " + this.toString());
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
            this.unpackFrom((UMBeanCoder)(new UMBeanCoder_b(new UMIOStream(var1))));
        } catch (UMException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        j.put(UMBeanTransfer_c.class, new IdTracking.b());
        j.put(UMBeanTransfer_d.class, new IdTracking.d_inner());
        EnumMap var0 = new EnumMap(IdTracking.e_enum.class);
        var0.put(e_enum.a, new a.a.a.a.b("snapshots", (byte)1, new a.a.a.a.e((byte)13, new a.a.a.a.c((byte)11), new g((byte)12, IdSnapshot_c.class))));
        var0.put(e_enum.b, new a.a.a.a.b("journals", (byte)2, new a.a.a.a.d((byte)15, new g((byte)12, IdJournal_b.class))));
        var0.put(e_enum.c, new a.a.a.a.b("checksum", (byte)2, new a.a.a.a.c((byte)11)));
        d = Collections.unmodifiableMap(var0);
        a.a.a.a.b b = new a.a.a.a.b(null, (byte)1, null);
        b.a(IdTracking.class, d);
    }

    private static class c extends UMBeanTransfer_d<IdTracking> {
        private c() {
        }

        public void unpack(UMBeanCoder var1, IdTracking var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
            var3.writeUnsignedInt(var2.snapshots.size());
            Iterator var4 = var2.snapshots.entrySet().iterator();

            while(var4.hasNext()) {
                Entry var5 = (Entry)var4.next();
                var3.writeString((String)var5.getKey());
                ((IdSnapshot_c)var5.getValue()).packTo(var3);
            }

            BitSet var7 = new BitSet();
            if(var2.k()) {
                var7.set(0);
            }

            if(var2.n()) {
                var7.set(1);
            }

            var3.a(var7, 2);
            if(var2.k()) {
                var3.writeUnsignedInt(var2.journals.size());
                Iterator var8 = var2.journals.iterator();

                while(var8.hasNext()) {
                    IdJournal_b var6 = (IdJournal_b)var8.next();
                    var6.packTo(var3);
                }
            }

            if(var2.n()) {
                var3.writeString(var2.checksum);
            }

        }

        public void pack(UMBeanCoder var1, IdTracking var2) throws UMException {
            UMBeanCoder_n var3 = (UMBeanCoder_n)var1;
            a.a.a.b.e var4 = new a.a.a.b.e((byte)11, (byte)12, var3.readSignedInt());
            var2.snapshots = new HashMap(2 * var4.size);

            for(int var5 = 0; var5 < var4.size; ++var5) {
                String var6 = var3.readString();
                IdSnapshot_c var7 = new IdSnapshot_c();
                var7.unpackFrom(var3);
                var2.snapshots.put(var6, var7);
            }

            var2.a(true);
            BitSet var8 = var3.b(2);
            if(var8.get(0)) {
                a.a.a.b.d var9 = new a.a.a.b.d((byte)12, var3.readSignedInt());
                var2.journals = new ArrayList(var9.b);

                for(int var10 = 0; var10 < var9.b; ++var10) {
                    IdJournal_b var11 = new IdJournal_b();
                    var11.unpackFrom(var3);
                    var2.journals.add(var11);
                }

                var2.b(true);
            }

            if(var8.get(1)) {
                var2.checksum = var3.readString();
                var2.c(true);
            }

        }
    }

    private static class d_inner implements BeanTransferGetter {
        private d_inner() {
        }

        public IdTracking.c getBeanTransfer() {
            return new IdTracking.c();
        }
    }

    private static class a extends UMBeanTransfer_c<IdTracking> {
        private a() {
        }

        public void unpack(UMBeanCoder var1, IdTracking var2) throws UMException {
            var1.startUnpack();

            while(true) {
                TField_c var3 = var1.readTField();
                if(var3.type == 0) {
                    var1.k();
                    var2.o();
                    return;
                }

                int var5;
                switch(var3.id) {
                    case 1:
                        if(var3.type != 13) {
                            UMBeanCoderEngine.read(var1, var3.type);
                            break;
                        }

                        a.a.a.b.e var8 = var1.n();
                        var2.snapshots = new HashMap(2 * var8.size);

                        for(var5 = 0; var5 < var8.size; ++var5) {
                            String var9 = var1.readString();
                            IdSnapshot_c var7 = new IdSnapshot_c();
                            var7.unpackFrom(var1);
                            var2.snapshots.put(var9, var7);
                        }

                        var1.o();
                        var2.a(true);
                        break;
                    case 2:
                        if(var3.type != 15) {
                            UMBeanCoderEngine.read(var1, var3.type);
                            break;
                        }

                        a.a.a.b.d var4 = var1.p();
                        var2.journals = new ArrayList(var4.b);

                        for(var5 = 0; var5 < var4.b; ++var5) {
                            IdJournal_b var6 = new IdJournal_b();
                            var6.unpackFrom(var1);
                            var2.journals.add(var6);
                        }

                        var1.q();
                        var2.b(true);
                        break;
                    case 3:
                        if(var3.type == 11) {
                            var2.checksum = var1.readString();
                            var2.c(true);
                        } else {
                            UMBeanCoderEngine.read(var1, var3.type);
                        }
                        break;
                    default:
                        UMBeanCoderEngine.read(var1, var3.type);
                }

                var1.m();
            }
        }

        public void pack(UMBeanCoder var1, IdTracking var2) throws UMException {
            var2.o();
            var1.startPack(IdTracking.f);
            Iterator var3;
            if(var2.snapshots != null) {
                var1.writeTField(IdTracking.g);
                var1.a(new a.a.a.b.e((byte)11, (byte)12, var2.snapshots.size()));
                var3 = var2.snapshots.entrySet().iterator();

                while(var3.hasNext()) {
                    Entry var4 = (Entry)var3.next();
                    var1.writeString((String)var4.getKey());
                    ((IdSnapshot_c)var4.getValue()).packTo(var1);
                }

                var1.e();
                var1.c();
            }

            if(var2.journals != null && var2.k()) {
                var1.writeTField(IdTracking.h);
                var1.a(new a.a.a.b.d((byte)12, var2.journals.size()));
                var3 = var2.journals.iterator();

                while(var3.hasNext()) {
                    IdJournal_b var5 = (IdJournal_b)var3.next();
                    var5.packTo(var1);
                }

                var1.f();
                var1.c();
            }

            if(var2.checksum != null && var2.n()) {
                var1.writeTField(IdTracking.i);
                var1.writeString(var2.checksum);
                var1.c();
            }

            var1.d();
            var1.b();
        }
    }

    private static class b implements BeanTransferGetter {
        private b() {
        }

        public IdTracking.a getBeanTransfer() {
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
