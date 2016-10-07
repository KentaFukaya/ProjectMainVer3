package com.example.b1014100_2.projectmainver3.zukan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Allen on 2016/10/07.
 */

public class ZukanFragmentPagerAdapter extends FragmentPagerAdapter{

    public ZukanFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ZukanFragment.newInstance(android.R.color.holo_blue_bright);
            case 1:
                return ZukanFragment.newInstance(android.R.color.holo_green_light);
            case 2:
                return ZukanFragment.newInstance(android.R.color.holo_red_dark);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "ページ" + (position + 1);
    }
}
