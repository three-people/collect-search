package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 雇佣关系相关的ES索引实体
 */
@Document(indexName = IndexConstants.EMPLOYERINDEXNAME)
public class EmployerEsIndex {
}
