package com.example.searchapi;

import com.example.searchapi.service.Search;
import com.example.searchapi.service.SearchImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchapiApplicationTests {
    @Autowired
    @Qualifier("searchImpl")
    Search search ;
    @Test
    // 测试
    void contextLoads() {
        search.search("雷");
    }

}
