package com.android.zhijiaoyi.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;


public class ImageUtil {
    public static final String CROP_CACHE_FILE_NAME = "icon_head.jpg";
    public static final int REQUEST_GALLERY = 0xa0;
    public static final int REQUEST_CAMERA = 0xa1;
    public static final int RE_GALLERY = 127;
    public static final int RE_CAMERA = 128;

    public ImageUtil() {
    }

    private static ImageUtil instance = new ImageUtil();

    public static ImageUtil getCropHelperInstance() {
        return instance;
    }

    private Uri buildUri() {
        return Uri.fromFile(Environment.getExternalStorageDirectory())
                .buildUpon().appendPath(CROP_CACHE_FILE_NAME).build();
    }

    public void sethandleResultListerner(CropHandler handler, int requestCode,
                                         int resultCode, Intent data) {
        if (handler == null)
            return;
        if (resultCode == Activity.RESULT_CANCELED) {
            handler.onCropCancel();
        } else if (resultCode == Activity.RESULT_OK) {
            Bitmap photo;
            switch (requestCode) {
                case RE_CAMERA:
                    if (data == null || data.getExtras() == null) {
                        handler.onCropFailed("CropHandler's context MUST NOT be null!");
                        return;
                    }
                    photo = data.getExtras().getParcelable("data");
                    try {
                        photo.compress(Bitmap.CompressFormat.JPEG, 30,
                                new FileOutputStream(getCachedCropFile()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                 /*   //创建Options对象
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    photo = BitmapFactory.decodeFile(saveFile.getPath(), opts);
                    photo = comp(photo);
                    //将图片显示到ImageView中*/
                    handler.onPhotoCropped(photo, requestCode);
                    break;
                case RE_GALLERY:
                    if (data == null || data.getExtras() == null) {
                        handler.onCropFailed("CropHandler's context MUST NOT be null!");
                        return;
                    }
                    photo = data.getExtras().getParcelable("data");
                    try {
                        photo.compress(Bitmap.CompressFormat.JPEG, 30,
                                new FileOutputStream(getCachedCropFile()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.onPhotoCropped(photo, requestCode);
                    break;
                case REQUEST_CAMERA:
                    Intent intent = buildCropIntent(buildUri());
                    if (handler.getContext() != null) {
                        handler.getContext().startActivityForResult(intent,
                                RE_CAMERA);
                    } else {
                        handler.onCropFailed("CropHandler's context MUST NOT be null!");
                    }
                    break;
                case REQUEST_GALLERY:
                    if (data == null) {
                        handler.onCropFailed("Data MUST NOT be null!");
                        return;
                    }
                    Intent intent2 = buildCropIntent(data.getData());

                    if (handler.getContext() != null) {
                        handler.getContext().startActivityForResult(intent2,
                                RE_GALLERY);
                    } else {
                        handler.onCropFailed("CropHandler's context MUST NOT be null!");
                    }
                    break;
            }
        }
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

    public Intent buildGalleryIntent() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        return galleryIntent;
    }

    public Intent buildCameraIntent() {
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        if (hasSdcard()) {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, buildUri());
        }
        return cameraIntent;
    }

    private boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private Intent buildCropIntent(Uri uri) {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(uri, "image/*");
        cropIntent.putExtra("crop", "true");
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("outputX", 300);
        cropIntent.putExtra("outputY", 300);
        cropIntent.putExtra("return-data", true);
        return cropIntent;
    }

    public interface CropHandler {
        void onPhotoCropped(Bitmap photo, int requesCode);

        void onCropCancel();

        void onCropFailed(String message);

        Activity getContext();
    }

    public File getCachedCropFile() {
        if (buildUri() == null)
            return null;
        LogUtils.logE(ImageUtil.class, new File(buildUri().getPath()).getAbsolutePath() + "-----");
        return new File(buildUri().getPath());
    }

    public Bitmap getImage(String path, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            // 缩放
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = (int) scale;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), w, h, true);
    }

}
