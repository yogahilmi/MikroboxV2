package com.tasanahetech.mikroboxv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.SSLCertificateSocketFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CheckBox;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class MainActivity extends AppCompatActivity {

    //Agar API dapat diakses dari Activity / Fragment yang berbeda
    public static ApiConnection con;
    public static ApiConnection getCon()
    {
        if(con != null)
            return con;
        else
            return null;
    }

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

                } catch (MikrotikApiException e) {
                    e.printStackTrace();
                    System.out.println("Password Salah");
                }


                if(con.isConnected()){
                    Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Router Connected",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}
