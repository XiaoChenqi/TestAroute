package com.ezdata.washtakephoto.presenter;

import android.text.TextUtils;

import com.ezdata.commonlib.core.mvp.BasePresenter;
import com.ezdata.commonlib.net.BaseNetCallback;
import com.ezdata.washtakephoto.model.WashModel;
import com.ezdata.washtakephoto.presenter.iview.WashMvpView;

import rx.Subscription;

public class PhotoInfoPresenter extends BasePresenter<WashMvpView> {

    private static WashModel washModel;//

    public synchronized static WashModel initWashModel() {
        if (washModel == null) {
            washModel = new WashModel();
        }
        return washModel;
    }

    public PhotoInfoPresenter() {
        initWashModel();
    }

    /**
     * 拍照v2
     * @param takId
     * @param requestCode
     */
    public void takePhotoV2(String takId, final int requestCode) {
        washModel.takePhotoV2(takId, new BaseNetCallback(getMvpView(), requestCode));
    }

    /**
     * 提交照片负载量
     * @param imgPath
     * @param loadCapacity
     * @param classifys
     * @param taskId
     * @param requestCode
     */
    public void submitPhotoInfo(String imgPath,
                                String loadCapacity,
                                String classifys,
                                String taskId, final int requestCode) {
        if(TextUtils.isEmpty(loadCapacity)){
            getMvpView().burdenNull();
            return;
        }
        Subscription tempSub = washModel.submitPhotoInfo(imgPath,
                loadCapacity,
                classifys,
                taskId, new BaseNetCallback(getMvpView(), requestCode));
        addSubscription(tempSub);

    }

    /**
     * 删除照片
     * @param imgPath
     * @param requestCode
     */
    public void deletPhoto(String imgPath,final int requestCode){
        Subscription tempSub = washModel.deletePhoto(imgPath,
                new BaseNetCallback(getMvpView(), requestCode));
        addSubscription(tempSub);
    }
}
