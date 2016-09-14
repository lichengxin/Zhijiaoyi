package com.android.zhijiaoyi.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.zhijiaoyi.R;
import com.dinuscxj.progressbar.CircleProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class UpdateManager {

    private SharedPreferences preferences;

    private Activity mContext;

    //返回的安装包
    private String apkUrl;

    /* 下载包安装路径 */
    private static final String savePath = "/sdcard/update/";

    private static final String saveFileName = savePath + "zjy.apk";

    /* 进度条与通知ui刷新的handler和msg常量 */
    private ProgressDialog progressDialog;

    private static final int DOWN_UPDATE = 1;

    private static final int DOWN_OVER = 2;

    private int progress;

    private Thread downLoadThread;

    private boolean interceptFlag = false;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    mProgerss.setProgress(progress);
                    break;
                case DOWN_OVER:
                    mDialog.dismiss();
                    installApk();
                    break;
                default:
                    break;
            }
        }
    };
    private CircleProgressBar mProgerss;
    private Dialog mDialog;

    public UpdateManager(Activity context, String url) {
        this.mContext = context;
        this.apkUrl = url;
    }

    //1: 普通更新 2:强制更新
    public void ApkUpdateInfo(String title, StringBuilder msg, int type) {
        showDialog(title, msg.toString(), type);
    }

    private void showDialog(String title, String message, final int type) {
        /*
            这里使用了 android.support.v7.app.AlertDialog.Builder
            可以直接在头部写 import android.support.v7.app.AlertDialog
            那么下面就可以写成 AlertDialog.Builder
        */
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
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
                    showDownloadDialog(type);
                }
            });
        }

        builder.show();
    }


    private void showDownloadDialog(final int num) {

        mDialog = new Dialog(mContext, R.style.theme_dialog);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.progress_dialog_horizonal, null, false);
        TextView msg = (TextView) view.findViewById(R.id.tv_topic_msg);
        mProgerss = (CircleProgressBar) view.findViewById(R.id.line_progress);
        Button neg = (Button) view.findViewById(R.id.btn_download_neg);
        Button pos = (Button) view.findViewById(R.id.btn_download_pos);
        msg.setText("正在下载中...");
        neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interceptFlag = true;

            }
        });
        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        mDialog.show();
        downloadApk();
    }

    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(apkUrl);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                String apkFile = saveFileName;
                File ApkFile = new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    progress = (int) (((float) count / length) * 100 + 1);
                    //更新进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        //下载完成通知安装
                        mHandler.sendEmptyMessage(DOWN_OVER);
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);//点击取消就停止下载.

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    /**
     * 下载apk
     *
     * @param
     */
    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    /**
     * 安装apk
     *
     * @param
     */
    private void installApk() {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        mContext.startActivity(i);
        preferences = mContext.getSharedPreferences("count", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putInt("count", 0);
        //提交修改
        editor.commit();
    }
}
