package com.allen.alpagerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.alpagerview.utils.ResourceUtils;

/**
 * Created by Administrator on 2018-4-28.
 */

public class PagerAdapter extends android.support.v4.view.PagerAdapter {

    private Context context;
    private String layoutName;


    public PagerAdapter(Context context, String layoutName) {
        this.context = context;
        this.layoutName = layoutName;
    }

    @Override
    public int getCount() {
        return 5;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(
                ResourceUtils.getIdFromLayout(context, layoutName), null);
        view.setId(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container.findViewById(position));
    }


}