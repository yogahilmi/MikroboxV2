package com.tasanahetech.mikroboxv2.config;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.tasanahetech.mikroboxv2.MainActivity;
import com.tasanahetech.mikroboxv2.R;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.config.ip.addresses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IP extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_ip);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.add_ip);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IP.this, addresses.class);
                startActivity(intent);
            }
        });

        ApiConnection con = MainActivity.getCon();
        listView = (ListView) findViewById(R.id.ipMenu);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(
                IP.this, android.R.layout.simple_list_item_1,
                arrayList);
        listView.setAdapter(adapter);

        if (con !=null) {
            try {
                List<Map<String, String>> rs = con.execute("/ip/address/print");
                for (Map<String, String> r : rs) {
                    String result = r.get("interface");
                    //String result_comment = r.get("comment");
                    String result_ip = r.get("address");
                    String result_net = r.get("network");

                    arrayList.add("Interface : "+result+"\n"+"Address  : "+result_ip+"\n"+"Network  : "+result_net);
                    adapter.notifyDataSetChanged();
                }
            } catch (MikrotikApiException e) {
                e.printStackTrace();
            }
        }

        /*
        final String[] ipMenu = { "Addresses",
                "DHCP Client",
                "DHCP Server",
                "DNS",
                "Firewall",
                "IP Pool"};

        ListView listView = (ListView) findViewById(R.id.ipMenu);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, ipMenu
        );

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String PilihMenu = ipMenu[+position];
                Toast.makeText(getApplicationContext(), PilihMenu, Toast.LENGTH_SHORT).show();
                if (position==0) {
                    Intent intent = new Intent(IP.this, addresses.class);
                    startActivity(intent);
                }
                if (position==1) {
                    Intent intent = new Intent(IP.this, dhcp_client.class);
                    startActivity(intent);
                }
                if (position==2) {
                    Intent intent = new Intent(IP.this, dhcp_server.class);
                    startActivity(intent);
                }
                if (position==3) {
                    Intent intent = new Intent(IP.this, dns.class);
                    startActivity(intent);
                }
                if (position==4) {
                    Intent intent = new Intent(IP.this, firewall.class);
                    startActivity(intent);
                }
                if (position==5) {
                    Intent intent = new Intent(IP.this, pool.class);
                    startActivity(intent);
                }
            }

        });
        */

    }
}
