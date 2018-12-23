package com.bakudynamics.android.bootstrap.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bakudynamics.android.bootstrap.views.BaseFragment;

import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private List<String> titles;

    public SectionsPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments == null)
            return null;
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        if (fragments != null)
            return fragments.size();
        else return 0;
    }


    @Override
    public CharSequence getPageTitle(int position) {

        if (titles != null && position < titles.size())
            return titles.get(position);

        return null;
    }
}