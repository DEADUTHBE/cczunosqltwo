package com.example.searchapi.service;

import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    @Autowired
    MongoTemplate mongoTemplate;

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
        searchSourceBuilder.from((page - 1) * 20);
        searchSourceBuilder.size(20);
        searchSourceBuilder.sort("follower_count", SortOrder.DESC);

        //3
        QueryBuilder query = QueryBuilders.boolQuery()
                .should(QueryBuilders.multiMatchQuery(key, "description", "headline", "name"));
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        String[] includes = {"avatar_url", "answer_count", "follower_count", "highlight", "name", "description", "headline", "url_token"};
        searchSourceBuilder.query(query).fetchSource(includes, null);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder
                .field("name").preTags("<em>").postTags("</em>")
                .field("headline").preTags("<em>").postTags("</em>");
        searchSourceBuilder.highlighter(highlightBuilder);

        //4
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit hit : search.getHits().getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField headline = highlightFields.get("headline");
            HighlightField name = highlightFields.get("name");
            Text[] fragmentsHeadline = null;
            Text[] fragmentsName = null;
            StringBuilder sbDescription = new StringBuilder("");
            StringBuilder sbHeadline = new StringBuilder("");
            StringBuilder sbName = new StringBuilder("");
            if (headline != null) {
                fragmentsHeadline = headline.getFragments();
                for (Text fragment : fragmentsHeadline) {
                    sbHeadline.append(fragment.toString());
                }
                map.replace("headline", sbHeadline.toString());
            }
            if (name != null) {
                fragmentsName = name.getFragments();
                for (Text text : fragmentsName) {
                    sbName.append(text.toString());
                }
                map.replace("name", sbName.toString());
            }
            list.add(map);
        }
        return list;
    }

    public long getResultNum(String key) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder query = QueryBuilders.boolQuery().
                should(QueryBuilders.multiMatchQuery(key, "description", "headline", "name"));
        sourceBuilder.query(query);
        searchRequest.source(sourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        TotalHits totalHits = hits.getTotalHits();
        return totalHits.value;
    }
}
