package com.ezdata.commonlib.Constants;


/**
 * Created by MSI-PC on 2018/4/4.
 */

public class Constant {
    //public static final String BASE_URL = "http://192.168.3.101:9998";// ip地址改为不可修改，应对某些时候url会变成空的情况
    //public static final String BASE_URL = "http://192.168.3.111:9998";
    //public static String BASE_URL = "http://192.168.88.250:9998";
    public static  String BASE_URL = "http://120.195.205.78:3329";
    //public static final String BASE_URL = "http://192.168.88.250:9994";

    //网络返回正确
    public static final int CODE_OK=200;//


    //列表筛选条件，str字符串，存入shreprefrence用
    public static final String SP_FACE = "SP_FACE";//数据库名称
    public static final String SELECTION_DATA = "SELECTION_DATA";//筛选信息
    public static final String USER_DATA = "USER_DATA";//用户信息
    public static final String BAIDU_FACE_DATA = "BAIDU_FACE_FATA";//百度人脸会用的数据库名称


    /**
     * 密码验证页面的跳转str字符串
     */
    public static final String MACHINE_ID = "machine_id";//哪台洗衣机
    public static final String INTENT_SOURCE = "intent_source";//从哪个页面进行验证页面
    public static final String LOGIN_PAGE = "login_page";//表示从注册页面进行验证
    public static final String PERSONAL_PAGE = "personal_page";//表示从个人中心进行验证
    /**
     * 增量更新app使用的常量
     */
    public static final String APP_PATCH_NAME = "/currentVersion.patch";//下载的补丁的名称
    public static final String LAST_APK_NAME = "/prison.apk";//合成的apk的名称
    /**
     * 百度前后屏跳转常量
     */
    public static final String BAIDU_CAMERA_DIRECTION = "baidu_camera_direction";//前置还是后置摄像头

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }
}
