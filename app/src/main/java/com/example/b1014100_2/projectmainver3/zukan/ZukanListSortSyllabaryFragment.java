package com.example.b1014100_2.projectmainver3.zukan;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

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
        final String[] syllabary = {"a", "ka", "sa", "ta", "na", "ha", "ma", "ya", "ra", "wa"};

        for (int i = 0; i < syllabary.length; i++) {
            final int index = i;
            final String resViewName = "zukan_list_sort_syllabary_" + syllabary[index];
            int viewId = getActivity().getResources().getIdentifier(resViewName, "id", getActivity().getPackageName());
            final ImageButton imageButton = (ImageButton) getActivity().findViewById(viewId);
            //selected
            String selectedImageName = resViewName + "_sorted_list";
            final int selectedImageId = getActivity().getResources().getIdentifier(selectedImageName, "drawable", getActivity().getPackageName());

            //ソート条件になっているとき
            if(ZukanListActivity.sortType == ZukanListActivity.TYPE_SYLLABARY && i == ZukanListActivity.sortNo && selectedImageId != 0){
                    imageButton.setImageResource(selectedImageId);
            }

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageButton.setImageResource(selectedImageId);
                    ZukanListActivity.zukans = new ZukanDatabase(getActivity()).getZukanSyllabary(syllabary[index]);
                    int viewId = getActivity().getResources().getIdentifier(resViewName + "_sorted", "drawable", getActivity().getPackageName());
                    ((ImageView) getActivity().findViewById(R.id.zukan_list_sort_unselected)).setImageResource(viewId);
                    //ソート条件をセット
                    ZukanListActivity.sortType = ZukanListActivity.TYPE_SYLLABARY;
                    ZukanListActivity.sortNo = index;
                    //押されたときにActivityに通知
                    if (listener != null) listener.onZukanListSortFragmentChange();
                }
            });
        }

    }
}
