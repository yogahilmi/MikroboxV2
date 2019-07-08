package com.tasanahetech.mikroboxv2.config.ip;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tasanahetech.mikroboxv2.ConfigActivity;
import com.tasanahetech.mikroboxv2.MainActivity;
import com.tasanahetech.mikroboxv2.R;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.ApiConnectionException;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.config.IP;

public class addresses extends AppCompatActivity {

    EditText ip, network, select_inter;
    ApiConnection con = MainActivity.getCon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresses);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_addresses);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ip = findViewById(R.id.editTextIp);
        network = findViewById(R.id.editTextNetwork);
        select_inter = findViewById(R.id.editTextInterface);

        final Button btnSetIP = findViewById(R.id.btn_setIP);
        btnSetIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = ip.getText().toString();
                String net = network.getText().toString();
                String inter = select_inter.getText().toString();

                try{
                    con.execute("/ip/address/add address= "+address+" network= "+net+" interface= "+inter);
                    Intent intent = new Intent(addresses.this, IP.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

                } catch (MikrotikApiException e) {
                    e.printStackTrace();
                }
            }
        });

        final Button btnCancelIP = findViewById(R.id.btn_cancelIP);
        btnCancelIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addresses.this
                         .finish();
            }
        });
    }
}
