package com.baseinfo.collect.service.impl;

import com.baseinfo.collect.beans.EmployerBean;
import com.baseinfo.collect.beans.HouseBean;
import com.baseinfo.collect.service.ElasticSearchCRUDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiming on 2016-11-19.
 */
public class HouseElasticSearchServiceImpl implements ElasticSearchCRUDService<HouseBean> {

    private static final Logger logger = LoggerFactory.getLogger(HouseElasticSearchServiceImpl.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public boolean insertIndex(HouseBean info) {
        IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(info.getId())).withObject(info).build();
        elasticsearchTemplate.index(indexQuery);
        return true;
    }

    @Override
    public boolean insertIndexBatch(List<HouseBean> infoList) {
        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (HouseBean index : infoList) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(index.getId())).withObject(index).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return true;
    }

    @Override
    public boolean updateIndex(HouseBean info) {
        IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(info.getId())).withObject(info).build();
        elasticsearchTemplate.index(indexQuery);
        return true;
    }

    @Override
    public boolean updateIndexBatch(List<HouseBean> infoList) {
        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (HouseBean index : infoList) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(index.getId())).withObject(index).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return true;
    }

    @Override
    public boolean deleteById(String id, Class<HouseBean> clazz) {
        try {
            elasticsearchTemplate.delete(clazz, id);
            return true;
        } catch (Exception e) {
            logger.error("delete " + clazz + " by id " + id + " error.", e);
            return false;
        }
    }
}
