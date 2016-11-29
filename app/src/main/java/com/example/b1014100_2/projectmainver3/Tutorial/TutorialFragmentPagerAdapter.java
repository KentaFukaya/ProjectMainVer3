package com.example.b1014100_2.projectmainver3.Tutorial;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by b1014100_2 on 2016/11/25.
 */

public class TutorialFragmentPagerAdapter extends FragmentStatePagerAdapter {
    public TutorialFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TutorialFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 9;
    }

}
