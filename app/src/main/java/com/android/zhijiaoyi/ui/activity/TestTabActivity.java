package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.ui.fragment.tab2.Tab1Fragment;
import com.android.zhijiaoyi.ui.fragment.tab2.Tab2Fragment;
import com.flyco.tablayout.SegmentTabLayout;

import java.util.ArrayList;

public class TestTabActivity extends AppCompatActivity {

    private SegmentTabLayout tl_4;
    private FrameLayout fl_change;
    private String[] mTitles_2 = {"首页", "消息"};
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tab);
        mFragments2.add(new Tab1Fragment());
        mFragments2.add(new Tab2Fragment());
        initView();
    }

    private void initView() {
        tl_4 = (SegmentTabLayout) findViewById(R.id.tl_4);
        fl_change = (FrameLayout) findViewById(R.id.fl_change);
        tl_4.setTabData(mTitles_2,TestTabActivity.this,R.id.fl_change,mFragments2);

    }
}
