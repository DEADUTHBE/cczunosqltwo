package com.example.searchapi.controller;

import com.example.searchapi.service.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * @ClassName ElasticSearchController
 * @Description
 * @Author darkgreen
 * @Date 2021/11/18 17:08
 */
@RestController
public class ElasticSearchController {
    @Autowired
    private ElasticService elasticService;

    @GetMapping("/search")
    public List<Map<String,Object>> search(@RequestParam("key") String key, @RequestParam(value = "page",defaultValue = "0") int page, HttpServletResponse resp) throws IOException {
        int num = (int) elasticService.getResultNum(key);
        Integer pageNum = (int)Math.ceil(num/20.0);
        System.out.println(num);
        // 返回前端搜索结果的页数
        System.out.println(pageNum);
        Cookie cookie = new Cookie("num", pageNum.toString());
        resp.addCookie(cookie);
        return elasticService.search(key,page);
    }
}
