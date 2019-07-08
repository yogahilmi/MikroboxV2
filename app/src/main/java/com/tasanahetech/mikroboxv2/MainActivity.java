package com.tasanahetech.mikroboxv2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.SSLCertificateSocketFactory;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CheckBox;
import android.support.v7.widget.Toolbar;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.ApiConnectionException;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class MainActivity extends AppCompatActivity {

    private SocketFactory ssl;

    //Agar API dapat diakses dari Activity / Fragment yang berbeda
    public static ApiConnection con;
    public static ApiConnection getCon()
    {
        if(con != null)
            return con;
        else
            return null;
    }

    /*
    //SSL Socket
    public MainActivity (Context mcontext){
        SSLContext context = null;
        try {
            ssl = ssl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected SocketFactory ssl() throws Exception{
        SocketFactory socketFactory;
        // Load CAs from an InputStream
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        //local cert
        InputStream caInput = new BufferedInputStream(getApplicationContext().getAssets().open("router.crt"));
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

        return socketFactory;
    }
    */



    EditText host,user,pass;
    CheckBox checkBox;
    Boolean saveLogin;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        /*
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        */

        host = findViewById(R.id.editTextAddress);
        user = findViewById(R.id.editTextUsername);
        pass = findViewById(R.id.editTextPassword);
        checkBox = findViewById(R.id.checkBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin) {
            host.setText(loginPreferences.getString("connection", ""));
            user.setText(loginPreferences.getString("username",""));
            pass.setText(loginPreferences.getString("password",""));
            checkBox.setChecked(true);
        }

        final Button button = findViewById(R.id.btn_connect);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromInputMethod(host.getWindowToken(), 0);

                // Code here executes on main thread after user presses button
                String connection = host.getText().toString();
                String username = user.getText().toString();
                String password = pass.getText().toString();

                if (checkBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("connection", connection);
                    loginPrefsEditor.putString("username", username);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();

                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }


                try {
                    con = ApiConnection.connect(SocketFactory.getDefault(), connection, ApiConnection.DEFAULT_PORT, ApiConnection.DEFAULT_CONNECTION_TIMEOUT);
                    con.login(username, password);
                    Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Router Connected",Toast.LENGTH_LONG).show();

                } catch (MikrotikApiException e) {
                    e.printStackTrace();

                    // Jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Invalid username or password")
                            .setNegativeButton("Try again", null).create().show();
                }

             /*   if(con.isConnected()){
                    Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Router Connected",Toast.LENGTH_LONG).show();
                }

             */
            }
        });

        final Button button1 = findViewById(R.id.btn_exit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit")
                        .setMessage("Are you sure?")
                        .setNegativeButton("No",null)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                finish();
                                System.exit(0);
                            }
                        }).create().show();
            }
        });

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure")
                .setCancelable(false)
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                        finish();
                        System.exit(0);
                    }
                }).create().show();
    }


}
