package com.example.b1014100_2.projectmainver3.zukan;

import android.app.Activity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by 1014159 on 2016/10/28.
 */

public enum ZukanListSortEvent {

    SORT_SEASON_SPRING {
        @Override
        public void apply(Activity activity) {
            ZukanListActivity.zukans = new ZukanDatabase(activity).getZukanSeason(ZukanDatabase.SEASON_SPRING);
        }
    },
    SORT_SEASON_SUMMER {
        @Override
        public void apply(Activity activity) {
            // Fragment→ActivityのActivity側の処理とか
        }
    },
    EVENT3 {
        @Override
        public void apply(Activity activity) {
            // Fragment→ActivityのActivity側の処理とか
        }
    };

    abstract public void apply(Activity activity);
}
