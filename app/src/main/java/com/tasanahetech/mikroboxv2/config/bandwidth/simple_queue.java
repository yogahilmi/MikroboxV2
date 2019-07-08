package com.tasanahetech.mikroboxv2.config.bandwidth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tasanahetech.mikroboxv2.MainActivity;
import com.tasanahetech.mikroboxv2.R;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.config.Bandwidth;
import com.tasanahetech.mikroboxv2.config.IP;
import com.tasanahetech.mikroboxv2.config.ip.addresses;

import java.util.ArrayList;

public class simple_queue extends AppCompatActivity {

    EditText ip, max, limit, name;
    ApiConnection con = MainActivity.getCon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_queue);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_sq);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ip = findViewById(R.id.editTextIp_bw);
        max = findViewById(R.id.editTextDownload);
        limit = findViewById(R.id.editTextUpload);
        name = findViewById(R.id.editTextIp_name);

        final Button btnSetBW = findViewById(R.id.btn_setBW);
        btnSetBW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_ip = ip.getText().toString();
                String input_max = max.getText().toString();
                String input_limit = limit.getText().toString();
                String input_name = name.getText().toString();

                try{
                    con.execute("/queue/simple/add name= "+input_name+
                            " target= "+input_ip+" max-limit= "+input_max+
                            " limit-at= "+input_limit);
                    Intent intent = new Intent(simple_queue.this, Bandwidth.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                } catch (MikrotikApiException e) {
                    e.printStackTrace();
                }
            }
        });

        final Button btnCancelBW = findViewById(R.id.btn_cancelBW);
        btnCancelBW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simple_queue.this
                        .finish();
            }
        });
    }
}
