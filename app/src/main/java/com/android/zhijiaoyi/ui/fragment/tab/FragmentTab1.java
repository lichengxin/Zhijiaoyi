package com.android.zhijiaoyi.ui.fragment.tab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTab1 extends BaseFragment {


    public FragmentTab1() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_tab1, container, false);
        return view;
    }

}
