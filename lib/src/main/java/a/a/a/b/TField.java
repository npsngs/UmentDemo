//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

public class TField {
    public final String domain;
    public final byte type;
    public final short id;

    public TField() {
        this("", (byte)0, (short)0);
    }

    public TField(String domain, byte type, short id) {
        this.domain = domain;
        this.type = type;
        this.id = id;
    }

    public String toString() {
        return "<TField domain:\'" + this.domain + "\' type:" + this.type + " field-id:" + this.id + ">";
    }

    public boolean isEqual(TField tField) {
        return this.type == tField.type && this.id == tField.id;
    }
}
