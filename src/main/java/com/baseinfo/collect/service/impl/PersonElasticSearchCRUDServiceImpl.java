package com.baseinfo.collect.service.impl;

import com.baseinfo.collect.beans.PeopleBean;
import com.baseinfo.collect.service.ElasticSearchCRUDService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("peopleService")
public class PersonElasticSearchCRUDServiceImpl implements ElasticSearchCRUDService<PeopleBean> {
    private static final Logger logger = LoggerFactory.getLogger(PersonElasticSearchCRUDServiceImpl.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public boolean insertIndex(PeopleBean info) {
        IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(info.getId())).withObject(info).build();
        String id = elasticsearchTemplate.index(indexQuery);
        if (StringUtils.isNotBlank(id))
            return true;
        return false;
    }

    @Override
    public boolean insertIndexBatch(List<PeopleBean> infoList) {

        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (PeopleBean index : infoList) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(index.getId())).withObject(index).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return true;
    }

    @Override
    public boolean updateIndex(PeopleBean info) {
        IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(info.getId())).withObject(info).build();
        elasticsearchTemplate.index(indexQuery);
        return true;
    }

    @Override
    public boolean updateIndexBatch(List<PeopleBean> infoList) {
        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (PeopleBean index : infoList) {
            IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(index.getId())).withObject(index).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return true;
    }

    @Override
    public boolean deleteById(String id, Class<PeopleBean> clazz) {
        try {
            elasticsearchTemplate.delete(clazz, id);
            return true;
        } catch (Exception e) {
            logger.error("delete " + clazz + " by id " + id + " error.", e);
            return false;
        }
    }


}
