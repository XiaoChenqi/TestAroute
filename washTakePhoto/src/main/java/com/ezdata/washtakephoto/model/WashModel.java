package com.ezdata.washtakephoto.model;

import com.ezdata.commonlib.bean.BaseResponse;
import com.ezdata.commonlib.core.mvp.BasePresenter;
import com.ezdata.commonlib.core.mvp.BaseSubcriber;
import com.ezdata.commonlib.net.INetCallback;
import com.ezdata.commonlib.net.NetWork;
import com.ezdata.washtakephoto.bean.ListBean;
import com.ezdata.washtakephoto.bean.PhotoDetail;
import com.ezdata.washtakephoto.bean.WashTask;
import com.ezdata.washtakephoto.bean.WashTaskDetail;
import com.ezdata.washtakephoto.bean.WashWater;
import com.ezdata.washtakephoto.model.imodel.IWahModel;
import com.ezdata.washtakephoto.net.WashNetWork;

import java.util.List;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class WashModel implements IWahModel {
    @Override
    public void getTaskList(String page,String size,String machine_code, String status, String task_id,final INetCallback<ListBean<WashTask>> callback) {

        WashNetWork.getInstance().getWashNetApi().getTaskList(page,size,machine_code,status,task_id).
                subscribeOn(Schedulers.io())//事件产生的线程
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //这里可以做一些初始化的操作，加载初始动画，
                        if (callback != null) {
                            callback.startRequest();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//事件消费的线程
                .subscribe(new BaseSubcriber<BaseResponse<ListBean<WashTask>>>(callback));

    }

    @Override
    public Subscription getTaskListSub(String page, String size, String machine_code, String status, String task_id,final INetCallback<ListBean<WashTask>> callback) {
        Subscription taskSubtion = WashNetWork.getInstance().getWashNetApi().getTaskList(page,size,machine_code,status,task_id).
                subscribeOn(Schedulers.io())//事件产生的线程
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //这里可以做一些初始化的操作，加载初始动画，
                        if (callback != null) {
                            callback.startRequest();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//事件消费的线程
                .subscribe(new BaseSubcriber<BaseResponse<ListBean<WashTask>>>(callback));
        return  taskSubtion;
    }

//    @Override
//    public void takePhoto(String task_id,final INetCallback callback) {
//        WashNetWork.getInstance().getWashNetApi().takePhoto(task_id)
//                .subscribeOn(Schedulers.io())//事件产生的线程
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        //这里可以做一些初始化的操作，加载初始动画，
//                        if (callback != null) {
//                            callback.startRequest();
//                        }
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())//事件消费的线程
//                .subscribe(new BaseSubcriber<BaseResponse>(callback));
//    }

    @Override
    public void getTaskDetails(String taskId,final INetCallback callback) {
        WashNetWork.getInstance().getWashNetApi().getTaskDetail(taskId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //这里可以做一些初始化的操作，加载初始动画，
                        if (callback != null) {
                            callback.startRequest();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//事件消费的线程
                .subscribe(new BaseSubcriber<BaseResponse<List<WashTaskDetail>>>(callback));
    }

    @Override
    public Subscription takePhotoV2(String task_id,final INetCallback<PhotoDetail> callback) {
        Subscription photoResult = WashNetWork.getInstance().getWashNetApi().takePhotoV2(task_id)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //这里可以做一些初始化的操作，加载初始动画，
                        if (callback != null) {
                            callback.startRequest();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubcriber<BaseResponse<PhotoDetail>>(callback));
        return photoResult;
    }

    @Override
    public Subscription submitPhotoInfo(String imgPath, String loadCapacity, String classifys, String taskId,final INetCallback callback) {
        Subscription photoInfoResult = WashNetWork.getInstance().getWashNetApi().submitPhotoInfo(imgPath,loadCapacity,classifys,taskId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //这里可以做一些初始化的操作，加载初始动画，
                        if (callback != null) {
                            callback.startRequest();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubcriber<BaseResponse>(callback));
        return photoInfoResult;
    }

    @Override
    public Subscription deletePhoto(String imgPath,final INetCallback callback) {
        Subscription result = WashNetWork.getInstance().getWashNetApi().deletePhoto(imgPath)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //这里可以做一些初始化的操作，加载初始动画，
                        if (callback != null) {
                            callback.startRequest();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubcriber<BaseResponse>(callback));
        return result;
    }

    @Override
    public Subscription getWashWaterInfo(String code, final INetCallback<WashWater> callback) {
        Subscription result = WashNetWork.getInstance().getWashNetApi().getWashWaterInfo(code)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //这里可以做一些初始化的操作，加载初始动画，
                        if (callback != null) {
                            callback.startRequest();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubcriber<BaseResponse<WashWater>>(callback));
        return result;
    }

    @Override
    public Subscription submitWashWaterInfo(String barCode, String brandName, String productName, String productForm, String productModel, String potency, final INetCallback callback) {
        Subscription result = WashNetWork.getInstance().getWashNetApi().submitWashWaterInfo(barCode,brandName,productName,productForm,productModel,potency)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //这里可以做一些初始化的操作，加载初始动画，
                        if (callback != null) {
                            callback.startRequest();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubcriber<BaseResponse>(callback));
        return result;
    }
}
