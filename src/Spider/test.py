import requests
import pymongo
import json

"""
:Author:    iWorld
:Create:    2021/11/19 16:34
:GitHub:    https://github.com/DEADUTHBE/cczunosqltwo
:Abstract:  知乎热榜爬虫
"""
# %%
myclient = pymongo.MongoClient("mongodb://localhost:27017/")
mydb = myclient["hotSearch"]
mycol = mydb["ZhiHu"]
mycol.drop()
url = "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/total?limit=50&mobile=true"
questionUrl = "https://www.zhihu.com/question/{}"
headers = {
    'User-Agent': "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) " +
                  "AppleWebKit/605.1.15 (KHTML, like Gecko) " +
                  "Version/13.0.3 Mobile/15E148 Safari/604.1 Edg/96.0.4664.45"
}
# %%
req = requests.get(url, headers=headers)
hotSearch = req.text
hotSearch = json.loads(hotSearch)
# %%
hotSearch = hotSearch["data"]
# %%
hotDict = {}
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
    hotDict["questionUrl"] = questionUrl.format(hot["target"]["id"])
    # 存入MongoDB
    mycol.insert_one(hotDict)
    print(questionUrl)
