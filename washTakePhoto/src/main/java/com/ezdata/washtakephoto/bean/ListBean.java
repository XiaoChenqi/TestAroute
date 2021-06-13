package com.ezdata.washtakephoto.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListBean<T> implements Serializable {

    public int total;

    @Expose
    @SerializedName("list")
    public List<T> list;//某某实体类的列表
}
