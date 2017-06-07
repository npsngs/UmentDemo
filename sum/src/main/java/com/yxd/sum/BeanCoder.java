package com.yxd.sum;

import a.a.a.UMException;

import com.yxd.sum.coder.IOStream;
import com.yxd.sum.obj.ListHeader;
import com.yxd.sum.obj.MapHeader;
import com.yxd.sum.obj.UMMsgException;
import com.yxd.sum.obj.ArrayHeader;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class BeanCoder {
    private byte[] tmp_i;
    private byte[] tmp_j;
    private byte[] tmp_k;
    private byte[] tmp_m;
    private byte[] tmp_n;
    private byte[] tmp_o;
    private byte[] tmp_p;
    protected IOStream ioStream;
    public BeanCoder(IOStream ioStream) {
        this.ioStream = ioStream;
        this.tmp_i = new byte[1];
        this.tmp_j = new byte[2];
        this.tmp_k = new byte[4];
        this.tmp_m = new byte[1];
        this.tmp_n = new byte[2];
        this.tmp_o = new byte[4];
        this.tmp_p = new byte[8];
    }

    public void writeTField(TField tField) throws UMException {
        this.writeByte(tField.type);
        this.writeUnsignedShort(tField.id);
    }

    public void writeDivider() throws UMException {
        this.writeByte((byte)0);
    }

    public void writeByte(byte var1) throws UMException {
        this.tmp_i[0] = var1;
        this.ioStream.write(this.tmp_i, 0, 1);
    }

    public void writeUnsignedShort(short var1) throws UMException {
        this.tmp_j[0] = (byte)(255 & var1 >> 8);
        this.tmp_j[1] = (byte)(255 & var1);
        this.ioStream.write(this.tmp_j, 0, 2);
    }

    public void writeUnsignedInt(int var1) throws UMException {
        this.tmp_k[0] = (byte)(255 & var1 >> 24);
        this.tmp_k[1] = (byte)(255 & var1 >> 16);
        this.tmp_k[2] = (byte)(255 & var1 >> 8);
        this.tmp_k[3] = (byte)(255 & var1);
        this.ioStream.write(this.tmp_k, 0, 4);
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

    public void writeByteBuffer(ByteBuffer byteBuffer) throws UMException {
        int length = byteBuffer.limit() - byteBuffer.position();
        this.writeUnsignedInt(length);
        this.ioStream.write(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), length);
    }




    public TField readTField() throws UMException {
        byte type = this.readByte();
        short id = type == 0?0:this.readSignedShort();
        return new TField("", type, id);
    }


    public MapHeader readMapHeader() throws UMException {
        return new MapHeader(this.readByte(), this.readByte(), this.readSignedInt());
    }


    public ListHeader readListHeader() throws UMException {
        return new ListHeader(this.readByte(), this.readSignedInt());
    }



    public ArrayHeader readArrayHeader() throws UMException {
        return new ArrayHeader(this.readByte(), this.readSignedInt());
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
            this.readRemote(this.tmp_m, 0, 1);
            return this.tmp_m[0];
        }
    }

    public short readSignedShort() throws UMException {
        byte[] var1 = this.tmp_n;
        int var2 = 0;
        if(this.ioStream.getBufferSize() >= 2) {
            var1 = this.ioStream.getBuffer();
            var2 = this.ioStream.getBufferOffset();
            this.ioStream.offset(2);
        } else {
            this.readRemote(this.tmp_n, 0, 2);
        }

        return (short)((var1[var2] & 255) << 8 | var1[var2 + 1] & 255);
    }

    public int readSignedInt() throws UMException {
        byte[] var1 = this.tmp_o;
        int var2 = 0;
        if(this.ioStream.getBufferSize() >= 4) {
            var1 = this.ioStream.getBuffer();
            var2 = this.ioStream.getBufferOffset();
            this.ioStream.offset(4);
        } else {
            this.readRemote(this.tmp_o, 0, 4);
        }

        return (var1[var2] & 255) << 24 | (var1[var2 + 1] & 255) << 16 | (var1[var2 + 2] & 255) << 8 | var1[var2 + 3] & 255;
    }

    public long readSignedLong() throws UMException {
        byte[] var1 = this.tmp_p;
        int var2 = 0;
        if(this.ioStream.getBufferSize() >= 8) {
            var1 = this.ioStream.getBuffer();
            var2 = this.ioStream.getBufferOffset();
            this.ioStream.offset(8);
        } else {
            this.readRemote(this.tmp_p, 0, 8);
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
            this.assertValidLength(len);
            byte[] var2 = new byte[len];
            this.ioStream.readRemote(var2, 0, len);
            return new String(var2, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            throw new UMException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer readByteBuffer() throws UMException {
        int length = this.readSignedInt();
        this.assertValidLength(length);
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
        this.assertValidLength(length);
        return this.ioStream.readRemote(bytes, offset, length);
    }


    private void assertValidLength(int length) throws UMException {
        if(length < 0) {
            throw new UMMsgException("Negative length: " + length);
        }
    }
}
