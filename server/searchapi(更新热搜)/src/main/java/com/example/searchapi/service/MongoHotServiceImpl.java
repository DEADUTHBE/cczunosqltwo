package com.example.searchapi.service;

import com.example.searchapi.pojo.BiliHotRecord;
import com.example.searchapi.pojo.WeiboHotRecord;
import com.example.searchapi.pojo.ZhihuHotRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.annotation.Documented;
import java.util.List;
@Service
public class MongoHotServiceImpl implements mongoHotService{
    @Resource
    MongoTemplate mongoTemplate;
    @Override
    public List<BiliHotRecord> getBiliHot() {
        Query query=new Query(Criteria.where("rank").gte(0));
        List<BiliHotRecord> biliHotRecords = mongoTemplate.find(query, BiliHotRecord.class, "Bilibili");
        return biliHotRecords;
    }

    @Override
    public List<WeiboHotRecord> getWeiboHot() {
        Query query=new Query(Criteria.where("rank").gte(0));
        List<WeiboHotRecord> weiboHotRecords = mongoTemplate.find(query, WeiboHotRecord.class, "WeiBo");
        return weiboHotRecords;
    }

    @Override
    public List<ZhihuHotRecord> getZhihuHot() {
        Query query=new Query(Criteria.where("rank").gte(0));
        List<ZhihuHotRecord> zhihuHotRecords = mongoTemplate.find(query, ZhihuHotRecord.class, "ZhiHu");
        return zhihuHotRecords;
    }
}
