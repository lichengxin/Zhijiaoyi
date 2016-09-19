package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.util.HttpUtil;
import com.android.zhijiaoyi.util.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class VolleyActivity extends AppCompatActivity {

    private FrameLayout back;
    private TextView title;
    private TextView tv_disPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        initView();
    }


    private void initView() {
        back = (FrameLayout) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        tv_disPlay = (TextView) findViewById(R.id.tv_disPlay);
        String url=" http://proxyapi.test.langbank.org/v1/PeiZi/User/login/?ua=LOCAL_DEV_CLIENT&sign=e9aef5d16ad39167c5b67737a37647ba";
        Map<String,String> params = new HashMap<>();
        params.put("username","songbingli");
        params.put("password","000000");
        HttpUtil.getInstance(this).sendPostRequest(url, params, new HttpUtil.HttpListener() {
            @Override
            public void onResponse(String response) {
                LogUtils.logE(VolleyActivity.class,"success:"+response);

            }

            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.logE(VolleyActivity.class,"error:"+error);
            }
        });
    }
}
