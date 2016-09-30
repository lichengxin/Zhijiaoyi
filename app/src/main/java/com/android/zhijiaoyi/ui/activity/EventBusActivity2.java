package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.bean.User;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class EventBusActivity2 extends BaseActivity {

    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public String returnToolBarTitle() {
        return "EventBus2";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_bus2;
    }

    private List<User> lists = new ArrayList<>();
    private void initView() {
        mLv = (ListView) findViewById(R.id.lv);

            lists.add(new User("小明======"));
            lists.add(new User("cc======"));
            lists.add(new User("ddd======"));
            lists.add(new User("gggg======"));
            lists.add(new User("小dfd明======"));
            lists.add(new User("小明dfd======"));
            lists.add(new User("小dfdfd明======"));

        mLv.setAdapter(new CommonAdapter<User>(this, R.layout.item1, lists) {

            @Override
            protected void convert(ViewHolder viewHolder, User item, int position) {
                viewHolder.setText(R.id.tv_item1, item.getName());
            }
        });
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(EventBusActivity2.this, "pos" + position, Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(lists.get(position));
            }
        });
    }
}
