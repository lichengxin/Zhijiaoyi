package com.android.zhijiaoyi.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.adapter.SearchAdapter;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.bean.StockItem2;
import com.android.zhijiaoyi.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class ListViewDataActivity extends BaseActivity {

    private List<StockItem2> lists = new ArrayList<>();
    private ListView mLvList;
    private SearchAdapter adapter;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 1000; i++) {
            lists.add(new StockItem2("600001", "浦发银行"));
        }
        initView();
    }

    @Override
    public String returnToolBarTitle() {
        return "listView数据item显示异常";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_view_data;
    }

    private void initView() {
        mLvList = (ListView) findViewById(R.id.lv_list);
        adapter = new SearchAdapter(lists, ListViewDataActivity.this);
        mLvList.setAdapter(adapter);

        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogs("浦发银行");
            }
        });

    }

    private void showDialogs(String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_item, null);
        TextView tv0 = (TextView) view.findViewById(R.id.tv0);
        Button tv1 = (Button) view.findViewById(R.id.tv1);
        Button tv2 = (Button) view.findViewById(R.id.tv2);
        Button tv3 = (Button) view.findViewById(R.id.tv3);
        tv0.setText(title);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("点击了第一个", getApplicationContext());
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("点击了第2个", getApplicationContext());
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("点击了第3个", getApplicationContext());
            }
        });
        builder.setView(view);
        builder.setCancelable(false);
        builder.setPositiveButton("取消", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.show();

    }


}
