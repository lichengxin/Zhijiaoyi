package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.constans.Defaultcontent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

public class ShareActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout mBack;
    private TextView mTitle;
    private Button mBtnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public String returnToolBarTitle() {
        return "分享";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_share;
    }

    private void initView() {
        mBack = (FrameLayout) findViewById(R.id.back);
        mTitle = (TextView) findViewById(R.id.title);
        mBtnDialog = (Button) findViewById(R.id.btn_dialog);

        mBtnDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                new ShareAction(ShareActivity.this).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withTitle(Defaultcontent.title)
                        .withText(Defaultcontent.text+"——来自友盟分享面板")
                        .withMedia(new UMImage(ShareActivity.this,"http://dev.umeng.com/images/tab2_1.png"))
                        .withTargetUrl("https://wsq.umeng.com/")
                        .setCallback(umShareListener)
                        .open();
                break;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(ShareActivity.this,platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ShareActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

}
