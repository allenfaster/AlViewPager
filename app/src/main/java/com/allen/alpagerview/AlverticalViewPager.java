package com.allen.alpagerview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

/**
 * Created by Administrator on 2018-4-28.
 */

public class AlverticalViewPager extends VerticalViewPager {

    AlViewPager alViewPager;


    public AlverticalViewPager(Context context) {
        super(context);
    }

    public AlverticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        super.setOnPageChangeListener(listener);
        alViewPager = (AlViewPager) listener;
    }

    public int getCurrentPage() {

        if (alViewPager == null) {
           return -1;
        }
        return  alViewPager.getCurrentPage();
    }

    public int getLastListPosition() {

        if (alViewPager == null) {
            return -1;
        }
        return  alViewPager.getLastListPosition();
    }
}
