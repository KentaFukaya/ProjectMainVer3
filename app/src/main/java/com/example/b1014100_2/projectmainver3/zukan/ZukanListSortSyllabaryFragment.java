package com.example.b1014100_2.projectmainver3.zukan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZukanListSortSyllabaryFragment extends Fragment {


    public ZukanListSortSyllabaryFragment() {
        // Required empty public constructor
    }

    public static ZukanListSortSyllabaryFragment newInstance() {
        ZukanListSortSyllabaryFragment fragment = new ZukanListSortSyllabaryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zukan_list_sort_syllabary, null);
//        return view;

        return inflater.inflate(R.layout.fragment_zukan_list_sort_syllabary, container, false);
    }

}
