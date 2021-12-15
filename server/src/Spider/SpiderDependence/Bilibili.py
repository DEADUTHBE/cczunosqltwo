import json
import time

import requests

from .SpiderRootClass import Spider

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
        hotDict = dict()
        for hot in hotSearch:
            hotDict.clear()
            # 热搜词
            hotDict["word"] = hot["title"]
            # 热度指数
            hotDict["num"] = hot["score"]
            # 预览文案
            hotDict["text"] = hot["desc"]
            # 预览图url
            hotDict["picUrl"] = hot["pic"]
            # 问题详情页url
            hotDict["detailUrl"] = hot["short_link"]
            # 问题排名
            hotDict["rank"] = rank
            # 时间戳
            hotDict["timestamp"] = time.time()
            # 存入MongoDB
            self.myCol.insert_one(hotDict)
            rank += 1
        print(f"Bilibili\t热搜已导入MongoDB - {time.asctime(time.localtime(time.time()))}")
