package com.example.b1014100_2.projectmainver3.Tutorial;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by b1014100_2 on 2016/11/26.
 */

public class TutorialViewPagaer extends ViewPager{
    public TutorialViewPagaer(Context context) {
        super(context);
    }

    public TutorialViewPagaer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }
}
