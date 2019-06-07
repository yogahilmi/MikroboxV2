package com.tasanahetech.mikroboxv2;

;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class ConfigFragment extends Fragment {

    ListViewAdapter list;
    String ConfigMenu[] = {
            "Interfaces",
            "IP",
            "Routes",
            "Queues",
            "Log",
            "Reboot"
    };
    Integer ConfigIcon[] = {
            R.drawable.ic_interface,
            R.drawable.ic_ppp,
            R.drawable.ic_routing,
            R.drawable.ic_queue,
            R.drawable.ic_log,
            R.drawable.ic_reboot
    };

    public ConfigFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, container, false);

        final ListViewAdapter adapter = new ListViewAdapter(getActivity(), ConfigIcon, ConfigMenu);
        ListView listView = (ListView) view.findViewById(R.id.listview_fragment_config);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String PilihMenu = ConfigMenu[+position];
                 Toast.makeText(getContext(), PilihMenu, Toast.LENGTH_SHORT).show();

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
