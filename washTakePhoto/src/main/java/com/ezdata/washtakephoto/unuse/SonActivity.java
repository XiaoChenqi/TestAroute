package com.ezdata.washtakephoto.unuse;

import com.ezdata.washtakephoto.WashApp;

import java.util.ArrayList;
import java.util.List;

public class SonActivity
        //extends ModuleBaseActivity
        {

//    public SonActivity(Context context, List<String> dataList) {
//        super(context, dataList);
//    }
    public static void main(String args[]){
        List<String> tempList = new ArrayList<>();
        tempList.add("大蘑菇");
        tempList.add("小蘑菇");
        tempList.add("水蜜桃");
        tempList.add("奶奶");
        tempList.add("外婆");
        ModuleBaseActivity testActivity = new  ModuleBaseActivity(WashApp.getInstance(), tempList) {
            @Override
            public boolean ifLogin() {
                System.out.print("判断下是否登录:");
                return true;
            }
        };
        testActivity.showDataList();
        testActivity.initListeners();
        int n = testActivity.getLayout();
        System.out.print("n:"+n);
    }
}
