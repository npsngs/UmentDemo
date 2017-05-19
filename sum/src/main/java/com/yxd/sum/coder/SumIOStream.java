//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import a.a.a.d.UMErrCodeException;

public class SumIOStream extends IOStream {
    protected InputStream is = null;
    protected OutputStream os = null;

    protected SumIOStream() {
    }

    public SumIOStream(InputStream is) {
        this.is = is;
    }

    public SumIOStream(OutputStream os) {
        this.os = os;
    }

    public SumIOStream(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
    }

    public boolean a() {
        return true;
    }

    public void b() throws UMErrCodeException {
    }

    public void close() {
        if(this.is != null) {
            try {
                this.is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.is = null;
        }

        if(this.os != null) {
            try {
                this.os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.os = null;
        }

    }

    @Override
    public int read(byte[] data, int offset, int count) throws UMErrCodeException {
        if(this.is == null) {
            throw new UMErrCodeException(1, "Cannot readByteBuffer from null inputStream");
        } else {
            int ret;
            try {
                ret = this.is.read(data, offset, count);
            } catch (IOException e) {
                throw new UMErrCodeException(0, e);
            }

            if(ret < 0) {
                throw new UMErrCodeException(4);
            } else {
                return ret;
            }
        }
    }

    public void write(byte[] buffer, int offset, int count) throws UMErrCodeException {
        if(this.os == null) {
            throw new UMErrCodeException(1, "Cannot writeDouble to null outputStream");
        } else {
            try {
                this.os.write(buffer, offset, count);
            } catch (IOException e) {
                throw new UMErrCodeException(0, e);
            }
        }
    }

    public void flush() throws UMErrCodeException {
        if(this.os == null) {
            throw new UMErrCodeException(1, "Cannot flush null outputStream");
        } else {
            try {
                this.os.flush();
            } catch (IOException e) {
                throw new UMErrCodeException(0, e);
            }
        }
    }
}
