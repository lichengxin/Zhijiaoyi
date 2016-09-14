package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;

public class ItemActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String returnToolBarTitle() {
        return "微信item";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_item;
    }
}
