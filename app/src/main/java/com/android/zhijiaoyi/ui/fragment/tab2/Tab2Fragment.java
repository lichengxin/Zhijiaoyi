package com.android.zhijiaoyi.ui.fragment.tab2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.util.LogUtils;


public class Tab2Fragment extends Fragment {

    public Tab2Fragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.logE(Tab2Fragment.class,"onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.logE(Tab2Fragment.class,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.logE(Tab2Fragment.class,"onCreateView");
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.logE(Tab2Fragment.class,"onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.logE(Tab2Fragment.class,"onResume");
    }


    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.logE(Tab2Fragment.class,"onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.logE(Tab2Fragment.class,"onDestroy");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.logE(Tab2Fragment.class,"isVisibleToUser");
    }
}
