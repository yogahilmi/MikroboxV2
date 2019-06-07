package com.tasanahetech.mikroboxv2.config;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tasanahetech.mikroboxv2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IP extends Fragment {


    public IP() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_ip, container, false);

        final String[] ipMenu = { "Addresses",
                            "DHCP Client",
                            "DHCP Server",
                            "DNS",
                            "Firewall",
                            "IP Pool"};

        ListView listView = (ListView) view.findViewById(R.id.ipMenu);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                ipMenu
        );

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String PilihMenu = ipMenu[+position];
                Toast.makeText(getContext(), PilihMenu, Toast.LENGTH_SHORT).show();

            }

        });

        return view;
    }

}
