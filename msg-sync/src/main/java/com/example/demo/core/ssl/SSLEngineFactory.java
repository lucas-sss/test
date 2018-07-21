package com.hzflk.msg.core.ssl;

import com.hzflk.msg.utils.SSLUtil;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/6/29
 */
public class SSLEngineFactory {

    private SSLEngine sslEngine;

    private String ksPath;
    private String keyPasswd;
    private String storePasswd;

    private String tksPath;
    private String tstorePasswd;

    private boolean client = false;

    public SSLEngineFactory() {
    }

    public SSLEngineFactory(boolean client) {
        this.client = client;
    }

    public void init(){
        KeyManagerFactory kmf = null;
        if (StringUtils.isNoneBlank(ksPath, keyPasswd, storePasswd)){
            kmf = SSLUtil.createKeyManagerFactory(ksPath, keyPasswd, storePasswd);
        }
        TrustManagerFactory tmf = null;
        if (StringUtils.isNoneBlank(tksPath, tstorePasswd)){
            tmf = SSLUtil.createTrustManager(tksPath, tstorePasswd);
        }
        this.sslEngine = SSLUtil.createSSLEngine(kmf, tmf);
    }

    public SSLEngine getSSLEngine(){
        if (null == sslEngine){
            return null;
        }
        if (client){
            sslEngine.setNeedClientAuth(true);
        }else {
            sslEngine.setNeedClientAuth(true);
        }

        return this.sslEngine;
    }

    public void setSslEngine(SSLEngine sslEngine) {
        this.sslEngine = sslEngine;
    }

    public void setKsPath(String ksPath) {
        this.ksPath = ksPath;
    }

    public void setKeyPasswd(String keyPasswd) {
        this.keyPasswd = keyPasswd;
    }

    public void setStorePasswd(String storePasswd) {
        this.storePasswd = storePasswd;
    }

    public void setTksPath(String tksPath) {
        this.tksPath = tksPath;
    }

    public void setTstorePasswd(String tStorePasswd) {
        this.tstorePasswd = tStorePasswd;
    }
}
