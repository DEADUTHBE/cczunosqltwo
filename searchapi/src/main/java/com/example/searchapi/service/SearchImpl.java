package com.example.searchapi.service;

import com.example.searchapi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @ClassName MongoService
 * @Description
 * @Author darkgreen
 * @Date 2021/11/15 14:14
 */
@Service("searchImpl")
public class SearchImpl implements Search {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> searchDescription(String description) {
        String str = "^.*" + description + ".*$";
        Pattern pattern = Pattern.compile(str);
        Query query = new Query(Criteria.where("description").regex(pattern));
        List<User> zhihuuserinfo = mongoTemplate.find(query, User.class, "zhihuuserinfo");
        return zhihuuserinfo;
    }

    public List<User> searchHeadline(String headline) {
        String str = "^.*" + headline + ".*$";
        Pattern pattern = Pattern.compile(str);
        Query query = new Query(Criteria.where("headline").regex(pattern));
        List<User> zhihuuserinfo = mongoTemplate.find(query, User.class, "zhihuuserinfo");
        return zhihuuserinfo;
    }

    public List<User> searchNames(String name) {
        String str = "^.*" + name + ".*$";
        Pattern pattern = Pattern.compile(str);
        Query query = new Query(Criteria.where("name").regex(pattern));
        List<User> zhihuuserinfo = mongoTemplate.find(query, User.class, "zhihuuserinfo");
        return zhihuuserinfo;
    }

    @Override
    public List<User> search(String key) {
        List<User> users1 = searchDescription(key);
        List<User> users2 = searchHeadline(key);
        List<User> users3 = searchNames(key);
        List<User> allUser = new ArrayList<User>();
        if (users1 != null) {
            for (User user : users1) {
                allUser.add(user);
            }
        }
        if (users2 != null) {
            for (User user : users2) {
                if (allUser.contains(user))
                    continue;
                allUser.add(user);
            }
        }
        if (users3 != null) {
            for (User user : users3) {
                if (allUser.contains(user))
                    continue;
                allUser.add(user);
            }
        }
        System.out.println(users1.size());
        System.out.println(users2.size());
        System.out.println(users3.size());
        System.out.println(allUser.size());

        return allUser;
    }
}
