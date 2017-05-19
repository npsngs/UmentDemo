//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

import a.a.a.UMException;
import a.a.a.c.UMBeanTransfer;
import a.a.a.c.UMBeanTransfer_c;
import a.a.a.d.IOStream;
import java.nio.ByteBuffer;

public abstract class UMBeanCoder {
    protected IOStream ioStream;

    private UMBeanCoder() {
    }

    protected UMBeanCoder(IOStream ioStream) {
        this.ioStream = ioStream;
    }

    public IOStream getIOStream() {
        return this.ioStream;
    }

    public abstract void write(TMessage tMessage) throws UMException;

    public abstract void a() throws UMException;

    public abstract void startPack(UMName var1) throws UMException;

    public abstract void endWriteObj() throws UMException;

    public abstract void writeTField(TField tField) throws UMException;

    public abstract void endWriteField() throws UMException;

    public abstract void writeDivider() throws UMException;

    public abstract void writeMapHeader(MapHeader var1) throws UMException;

    public abstract void e() throws UMException;

    public abstract void writeListHeader(ListHeader var1) throws UMException;

    public abstract void f() throws UMException;

    public abstract void a(l var1) throws UMException;

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

    public abstract UMName startUnpack() throws UMException;

    public abstract void k() throws UMException;

    public abstract TField readTField() throws UMException;

    public abstract void endReadObj() throws UMException;

    public abstract MapHeader readMapHeader() throws UMException;

    public abstract void o() throws UMException;

    public abstract ListHeader readListHeader() throws UMException;

    public abstract void q() throws UMException;

    public abstract l r() throws UMException;

    public abstract void s() throws UMException;

    public abstract boolean readBoolean() throws UMException;

    public abstract byte readByte() throws UMException;

    public abstract short readSignedShort() throws UMException;

    public abstract int readSignedInt() throws UMException;

    public abstract long readSignedLong() throws UMException;

    public abstract double readDouble() throws UMException;

    public abstract String readString() throws UMException;

    public abstract ByteBuffer readByteBuffer() throws UMException;

    public void B() {
    }

    public Class<? extends UMBeanTransfer> getBeanTransferClass() {
        return UMBeanTransfer_c.class;
    }
}
