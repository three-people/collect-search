package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = IndexConstants.PERSONINDEXNAME)
public class UserEsIndex {

}
