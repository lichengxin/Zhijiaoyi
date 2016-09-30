package com.android.zhijiaoyi.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;
import com.android.zhijiaoyi.util.IntentUtil;
import com.wevey.selector.dialog.DialogOnClickListener;
import com.wevey.selector.dialog.DialogOnItemClickListener;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.MDEditDialog;
import com.wevey.selector.dialog.MDSelectionDialog;
import com.wevey.selector.dialog.NormalAlertDialog;
import com.wevey.selector.dialog.NormalSelectionDialog;

import java.util.ArrayList;

public class DialogActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout mBack;
    private Button mBtnAlert;
    private Button mBtnSelectDialog;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button button5;
    private Button button6;
    private NormalSelectionDialog dialog1;
    private NormalAlertDialog dialog2;
    private NormalAlertDialog dialog3;
    private MDAlertDialog dialog4;
    private MDEditDialog dialog6;
    private MDSelectionDialog dialog5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {
        initBottomDialog();
        initNormalDialog();
        initNormalDialog2();
        initMDDialog();
        initMDMidDialog();
        initMDEditDialog();
    }

    private void initBottomDialog() {

        final ArrayList<String> s = new ArrayList<>();
        s.add("Weavey0");
        s.add("Weavey1");
        s.add("Weavey2");
        s.add("Weavey3");

        dialog1 = new NormalSelectionDialog.Builder(this)
                .setlTitleVisible(true)   //设置是否显示标题
                .setTitleHeight(65)   //设置标题高度
                .setTitleText("please select")  //设置标题提示文本
                .setTitleTextSize(14) //设置标题字体大小 sp
                .setTitleTextColor(R.color.colorPrimary) //设置标题文本颜色
                .setItemHeight(40)  //设置item的高度
                .setItemWidth(0.9f)  //屏幕宽度*0.9
                .setItemTextColor(R.color.colorPrimaryDark)  //设置item字体颜色
                .setItemTextSize(14)  //设置item字体大小
                .setCancleButtonText("Cancle")  //设置最底部“取消”按钮文本
                .setOnItemListener(new DialogOnItemClickListener() {  //监听item点击事件
                    @Override
                    public void onItemClick(Button button, int position) {

//                                dialog1.dismiss();
                        Toast.makeText(DialogActivity.this, s.get(position), Toast.LENGTH_SHORT).show();
                    }
                })
                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                .build();


        dialog1.setDataList(s);


    }
    private void initNormalDialog(){

        dialog2 = new NormalAlertDialog.Builder(DialogActivity.this)
                .setHeight(0.23f)  //屏幕高度*0.23
                .setWidth(0.65f)  //屏幕宽度*0.65
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.black_light)
                .setContentText("是否关闭对话框？")
                .setContentTextColor(R.color.black_light)
                .setLeftButtonText("关闭")
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText("不关闭")
                .setRightButtonTextColor(R.color.black_light)
                .setOnclickListener(new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(View view) {
                        dialog2.dismiss();
                    }

                    @Override
                    public void clickRightButton(View view) {

                        dialog2.dismiss();
                    }
                })
                .build();

    }

    private void initNormalDialog2(){

        dialog3 = new NormalAlertDialog.Builder(DialogActivity.this)
                .setHeight(0.23f)  //屏幕高度*0.23
                .setWidth(0.65f)  //屏幕宽度*0.65
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.colorPrimary)
                .setContentText("是否关闭对话框？")
                .setContentTextColor(R.color.colorPrimaryDark)
                .setSingleMode(true)
                .setSingleButtonText("关闭")
                .setSingleButtonTextColor(R.color.colorAccent)
                .setCanceledOnTouchOutside(true)
                .setSingleListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog3.dismiss();
                    }
                })
                .build();

    }

    private void initMDDialog() {

        dialog4 = new MDAlertDialog.Builder(DialogActivity.this)
                .setHeight(0.21f)  //屏幕高度*0.21
                .setWidth(0.7f)  //屏幕宽度*0.7
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.black_light)
                .setContentText("确定发送文件？")
                .setContentTextColor(R.color.black_light)
                .setLeftButtonText("不发送")
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText("发送")
                .setRightButtonTextColor(R.color.black_light)
                .setTitleTextSize(16)
                .setContentTextSize(14)
                .setButtonTextSize(14)
                .setOnclickListener(new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(View view) {

                        dialog4.dismiss();
                    }

                    @Override
                    public void clickRightButton(View view) {

                        dialog4.dismiss();
                    }
                })
                .build();

    }

    private void initMDMidDialog(){


        final ArrayList<String> s = new ArrayList<>();
        s.add("标为未读");
        s.add("置顶聊天");
        s.add("删除该聊天");
        dialog5 = new MDSelectionDialog.Builder(DialogActivity.this)
                .setCanceledOnTouchOutside(true)
                .setItemTextColor(R.color.black_light)
                .setItemHeight(50)
                .setItemWidth(0.8f)  //屏幕宽度*0.8
                .setItemTextSize(15)

                .setCanceledOnTouchOutside(true)
                .setOnItemListener(new DialogOnItemClickListener() {
                    @Override
                    public void onItemClick(Button button, int position) {

                        Toast.makeText(DialogActivity.this, s.get(position), Toast.LENGTH_SHORT).show();
                        dialog5.dismiss();
                    }
                })
                .build();
        dialog5.setDataList(s);

    }

    private void initMDEditDialog(){

        dialog6 = new MDEditDialog.Builder(DialogActivity.this)
                .setTitleVisible(true)
                .setTitleText("修改用户名")
                .setTitleTextSize(20)
                .setTitleTextColor(R.color.black_light)
                .setContentText("Weavey")
                .setContentTextSize(18)
                .setMaxLength(7)
                .setHintText("7位字符")
                .setMaxLines(1)
                .setContentTextColor(R.color.colorPrimary)
                .setButtonTextSize(14)
                .setLeftButtonTextColor(R.color.colorPrimary)
                .setLeftButtonText("取消")
                .setRightButtonTextColor(R.color.colorPrimary)
                .setRightButtonText("确定")
                .setLineColor(R.color.colorPrimary)
                .setOnclickListener(new MDEditDialog.OnClickEditDialogListener() {
                    @Override
                    public void clickLeftButton(View view, String editText) {
                        dialog6.dismiss();
                        Toast.makeText(DialogActivity.this, editText.trim(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void clickRightButton(View view, String editText) {

                        Toast.makeText(DialogActivity.this, editText.trim(), Toast.LENGTH_SHORT).show();
                        dialog6.dismiss();
                    }
                })
                .setMinHeight(0.3f)
                .setWidth(0.8f)
                .build();
    }

    @Override
    public String returnToolBarTitle() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
    }

    private void initView() {
        mBack = (FrameLayout) findViewById(R.id.back);
        mBtnAlert = (Button) findViewById(R.id.btn_alert);
        mBtnAlert.setOnClickListener(this);

        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alert:
                IntentUtil.showIntent(DialogActivity.this, DialogAlertActivity.class);
                break;

            case R.id.button1:
                dialog1.show();
                break;
            case R.id.button2:
                dialog2.show();
                break;
            case R.id.button3:
                dialog3.show();
                break;
            case R.id.button4:
                dialog4.show();
                break;
            case R.id.button5:
                dialog5.show();
                break;
            case R.id.button6:
                dialog6.show();
                break;
        }
    }


}
