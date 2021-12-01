package com.example.searchapi.service;

import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ElasticService
 * @Description
 * @Author darkgreen
 * @Date 2021/11/18 16:42
 */
@Service
public class ElasticService {
    @Autowired
    private RestHighLevelClient client;
//    @Autowired
//    MongoTemplate mongoTemplate;

    public List<Map<String, Object>> search(String key, int page) throws IOException {
        if (page <= 0) {
            page = 1;
        }
        System.out.println(key);
        List<Map<String, Object>> list = new LinkedList<>();

        //1
        SearchRequest searchRequest = new SearchRequest();

        //2
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from((page-1)*20);
        searchSourceBuilder.size(20);

        //3
        MatchQueryBuilder query = QueryBuilders.matchQuery("description",key);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.query(query);

        //4
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : search.getHits().getHits()) {
            System.out.println(hit);
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            list.add(sourceAsMap);
        }
        return list;
    }
    public long getResultNum(String key) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder query = QueryBuilders.matchQuery("description",key);
        sourceBuilder.query(query);
        searchRequest.source(sourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        TotalHits totalHits = hits.getTotalHits();
        return totalHits.value;
    }


}
