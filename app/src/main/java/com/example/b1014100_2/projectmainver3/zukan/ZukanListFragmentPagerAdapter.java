package com.example.b1014100_2.projectmainver3.zukan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Allen on 2016/10/07.
 */

public class ZukanListFragmentPagerAdapter extends FragmentPagerAdapter{

    public ZukanListFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ZukanListFragment.newInstance();
    }

    @Override
    public int getCount() {
        return getPageSize();
    }

    //ページ数を動的に指定するためのメソッド
    public int getPageSize() {
        return 2;
    }
}
