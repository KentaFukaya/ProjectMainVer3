package com.example.b1014100_2.projectmainver3.map;

import android.content.Context;
import android.graphics.Color;
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
    int area,location;
    public MapListViewAdapter(Context context, ArrayList MapDates,int area,int loacrion) {
        super(context,0,MapDates);
        mInflater = LayoutInflater.from(context);
        this.area  =area;
        this.location = loacrion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_maps_list, parent, false);
        }
            MapData md  =  getItem(position);
        if(md.getLocation_id() == -1) {//Area
            convertView = mInflater.inflate(R.layout.activity_maps_list_area, parent, false);
                convertView.setBackgroundColor(Color.GREEN);
                setClickColor(convertView,md);
        }else {//location
            convertView = mInflater.inflate(R.layout.activity_maps_list_location, parent, false);
            if(md.getCheck360() == 1){//360movei
                    convertView.setBackgroundColor(Color.RED);
                    setClickColor(convertView,md);
            }else{//nomarmovie
                convertView.setBackgroundColor(Color.BLUE);
                setClickColor(convertView,md);
            }
        }
        TextView tv = (TextView) convertView.findViewById(R.id.contents);
        tv.setText(md.getName());
        return convertView;
    }

    public void setClickColor(View view,MapData mapData){
        if(mapData.getArea_id() == area)
        if(mapData.getLocation_id() == -1){// Clicked && Area
            view.setBackgroundColor(Color.BLACK);
        }else if(mapData.getLocation_id() == location){//clicked && location
            if(mapData.getCheck360() == 1) {//360movei
                view.setBackgroundColor(Color.BLACK);
            }else{//nomal movie
                view.setBackgroundColor(Color.BLACK);
            }
        }
    }
}
