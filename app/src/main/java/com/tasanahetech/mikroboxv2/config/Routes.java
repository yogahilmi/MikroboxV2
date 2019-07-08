package com.tasanahetech.mikroboxv2.config;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.tasanahetech.mikroboxv2.MainActivity;
import com.tasanahetech.mikroboxv2.R;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.config.routes.route;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Routes extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_routes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.add_route);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Routes.this, route.class);
                startActivity(intent);
            }
        });

        ApiConnection con = MainActivity.getCon();
        lv = (ListView) findViewById(R.id.routeMenu);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(Routes.this, android.R.layout.simple_list_item_1,
                arrayList);
        lv.setAdapter(adapter);

        if (con !=null) {
            try{
                List<Map<String, String>> rs = con.execute("/ip/route/print");
                for (Map<String, String> r : rs) {

                    String dst = r.get("dst-address");
                    String gateway = r.get("gateway");
                    String pref = r.get("pref-src");
                    String dist = r.get("distance");
                    String gate_status = r.get("gateway-status");

                    arrayList.add("dst-address : "+dst+"\n"+"pref-src : "+pref+"\n"+"gateway : "+gateway+" | "+"distance : "+dist+"\n"+gate_status);
                    adapter.notifyDataSetChanged();
                }
            } catch (MikrotikApiException e) {
                e.printStackTrace();
            }
        }


        /*
        final String[] routeMenu = {"Routes",
                "Nexthops",
                "Rules",
                "VRF"};
        ListView listView = (ListView) findViewById(R.id.routeMenu);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                routeMenu
        );
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String PilihMenu = routeMenu[+position];
                Toast.makeText(getApplicationContext(), PilihMenu, Toast.LENGTH_SHORT).show();

                if (position==0) {
                    Intent intent = new Intent(Routes.this, route.class);
                    startActivity(intent);
                }
                if (position==1) {
                    Intent intent = new Intent(Routes.this, nexthops.class);
                    startActivity(intent);
                }
                if (position==2) {
                    Intent intent = new Intent(Routes.this, rules.class);
                    startActivity(intent);
                }
                if (position==3) {
                    Intent intent = new Intent(Routes.this, vrf.class);
                    startActivity(intent);
                }
            }

        });
        */
    }
}
