package com.example.searchapi;

import com.example.searchapi.service.Search;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SearchapiApplicationTests {
    @Autowired
    @Qualifier("searchImpl")
    private Search search ;
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;
    @Test
    // 测试
    void contextLoads() {
        search.search("雷");
    }
    @Test
    public void test(){
    }
}
