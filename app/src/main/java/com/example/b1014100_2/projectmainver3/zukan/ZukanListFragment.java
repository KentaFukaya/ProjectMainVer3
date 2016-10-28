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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

public class ZukanListFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    static final int RESULT_SUBACTIVITY = 1000;

    int i;//for

    int zukanImgViewResId[] = {
            R.id.zukan_list_image_view1, R.id.zukan_list_image_view2, R.id.zukan_list_image_view3, R.id.zukan_list_image_view4,
            R.id.zukan_list_image_view5, R.id.zukan_list_image_view6, R.id.zukan_list_image_view7, R.id.zukan_list_image_view8};
    int zukanFishNameViewResId[] = {
            R.id.zukan_list_fish_name1, R.id.zukan_list_fish_name2, R.id.zukan_list_fish_name3, R.id.zukan_list_fish_name4,
            R.id.zukan_list_fish_name5, R.id.zukan_list_fish_name6, R.id.zukan_list_fish_name7, R.id.zukan_list_fish_name8};
    int zukanListItemResId[] = {
            R.id.zukan_list_item1, R.id.zukan_list_item2, R.id.zukan_list_item3, R.id.zukan_list_item4,
            R.id.zukan_list_item5, R.id.zukan_list_item6, R.id.zukan_list_item7, R.id.zukan_list_item8};

    //ボタンのfishId配列
    final int[] fishIds = new int[8];

    private ArrayList<Zukan> zukans;

    public static ZukanListFragment newInstance(int page) {
        //page数をBundleに詰める
        ZukanListFragment fragment = new ZukanListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page", page);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zukans = ZukanListActivity.zukans;

        //ボタンのfishId配列set
        int page = getArguments().getInt("page");
        for(int i = 0; i < 8; i++){
            if((page * 8 + i + 1) <= zukans.size()) {
                fishIds[i] = page * 8 + i + 1;
            }else{
                fishIds[i] = 0;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zukan_list, null);
        setViews(view);

        return view;
    }

    private void setViews(View view) {
        for (i = 0; i < 8; i++) {
            if (fishIds[i] != 0) {
                final int fishId = fishIds[i]-1;
                ImageView image = (ImageView) view.findViewById(zukanImgViewResId[i]);
                //文字列から画像のdrawableのIDを取得する
                int imageId = getResources().getIdentifier(zukans.get(fishId).getImageName(), "drawable", getActivity().getPackageName());
                //画像をImageViewにセットする
                image.setImageResource(imageId);
                ((TextView) view.findViewById(zukanFishNameViewResId[i])).setText(zukans.get(fishId).getName());

                view.findViewById(zukanListItemResId[i]).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ZukanDetailActivity.class); //図鑑アクティビティにに飛ぶ処理
                        intent.putExtra("id", fishId);
                        startActivity(intent);
                    }
                });
            }
        }



        //1個目のコンテンツセット
//        if(fishIds[0] != 0) {
//            ImageButton image1 = (ImageButton) view.findViewById(R.id.zukan1 + i);
//            //ボタンを表示
//            image1.setVisibility(View.VISIBLE);
//            //文字列から画像のdrawableのIDを取得する
//            int imageId1 = getResources().getIdentifier(zukans.get(fishIds[0] - 1).getImageName(), "drawable", getActivity().getPackageName());
//            //画像をImageViewにセットする
//            image1.setImageResource(imageId1);
//            image1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d("Test", getActivity() + "onClick: Zukanid = " + fishIds[0]);
//                    Intent intent = new Intent(getActivity(), ZukanDetailActivity.class); //図鑑アクティビティにに飛ぶ処理
//                    intent.putExtra("id", fishIds[0]);
//                    getActivity().startActivityForResult(intent, RESULT_SUBACTIVITY);
////                startActivity(intent);
//                }
//            });
//        }
    }
}