import json
import time
import requests
from urllib.parse import quote
from SpiderRootClass import Spider

"""

:Author:    iWorld
:Create:    2021/11/19 15:30
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo
:Abstract:  微博爬虫, 通过request库以get方式获取微博热搜的JSON
"""


class WeiboSpider(Spider):
    '''
    返回的数据结构:
    {
        "ok": 1,
        "http_code": 200,
        "data": {
            "band_list": [
                {
                    "is_new": 1,                                    # 话题右侧是否加上 "新" 标识
                    "star_word": 0,
                    "word": "西安发现加拿大一枝黄花",                   # 热搜词
                    "category": "社会新闻",                          # 热搜分类
                    "num": 1778139,                                # 热度指数
                    "subject_querys": "",
                    "flag": 1,
                    "icon_desc": "新",                             # 话题右侧的标识
                    "raw_hot": 1778139,
                    "note": "西安发现加拿大一枝黄花",                  # 热度指数
                    "emoticon": "",
                    "ad_info": "",
                    "icon_desc_color": "#ff3852",
                    "realpos": 1,
                    "topic_flag": 1,
                    "mid": "4705195504962404",
                    "fun_word": 0,
                    "onboard_time": 1637305343,
                    "rank": 0,
                    "mblog": {
                        "text": "",                                # 预览文案
                        "pic_ids": [
                            "722ed599ly1gwk9aymbxej20m80go41s",
                            "722ed599ly1gwk9ayexzij20m80cu75k"
                        ],
                        "pic_num": 2,
                        "topic_struct": [
                            {
                                "title": "",
                                "topic_url": "",
                                "topic_title": "西安发现加拿大一枝黄花",
                                "is_invalid": 0
                            }
                        ]
                    }
                }
            ]
        }
    }
    '''

    def __init__(self):
        super().__init__("WeiBo")
        self.url = "https://weibo.com/ajax/statuses/hot_band"
        self.detail = "https://s.weibo.com/weibo?q=%23{}%23"

    def getWeiboHot(self):
        req = requests.get(self.url, headers=self.headers)
        hotSearch = req.text
        hotSearch = json.loads(hotSearch)
        hotSearch = hotSearch["data"]["band_list"]
        rank = 0
        for hot in hotSearch:
            self.__hot.clear()
            # 热搜词
            self.__hot["word"] = hot["word"]
            # 热度指数
            self.__hot["num"] = hot["num"]
            # 预览文案
            self.__hot["text"] = hot["mblog"]["text"]
            # 预览图url
            self.__hot["picUrl"] = hot["mblog"]["page_info"]["page_pic"]
            # 问题详情页url
            self.__hot["detailUrl"] = self.detail.format(quote(hot["word"]))
            # 问题排名
            self.__hot["rank"] = rank
            # 时间戳
            self.__hot["timestamp"] = time.time()
            # 存入MongoDB
            self.myCol.insert_one(self.__hot)

            rank += 1
        print(f"微博\t热搜已导入MongoDB - {time.asctime(time.localtime(time.time()))}")
