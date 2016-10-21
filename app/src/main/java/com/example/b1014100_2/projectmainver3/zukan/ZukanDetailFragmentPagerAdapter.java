package com.example.b1014100_2.projectmainver3.zukan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Allen on 2016/10/07.
 */

public class ZukanDetailFragmentPagerAdapter extends FragmentPagerAdapter{

    ArrayList<Zukan> zukans = Zukan.zukanCrate();

    public ZukanDetailFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //図鑑の最大個数を渡す
        return ZukanDetailFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return getPageSize();
    }

    //ページ数を動的に指定するためのメソッド
    public int getPageSize() {
        return zukans.size();
//        int pageSize = zukans.size();//zukanitemの総数を取得
        /*
        * ex3の時) pageSize == 12 の場合　12 / 3 == 4を返す
        * 　　pageSize == 13 or 14　の場合　13 / 3 + 1= 5 を返す
        */
//        if (zukans.size() % 4 == 0){
//            return pageSize / 4;
//        }else {
//            return pageSize / 4 + 1;
//        }
    }
}
