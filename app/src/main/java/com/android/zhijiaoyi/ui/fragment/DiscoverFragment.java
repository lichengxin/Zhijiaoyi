package com.android.zhijiaoyi.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseFragment;
import com.android.zhijiaoyi.constans.Constant;
import com.android.zhijiaoyi.util.StrUtils;

import java.util.HashMap;
import java.util.Map;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

/**
 * A simple {@link Fragment} subclass.
 * 发现
 */
public class DiscoverFragment extends BaseFragment implements View.OnClickListener {


    private EditText mEtUserName;
    private EditText mEtPassword;
    private Button mBtnLogin;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEtUserName = (EditText) view.findViewById(R.id.et_userName);
        mEtPassword = (EditText) view.findViewById(R.id.et_password);
        mBtnLogin = (Button) view.findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
                break;
        }
    }

    private void submit() {

        String userName = mEtUserName.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {

            return;
        }

        if (TextUtils.isEmpty(password)) {

            return;
        }
        doLogin(userName,password);




    }

    public void doLogin(String username, String password) {


        String ua = "LOCAL_DEV_CLIENT";
        String signkey = "LOCAL_DEV_REQUEST_SIGN_KEY";
        String signdata = signkey + "password="+password + "&username=" + username + signkey;
        Log.e("ww",signdata);

        String sign = StrUtils.MD5(signdata);
        String url = Constant.USERLOGIN + "sign=" + sign+"&ua="+ua;
        RequestParams params = new RequestParams();//请求参数
        params.addFormDataPart("username", username);
        params.addFormDataPart("password", password);
        HttpRequest.post(url,params,new StringHttpRequestCallback(){
            @Override
            protected void onSuccess(String s) {
                super.onSuccess(s);

                Log.e("w","=="+s);
            }
        });
    }
}
