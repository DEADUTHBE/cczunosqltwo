package com.example.searchapi.controller;

import com.example.searchapi.service.MongoHotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OnloadController {
    @Autowired
    private MongoHotServiceImpl mongoHotService;
    @GetMapping("/hotdata")
    public List<Map<String,List>> hotdata(){
        Map<String,List> map=new HashMap<>();
        map.put("bili",mongoHotService.getBiliHot());
        map.put("weibo",mongoHotService.getWeiboHot());
        map.put("zhihu",mongoHotService.getZhihuHot());
        List<Map<String,List>> list = new ArrayList<>();
        list.add(map);
        return list;
    }
}
