package com.tasanahetech.mikroboxv2.config;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tasanahetech.mikroboxv2.MainActivity;
import com.tasanahetech.mikroboxv2.R;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Log extends Fragment {


    public Log() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        // Untuk menjalankan API Mikrotik
        ApiConnection con = MainActivity.getCon();
        if (con !=null) {
            try {
                List<Map<String, String>> rs = con.execute("/log/print");
                for (Map<String, String> r : rs) {
                    ListView listView = (ListView) view.findViewById(R.id.viewLog);
                    ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                            getActivity(),
                            android.R.layout.simple_list_item_1);
                    listView.setAdapter(listViewAdapter);
                }

            } catch (MikrotikApiException e) {
                e.printStackTrace();
            }
        }


        return view;
    }

}
