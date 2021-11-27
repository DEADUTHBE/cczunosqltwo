import time
import requests
import pymongo
import json

"""
:Author:    iWorld
:Create:    2021/11/25 21:10
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo
:Abstract:  Bilibili热榜爬虫
"""


class BilibiliSpider:
    def __init__(self):
        self.myClient = pymongo.MongoClient("mongodb://localhost:27017/")
        self.myDB = self.myClient["hotSearch"]
        self.myCol = self.myDB["Bilibili"]
        self.myCol.drop()
        self.url = "https://api.bilibili.com/x/web-interface/ranking/v2?rid=0&type=all"
        self.headers = {
            'User-Agent': "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) " +
                          "AppleWebKit/605.1.15 (KHTML, like Gecko) " +
                          "Version/13.0.3 Mobile/15E148 Safari/604.1 Edg/96.0.4664.45"
        }

    def getBilibiliHot(self):
        req = requests.get(self.url, headers=self.headers)
        hotSearch = req.text
        hotSearch = json.loads(hotSearch)
        hotSearch = hotSearch["data"]["list"]

        hotDict = {}
        rank = 0
        for hot in hotSearch:
            hotDict.clear()
            # 热搜词
            hotDict["word"] = hot["title"]
            # 热度指数
            hotDict["num"] = hot["score"]
            # 预览文案
            hotDict["text"] = hot["desc"]
            # 预览图url
            hotDict["previewPicUrl"] = hot["pic"]
            # 问题详情页url
            hotDict["questionUrl"] = hot["short_link"]
            # 问题排名
            hotDict["rank"] = rank
            # 时间戳
            hotDict["timestamp"] = time.time()
            # 存入MongoDB
            self.myCol.insert_one(hotDict)
            rank += 1
        print(f"Bilibili\t热搜已导入MongoDB - {time.asctime(time.localtime(time.time()))}")
