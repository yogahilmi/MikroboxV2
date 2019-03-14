package com.tasanahetech.mikroboxv2;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ListViewAdapter extends ArrayAdapter<String>{

    private final Context context;
    private final String[] ConfigMenu;
    private final Integer[] ConfigIcon;

    public ListViewAdapter(Activity context, Integer[] ConfigIcon, String[] ConfigMenu) {
        super(context, R.layout.mylist, ConfigMenu);

        this.ConfigMenu=ConfigMenu;
        this.ConfigIcon=ConfigIcon;
        this.context=context;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.mylist,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.config_icon);

        txtTitle.setText(ConfigMenu[position]);
        imageView.setImageResource(ConfigIcon[position]);

        return rowView;
    }
}
