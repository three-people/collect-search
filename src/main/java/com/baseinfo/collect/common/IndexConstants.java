package com.baseinfo.collect.common;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 索引相关的常量
 */
public class IndexConstants {
    //人口相关的索引
    public static final String PERSONINDEXNAME = "people_index";
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
    
    public static String getIndexByType(String type) {
    	switch(type) {
    	case "people": return PERSONINDEXNAME;
    	case "house": return HOUSEINDEXNAME;
    	case "place": return PLACEINDEXNAME;
    	case "employer": return EMPLOYERINDEXNAME;
    	case "camera": return CAMERAINDEXNAME;
    	default : return null;
    	}
    }
}
