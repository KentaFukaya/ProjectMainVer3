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

import com.example.b1014100_2.projectmainver3.Tutorial.TutorialActivity;
import com.example.b1014100_2.projectmainver3.map.MapsActivity;


public class HomeActivity extends AppCompatActivity {
    private ImageButton Map_btn,Tutorial_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*----------------------mapbtn------------------------------------*/
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
                //Intent intent = new Intent(HomeActivity.this, MapsActivity.class); //ダイビングアクティビティに飛ぶ処理
                //startActivity(intent);

                startTutorila();
            }
        });

    }

    public void startTutorila(){
        Intent intent1 = new Intent(HomeActivity.this, TutorialActivity.class); //move to  TutorialActivity
        Intent intent2 = new Intent(HomeActivity.this,MapsActivity.class);

        startActivity(intent2);
        startActivity(intent1);
    }
}
