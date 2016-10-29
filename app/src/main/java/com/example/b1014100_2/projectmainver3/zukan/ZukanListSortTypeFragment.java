package com.example.b1014100_2.projectmainver3.zukan;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZukanListSortTypeFragment extends Fragment {

    private ZukanListSortFragmentListener listener = null;
    private ArrayList<ZukanListSearchId> searchIds;

    private int i;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setButtons();
    }

    private void setButtons(){
        setSearchId();
        for(int i = 0; i < searchIds.size(); i++){
            final int index = i;
            int viewId = getActivity().getResources().getIdentifier(searchIds.get(index).getIdName(), "id", getActivity().getPackageName());
            getActivity().findViewById(viewId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("zukanlistsorttype", "onclick: " + searchIds.get(index).getIdName() + " " + searchIds.get(index).getSearchName());
                    ZukanListActivity.zukans = ZukanDatabase.getZukanType(searchIds.get(index).getSearchName());
                    for (int i = 0; i < ZukanListActivity.zukans.size(); i++){
                        Log.d("zukanlistsorttype", "onClick: " + ZukanListActivity.zukans.get(i).printall());
                    }
                    if (listener != null) listener.onZukanListSortFragmentChange();
                }
            });
        }

    }

    private void setSearchId(){
        searchIds = new ArrayList<>();
        searchIds.add(new ZukanListSearchId("zukan_list_sort_type_ainame", "アイナメ科"));
        searchIds.add(new ZukanListSearchId("zukan_list_sort_type_akaika", "アカイカ科"));
    }
}

class ZukanListSearchId {
    private String idName;
    private String searchName;

    public ZukanListSearchId(String idName, String searchName) {
        this.idName = idName;
        this.searchName = searchName;
    }

    public String getIdName() {
        return idName;
    }

    public String getSearchName() {
        return searchName;
    }
}