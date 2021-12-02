package com.example.searchapi.service;

import com.example.searchapi.pojo.User;

/**
 * @ClassName MongoService
 * @Description
 * @Author darkgreen
 * @Date 2021/12/3 4:01
 */
// 适配器模式
public interface MongoService {
    default void insert(User user){}
    default void delete(){}
    default void update(){}
    default void query(){}
}
