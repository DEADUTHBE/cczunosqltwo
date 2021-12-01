package com.example.searchapi.service;

import com.example.searchapi.pojo.BiliHotRecord;
import com.example.searchapi.pojo.WeiboHotRecord;
import com.example.searchapi.pojo.ZhihuHotRecord;


import java.util.List;

public interface mongoHotService {
    List<BiliHotRecord> getBiliHot();
    List<WeiboHotRecord> getWeiboHot();
    List<ZhihuHotRecord> getZhihuHot();
}
