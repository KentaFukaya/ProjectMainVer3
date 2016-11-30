package com.example.b1014100_2.projectmainver3.Tutorial;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.b1014100_2.projectmainver3.R;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        setViews();
    }
    private void setViews() {
        FragmentManager manager = getSupportFragmentManager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.tutorial_viewPager);
        TutorialFragmentPagerAdapter adapter = new TutorialFragmentPagerAdapter(manager);
        //viewpagerのスクロールバーを消す
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        viewPager.setAdapter(adapter);
    }
}
