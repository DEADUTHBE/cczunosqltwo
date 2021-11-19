import time
import requests
import pymongo
import json

"""
:Author:    iWorld
:Create:    2021/11/19 16:34
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo
:Abstract:  知乎热榜爬虫
"""


class ZhiHuSpider:
    def __init__(self):
        self.myClient = pymongo.MongoClient("mongodb://localhost:27017/")
        self.myDB = self.myClient["hotSearch"]
        self.myCol = self.myDB["ZhiHu"]
        self.myCol.drop()
        self.url = "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/total?limit=50&mobile=true"
        self.questionUrl = "https://www.zhihu.com/question/{}"
        self.headers = {
            'User-Agent': "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) " +
                          "AppleWebKit/605.1.15 (KHTML, like Gecko) " +
                          "Version/13.0.3 Mobile/15E148 Safari/604.1 Edg/96.0.4664.45"
        }

    def getZhiHuHot(self):
        req = requests.get(self.url, headers=self.headers)
        hotSearch = req.text
        hotSearch = json.loads(hotSearch)
        hotSearch = hotSearch["data"]

        hotDict = {}
        rank = 0
        for hot in hotSearch:
            hotDict.clear()
            # 热搜词
            hotDict["word"] = hot["target"]["title"]
            # 热度指数
            hotDict["num"] = hot["detail_text"]
            # 预览文案
            hotDict["text"] = hot["target"]["excerpt"]
            # 预览图url
            hotDict["previewPicUrl"] = hot["children"][0]["thumbnail"]
            # 问题详情页url
            hotDict["questionUrl"] = self.questionUrl.format(hot["target"]["id"])
            # 问题排名
            hotDict["rank"] = rank
            # 时间戳
            hotDict["timestamp"] = time.time()
            # 存入MongoDB
            self.myCol.insert_one(hotDict)
            rank += 1