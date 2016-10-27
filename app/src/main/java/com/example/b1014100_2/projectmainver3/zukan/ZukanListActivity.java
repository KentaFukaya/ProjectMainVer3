package com.example.b1014100_2.projectmainver3.zukan;

import android.content.Context;
import android.content.Intent;
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

    ViewPager viewPager;
    ZukanListFragmentPagerAdapter adapter;

    static final int RESULT_SUBACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    private void setButton(){
        ImageButton buttonList = (ImageButton) findViewById(R.id.zukan_list_back_button);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ZukanDetailActivity.class);
                startActivity( intent );
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
    }


}
