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
import android.widget.LinearLayout;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

public class ZukanFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";
    private final static String MAX_PAGE = "maxPage";
    private final static String POSITION = "potion";

    static final int RESULT_SUBACTIVITY = 1000;

//    private ArrayList<Zukan> zukans = ZukanDatabase.getZukanAll();
//    private ArrayList<Zukan> zukans = ZukanDatabase.getZukan(null, null, "春");
    private ArrayList<Zukan> zukans = ZukanActivity.zukans;
//    private ArrayList<Zukan> zukans = Zukan.zukanCrate();
    int i;

    public static ZukanFragment newInstance(int position, int maxPage) {
        //page数をBundleに詰める
        ZukanFragment fragment = new ZukanFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MAX_PAGE, maxPage);
        bundle.putInt(POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final int page = getArguments().getInt(POSITION);
        int maxPage = getArguments().getInt(MAX_PAGE);
        Log.d("ZukanFragment", "onCreateView: " + page + " max:" + maxPage);

        View view = inflater.inflate(R.layout.fragment_zukan, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_zukan);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));

        //右左bottonのクリックリスナー
        Button leftAllow = (Button) getActivity().findViewById(R.id.zukan_left);
        leftAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(-1);
            }
        });
        Button rightAllow = (Button) getActivity().findViewById(R.id.zukan_right);
        rightAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(1);
            }
        });

        //ボタンのリソース配列
        int[] zukanResId = new int[] {R.id.zukan1, R.id.zukan2, R.id.zukan3, R.id.zukan4};
        //ボタンのfishId配列
        final int[] fishIds = new int[4];
        for(int i = 0; i < 4; i++){
            if((page * 4 + i + 1) <= zukans.size()) {
                fishIds[i] = page * 4 + i + 1;
            }else{
                fishIds[i] = 0;
            }
        }


//        for(i = 0; i < 4; i++){
//            ImageButton image = (ImageButton) view.findViewById(zukanResId[i]);
//            //文字列から画像のdrawableのIDを取得する
//            int imageId = getResources().getIdentifier(zukans.get(page+i).getImageName(), "drawable", getActivity().getPackageName());
//            //画像をImageViewにセットする
//            image.setImageResource(imageId);
//            image.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view) {
//                    Log.d("Test", "onClick: Zukanid = "+page+i);
//                    Intent intent = new Intent(getActivity(),ZukanDetailActivity.class); //図鑑アクティビティにに飛ぶ処理
//                    intent.putExtra("id",page + i);
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

        //2個目のコンテンツセット
//        if(fishIds[1] != 0) {
//            ImageButton image2 = (ImageButton) view.findViewById(R.id.zukan2);
//            image2.setVisibility(View.VISIBLE);
//            //文字列から画像のdrawableのIDを取得する
//            int imageId2 = getResources().getIdentifier(zukans.get(fishIds[1]-1).getImageName(), "drawable", getActivity().getPackageName());
//            //画像をImageViewにセットする
//            image2.setImageResource(imageId2);
//            image2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d("Test", "onClick: Zukanid = " + fishIds[1]);
//                    Intent intent = new Intent(getActivity(), ZukanDetailActivity.class); //図鑑アクティビティにに飛ぶ処理
//                    intent.putExtra("id", fishIds[1]);
//                    getActivity().startActivityForResult(intent, 1000);
////                startActivity(intent);
//                }
//            });
//        }
//
//        //3個目のコンテンツセット
//        if(fishIds[2] != 0) {
//            ImageButton image3 = (ImageButton) view.findViewById(R.id.zukan3);
//            image3.setVisibility(View.VISIBLE);
//            //文字列から画像のdrawableのIDを取得する
//            int imageId3 = getResources().getIdentifier(zukans.get(fishIds[2]-1).getImageName(), "drawable", getActivity().getPackageName());
//            //画像をImageViewにセットする
//            image3.setImageResource(imageId3);
//            image3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d("Test", "onClick: Zukanid = " + fishIds[2]);
//                    Intent intent = new Intent(getActivity(), ZukanDetailActivity.class); //図鑑アクティビティにに飛ぶ処理
//                    intent.putExtra("id", fishIds[2]);
//                    getActivity().startActivityForResult(intent, 1000);
////                startActivity(intent);
//                }
//            });
//        }
//
//        //4個目のコンテンツセット
//        if(fishIds[3] != 0) {
//            ImageButton image4 = (ImageButton) view.findViewById(R.id.zukan4);
//            image4.setVisibility(View.VISIBLE);
//            //文字列から画像のdrawableのIDを取得する
//            int imageId4 = getResources().getIdentifier(zukans.get(fishIds[3]-1).getImageName(), "drawable", getActivity().getPackageName());
//            //画像をImageViewにセットする
//            image4.setImageResource(imageId4);
//            image4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d("Test", "onClick: Zukanid = " + fishIds[3]);
//                    Intent intent = new Intent(getActivity(), ZukanDetailActivity.class); //図鑑アクティビティにに飛ぶ処理
//                    intent.putExtra("id", fishIds[3]);
//                    getActivity().startActivityForResult(intent, 1000);
////                startActivity(intent);
//                }
//            });
//        }

        return view;
    }

    //ボタンを押したときにviewpagerの移動
    //iは、1で進む、-1で戻る
    private void moveViewPager(int i){
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.Zukan_ViewPager);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + i);
    }

}