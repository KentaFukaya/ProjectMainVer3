package com.example.b1014100_2.projectmainver3.zukan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b1014100_2.projectmainver3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZukanListSortTypeFragment extends Fragment {


    public ZukanListSortTypeFragment() {
        // Required empty public constructor
    }

    public static ZukanListSortTypeFragment newInstance() {
        ZukanListSortTypeFragment fragment = new ZukanListSortTypeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zukan_list_sort_type, null);
        return view;
    }

}
