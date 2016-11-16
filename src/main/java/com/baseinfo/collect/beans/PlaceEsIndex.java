package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 位置相关的ES索引
 */
@Document(indexName = IndexConstants.PLACEINDEXNAME)
public class PlaceEsIndex {
}
