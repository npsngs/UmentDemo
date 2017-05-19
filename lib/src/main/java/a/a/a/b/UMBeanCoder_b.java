//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.b;

import a.a.a.UMException;
import a.a.a.ShortStack;
import a.a.a.d.IOStream;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class UMBeanCoder_b extends UMBeanCoder {
    private static final UMName d = new UMName("");
    private static final TField EMPTY_FIELD = new TField("", (byte) 0, (short) 0);
    private static final byte[] f = new byte[16];
    private static final byte h = -126;
    private static final byte i = 1;
    private static final byte j = 31;
    private static final byte k = -32;
    private static final int l = 5;
    private ShortStack shortStack;
    private short n;
    private TField o;
    private Boolean p;
    private final long maxLength;
    byte[] tmpInt;
    byte[] b;
    private byte[] tmpByte;
    byte[] c;

    public UMBeanCoder_b(IOStream ioStream, long maxLength) {
        super(ioStream);
        this.shortStack = new ShortStack(15);
        this.n = 0;
        this.o = null;
        this.p = null;
        this.tmpInt = new byte[5];
        this.b = new byte[10];
        this.tmpByte = new byte[1];
        this.c = new byte[1];
        this.maxLength = maxLength;
    }

    public UMBeanCoder_b(IOStream ioStream) {
        this(ioStream, -1L);
    }

    public void B() {
        this.shortStack.clear();
        this.n = 0;
    }

    public void write(TMessage tMessage) throws UMException {
        this.writeInt(-126);
        this.writeByte(1 | tMessage.type << 5 & -32);
        this.writeInt(tMessage.seqid);
        this.writeString(tMessage.domain);
    }

    public void startPack(UMName var1) throws UMException {
        this.shortStack.push(this.n);
        this.n = 0;
    }

    public void endWriteObj() throws UMException {
        this.n = this.shortStack.pop();
    }

    public void writeTField(TField var1) throws UMException {
        if(var1.type == 2) {
            this.o = var1;
        } else {
            this.a(var1, (byte)-1);
        }

    }

    private void a(TField tField, byte var2) throws UMException {
        byte var3 = var2 == -1?this.map(tField.type):var2;
        if(tField.id > this.n && tField.id - this.n <= 15) {
            this.writeByte(tField.id - this.n << 4 | var3);
        } else {
            this.writeByte(var3);
            this.writeUnsignedShort(tField.id);
        }

        this.n = tField.id;
    }

    public void writeDivider() throws UMException {
        this.writeByte((byte)0);
    }

    public void writeMapHeader(MapHeader mapHeader) throws UMException {
        if(mapHeader.size == 0) {
            this.writeByte(0);
        } else {
            this.writeInt(mapHeader.size);
            this.writeByte(this.map(mapHeader.keyType) << 4 | this.map(mapHeader.valueType));
        }
    }

    public void writeListHeader(ListHeader listHeader) throws UMException {
        this.write(listHeader.type, listHeader.size);
    }

    public void a(l var1) throws UMException {
        this.write(var1.a, var1.b);
    }

    public void writeBoolean(boolean var1) throws UMException {
        if(this.o != null) {
            this.a(this.o, (byte)(var1?1:2));
            this.o = null;
        } else {
            this.writeByte((byte)(var1?1:2));
        }

    }


    public void writeUnsignedShort(short var1) throws UMException {
        this.writeInt(this.toUnsigned((int)var1));
    }

    public void writeUnsignedInt(int var1) throws UMException {
        this.writeInt(this.toUnsigned(var1));
    }

    public void writeUnsignedLong(long var1) throws UMException {
        this.writeLong(this.toUnsigned(var1));
    }

    public void writeDouble(double var1) throws UMException {
        byte[] outBytes = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
        this.long2Bytes(Double.doubleToLongBits(var1), outBytes, 0);
        this.ioStream.write(outBytes);
    }

    public void writeString(String s) throws UMException {
        try {
            byte[] var2 = s.getBytes("UTF-8");
            this.write(var2, 0, var2.length);
        } catch (UnsupportedEncodingException var3) {
            throw new UMException("UTF-8 not supported!");
        }
    }

    public void writeByteBuffer(ByteBuffer byteBuffer) throws UMException {
        int length = byteBuffer.limit() - byteBuffer.position();
        this.write(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), length);
    }

    private void write(byte[] bytes, int offset, int length) throws UMException {
        this.writeInt(length);
        this.ioStream.write(bytes, offset, length);
    }

    public void a() throws UMException {
    }

    public void e() throws UMException {
    }

    public void f() throws UMException {
    }

    public void g() throws UMException {
    }

    public void endWriteField() throws UMException {
    }

    protected void write(byte type, int size) throws UMException {
        if(size <= 14) {
            this.writeByte(size << 4 | this.map(type));
        } else {
            this.writeByte(0xf0 | this.map(type));
            this.writeInt(size);
        }

    }

    private void writeInt(int var1) throws UMException {
        int i;
        for(i = 0; (var1 & -128) != 0; var1 >>>= 7) {
            this.tmpInt[i++] = (byte)(var1 & 127 | 128);
        }

        this.tmpInt[i++] = (byte)var1;
        this.ioStream.write(this.tmpInt, 0, i);
    }

    private void writeLong(long var1) throws UMException {
        int var3;
        for(var3 = 0; (var1 & -128L) != 0L; var1 >>>= 7) {
            this.b[var3++] = (byte)((int)(var1 & 127L | 128L));
        }

        this.b[var3++] = (byte)((int)var1);
        this.ioStream.write(this.b, 0, var3);
    }

    private long toUnsigned(long var1) {
        return var1 << 1 ^ var1 >> 63;
    }

    private int toUnsigned(int var1) {
        return var1 << 1 ^ var1 >> 31;
    }

    private void long2Bytes(long inputLong, byte[] outBytes, int offset) {
        outBytes[offset + 0] = (byte)((int)(inputLong & 255L));
        outBytes[offset + 1] = (byte)((int)(inputLong >> 8 & 255L));
        outBytes[offset + 2] = (byte)((int)(inputLong >> 16 & 255L));
        outBytes[offset + 3] = (byte)((int)(inputLong >> 24 & 255L));
        outBytes[offset + 4] = (byte)((int)(inputLong >> 32 & 255L));
        outBytes[offset + 5] = (byte)((int)(inputLong >> 40 & 255L));
        outBytes[offset + 6] = (byte)((int)(inputLong >> 48 & 255L));
        outBytes[offset + 7] = (byte)((int)(inputLong >> 56 & 255L));
    }

    public void writeByte(byte var1) throws UMException {
        this.tmpByte[0] = var1;
        this.ioStream.write(this.tmpByte);
    }

    private void writeByte(int var1) throws UMException {
        this.writeByte((byte)var1);
    }

    public TMessage readMessageBegin() throws UMException {
        byte protocolId = this.readByte();
        if(protocolId != -126) {
            throw new UMMsgException("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(protocolId));
        } else {
            byte var2 = this.readByte();
            byte version = (byte)(var2 & 31);
            if(version != 1) {
                throw new UMMsgException("Expected version 1 but got " + version);
            } else {
                byte var4 = (byte)(var2 >> 5 & 3);
                int var5 = this.readInt();
                String var6 = this.readString();
                return new TMessage(var6, var4, var5);
            }
        }
    }

    public UMName startUnpack() throws UMException {
        this.shortStack.push(this.n);
        this.n = 0;
        return d;
    }

    public void k() throws UMException {
        this.n = this.shortStack.pop();
    }

    public TField readTField() throws UMException {
        byte protocolId = this.readByte();
        if(protocolId == 0) {
            return EMPTY_FIELD;
        } else {
            short var3 = (short)((protocolId & 0xf0) >> 4);
            short var2;
            if(var3 == 0) {
                var2 = this.readSignedShort();
            } else {
                var2 = (short)(this.n + var3);
            }

            TField tField = new TField("", this.unmap((byte)(protocolId & 0xf)), var2);
            if(this.c(protocolId)) {
                this.p = (byte)(protocolId & 0xf) == 1?Boolean.TRUE:Boolean.FALSE;
            }

            this.n = tField.id;
            return tField;
        }
    }

    public MapHeader readMapHeader() throws UMException {
        int listSize = this.readInt();
        byte var2 = listSize == 0?0:this.readByte();
        return new MapHeader(this.unmap((byte)(var2 >> 4)), this.unmap((byte)(var2 & 15)), listSize);
    }

    public ListHeader readListHeader() throws UMException {
        byte var1 = this.readByte();
        int var2 = var1 >> 4 & 15;
        if(var2 == 0xf) {
            var2 = this.readInt();
        }

        byte type = this.unmap(var1);
        return new ListHeader(type, var2);
    }

    public l r() throws UMException {
        return new l(this.readListHeader());
    }

    public boolean readBoolean() throws UMException {
        if(this.p != null) {
            boolean var1 = this.p.booleanValue();
            this.p = null;
            return var1;
        } else {
            return this.readByte() == 1;
        }
    }

    public byte readByte() throws UMException {
        byte ret;
        if(this.ioStream.getBufferSize() > 0) {
            ret = this.ioStream.getBuffer()[this.ioStream.getBufferOffset()];
            this.ioStream.offset(1);
        } else {
            this.ioStream.readRemote(this.c, 0, 1);
            ret = this.c[0];
        }

        return ret;
    }

    public short readSignedShort() throws UMException {
        return (short)this.toSigned(this.readInt());
    }

    public int readSignedInt() throws UMException {
        return this.toSigned(this.readInt());
    }

    public long readSignedLong() throws UMException {
        return this.toSigned(this.readLong());
    }

    public double readDouble() throws UMException {
        byte[] var1 = new byte[8];
        this.ioStream.readRemote(var1, 0, 8);
        return Double.longBitsToDouble(this.bytes2Long(var1));
    }

    public String readString() throws UMException {
        int len = this.readInt();
        this.assertValidLength(len);
        if(len == 0) {
            return "";
        } else {
            try {
                if(this.ioStream.getBufferSize() >= len) {
                    String ret = new String(this.ioStream.getBuffer(), this.ioStream.getBufferOffset(), len, "UTF-8");
                    this.ioStream.offset(len);
                    return ret;
                } else {
                    return new String(this.readBytes(len), "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                throw new UMException("UTF-8 not supported!");
            }
        }
    }

    public ByteBuffer readByteBuffer() throws UMException {
        int len = this.readInt();
        this.assertValidLength(len);
        if(len == 0) {
            return ByteBuffer.wrap(new byte[0]);
        } else {
            byte[] bytes = new byte[len];
            this.ioStream.readRemote(bytes, 0, len);
            return ByteBuffer.wrap(bytes);
        }
    }

    private byte[] readBytes(int len) throws UMException {
        if(len == 0) {
            return new byte[0];
        } else {
            byte[] ret = new byte[len];
            this.ioStream.readRemote(ret, 0, len);
            return ret;
        }
    }

    private void assertValidLength(int length) throws UMMsgException {
        if(length < 0) {
            throw new UMMsgException("Negative length: " + length);
        } else if(this.maxLength != -1L && (long)length > this.maxLength) {
            throw new UMMsgException("Length exceeded max allowed: " + length);
        }
    }

    public void i() throws UMException {
    }

    public void endReadObj() throws UMException {
    }

    public void o() throws UMException {
    }

    public void q() throws UMException {
    }

    public void s() throws UMException {
    }

    private int readInt() throws UMException {
        int var1 = 0;
        int var2 = 0;
        if(this.ioStream.getBufferSize() >= 5) {
            byte[] buffer = this.ioStream.getBuffer();
            int offset = this.ioStream.getBufferOffset();
            int i = 0;

            while(true) {
                byte var6 = buffer[offset + i];
                var1 |= (var6 & 127) << var2;
                if((var6 & 128) != 128) {
                    this.ioStream.offset(i + 1);
                    break;
                }

                var2 += 7;
                ++i;
            }
        } else {
            while(true) {
                byte var3 = this.readByte();
                var1 |= (var3 & 127) << var2;
                if((var3 & 128) != 128) {
                    break;
                }

                var2 += 7;
            }
        }

        return var1;
    }

    private long readLong() throws UMException {
        int var1 = 0;
        long var2 = 0L;
        if(this.ioStream.getBufferSize() >= 10) {
            byte[] var8 = this.ioStream.getBuffer();
            int var5 = this.ioStream.getBufferOffset();
            int var6 = 0;

            while(true) {
                byte var7 = var8[var5 + var6];
                var2 |= (long)(var7 & 127) << var1;
                if((var7 & 128) != 128) {
                    this.ioStream.offset(var6 + 1);
                    break;
                }

                var1 += 7;
                ++var6;
            }
        } else {
            while(true) {
                byte readByte = this.readByte();
                var2 |= (long)(readByte & 127) << var1;
                if((readByte & 128) != 128) {
                    break;
                }

                var1 += 7;
            }
        }

        return var2;
    }

    private int toSigned(int var1) {
        return var1 >>> 1 ^ -(var1 & 1);
    }

    private long toSigned(long var1) {
        return var1 >>> 1 ^ -(var1 & 1L);
    }

    private long bytes2Long(byte[] bytes) {
        return ((long)bytes[7] & 255L) << 56 | ((long)bytes[6] & 255L) << 48 | ((long)bytes[5] & 255L) << 40 | ((long)bytes[4] & 255L) << 32 | ((long)bytes[3] & 255L) << 24 | ((long)bytes[2] & 255L) << 16 | ((long)bytes[1] & 255L) << 8 | (long)bytes[0] & 255L;
    }

    private boolean c(byte var1) {
        int var2 = var1 & 15;
        return var2 == 1 || var2 == 2;
    }

    private byte unmap(byte var1) throws UMMsgException {
        switch((byte)(var1 & 15)) {
            case 0:
                return 0;
            case 1:
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 6;
            case 5:
                return 8;//int
            case 6:
                return 10;//long
            case 7:
                return 4;
            case 8:
                return 11;//string
            case 9:
                return 15;//list
            case 10:
                return 14;
            case 11:
                return 13;//map
            case 12:
                return 12;//object
            default:
                throw new UMMsgException("don\'getPackageName know what type: " + (byte)(var1 & 15));
        }
    }

    private byte map(byte var1) {
        return f[var1];
    }

    static {
        f[0] = 0;
        f[2] = 1;
        f[3] = 3;
        f[6] = 4;
        f[8] = 5;
        f[10] = 6;
        f[4] = 7;
        f[11] = 8;
        f[15] = 9;
        f[14] = 10;
        f[13] = 11;
        f[12] = 12;
    }

    private static class b_inner {
        public static final byte a = 1;
        public static final byte b = 2;
        public static final byte c = 3;
        public static final byte d = 4;
        public static final byte e = 5;
        public static final byte f = 6;
        public static final byte g = 7;
        public static final byte h = 8;
        public static final byte i = 9;
        public static final byte j = 10;
        public static final byte k = 11;
        public static final byte l = 12;

        private b_inner() {
        }
    }

    public static class UMBeanCoder_b_Builder implements UMBeanCoderBuilder {
        private final long a;

        public UMBeanCoder_b_Builder() {
            this.a = -1L;
        }

        public UMBeanCoder_b_Builder(int var1) {
            this.a = (long)var1;
        }

        public UMBeanCoder build(IOStream ioStream) {
            return new UMBeanCoder_b(ioStream, this.a);
        }
    }
}
