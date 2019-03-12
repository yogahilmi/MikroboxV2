package com.tasanahetech.mikroboxv2;

import android.app.FragmentTransaction;;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class ConfigFragment extends Fragment {

    public ConfigFragment() {

    }

    Intent intent;
    ArrayList<String> ListViewItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, container, false);

            ListViewItem = new ArrayList<>();
            ListViewItem.add("CAPsMAN");
            ListViewItem.add("Interfaces");
            ListViewItem.add("Wireless");
            ListViewItem.add("Bridge");
            ListViewItem.add("PPP");
            ListViewItem.add("Mesh");
            ListViewItem.add("Routing");
            ListViewItem.add("System");
            ListViewItem.add("Queues");
            ListViewItem.add("Log");
            ListViewItem.add("Radius");
            ListViewItem.add("Reboot");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getContext(),
                    android.R.layout.simple_list_item_1,
                    ListViewItem
            );

            ListView listView = (ListView) view.findViewById(R.id.listview_fragment_config);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    if (position==0) {
//                        intent = new Intent(getActivity(), CAPsMAN.class);
//                        startActivity(intent);
//                    } else if (position==1) {
//                        intent = new Intent(getActivity(), Interfaces.class);
//                        startActivity(intent);
//                    } else if (position==2) {
//                        intent = new Intent(getActivity(), Wireless.class);
//                        startActivity(intent);
//                    } else {
//                        Toast t = Toast.makeText(getActivity(), ListViewItem.get(position), Toast.LENGTH_SHORT);
//                        t.show();
//                    }
                }
            });

        return view;
    }

    // Dipanggil setelah onCreateView() sukses.
    // onViewCreated() hanya dipanggil jika view dari onCreateView() tidak null
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        insertNestedFragment();
//    }

//    private void insertNestedFragment() {
//        Fragment childFragment = ChildFragment();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.child_fragment_caps, childFragment).commit();
//    }


}
