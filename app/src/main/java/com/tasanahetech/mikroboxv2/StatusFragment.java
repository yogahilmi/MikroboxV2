package com.tasanahetech.mikroboxv2;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.api.ResultListener;
import java.util.List;
import java.util.Map;


public class StatusFragment extends Fragment {

    LineGraphSeries<DataPoint> cpu, memory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_status, container, false);

        //Untuk menjalankan command dari API
        ApiConnection con = MainActivity.getCon();
        if (con !=null)
        {
            try {
                //Untuk Menampilkan Resource dari Mikrotik
                String rs = con.execute("/system/resource/print interval=1",
                        new ResultListener() {
                            //TextView
                            TextView tv0 = (TextView) view.findViewById(R.id.textView0);
                            TextView tv1 = (TextView) view.findViewById(R.id.textView1);
                            TextView tv2 = (TextView) view.findViewById(R.id.textView2);
                            TextView tv3 = (TextView) view.findViewById(R.id.textView3);
                            TextView tv4 = (TextView) view.findViewById(R.id.textView4);
                            TextView tv5 = (TextView) view.findViewById(R.id.textView5);
                            TextView tv6 = (TextView) view.findViewById(R.id.textView6);
                            TextView tv7 = (TextView) view.findViewById(R.id.textView7);
                            TextView tv8 = (TextView) view.findViewById(R.id.textView8);

                            //Graphview
                            GraphView graphView = (GraphView) view.findViewById(R.id.graph_cpu);
                            GraphView graphView1 = (GraphView) view.findViewById(R.id.graph_memory);

                            public void receive(final Map<String, String> result) {
                                if (getActivity()!=null)

                                    getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv0.setText(result.get("platform"));
                                        tv1.setText(result.get("board-name"));
                                        tv2.setText(result.get("version"));
                                        tv3.setText(result.get("uptime"));
                                        tv4.setText(String.format("%s %%", result.get("cpu-load")));
                                        tv5.setText(String.format("%s MB", Integer.parseInt(result.get("free-memory")) /(1024*1024) ));
                                        tv6.setText(String.format("%s MB", Integer.parseInt(result.get("total-memory")) /(1024*1024) ));
                                        tv7.setText(String.format("%s MB", Integer.parseInt(result.get("free-hdd-space")) /(1024*1024) ));
                                        tv8.setText(String.format("%s MB", Integer.parseInt(result.get("total-hdd-space")) /(1024*1024) ));


                                        cpu = new LineGraphSeries<DataPoint>(new DataPoint[] {
                                                new DataPoint(0, 1),
                                                new DataPoint(1, 5),
                                                new DataPoint(2, 3),
                                                new DataPoint(3, 2),
                                                new DataPoint(4, 6)
                                        });
                                        graphView.addSeries(cpu);

                                        memory = new LineGraphSeries<DataPoint>(new DataPoint[] {
                                                new DataPoint(0, 1),
                                                new DataPoint(1, 5),
                                                new DataPoint(2, 3),
                                                new DataPoint(3, 2),
                                                new DataPoint(4, 6)
                                        });
                                        graphView1.addSeries(memory);
                                    }
                                });
                            }

                            public void error(MikrotikApiException e) {
                                System.out.println("An error occurred: " + e.getMessage());
                            }

                            public void completed() {
                                System.out.println("Asynchronous command has finished");
                            }
                        }

                );


            } catch (MikrotikApiException e) {
                e.printStackTrace();
            }

        }



        return view;
    }

}
