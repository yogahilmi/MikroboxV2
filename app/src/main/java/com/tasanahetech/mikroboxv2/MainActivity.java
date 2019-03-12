package com.tasanahetech.mikroboxv2;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.Config;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.api.ResultListener;
import java.util.List;
import java.util.Map;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class MainActivity extends AppCompatActivity {

    EditText host,user,pass;
    public static ApiConnection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        host = findViewById(R.id.editTextAddress);
        user = findViewById(R.id.editTextUsername);
        pass = findViewById(R.id.editTextPassword);
        final Button button = findViewById(R.id.btn_connect);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                String connection = host.getText().toString();
                String username = user.getText().toString();
                String password = pass.getText().toString();

                try {
                    con = ApiConnection.connect(SocketFactory.getDefault(), connection, ApiConnection.DEFAULT_PORT,2000);
                    con.login(username, password);

                } catch (MikrotikApiException e) {
                    e.printStackTrace();
                }

                if(con.isConnected()){
                    Intent intent = new Intent(MainActivity.this,ConfigActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Router Connected",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public static ApiConnection getCon()
    {
        if(con != null)
            return con;
        else
            return null;
    }
}
