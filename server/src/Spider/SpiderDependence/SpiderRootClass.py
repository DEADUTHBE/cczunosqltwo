import pymongo

"""
:Author:    iWorld
:Create:    2021/11/30 20:11
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo
:Abstract:  Spider 主类
"""


class Spider:
    # 存放热搜字段
    """
    热搜字段项:
         title      热搜条目
         score      热搜指数
         text       热搜内容
         picUrl     预览图
         detailUrl  详情页
         rank       热搜排名
         timeStamp  时间戳
    """
    __hot = {}

    def __init__(self, DBName):
        self.myClient = pymongo.MongoClient("mongodb://localhost:27017/")
        self.myDB = self.myClient["hotSearch"]
        self.myCol = self.myDB[f"{DBName}"]
        self.myCol.drop()
        self.headers = {
            'User-Agent': "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) " +
                          "AppleWebKit/605.1.15 (KHTML, like Gecko) " +
                          "Version/13.0.3 Mobile/15E148 Safari/604.1 Edg/96.0.4664.45"
        }
