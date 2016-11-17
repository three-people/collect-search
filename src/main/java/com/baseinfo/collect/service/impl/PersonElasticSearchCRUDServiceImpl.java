package com.baseinfo.collect.service.impl;

import com.baseinfo.collect.beans.PersonEsIndex;
import com.baseinfo.collect.service.ElasticSearchCRUDService;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class PersonElasticSearchCRUDServiceImpl implements ElasticSearchCRUDService<PersonEsIndex> {
    private static final Logger logger = LoggerFactory.getLogger(PersonElasticSearchCRUDServiceImpl.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private Client esClient;

    @Override
    public boolean insertIndex(PersonEsIndex info) {
        IndexQuery indexQuery = new IndexQueryBuilder().withId(info.getPersonID()).withObject(info).build();
        elasticsearchTemplate.index(indexQuery);
        return true;
    }

    @Override
    public boolean insertIndexBatch(List<PersonEsIndex> infoList) {

        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (PersonEsIndex index : infoList) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(index.getPersonID()).withObject(index).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return true;
    }

    @Override
    public boolean updateIndex(PersonEsIndex info) {
        IndexQuery indexQuery = new IndexQueryBuilder().withId(info.getPersonID()).withObject(info).build();
        elasticsearchTemplate.index(indexQuery);
        return true;
    }

    @Override
    public boolean updateIndexBatch(List<PersonEsIndex> infoList) {
        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (PersonEsIndex index : infoList) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(index.getPersonID()).withObject(index).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return true;
    }

    @Override
    public boolean deleteById(String id, Class<PersonEsIndex> clazz) {
        try {
            elasticsearchTemplate.delete(clazz, id);
            return true;
        } catch (Exception e) {
            logger.error("delete " + clazz + " by id " + id + " error.", e);
            return false;
        }
    }


}
