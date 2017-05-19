//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum;

public final class MapHeader {
    public final byte keyType;
    public final byte valueType;
    public final int size;

    public MapHeader() {
        this((byte)0, (byte)0, 0);
    }

    public MapHeader(byte keyType, byte valueType, int size) {
        this.keyType = keyType;
        this.valueType = valueType;
        this.size = size;
    }
}
