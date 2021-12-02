import json
import time

import requests

from .SpiderRootClass import Spider

"""
:Author:    iWorld
:Create:    2021/11/19 16:34
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo
:Abstract:  知乎热榜爬虫
"""


class ZhiHuSpider(Spider):
    def __init__(self):
        super().__init__("ZhiHu")
        self.url = "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/total?limit=50&mobile=true"
        self.questionUrl = "https://www.zhihu.com/question/{}"

    def getZhiHuHot(self):
        req = requests.get(self.url, headers=self.headers)
        hotSearch = req.text
        hotSearch = json.loads(hotSearch)
        hotSearch = hotSearch["data"]
        hotDict = dict()

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
            hotDict["PicUrl"] = hot["children"][0]["thumbnail"]
            # 问题详情页url
            hotDict["detailUrl"] = self.questionUrl.format(hot["target"]["id"])
            # 问题排名
            hotDict["rank"] = rank
            # 时间戳
            hotDict["timestamp"] = time.time()
            # 存入MongoDB
            self.myCol.insert_one(hotDict)
            rank += 1
        print(f"知乎\t热搜已导入MongoDB - {time.asctime(time.localtime(time.time()))}")
