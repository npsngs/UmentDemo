//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.d;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

class UMTrustManager implements X509TrustManager {
    X509TrustManager manager;

    public UMTrustManager() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore)null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            for(int i = 0; i < trustManagers.length; ++i) {
                if(trustManagers[i] instanceof X509TrustManager) {
                    this.manager = (X509TrustManager)trustManagers[i];
                    return;
                }
            }
        } catch (Throwable throwable) {
        }

    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        try {
            this.manager.checkServerTrusted(chain, authType);
        } catch (CertificateException e) {
        }

    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.manager.getAcceptedIssuers();
    }
}
