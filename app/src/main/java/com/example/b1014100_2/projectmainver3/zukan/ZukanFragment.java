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

    private ArrayList<Zukan> zukans = Zukan.zukanCrate();
    int i;

    public static ZukanFragment newInstance(int position, int maxPage) {
//        ZukanFragment frag = new ZukanFragment();
//        Bundle b = new Bundle();
//        b.putInt(BACKGROUND_COLOR, IdRes);
//        frag.setArguments(b);
//        return frag;
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

        View view = inflater.inflate(R.layout.fragment_zukan, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_zukan);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));

        //右左bottonのクリックリスナー
        Button leftAllow = (Button) getActivity().findViewById(R.id.button4);
        leftAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(-1);
            }
        });
        Button rightAllow = (Button) getActivity().findViewById(R.id.button5);
        rightAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(1);
            }
        });

        //ボタンのリソース配列
        int[] zukanResId = new int[] {R.id.zukan1, R.id.zukan2, R.id.zukan3, R.id.zukan4 };

        for(i = 0; i < 4; i++){
            ImageButton image = (ImageButton) view.findViewById(zukanResId[i]);
            //文字列から画像のdrawableのIDを取得する
            int imageId = getResources().getIdentifier(zukans.get(page).getImageName(), "drawable", getActivity().getPackageName());
            //画像をImageViewにセットする
            image.setImageResource(imageId);
            image.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.d("Test", "onClick: Zukanid = "+page);
                    Intent intent = new Intent(getActivity(),ZukanDetailActivity.class); //図鑑アクティビティにに飛ぶ処理
                    intent.putExtra("id",page + i);
                    startActivity(intent);
                }
            });
        }


        //1個目のコンテンツセット
        ImageButton image1 = (ImageButton) view.findViewById(R.id.zukan1);
        //文字列から画像のdrawableのIDを取得する
        int imageId1 = getResources().getIdentifier(zukans.get(page).getImageName(), "drawable", getActivity().getPackageName());
        //画像をImageViewにセットする
        image1.setImageResource(imageId1);
        image1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("Test", "onClick: Zukanid = "+page);
                Intent intent = new Intent(getActivity(),ZukanDetailActivity.class); //図鑑アクティビティにに飛ぶ処理
                intent.putExtra("id",page);
                startActivity(intent);
            }
        });

        //2個目のコンテンツセット
        ImageButton image2 = (ImageButton) view.findViewById(R.id.zukan2);
        //文字列から画像のdrawableのIDを取得する
        int imageId2 = getResources().getIdentifier(zukans.get(page + 1).getImageName(), "drawable", getActivity().getPackageName());
        //画像をImageViewにセットする
        image2.setImageResource(imageId2);

        //3個目のコンテンツセット
        ImageButton image3 = (ImageButton) view.findViewById(R.id.zukan3);
        //文字列から画像のdrawableのIDを取得する
        int imageId3 = getResources().getIdentifier(zukans.get(page + 2).getImageName(), "drawable", getActivity().getPackageName());
        //画像をImageViewにセットする
        image3.setImageResource(imageId3);

        //4個目のコンテンツセット
        ImageButton image4 = (ImageButton) view.findViewById(R.id.zukan4);
        //文字列から画像のdrawableのIDを取得する
        int imageId4 = getResources().getIdentifier(zukans.get(page + 3).getImageName(), "drawable", getActivity().getPackageName());
        //画像をImageViewにセットする
        image4.setImageResource(imageId4);

        return view;
    }

    //ボタンを押したときにviewpagerの移動
    //iは、1で進む、-1で戻る
    private void moveViewPager(int i){
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.Zukan_ViewPager);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + i);
    }

}