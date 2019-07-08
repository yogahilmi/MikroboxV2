package com.tasanahetech.mikroboxv2.config;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tasanahetech.mikroboxv2.ConfigActivity;
import com.tasanahetech.mikroboxv2.ConfigFragment;
import com.tasanahetech.mikroboxv2.MainActivity;
import com.tasanahetech.mikroboxv2.R;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.Config;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.api.ResultListener;
import com.tasanahetech.mikroboxv2.config.ip.addresses;

import java.util.List;
import java.util.Map;

public class Ping extends AppCompatActivity {

    EditText ping;
    ListView listView;
    TextView tv_ping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_ping);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ping = findViewById(R.id.editTextPing);
        tv_ping = findViewById(R.id.report_ping);

        Button button = findViewById(R.id.btn_ping);
        Button btnCancelPing = findViewById(R.id.btn_cancelIP);
        button.setOnClickListener(new View.OnClickListener() {

            final ApiConnection con = MainActivity.getCon();
            @Override
            public void onClick(final View view){


                try {
                    String tag = con.execute("/ping count=5 interval=2 address= " + ping.getText().toString(),
                            new ResultListener() {
                                TextView tv_ping = (TextView) view.findViewById(R.id.report_ping);

                                //ListView
                                public void receive(final Map<String, String> result) {
                                    Ping.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv_ping.setText(result.get(("host"+"size"+"ttl"+"time")));
                                            System.out.println(result);
                                        }
                                    });
                                }
                                public void error(MikrotikApiException e) {
                                    System.out.println("An error occurred: " + e.getMessage());
                                }

                                public void completed() {
                                    System.out.println("Asynchronous command has finished");
                                }

                            }
                    );

                } catch (MikrotikApiException e) {
                    e.printStackTrace();
                }

            }
        });

        final Button btnCancelIP = findViewById(R.id.btn_cancelPing);
        btnCancelIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ping.this.finish();
            }
        });
    }
}
