package com.example.b1014100_2.projectmainver3.zukan;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.b1014100_2.projectmainver3.R;

public class ZukanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zukan);
        setViews();
    }

    private void setViews() {
        FragmentManager manager = getSupportFragmentManager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ZukanFragmentPagerAdapter adapter = new ZukanFragmentPagerAdapter(manager);
        viewPager.setAdapter(adapter);
    }


}
