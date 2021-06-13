package com.ezdata.washtakephoto.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoDetail {
    @Expose
    @SerializedName("file")
    public String picUrl;//图片地址
    @Expose
    @SerializedName("list")
    public List<ClothGroup> clothGroups;//玩具清单列表

    public String burden;//负载量 empty，few, mid, many，分别表示空、少、中、多；

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<ClothGroup> getClothes() {
        return clothGroups;
    }

    public void setClothGroups(List<ClothGroup> clothGroups) {
        this.clothGroups = clothGroups;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public class ClothGroup {
        public String name;
        public String id;
        public String label;
        public int num;//前台添加

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
