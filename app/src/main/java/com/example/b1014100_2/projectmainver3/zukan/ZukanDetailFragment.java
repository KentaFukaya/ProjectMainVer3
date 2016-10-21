package com.example.b1014100_2.projectmainver3.zukan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ZukanDetailFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

//    private ArrayList<Zukan> zukans = ZukanDatabase.getZukanAll();
//private ArrayList<Zukan> zukans = ZukanDatabase.getZukan(null, null, "春");
    private ArrayList<Zukan> zukans = ZukanActivity.zukans;
//    private ArrayList<Zukan> zukans = Zukan.zukanCrate();

    public static ZukanDetailFragment newInstance(int fishId) {
        //page数をBundleに詰める
        ZukanDetailFragment fragment = new ZukanDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", fishId);
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
        final int fishId = getArguments().getInt("id");

        View view = inflater.inflate(R.layout.fragment_detail_zukan, null);
//        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_zukan);

        //右左bottonのクリックリスナー
        Button leftAllow = (Button) getActivity().findViewById(R.id.zukan_detail_left);
        leftAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(-1);
            }
        });
        Button rightAllow = (Button) getActivity().findViewById(R.id.zukan_detail_right);
        rightAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveViewPager(1);
            }
        });

        // MainActivity へ戻るボタン
        ImageButton backButton = (ImageButton) view.findViewById(R.id.ZukanDetail_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // keyword "RESULT" でデータの可算結果 value を返す
                intent.putExtra("currentPage", fishId / 4);
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
//                Log.d("","return");
            }
        });

        //魚の画像の表示
        ImageView imageView = (ImageView) view.findViewById(R.id.ZukanDetail_pic);
        //文字列から画像のdrawableのIDを取得する
        int imageId = getResources().getIdentifier(zukans.get(fishId).getImageName(), "drawable", getActivity().getPackageName());
        //画像をImageViewにセットする
        imageView.setImageResource(imageId);

        //魚の名前の表示
        TextView pageFishName = (TextView)view.findViewById(R.id.ZukanDetail_fishName);
        pageFishName.setText(zukans.get(fishId).getName());
        //魚の概要の表示
        TextView abstractText = (TextView) view.findViewById(R.id.ZukanDetail_abstarct);
        abstractText.setText(zukans.get(fishId).getContent());


        return view;
    }

    //ボタンを押したときにviewpagerの移動
    //iは、1で進む、-1で戻る
    private void moveViewPager(int i){
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.Zukan_Detail_ViewPager);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + i);
    }

}