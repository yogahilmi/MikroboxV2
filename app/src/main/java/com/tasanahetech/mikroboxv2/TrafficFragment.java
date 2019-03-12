package com.tasanahetech.mikroboxv2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;

import java.util.List;
import java.util.Map;


public class TrafficFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traffic, container, false);


        ApiConnection con = MainActivity.getCon();
        if (con !=null)
        {
            try {
                //Untuk Menampilkan Platform Mikrotik
                List<Map<String, String>> rs = con.execute("/system/resource/print");
                for (Map<String, String> map : rs) {
                    System.out.println(map.get("platform"));
                }
            } catch (MikrotikApiException e) {
                e.printStackTrace();
            }

        }

        return view;
    }
}
