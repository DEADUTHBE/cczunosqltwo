package com.example.searchapi.controller;

import com.example.searchapi.service.HotSearchService;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class HotSearchController {
    @Autowired
    private HotSearchService hotSearchService;

    @GetMapping("/hot")
    @ResponseBody
    public List<Map<String,Object>> hotsearch(@RequestParam String keyword,@RequestParam(required = false,defaultValue = "1") String page,HttpServletResponse resp) throws IOException {
//        answer_count: 1477
//        articles_count: 19
//        avatar_url: "https://pic4.zhimg.com/7110e47e9674568d087befc770575b54_is.jpg"
//        business: {avatar_url: "https://pic4.zhimg.com/e82bab09c_is.jpg", name: "计算机软件", id: "19619368", excerpt: "徼",…}
    //        avatar_url: "https://pic4.zhimg.com/e82bab09c_is.jpg"
    //        excerpt: "徼"
    //        id: "19619368"
    //        introduction: "徼"
    //        name: "计算机软件"
    //        type: "topic"
    //        url: "https://www.zhihu.com/topics/19619368"
//        description: "国内每年有400万左右 人 新患癌症。每年有300万左右 人 死于癌症。每年全部死亡人口中约有五分之一 死于癌症。国内目前共有1600万左右的癌症病患 每100中国人中就有超过1个人患癌，癌症是人类生存的大患，究竟何时能够彻底治愈？若非意外，你的家庭成员最终可能有30%的概率最终会患癌。"
//        educations: [{,…}]
//          0: {,…}
//            diploma: 0
    //        major: {avatar_url: "https://pic4.zhimg.com/e82bab09c_is.jpg", name: "还在转型期", id: "", excerpt: "",…}
    //        avatar_url: "https://pic4.zhimg.com/e82bab09c_is.jpg"
    //        excerpt: ""
    //        id: ""
    //        introduction: ""
    //        name: "还在转型期"
    //        type: "topic"
    //        url: ""
//        school: {avatar_url: "https://pic4.zhimg.com/e82bab09c_is.jpg", name: "AMD粉", id: "", excerpt: "",…}
//        avatar_url: "https://pic4.zhimg.com/e82bab09c_is.jpg"
//        excerpt: ""
//        id: ""
//        introduction: ""
//        name: "AMD粉"
//        type: "topic"
//        url: ""
//        employments: [{,…}]
//        0: {,…}
//        company: {avatar_url: "https://pic1.zhimg.com/de0a28d75efd403ead9855a28150a4ca_is.jpg", name: "小公司",…}
//        avatar_url: "https://pic1.zhimg.com/de0a28d75efd403ead9855a28150a4ca_is.jpg"
//        excerpt: ""
//        id: "19614575"
//        introduction: ""
//        name: "小公司"
//        type: "topic"
//        url: "https://www.zhihu.com/topics/19614575"
//        job: {avatar_url: "https://pic4.zhimg.com/e82bab09c_is.jpg", name: "一线码农", id: "", excerpt: "",…}
//        avatar_url: "https://pic4.zhimg.com/e82bab09c_is.jpg"
//        excerpt: ""
//        id: ""
//        introduction: ""
//        name: "一线码农"
//        type: "topic"
//        url: ""
//        favorited_count: 691
//        follower_count: 431
//        following_count: 772
//        gender: 1
//        headline: "起先要命的是癌症，某天癌症可治愈了，要命的变成医药费。"
//        locations: [{avatar_url: "https://pic1.zhimg.com/ea5e67bb682fb5822bff17898a6cfede_is.jpg", name: "长三角",…}]
//        0: {avatar_url: "https://pic1.zhimg.com/ea5e67bb682fb5822bff17898a6cfede_is.jpg", name: "长三角",…}
//        avatar_url: "https://pic1.zhimg.com/ea5e67bb682fb5822bff17898a6cfede_is.jpg"
//        excerpt: ""
//        id: "19603110"
//        introduction: ""
//        name: "长三角"
//        type: "topic"
//        url: "https://www.zhihu.com/topics/19603110"
//        name: "ggff ss"
//        thanked_count: 735
//        url_token: "ggff-ss"
//        user_type: "people"
//        voteup_count: 4884 投票计数

        String[] Scopes=new String[]{"description","headline","name"};//根据前端定义
        Map res=hotSearchService.hotSearchbyScope(keyword,page,Scopes,"answer_count");

        Iterator<List<Map<String, Object>>> iter = res.keySet().iterator();
        List a=iter.next();Integer num=(Integer)res.get(a);
        Integer pageNum = (int)Math.ceil(num/20.0);
        Integer rest=num%20;
        Cookie cookie = new Cookie("pagenum", pageNum.toString());
        Cookie cookie2 = new Cookie("rest", rest.toString());
        resp.addCookie(cookie);
        resp.addCookie(cookie2);
        return a;
    }
@GetMapping("/searchhot")
    public ModelAndView hotsearch2(@RequestParam String keyword, @RequestParam(required = false,defaultValue = "1") String page) throws IOException {
        if(keyword==""){
            ModelAndView modelAndViewIndex=new ModelAndView("redirect:/");
            return modelAndViewIndex;
        }
        keyword=URLEncoder.encode(keyword, "utf-8");
        page =URLEncoder.encode(page, "utf-8");
        ModelAndView modelAndView=new ModelAndView("redirect:/second.html?keyword="+keyword+"&page="+page);

        return modelAndView;
    }



}
