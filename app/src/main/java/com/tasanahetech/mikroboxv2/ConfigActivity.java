package com.tasanahetech.mikroboxv2;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.api.ResultListener;

import java.util.List;
import java.util.Map;


public class ConfigActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        ApiConnection con = MainActivity.getCon();
        if(con !=null)
        {
            try {
                //Monitor traffic jaringan secara asynchronous
                String tag = con.execute("/interface/monitor-traffic interface=ether1",
                        new ResultListener() {

                            public void receive(Map<String, String> result) {
                                System.out.println(result);
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
                fragment = new TrafficFragment();
                break;
        }
        return loadFragment(fragment);
    }

}
