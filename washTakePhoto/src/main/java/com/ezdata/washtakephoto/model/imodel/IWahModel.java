package com.ezdata.washtakephoto.model.imodel;


import com.ezdata.commonlib.net.INetCallback;
import com.ezdata.washtakephoto.bean.ListBean;
import com.ezdata.washtakephoto.bean.PhotoDetail;
import com.ezdata.washtakephoto.bean.WashTask;
import com.ezdata.washtakephoto.bean.WashWater;

import java.util.List;

import rx.Subscription;

public interface IWahModel {
    /**
     * 获取任务信息
     *
     * @param machine_code
     * @param status
     * @param task_id
     * @param callback
     */
    void getTaskList(String page, String size, String machine_code, String status, String task_id, INetCallback<ListBean<WashTask>> callback);

    Subscription getTaskListSub(String page, String size, String machine_code, String status, String task_id, INetCallback<ListBean<WashTask>> callback);
    /**
     *
     * @param task_id
     * @param callback
     */
    //void takePhoto(String task_id, INetCallback callback);

    /**
     * 获取任务详情
     *
     * @param taskId
     * @param callback
     */
    void getTaskDetails(String taskId, INetCallback callback);

    Subscription takePhotoV2(String task_id, INetCallback<PhotoDetail> callback);

    /**
     * 提交负载量照片信息
     *
     * @param imgPath
     * @param loadCapacity 负载量 empty，few, mid, many
     * @param classifys    衣物种类  “shirt：5-sweater：8” 表示衬衫5件，毛衣8件
     * @param taskId
     * @param callback
     * @return
     */
    Subscription submitPhotoInfo(String imgPath,
                                 String loadCapacity,
                                 String classifys,
                                 String taskId, INetCallback callback);

    /**
     * 照片作废接口
     *
     * @param imgPath
     * @param callback
     * @return
     */
    Subscription deletePhoto(String imgPath, INetCallback callback);

    /**
     * 获取洗衣液信息
     *
     * @param code
     * @param callback
     * @return
     */
    Subscription getWashWaterInfo(String code, INetCallback<WashWater> callback);

    Subscription submitWashWaterInfo(String barCode,
                                     String brandName,
                                     String productName, String productForm,
                                     String productModel, String potency,
                                     INetCallback callback);
}
