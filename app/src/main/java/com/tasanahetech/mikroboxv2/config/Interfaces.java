package com.tasanahetech.mikroboxv2.config;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.tasanahetech.mikroboxv2.R;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class Interfaces extends Fragment {

    public Interfaces() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_interfaces, container, false);
        return view;

    }

}
