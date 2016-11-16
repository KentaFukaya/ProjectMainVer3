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
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;

public class ZukanListActivity extends AppCompatActivity implements ZukanListSortFragmentListener {

    public final static int TYPE_SYLLABARY = 1;
    public final static int TYPE_TYPE = 2;
    public final static int TYPE_SEASON = 3;

    //QuizSQLiteOpenHelperで使う
    private static Context ctx;
    //表示する図鑑データ
    public static ArrayList<Zukan> zukans;
    public static ArrayList<String> type_romajis;

    //ソート情報を保持
    public static int sortType;
    public static int sortNo;

    ViewPager viewPager;
    ZukanListFragmentPagerAdapter adapter;

    static final int RESULT_SUBACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test listactivity", "onCreate: ");
        //QuizSQLiteOpenHelperで使う
        ctx = this;
        //図鑑を全て表示にする
        zukans = new ZukanDatabase(this).getZukanAll();
        type_romajis = new ZukanDatabase(this).getZukanListSortTypeRomajis();

        sortType = 0;
        sortNo = 0;

        setContentView(R.layout.activity_zukan_list);
        setViews();
        setButton();
    }

    private void setViews() {

        FragmentManager manager = getSupportFragmentManager();
        viewPager = (ViewPager) findViewById(R.id.zukan_list_viewpager);
        if (adapter != null) adapter.notifyDataSetChanged();
        adapter = new ZukanListFragmentPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        // ページ生成数を変える
//        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        //viewpagerのスクロールバーを消す
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        //スクロールビューのスクロール消す
        ScrollView fishContentScrollView = (ScrollView) findViewById(R.id.zukan_list_drawer_view);
        fishContentScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        fishContentScrollView.setVerticalScrollBarEnabled(false);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.zukan_list_drawer_view, new ZukanListSortSyllabaryFragment());
        fragmentTransaction.commit();

        ((DrawerLayout) findViewById(R.id.zukan_list_drawer_layout)).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void setButton() {

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
                openSortDrawer(ZukanListSortSyllabaryFragment.newInstance());
            }
        });

        //種類順ソートボタン
        findViewById(R.id.zukan_list_sort_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSortDrawer(ZukanListSortTypeFragment.newInstance());
            }
        });

        //季節順ソートボタン
        findViewById(R.id.zukan_list_sort_season).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSortDrawer(ZukanListSortSeasonFragment.newInstance());
            }
        });

        //ソートクリアボタン
        findViewById(R.id.zukan_list_sort_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearZukans();
                viewPager.getAdapter().notifyDataSetChanged();
                viewPager.setCurrentItem(0);
                ((ImageView) findViewById(R.id.zukan_list_sort_unselected)).setImageResource(R.drawable.zukan_list_sort_unselected);
            }
        });
    }

    private void openSortDrawer(Fragment newFragment) {
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

    private void clearZukans() {
        zukans = new ZukanDatabase(this).getZukanAll();
        sortType = 0;
        sortNo = 0;
    }

    //fragmentからのリスナーを受け取る
    @Override
    public void onZukanListSortFragmentChange() {

        viewPager.getAdapter().notifyDataSetChanged();
        viewPager.setCurrentItem(0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.zukan_list_drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
    }
}

