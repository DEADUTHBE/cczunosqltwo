package com.example.searchapi.service;

import com.example.searchapi.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MogoSearch
 * @Description
 * @Author darkgreen
 * @Date 2021/11/15 15:05
 */
public interface Search {
    List<User> search(String key);
}
