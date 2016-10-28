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
    int zukanImgViewResId = R.id.zukan_list_image_view1;
    private ArrayList<Zukan> zukans;

    public static ZukanListFragment newInstance() {
        //page数をBundleに詰める
        ZukanListFragment fragment = new ZukanListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zukans = ZukanDatabase.getZukanAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zukan_list, null);

        setViews(view);

        return view;
    }

    private void setViews(View view){
//                for(int i = 0; i < 4; i++){
            ImageView image = (ImageView) view.findViewById(zukanImgViewResId);
            //文字列から画像のdrawableのIDを取得する
            int imageId = getResources().getIdentifier(zukans.get(0).getImageName(), "drawable", getActivity().getPackageName());
            //画像をImageViewにセットする
            image.setImageResource(R.drawable.zukan1);
        ((TextView)view.findViewById(R.id.zukan_list_fish_name1)).setText(zukans.get(0).getName());
//            image.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(getActivity(),ZukanDetailActivity.class); //図鑑アクティビティにに飛ぶ処理
//                    startActivity(intent);
//                }
//            });
//        }



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