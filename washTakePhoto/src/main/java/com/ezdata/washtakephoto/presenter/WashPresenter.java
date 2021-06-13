package com.ezdata.washtakephoto.presenter;

import android.text.TextUtils;

import com.ezdata.commonlib.core.mvp.BasePresenter;
import com.ezdata.commonlib.core.mvp.MvpView;
import com.ezdata.commonlib.net.BaseNetCallback;
import com.ezdata.washtakephoto.bean.ListBean;
import com.ezdata.washtakephoto.bean.WashTask;
import com.ezdata.washtakephoto.bean.WashTaskDetail;
import com.ezdata.washtakephoto.bean.WashWater;
import com.ezdata.washtakephoto.model.WashModel;
import com.ezdata.washtakephoto.presenter.iview.WashMvpView;

import java.util.List;

import rx.Subscription;

public class WashPresenter extends BasePresenter<MvpView> {

    private static WashModel washModel;//

    public synchronized static WashModel initWashModel() {
        if (washModel == null) {
            washModel = new WashModel();
        }
        return washModel;
    }

    public WashPresenter() {
        initWashModel();
    }

    public void getClothGroupList(int page, int size, String machine_code, String status, String task_id, final int requestCode) {
        //washModel.getTaskList(String.valueOf(page),String.valueOf(size),machine_code,status,task_id,new BaseNetCallback<ListBean<WashTask>>(getMvpView(),requestCode));
        Subscription tempSub = washModel.getTaskListSub(String.valueOf(page), String.valueOf(size), machine_code, status, task_id, new BaseNetCallback<ListBean<WashTask>>(getMvpView(), requestCode));
        addSubscription(tempSub);
    }

    public void getTaskDetails(String taskId, final int requestCode) {
        washModel.getTaskDetails(taskId, new BaseNetCallback<List<WashTaskDetail>>(getMvpView(), requestCode));
    }

    public void getWashWaterInfo(String code, final int requestCode) {
        Subscription tempSub = washModel.getWashWaterInfo(code,new BaseNetCallback<WashWater>(getMvpView(), requestCode));
        addSubscription(tempSub);
    }
}
