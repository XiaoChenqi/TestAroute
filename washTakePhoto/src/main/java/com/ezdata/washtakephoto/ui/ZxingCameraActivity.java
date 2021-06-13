package com.ezdata.washtakephoto.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ezdata.commonlib.Constants.Constant;
import com.ezdata.commonlib.core.BaseActivity;
import com.ezdata.commonlib.core.mvp.MvpView;
import com.ezdata.commonlib.glide.GlideUtils;
import com.ezdata.commonlib.utils.ToastUtils;
import com.ezdata.washtakephoto.MainActivity;
import com.ezdata.washtakephoto.R;
import com.ezdata.washtakephoto.bean.WashTask;
import com.ezdata.washtakephoto.bean.WashWater;
import com.ezdata.washtakephoto.presenter.PhotoInfoPresenter;
import com.ezdata.washtakephoto.presenter.WashPresenter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ZxingCameraActivity extends BaseActivity implements MvpView {

    private WashPresenter presenter;
    private final int GET_WASH_WATER_CODE = 90;
    private final int NO_CODE = 500;

    TextView qrTv;
    TextView textView15;
    TextView textView16;
    TextView textView17;
    TextView textView18;
    TextView textView19;
    TextView textView20;
    TextView textView21;
    TextView textView22;
    TextView textView23;
    TextView textView24;
    TextView textView25;
    TextView textView28;
    TextView textView30;
    TextView textView32;
    TextView textView34;
    TextView textView36;
    TextView textView38;
    TextView textView40;
    TextView textView42;
    ImageView imageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zxing_camera;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        qrTv = findViewById(R.id.qrTv);
        imageView = findViewById(R.id.imageView);
        textView15 = findViewById(R.id.textView15);
        textView16 = findViewById(R.id.textView16);
        textView17 = findViewById(R.id.textView17);
        textView18 = findViewById(R.id.textView18);
        textView19 = findViewById(R.id.textView19);
        textView20 = findViewById(R.id.textView20);
        textView21 = findViewById(R.id.textView21);
        textView22 = findViewById(R.id.textView22);
        textView23 = findViewById(R.id.textView23);
        textView24 = findViewById(R.id.textView24);
        textView25 = findViewById(R.id.textView25);
        textView28 = findViewById(R.id.textView28);
        textView30 = findViewById(R.id.textView30);
        textView32 = findViewById(R.id.textView32);
        textView34 = findViewById(R.id.textView34);
        textView36 = findViewById(R.id.textView36);
        textView38 = findViewById(R.id.textView38);
        textView40 = findViewById(R.id.textView40);
        textView42 = findViewById(R.id.textView42);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        qrTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(ZxingCameraActivity.this).initiateScan();
            }
        });
    }

    @Override
    protected void initData() {
        //实例化presenter
        presenter = new WashPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                // 做网络请求
                //getQrResult("4012400524303");
                getQrResult(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     *
     */
    public void getQrResult(String code) {
        presenter.getWashWaterInfo(code, GET_WASH_WATER_CODE);
    }

    @Override
    public void onStartRequest(int requestCode) {

    }

    @Override
    public void onSuccess(int requestCode, Object o) {
        Log.d(TAG, "onSuccess: ");
        switch (requestCode) {
            case GET_WASH_WATER_CODE:
                WashWater temp = (WashWater) o;
                StringBuilder imgPath = new StringBuilder().append(Constant.BASE_URL).append(temp.img);
                GlideUtils.displayUrlCenter(imageView, imgPath.toString(), R.drawable.loading_01);
                textView15.setText(": "+temp.getBarCode());
                textView16.setText(": "+temp.getBrandName());
                textView17.setText(": "+temp.getProductName());
                textView18.setText(": "+temp.getProductForm());
                textView19.setText(": "+temp.getProductModel());
                textView20.setText(": "+temp.getFragranceType());
                textView21.setText(": "+temp.getSpecifications());
                textView22.setText(": "+temp.getPotency());
                textView23.setText(": "+temp.getFeature());
                textView24.setText(": "+temp.getContainp());
                textView25.setText(": "+temp.getWash());
                textView28.setText(": "+temp.getScope());
                textView30.setText(": "+temp.getUsages());
                textView32.setText(": "+temp.getNote());
                textView34.setText(": "+temp.getMainIngredients());
                textView36.setText(": "+temp.getStorageRequirement());
                textView38.setText(": "+temp.getOrginState());
                textView40.setText(": "+temp.getProductArea());
                textView42.setText(": "+temp.getProductStandard());
                break;

        }

    }

    @Override
    public void onErrorCode(int resultCode, String msg, int requestCode) {
        Log.d(TAG, "onErrorCode: ");

                // 跳转到录入界面
                ToastUtils.show(this, "未识别到该产品", Toast.LENGTH_LONG);
                Intent it  = new Intent(ZxingCameraActivity.this,WashWaterSubmitActivity.class);
                startActivity(it);



    }

    @Override
    public void onEndRequest(int requestCode) {
        Log.d(TAG, "onEndRequest: ");
    }
    String TAG = "周杨";

    @Override
    public void onFailure(Throwable e) {
        Log.d(TAG, "onFailure: ");
    }
}
