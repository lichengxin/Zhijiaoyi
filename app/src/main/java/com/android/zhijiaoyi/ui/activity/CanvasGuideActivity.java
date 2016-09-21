package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.util.LogUtils;
import com.android.zhijiaoyi.util.SharedPreferencesUtil;
import com.android.zhijiaoyi.view.GuideView;

public class CanvasGuideActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton mIbMenu;
    private Button mBtnTest;
    private Button mBtnTest2;
    private GuideView guideView;
    private GuideView guideView2;
    private GuideView guideView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        SharedPreferencesUtil.saveData(this, "name", "zhangsan");
        String name = (String) SharedPreferencesUtil.getData(this, "name", "");
        LogUtils.logE(CanvasGuideActivity.class, "name:::" + name);
        mIbMenu = (ImageButton) findViewById(R.id.ib_menu);
        mBtnTest = (Button) findViewById(R.id.btn_test);
        mBtnTest2 = (Button) findViewById(R.id.btn_test2);

        mBtnTest.setOnClickListener(this);
        mIbMenu.setOnClickListener(this);
        mBtnTest2.setOnClickListener(this);
    }

    @Override
    public String returnToolBarTitle() {
        return "图层引导";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_canvas_guide;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_menu:

                break;
            case R.id.btn_test:

                break;
            case R.id.btn_test2:

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Boolean isFirst = (Boolean) SharedPreferencesUtil.getData(CanvasGuideActivity.this, "isFirst", true);
        LogUtils.logE(CanvasGuideActivity.class, "isFirst:" + isFirst);
        if (isFirst) {
            setGuideView();
        }

    }

    private void setGuideView() {
        // 使用图片
        final ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.img_new_task_guide);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(params);

        // 使用文字
        TextView tv = new TextView(this);
        tv.setText("欢迎使用");
        tv.setTextColor(getResources().getColor(R.color.white));
        tv.setTextSize(30);
        tv.setGravity(Gravity.CENTER);

        // 使用文字
        final TextView tv2 = new TextView(this);
        tv2.setText("欢迎使用2");
        tv2.setTextColor(getResources().getColor(R.color.white));
        tv2.setTextSize(30);
        tv2.setGravity(Gravity.CENTER);

        guideView = GuideView.Builder
                .newInstance(this)
                .showOnce()
                .setTargetView(mIbMenu)//设置目标
                .setCustomGuideView(iv)
                .setDirction(GuideView.Direction.LEFT_BOTTOM)
                .setShape(GuideView.MyShape.CIRCULAR)   // 设置圆形显示区域，
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView.hide();
                        guideView2.show();
                    }
                })
                .build();


        guideView2 = GuideView.Builder
                .newInstance(this)
                .showOnce()
                .setTargetView(mBtnTest)
                .setCustomGuideView(tv)
                .setDirction(GuideView.Direction.LEFT_BOTTOM)
                .setShape(GuideView.MyShape.ELLIPSE)   // 设置椭圆形显示区域，
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView2.hide();
                        guideView3.show();

                    }
                })
                .build();


        guideView3 = GuideView.Builder
                .newInstance(this)
                .showOnce()
                .setTargetView(mBtnTest2)
                .setCustomGuideView(tv2)
                .setDirction(GuideView.Direction.LEFT_BOTTOM)
                .setShape(GuideView.MyShape.RECTANGULAR)   // 设置矩形显示区域，
                .setRadius(80)          // 设置圆形或矩形透明区域半径，默认是targetView的显示矩形的半径，如果是矩形，这里是设置矩形圆角大小
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView3.hide();
//                        guideView.show();
                        SharedPreferencesUtil.saveData(CanvasGuideActivity.this, "isFirst", false);
                    }
                })
                .build();

        guideView.show();

    }
}
