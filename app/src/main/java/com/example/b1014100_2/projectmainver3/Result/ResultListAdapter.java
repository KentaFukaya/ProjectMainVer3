package com.example.b1014100_2.projectmainver3.Result;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

/**
 * Created by b1014100_2 on 2016/11/29.
 */

public class ResultListAdapter extends ArrayAdapter<ResultData> {

    LayoutInflater mInflater;
    Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "noadd_FUJIPOP.TTC");

    public ResultListAdapter(Context context) {
        super(context, 0);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_result, parent, false);
        }

        ResultData resultData = getItem(position);



        if(resultData.getState() == 0) {
            convertView = mInflater.inflate(R.layout.list_result_ng,parent,false);
            LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.result_list_layout);

            TextView tv = (TextView) convertView.findViewById(R.id.result_list_text);
            tv.setTypeface(typeFace);

            linearLayout.setBackgroundResource(R.drawable.result_ng);//setBackGround

            tv.setText(resultData.getDesc());//説明を入れる


        }else {
            convertView = mInflater.inflate(R.layout.list_result,parent,false);
            LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.result_list_layout);

            linearLayout.setBackgroundResource(R.drawable.result_ok);//setBackGround

            TextView tv = (TextView) convertView.findViewById(R.id.result_list_text);
            tv.setTypeface(typeFace);
            tv.setText(resultData.getName());//照合名入れる

            ImageView imageView = (ImageView) convertView.findViewById(R.id.result_star);
            imageView.setImageResource(R.drawable.result_revel1 + resultData.getLevel() -1);//照合の星を入れる
        }

        return convertView;
    }

}
