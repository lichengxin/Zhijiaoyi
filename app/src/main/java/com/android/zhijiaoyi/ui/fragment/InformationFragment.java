package com.android.zhijiaoyi.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * 资讯
 */
public class InformationFragment extends BaseFragment implements View.OnClickListener {


    private Button mBtn1;
    private String jsonData="{\"status\":1,\"message\":\"success\",\"result\":{\"tokenId\":\"e0a37f8f1e042de88c4498c3565f63e5\",\"userId\":1084,\"username\":\"songbingli\"}}";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        mBtn1 = (Button) view.findViewById(R.id.btn1);

        mBtn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:

                break;
        }
    }
}
