package com.example.b1014100_2.projectmainver3.zukan;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;

public class ZukanDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Log.d("fishid", "onCreate: " + intent.getIntExtra("id", 0));
        setContentView(R.layout.activity_zukan_detail);
        setViews(intent.getIntExtra("id", 0));
        setButton();
    }

    @Override
    protected void onPause() {
////        super.onPause();
//        Intent intent = new Intent();
//        // keyword "RESULT" でデータの可算結果 value を返す
//        intent.putExtra("currentPage", 1);
//        setResult(RESULT_OK, intent);
//        finish();
//        Log.d("test", "onPause: ");
        super.onPause();
    }

    public void setViews(int fishId) {
        Zukan zukan = ZukanListActivity.zukans.get(fishId);
        ImageView image = (ImageView) findViewById(R.id.zukan_detail_image);
        //文字列から画像のdrawableのIDを取得する
        int imageId = getResources().getIdentifier(zukan.getImageName(), "drawable", getPackageName());
        //画像をImageViewにセットする
        image.setImageResource(imageId);
        //魚の名前セット
        TextView fishNameView = (TextView) findViewById(R.id.zukan_detail_fish_name);
         fishNameView.setTypeface(Typeface.createFromAsset(getAssets(), "FUJIPOP.TTC"));
        fishNameView.setText(zukan.getName());
        //魚の種類セット
        TextView fishTypeView = (TextView) findViewById(R.id.zukan_detail_fish_type);
        fishTypeView.setTypeface(Typeface.createFromAsset(getAssets(), "FUJIPOP.TTC"));
        fishTypeView.setText(zukan.getType());
//        ((TextView) findViewById(R.id.zukan_detail_fish_ka)).setText(zukan.getType());
        //魚の大きさセット
        TextView fishLengthView = (TextView) findViewById(R.id.zukan_detail_fish_length);
        fishLengthView.setTypeface(Typeface.createFromAsset(getAssets(), "FUJIPOP.TTC"));
//        ((TextView) findViewById(R.id.zukan_detail_fish_length)).setText(zukan.getLength() + "cm");
        fishLengthView.setText(zukan.getLength() + "cm");
        //説明文セット
        String hoge = zukan.getContent();
        hoge = hoge.replaceAll("\\\\n", "\n");
        TextView fishContentView = (TextView) findViewById(R.id.zukan_detail_fish_content);
        fishContentView.setTypeface(Typeface.createFromAsset(getAssets(), "FUJIPOP.TTC"));
        fishContentView.setText(hoge);
//        ((TextView) findViewById(R.id.textView4)).setText(zukan.getContent());

    }

    private void setButton() {
        //戻るボタン
        ImageButton buttonList = (ImageButton) findViewById(R.id.zukan_detail_back_button);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //戻るボタン
        ImageButton buttonListd = (ImageButton) findViewById(R.id.zukan_detail_quiz_button);
        buttonListd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
