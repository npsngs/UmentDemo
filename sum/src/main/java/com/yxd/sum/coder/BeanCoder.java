//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.coder;

import a.a.a.UMException;
import com.yxd.sum.obj.ListHeader;
import com.yxd.sum.obj.MapHeader;
import com.yxd.sum.TField;
import com.yxd.sum.obj.TMessage;
import com.yxd.sum.obj.BeanName;
import com.yxd.sum.obj.ArrayHeader;

import java.nio.ByteBuffer;

public abstract class BeanCoder {
    protected IOStream ioStream;

    private BeanCoder() {
    }

    protected BeanCoder(IOStream ioStream) {
        this.ioStream = ioStream;
    }

    public IOStream getIOStream() {
        return this.ioStream;
    }

    public abstract void write(TMessage tMessage) throws UMException;

    public abstract void a() throws UMException;

    public abstract void startPack(BeanName var1) throws UMException;

    public abstract void endWriteObj() throws UMException;

    public abstract void writeTField(TField tField) throws UMException;

    public abstract void endWriteField() throws UMException;

    public abstract void writeDivider() throws UMException;

    public abstract void writeMapHeader(MapHeader var1) throws UMException;

    public abstract void e() throws UMException;

    public abstract void writeListHeader(ListHeader var1) throws UMException;

    public abstract void f() throws UMException;

    public abstract void writeArrayHeader(ArrayHeader var1) throws UMException;

    public abstract void g() throws UMException;

    public abstract void writeBoolean(boolean var1) throws UMException;

    public abstract void writeByte(byte var1) throws UMException;

    public abstract void writeUnsignedShort(short var1) throws UMException;

    public abstract void writeUnsignedInt(int var1) throws UMException;

    public abstract void writeUnsignedLong(long var1) throws UMException;

    public abstract void writeDouble(double var1) throws UMException;

    public abstract void writeString(String var1) throws UMException;

    public abstract void writeByteBuffer(ByteBuffer byteBuffer) throws UMException;

    public abstract TMessage readMessageBegin() throws UMException;

    public abstract void i() throws UMException;

    public abstract BeanName startUnpack() throws UMException;

    public abstract void popStack() throws UMException;

    public abstract TField readTField() throws UMException;

    public abstract void endReadObj() throws UMException;

    public abstract MapHeader readMapHeader() throws UMException;

    public abstract void o() throws UMException;

    public abstract ListHeader readListHeader() throws UMException;

    public abstract void q() throws UMException;

    public abstract ArrayHeader readArrayHeader() throws UMException;

    public abstract void s() throws UMException;

    public abstract boolean readBoolean() throws UMException;

    public abstract byte readByte() throws UMException;

    public abstract short readSignedShort() throws UMException;

    public abstract int readSignedInt() throws UMException;

    public abstract long readSignedLong() throws UMException;

    public abstract double readDouble() throws UMException;

    public abstract String readString() throws UMException;

    public abstract ByteBuffer readByteBuffer() throws UMException;

    public void reset() {
    }

    public Class<? extends Serializer> getSerializerClass() {
        return NSerializer.class;
    }
}
