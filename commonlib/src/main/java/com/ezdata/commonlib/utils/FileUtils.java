package com.ezdata.commonlib.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Environment;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description：GriddingSys
 * Created by：Kyle
 * Date：2017/8/7
 */
public class FileUtils {

    /**
     * InputStrem 转byte[]
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static byte[] readStreamToBytes(InputStream in) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 8];
        int length = -1;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        out.flush();
        byte[] result = out.toByteArray();
        in.close();
        out.close();
        return result;
    }

    /**
     * 写入文件
     *
     * @param in
     * @param file
     */
    public static void writeFile(InputStream in, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (file != null && file.exists())
            file.delete();

        FileOutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 128];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();

    }

    /**
     * 得到Bitmap的byte
     *
     * @return
     * @author YOLANDA
     */
    public static byte[] bmpToByteArray(Bitmap bmp) {
        if (bmp == null)
            return null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 80, output);

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    /*
    * 根据view来生成bitmap图片，可用于截图功能
    */
    public static Bitmap getViewBitmap(View v) {

        v.clearFocus(); //

        v.setPressed(false); //
        // 能画缓存就返回false

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }

        v.buildDrawingCache();

        Bitmap cacheBitmap = v.getDrawingCache();

        if (cacheBitmap == null) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        // Restore the view

        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;

    }

    /**
     * 图片压缩 大于100KB
     *
     * @param list 拍照获取的图片的路径
     */
//    public static void compress (final Context context, List<String> list, final Map<String, RequestBody> params, final ImageCompressListener listener) {
//        if (list == null || list.size () == 0)
//            return;
//        final int size = list.size ();
//        Luban.with (context).load (list).setCompressListener (new OnCompressListener () {
//            @Override
//            public void onStart () {
//                DialogUtils.startLoad (context, "");
//            }
//
//            @Override
//            public void onSuccess (File file) {
//                Constant.COMPRESS_COUNT++;
//                Constant.compressResult.add (file);
//                if (Constant.COMPRESS_COUNT == size) {
//                    addParam (Constant.compressResult, params, listener);
//                    Constant.COMPRESS_COUNT = 0;
//                }
//            }
//
//            @Override
//            public void onError (Throwable e) {
//
//            }
//        }).launch ();
//    }
//
//    private static void addParam (List<File> list, Map<String, RequestBody> params, ImageCompressListener listener) {
//        for (File file : list) {
//            params.put ("file\"" + "; filename=\"" + file.getName (), RequestBody.create (MediaType.parse ("image/*"), file));
//        }
//        listener.compressResult (params);
//        Constant.compressResult.clear ();
//    }

    /**
     * Checks if is sd card available.
     *
     * @return true, if is sd card available
     */
    public static boolean isSdCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * Gets the SD root file.
     *
     * @return the SD root file
     */
    public static File getSDRootFile() {
        if (isSdCardAvailable()) {
            return Environment.getExternalStorageDirectory();
        } else {
            return null;
        }
    }

    /**
     * 获取prison安装包的路径
     * 有3个文件，新apk，旧apk，差异包
     * @return
     */
    public static File getApkUpdateDirectory() {
        File sdRootFile = getSDRootFile();
        File file = null;
        if (sdRootFile != null && sdRootFile.exists()) {
            file = new File(sdRootFile, "zzz_prisonApks");
            if (!file.exists()) {
                boolean success = file.mkdirs();
            }
        }

        return file;
    }
}
