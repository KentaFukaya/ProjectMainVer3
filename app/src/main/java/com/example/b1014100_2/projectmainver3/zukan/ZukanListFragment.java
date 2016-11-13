package com.example.b1014100_2.projectmainver3.zukan;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

public class ZukanListFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    static final int RESULT_SUBACTIVITY = 1000;

    int i;//forで使うために

    int zukanImgViewResId[] = {
            R.id.zukan_list_fish_image1, R.id.zukan_list_fish_image2, R.id.zukan_list_fish_image3, R.id.zukan_list_fish_image4,
            R.id.zukan_list_fish_image5, R.id.zukan_list_fish_image6, R.id.zukan_list_fish_image7, R.id.zukan_list_fish_image8};
    int zukanFishNameViewResId[] = {
            R.id.zukan_list_fish_name1, R.id.zukan_list_fish_name2, R.id.zukan_list_fish_name3, R.id.zukan_list_fish_name4,
            R.id.zukan_list_fish_name5, R.id.zukan_list_fish_name6, R.id.zukan_list_fish_name7, R.id.zukan_list_fish_name8};
    int zukanListItemResId[] = {
            R.id.zukan_list_item1, R.id.zukan_list_item2, R.id.zukan_list_item3, R.id.zukan_list_item4,
            R.id.zukan_list_item5, R.id.zukan_list_item6, R.id.zukan_list_item7, R.id.zukan_list_item8};

    //ボタンのfishId配列
    final int[] fishIds = new int[8];

    private ArrayList<Zukan> zukans;

    public static ZukanListFragment newInstance(int page, int finalPage) {
        //page数をBundleに詰める
        ZukanListFragment fragment = new ZukanListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page", page);
        bundle.putInt("final_page", finalPage);
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
        String font = "noadd_FUJIPOP.TTC";
        //最初のページの時、前へ見えなくする
        if(getArguments().getInt("page") == 0){
            view.findViewById(R.id.zukan_list_prev).setVisibility(View.INVISIBLE);
        }else{
            view.findViewById(R.id.zukan_list_prev).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
//                    moveViewPager(-1);
                }
            });
        }
        //最後のページの時、次へ見えなくする
        if(getArguments().getInt("page") == getArguments().getInt("final_page") - 1){
            view.findViewById(R.id.zukan_list_next).setVisibility(View.INVISIBLE);
        }else{
            view.findViewById(R.id.zukan_list_next).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
//                    moveViewPager(1);
                }
            });
        }

        for (i = 0; i < 8; i++) {
            if (fishIds[i] != 0) {
                final int fishId = fishIds[i]-1;
                ImageView image = (ImageView) view.findViewById(zukanImgViewResId[i]);
                //文字列から画像のdrawableのIDを取得する
                int imageId = getResources().getIdentifier(zukans.get(fishId).getImageName(), "drawable", getActivity().getPackageName());
                //画像をImageViewにセットする
//                image.setImageResource(imageId);
                image.setImageBitmap(decodeSampledBitmapFromResource(getResources(), imageId, 50, 50));
                //魚の名前をセット
                TextView fishNameView = (TextView) view.findViewById(zukanFishNameViewResId[i]);
                fishNameView.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), font));
                fishNameView.setText(zukans.get(fishId).getName());
                //図鑑詳細画面に遷移リスナー
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
    }

    //ボタンを押したときにviewpagerの移動
    //iは、1で進む、-1で戻る
    private void moveViewPager(int i){
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.zukan_list_viewpager);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + i);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}