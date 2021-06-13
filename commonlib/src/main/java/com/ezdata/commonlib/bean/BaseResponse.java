package com.ezdata.commonlib.bean;


import com.ezdata.commonlib.Constants.Constant;

/**
 * Description：网络返回数据基类,这个根据情况可以修改
 * Created by：Kyle
 * Date：2017/2/6
 */
public class BaseResponse<T> {
//    @SerializedName("result")
    private int status;
    private String message;
    private T data;

    public int getCode() {
        return status;
    }

    public void setCode(int code) {
        this.status = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public boolean isOk(){
        return status == Constant.CODE_OK;
    }
}
