package com.example.b1014100_2.projectmainver3.zukan;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.b1014100_2.projectmainver3.R;

public class ZukanFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    public static ZukanFragment newInstance(@ColorRes int IdRes) {
        ZukanFragment frag = new ZukanFragment();
        Bundle b = new Bundle();
        b.putInt(BACKGROUND_COLOR, IdRes);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zukan, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_zukan);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));

        return view;
    }
}