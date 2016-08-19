package com.android.zhijiaoyi.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * 作者： Li
 * 时间： 2016/8/19 11:21
 * QQ 1205303495
 * Expalin
 */
public class BaseFragment extends Fragment {

    /*Fragment当前状态是否可见*/
    protected boolean isVisiable;
    protected boolean isCreated;

    protected BaseActivity mBaseActivity;


    @Override
    public void onAttach(Activity activity) {
        this.mBaseActivity = (BaseActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCreated = false;
        if (getUserVisibleHint()) {
            isVisiable = true;
            onVisiable();
        } else {
            isVisiable=false;
            InVisiable();
        }
    }

    /*Fragment可见是调用该方法*/
    protected void onVisiable() {

    }

    /*Fragment 不可见时调用该方法*/
    protected void InVisiable() {

    }
}
