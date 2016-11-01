package com.example.b1014100_2.projectmainver3.map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

/**
 * Created by b1014100_2 on 2016/10/30.
 */


public class MapListViewAdapter extends ArrayAdapter<MapData> {
    LayoutInflater mInflater;
    int area, location;
    Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "FUJIPOP.TTC");
    public MapListViewAdapter(Context context, ArrayList MapDates, int area, int loacrion) {
        super(context, 0, MapDates);
        mInflater = LayoutInflater.from(context);
        this.area = area;
        this.location = loacrion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            MapData md = getItem(position);
            if (md.getLocation_id() == -1) {//Area

                if(position != 0)
                    if(md.getName().length() == 3)
                        convertView = mInflater.inflate(R.layout.activity_maps_list_area3, parent, false);
                    else
                        convertView = mInflater.inflate(R.layout.activity_maps_list_area2, parent, false);
                else
                    convertView = mInflater.inflate(R.layout.activity_maps_list_area_first, parent, false);

                tv = (TextView) convertView.findViewById(R.id.map_list_contents);

                setClickColor(convertView, md,tv);
            } else {//location
                convertView = mInflater.inflate(R.layout.activity_maps_list_location, parent, false);
                tv = (TextView) convertView.findViewById(R.id.map_list_contents);
                if (md.getCheck360() == 1) {//360movei
                    //convertView.setBackgroundColor(Color.RED);
                    setClickColor(convertView, md ,tv);
                } else {//nomarmovie
                    convertView.setBackgroundColor(Color.BLUE);
                    setClickColor(convertView, md,tv);
                }
            }
            tv.setTypeface(typeFace);
            tv.setText(md.getName());

        return convertView;
    }

    public void setClickColor(View view, MapData mapData, TextView textView) {
        if (mapData.getArea_id() == area)
            if (mapData.getLocation_id() == -1) {// Clicked && Area
                textView.setTextColor(Color.rgb(51,181,229));
                ImageView imageView = (ImageView) view.findViewById(R.id.map_list_bg);
                imageView.setImageResource(R.drawable.map_areabg_ontouch);
            } else if (mapData.getLocation_id() == location) {//clicked && location
                if (mapData.getCheck360() == 1) {//360movei
                    view.setBackgroundColor(Color.BLACK);
                } else {//nomal movie
                    view.setBackgroundColor(Color.BLACK);
                }
            }
    }
}
