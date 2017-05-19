package com.yxd.sum.obj;

public final class ArrayHeader {
    public final byte type;
    public final int size;

    public ArrayHeader() {
        this((byte)0, 0);
    }

    public ArrayHeader(byte type, int size) {
        this.type = type;
        this.size = size;
    }

    public ArrayHeader(ListHeader listHeader) {
        this(listHeader.type, listHeader.size);
    }
}
