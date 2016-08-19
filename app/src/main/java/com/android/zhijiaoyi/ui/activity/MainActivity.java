package com.android.zhijiaoyi.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.ui.fragment.Indicator;
import com.android.zhijiaoyi.util.DoubleClickExitHelper;

public class MainActivity extends BaseActivity {

    private FragmentTabHost mTab;
    private FrameLayout mContaier;
    private DoubleClickExitHelper mDoubleClickExitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showOrHideToolbaeNavigation(false);

    }

    @Override
    public String returnToolBarTitle() {
        return "测试";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        Indicator[] indicators = Indicator.values();
//        mTab = (FragmentTabHost) findViewById(R.id.ft_tab);
        mContaier = (FrameLayout) findViewById(R.id.contaier);
        mDoubleClickExitHelper = new DoubleClickExitHelper(this);

//        mTab.setup(getApplicationContext(), getSupportFragmentManager(), R.id.contaier);

//        /*初始化Tab*/
//        for (int i = 0; i < indicators.length; i++) {
//            TabHost.TabSpec tabSpec = mTab.newTabSpec(getString(indicators[i].getId()));
//            tabSpec.setIndicator(getIndicatorView(indicators[i]));
//            mTab.addTab(tabSpec,indicators[i].getClc(),null);
//        }
//
//        /*去除底部按钮间的分割线*/
//        if (Build.VERSION.SDK_INT > 10) {
//            mTab.getTabWidget().setShowDividers(0);
//            mTab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//                @Override
//                public void onTabChanged(String tabId) {
//
//                }
//            });
//        }

    }

    private View getIndicatorView(Indicator indicator) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_indicator, null, false);
        ImageView img = (ImageView) view.findViewById(R.id.tab_item);
        Drawable icon = this.getResources().getDrawable(indicator.getTab());
      /*    自定义ICON大小
       icon.setBounds(0, 0, 75, 75);
      indicator.setCompoundDrawables(null,icon,null,null);*/
       return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return  mDoubleClickExitHelper.onKeyDown(keyCode,event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
