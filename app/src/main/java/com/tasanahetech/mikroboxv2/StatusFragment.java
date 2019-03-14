package com.tasanahetech.mikroboxv2;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.api.ResultListener;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;


public class StatusFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);

        //Untuk menjalankan command dari API
        ApiConnection con = MainActivity.getCon();
        final TextView tv0 = (TextView) view.findViewById(R.id.textView0);
        TextView tv1 = (TextView) view.findViewById(R.id.textView1);
        TextView tv2 = (TextView) view.findViewById(R.id.textView2);
        TextView tv3 = (TextView) view.findViewById(R.id.textView3);
        TextView tv4 = (TextView) view.findViewById(R.id.textView4);
        TextView tv5 = (TextView) view.findViewById(R.id.textView5);
        TextView tv6 = (TextView) view.findViewById(R.id.textView6);
        TextView tv7 = (TextView) view.findViewById(R.id.textView7);
        TextView tv8 = (TextView) view.findViewById(R.id.textView8);

        if (con !=null)
        {
            try {
                //Untuk Menampilkan Resource dari Mikrotik
                List<Map<String, String>> rs = con.execute("/system/resource/print");
                for (Map<String, String> map : rs) {
                    tv0.setText(map.get("platform"));
                    tv1.setText(map.get("board-name"));
                    tv2.setText(map.get("version"));
                    tv3.setText(map.get("uptime"));
                    tv4.setText(map.get("cpu-load"));
                    tv5.setText(map.get("free-memory"));
                    tv6.setText(map.get("total-memory"));
                    tv7.setText(map.get("free-hdd-space"));
                    tv8.setText(map.get("total-hdd-space"));
                }

            } catch (MikrotikApiException e) {
                e.printStackTrace();
            }

        }

        return view;
    }

}
