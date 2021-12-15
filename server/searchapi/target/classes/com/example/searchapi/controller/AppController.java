package com.example.searchapi.controller;

import com.example.searchapi.pojo.User;
import com.example.searchapi.service.MongoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName AppController
 * @Description
 * @Author darkgreen
 * @Date 2021/12/1 15:48
 */
@Controller
public class AppController {
    @Autowired
    MongoServiceImpl mongoService;
    @RequestMapping({"/","/index"})
    public String toIndex() {
        return "index.html";
    }

    @GetMapping("/search")
    public String toSearch(@RequestParam(value = "key", defaultValue = "") String key) {
        // 执行查找 收集用户查找信息 并且不影响主线程速度

        if (key.equals("")){
            return "redirect:/";
        }
        new Thread(() -> {
            User user = new User(key,Long.toString(System.currentTimeMillis()));
            mongoService.insert(user);
        }).start();
        return "search.html";
    }
}
