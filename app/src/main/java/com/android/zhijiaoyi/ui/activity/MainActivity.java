package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.ui.fragment.DiscoverFragment;
import com.android.zhijiaoyi.ui.fragment.FinancingFragment;
import com.android.zhijiaoyi.ui.fragment.InformationFragment;
import com.android.zhijiaoyi.ui.fragment.MarketFragment;
import com.android.zhijiaoyi.ui.fragment.TradeFragment;
import com.android.zhijiaoyi.util.DoubleClickExitHelper;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private FragmentTabHost mTab;
    private FrameLayout mContaier;
    private DoubleClickExitHelper mDoubleClickExitHelper;
    private Button btn1;
    private Button btn2;
    private Fragment[] fragments;
    private RadioGroup mMainFragmentTab;
    private FragmentManager mManager;
    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showOrHideToolbaeNavigation(false);
        init();
        initView();
    }

    private void initView() {
        Fragment f1 = new FinancingFragment();
        Fragment f2 = new MarketFragment();
        Fragment f3 = new TradeFragment();
        Fragment f4 = new InformationFragment();
        Fragment f5 = new DiscoverFragment();
        fragments = new Fragment[]{f1, f2, f3, f4, f5};
        mManager = getSupportFragmentManager();
        mManager.beginTransaction().add(R.id.main_content, f1)
                .add(R.id.main_content, f2)
                .add(R.id.main_content, f3)
                .add(R.id.main_content, f4)
                .add(R.id.main_content, f5)
                .hide(f2)
                .hide(f3)
                .hide(f4)
                .hide(f5)
                .show(f1).commit();

        mMainFragmentTab = (RadioGroup) findViewById(R.id.main_fragment_tab);
        mMainFragmentTab.setOnCheckedChangeListener(this);
    }

    private void init() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        String url = "http://www.renqidai.cc:8088/Product/CreditorInfoListNew";
       /* HttpRequest.get(url, new BaseHttpRequestCallback<String>() {
            @Override
            protected void onSuccess(String s) {
                super.onSuccess(s);

                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });*/

        HttpRequest.get(url, new StringHttpRequestCallback() {
            @Override
            protected void onSuccess(String s) {
                super.onSuccess(s);
                Log.e("ww", s + "==");
            }
        });
    }

    @Override
    public String returnToolBarTitle() {
        return "测试";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


   /* @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                new AlertView(null, null, "取消", null,
                        new String[]{"拍照", "从相册中选择"},
                        this, AlertView.Style.ActionSheet, new OnItemClickListener() {
                    public void onItemClick(Object o, int position) {
                        Toast.makeText(getApplicationContext(), "点击了第" + position + "个",
                                Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.btn2:
                new AlertView("标题", "内容", null, new String[]{"确定"}, null, this,
                        AlertView.Style.Alert, null).show();
                break;
        }
    }*/

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_tab_1:
                index=0;
                break;
            case R.id.main_tab_2:
                index=1;
                break;
            case R.id.main_tab_3:
                index=2;
                break;
            case R.id.main_tab_4:
                index=3;
                break;
            case R.id.main_tab_5:
                index=4;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction transaction = mManager.beginTransaction();
            transaction.hide(fragments[currentTabIndex]);
            transaction.show(fragments[index]).commit();
        }
        currentTabIndex=index;
    }


}
