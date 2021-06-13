package com.ezdata.washtakephoto.presenter;

import android.text.TextUtils;

import com.ezdata.commonlib.core.mvp.BasePresenter;
import com.ezdata.commonlib.net.BaseNetCallback;
import com.ezdata.washtakephoto.model.WashModel;
import com.ezdata.washtakephoto.presenter.iview.WashMvpView;
import com.ezdata.washtakephoto.presenter.iview.WashWaterMvpView;

import rx.Subscription;

public class WashWaterInfoPresenter extends BasePresenter<WashWaterMvpView> {

    private static WashModel washModel;//

    public synchronized static WashModel initWashModel() {
        if (washModel == null) {
            washModel = new WashModel();
        }
        return washModel;
    }

    public WashWaterInfoPresenter() {
        initWashModel();
    }

    public void submitWashWaterInfo(String barCode,
                                    String brandName,
                                    String productName, String productForm,
                                    String productModel, String potency, final int requestCode) {
        if (TextUtils.isEmpty(barCode)) {
            getMvpView().barCodeNull();
            return;
        }
        if (TextUtils.isEmpty(brandName)) {
            getMvpView().barNameNull();
            return;
        }
        if (TextUtils.isEmpty(productName)) {
            getMvpView().productNameNull();
            return;
        }
        if (TextUtils.isEmpty(productForm)) {
            getMvpView().productFormNull();
            return;
        }
        if (TextUtils.isEmpty(productModel)) {
            getMvpView().productModelNull();
            return;
        }
        if (TextUtils.isEmpty(potency)) {
            getMvpView().potencyNull();
            return;
        }
        Subscription tempSub = washModel.submitWashWaterInfo(barCode,
                brandName,
                productName, productForm,
                productModel, potency, new BaseNetCallback(getMvpView(), requestCode));
        addSubscription(tempSub);

    }

}
