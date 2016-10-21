package com.example.b1014100_2.projectmainver3.zukan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.b1014100_2.projectmainver3.R;

public class ZukanActivity extends AppCompatActivity {

    private static int currentPage;
    //QuizSQLiteOpenHelperで使う
    private static Context ctx;

    static final int RESULT_SUBACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ページを最初にする
        currentPage = 0;
        //QuizSQLiteOpenHelperで使う
        ctx = this;
        setContentView(R.layout.activity_zukan);
        setViews(currentPage);
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

    public  void toDetailActivity(int fishId){
        Intent intent = new Intent(getApplication(), ZukanDetailActivity.class);
        intent.putExtra("id",fishId);
        int requestCode = RESULT_SUBACTIVITY;
        startActivityForResult( intent, requestCode );
    }

}
