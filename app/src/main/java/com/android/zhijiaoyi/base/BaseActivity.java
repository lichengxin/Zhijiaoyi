package com.android.zhijiaoyi.base;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.system.AppManager;
import com.android.zhijiaoyi.base.system.StatusBarCompat;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者： Li
 * 时间： 2016/8/19 11:20
 * QQ 1205303495
 * Expalin
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeSetContentLayout();
        /*设置布局*/
        setContentView(getLayoutId());
        /*禁止横屏*/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppManager.getAppManager().addActivity(this);
        setStatusBarCompat();
        initToolBar();


    }

    /*隐藏ToolBar*/
    public void hideToolBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    /*显示ToolBar*/
    public void showToolBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }

    /*初始化ToolBar*/
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        if (toolbar != null) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            setSupportActionBar(toolbar);
        }
    }

    /*是否隐藏ToolBar返回键*/
    public void showOrHideToolbaeNavigation(boolean show) {
        FrameLayout back = (FrameLayout) findViewById(R.id.back);

        if (show) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            back.setVisibility(View.GONE);
        }
    }

    /**
     * 设置兼容4.4版本 状态栏颜色改变成和5.0效果差不多
     * 1、需要在values-19加上支持SystemWindows
     * 2、ToolBar添加属性 android:fitsSystemWindows="true"
     * 3、然后在加载完activity布局后调用该方法
     */
    public void setStatusBarCompat() {
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));
    }

    /*设置状态栏颜色*/
    public void setStatusBarCompat(int colorId) {
        StatusBarCompat.compat(this, colorId);
    }

    /*设置标题栏*/
    public void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(returnToolBarTitle());
    }

    public abstract String returnToolBarTitle();

    /*设置布局前操作*/
    protected void onBeforeSetContentLayout() {

    }

   public abstract int getLayoutId();


    @Override
    protected void onResume() {
        super.onResume();
        setTitle();
    }

    public void showDialogs(int layId,String neg,String pos) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(layId, null);
        builder.setView(view);
        builder.setCancelable(false);
        if (neg != null) {
            builder.setPositiveButton(neg, new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
        if (pos != null) {
            builder.setPositiveButton(neg, new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }


        builder.show();
    }
    interface onNegListener{}
    interface onPosListener{}
}
