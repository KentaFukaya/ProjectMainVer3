package com.example.b1014100_2.projectmainver3.zukan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Allen on 2016/10/07.
 */

public class ZukanFragmentPagerAdapter extends FragmentPagerAdapter{

//    ArrayList<Zukan> zukans = Zukan.zukanCrate();
    ArrayList<Zukan> zukans = ZukanListActivity.zukans;


    public ZukanFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //図鑑の最大個数を渡す
        return ZukanFragment.newInstance(position, zukans.size());
//        switch (position) {
//            case 0:
//                return ZukanFragment.newInstance(android.R.color.holo_blue_bright);
//            case 1:
//                return ZukanFragment.newInstance(android.R.color.holo_green_light);
//            case 2:
//                return ZukanFragment.newInstance(android.R.color.holo_red_dark);
//        }
//        return null;
    }

    @Override
    public int getCount() {
        return getPageSize();
    }

    //ページ数を動的に指定するためのメソッド
    public int getPageSize() {
        int pageSize = zukans.size();//zukanitemの総数を取得
        /*
        * ex3の時) pageSize == 12 の場合　12 / 3 == 4を返す
        * 　　pageSize == 13 or 14　の場合　13 / 3 + 1= 5 を返す
        */
        if (zukans.size() % 4 == 0){
            return pageSize / 4;
        }else {
            return pageSize / 4 + 1;
        }
    }
}
