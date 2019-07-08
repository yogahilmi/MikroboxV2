package com.tasanahetech.mikroboxv2.config;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tasanahetech.mikroboxv2.ListViewAdapter;
import com.tasanahetech.mikroboxv2.MainActivity;
import com.tasanahetech.mikroboxv2.R;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Interfaces extends AppCompatActivity {

    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        //Tombol Back Navigation
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_interface);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ApiConnection con = MainActivity.getCon();

        /*
        final String[] interMenu = {"ether1",
                "ether2",
                "ether3",
                "ether4",
                "ether5"};

        ListView listView = (ListView) findViewById(R.id.listview_interface);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                interMenu
        );
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String PilihMenu = interMenu[+position];
                Toast.makeText(getApplicationContext(), PilihMenu, Toast.LENGTH_SHORT).show();
            }

        });
        */

        lv = (ListView) findViewById(R.id.listview_interface);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(Interfaces.this, android.R.layout.simple_list_item_1,
                arrayList);
        lv.setAdapter(adapter);

        if (con !=null) {
            try {
                List<Map<String, String>> rs = con.execute("/interface/ethernet/print");
                for (Map<String, String> r : rs) {
                    String result = r.get("default-name");
                   // String result_comment = r.get("comment");
                    arrayList.add(result);
                    adapter.notifyDataSetChanged();
                }

            } catch (MikrotikApiException e) {
                e.printStackTrace();
            }
        }
    }
}
