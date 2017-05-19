package com.yxd.sum;

import java.nio.ByteBuffer;

public interface Serializer {

    void startPack(String name) throws Exception;
    void endPackObj() throws Exception;
    void endPackField() throws Exception;
    void packTField(TField tField) throws Exception;
    void packDivider() throws Exception;
    void packMapHeader(MapHeader mapHeader) throws Exception;
    void packListHeader(ListHeader listHeader) throws Exception;
    void packBoolean(boolean b) throws Exception;
    void packByte(byte bt) throws Exception;
    void packUnsignedShort(short value) throws Exception;
    void packUnsignedInt(int value) throws Exception;
    void packUnsignedLong(long value) throws Exception;
    void packDouble(double value) throws Exception;
    void packString(String str) throws Exception;
    void packByteBuffer(ByteBuffer byteBuffer) throws Exception;

    
    String startUnpack() throws Exception;
    void endUnpackObj() throws Exception;

    TField unpackTField() throws Exception;
    MapHeader unpackMapHeader() throws Exception;
    ListHeader unpackListHeader() throws Exception;
    boolean unpackBoolean() throws Exception;
    byte unpackByte() throws Exception;
    short unpackSignedShort() throws Exception;
    int unpackSignedInt() throws Exception;
    long unpackSignedLong() throws Exception;
    double unpackDouble() throws Exception;
    String unpackString() throws Exception;
    ByteBuffer unpackByteBuffer() throws Exception;

}
