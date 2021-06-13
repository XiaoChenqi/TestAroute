package com.ezdata.washtakephoto.bean;

import java.util.List;

public class WashTaskDetail {
    public String name;//衣服种类名称
    //public List<String> codes;
    public int enterDbNumber;
    public int needTaskNumber;

    public List<clothDetail> detailLists;


    public class clothDetail{
        public String code;
        public String path;
    }
}
