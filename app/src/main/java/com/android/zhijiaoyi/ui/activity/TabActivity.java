package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.ui.fragment.tab2.SimpleCardFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class TabActivity extends BaseActivity {

    private FrameLayout mBack;
    private SegmentTabLayout mTab1;
    private ViewPager mVp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"首页", "消息"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
        }
        mVp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mTab1.setTabData(mTitles);
        mTab1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTab1.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public String returnToolBarTitle() {
        return "Tab标签";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab;
    }

    private void initView() {
        mBack = (FrameLayout) findViewById(R.id.back);
        mTab1 = (SegmentTabLayout) findViewById(R.id.tab1);
        mVp = (ViewPager) findViewById(R.id.vp);


    }

    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
