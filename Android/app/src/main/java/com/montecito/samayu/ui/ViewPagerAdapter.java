package com.montecito.samayu.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by fgs on 1/18/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String title[]={"Items","Category","Floor"};
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return ChartFragment.getInstance(position);
    }
    // Returns total number of pages
    @Override
    public int getCount() {
        return title.length;
    }
    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position){
        return title[position];
    }
}
