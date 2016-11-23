package com.baseinfo.collect.service;

import com.baseinfo.collect.beans.TotalHits;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 搜索服务
 */
@Service("esSearchService")
public class ESSearchService {

    public void setClient(Client client) {
        this.client = client;
    }

    @Autowired
    private Client client;


    public List<Map<String, Object>> queryForObjectNotEq(String content, int from, int size, String esIndexName, TotalHits hitsTimes) {
        SearchRequestBuilder reqBuilder = client.prepareSearch(esIndexName)
                .setTypes("fulltext").setSearchType(SearchType.DEFAULT)
                .setExplain(true);
        QueryStringQueryBuilder queryString = QueryBuilders.queryStringQuery("\""+ content + "\"");
        queryString.field("_all");
        queryString.analyzer("standard");
        //queryString.minimumShouldMatch("10");
        reqBuilder.setQuery(QueryBuilders.boolQuery().should(queryString))
                .setExplain(true);
        if (from >= 0 && size > 0) {
            reqBuilder.setFrom(from).setSize(size);
        }

        SearchResponse resp = reqBuilder.execute().actionGet();
        long total = resp.getHits().getTotalHits();
        hitsTimes.setTotal(total);
        SearchHit[] hits = resp.getHits().getHits();
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : hits) {
            results.add(hit.getSource());
        }
        return results;
    }
}
