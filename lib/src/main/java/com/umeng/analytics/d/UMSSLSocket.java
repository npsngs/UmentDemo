//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.d;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class UMSSLSocket extends SSLSocketFactory {
    SSLContext sslContext = SSLContext.getInstance("TLS");

    public UMSSLSocket(KeyStore var1) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(var1);

        try {
            UMTrustManager umTrustManager = new UMTrustManager() {
            };
            this.sslContext.init((KeyManager[])null, new TrustManager[]{umTrustManager}, (SecureRandom)null);
        } catch (Throwable t) {
        }

    }

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
        return this.sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
    }

    public Socket createSocket() throws IOException {
        return this.sslContext.getSocketFactory().createSocket();
    }
}
