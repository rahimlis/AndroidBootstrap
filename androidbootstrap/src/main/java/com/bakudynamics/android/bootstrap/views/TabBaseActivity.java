package com.bakudynamics.android.bootstrap.views;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.bakudynamics.android.bootstrap.R;
import com.bakudynamics.android.bootstrap.adapters.SectionsPagerAdapter;
import com.bakudynamics.android.bootstrap.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class TabBaseActivity extends BaseActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    protected SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    protected ViewPager mViewPager;
    protected final List<BaseFragment> fragments = new ArrayList<>();
    protected final List<String> titles = new ArrayList<>();
    protected TabLayout tabLayout;
    protected Toolbar toolbar;

    protected ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());

        setToolbar(findViewById(getToolbarResId()));

        setSupportActionBar(getToolbar());

        setSectionsPagerAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), getTabFragments(), getTabTitles()));

        setViewPager(findViewById(getViewPagerResId()));

        getViewPager().setOffscreenPageLimit(getOffScreenPageLimit());

        getViewPager().setAdapter(getSectionsPagerAdapter());

        setTabLayout(findViewById(getTabLayoutResId()));

        getViewPager().addOnPageChangeListener(getOnPageChangeListener());

        getTabLayout().setupWithViewPager(getViewPager());
    }

    protected View getTabViewAt(int index) {
        if (tabLayout == null || tabLayout.getChildAt(0) == null)
            return null;

        return ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(index);
    }


    protected abstract int getContentView();

    public void addFragment(@NonNull BaseFragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
        mSectionsPagerAdapter.notifyDataSetChanged();
    }


    public void addFragment(@NonNull Class<? extends BaseFragment> fragment, @NonNull String title, @LayoutRes int resId) {
        addFragment(FragmentFactory.newInstanceOf(fragment, resId), title);
    }

    public void addFragment(@NonNull Class<? extends BaseFragment> fragment, @StringRes int titleRes, @LayoutRes int resId) {
        addFragment(FragmentFactory.newInstanceOf(fragment, resId), getString(titleRes));
    }

    public SectionsPagerAdapter getSectionsPagerAdapter() {
        return mSectionsPagerAdapter;
    }

    public void setSectionsPagerAdapter(SectionsPagerAdapter mSectionsPagerAdapter) {
        this.mSectionsPagerAdapter = mSectionsPagerAdapter;
    }

    @NonNull
    public ViewPager getViewPager() {
        return mViewPager;
    }

    public void setViewPager(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
    }

    @NonNull
    public List<BaseFragment> getTabFragments() {
        return fragments;
    }

    @NonNull
    public List<String> getTabTitles() {
        return titles;
    }

    @NonNull
    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }

    @NonNull
    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    public int getOffScreenPageLimit() {
        return 3;
    }

    @IdRes
    public int getToolbarResId() {
        return R.id.toolbar;
    }

    @IdRes
    public int getTabLayoutResId() {
        return R.id.tabs;
    }

    @IdRes
    public int getViewPagerResId() {
        return R.id.container;
    }
}
