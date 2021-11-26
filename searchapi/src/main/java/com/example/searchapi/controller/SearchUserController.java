package com.example.searchapi.controller;

import com.example.searchapi.pojo.User;
import com.example.searchapi.service.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SearchController
 * @Description
 * @Author darkgreen
 * @Date 2021/11/15 14:13
 */
@Deprecated
@RestController
public class SearchUserController {
    @Autowired
    @Qualifier("searchImpl")
    Search search;
    @GetMapping("/s")
    public List<User> search(@RequestParam("key") String key){

        List<User> searchReasult = this.search.search(key);
        return searchReasult;
    }
}
