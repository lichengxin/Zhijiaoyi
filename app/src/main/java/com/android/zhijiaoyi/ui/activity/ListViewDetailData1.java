package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;

public class ListViewDetailData1 extends BaseActivity {

    private FrameLayout mBack;
    private TextView mTitle;
    private TextView mTvData1;
    private TextView mTvData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mTvData1.setText("http://blog.csdn.net/dai_zhenliang/article/details/42743797");
        mTvData2.setText(R.string.listviewdetail);

    }

    @Override
    public String returnToolBarTitle() {
        return "ListView的属性及方法详解";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_view_detail_data1;
    }

    private void initView() {
        mBack = (FrameLayout) findViewById(R.id.back);
        mTitle = (TextView) findViewById(R.id.title);
        mTvData1 = (TextView) findViewById(R.id.tv_data1);
        mTvData2 = (TextView) findViewById(R.id.tv_data2);
    }
}
