package com.tasanahetech.mikroboxv2.config;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tasanahetech.mikroboxv2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CAPsMAN extends Fragment {


    public CAPsMAN() {
        // Required empty public constructor
    }

    ArrayList<String> capsman_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caps_man, container, false);

        capsman_item = new ArrayList<>();
        capsman_item.add("1");
        capsman_item.add("2");
        capsman_item.add("3");
        capsman_item.add("4");
        capsman_item.add("5");
        capsman_item.add("6");
        capsman_item.add("7");
        capsman_item.add("8");
        capsman_item.add("9");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                capsman_item
        );

        ListView listView = (ListView) view.findViewById(R.id.capsman_listview);
        listView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

}
