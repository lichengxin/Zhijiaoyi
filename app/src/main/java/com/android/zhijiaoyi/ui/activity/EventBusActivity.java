package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.bean.User;
import com.android.zhijiaoyi.util.IntentUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnClick;
    private TextView mShowText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        EventBus.getDefault().register(this);

    }
    /**
     * 1、onEvent
     2、onEventMainThread
     3、onEventBackgroundThread
     4、onEventAsync
     */

    /**用法
     * onEvent:如果使用onEvent作为订阅函数，那么该事件在哪个线程发布出来的，onEvent就会在这个线程中运行，也就是说发布事件和接收事件线程在同一个线程。使用这个方法时，在onEvent方法中不能执行耗时操作，如果执行耗时操作容易导致事件分发延迟。
     onEventMainThread:如果使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行，这个在Android中是非常有用的，因为在Android中只能在UI线程中跟新UI，所以在onEvnetMainThread方法中是不能执行耗时操作的。
     onEventBackground:如果使用onEventBackgrond作为订阅函数，那么如果事件是在UI线程中发布出来的，那么onEventBackground就会在子线程中运行，如果事件本来就是子线程中发布出来的，那么onEventBackground函数直接在该子线程中执行。
     onEventAsync：使用这个函数作为订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程在执行onEventAsync.
     */

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(User user) {
        mShowText.setText(user.getName());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public String returnToolBarTitle() {
        return "EventBus 详解";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_bus;
    }

    private void initView() {
        mBtnClick = (Button) findViewById(R.id.btn_click);
        mShowText = (TextView) findViewById(R.id.showText);

        mBtnClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
                IntentUtil.showIntent(EventBusActivity.this, EventBusActivity2.class);
                break;
        }
    }
}
