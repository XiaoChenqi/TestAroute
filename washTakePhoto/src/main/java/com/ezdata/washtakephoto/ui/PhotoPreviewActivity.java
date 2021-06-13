package com.ezdata.washtakephoto.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Interpolator;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.ezdata.commonlib.core.BaseActivity;
import com.ezdata.commonlib.glide.GlideUtils;
import com.ezdata.washtakephoto.R;

public class PhotoPreviewActivity extends BaseActivity {

    //ImageView bigIv;
    PhotoView photoView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_preview;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //bigIv  = findViewById(R.id.bigIv);
        photoView = (PhotoView) findViewById(R.id.img);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        Intent it =  getIntent();
        String url = it.getStringExtra("imgPath");
        //url = "http://192.168.88.250:8082/clothes/cd-4.png";
        GlideUtils.displayUrlCenter(photoView, url, R.drawable.loading_01);

        /**
         * 缩放图片
         */
        // 启用图片缩放功能
        photoView.enable();
        // 获取图片信息
        Info info = photoView.getInfo();
        // 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
        photoView.animaFrom(info);
        // 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照demo的使用
//        photoView.animaTo(info,new Runnable() {
//            @Override
//            public void run() {
//                //动画完成监听
//            }
//        });
        // 获取/设置 动画持续时间
        photoView.setAnimaDuring(60000);
        int d = photoView.getAnimaDuring();
// 获取/设置 最大缩放倍数
        photoView.setMaxScale(3);
        float maxScale = photoView.getMaxScale();
// 设置动画的插入器
        photoView.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return 0;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
