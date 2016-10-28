package com.example.b1014100_2.projectmainver3.zukan;


import android.content.Context;
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

    private ZukanListSortFragmentListener listener = null;

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
        return inflater.inflate(R.layout.fragment_zukan_list_sort_type, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ZukanListSortFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement OnArticleSelectedListener.");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

//        getActivity().findViewById(R.id.button22).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    // Activityにイベント通知
//                    listener.onHogeFragmentEvent(HogeEvent.DRAWER1_A);
//                }
//            }
//        });
    }
}
