//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxd.sum.report;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class MSSLSocketFactory extends SSLSocketFactory {
    SSLContext sslContext = SSLContext.getInstance("TLS");

    public MSSLSocketFactory(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
        try {
            MX509TrustManager umTrustManager = new MX509TrustManager() {
            };
            this.sslContext.init(null, new TrustManager[]{umTrustManager}, (SecureRandom)null);
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
