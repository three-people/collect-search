package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 用户的ES索引
 */

//五份索引，type为""
@Document(indexName = IndexConstants.PERSONINDEXNAME)
public class UserEsIndex {

}
