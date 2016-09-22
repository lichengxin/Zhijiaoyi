package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;

public class KeyBordListenerActivity extends BaseActivity {

    private TextView mTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {
        String html = "<a href='http://blog.sina.com.cn/s/blog_9564cb6e0101g2eb.html'>1、键盘弹出设置</a>" + "<font color='#666666'></font>";
        mTv1.setText(Html.fromHtml(html));
        mTv1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public String returnToolBarTitle() {
        return "键盘";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_key_bord_listener;
    }

    private void initView() {
        mTv1 = (TextView) findViewById(R.id.tv1);
    }
}
