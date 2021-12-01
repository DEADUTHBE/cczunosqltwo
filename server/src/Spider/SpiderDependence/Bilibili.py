import json
import time
import requests

from SpiderRootClass import Spider

"""
:Author:    iWorld
:Create:    2021/11/25 21:10
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo
:Abstract:  Bilibili热榜爬虫
"""


class BilibiliSpider(Spider):
    def __init__(self):
        super().__init__("Bilibili")
        self.url = "https://api.bilibili.com/x/web-interface/ranking/v2?rid=0&type=all"

    def getBilibiliHot(self):
        req = requests.get(self.url, headers=self.headers)
        hotSearch = req.text
        hotSearch = json.loads(hotSearch)
        hotSearch = hotSearch["data"]["list"]

        rank = 0
        for hot in hotSearch:
            self.__hot.clear()
            # 热搜词
            self.__hot["title"] = hot["title"]
            # 热度指数
            self.__hot["score"] = hot["score"]
            # 预览文案
            self.__hot["text"] = hot["desc"]
            # 预览图url
            self.__hot["picUrl"] = hot["pic"]
            # 问题详情页url
            self.__hot["detailUrl"] = hot["short_link"]
            # 问题排名
            self.__hot["rank"] = rank
            # 时间戳
            self.__hot["timestamp"] = time.time()
            # 存入MongoDB
            self.myCol.insert_one(self.__hot)
            rank += 1
        print(f"Bilibili\t热搜已导入MongoDB - {time.asctime(time.localtime(time.time()))}")
