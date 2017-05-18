//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

import a.a.a.UMException;
import a.a.a.d.IOStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class UMBeanCoder_a extends UMBeanCoder {
    private static final Name h = new Name();
    protected static final int a = -65536;
    protected static final int b = -2147418112;
    protected boolean c;
    protected boolean d;
    protected int messageMaxLength;
    protected boolean isLimitLength;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;

    public UMBeanCoder_a(IOStream var1) {
        this(var1, false, true);
    }

    public UMBeanCoder_a(IOStream var1, boolean var2, boolean var3) {
        super(var1);
        this.c = false;
        this.d = true;
        this.isLimitLength = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.c = var2;
        this.d = var3;
    }

    public void write(TMessage tMessage) throws UMException {
        if(this.d) {
            int var2 = -2147418112 | tMessage.type;
            this.writeUnsignedInt(var2);
            this.writeString(tMessage.name);
            this.writeUnsignedInt(tMessage.seqid);
        } else {
            this.writeString(tMessage.name);
            this.writeByte(tMessage.type);
            this.writeUnsignedInt(tMessage.seqid);
        }

    }

    public void a() {
    }

    public void startPack(Name var1) {
    }

    public void endPack() {
    }

    public void writeTField(TField_c tField) throws UMException {
        this.writeByte(tField.type);
        this.writeUnsignedShort(tField.id);
    }

    public void c() {
    }

    public void writeDivider() throws UMException {
        this.writeByte((byte)0);
    }

    public void writeMapHeader(MapHeader var1) throws UMException {
        this.writeByte(var1.keyType);
        this.writeByte(var1.valueType);
        this.writeUnsignedInt(var1.size);
    }

    public void e() {
    }

    public void writeListHeader(ListHeader var1) throws UMException {
        this.writeByte(var1.type);
        this.writeUnsignedInt(var1.size);
    }

    public void f() {
    }

    public void a(l var1) throws UMException {
        this.writeByte(var1.a);
        this.writeUnsignedInt(var1.b);
    }

    public void g() {
    }

    public void writeBoolean(boolean var1) throws UMException {
        this.writeByte((byte)(var1?1:0));
    }

    public void writeByte(byte var1) throws UMException {
        this.i[0] = var1;
        this.ioStream.write(this.i, 0, 1);
    }

    public void writeUnsignedShort(short var1) throws UMException {
        this.j[0] = (byte)(255 & var1 >> 8);
        this.j[1] = (byte)(255 & var1);
        this.ioStream.write(this.j, 0, 2);
    }

    public void writeUnsignedInt(int var1) throws UMException {
        this.k[0] = (byte)(255 & var1 >> 24);
        this.k[1] = (byte)(255 & var1 >> 16);
        this.k[2] = (byte)(255 & var1 >> 8);
        this.k[3] = (byte)(255 & var1);
        this.ioStream.write(this.k, 0, 4);
    }

    public void writeUnsignedLong(long var1) throws UMException {
        this.l[0] = (byte)((int)(255L & var1 >> 56));
        this.l[1] = (byte)((int)(255L & var1 >> 48));
        this.l[2] = (byte)((int)(255L & var1 >> 40));
        this.l[3] = (byte)((int)(255L & var1 >> 32));
        this.l[4] = (byte)((int)(255L & var1 >> 24));
        this.l[5] = (byte)((int)(255L & var1 >> 16));
        this.l[6] = (byte)((int)(255L & var1 >> 8));
        this.l[7] = (byte)((int)(255L & var1));
        this.ioStream.write(this.l, 0, 8);
    }

    public void writeDouble(double var1) throws UMException {
        this.writeUnsignedLong(Double.doubleToLongBits(var1));
    }

    public void writeString(String var1) throws UMException {
        try {
            byte[] var2 = var1.getBytes("UTF-8");
            this.writeUnsignedInt(var2.length);
            this.ioStream.write(var2, 0, var2.length);
        } catch (UnsupportedEncodingException var3) {
            throw new UMException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void writeByteBuffer(ByteBuffer var1) throws UMException {
        int var2 = var1.limit() - var1.position();
        this.writeUnsignedInt(var2);
        this.ioStream.write(var1.array(), var1.position() + var1.arrayOffset(), var2);
    }

    public TMessage readMessageBegin() throws UMException {
        int var1 = this.readSignedInt();
        if(var1 < 0) {
            int var2 = var1 & -65536;
            if(var2 != -2147418112) {
                throw new UMMsgException(4, "Bad version in readMessageBegin");
            } else {
                return new TMessage(this.readString(), (byte)(var1 & 255), this.readSignedInt());
            }
        } else if(this.c) {
            throw new UMMsgException(4, "Missing version in readMessageBegin, old client?");
        } else {
            return new TMessage(this.readString(var1), this.readByte(), this.readSignedInt());
        }
    }

    public void i() {
    }

    public Name startUnpack() {
        return h;
    }

    public void k() {
    }

    public TField_c readTField() throws UMException {
        byte type = this.readByte();
        short id = type == 0?0:this.readSignedShort();
        return new TField_c("", type, id);
    }

    public void m() {
    }

    public MapHeader readMapHeader() throws UMException {
        return new MapHeader(this.readByte(), this.readByte(), this.readSignedInt());
    }

    public void o() {
    }

    public ListHeader readListHeader() throws UMException {
        return new ListHeader(this.readByte(), this.readSignedInt());
    }

    public void q() {
    }

    public l r() throws UMException {
        return new l(this.readByte(), this.readSignedInt());
    }

    public void s() {
    }

    public boolean readBoolean() throws UMException {
        return this.readByte() == 1;
    }

    public byte readByte() throws UMException {
        if(this.ioStream.getBufferSize() >= 1) {
            byte var1 = this.ioStream.getBuffer()[this.ioStream.getBufferOffset()];
            this.ioStream.offset(1);
            return var1;
        } else {
            this.readRemote(this.m, 0, 1);
            return this.m[0];
        }
    }

    public short readSignedShort() throws UMException {
        byte[] var1 = this.n;
        int var2 = 0;
        if(this.ioStream.getBufferSize() >= 2) {
            var1 = this.ioStream.getBuffer();
            var2 = this.ioStream.getBufferOffset();
            this.ioStream.offset(2);
        } else {
            this.readRemote(this.n, 0, 2);
        }

        return (short)((var1[var2] & 255) << 8 | var1[var2 + 1] & 255);
    }

    public int readSignedInt() throws UMException {
        byte[] var1 = this.o;
        int var2 = 0;
        if(this.ioStream.getBufferSize() >= 4) {
            var1 = this.ioStream.getBuffer();
            var2 = this.ioStream.getBufferOffset();
            this.ioStream.offset(4);
        } else {
            this.readRemote(this.o, 0, 4);
        }

        return (var1[var2] & 255) << 24 | (var1[var2 + 1] & 255) << 16 | (var1[var2 + 2] & 255) << 8 | var1[var2 + 3] & 255;
    }

    public long readSignedLong() throws UMException {
        byte[] var1 = this.p;
        int var2 = 0;
        if(this.ioStream.getBufferSize() >= 8) {
            var1 = this.ioStream.getBuffer();
            var2 = this.ioStream.getBufferOffset();
            this.ioStream.offset(8);
        } else {
            this.readRemote(this.p, 0, 8);
        }

        return (long)(var1[var2] & 255) << 56 | (long)(var1[var2 + 1] & 255) << 48 | (long)(var1[var2 + 2] & 255) << 40 | (long)(var1[var2 + 3] & 255) << 32 | (long)(var1[var2 + 4] & 255) << 24 | (long)(var1[var2 + 5] & 255) << 16 | (long)(var1[var2 + 6] & 255) << 8 | (long)(var1[var2 + 7] & 255);
    }

    public double readDouble() throws UMException {
        return Double.longBitsToDouble(this.readSignedLong());
    }

    public String readString() throws UMException {
        int var1 = this.readSignedInt();
        if(this.ioStream.getBufferSize() >= var1) {
            try {
                String var2 = new String(this.ioStream.getBuffer(), this.ioStream.getBufferOffset(), var1, "UTF-8");
                this.ioStream.offset(var1);
                return var2;
            } catch (UnsupportedEncodingException var3) {
                throw new UMException("JVM DOES NOT SUPPORT UTF-8");
            }
        } else {
            return this.readString(var1);
        }
    }

    public String readString(int len) throws UMException {
        try {
            this.assertValidMessageLen(len);
            byte[] var2 = new byte[len];
            this.ioStream.readRemote(var2, 0, len);
            return new String(var2, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            throw new UMException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer readByteBuffer() throws UMException {
        int length = this.readSignedInt();
        this.assertValidMessageLen(length);
        if(this.ioStream.getBufferSize() >= length) {
            ByteBuffer var3 = ByteBuffer.wrap(this.ioStream.getBuffer(), this.ioStream.getBufferOffset(), length);
            this.ioStream.offset(length);
            return var3;
        } else {
            byte[] var2 = new byte[length];
            this.ioStream.readRemote(var2, 0, length);
            return ByteBuffer.wrap(var2);
        }
    }

    private int readRemote(byte[] bytes, int offset, int length) throws UMException {
        this.assertValidMessageLen(length);
        return this.ioStream.readRemote(bytes, offset, length);
    }

    public void setMessageMaxLen(int messageMaxLen) {
        this.messageMaxLength = messageMaxLen;
        this.isLimitLength = true;
    }

    protected void assertValidMessageLen(int length) throws UMException {
        if(length < 0) {
            throw new UMMsgException("Negative length: " + length);
        } else {
            if(this.isLimitLength) {
                this.messageMaxLength -= length;
                if(this.messageMaxLength < 0) {
                    throw new UMMsgException("Message length exceeded: " + length);
                }
            }

        }
    }

    public static class a_inner implements UMBeanCoderBuilder {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a_inner() {
            this(false, true);
        }

        public a_inner(boolean var1, boolean var2) {
            this(var1, var2, 0);
        }

        public a_inner(boolean var1, boolean var2, int var3) {
            this.a = false;
            this.b = true;
            this.a = var1;
            this.b = var2;
            this.c = var3;
        }

        public UMBeanCoder build(IOStream ioStream) {
            UMBeanCoder_a var2 = new UMBeanCoder_a(ioStream, this.a, this.b);
            if(this.c != 0) {
                var2.setMessageMaxLen(this.c);
            }

            return var2;
        }
    }
}
