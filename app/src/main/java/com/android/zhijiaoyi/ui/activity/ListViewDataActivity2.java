package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.adapter.Adapter;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.bean.DianZan;

import java.util.ArrayList;

public class ListViewDataActivity2 extends BaseActivity {

    private FrameLayout mBack;
    private TextView mTitle;
    private ListView listView;
    private ArrayList<DianZan> list;

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
        return R.layout.activity_list_view_data2;
    }

    private void initView() {
        mBack = (FrameLayout) findViewById(R.id.back);
        mTitle = (TextView) findViewById(R.id.title);
        listView = (ListView) findViewById(R.id.lv_list2);


        list = new ArrayList<DianZan>();
        for (int i = 0; i < 100; i++) {
            list.add(new DianZan());
        }
        Adapter adapter = new Adapter(ListViewDataActivity2.this, list);
        listView.setAdapter(adapter);
    }
}
