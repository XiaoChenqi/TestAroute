package com.ezdata.washtakephoto.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * {
 "status": 200,
 "message": "success",
 "data": [
 {
 "task_id": "8e663a96b1f548219ff27d65ec0960ef",
 "classify_names": "衬衫,裤子",
 "plan_pic_number": 10,
 "take_pic_number": 32,
 "status": "Finish",
 "detail": {
 "ids": [
 "621378a7d4674cc4a7f5be88084eb411"
 ],
 "codes": [
 "2"
 ]
 },
 "photos": null
 }
 ]
 }
 */
public class WashTask implements Serializable {
    @Expose
    @SerializedName("task_id")
    public String taskId;
    public String machine_code;
    public String classify_names;
    public String number_desc;
    @Expose
    @SerializedName("plan_pic_number")
    public int targetNum;
    public int take_pic_number;
    public String status;
    @Expose
    @SerializedName("detail")
    public cloth detail;//衣服
    public String photos;
//
    public class cloth implements Serializable{
        public List<String> ids;
        public List<String> codes;//衣服的编号，cs01之类的
    }
}
