package com.tasanahetech.mikroboxv2.config;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.tasanahetech.mikroboxv2.MainActivity;
import com.tasanahetech.mikroboxv2.R;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.config.bandwidth.simple_queue;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bandwidth extends AppCompatActivity {


    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandwidth);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_queues);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ApiConnection con = MainActivity.getCon();

        lv = (ListView) findViewById(R.id.listview_bw);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                arrayList);
        lv.setAdapter(adapter);

        try {
            List<Map<String, String>> rs = con.execute("/queue/simple/print");
            for (Map<String,String> r : rs) {
                System.out.println(r);

                String name = r.get("name");
                String target = r.get("target");
                String max_limit = r.get("max-limit");
                String limit_at = r.get("limit-at");

                arrayList.add("Target : "+target+"\n"+"Name  : "+name+"\n"+"Max-Limit : "+max_limit+"\n"+"Limit-At     : "+limit_at+"\n");
                adapter.notifyDataSetChanged();
            }

        } catch (MikrotikApiException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = findViewById(R.id.add_bandwidth);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bandwidth.this, simple_queue.class);
                startActivity(intent);
            }
        });


        /*
        final String[] queueMenu = { "Simple Bandwidth",
                "Interface Bandwidth",
                "Queue Tree",
                "Queue Types"};

        ListView listView = (ListView) findViewById(R.id.queueMenu);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                queueMenu
        );
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String PilihMenu = queueMenu[+position];
                Toast.makeText(getApplicationContext(), PilihMenu, Toast.LENGTH_SHORT).show();
                if (position==0) {
                    Intent intent = new Intent(Bandwidth.this, simple_queue.class);
                    startActivity(intent);
                }
                if (position==1) {
                    Intent intent = new Intent(Bandwidth.this, interface_queue.class);
                    startActivity(intent);
                }
                if (position==2) {
                    Intent intent = new Intent(Bandwidth.this, queue_tree.class);
                    startActivity(intent);
                }
                if (position==3) {
                    Intent intent = new Intent(Bandwidth.this, queue_type.class);
                    startActivity(intent);
                }
            }
        });
        */
    }
}
