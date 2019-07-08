package com.tasanahetech.mikroboxv2;

;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.ApiConnectionException;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.config.Bandwidth;
import com.tasanahetech.mikroboxv2.config.IP;
import com.tasanahetech.mikroboxv2.config.Ping;
import com.tasanahetech.mikroboxv2.config.Interfaces;
import com.tasanahetech.mikroboxv2.config.Routes;


public class ConfigFragment extends Fragment {

    ListViewAdapter list;
    String ConfigMenu[] = {
            "Interfaces",
            "IP Address",
            "Routes",
            "Bandwidth Management",
            "Ping",
            "Reboot",
            "Logout"
    };
    Integer ConfigIcon[] = {
            R.drawable.ic_interface,
            R.drawable.ic_ppp,
            R.drawable.ic_routing,
            R.drawable.ic_queue,
            R.drawable.ic_ping,
            R.drawable.ic_reboot,
            R.drawable.ic_logout
    };

    public ConfigFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, container, false);

        final ApiConnection con = MainActivity.getCon();

        final ListViewAdapter adapter = new ListViewAdapter(getActivity(), ConfigIcon, ConfigMenu);
        ListView listView = (ListView) view.findViewById(R.id.listview_fragment_config);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 //String PilihMenu = ConfigMenu[+position];
                 //Toast.makeText(getContext(), PilihMenu, Toast.LENGTH_SHORT).show();

                 if (position==0) {
                     Intent intent = new Intent(getActivity(), Interfaces.class);
                     startActivity(intent);
                 }

                 if (position==1) {
                     Intent intent = new Intent(getActivity(), IP.class);
                     startActivity(intent);
                    }

                 if (position==2) {
                     Intent intent = new Intent(getActivity(), Routes.class);
                     startActivity(intent);
                 }

                 if (position==3) {
                     Intent intent = new Intent(getActivity(), Bandwidth.class);
                     startActivity(intent);
                 }

                 if (position==4) {
                     Intent intent = new Intent(getActivity(), Ping.class);
                     startActivity(intent);
                 }

                 if (position==5) {
                     ApiConnection con = MainActivity.getCon();
                     if (con !=null) {
                         AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                         builder.setMessage("Are you sure?")
                                 .setTitle("Reboot")
                                 .setCancelable(false)
                                 .setNegativeButton("No",null)
                                 .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                     final ApiConnection con = MainActivity.getCon();
                                     @Override
                                     public void onClick(DialogInterface dialogInterface, int i) {
                                         try {
                                             con.execute("/system/reboot");

                                         } catch (MikrotikApiException e) {
                                             e.printStackTrace();
                                         }
                                         Intent intent = new Intent(getActivity(), MainActivity.class);
                                         startActivity(intent);
                                     }
                                 }).show();
                     }
                 }

                 if (position==6) {
                     ApiConnection con = MainActivity.getCon();
                     if (con !=null) {
                         AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                         builder.setMessage("Are you sure?")
                                 .setTitle("Logout")
                                 .setCancelable(false)
                                 .setNegativeButton("No",null)
                                 .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                     final ApiConnection con = MainActivity.getCon();
                                     @Override
                                     public void onClick(DialogInterface dialogInterface, int i) {
                                         try {
                                             con.close();
                                         } catch (ApiConnectionException e) {
                                             e.printStackTrace();
                                         }
                                         Intent intent = new Intent(getActivity(), MainActivity.class);
                                         startActivity(intent);
                                     }
                                 }).show();
                     }
                 }

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
