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
        return ZukanListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return getPageSize();
    }

    //ページ数を動的に指定するためのメソッド
    public int getPageSize() {
        int pageSize = ZukanListActivity.zukans.size();//zukanitemの総数を取得
        /*
        * ex3の時) pageSize == 12 の場合　12 / 3 == 4を返す
        * 　　pageSize == 13 or 14　の場合　13 / 3 + 1= 5 を返す
        */
        if (ZukanListActivity.zukans.size() % 8 == 0){
            return pageSize / 8;
        }else {
            return pageSize / 8 + 1;
        }
    }
}
