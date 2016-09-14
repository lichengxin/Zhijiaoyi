package com.android.zhijiaoyi.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseFragment;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 * 行情
 */
public class MarketFragment extends BaseFragment implements View.OnClickListener {


    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private AlertView alertView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBtn1 = (Button) view.findViewById(R.id.btn1);
        mBtn2 = (Button) view.findViewById(R.id.btn2);
        mBtn3 = (Button) view.findViewById(R.id.btn3);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);

       /* alertView = new AlertView("提示",null, "取消", null, null, getActivity(), AlertView.Style.Alert, null);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alertext_form, null);
        TextView tv1 = (TextView) extView.findViewById(R.id.tv1);
        TextView tv2 = (TextView) extView.findViewById(R.id.tv2);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        alertView.addExtView(extView);*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                new AlertView.Builder().setContext(getActivity())
                        .setStyle(AlertView.Style.ActionSheet)
                        .setTitle("选择操作")
                        .setMessage(null)
                        .setCancelText("取消")
                        .setDestructive("拍照", "从相册中选择")
                        .setOthers(null)
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(Object o, int position) {

                            }
                        })
                        .build()
                        .show();
                break;
            case R.id.btn2:
                new AlertView("智交易", null, "取消", null,
                        new String[]{"删除", "置顶", "买入", "卖出"},
                        getActivity(), AlertView.Style.Alert, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        Log.e("ll", "pos:" + position);
                    }
                }).show();
                break;
            case R.id.btn3:
                alertView.show();
                break;
          /*  case R.id.tv1:
                Toast.makeText(getActivity(),"tv:1",Toast.LENGTH_LONG).show();
                break;
            case R.id.tv2:
                Toast.makeText(getActivity(),"tv:2",Toast.LENGTH_LONG).show();
                break;*/
        }
    }
}
