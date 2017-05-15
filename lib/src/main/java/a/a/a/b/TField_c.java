//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

public class TField_c {
    public final String name;
    public final byte type;
    public final short id;

    public TField_c() {
        this("", (byte)0, (short)0);
    }

    public TField_c(String name, byte type, short id) {
        this.name = name;
        this.type = type;
        this.id = id;
    }

    public String toString() {
        return "<TField domain:\'" + this.name + "\' type:" + this.type + " field-id:" + this.id + ">";
    }

    public boolean isEqual(TField_c tField_c) {
        return this.type == tField_c.type && this.id == tField_c.id;
    }
}
