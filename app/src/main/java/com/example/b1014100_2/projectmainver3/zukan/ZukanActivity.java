package com.example.b1014100_2.projectmainver3.zukan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

public class ZukanActivity extends AppCompatActivity {

    private static int currentPage;
    //QuizSQLiteOpenHelperで使う
    private static Context ctx;
    //表示する図鑑データ
    static ArrayList<Zukan> zukans;


    static final int RESULT_SUBACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ページを最初にする
        currentPage = 0;
        //QuizSQLiteOpenHelperで使う
        ctx = this;
        //図鑑を全て表示にする
        zukans = ZukanDatabase.getZukanAll();
        zukans = ZukanDatabase.getZukan(null, null, "春");
        setContentView(R.layout.activity_zukan);
        setViews(currentPage);
        setButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        Log.d("", "onActivityResult: " + requestCode + " result: "+ resultCode);
        if(resultCode == RESULT_OK && requestCode == RESULT_SUBACTIVITY && null != intent) {
            int res = intent.getIntExtra("currentPage", 0);
            currentPage = res;
            Log.d("currentpage", "onActivityResult: " + currentPage);
            setViews(currentPage);
        }
    }

    //QuizSQLiteOpenHelperで使う
    public static Context getContext() {
        return ctx;
    }

    private void setViews(int currentPage) {
        FragmentManager manager = getSupportFragmentManager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.Zukan_ViewPager);
        ZukanFragmentPagerAdapter adapter = new ZukanFragmentPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentPage);
    }

    private void setButton(){
        Button buttonSpring = (Button) findViewById(R.id.season_spring);
        buttonSpring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zukans = ZukanDatabase.getZukan(null, null, "春");
                setViews(currentPage);
            }
        });

        Button buttonReset = (Button) findViewById(R.id.season_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zukans = ZukanDatabase.getZukan(null, null, null);
                setViews(currentPage);
            }
        });
    }

    public  void toDetailActivity(int fishId){
        Intent intent = new Intent(getApplication(), ZukanDetailActivity.class);
        intent.putExtra("id",fishId);
        int requestCode = RESULT_SUBACTIVITY;
        startActivityForResult( intent, requestCode );
    }

}
