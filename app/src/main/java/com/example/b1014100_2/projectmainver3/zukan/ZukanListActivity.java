package com.example.b1014100_2.projectmainver3.zukan;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

public class ZukanListActivity extends AppCompatActivity {

    //QuizSQLiteOpenHelperで使う
    private static Context ctx;
    //表示する図鑑データ
    public static ArrayList<Zukan> zukans;

    ViewPager viewPager;
    ZukanListFragmentPagerAdapter adapter;

    static final int RESULT_SUBACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //QuizSQLiteOpenHelperで使う
        ctx = this;
        //図鑑を全て表示にする
        zukans = ZukanDatabase.getZukanAll();

        setContentView(R.layout.activity_zukan_list);
        setViews();
        setButton();
    }

    private void setViews() {

        FragmentManager manager = getSupportFragmentManager();
        viewPager = (ViewPager) findViewById(R.id.zukan_list_viewpager);
        if(adapter != null) adapter.notifyDataSetChanged();
        adapter = new ZukanListFragmentPagerAdapter(manager);
        viewPager.setAdapter(adapter);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.zukan_list_drawer_view, new ZukanListSortSyllabaryFragment());
        fragmentTransaction.commit();

        ((DrawerLayout) findViewById(R.id.zukan_list_drawer_layout)).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void setButton(){

        //戻るボタン
        ImageButton buttonList = (ImageButton) findViewById(R.id.zukan_list_back_button);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //五十音順ソートボタン
        findViewById(R.id.zukan_list_sort_syllabary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("syllabary", "onClick: ");
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.zukan_list_drawer_view, new ZukanListSortSyllabaryFragment());
                fragmentTransaction.commit();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.zukan_list_drawer_layout);
                drawer.openDrawer(GravityCompat.END);
            }
        });

        //種類順ソートボタン
        findViewById(R.id.zukan_list_sort_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSortDrawer(new ZukanListSortTypeFragment());
            }
        });

        //季節順ソートボタン
        findViewById(R.id.zukan_list_sort_season).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSortDrawer(new ZukanListSortSeasonFragment());
            }
        });
    }

    private void openSortDrawer(Fragment newFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.zukan_list_drawer_view, newFragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.zukan_list_drawer_layout);
        drawer.openDrawer(GravityCompat.END);
    }

    //QuizSQLiteOpenHelperで使う
    public static Context getContext() {
        return ctx;
    }
}
