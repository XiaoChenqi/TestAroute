package com.ezdata.washtakephoto.net;

import com.ezdata.commonlib.net.NetWork;

import retrofit.Retrofit;

/**
 * 用于把 commoblib中的基础Retrofit，和 module的参数组成完成的 Retrofit
 */
public class WashNetWork {

    private static WashNetWork instance;//不带header的请求
    private WashNetApi washNetApi;

    public static WashNetWork getInstance() {
        if (instance == null)
            instance = new WashNetWork();
        return instance;
    }

    /**
     * 用于把 commoblib中的基础Retrofit，和 module的参数组成完成的 Retrofit
     */
    private WashNetWork(){
        Retrofit partRetrofit = NetWork.getInstance().getPartRetrofit();
        washNetApi = partRetrofit.create(WashNetApi.class);
    }

    public WashNetApi getWashNetApi(){

        return washNetApi;
    }
}
