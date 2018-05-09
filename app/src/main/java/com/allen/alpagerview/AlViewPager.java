package com.allen.alpagerview;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-4-28.
 */

public class AlViewPager extends ViewPager.SimpleOnPageChangeListener implements ViewPager.PageTransformer,
        CompoundButton.OnCheckedChangeListener{

    //上一次的位移
    private int lastValue = -1;
    //页面ID
    private int mRoomId = -1;
    //list上次的位置记录
    private int lastListPosition = -1;
    //是否向下滑动
    private boolean isdown = false;
    //page是否改变
    private boolean mIsChanged = false;
    //当前页面ID
    private int mCurrentItem;

    private RelativeLayout mRoomContainer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private FragmentManager fragmentManager;
    private ShadowTransformer mFragmentCardShadowTransformer;
    //左右
    private ViewPager inkeViewPager;
    //上下
    private AlverticalViewPager verticalViewPager;
    private Context context;

    private List<List<CardFragment>> list = new ArrayList<>();


    public AlViewPager(Context context, FragmentManager fragmentManager, List<List<CardFragment>> list, AlverticalViewPager verticalViewPager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.list = list;
        this.verticalViewPager = verticalViewPager;
        mRoomContainer = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.view_room_container, null);
        inkeViewPager = (ViewPager) mRoomContainer.findViewById(R.id.viewPagercontainer);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (positionOffset != 0) {
            if (lastValue >= positionOffsetPixels) {
                //上滑
                isdown = false;
            } else if (lastValue < positionOffsetPixels) {
                //下滑
                isdown = true;
            }

        }
        lastValue = positionOffsetPixels;

    }

    @Override
    public void onPageSelected(int position) {
        mIsChanged=true;
        if(position==0){
            mCurrentItem=3;
        }else if(position==4){
            mCurrentItem=1;
        }else{
            mCurrentItem=position;
        }

        Log.e("----","------------------>>>currentItem:"+mCurrentItem);
        Log.e("----","------------------>>>OnPageSelected");

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state==ViewPager.SCROLL_STATE_IDLE){
            if(mIsChanged){
                mIsChanged=false;
                verticalViewPager.setCurrentItem(mCurrentItem,false);
            }
        }

        if(state==ViewPager.SCROLL_STATE_DRAGGING){
            Log.e("-----ScrollState------","-----SCROLL_STATE_DRAGGING-----");
        }else if(state==ViewPager.SCROLL_STATE_SETTLING){
            Log.e("-----ScrollState------","-----SCROLL_STATE_SETTLING-----");
        }else if(state==ViewPager.SCROLL_STATE_IDLE){
            Log.e("-----ScrollState------","-----SCROLL_STATE_IDLE-----");
        }
    }

    @Override
    public void transformPage(View page, float position) {
        ViewGroup viewGroup = (ViewGroup) page;
        if ((position < 0 && viewGroup.getId() != mCurrentItem)) {
            View roomContainer = viewGroup.findViewById(R.id.room_container);
            if (roomContainer != null && roomContainer.getParent() != null && roomContainer.getParent() instanceof ViewGroup) {
                ((ViewGroup) (roomContainer.getParent())).removeView(roomContainer);
            }
        }
        // 满足此种条件，表明需要加载当前页面了
        if ((viewGroup.getId() == mCurrentItem && position == 0 && mCurrentItem != mRoomId) ||
                (viewGroup.getId() == mCurrentItem && Math.abs(position) == 2 && mCurrentItem != mRoomId)) {
            if (mRoomContainer.getParent() != null && mRoomContainer.getParent() instanceof ViewGroup) {
                ((ViewGroup) (mRoomContainer.getParent())).removeView(mRoomContainer);
            }
            loadItemRoom(viewGroup, mCurrentItem);
        }

    }

    private void loadItemRoom(ViewGroup viewGroup, int currentItem) {
        loadItem();
        viewGroup.addView(mRoomContainer);
        mRoomId = currentItem;
    }

    private void loadItem() {
        if (lastListPosition >= 0) {
            if (isdown == true) {
                //下滑操作页面+1
                if (lastListPosition == list.size() -1) {
                    lastListPosition = 0;
                } else {
                    lastListPosition ++;
                }
            } else {
                if (lastListPosition == 0) {
                    lastListPosition = list.size() -1;
                } else {
                    lastListPosition --;
                }
            }
            System.err.println("lastListPosition:" +lastListPosition);
            Log.d("MainActivity", "lastListPosition: "+lastListPosition);
        } else {
            lastListPosition =0;
        }

        mFragmentCardAdapter = new CardFragmentPagerAdapter(fragmentManager,
                dpToPixels(2, context), list.get(lastListPosition));
        mFragmentCardShadowTransformer = new ShadowTransformer(inkeViewPager, mFragmentCardAdapter);
        inkeViewPager.setAdapter(mFragmentCardAdapter);
        inkeViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);


    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean b) {
        mFragmentCardShadowTransformer.enableScaling(b);
    }

    public int getCurrentPage() {
        return mCurrentItem;
    }
}
