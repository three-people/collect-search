package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 房屋相关的ES索引实体
 */

@Document(indexName = IndexConstants.HOUSEINDEXNAME)
public class HouseEsIndex {

}
