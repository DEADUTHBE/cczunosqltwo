package com.example.searchapi.service;

import com.example.searchapi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName MongoServieImpl
 * @Description
 * @Author darkgreen
 * @Date 2021/12/3 4:03
 */
@Service
public class MongoServiceImpl implements MongoService{
    @Autowired
    MongoTemplate template;
    @Override
    public void insert(User user) {
        template.insert(user,"key");
    }
}
