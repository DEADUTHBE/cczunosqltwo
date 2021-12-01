import json
import time
import requests
from SpiderRootClass import Spider

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

        rank = 0
        for hot in hotSearch:
            self.__hot.clear()
            # 热搜词
            self.__hot["word"] = hot["target"]["title"]
            # 热度指数
            self.__hot["num"] = hot["detail_text"]
            # 预览文案
            self.__hot["text"] = hot["target"]["excerpt"]
            # 预览图url
            self.__hot["previewPicUrl"] = hot["children"][0]["thumbnail"]
            # 问题详情页url
            self.__hot["questionUrl"] = self.questionUrl.format(hot["target"]["id"])
            # 问题排名
            self.__hot["rank"] = rank
            # 时间戳
            self.__hot["timestamp"] = time.time()
            # 存入MongoDB
            self.myCol.insert_one(self.__hot)
            rank += 1
        print(f"知乎\t热搜已导入MongoDB - {time.asctime(time.localtime(time.time()))}")
