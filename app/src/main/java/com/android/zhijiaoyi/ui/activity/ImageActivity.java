package com.android.zhijiaoyi.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.zhijiaoyi.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.attr.width;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_camera;
    private Button btn_galery;
    private ImageView imageView;

    //创建Bitmap对象
    private Bitmap bitmap;
    //文件保存目录
    private File saveCatalog;
    //保存的文件
    private File saveFile;
    //文件保存目录字符串
    String path = Environment.getExternalStorageDirectory().getPath() + "/Zeny";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
        saveCatalog = new File(path);
        if (!saveCatalog.exists()) {
            //创建目录
            saveCatalog.mkdirs();
        }
        //创建保存图片文件File
        saveFile = new File(saveCatalog, "image2.jpg");
        if (!saveFile.exists()) {
            try {
                //创建文件
                saveFile.createNewFile();
            } catch (IOException e) {
                Toast.makeText(this, "创建文件失败！", Toast.LENGTH_SHORT).show();
            }
        }
        if (saveFile.exists()) {
            Picasso.with(ImageActivity.this)//
                    .load(saveFile)//
                    .into(imageView);
        }

    }

    /**
     * 拍照按钮
     */
    public void openCamera() {
        //创建一个意图
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(saveFile));
        startActivityForResult(intent, 3);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //保存后调用，裁剪
                case 1:
                    //创建裁剪的意图
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    try {
                        intent.setData(Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(), saveFile.getPath(), null, null)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    intent.putExtra("crop", "true");
                    intent.putExtra("aspectX", 3);
                    intent.putExtra("aspectY", 2);
                    intent.putExtra("outputX", 110);
                    intent.putExtra("outputY", 110);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, 2);
                    //获取保存的图片
//                    saveFile=new File(saveCatalog,"image.jpg");
                    //如果存在，则删除
                    if (saveFile.exists())
                        //删除文件
                        saveFile.delete();
                    break;
                //剪切后显示图片，并保存图片
                case 2:
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        //获取Bitmap对象
                        bitmap = extras.getParcelable("data");
                    }
                    //压缩图片
                    try {
                        //创建FileOutputStream对象
                        FileOutputStream fos = new FileOutputStream(saveFile);
                        //开始压缩图片
                        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos)) {
                            fos.flush();
                            //关闭流对象
                            fos.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //将图片显示到ImageView中
                    imageView.setImageBitmap(bitmap);
                    break;
                //压缩图片显示
                case 3:
                    //创建Options对象
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(saveFile.getPath(), opts);
                    bitmap = comp(bitmap);
                    //将图片显示到ImageView中
                    imageView.setImageBitmap(bitmap);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 压缩图片显示
     */
    private Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 100f;//这里设置高度为100f
        float ww = 100f;//这里设置宽度为100f
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    private Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    private void initView() {
        btn_camera = (Button) findViewById(R.id.btn_camera);
        btn_galery = (Button) findViewById(R.id.btn_galery);
        imageView = (ImageView) findViewById(R.id.iv);

        btn_camera.setOnClickListener(this);
        btn_galery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:
                openCamera();
                break;
            case R.id.btn_galery:

                break;
        }
    }
}
