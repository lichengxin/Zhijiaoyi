package com.android.zhijiaoyi.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * 理财
 */
public class FinancingFragment extends BaseFragment {


    public FinancingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_financing, container, false);
    }

}
