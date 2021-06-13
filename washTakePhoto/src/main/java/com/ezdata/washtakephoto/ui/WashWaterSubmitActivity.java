package com.ezdata.washtakephoto.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ezdata.commonlib.core.BaseActivity;
import com.ezdata.commonlib.utils.ToastUtils;
import com.ezdata.washtakephoto.R;
import com.ezdata.washtakephoto.bean.WashWater;
import com.ezdata.washtakephoto.presenter.WashWaterInfoPresenter;
import com.ezdata.washtakephoto.presenter.iview.WashWaterMvpView;

public class WashWaterSubmitActivity extends BaseActivity implements WashWaterMvpView {


    private EditText barCodeEt;
    private EditText barNameEt;
    private EditText productNameEt;
    private EditText productFormEt;
    private EditText productModelEt;
    private EditText potencyEt;
    private Button submitBtn;

    private WashWaterInfoPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wash_water_submit;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        barCodeEt = findViewById(R.id.productFormEt);
        barNameEt = findViewById(R.id.barNameEt);
        productNameEt = findViewById(R.id.productNameEt);
        productFormEt = findViewById(R.id.productFormEt);
        productModelEt = findViewById(R.id.productModelEt);
        potencyEt = findViewById(R.id.potencyEt);
        submitBtn = findViewById(R.id.submitBtn);

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.submitWashWaterInfo(barCodeEt.getText().toString(),
                        barNameEt.getText().toString(),
                        productNameEt.getText().toString(),
                        productFormEt.getText().toString(),
                        productModelEt.getText().toString(),
                        potencyEt.getText().toString(),100
                        );
            }
        });
    }

    @Override
    protected void initData() {
        //实例化presenter
        presenter = new WashWaterInfoPresenter();
        presenter.attachView(this);
    }



    @Override
    public void onStartRequest(int requestCode) {

    }

    @Override
    public void onSuccess(int requestCode, Object o) {
        WashWaterSubmitActivity.this.finish();
    }

    @Override
    public void onErrorCode(int resultCode, String msg, int requestCode) {

    }

    @Override
    public void onEndRequest(int requestCode) {

    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    public void barCodeNull() {
        ToastUtils.show(this, "商品条码不能为空", Toast.LENGTH_LONG);
    }

    @Override
    public void barNameNull() {
        ToastUtils.show(this, "品牌名称不能为空", Toast.LENGTH_LONG);
    }

    @Override
    public void productNameNull() {
        ToastUtils.show(this, "产拍名称不能为空", Toast.LENGTH_LONG);
    }

    @Override
    public void productFormNull() {
        ToastUtils.show(this, "产品形态不能为空", Toast.LENGTH_LONG);
    }

    @Override
    public void productModelNull() {
        ToastUtils.show(this, "产品型号不能为空", Toast.LENGTH_LONG);
    }

    @Override
    public void potencyNull() {
        ToastUtils.show(this, "浓度不能为空", Toast.LENGTH_LONG);
    }
}
