package com.android.zhijiaoyi.ui.fragment.tab2;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.util.LogUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.logE(Tab1Fragment.class,"onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.logE(Tab1Fragment.class,"onCreate");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.logE(Tab1Fragment.class,"isVisibleToUser");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.logE(Tab1Fragment.class,"onCreateView");
        return inflater.inflate(R.layout.fragment_tab1_frgment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.logE(Tab1Fragment.class,"onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.logE(Tab1Fragment.class,"onResume");
    }


    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.logE(Tab1Fragment.class,"onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.logE(Tab1Fragment.class,"onDestroy");
    }
}
