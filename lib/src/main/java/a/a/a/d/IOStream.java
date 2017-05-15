//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a.a.a.d;


public abstract class IOStream {
    public IOStream() {
    }

    public abstract boolean a();

    public boolean i() {
        return this.a();
    }

    public abstract void b() throws UMErrCodeException;

    public abstract void close();

    public abstract int read(byte[] buffer, int offset, int count) throws UMErrCodeException;

    public int readRemote(byte[] bytes, int offset, int length) throws UMErrCodeException {
        int i = 0;

        int ret;
        for(; i < length; i += ret) {
            ret = this.read(bytes, offset + i, length - i);
            if(ret <= 0) {
                throw new UMErrCodeException("Cannot readByteBuffer. Remote side has closed. Tried to readByteBuffer " + length + " bytes, but only got " + i + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
            }
        }

        return i;
    }

    public void write(byte[] bytes) throws UMErrCodeException {
        this.write(bytes, 0, bytes.length);
    }

    public abstract void write(byte[] bytes, int offset, int length) throws UMErrCodeException;

    public void flush() throws UMErrCodeException {
    }

    public byte[] getBuffer() {
        return null;
    }

    public int getBufferOffset() {
        return 0;
    }

    public int getBufferSize() {
        return -1;
    }

    public void offset(int offset) {
    }
}
