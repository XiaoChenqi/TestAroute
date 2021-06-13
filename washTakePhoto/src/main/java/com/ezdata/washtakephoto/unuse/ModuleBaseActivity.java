package com.ezdata.washtakephoto.unuse;

import android.content.Context;

import java.util.List;

public abstract class ModuleBaseActivity extends BaseActivity {

    public ModuleBaseActivity(Context context, List<String> dataList) {
        super(context, dataList);
    }

    @Override
    public int getLayout() {
        if(ifLogin()) {
            //return super.getLayout();
            return 4;//我就返回0了你咋地
        }else{
            return 0 ;
        }
    }

    @Override
    public void initListeners() {
        if(ifLogin()){
            super.initListeners();
            System.out.println("我监听玩还要赶别的事情:");
        }

    }

    public void showDataList(){
        if(ifLogin()) {
            List<String> temp = super.dataList;
            for (String xcq : temp) {
                System.out.println(xcq);
            }
        }
    }

    /**
     * 这个应用中的每隔 activity都有一个判断是否登录的方法
     */
    public abstract boolean ifLogin();
}
