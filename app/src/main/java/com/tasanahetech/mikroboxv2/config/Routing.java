package com.tasanahetech.mikroboxv2.config;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tasanahetech.mikroboxv2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Routing extends Fragment {


    public Routing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_routing, container, false);
    }

}
