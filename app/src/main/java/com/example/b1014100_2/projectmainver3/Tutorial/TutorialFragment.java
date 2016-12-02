package com.example.b1014100_2.projectmainver3.Tutorial;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.b1014100_2.projectmainver3.R;
import com.example.b1014100_2.projectmainver3.map.MapsActivity;

/**
 * Created by b1014100_2 on 2016/11/25.
 */

public class TutorialFragment extends Fragment {
    private final static String PAGE = "page";
    private final static int FINAL_PAGE = 8;

    ImageButton next,mae,skip;

    public static TutorialFragment newInstance(int page) {
        TutorialFragment frag = new TutorialFragment();
        Bundle b = new Bundle();
        b.putInt(PAGE, page);
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
        int page = getArguments().getInt(PAGE);
        View view = inflater.inflate(R.layout.fragment_tutorial, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tutorial_bg);
        imageView.setImageResource(R.drawable.tutorial0+page);

        mae = (ImageButton)view.findViewById(R.id.tutorial_mae);
        mae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(-1);
            }
        });

        skip = (ImageButton)view.findViewById(R.id.tutorial_skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(this, MapsActivity.class);
            }
        });

        if(page == 0) {
            mae.setVisibility(View.INVISIBLE);
            skip.setVisibility(View.INVISIBLE);
        }else {
            mae.setVisibility(View.VISIBLE);
            skip.setVisibility(View.VISIBLE);
        }
        next = (ImageButton)view.findViewById(R.id.tutorial_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(+1);
            }
        });
        if(page == FINAL_PAGE)
            next.setImageResource(R.drawable.tutorial_close);
        else
            next.setImageResource(R.drawable.tutorial_close);

        return view;
    }
    //ボタンを押したときにviewpagerの移動
    //iは、1で進む、-1で戻る
    private void moveViewPager(int i){
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.tutorial_viewPager);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + i);
    }
}
