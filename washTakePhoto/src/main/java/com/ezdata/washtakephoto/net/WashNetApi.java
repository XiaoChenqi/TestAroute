package com.ezdata.washtakephoto.net;

import com.ezdata.commonlib.bean.BaseResponse;
import com.ezdata.washtakephoto.bean.ListBean;
import com.ezdata.washtakephoto.bean.PhotoDetail;
import com.ezdata.washtakephoto.bean.WashTask;
import com.ezdata.washtakephoto.bean.WashTaskDetail;
import com.ezdata.washtakephoto.bean.WashWater;


import java.util.List;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

public interface WashNetApi {

    @GET("plan/task/list")
    Observable<BaseResponse<ListBean<WashTask>>> getTaskList(@Query("page") String page,
                                                             @Query("size") String size,
                                                             @Query("machine_code") String machine_code,
                                                             @Query("status") String status,
                                                             @Query("task_id") String task_id);

    /**
     * 拍照
     *
     * @return
     */
//    @FormUrlEncoded
//    @POST("api/execute/task")
//    Observable<BaseResponse> takePhoto(@Field("task_id") String task_id);
    @FormUrlEncoded
    @POST("execute/task")
    Observable<BaseResponse<PhotoDetail>> takePhotoV2(@Field("task_id") String task_id);


    @GET("plan/task/clothes/group")
    Observable<BaseResponse<List<WashTaskDetail>>> getTaskDetail(@Query("task_id") String task_id);

    /**
     * 提交负载量照片信息
     *
     * @param imgPath
     * @param loadCapacity
     * @param classifys
     * @param taskId
     * @return
     */
    //@FormUrlEncoded
    @POST("execute/save")
    Observable<BaseResponse> submitPhotoInfo(@Query("imgPath") String imgPath,
                                             @Query("loadCapacity") String loadCapacity,
                                             @Query("classifys") String classifys,
                                             @Query("taskId") String taskId);

    /**
     * 照片作废接口
     *
     * @param imgPath
     * @return
     */
    @POST("/execute/deletePhoto")
    Observable<BaseResponse> deletePhoto(@Query("path") String imgPath);

    @GET("/liquid/selectCode")
    Observable<BaseResponse<WashWater>> getWashWaterInfo(@Query("code") String code);


    @POST("/liquid/add")
    Observable<BaseResponse> submitWashWaterInfo(@Query("barCode") String barCode,
                                                 @Query("brandName") String brandName,
                                                 @Query("potency") String potency,
                                                 @Query("productName") String productName,
                                                 @Query("productModel") String productModel,
                                                 @Query("productForm") String productForm);
}
