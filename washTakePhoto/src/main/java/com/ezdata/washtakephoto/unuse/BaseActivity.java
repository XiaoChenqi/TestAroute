package com.ezdata.washtakephoto.unuse;

import android.content.Context;
import android.util.Log;

import com.ezdata.commonlib.utils.ToastUtils;
import com.ezdata.washtakephoto.WashApp;

import java.util.List;

public class BaseActivity {

    protected Context context;
    protected List<String> dataList;

    public BaseActivity(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    /**
     * 这玩意可不能写死啊
     *
     * @return
     */
    public  int getLayout() {
        System.out.println("layoutId:0");
        return 0;
    }

    /**
     * 所有的activity都有下面的bar监听，可以写呀
     */
    public void initListeners() {
        System.out.println("bar的监听:");
    }

    /**
     * 这个方法是异步的，用hanlde的又必须写，可以写在base里
     */
    public void showToast(String msg, int duration) {
        ToastUtils.show(WashApp.getInstance(), msg, duration);
    }


}