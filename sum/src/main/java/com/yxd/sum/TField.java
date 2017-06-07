package com.yxd.sum;

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

}
