package com.baseinfo.collect.service.impl;

import com.baseinfo.collect.beans.PersonEsIndex;
import com.baseinfo.collect.service.ElasticSearchCRUDService;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.List;


public class PersonElasticSearchCRUDServiceImpl implements ElasticSearchCRUDService<PersonEsIndex> {
    private static final Logger logger = LoggerFactory.getLogger(PersonElasticSearchCRUDServiceImpl.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private Client esClient;

    @Override
    public boolean update(List<PersonEsIndex> taskInfoList) {
        return false;
    }

    @Override
    public boolean insertOrUpdateTaskInfo(List<PersonEsIndex> taskInfoList) {
        return false;
    }

    @Override
    public boolean insertOrUpdateNewsInfo(PersonEsIndex newsInfo) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
