package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.util.IntentUtil;

public class ListViewDetail extends BaseActivity implements View.OnClickListener {

    private Button mBtnList1;
    private TextView mTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        String html = "<a href='http://blog.csdn.net/dai_zhenliang/article/details/42743797'>listView属性</a>" + "<font color='#666666'></font>";
        mTv1.setText(Html.fromHtml(html));
        mTv1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public String returnToolBarTitle() {
        return "ListView 相关内容";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_view_detail;
    }

    private void initView() {
        mBtnList1 = (Button) findViewById(R.id.btn_list1);

        mBtnList1.setOnClickListener(this);
        mTv1 = (TextView) findViewById(R.id.tv1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list1:
                IntentUtil.showIntent(ListViewDetail.this, ListViewDetailData1.class);
                break;
        }
    }
}
