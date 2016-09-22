package com.android.zhijiaoyi.ui.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.zhijiaoyi.R;
import com.android.zhijiaoyi.base.BaseActivity;

public class DialogAlertActivity extends BaseActivity implements View.OnClickListener {

    private Button mShowMessage;
    private Button mShowLongMessage;
    private Button mShowProgress;
    private Button mShowProgressHorizontal;
    private Button mShowList;
    private Button mShowMultiChoiceList;
    private Button mShowSingleChoiceList;
    private Button mShowCustomView;
    private static final int NATIVE_THEME = Integer.MIN_VALUE;
    private int mTheme = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String returnToolBarTitle() {
        return "AlertDialog";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog_alert;
    }

    private void initView() {
        mShowMessage = (Button) findViewById(R.id.showMessage);
        mShowLongMessage = (Button) findViewById(R.id.showLongMessage);
        mShowProgress = (Button) findViewById(R.id.showProgress);
        mShowProgressHorizontal = (Button) findViewById(R.id.showProgressHorizontal);
        mShowList = (Button) findViewById(R.id.showList);
        mShowMultiChoiceList = (Button) findViewById(R.id.showMultiChoiceList);
        mShowSingleChoiceList = (Button) findViewById(R.id.showSingleChoiceList);
        mShowCustomView = (Button) findViewById(R.id.showCustomView);

        mShowMessage.setOnClickListener(this);
        mShowLongMessage.setOnClickListener(this);
        mShowProgress.setOnClickListener(this);
        mShowProgressHorizontal.setOnClickListener(this);
        mShowList.setOnClickListener(this);
        mShowMultiChoiceList.setOnClickListener(this);
        mShowSingleChoiceList.setOnClickListener(this);
        mShowCustomView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showMessage:
                showMessageAlertDialog();
                break;
            case R.id.showLongMessage:

                break;
            case R.id.showProgress:

                break;
            case R.id.showProgressHorizontal:

                break;
            case R.id.showList:

                break;
            case R.id.showMultiChoiceList:

                break;
            case R.id.showSingleChoiceList:

                break;
            case R.id.showCustomView:

                break;
        }
    }
    private void showMessageAlertDialog() {
        createAlertDialogBuilder()
                .setTitle(R.string.app_name)
                .setMessage("Hello, charming AlertDialogPro!")
                .setPositiveButton("Nice Job", new ButtonClickedListener("Dismiss"))
                .show();
    }

    private AlertDialog.Builder createAlertDialogBuilder() {
        if (mTheme == NATIVE_THEME) {
            return new AlertDialog.Builder(this);
        }

        return new AlertDialog.Builder(this, mTheme);
    }
    private class ButtonClickedListener implements DialogInterface.OnClickListener {
        private CharSequence mShowWhenClicked;

        public ButtonClickedListener(CharSequence showWhenClicked) {
            mShowWhenClicked = showWhenClicked;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            showToast("\"" + mShowWhenClicked + "\"" + " button clicked.");
        }
    }
    private Toast mToast = null;
    private void showToast(CharSequence toastText) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
        mToast.show();
    }

}
