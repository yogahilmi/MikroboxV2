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
public class Wireless extends Fragment {


    public Wireless() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wireless, container, false);

        // Inflate the layout for this fragment
        return view;
    }

}
