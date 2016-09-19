package com.android.zhijiaoyi.ui.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseFragment;
import com.android.zhijiaoyi.constans.Constant;
import com.android.zhijiaoyi.ui.activity.ImageActivity;
import com.android.zhijiaoyi.ui.activity.ItemActivity;
import com.android.zhijiaoyi.ui.activity.TabActivity;
import com.android.zhijiaoyi.ui.activity.TestTabActivity;
import com.android.zhijiaoyi.ui.activity.VolleyActivity;
import com.android.zhijiaoyi.util.CacheUtils;
import com.android.zhijiaoyi.util.CheckUtils;
import com.android.zhijiaoyi.util.IntentUtil;
import com.android.zhijiaoyi.util.LogUtils;
import com.android.zhijiaoyi.util.NetworkUtils;
import com.android.zhijiaoyi.util.SystemUtils;
import com.android.zhijiaoyi.util.UpdateManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

/**
 * A simple {@link Fragment} subclass.
 * 交易
 */
public class TradeFragment extends BaseFragment implements View.OnClickListener {


    private TextView mTitle;
    private Button mBtnVersion;
    private int versionCode;
    private String path;
    private StringBuilder hintMsg;
    public boolean UPDATE = true;
    private UpdateManager updateManager;
    private Button mBtnTab;
    private Button mBtnWchat;
    private Button mBtnTab2;
    private Button btn_Volley;
    private Button btn_image;
    private Button btn_image2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trade, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTitle = (TextView) view.findViewById(R.id.title);
        mBtnVersion = (Button) view.findViewById(R.id.btn_version);
        mBtnTab = (Button) view.findViewById(R.id.btn_tab);
        mBtnWchat = (Button) view.findViewById(R.id.btn_wchat);
        mBtnTab2 = (Button) view.findViewById(R.id.btn_tab2);
        btn_Volley = (Button) view.findViewById(R.id.btn_Volley);
        btn_image = (Button) view.findViewById(R.id.btn_image);
        btn_image2 = (Button) view.findViewById(R.id.btn_image2);
        mBtnVersion.setOnClickListener(this);
        mBtnTab.setOnClickListener(this);
        mBtnWchat.setOnClickListener(this);
        mBtnTab2.setOnClickListener(this);
        btn_Volley.setOnClickListener(this);
        btn_image.setOnClickListener(this);
        btn_image2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_version:
                getVersion();
                break;
            case R.id.btn_tab:
                IntentUtil.showIntent(getActivity(), TabActivity.class);
                break;
            case R.id.btn_wchat:
                IntentUtil.showIntent(getActivity(), ItemActivity.class);
                break;
            case R.id.btn_tab2:
                IntentUtil.showIntent(getActivity(), TestTabActivity.class);
                break;
            case R.id.btn_Volley:
                IntentUtil.showIntent(getActivity(), VolleyActivity.class);

                break;
            case R.id.btn_image:
                IntentUtil.showIntent(getActivity(), ImageActivity.class);
                break;
            case R.id.btn_image2:
                IntentUtil.showIntent(getActivity(), ImageActivity.class);
                break;
        }
    }

    public void getVersion() {

        HttpRequest.get(Constant.VERSION, new StringHttpRequestCallback() {
            @Override
            protected void onSuccess(String s) {
                super.onSuccess(s);
                LogUtils.logE(TradeFragment.class, "==" + s);
                try {
                    JSONObject obj = new JSONObject(s);

                    int ret = obj.getInt("Ret");
                    if (ret == 0) {
                        hintMsg = new StringBuilder();
                        obj = obj.getJSONObject("Data");
                        versionCode = obj.getInt("VersionCode");
                        path = obj.getString("LoadUrl");
                        int androidNum = obj.getInt("AndroidNum");
                        String title = obj.getString("ProductExplainTitle");
                        JSONArray array = obj.getJSONArray("ProductExplainContent");

                        for (int i = 0; i < array.length(); i++) {

                            hintMsg.append(array.getString(i)).append("\n");
                        }
                        if (1 == androidNum) {
                            //普通更新 1
                            checkTime(title, hintMsg, androidNum);
                        }
                        if (2 == androidNum) {
                            updateAPP(title, hintMsg, androidNum);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);

            }
        });
    }

    private void checkTime(String title, StringBuilder hintsMsg, int type) {
        //普通更新检查
        if (NetworkUtils.isNetworkAvailable(getActivity())) {
            String currentDate = CheckUtils.fotmatData(System.currentTimeMillis());
            String updateDate = CacheUtils.getString(getActivity(), "update_date", null);
            if (null != updateDate) {
                if (updateDate.equals(currentDate)) {
                    UPDATE = false;
                } else {
                    UPDATE = true;
                }
            } else {
                UPDATE = true;
            }
            if (UPDATE) {
                updateAPP(title, hintsMsg, type);
            }
            String spDate = CheckUtils.fotmatData(System.currentTimeMillis());
            CacheUtils.saveString(getActivity(), "update_date", spDate);
        }
    }

    private void updateAPP(String title, StringBuilder hintMsg, int type) {
        int localVersionCode = SystemUtils.getVersionCode(getActivity());
        //更新APP
        if (localVersionCode != versionCode) {
//            showDialog(title, hintMsg.toString(), type);
            updateManager = new UpdateManager(getActivity(), path);
            updateManager.ApkUpdateInfo(title, hintMsg, type);
        }
    }

    private void showDialog(String title, String message, int type) {
        /*
            这里使用了 android.support.v7.app.AlertDialog.Builder
            可以直接在头部写 import android.support.v7.app.AlertDialog
            那么下面就可以写成 AlertDialog.Builder
        */
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        if (type == 1) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //TODO 访问下载apk 弹出进度条
                }
            });
        }
        if (type == 2) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //TODO 访问下载apk 弹出进度条
                }
            });
        }

        builder.show();
    }


}
