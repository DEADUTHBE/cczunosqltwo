package com.example.searchapi.service;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Service
public class HotSearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    //根据条件降序排序
    public List<Map<String, Object>> search1(String key, int page,String[] scope) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        //1
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        设置matchquerybuilder条件
        MultiMatchQueryBuilder query = QueryBuilders.multiMatchQuery(key,scope);
//        允许搜索的超时时长
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.query(query);//添加匹配查询
        searchSourceBuilder.sort(new FieldSortBuilder("answer_count").order(SortOrder.DESC));
//        分页
        searchSourceBuilder.from((page-1)*20);
        searchSourceBuilder.size(20);
        //4
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 返回结果
        for (SearchHit hit : search.getHits().getHits()) {
            System.out.println(hit);
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            list.add(sourceAsMap);
        }
        return list;
    }
}
