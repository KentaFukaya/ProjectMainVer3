package com.example.b1014100_2.projectmainver3;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.b1014100_2.projectmainver3.map.MapsActivity;


public class HomeActivity extends AppCompatActivity {
    private ImageButton Map_btn;
    private SoundPool soundPool;
    private int diving_se; // 正解の効果音の識別ID
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*------------------set efect sounds--------------------------*/
        // Android 5.0(Lolipop)より古いかどうかでSoundPoolの使い方は変わってくる
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Android 5.0(Lolipop)より古いとき
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        } else {
            // Android 5.0(Lolipop)以降
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(2)
                    .build();
        }
        diving_se = soundPool.load(this, R.raw.diving, 1); // 正解の効果音の識別IDを保存


        Map_btn = (ImageButton) findViewById(R.id.home_mapButton); //ダイビングアクティビティに飛ぶボタンのidを渡す

        Map_btn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //押したときの動作
                    Map_btn.setImageResource(R.drawable.home_divingbutton_ontouch);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //離したときの動作
                    Map_btn.setImageResource(R.drawable.home_divingbutton);
                }
                return false; //trueにすると他のリスナーが呼ばれない
            }
        });

        Map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(diving_se, 1F, 1F, 0, 0, 1F);
                Intent intent = new Intent(HomeActivity.this, MapsActivity.class); //ダイビングアクティビティに飛ぶ処理
                startActivity(intent);
               // soundPool.release();
            }
        });
    }
}
