package com.example.b1014100_2.projectmainver3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.b1014100_2.projectmainver3.map.MapsActivity;

public class HomeActivity extends AppCompatActivity {
    private Button Map_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Map_btn = (Button)findViewById(R.id.map_button); //ダイビングアクティビティに飛ぶボタンのidを渡す
        Map_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(HomeActivity.this, MapsActivity.class); //ダイビングアクティビティに飛ぶ処理
                startActivity(intent);
            }
        });
    }
}
