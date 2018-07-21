package com.hzflk.msg.utils;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/7
 */
public class SSLUtil {

    private static final String PROTOCOL = "TLS";
    private static final String KS_TYPE = "JKS";

    private static String defaultAlgorithm = KeyManagerFactory.getDefaultAlgorithm();


    public static SSLEngine createSSLEngine(KeyManagerFactory kmf, TrustManagerFactory tmf) {

        try {
            SSLContext sslContext = SSLContext.getInstance(PROTOCOL);
            KeyManager[] keyManagers = null;
            if (kmf != null){
                keyManagers = kmf.getKeyManagers();
            }
            TrustManager[] trustManagers = null;
            if (tmf != null){
                trustManagers = tmf.getTrustManagers();
            }
            sslContext.init(keyManagers, trustManagers, null);
            SSLEngine sslEngine = sslContext.createSSLEngine();
            return sslEngine;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static KeyManagerFactory createKeyManagerFactory(String ksPath, String keyPasswd, String storePasswd) {

        try {
            KeyStore ks = KeyStore.getInstance(KS_TYPE);
            FileInputStream in = new FileInputStream(ksPath);
            ks.load(in, storePasswd.toCharArray());

            KeyManagerFactory kmf = KeyManagerFactory.getInstance(defaultAlgorithm);
            kmf.init(ks, keyPasswd.toCharArray());
            return kmf;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static TrustManagerFactory createTrustManager(String tksPath, String storePasswd) {

        try {
            KeyStore tks = KeyStore.getInstance(KS_TYPE);
            FileInputStream in = new FileInputStream(tksPath);
            tks.load(in, storePasswd.toCharArray());

            TrustManagerFactory tf = TrustManagerFactory.getInstance(defaultAlgorithm);
            tf.init(tks);
            return tf;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
