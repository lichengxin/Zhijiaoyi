package com.android.zhijiaoyi.ui.fragment;


import android.content.Intent;
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
import com.android.zhijiaoyi.constans.Cons;
import com.android.zhijiaoyi.constans.Constant;
import com.android.zhijiaoyi.ui.activity.CanvasGuideActivity;
import com.android.zhijiaoyi.ui.activity.CreateGestureActivity;
import com.android.zhijiaoyi.ui.activity.DialogActivity;
import com.android.zhijiaoyi.ui.activity.GestureLoginActivity;
import com.android.zhijiaoyi.ui.activity.JsonActivity;
import com.android.zhijiaoyi.ui.activity.KeyBordListenerActivity;
import com.android.zhijiaoyi.ui.activity.ListViewDataActivity;
import com.android.zhijiaoyi.ui.activity.ListViewDataActivity2;
import com.android.zhijiaoyi.ui.activity.ListViewDetail;
import com.android.zhijiaoyi.ui.activity.ShareActivity;
import com.android.zhijiaoyi.util.IntentUtil;
import com.android.zhijiaoyi.util.LogUtils;
import com.android.zhijiaoyi.util.StrUtils;
import com.android.zhijiaoyi.util.cache.ACache;

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
    private Button btn_guesture;

    private ACache aCache;
    private Button mBtnJson;
    private Button mBtnCanvas;
    private Button mBtnShare;
    private Button mBtnList;
    private Button mBtnColect;
    private Button mBtnListView;
    private Button mBtnKeybord;
    private Button mBtnDialog;

    public DiscoverFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        aCache = ACache.get(getActivity());
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEtUserName = (EditText) view.findViewById(R.id.et_userName);
        mEtPassword = (EditText) view.findViewById(R.id.et_password);
        mBtnLogin = (Button) view.findViewById(R.id.btn_login);
        btn_guesture = (Button) view.findViewById(R.id.btn_guesture);
        mBtnCanvas = (Button) view.findViewById(R.id.btn_canvas);

        mBtnLogin.setOnClickListener(this);
        btn_guesture.setOnClickListener(this);
        mBtnJson = (Button) view.findViewById(R.id.btn_json);
        mBtnJson.setOnClickListener(this);
        mBtnCanvas.setOnClickListener(this);
        mBtnShare = (Button) view.findViewById(R.id.btn_share);
        mBtnShare.setOnClickListener(this);
        mBtnList = (Button) view.findViewById(R.id.btn_list);
        mBtnList.setOnClickListener(this);
        mBtnColect = (Button) view.findViewById(R.id.btn_colect);
        mBtnColect.setOnClickListener(this);
        mBtnListView = (Button) view.findViewById(R.id.btn_listView);
        mBtnListView.setOnClickListener(this);
        mBtnKeybord = (Button) view.findViewById(R.id.btn_keybord);
        mBtnKeybord.setOnClickListener(this);
        mBtnDialog = (Button) view.findViewById(R.id.btn_dialog);
        mBtnDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
                break;
            case R.id.btn_guesture:
                String gesturePassword = aCache.getAsString(Cons.GESTURE_PASSWORD);
                if (gesturePassword == null || "".equals(gesturePassword)) {
                    LogUtils.logE(DiscoverFragment.class, "去创建手势密码");
                    Intent intent = new Intent(getActivity(), CreateGestureActivity.class);
                    startActivity(intent);
                } else {
                    LogUtils.logE(DiscoverFragment.class, "去解锁");
                    Intent intent = new Intent(getActivity(), GestureLoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btn_json:
                IntentUtil.showIntent(getActivity(), JsonActivity.class);
                break;
            case R.id.btn_canvas:
                IntentUtil.showIntent(getActivity(), CanvasGuideActivity.class);
                break;
            case R.id.btn_share:
                IntentUtil.showIntent(getActivity(), ShareActivity.class);
                break;
            case R.id.btn_list:
                IntentUtil.showIntent(getActivity(), ListViewDataActivity.class);
                break;
            case R.id.btn_colect:
                IntentUtil.showIntent(getActivity(), ListViewDataActivity2.class);
                break;
            case R.id.btn_listView:
                IntentUtil.showIntent(getActivity(), ListViewDetail.class);
                break;
            case R.id.btn_keybord:
                IntentUtil.showIntent(getActivity(), KeyBordListenerActivity.class);
                break;
            case R.id.btn_dialog:
                IntentUtil.showIntent(getActivity(), DialogActivity.class);
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
        doLogin(userName, password);


    }

    public void doLogin(String username, String password) {


        String ua = "LOCAL_DEV_CLIENT";
        String signkey = "LOCAL_DEV_REQUEST_SIGN_KEY";
        String signdata = signkey + "password=" + password + "&username=" + username + signkey;
        Log.e("ww", signdata);

        String sign = StrUtils.MD5(signdata);
        String url = Constant.USERLOGIN + "sign=" + sign + "&ua=" + ua;
        RequestParams params = new RequestParams();//请求参数
        params.addFormDataPart("username", username);
        params.addFormDataPart("password", password);
        HttpRequest.post(url, params, new StringHttpRequestCallback() {
            @Override
            protected void onSuccess(String s) {
                super.onSuccess(s);

                Log.e("w", "==" + s);
            }
        });
    }
}
