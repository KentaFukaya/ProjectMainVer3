package com.example.b1014100_2.projectmainver3.zukan;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b1014100_2.projectmainver3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZukanListSortSeasonFragment extends Fragment {

    private ZukanListSortFragmentListener listener = null;

    public ZukanListSortSeasonFragment() {
        // Required empty public constructor
    }

    public static ZukanListSortSeasonFragment newInstance() {
        ZukanListSortSeasonFragment fragment = new ZukanListSortSeasonFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zukan_list_sort_season, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //ZukanListActivityにlistenerのオーバーライドを確認
        try {
            listener = (ZukanListSortFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement OnArticleSelectedListener.");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setButtons();
    }

    private void setButtons(){
        //春ソートボタン
        getActivity().findViewById(R.id.zukan_list_sort_season_spring).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSeason(ZukanDatabase.SEASON_SPRING);
                    // Activityにイベント通知
                    listener.onZukanListSortFragmentChange();
                }
            }
        });

        //夏ソートボタン
        getActivity().findViewById(R.id.zukan_list_sort_season_summer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSeason(ZukanDatabase.SEASON_SUMMER);
                    // Activityにイベント通知
                    listener.onZukanListSortFragmentChange();
                }
            }
        });

        //秋ソートボタン
        getActivity().findViewById(R.id.zukan_list_sort_season_fall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSeason(ZukanDatabase.SEASON_FALL);
                    // Activityにイベント通知
                    listener.onZukanListSortFragmentChange();
                }
            }
        });

        //冬ソートボタン
        getActivity().findViewById(R.id.zukan_list_sort_season_winter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSeason(ZukanDatabase.SEASON_WINTER);
                    // Activityにイベント通知
                    listener.onZukanListSortFragmentChange();
                }
            }
        });
    }
}
