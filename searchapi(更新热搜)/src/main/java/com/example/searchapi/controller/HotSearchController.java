package com.example.searchapi.controller;

import com.example.searchapi.service.HotSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class HotSearchController {
    @Autowired
    private HotSearchService hotSearchService;

    @GetMapping("/hot")
    public List<Map<String, Object>> hotsearch(@RequestParam String keyword,@RequestParam(required = false,defaultValue = "1") Integer page) throws IOException {
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
        String[] Scopes=new String[]{"description","headline","educations.major.name"};//根据前端定义
        return hotSearchService.hotSearchbyScope(keyword,page,Scopes,"voteup_count");
    }

}
