package com.allen.alpagerview;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        List<List<CardFragment>> list1 = new ArrayList<>();

        List<CardFragment> list = new ArrayList<>();
        list.add(new CardFragment());

        List<CardFragment> list2 = new ArrayList<>();
        list2.add(new CardFragment());
        list2.add(new CardFragment());

        List<CardFragment> list3 = new ArrayList<>();
        list3.add(new CardFragment());
        list3.add(new CardFragment());
        list3.add(new CardFragment());

        List<CardFragment> list4 = new ArrayList<>();
        list4.add(new CardFragment());
        list4.add(new CardFragment());
        list4.add(new CardFragment());
        list4.add(new CardFragment());

        List<CardFragment> list5 = new ArrayList<>();
        list5.add(new CardFragment());
        list5.add(new CardFragment());
        list5.add(new CardFragment());
        list5.add(new CardFragment());
        list5.add(new CardFragment());

        list1.add(list);
        list1.add(list2);
        list1.add(list3);
        list1.add(list4);
        list1.add(list5);

        //test
        AlverticalViewPager  alverticalViewPager = (AlverticalViewPager) findViewById(R.id.al);
        AlViewPager alViewPager = new AlViewPager(this, getSupportFragmentManager(), list1, alverticalViewPager);
        alverticalViewPager.setOnPageChangeListener(alViewPager);
        alverticalViewPager.setPageTransformer(false,alViewPager);
        alverticalViewPager.setAdapter(new PagerAdapter(this, "view_room_item"));
        alverticalViewPager.setCurrentItem(1,false);


    }
}
