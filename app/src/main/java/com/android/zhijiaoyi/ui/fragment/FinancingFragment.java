package com.android.zhijiaoyi.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseFragment;
import com.android.zhijiaoyi.ui.fragment.tab.FragmentTab1;
import com.android.zhijiaoyi.ui.fragment.tab.FragmentTab2;
import com.android.zhijiaoyi.ui.fragment.tab.FragmentTab3;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * 理财
 */
public class FinancingFragment extends BaseFragment {


    private SegmentTabLayout mSeTab;
    private ViewPager mViewPager;
    private String[] mTitles_2 = {"自选股", "热门股", "历史股"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public FinancingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_financing, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSeTab = ((SegmentTabLayout) view.findViewById(R.id.seTab));
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mFragments.clear();
        mFragments.add(new FragmentTab1());
        mFragments.add(new FragmentTab2());
        mFragments.add(new FragmentTab3());
        mViewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        mSeTab.setTabData(mTitles_2);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                mSeTab.setCurrentTab(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mSeTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mTitles_2.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles_2[position];
        }
    }

}
