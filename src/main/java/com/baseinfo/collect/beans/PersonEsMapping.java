package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;

/**
 * Person的Mapping
 */
@Mapping
public class PersonEsMapping {
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String personID;

    @Field(type = FieldType.String,index = FieldIndex.not_analyzed, store = true)
    private String sex;

    @Field(type = FieldType.String,index = FieldIndex.not_analyzed, store = true)
    private String Uname;

    @Field(type = FieldType.String,index = FieldIndex.analyzed, analyzer = IndexConstants.AnalyserName ,store = true,searchAnalyzer = "ik_max_word")
    private String addrass;

}
