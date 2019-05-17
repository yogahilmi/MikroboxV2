package com.tasanahetech.mikroboxv2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.api.ResultListener;

import java.util.List;
import java.util.Map;


public class TrafficFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traffic, container, false);

        //Untuk menjalankan command dari API
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
        return view;
    }
}
