package com.tasanahetech.mikroboxv2;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.ApiConnectionException;
import com.tasanahetech.mikroboxv2.config.IP;
import com.tasanahetech.mikroboxv2.config.Interfaces;
import com.tasanahetech.mikroboxv2.config.Log;
import com.tasanahetech.mikroboxv2.config.Queues;
import com.tasanahetech.mikroboxv2.config.Routing;


public class ConfigActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    ApiConnection con = MainActivity.getCon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        // set default nya Status Fragment
        loadFragment(new StatusFragment());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.status_menu:
                fragment = new StatusFragment();
                break;
            case R.id.config_menu:
                fragment = new ConfigFragment();
                break;
            case R.id.traffic_menu:
                fragment = new Interfaces();
                break;
        }
        return loadFragment(fragment);
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Disconnect from router?")
                .setCancelable(false)
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            con.close();
                        } catch (ApiConnectionException e) {
                            e.printStackTrace();
                        }
                        ConfigActivity.this.finish();
                    }
                })
                .show();
    }

}
