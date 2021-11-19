import requests
import pymongo
import json

"""

:Author:    iWorld
:Create:    2021/11/19 15:30
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo

"""


class weiboSpider:
    '''
    通过request库以get方式获取微博热搜的JSON

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
        self.myclient = pymongo.MongoClient("mongodb://localhost:27017/")
        self.mydb = self.myclient["hotSearch"]
        self.mycol = self.mydb["weibo"]
        self.mycol.drop()
        self.url = "https://weibo.com/ajax/statuses/hot_band"
        self.headers = {
            'User-Agent': "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36"
        }

    def getHotSearch(self):
        req = requests.get(self.url)
        hotSearch = req.text
        hotSearch = json.loads(hotSearch)
        hotSearch = hotSearch["data"]["band_list"]

        # 存入MongoDB
        for item in hotSearch:
            self.mycol.insert_one(item)
