package com.yxd.sum.obj;

public final class ListHeader {
    public final byte type;
    public final int size;

    public ListHeader() {
        this((byte)0, (short)0);
    }

    public ListHeader(byte type, int size) {
        this.type = type;
        this.size = size;
    }
}
