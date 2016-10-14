package com.example.b1014100_2.projectmainver3.zukan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.b1014100_2.projectmainver3.R;


//test
public class ZukanDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Log.d("fishid", "onCreate: " + intent.getIntExtra("id", 0));
//        data1 = intent.getIntExtra("DATA1", 0);
        setContentView(R.layout.activity_zukan_detail);
        setViews(intent.getIntExtra("id", 0));
    }

    @Override
    protected void onPause() {
//        super.onPause();
        Intent intent = new Intent();
        // keyword "RESULT" でデータの可算結果 value を返す
        intent.putExtra("currentPage", 1);
        setResult(RESULT_OK, intent);
        finish();
        Log.d("test", "onPause: ");
        super.onPause();
    }

    public void setViews(int fishId) {
        FragmentManager manager = getSupportFragmentManager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.Zukan_Detail_ViewPager);
        ZukanDetailFragmentPagerAdapter adapter = new ZukanDetailFragmentPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(fishId - 1);
    }
}
