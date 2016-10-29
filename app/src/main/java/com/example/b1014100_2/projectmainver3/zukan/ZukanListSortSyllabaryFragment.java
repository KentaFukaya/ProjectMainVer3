package com.example.b1014100_2.projectmainver3.zukan;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private ZukanListSortFragmentListener listener = null;

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
        return inflater.inflate(R.layout.fragment_zukan_list_sort_syllabary, container, false);
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
        //あ　ソート
        getActivity().findViewById(R.id.zukan_list_sort_syllabary_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary("あ");
                if (listener != null) listener.onZukanListSortFragmentChange();
            }
        });
        //か　ソート
        getActivity().findViewById(R.id.zukan_list_sort_syllabary_ka).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary("か");
                if (listener != null) listener.onZukanListSortFragmentChange();
            }
        });
        //さ　ソート
        getActivity().findViewById(R.id.zukan_list_sort_syllabary_sa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary("さ");
                if (listener != null) listener.onZukanListSortFragmentChange();
            }
        });
        //た　ソート
        getActivity().findViewById(R.id.zukan_list_sort_syllabary_ta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary("た");
                if (listener != null) listener.onZukanListSortFragmentChange();
            }
        });
        //な　ソート
        getActivity().findViewById(R.id.zukan_list_sort_syllabary_na).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary("な");
                if (listener != null) listener.onZukanListSortFragmentChange();
            }
        });
        //は　ソート
        getActivity().findViewById(R.id.zukan_list_sort_syllabary_ha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary("は");
                if (listener != null) listener.onZukanListSortFragmentChange();
            }
        });
        //ま　ソート
        getActivity().findViewById(R.id.zukan_list_sort_syllabary_ma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary("ま");
                if (listener != null) listener.onZukanListSortFragmentChange();
            }
        });
        //や　ソート
        getActivity().findViewById(R.id.zukan_list_sort_syllabary_ya).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary("や");
                if (listener != null) listener.onZukanListSortFragmentChange();
            }
        });
        //ら　ソート
        getActivity().findViewById(R.id.zukan_list_sort_syllabary_ra).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary("ら");
                if (listener != null) listener.onZukanListSortFragmentChange();
            }
        });
        //わ　ソート
        getActivity().findViewById(R.id.zukan_list_sort_syllabary_wa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary("わ");
                if (listener != null) listener.onZukanListSortFragmentChange();
            }
        });
    }
}
