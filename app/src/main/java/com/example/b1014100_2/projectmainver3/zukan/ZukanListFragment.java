package com.example.b1014100_2.projectmainver3.zukan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

public class ZukanListFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    static final int RESULT_SUBACTIVITY = 1000;
    int i;

    public static ZukanListFragment newInstance() {
        //page数をBundleに詰める
        ZukanListFragment fragment = new ZukanListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zukan_list, null);

        return view;
    }


}