package com.baseinfo.collect.common;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 索引相关的常量
 */
public class IndexConstants {
    //人口相关的索引
    public static final String PERSONINDEXNAME = "people_index2";
    //房屋相关的索引
    public static final String HOUSEINDEXNAME = "house_index";
    //位置相关索引
    public static final String PLACEINDEXNAME = "place_index";
    //雇佣相关的索引
    public static final String EMPLOYERINDEXNAME = "employer_index";
    //监控相关的索引
    public static final String CAMERAINDEXNAME = "camera_index";

    //public static final String HOUSEINDEXNAME = "house_index";

    public static final String AnalyserName = "ik_max_word";
}
