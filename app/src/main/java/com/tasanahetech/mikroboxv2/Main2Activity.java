package com.tasanahetech.mikroboxv2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tasanahetech.mikroboxv2.api.ApiConnection;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import static com.tasanahetech.mikroboxv2.api.Config.HOST;
import static com.tasanahetech.mikroboxv2.api.Config.PASSWORD;
import static com.tasanahetech.mikroboxv2.api.Config.USERNAME;

public class Main2Activity extends AppCompatActivity {

    //private final Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

   /*
    private SocketFactory ssl;
    public static ApiConnection con;
    public MainActivity (Context mcontext){
        this.mcontext=mcontext;
        try {
            ssl = ssl();
            con = connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected SocketFactory ssl() throws Exception{
        // Load CAs from an InputStream
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        //local cert
        InputStream caInput = new BufferedInputStream(mcontext.getAssets().open("router.crt"));
        Certificate ca1;
        try {
            ca1 = cf.generateCertificate(caInput);
        } finally {
            caInput.close();
        }

        // Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca1", ca1);

        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // Create an SSLContext that uses our TrustManager
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tmf.getTrustManagers(), null);


        return new TLSFact(context);
    }


    protected  ApiConnection connect() throws Exception {
        ApiConnection con = ApiConnection.connect(ssl, HOST, ApiConnection.DEFAULT_TLS_PORT, ApiConnection.DEFAULT_CONNECTION_TIMEOUT);
        con.login(USERNAME, PASSWORD);
        return con;
    }
    */
}
