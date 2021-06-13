package com.ezdata.commonlib.net;

import android.util.Log;

import com.ezdata.commonlib.Constants.Constant;
import com.ezdata.commonlib.core.App;
import com.ezdata.commonlib.net.download.DownloadProgressListener;
import com.ezdata.commonlib.net.download.DownloadProgressResponseBody;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * 网络处理
 * Created by MSI-PC on 2018/4/4.
 */

public class NetWork implements DownloadProgressListener {

    private static NetWork instance;//不带header的请求
    private static NetWork instanceHeader;//带header的请求
    //private netApi;
    /**
     * 没有把返回类型构造进去的 Retrofit，
     * 构建了超时时间，baseurl，拦截器（第三方），头文件等的 Retrofit
     */
    private Retrofit partRetrofit;
    private DownloadProgressListener listener;


    public static NetWork getInstance() {
        if (instance == null)
            instance = new NetWork();
        return instance;
    }


    private NetWork() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.i("xcq", "NetWork: ");
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);//网络请求超时时间设置8秒
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);//连接超时时间设置15秒
        // 新加入的设置最大请求数
        //okHttpClient.getDispatcher().setMaxRequestsPerHost(1);//???
        //okHttpClient.getDispatcher().setMaxRequestsPerHost(1);???
        //http请求拦截器，用于查看网络请求发送状况
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //包含header和返回的body数据
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //不一定需要添加
                //添加统一的接口参数，例如：
                //100个接口都需要传userid和token的时候，可以在这里配置
                HttpUrl originalHttpUrl = chain.request().httpUrl();
                HttpUrl url = originalHttpUrl.newBuilder()
                        //.addQueryParameter("platformkey", "app_firecontrol_supervise")//???
                        .build();//此处统一的接口参数是paltfomkey
                Request newRequest=chain.request().newBuilder()
                        .url(url)
                // 添加头文件，不一定需要
                        //.addHeader("token","NmZRak51MXFGMzRLL29aRVlWVVo4MVk5NnhGQ1ZZcW1kUnR4TlY1cWtVa01Ic0JLVHIxc0xsY1l6N3ZBLzR5ZQ")
                        .build();
                Response response = chain.proceed(newRequest);
                //TODO 回头看看需不需要
                Response formatRes = response.newBuilder().body(new DownloadProgressResponseBody(response.body(),NetWork.this)).build();
                return formatRes;
            }
        };
        //添加拦截器
        List<Interceptor> interceptors=new ArrayList<>();
        interceptors.add(loggingInterceptor);
        interceptors.add(interceptor);
        okHttpClient.interceptors().addAll(interceptors);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(
                        RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(
                        App.getInstance().gson))
                .client(okHttpClient)
                .build();
        partRetrofit = retrofit;
        //netApi = retrofit.create(NetApi.class);

    }

    /**
     * 用于多数请求接口，不进行文件下载时使用
     * @return
     */
//    public NetApi getNetApi(){
//        return netApi;
//    }
    public Retrofit getPartRetrofit(){
        return partRetrofit;
    }
    /**
     * 用于少数有文件下载的接口使用
     * @param
     * @return
     */
//    public NetApi getNetApi(DownloadProgressListener lis){
//        listener=lis;
//        return netApi;
//    }

    @Override
    public void update(long bytesRead, long contentLength, boolean done) {
        if(listener!=null)
            listener.update(bytesRead,contentLength,done);
    }
}
