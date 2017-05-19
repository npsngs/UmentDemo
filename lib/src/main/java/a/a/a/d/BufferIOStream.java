//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.d;

public final class BufferIOStream extends IOStream {
    private byte[] buffer;
    private int buferrOffset;
    private int bufferSize;

    public BufferIOStream() {
    }

    public BufferIOStream(byte[] var1) {
        this.setBuffer(var1);
    }

    public BufferIOStream(byte[] var1, int var2, int var3) {
        this.setBuffer(var1, var2, var3);
    }

    public void setBuffer(byte[] buffer) {
        this.setBuffer(buffer, 0, buffer.length);
    }

    public void setBuffer(byte[] buffer, int offset, int length) {
        this.buffer = buffer;
        this.buferrOffset = offset;
        this.bufferSize = offset + length;
    }

    public void clearBuffer() {
        this.buffer = null;
    }

    public void close() {
    }

    public boolean a() {
        return true;
    }

    public void b() throws UMErrCodeException {
    }

    public int read(byte[] var1, int var2, int var3) throws UMErrCodeException {
        int var4 = this.getBufferSize();
        int var5 = var3 > var4?var4:var3;
        if(var5 > 0) {
            System.arraycopy(this.buffer, this.buferrOffset, var1, var2, var5);
            this.offset(var5);
        }

        return var5;
    }

    public void write(byte[] var1, int var2, int var3) throws UMErrCodeException {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    @Override
    public int getBufferOffset() {
        return this.buferrOffset;
    }

    public int getBufferSize() {
        return this.bufferSize - this.buferrOffset;
    }

    public void offset(int offset) {
        this.buferrOffset += offset;
    }
}
