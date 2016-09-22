package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.util.IntentUtil;

public class DialogActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout mBack;
    private Button mBtnAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    @Override
    public String returnToolBarTitle() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
    }

    private void initView() {
        mBack = (FrameLayout) findViewById(R.id.back);
        mBtnAlert = (Button) findViewById(R.id.btn_alert);
        mBtnAlert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alert:
                IntentUtil.showIntent(DialogActivity.this, DialogAlertActivity.class);
                break;
        }
    }

    private void showDg() {

    }
}
