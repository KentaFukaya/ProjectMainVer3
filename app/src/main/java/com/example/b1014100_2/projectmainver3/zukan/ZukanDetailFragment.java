package com.example.b1014100_2.projectmainver3.zukan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

public class ZukanDetailFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    private ArrayList<Zukan> zukans = Zukan.zukanCrate();

    public static ZukanDetailFragment newInstance(int fishId) {
        //page数をBundleに詰める
        ZukanDetailFragment fragment = new ZukanDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", fishId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int fishId = getArguments().getInt("id");

        View view = inflater.inflate(R.layout.fragment_detail_zukan, null);
//        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_zukan);

        //魚の画像の表示
        ImageView imageView = (ImageView) view.findViewById(R.id.ZukanDetail_pic);
        imageView.setImageResource(R.drawable.zukan1);
        //魚の名前の表示
        TextView pageFishName = (TextView)view.findViewById(R.id.ZukanDetail_fishName);
        pageFishName.setText(zukans.get(fishId).getName());
        //魚の概要の表示
        TextView abstractText = (TextView) view.findViewById(R.id.ZukanDetail_abstarct);
        abstractText.setText(zukans.get(fishId).getContent());


        return view;
    }

}