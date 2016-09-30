package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;

public class ShapeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public String returnToolBarTitle() {
        return "shape使用";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shape;
    }
}
