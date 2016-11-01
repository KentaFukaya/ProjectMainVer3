package com.example.b1014100_2.projectmainver3.map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        if (convertView == null) {
            TextView tv;
            MapData md = getItem(position);
            if (md.getLocation_id() == -1) {//Area
                convertView = mInflater.inflate(R.layout.activity_maps_list_area, parent, false);
                tv = (TextView) convertView.findViewById(R.id.map_list_contents);
                //convertView.setBackgroundColor(Color.GREEN);
                setClickColor(convertView, md,tv);
            } else {//location
                convertView = mInflater.inflate(R.layout.activity_maps_list_location, parent, false);
                tv = (TextView) convertView.findViewById(R.id.map_list_contents);
                if (md.getCheck360() == 1) {//360movei
                    convertView.setBackgroundColor(Color.RED);
                    setClickColor(convertView, md ,tv);
                } else {//nomarmovie
                    convertView.setBackgroundColor(Color.BLUE);
                    setClickColor(convertView, md,tv);
                }
            }
            tv.setTypeface(typeFace);
            tv.setText(md.getName());
        }
        return convertView;
    }

    public void setClickColor(View view, MapData mapData, TextView textView) {
        if (mapData.getArea_id() == area)
            if (mapData.getLocation_id() == -1) {// Clicked && Area
                textView.setTextColor(Color.argb(187,51,181,229));
            } else if (mapData.getLocation_id() == location) {//clicked && location
                if (mapData.getCheck360() == 1) {//360movei
                    view.setBackgroundColor(Color.BLACK);
                } else {//nomal movie
                    view.setBackgroundColor(Color.BLACK);
                }
            }
    }
}
